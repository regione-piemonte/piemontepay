/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import it.csi.epay.epaysim.business.epaysim.manager.FlussiManager;
import it.csi.epay.epaysim.business.epaysim.manager.ProvvisorioManager;
import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio;
import it.csi.epay.epaysim.business.epaysim.utils.Costanti;
import it.csi.epay.epaysim.business.epaysim.utils.Messaggi;
import it.csi.epay.epaysim.business.epaysim.utils.StatoProvvisorio;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysim.dto.GiornaleDiCassaDTO;
import it.csi.epay.epaysim.dto.GiornaleDiCassaInputDTO;
import it.csi.epay.epaysim.dto.GiornaleDiCassaOutputDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioInputDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioOutputDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioOutputResponse;
import it.csi.epay.epaysim.dto.ProvvisorioDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoErroreInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoOutputResponse;
import it.csi.epay.epaysim.dto.UpdateEsitoStatoElaborazioneFlussoInputDTO;
import it.csi.epay.epaysim.dto.UpdateEsitoStatoElaborazioneFlussoOutputDTO;
import it.csi.epay.epaysim.dto.base.DTOProvvisorio;
import it.csi.epay.epaysim.dto.ws.DTOInputWsRicercaProvvisori;
import it.csi.epay.epaysim.dto.ws.DTOOutputWsEsito;
import it.csi.epay.epaysim.dto.ws.DTOOutputWsProvvisori;
import it.csi.epay.epaysim.exception.epaysim.EpaysimulatorException;
import it.csi.epay.epaysim.interfacews.epaysim.CostantiErrori;
import it.csi.epay.epaysim.util.DateUtils;
import it.csi.epay.epaysim.util.LogConfig;
import it.csi.util.performance.StopWatch;


/**
 *
 */
@Service
public class EpaysimulatorDatawsBusinessImpl implements EpaysimulatorDatawsBusiness {

    private final static String STOPWATCH_CATEGORY = "epaysimulatorserv";

    private static final Logger LOGGER = Logger.getLogger ( LogConfig.HANDLER_SERVICES );

    @Autowired
    private FlussiManager flussiManager;

    @Autowired
    private ProvvisorioManager provvisorioManager;

