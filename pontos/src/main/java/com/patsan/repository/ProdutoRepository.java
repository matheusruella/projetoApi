package com.patsan.repository;

	import com.patsan.entity.Produto;
	import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
