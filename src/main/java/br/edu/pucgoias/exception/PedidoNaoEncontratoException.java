package br.edu.pucgoias.exception;

public class PedidoNaoEncontratoException extends RuntimeException {

    public PedidoNaoEncontratoException() {
        super("Pedido não Encontrato!");
    }
}
