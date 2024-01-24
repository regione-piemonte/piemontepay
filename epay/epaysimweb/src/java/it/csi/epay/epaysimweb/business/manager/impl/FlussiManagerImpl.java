/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.business.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import it.csi.epay.epaysimweb.business.manager.FlussiManager;
import it.csi.epay.epaysimweb.common.Constants;
import it.csi.epay.epaysimweb.common.builder.EsitoStatoElaborazioneFlussoResponseBuilder;
import it.csi.epay.epaysimweb.common.config.LogConfig;
import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.facade.impl.EpaysimulatorDataWsFacade;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DatatypeConfigurationException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.EpaysimulatorException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.Exception_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoDettaglioPagopaDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOriginePagopaDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoSintesiPagopaDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoOutputResponse;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UnrecoverableException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoResponse;
import it.csi.epay.epaysimweb.model.flussi.EsportazioneFlussoVO;
import it.csi.epay.epaysimweb.model.flussi.FlussiDettaglioVO;
import it.csi.epay.epaysimweb.model.flussi.FlussiSintesiVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoDettaglioVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoOrigineVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoSintesiVO;
import it.csi.epay.epaysimweb.model.flussi.RicercaFlussoFiltroVO;
import it.csi.epay.epaysimweb.util.VOConverterUtils;


/**
 *
 */
@Service
@Transactional ( readOnly = true )
public class FlussiManagerImpl implements FlussiManager {

    private static final Logger LOGGER = Logger.getLogger ( LogConfig.HANDLER_SERVICES );

    @Autowired
    private EpaysimulatorDataWsFacade simulatorFacade;

    @Override
    public List<FlussoOrigineVO> ricercaFlussi ( RicercaFlussoFiltroVO filtro ) throws OperationFailedException {
        RicercaFlussoInputDTO param = VOConverterUtils.createRicercaFlussoInputDTO ( filtro );
        RicercaFlussoOutputResponse responseWS = null;
        try {
            responseWS = simulatorFacade.ricercaFlusso ( param );
        } catch ( EpaysimulatorException_Exception | DatatypeConfigurationException_Exception e ) {
            LOGGER.error ( "errore in fase di richiamo del servizio ricercaFlusso: ", e );
            throw new OperationFailedException ( "errore in fase di richiamo del servizio ricercaFlusso", e );
        }
        Assert.notNull ( responseWS, "Ritorno del WS ricercaFlusso nullo!" );
        if ( !Constants.WS_ESITO_OK_DEFAULT.equals ( responseWS.getCodiceEsito () ) ) {
            return new ArrayList<> ();
        }
        List<FlussoOrigineVO> ret = new ArrayList<> ();
        for ( FlussoOriginePagopaOutputDTO flussoOriginePagopaDTO: responseWS.getTestata () ) {
            ret.add ( VOConverterUtils.createFlussoOrigineVO ( flussoOriginePagopaDTO ) );
        }
        return ret;
    }

    @Override
    public UpdateEsitoStatoElaborazioneFlussoResponse updateEsitoStatoElaborazioneFlusso ( UpdateEsitoStatoElaborazioneFlussoInputDTO param )
                    throws OperationFailedException {
        UpdateEsitoStatoElaborazioneFlussoOutputDTO responseWS = null;
        try {
            responseWS = simulatorFacade.updateEsitoStatoElaborazioneFlusso ( param );
        } catch ( EpaysimulatorException_Exception e ) {
            LOGGER.error ( "errore in fase di richiamo del servizio ricercaFlusso: ", e );
            throw new OperationFailedException ( "errore in fase di richiamo del servizio update esito stato elaborazione flusso", e );
        }
        Assert.notNull ( responseWS, "Ritorno del WS updateEsitoStatoElaborazioneFlusso nullo!" );
        if ( !Constants.WS_ESITO_OK_DEFAULT.equals ( responseWS.getCodiceEsito () ) ) {
            throw new OperationFailedException ( String.format ( "Errore in fase di invocazione del servizio", responseWS.getDescrizioneEsito () ) );
        }

        return esitoStatoElaborazioneFlussoResponse ( responseWS );
    }

