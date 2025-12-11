/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;


import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.Type;
/**
 * Persistent class for "EpayTEntiTemp" entity stored in table "epay_t_enti_temp" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_enti_temp", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTEntiTemp.countAll",  query="SELECT COUNT(x) FROM EpayTEntiTemp x" ),
  @NamedQuery ( name="EpayTEntiTemp.countById", query="SELECT COUNT(x) FROM EpayTEntiTemp x WHERE x.idEnteTemp = ?1 " )
} )
public class EpayTEntiTemp implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTEntiTemp reference ( Long idEnteTemp ) {
        EpayTEntiTemp reference = new EpayTEntiTemp ();
        reference.setIdEnteTemp( idEnteTemp );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_ente_temp", nullable=false)
    private Long idEnteTemp ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="id_operazione", nullable=false, length=100)
    private String idOperazione ;

    @Column(name="nome", nullable=false, length=250)
    private String nome ;

    @Column(name="codice_fiscale", nullable=false, length=16)
    private String codiceFiscale ;

    @Lob
    @Column(name="logo")
    @Type ( type = "org.hibernate.type.BinaryType" )
    private byte[] logo ;

    @Column(name="codice_gs1_gln", nullable=false)
    private BigDecimal codiceGs1Gln ;

    @Column(name="orari", length=2000)
    private String orari ;

    @Column(name="flag_invio_pagamenti")
    private Boolean flagInvioPagamenti ;

    @Column(name="codice_interbancario", length=5)
    private String codiceInterbancario ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_ente", referencedColumnName="id_ente")
	private EpayTEnti epayTEnti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTEntiTemp() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdEnteTemp();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdEnteTemp( Long idEnteTemp ) {
        this.idEnteTemp = idEnteTemp ;
    }
    public Long getIdEnteTemp() {
        return this.idEnteTemp;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : id_operazione ( varchar ) 
    public void setIdOperazione( String idOperazione ) {
        this.idOperazione = idOperazione;
    }
    public String getIdOperazione() {
        return this.idOperazione;
    }

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
        sb.append(idEnteTemp);
        sb.append("]:"); 
        sb.append(idOperazione);
        sb.append("|");
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
