/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Provvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsCancellaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaProvvisori;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsProvvisori;


/**
 * @author vsgro
 */
public interface ProvvisoriManager {

    public List<Provvisori> leggi ();

    public List<Provvisori> leggi ( String idEnte, String identificativoFlusso );

    public List<Provvisori> richiediProvvisori ( Configurazione conf, String identificativoEnte, List<String> causaliVersamento, Date dataInizio, Date dataFine,
        BigInteger numeroProvvisorioDa, BigInteger numeroProvvisorioA);

    public DTOOutputWsEsito aggiornaProvvisori ( DTOInputWsAggiornaProvvisori in );

    public DTOOutputWsEsito cancellaProvvisori ( DTOInputWsCancellaProvvisori in );

    public DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori );

}
