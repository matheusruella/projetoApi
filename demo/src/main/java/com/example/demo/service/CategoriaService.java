package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Categoria;
import com.example.demo.repository.CategoriaRepository;
	
	@Service
	public class CategoriaService {
	
		@Autowired
		private CategoriaRepository repository;
			
		
	public Categoria inserir (Categoria categoria) {
		return repository.save(categoria);		
	}
	}
		
		

