/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;
/**
 * Persistent class for "EpayTRt" entity stored in table "epay_t_rt" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_rt", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTRt.countAll",  query="SELECT COUNT(x) FROM EpayTRt x" ),
  @NamedQuery ( name="EpayTRt.countById", query="SELECT COUNT(x) FROM EpayTRt x WHERE x.idRt = ?1 " )
} )
public class EpayTRt implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTRt reference ( Long idRt ) {
        EpayTRt reference = new EpayTRt ();
        reference.setIdRt( idRt );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_rt", nullable=false)
    private Long idRt ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Lob
    @Column(name="ricevuta_pdf")
    @Type ( type = "org.hibernate.type.BinaryType" )
    private byte[] ricevutaPdf ;

    @Lob
    @Column(name="rt_xml", nullable=false)
    @Type ( type = "org.hibernate.type.BinaryType" )
    private byte[] rtXml ;

    @Column(name="id_applicazione", length=50)
    private String idApplicazione ;

    @Column(name="id_transazione", length=30)
    private String idTransazione ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dataora_msg_ricevuta")
    private Date dataoraMsgRicevuta ;

    @Column(name="id_msg_ricevuta", length=35)
    private String idMsgRicevuta ;

    @Column(name="tipo_firma", length=10)
    private String tipoFirma ;

    @Column(name="iuv", length=35)
    private String iuv ;

    @Column(name="cod_esito_pagamento")
    private Integer codEsitoPagamento ;

    @Column(name="desc_esito_pagamento", length=255)
    private String descEsitoPagamento ;

    @Column(name="id_msg_richiesta", length=35)
    private String idMsgRichiesta ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_registro", referencedColumnName="id_registro")
	private EpayTRegistroVersamenti epayTRegistroVersamenti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTRt() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdRt();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdRt( Long idRt ) {
        this.idRt = idRt ;
    }
    public Long getIdRt() {
        return this.idRt;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : ricevuta_pdf ( bytea ) 
    public void setRicevutaPdf( byte[] ricevutaPdf ) {
        this.ricevutaPdf = ricevutaPdf;
    }
    public byte[] getRicevutaPdf() {
        return this.ricevutaPdf;
    }

    //--- DATABASE MAPPING : rt_xml ( bytea ) 
    public void setRtXml( byte[] rtXml ) {
        this.rtXml = rtXml;
    }
    public byte[] getRtXml() {
        return this.rtXml;
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

    //--- DATABASE MAPPING : dataora_msg_ricevuta ( timestamp ) 
    public void setDataoraMsgRicevuta( Date dataoraMsgRicevuta ) {
        this.dataoraMsgRicevuta = dataoraMsgRicevuta;
    }
    public Date getDataoraMsgRicevuta() {
        return this.dataoraMsgRicevuta;
    }

    //--- DATABASE MAPPING : id_msg_ricevuta ( varchar ) 
    public void setIdMsgRicevuta( String idMsgRicevuta ) {
        this.idMsgRicevuta = idMsgRicevuta;
    }
    public String getIdMsgRicevuta() {
        return this.idMsgRicevuta;
    }

    //--- DATABASE MAPPING : tipo_firma ( varchar ) 
    public void setTipoFirma( String tipoFirma ) {
        this.tipoFirma = tipoFirma;
    }
    public String getTipoFirma() {
        return this.tipoFirma;
    }

    //--- DATABASE MAPPING : iuv ( varchar ) 
    public void setIuv( String iuv ) {
        this.iuv = iuv;
    }
    public String getIuv() {
        return this.iuv;
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

    //--- DATABASE MAPPING : id_msg_richiesta ( varchar ) 
    public void setIdMsgRichiesta( String idMsgRichiesta ) {
        this.idMsgRichiesta = idMsgRichiesta;
    }
    public String getIdMsgRichiesta() {
        return this.idMsgRichiesta;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
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
        sb.append(idRt);
        sb.append("]:"); 
        // attribute 'ricevutaPdf' not usable (type = byte[])
        // attribute 'rtXml' not usable (type = byte[])
        sb.append(idApplicazione);
        sb.append("|");
        sb.append(idTransazione);
        sb.append("|");
        sb.append(dataoraMsgRicevuta);
        sb.append("|");
        sb.append(idMsgRicevuta);
        sb.append("|");
        sb.append(tipoFirma);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(codEsitoPagamento);
        sb.append("|");
        sb.append(descEsitoPagamento);
        sb.append("|");
        sb.append(idMsgRichiesta);
        return sb.toString(); 
    } 

}
