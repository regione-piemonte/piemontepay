/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaybeapi.business.AvvisoPagamentoReportService;
import it.csi.epay.epaybeapi.business.StampaAvvisoPagamentoService;
import it.csi.epay.epaybeapi.dto.report.DestinatarioAvvisoPagamentoDTO;
import it.csi.epay.epaybeapi.dto.report.EnteCreditoreAvvisoPagamentoDTO;
import it.csi.epay.epaybeapi.dto.report.InfoPagamentoAvvisoPagamentoDTO;
import it.csi.epay.epaybeapi.dto.request.RichiestaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.request.StampaAvvisoPagamentoRequest;
import it.csi.epay.epaybeapi.dto.response.StampaAvvisoPagamentoResponse;
import it.csi.epay.epaybeapi.integration.dto.EpayTPdfReportDTO;
import it.csi.epay.epaybeapi.util.AvvisoPagamentoPdfGenerator;
import it.csi.epay.epaybeapi.util.Constants;
import it.csi.epay.epaybeapi.util.Messages;
import it.csi.epay.epayservices.interfaces.ejb.DatiAvvisoPagamentoFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.Rt;
import net.sf.jasperreports.engine.JRException;


@Service
public class StampaAvvisoPagamentoServiceImpl implements StampaAvvisoPagamentoService {
	
    private static final String CLASSNAME = StampaAvvisoPagamentoServiceImpl.class.getSimpleName ();

    private static Logger logger = LoggerFactory.getLogger ( CLASSNAME );

    @Autowired
    private AvvisoPagamentoReportService avvisoPagamentoReportService;

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @Autowired
    private DatiAvvisoPagamentoFacade datiAvvisoPagamentoFacade;

    @Override
    public StampaAvvisoPagamentoResponse getAvvisoPagamento ( RichiestaAvvisoPagamentoRequest request ) {

        StampaAvvisoPagamentoResponse response = null;

        if ( StringUtils.isBlank ( request.getIuv () ) ) {
            response = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.IUV_NON_VALORIZZATO.getMessage () );
            return response;
        }

        // EPAY_175: la ricerca ed i controlli sul pagamento sono identici a quella fatta in epayweb,
        // nel momento in cui si stampa l'avviso di un pagamento di cui si ha lo IUV, in quanto, da analisi e' richiesto che i due siano identici
        Pagamento pagamento;

        try {
            pagamento = pagamentoFacade.ricerca ( request.getIuv () );
            if ( pagamento == null ) {
                response = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.IUV_INESISTENTE.getMessage () );
                return response;
            }

