/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

public class AggiornaDataInizioTentativiCodaInvioReceiptDAO extends BaseDAO {
	
	LogUtil log = new LogUtil(AggiornaDataInizioTentativiCodaInvioReceiptDAO.class);
	
	
	public  AggiornaDataInizioTentativiCodaInvioReceiptDAO(Timestamp dataInizioTentativi,String iuv) throws SerialException, SQLException {
		if (null == dataInizioTentativi) {
			throw new IllegalArgumentException("data inizio tentativi  null");
		}
		if (StringUtils.isBlank ( iuv )) {
			throw new IllegalArgumentException("iuv null o vuota");
		}
		
        setStatementMapper ( new GenericObjectArrayStatementMapper ( dataInizioTentativi, iuv ) );
	}

    @Override
    public String componiQuery () {
        String query = "";
        query = "update receipt_coda_invio set data_inizio_tentativi = ? , data_ultima_modifica = now() where iuv =  ? ";
        return query;
    }
	   
}
