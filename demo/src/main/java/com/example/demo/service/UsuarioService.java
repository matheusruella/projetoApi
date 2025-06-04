package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.MailConfig;
import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.UsuarioPerfil;
import com.example.demo.entity.pk.UsuarioPerfilPK;
import com.example.demo.exception.UsuarioException;
import com.example.demo.repository.UsuarioPerfilRepository;
import com.example.demo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private MailConfig mailConfig;
	
	
	public List<UsuarioResponseDTO> listar() {
		List<Usuario> usuarios = repository.findAll();
		List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();

		for (Usuario usuario : usuarios) {
			usuariosDTO.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
		}
		return usuariosDTO;
	}

	@Transactional
	public UsuarioResponseDTO inserir(UsuarioRequestDTO usuario) {
		Optional<Usuario> u = repository.findByEmail(usuario.getEmail());
		if (u.isPresent()) {
			throw new UsuarioException("Email já cadastrado!");
		}
		
		Usuario usuarioEntity = new Usuario();
		usuarioEntity.setNome(usuario.getNome());
		usuarioEntity.setEmail(usuario.getEmail());
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioEntity.setSenha(usuario.getSenha());
	    
		// Salva o usuário
	    usuarioEntity = repository.save(usuarioEntity);
		
	    Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
	    
		for (Long idPerfil : usuario.getUsuarioPerfis()) {
			UsuarioPerfil up = new UsuarioPerfil();
	        up.setId(new UsuarioPerfilPK());

			
			up.getId().setPerfil(perfilService.buscar(idPerfil));
			up.getId().setUsuario(usuarioEntity);
			up.setDataCriacao(LocalDate.now());
		
	        usuarioPerfis.add(up);

		}
		
		usuarioPerfilRepository.saveAll(usuarioPerfis);
		
		//mailConfig.enviar(usuarioEntity.getEmail(),"Confimação de cadastro", usuario.toString());

		return new UsuarioResponseDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getEmail());
	}
}