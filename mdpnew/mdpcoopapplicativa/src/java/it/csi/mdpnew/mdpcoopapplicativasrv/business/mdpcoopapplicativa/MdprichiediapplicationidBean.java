/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa.interfaces.MdpRichiediApplicationIdBusinessInterface;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResponseType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResultType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop.PersistenceException;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTErrore;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.MdpcoopapplicativaConstants;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.coop.CostantiErrori;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;


//Definizione transazione vedi : ejb-jar.xml

public class MdprichiediapplicationidBean implements SessionBean, MdpRichiediApplicationIdBusinessInterface {

    private static final long serialVersionUID = 1L;

    protected MdpcoopapplicativaImpl delegate = null;

    public SessionContext ctx = null;

    public boolean hasSelfCheck () throws it.csi.csi.wrapper.CSIException {
        return delegate.hasSelfCheck ();
    }

    public void ejbActivate () throws EJBException, RemoteException {
        // TODO Auto-generated method stub
    }

    public void ejbPassivate () throws EJBException, RemoteException {
        // TODO Auto-generated method stub
    }

    public void ejbRemove () throws EJBException, RemoteException {
        // TODO Auto-generated method stub
    }

    public void ejbCreate () {
        Logger logger = getLogger ( "ejb" );
        logger.debug ( "[MdpiuvBean::create] - BEGIN" );

        logger.debug ( "[MdpiuvBean::create] - END" );
    }

    public void setSessionContext ( SessionContext ctx ) throws EJBException, RemoteException {

        Logger logger = getLogger ( null );
        logger.debug ( "[MdpiuvBean::setSessionContext] - START" );
        this.ctx = ctx;

        Object implInitOptions = null;

        ApplicationContext context;
        try {
            context = new ClassPathXmlApplicationContext ( "beanContext.xml" );
        } catch ( BeansException e ) {

            logger.error ( "[MdpiuvBean::setSessionContext] - errore : " + e.getMessage () );
        }

        logger.debug ( "[MdpiuvBean::setSessionContext] caricato ClassPathXmlApplicationContext " );

        this.ctx = ctx;

        delegate = (MdpcoopapplicativaImpl) ServiceBeanLocator.getBeanByName ( "delegate" );

        //createImpl(implInitOptions);	
        logger.debug ( "[MdpiuvBean::setSessionContext] - END" );
    }

    protected Logger getLogger ( String subsystem ) {
        if ( subsystem != null )
            return Logger.getLogger ( MdpcoopapplicativaConstants.LOGGER_PREFIX + "." + subsystem );
        else
            return Logger.getLogger ( MdpcoopapplicativaConstants.LOGGER_PREFIX );
    }



    private ResponseType returnErrorOutput ( String codiceErrore ) throws PersistenceException {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();
        Logger logger = getLogger ( "ejb" );

        MdpTErrore errore = delegate.findOneErroreByCodiceErrore ( codiceErrore );

        if ( null == errore || !StringUtils.isNotBlank ( errore.getDescrizioneErrore () ) ) {
            result.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            result.setMessaggio ( "Si e' verificato un errore durante l'elaborazione" );
            logger.warn (
                String.format ( "Non e' stato possibile recuperare l'errore associato al codice %s, verra' utilizzato un errore di default.", codiceErrore ) );
        } else {
            result.setCodice ( codiceErrore );
            result.setMessaggio ( errore.getDescrizioneErrore () );
        }

        responseType.setResult ( result );
        return responseType;
    }

    private ResponseType returnErrorResponseType ( String codiceErrore, String descrizione ) {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();

        if ( StringUtils.isNotBlank ( codiceErrore ) && StringUtils.isNotBlank ( descrizione ) ) {
            result.setCodice ( codiceErrore );
            result.setMessaggio ( descrizione );
        } else {
            result.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            result.setMessaggio ( "Si e' verificato un errore durante l'elaborazione" );
        }
        responseType.setResult ( result );
        return responseType;
    }

    private ResponseType returnStandardSuccessfulOutput () {
        ResponseType responseType = new ResponseType ();
        ResultType result = new ResultType ();
        result.setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
        result.setMessaggio ( "Operazione completata correttamente" );
        responseType.setResult ( result );
        return responseType;
    }


	public RichiediApplicationIdResponse richiediApplicationId(RichiediApplicationIdRequest request) {
		System.out.println("TEST LOLLO");
		return null;
	}
}
