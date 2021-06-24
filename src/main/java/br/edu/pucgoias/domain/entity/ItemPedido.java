package br.edu.pucgoias.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_fk")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_fk")
    private Produto produto;

    @Column(name = "ipe_qtd")
    private Integer quantidade;


}
