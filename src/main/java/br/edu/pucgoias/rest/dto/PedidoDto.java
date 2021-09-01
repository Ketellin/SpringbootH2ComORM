package br.edu.pucgoias.rest.dto;

import br.edu.pucgoias.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO - Data Transfer Object
 * É um objeto de transicao para receber ou enviar dados usado para mapear objetos
 * com propriedades simples que atendam a uma determinada necessidade
 * Esse objeto será usado para receber dados da requisiçao e receber o objeto modificado, ou nao
 * por algum método. Será usado para salvar o pedido e portanto todos os objetos que compõe essa classe
 * devem ser tratados
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {
    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;
    @NotNull(message = "Campo total do pedido é obrigatório")
    private BigDecimal total;
    //Anotação personalizada dentro do projeto
    @NotEmptyList(message = "Pedido não pode ser realizado sem os itens.")
    private List<ItemPedidoDto> itens;
}
