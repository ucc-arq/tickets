package ar.edu.ucc.arqsoft.casos.dao;

import java.util.List;

import ar.edu.ucc.arqsoft.casos.model.EstadoTicket;
import ar.edu.ucc.arqsoft.casos.model.Transicion;

public interface TransicionDao extends DaoGenerico<Transicion, Long> {
	
	public List<Transicion> getTransicionesByEstado(EstadoTicket estadoTicket); 

}
