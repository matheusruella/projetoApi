package com.example.demo.dto;

<<<<<<< HEAD
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Produto;

import java.util.List;

public record CategoriaResponseDTO(Long id, String tipo, List<Produto> produtos) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getTipo(), categoria.getProdutos());
    }
}
=======
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
>>>>>>> a3b7473af56cd74c4b1a054ba881189876ca8fce
