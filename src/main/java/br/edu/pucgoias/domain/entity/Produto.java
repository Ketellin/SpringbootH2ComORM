package br.edu.pucgoias.domain.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter //Estou indicando para o compilador que o lombok irá inserir os gets e sets
@Setter // Todas essas anotations pode ser substituidas pela anotacoa @Data
@ToString
@EqualsAndHashCode
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pro_descricao", length = 230)
    @NotEmpty(message = "O campo descrição é obrigatório!")
    private String descricao;

    @Column(name = "pro_preco")
    @NotNull(message = "O campo Preço é obrigatório!")
    private BigDecimal precoUnitario;


}
