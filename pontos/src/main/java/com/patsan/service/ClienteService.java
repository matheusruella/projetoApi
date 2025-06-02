package com.patsan.service;

import com.patsan.entity.Cliente;
import com.patsan.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	// Método listar clientes
	public List<Cliente> listarTodosClientes() {
		return clienteRepository.findAll();
	}

	// Método buscar cliente por ID
	public Optional<Cliente> buscarClientePorId(Long id) {
		return clienteRepository.findById(id);
	}

	// Método criar cliente
	public Cliente criarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// Método adicionar pontos x cliente (fora de um pedido)
	public Cliente adicionarPontos(Long clienteId, Integer pontosParaAdicionar) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));

		if (pontosParaAdicionar < 0) {
			throw new IllegalArgumentException("Pontos para adicionar não podem ser negativos.");
		}

		cliente.setPontos(cliente.getPontos() + pontosParaAdicionar);
		return clienteRepository.save(cliente);
	}

	// Método resgatar pontos x cliente
	public Cliente resgatarPontos(Long clienteId, Integer pontosParaResgatar) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));

		if (pontosParaResgatar < 0) {
			throw new IllegalArgumentException("Pontos para resgatar não podem ser negativos.");
		}

		if (cliente.getPontos() < pontosParaResgatar) {
			throw new RuntimeException("Pontos insuficientes para resgate.");
		}

		cliente.setPontos(cliente.getPontos() - pontosParaResgatar);
		return clienteRepository.save(cliente);
	}

	// Método atualizar informações cliente (exceto pontos)
	public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
		return clienteRepository.findById(id).map(cliente -> {
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setEmail(clienteAtualizado.getEmail());
			return clienteRepository.save(cliente);
		}).orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
	}

	// Método deletar cliente
	public void deletarCliente(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new RuntimeException("Cliente não encontrado com ID: " + id);
		}
		clienteRepository.deleteById(id);
	}
}
