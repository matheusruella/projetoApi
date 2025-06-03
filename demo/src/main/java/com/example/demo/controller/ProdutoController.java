package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.ProdutoRespondeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Produto;
import com.example.demo.service.ProdutoService;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ProdutoRespondeDTO inserir(@RequestBody Produto produto) {
		System.out.println(produto);
		return produtoService.inserir(produto);
	}
	
	@GetMapping
	public List<Produto> listar(){
		return produtoService.listar();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> alterar(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoService.alterar(id, produto);
	}
}
