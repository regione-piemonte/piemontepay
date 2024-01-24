/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCambioStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.EsitoFlussiPagoPAResponse;

/**
 * @author vsgro
 */
public interface FlussoOrigineManager {

    public  FlussoOrigine leggi ( Long id );

    public List<FlussoOrigine> leggi ( ArrayList<Long> ids );

    public void cambiaStatoFlusso ( FlussoOrigine flusso, StatoFlussoEnum statoFlussoEnum );

    public int aggiornaContatoreTentativi ( Integer contatoreTentativi, String identificativoFlusso );

    public DTOOutputWsFlussoOrigine ricercaFlussoOrigine (DTOInputWsRicercaFlussoOrigine inputWs);

    //lfantini
    public List<FlussoOrigine> recuperaFlussiDaElaborare ( String identificativoIstitutoRicevente, List<String> identificativiFlusso, int pageNumber,
        int pageSize, List<String> statiDaEscludere, Long idElaborazione );

    public void impostaStatoFlussoEdElaborazione(FlussoOrigine flussoOrigine, Elaborazione elaborazione);

    public DTOOutputWsCambioStatoFlusso aggiornaStatoFlusso(List<String> identificativoFlusso, String idEnte,
        String nuovoStatoFlusso);
    //NG
    public EsitoFlussiPagoPAResponse esitoFlussiPagoPA ();

    // LF 10/05/2021
    public long contaFlussiOrigineDaElaborare ( String identificativoIstitutoRicevente, List<String> identificativiFlusso, List<String> statiDaEscludere );

}

