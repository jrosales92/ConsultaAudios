package com.bbva.bean;

public class TATN001_APLICACION {

	private int CD_APLICACION;
	private String NB_APLICACION;
	
	public TATN001_APLICACION(){
		super();
	}
	
	public TATN001_APLICACION(int cD_APLICACION, String nB_APLICACION) {
		super();
		CD_APLICACION = cD_APLICACION;
		NB_APLICACION = nB_APLICACION;
	}

	public int getCD_APLICACION() {
		return CD_APLICACION;
	}

	public void setCD_APLICACION(int i) {
		CD_APLICACION = i;
	}

	public String getNB_APLICACION() {
		return NB_APLICACION;
	}

	public void setNB_APLICACION(String nB_APLICACION) {
		NB_APLICACION = nB_APLICACION;
	}

	@Override
	public String toString() {
		return "TATN001_APLICACION [CD_APLICACION=" + CD_APLICACION + ", NB_APLICACION=" + NB_APLICACION
				+ ", getCD_APLICACION()=" + getCD_APLICACION() + ", getNB_APLICACION()=" + getNB_APLICACION()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
