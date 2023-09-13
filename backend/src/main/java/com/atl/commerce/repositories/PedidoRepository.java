package com.atl.commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atl.commerce.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Query("SELECT ped FROM Pedido ped WHERE cliente.id = ?1 and ped.dataPedido is null")
	public Pedido getByClienteId(int clienteId);

	@Query("SELECT ped FROM Pedido ped WHERE cliente.id = ?1 and ped.dataPedido is not null")
	public List<Pedido> getByClienteIdComDataPedido(int clienteId);


}
