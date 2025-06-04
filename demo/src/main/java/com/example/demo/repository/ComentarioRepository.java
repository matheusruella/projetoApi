package com.example.demo.repository;


<<<<<<< HEAD
import com.example.demo.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
=======
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Comentario;
>>>>>>> main
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByProdutoId(Long produtoId);
}