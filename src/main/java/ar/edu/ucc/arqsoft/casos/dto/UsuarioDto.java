package ar.edu.ucc.arqsoft.casos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UsuarioDto {

	private Long id;

	private String nombre;

	private String apellido;

	@JsonProperty(value= "nombre_apellido", access=Access.READ_ONLY)	
	private String nombreApellido;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getNombreApellido() {
		return nombre + " " + apellido;
	}

}
