/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

import it.csi.epay.epaypaweb.presentation.util.ApplicationConfiguration;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("logout")
@Results({
//@formatter:off
	@Result(name = SUCCESS, type="redirect", location = "${logoutUrl}")
//@formatter:on
})
public class LogoutAction extends EpaypawebBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = LogoutAction.class.getSimpleName();

	@Override
	@SuppressWarnings("rawtypes")
	public String execute() throws Exception {
		String methodName = "execute";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		SessionMap session = (SessionMap) ActionContext.getContext().getSession();
		session.clear();
		session.invalidate();

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return SUCCESS;
	}

	public String getLogoutUrl() {
		return ApplicationConfiguration.getApplicationConfiguration().getStringProperty("logout.url");
	}
}
