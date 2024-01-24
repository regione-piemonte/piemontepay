/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysim.integration.epaymodric.stubs.DtoBaseContabilizzatore;


/**
 * NG
 */

//DTO input del servizio ricercaProvvisori
public class RicercaProvvisorioInputDTO extends DtoBaseContabilizzatore implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String identificativoFlusso;

    private XMLGregorianCalendar dataProvvisorioDa;

    private XMLGregorianCalendar dataProvvisorioA;

    private String stato;

}
