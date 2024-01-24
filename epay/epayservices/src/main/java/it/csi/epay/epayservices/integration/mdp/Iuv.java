/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.mdp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import it.csi.epay.epayservices.integration.db.manager.ParametriManager;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.mdpiuv.CSIException_Exception;
import it.csi.mdpiuv.MdpIuvSrvException_Exception;
import it.csi.mdpiuv.MdpiuvWSInterface;
import it.csi.mdpiuv.MdpiuvWSInterface_Service;
import it.csi.mdpiuv.SOAPException_Exception;
import it.csi.mdpiuv.SystemException_Exception;
import it.csi.mdpiuv.UnrecoverableException_Exception;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;

@AccessTimeout(value = 30, unit = TimeUnit.SECONDS)
@Singleton
@Startup
public class Iuv {

    @EJB
    ParametriManager parametriManager;

	private MdpiuvWSInterface mdpiuvWSInterface;

    @PostConstruct
    public void initialization () throws MalformedURLException, ClassNotFoundException {

        Properties properties = new Properties ();

        List<Parametro> listaParametri = parametriManager.elencoPerGruppo ( "epayservices" );
        for ( Parametro parametro: listaParametri ) {
            properties.put ( parametro.getCodice (), parametro.getValore () );
        }

        if ( ( (String) properties.get ( "mdpIuv.apiManager.flag" ) ).equals ( "true" ) ) {
            //TODO aggiungerlo nel build
            URL wsdlLocation = Iuv.class.getClassLoader ().getResource ( "wsdl/MdpiuvsrvServiceWS.wsdl" );
            MdpiuvWSInterface_Service service = new MdpiuvWSInterface_Service ( wsdlLocation );
            MdpiuvWSInterface mdpIuvWS = service.getMdpiuvWSPort ();

            TokenRetryManager trm = new TokenRetryManager ();
            trm.setOauthHelper ( new OauthHelper (
                (String) properties.get ( "mdpIuv.apiManager.oAuthUrl" ),
                (String) properties.get ( "mdpIuv.apiManager.consumerKey" ),
                (String) properties.get ( "mdpIuv.apiManager.consumerSecret" ) ) );
            trm.setWsProvider ( new CxfImpl () );

            GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean ();
            gwfb.setEndPoint ( (String) properties.get ( "mdpIuv.apiManager.endPoint" ) );
            gwfb.setWrappedInterface ( MdpiuvWSInterface.class );
            gwfb.setPort ( mdpIuvWS );
            gwfb.setTokenRetryManager ( trm );

            mdpiuvWSInterface = (MdpiuvWSInterface) gwfb.create ();
        } else {
            URL urlService = new URL ( (String) properties.get ( "mdpIuv.url.wsdl" ) );
            MdpiuvWSInterface_Service service = new MdpiuvWSInterface_Service ( urlService );
            MdpiuvWSInterface mdpIuvWS = service.getMdpiuvWSPort ();

            mdpiuvWSInterface = mdpIuvWS;
        }
    }

	public String generateNewIuv(final TipoPagamento tipoPagamento) throws MdpException{
		try {

			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Call mdpiuvWSInterface.getSingleIUV");
			System.out.println("     Param IdApplicazione        : " + tipoPagamento.getIdApplicazione());
			System.out.println("     Param Codice Versamento     : " + tipoPagamento.getCodiceVersamento());
			String iuv = mdpiuvWSInterface.getSingleIUV(tipoPagamento.getIdApplicazione(), tipoPagamento.getCodiceVersamento());
			System.out.println("Response mdpiuvWSInterface.getSingleIUV : " + iuv);
			System.out.println("--------------------------------------------------------------------------------");

			return iuv;
        } catch ( CSIException_Exception | MdpIuvSrvException_Exception | SOAPException_Exception
				| SystemException_Exception | UnrecoverableException_Exception e) {
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------------------");
			throw new MdpException("Errore invocazione getSingleIUV", e);
		}
	}

	public String generateNewIuv(final TipoPagamento tipoPagamento, int numberTry) throws MdpException {
		try {
			return generateNewIuv(tipoPagamento);
		} catch (Exception e ) {
			if (--numberTry == 0 ) {
				throw e;
			}
			return generateNewIuv(tipoPagamento, numberTry);
		}
	}


	public static void main(String[] args) {
		try {
			URL urlService;
			try {
				urlService = new URL("http://tst-applogic.reteunitaria.piemonte.it/mdpiuv/MdpiuvsrvServiceWS?wsdl");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return ;
			}
			MdpiuvWSInterface_Service service = new MdpiuvWSInterface_Service(urlService);
			MdpiuvWSInterface mdpIuvWS = service.getMdpiuvWSPort();

			String iuv = mdpIuvWS.getSingleIUV("EPAY_RP", "test");
			System.out.println("Codice Iuv generato : >" + iuv + "<");
        } catch ( CSIException_Exception | MdpIuvSrvException_Exception | SOAPException_Exception
				| SystemException_Exception | UnrecoverableException_Exception e) {
			e.printStackTrace();
			throw new MdpException("Errore invocazione getSingleIUV", e);
		}
	}
}
