package  com.bbva.configuracion;

public interface Parametros {

	//********************************************************************************
	// Limite de páginas en Digitalizacion Masiva para procesarlos en las noches
	public static final int LIMITE_PAG_DIG_MASIVA = 60;
	// Limite de resultados obtenidos en la busqueda de Historico
	public static final int LIMITE_RESUL_HISTORICO = 500;
	// Limite de resultados obtenidos en la busqueda de Bitacora
	public static final int LIMITE_RESUL_BITACORA = 200;
	// Limite de resultados obtenidos en la busqueda de filtroReportes
	public static final int LIMITE_RESUL_REPORTE = 500;
	// Tipo de Servidor de Aplicaciones  W: Websphere T:Tomcat
	public static final String TIPO_SESION = "W";
	// Limite de Directorios para el Volumen Manager
	public static final int LIMITE_DIRECTORIOS=600;
	// Limite de volumenes dentro de los directorios para Volumen Maqnager
	public static final int LIMITE_VOLUMENES=512;
	// Tipo de Base de Datos Revisar las constantes en com.bancomer.constantes.constDB;
	public static final int TIPO_BD =  com.bbva.configuracion.constBD.ORACLE;
	//Configuracion Volumenes
	public static final  int ESTADO_UNIDAD = 3;
	//Produccion = 3;
	
	//Configuracion Volumenes
	public static final String CAPACIDAD = "3";
	//Produccion = 3;
	
	//Configuracion Volumenes
	public static final String TIPO_VOLUMEN = "0";
	// Nombre del JniName
	public static final String JNINAME = "jdbc/AUDIOS";
	public static final String[] USUARIOS_PRUEBA = 
		new String[]{"AREC","ARED","CONSULTA","JURIDICO","MCONTROL","OPERACIONES","ORIGINACION","PROMOCION","TECNICO","UG","PRUEBA"};
	//Tipo de Ambiente
	public static final int AMBIENTE =  2;
	//Desarrollo = 2;
	//Produccion = 1;

	public static final String TITULO_APLICACION = "SUGO";
	
	/*Parametros para Archiving*/
	public static final String VOL_ARCHIVING        		= "@archvng";
	public static final String URL_ARCHIVING				= "http://150.100.22.50:9090";
	/*Parametros para Archiving*/
	
	public static final boolean MOSTRAR_DEBUG = true;						// Muestra e imprime en logs los valores de los system.out.println()
//	public static final boolean MOSTRAR_DEBUG = false;						// Muestra e imprime en logs los valores de los system.out.println()
	
//	public static final int BASE_DATOS        = 1;	// Base de datos a utilizar
	public static final int BASE_DATOS        = 2;		// Base de datos a utilizar
	
	public static final int SQL_SERVER 	= 1;
	public static final int ORACLE 		= 2;
	
}
