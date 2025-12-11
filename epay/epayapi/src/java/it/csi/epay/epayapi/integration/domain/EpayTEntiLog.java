/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;
/**
 * Persistent class for "EpayTEntiLog" entity stored in table "epay_t_enti_log" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_enti_log", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTEntiLog.countAll",  query="SELECT COUNT(x) FROM EpayTEntiLog x" ),
  @NamedQuery ( name="EpayTEntiLog.countById", query="SELECT COUNT(x) FROM EpayTEntiLog x WHERE x.seq = ?1 " )
} )
public class EpayTEntiLog implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTEntiLog reference ( Long seq ) {
        EpayTEntiLog reference = new EpayTEntiLog ();
        reference.setSeq( seq );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="seq", nullable=false)
    private Long seq ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nome", nullable=false, length=250)
    private String nome ;

    @Column(name="codice_fiscale", nullable=false, length=16)
    private String codiceFiscale ;

    @Lob
    @Column(name="logo", nullable=false)
    @Type ( type = "org.hibernate.type.BinaryType" )
    private byte[] logo ;

    @Column(name="codice_gs1_gln", nullable=false)
    private BigDecimal codiceGs1Gln ;

    @Column(name="orari", length=2000)
    private String orari ;

    @Column(name="flag_invio_pagamenti", nullable=false)
    private Boolean flagInvioPagamenti ;

    @Column(name="codice_interbancario", length=5)
    private String codiceInterbancario ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_trigger")
    private Date dataTrigger ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_ente", referencedColumnName="id_ente")
	private EpayTEnti epayTEnti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTEntiLog() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getSeq();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setSeq( Long seq ) {
        this.seq = seq ;
    }
    public Long getSeq() {
        return this.seq;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : nome ( varchar ) 
    public void setNome( String nome ) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    //--- DATABASE MAPPING : codice_fiscale ( varchar ) 
    public void setCodiceFiscale( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    //--- DATABASE MAPPING : logo ( bytea ) 
    public void setLogo( byte[] logo ) {
        this.logo = logo;
    }
    public byte[] getLogo() {
        return this.logo;
    }

    //--- DATABASE MAPPING : codice_gs1_gln ( numeric ) 
    public void setCodiceGs1Gln( BigDecimal codiceGs1Gln ) {
        this.codiceGs1Gln = codiceGs1Gln;
    }
    public BigDecimal getCodiceGs1Gln() {
        return this.codiceGs1Gln;
    }

    //--- DATABASE MAPPING : orari ( varchar ) 
    public void setOrari( String orari ) {
        this.orari = orari;
    }
    public String getOrari() {
        return this.orari;
    }

    //--- DATABASE MAPPING : flag_invio_pagamenti ( bool ) 
    public void setFlagInvioPagamenti( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }
    public Boolean getFlagInvioPagamenti() {
        return this.flagInvioPagamenti;
    }

    //--- DATABASE MAPPING : codice_interbancario ( varchar ) 
    public void setCodiceInterbancario( String codiceInterbancario ) {
        this.codiceInterbancario = codiceInterbancario;
    }
    public String getCodiceInterbancario() {
        return this.codiceInterbancario;
    }

    //--- DATABASE MAPPING : data_trigger ( timestamptz ) 
    public void setDataTrigger( Date dataTrigger ) {
        this.dataTrigger = dataTrigger;
    }
    public Date getDataTrigger() {
        return this.dataTrigger;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTEnti( EpayTEnti epayTEnti ) {
        this.epayTEnti = epayTEnti;
    }
    public EpayTEnti getEpayTEnti() {
        return this.epayTEnti;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(seq);
        sb.append("]:"); 
        sb.append(nome);
        sb.append("|");
        sb.append(codiceFiscale);
        // attribute 'logo' not usable (type = byte[])
        sb.append("|");
        sb.append(codiceGs1Gln);
        sb.append("|");
        sb.append(orari);
        sb.append("|");
        sb.append(flagInvioPagamenti);
        sb.append("|");
        sb.append(codiceInterbancario);
        sb.append("|");
        sb.append(dataTrigger);
        return sb.toString(); 
    } 

}
