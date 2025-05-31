package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente inserir (Cliente cliente) {

		return repository.save(cliente);
	}
	
	public ResponseEntity<Cliente> alterar(Long id, Cliente cliente) {
		
		if (repository.findById(id).isPresent()) {
			cliente.setId(id);
			return ResponseEntity.ok(repository.save(cliente));
		}
		return ResponseEntity.notFound().build();
	}
}
