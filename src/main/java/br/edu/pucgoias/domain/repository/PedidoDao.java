package br.edu.pucgoias.domain.repository;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoDao extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);

    @Query(" SELECT P FROM Pedido P LEFT JOIN FETCH P.itemPedido WHERE P.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
