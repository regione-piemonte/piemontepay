/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;



import javax.persistence.*;

import java.util.List;
/**
 * Persistent class for "EpayTTransazioneMdp" entity stored in table "epay_t_transazione_mdp" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_transazione_mdp", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTTransazioneMdp.countAll",  query="SELECT COUNT(x) FROM EpayTTransazioneMdp x" ),
  @NamedQuery ( name="EpayTTransazioneMdp.countById", query="SELECT COUNT(x) FROM EpayTTransazioneMdp x WHERE x.idTransazione = ?1 " )
} )
public class EpayTTransazioneMdp implements IEntity<String> {

    private static final long serialVersionUID = 1L;

	public static EpayTTransazioneMdp reference ( String idTransazione ) {
        EpayTTransazioneMdp reference = new EpayTTransazioneMdp ();
        reference.setIdTransazione( idTransazione );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id_transazione", nullable=false, length=50)
    private String idTransazione ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="iuv", nullable=false, length=25)
    private String iuv ;

    @Column(name="id_gateway", length=50)
    private String idGateway ;

    @Column(name="id_payment_mode", length=50)
    private String idPaymentMode ;

    @Column(name="id_informativa_psp")
    private Integer idInformativaPsp ;

    @Column(name="identificativo_psp", length=250)
    private String identificativoPsp ;

    @Column(name="ragione_sociale_psp", length=250)
    private String ragioneSocialePsp ;

    @Column(name="identificativo_canale_psp", length=250)
    private String identificativoCanalePsp ;

    @Column(name="tipo_versamento_psp", length=250)
    private String tipoVersamentoPsp ;

    @Column(name="modello_pagamento_psp")
    private Integer modelloPagamentoPsp ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @OneToMany(mappedBy="epayTTransazioneMdp", targetEntity=EpayTRegistroVersamenti.class)
	private List<EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTTransazioneMdp() {
		super();
    }

	@Override
	public String getPrimaryKey() {
		return getIdTransazione();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdTransazione( String idTransazione ) {
        this.idTransazione = idTransazione ;
    }
    public String getIdTransazione() {
        return this.idTransazione;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : iuv ( varchar ) 
    public void setIuv( String iuv ) {
        this.iuv = iuv;
    }
    public String getIuv() {
        return this.iuv;
    }

    //--- DATABASE MAPPING : id_gateway ( varchar ) 
    public void setIdGateway( String idGateway ) {
        this.idGateway = idGateway;
    }
    public String getIdGateway() {
        return this.idGateway;
    }

    //--- DATABASE MAPPING : id_payment_mode ( varchar ) 
    public void setIdPaymentMode( String idPaymentMode ) {
        this.idPaymentMode = idPaymentMode;
    }
    public String getIdPaymentMode() {
        return this.idPaymentMode;
    }

    //--- DATABASE MAPPING : id_informativa_psp ( int4 ) 
    public void setIdInformativaPsp( Integer idInformativaPsp ) {
        this.idInformativaPsp = idInformativaPsp;
    }
    public Integer getIdInformativaPsp() {
        return this.idInformativaPsp;
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

    //--- DATABASE MAPPING : identificativo_canale_psp ( varchar ) 
    public void setIdentificativoCanalePsp( String identificativoCanalePsp ) {
        this.identificativoCanalePsp = identificativoCanalePsp;
    }
    public String getIdentificativoCanalePsp() {
        return this.identificativoCanalePsp;
    }

    //--- DATABASE MAPPING : tipo_versamento_psp ( varchar ) 
    public void setTipoVersamentoPsp( String tipoVersamentoPsp ) {
        this.tipoVersamentoPsp = tipoVersamentoPsp;
    }
    public String getTipoVersamentoPsp() {
        return this.tipoVersamentoPsp;
    }

    //--- DATABASE MAPPING : modello_pagamento_psp ( int4 ) 
    public void setModelloPagamentoPsp( Integer modelloPagamentoPsp ) {
        this.modelloPagamentoPsp = modelloPagamentoPsp;
    }
    public Integer getModelloPagamentoPsp() {
        return this.modelloPagamentoPsp;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTRegistroVersamenti( List<EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti ) {
        this.listOfEpayTRegistroVersamenti = listOfEpayTRegistroVersamenti;
    }
    public List<EpayTRegistroVersamenti> getListOfEpayTRegistroVersamenti() {
        return this.listOfEpayTRegistroVersamenti;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idTransazione);
        sb.append("]:"); 
        sb.append(iuv);
        sb.append("|");
        sb.append(idGateway);
        sb.append("|");
        sb.append(idPaymentMode);
        sb.append("|");
        sb.append(idInformativaPsp);
        sb.append("|");
        sb.append(identificativoPsp);
        sb.append("|");
        sb.append(ragioneSocialePsp);
        sb.append("|");
        sb.append(identificativoCanalePsp);
        sb.append("|");
        sb.append(tipoVersamentoPsp);
        sb.append("|");
        sb.append(modelloPagamentoPsp);
        return sb.toString(); 
    } 

}
