/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Persistent class for "EpayTPagamentoComponenti" entity stored in table "epay_t_pagamento_componenti" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_pagamento_componenti", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTPagamentoComponenti.countAll",  query="SELECT COUNT(x) FROM EpayTPagamentoComponenti x" ),
  @NamedQuery ( name="EpayTPagamentoComponenti.countById", query="SELECT COUNT(x) FROM EpayTPagamentoComponenti x WHERE x.idComponente = ?1 " )
} )
public class EpayTPagamentoComponenti implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTPagamentoComponenti reference ( Long idComponente ) {
        EpayTPagamentoComponenti reference = new EpayTPagamentoComponenti ();
        reference.setIdComponente( idComponente );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_componente", nullable=false)
    private Long idComponente ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="progressivo", nullable=false)
    private Integer progressivo ;

    @Column(name="importo", nullable=false)
    private BigDecimal importo ;

    @Column(name="causale", nullable=false, length=140)
    private String causale ;

    @Column(name="dati_specifici_riscossione", nullable=false, length=140)
    private String datiSpecificiRiscossione ;

    @Column(name="utente_ultima_modifica", nullable=false, length=100)
    private String utenteUltimaModifica ;

    @Column(name="anno_accertamento")
    private Long annoAccertamento ;

    @Column(name="numero_accertamento", length=35)
    private String numeroAccertamento ;
    
    
    @Column( name = "codice_tributo")
    private String codiceTributo;
    


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_pagamento", referencedColumnName="id_pagamento")
	private EpayTPagamento epayTPagamento ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTPagamentoComponenti() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdComponente();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdComponente( Long idComponente ) {
        this.idComponente = idComponente ;
    }
    public Long getIdComponente() {
        return this.idComponente;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
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

    //--- DATABASE MAPPING : anno_accertamento ( int8 ) 
    public void setAnnoAccertamento( Long annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }
    public Long getAnnoAccertamento() {
        return this.annoAccertamento;
    }

    //--- DATABASE MAPPING : numero_accertamento ( varchar ) 
    public void setNumeroAccertamento( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }
    public String getNumeroAccertamento() {
        return this.numeroAccertamento;
    }


    
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
        sb.append(idComponente);
        sb.append("]:"); 
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
        sb.append(annoAccertamento);
        sb.append("|");
        sb.append(numeroAccertamento);
        sb.append("|");
        sb.append(codiceTributo);
        return sb.toString(); 
    } 

}
