package com.foro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foro.Services.TopicoService;
import com.foro.models.Topicos.Topico;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	TopicoService service;
	
	@GetMapping
	public ResponseEntity<Page<Topico>> getTopicos(@PageableDefault(size = 2) Pageable pageable){
		Page<Topico>topicos = service.getTopicosPaginados(pageable);
		return ResponseEntity.ok(topicos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Topico> getTopico(@PathVariable Long id){
		Topico topico = service.getTopico(id);
		if(topico != null)return ResponseEntity.ok(topico);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Topico> saveTopico(@RequestBody Topico topico){
		Topico newTopico = service.addTopico(topico);
		return ResponseEntity.created(null).body(newTopico);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Topico> updateTopico(@PathVariable Long id,
			@RequestBody Topico topico){
		
		Topico topicoUpdated = service.updateTopico(id);
		
		if(topicoUpdated == null) ResponseEntity.noContent().build();
		
		if(topico.getTitle() != null) {
			topicoUpdated.setTitle(topico.getTitle());
		}
		if(topico.getMessage() != null) {
			topicoUpdated.setMessage(topico.getTitle());
		}
		if(topico.getUsuario() != null) {
			topicoUpdated.setUsuario(topico.getUsuario());
		}
		if(topico.getCourse() != null) {
			topicoUpdated.setCourse(topico.getCourse());
		}
		if(topico.getStatus() != null) {
			topicoUpdated.setStatus(topico.getStatus());
		}
		service.addTopico(topicoUpdated);
		return ResponseEntity.ok(topicoUpdated);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Topico> deleteTopico(@PathVariable Long id){
		service.deleteTopico(id);
		return ResponseEntity.noContent().build();
	}
		
}
