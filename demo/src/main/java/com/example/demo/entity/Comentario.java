package com.example.demo.entity;
<<<<<<< HEAD

=======
>>>>>>> main
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private Long clienteId;

<<<<<<< HEAD
    @Column(nullable = false, length = 500)
    private String texto;

    @Column(nullable = false)
    private int nota; // Avaliação de 1 a 5 estrelas
=======
    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private int nota; // De 1 a 5 estrelas
>>>>>>> main

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataPublicacao;

<<<<<<< HEAD
    /**
     * Construtor padrão necessário para JPA.
     */
    public Comentario() {}

    /**
     * Construtor para inicializar um novo comentário.
     * 
     * @param produtoId ID do produto comentado.
     * @param clienteId ID do cliente que fez o comentário.
     * @param texto Conteúdo do comentário.
     * @param nota Avaliação do produto (1 a 5).
     */
=======
    public Comentario() {}

>>>>>>> main
    public Comentario(Long produtoId, Long clienteId, String texto, int nota) {
        this.produtoId = produtoId;
        this.clienteId = clienteId;
        this.texto = texto;
        this.nota = nota;
        this.dataPublicacao = LocalDateTime.now();
    }

<<<<<<< HEAD
    /**
     * Define automaticamente a data de publicação antes de salvar no banco de dados.
     */
=======
>>>>>>> main
    @PrePersist
    public void prePersist() {
        this.dataPublicacao = LocalDateTime.now();
    }

    // Getters e Setters
<<<<<<< HEAD

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }
>>>>>>> main
}