package com.patsan.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Pedido {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Cliente cliente;

	private BigDecimal total;
	private Boolean pagoComPontos = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Boolean getPagoComPontos() {
		return pagoComPontos;
	}

	public void setPagoComPontos(Boolean pagoComPontos) {
		this.pagoComPontos = pagoComPontos;
	}

}
