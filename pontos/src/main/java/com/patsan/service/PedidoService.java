package com.patsan.service;


import com.patsan.dto.PedidoDto;
import com.patsan.entity.Cliente;
import com.patsan.entity.Produto;
import com.patsan.entity.Pedido;
import com.patsan.repository.ClienteRepository;
import com.patsan.repository.ProdutoRepository;
import com.patsan.repository.PedidoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

	@Service
	public class PedidoService {
	    private final ClienteRepository clienteRepo;
	    private final ProdutoRepository produtoRepo;
	    private final PedidoRepository pedidoRepo;

	    public PedidoService(ClienteRepository clienteRepo, ProdutoRepository produtoRepo, PedidoRepository pedidoRepo) {
	        this.clienteRepo = clienteRepo;
	        this.produtoRepo = produtoRepo;
	        this.pedidoRepo = pedidoRepo;
	    }

	    public Pedido realizarPedido(PedidoDto dto) {
	        Cliente cliente = clienteRepo.findById(dto.clienteId()).orElseThrow();
	        List<Produto> produtos = produtoRepo.findAllById(dto.produtosId());

	        BigDecimal total = produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);

	        boolean usarPontos = dto.usarPontos();
	        int pontosNecessarios = total.intValue() * 10;

	        if (usarPontos && cliente.getPontos() >= pontosNecessarios) {
	            cliente.setPontos(cliente.getPontos() - pontosNecessarios);
	        } else if (usarPontos) {
	            throw new RuntimeException("Pontos insuficientes");
	        } else {
	            cliente.setPontos(cliente.getPontos() + total.intValue() * 10);
	        }

	        Pedido pedido = new Pedido();
	        pedido.setCliente(cliente);
	        pedido.setTotal(total);
	        pedido.setPagoComPontos(usarPontos);

	        clienteRepo.save(cliente);
	        return pedidoRepo.save(pedido);
	    }
	}


