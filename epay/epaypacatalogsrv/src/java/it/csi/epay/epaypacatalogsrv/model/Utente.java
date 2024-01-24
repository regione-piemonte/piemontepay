/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epaypacat_t_utente database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_utente" )
@NamedQuery ( name = "Utente.findAll", query = "SELECT e FROM Utente e" )
public class Utente extends AbstractPropagableParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column ( name = "codice_fiscale" )
    private String codiceFiscale;

    private String cognome;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "data_fine_validita" )
    private Date dataFineValidita;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "data_inizio_validita" )
    private Date dataInizioValidita;

    private String nome;

    private String email;

    //uni-directional many-to-many association to EpaypacatTEnte
    //EPAY-80
    /*
     * @ManyToMany
     * @JoinTable ( name = "epaycat_r_utente_ente", joinColumns = { @JoinColumn ( name = "id_utente", insertable = false, updatable = false ) },
     * inverseJoinColumns = { @JoinColumn ( name = "id_ente", insertable = false, updatable = false ) } ) private List<Ente> enti;
     */



    @OneToMany ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_utente", insertable = true, updatable = false )
    private List<AssociazioneUtenteEnte> associazioneUtenteEnte;

    public Utente () {
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    /**
     * @return the associazioneUtenteEnte
     */
    public List<AssociazioneUtenteEnte> getAssociazioneUtenteEnte () {
        return associazioneUtenteEnte;
    }

    /**
     * @param associazioneUtenteEnte the associazioneUtenteEnte to set
     */
    public void setAssociazioneUtenteEnte ( List<AssociazioneUtenteEnte> associazioneUtenteEnte ) {
        this.associazioneUtenteEnte = associazioneUtenteEnte;
    }

	@Override
	public String toString() {
		return "Utente [id=" + id + ", codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", dataFineValidita="
				+ dataFineValidita + ", dataInizioValidita=" + dataInizioValidita + ", nome=" + nome + ", email="
				+ email + "]";
	}
    
}
