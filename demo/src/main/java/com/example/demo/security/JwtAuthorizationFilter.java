package com.example.demo.security;

import java.io.IOException;

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

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private JwtUtil jwtUtil;  //pegar o nome, se token ok
	private UserDetailsService userDetailsService;  //busca usuario, pelo nome da verificação

    //construtor
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {  //pedido chega e isso é chamado
		String header = request.getHeader("Authorization");   // pega o cabeçalho da requisição
		if (header != null && header.startsWith("Bearer ")) {  // se existe o cabeçalho, que começa com bearer
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));  //captura o token e valida
			System.out.println("Teste1");  //mostra que chegou neste ponto
			if (auth != null) {   //se autenticação é não nula, token está ok
				SecurityContextHolder.getContext().setAuthentication(auth);   //guarda o usuario e aponta o que é liberado p/ ele
				System.out.println("Teste2");   //mostra que chegou neste ponto
			}
		}
		chain.doFilter(request, response);   //pedido vai pra proxima fila, pra evitar travamentos
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {   //ver de quem é o token
		if (jwtUtil.isValidToken(token)) {
			System.out.println(token);
			String username = jwtUtil.getUsername(token);   //se vãlido, mostra o nome
			System.out.println("Teste2.1");   //mostra que chegou neste ponto
			UserDetails user = userDetailsService.loadUserByUsername(username);    // pega as restrições e permissões do usuario, para liberar ou não
			System.out.println("Teste3");   //mostra que chegou neste ponto
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());   //retorna user, senha nula, pq token é usado e as permissoes
		}
		return null;   //se inválido, retorna nulo.
	}
}