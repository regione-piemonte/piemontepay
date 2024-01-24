/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoria;

import java.util.List;

//@formatter:off
public interface EpaypaTPosizioneDebitoriaDao extends EpaypaDaoBase<Long, EpaypaTPosizioneDebitoria> {

	public Long countAllByIdFlusso(Long idFlusso) throws PersistenceException;

	public List<EpaypaTPosizioneDebitoria> findAllByIdFlusso(Long idFlusso) throws PersistenceException;

	public List<EpaypaTPosizioneDebitoria> findAllPagamentiByFilter( PagamentiFilterDto filter, List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, PaginazioneDto pag, String codUtente) throws PersistenceException;
	
	public EpaypaTPosizioneDebitoria findPosizioneDebitoriaByIuv(String iuv) throws PersistenceException;

	public Long countAllPosizioneDebitoriaByFilter(PagamentiFilterDto filter, String codUtente) throws PersistenceException;
	
	public Long countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( String posizioneDebitoriaEst, Integer enteId, Integer idCodVersamento ) throws PersistenceException;

	EpaypaTPosizioneDebitoria findOneByIdPosizioneDebitoriaEsternaAndIUV ( String iDposizioneDebitoriaEsterna, String iuv, Integer idEnte, Integer idCodiceVersamento ) throws PersistenceException;
}
//@formatter:on
