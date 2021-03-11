package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.repository.ClientesDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
O RequestMapping mapeia uma rota, através de um verbo http(RequestMethod.GET),
e passa a tratar a requisição do cliente
 */
@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesDao clientesDao;

    /*
    A injeção do DAO acontecerá pois o springboot perceberá a anotação Controller, perceberá também
     o controlador recebendo parâmetros e
    instaciará clientesDao, ou usará uma instancia já pronta do container
     */
    public ClienteController(ClientesDao clientesDao) {
        this.clientesDao = clientesDao;
    }

    //Por padrão o springboot trabalha com json

    @GetMapping("/{id}") //Obter dados. Faz o mesmo que a anotação RequestMapping, porem com o GET definido. Veja o código.
    @ResponseBody //Mapeará o objeto Cliente em um objeto Json. Se não for colocado, a requisição irá
    //aguardar uma string que seria o endereço de uma página web. Como estamos trabalhando com REST, precisamos da anotação
    public ResponseEntity getClienteById(@PathVariable Integer id){
        //PathVariable define que o parâmetro id será recebido via requisição http, na url
        //Se vc quiser usar o nome do parâmetro na url diferente do parâmetro no método, a anotação PathVariable
        //deverá acompanhar o nome do parâmetro na url.

        /*
        Esse metodo não foi implementado pois ele já existe no JpaRepository que foi extendido pela
        classe ClientesDao
        O retorno do método é um Optional pois pode ou não existir o cliente com o id especificado
         */
        Optional<Cliente> cliente = clientesDao.findById(id);
        if(cliente.isPresent()) {
            //Representa a resposta ao cliente
            //ResponseEntity<Cliente> responseEntity = new ResponseEntity<>(cliente.get(), HttpStatus.OK);
            //A LINHA ACIMA EQUIVALE A
            return ResponseEntity.ok(cliente.get());
            //o ok retornará o código 200. O método get retornará o cliente contido em Optional
        }
        return ResponseEntity.notFound().build();//retornará o código 440
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        /*
        Não confundir ResponseBody com RequestBody. Esse último é o que entra de dados, ou seja, o corpo da
        Requisição.
        O primeiro é o que será retornado, ou seja, Resposta.
         */
        Cliente cli = clientesDao.save(cliente);
        return ResponseEntity.ok(cli);

    }
}

/*
OBSERVAÇÃO:
Conseguimos testar pelo browser por ser uma requisição GET que usa url.
 */