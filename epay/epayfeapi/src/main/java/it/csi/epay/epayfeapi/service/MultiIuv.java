package it.csi.epay.epayfeapi.service;

import io.quarkiverse.cxf.annotation.CXFClient;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.MdpMultiIuvWSInterface;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.MdpMultiIuvWSInterface_Service;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


@ApplicationScoped
@Transactional
public class MultiIuv {

	@Inject
	@CXFClient ( "MdpMultiIuvsrvServiceWS" )
	@SuppressWarnings ( "all" )
	MdpMultiIuvWSInterface mdpMultiIuvWSInterface;

	@Inject
	ParametriService parametriService;

	@PostConstruct
	public void initialization () throws MalformedURLException, ClassNotFoundException {
		var properties = new Properties ();
		//
		var listaParametri = parametriService.findByGruppo ( "epayservices" );
		for ( var parametro : listaParametri ) {
			properties.put ( parametro.getId ().getCodice (), parametro.getValore () );
		}
		//
		parametriService.getParametro ( "mdp", "passphrase" );

		if ( properties.get ( "mdpMultiIuv.apiManager.flag" ).equals ( "true" ) ) {
			var urlService = new URL ( (String) properties.get ( "mdpMultiIuv.url.wsdl" ) );
			var service = new MdpMultiIuvWSInterface_Service ( urlService );
			var mdpMultiIuvWS = service.getMdpMultiIuvWSPort ();
			//
			var trm = new TokenRetryManager ();
			trm.setOauthHelper ( new OauthHelper (
							(String) properties.get ( "mdpMultiIuv.apiManager.oAuthUrl" ),
							(String) properties.get ( "mdpMultiIuv.apiManager.consumerKey" ),
							(String) properties.get ( "mdpMultiIuv.apiManager.consumerSecret" ) ) );
			trm.setWsProvider ( new CxfImpl () );
			//
			var gwfb = new GenericWrapperFactoryBean ();
			gwfb.setEndPoint ( (String) properties.get ( "mdpMultiIuv.apiManager.endPoint" ) );
			gwfb.setWrappedInterface ( MdpMultiIuvWSInterface.class );
			gwfb.setPort ( mdpMultiIuvWS );
			gwfb.setTokenRetryManager ( trm );

			mdpMultiIuvWSInterface = (MdpMultiIuvWSInterface) gwfb.create ();
		}
	}

}
