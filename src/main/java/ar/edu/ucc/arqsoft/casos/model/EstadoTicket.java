package ar.edu.ucc.arqsoft.casos.model;

public enum EstadoTicket {
	CREADO("Creado"), EN_PROCESO("En Proceso"), FINALIZADO("Finalizado"), DEMORADO("Demorado");
	
	private String label;

	public String getLabel() {
		return label;
	}

	private EstadoTicket(String label) {
		this.label = label;
	}
}
