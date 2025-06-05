package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario inserir(Usuario cliente, Long usuarioId) {

		
		// Buscar o usuário e associar
				Usuario usuario = usuarioRepository.findById(usuarioId)
						.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));

				cliente.setUsuario(usuario);
		
		Usuario salvo = repository.save(cliente);
		

		// envio de e-mail após cadastrar
		emailService.enviarEmail(salvo.getEmail(), "Usuario cadastrado!",
				"Olá " + salvo.getNome() + ", seu cadastro foi efetuado com sucesso!");
		return salvo;
	}

	public ResponseEntity<Usuario> alterar(Long id, Usuario cliente, Long usuarioId) {

		if (repository.findById(id).isPresent()) {

			Usuario usuario = usuarioRepository.findById(usuarioId)
					.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));
			
			cliente.setUsuario(usuario);
			cliente.setId(id);
			
			Usuario atualizado = repository.save(cliente);

			// Envio de e-mail de atualização
			emailService.enviarEmail(atualizado.getEmail(), "Cadastro atualizado!",
					"Olá " + atualizado.getNome() + ", seu cadastro foi atualizado com sucesso!");

			return ResponseEntity.ok(atualizado);
		}
		return ResponseEntity.notFound().build();
	}

}
