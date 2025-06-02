package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaResponseDTO {
    @NotBlank
	private String tipo;

	public CategoriaResponseDTO() {
		super();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}