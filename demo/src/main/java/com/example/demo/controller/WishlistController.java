package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Wishlist;
import com.example.demo.entity.WishlistResponseDTO;
import com.example.demo.service.WishlistService;

@RestController
@RequestMapping("listadedesejos")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<Wishlist> criar(@RequestBody WishlistResponseDTO dto){
        return ResponseEntity.ok(wishlistService.criarWishlist(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> atualizar(@PathVariable Long id, @RequestBody WishlistResponseDTO dto){
        return ResponseEntity.ok(wishlistService.atualizr(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        wishlistService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Wishlist>> listarPorCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(wishlistService.listarPorCliente(clienteId));
    }
}
