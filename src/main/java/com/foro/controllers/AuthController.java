package com.foro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foro.models.Usuario.DatosAuth;
import com.foro.models.Usuario.Usuario;
import com.foro.utils.security.DatosJWT;
import com.foro.utils.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired 
	private AuthenticationManager authManager;
	
    @Autowired
    private TokenService tokenService;
    
	@PostMapping
	public ResponseEntity authentication(@RequestBody @Valid DatosAuth data) {
		 var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		 
	     var authenticion = authManager.authenticate(token);
	     
	     var tokenJWT = tokenService.generarToken((Usuario) authenticion.getPrincipal());
	     
	     System.out.println("Token JWT generado: " + tokenJWT); // Imprime el token JWT
	     
		return ResponseEntity.ok(new DatosJWT(tokenJWT));
	}
}