            pagamento = pagamentoFacade.ricercaSoloAttivi ( request.getIuv () );
            if ( pagamento == null ) {
                response = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.PAGAMENTO_NON_EFFETTUABILE.getMessage () );
                return response;
            }
            Rt rt = null;
            try {
                rt = pagamentoFacade.ricercaRtByIuv ( request.getIuv () );
                if ( null != rt && rt.getIdRR () != null ) {
                    response = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.IUV_REVOCATO.getMessage () );
                    return response;
                }
            } catch ( NoDataException noDataExceptionRt ) {
                logger.debug ( "RT non trovata" );
            }

        } catch ( NoDataException e ) {
            response = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.IUV_INESISTENTE.getMessage () );
            return response;
        }

        if ( pagamento.getImporto ().doubleValue () > Constants.MAX_IMPORTO ) {
            logger.error ( "Importo dovuto superiore al max consentito {}", Constants.MAX_IMPORTO );
            response
                = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM,
                    String.format ( Messages.IMPORTO_SUPERIORE_AL_MAX_CONSENTITO.getMessage (), Constants.MAX_IMPORTO ) );
            return response;
        }

        if ( pagamento.getTipoPagamento () == null || pagamento.getTipoPagamento ().getIdTipoPagamento () == null ) {
            logger.error ( "Tipo pagamento non identificato" );
            response
                = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.TIPO_PAGAMENTO_NON_IDENTIFICATO.getMessage () );
            return response;
        }

        EnteCreditoreAvvisoPagamentoDTO enteCreditore = buildEnteCreditoreAvvisoPagamentoDTO ( pagamento.getEnte (),
            pagamento.getTipoPagamento ().getFlagPresenzaBollettinoPostale (), pagamento.getTipoPagamento ().getIdTipoPagamento () );

        DestinatarioAvvisoPagamentoDTO destinatario = buildDestinatarioAvvisoPagamentoDTO ( pagamento.getPagatore () , pagamento.getRiferimenti () );

        InfoPagamentoAvvisoPagamentoDTO infoPagamento
            = buildInfoPagamentoAvvisoPagamentoDTO ( pagamento.getCausale (), pagamento.getDataScadenza (), pagamento.getImporto ().doubleValue (),
                pagamento.getIuv (), enteCreditore.getNumeroCCPostale (), enteCreditore.getAutorizzazione (), request.getCodAvviso () );

        EpayTPdfReportDTO epayTPdfReportDTO = avvisoPagamentoReportService.getJasperReport ();

        AvvisoPagamentoPdfGenerator avvisoPagamentoPdfGenerator = new AvvisoPagamentoPdfGenerator ();

        try {
            response = new StampaAvvisoPagamentoResponse ();
            response.setCodEsito ( Constants.RETURN_OK );
            response.setAvvisoPagamentoData ( avvisoPagamentoPdfGenerator.creaPdf ( enteCreditore, destinatario,
                infoPagamento, epayTPdfReportDTO ) );
        } catch ( IOException | JRException e ) {
            logger.error ( "Errore nella compilazione del report PDF ", e );
            response
                = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, "Errore nella compilazione del report PDF: " + e.getMessage () );
        }

        return response;
    }

    @Override
    public StampaAvvisoPagamentoResponse getAvvisoPagamentoSportello ( StampaAvvisoPagamentoRequest request ) {

        StampaAvvisoPagamentoResponse response = null;

        if ( request.getImportoTotale () == null ) {
            response
                = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.IMPORTO_NON_INDICATO.getMessage () );
            return response;
        }

        if ( request.getImportoTotale ().doubleValue () > Constants.MAX_IMPORTO ) {
            logger.error ( "Importo dovuto superiore al max consentito {}", Constants.MAX_IMPORTO );
            response
                = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM,
                    String.format ( Messages.IMPORTO_SUPERIORE_AL_MAX_CONSENTITO.getMessage (), Constants.MAX_IMPORTO ) );
            return response;
        }

        if ( request.getIdTipoPagamento () == null ) {
            logger.error ( "Tipo pagamento non identificato" );
            response
                = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, Messages.TIPO_PAGAMENTO_NON_IDENTIFICATO.getMessage () );
            return response;
        }

        EnteCreditoreAvvisoPagamentoDTO enteCreditore
            = buildEnteCreditoreAvvisoPagamentoDTO ( request.getEnte (), request.getBollettinoPostale (), request.getIdTipoPagamento () );

        DestinatarioAvvisoPagamentoDTO destinatario = buildDestinatarioAvvisoPagamentoDTO ( request.getSoggettoDebitore () , null );

        InfoPagamentoAvvisoPagamentoDTO infoPagamento
            = buildInfoPagamentoAvvisoPagamentoDTO ( request.getCausale (), request.getScadenza (), request.getImportoTotale ().doubleValue (),
                request.getIuv (), enteCreditore.getNumeroCCPostale (), enteCreditore.getAutorizzazione (), request.getCodAvviso () );

        EpayTPdfReportDTO epayTPdfReportDTO = avvisoPagamentoReportService.getJasperReport ();

        AvvisoPagamentoPdfGenerator avvisoPagamentoPdfGenerator = new AvvisoPagamentoPdfGenerator ();

        try {
            response = new StampaAvvisoPagamentoResponse ();
            response.setCodEsito ( Constants.RETURN_OK );
            response.setAvvisoPagamentoData ( avvisoPagamentoPdfGenerator.creaPdf ( enteCreditore, destinatario,
                infoPagamento, epayTPdfReportDTO ) );
        } catch ( IOException | JRException e ) {
            logger.error ( "Errore nella compilazione del report PDF ", e );
            response
                = new StampaAvvisoPagamentoResponse ( Constants.RETURN_SERVER_PROBLEM, "Errore nella compilazione del report PDF: " + e.getMessage () );
        }

        return response;
    }

    private EnteCreditoreAvvisoPagamentoDTO buildEnteCreditoreAvvisoPagamentoDTO ( Ente ente, Boolean bollettinoPostale, Long idTipoPagamento ) {
        EnteCreditoreAvvisoPagamentoDTO enteCreditore = new EnteCreditoreAvvisoPagamentoDTO ();

        if ( ente != null ) {
            enteCreditore.setCbill ( ente.getCodiceInterbancario () );
            enteCreditore.setEcLogo ( ente.getLogo () );
            enteCreditore.setEnteCreditore ( ente.getNome () );
            enteCreditore.setCfEnte ( ente.getCodiceFiscale () );

            if ( Boolean.TRUE.equals ( bollettinoPostale ) ) {

                try {
                    DatiAvvisoPagamento datiAvvisoPagamento = datiAvvisoPagamentoFacade.ricerca ( idTipoPagamento );

                    if ( datiAvvisoPagamento != null ) {
                        enteCreditore.setInfoEnte ( createInfoEnte ( datiAvvisoPagamento ) );
                        enteCreditore.setAutorizzazione ( datiAvvisoPagamento.getAutorizzazioneDaPosteIt () );
                        enteCreditore.setSettoreEnte ( datiAvvisoPagamento.getSettore () );
                        enteCreditore.setNumeroCCPostale ( datiAvvisoPagamento.getNumeroCCPostale () );
                        enteCreditore.setIntestatarioCCPostale ( datiAvvisoPagamento.getIntestatarioCCPostale () );
                    }
                } catch ( IllegalArgumentException | NoDataException e ) {
                    logger.error ( "Errore nella ricerca dei dati per l'avviso del pagamento ", e );
                }
            }
        }
        return enteCreditore;
    }

    private DestinatarioAvvisoPagamentoDTO buildDestinatarioAvvisoPagamentoDTO ( Anagrafica anagrafica, List<PagamentoRiferimenti> riferimenti ) {

        if ( anagrafica == null ) {
            return null;
        }

        DestinatarioAvvisoPagamentoDTO destinatario = new DestinatarioAvvisoPagamentoDTO ();

        if ( StringUtils.isNotBlank ( anagrafica.getRagioneSociale () ) ) {
            destinatario.setAnagraficaDestinatario ( "<b>" + anagrafica.getRagioneSociale () + "</b>" );
            destinatario.setDatiDestinatario( anagrafica.getRagioneSociale () );
        } else {
            destinatario.setAnagraficaDestinatario ( "<b>" + anagrafica.getNome () + " " + anagrafica.getCognome () + "</b>" );
            destinatario.setDatiDestinatario( anagrafica.getNome () + " " + anagrafica.getCognome () );
        }

        destinatario.setIdUnivocoDestinatario ( anagrafica.getCodiceFiscale () );
        destinatario.setIndirizzoDestinatario ( createIndirizzoDebitore ( anagrafica ) );

        if( anagrafica.getFlagPersonaFisica() &&
        	Constants.ANONIMO.equalsIgnoreCase ( anagrafica.getNome () ) &&  
        	Constants.ANONIMO.equalsIgnoreCase ( anagrafica.getCognome () ) && 
        	Constants.ANONIMO.equalsIgnoreCase ( anagrafica.getCodiceFiscale () ) &&
        	( riferimenti != null ) && 
        	riferimenti.size () > 0) {
        	
        	destinatario.setAnagraficaDestinatario ( StringUtils.EMPTY );
        	destinatario.setIndirizzoDestinatario ( StringUtils.EMPTY );
        	destinatario.setDatiDestinatario(  StringUtils.EMPTY );
        	destinatario.setIdUnivocoDestinatario ( "-" );
        	        	
        	Collections.sort(riferimenti, new Comparator<PagamentoRiferimenti>() {
        		  @Override
        		  public int compare(PagamentoRiferimenti r1, PagamentoRiferimenti r2) {
        		    return r1.getProgressivo().compareTo(r2.getProgressivo());
        		  }
        		});
        	
        	
        	for ( int i = 0 ; i < riferimenti.size () ; i++) {
        		String rif = riferimenti.get(i).getNome() + " : " + riferimenti.get(i).getValore(); 
        		if ( i  == 0) {
        			destinatario.setAnagraficaDestinatario( rif );
        			destinatario.setDatiDestinatario( riferimenti.get(i).getValore() );
        		}else {
        			if ( i  == 1) {
        				destinatario.setDatiDestinatario( destinatario.getDatiDestinatario() + " - " + riferimenti.get(i).getValore() );
        			}
        			
        			if ( StringUtils.isNotEmpty ( destinatario.getIndirizzoDestinatario () ) ) {
        				destinatario.setIndirizzoDestinatario( destinatario.getIndirizzoDestinatario () +  "<br/>" );
        			}
        			destinatario.setIndirizzoDestinatario( destinatario.getIndirizzoDestinatario() + rif );
        		}
        	}
        }
        
        return destinatario;
    }

    private InfoPagamentoAvvisoPagamentoDTO buildInfoPagamentoAvvisoPagamentoDTO ( String causale, Date dataScadenza, Double importo, String iuv,
        String numeroContoCorrentePostale,
        String autorizzazione, String codAvviso ) {
        InfoPagamentoAvvisoPagamentoDTO infoPagamento = new InfoPagamentoAvvisoPagamentoDTO ();

        infoPagamento.setOggettoDelPagamento ( causale );
        infoPagamento.setData ( dataScadenza );
        infoPagamento.setImporto ( importo );
        infoPagamento.setCodiceAvviso ( codAvviso.replaceAll ( "(.{4})", "$1 " ) );

        // TODO: Modificare in base alle rate
        infoPagamento.setPagamentoReteale ( null );
        infoPagamento.setRate ( Constants.UNA_UNICA_RATA );
        infoPagamento.setAllaRata ( Constants.UNICA_RATA );

        StringBuilder sb = new StringBuilder ();
        // TODO: in futuro, quando si avra' l'opzione relativa al modello 1 o 2, si
        // settera'. per ora e' sempre true
        if ( true ) {
            infoPagamento.setModalitaPagamentoEnteCreditore ( Constants.DEL_TUO_ENTE_CREDITORE );
            infoPagamento.setUrlPagamentoEnteCreditore ( Constants.URL_ENTE_CREDITORE );
        }
        if ( StringUtils.isNotBlank ( numeroContoCorrentePostale )
            && StringUtils.isNotBlank ( autorizzazione ) )
            sb.append ( Constants.DI_POSTE_ITALIANE );
        sb.append ( Constants.DELLA_TUA_BANCA );
        infoPagamento.setModalitaPagamento ( sb.toString () );
        infoPagamento.setIuv ( iuv );

        return infoPagamento;
    }

    private String createIndirizzoDebitore ( Anagrafica soggettoDebitore ) {
        StringBuilder indirizzo = new StringBuilder ();
        if ( StringUtils.isNotBlank ( soggettoDebitore.getIndirizzo () ) ) {
            indirizzo.append ( soggettoDebitore.getIndirizzo () );
            if ( StringUtils.isNotBlank ( soggettoDebitore.getCivico () ) ) {
                indirizzo.append ( ", " + soggettoDebitore.getCivico () + "<br/>" );
            } else {
                indirizzo.append ( "<br/>" );
            }
            if ( StringUtils.isNotBlank ( soggettoDebitore.getCap () ) ) {
                indirizzo.append ( soggettoDebitore.getCap () + ", " );
            }
            if ( StringUtils.isNotBlank ( soggettoDebitore.getLocalita () ) ) {
                indirizzo.append ( soggettoDebitore.getLocalita () );
                if ( StringUtils.isNotBlank ( soggettoDebitore.getProvincia () ) ) {
                    indirizzo.append ( " (" + soggettoDebitore.getProvincia () + ") " );
                }
            }
            if ( StringUtils.isNotBlank ( soggettoDebitore.getNazione () ) ) {
                indirizzo.append ( soggettoDebitore.getNazione () );
            }
        }
        return indirizzo.toString ();
    }

    private String createInfoEnte ( DatiAvvisoPagamento infoEnte ) {
        StringBuilder info = new StringBuilder ();
        if ( StringUtils.isNotBlank ( infoEnte.getIndirizzo () ) ) {
            info.append ( infoEnte.getIndirizzo () );
            if ( StringUtils.isNotBlank ( infoEnte.getCap () ) ) {
                info.append ( ", " + infoEnte.getCap () );
            }
            if ( StringUtils.isNotBlank ( infoEnte.getLocalita () ) ) {
                info.append ( ( " - " ) + infoEnte.getLocalita () );
                if ( StringUtils.isNotBlank ( infoEnte.getSiglaProvincia () ) ) {
                    info.append ( " (" + infoEnte.getSiglaProvincia () + ") " );
                }
            }
            if ( StringUtils.isNotBlank ( infoEnte.getEmail () ) ) {
                info.append ( "<br/>" + infoEnte.getEmail () );
            }
            if ( StringUtils.isNotBlank ( infoEnte.getInfoEnte () ) ) {
                info.append ( "<br/>" + infoEnte.getInfoEnte () );
            }
        }
        return info.toString ();
    }

}
