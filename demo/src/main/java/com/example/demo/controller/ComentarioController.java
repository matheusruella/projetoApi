package com.example.demo.controller;
<<<<<<< HEAD
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> main

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.entity.Comentario;
import com.example.demo.service.ComentarioService;
<<<<<<< HEAD
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
=======

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/comentarios")
>>>>>>> main
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;
<<<<<<< HEAD

    @PostMapping
    @Operation(summary = "Envio de comentário do cliente para a empresa",
               description = "O cliente envia um comentário via Frontend, e a API processa e registra no banco de dados. A resposta retorna a confirmação do envio.")
    @ApiResponse(responseCode = "201", description = "Comentário registrado com sucesso")
=======
    
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
>>>>>>> main
    public ResponseEntity<Comentario> criarComentario(@Valid @RequestBody ComentarioDTO dto) {
        Comentario comentario = comentarioService.salvarComentario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
    }

<<<<<<< HEAD
    @GetMapping("/{produtoId}")
    @Operation(summary = "Listar comentários por produto", description = "Retorna todos os comentários de um produto específico")
    @ApiResponse(responseCode = "200", description = "Lista de comentários recuperada com sucesso")
=======
    // Listar comentários por produto
    @GetMapping("/{produtoId}")
>>>>>>> main
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable Long produtoId) {
        List<Comentario> comentarios = comentarioService.listarComentariosPorProduto(produtoId);
        return ResponseEntity.ok(comentarios);
    }

<<<<<<< HEAD
    @GetMapping
    @Operation(summary = "Listar todos os comentários", description = "Retorna todos os comentários cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista completa de comentários")
=======
    // Listar todos os comentários
    @GetMapping
>>>>>>> main
    public ResponseEntity<List<Comentario>> listarTodosComentarios() {
        List<Comentario> comentarios = comentarioService.listarTodosComentarios();
        return ResponseEntity.ok(comentarios);
    }
}