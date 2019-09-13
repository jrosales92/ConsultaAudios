package com.bbva.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bbva.bean.EjempoConsulta;
import com.bbva.datasource.DataSourceManager;

public class ConsultaManager extends DataSourceManager {
	
	public String[][] getDefinicionBucket(String aplicacion){
		String[][] data = new String[0][0];
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int totalRegistros = 0;
		try {
			conn = getConnection();
			String query = "select count(nb_parametro) as nbparam FROM GORAPR.tatn008_paramconf where tx_valorparam = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aplicacion.toUpperCase());

			rs = pstmt.executeQuery();
			if (rs.next()) 
				totalRegistros = rs.getInt(1);

			data = new String[totalRegistros][3];

			String query2 = "select nb_parametro, tx_valorparam, tp_parametro FROM GORAPR.tatn008_paramconf where tx_valorparam = ?";
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, aplicacion.toUpperCase());

			rs2 = pstmt2.executeQuery();
			for (int row = 0; row < totalRegistros; row++) {
				rs2.next();
				for (int i = 0; i < rs2.getMetaData().getColumnCount() - 1; i++) {
					String valor = (rs2.getString(i + 2) == null ? ""
							: ("null".equalsIgnoreCase(rs2.getString(i + 2).trim()) ? ""
									: rs2.getString(i + 2).trim()));
					data[row][i] = (i == 0) ? rs2.getString("nb_parametro") : valor;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(rs2 != null) rs2.close();
				if(pstmt2 != null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public List<EjempoConsulta> EjempoConsultaNumCliente(String numcliente) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EjempoConsulta bean = null;

		List<EjempoConsulta> lista = new ArrayList<EjempoConsulta>();
		try {

			conn = getConnection();

			String query = "SELECT T014.NB_DOSSIER, T017.NB_DOCUMENTO, T017.CD_CVEDOCTO, T019.NB_PAGINA, T019.TP_EXT, T019.NB_USUARIO, T019.CD_FOLIO_DIG, T019.FH_ALTA  FROM GORAPR.tatn014_dossier T014 "
					+ "INNER JOIN GORAPR.tatn017_documento  T017 "
					+ "ON T014.CD_ATHAN = T017.cd_athan"
					+ "AND T014.CD_CONTENEDOR = T017.CD_CONTENEDOR"
					+ "AND T014.CD_DOSSIER = T017.CD_DOSSIER"
					+ "INNER JOIN GORAPR.tatn019_pagina T019"
					+ "ON t017.cd_athan = t019.cd_athan"
					+ "AND T017.CD_CONTENEDOR = t019.cd_contenedor"
					+ "AND t017.cd_dossier = t019.cd_dossier"
					+ "AND t017.cd_documento = t019.cd_documento"
					+ "WHERE T014.NB_DOSSIER = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,numcliente);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EjempoConsulta();
				bean.setCD_DOSSIER(rs.getInt("CD_DOSSIER"));
				bean.setNB_DOCUMENTO(rs.getNString("NB_DOCUMENTO"));
				bean.setCD_CVDOCTO(rs.getInt("CD_CVDOCTO"));
				bean.setNB_PAGINA(rs.getString("NB_PAGINA"));
				bean.setTP_EXT(rs.getString("TP_EXT"));
				bean.setNB_USUARIO(rs.getNString("NB_USUARIO"));
				bean.setCD_FOLIO_DIG(rs.getNString("CD_FOLIO_DIG"));
				bean.setFH_ALTA(rs.getInt("FH_ALTA"));
				lista.add (bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public List<EjempoConsulta> EjempoConsultaNumContrato(String numcontrato) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EjempoConsulta bean = null;

		List<EjempoConsulta> lista = new ArrayList<EjempoConsulta>();
		try {

			conn = getConnection();

			String query = "SELECT T014.NB_DOSSIER, T017.NB_DOCUMENTO, T017.CD_CVEDOCTO, T019.NB_PAGINA, T019.TP_EXT, T019.NB_USUARIO, T019.CD_FOLIO_DIG, T019.FH_ALTA  FROM GORAPR.tatn014_dossier T014 "
					+ "INNER JOIN GORAPR.tatn017_documento  T017 "
					+ "ON T014.CD_ATHAN = T017.cd_athan"
					+ "AND T014.CD_CONTENEDOR = T017.CD_CONTENEDOR"
					+ "AND T014.CD_DOSSIER = T017.CD_DOSSIER"
					+ "INNER JOIN GORAPR.tatn019_pagina T019"
					+ "ON t017.cd_athan = t019.cd_athan"
					+ "AND T017.CD_CONTENEDOR = t019.cd_contenedor"
					+ "AND t017.cd_dossier = t019.cd_dossier"
					+ "AND t017.cd_documento = t019.cd_documento"
					+ "WHERE T017.NB_DOCUMENTO = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,numcontrato);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new EjempoConsulta();
				bean.setCD_DOSSIER(rs.getInt("CD_DOSSIER"));
				bean.setNB_DOCUMENTO(rs.getString("NB_DOCUMENTO"));
				bean.setCD_CVDOCTO(rs.getInt("CD_CVDOCTO"));
				bean.setNB_PAGINA(rs.getString("NB_PAGINA"));
				bean.setTP_EXT(rs.getString("TP_EXT"));
				bean.setNB_USUARIO(rs.getString("NB_USUARIO"));
				bean.setCD_FOLIO_DIG(rs.getString("CD_FOLIO_DIG"));
				bean.setFH_ALTA(rs.getInt("FH_ALTA"));
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
}
