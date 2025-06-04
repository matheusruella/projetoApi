package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Endereco;
import com.example.demo.exception.EnderecoException;
import com.example.demo.service.ClienteService;
import com.example.demo.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	EnderecoService enderecoService;

	@PostMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.CREATED)

	public Cliente inserir(@RequestBody Cliente cliente, @PathVariable Long usuarioId) throws EnderecoException {

		Endereco endereco = enderecoService.buscarEnderecoPorCep(cliente.getCep());
		cliente.setEndereco(endereco);
		return clienteService.inserir(cliente, usuarioId);
	}

	@PutMapping("/{id}/{usuarioId}") //passa id cliente e seu id usuario
	public ResponseEntity<Cliente> alterar(@PathVariable Long id, @PathVariable Long usuarioId,
			@RequestBody @Valid Cliente cliente) {
		return clienteService.alterar(id, cliente, usuarioId);
	}

}
