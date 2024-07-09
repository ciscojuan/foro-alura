package com.foro.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.models.Usuario.Usuario;
import com.foro.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository repository;
	

	public Page<Usuario> getUsuariosPaginados(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Usuario> getUsuarios() {
		return repository.findAll();
	}

	@Override
	public Usuario getUsuario(Long usuario_id) {
		Usuario usuario = repository.findById(usuario_id).orElse(null);
		return usuario;
	}

	@Transactional
	@Override
	public Usuario addUsuario(Usuario usuario) {
		return repository.save(usuario);

	}

	@Transactional
	@Override
	public Usuario updateUsuario(Long usuario_id) {

		return repository.findById(usuario_id).get();
	}

	@Override
	public void deleteUsuario(Long usuario_id) {
		repository.deleteById(usuario_id);

	}

}
