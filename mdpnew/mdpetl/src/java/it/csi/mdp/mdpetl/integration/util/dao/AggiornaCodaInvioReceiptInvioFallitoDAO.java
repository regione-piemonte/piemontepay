/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;
import org.apache.commons.lang.StringUtils;


public class AggiornaCodaInvioReceiptInvioFallitoDAO extends BaseDAO {

	private final LogUtil log = new LogUtil ( AggiornaCodaInvioReceiptInvioFallitoDAO.class );

	public AggiornaCodaInvioReceiptInvioFallitoDAO ( Integer contatoreTentativi, Integer numGiorniTentativiKo, String statoInvioFornitore, String iuv ) {
		if ( null == contatoreTentativi ) {
			throw new IllegalArgumentException ( "contatoreTentativi  null" );
		}
		if ( null == numGiorniTentativiKo ) {
			throw new IllegalArgumentException ( "numGiorniTentativiKo  null" );
		}
		if ( StringUtils.isBlank ( statoInvioFornitore ) ) {
			throw new IllegalArgumentException ( "statoInvioFornitore  null" );
		}
		if ( StringUtils.isBlank ( iuv ) ) {
			throw new IllegalArgumentException ( "iuv null o vuota" );
		}
		setStatementMapper ( new GenericObjectArrayStatementMapper ( contatoreTentativi, numGiorniTentativiKo, StringUtils.abbreviate ( statoInvioFornitore, 70 ), iuv ) );
	}

	@Override public String componiQuery () {
		return "update receipt_coda_invio set contatore_tentativi = ?, num_giorni_tentativi_ko = ?, ultimo_esito_fruitore = ?, data_ultima_modifica = now() where iuv = ?";
	}
}
