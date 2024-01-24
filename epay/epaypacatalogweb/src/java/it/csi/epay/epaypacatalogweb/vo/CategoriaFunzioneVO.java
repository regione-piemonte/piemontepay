/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.vo;

import java.io.Serializable;


public class CategoriaFunzioneVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String codice;

    private String descrizione;

    public CategoriaFunzioneVO ( String codice, String descrizione ) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codice == null ) ? 0 : codice.hashCode () );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass () != obj.getClass () ) {
            return false;
        }
        CategoriaFunzioneVO other = (CategoriaFunzioneVO) obj;
        if ( codice == null ) {
            if ( other.codice != null ) {
                return false;
            }
        } else if ( !codice.equals ( other.codice ) ) {
            return false;
        }
        return true;
    }

}
