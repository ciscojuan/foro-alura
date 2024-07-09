package com.foro.models.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAuth(
		@Email
		@NotBlank
		String email,
		@NotBlank
		String password) {

}
