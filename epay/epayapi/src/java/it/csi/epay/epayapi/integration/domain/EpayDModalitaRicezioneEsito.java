/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

/**
 * Persistent class for "EpayDModalitaRicezioneEsito" entity stored in table "epay_d_modalita_ricezione_esito" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_d_modalita_ricezione_esito", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayDModalitaRicezioneEsito.countAll",  query="SELECT COUNT(x) FROM EpayDModalitaRicezioneEsito x" ),
  @NamedQuery ( name="EpayDModalitaRicezioneEsito.countById", query="SELECT COUNT(x) FROM EpayDModalitaRicezioneEsito x WHERE x.idModalitaRicezioneEsito = ?1 " )
} )
public class EpayDModalitaRicezioneEsito implements IEntity<Integer> {

    private static final long serialVersionUID = 1L;

	public static EpayDModalitaRicezioneEsito reference ( Integer idModalitaRicezioneEsito ) {
        EpayDModalitaRicezioneEsito reference = new EpayDModalitaRicezioneEsito ();
        reference.setIdModalitaRicezioneEsito( idModalitaRicezioneEsito );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id_modalita_ricezione_esito", nullable=false)
    private Integer idModalitaRicezioneEsito ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="descrizione", nullable=false, length=200)
    private String descrizione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayDModalitaRicezioneEsito() {
		super();
    }

	@Override
	public Integer getPrimaryKey() {
		return getIdModalitaRicezioneEsito();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdModalitaRicezioneEsito( Integer idModalitaRicezioneEsito ) {
        this.idModalitaRicezioneEsito = idModalitaRicezioneEsito ;
    }
    public Integer getIdModalitaRicezioneEsito() {
        return this.idModalitaRicezioneEsito;
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

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idModalitaRicezioneEsito);
        sb.append("]:"); 
        sb.append(descrizione);
        return sb.toString(); 
    } 

}
