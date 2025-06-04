package com.example.demo.security;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Essa classe verifica se o usuário tem um token válido antes de permitir o acesso
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private JwtUtil jwtUtil; // Responsável por validar tokens JWT
    private UserDetailsService userDetailsService; // Usado para buscar informações do usuário no banco

    // Construtor para inicializar o filtro com as dependências necessárias
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                                  UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // Método que intercepta todas as requisições e verifica se o token JWT é válido
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        String header = request.getHeader(HttpHeaders.AUTHORIZATION); // Captura o cabeçalho "Authorization"
        
        if (header != null && header.startsWith("Bearer ")) { // Confere se há um token válido no cabeçalho
            String token = header.substring(7); //  Remove "Bearer " e pega apenas o token
            UsernamePasswordAuthenticationToken auth = getAuthentication(token); // ✅ Autentica o usuário

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth); // Guarda a autenticação no contexto de segurança
                logger.info("Usuário autenticado com sucesso!");
            } else {
                logger.warn("Falha na autenticação: token inválido.");
            }
        }

        chain.doFilter(request, response); //  Passa para o próximo filtro para continuar a requisição
    }

    // Método que verifica a autenticidade do token e extrai as informações do usuário
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (!jwtUtil.isValidToken(token)) {
            logger.error("Token inválido ou expirado.");
            return null;
        }

        String username = jwtUtil.getUsername(token); //  Extrai o nome do usuário do token
        UserDetails user = userDetailsService.loadUserByUsername(username); // 🔍 Busca as informações do usuário no banco
        logger.info("Usuário identificado: " + username);

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); // 🔐 Retorna um usuário autenticado com suas permissões
    }
}