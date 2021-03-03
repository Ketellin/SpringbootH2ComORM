package br.edu.pucgoias.domain.repositorio;

import br.edu.pucgoias.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Mudanças na classe para usar a interface do repositório Spring Data
 */
/*
Modificar para interface e extender de JpaRepository passando dois parâmetros:
A entidade e o tipo do dado que identifica o objeto
Retirar o EntityManager pois ele está encapsulado dentro do JpaRepository
 */
public interface ClientesDao extends JpaRepository <Cliente, Integer>{

    List<Cliente> findByNomeLike(String nome);
}
