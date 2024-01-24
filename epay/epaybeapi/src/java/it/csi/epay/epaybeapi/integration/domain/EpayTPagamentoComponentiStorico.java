/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for "EpayTPagamentoComponentiStorico" entity stored in table "epay_t_pagamento_componenti_storico" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_pagamento_componenti_storico", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTPagamentoComponentiStorico.countAll",  query="SELECT COUNT(x) FROM EpayTPagamentoComponentiStorico x" ),
  @NamedQuery ( name="EpayTPagamentoComponentiStorico.countById", query="SELECT COUNT(x) FROM EpayTPagamentoComponentiStorico x WHERE x.id = ?1 " )
} )
public class EpayTPagamentoComponentiStorico implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTPagamentoComponentiStorico reference ( Long id ) {
        EpayTPagamentoComponentiStorico reference = new EpayTPagamentoComponentiStorico ();
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_storicizzazione", nullable=false)
    private Date dataStoricizzazione ;

    @Column(name="id_componente")
    private Long idComponente ;

    @Column(name="id_pagamento")
    private Long idPagamento ;

    @Column(name="progressivo")
    private Integer progressivo ;

    @Column(name="importo")
    private BigDecimal importo ;

    @Column(name="causale", length=140)
    private String causale ;

    @Column(name="dati_specifici_riscossione", length=140)
    private String datiSpecificiRiscossione ;

    @Column(name="utente_ultima_modifica", nullable=false, length=100)
    private String utenteUltimaModifica ;
    
    
    @Column( name = "codice_tributo")
    private String codiceTributo;
    


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTPagamentoComponentiStorico() {
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
    //--- DATABASE MAPPING : data_storicizzazione ( timestamp ) 
    public void setDataStoricizzazione( Date dataStoricizzazione ) {
        this.dataStoricizzazione = dataStoricizzazione;
    }
    public Date getDataStoricizzazione() {
        return this.dataStoricizzazione;
    }

    //--- DATABASE MAPPING : id_componente ( int8 ) 
    public void setIdComponente( Long idComponente ) {
        this.idComponente = idComponente;
    }
    public Long getIdComponente() {
        return this.idComponente;
    }

    //--- DATABASE MAPPING : id_pagamento ( int8 ) 
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    //--- DATABASE MAPPING : progressivo ( int4 ) 
    public void setProgressivo( Integer progressivo ) {
        this.progressivo = progressivo;
    }
    public Integer getProgressivo() {
        return this.progressivo;
    }

    //--- DATABASE MAPPING : importo ( numeric ) 
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    public BigDecimal getImporto() {
        return this.importo;
    }

    //--- DATABASE MAPPING : causale ( varchar ) 
    public void setCausale( String causale ) {
        this.causale = causale;
    }
    public String getCausale() {
        return this.causale;
    }

    //--- DATABASE MAPPING : dati_specifici_riscossione ( varchar ) 
    public void setDatiSpecificiRiscossione( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }
    public String getDatiSpecificiRiscossione() {
        return this.datiSpecificiRiscossione;
    }

    //--- DATABASE MAPPING : utente_ultima_modifica ( varchar ) 
    public void setUtenteUltimaModifica( String utenteUltimaModifica ) {
        this.utenteUltimaModifica = utenteUltimaModifica;
    }
    public String getUtenteUltimaModifica() {
        return this.utenteUltimaModifica;
    }

    

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    
    /**
     * @return the codiceTributo
     */
    public String getCodiceTributo () {
        return codiceTributo;
    }

    
    /**
     * @param codiceTributo the codiceTributo to set
     */
    public void setCodiceTributo ( String codiceTributo ) {
        this.codiceTributo = codiceTributo;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(dataStoricizzazione);
        sb.append("|");
        sb.append(idComponente);
        sb.append("|");
        sb.append(idPagamento);
        sb.append("|");
        sb.append(progressivo);
        sb.append("|");
        sb.append(importo);
        sb.append("|");
        sb.append(causale);
        sb.append("|");
        sb.append(datiSpecificiRiscossione);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        sb.append("|");
        sb.append(codiceTributo);
        return sb.toString(); 
    } 

}
