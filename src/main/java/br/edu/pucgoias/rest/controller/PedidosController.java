package br.edu.pucgoias.rest.controller;

import br.edu.pucgoias.domain.entity.Pedido;
import br.edu.pucgoias.rest.dto.PedidoDto;
import br.edu.pucgoias.service.PedidosService;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;

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
}
