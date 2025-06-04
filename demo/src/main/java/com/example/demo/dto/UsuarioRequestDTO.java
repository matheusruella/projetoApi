package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Usuario;

import com.example.demo.entity.UsuarioPerfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDTO {
	@NotBlank
	private String nome;
	@Email
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
	private EnderecoResponseDTO endereco;

	private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();

	public UsuarioRequestDTO() {
	}

	public UsuarioRequestDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.endereco = usuario.getEndereco().toDTO(usuario.getEndereco());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnderecoResponseDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResponseDTO endereco) {
		this.endereco = endereco;
	}

	public Set<UsuarioPerfil> getUsuarioPerfis() {
		return usuarioPerfis;
	}

	public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
}
