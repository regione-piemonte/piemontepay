/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.model.provvisorio;

import java.io.Serializable;

import org.springframework.util.StringUtils;



/**
 *
 */

public class ErroreUploadVO implements Serializable, Comparable<ErroreUploadVO> {

    /**
     *
     */
    private static final long serialVersionUID = 1491773499770695416L;

    private final int riga;

    private int colonna;

    private String nomeCampo;

    private String errore;

    public ErroreUploadVO ( int riga, int colonna ) {
        super ();
        this.riga = riga;
        this.colonna = colonna;
        this.nomeCampo = "";
        this.errore = "";
    }

    public ErroreUploadVO ( int riga, int colonna, String nomeCampo ) {
        super ();
        this.riga = riga;
        this.colonna = colonna;
        this.nomeCampo = nomeCampo;
        this.errore = "";
    }

    public ErroreUploadVO ( int riga, String errore ) {
        super ();
        this.riga = riga;
        this.errore = errore;
    }

    public ErroreUploadVO ( int riga, int colonna, String nomeCampo, String errore ) {
        super ();
        this.riga = riga;
        this.colonna = colonna;
        this.nomeCampo = nomeCampo;
        this.errore = errore;
    }

    public int getRiga () {
        return riga;
    }

    public int getColonna () {
        return colonna;
    }

    public void setColonna ( int colonna ) {
        this.colonna = colonna;
    }

    public String getNomeCampo () {
        return nomeCampo;
    }

    public void setNomeCampo ( String nomeCampo ) {
        this.nomeCampo = nomeCampo;
    }

    public String getErrore () {
        return errore;
    }

    public void setErrore ( String errore ) {
        this.errore = errore;
    }

    @Override
    public int compareTo ( ErroreUploadVO o ) {
        if ( o == null ) {
            return 1;
        }
        if ( this.getRiga () > o.getRiga () ) {
            return 1;

        }
        if ( this.getRiga () < o.getRiga () ) {
            return -1;

        }
        if ( this.getRiga () == o.getRiga () && this.getColonna () > o.getColonna () ) {
            return 1;
        }
        if ( this.getRiga () == o.getRiga () && this.getColonna () < o.getColonna () ) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString () {
        if ( riga > 0 && colonna > 0 && StringUtils.hasText ( nomeCampo ) && StringUtils.hasText ( errore ) ) {
            return "RIGA " + ( riga + 1 ) + " COLONNA " + colonna + " CAMPO " + nomeCampo + " - " + errore;
        }
        if ( riga > 0 && StringUtils.hasText ( errore ) ) {
            return "RIGA " + ( riga + 1 ) + " - " + errore;
        }

        return "Non e' stato possibile recuperare l'errore.";
    }

}
