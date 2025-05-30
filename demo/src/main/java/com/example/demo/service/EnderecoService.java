package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Endereco;
import com.example.demo.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	public Endereco inserir (Endereco endereco) {
		return repository.save(endereco);
	}
	
	public ResponseEntity<Endereco> alterar(Long id, Endereco endereco) {
		
		if (repository.findById(id).isPresent()) {
			endereco.setId(id);
			return ResponseEntity.ok(repository.save(endereco));
		}
		return ResponseEntity.notFound().build();
	}
}
