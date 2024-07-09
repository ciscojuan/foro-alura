package com.foro.Services;

import java.util.List;

import com.foro.models.Respuestas.Respuesta;

public interface IRespuestaService{
	public List<Respuesta> getRespuestas();
	
	public Respuesta getRespuesta(Long respuesta_id);
	
	public Respuesta addRespuesta(Respuesta respuesta);
	
	public Respuesta updateRespuesta(Long respuesta_id);
	
	public void deleteRespuesta(long respuesta_id);

}
