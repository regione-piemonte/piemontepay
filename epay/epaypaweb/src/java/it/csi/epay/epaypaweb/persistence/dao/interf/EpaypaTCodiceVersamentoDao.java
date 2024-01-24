/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.Collection;
import java.util.List;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;

//@formatter:off
public interface EpaypaTCodiceVersamentoDao extends EpaypaDaoBase<Integer, EpaypaTCodiceVersamento> {

	public String  findCodVersamentoById(Integer idCodVersamento) throws PersistenceException;
	public Integer findIdCodVersamentoByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException;
	public EpaypaTCodiceVersamento findOneByCodAndEnte(String codVersamento, Integer idEnte) throws PersistenceException;
    public List<EpaypaTCodiceVersamento> findAllByIdEnteAndCodiceVersamento ( Integer idEnte, String codiceVersamento ) throws PersistenceException;
    public List<EpaypaTCodiceVersamento> findByIdEnte ( Integer idEnte ) throws PersistenceException;

	public List<Integer> findAllIdCodVersamentoByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException;
	public List<EpaypaTCodiceVersamento> findAllByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException;

    public List<EpaypaTCodiceVersamento> findAllCodVersamentoByIdEnteAndCodVersamentoIn ( Integer idEnte, Collection<String> codici )
                    throws PersistenceException;

    public List<EpaypaTCodiceVersamento> findAllCodVersamentoByIdEnteAndCodVersamentoLike ( Integer idEnte, String codiceExpression )
                    throws PersistenceException;

}
//@formatter:on
