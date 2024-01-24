/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.interfaces.ejb.v1;

import javax.ejb.Remote;

import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.interfaces.exception.PosizioneDebitoriaException;
import it.csi.epay.epayservices.interfaces.exception.TestataException;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.DatiPDFReport;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaOutput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoOutput;


/**
 * @author Artan Bora
 */
@Remote
public interface ChiamataEsternaSincronaSplitFacade {

    /**
     * Metodo per ottenere lo stato di una posizione debitoria
     */
    GetStatoPosizioneDebitoriaOutput getStatoPosizioneDebitoria ( GetStatoPosizioneDebitoriaInput input );

    /**
     * Metodo per ottenere i dati di un pagamento
     */
    GetDatiPagamentoChiamanteEsternoOutput getDatiPagamentoChiamanteEsterno ( GetDatiPagamentoChiamanteEsternoInput input );

    /**
     * Metodo per aggiornare una posizione debitoria
     * 
     * @param input dto
     * @return dto
     */
    AggiornaPosizioneDebitoriaChiamanteEsternoOutput aggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input );

    /**
     * Metodo per aggiornare una posizione debitoria multibeneficiario
     * 
     * @param input dto
     * @return dto
     */
    AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput aggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input );

    /**
     * Metodo per eliminare una posizione debitoria
     * @param input dto
     * @return dto
     */
    EliminaPosizioneDebitoriaChiamanteEsternoOutput eliminaPosizioneDebitoriaChiamanteEsterno ( EliminaPosizioneDebitoriaChiamanteEsternoInput input );

	/*
	 * usato in stampa avviso
	 */
	PagamentoPerStampaAvvisoOutput ricercaPagamentoByIUV ( PagamentoPerStampaAvvisoInput input );

	/*
	 * usato in stampa avviso
	 */
	Rt ricercaRtByIuv ( String iuv ) throws NoDataException;

	DatiAvvisoPagamento ricercaDatiAvvisoPagamentoByIdTipoPagamento ( Long idTipoPagamento ) throws NoDataException;

    /**
     * Servizio per recuperare il template della stampa avviso
     * 
     * @param codiceTemplate
     * @return
     * @throws NoDataException
     */
    DatiPDFReport ricercaTemplatePDFStampaAvviso ( String codiceTemplate ) throws NoDataException;

    /**
     * Servizio che aggiorna il template compilato
     * 
     * @param id
     * @param templateCompilato
     * @throws NoDataException
     */
    void aggiornaTemplateCompilato ( Long id, byte [] templateCompilato ) throws NoDataException;
    
    /**
     * Metodo per ottenere l'url del WISP che consente al cittadino di effettuare il pagamento
     *
     * @param input contiene le informazioni relative a chi ha effettuato la chiamata, all'identificativo del pagamento, al codice fiscale del pagatore e allo
     *            iuv del pagamento
     * @return un oggetto contenente l'esito della chiamata e, nel caso di esito positivo, l'url del wisp
     */
    PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno(PagamentoIuvChiamanteEsternoInput input);

    /**
     * Gestione dell'attualizzazione dell'importo durante la chiediDatiPagamento, dopo l'invocazione di getNotificationPrice, nel caso si tratti di pagamento
     * multibeneficiario
     * 
     * @param input
     * @param ente
     * @param pagamento
     * @param tPagamento
     * @return
     * @throws TestataException
     * @throws PosizioneDebitoriaException
     * @throws TassonomiaServiceException
     */
	AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput aggiornaPosizioneDebitoriaMultibeneficiarioUsoInterno(
			AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input, 
			Ente ente, 
			Pagamento pagamento);

	AggiornaPosizioneDebitoriaChiamanteEsternoOutput aggiornaPosizioneDebitoriaUsoInterno (
			AggiornaPosizioneDebitoriaChiamanteEsternoInput input, 
			Ente ente, 
			Pagamento pagamento );

}
