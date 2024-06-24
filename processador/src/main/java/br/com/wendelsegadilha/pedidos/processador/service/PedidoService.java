package br.com.wendelsegadilha.pedidos.processador.service;

import br.com.wendelsegadilha.pedidos.processador.entity.ItemPedido;
import br.com.wendelsegadilha.pedidos.processador.entity.Pedido;
import br.com.wendelsegadilha.pedidos.processador.repository.PedidoRepoditory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepoditory pedidoRepoditory;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepoditory pedidoRepoditory, ProdutoService produtoService, ItemPedidoService itemPedidoService) {
        this.pedidoRepoditory = pedidoRepoditory;
        this.produtoService = produtoService;
        this.itemPedidoService = itemPedidoService;
    }

    public void save(Pedido pedido) {

        // salvamos os produtos
        produtoService.save(pedido.getItens());

        // salvamos os itens do pedido
        List<ItemPedido> itemPedidos = itemPedidoService.save(pedido.getItens());

        // salvamos o pedido
        pedidoRepoditory.save(pedido);

        // atualiza o item pedido definindo o pedido ao qual ele faz parte
        itemPedidoService.updatedItemPedido(itemPedidos, pedido);

        logger.info("Pedido salvo: {}", pedido.toString());

    }

}
