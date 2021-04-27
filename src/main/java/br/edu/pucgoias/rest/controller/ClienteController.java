package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.repository.ClientesDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Refatorando para tornar o código restFull
 */
//Assim como o @Controller, a @RestController também é usada como cotroller porem com mais funcionalidades
//Ela já vem com a anotação @ResponseBody evitando o uso repetido.
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesDao clientesDao;


    public ClienteController(ClientesDao clientesDao) {
        this.clientesDao = clientesDao;
    }


    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        // Mais informações sobre Optional.orElseThrow
        // https://medium.com/@racc.costa/optional-no-java-8-e-no-java-9-7c52c4b797f1
        return clientesDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){

        return clientesDao.save(cliente);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clientesDao.findById(id)
                .map(cli -> {
                    clientesDao.delete(cli);
                    return cli;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente){

        clientesDao.findById(id).map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientesDao.save(cliente);
            return  clienteExistente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    /**
     * Esse método utiliza Query by Example (QBE)
     * QBE é uma técnica de consulta amigável que permite a criação de consultas dinâmicas e não exige
     * que você escreva consultas que contenham nomes de campo.
     *
     * A API Query by Example consiste em três partes:
     ** Probe: O exemplo real de um objeto de domínio com campos preenchidos.
     ** ExampleMatcher: ExampleMatcher Contém detalhes sobre como combinar campos específicos.
     * Ele pode ser reutilizado em vários exemplos.
     ** Example: Um Example consiste no objeto de domínio e no ExampleMatcher. Ele é usado para criar a consulta.
     *
     * Origem: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example.introduction
     *
     * @param filtro recebe um objeto cliente com os atributos a serem usados como filtro, preenchido
     * @return ResponseEntity que representa um código de resposta ao cliente que fez a requisição
     */
    @GetMapping("")
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching() //informando da combinação de atributos para filtrar
                .withIgnoreCase() //Nos campos string, ignorar se está maiúsculo ou minúsculo
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //Nos campos string consultar %filtro%
        //Example.of coleta apenas as propriedades preenchidas e cria o objeto ex
        Example ex = Example.of(filtro, matcher);
        return clientesDao.findAll(ex);
    }

}
