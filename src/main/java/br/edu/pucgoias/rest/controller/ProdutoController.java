package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.Produto;
import br.edu.pucgoias.domain.repository.ProdutoDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Controller
@RequestMapping("/api/pedidos")
public class ProdutoController {

    private ProdutoDao produtoDao;

    //Fiz a injeção de dependência
    public ProdutoController (ProdutoDao produtoDao){
        this.produtoDao = produtoDao;
    }

    @GetMapping("/{id}") //Obter os dados
    @ResponseBody
    public ResponseEntity getProdutoById(@PathVariable Integer id){

        Optional<Produto> pr = produtoDao.findById(id);
        if(pr.isPresent())
            return ResponseEntity.ok(pr.get());

        return  ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save (@RequestBody Produto produto){
        Produto pro = produtoDao.save(produto);
        return ResponseEntity.ok(pro);
    }


}
