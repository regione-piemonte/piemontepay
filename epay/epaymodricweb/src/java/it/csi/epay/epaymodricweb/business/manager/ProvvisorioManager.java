/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.business.manager;

import it.csi.epay.epaymodricweb.common.exceptions.FileUploadException;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsAggiornaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsCancellaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.provvisorio.FileVO;


public interface ProvvisorioManager {

    DtoOutputWsEsito aggiornaProvvisori ( DtoInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori )
                    throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    DtoOutputWsProvvisori ricercaProvvisori (
        DtoInputWsRicercaProvvisori listaProvvisoriDaCercare ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    DtoOutputWsEsito cancellaProvvisori ( DtoInputWsCancellaProvvisori dtoInputWsCancellaProvvisori )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

    /**
     * metodo di gestione del caricamento dei provvisori da file
     *
     * @param uploadedFile
     * @param estensioneFileCsv
     */
    DtoOutputWsEsito uploadProvvisori ( FileVO uploadedFile )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception, FileUploadException;
}
