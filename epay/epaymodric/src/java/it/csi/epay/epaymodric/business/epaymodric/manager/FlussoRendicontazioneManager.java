/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAcquisizioneFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSpacchettamentoFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsElaborazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoAcquisizione;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TrasmettiFlussoRendicontazioneExtRequestType;


public interface FlussoRendicontazioneManager {

    public ResponseType acquisciFlussoRendicontazione ( TrasmettiFlussoRendicontazioneExtRequestType in, TipoAcquisizione tipoAcquisizione );

    public DTOOutputWsElaborazione spacchettaFlussiRendicontazioneDaSpacchettareByEnte ( DTOInputWsAcquisizioneFlussoRendicontazione in );

    public DTOOutputWsElaborazione spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso ( DTOInputWsSpacchettamentoFlussoRendicontazione in );

    //Metodi locali

    public void spacchettaSingoloFlussoConTestBusiness ( CblTEnte ente, CblTFlussoOrigine flusso ) throws Exception;

    public void spacchettaSingoloFlussoUndo ( CblTEnte ente, CblTFlussoOrigine flusso ) throws Exception;
}
