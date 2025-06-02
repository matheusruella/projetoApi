package com.patsan.controller;

	import com.patsan.dto.PedidoDto;
	import com.patsan.entity.Pedido;
	import com.patsan.service.PedidoService;
	import jakarta.validation.Valid;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	
	@RestController
	@RequestMapping("/api/pedidos")
	public class PedidoController {
	    private final PedidoService service;

	    public PedidoController(PedidoService service) {
	        this.service = service;
	    }

	    @PostMapping
	    public ResponseEntity<?> realizarPedido(@RequestBody @Valid PedidoDto dto) {
	        try {
	            Pedido pedido = service.realizarPedido(dto);
	            return ResponseEntity.ok(pedido);
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	}

	
	
	

