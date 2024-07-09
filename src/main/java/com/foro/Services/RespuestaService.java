package com.foro.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foro.models.Respuestas.Respuesta;
import com.foro.models.Topicos.Topico;
import com.foro.models.Usuario.Usuario;
import com.foro.repository.RespuestaRepository;
import com.foro.repository.TopicoRepository;
import com.foro.repository.UsuarioRepository;

@Service
public class RespuestaService implements IRespuestaService {

	@Autowired	RespuestaRepository respuestaRepository;
	
	@Autowired UsuarioRepository usuarioRepository;
	
	@Autowired TopicoRepository topicoRepository;
	
	public Page<Respuesta> getRespuestasPaginados(Pageable pageable) {
		return respuestaRepository.findAll(pageable);
	}

	@Override
	public List<Respuesta> getRespuestas() {

		return respuestaRepository.findAll();
	}

	@Override
	public Respuesta getRespuesta(Long respuesta_id) {
		Respuesta respuesta = respuestaRepository.findById(respuesta_id).orElse(null);
		return respuesta;
	}

//	@Override
//	public Respuesta addRespuesta(Respuesta respuesta) {
//		return repository.save(respuesta);
//	}

    public Respuesta addRespuesta(Respuesta respuesta) {
    	
    	if (respuesta.getUsuario() == null || respuesta.getUsuario().getUser_id() == null) {
            throw new IllegalArgumentException("Usuario ID must not be null within the payload");
        }
    	
        if (respuesta.getTopico() == null || respuesta.getTopico().getTopico_id() == null) {
            throw new IllegalArgumentException("Topico ID must not be null within the payload");
        }

        // Load existing Usuario and Topico entities
        Usuario usuario = usuarioRepository.findById(respuesta.getUsuario().getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
        Topico topico = topicoRepository.findById(respuesta.getTopico().getTopico_id())
                .orElseThrow(() -> new IllegalArgumentException("Topico not found"));

        // Set the loaded entities
        respuesta.setUsuario(usuario);
        respuesta.setTopico(topico);

        return respuestaRepository.save(respuesta);
    }
	
	@Override
	public Respuesta updateRespuesta(Long respuesta_id) {
		
		return respuestaRepository.getReferenceById(respuesta_id);
	}

	@Override
	public void deleteRespuesta(long respuesta_id) {
		respuestaRepository.deleteById(respuesta_id);

	}

}
