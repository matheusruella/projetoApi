package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.entity.Comentario;
import com.example.demo.repository.ComentarioRepository;
import jakarta.validation.Valid;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    // Salvar um novo comentário
    public Comentario salvarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Listar comentários por produto
    public List<Comentario> listarComentariosPorProduto(Long produtoId) {
        return comentarioRepository.findByProdutoId(produtoId);
    }

    // Listar todos os comentários
    public List<Comentario> listarTodosComentarios() {
        return comentarioRepository.findAll();
    }

    public Comentario salvarComentario(@Valid ComentarioDTO dto) {
        Comentario comentario = new Comentario(dto.getProdutoId(), dto.getClienteId(), dto.getTexto(), dto.getNota());
        return comentarioRepository.save(comentario);
    }
}

