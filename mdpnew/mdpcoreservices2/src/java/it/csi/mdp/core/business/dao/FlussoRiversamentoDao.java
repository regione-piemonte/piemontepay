/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.util.Date;
import java.util.List;

public interface  FlussoRiversamentoDao {

	public List<FlussoRiversamento> getFlussoRiversamentoByParam(
									Integer  id,
									String   identificativopsp,
									String   identificativoflusso,
									String   versioneoggetto,
									String   identificativounivocoregolamento,
									String   identificativoistitutomittente,
									String   identificativoistitutoricevente,
									Integer  numerototalepagamenti,
									Double   importototalepagamenti,
									Date     dataoraflusso,
									Date     dataregolamentoDa,
									Date     dataregolamentoA,
									Date     datainserimento,
									Date     datamodifica,
									String   xmlflusso,
									String   denominazionemittente,
									String   denominazionericevente
) throws DaoException;
	
	
}
