package br.edu.pucgoias.domain.repositorio;

import br.edu.pucgoias.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /*
    Consultas usando hql
     */
    //@Query(value = "SELECT C FROM Cliente C WHERE C.nome LIKE :nome")
    /*
    Consultas usando sql nativo
     */
    @Query(value = " SELECT * FROM cliente C WHERE C.nome LIKE '%:nome%' ", nativeQuery = true)
    List<Cliente> enontrarPorNome(@Param("nome") String nome);

    //Query Method
    void deleteByNome(String nome);

    
    @Query(value = " delete from Cliente c where c.nome = :nome")
    @Modifying //deve ser colocada todas as vezes que ocorrer atualizações na base de dados
    void apagarPorNome(String nome);
    boolean existsByNome(String nome);


}
