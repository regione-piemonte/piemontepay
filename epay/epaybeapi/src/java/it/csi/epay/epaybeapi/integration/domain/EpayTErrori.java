/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for "EpayTErrori" entity stored in table "epay_t_errori" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_errori", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTErrori.countAll",  query="SELECT COUNT(x) FROM EpayTErrori x" ),
  @NamedQuery ( name="EpayTErrori.countById", query="SELECT COUNT(x) FROM EpayTErrori x WHERE x.id = ?1 " )
} )
public class EpayTErrori implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTErrori reference ( Long id ) {
        EpayTErrori reference = new EpayTErrori ();
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
    @Column(name="data", nullable=false)
    private Date data ;

    @Column(name="descrizione", nullable=false, length=500)
    private String descrizione ;

    @Column(name="id_pagamento")
    private Long idPagamento ;

    @Column(name="id_registro_versamento")
    private Long idRegistroVersamento ;

    @Column(name="iuv", length=25)
    private String iuv ;

    @Column(name="id_transazione")
    private Long idTransazione ;

    @Column(name="desc_correzione", length=500)
    private String descCorrezione ;

    @Column(name="applicativo", nullable=false, length=200)
    private String applicativo ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_stato_errore", referencedColumnName="id_stato")
	private EpayDStatoErrore epayDStatoErrore ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTErrori() {
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
    //--- DATABASE MAPPING : data ( timestamp ) 
    public void setData( Date data ) {
        this.data = data;
    }
    public Date getData() {
        return this.data;
    }

    //--- DATABASE MAPPING : descrizione ( varchar ) 
    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }
    public String getDescrizione() {
        return this.descrizione;
    }

    //--- DATABASE MAPPING : id_pagamento ( int8 ) 
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    //--- DATABASE MAPPING : id_registro_versamento ( int8 ) 
    public void setIdRegistroVersamento( Long idRegistroVersamento ) {
        this.idRegistroVersamento = idRegistroVersamento;
    }
    public Long getIdRegistroVersamento() {
        return this.idRegistroVersamento;
    }

    //--- DATABASE MAPPING : iuv ( varchar ) 
    public void setIuv( String iuv ) {
        this.iuv = iuv;
    }
    public String getIuv() {
        return this.iuv;
    }

    //--- DATABASE MAPPING : id_transazione ( int8 ) 
    public void setIdTransazione( Long idTransazione ) {
        this.idTransazione = idTransazione;
    }
    public Long getIdTransazione() {
        return this.idTransazione;
    }

    //--- DATABASE MAPPING : desc_correzione ( varchar ) 
    public void setDescCorrezione( String descCorrezione ) {
        this.descCorrezione = descCorrezione;
    }
    public String getDescCorrezione() {
        return this.descCorrezione;
    }

    //--- DATABASE MAPPING : applicativo ( varchar ) 
    public void setApplicativo( String applicativo ) {
        this.applicativo = applicativo;
    }
    public String getApplicativo() {
        return this.applicativo;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayDStatoErrore( EpayDStatoErrore epayDStatoErrore ) {
        this.epayDStatoErrore = epayDStatoErrore;
    }
    public EpayDStatoErrore getEpayDStatoErrore() {
        return this.epayDStatoErrore;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(data);
        sb.append("|");
        sb.append(descrizione);
        sb.append("|");
        sb.append(idPagamento);
        sb.append("|");
        sb.append(idRegistroVersamento);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(idTransazione);
        sb.append("|");
        sb.append(descCorrezione);
        sb.append("|");
        sb.append(applicativo);
        return sb.toString(); 
    } 

}
