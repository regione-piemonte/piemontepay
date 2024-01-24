/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.annotation.Generated;


/**
 * The persistent class for the gde database table.
 * 
 */
@Entity
@NamedQuery(name="Gde.findAll", query="SELECT g FROM Gde g")
public class Gde implements Serializable {

	/**
     *
     */
    private static final long serialVersionUID = -3930802873592840996L;

    @Id
	@SequenceGenerator(name="GDE_ID_GENERATOR", sequenceName="gde_keyid_seq", allocationSize = 1) //nextval('gde_keyid_seq'::regclass)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GDE_ID_GENERATOR")
	private Integer id;

	@Column(name="application_id")
	private String applicationId;

	private String canalepagamento;

	private String categoriaevento;

	@Column(name="codice_contesto")
	private String codiceContesto;

	private String componente;

	@Temporal ( TemporalType.TIMESTAMP )
	private Date dataoraevento;

	private String esito;

	@Column(name="id_int_psp")
	private String idIntPsp;

	@Column(name="id_psp")
	private String idPsp;

	private String identificativodominio;

	private String identificativoerogatore;

	private String identificativofruitore;

	private String identificativostazioneintermediariopa;

	@Column(name="insert_date")
	@Temporal ( TemporalType.TIMESTAMP )
	private Date insertDate;

	private String iuv;

	@Column(name="last_update")
	@Temporal ( TemporalType.TIMESTAMP )
	private Date lastUpdate;

	private String parametrispecificiinterfaccia;

	private String sottotipoevento;

	private String tipoevento;

	private String tipoversamento;

	private String transactionid;


