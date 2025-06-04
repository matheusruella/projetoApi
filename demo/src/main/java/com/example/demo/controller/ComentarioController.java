package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.entity.Comentario;
import com.example.demo.service.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;
    
    @Operation(summary = "Envio de comentário do cliente para a empresa",
            description = "O cliente envia um comentário via Frontend, e a API processa e registra no banco de dados. A resposta retorna a confirmação do envio.")
 @ApiResponses(value = { 
     @ApiResponse(responseCode = "201", content = {
         @Content(schema = @Schema(implementation = Comentario.class), mediaType = "application/json") }, description = "Comentário registrado com sucesso."),
     @ApiResponse(responseCode = "400", description = "Erro na requisição - dados inválidos"),
     @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
     @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
     @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
     @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
 })

    // Criar um novo comentário
    @PostMapping
    public ResponseEntity<Comentario> criarComentario(@Valid @RequestBody ComentarioDTO dto) {
        Comentario comentario = comentarioService.salvarComentario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
    }

    // Listar comentários por produto
    @GetMapping("/{produtoId}")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable Long produtoId) {
        List<Comentario> comentarios = comentarioService.listarComentariosPorProduto(produtoId);
        return ResponseEntity.ok(comentarios);
    }

    // Listar todos os comentários
    @GetMapping
    public ResponseEntity<List<Comentario>> listarTodosComentarios() {
        List<Comentario> comentarios = comentarioService.listarTodosComentarios();
        return ResponseEntity.ok(comentarios);
    }
}