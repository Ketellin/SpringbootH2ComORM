package br.edu.pucgoias.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO - Data Transfer Object
 * É um objeto de transicao para receber ou enviar dados usado para mapear objetos
 * com propriedades simples que atendam a uma determinada necessidade
 * Esse objeto será usado para receber dados da requisiçao e receber o objeto modificado, ou nao
 * por algum método.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDto> itens;
}
