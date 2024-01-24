/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf.cooppec;

import java.util.List;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.PersistencePersistException;
import it.csi.epay.epaypaweb.exception.PersistenceRemoveException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaTCodiceVersamentoTemp;


public interface EpaypaTCodiceVersamentoTempDao extends EpaypaDaoBase<Long, EpaypaTCodiceVersamentoTemp> {

    public void save ( List<EpaypaTCodiceVersamentoTemp> codiceVersamentoTempList ) throws PersistencePersistException;

    public List<EpaypaTCodiceVersamentoTemp> findAllByIdOperazione ( String idOperazione ) throws PersistenceException;

    public void delete ( List<EpaypaTCodiceVersamentoTemp> catalogoTempList ) throws PersistenceRemoveException;
}
