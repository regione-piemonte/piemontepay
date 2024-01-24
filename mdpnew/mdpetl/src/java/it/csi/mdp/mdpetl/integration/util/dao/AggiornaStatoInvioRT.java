/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

public class AggiornaStatoInvioRT extends BaseDAO {
	
	LogUtil log = new LogUtil(AggiornaStatoInvioRT.class);
	
	private String inclause;
	
	public  AggiornaStatoInvioRT(StatoInvioRTEnum statoDaAggiornare,String cause) throws SerialException, SQLException {
		if (null == statoDaAggiornare) {
			throw new IllegalArgumentException("statoDaAggiornare null");
		}
		if (StringUtils.isBlank ( cause )) {
			throw new IllegalArgumentException("lista iuv null o vuota");
		}
		inclause = cause;
        setStatementMapper ( new GenericObjectArrayStatementMapper ( statoDaAggiornare.getCodiceStato () ) );
	}

    @Override
    public String componiQuery () {
        String query = "";
        query = "update rt set stato_invio = ? where iuv in (" + inclause + ")";
        return query;
    }
	   
}
