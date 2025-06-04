package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComentarioDTO {

    @NotNull(message = "O produtoId não pode ser nulo.")
    private Long produtoId;

    @NotNull(message = "O clienteId não pode ser nulo.")
    private Long clienteId;

    @NotNull(message = "O texto do comentário não pode ser nulo.")
    @Size(min = 5, max = 500, message = "O texto deve ter entre 5 e 500 caracteres.")
    private String texto;

    @NotNull(message = "A nota do comentário não pode ser nula.")
    private int nota;

    public ComentarioDTO() {}

    public ComentarioDTO(Long produtoId, Long clienteId, String texto, int nota) {
        this.produtoId = produtoId;
        this.clienteId = clienteId;
        this.texto = texto;
        this.nota = nota;
    }

    // Getters e Setters
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

}