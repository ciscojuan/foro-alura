package com.foro.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foro.models.Topicos.Topico;
import com.foro.repository.TopicoRepository;

@Service
public class TopicoService implements ITopicoService {

	@Autowired	TopicoRepository repository;
	
	
	public Page<Topico> getTopicosPaginados(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Override
	public List<Topico> getTopicos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Topico getTopico(Long topico_id) {
		Topico topico = repository.findById(topico_id).orElse(null);
		return topico;
	}

	@Override
	public Topico addTopico(Topico topico) {
		return repository.save(topico);
	}

	@Override
	public Topico updateTopico(Long topico_id) {
		return repository.findById(topico_id).get();
	}

	@Override
	public void deleteTopico(Long topico_id) {
		repository.deleteById(topico_id);
		
	}

}
