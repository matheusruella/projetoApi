package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Perfil;
import com.example.demo.service.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	
	@PostMapping
	public Perfil inserir(@RequestBody Perfil perfil) { // Agora o JSON será mapeado corretamente
	    return perfilService.inserir(perfil);
	}
	
	@GetMapping
	public Perfil buscar(Long id) {
		return perfilService.buscar(id);
	}
	
}
