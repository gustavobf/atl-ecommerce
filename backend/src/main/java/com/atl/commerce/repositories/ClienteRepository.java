package com.atl.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atl.commerce.entities.Cliente;
import com.atl.commerce.entities.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public Cliente findByUsuario(Usuario usuario);

}
