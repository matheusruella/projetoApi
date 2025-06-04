package com.example.demo.entity;

import java.time.LocalDate;

import com.example.demo.entity.pk.UsuarioPerfilPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UsuarioPerfil {

	@EmbeddedId
	private UsuarioPerfilPK id = new UsuarioPerfilPK();

	private LocalDate dataCriacao;
	

	public UsuarioPerfil() {
	}
	
	public UsuarioPerfil(UsuarioPerfilPK id, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
	}

	public UsuarioPerfilPK getId() {
		return id;
	}

	public void setId(UsuarioPerfilPK id) {
		this.id = id;
	}


	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Perfil getPerfil() {
	    return this.id.getPerfil();
	}

	public void setPerfil(Perfil perfil) {
	    this.id.setPerfil(perfil);
	}

	public Usuario getUsuario() {
	    return this.id.getUsuario();
	}

	public void setUsuario(Usuario usuario) {
	    this.id.setUsuario(usuario);
	}

}