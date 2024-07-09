package com.foro.Services;

import java.util.List;

import com.foro.models.Usuario.Usuario;

public interface IUsuarioService {
	public List<Usuario> getUsuarios();
	
	public Usuario getUsuario(Long usuario_id);
	
	public Usuario addUsuario(Usuario usuario);
	
	public Usuario updateUsuario(Long usuario_id);
	
	public void deleteUsuario(Long usuario_id);
	
}
