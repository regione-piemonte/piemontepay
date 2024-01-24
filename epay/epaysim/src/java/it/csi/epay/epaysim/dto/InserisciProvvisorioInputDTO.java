/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.util.List;


/**
 *
 */

public class InserisciProvvisorioInputDTO extends ParentInput {

    private static final long serialVersionUID = -8524673589869522163L;

    private String xmlFlusso;

    private String identificativoFlussoBT;

    private List<ProvvisorioDTO> provvisorioDTOList;

    /**
     * @return the xmlFlusso
     */
    public final String getXmlFlusso () {
        return xmlFlusso;
    }

    /**
     * @param xmlFlusso the xmlFlusso to set
     */
    public final void setXmlFlusso ( String xmlFlusso ) {
        this.xmlFlusso = xmlFlusso;
    }

    /**
     * @return the identificiativoFlussoBT
     */
    public String getIdentificativoFlussoBT () {
        return identificativoFlussoBT;
    }

    /**
     * @param identificativoFlussoBT the identificativoFlussoBT to set
     */
    public void setIdentificativoFlussoBT ( String identificativoFlussoBT ) {
        this.identificativoFlussoBT = identificativoFlussoBT;
    }

    /**
     * @return the provvisorioDTOList
     */
    public final List<ProvvisorioDTO> getProvvisorioDTOList () {
        return provvisorioDTOList;
    }

    /**
     * @param provvisorioDTOList the provvisorioDTOList to set
     */
    public final void setProvvisorioDTOList ( List<ProvvisorioDTO> provvisorioDTOList ) {
        this.provvisorioDTOList = provvisorioDTOList;
    }

}
