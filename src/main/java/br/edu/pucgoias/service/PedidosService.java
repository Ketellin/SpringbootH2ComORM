package br.edu.pucgoias.service;


import br.edu.pucgoias.domain.entity.Pedido;
import br.edu.pucgoias.domain.enums.StatusPedido;
import br.edu.pucgoias.rest.dto.PedidoDto;

import java.util.Optional;

public interface PedidosService {
    public Pedido salvar(PedidoDto dto);
    public Optional<Pedido> obterPedidoCompleto(Integer id);
    public void atualizaStatus(Integer id, StatusPedido statusPedido);
}
