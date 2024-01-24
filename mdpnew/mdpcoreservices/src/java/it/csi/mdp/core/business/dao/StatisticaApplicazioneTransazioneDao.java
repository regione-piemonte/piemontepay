/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao;

import it.csi.mdp.core.business.dao.StatisticaApplicazioneTransazioneDao;
import it.csi.mdp.core.business.dto.StatisticaApplicazioneTransazione;
import it.csi.mdp.core.business.exceptions.DaoException;
import java.util.Date;
import java.util.List;

public interface StatisticaApplicazioneTransazioneDao{
	public List<StatisticaApplicazioneTransazione> getStatisticaApplicazioneTransazione(String applicationId, Date dateDa,Date dateA) throws DaoException;
}
