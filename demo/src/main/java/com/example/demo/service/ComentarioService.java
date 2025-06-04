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

    /**
     * Salva um novo comentário diretamente como entidade.
     * @param comentario Objeto Comentario a ser salvo.
     * @return Comentario persistido no banco de dados.
     */
    public Comentario salvarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    /**
     * Salva um novo comentário a partir de um DTO.
     * @param dto Objeto ComentarioDTO contendo os dados do comentário.
     * @return Comentario persistido no banco de dados.
     */
    public Comentario salvarComentario(@Valid ComentarioDTO dto) {
        Comentario comentario = new Comentario(dto.getProdutoId(), dto.getClienteId(), dto.getTexto(), dto.getNota());
        return comentarioRepository.save(comentario);
    }

    /**
     * Recupera todos os comentários associados a um produto específico.
     * @param produtoId Identificador único do produto.
     * @return Lista de comentários do produto.
     */
    public List<Comentario> listarComentariosPorProduto(Long produtoId) {
        return comentarioRepository.findByProdutoId(produtoId);
    }

    /**
     * Recupera todos os comentários cadastrados no sistema.
     * @return Lista completa de comentários.
     */
    public List<Comentario> listarTodosComentarios() {
        return comentarioRepository.findAll();
    }
}