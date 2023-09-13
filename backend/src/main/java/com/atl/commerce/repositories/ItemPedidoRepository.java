package com.atl.commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atl.commerce.entities.ItemPedido;
import com.atl.commerce.entities.Produto;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

	@Query("SELECT item FROM ItemPedido item WHERE pedido.id = ?1")
	public List<ItemPedido> getByPedidoId(int pedidoId);

	@Query("SELECT produto FROM ItemPedido ip WHERE ip.id = ?1")
	public Produto getProdutosByItemPedidoId(int pedidoId);


}