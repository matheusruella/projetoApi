package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.dto.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Classe que processa a autenticação e gera o token JWT
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {    

    private AuthenticationManager authenticationManager; // Gerencia a autenticação dos usuários
    private JwtUtil jwtUtil; // Gera e valida tokens JWT

    // Construtor que recebe as dependências necessárias
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Método chamado quando uma requisição de login é feita
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        try {
            // Lê os dados do login enviados no corpo da requisição (JSON)
            LoginDTO login = new ObjectMapper().readValue(request.getReader(), LoginDTO.class);

            // Cria um objeto de autenticação com nome e senha
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    login.getUsername(),
                    login.getPassword(),
                    new ArrayList<>() // Lista vazia, permissões serão carregadas depois
            );

            // Autentica o usuário e retorna o resultado
            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            // Se ocorrer um erro ao processar o login, retorna uma exceção
            throw new RuntimeException("Erro ao processar login!", e);
        }
    }

    // Método chamado quando o login é bem-sucedido
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException {
        
        // Obtém o nome do usuário autenticado
        String username = ((UserDetails) authResult.getPrincipal()).getUsername();

        // Gera um token JWT para esse usuário
        String token = jwtUtil.generateToken(username);

        // Define o token no cabeçalho da resposta HTTP
        response.setHeader("Authorization", "Bearer " + token);
        response.setHeader("access-control-expose-headers", "Authorization");

        // Envia o token como resposta no corpo da requisição para facilitar o uso pelo frontend
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"Bearer " + token + "\"}");
    }
}