    private UpdateEsitoStatoElaborazioneFlussoResponse esitoStatoElaborazioneFlussoResponse ( UpdateEsitoStatoElaborazioneFlussoOutputDTO responseWS ) {
        return new EsitoStatoElaborazioneFlussoResponseBuilder ()
                        .withCodiceEsito ( responseWS.getCodiceEsito () )
                        .withCodiceMessaggio ( responseWS.getCodiceMessaggio () )
                        .withCodiceStato ( responseWS.getCodiceStato () )
                        .withDescrizioneEsito ( responseWS.getDescrizioneEsito () )
                        .build ();
    }

    @Override
    public FlussiDettaglioVO ricercaFlussoSintesiPagoPa ( FlussoSintesiVO flussoSintesi ) throws OperationFailedException {
        FlussoSintesiPagopaDTO param = new FlussoSintesiPagopaDTO ();
        param.setId ( flussoSintesi.getId () );
        FlussoSintesiPagopaOutputDTO responseWS = null;
        try {
            responseWS = simulatorFacade.ricercaFlussoSintesiPagoPa ( param );
        } catch ( EpaysimulatorException_Exception e ) {
            LOGGER.error ( "errore in fase di richiamo del servizio ricercaFlussoSintesiPagoPa: ", e );
            throw new OperationFailedException ( "errore in fase di richiamo del servizio ricercaFlussoSintesiPagoPa", e );
        }
        Assert.notNull ( responseWS, "Ritorno del WS ricercaFlussoSintesiPagoPa nullo!" );
        if ( !Constants.WS_ESITO_OK_DEFAULT.equals ( responseWS.getCodiceEsito () ) ) {
            return new FlussiDettaglioVO ();
        }

        FlussiDettaglioVO result = new FlussiDettaglioVO ();
        result.setFlussoSintesi ( VOConverterUtils.createFlussoSintesiVO ( responseWS ) );
        List<FlussoDettaglioVO> listFlussoDettaglioVO = new ArrayList<> ();
        for ( FlussoDettaglioPagopaOutputDTO flussoDettaglioPagopaOutputDTO: responseWS.getListFlussoDettaglioPagopaOutputDTO () ) {
            listFlussoDettaglioVO.add ( VOConverterUtils.createFlussoDettaglioVO ( flussoDettaglioPagopaOutputDTO ) );
        }
        result.setFlussiDettaglio ( listFlussoDettaglioVO );

        return result;
    }

    @Override
    public FlussiSintesiVO ricercaFlussoOriginePagoPa ( FlussoOrigineVO flussoOrigine ) throws OperationFailedException {
        FlussoOriginePagopaDTO param = new FlussoOriginePagopaDTO ();
        param.setId ( flussoOrigine.getId () );
        FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO = null;
        try {
            flussoOriginePagopaOutputDTO = simulatorFacade.ricercaFlussoOriginePagoPa ( param );
        } catch ( Exception_Exception | EpaysimulatorException_Exception | UnrecoverableException_Exception e ) {
            LOGGER.error ( "errore in fase di richiamo del servizio ricercaFlussoOriginePagoPa: ", e );
            throw new OperationFailedException ( "errore in fase di richiamo del servizio ricercaFlussoOriginePagoPa", e );
        }
        Assert.notNull ( flussoOriginePagopaOutputDTO, "Ritorno del WS ricercaFlussoOriginePagoPa nullo!" );
        if ( !Constants.WS_ESITO_OK_DEFAULT.equals ( flussoOriginePagopaOutputDTO.getCodiceEsito () ) ) {
            return new FlussiSintesiVO ();
        }

        FlussiSintesiVO result = new FlussiSintesiVO ();
        result.setFlussoOrigine ( VOConverterUtils.createFlussoOrigineVO ( flussoOriginePagopaOutputDTO ) );

        List<FlussoSintesiVO> listFlussoSintesiVO = new ArrayList<> ();
        for ( FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO: flussoOriginePagopaOutputDTO.getListFlussoSintesiPagopaOutputDTO () ) {
            listFlussoSintesiVO.add ( VOConverterUtils.createFlussoSintesiVO ( flussoSintesiPagopaOutputDTO ) );
        }
        result.setFlussiSintesi ( listFlussoSintesiVO );

        return result;
    }

