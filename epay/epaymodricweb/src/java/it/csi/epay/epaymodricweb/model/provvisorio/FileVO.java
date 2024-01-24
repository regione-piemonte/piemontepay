/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.model.provvisorio;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


/**
 *
 */
public class FileVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5831038408242687902L;

    // campo finto per mostrare messaggi non relativi ad un campo specifico 
    // (sono sicuro che esiste un modo migliore per fare questa cosa)
    private String validitaGenerica;

    private MultipartFile fileContent;

    private String estensione;

    /**
     * 
     */
    public FileVO () {
        super ();
    }

    /**
     * @param estensione
     */
    public FileVO ( String estensione ) {
        super ();
        this.estensione = estensione;
    }

    /**
     * @return the validitaGenerica
     */
    public String getValiditaGenerica () {
        return validitaGenerica;
    }

    /**
     * @param validitaGenerica the validitaGenerica to set
     */
    public void setValiditaGenerica ( String validitaGenerica ) {
        this.validitaGenerica = validitaGenerica;
    }

    /**
     * @return the estensione
     */
    public String getEstensione () {
        return estensione;
    }

    /**
     * @param estensione the estensione to set
     */
    public void setEstensione ( String estensione ) {
        this.estensione = estensione;
    }

    /**
     * @return the fileContent
     */
    public MultipartFile getFileContent () {
        return fileContent;
    }

    /**
     * @param fileContent the fileContent to set
     */
    public void setFileContent ( MultipartFile fileContent ) {
        this.fileContent = fileContent;
    }

}
