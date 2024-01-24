/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import java.util.Collection;
import java.util.List;

import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;

//@formatter:off
/** cuscinetto intermedio tra business e dao, contiene semplice logica di accesso ai dati */
public interface AccessoDad {

    //	public boolean existsUtenteById(Long idUtente) throws PersistenceException;
    //	public boolean existsUtenteByCod(String codUtente) throws PersistenceException;
    //	public UtenteDto findUtenteById(Long idUtente) throws PersistenceException;
    //	public UtenteDto findUtenteByCod(String codUtente) throws PersistenceException;

    public List<EnteDto> findAllEnteByCodUtente(String codUtente) throws PersistenceException;
    public List<RuoloDto> findAllRuoloByIdUtenteAndIdEnte(Long idUtente, Integer idEnte) throws PersistenceException;
    public List<ProfiloDto> findAllProfiloByIdUtenteAndIdEnte(Long idUtente, Integer idEnte) throws PersistenceException;
    public List<CduDto> findAllCduByIdRuolo(Integer idRuolo) throws PersistenceException;
    public List<Integer> findAllIdCodVersamentoByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException;
    public List<CodiceVersamentoDto> findAllCodiceVersamentoByIdProfiloAndIdEnte(Integer idProfilo, Integer idEnte) throws PersistenceException;

    public EnteDto findEnteById(Integer idEnte) throws PersistenceException;
    public RuoloDto findRuoloById(Integer idRuolo) throws PersistenceException;
    public ProfiloDto findProfiloById(Integer idProfilo) throws PersistenceException;

    public EnteDto findEnteByCod ( String codEnte ) throws PersistenceException;

    public List<CodiceVersamentoDto> findAllCodVersamentoByIdEnteAndCodiceIn ( Integer idEnte, Collection<String> codici ) throws PersistenceException;

    public List<CodiceVersamentoDto> findAllCodVersamentoByIdEnteAndCodiceLike ( Integer idEnte, String codiceExpression ) throws PersistenceException;

    public Integer findIdTipoPagamentoREDS () throws PersistenceException;
    public Integer findIdTipoPagamento (String codice) throws PersistenceException ;
}
//@formatter:on
