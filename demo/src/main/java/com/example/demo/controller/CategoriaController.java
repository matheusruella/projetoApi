package com.example.demo.controller;

import com.example.demo.dto.CategoriaRequestDTO;
import com.example.demo.dto.CategoriaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> a3b7473af56cd74c4b1a054ba881189876ca8fce

import com.example.demo.entity.Categoria;
import com.example.demo.service.CategoriaService;



@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping
<<<<<<< HEAD
	public CategoriaResponseDTO inserir(@RequestBody CategoriaRequestDTO categoria) {
		System.out.println("PAssou aqui");
		return categoriaService.inserir(categoria);

=======
	public ResponseEntity<Categoria> inserir(@RequestBody Categoria categoria) {
		Categoria novaCategoria = categoriaService.inserir(categoria);
		return ResponseEntity.ok(novaCategoria);
>>>>>>> a3b7473af56cd74c4b1a054ba881189876ca8fce
	}
	
//	@PostMapping
//    public Categoria inserir(@RequestBody Categoria categoria) {
//        return categoriaService.inserir(categoria);
//    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> alterar(@PathVariable Long id, @RequestBody Categoria categoria) {
		return categoriaService.alterar(id, categoria);
	}
	@GetMapping
	public ResponseEntity<String> listar() {
		return ResponseEntity.ok("Teste");
	}
}
