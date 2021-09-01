package br.edu.pucgoias.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //modelo de geração automatica da chave
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    //No momento de fazer uma validação de uma instancia de cliente, essa anotação irá garantir
    // que a propriedade nome Não seja "" ou null. Se for o caso, uma exceção será lançada com
    // a mensagem passada como parametro para message
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo cpf é obrigatório!")
    @CPF(message = "Informe um CPF válido!")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.nome = nome;
        this.id = id;
    }

}

