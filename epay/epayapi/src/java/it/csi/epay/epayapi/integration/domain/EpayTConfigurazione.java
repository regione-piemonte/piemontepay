/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

/**
 * Persistent class for "EpayTConfigurazione" entity stored in table "epay_t_configurazione" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_configurazione", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTConfigurazione.countAll",  query="SELECT COUNT(x) FROM EpayTConfigurazione x" ),
  @NamedQuery ( name="EpayTConfigurazione.countById", query="SELECT COUNT(x) FROM EpayTConfigurazione x WHERE x.id = ?1 " )
} )
public class EpayTConfigurazione implements IEntity<Integer> {

    private static final long serialVersionUID = 1L;

	public static EpayTConfigurazione reference ( Integer id ) {
        EpayTConfigurazione reference = new EpayTConfigurazione ();
        reference.setId( id );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id", nullable=false)
    private Integer id ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="codice", nullable=false, length=100)
    private String codice ;

    @Column(name="valore", nullable=false, length=100)
    private String valore ;

    @Column(name="descrizione", length=1000)
    private String descrizione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_ente", referencedColumnName="id_ente")
	private EpayTEnti epayTEnti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTConfigurazione() {
		super();
    }

	@Override
	public Integer getPrimaryKey() {
		return getId();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : codice ( varchar ) 
    public void setCodice( String codice ) {
        this.codice = codice;
    }
    public String getCodice() {
        return this.codice;
    }

    //--- DATABASE MAPPING : valore ( varchar ) 
    public void setValore( String valore ) {
        this.valore = valore;
    }
    public String getValore() {
        return this.valore;
    }

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
        sb.append(id);
        sb.append("]:"); 
        sb.append(codice);
        sb.append("|");
        sb.append(valore);
        sb.append("|");
        sb.append(descrizione);
        return sb.toString(); 
    } 

}
