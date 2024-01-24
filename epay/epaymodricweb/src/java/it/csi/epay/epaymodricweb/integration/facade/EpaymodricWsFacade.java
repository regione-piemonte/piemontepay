/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.facade;

import java.net.URL;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.mapper.FlussoMapper;
import it.csi.epay.epaymodricweb.integration.mapper.InserisciElaborazioneMapper;
import it.csi.epay.epaymodricweb.integration.mapper.ParentMapper;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoCaller;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoCodiceVersamento;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoEnte;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoFlussoDettaglio;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoFlussoSintesi;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputBase;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputVuoto;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsAggiornaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsAggiornaStatoReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsCancellaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsElaborazione;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsInserisciFlussiDaRielaborare;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaCodiciVersamento;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaElaborazionePrecedente;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussoDettaglio;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaFlussoSintesi;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaStatoFlusso;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputRicercaElaborazionePrecedente;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsAggiornaStatoReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsCodiciVersamento;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEnti;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoDettaglio;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoSintesi;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsProvvisori;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteEsportazione;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsStatiElaborazione;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsStatoFlusso;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoPrincipal;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoStatoElaborazione;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoStatoFlusso;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Epaymodric;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricService;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.TipoFileReportEnum;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.GenericVO;
import it.csi.epay.epaymodricweb.model.elaborazione.ElaborazioneVO;
import it.csi.epay.epaymodricweb.model.elaborazione.RicercaElaborazionePrecedenteFiltroVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiSintesiVO;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;
import it.csi.epay.epaymodricweb.model.inseriscielaborazione.InserisciElaborazioneFiltroVO;
import it.csi.epay.epaymodricweb.model.report.FileReportVO;
import it.csi.epay.epaymodricweb.model.report.RisultatiRicercaReportVO;
import it.csi.epay.epaymodricweb.security.UserDetails;
import it.csi.epay.epaymodricweb.util.SecurityUtils;


@Service

public class EpaymodricWsFacade extends ParentFacade {

    private static final String THIS_APPLICATION = "EPAYMODRICWEB";

    private static final String MAPPING_ERROR = "Errore nella conversione della risposta dal servizio remoto";

    private static final String FLUSSO_NOT_FOUND = "Non  stato trovato nessun flusso corrispondente all'identificativo specificato.";

    private static final String REMOTE_ERROR = "Errore nella chiamata all'operazione remota: ";

    private static final String NO_RESPONSE = "Nessuna risposta dal servizio remoto";

    private EpaymodricService service;

    private Epaymodric port;

    public EpaymodricWsFacade () {
        super ();
        service = null;
        port = null;
    }

