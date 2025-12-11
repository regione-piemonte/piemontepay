/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business;

import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaDaBolloChiamanteEsternoInput;


/**
 * Servizio client per epayservices
 */
public interface EpayServicesClientService {

    AccessoChiamanteEsternoSincronoOutput accessoChiamanteEsternoSincrono ( AccessoChiamanteEsternoSincronoInput input );

    /**
     * Metodo per ottenere la lista degli IUV da associare alla lista di pagamenti che devono essere effettuato dal/dai cittadino/i
     *
     * @param input contiene le informazioni per identificare il/i pagamento/i, il pagatore ed il creditore
     * @return lo/gli IUV del/dei pagamento/i
     */
	GetIuvChiamanteEsternoOutputContainer getListIUVChiamanteEsterno ( GetIuvChiamanteEsternoInputContainer input );

    /**
     * Metodo per ottenere la lista degli IUV multibeneficiario da associare alla lista di pagamenti che devono essere effettuato dal/dai cittadino/i
     *
     * @param input contiene le informazioni per identificare il/i pagamento/i, il pagatore ed il creditore
     * @return lo/gli IUV del/dei pagamento/i
     */
	GetIuvMultibeneficiarioChiamanteEsternoOutputContainer
    getListIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInputContainer input );

    /**
     * Metodo per ottenere lo IUV da associare al pagamento che deve essere effettuato dal cittadino
     *
     * @param input contiene le informazioni per identificare il pagamento, il pagatore ed il creditore
     * @return lo IUV del pagamento
     */
    GetIuvChiamanteEsternoOutput getIUVChiamanteEsterno ( GetIuvChiamanteEsternoInput input );

    /**
     * Metodo per ottenere lo IUV da associare al pagamento che deve essere effettuato dal cittadino
     *
     * @param input contiene le informazioni per identificare il pagamento, il pagatore ed il creditore
     * @return lo IUV del pagamento
     */
    GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInput input );

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
     * Metodo per aggiornare/annullare una lista di posizioni debitorie in input
     *
     * @param input contiene le informazioni per identificare le pos. debitorie
     * @return esito dell'operazione
     */
    AggiornaPosizioneDebitoriaChiamanteEsternoOutput aggiornaPosizioneChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input );

    /**
     * Metodo per aggiornare/annullare una lista di posizioni debitorie in input di tipo multibeneficiario
     *
     * @param input contiene le informazioni per identificare le pos. debitorie
     * @return esito dell'operazione
     */
    AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput
        aggiornaPosizioneChiamanteEsternoMultibeneficiario ( AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput input );
    void test ();

}
