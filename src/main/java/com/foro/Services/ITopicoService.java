package com.foro.Services;

import java.util.List;

import com.foro.models.Topicos.Topico;

public interface ITopicoService {
	
	public List<Topico> getTopicos();
	
	public Topico getTopico(Long topico_id);
	
	public Topico addTopico(Topico topico);
	
	public Topico updateTopico(Long topico_id);
	
	public void deleteTopico(Long topico);

}
