/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;


import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Persistent class for "EpayTDatiSingoliVersamenti" entity stored in table "epay_t_dati_singoli_versamenti" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_dati_singoli_versamenti", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTDatiSingoliVersamenti.countAll",  query="SELECT COUNT(x) FROM EpayTDatiSingoliVersamenti x" ),
  @NamedQuery ( name="EpayTDatiSingoliVersamenti.countById", query="SELECT COUNT(x) FROM EpayTDatiSingoliVersamenti x WHERE x.id = ?1 " )
} )
public class EpayTDatiSingoliVersamenti implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTDatiSingoliVersamenti reference ( Long id ) {
        EpayTDatiSingoliVersamenti reference = new EpayTDatiSingoliVersamenti ();
        reference.setId( id );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="importo", nullable=false)
    private BigDecimal importo ;

    @Column(name="descrizione_causale", length=100)
    private String descrizioneCausale ;

    @Column(name="dati_specifici_riscossione", nullable=false, length=140)
    private String datiSpecificiRiscossione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_pagamento", referencedColumnName="id_pagamento")
	private EpayTPagamento epayTPagamento ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTDatiSingoliVersamenti() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getId();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : importo ( numeric ) 
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    public BigDecimal getImporto() {
        return this.importo;
    }

    //--- DATABASE MAPPING : descrizione_causale ( varchar ) 
    public void setDescrizioneCausale( String descrizioneCausale ) {
        this.descrizioneCausale = descrizioneCausale;
    }
    public String getDescrizioneCausale() {
        return this.descrizioneCausale;
    }

    //--- DATABASE MAPPING : dati_specifici_riscossione ( varchar ) 
    public void setDatiSpecificiRiscossione( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }
    public String getDatiSpecificiRiscossione() {
        return this.datiSpecificiRiscossione;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTPagamento( EpayTPagamento epayTPagamento ) {
        this.epayTPagamento = epayTPagamento;
    }
    public EpayTPagamento getEpayTPagamento() {
        return this.epayTPagamento;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(importo);
        sb.append("|");
        sb.append(descrizioneCausale);
        sb.append("|");
        sb.append(datiSpecificiRiscossione);
        return sb.toString(); 
    } 

}
