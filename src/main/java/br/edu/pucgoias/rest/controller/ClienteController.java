package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
O RequestMapping mapeia uma rota, através de um verbo http(RequestMethod.GET),
e passa a tratar a requisição do cliente
 */
@Controller
@RequestMapping("/api/clientes")
public class ClienteController {


    @RequestMapping(
            value = {"/hello/{nome}","/api/hello"},//value recebe um array de strings
            method = RequestMethod.POST, //O cliente solicita alterar o servidor
            consumes = {"application/json", "application/xml"}, //A alteração pode ser um arquivo json ou xml.
                                                                // Dados recebidos no servidor
            produces = {"application/json", "application/xml"} //Servidor produz um cliente em json ou xml
            //o cliente que fará a requisição definirá no contentType o tipo de padrão, ou seja, json ou xml
    )
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCli, @RequestBody Cliente cliente){
        //A anotação RequestBody define que o arquivo json ou xml deve ser um cliente
        return String.format("Hello %s ", nomeCli);
    }
}
