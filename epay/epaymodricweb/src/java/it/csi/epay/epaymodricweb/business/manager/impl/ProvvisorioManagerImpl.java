/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodricweb.business.manager.ProvvisorioManager;
import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.common.exceptions.FileUploadException;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.facade.EpaymodricWsFacade;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsAggiornaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsCancellaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.provvisorio.DTOProvvisorioBuilder;
import it.csi.epay.epaymodricweb.model.provvisorio.FileVO;
import it.csi.epay.epaymodricweb.model.provvisorio.ProvvisorioUploadVO;
import it.csi.epay.epaymodricweb.model.provvisorio.StatoProvvisorio;
import it.csi.epay.epaymodricweb.model.provvisorio.UploadFileVO;
import it.csi.epay.epaymodricweb.util.CSVUtils;
import it.csi.epay.epaymodricweb.util.XLSUtils;


@Service
public class ProvvisorioManagerImpl implements ProvvisorioManager {

    @Autowired
    private EpaymodricWsFacade service;

    @Override
    public DtoOutputWsEsito aggiornaProvvisori ( DtoInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori )
                    throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        return service.aggiornaProvvisori ( dtoInputWsAggiornaProvvisori );
    }

    @Override
    public DtoOutputWsProvvisori ricercaProvvisori ( DtoInputWsRicercaProvvisori listaProvvisoriDaCercare )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        return service.ricercaProvvisori ( listaProvvisoriDaCercare );
    }

    @Override
    public DtoOutputWsEsito cancellaProvvisori ( DtoInputWsCancellaProvvisori dtoInputWsCancellaProvvisori )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        return service.cancellaProvvisori ( dtoInputWsCancellaProvvisori );
    }

    @Override
    public DtoOutputWsEsito uploadProvvisori ( FileVO uploadedFile )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception, FileUploadException {

        UploadFileVO uploadFileVO = new UploadFileVO ();

        if ( Constants.ESTENSIONE_FILE_CSV.equals ( uploadedFile.getEstensione () ) ) {
            try {
                uploadFileVO = CSVUtils.mapFileToVO ( uploadedFile.getFileContent () );
            } catch ( Exception e ) {
                throw new Exception_Exception ( e.getMessage () );
            }
        } else if ( Constants.ESTENSIONE_FILE_XLS.equals ( uploadedFile.getEstensione () ) ) {
            uploadFileVO = XLSUtils.mapFileToVO ( uploadedFile.getFileContent () );
        }
        if ( null == uploadFileVO.getErroriList () || uploadFileVO.getErroriList ().size () == 0 ) {
            DtoInputWsAggiornaProvvisori dtoInputWsAggiornaProvvisori = new DtoInputWsAggiornaProvvisori ();
            List<ProvvisorioUploadVO> listaVO = uploadFileVO.getProvvisori ();
            for ( ProvvisorioUploadVO provvisorioUploadVO: listaVO ) {
                dtoInputWsAggiornaProvvisori.getDtoProvvisorioList ().add ( new DTOProvvisorioBuilder ()
                    .withAnnoEsercizio ( provvisorioUploadVO.getAnnoEsercizio () )
                    .withAnnoProvvisorio ( provvisorioUploadVO.getAnnoProvvisorio () )
                    .withDataMovimento ( provvisorioUploadVO.getDataMovimento () )
                    .withDescrizione ( provvisorioUploadVO.getDescrizione () )
                    .withIdentificativoFlusso ( provvisorioUploadVO.getIdentificativoFlusso () )
                    .withImportoProvvisorio ( provvisorioUploadVO.getImportoProvvisorio () )
                    .withNumeroProvvisorio ( provvisorioUploadVO.getNumeroProvvisorio () )
                    .withStato ( StatoProvvisorio.VALIDO.name () )
                    .build () );
            }

            dtoInputWsAggiornaProvvisori.setNonBatch ( true );
            return service.aggiornaProvvisori ( dtoInputWsAggiornaProvvisori );
        } else {
            throw new FileUploadException ( uploadFileVO.getErroriList () );
        }
    }

}
