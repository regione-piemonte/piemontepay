/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.sql.Timestamp;
import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTApplicativo;

//@formatter:off
public interface EPaywsoTApplicativoDao extends EPaywsoDaoBase<String, EPaywsoTApplicativo> {

	EPaywsoTApplicativo findOneById(Integer id, Timestamp timestamp) throws PersistenceException;
	List<EPaywsoTApplicativo> findAllByIdEnte(Integer idEnte, Timestamp timestamp) throws PersistenceException;
	EPaywsoTApplicativo findOneByIdEnteAndDescrizioneAndCodiceModalitaIntegrazione(Integer idEnte, Timestamp timestamp, String descrizione,String codiceModalitaIntegrazione) throws PersistenceException;
	EPaywsoTApplicativo findOneByIdEnteAndCodiceModalitaIntegrazione(Integer idEnte, Timestamp timestamp, String codiceModalitaIntegrazione) throws PersistenceException;
	public Integer findIdApplicativoMax () throws PersistenceException;

}
//@formatter:on
