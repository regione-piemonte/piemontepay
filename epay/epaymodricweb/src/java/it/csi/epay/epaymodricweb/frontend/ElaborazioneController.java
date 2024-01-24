/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/elaborazioni")
public class ElaborazioneController extends AuthenticatedParentController {

	@Override
	public void loadComboboxes(Model model) {
		// NOP
	}

	@RequestMapping(value = "ricerca")
	public String ricerca(Model model, HttpServletRequest request, HttpServletResponse response) {

		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("message", "La funzionalit richiesta non  ancora stata implementata");
		return "error";

	}

}
