package com.example.demo.controller;

import com.example.demo.dto.EnderecoResponseDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Endereco;
import com.example.demo.exception.EnderecoException;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.EnderecoService;
<<<<<<< HEAD
import jakarta.validation.Valid;
=======

import java.util.List;

import org.serratec.backend.dto.UsuarioResponseDTO;
>>>>>>> a3b7473af56cd74c4b1a054ba881189876ca8fce
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
<<<<<<< HEAD
    public Cliente inserir(@RequestBody @Valid Cliente cliente) throws Exception {
=======
    public Cliente inserir(@RequestBody Cliente cliente) throws EnderecoException {
>>>>>>> a3b7473af56cd74c4b1a054ba881189876ca8fce

        Endereco endereco = enderecoService.buscarEnderecoPorCep(cliente.getCep());
        cliente.setEndereco(endereco);
        return clienteService.inserir(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> alterar (@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        return clienteService.alterar(id, cliente);
    }
    
}
