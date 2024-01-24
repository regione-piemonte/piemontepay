/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.interceptors;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import it.csi.epay.epayservices.interfaces.ejb.ParametroFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Parametro;
import it.csi.epay.epayweb.utilities.LogUtil;

public class CommonSet extends HandlerInterceptorAdapter {
	
	private static final Long DEADLINELIMITCACHE = 10 * 60 * 1000L; //(in millisecondi) --> 10 minuti
	
	private LogUtil log = new LogUtil(this.getClass());
	
	class GlobalParam {
		String value;
		Date expirationDate;
		
		public GlobalParam(final String value) {
			this.value = value;
			this.expirationDate = new Date();
		}
	}

	static private ConcurrentMap<String, GlobalParam> listaParametri = new ConcurrentHashMap<String, GlobalParam>(30);

	@Autowired
	ParametroFacade parametro;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final String methodName = "preHandle";
		log.debugStart(methodName);
		log.debug(methodName, "request URI: " + request.getRequestURI());
		
		HttpSession session = request.getSession();
		setGruppoParametriInSessione("epayweb", session);
		setParametroInSession("mdp", "passphrase", session);
		setParametroInSession("mdp", "versione", session);
		
		log.debugEnd(methodName);
		return true;
	}
	
	private void setGruppoParametriInSessione(final String gruppo, HttpSession session) {
		String gr = "gruppo_" + gruppo;
		if (session.getAttribute(gr) == null) {
			if (listaParametri.containsKey(gr)) {
				GlobalParam gp = listaParametri.get(gr);
				if (expirated(gp.expirationDate)) {
					listaParametri.remove(gr);
					for (Parametro parametro : parametro.ricercaGruppo(gruppo)) {
						listaParametri.remove(parametro.getCodice());
						listaParametri.putIfAbsent(parametro.getCodice(), new GlobalParam(parametro.getValore()));
					}
					listaParametri.putIfAbsent(gr, new GlobalParam(gr));
				}
			} else {
				for (Parametro parametro : parametro.ricercaGruppo(gruppo)) {
					listaParametri.remove(parametro.getCodice());
					listaParametri.putIfAbsent(parametro.getCodice(), new GlobalParam(parametro.getValore()));
				}
				listaParametri.putIfAbsent(gr, new GlobalParam(gr));
			}
			for (Map.Entry<String,CommonSet.GlobalParam> entry : listaParametri.entrySet()) {
				session.setAttribute(entry.getKey(), entry.getValue().value);
			}
		}
	}		

	private void setParametroInSession(final String gruppo, final String param, HttpSession session) {
		try {
			if (session.getAttribute(param) == null) {
				if (listaParametri.containsKey(param)) {
					GlobalParam gp = listaParametri.get(param);
					if (expirated(gp.expirationDate)) {
						listaParametri.remove(param);
						Parametro paramValue = parametro.ricerca(gruppo, param);
						listaParametri.putIfAbsent(param, new GlobalParam(paramValue.getValore()));
					}
				} else {
					Parametro paramValue = parametro.ricerca(gruppo, param);
					listaParametri.putIfAbsent(param, new GlobalParam(paramValue.getValore()));
				}
				session.setAttribute(param, listaParametri.get(param).value);
			}
		} catch (NoDataException e) {
			throw new RuntimeException("Parametro epayweb/" + param + " non trovato!");
		}
	}
	
	private static boolean expirated(final Date date) {
		return ((new Date()).getTime() - date.getTime()) > DEADLINELIMITCACHE;
	}
}
