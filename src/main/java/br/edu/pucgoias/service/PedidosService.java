package br.edu.pucgoias.service;


import br.edu.pucgoias.domain.entity.Pedido;
import br.edu.pucgoias.rest.dto.PedidoDto;

public interface PedidosService {
    public Pedido salvar(PedidoDto dto);
}
