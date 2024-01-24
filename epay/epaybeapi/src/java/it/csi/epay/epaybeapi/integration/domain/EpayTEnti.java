/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import java.util.List;
/**
 * Persistent class for "EpayTEnti" entity stored in table "epay_t_enti" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_enti", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTEnti.countAll",  query="SELECT COUNT(x) FROM EpayTEnti x" ),
  @NamedQuery ( name="EpayTEnti.countById", query="SELECT COUNT(x) FROM EpayTEnti x WHERE x.idEnte = ?1 " )
} )
public class EpayTEnti implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTEnti reference ( Long idEnte ) {
        EpayTEnti reference = new EpayTEnti ();
        reference.setIdEnte( idEnte );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_ente", nullable=false)
    private Long idEnte ;



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


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @OneToMany(mappedBy="epayTEnti", targetEntity=EpayTEntiTemp.class)
	private List<EpayTEntiTemp> listOfEpayTEntiTemp ;
    
    @OneToMany(mappedBy="epayTEnti", targetEntity=EpayTTipoPagamentoLog.class)
	private List<EpayTTipoPagamentoLog> listOfEpayTTipoPagamentoLog ;
    
    @OneToMany(mappedBy="epayTEnti", targetEntity=EpayTEntiLog.class)
	private List<EpayTEntiLog> listOfEpayTEntiLog ;
    
    @OneToMany(mappedBy="epayTEnti", targetEntity=EpayTConfigurazione.class)
	private List<EpayTConfigurazione> listOfEpayTConfigurazione ;
    
    @OneToMany(mappedBy="epayTEnti", targetEntity=EpayTTipoPagamento.class)
	private List<EpayTTipoPagamento> listOfEpayTTipoPagamento ;
    
    @OneToMany(mappedBy="epayTEnti", targetEntity=EpayTTipoPagamentoTemp.class)
	private List<EpayTTipoPagamentoTemp> listOfEpayTTipoPagamentoTemp ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTEnti() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdEnte();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdEnte( Long idEnte ) {
        this.idEnte = idEnte ;
    }
    public Long getIdEnte() {
        return this.idEnte;
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


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTEntiTemp( List<EpayTEntiTemp> listOfEpayTEntiTemp ) {
        this.listOfEpayTEntiTemp = listOfEpayTEntiTemp;
    }
    public List<EpayTEntiTemp> getListOfEpayTEntiTemp() {
        return this.listOfEpayTEntiTemp;
    }
    
    public void setListOfEpayTTipoPagamentoLog( List<EpayTTipoPagamentoLog> listOfEpayTTipoPagamentoLog ) {
        this.listOfEpayTTipoPagamentoLog = listOfEpayTTipoPagamentoLog;
    }
    public List<EpayTTipoPagamentoLog> getListOfEpayTTipoPagamentoLog() {
        return this.listOfEpayTTipoPagamentoLog;
    }
    
    public void setListOfEpayTEntiLog( List<EpayTEntiLog> listOfEpayTEntiLog ) {
        this.listOfEpayTEntiLog = listOfEpayTEntiLog;
    }
    public List<EpayTEntiLog> getListOfEpayTEntiLog() {
        return this.listOfEpayTEntiLog;
    }
    
    public void setListOfEpayTConfigurazione( List<EpayTConfigurazione> listOfEpayTConfigurazione ) {
        this.listOfEpayTConfigurazione = listOfEpayTConfigurazione;
    }
    public List<EpayTConfigurazione> getListOfEpayTConfigurazione() {
        return this.listOfEpayTConfigurazione;
    }
    
    public void setListOfEpayTTipoPagamento( List<EpayTTipoPagamento> listOfEpayTTipoPagamento ) {
        this.listOfEpayTTipoPagamento = listOfEpayTTipoPagamento;
    }
    public List<EpayTTipoPagamento> getListOfEpayTTipoPagamento() {
        return this.listOfEpayTTipoPagamento;
    }
    
    public void setListOfEpayTTipoPagamentoTemp( List<EpayTTipoPagamentoTemp> listOfEpayTTipoPagamentoTemp ) {
        this.listOfEpayTTipoPagamentoTemp = listOfEpayTTipoPagamentoTemp;
    }
    public List<EpayTTipoPagamentoTemp> getListOfEpayTTipoPagamentoTemp() {
        return this.listOfEpayTTipoPagamentoTemp;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idEnte);
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
        return sb.toString(); 
    } 

}
