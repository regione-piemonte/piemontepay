/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service;

import java.util.Map;

import it.csi.epay.epaypacatalogsrv.model.TemplateEmail;


public interface TemplateMailService {

    TemplateEmail getTemplate ( String codice );

    String popolaTemplate ( String raw, Map<String, String> model );

}
