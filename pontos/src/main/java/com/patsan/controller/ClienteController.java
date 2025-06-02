package com.patsan.controller;

import com.patsan.entity.Cliente;
import com.patsan.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes") // Mapeamento endpoints clientes
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	// Endpoint listar clientes
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> clientes = clienteService.listarTodosClientes();
		return ResponseEntity.ok(clientes);
	}

	// Endpoint buscar cliente por ID
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
		return clienteService.buscarClientePorId(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Endpoint criar novo cliente
	@PostMapping
	public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
		Cliente novoCliente = clienteService.criarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
	}

	// Endpoint adicionar pontos x cliente
	@PutMapping("/{id}/adicionar-pontos")
	public ResponseEntity<?> adicionarPontos(@PathVariable Long id, @RequestParam Integer quantidade) {
		try {
			Cliente clienteAtualizado = clienteService.adicionarPontos(id, quantidade);
			return ResponseEntity.ok(clienteAtualizado);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Endpoint resgatar pontos x cliente
	@PutMapping("/{id}/resgatar-pontos")
	public ResponseEntity<?> resgatarPontos(@PathVariable Long id, @RequestParam Integer quantidade) {
		try {
			Cliente clienteAtualizado = clienteService.resgatarPontos(id, quantidade);
			return ResponseEntity.ok(clienteAtualizado);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Endpoint atualizar cliente existente
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado) {
		try {
			Cliente cliente = clienteService.atualizarCliente(id, clienteAtualizado);
			return ResponseEntity.ok(cliente);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Endpoint deletar cliente
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		try {
			clienteService.deletarCliente(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}