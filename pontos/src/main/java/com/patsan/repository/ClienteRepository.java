	package com.patsan.repository;

	import com.patsan.entity.Cliente;
	import org.springframework.data.jpa.repository.JpaRepository;

	public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	}

