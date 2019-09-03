package ar.edu.ucc.arqsoft.casos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRANSICION")
public class Transicion extends ObjetoGenerico{
	
	@Column(name="FECHA_CREACION", nullable=false)
	private Date fechaCreacion;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Ticket.class)
	@JoinColumn(name="TICKET_ID", nullable=false)
	private Ticket ticket;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="ESTADO_ANTERIOR", nullable=false)
	private EstadoTicket estadoAnterior;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="ESTADO_NUEVO", nullable=false)
	private EstadoTicket estadoNuevo;
	
	@Column(name="COMENTARIO", nullable=true, length=1000)
	private String cometario;

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public EstadoTicket getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(EstadoTicket estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	public EstadoTicket getEstadoNuevo() {
		return estadoNuevo;
	}

	public void setEstadoNuevo(EstadoTicket estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}

	public String getCometario() {
		return cometario;
	}

	public void setCometario(String cometario) {
		this.cometario = cometario;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
