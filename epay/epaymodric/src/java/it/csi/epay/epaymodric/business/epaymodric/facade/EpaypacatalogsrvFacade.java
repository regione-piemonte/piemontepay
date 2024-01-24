/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.facade;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.CallerInputDto;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.Epaypacatalogsrv;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.EpaypacatalogsrvService;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.GetProfilazioneUtenteInput;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.GetProfilazioneUtenteOutput;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.PrincipalInputDto;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.UnrecoverableException_Exception;

@Service
public class EpaypacatalogsrvFacade {

    private EpaypacatalogsrvService service;

    private Epaypacatalogsrv port;

    public EpaypacatalogsrvFacade() {
        super();
        service = null;
        port = null;
    }

    private Epaypacatalogsrv getPort() {
        if (port == null) {
            try {
                String endPointUrl = java.util.ResourceBundle.getBundle("config")
                                .getString ( "service.epaypacatalogsrv.endpoint" );

                try {
                    service = new EpaypacatalogsrvService ( new URL ( endPointUrl ) );
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                port = service.getEpaypacatalogsrvPort();

                BindingProvider bp = (BindingProvider) port;

                Map<String, Object> context = bp.getRequestContext();
                context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointUrl);

                return port;

            } catch (Exception e) {
                service = null;
                port = null;
                throw new RuntimeException("Error contacting remote service", e);
            }
        } else {
            return port;
        }
    }

    //	private void addCallerInfo(ParentInput input) {
    //        PrincipalVO currentPrincipal = RequestContextUtils.getPrincipalIfPresent ();
    //
    //		CallerInputDto caller = new CallerInputDto();
    //        caller.setCodiceApplicativo ( Costanti.CODICE_APPLICAZIONE );
    //
    //        if ( currentPrincipal != null && currentPrincipal.getUtente () != null ) {
    //			PrincipalInputDto principal = new PrincipalInputDto();
    //            principal.setCodiceEnte ( currentPrincipal.getEnte ().getCodiceFiscale () );
    //            principal.setCodiceFiscale ( currentPrincipal.getUtente ().getCodiceFiscale () );
    //            principal.setCodiceRuolo ( currentPrincipal.getRuolo ().getCodice () );
    //			caller.setPrincipal(principal);
    //		}
    //
    //        if ( RequestContextHolder.getRequestAttributes () != null ) {
    //            HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () ).getRequest ();
    //            if ( request != null ) {
    //                caller.setIp ( request.getRemoteAddr () );
    //            }
    //        }
    //
    //		input.setCaller(caller);
    //	}

    public GetProfilazioneUtenteOutput getProfilazioneUtente ( CallerInputDto caller )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {

        GetProfilazioneUtenteInput getInfoUtenteInput = new GetProfilazioneUtenteInput ();

        caller.setCodiceApplicativo ( Costanti.CODICE_APPLICAZIONE );
        getInfoUtenteInput.setCaller(caller);

        if ( RequestContextHolder.getRequestAttributes () != null ) {
            HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () ).getRequest ();
            if ( request != null ) {
                caller.setIp ( request.getRemoteAddr () );
            }
        }

        return getPort ().getProfilazioneUtente ( getInfoUtenteInput );
    }
}
