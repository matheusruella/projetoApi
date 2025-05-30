package com.example.demo.entity;

import com.example.demo.enums.PedidoStatus;
import jakarta.persistence.*;


import java.util.List;

@Entity

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private Double preco;

    @OneToOne
    private Cliente cliente;

    @OneToOne
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
    joinColumns = @JoinColumn(name = "id_pedido"),
    inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtos;

	@Enumerated(EnumType.STRING)
	private PedidoStatus status;

	public Pedido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

}
