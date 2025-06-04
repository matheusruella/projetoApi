package com.example.demo.entity.pk;

import java.util.Objects;

import com.example.demo.entity.Perfil;
import com.example.demo.entity.Usuario;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class UsuarioPerfilPK {

	@ManyToOne
	@JoinColumn(name = "id_usuario") //PK
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_perfil")  //PK
	private Perfil perfil;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	//indexar os dados - ex: cpf vira (1a) pra facilitar a comparação pelo equals

	@Override
	public int hashCode() {
		return Objects.hash(perfil, usuario);
	}

	//comparar e evitar duplicidade

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		UsuarioPerfilPK other = (UsuarioPerfilPK) obj;
		return Objects.equals(perfil, other.perfil) && Objects.equals(usuario, other.usuario);
	}

}
