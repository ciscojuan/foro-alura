package com.foro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foro.Services.RespuestaService;
import com.foro.models.Respuestas.Respuesta;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

	@Autowired
	RespuestaService service;
	
	@GetMapping
	public ResponseEntity<Page<Respuesta>> getRespuestas(Pageable pageable){
		Page<Respuesta> respuestas = service.getRespuestasPaginados(pageable);
		return ResponseEntity.ok(respuestas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Respuesta> getRespuesta(@PathVariable Long id){
		Respuesta respuesta = service.getRespuesta(id);
		if(respuesta == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(respuesta);
	}
	
	@PostMapping
	public ResponseEntity<Respuesta> saveRespuesta(@RequestBody Respuesta respuesta){
		Respuesta newRespuesta = service.addRespuesta(respuesta);
		return ResponseEntity.created(null).body(newRespuesta);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Respuesta> updateRespuesta(@PathVariable long id,
			@RequestBody Respuesta respuesta) throws Exception{
		
		Respuesta respuestaUpdated = service.updateRespuesta(id);
		
		if(respuestaUpdated == null) ResponseEntity.noContent().build();
		
		if(respuesta.getTopico() != null) {
			respuestaUpdated.setTopico(respuesta.getTopico());
		}
		if(respuesta.getUsuario() != null) {
			respuestaUpdated.setUsuario(respuesta.getUsuario());
		}
		if(respuesta.getMessage() != null){
			respuestaUpdated.setMessage(respuesta.getMessage());
		}
		if(respuesta.getSolve() != null) {
			respuestaUpdated.setSolve(respuesta.getSolve());
		}
		
		service.addRespuesta(respuestaUpdated);
		
		return ResponseEntity.ok(respuestaUpdated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Respuesta> deleteRespuesta(@PathVariable Long id){
		service.deleteRespuesta(id);
		return ResponseEntity.noContent().build();
	}
}
