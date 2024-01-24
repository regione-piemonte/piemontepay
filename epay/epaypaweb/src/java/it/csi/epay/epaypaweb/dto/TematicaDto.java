/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;


public class TematicaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String cod;

    private String des;

    private Boolean visibilitaTotale;

    public TematicaDto ( Integer id, String cod ) {
        this.id = id;
        this.cod = cod;
    }

    public Boolean getVisibilitaTotale () {
        return visibilitaTotale;
    }

    public void setVisibilitaTotale ( Boolean visibilitaTotale ) {
        this.visibilitaTotale = visibilitaTotale;
    }

    public String getCod () {
        return cod;
    }

    public Integer getId () {
        return id;
    }

    public String getDes () {
        return des;
    }

    public void setDes ( String des ) {
        this.des = des;
    }

    @Override
    public String toString () {
        final String COMMA = ", ";
        StringBuilder s = new StringBuilder ();
        s.append ( "{ " );
        s.append ( "id:" ).append ( id ).append ( COMMA );
        s.append ( "cod:" ).append ( quote ( cod ) ).append ( COMMA );
        s.append ( "des:" ).append ( quote ( des ) ).append ( COMMA );
        s.append ( "visibilitaTotale:" ).append ( visibilitaTotale );
        s.append ( " }" );
        return s.toString ();
    }

}
