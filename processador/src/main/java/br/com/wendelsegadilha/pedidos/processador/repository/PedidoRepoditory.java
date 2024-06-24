package br.com.wendelsegadilha.pedidos.processador.repository;

import br.com.wendelsegadilha.pedidos.processador.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepoditory extends JpaRepository<Pedido, UUID> {
}
