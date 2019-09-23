package ar.edu.ucc.arqsoft.casos.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.ucc.arqsoft.casos.model.EstadoTicket;
import ar.edu.ucc.arqsoft.casos.model.Transicion;

public class TransicionDaoImp extends DaoGenericoImp<Transicion, Long> implements TransicionDao{

	public List<Transicion> getTransicionesByEstado(EstadoTicket estadoTicket){
		
		List<Transicion> transiciones = null;
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("estadoNuevo", estadoTicket);
		
		transiciones= this.getByProperties(properties);
		
		return transiciones;
	}
	

}
