/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for "EpayTChiamataEsternaNonValida" entity stored in table "epay_t_chiamata_esterna_non_valida" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_chiamata_esterna_non_valida", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTChiamataEsternaNonValida.countAll",  query="SELECT COUNT(x) FROM EpayTChiamataEsternaNonValida x" ),
  @NamedQuery ( name="EpayTChiamataEsternaNonValida.countById", query="SELECT COUNT(x) FROM EpayTChiamataEsternaNonValida x WHERE x.idChiamata = ?1 " )
} )
public class EpayTChiamataEsternaNonValida implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTChiamataEsternaNonValida reference ( Long idChiamata ) {
        EpayTChiamataEsternaNonValida reference = new EpayTChiamataEsternaNonValida ();
        reference.setIdChiamata( idChiamata );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_chiamata", nullable=false)
    private Long idChiamata ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="codice_chiamante", nullable=false, length=100)
    private String codiceChiamante ;

    @Column(name="digest", length=100)
    private String digest ;

    @Column(name="iuv", length=35)
    private String iuv ;

    @Column(name="codice_fiscale", length=35)
    private String codiceFiscale ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="timestamp_chiamata", nullable=false)
    private Date timestampChiamata ;

    @Column(name="remote_host", length=500)
    private String remoteHost ;

    @Column(name="descrizione_errore", length=500)
    private String descrizioneErrore ;

    @Column(name="identificativo_pagamento", length=50)
    private String identificativoPagamento ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTChiamataEsternaNonValida() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdChiamata();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdChiamata( Long idChiamata ) {
        this.idChiamata = idChiamata ;
    }
    public Long getIdChiamata() {
        return this.idChiamata;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : codice_chiamante ( varchar ) 
    public void setCodiceChiamante( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }
    public String getCodiceChiamante() {
        return this.codiceChiamante;
    }

    //--- DATABASE MAPPING : digest ( varchar ) 
    public void setDigest( String digest ) {
        this.digest = digest;
    }
    public String getDigest() {
        return this.digest;
    }

    //--- DATABASE MAPPING : iuv ( varchar ) 
    public void setIuv( String iuv ) {
        this.iuv = iuv;
    }
    public String getIuv() {
        return this.iuv;
    }

    //--- DATABASE MAPPING : codice_fiscale ( varchar ) 
    public void setCodiceFiscale( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    //--- DATABASE MAPPING : timestamp_chiamata ( timestamp ) 
    public void setTimestampChiamata( Date timestampChiamata ) {
        this.timestampChiamata = timestampChiamata;
    }
    public Date getTimestampChiamata() {
        return this.timestampChiamata;
    }

    //--- DATABASE MAPPING : remote_host ( varchar ) 
    public void setRemoteHost( String remoteHost ) {
        this.remoteHost = remoteHost;
    }
    public String getRemoteHost() {
        return this.remoteHost;
    }

    //--- DATABASE MAPPING : descrizione_errore ( varchar ) 
    public void setDescrizioneErrore( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }
    public String getDescrizioneErrore() {
        return this.descrizioneErrore;
    }

    //--- DATABASE MAPPING : identificativo_pagamento ( varchar ) 
    public void setIdentificativoPagamento( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }
    public String getIdentificativoPagamento() {
        return this.identificativoPagamento;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idChiamata);
        sb.append("]:"); 
        sb.append(codiceChiamante);
        sb.append("|");
        sb.append(digest);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(codiceFiscale);
        sb.append("|");
        sb.append(timestampChiamata);
        sb.append("|");
        sb.append(remoteHost);
        sb.append("|");
        sb.append(descrizioneErrore);
        sb.append("|");
        sb.append(identificativoPagamento);
        return sb.toString(); 
    } 

}
