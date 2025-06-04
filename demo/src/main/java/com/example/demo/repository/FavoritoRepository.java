package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Favorito;
import com.example.demo.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    Favorito findFavoritoByClienteAndProduto(Cliente cliente, Produto produto);
    List<Favorito> findAllByCliente(Cliente cliente);
    Optional<Favorito> findByClienteAndProduto(Cliente cliente, Produto produto);
}
