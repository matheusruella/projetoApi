package com.example.demo.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Usuario implements UserDetails { // Implementa UserDetails para integrar com Spring Security
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Gera ID automaticamente no banco
    private Long id;
    private String nome;
    private String email;
    private String senha; 

    // um usuário pode usar vários perfis, relaciona usuario com perfil
    @OneToMany(mappedBy = "id.usuario")
    private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_endereco") // Define a chave estrangeira
    private Endereco endereco;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Set<UsuarioPerfil> getUsuarioPerfis() {
        return usuarioPerfis;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + "]";
    }

    // Retorna as permissões do usuário com base nos perfis que ele possui
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuarioPerfis.stream()
            .map(up -> new SimpleGrantedAuthority(up.getId().getPerfil().getNome().toUpperCase())) // Agora acessa via `getId()`
            .collect(Collectors.toSet());
    }

    // indica se a conta está ativa e válida
    @Override
    public String getPassword() { // Retorna a senha do usuário
        return senha;
    }
    @Override
    public String getUsername() { // Define o login como o email
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true; // Indica que a conta não está expirada
    }
    @Override
    public boolean isAccountNonLocked() {
        return true; // Indica que a conta não está bloqueada
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Indica que as credenciais não expiraram
    }
    @Override
    public boolean isEnabled() {
        return true; // Indica que a conta está ativa
    }

}