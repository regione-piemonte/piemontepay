/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.batch;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputVuoto;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAttivaElaborazioneConStatiDaEscludere;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsEseguiElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;


@WebService ( name = "EpaymodricJob" )
public interface EpaymodricJob {

    // Caso d'uso : 2.2.6
    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsMotoreDiRiconciliazione attivaElaborazione ( @WebParam ( name = "param" ) DTOInputVuoto input )
                    throws EpaymodricException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsMotoreDiRiconciliazione attivaElaborazioneConStatiDaEscludere (
        @WebParam ( name = "attivaElaborazioneInputConStatiDaEscludere" ) DTOInputWsAttivaElaborazioneConStatiDaEscludere input )
                    throws EpaymodricException, Exception, UnrecoverableException;

    // Caso d'uso : 2.2.7
    @WebResult ( name = "result" )
    @WebMethod
    public DTOOutputWsMotoreDiRiconciliazione eseguiElaborazione ( @WebParam ( name = "input" ) DTOInputWsEseguiElaborazione input )
                    throws EpaymodricException, Exception, UnrecoverableException;

}