    private Epaymodric getPort () {
        if ( service == null ) {
            try {
                String endPointUrl = java.util.ResourceBundle.getBundle ( "config" )
                                .getString ( "service.epaymodricwebws.endpoint" );

                service = new EpaymodricService ( new URL ( endPointUrl + "?WSDL" ) );
                port = service.getEpaymodricPort ();

                BindingProvider bp = (BindingProvider) port;

                Map<String, Object> context = bp.getRequestContext ();
                context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointUrl );

                return port;

            } catch ( Exception e ) {
                e.printStackTrace ();
                service = null;
                port = null;
                throw new RuntimeException ( "Error contacting remote service", e );
            }
        } else {
            return port;
        }
    }

    private void addCallerInfo ( DtoInputBase input ) {
        UserDetails user = SecurityUtils.getUser ();

        DtoCaller caller = new DtoCaller ();
        caller.setCodiceApplicativo ( THIS_APPLICATION );

        if ( user != null ) {
            DtoPrincipal principal = new DtoPrincipal ();
            principal.setCodiceEnte ( user.getEnte ().getCodiceFiscale () );
            principal.setCodiceFiscale ( user.getUtente ().getCodiceFiscale () );
            caller.setPrincipal ( principal );
        }

        input.setCaller ( caller );
    }

    public FlussiDettaglioVO ricercaFlussiDettaglio ( Long idFlussoSintesi ) throws OperationFailedException {
        if ( idFlussoSintesi == null || idFlussoSintesi <= 0L ) {
            throw new InvalidParameterException ();
        }

        DtoInputWsRicercaFlussoDettaglio ricercaFlussoDettaglio = new DtoInputWsRicercaFlussoDettaglio ();
        ricercaFlussoDettaglio.setIdFlussoSintesi ( idFlussoSintesi.toString () );

        List<DtoOutputWsFlussoDettaglio> output;
        try {
            addCallerInfo ( ricercaFlussoDettaglio );
            output = getPort ().ricercaFlussiDettaglio ( ricercaFlussoDettaglio );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( output == null || output.size () < 1 ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        DtoOutputWsFlussoDettaglio outputData = output.get ( 0 );

        if ( outputData.getEsito () != null ) {
            if ( !"OK".equals ( outputData.getEsito ().getEsito () ) ) {
                throw new OperationFailedException ( outputData.getEsito ().getDescrizione () );
            }
        }

        if ( outputData.getFlussoSintesi () == null ) {
            throw new OperationFailedException ( FLUSSO_NOT_FOUND );
        }

        FlussiDettaglioVO outputVO = new FlussiDettaglioVO ();

        outputVO.setFlussoSintesi ( FlussoMapper.parseFlussoSintesiVO ( outputData.getFlussoSintesi () ) );

        outputVO.setFlussiDettaglio ( new ArrayList<> () );

        for ( DtoFlussoDettaglio dto: outputData.getFlussiDettaglio () ) {
            try {
                outputVO.getFlussiDettaglio ().add ( FlussoMapper.parseFlussoDettaglioVO ( dto ) );
            } catch ( ParseException e ) {
                throw new OperationFailedException ( MAPPING_ERROR, e );
            }
        }

        return outputVO;
    }

    public List<GenericVO> ricercaEnte ( boolean recuperaIdEnte ) throws OperationFailedException {

        DtoOutputWsEnti opzioni;
        try {
            DtoInputVuoto request = new DtoInputVuoto ();
            addCallerInfo ( request );
            opzioni = getPort ().elencaEnti ( request );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( opzioni == null || opzioni.getEnti ().size () < 1 ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        List<GenericVO> o = new ArrayList<> ();
        for ( DtoEnte ente: opzioni.getEnti () ) {
            if ( recuperaIdEnte ) {
                o.add ( new GenericVO ( ente.getId (), ente.getIdEnte (), ente.getDenominazione () ) );
            } else {
                o.add ( new GenericVO ( ente.getId (), ente.getCodiceFiscale (), ente.getDenominazione () ) );
            }
        }
        return o;
    }

    public List<GenericVO> ricercaStatoFlusso () throws OperationFailedException {
        DtoInputWsRicercaStatoFlusso input = new DtoInputWsRicercaStatoFlusso ();

        List<DtoOutputWsStatoFlusso> opzioni;
        try {
            addCallerInfo ( input );
            opzioni = getPort ().ricercaStatoFlusso ( input );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( opzioni == null || opzioni.size () < 1 ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        if ( opzioni.get ( 0 ).getEsito () != null ) {
            if ( !"OK".equals ( opzioni.get ( 0 ).getEsito ().getEsito () ) ) {
                throw new OperationFailedException ( opzioni.get ( 0 ).getEsito ().getDescrizione () );
            }
        }

        List<GenericVO> o = new ArrayList<> ();
        for ( DtoStatoFlusso opzione: opzioni.get ( 0 ).getStatiFlusso () ) {
            o.add ( new GenericVO ( Long.valueOf ( opzione.getId () ), opzione.getCodiceStato (),
                opzione.getDescrizioneStato () ) );
        }
        return o;
    }

    public List<GenericVO> ricercaStatiElaborazione () throws OperationFailedException {
        DtoInputVuoto input = new DtoInputVuoto ();

        DtoOutputWsStatiElaborazione output;
        try {
            addCallerInfo ( input );
            output = getPort ().elencaStatiElaborazione ( input );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( output == null || output.getStatiElaborazione () == null || output.getStatiElaborazione ().size () < 1 ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }


        List<GenericVO> o = new ArrayList<> ();
        for ( DtoStatoElaborazione opzione: output.getStatiElaborazione () ) {
            o.add ( new GenericVO ( Long.valueOf ( opzione.getId () ), opzione.getCodiceStato (),
                opzione.getDescrizioneStato () ) );
        }
        return o;
    }
    
    public DtoOutputWsFlussoOrigine ricercaFlussoOrigine ( RicercaFlussoFiltroVO filtro) throws OperationFailedException {
        DtoInputWsRicercaFlussoOrigine input = new DtoInputWsRicercaFlussoOrigine ();

        if ( filtro != null ) {
            if ( filtro.getDataElaborazioneDa () != null ) {
                input.setDataElaborazioneDa (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataElaborazioneDa () ) );
            }
            if ( filtro.getDataElaborazioneA () != null ) {
                input.setDataElaborazioneA (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataElaborazioneA () ) );
            }
            if ( StringUtils.isNotEmpty ( filtro.getIdentificativoFlusso () ) ) {
				Pattern pattern = Pattern.compile("^\\/RFS\\/([^\\/]+)\\/.*");
				Matcher matcher = pattern.matcher(filtro.getIdentificativoFlusso());
            	if(matcher.matches()) {
            		String causale = matcher.group(1);
            		input.setIdentificativoIstitutoRicevente(causale);
            	}
            	else {
            		input.setIdentificativoFlusso ( filtro.getIdentificativoFlusso () );
            	}
            }
            if ( StringUtils.isNotEmpty ( filtro.getStatoFlusso () ) ) {
                input.setIdStatoFlusso ( filtro.getStatoFlusso () );
            }
            if ( ( filtro.getDataElaborazioneDa () != null ) || ( filtro.getDataElaborazioneA () != null ) ) {
                if ( input.getCaller () != null && input.getCaller ().getPrincipal ().getCodiceEnte () != null ) {
                    input.setIdentificativoIstitutoRicevente ( input.getCaller ().getPrincipal ().getCodiceEnte () );
                }
            }
            if ( filtro.getIdentificativiFlusso () != null && filtro.getIdentificativiFlusso ().size () > 0 ) {
                input.getIdentificativiFlusso ().addAll ( filtro.getIdentificativiFlusso () );
            }
            if ( !StringUtils.isEmpty ( filtro.getPsp () ) ) {
                input.setPsp ( filtro.getPsp () );
            }
            if ( filtro.getDataRicezioneDa () != null ) {
                input.setDataRicezioneDa (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataRicezioneDa () ) );

                if ( filtro.getDataRicezioneA () == null ) {
                    filtro.setDataRicezioneA ( Calendar.getInstance ().getTime () );
                }
            }
            if ( filtro.getDataRicezioneA () != null ) {
                input.setDataRicezioneA (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataRicezioneA () ) );
            }
            if ( StringUtils.isNotEmpty ( filtro.getIuv () ) ) {
                input.setIuv ( filtro.getIuv () );
            }
            
            if ( StringUtils.isNotEmpty ( filtro.getIdCodVersamento () ) ) {
                input.setIdCodVersamento ( filtro.getIdCodVersamento () );
            }
            
            input.setStart ( filtro.getStart () );
            input.setLength ( filtro.getLength () );
            input.setDraw ( filtro.getDraw () );
        }

        DtoOutputWsFlussoOrigine output;
        try {
            addCallerInfo ( input );
            input.setIdentificativoIstitutoRicevente ( input.getCaller ().getPrincipal ().getCodiceEnte () );
            output = getPort ().ricercaFlussoOrigine ( input );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( output == null ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        if ( output.getEsito () != null && !"OK".equals ( output.getEsito ().getEsito () ) ) {
//                throw new OperationFailedException ( output.getEsito ().getDescrizione () );
        	 throw new OperationFailedException ( output.getEsito ().getCodiceErrore() );
        }
        return output;
    }

    public DtoOutputWsFlussoOrigine ricercaFlussoOrigineConOrdinamento ( RicercaFlussoFiltroVO filtro, String sortingCol, String sortingDir ) throws OperationFailedException {
        DtoInputWsRicercaFlussoOrigine input = new DtoInputWsRicercaFlussoOrigine ();

        if ( filtro != null ) {
            if ( filtro.getDataElaborazioneDa () != null ) {
                input.setDataElaborazioneDa (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataElaborazioneDa () ) );
            }
            if ( filtro.getDataElaborazioneA () != null ) {
                input.setDataElaborazioneA (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataElaborazioneA () ) );
            }
            if ( StringUtils.isNotEmpty ( filtro.getIdentificativoFlusso () ) ) {
				Pattern pattern = Pattern.compile("^\\/RFS\\/([^\\/]+)\\/.*");
				Matcher matcher = pattern.matcher(filtro.getIdentificativoFlusso());
            	if(matcher.matches()) {
            		String causale = matcher.group(1);
            		input.setIdentificativoIstitutoRicevente(causale);
            	}
            	else {
            		input.setIdentificativoFlusso ( filtro.getIdentificativoFlusso () );
            	}
            }
            if ( StringUtils.isNotEmpty ( filtro.getStatoFlusso () ) ) {
                input.setIdStatoFlusso ( filtro.getStatoFlusso () );
            }
            if ( ( filtro.getDataElaborazioneDa () != null ) || ( filtro.getDataElaborazioneA () != null ) ) {
                if ( input.getCaller () != null && input.getCaller ().getPrincipal ().getCodiceEnte () != null ) {
                    input.setIdentificativoIstitutoRicevente ( input.getCaller ().getPrincipal ().getCodiceEnte () );
                }
            }
            if ( filtro.getIdentificativiFlusso () != null && filtro.getIdentificativiFlusso ().size () > 0 ) {
                input.getIdentificativiFlusso ().addAll ( filtro.getIdentificativiFlusso () );
            }
            if ( !StringUtils.isEmpty ( filtro.getPsp () ) ) {
                input.setPsp ( filtro.getPsp () );
            }
            if ( filtro.getDataRicezioneDa () != null ) {
                input.setDataRicezioneDa (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataRicezioneDa () ) );

                if ( filtro.getDataRicezioneA () == null ) {
                    filtro.setDataRicezioneA ( Calendar.getInstance ().getTime () );
                }
            }
            if ( filtro.getDataRicezioneA () != null ) {
                input.setDataRicezioneA (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataRicezioneA () ) );
            }
            if ( StringUtils.isNotEmpty ( filtro.getIuv () ) ) {
                input.setIuv ( filtro.getIuv () );
            }
            
            if ( StringUtils.isNotEmpty ( filtro.getIdCodVersamento () ) ) {
                input.setIdCodVersamento ( filtro.getIdCodVersamento () );
            }
            
            input.setStart ( filtro.getStart () );
            input.setLength ( filtro.getLength () );
            input.setDraw ( filtro.getDraw () );
            input.setSortingCol(sortingCol);
            input.setSortingDir(sortingDir);
        }

        DtoOutputWsFlussoOrigine output;
        try {
            addCallerInfo ( input );
            input.setIdentificativoIstitutoRicevente ( input.getCaller ().getPrincipal ().getCodiceEnte () );
            output = getPort ().ricercaFlussoOrigine ( input );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( output == null ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        if ( output.getEsito () != null && !"OK".equals ( output.getEsito ().getEsito () ) ) {
//                throw new OperationFailedException ( output.getEsito ().getDescrizione () );
        	 throw new OperationFailedException ( output.getEsito ().getCodiceErrore() );
        }
        return output;
    }

    public FileReportVO ricercaFlussiDaEsportare ( RicercaFlussoFiltroVO filtro, TipoFileReportEnum tipoFileReport ) throws OperationFailedException {
        DtoInputWsRicercaFlussiDaEsportare input = new DtoInputWsRicercaFlussiDaEsportare ();
        input.setTipoFileReport ( tipoFileReport );
        if ( filtro != null ) {
            if ( filtro.getDataElaborazioneDa () != null ) {
                input.setDataElaborazioneDa (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataElaborazioneDa () ) );
            }
            if ( filtro.getDataElaborazioneA () != null ) {
                input.setDataElaborazioneA (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataElaborazioneA () ) );
            }
            if ( StringUtils.isNotEmpty ( filtro.getIdentificativoFlusso () ) ) {
                input.setIdentificativoFlusso ( filtro.getIdentificativoFlusso () );
            }
            if ( StringUtils.isNotEmpty ( filtro.getStatoFlusso () ) ) {
                input.setIdStatoFlusso ( filtro.getStatoFlusso () );
            }
            if ( ( filtro.getDataElaborazioneDa () != null ) || ( filtro.getDataElaborazioneA () != null ) ) {
                if ( input.getCaller () != null && input.getCaller ().getPrincipal ().getCodiceEnte () != null ) {
                    input.setIdentificativoIstitutoRicevente ( input.getCaller ().getPrincipal ().getCodiceEnte () );
                }
            }
            if ( filtro.getIdentificativiFlusso () != null && filtro.getIdentificativiFlusso ().size () > 0 ) {
                input.getIdentificativiFlusso ().addAll ( filtro.getIdentificativiFlusso () );
            }
            if ( !StringUtils.isEmpty ( filtro.getPsp () ) ) {
                input.setPsp ( filtro.getPsp () );
            }
            if ( filtro.getDataRicezioneDa () != null ) {
                input.setDataRicezioneDa (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataRicezioneDa () ) );

                if ( filtro.getDataRicezioneA () == null ) {
                    filtro.setDataRicezioneA ( Calendar.getInstance ().getTime () );
                }
            }
            if ( filtro.getDataRicezioneA () != null ) {
                input.setDataRicezioneA (
                    ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataRicezioneA () ) );
            }
            if ( StringUtils.isNotEmpty ( filtro.getIuv () ) ) {
                input.setIuv ( filtro.getIuv () );
            }

            if ( StringUtils.isNotEmpty ( filtro.getIdCodVersamento () ) ) {
                input.setIdCodVersamento ( filtro.getIdCodVersamento () );
            }
        }

        DtoOutputWsRicercaFlussiDaEsportare output;
        try {
            addCallerInfo ( input );
            input.setIdentificativoIstitutoRicevente ( input.getCaller ().getPrincipal ().getCodiceEnte () );
            output = getPort ().ricercaFlussiDaEsportare ( input );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( output == null ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        if ( output.getEsito () != null ) {
            if ( !"OK".equals ( output.getEsito ().getEsito () ) ) {
                throw new OperationFailedException ( output.getEsito ().getCodiceErrore() );
            }
        }
        FileReportVO fileOutput = new FileReportVO ();
        fileOutput.setContenutoFile ( output.getContenuto () );
        fileOutput.setNomeFile ( output.getNomeFile () );
        return fileOutput;
    }

    public FlussiSintesiVO ricercaFlussiSintesi ( Long idFlussoOrigine ) throws OperationFailedException {

        if ( idFlussoOrigine == null || idFlussoOrigine <= 0L ) {
            throw new InvalidParameterException ();
        }

        DtoInputWsRicercaFlussoSintesi ricercaFlussoSintesi = new DtoInputWsRicercaFlussoSintesi ();
        ricercaFlussoSintesi.setIdFlussoOrigine ( idFlussoOrigine.toString () );

        List<DtoOutputWsFlussoSintesi> output;
        try {
            addCallerInfo ( ricercaFlussoSintesi );
            output = getPort ().ricercaFlussiSintesi ( ricercaFlussoSintesi );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( output == null || output.size () < 1 ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        DtoOutputWsFlussoSintesi outputData = output.get ( 0 );

        if ( outputData.getEsito () != null ) {
            if ( !"OK".equals ( outputData.getEsito ().getEsito () ) ) {
                throw new OperationFailedException ( outputData.getEsito ().getDescrizione () );
            }
        }

        if ( outputData.getFlussoOrigine () == null ) {
            throw new OperationFailedException ( FLUSSO_NOT_FOUND );
        }

        FlussiSintesiVO outputVO = new FlussiSintesiVO ();

        try {
            outputVO.setFlussoOrigine ( FlussoMapper.parseFlussoOrigineVO ( outputData.getFlussoOrigine () ) );
        } catch ( ParseException e ) {
            throw new OperationFailedException ( MAPPING_ERROR, e );
        }

        outputVO.setFlussiSintesi ( new ArrayList<> () );

        for ( DtoFlussoSintesi dto: outputData.getFlussiSintesi () ) {
            outputVO.getFlussiSintesi ().add ( FlussoMapper.parseFlussoSintesiVO ( dto ) );
        }

        return outputVO;
    }

    public List<ElaborazioneVO> ricercaElaborazionePrecedente ( RicercaElaborazionePrecedenteFiltroVO filtro ) throws OperationFailedException {
        List<DtoOutputRicercaElaborazionePrecedente> lista = new ArrayList<> ();
        List<ElaborazioneVO> listaElaborazioneVO = new ArrayList<> ();
        DtoInputWsRicercaElaborazionePrecedente input = new DtoInputWsRicercaElaborazionePrecedente ();
        addCallerInfo ( input );
        if ( filtro != null ) {
            if ( filtro.getDataFine () != null ) {
                input.setDataFine ( ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataFine () ) );
            }
            if ( filtro.getDataInizio () != null ) {
                input.setDataInizio ( ParentMapper.parseXMLGregorianCalendarFromDate ( filtro.getDataInizio () ) );
            }
            input.setCodiceFiscaleEnte ( filtro.getCodiceFiscaleEnte () );
            input.setStatoElaborazione ( filtro.getStatoElaborazione () );
            input.setUtenteElaborazione ( input.getCaller ().getPrincipal ().getCodiceFiscale () );

        }
        try {
            lista = getPort ().ricercaElaborazionePrecedente ( input );
            if ( !lista.isEmpty () ) {
                for ( DtoOutputRicercaElaborazionePrecedente dtoOutput: lista ) {
                    ElaborazioneVO elaborazioneVo = new ElaborazioneVO ();
                    elaborazioneVo = InserisciElaborazioneMapper.parseElaborazioneVO ( dtoOutput );
                    listaElaborazioneVO.add ( elaborazioneVo );
                }

            }

        } catch ( UnrecoverableException_Exception e ) {
            e.printStackTrace ();
        } catch ( Exception_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        } catch ( EpaymodricException_Exception e ) {
            e.printStackTrace ();
        }

        return listaElaborazioneVO;
    }

    public void inserisciElaborazione ( InserisciElaborazioneFiltroVO input )
                    throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        DtoInputWsElaborazione in = new DtoInputWsElaborazione ();
        if ( input != null ) {

            try {
                addCallerInfo ( in );
                in.setCodiceFiscaleEnte ( input.getCodiceFiscaleEnte () );
                in.setUtenteElaborazione ( input.getUtenteElaborazione () );
                in.setIdElaborazionePrecedente ( input.getIdElaborazionePrecedente ().toString () );
                if ( input.getFlussiDaElaborare ().size () > 0 )
                    in.getFlussiDaElaborare ().addAll ( input.getFlussiDaElaborare () );
                in.setStatoDaImpostare ( input.getStatoDaImpostare () );
                in.setRiesecuzione ( true );
                //Chiamata al servizio inserisciElaborazione lato back end
                getPort ().inserisciElaborazione ( in );

            } catch ( Exception e ) {
                throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
            }

        }
    }

    public DtoOutputWsEsito inserisciFlussiDaRielaborare ( DtoInputWsInserisciFlussiDaRielaborare input )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        addCallerInfo ( input );
        return getPort ().inserisciFlussiDaRielaborare ( input );
    }

    public DtoOutputWsEsito aggiornaProvvisori ( DtoInputWsAggiornaProvvisori input )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        addCallerInfo ( input );
        return getPort ().aggiornaProvvisori ( input );
    }

    public DtoOutputWsProvvisori ricercaProvvisori ( DtoInputWsRicercaProvvisori input )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        addCallerInfo ( input );
        return getPort ().ricercaProvvisori ( input );
    }

    public DtoOutputWsEsito cancellaProvvisori ( DtoInputWsCancellaProvvisori input )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        addCallerInfo ( input );
        return getPort ().cancellaProvvisori ( input );
    }
    
    public List<RisultatiRicercaReportVO> ricercaReport() throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception{
    	
    	DtoInputWsRicercaPrenotazioneReport input = new DtoInputWsRicercaPrenotazioneReport();
    	
    	addCallerInfo(input);
    	return mappingRisultati(getPort().prenotazioneReport(input));
    	
    }
    
    public List<RisultatiRicercaReportVO> mappingRisultati ( List<DtoOutputWsRicercaPrenotazioneReport> risultatiRicerca ) throws OperationFailedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd-MM-yyyy HH:mm:ss" );
        List<RisultatiRicercaReportVO> risultatiMappati = new LinkedList<> ();

        for ( DtoOutputWsRicercaPrenotazioneReport singleReport: risultatiRicerca ) {

            RisultatiRicercaReportVO reportMappato = new RisultatiRicercaReportVO ();

            reportMappato.setId ( singleReport.getId () );
            reportMappato.setCodiceStato ( singleReport.getStatoReport ().getCodice () );
            reportMappato.setNominativoExport ( singleReport.getNominativoExport () );
            if(null != singleReport.getDataOraPrenotazioneReport())
            {
            	reportMappato.setDataOraPrenotazione (
                        dateFormat.format ( ParentMapper.parseDateFromXmlGregorianCalendar ( singleReport.getDataOraPrenotazioneReport()) ) );
            }
            
            
            if(null != singleReport.getFileReport () && null != singleReport.getFileReport ().getId () ) {
                reportMappato.setIdFile ( singleReport.getFileReport ().getId ().toString () );
            }
            if ( singleReport.getTipoReport () != null ) {
                reportMappato.setTipoReport ( singleReport.getTipoReport ().getDescrizione () );
            }

            if ( singleReport.getStatoReport () != null ) {
                reportMappato.setStatoReport ( singleReport.getStatoReport ().getDescrizione () );
            }
            if ( singleReport.getFileReport () != null ) {
                reportMappato.setNomeFile ( singleReport.getFileReport ().getNomeFile () );
//                if ( null != singleReport.getFileReport ().getDataInserimento () ) {
//                    reportMappato.setDataOraPrenotazione (
//                        dateFormat.format ( ParentMapper.parseDateFromXmlGregorianCalendar ( singleReport.getFileReport ().getDataInserimento () ) ) );
//                } else {
//                    reportMappato.setDataOraPrenotazione ( "-" );
//                }
                if ( null != singleReport.getFileReport ().getDataModifica () ) {
                    reportMappato.setDataOraUltimaModifica (
                        dateFormat.format ( ParentMapper.parseDateFromXmlGregorianCalendar ( singleReport.getFileReport ().getDataModifica () ) ) );
                } else {
                    reportMappato.setDataOraUltimaModifica ( "-" );
                }
                if ( null != singleReport.getFileReport ().getDataInizioElaborazione () ) {
                    reportMappato.setDataOraInizioElaborazione (
                        dateFormat.format ( ParentMapper.parseDateFromXmlGregorianCalendar ( singleReport.getFileReport ().getDataInizioElaborazione () ) ) );
                } else {
                    reportMappato.setDataOraInizioElaborazione ( "-" );
                }
            }
            risultatiMappati.add ( reportMappato );
        }
        return risultatiMappati;
    }
    
    public DtoOutputWsAggiornaStatoReport aggiornaStato (Long idExport) throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception{
    	DtoInputWsAggiornaStatoReport input = new DtoInputWsAggiornaStatoReport();
    	addCallerInfo ( input );
    	input.setIdExport(idExport);
    	return getPort().aggiornaStatoReport(input);
    }
    
    
    public DtoOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice ( DtoInputWsRicercaCodiciVersamento input )
                    throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        return getPort ().ricercaCodiciVersamentoPerCodice ( input );
    }
    
    public DtoOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport ( DtoInputWsInserisciPrenotazioneReport input )
    		throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
    	addCallerInfo ( input );
    	
    	return getPort ().inserisciPrenotazioneReport ( input );
    }
    
    public DtoOutputWsRicercaLimiteEsportazione ricercaLimiteEsportazione() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
    	return getPort().ricercaLimiteEsportazione();
    }
    
    public DtoOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
    	return getPort().ricercaLimiteElaborazioneReport();
    }

	public List<GenericVO> ricercaCodiciVersamento() throws OperationFailedException {
		DtoInputWsRicercaCodiciVersamento input = new DtoInputWsRicercaCodiciVersamento ();

        List<DtoOutputWsCodiciVersamento> opzioni;
        try {
            addCallerInfo ( input );
            opzioni = getPort ().ricercaCodiciVersamento ( input );
        } catch ( it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception
                        | it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception
                        | EpaymodricException_Exception e ) {
            throw new OperationFailedException ( REMOTE_ERROR + e.getMessage (), e );
        }

        if ( opzioni == null || opzioni.size () < 1 ) {
            throw new OperationFailedException ( NO_RESPONSE );
        }

        if ( opzioni.get ( 0 ).getEsito () != null ) {
            if ( !"OK".equals ( opzioni.get ( 0 ).getEsito ().getEsito () ) ) {
                throw new OperationFailedException ( opzioni.get ( 0 ).getEsito ().getDescrizione () );
            }
        }

        List<GenericVO> o = new ArrayList<> ();
        for ( DtoCodiceVersamento opzione: opzioni.get ( 0 ).getCodiciVersamento() ) {
            o.add ( new GenericVO (  opzione.getId () , opzione.getCodice() ,
                opzione.getCodice () + " - " + opzione.getDenominazione() ) );
        }
        return o;
	}
    
}
