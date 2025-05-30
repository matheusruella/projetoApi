package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Produto;
import com.example.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto inserir (Produto produto) {
		return repository.save(produto);
	}
	
	public ResponseEntity<Produto> alterar(Long id, Produto produto) {
		
		if (repository.findById(id).isPresent()) {
			produto.setId(id);
			return ResponseEntity.ok(repository.save(produto));
		}
		return ResponseEntity.notFound().build();
	}
}
