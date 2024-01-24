/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;


public class AggiornaDataTentativiCodaInvioReceiptDAO extends BaseDAO {

	private final LogUtil log = new LogUtil ( AggiornaDataTentativiCodaInvioReceiptDAO.class );

	public AggiornaDataTentativiCodaInvioReceiptDAO ( Timestamp dataTentativi, String iuv ) {
		if ( null == dataTentativi ) {
			throw new IllegalArgumentException ( "data tentativi  null" );
		}
		if ( StringUtils.isBlank ( iuv ) ) {
			throw new IllegalArgumentException ( "iuv null o vuota" );
		}

		setStatementMapper ( new GenericObjectArrayStatementMapper ( dataTentativi, iuv ) );
	}

	@Override public String componiQuery () {
		return "update receipt_coda_invio set data_tentativi = ?, data_ultima_modifica = now() where iuv = ( ? )";
	}
}
