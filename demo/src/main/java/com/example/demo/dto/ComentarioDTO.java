package com.example.demo.dto;


import jakarta.validation.constraints.*;

public class ComentarioDTO {

    private Long produtoId;
    private Long clienteId;

    @Size(min = 5, max = 500, message = "O texto do comentário deve ter entre 5 e 500 caracteres.")
    private String texto;

    @Min(value = 1, message = "A nota mínima permitida é 1.")
    @Max(value = 5, message = "A nota máxima permitida é 5.")
    private int nota;

    /**
     * Construtor padrão necessário para a serialização/deserialização.
     */
    public ComentarioDTO() {}

    /**
     * Construtor para inicializar um novo objeto ComentarioDTO.
     *
     * @param produtoId ID do produto relacionado ao comentário.
     * @param clienteId ID do cliente que escreveu o comentário.
     * @param texto Conteúdo do comentário.
     * @param nota Avaliação do produto (1 a 5 estrelas).
     */
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