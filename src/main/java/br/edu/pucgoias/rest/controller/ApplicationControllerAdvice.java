package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.Pedido;
import br.edu.pucgoias.exception.PedidoNaoEncontratoException;
import br.edu.pucgoias.exception.RegraNegocioException;
import br.edu.pucgoias.rest.ApiErrors;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ControllerAdvice coloca a classe dentro do contexto do spring com o objetivo de tratar excessões
 * através de ExceptionHandler (tratador de excessão) que possibilita retornar códigos de status
 * e uma mensagem adequada para o usuário
 * A anotação ControllerAdvice pode ser substituída pela RestControllerAdvice que já contém as anotações
 * (@ControllerAdvice e @ResponseBody)
 */
@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(RegraNegocioException.class) //Marcará o método abaixo para ser um tratador de exceções
                      //Nesse caso, a exceção RegraNegocioException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException exc){
        //Todas as vezes que o projeto lançar RegraNegocioException o código abaixo será executado
        //por causa do Handler
        String mensagemErro = exc.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontratoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundExeption(PedidoNaoEncontratoException exception){
        return new ApiErrors(exception.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //MethodArgumentNotValidException é lançada quando a validação de um argumento anotado com @Valid falha
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult() //carrega os dados de validação e o que falhou
                .getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
