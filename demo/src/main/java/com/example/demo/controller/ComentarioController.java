package com.example.demo.controller;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.entity.Comentario;
import com.example.demo.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@Tag(name = "Comentários", description = "Gerenciamento de comentários")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    @Operation(summary = "Envio de comentário do cliente para a empresa",
               description = "O cliente envia um comentário via Frontend, e a API processa e registra no banco de dados. A resposta retorna a confirmação do envio.")
    @ApiResponse(responseCode = "201", description = "Comentário registrado com sucesso")
    public ResponseEntity<Comentario> criarComentario(@Valid @RequestBody ComentarioDTO dto) {
        Comentario comentario = comentarioService.salvarComentario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
    }

    @GetMapping("/{produtoId}")
    @Operation(summary = "Listar comentários por produto", description = "Retorna todos os comentários de um produto específico")
    @ApiResponse(responseCode = "200", description = "Lista de comentários recuperada com sucesso")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable Long produtoId) {
        List<Comentario> comentarios = comentarioService.listarComentariosPorProduto(produtoId);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping
    @Operation(summary = "Listar todos os comentários", description = "Retorna todos os comentários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista completa de comentários")
    public ResponseEntity<List<Comentario>> listarTodosComentarios() {
        List<Comentario> comentarios = comentarioService.listarTodosComentarios();
        return ResponseEntity.ok(comentarios);
    }
}