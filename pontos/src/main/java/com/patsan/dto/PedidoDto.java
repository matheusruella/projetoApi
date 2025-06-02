package com.patsan.dto;

import java.util.List;

public record PedidoDto(Long clienteId, List<Long> produtosId, boolean usarPontos) {}
	

