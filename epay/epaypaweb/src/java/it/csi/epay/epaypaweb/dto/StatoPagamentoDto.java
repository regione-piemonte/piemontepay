/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;


/** dto facade <-> business <-> persistence */
public class StatoPagamentoDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2908258297512805465L;

    private Integer id;


    private String descrizione;


    /**
     * 
     */
    public StatoPagamentoDto () {
        super ();
    }


    public StatoPagamentoDto ( Integer id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }


    /**
     * @return the id
     */
    public Integer getId () {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId ( Integer id ) {
        this.id = id;
    }




    /**
     * @return the descrizione
     */
    public String getDescrizione () {
        return descrizione;
    }


    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "StatoPagamentoDto [id=" );
        builder.append ( id );
        builder.append ( ", descrizione=" );
        builder.append ( descrizione );
        builder.append ( "]" );
        return builder.toString ();
    }

}
