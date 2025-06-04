package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Usuario;

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


	private Set<Long> usuarioPerfis = new HashSet<>();

	public UsuarioRequestDTO() {
		super();
	}

	public UsuarioRequestDTO(@NotBlank String nome, @Email String email, @NotBlank String senha,
			@NotBlank EnderecoResponseDTO endereco, Set<Long> usuarioPerfis) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}

	public Set<Long> getUsuarioPerfis() {
		return usuarioPerfis;
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

	public void setUsuarioPerfis(Set<Long> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
	

}