    @Override
    public GiornaleDiCassaOutputDTO ricercaGiornaleDiCassa ( GiornaleDiCassaInputDTO giornaleDiCassaInputDTO ) throws EpaysimulatorException {
        LOGGER.debug ( "[EpaysimulatorDatawsBusinessImpl::verificaGiornaleDiCassa] - START" );
        StopWatch watcher = new it.csi.util.performance.StopWatch ( STOPWATCH_CATEGORY );
        watcher.start ();
        try {

            GiornaleDiCassaOutputDTO giornaleDiCassaOutputDTO = new GiornaleDiCassaOutputDTO ();
            giornaleDiCassaOutputDTO.setCodiceEsito ( Costanti.RESULT_CODE_OK );
            giornaleDiCassaOutputDTO.setGiornaleDiCassaDTO ( new GiornaleDiCassaDTO () );

            if ( giornaleDiCassaInputDTO == null || giornaleDiCassaInputDTO.getIdentificativoFlussoBT () == null
                || giornaleDiCassaInputDTO.getIdentificativoFlussoBT ().isEmpty () ) {
                giornaleDiCassaOutputDTO.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
                giornaleDiCassaOutputDTO
                    .setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "identificativo_flusso_BT" ) );
            } else {
                GiornaleDiCassaDTO giornaleDiCassa = provvisorioManager.ricercaGiornaleDiCassa ( giornaleDiCassaInputDTO.getIdentificativoFlussoBT () );
                if ( giornaleDiCassa != null ) {
                    giornaleDiCassaOutputDTO
                        .setDescrizioneEsito ( String.format ( Messaggi.GIORNALE_DI_CASSA_PRESENTE, giornaleDiCassaInputDTO.getIdentificativoFlussoBT () ) );
                    giornaleDiCassaOutputDTO.setGiornaleDiCassaDTO ( giornaleDiCassa );
                }
            }
            return giornaleDiCassaOutputDTO;

        } catch ( RuntimeException ex ) {
            LOGGER.error ( "[EpaysimulatorDatawsBusinessImpl::verificaGiornaleDiCassa] - Errore imprevisto occorso durante l'esecuzione del metodo:" + ex, ex );
            throw new RuntimeException ( "Errore imprevisto occorso durante l'esecuzione del metodo:" + ex, ex );
        } catch ( Throwable ex ) {
            if ( Exception.class.isAssignableFrom ( ex.getClass () ) ) {
                LOGGER.error ( "[EpaysimulatorDatawsBusinessImpl::verificaGiornaleDiCassa]  - Errore CSI occorso durante l'esecuzione del metodo:" + ex, ex );
                throw (EpaysimulatorException) ex;
            } else {
                LOGGER.error ( "[EpaysimulatorDatawsBusinessImpl::verificaGiornaleDiCassa]  - Errore imprevisto occorso durante l'esecuzione del metodo:" + ex,
                    ex );
                throw new EpaysimulatorException ( "", "Errore imprevisto occorso durante l'esecuzione del metodo:" + ex, ex );
            }
        } finally {
            // fine misurazione
            watcher.stop ();
            watcher.dumpElapsed ( "EpaysimulatorDatawsBusinessImpl", "verificaGiornaleDiCassa()",
                "invocazione servizio [epaysimulator]::[verificaGiornaleDiCassa]", "(valore input omesso)" );
            LOGGER.debug ( "[EpaysimulatorDatawsBusinessImpl::verificaGiornaleDiCassa] - END" );
        }
    }

    @Override
    @Transactional ( propagation = Propagation.NOT_SUPPORTED )
    public InserisciProvvisorioOutputResponse inserisciProvvisorio ( InserisciProvvisorioInputDTO inserisciProvvisorioInputDTO )
                    throws EpaysimulatorException { //, Exception, UnrecoverableException {
        LOGGER.debug ( "[EpaysimulatorDatawsBusinessImpl::inserisciProvvisorio] - START" );
        StopWatch watcher = new it.csi.util.performance.StopWatch ( STOPWATCH_CATEGORY );
        watcher.start ();
        try {
            InserisciProvvisorioOutputResponse inserisciProvvisorioOutputResponse = new InserisciProvvisorioOutputResponse ();
            inserisciProvvisorioOutputResponse.setInserisciProvvisorioOutputDTOList ( new ArrayList<> () );
            inserisciProvvisorioOutputResponse.setCodiceEsito ( Costanti.RESULT_CODE_OK );
            int inseriti = 0;
            int provvisoriTotali = 0;

            if ( inserisciProvvisorioInputDTO == null ) {
                inserisciProvvisorioOutputResponse.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
                inserisciProvvisorioOutputResponse.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "InserisciProvvisorioInputDTO" ) );
            } else if ( CollectionUtils.isEmpty ( inserisciProvvisorioInputDTO.getProvvisorioDTOList () ) ) {
                inserisciProvvisorioOutputResponse.setCodiceEsito ( Costanti.RESULT_CODE_FIELD_REQUIRED );
                inserisciProvvisorioOutputResponse.setDescrizioneEsito ( String.format ( Messaggi.FIELD_REQUIRED, "provvisorioDTOList" ) );
            } else {
                GiornaleDiCassaDTO giornaleDiCassa = provvisorioManager.inserisciGiornaleDiCassa ( inserisciProvvisorioInputDTO.getXmlFlusso (),
                    inserisciProvvisorioInputDTO.getIdentificativoFlussoBT (),
                    inserisciProvvisorioInputDTO.getCaller ().getPrincipal ().getCodiceFiscale () );
                List<String> errori = new ArrayList<> ();
                provvisoriTotali = inserisciProvvisorioInputDTO.getProvvisorioDTOList ().size ();
                for ( ProvvisorioDTO provv: inserisciProvvisorioInputDTO.getProvvisorioDTOList () ) {
                    InserisciProvvisorioOutputDTO outputDTO = new InserisciProvvisorioOutputDTO ();
                    try {
                        outputDTO = provvisorioManager.inserisciProvvisorio ( provv, giornaleDiCassa.getId (),
                            inserisciProvvisorioInputDTO.getCaller ().getPrincipal ().getCodiceFiscale () );
                        if ( outputDTO.getCodiceEsito ().equals ( Costanti.RESULT_CODE_OK ) ) {
                            inseriti++;
                        }
                    } catch ( Exception ex ) {
                        outputDTO.setCodiceEsito ( Costanti.RESULT_CODE_INTERNAL_ERROR );
                        String errorMessage = ex.getMessage ();
                        if ( StringUtils.hasLength ( errorMessage ) ) {
                            outputDTO.setDescrizioneEsito ( String.format ( Messaggi.PROVVISORIO_PRESENTE_IN_BASE_DATI, provv.getIdentificativoFlusso () ) );
                            errori.add ( String.format ( Messaggi.PROVVISORIO_PRESENTE_IN_BASE_DATI, provv.getIdentificativoFlusso () ) );
                        } else {
                            outputDTO.setDescrizioneEsito ( String.format ( Messaggi.ERRORE_INSERIMENTO_PROVVISORIO, provv.getIdentificativoFlusso () ) );
                            errori.add ( String.format ( Messaggi.ERRORE_INSERIMENTO_PROVVISORIO, provv.getIdentificativoFlusso () ) );
                        }

                    }
                    outputDTO.setProvvisorioDTO ( provv );
                    inserisciProvvisorioOutputResponse.getInserisciProvvisorioOutputDTOList ().add ( outputDTO );
                }

                if ( inseriti == 0 ) {
                    inserisciProvvisorioOutputResponse.setCodiceEsito ( Costanti.RESULT_CODE_OPERATION_KO );
                    inserisciProvvisorioOutputResponse.setDescrizioneEsito ( "NESSUN INSERIMENTO. \n" + String.join ( "\n\n", errori ) );
                } else if ( inseriti < provvisoriTotali ) {
                    inserisciProvvisorioOutputResponse.setCodiceEsito ( Costanti.RESULT_CODE_OPERATION_COMPLETED_WITH_ERROR );
                    inserisciProvvisorioOutputResponse.setDescrizioneEsito ( String.join ( "\n\n", errori ) );
                }
            }
            return inserisciProvvisorioOutputResponse;
        } catch ( RuntimeException ex ) {
            LOGGER.error ( "[EpaysimulatorDatawsBusinessImpl::inserisciProvvisorio] - Errore imprevisto occorso durante l'esecuzione del metodo:" + ex, ex );
            throw new RuntimeException ( "Errore imprevisto occorso durante l'esecuzione del metodo:" + ex, ex );
        } catch ( Throwable ex ) {
            if ( Exception.class.isAssignableFrom ( ex.getClass () ) ) {
                LOGGER.error ( "[EpaysimulatorDatawsBusinessImpl::inserisciProvvisorio]  - Errore CSI occorso durante l'esecuzione del metodo:" + ex, ex );
                throw (EpaysimulatorException) ex;
            } else {
                LOGGER.error ( "[EpaysimulatorDatawsBusinessImpl::inserisciProvvisorio]  - Errore imprevisto occorso durante l'esecuzione del metodo:" + ex,
                    ex );
                throw new EpaysimulatorException ("", "Errore imprevisto occorso durante l'esecuzione del metodo:" + ex, ex );
            }
        } finally {
            // fine misurazione
            watcher.stop ();
            watcher.dumpElapsed ( "EpaysimulatorDatawsBusinessImpl", "inserisciProvvisorio()",
                "invocazione servizio [epaysimulator]::[inserisciProvvisorio]", "(valore input omesso)" );
            LOGGER.debug ( "[EpaysimulatorDatawsBusinessImpl::inserisciProvvisorio] - END" );
        }
    }

    @Override
    @Transactional ( propagation = Propagation.NOT_SUPPORTED )
    public UpdateEsitoStatoElaborazioneFlussoOutputDTO
    updateEsitoStatoElaborazioneFlusso ( UpdateEsitoStatoElaborazioneFlussoInputDTO updateEsitoStatoElaborazioneFlussoInputDTO )
                    throws EpaysimulatorException { //, UnrecoverableException, Exception {
        StringBuilder flussiDaAggiornareKO = new StringBuilder ();

        Assert.notNull ( updateEsitoStatoElaborazioneFlussoInputDTO, "Oggetto di input non valorizzato" );
        Assert.notEmpty ( updateEsitoStatoElaborazioneFlussoInputDTO.getIdentificativiFlussoOrigineList (), "Deve essere specificato almeno un id flusso" );

        UpdateEsitoStatoElaborazioneFlussoOutputDTO updateEsitoStatoElaborazioneFlussoOutputDTO = new UpdateEsitoStatoElaborazioneFlussoOutputDTO ();

        for ( String idFlusso: updateEsitoStatoElaborazioneFlussoInputDTO.getIdentificativiFlussoOrigineList () ) {
            try {
                flussiManager.updateEsitoStatoElaborazioneFlussoSingolo ( Long.valueOf ( idFlusso ),
                    updateEsitoStatoElaborazioneFlussoInputDTO.getCaller ().getPrincipal ().getCodiceFiscale (),
                    updateEsitoStatoElaborazioneFlussoInputDTO.getNuovoStato () );
            } catch ( Exception e ) {
                LOGGER.error (
                    String.format ( "Errore in fase di aggiornamento dello stato del flusso: %s", idFlusso ),
                    e );
                flussiDaAggiornareKO.append ( idFlusso );
            }
        }
        if ( flussiDaAggiornareKO.length () <= 0 ) {
            updateEsitoStatoElaborazioneFlussoOutputDTO.setCodiceEsito ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            updateEsitoStatoElaborazioneFlussoOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.WS_ESITO_OK_DEFAULT ) );
            updateEsitoStatoElaborazioneFlussoOutputDTO.setDescrizioneEsito ( null );
        } else {
            updateEsitoStatoElaborazioneFlussoOutputDTO.setCodiceEsito ( Costanti.RESULT_CODE_OPERATION_COMPLETED_WITH_ERROR );
            updateEsitoStatoElaborazioneFlussoOutputDTO.setCodiceStato ( Integer.valueOf ( CostantiErrori.ELABORAZIONE_TERMINATA_CON_ERRORI ) );
            updateEsitoStatoElaborazioneFlussoOutputDTO
            .setDescrizioneEsito ( String.format ( Messaggi.ELABORAZIONE_TERMINATA_CON_ERRORI, flussiDaAggiornareKO ) );
        }

        return updateEsitoStatoElaborazioneFlussoOutputDTO;
    }

    @Override
    public RicercaFlussoOutputResponse ricercaFlusso ( RicercaFlussoInputDTO ricercaFlussoInputDTO )
                    throws EpaysimulatorException, DatatypeConfigurationException {//, UnrecoverableException, Exception;, UnrecoverableException, Exception {
        return flussiManager.ricercaFlusso ( ricercaFlussoInputDTO );
    }

    //---------------------------------------------------------
    //Logica attinta da epaymodric
    //---------------------------------------------------------

    @Override
    public DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori ) {

        if ( ( null == dtoInputWsRicercaProvvisori.getDataDa () && null != dtoInputWsRicercaProvvisori.getDataA () )
                        || ( null != dtoInputWsRicercaProvvisori.getDataDa () && null == dtoInputWsRicercaProvvisori.getDataA () ) ) {
            throw new IllegalArgumentException ( "Le date di inizio e fine devono essere valorizzate contemporaneamente" );
        }

        if ( null != dtoInputWsRicercaProvvisori.getDataDa ()
                        && dtoInputWsRicercaProvvisori.getDataA ().toGregorianCalendar ().before ( dtoInputWsRicercaProvvisori.getDataDa () ) ) {
            throw new IllegalStateException ( "La data di fine deve essere uguale o successiva a quella di inizio" );
        }

        DTOOutputWsProvvisori response = new DTOOutputWsProvvisori ();

        List<SimTProvvisorio> provvisoriList = new ArrayList<SimTProvvisorio> ();

        ArrayList<DTOProvvisorio> provvisoriDto = new ArrayList<DTOProvvisorio> ();

        DTOOutputWsEsito esito = new DTOOutputWsEsito ();
        esito.setCodiceErrore ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        esito.setDescrizione ( "Recupero provvisori OK" );
        esito.setEsito ( "OK" );

        response.setEsito ( esito );
        response.setProvvisori ( provvisoriDto );

        try {
            if ( dtoInputWsRicercaProvvisori.getId () != null ) {
                SimTProvvisorio item = provvisorioManager.find ( dtoInputWsRicercaProvvisori.getId () );
                provvisoriList.add ( item );
            } else {
                if ( dtoInputWsRicercaProvvisori.getCaller () == null || dtoInputWsRicercaProvvisori.getCaller ().getPrincipal () == null
                                || dtoInputWsRicercaProvvisori.getCaller ().getPrincipal ().getCodiceEnte () == null
                                || dtoInputWsRicercaProvvisori.getCaller ().getPrincipal ().getCodiceEnte ().isEmpty () ) {
                    throw new Exception ( "Codice fiscale ente non valorizzato dal chiamante" );
                }

                String cfEnte = dtoInputWsRicercaProvvisori.getCaller ().getPrincipal ().getCodiceEnte ();

                GregorianCalendar gcal = new GregorianCalendar ();
                gcal.setTimeInMillis ( 0 );

                if ( dtoInputWsRicercaProvvisori.getDataDa () == null )
                    dtoInputWsRicercaProvvisori.setDataDa ( DatatypeFactory.newInstance ().newXMLGregorianCalendar ( gcal ) );

                Date fromDate = DateUtils.xmlGregorianCalendarToDate ( dtoInputWsRicercaProvvisori.getDataDa () );
                Date toDate = null;

                if ( dtoInputWsRicercaProvvisori.getDataA () != null )
                    toDate = DateUtils.xmlGregorianCalendarToDate ( dtoInputWsRicercaProvvisori.getDataA () );

                if ( dtoInputWsRicercaProvvisori.getIdentificativoFlusso () == null )
                    dtoInputWsRicercaProvvisori.setIdentificativoFlusso ( "" );

                Collection<String> stati = new ArrayList<String> ();

                if ( dtoInputWsRicercaProvvisori.getIdStatoFlusso () == null || dtoInputWsRicercaProvvisori.getIdStatoFlusso ().isEmpty () ) {
                    for ( int i = 0; i < StatoProvvisorio.values ().length; i++ ) {
                        stati.add ( StatoProvvisorio.values () [i].value () );
                    }
                } else {
                    stati.add ( dtoInputWsRicercaProvvisori.getIdStatoFlusso () );
                }

                //repositoryFilter
                provvisoriList
                = provvisorioManager.find ( cfEnte, "%" + dtoInputWsRicercaProvvisori.getIdentificativoFlusso () + "%", stati, fromDate, toDate );
            }

            for ( SimTProvvisorio provvisorio: provvisoriList ) {
                DTOProvvisorio dto = new DTOProvvisorio ();

                dto.setAnnoEsercizio ( provvisorio.getAnnoEsercizio () );
                dto.setAnnoProvvisorio ( provvisorio.getAnnoProvvisorio () );
                dto.setCausale ( provvisorio.getCausale () );

                if ( provvisorio.getDataMovimento () != null )
                    dto.setDataMovimento ( DateUtils.dateToXMLGregorianCalendar ( provvisorio.getDataMovimento () ) );

                dto.setIdentificativoFlusso ( provvisorio.getIdentificativoFlusso () );
                dto.setImportoProvvisorio ( provvisorio.getImportoProvvisorio () );
                dto.setNumeroProvvisorio ( provvisorio.getNumeroProvvisorio () );
                dto.setStato ( provvisorio.getStato () );
                dto.setId ( provvisorio.getId ().longValue () );

                dto.setDescrizione ( "EMPTY DESCRIPTION" );

                provvisoriDto.add ( dto );
            }

        } catch ( Exception e ) {
            esito.setCodiceErrore ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            esito.setDescrizione ( "Errore in fase di recupero provvisori" );
            esito.setEsito ( "KO" );
            response.setEsito ( esito );
            LOGGER.error ( "ERRORE DURANTE LA CHIAMATA AL WS DI RICERCA PROVVISORI", e );
        }

        return response;
    }

    @Override
    public List<FlussoOrigineErrorePagopaOutputDTO> ricercaFlussiInErrore ( RicercaFlussoErroreInputDTO inputRicercaFlussoErrore )
                    throws EpaysimulatorException {//, UnrecoverableException, Exception;, UnrecoverableException, Exception;
        // TODO Auto-generated method stub
        return flussiManager.ricercaFlussiInErrore ( inputRicercaFlussoErrore );
    }

    //@Override
    //public RicercaProvvisorioOutputDTO ricercaProvvisori ( RicercaProvvisorioInputDTO inputRicercaProvvisorio )
    //                throws EpaysimulatorException, UnrecoverableException, Exception {
    //
    //    return flussiManager.ricercaProvvisori ( inputRicercaProvvisorio );
    //}

    @Override
    public FlussoDettaglioPagopaOutputDTO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO )
                    throws EpaysimulatorException {//, UnrecoverableException, Exception;, UnrecoverableException, Exception;

        return flussiManager.ricercaFlussoDettaglioPagoPa ( inputFlussoDettaglioPagopaDTO );
    }

    @Override
    public FlussoSintesiPagopaOutputDTO ricercaFlussoSintesiPagoPa ( FlussoSintesiPagopaDTO inputFlussoSintesiPagopaDTO )
                    throws EpaysimulatorException {//, UnrecoverableException, Exception;, UnrecoverableException, Exception;
        return flussiManager.ricercaFlussoSintesiPagoPa ( inputFlussoSintesiPagopaDTO );
    }

    @Override
    public FlussoOriginePagopaOutputDTO ricercaFlussoOriginePagoPa ( FlussoOriginePagopaDTO flussoOriginePagopaDTO ) {

        return flussiManager.ricercaFlussoOriginePagoPa ( flussoOriginePagopaDTO );
    }

}
