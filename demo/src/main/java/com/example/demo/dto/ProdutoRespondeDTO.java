package com.example.demo.dto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

public record ProdutoRespondeDTO(Long id, String nome, Double preco, Long categoria) {
    
    public ProdutoRespondeDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getCategoria().getId());
    }
}