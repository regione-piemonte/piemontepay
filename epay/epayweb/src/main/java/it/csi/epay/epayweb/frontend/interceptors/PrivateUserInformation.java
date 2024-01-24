/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import it.csi.epay.epayweb.frontend.models.IdentitaShibboleth;
import it.csi.epay.epayweb.utilities.LogUtil;

public class PrivateUserInformation extends HandlerInterceptorAdapter {

	protected LogUtil log = new LogUtil(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String methodName = "preHandle";

        log.debugStart ( methodName );

		/* esempio parametri shibboleth
		Shib-Cookie-Name -
		Shib-Session-ID - _63588789e122cf9ff563cd534b8b2ec6
		Shib-Session-Index - 49c81ef1b6db1aa34a60b5d0d76fe8d9c9fa4f1d3d959fcd89274f3cc50b905f
		Shib-Identity-Provider - IDENTITY_PROVIDER_GASP_REGIONE
		Shib-Authentication-Method - https://www.spid.gov.it/SpidL1
		Shib-Authentication-Instant - 2018-03-08T15:11:11.036Z
		Shib-AuthnContext-Class - https://www.spid.gov.it/SpidL1
		Shib-AuthnContext-Decl -
		Shib-Assertion-Count -
		Shib-Identita-Cognome - Pcom
		Shib-Identita-Nome - Test
		Shib-Identita-CodiceFiscale - STTRLI70A01F205L
		Shib-Identita-LivAuth - 2
		Shib-Iride-IdentitaDigitale - STTRLI70A01F205L/Test/Pcom/SPID/20180308161110/2/pu3BlVHLOfLQvzW1f5TQ9A==
		Shib-Identita-TimeStamp - 20180308161110
		Shib-Identita-Provider - SPID
		Shib-loa -
		Shib-Identita-loa - https://www.spid.gov.it/SpidL1
		Shib-community - SPID
		Shib-Identita-Riscontro - S
		Codice-identificativo-SPID - PTITOB59U4BS7F
		Codice-fiscale-SPID - TINIT-STTRLI70A01F205L
		Shib-Email - claudio.quaresima@postecom.it
		Shib-Mobile-Phone -
		Shib-Application-ID - APPLICATION_DEV-EPAY-SISTEMAPIEMONTE.BILANCIO.CSI.IT_443_LIV1_SISP_LIV1_SPID_GASP_REGIONE
		 */

		IdentitaShibboleth identitaShibboleth = new IdentitaShibboleth(request);
		request.getSession().setAttribute("identitaShibboleth", identitaShibboleth);

        log.debug ( methodName, identitaShibboleth.toString () );

        log.debugEnd ( methodName );

		return true;
	}

}
