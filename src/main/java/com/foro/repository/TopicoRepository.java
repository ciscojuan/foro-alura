package com.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foro.models.Topicos.Topico;

public interface TopicoRepository  extends JpaRepository<Topico, Long>{

}
