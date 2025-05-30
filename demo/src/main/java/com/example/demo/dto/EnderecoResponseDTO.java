package com.example.demo.dto;

import  com.example.demo.entity.Endereco;

public record EnderecoResponseDTO(String logradouro, String bairro, String localidade, String uf) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getLocalidade(),
                endereco.getUf());
    }
}
