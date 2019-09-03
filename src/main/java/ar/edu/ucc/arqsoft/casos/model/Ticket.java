package ar.edu.ucc.arqsoft.casos.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TICKET")
public class Ticket extends ObjetoGenerico {
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="ESTADO_TICKET", nullable=false)
	private EstadoTicket estadoTicket;
	
	@Column(name="FECHA_CREACION", nullable= false)
	private Date fechaCreacion;
	
	@Column(name="TITULO", length=600, nullable= false)
	private String titulo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CREADOR_ID", nullable=false)
	private Usuario creador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ASIGNADO_ID", nullable=true)
	private Usuario asignado;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="ticket")
	private Set<Transicion> transiciones = new HashSet<Transicion>();

	public Set<Transicion> getTransiciones() {
		return transiciones;
	}

	public void setTransiciones(Set<Transicion> transiciones) {
		this.transiciones = transiciones;
	}

	public EstadoTicket getEstadoTicket() {
		return estadoTicket;
	}

	public void setEstadoTicket(EstadoTicket estadoTicket) {
		this.estadoTicket = estadoTicket;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public Usuario getAsignado() {
		return asignado;
	}

	public void setAsignado(Usuario asignado) {
		this.asignado = asignado;
	}

}
