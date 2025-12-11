/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

import java.util.List;
/**
 * Persistent class for "EpayDStatoErrore" entity stored in table "epay_d_stato_errore" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_d_stato_errore", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayDStatoErrore.countAll",  query="SELECT COUNT(x) FROM EpayDStatoErrore x" ),
  @NamedQuery ( name="EpayDStatoErrore.countById", query="SELECT COUNT(x) FROM EpayDStatoErrore x WHERE x.idStato = ?1 " )
} )
public class EpayDStatoErrore implements IEntity<Integer> {

    private static final long serialVersionUID = 1L;

	public static EpayDStatoErrore reference ( Integer idStato ) {
        EpayDStatoErrore reference = new EpayDStatoErrore ();
        reference.setIdStato( idStato );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id_stato", nullable=false)
    private Integer idStato ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="descrizione", length=200)
    private String descrizione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @OneToMany(mappedBy="epayDStatoErrore", targetEntity=EpayTErrori.class)
	private List<EpayTErrori> listOfEpayTErrori ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDStatoErrore() {
		super();
    }

	@Override
	public Integer getPrimaryKey() {
		return getIdStato();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdStato( Integer idStato ) {
        this.idStato = idStato ;
    }
    public Integer getIdStato() {
        return this.idStato;
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
    public void setListOfEpayTErrori( List<EpayTErrori> listOfEpayTErrori ) {
        this.listOfEpayTErrori = listOfEpayTErrori;
    }
    public List<EpayTErrori> getListOfEpayTErrori() {
        return this.listOfEpayTErrori;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idStato);
        sb.append("]:"); 
        sb.append(descrizione);
        return sb.toString(); 
    } 

}
