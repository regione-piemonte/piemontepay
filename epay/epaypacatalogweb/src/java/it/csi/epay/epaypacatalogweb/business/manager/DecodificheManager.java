/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.OpzioniCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.ppay.MacrotipoPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.TematicaPPayVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioniAssociazioneUtenteCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioniAssociazioneUtenteTematicaVO;

public interface DecodificheManager {

    String getTestoMessaggio ( String codice );

	List<GenericVO> getListaOpzioniPeriodicitaSchedulazioneRiconciliazione();

	List<GenericVO> getListaOpzioniTipologiaAccertamento();

	List<GenericVO> getListaOpzioniModalitaAcquisizioneProvvisori();

	List<TematicaPPayVO> getListaOpzioniTematicheVociEntrata();

    List<TematicaPPayVO> getListaOpzioniTematicheVociEntrataClean();

	List<MacrotipoPPayVO> getListaOpzioniMacrotipiVociEntrata();

    List<OpzioniCodiceVersamentoVO> getListaOpzioniCodiceVersamento ( Boolean soloBase );

    List<GenericVO> getListaOpzioniStatoAggiornamento ();

    List<GenericVO> getListaOpzioniTipoPagamento ();

    List<GenericVO> getListaOpzioniTipologiaDatoSpecificoRiscossioneRiferimentoContabile ();

    OpzioniAssociazioneUtenteCduVO getListaOpzioniAssociazioneUtenteCdu ();

    OpzioniAssociazioneUtenteTematicaVO getListaOpzioniAssociazioneUtenteTematica ();

    List<GenericVO> getListaOpzioniModalitaIntegrazione ();
    
    List<GenericVO>  ricercaCodiceVersamentoRifContabileSecondario (  Long idEnte ) ;


}
