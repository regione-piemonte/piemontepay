/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.util.List;


/**
 *
 */

public class InserisciProvvisorioOutputResponse extends ParentOutput {

    private static final long serialVersionUID = -2578388483347857376L;

    private List<InserisciProvvisorioOutputDTO> inserisciProvvisorioOutputDTOList;

    /**
     * @return the inserisciProvvisorioOutputDTOList
     */
    public final List<InserisciProvvisorioOutputDTO> getInserisciProvvisorioOutputDTOList () {
        return inserisciProvvisorioOutputDTOList;
    }

    /**
     * @param inserisciProvvisorioOutputDTOList the inserisciProvvisorioOutputDTOList to set
     */
    public final void setInserisciProvvisorioOutputDTOList ( List<InserisciProvvisorioOutputDTO> inserisciProvvisorioOutputDTOList ) {
        this.inserisciProvvisorioOutputDTOList = inserisciProvvisorioOutputDTOList;
    }

}
