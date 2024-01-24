/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.common.exceptions;

import java.util.List;

import it.csi.epay.epaymodricweb.model.provvisorio.ErroreUploadVO;


/**
 *
 */

public class FileUploadException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3702180301133390808L;
    private List<ErroreUploadVO> errori;

    public List<ErroreUploadVO> getErrori () {
        return errori;
    }

    public FileUploadException ( List<ErroreUploadVO> errori ) {
        super ();
        this.errori = errori;
    }

    public void setErrori ( List<ErroreUploadVO> errori ) {
        this.errori = errori;
    }

}
