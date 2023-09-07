package com.atl.commerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atl.commerce.dtos.ProdutoDTO;
import com.atl.commerce.entities.Produto;
import com.atl.commerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaService categoriaService;

	public List<ProdutoDTO> obterTodos() {
		return repository.findAll().stream().map(this::produtoToDTO).collect(Collectors.toList());
	}

	private ProdutoDTO produtoToDTO(final Produto produto) {
		final ProdutoDTO dto = new ProdutoDTO();
		dto.setId(produto.getId());
		dto.setNome(produto.getNome());
		dto.setDescricao(produto.getDescricao());
		dto.setImagem(produto.getImagem());
		dto.setPreco(produto.getPreco());
		dto.setCategoria(categoriaService.categoriaToDTO(produto.getCategoria()));
		return dto;
	}

	private Produto dtoToProduto(final ProdutoDTO dto) {
		final Produto produto = new Produto();
		produto.setId(dto.getId());
		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setImagem(dto.getImagem());
		produto.setPreco(dto.getPreco());
		produto.setCategoria(categoriaService.dtoToCategoria(dto.getCategoria()));
		return produto;
	}

	public ProdutoDTO obterPorId(int id) {
		Optional<Produto> produto = repository.findById(id);
		if (produto.isPresent()) {
			return produtoToDTO(produto.get());
		} else {
			throw new RuntimeException("Não encontrado.");
		}
	}

	public int deletarPorId(int id) {
		repository.deleteById(id);
		return id;
	}

	public ProdutoDTO novo(ProdutoDTO dto) {
		return produtoToDTO(repository.save(dtoToProduto(dto)));
	}

	public ProdutoDTO atualizar(ProdutoDTO dto) {
		return produtoToDTO(repository.save(dtoToProduto(dto)));
	}

}
