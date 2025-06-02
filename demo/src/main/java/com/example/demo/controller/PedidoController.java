package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PedidoRespondeDTO;
import com.example.demo.service.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	public PedidoRespondeDTO inserir(@RequestBody PedidoRespondeDTO pedido){
		return pedidoService.inserir(pedido);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoRespondeDTO> alterar(@PathVariable Long id, @RequestBody PedidoRespondeDTO pedido) {
		return pedidoService.alterar(id, pedido);
	}
	
	@GetMapping("/{id}")
	public PedidoRespondeDTO listarPorId (@PathVariable Long id) {
		return pedidoService.listarPorId(id);
	}
}