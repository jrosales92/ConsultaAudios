package com.bbva.bean;

public class ConsultaArchiving {
	private int idAplicacion;
	private String numeroCliente;
	private String numeroContrato;
	
	public ConsultaArchiving() {
		super();
	}
	public ConsultaArchiving(int idAplicacion, String numeroCliente, String numeroContrato) {
		super();
		this.idAplicacion = idAplicacion;
		this.numeroCliente = numeroCliente;
		this.numeroContrato = numeroContrato;
	}
	public int getIdAplicacion() {
		return idAplicacion;
	}
	public void setIdAplicacion(int idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
	public String getNumeroCliente() {
		return numeroCliente;
	}
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	@Override
	public String toString() {
		return "ConsultaArchiving [idAplicacion=" + idAplicacion + ", numeroCliente=" + numeroCliente
				+ ", numeroContrato=" + numeroContrato + "]";
	}
	
	
}
