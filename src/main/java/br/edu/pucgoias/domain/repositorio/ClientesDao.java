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

    /**
     * Exemplo de Query Methods do Spring boot
     * Convensão: findByNome_Propriedade_BuscarLike sendo que Nome_Propriedade_Buscar é o
     * filtro que se deseja aplicar o filtro. Na classe Cliente temos a propriedade "nome".
     * Escrever findByNomeLike é o mesmo que escrever SELECT C FROM Cliente WHERE C.nome LIKE :nome
     * O compilador irá criar o jpql automaticamente
     * @param nome
     * @return
     */
    List<Cliente> findByNomeLike(String nome);

    /*
    Os parametros devem aparecer na ordem escrita no Query Method
     */
    //List<Cliente> findByNomeOrIdOOrderById(String nome, Integer id);

    /*
    O findOne deve ser usado para chaves como CPF, matricula, etc para que seja
    retornado apenas um objeto. Se retornar uma lista, ocorrerá excessão
     */
    //Cliente findOneByNome(String nome);

    boolean existsByNome(String nome);
}
