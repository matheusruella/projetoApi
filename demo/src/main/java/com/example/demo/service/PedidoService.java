package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Pedido;
import com.example.demo.entity.Produto;
import com.example.demo.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido inserir (Pedido pedido) {
		return repository.save(pedido);
	}
	
	public ResponseEntity<Pedido> alterar(Long id, Pedido pedido) {
		
		if (repository.findById(id).isPresent()) {
			pedido.setId(id);
			return ResponseEntity.ok(repository.save(pedido));
		}
		return ResponseEntity.notFound().build();
	}
}
