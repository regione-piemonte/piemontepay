/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import it.csi.epay.epaypaweb.business.interf.AccessoBusiness;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import org.apache.struts2.convention.annotation.*;

import javax.ejb.EJB;

import static com.opensymphony.xwork2.Action.SUCCESS;


@Namespace ( "/" )
@InterceptorRef ( "epaypawebStack" )
@Results ( {
    //@formatter:off
    @Result ( name = SUCCESS, location = "pages/main-menu-integrata.jsp" )
    //@formatter:on
} )
public class MainMenuHomeIntegrataAction extends EpaypawebBaseAction {

    static private final long serialVersionUID = 1L;

    /* mantengo il logger col vecchio nome per compatibilita' */
    static private final String CLASSNAME = "MainMenuAction";

    private String cfEnte;

    @EJB
    private AccessoBusiness accessoBusiness;

    @Action ( "entry-main-menu" )
    public String entryMainMenu () {
        String methodName = "execute";


        String result;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            SessionContext sessionContext = getSessionContext ();

            /*
             * POST-INTEGRAZIONE il check con la funzione getListaCdu
             * non e' piu' valido perche' esistono dei CDU virtuali
             * non specificatamente collegati ad una categoria (es. 'LOGIN')
             * occorre utilizzare la funzione getListaCduNonVirtuali
             * per eseguire il controllo escludendo i casi d'uso virtuali
             */
            if ( sessionContext.getListaCduNonVirtuali ().isEmpty () ) {
                String message = getText ( "nessun.caso.uso.configurato.per.utente.ente",
                    new String [] { sessionContext.getUtente ().getNomeCompleto (),
                        sessionContext.getEnte ().getDenominazione () } );
                log.error ( message );
                addActionError ( message );
                result = "system-error";
            } else {
                result = SUCCESS;
            }

            if ( null != sessionContext && null != sessionContext.getEnte () ) {
                cfEnte = sessionContext.getEnte ().getCodFiscale ();
            }

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return result;
    }

    @Action ( "show-main-menu" )
    public String showMainMenu () {
        SessionContext sessionContext = getSessionContext ();
        if ( null != sessionContext && null != sessionContext.getEnte () ) {
            cfEnte = sessionContext.getEnte ().getCodFiscale ();
        }
        return SUCCESS;
    }

    public String getCfEnte () {
        return cfEnte;
    }

    public void setCfEnte ( String cfEnte ) {
        this.cfEnte = cfEnte;
    }
}
