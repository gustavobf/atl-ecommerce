package com.atl.commerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.CategoriaDTO;
import com.atl.commerce.entities.Categoria;
import com.atl.commerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

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

	public CategoriaDTO obterPorId(int id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return categoriaToDTO(categoria.get());
		} else {
			throw new RuntimeException("Não encontrado.");
		}
	}

	public int deletarPorId(int id) {
		categoriaRepository.deleteById(id);
		return id;
	}

	public CategoriaDTO novo(CategoriaDTO dto) {
		return categoriaToDTO(categoriaRepository.save(dtoToCategoria(dto)));
	}

	public CategoriaDTO atualizar(CategoriaDTO dto) {
		return categoriaToDTO(categoriaRepository.save(dtoToCategoria(dto)));
	}

	public List<CategoriaDTO> obterTodos() {
		return categoriaRepository.findAll().stream().map(this::categoriaToDTO).collect(Collectors.toList());

	}

}
