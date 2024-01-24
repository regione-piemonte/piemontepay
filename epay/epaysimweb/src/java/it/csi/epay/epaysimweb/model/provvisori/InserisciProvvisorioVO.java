/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.provvisori;

import org.springframework.web.multipart.MultipartFile;

import it.csi.epay.epaysimweb.model.GenericVO;


public class InserisciProvvisorioVO extends GenericVO {

    private static final long serialVersionUID = -6378987759398157172L;

    private MultipartFile xmlFlussoProvvisorio;

    /**
     * @return the xmlFlussoProvvisorio
     */
    public final MultipartFile getXmlFlussoProvvisorio () {
        return xmlFlussoProvvisorio;
    }

    /**
     * @param xmlFlussoProvvisorio the xmlFlussoProvvisorio to set
     */
    public final void setXmlFlussoProvvisorio ( MultipartFile xmlFlussoProvvisorio ) {
        this.xmlFlussoProvvisorio = xmlFlussoProvvisorio;
    }

}
