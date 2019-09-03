package com.bbva.bean;

public class EjempoConsulta {
	
	private int CD_DOSSIER;
	private String NB_DOCUMENTO;
	private int CD_CVDOCTO;
	private String NB_PAGINA;
	private String TP_EXT;
	private String NB_USUARIO;
	private String CD_FOLIO_DIG;
	private int FH_ALTA;
	
	public EjempoConsulta(int cD_DOSSIER, String nB_DOCUMENTO, int cD_CVDOCTO, String nB_PAGINA, String tP_EXT,
			String nB_USUARIO, String cD_FOLIO_DIG, int fH_ALTA) {
		super();
		CD_DOSSIER = cD_DOSSIER;
		NB_DOCUMENTO = nB_DOCUMENTO;
		CD_CVDOCTO = cD_CVDOCTO;
		NB_PAGINA = nB_PAGINA;
		TP_EXT = tP_EXT;
		NB_USUARIO = nB_USUARIO;
		CD_FOLIO_DIG = cD_FOLIO_DIG;
		FH_ALTA = fH_ALTA;
	}

	public EjempoConsulta() {
		// TODO Auto-generated constructor stub
	}

	public int getCD_DOSSIER() {
		return CD_DOSSIER;
	}

	public void setCD_DOSSIER(int cD_DOSSIER) {
		CD_DOSSIER = cD_DOSSIER;
	}

	public String getNB_DOCUMENTO() {
		return NB_DOCUMENTO;
	}

	public void setNB_DOCUMENTO(String nB_DOCUMENTO) {
		NB_DOCUMENTO = nB_DOCUMENTO;
	}

	public int getCD_CVDOCTO() {
		return CD_CVDOCTO;
	}

	public void setCD_CVDOCTO(int cD_CVDOCTO) {
		CD_CVDOCTO = cD_CVDOCTO;
	}

	public String getNB_PAGINA() {
		return NB_PAGINA;
	}

	public void setNB_PAGINA(String nB_PAGINA) {
		NB_PAGINA = nB_PAGINA;
	}

	public String getTP_EXT() {
		return TP_EXT;
	}

	public void setTP_EXT(String tP_EXT) {
		TP_EXT = tP_EXT;
	}

	public String getNB_USUARIO() {
		return NB_USUARIO;
	}

	public void setNB_USUARIO(String nB_USUARIO) {
		NB_USUARIO = nB_USUARIO;
	}

	public String getCD_FOLIO_DIG() {
		return CD_FOLIO_DIG;
	}

	public void setCD_FOLIO_DIG(String cD_FOLIO_DIG) {
		CD_FOLIO_DIG = cD_FOLIO_DIG;
	}

	public int getFH_ALTA() {
		return FH_ALTA;
	}

	public void setFH_ALTA(int fH_ALTA) {
		FH_ALTA = fH_ALTA;
	}

	@Override
	public String toString() {
		return "EjempoConsulta [CD_DOSSIER=" + CD_DOSSIER + ", NB_DOCUMENTO=" + NB_DOCUMENTO + ", CD_CVDOCTO="
				+ CD_CVDOCTO + ", NB_PAGINA=" + NB_PAGINA + ", TP_EXT=" + TP_EXT + ", NB_USUARIO=" + NB_USUARIO
				+ ", CD_FOLIO_DIG=" + CD_FOLIO_DIG + ", FH_ALTA=" + FH_ALTA + "]";
	}

	
}
