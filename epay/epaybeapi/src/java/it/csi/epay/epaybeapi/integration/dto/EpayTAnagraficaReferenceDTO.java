/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Reference DTO for entity "epay_t_anagrafica" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTAnagraficaReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idAnagrafica ; // Long // Id or Primary Key

    @Size( max = 250 )
    private String nome ;  // String 
    @Size( max = 250 )
    private String cognome ;  // String 
    @Size( max = 250 )
    private String ragioneSociale ;  // String 
    @Size( max = 35 )
    private String codiceFiscale ;  // String 
    @Size( max = 70 )
    private String indirizzo ;  // String 
    @Size( max = 250 )
    private String email ;  // String 
    @Size( max = 30 )
    private String telefono ;  // String 
    @Size( max = 30 )
    private String cellulare ;  // String 
    @Size( max = 16 )
    private String civico ;  // String 
    @Size( max = 16 )
    private String cap ;  // String 
    @Size( max = 35 )
    private String localita ;  // String 
    @Size( max = 35 )
    private String provincia ;  // String 
    @Size( max = 2 )
    private String nazione ;  // String 
    @NotNull
    private Boolean flagPersonaFisica ;  // Boolean 

    /**
     * Default constructor
     */
    public EpayTAnagraficaReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idAnagrafica" field value
     * This field is mapped on the database column "id_anagrafica" ( type "bigserial", NotNull : true ) 
     * @param idAnagrafica
     */
	public void setIdAnagrafica( Long idAnagrafica ) {
        this.idAnagrafica = idAnagrafica ;
    }
    /**
     * Get the "idAnagrafica" field value
     * This field is mapped on the database column "id_anagrafica" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdAnagrafica() {
        return this.idAnagrafica;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "nome" field value
     * This field is mapped on the database column "nome" ( type "varchar", NotNull : false ) 
     * @param nome
     */
    public void setNome( String nome ) {
        this.nome = nome;
    }
    /**
     * Get the "nome" field value
     * This field is mapped on the database column "nome" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Set the "cognome" field value
     * This field is mapped on the database column "cognome" ( type "varchar", NotNull : false ) 
     * @param cognome
     */
    public void setCognome( String cognome ) {
        this.cognome = cognome;
    }
    /**
     * Get the "cognome" field value
     * This field is mapped on the database column "cognome" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Set the "ragioneSociale" field value
     * This field is mapped on the database column "ragione_sociale" ( type "varchar", NotNull : false ) 
     * @param ragioneSociale
     */
    public void setRagioneSociale( String ragioneSociale ) {
        this.ragioneSociale = ragioneSociale;
    }
    /**
     * Get the "ragioneSociale" field value
     * This field is mapped on the database column "ragione_sociale" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getRagioneSociale() {
        return this.ragioneSociale;
    }

    /**
     * Set the "codiceFiscale" field value
     * This field is mapped on the database column "codice_fiscale" ( type "varchar", NotNull : false ) 
     * @param codiceFiscale
     */
    public void setCodiceFiscale( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
    /**
     * Get the "codiceFiscale" field value
     * This field is mapped on the database column "codice_fiscale" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    /**
     * Set the "indirizzo" field value
     * This field is mapped on the database column "indirizzo" ( type "varchar", NotNull : false ) 
     * @param indirizzo
     */
    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }
    /**
     * Get the "indirizzo" field value
     * This field is mapped on the database column "indirizzo" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * Set the "email" field value
     * This field is mapped on the database column "email" ( type "varchar", NotNull : false ) 
     * @param email
     */
    public void setEmail( String email ) {
        this.email = email;
    }
    /**
     * Get the "email" field value
     * This field is mapped on the database column "email" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the "telefono" field value
     * This field is mapped on the database column "telefono" ( type "varchar", NotNull : false ) 
     * @param telefono
     */
    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }
    /**
     * Get the "telefono" field value
     * This field is mapped on the database column "telefono" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * Set the "cellulare" field value
     * This field is mapped on the database column "cellulare" ( type "varchar", NotNull : false ) 
     * @param cellulare
     */
    public void setCellulare( String cellulare ) {
        this.cellulare = cellulare;
    }
    /**
     * Get the "cellulare" field value
     * This field is mapped on the database column "cellulare" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCellulare() {
        return this.cellulare;
    }

    /**
     * Set the "civico" field value
     * This field is mapped on the database column "civico" ( type "varchar", NotNull : false ) 
     * @param civico
     */
    public void setCivico( String civico ) {
        this.civico = civico;
    }
    /**
     * Get the "civico" field value
     * This field is mapped on the database column "civico" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCivico() {
        return this.civico;
    }

    /**
     * Set the "cap" field value
     * This field is mapped on the database column "cap" ( type "varchar", NotNull : false ) 
     * @param cap
     */
    public void setCap( String cap ) {
        this.cap = cap;
    }
    /**
     * Get the "cap" field value
     * This field is mapped on the database column "cap" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCap() {
        return this.cap;
    }

    /**
     * Set the "localita" field value
     * This field is mapped on the database column "localita" ( type "varchar", NotNull : false ) 
     * @param localita
     */
    public void setLocalita( String localita ) {
        this.localita = localita;
    }
    /**
     * Get the "localita" field value
     * This field is mapped on the database column "localita" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getLocalita() {
        return this.localita;
    }

    /**
     * Set the "provincia" field value
     * This field is mapped on the database column "provincia" ( type "varchar", NotNull : false ) 
     * @param provincia
     */
    public void setProvincia( String provincia ) {
        this.provincia = provincia;
    }
    /**
     * Get the "provincia" field value
     * This field is mapped on the database column "provincia" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getProvincia() {
        return this.provincia;
    }

    /**
     * Set the "nazione" field value
     * This field is mapped on the database column "nazione" ( type "bpchar", NotNull : false ) 
     * @param nazione
     */
    public void setNazione( String nazione ) {
        this.nazione = nazione;
    }
    /**
     * Get the "nazione" field value
     * This field is mapped on the database column "nazione" ( type "bpchar", NotNull : false ) 
     * @return the field value
     */
    public String getNazione() {
        return this.nazione;
    }

    /**
     * Set the "flagPersonaFisica" field value
     * This field is mapped on the database column "flag_persona_fisica" ( type "bool", NotNull : true ) 
     * @param flagPersonaFisica
     */
    public void setFlagPersonaFisica( Boolean flagPersonaFisica ) {
        this.flagPersonaFisica = flagPersonaFisica;
    }
    /**
     * Get the "flagPersonaFisica" field value
     * This field is mapped on the database column "flag_persona_fisica" ( type "bool", NotNull : true ) 
     * @return the field value
     */
    public Boolean getFlagPersonaFisica() {
        return this.flagPersonaFisica;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idAnagrafica);
        sb.append("|");
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
