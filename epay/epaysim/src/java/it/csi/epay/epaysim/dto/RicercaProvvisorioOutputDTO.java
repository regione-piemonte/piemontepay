/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.util.List;

import it.csi.epay.epaysim.integration.epaymodric.stubs.DtoBaseResponse;


/**
 * NG
 */
//DTO di output per il servizio ricercaProvvisori
public class RicercaProvvisorioOutputDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private DtoBaseResponse DtoBaseResponse;

    private List<ProvvisorioDTO> listaProvvisorioDTO;

    public RicercaProvvisorioOutputDTO () {

    }

    public DtoBaseResponse getDtoBaseResponse () {
        return DtoBaseResponse;
    }

    public void setDtoBaseResponse ( DtoBaseResponse dtoBaseResponse ) {
        DtoBaseResponse = dtoBaseResponse;
    }

    public List<ProvvisorioDTO> getListaProvvisorioDTO () {
        return listaProvvisorioDTO;
    }

    public void setListaProvvisorioDTO ( List<ProvvisorioDTO> listaProvvisorioDTO ) {
        this.listaProvvisorioDTO = listaProvvisorioDTO;
    }

}