    @Generated ( "SparkTools" )
    private Gde ( Builder builder ) {
        this.id = builder.id;
        this.applicationId = builder.applicationId;
        this.canalepagamento = builder.canalepagamento;
        this.categoriaevento = builder.categoriaevento;
        this.codiceContesto = builder.codiceContesto;
        this.componente = builder.componente;
        this.dataoraevento = builder.dataoraevento;
        this.esito = builder.esito;
        this.idIntPsp = builder.idIntPsp;
        this.idPsp = builder.idPsp;
        this.identificativodominio = builder.identificativodominio;
        this.identificativoerogatore = builder.identificativoerogatore;
        this.identificativofruitore = builder.identificativofruitore;
        this.identificativostazioneintermediariopa = builder.identificativostazioneintermediariopa;
        this.insertDate = builder.insertDate;
        this.iuv = builder.iuv;
        this.lastUpdate = builder.lastUpdate;
        this.parametrispecificiinterfaccia = builder.parametrispecificiinterfaccia;
        this.sottotipoevento = builder.sottotipoevento;
        this.tipoevento = builder.tipoevento;
        this.tipoversamento = builder.tipoversamento;
        this.transactionid = builder.transactionid;
    }

    
    public Gde () {
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
     * @return the applicationId
     */
    public String getApplicationId () {
        return applicationId;
    }

    
    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    
    /**
     * @return the canalepagamento
     */
    public String getCanalepagamento () {
        return canalepagamento;
    }

    
    /**
     * @param canalepagamento the canalepagamento to set
     */
    public void setCanalepagamento ( String canalepagamento ) {
        this.canalepagamento = canalepagamento;
    }

    
    /**
     * @return the categoriaevento
     */
    public String getCategoriaevento () {
        return categoriaevento;
    }

    
    /**
     * @param categoriaevento the categoriaevento to set
     */
    public void setCategoriaevento ( String categoriaevento ) {
        this.categoriaevento = categoriaevento;
    }

    
    /**
     * @return the codiceContesto
     */
    public String getCodiceContesto () {
        return codiceContesto;
    }

    
    /**
     * @param codiceContesto the codiceContesto to set
     */
    public void setCodiceContesto ( String codiceContesto ) {
        this.codiceContesto = codiceContesto;
    }

    
    /**
     * @return the componente
     */
    public String getComponente () {
        return componente;
    }

    
    /**
     * @param componente the componente to set
     */
    public void setComponente ( String componente ) {
        this.componente = componente;
    }

    
    /**
     * @return the dataoraevento
     */
    public Date getDataoraevento () {
        return dataoraevento;
    }

    
    /**
     * @param dataoraevento the dataoraevento to set
     */
    public void setDataoraevento ( Date dataoraevento ) {
        this.dataoraevento = dataoraevento;
    }

    
    /**
     * @return the esito
     */
    public String getEsito () {
        return esito;
    }

    
    /**
     * @param esito the esito to set
     */
    public void setEsito ( String esito ) {
        this.esito = esito;
    }

    
    /**
     * @return the idIntPsp
     */
    public String getIdIntPsp () {
        return idIntPsp;
    }

    
    /**
     * @param idIntPsp the idIntPsp to set
     */
    public void setIdIntPsp ( String idIntPsp ) {
        this.idIntPsp = idIntPsp;
    }

    
    /**
     * @return the idPsp
     */
    public String getIdPsp () {
        return idPsp;
    }

    
    /**
     * @param idPsp the idPsp to set
     */
    public void setIdPsp ( String idPsp ) {
        this.idPsp = idPsp;
    }

    
    /**
     * @return the identificativodominio
     */
    public String getIdentificativodominio () {
        return identificativodominio;
    }

    
    /**
     * @param identificativodominio the identificativodominio to set
     */
    public void setIdentificativodominio ( String identificativodominio ) {
        this.identificativodominio = identificativodominio;
    }

    
    /**
     * @return the identificativoerogatore
     */
    public String getIdentificativoerogatore () {
        return identificativoerogatore;
    }

    
    /**
     * @param identificativoerogatore the identificativoerogatore to set
     */
    public void setIdentificativoerogatore ( String identificativoerogatore ) {
        this.identificativoerogatore = identificativoerogatore;
    }

    
    /**
     * @return the identificativofruitore
     */
    public String getIdentificativofruitore () {
        return identificativofruitore;
    }

    
    /**
     * @param identificativofruitore the identificativofruitore to set
     */
    public void setIdentificativofruitore ( String identificativofruitore ) {
        this.identificativofruitore = identificativofruitore;
    }

    
    /**
     * @return the identificativostazioneintermediariopa
     */
    public String getIdentificativostazioneintermediariopa () {
        return identificativostazioneintermediariopa;
    }

    
    /**
     * @param identificativostazioneintermediariopa the identificativostazioneintermediariopa to set
     */
    public void setIdentificativostazioneintermediariopa ( String identificativostazioneintermediariopa ) {
        this.identificativostazioneintermediariopa = identificativostazioneintermediariopa;
    }

    
    /**
     * @return the insertDate
     */
    public Date getInsertDate () {
        return insertDate;
    }

    
    /**
     * @param insertDate the insertDate to set
     */
    public void setInsertDate ( Date insertDate ) {
        this.insertDate = insertDate;
    }

    
    /**
     * @return the iuv
     */
    public String getIuv () {
        return iuv;
    }

    
    /**
     * @param iuv the iuv to set
     */
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    
    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate () {
        return lastUpdate;
    }

    
    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate ( Date lastUpdate ) {
        this.lastUpdate = lastUpdate;
    }

    
    /**
     * @return the parametrispecificiinterfaccia
     */
    public String getParametrispecificiinterfaccia () {
        return parametrispecificiinterfaccia;
    }

    
    /**
     * @param parametrispecificiinterfaccia the parametrispecificiinterfaccia to set
     */
    public void setParametrispecificiinterfaccia ( String parametrispecificiinterfaccia ) {
        this.parametrispecificiinterfaccia = parametrispecificiinterfaccia;
    }

    
    /**
     * @return the sottotipoevento
     */
    public String getSottotipoevento () {
        return sottotipoevento;
    }

    
    /**
     * @param sottotipoevento the sottotipoevento to set
     */
    public void setSottotipoevento ( String sottotipoevento ) {
        this.sottotipoevento = sottotipoevento;
    }

    
    /**
     * @return the tipoevento
     */
    public String getTipoevento () {
        return tipoevento;
    }

    
    /**
     * @param tipoevento the tipoevento to set
     */
    public void setTipoevento ( String tipoevento ) {
        this.tipoevento = tipoevento;
    }

    
    /**
     * @return the tipoversamento
     */
    public String getTipoversamento () {
        return tipoversamento;
    }

    
    /**
     * @param tipoversamento the tipoversamento to set
     */
    public void setTipoversamento ( String tipoversamento ) {
        this.tipoversamento = tipoversamento;
    }

    
    /**
     * @return the transactionid
     */
    public String getTransactionid () {
        return transactionid;
    }

    
    /**
     * @param transactionid the transactionid to set
     */
    public void setTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
    }


