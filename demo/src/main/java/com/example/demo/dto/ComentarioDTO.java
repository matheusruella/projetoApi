package com.example.demo.dto;

public class ComentarioDTO {
    private Long produtoId;
    private Long clienteId;
    private String texto;
    private int nota;

    // Construtor padrão
    public ComentarioDTO() {}

    // Construtor com parâmetros
    public ComentarioDTO(Long produtoId, Long clienteId, String texto, int nota) {
        this.produtoId = produtoId;
        this.clienteId = clienteId;
        this.texto = texto;
        this.nota = nota;
    }

    // Getters e Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}