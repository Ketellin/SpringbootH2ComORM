package br.edu.pucgoias.domain.entity;

import javax.persistence.*;

//Anotação usada para mapear uma classe para uma tabela no banco via JPA
//Aqui estamos dizendo para o JPA escanear essa classe e registrar ela como uma tabela do BD
@Entity
//A anotação abaixo é obrigatória somente se o nome da sua classe for diferente do nome da tabela
//@Table(name = "nome_tabela")
public class Cliente {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //modelo de geração automatica da chave
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.nome = nome;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
