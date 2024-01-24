/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;



import javax.persistence.*;

import java.util.List;
/**
 * Persistent class for "EpayDStatoPagamento" entity stored in table "epay_d_stato_pagamento" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_d_stato_pagamento", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayDStatoPagamento.countAll",  query="SELECT COUNT(x) FROM EpayDStatoPagamento x" ),
  @NamedQuery ( name="EpayDStatoPagamento.countById", query="SELECT COUNT(x) FROM EpayDStatoPagamento x WHERE x.idStato = ?1 " )
} )
public class EpayDStatoPagamento implements IEntity<Short> {

    private static final long serialVersionUID = 1L;

	public static EpayDStatoPagamento reference ( Short idStato ) {
        EpayDStatoPagamento reference = new EpayDStatoPagamento ();
        reference.setIdStato( idStato );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id_stato", nullable=false)
    private Short idStato ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="desc_stato", nullable=false, length=250)
    private String descStato ;

    @Column(name="pagabile", nullable=false)
    private Boolean pagabile ;

    @Column(name="modificabile", nullable=false)
    private Boolean modificabile ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @OneToMany(mappedBy="epayDStatoPagamento", targetEntity=EpayTRegistroVersamenti.class)
	private List<EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti ;
    
    @OneToMany(mappedBy="epayDStatoPagamento", targetEntity=EpayTPagamento.class)
	private List<EpayTPagamento> listOfEpayTPagamento ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDStatoPagamento() {
		super();
    }

	@Override
	public Short getPrimaryKey() {
		return getIdStato();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdStato( Short idStato ) {
        this.idStato = idStato ;
    }
    public Short getIdStato() {
        return this.idStato;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : desc_stato ( varchar ) 
    public void setDescStato( String descStato ) {
        this.descStato = descStato;
    }
    public String getDescStato() {
        return this.descStato;
    }

    //--- DATABASE MAPPING : pagabile ( bool ) 
    public void setPagabile( Boolean pagabile ) {
        this.pagabile = pagabile;
    }
    public Boolean getPagabile() {
        return this.pagabile;
    }

    //--- DATABASE MAPPING : modificabile ( bool ) 
    public void setModificabile( Boolean modificabile ) {
        this.modificabile = modificabile;
    }
    public Boolean getModificabile() {
        return this.modificabile;
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
    
    public void setListOfEpayTPagamento( List<EpayTPagamento> listOfEpayTPagamento ) {
        this.listOfEpayTPagamento = listOfEpayTPagamento;
    }
    public List<EpayTPagamento> getListOfEpayTPagamento() {
        return this.listOfEpayTPagamento;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idStato);
        sb.append("]:"); 
        sb.append(descStato);
        sb.append("|");
        sb.append(pagabile);
        sb.append("|");
        sb.append(modificabile);
        return sb.toString(); 
    } 

}
