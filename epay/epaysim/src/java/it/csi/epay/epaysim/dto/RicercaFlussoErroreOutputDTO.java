/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.util.List;

import it.csi.epay.epaysim.integration.epaymodric.stubs.DtoBaseResponse;


/**
 *
 */
//NG: dto di output per il servizio RicercaFlussoErroreInputDTO
public class RicercaFlussoErroreOutputDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private DtoBaseResponse DtoBaseResponse;

    private List<FlussoErrorePagopaDTO> listaFlussoErrorePagopaDTO;

}
