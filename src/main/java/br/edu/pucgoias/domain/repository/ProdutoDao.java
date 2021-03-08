package br.edu.pucgoias.domain.repository;

import br.edu.pucgoias.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDao extends JpaRepository<Produto, Integer> {
}
