/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

import java.util.List;
/**
 * Persistent class for "EpayTAnagrafica" entity stored in table "epay_t_anagrafica" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_anagrafica", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTAnagrafica.countAll",  query="SELECT COUNT(x) FROM EpayTAnagrafica x" ),
  @NamedQuery ( name="EpayTAnagrafica.countById", query="SELECT COUNT(x) FROM EpayTAnagrafica x WHERE x.idAnagrafica = ?1 " )
} )
public class EpayTAnagrafica implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTAnagrafica reference ( Long idAnagrafica ) {
        EpayTAnagrafica reference = new EpayTAnagrafica ();
        reference.setIdAnagrafica( idAnagrafica );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_anagrafica", nullable=false)
    private Long idAnagrafica ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nome", length=250)
    private String nome ;

    @Column(name="cognome", length=250)
    private String cognome ;

    @Column(name="ragione_sociale", length=250)
    private String ragioneSociale ;

    @Column(name="codice_fiscale", length=35)
    private String codiceFiscale ;

    @Column(name="indirizzo", length=70)
    private String indirizzo ;

    @Column(name="email", length=256)
    private String email ;

    @Column(name="telefono", length=30)
    private String telefono ;

    @Column(name="cellulare", length=30)
    private String cellulare ;

    @Column(name="civico", length=16)
    private String civico ;

    @Column(name="cap", length=16)
    private String cap ;

    @Column(name="localita", length=35)
    private String localita ;

    @Column(name="provincia", length=35)
    private String provincia ;

    @Column(name="nazione", length=2)
    private String nazione ;

    @Column(name="flag_persona_fisica", nullable=false)
    private Boolean flagPersonaFisica ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @OneToMany(mappedBy="epayTAnagrafica", targetEntity=EpayTPagamento.class)
	private List<EpayTPagamento> listOfEpayTPagamento ;
    
    @OneToMany(mappedBy="epayTAnagrafica", targetEntity=EpayTRegistroVersamenti.class)
	private List<EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTAnagrafica() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdAnagrafica();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdAnagrafica( Long idAnagrafica ) {
        this.idAnagrafica = idAnagrafica ;
    }
    public Long getIdAnagrafica() {
        return this.idAnagrafica;
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

    //--- DATABASE MAPPING : cognome ( varchar ) 
    public void setCognome( String cognome ) {
        this.cognome = cognome;
    }
    public String getCognome() {
        return this.cognome;
    }

    //--- DATABASE MAPPING : ragione_sociale ( varchar ) 
    public void setRagioneSociale( String ragioneSociale ) {
        this.ragioneSociale = ragioneSociale;
    }
    public String getRagioneSociale() {
        return this.ragioneSociale;
    }

    //--- DATABASE MAPPING : codice_fiscale ( varchar ) 
    public void setCodiceFiscale( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    //--- DATABASE MAPPING : indirizzo ( varchar ) 
    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }
    public String getIndirizzo() {
        return this.indirizzo;
    }

    //--- DATABASE MAPPING : email ( varchar ) 
    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    //--- DATABASE MAPPING : telefono ( varchar ) 
    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }
    public String getTelefono() {
        return this.telefono;
    }

    //--- DATABASE MAPPING : cellulare ( varchar ) 
    public void setCellulare( String cellulare ) {
        this.cellulare = cellulare;
    }
    public String getCellulare() {
        return this.cellulare;
    }

    //--- DATABASE MAPPING : civico ( varchar ) 
    public void setCivico( String civico ) {
        this.civico = civico;
    }
    public String getCivico() {
        return this.civico;
    }

    //--- DATABASE MAPPING : cap ( varchar ) 
    public void setCap( String cap ) {
        this.cap = cap;
    }
    public String getCap() {
        return this.cap;
    }

    //--- DATABASE MAPPING : localita ( varchar ) 
    public void setLocalita( String localita ) {
        this.localita = localita;
    }
    public String getLocalita() {
        return this.localita;
    }

    //--- DATABASE MAPPING : provincia ( varchar ) 
    public void setProvincia( String provincia ) {
        this.provincia = provincia;
    }
    public String getProvincia() {
        return this.provincia;
    }

    //--- DATABASE MAPPING : nazione ( bpchar ) 
    public void setNazione( String nazione ) {
        this.nazione = nazione;
    }
    public String getNazione() {
        return this.nazione;
    }

    //--- DATABASE MAPPING : flag_persona_fisica ( bool ) 
    public void setFlagPersonaFisica( Boolean flagPersonaFisica ) {
        this.flagPersonaFisica = flagPersonaFisica;
    }
    public Boolean getFlagPersonaFisica() {
        return this.flagPersonaFisica;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTPagamento( List<EpayTPagamento> listOfEpayTPagamento ) {
        this.listOfEpayTPagamento = listOfEpayTPagamento;
    }
    public List<EpayTPagamento> getListOfEpayTPagamento() {
        return this.listOfEpayTPagamento;
    }
    
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
        sb.append(idAnagrafica);
        sb.append("]:"); 
        sb.append(nome);
        sb.append("|");
        sb.append(cognome);
        sb.append("|");
        sb.append(ragioneSociale);
        sb.append("|");
        sb.append(codiceFiscale);
        sb.append("|");
        sb.append(indirizzo);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(telefono);
        sb.append("|");
        sb.append(cellulare);
        sb.append("|");
        sb.append(civico);
        sb.append("|");
        sb.append(cap);
        sb.append("|");
        sb.append(localita);
        sb.append("|");
        sb.append(provincia);
        sb.append("|");
        sb.append(nazione);
        sb.append("|");
        sb.append(flagPersonaFisica);
        return sb.toString(); 
    } 

}