    /**
     * Creates builder to build {@link Gde}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }


    /**
     * Builder to build {@link Gde}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Integer id;

        private String applicationId;

        private String canalepagamento;

        private String categoriaevento;

        private String codiceContesto;

        private String componente;

        private Date dataoraevento;

        private String esito;

        private String idIntPsp;

        private String idPsp;

        private String identificativodominio;

        private String identificativoerogatore;

        private String identificativofruitore;

        private String identificativostazioneintermediariopa;

        private Date insertDate;

        private String iuv;

        private Date lastUpdate;

        private String parametrispecificiinterfaccia;

        private String sottotipoevento;

        private String tipoevento;

        private String tipoversamento;

        private String transactionid;

        private Builder () {
        }

        public Builder withId ( Integer id ) {
            this.id = id;
            return this;
        }

        public Builder withApplicationId ( String applicationId ) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder withCanalepagamento ( String canalepagamento ) {
            this.canalepagamento = canalepagamento;
            return this;
        }

        public Builder withCategoriaevento ( String categoriaevento ) {
            this.categoriaevento = categoriaevento;
            return this;
        }

        public Builder withCodiceContesto ( String codiceContesto ) {
            this.codiceContesto = codiceContesto;
            return this;
        }

        public Builder withComponente ( String componente ) {
            this.componente = componente;
            return this;
        }

        public Builder withDataoraevento ( Date dataoraevento ) {
            this.dataoraevento = dataoraevento;
            return this;
        }

        public Builder withEsito ( String esito ) {
            this.esito = esito;
            return this;
        }

        public Builder withIdIntPsp ( String idIntPsp ) {
            this.idIntPsp = idIntPsp;
            return this;
        }

        public Builder withIdPsp ( String idPsp ) {
            this.idPsp = idPsp;
            return this;
        }

        public Builder withIdentificativodominio ( String identificativodominio ) {
            this.identificativodominio = identificativodominio;
            return this;
        }

        public Builder withIdentificativoerogatore ( String identificativoerogatore ) {
            this.identificativoerogatore = identificativoerogatore;
            return this;
        }

        public Builder withIdentificativofruitore ( String identificativofruitore ) {
            this.identificativofruitore = identificativofruitore;
            return this;
        }

        public Builder withIdentificativostazioneintermediariopa ( String identificativostazioneintermediariopa ) {
            this.identificativostazioneintermediariopa = identificativostazioneintermediariopa;
            return this;
        }

        public Builder withInsertDate ( Date insertDate ) {
            this.insertDate = insertDate;
            return this;
        }

        public Builder withIuv ( String iuv ) {
            this.iuv = iuv;
            return this;
        }

        public Builder withLastUpdate ( Date lastUpdate ) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public Builder withParametrispecificiinterfaccia ( String parametrispecificiinterfaccia ) {
            this.parametrispecificiinterfaccia = parametrispecificiinterfaccia;
            return this;
        }

        public Builder withSottotipoevento ( String sottotipoevento ) {
            this.sottotipoevento = sottotipoevento;
            return this;
        }

        public Builder withTipoevento ( String tipoevento ) {
            this.tipoevento = tipoevento;
            return this;
        }

        public Builder withTipoversamento ( String tipoversamento ) {
            this.tipoversamento = tipoversamento;
            return this;
        }

        public Builder withTransactionid ( String transactionid ) {
            this.transactionid = transactionid;
            return this;
        }

        public Gde build () {
            return new Gde ( this );
        }
    }

 
}
