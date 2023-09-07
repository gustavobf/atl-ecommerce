package com.atl.commerce.services;

import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.CategoriaDTO;
import com.atl.commerce.entities.Categoria;

@Service
public class CategoriaService {

	protected CategoriaDTO categoriaToDTO(final Categoria categoria) {
		final CategoriaDTO dto = new CategoriaDTO();
		dto.setId(categoria.getId());
		dto.setNome(categoria.getNome());
		return dto;
	}

	protected Categoria dtoToCategoria(final CategoriaDTO dto) {
		final Categoria categoria = new Categoria();
		categoria.setId(dto.getId());
		categoria.setNome(dto.getNome());
		return categoria;
	}

}
