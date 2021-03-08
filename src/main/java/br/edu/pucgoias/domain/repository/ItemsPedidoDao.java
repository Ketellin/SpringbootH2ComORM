package br.edu.pucgoias.domain.repository;

import br.edu.pucgoias.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ItemsPedidoDao extends JpaRepository<ItemPedido, Integer> {
}
