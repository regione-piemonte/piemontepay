/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

/**
 *
 */

public class InserisciProvvisorioOutputDTO extends ParentOutput {

    private static final long serialVersionUID = -2578388483347857376L;

    private ProvvisorioDTO provvisorioDTO;

    /**
     * @return the provvisorioDTO
     */
    public final ProvvisorioDTO getProvvisorioDTO () {
        return provvisorioDTO;
    }

    /**
     * @param provvisorioDTO the provvisorioDTO to set
     */
    public final void setProvvisorioDTO ( ProvvisorioDTO provvisorioDTO ) {
        this.provvisorioDTO = provvisorioDTO;
    }

}
