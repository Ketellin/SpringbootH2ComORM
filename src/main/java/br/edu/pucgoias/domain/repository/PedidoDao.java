package br.edu.pucgoias.domain.repository;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoDao extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
