package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.Produto;
import br.edu.pucgoias.domain.repository.ProdutoDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

//Assim como o @Controller, a @RestController também é usada como cotroller porem com mais funcionalidades
//Ela já vem com a anotação @ResponseBody evitando o uso repetido.
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoDao produtoDao;

    //Fiz a injeção de dependência
    public ProdutoController (ProdutoDao produtoDao){
        this.produtoDao = produtoDao;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save (@RequestBody @Valid Produto produto){
        return produtoDao.save(produto);

    }

    @GetMapping("/{id}") //Obter os dados
    public Produto getProdutoById(@PathVariable Integer id){
        System.out.println("Passou no controlador do cliente!");
        return produtoDao.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!")
        );

    }

    @GetMapping
    public List<Produto> buscarProdutosFiltros(Produto produto){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example ex = Example.of(produto, matcher);
        return produtoDao.findAll(ex);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto (@PathVariable Integer id, @RequestBody @Valid Produto produto){
        produtoDao.findById(id).map( produtoBD ->
                {
                    produto.setId(produtoBD.getId());
                    produtoDao.save(produto);
                    return produto;
                }
        ).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarProduto (@PathVariable Integer id){
        produtoDao.findById(id).map(produtoBD ->
                {
                    produtoDao.delete(produtoBD);
                    return Void.TYPE;
                }
        ).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

}



