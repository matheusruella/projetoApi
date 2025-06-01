package com.example.demo.controller;


import com.example.demo.entity.Endereco;
import com.example.demo.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping("{cep}")
    public ResponseEntity<Endereco> buscarCep(@PathVariable String cep) throws Exception {
        var endereco = service.buscarEnderecoPorCep(cep);
        if (endereco != null) {
            return ResponseEntity.ok(endereco);
        }
        return ResponseEntity.notFound().build();
    }


}
