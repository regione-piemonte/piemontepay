/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


@SuppressWarnings ( "rawtypes" )
public class AggiornaStatoRPTInviataDAO extends BaseDAO {

	public AggiornaStatoRPTInviataDAO ( int id ) {
		setStatementMapper ( new GenericObjectArrayStatementMapper (
						id
		) );
	}

	@Override
	public String componiQuery () {
		return " update rpt set id_stati_rpt = 80, last_update = now() where id = ?";
	}
}
