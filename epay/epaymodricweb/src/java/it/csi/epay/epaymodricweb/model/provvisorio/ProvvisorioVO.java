/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.model.provvisorio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;


/**
 *
 */

public class ProvvisorioVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String identificativoFlusso;

    private String descrizione;

    private String stato;

    private Integer annoProvvisorio;

    private String causale;

    private Date dataMovimento;

    private Integer annoEsercizio;

    private Date dataFine;

    private String idEnte;

    private BigDecimal importoProvvisorio;

    private Integer numeroProvvisorio;

    private String tipoMovimento;

    @Generated ( "SparkTools" )
    private ProvvisorioVO ( Builder builder ) {
        this.id = builder.id;
        this.identificativoFlusso = builder.identificativoFlusso;
        this.descrizione = builder.descrizione;
        this.stato = builder.stato;
        this.annoProvvisorio = builder.annoProvvisorio;
        this.causale = builder.causale;
        this.dataMovimento = builder.dataMovimento;
        this.annoEsercizio = builder.annoEsercizio;
        this.dataFine = builder.dataFine;
        this.idEnte = builder.idEnte;
        this.importoProvvisorio = builder.importoProvvisorio;
        this.numeroProvvisorio = builder.numeroProvvisorio;
    }

    public ProvvisorioVO () {
        super ();
    }

    /**
     * @param id
     * @param identificativoFlusso
     * @param descrizione
     * @param stato
     * @param annoProvvisorio
     * @param causale
     * @param dataMovimento
     * @param annoEsercizio
     * @param dataFine
     * @param idEnte
     * @param importoDisponibilita
     * @param importoProvvisorio
     * @param numeroProvvisorio
     */
    public ProvvisorioVO ( Long id, String identificativoFlusso, String descrizione, String stato, Integer annoProvvisorio, String causale, Date dataMovimento,
        Integer annoEsercizio, Date dataFine, String idEnte, BigDecimal importoProvvisorio, Integer numeroProvvisorio) {
        super ();
        this.id = id;
        this.identificativoFlusso = identificativoFlusso;
        this.descrizione = descrizione;
        this.stato = stato;
        this.annoProvvisorio = annoProvvisorio;
        this.causale = causale;
        this.dataMovimento = dataMovimento;
        this.annoEsercizio = annoEsercizio;
        this.dataFine = dataFine;
        this.idEnte = idEnte;
        this.importoProvvisorio = importoProvvisorio;
        this.numeroProvvisorio = numeroProvvisorio;
    }

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the identificativoFlusso
     */
    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    /**
     * @param identificativoFlusso the identificativoFlusso to set
     */
    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
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

    /**
     * @return the stato
     */
    public String getStato () {
        return stato;
    }

    /**
     * @param stato the stato to set
     */
    public void setStato ( String stato ) {
        this.stato = stato;
    }

    /**
     * @return the annoProvvisorio
     */
    public Integer getAnnoProvvisorio () {
        return annoProvvisorio;
    }

    /**
     * @param annoProvvisorio the annoProvvisorio to set
     */
    public void setAnnoProvvisorio ( Integer annoProvvisorio ) {
        this.annoProvvisorio = annoProvvisorio;
    }

    /**
     * @return the causale
     */
    public String getCausale () {
        return causale;
    }

    /**
     * @param causale the causale to set
     */
    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    /**
     * @return the dataMovimento
     */
    public Date getDataMovimento () {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento ( Date dataMovimento ) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the annoEsercizio
     */
    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    /**
     * @param annoEsercizio the annoEsercizio to set
     */
    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    /**
     * @return the dataFine
     */
    public Date getDataFine () {
        return dataFine;
    }

    /**
     * @param dataFine the dataFine to set
     */
    public void setDataFine ( Date dataFine ) {
        this.dataFine = dataFine;
    }

    /**
     * @return the idEnte
     */
    public String getIdEnte () {
        return idEnte;
    }

    /**
     * @param idEnte the idEnte to set
     */
    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    /**
     * @return the importoProvvisorio
     */
    public BigDecimal getImportoProvvisorio () {
        return importoProvvisorio;
    }

    /**
     * @param importoProvvisorio the importoProvvisorio to set
     */
    public void setImportoProvvisorio ( BigDecimal importoProvvisorio ) {
        this.importoProvvisorio = importoProvvisorio;
    }

    /**
     * @return the numeroProvvisorio
     */
    public Integer getNumeroProvvisorio () {
        return numeroProvvisorio;
    }

    /**
     * @param numeroProvvisorio the numeroProvvisorio to set
     */
    public void setNumeroProvvisorio ( Integer numeroProvvisorio ) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        ProvvisorioVO other = (ProvvisorioVO) obj;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        return true;
    }

    /**
     * Creates builder to build {@link ProvvisorioVO}.
     * 
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link ProvvisorioVO}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long id;

        private String identificativoFlusso;

        private String descrizione;

        private String stato;

        private Integer annoProvvisorio;

        private String causale;

        private Date dataMovimento;

        private Integer annoEsercizio;

        private Date dataFine;

        private String idEnte;

        private BigDecimal importoProvvisorio;

        private Integer numeroProvvisorio;

        private Builder () {
        }

        public Builder withId ( Long id ) {
            this.id = id;
            return this;
        }

        public Builder withIdentificativoFlusso ( String identificativoFlusso ) {
            this.identificativoFlusso = identificativoFlusso;
            return this;
        }

        public Builder withDescrizione ( String descrizione ) {
            this.descrizione = descrizione;
            return this;
        }

        public Builder withStato ( String stato ) {
            this.stato = stato;
            return this;
        }

        public Builder withAnnoProvvisorio ( Integer annoProvvisorio ) {
            this.annoProvvisorio = annoProvvisorio;
            return this;
        }

        public Builder withCausale ( String causale ) {
            this.causale = causale;
            return this;
        }

        public Builder withDataMovimento ( Date dataMovimento ) {
            this.dataMovimento = dataMovimento;
            return this;
        }

        public Builder withAnnoEsercizio ( Integer annoEsercizio ) {
            this.annoEsercizio = annoEsercizio;
            return this;
        }

        public Builder withDataFine ( Date dataFine ) {
            this.dataFine = dataFine;
            return this;
        }

        public Builder withIdEnte ( String idEnte ) {
            this.idEnte = idEnte;
            return this;
        }

        public Builder withImportoProvvisorio ( BigDecimal importoProvvisorio ) {
            this.importoProvvisorio = importoProvvisorio;
            return this;
        }

        public Builder withNumeroProvvisorio ( Integer numeroProvvisorio ) {
            this.numeroProvvisorio = numeroProvvisorio;
            return this;
        }

        public ProvvisorioVO build () {
            return new ProvvisorioVO ( this );
        }
    }
}
