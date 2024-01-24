/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.IuvOttico;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class EstraiApplicationIdDaIuvDAO extends BaseDAO<IuvOttico> {
	 LogUtil log = new LogUtil(EstraiApplicationIdDaIuvDAO.class);
	
	public  EstraiApplicationIdDaIuvDAO(String iuv) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv));
		setResultSetExtractor(new ApplicationIdExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from iuv_ottici where iuv_ottico = ? ";
	}

}

class ApplicationIdExtractor implements ResultSetExtractor<IuvOttico> {
	
	public IuvOttico extractData(ResultSet rs) throws SQLException {
		IuvOttico iuvOttico = null;
		while(rs.next()){
			iuvOttico = new IuvOttico();
			iuvOttico.setApplicationId(rs.getString("application_id"));
			iuvOttico.setCodiceVersamento(rs.getString("cod_versamento"));
			iuvOttico.setDataCreazione(rs.getTimestamp("data_creazione"));
			iuvOttico.setEnteId(rs.getString("ente_id"));
			iuvOttico.setId(rs.getInt("id"));
			iuvOttico.setIuvOttico(rs.getString("iuv_ottico"));
			iuvOttico.setIuvStandard(rs.getString("iuv_standard"));
		}
		
		return iuvOttico;
	}
	
}

