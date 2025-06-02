package com.example.demo.service;

import com.example.demo.dto.CategoriaRequestDTO;
import com.example.demo.dto.CategoriaResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	@Transactional
	public CategoriaResponseDTO inserir (CategoriaRequestDTO dto) {
		Categoria cat = new Categoria();
		System.out.println(dto.getTipo());
		cat.setTipo(dto.getTipo());
		repository.save(cat);
		return new CategoriaResponseDTO(cat);
	}
	
	public ResponseEntity<Categoria> alterar(Long id, Categoria categoria) {
		
		if (repository.findById(id).isPresent()) {
			categoria.setId(id);
			return ResponseEntity.ok(repository.save(categoria));
		}
		return ResponseEntity.notFound().build();
	}
}

