/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

import java.util.List;
/**
 * Persistent class for "EpayDModalitaPagamento" entity stored in table "epay_d_modalita_pagamento" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_d_modalita_pagamento", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayDModalitaPagamento.countAll",  query="SELECT COUNT(x) FROM EpayDModalitaPagamento x" ),
  @NamedQuery ( name="EpayDModalitaPagamento.countById", query="SELECT COUNT(x) FROM EpayDModalitaPagamento x WHERE x.idModalitaPagamento = ?1 " )
} )
public class EpayDModalitaPagamento implements IEntity<Integer> {

    private static final long serialVersionUID = 1L;

	public static EpayDModalitaPagamento reference ( Integer idModalitaPagamento ) {
        EpayDModalitaPagamento reference = new EpayDModalitaPagamento ();
        reference.setIdModalitaPagamento( idModalitaPagamento );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id_modalita_pagamento", nullable=false)
    private Integer idModalitaPagamento ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="descrizione", nullable=false, length=200)
    private String descrizione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @OneToMany(mappedBy="epayDModalitaPagamento", targetEntity=EpayTEsitiRicevuti.class)
	private List<EpayTEsitiRicevuti> listOfEpayTEsitiRicevuti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDModalitaPagamento() {
		super();
    }

	@Override
	public Integer getPrimaryKey() {
		return getIdModalitaPagamento();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdModalitaPagamento( Integer idModalitaPagamento ) {
        this.idModalitaPagamento = idModalitaPagamento ;
    }
    public Integer getIdModalitaPagamento() {
        return this.idModalitaPagamento;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : descrizione ( varchar ) 
    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }
    public String getDescrizione() {
        return this.descrizione;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTEsitiRicevuti( List<EpayTEsitiRicevuti> listOfEpayTEsitiRicevuti ) {
        this.listOfEpayTEsitiRicevuti = listOfEpayTEsitiRicevuti;
    }
    public List<EpayTEsitiRicevuti> getListOfEpayTEsitiRicevuti() {
        return this.listOfEpayTEsitiRicevuti;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idModalitaPagamento);
        sb.append("]:"); 
        sb.append(descrizione);
        return sb.toString(); 
    } 

}
