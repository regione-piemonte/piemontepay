/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf.cooppec;

import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.exception.PersistenceRemoveException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTCodiceVersamentoTemp;


public interface EPaywsoTCodiceVersamentoTempDao extends EPaywsoDaoBase<Long, EPaywsoTCodiceVersamentoTemp> {

    public void save ( List<EPaywsoTCodiceVersamentoTemp> codiceVersamentoTempList ) throws PersistenceException;

    public List<EPaywsoTCodiceVersamentoTemp> findAllByIdOperazione ( String idOperazione ) throws PersistenceException;

    public void delete ( List<EPaywsoTCodiceVersamentoTemp> catalogoTempList ) throws PersistenceException;
}
