/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.model.provvisorio;

import java.io.Serializable;
import java.util.List;


/**
 *
 */

public class UploadFileVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<ProvvisorioUploadVO> provvisori;

    private List<ErroreUploadVO> erroriList;

    private String errori;

    /**
     * @return the provvisori
     */
    public List<ProvvisorioUploadVO> getProvvisori () {
        return provvisori;
    }

    /**
     * @param provvisori the provvisori to set
     */
    public void setProvvisori ( List<ProvvisorioUploadVO> provvisori ) {
        this.provvisori = provvisori;
    }

    /**
     * @return the errori
     */
    public String getErrori () {
        return errori;
    }

    /**
     * @param errori the errori to set
     */
    public void setErrori ( String errori ) {
        this.errori = errori;
    }

    public List<ErroreUploadVO> getErroriList () {
        return erroriList;
    }

    public void setErroriList ( List<ErroreUploadVO> erroriList ) {
        this.erroriList = erroriList;
    }

}
