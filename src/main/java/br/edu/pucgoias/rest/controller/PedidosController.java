package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.ItemPedido;
import br.edu.pucgoias.domain.entity.Pedido;
import br.edu.pucgoias.domain.enums.StatusPedido;
import br.edu.pucgoias.rest.dto.AtualizacaoStatusPedidoDTO;
import br.edu.pucgoias.rest.dto.InformacoesItemPedidoDTO;
import br.edu.pucgoias.rest.dto.InformacoesPedidoDTO;
import br.edu.pucgoias.rest.dto.PedidoDto;
import br.edu.pucgoias.service.PedidosService;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {
    PedidosService service;

    public PedidosController(PedidosService service) {
        this.service = service;
    }

    /**
     *
     * @return o número do pedido salvo
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDto dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id).map(p -> converter(p))
                .orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado!"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder() //o metodo builder foi possivel por causa da anotacao @Builder usado no DTO
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name()) //.name serve para transformar o enum REALIZADO, por exemplo, em uma string
                .items(converter(pedido.getItemPedido()))
                .build();

    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacoesItemPedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPrecoUnitario())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());
    }

    /**
     * Aqui nao usamos o PutMapping pois essa ação atualizaria todo o objeto pedido.
     * Na verdade queremos atualizar apenas o atributo status
     * @param id
     */
    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                               @RequestBody AtualizacaoStatusPedidoDTO statusDto){
        String novoStatus = statusDto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

    }
}
