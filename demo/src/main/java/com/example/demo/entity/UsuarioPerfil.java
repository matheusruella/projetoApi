package com.example.demo.entity;

import java.time.LocalDate;

import com.example.demo.entity.pk.UsuarioPerfilPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioPerfil {

    @EmbeddedId
    private UsuarioPerfilPK id;
    
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false) // Define a chave estrangeira
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "perfil_id", insertable = false, updatable = false) // Evita duplicidade
    private Perfil perfil;
    
    public UsuarioPerfil(UsuarioPerfilPK id, LocalDate dataCriacao) {
        this.id = id; // Usa diretamente o que foi passado
        this.dataCriacao = dataCriacao;
    
	}

	public UsuarioPerfil() {
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

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioPerfilPK getId() {
		return id;
	}
	public Perfil getPerfil() {
        return perfil;
    }

}