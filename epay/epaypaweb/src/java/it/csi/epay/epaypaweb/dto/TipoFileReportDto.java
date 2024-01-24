/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import javax.annotation.Generated;


/** dto facade <-> business <-> persistence */
public class TipoFileReportDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2908258297512805465L;

    private Integer id;

    private String codice;

    private String descrizione;

    /**
     * 
     */
    public TipoFileReportDto () {
        super ();
    }

    @Generated ( "SparkTools" )
    private TipoFileReportDto ( Builder builder ) {
        this.id = builder.id;
        this.codice = builder.codice;
        this.descrizione = builder.descrizione;
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
     * @return the codice
     */
    public String getCodice () {
        return codice;
    }

    /**
     * @param codice the codice to set
     */
    public void setCodice ( String codice ) {
        this.codice = codice;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "StatoReportDto [id=" );
        builder.append ( id );
        builder.append ( ", codice=" );
        builder.append ( codice );
        builder.append ( ", descrizione=" );
        builder.append ( descrizione );
        builder.append ( "]" );
        return builder.toString ();
    }

    /**
     * Creates builder to build {@link TipoFileReportDto}.
     * 
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link TipoFileReportDto}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Integer id;

        private String codice;

        private String descrizione;

        private Builder () {
        }

        public Builder withId ( Integer id ) {
            this.id = id;
            return this;
        }

        public Builder withCodice ( String codice ) {
            this.codice = codice;
            return this;
        }

        public Builder withDescrizione ( String descrizione ) {
            this.descrizione = descrizione;
            return this;
        }

        public TipoFileReportDto build () {
            return new TipoFileReportDto ( this );
        }
    }

}
