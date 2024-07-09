package com.foro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foro.Services.UsuarioService;
import com.foro.models.Usuario.Usuario;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> getUsuarios(Pageable pageable){
		Page<Usuario> usuarios = service.getUsuariosPaginados(pageable);
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
		Usuario usuario = service.getUsuario(id);
		if(usuario != null)return ResponseEntity.ok(usuario);
		else
			return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
		Usuario newUser = service.addUsuario(usuario);
		return ResponseEntity.created(null).body(newUser);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, 
			@RequestBody Usuario usuarioRecibido){
		
		Usuario user = service.updateUsuario(id);
		
		if(user == null) return ResponseEntity.noContent().build();
		
		if (usuarioRecibido.getName() != null) {
            user.setName(usuarioRecibido.getName());
        }
        if (usuarioRecibido.getLastname() != null) {
            user.setLastname(usuarioRecibido.getLastname());
        }
        if (usuarioRecibido.getEmail() != null) {
            user.setEmail(usuarioRecibido.getEmail());
        }
        if (usuarioRecibido.getPassword() != null) {
            user.setPassword(usuarioRecibido.getPassword());
        }
        if (usuarioRecibido.getPerfil() != null) {
            user.setPerfil(usuarioRecibido.getPerfil());
        }
		service.addUsuario(user);
		return ResponseEntity.ok(user);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable Long id){
		service.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}
}
