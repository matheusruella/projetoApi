package com.patsan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patsan.entity.Produto;
import com.patsan.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto inserir(Produto produto) {
		return repository.save(produto);
	}
}
