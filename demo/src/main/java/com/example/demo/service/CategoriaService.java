package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria inserir (Categoria categoria) {

		return repository.save(categoria);
	}
	
	public ResponseEntity<Categoria> alterar(Long id, Categoria categoria) {
		
		if (repository.findById(id).isPresent()) {
			categoria.setId(id);
			return ResponseEntity.ok(repository.save(categoria));
		}
		return ResponseEntity.notFound().build();
	}
}

