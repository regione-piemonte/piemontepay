/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business.v1;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoOutput;
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
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.PagamentoMarcaDaBolloChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoOutput;
import it.csi.epay.epayservices.model.v1.StampTaxAttachmentChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.StampTaxAttachmentChiamanteEsternoOutput;


/**
 * Servizio client per epayservices
 */
public interface EpayServicesClientService {

    AccessoChiamanteEsternoSincronoOutput accessoChiamanteEsternoSincrono ( String organization, String paymentType, AccessoChiamanteEsternoSincronoInput input );


    /**
     * Metodo per ottenere lo IUV da associare al pagamento che deve essere effettuato dal cittadino
     *
     * @param input contiene le informazioni per identificare il pagamento, il pagatore ed il creditore
     * @return lo IUV del pagamento
     */
    GetIuvChiamanteEsternoOutput getIUVChiamanteEsterno ( String organization, String paymentType, GetIuvChiamanteEsternoInput input );

    /**
     * Metodo per ottenere lo IUV da associare al pagamento che deve essere effettuato dal cittadino
     *
     * @param input contiene le informazioni per identificare il pagamento, il pagatore ed il creditore
     * @return lo IUV del pagamento
     */
    GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsterno ( String organization, String paymentType, GetIuvMultibeneficiarioChiamanteEsternoInput input );

    /**
     * Metodo per ottenere l'url del WISP da cui il cittadino puo' effettuare il pagamento
     *
     * @param input contiene le informazioni che permettono di riconoscere il pagamento nel database dello sportello dei pagamenti e procedere con la
     *            definizione dell'url del wisp
     * @return l'url dove effettuare il pagamento
     */
    PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno ( PagamentoIuvChiamanteEsternoInput input );

    /**
     * Effettua il pagamento di una marca da bollo con o senza istanza collegata
     *
     * @param input i dati di identificazione del pagamento e marca da bollo
     * @return l'url presso il quale effettuare il pagamento
     */
    PagamentoMarcaBolloChiamanteEsternoOutput getPagamentoMarcaBolloChiamanteEsterno ( PagamentoMarcaDaBolloChiamanteEsternoInput input );

    /**
     * Metodo per ottenere la stampa Rt
     *
     * @param input i dati di identificazione della stampa e il formato della stampa
     * @return la stampa rt nel formato richiesto
     */
    GetRTChiamanteEsternoOutput getRTChiamanteEsterno ( GetRTChiamanteEsternoInput input );

    /**
     * Metodo per aggiornare una posizione debitoria in input
     *
     * @param input contiene le informazioni per identificare le pos. debitorie
     * @return esito dell'operazione
     */
    AggiornaPosizioneDebitoriaChiamanteEsternoOutput aggiornaPosizioneChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input );

    /**
     * Metodo per aggiornare una posizione debitoria multibeneficiario in input
     *
     * @param input contiene le informazioni per identificare le pos. debitorie
     * @return esito dell'operazione
     */
    AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput aggiornaPosizioneMultibeneficiarioChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input );

    /**
     * Metodo per annullare una posizione debitoria in input
     *
     * @param input contiene le informazioni per identificare le pos. debitorie
     * @return esito dell'operazione
     */
    EliminaPosizioneDebitoriaChiamanteEsternoOutput eliminaPosizioneChiamanteEsterno ( EliminaPosizioneDebitoriaChiamanteEsternoInput input );

	/**
	 * Metodo per ottenere lo stato di una posizione debitoria
	 *
	 * @param input contiene le informazioni per identificare la posizione debitoria
	 * @return esito dell'operazione
	 */
	GetStatoPosizioneDebitoriaOutput getStatoPosizioneDebitoriaChiamanteEsterno ( GetStatoPosizioneDebitoriaInput input );

	/**
	 * Metodo per ottenre dati del pagamento
	 *
	 * @param input contiene le informazioni per identificare il pagamento
	 * @return esito dell'operazione
	 */
	GetDatiPagamentoChiamanteEsternoOutput getDatiPagamentoChiamanteEsterno ( GetDatiPagamentoChiamanteEsternoInput input );
    /**
     * Metodo di test
     */
    void test ();

	/*
	 * usato per stampa avviso, ricerca e ritorna il pagamento piu' eventuali errori post controlli
	 */
	PagamentoPerStampaAvvisoOutput ricercaPagamentoByIUV ( PagamentoPerStampaAvvisoInput input ) throws NoDataException;

	/*
	 * usato per stampa avviso
	 */
	Rt ricercaRtByIuv ( String iuv ) throws NoDataException;

	/*
	 * usato per stampa avviso
	 */
	DatiAvvisoPagamento ricercaDatiAvvisoPagamentoByIdTipoPagamento ( Long idTipoPagamento ) throws NoDataException;
	
	
	StampTaxAttachmentChiamanteEsternoOutput getStampTaxAttachment ( StampTaxAttachmentChiamanteEsternoInput input ) ;
}
