/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/accessoLibero")
public class AccessoLiberoController extends _Controller {

	@RequestMapping(value = "/pagaConIuv", method = RequestMethod.GET)
	public String pagaConIuv() {
		return redirect("pagaConIuv/");
	}
	
	@RequestMapping(value = "/pagaSenzaIuv", method = RequestMethod.GET)
	public String pagaSenzaIuv() {
		return redirect("pagaSenzaIuv/");
	}

	@RequestMapping(value = "/verifica", method = RequestMethod.GET)
	public String verifica() {
		return redirect("verifica/");
	}

	@RequestMapping(value = "/stampaAvviso", method = RequestMethod.GET)
	public String stampa(HttpSession httpSession) {
		return redirect("stampaAvviso/");
	}
}
