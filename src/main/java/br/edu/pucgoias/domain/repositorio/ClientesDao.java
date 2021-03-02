package br.edu.pucgoias.domain.repositorio;

import br.edu.pucgoias.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//O spring entende que a classe faz operações com o banco de dados e também traduzirá as exception geradas pelo banco
//Com essa anotaçao o spring acionará a feature ExeptionTranslate
@Repository
public class ClientesDao {

    //Anotação para que o SpringBoot injet uma instância da classe JdbcTemplate
    @Autowired
    /*
    A classe JdbcTemplate trabalha com sql nativo e por isso precisaremos de scripts sql, já que
    ele é uma configuração jdbc comum.
     */
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager; //é a interface usada para fazer as operações do banco de dados.


    private static String SELECT_ALL = "select * from cliente";

    private static String SELECT_POR_NOME = "select * from cliente where nome like ?";

    private static String UPDATE = "update cliente set nome = ? where id = ?";

    private static String DELETE = "delete from cliente where id = ?";

    //O EntityManager precisa de uma transação para executar as operações no bd
    @Transactional
    public Cliente Salvar(Cliente cliente){
        entityManager.persist(cliente);
        /*
        Após executar o persist, a entidade cliente passará para o estado MANAGED, ou seja,
        gerenciado pelo JPA.
        Antes de ser persistida, o estado era transient
         */
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        /*
        Se a entidade cliente a ser atualizada, não estiver sincronizada com a cache do EntityManager, isto é,
        estiver diferente de quando o objeto cliente foi consultado, o merge irá sincronizar o objeto cliente
        com o gerenciado pelo EntityManager. Posteriormente a anotação Transactional executa o
        commite da transação.
         */
        entityManager.merge(cliente);

        return cliente;
    }

    @Transactional
    public void apagar(Cliente cliente){
        if(!entityManager.contains(cliente))
            cliente = entityManager.merge(cliente);

        entityManager.remove(cliente);
    }

    @Transactional
    public void apagar(Integer id){
        Cliente cli = entityManager.find(Cliente.class, id);
        apagar(cli);
    }

    @Transactional
    public List<Cliente> obterTodos(){
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }


    @Transactional(readOnly = true) //Estamos dizendo que essa transação é somente leitura. Faz com que a transação fique mais rápida
    public List<Cliente> buscarPorNome(String nome){
        String jpql = "select c from Cliente c" +
                "       where c.nome like ?1";

        TypedQuery<Cliente> tq = entityManager.createQuery(jpql,Cliente.class);

        tq.setParameter(1,"%" + nome + "%");

        return tq.getResultList();
    }
}
