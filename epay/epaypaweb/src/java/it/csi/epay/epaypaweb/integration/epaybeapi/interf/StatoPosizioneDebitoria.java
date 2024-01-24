/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaybeapi.interf;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 */

public interface StatoPosizioneDebitoria {

    /**
     * Metodo per ottenere il pdf con l'avviso di pagamento.
     * 
     * @param request, contiene le informazioni utili che consentono di prelevare dal database di sportello tutti i dati necessari a compilare correttamente
     *            l'avviso
     * @return la risposta della chiamata con l'esito della risposta e il pdf da stampare
     * @throws MalformedURLException 
     * @throws IOException 
     */
    String getStatoPosizioneDebitoria ( String organization, String paymentType, String iuv ) throws  IOException;
}
