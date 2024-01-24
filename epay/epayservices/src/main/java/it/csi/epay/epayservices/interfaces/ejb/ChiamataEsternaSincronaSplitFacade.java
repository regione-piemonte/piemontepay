/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

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


/**
 * @author Fabrizio Naro
 */
@Remote
public interface ChiamataEsternaSincronaSplitFacade {

    /**
     * Metodo per ottenere lo IUV associato ad un pagamento
     *
     * @param input contiene le informazioni relative a chi ha effettuato la
     *              chiamata, al pagamento, al pagatore e al debitore
     * @return un oggetto contenente l'esito della chiamata e, nel caso di esito
     *         positivo, lo iuv del pagamento
     */
    GetIuvChiamanteEsternoOutput getIUVChiamanteEsterno(GetIuvChiamanteEsternoInput input);

    //CSI_PAG-1889 INIZIO
    /**
     * Metodo per ottenere lo IUV associato ad un pagamento multibeneficiario
     *
     * @param input contiene le informazioni relative a chi ha effettuato la chiamata, al pagamento, al pagatore e al debitore
     * @return un oggetto contenente l'esito della chiamata e, nel caso di esito positivo, lo iuv del pagamento
     */
    GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInput input );

    //CSI_PAG-1889 FINE
    /**
     * Metodo per ottenere lo IUV associato ad un pagamento
     *
     * @param input contiene le informazioni relative a chi ha effettuato la chiamata, al pagamento, al pagatore e al debitore
     * @return un oggetto contenente l'esito della chiamata e, nel caso di esito positivo, lo iuv del pagamento
     */
	GetIuvChiamanteEsternoOutputContainer getListIUVChiamanteEsterno ( GetIuvChiamanteEsternoInputContainer input );

    /**
     * Metodo per ottenere lo IUV associato ad un pagamento multibeneficiario
     *
     * @param input contiene le informazioni relative a chi ha effettuato la chiamata, al pagamento, al pagatore e al debitore
     * @return un oggetto contenente l'esito della chiamata e, nel caso di esito positivo, lo iuv del pagamento
     */
	GetIuvMultibeneficiarioChiamanteEsternoOutputContainer
		getListIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInputContainer input );

    /**
     * Metodo per ottenere l'url del WISP che consente al cittadino di effettuare il pagamento
     *
     * @param input contiene le informazioni relative a chi ha effettuato la chiamata, all'identificativo del pagamento, al codice fiscale del pagatore e allo
     *            iuv del pagamento
     * @return un oggetto contenente l'esito della chiamata e, nel caso di esito positivo, l'url del wisp
     */
    PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno(PagamentoIuvChiamanteEsternoInput input);

    /**
     * Metodo per verificare che il chiamante esterno, tracciato nella tabella
     * epay_t_tracciabilita_chiamante_esterno e cercato per l'idTransazione passata
     * in input, sia associato al tipo pagamento passato in input. In questo modo
     * significa che il pagamento e' avvenuto mediante la chiamata esterna asincrona
     *
     * @param idTransazione   e' l'id della transazione che viene generata da mdp
     *                        per poter effettuare un pagamento e che viene poi
     *                        restituita da wisp
     * @param idTipoPagamento e' l'id del tipo pagamento del pagamento che si e'
     *                        effettuato o meno
     * @return un valore booleano a seconda che il pagamento ed il chiamante esterno
     *         siano associati o meno
     */
    Boolean verificaAutorizzazione(String idTransazione, Long idTipoPagamento);


    /**
     * Metodo per ottenere la stampa Rt
     *
     * @param input i dati di identificazione della stampa e il formato della stampa
     * @return un oggetto contenente l'esito della chiamata e, nel caso di esito
     *         positivo, la stampa rt nel formato richiesto
     */
    GetRTChiamanteEsternoOutput getRTChiamanteEsterno ( GetRTChiamanteEsternoInput input );

    /**
     * Metodo per aggiornare una (o piu di una) posizione debitoria
     * 
     * @param input
     * @return
     */
    AggiornaPosizioneDebitoriaChiamanteEsternoOutput aggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input );

    /**
     * Metodo per aggiornare una (o piu di una) posizione debitoria di tipo multibeneficiario
     * @param input
     * @return
     */
    AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput
        aggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario ( AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput input );

    GetIuvMultibeneficiarioChiamanteEsternoOutput
        getIUVMultibeneficiarioChiamanteEsternov1 ( it.csi.epay.epayservices.model.v1.GetIuvMultibeneficiarioChiamanteEsternoInput input );

}