    @Override
    public FlussoDettaglioVO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioVO flussoDettaglioVO ) throws OperationFailedException {

        FlussoDettaglioPagopaDTO param = new FlussoDettaglioPagopaDTO ();
        param.setId ( flussoDettaglioVO.getId () );

        FlussoDettaglioPagopaOutputDTO flussoDettaglioPagopaOutputDTO = null;
        try {
            flussoDettaglioPagopaOutputDTO = simulatorFacade.ricercaFlussoDettaglioPagoPa ( param );
        } catch ( EpaysimulatorException_Exception e ) {
            LOGGER.error ( "errore in fase di richiamo del servizio ricercaFlussoDettaglioPagoPa: ", e );
            throw new OperationFailedException ( "errore in fase di richiamo del servizio ricercaFlussoDettaglioPagoPa", e );
        }
        Assert.notNull ( flussoDettaglioPagopaOutputDTO, "Ritorno del WS ricercaFlussoDettaglioPagoPa nullo!" );
        if ( !Constants.WS_ESITO_OK_DEFAULT.equals ( flussoDettaglioPagopaOutputDTO.getCodiceEsito () ) ) {
            return new FlussoDettaglioVO ();
        }

        FlussoDettaglioVO result = VOConverterUtils.createFlussoDettaglioVO ( flussoDettaglioPagopaOutputDTO );

        return result;
    }

    @Override
    public List<EsportazioneFlussoVO> ricercaFlussiDaEsportare ( RicercaFlussoFiltroVO filtro ) throws OperationFailedException {

        List<EsportazioneFlussoVO> flussi = new ArrayList<> ();

        List<FlussoOrigineVO> flussoOrigineVO = this.ricercaFlussi ( filtro );

        if ( null != flussoOrigineVO && flussoOrigineVO.size () > 0 ) {
            for ( FlussoOrigineVO flussoOrigine: flussoOrigineVO ) {

                FlussiSintesiVO flussiSintesiVO = this.ricercaFlussoOriginePagoPa ( flussoOrigine );

                if ( null != flussiSintesiVO && flussiSintesiVO.getFlussiSintesi () != null && flussiSintesiVO.getFlussiSintesi ().size () > 0 ) {
                    for ( FlussoSintesiVO flussoSintesiVO: flussiSintesiVO.getFlussiSintesi () ) {

                        FlussiDettaglioVO flussiDettaglioVO = this.ricercaFlussoSintesiPagoPa ( flussoSintesiVO );

                        if ( null != flussiDettaglioVO && flussiDettaglioVO.getFlussiDettaglio () != null
                                        && flussiDettaglioVO.getFlussiDettaglio ().size () > 0 ) {

                            for ( FlussoDettaglioVO flussoDettaglioVO: flussiDettaglioVO.getFlussiDettaglio () ) {
                                flussi.add ( new EsportazioneFlussoVO ( flussoOrigine, flussoSintesiVO, flussoDettaglioVO ) );
                            }

                        } else {
                            flussi.add ( new EsportazioneFlussoVO ( flussoOrigine, flussoSintesiVO, null ) );
                        }

                    }
                } else {
                    flussi.add ( new EsportazioneFlussoVO ( flussoOrigine, null, null ) );
                }

            }
        }

        return flussi;
    }

}
