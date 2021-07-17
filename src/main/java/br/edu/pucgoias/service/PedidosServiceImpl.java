package br.edu.pucgoias.service;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.entity.ItemPedido;
import br.edu.pucgoias.domain.entity.Pedido;
import br.edu.pucgoias.domain.entity.Produto;
import br.edu.pucgoias.domain.repository.ClientesDao;
import br.edu.pucgoias.domain.repository.ItemsPedidoDao;
import br.edu.pucgoias.domain.repository.PedidoDao;
import br.edu.pucgoias.domain.repository.ProdutoDao;
import br.edu.pucgoias.exception.RegraNegocioException;
import br.edu.pucgoias.rest.dto.ItemPedidoDto;
import br.edu.pucgoias.rest.dto.PedidoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//RequiredArgsConstructor: anotacao do lambok que irá gerar o construtor dos argumentos
//obrigatórios, ou seja, os final, pois sao instanciados
//                      no momento da construcao da classe
@Service
@RequiredArgsConstructor
public class PedidosServiceImpl implements PedidosService{
    public final PedidoDao pedidoDao;
    public final ClientesDao cliDao;
    public final ProdutoDao proDao;
    public final ItemsPedidoDao itemPedDao;

    //Substitui o construtor abaixo pela anotacao @RequiredArgsConstructor
    /*public PedidosServiceImpl(PedidoDao pedidoDao, ClientesDao clienteDao) {

        this.pedidoDao = pedidoDao;
        this.cliDao = clienteDao; //Injecao do clienteDao
    }*/


    @Override
    @Transactional //Significa que o pedido e seus itens serão salvos de maneira completa.
                   //Ocorrendo qualquer erro, um rollback será executado. Salva tudo ou nada.
    public Pedido salvar(PedidoDto dto) {
        Integer idCliente = dto.getCliente();
        Cliente cli = cliDao.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inexistente!"));
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cli);
        List<ItemPedido> itensPedidos = converterItens( pedido, dto.getItens());
        pedidoDao.save(pedido);
        itemPedDao.saveAll(itensPedidos);
        pedido.setItemPedido(itensPedidos);
        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDto> itensPedido){
        if(itensPedido.isEmpty())
            throw new RegraNegocioException("Não é possível salvar um pedido sem seus itens!");

        return itensPedido.stream()
                .map(dto -> {
                    Produto produto = proDao.findById(dto.getProduto())
                            .orElseThrow(() ->
                                    new RegraNegocioException("Código de produto inexistente! "
                                            + dto.getProduto()));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                })//map retornará uma stream de ItemPedido que precisa ser transformada
                  //em um List de ItemPedido. Para isso usamos o metodo collect
                .collect(Collectors.toList());
    }
}
