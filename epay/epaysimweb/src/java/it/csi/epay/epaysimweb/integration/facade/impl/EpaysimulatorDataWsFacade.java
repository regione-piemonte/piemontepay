/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.integration.facade.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Service;

import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.CallerInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DatatypeConfigurationException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoOutputWsProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.EpaysimulatorDataws;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.EpaysimulatorDatawsService;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.EpaysimulatorException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.Exception_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoDettaglioPagopaDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOriginePagopaDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoSintesiPagopaDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.GiornaleDiCassaInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.GiornaleDiCassaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.InserisciProvvisorioInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.InserisciProvvisorioOutputResponse;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.ParentInput;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.PrincipalInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoErroreInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoOutputResponse;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UnrecoverableException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoOutputDTO;
import it.csi.epay.epaysimweb.security.UserDetails;
import it.csi.epay.epaysimweb.util.SecurityUtils;


@Service
public class EpaysimulatorDataWsFacade extends ParentFacade implements EpaysimulatorDataws {

    private static final String EPAYSIMULATORDATA = "epaysimulatordata";

    private EpaysimulatorDatawsService service;

    private EpaysimulatorDataws port;

    public EpaysimulatorDataWsFacade () {
        super ();
        service = null;
        port = null;
    }

    private EpaysimulatorDataws getPort () {
        if ( service == null ) {
            try {
                String endPointUrl = java.util.ResourceBundle.getBundle ( "config" ).getString ( "service.epaysim.endpoint" );

                try {
                    service = new EpaysimulatorDatawsService ( new URL ( endPointUrl + "?WSDL" ) );
                } catch ( MalformedURLException e ) {
                    throw new RuntimeException ( e );
                }
                port = service.getEpaysimulatorDatawsPort ();

                BindingProvider bp = (BindingProvider) port;

                Map<String, Object> context = bp.getRequestContext ();
                context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointUrl );

                return port;

            } catch ( Exception e ) {
                service = null;
                port = null;
                throw new RuntimeException ( "Error contacting remote service", e );
            }
        } else {
            return port;
        }
    }

    private void addCallerInfo ( ParentInput input ) {
        UserDetails user = SecurityUtils.getUser ();

        CallerInputDTO caller = new CallerInputDTO ();
        caller.setCodiceApplicativo ( EPAYSIMULATORDATA );

        if ( user != null ) {
            PrincipalInputDTO principal = new PrincipalInputDTO ();
            principal.setCodiceEnte ( user.getEnte ().getCodiceFiscale () );
            principal.setCodiceFiscale ( user.getUtente ().getCodiceFiscale () );
            caller.setPrincipal ( principal );
        }

        input.setCaller ( caller );
    }

    @Override
    public GiornaleDiCassaOutputDTO ricercaGiornaleDiCassa ( GiornaleDiCassaInputDTO param ) throws EpaysimulatorException_Exception {
        addCallerInfo ( param );
        return getPort ().ricercaGiornaleDiCassa ( param );
    }

    @Override
    public InserisciProvvisorioOutputResponse inserisciProvvisorio ( InserisciProvvisorioInputDTO arg0 )
                    throws EpaysimulatorException_Exception {
        addCallerInfo ( arg0 );
        return getPort ().inserisciProvvisorio ( arg0 );
    }

    @Override
    public UpdateEsitoStatoElaborazioneFlussoOutputDTO updateEsitoStatoElaborazioneFlusso ( UpdateEsitoStatoElaborazioneFlussoInputDTO arg0 )
                    throws EpaysimulatorException_Exception {
        addCallerInfo ( arg0 );
        return getPort ().updateEsitoStatoElaborazioneFlusso ( arg0 );
    }

    @Override
    public RicercaFlussoOutputResponse ricercaFlusso ( RicercaFlussoInputDTO param )
                    throws EpaysimulatorException_Exception, DatatypeConfigurationException_Exception {
        addCallerInfo ( param );
        return getPort ().ricercaFlusso ( param );
    }

    @Override
    public DtoOutputWsProvvisori ricercaProvvisori ( DtoInputWsRicercaProvvisori param )
                    throws EpaysimulatorException_Exception {
        addCallerInfo ( param );
        return getPort ().ricercaProvvisori ( param );
    }

    @Override
    public FlussoSintesiPagopaOutputDTO ricercaFlussoSintesiPagoPa ( FlussoSintesiPagopaDTO param )
                    throws EpaysimulatorException_Exception {
        addCallerInfo ( param );
        return getPort ().ricercaFlussoSintesiPagoPa ( param );
    }

    @Override
    public FlussoDettaglioPagopaOutputDTO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioPagopaDTO param )
                    throws EpaysimulatorException_Exception {
        addCallerInfo ( param );
        return getPort ().ricercaFlussoDettaglioPagoPa ( param );
    }

    @Override
    public List<FlussoOrigineErrorePagopaOutputDTO> ricercaFlussiInErrore ( RicercaFlussoErroreInputDTO param )
                    throws EpaysimulatorException_Exception {
        addCallerInfo ( param );
        return getPort ().ricercaFlussiInErrore ( param );
    }

    @Override
    public FlussoOriginePagopaOutputDTO ricercaFlussoOriginePagoPa ( FlussoOriginePagopaDTO param )
                    throws EpaysimulatorException_Exception, UnrecoverableException_Exception, Exception_Exception {
        addCallerInfo ( param );
        return getPort ().ricercaFlussoOriginePagoPa ( param );
    }

    @Override
    public FlussoDettaglioPagopaOutputDTO ricercaDettaglioPagoPa ( FlussoDettaglioPagopaDTO param ) throws EpaysimulatorException_Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
