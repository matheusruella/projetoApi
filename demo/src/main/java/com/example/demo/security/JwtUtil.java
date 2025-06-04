package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// fabricar tokens
@Component
public class JwtUtil {

    // Pega a chave secreta do config
    @Value("${auth.jwt-secret}")
    private String jwtSecret;

    //validade do token
    @Value("${auth.jwt-expiration-miliseg}")
    private Long jwtExpirationMiliseg;

    // Guarda a senha secreta modificada
    private SecretKey secretKey;

    // cria o token para o usuário
    public String generateToken(String username) {
    	
        // Cria a secreta a partir da senha salva
        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        System.out.println("Secret" + "-" + secretKey);

        //nome do user, data de validade, e assinatura do token
        return Jwts.builder()
                .setSubject(username)  //nome no token
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtExpirationMiliseg))  // Define vencimento do token
                .signWith(secretKey)  // põe a chave secreta no token
                .compact();  // empacota o token
    }
 
    // verifica se o token está ok
    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);  // Pega as informações do token

        if (claims != null) {
            String username = claims.getSubject();  // Pega o nome no token
            Date expirationDate = claims.getExpiration();  // vencimento do token
            Date now = new Date(System.currentTimeMillis());  // Data atual

            // Verifica nome e data de vencimento do token
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;  // Token ok
            }
        }
        return false;  // Token vencido
    }

//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", userDetails.getAuthorities().stream()
//            .map(GrantedAuthority::getAuthority)
//            .collect(Collectors.toList()));
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 20))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
    
    // pega o nome guardado no token
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();  // retorna o nome
        }
        return null;  // Se falhar, devolve vazio
    }

    // pega o que está guardado no token
    public Claims getClaims(String token) {
        // Usa a chave secreta p/ verificar o token
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // Usa a chave secreta da assinatura
                .build()
                .parseClaimsJws(token)  // verifica integridade do token
                .getBody();  // Pega o conteúdo interno
    }
}