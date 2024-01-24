/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.facade;

import it.csi.epay.epaywsosrv.util.LogAndWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;


public class MarcHandler implements SOAPHandler<SOAPMessageContext> {
	static private final String CLASSNAME = MarcHandler.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".handler");

    static private final String FORCE_OUTBOUND_ENCODING = "UTF-8";

    protected static boolean doTraceMessage () {
        return log.isDebugEnabled ();
    }

    @Override
    public boolean handleMessage ( SOAPMessageContext context ) {
        try {
            return _handleMessage ( context );
        } catch ( Throwable t ) {
            log.error ( "ERRORE NELLA GESTIONE DEL MESSAGGIO SOAP. " +
                "IL MESSAGGIO VERRA' INVIATO/RICEVUTO COMUNQUE SENZA ESSERE ELABORATO", t );
            return true;
        }
    }

    private boolean _handleMessage ( SOAPMessageContext context ) throws SOAPException {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "handleMessage", false);
		lw.start();

        Boolean outbound = (Boolean) context.getOrDefault ( MessageContext.MESSAGE_OUTBOUND_PROPERTY, null );

        if ( doTraceMessage () ) {

            Object wsdlInterface = context.getOrDefault ( MessageContext.WSDL_INTERFACE, null );
            Object wsdlOperation = context.getOrDefault ( MessageContext.WSDL_OPERATION, null );
            Object wsdlService = context.getOrDefault ( MessageContext.WSDL_SERVICE, null );
            Object pathInfo = context.getOrDefault ( MessageContext.PATH_INFO, null );

            if ( outbound == null ) {
                if ( doTraceMessage () ) {
                    log.info ( "handling SOAP message ???" );
                }
            } else if ( !outbound.booleanValue () ) {
                if ( doTraceMessage () ) {
                    log.info ( "handling SOAP message INbound" );
                }
            } else {
                if ( doTraceMessage () ) {
                    log.info ( "handling SOAP message OUTbound" );
                }
            }
            log.info ( "\t{FIXED:PATH_INFO} = {" + pathInfo + "}" );
            log.info ( "\t{FIXED:WSDL_SERVICE} = {" + wsdlService + "}" );
            log.info ( "\t{FIXED:WSDL_OPERATION} = {" + wsdlOperation + "}" );
            log.info ( "\t{FIXED:WSDL_INTERFACE} = {" + wsdlInterface + "}" );
            log.info ( "\t{FIXED:OUTBOUND} = {" + context.get ( MessageContext.MESSAGE_OUTBOUND_PROPERTY ) + "}" );
            log.info ( "\t{FIXED:org.apache.cxf.message.Message.ENCODING} = {" + context.get ( "org.apache.cxf.message.Message.ENCODING" ) + "}" );
            log.info ( "\t{FIXED:MSG:" + SOAPMessage.CHARACTER_SET_ENCODING + "} = {" + context.getMessage ().getProperty ( SOAPMessage.CHARACTER_SET_ENCODING )
                + "}" );

            for ( Entry<String, Object> entry: context.entrySet () ) {
                log.info ( "\t{" + entry.getKey () + "} = {" + entry.getValue () + "}" );
            }
            for ( String entry: context.getRoles () ) {
                log.info ( "\t{FIXED:ROLE} = {" + entry + "}" );
            }
        }

        if ( outbound == null ) {
            // NOP
        } else if ( !outbound.booleanValue () ) {
            // NOP
        } else {
            // NOP
            if ( FORCE_OUTBOUND_ENCODING != null ) {
                if ( doTraceMessage () ) {
                    log.info ( "forcing encoding of outbound SOAP message to " + FORCE_OUTBOUND_ENCODING );
                }

                try {
                    context.getMessage ().setProperty ( SOAPMessage.CHARACTER_SET_ENCODING, FORCE_OUTBOUND_ENCODING );
                } catch ( SOAPException e ) {
                    throw new RuntimeException ( e );
                }
            }
		}

        if ( doTraceMessage () ) {
            log.info ( "\t{FIXED:MSG:" + SOAPMessage.CHARACTER_SET_ENCODING + " - POST} = {" +
                context.getMessage ().getProperty ( SOAPMessage.CHARACTER_SET_ENCODING ) + "}" );
            log.info ( "SOAP message handling completed; proceeding" );
        }

		lw.stop();
		return true;
	}

	@Override
	public void close(MessageContext context) {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "close", false);
		lw.start();
		// TODO
		lw.stop();
	}

	@Override
	public boolean handleFault(SOAPMessageContext soapMessageContext) {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "handleFault", false);
		lw.start();
		// TODO
		lw.stop();
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "getHeaders", false);
		lw.start();
		// TODO
		lw.stop();
		return Collections.emptySet();
	}
}
