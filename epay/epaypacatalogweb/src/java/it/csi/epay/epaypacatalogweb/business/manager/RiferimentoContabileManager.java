/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.Date;
import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ChiudiRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ModificaRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RicercaRiferimentoContabileFiltroVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO;


public interface RiferimentoContabileManager {

	RiferimentoContabileVO istanzia ();

	RiferimentoContabileVO merge ( Long id, ModificaRiferimentoContabileVO input, boolean isDuplicato ) throws OperationFailedException;

	List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> ricerca ( RicercaRiferimentoContabileFiltroVO filtro )
					throws OperationFailedException;

	RiferimentoContabileVO get ( Long id ) throws OperationFailedException;

	void inserisci ( ModificaRiferimentoContabileVO userInput ) throws OperationFailedException;

	void aggiorna ( ModificaRiferimentoContabileVO modelSubmitted ) throws OperationFailedException;

	void elimina ( Long id ) throws OperationFailedException;

	void chiudi ( ChiudiRiferimentoContabileVO userInput ) throws OperationFailedException;

	RiferimentoContabileVO getStorico ( Long idPadre, Long id ) throws OperationFailedException;


	List<GenericVO> ricercaRiferimentiContabiliSecondariPerCov(Long idCov) throws OperationFailedException;
	
	Integer verificaNumeroRiferimentiContabiliInVitaPerCov(Long idCov) throws OperationFailedException;

	public String verificaPresenzaRiferimentiContabili ( Long idCodiceVersamento, Integer annoEsercizio, Boolean generico, Date dataInizioValidita,  Date dataFineValidita, Long idRiferimentoContabile ) throws OperationFailedException;

}
