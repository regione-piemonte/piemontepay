/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.dto.common;

/**
 *
 */
public class FileContentDTO {

    private String mimeType;

    private String fileName;

    private byte [] content;

    public FileContentDTO () {
        // NOP
    }

    public FileContentDTO ( String fileName, byte [] content, String mimeType ) {
        super ();
        this.mimeType = mimeType;
        this.fileName = fileName;
        this.content = content;
    }

    public FileContentDTO ( String fileName, byte [] content ) {
        super ();
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileNameSafe () {
        if ( fileName == null || fileName.trim ().isEmpty () ) {
            return null;
        } else {
            return fileName.trim ().replaceAll ( "[^a-zA-Z0-9_\\.]+", "_" );
        }
    }

    public String getMimeType () {
        return mimeType;
    }

    public void setMimeType ( String mimeType ) {
        this.mimeType = mimeType;
    }

    public String getFileName () {
        return fileName;
    }

    public void setFileName ( String fileName ) {
        this.fileName = fileName;
    }

    public byte [] getContent () {
        return content;
    }

    public void setContent ( byte [] content ) {
        this.content = content;
    }

}
