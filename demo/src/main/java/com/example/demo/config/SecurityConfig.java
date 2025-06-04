package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtAuthorizationFilter;
import com.example.demo.security.JwtUtil;

// classe de segurança
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	// cria e valida tokens
	@Autowired
	private JwtUtil jwtUtil;

	// pega os dados do usuário
	@Autowired
	private UserDetailsService userDetailsService;

	// regras de acesso
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Desativa a proteção contra CSRF, pelo uso de token
				.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Libera acesso de outras portas

				// quem pode acessar cada coisa

				.authorizeHttpRequests(requests -> requests
						
						.requestMatchers("/public/**").permitAll() // pode todo mundo ver

						// H2 - BD
						.requestMatchers("/h2-console/**").permitAll() // mexer no H2 sem login

						// Categoria
						.requestMatchers(HttpMethod.POST, "/categorias").hasRole("ADMIN") // Só admin adiciona categoria
						.requestMatchers(HttpMethod.PUT, "/categorias/**").hasRole("ADMIN") // Só admin a altera
						.requestMatchers(HttpMethod.GET, "/categorias").permitAll() // Todos listam

						// Cliente
						.requestMatchers(HttpMethod.POST, "/clientes/**").permitAll() // Todos criam um cliente
						.requestMatchers(HttpMethod.PUT, "/clientes/**").permitAll() // Todos alteram dados

						// ContatoEmail
						.requestMatchers(HttpMethod.POST, "/contatos").permitAll() // todos podem enviar enviar ctt
						.requestMatchers(HttpMethod.GET, "/contatos/**").hasRole("ADMIN") // Só admin altera no DB

						// Endereço
						.requestMatchers(HttpMethod.GET, "/enderecos/**").permitAll() // Todos buscam endereço por CEP

						// Pedido
						.requestMatchers(HttpMethod.POST, "/pedidos").permitAll() // todos fazem pedido
						.requestMatchers(HttpMethod.PUT, "/pedidos/**").hasAnyRole("ADMIN", "USUARIO") // Só admin altera pedido
						.requestMatchers(HttpMethod.GET, "/pedidos/**").permitAll() // Todos podem consultar por ID

						// Produto
						.requestMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN") // Só admin adicionam produtos
						.requestMatchers(HttpMethod.PUT, "/produtos/**").hasRole("ADMIN") // Só admin podem os alterar
						.requestMatchers(HttpMethod.GET, "/produtos").permitAll() // Todos veem os produtos

						// Usuario
						.requestMatchers(HttpMethod.GET, "/usuarios").permitAll() // Todos listam
						.requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Só admin podem os alterar
						
						// Perfil
						.requestMatchers(HttpMethod.GET, "/perfis").permitAll() // Todos listam
						.requestMatchers(HttpMethod.POST, "/perfis").permitAll() // Só admin podem os alterar

						//Favoritos
						.requestMatchers(HttpMethod.POST, "/favoritos/**").permitAll()
		                .requestMatchers(HttpMethod.GET, "/favoritos/**").permitAll()
		                .requestMatchers(HttpMethod.PUT, "/favoritos/**").permitAll()
		                .requestMatchers(HttpMethod.DELETE, "/favoritos/**").permitAll()
		               
		                //Wishlist Complementar
		                .requestMatchers(HttpMethod.POST, "/listadedesejos").permitAll()
		                .requestMatchers(HttpMethod.GET, "/listadedesejos/{id}").permitAll()
		                .requestMatchers(HttpMethod.PUT, "/listadedesejos/{id}").permitAll()
		                .requestMatchers(HttpMethod.DELETE, "/listadedesejos/{id}").permitAll()
		                .requestMatchers(HttpMethod.GET, "/listadedesejos/cliente/{clienteId}").permitAll()
						
						.anyRequest().authenticated() // só usa se estiver logado
				)

				// sempre precisa do token para entrar

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// Libera o console do BD

				.headers(headers -> headers.frameOptions().disable());

		// filtro do login, gera token
		http.addFilterBefore(
				new JwtAuthenticationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
				UsernamePasswordAuthenticationFilter.class);

		// autoriza o token
		http.addFilterBefore(new JwtAuthorizationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil,
				userDetailsService), UsernamePasswordAuthenticationFilter.class);

		// retorna tudo configurado
		return http.build();
	}

	// gerenciador de autenticação do Spring
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// quem pode acessar o back
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		// portas onde o front roda
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:3000",
				"http://localhost:2000"));

		// métodos que o frontend pode usar
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

		// Cabeçalhos permitidos
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

		// Permitindo credenciais (se necessário para autenticação com cookies ou JWT)
		corsConfiguration.setAllowCredentials(true);

		// Aplica regras para as rotas
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());

		return source; // Retorna tudo pronto
	}
}