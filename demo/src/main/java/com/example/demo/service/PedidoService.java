package com.example.demo.service;

import com.example.demo.dto.PedidoRespondeDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Endereco;
import com.example.demo.enums.PedidoStatus;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Pedido;
import com.example.demo.entity.Produto;
import com.example.demo.repository.PedidoRepository;

import java.util.Optional;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public PedidoRespondeDTO inserir(PedidoRespondeDTO dto) {
		if (dto.getId() != null && repository.findById(dto.getId()).isPresent()) {
			throw new RuntimeException("Pedido já cadastrado");
		}


		Cliente cliente = clienteRepository.findById(dto.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


		Endereco endereco = enderecoRepository.findById(dto.getEnderecoId())
				.orElseThrow(() -> new RuntimeException("Endereço não encontrado"));


		Pedido pedido = new Pedido();
		pedido.setQuantidade(dto.getQuantidade());
		pedido.setPreco(dto.getPreco());
		pedido.setCliente(cliente);
		pedido.setEndereco(endereco);
		pedido.setProdutos(dto.getProdutos());
		pedido.setStatus(dto.getStatus() != null ? dto.getStatus() : PedidoStatus.PENDENTE);


		Pedido salvo = repository.save(pedido);


		return new PedidoRespondeDTO(
				salvo.getId(),
				salvo.getQuantidade(),
				salvo.getPreco(),
				salvo.getCliente().getId(),
				salvo.getEndereco().getId(),
				salvo.getStatus(),
				salvo.getProdutos()
		);
	}
	
	public ResponseEntity<PedidoRespondeDTO> alterar(Long id, PedidoRespondeDTO pedido) {

		Optional<Pedido> pedidoOptional = repository.findById(id);
		if (pedidoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Pedido pedidoEntity = pedidoOptional.get();
		Cliente cliente = clienteRepository.findById(pedido.getClienteId()).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
		pedidoEntity.setQuantidade(pedido.getQuantidade());
		pedidoEntity.setPreco(pedido.getPreco());
		pedidoEntity.setCliente(cliente);
		pedidoEntity.setProdutos(pedido.getProdutos());
		Pedido atualizado = repository.save(pedidoEntity);
		PedidoRespondeDTO respostaDTO = new PedidoRespondeDTO(
				atualizado.getId(),
				atualizado.getQuantidade(),
				atualizado.getPreco(),
				atualizado.getCliente().getId(),
				atualizado.getEndereco().getId(),
				atualizado.getStatus(),
				atualizado.getProdutos()
		);
		return ResponseEntity.ok(respostaDTO);
	}
}
