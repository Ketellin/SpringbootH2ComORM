package br.edu.pucgoias.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Passará a ser gerenciada pelo container do Springboot
//é a url base para acessar a classe
//Toda requisição feita para /api/clientes será respondida pelo ClienteController
@RequestMapping("/api/clientes")
public class ClienteController {

    //url para acesso ao método. Entre chaves, é o parâmetro que deverá ser passado para o método
    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
    //Informa que o retorno do método é o corpo da resposta da requisição
    @ResponseBody
    //PathVariable é a anotação para definir qual variável vou receber como parâmetro
    //Dessa maneira, a variável nome será injetada em nomeCli
    public String helloCliente(@PathVariable("nome") String nomeCli){
        return String.format("Hello %s ", nomeCli);
    }
}
