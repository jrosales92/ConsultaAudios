package com.bbva.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bbva.configuracion.Parametros;
import com.bbva.configuracion.constBD;

public class DataSourceManager implements constBD, Parametros {

	private static DataSource ds = null;
	private static DataSourceManager dsm = null;
	private static int numConexiones = 0;

	public DataSourceManager() {
		init();
	}

	protected void init(String jniName) {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup(jniName);
		} catch (NamingException ne) {
			try {
				initContext = new InitialContext();
				ds = (DataSource) initContext.lookup(jniName);
			} catch (NamingException exc) {
				ne.printStackTrace();
				exc.printStackTrace();
				throw new RuntimeException("No se encontro la fuente '" + jniName + "'");
			}
		}
	}

	protected void init() {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup(constBD.VAR_ENTORNO);
			ds = (DataSource) envContext.lookup(Parametros.JNINAME);
		} catch (NamingException ne) {
			try {
				initContext = new InitialContext();
				ds = (DataSource) initContext.lookup(Parametros.JNINAME);
			} catch (NamingException exc) {
				ne.printStackTrace();
				exc.printStackTrace();
				throw new RuntimeException("No se encontro la fuente '" + Parametros.JNINAME + "'");
			}
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;

		conn = ds.getConnection();
		conn.setAutoCommit(false);

		return conn;
	}

	public static Connection getConnectionStatic() throws SQLException {
		if (dsm == null)
			dsm = new DataSourceManager() {
			};

		if (ds == null)
			dsm.init();

		return dsm.getConnection();
	}
}
