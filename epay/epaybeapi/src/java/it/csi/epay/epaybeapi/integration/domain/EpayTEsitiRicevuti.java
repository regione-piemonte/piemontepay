/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for "EpayTEsitiRicevuti" entity stored in table "epay_t_esiti_ricevuti" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_esiti_ricevuti", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTEsitiRicevuti.countAll",  query="SELECT COUNT(x) FROM EpayTEsitiRicevuti x" ),
  @NamedQuery ( name="EpayTEsitiRicevuti.countById", query="SELECT COUNT(x) FROM EpayTEsitiRicevuti x WHERE x.idEsito = ?1 " )
} )
public class EpayTEsitiRicevuti implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTEsitiRicevuti reference ( Long idEsito ) {
        EpayTEsitiRicevuti reference = new EpayTEsitiRicevuti ();
        reference.setIdEsito( idEsito );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_esito", nullable=false)
    private Long idEsito ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="id_modalita_ricezione", nullable=false)
    private Integer idModalitaRicezione ;

    @Column(name="id_applicazione", length=30)
    private String idApplicazione ;

    @Column(name="id_transazione", length=50)
    private String idTransazione ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_pagamento")
    private Date dataPagamento ;

    @Column(name="cod_esito_pagamento")
    private Integer codEsitoPagamento ;

    @Column(name="desc_esito_pagamento", length=255)
    private String descEsitoPagamento ;

    @Column(name="importo")
    private BigDecimal importo ;

    @Column(name="identificativo_psp", length=250)
    private String identificativoPsp ;

    @Column(name="ragione_sociale_psp", length=250)
    private String ragioneSocialePsp ;

    @Column(name="iur", length=35)
    private String iur ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_modalita_pagamento", referencedColumnName="id_modalita_pagamento")
	private EpayDModalitaPagamento epayDModalitaPagamento ;
    
    @ManyToOne
    @JoinColumn(name="id_registro", referencedColumnName="id_registro")
	private EpayTRegistroVersamenti epayTRegistroVersamenti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTEsitiRicevuti() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdEsito();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdEsito( Long idEsito ) {
        this.idEsito = idEsito ;
    }
    public Long getIdEsito() {
        return this.idEsito;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : id_modalita_ricezione ( int4 ) 
    public void setIdModalitaRicezione( Integer idModalitaRicezione ) {
        this.idModalitaRicezione = idModalitaRicezione;
    }
    public Integer getIdModalitaRicezione() {
        return this.idModalitaRicezione;
    }

    //--- DATABASE MAPPING : id_applicazione ( varchar ) 
    public void setIdApplicazione( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
    }
    public String getIdApplicazione() {
        return this.idApplicazione;
    }

    //--- DATABASE MAPPING : id_transazione ( varchar ) 
    public void setIdTransazione( String idTransazione ) {
        this.idTransazione = idTransazione;
    }
    public String getIdTransazione() {
        return this.idTransazione;
    }

    //--- DATABASE MAPPING : data_pagamento ( timestamp ) 
    public void setDataPagamento( Date dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }
    public Date getDataPagamento() {
        return this.dataPagamento;
    }

    //--- DATABASE MAPPING : cod_esito_pagamento ( int4 ) 
    public void setCodEsitoPagamento( Integer codEsitoPagamento ) {
        this.codEsitoPagamento = codEsitoPagamento;
    }
    public Integer getCodEsitoPagamento() {
        return this.codEsitoPagamento;
    }

    //--- DATABASE MAPPING : desc_esito_pagamento ( varchar ) 
    public void setDescEsitoPagamento( String descEsitoPagamento ) {
        this.descEsitoPagamento = descEsitoPagamento;
    }
    public String getDescEsitoPagamento() {
        return this.descEsitoPagamento;
    }

    //--- DATABASE MAPPING : importo ( numeric ) 
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    public BigDecimal getImporto() {
        return this.importo;
    }

    //--- DATABASE MAPPING : identificativo_psp ( varchar ) 
    public void setIdentificativoPsp( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }
    public String getIdentificativoPsp() {
        return this.identificativoPsp;
    }

    //--- DATABASE MAPPING : ragione_sociale_psp ( varchar ) 
    public void setRagioneSocialePsp( String ragioneSocialePsp ) {
        this.ragioneSocialePsp = ragioneSocialePsp;
    }
    public String getRagioneSocialePsp() {
        return this.ragioneSocialePsp;
    }

    //--- DATABASE MAPPING : iur ( varchar ) 
    public void setIur( String iur ) {
        this.iur = iur;
    }
    public String getIur() {
        return this.iur;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayDModalitaPagamento( EpayDModalitaPagamento epayDModalitaPagamento ) {
        this.epayDModalitaPagamento = epayDModalitaPagamento;
    }
    public EpayDModalitaPagamento getEpayDModalitaPagamento() {
        return this.epayDModalitaPagamento;
    }
    
    public void setEpayTRegistroVersamenti( EpayTRegistroVersamenti epayTRegistroVersamenti ) {
        this.epayTRegistroVersamenti = epayTRegistroVersamenti;
    }
    public EpayTRegistroVersamenti getEpayTRegistroVersamenti() {
        return this.epayTRegistroVersamenti;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idEsito);
        sb.append("]:"); 
        sb.append(idModalitaRicezione);
        sb.append("|");
        sb.append(idApplicazione);
        sb.append("|");
        sb.append(idTransazione);
        sb.append("|");
        sb.append(dataPagamento);
        sb.append("|");
        sb.append(codEsitoPagamento);
        sb.append("|");
        sb.append(descEsitoPagamento);
        sb.append("|");
        sb.append(importo);
        sb.append("|");
        sb.append(identificativoPsp);
        sb.append("|");
        sb.append(ragioneSocialePsp);
        sb.append("|");
        sb.append(iur);
        return sb.toString(); 
    } 

}
