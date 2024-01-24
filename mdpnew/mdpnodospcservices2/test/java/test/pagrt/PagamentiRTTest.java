/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package test.pagrt;

import static org.junit.Assert.assertTrue;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.IntestazionePPT;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaRT;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PaaInviaRTRisposta;
import it.csi.mdp.mdpnodospcservices.integration.pagrt.interfacews.PagamentiTelematiciRT;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PagamentiRTTest {

	ApplicationContext ctx = null;

	@Before
	public void setUp() throws Exception {
/*
		try {
			// LoggerUtil.begin();
			ctx = new ClassPathXmlApplicationContext(
					new String[] { "classpath:/test/pagrt/beanContext.xml" });
			System.out.println("Contesto Spring inizializzato correttamente");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void funzionanteBase() throws Exception {

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://tst-jboss43cp09-01.csi.it:36410/mdpnodospcservices/PagamentiTelematiciRT/PagamentiTelematiciRT");
		factory.setServiceClass(PagamentiTelematiciRT.class);
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
//		PagamentiTelematiciRT servizio = (PagamentiTelematiciRT) ctx.getBean("pagamentiTelematiciRTService");
		try {
			PagamentiTelematiciRT servizio =  (PagamentiTelematiciRT)factory.create();
			IntestazionePPT header = new IntestazionePPT();
			header.setCodiceContestoPagamento("n/a");
			header.setIdentificativoDominio("80087670016");
			header.setIdentificativoIntermediarioPA("01995120019");
			header.setIdentificativoStazioneIntermediarioPA("01995120019_01");
			header.setIdentificativoUnivocoVersamento("iuv");
			
			PaaInviaRT bodyrichiesta = new PaaInviaRT();
			
			bodyrichiesta.setTipoFirma("0");
			FileInputStream fis = new FileInputStream("bin/test/pagrt/rt.xml");
			ByteArrayOutputStream bais = new ByteArrayOutputStream(fis.available());
			while (true) {
				int r = fis.read();
				if (r ==-1)
					break;
				bais.write(r);
			}
			bodyrichiesta.setRt(bais.toByteArray());
			fis.close();
			bais.close();
			PaaInviaRTRisposta risposta = servizio.paaInviaRT(bodyrichiesta, header);
			
			dump(risposta);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	private void dump(Object o) {
		XStream xstream = new XStream(new DomDriver());
		System.out.println(xstream.toXML(o));
	}
}
