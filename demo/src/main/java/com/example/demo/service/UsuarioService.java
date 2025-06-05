package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.MailConfig;
import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.entity.UsuarioPerfil;
import com.example.demo.exception.UsuarioException;
import com.example.demo.repository.UsuarioPerfilRepository;
import com.example.demo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PerfilRepository perfilRepository;

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
	    if (repository.findByEmail(usuario.getEmail()).isPresent()) {
	        throw new UsuarioException("Email já cadastrado!");
	    }

	    Usuario usuarioEntity = new Usuario();
	    usuarioEntity.setNome(usuario.getNome());
	    usuarioEntity.setEmail(usuario.getEmail());
	    usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));

	    for(UsuarioPerfil up : usuario.getUsuarioPerfis()){
			up.setUsuario(usuarioEntity);
			up.setPerfil(perfilRepository.findById(up.getPerfil().getId()).orElseThrow(()-> new RuntimeException("Perfil não encontrado!")));
			up.setDataCriacao(LocalDate.now());
		}

		repository.save(usuarioEntity);
		System.out.println(usuarioEntity.getUsuarioPerfis());
	    usuarioPerfilRepository.saveAll(usuario.getUsuarioPerfis());

	    return new UsuarioResponseDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getEmail());
	}
}
