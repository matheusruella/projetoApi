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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {    //faz o login e libera o token
    
    private AuthenticationManager authenticationManager;  //verificar se o login está certo
    private JwtUtil jwtUtil;   //criar o token 
    
    // filtra com as ferramentas necessárias
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // tenta fazer o login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        System.out.println("Tentativa de autenticação...");

        try {
           
			LoginDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            
            // Cria token com nome e senha
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    login.getUsername(),
                    login.getPassword(),
                    new ArrayList<>()  // permissões vazias, poderia passar
            );

            // verifica se o token é verdadeiro
            Authentication auth = authenticationManager.authenticate(authToken);
            
            // retorna se deu certo ou não
            return auth;
            
        } catch (IOException e) {
            // Se deu erro ao pegar o login
            throw new RuntimeException("Falha ao autenticar o cliente!", e);
        }
    }

    // usado quando o login está ok
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        System.out.println("Geração do token");
        
        // Pega o nome do usuário do login ativo
        String username = ((UserDetails) authResult.getPrincipal()).getUsername();
        
        // Gera um token para o usuario ativo
        String token = jwtUtil.generateToken(username);
        
        // Coloca o token do usuario na resposta
        response.addHeader("Authorization", "Bearer " + token);
        
        // Libera para ser lido pelo navegador
        response.addHeader("access-control-expose-headers", "Authorization");
    }
}