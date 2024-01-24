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

public class AggiornaCodaInvioRtInvioFallitoDAO extends BaseDAO {
LogUtil log = new LogUtil(AggiornaCodaInvioRtInvioFallitoDAO.class);
	
	
	public  AggiornaCodaInvioRtInvioFallitoDAO(Integer contatoreTentativi, 
			Integer numGiorniTentativiKo, 
			String statoInvioFornitore, 
			String iuv) throws SerialException, SQLException {
		if (null == contatoreTentativi) {
			throw new IllegalArgumentException("contatoreTentativi  null");
		}
		if (null == numGiorniTentativiKo) {
			throw new IllegalArgumentException("numGiorniTentativiKo  null");
		}
		if (StringUtils.isBlank (statoInvioFornitore)) {
			throw new IllegalArgumentException("statoInvioFornitore  null");
		}
		if (StringUtils.isBlank ( iuv )) {
			throw new IllegalArgumentException("iuv null o vuota");
		}
        setStatementMapper ( new GenericObjectArrayStatementMapper ( contatoreTentativi,numGiorniTentativiKo, StringUtils.abbreviate ( statoInvioFornitore, 70 ), iuv ) );
	}

    @Override
    public String componiQuery () {
        String query = "";
        query = "update rt_coda_invio set "
        		+ "contatore_tentativi = ?, "
        		+ "num_giorni_tentativi_ko = ?, "
        		+ "ultimo_esito_fruitore = ?, "
        		+ "data_ultima_modifica = now()  "
        		+ "where iuv =  ? ";
        return query;
    }
	   
}
