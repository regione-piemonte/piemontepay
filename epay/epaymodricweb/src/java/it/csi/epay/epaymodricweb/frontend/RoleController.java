/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ruoli")
public class RoleController extends GenericParentController {
	
	@RequestMapping("/seleziona")
	public ModelAndView selezionaRuolo() {
		return new ModelAndView("ruolo/selection", "role_list", new ArrayList<>());
	}
	
}
