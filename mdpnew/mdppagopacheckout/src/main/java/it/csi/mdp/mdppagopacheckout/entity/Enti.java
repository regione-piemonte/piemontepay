/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the enti database table.
 */
@Entity
@NamedQuery ( name = "Enti.findAll", query = "SELECT e FROM Enti e" )
@SuppressWarnings ( "unused" )
public class Enti implements Serializable {

	private static final long serialVersionUID = 874448895606307882L;

	@Id
	@SequenceGenerator ( name = "ENTI_ENTEID_GENERATOR", sequenceName = "ENTI_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "ENTI_ENTEID_GENERATOR" )
	@Column ( name = "ente_id" )
	private String enteId;

	private String attivo;

	@Column ( name = "codice_segregazione" )
	private String codiceSegregazione;

	private String descrizione;

	@Column ( name = "flag_invio_flusso_base" )
	private Boolean flagInvioFlussoBase;

	@Column ( name = "flag_invio_flusso_esteso" )
	private Boolean flagInvioFlussoEsteso;

	@Column ( name = "partita_iva" )
	private String partitaIva;

	@Column ( name = "progressivo_application_id" )
	private Integer progressivoApplicationId;

	@OneToMany ( mappedBy = "enti" )
	private List<IuvAttribute> iuvAttributes;

	@OneToMany ( mappedBy = "enti" )
	private List<IuvOttici> iuvOtticis;

	@ManyToMany
	@JoinTable (
					name = "r_application_enti"
					, joinColumns = {
					@JoinColumn ( name = "ente_id" )
	}
					, inverseJoinColumns = {
					@JoinColumn ( name = "application_id" )
	}
	)
	private List<Application> applications;

	public Enti () {
	}

	public String getEnteId () {
		return this.enteId;
	}

	public void setEnteId ( String enteId ) {
		this.enteId = enteId;
	}

	public String getAttivo () {
		return this.attivo;
	}

	public void setAttivo ( String attivo ) {
		this.attivo = attivo;
	}

	public String getCodiceSegregazione () {
		return this.codiceSegregazione;
	}

	public void setCodiceSegregazione ( String codiceSegregazione ) {
		this.codiceSegregazione = codiceSegregazione;
	}

	public String getDescrizione () {
		return this.descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public Boolean getFlagInvioFlussoBase () {
		return this.flagInvioFlussoBase;
	}

	public void setFlagInvioFlussoBase ( Boolean flagInvioFlussoBase ) {
		this.flagInvioFlussoBase = flagInvioFlussoBase;
	}

	public Boolean getFlagInvioFlussoEsteso () {
		return this.flagInvioFlussoEsteso;
	}

	public void setFlagInvioFlussoEsteso ( Boolean flagInvioFlussoEsteso ) {
		this.flagInvioFlussoEsteso = flagInvioFlussoEsteso;
	}

	public String getPartitaIva () {
		return this.partitaIva;
	}

	public void setPartitaIva ( String partitaIva ) {
		this.partitaIva = partitaIva;
	}

	public Integer getProgressivoApplicationId () {
		return this.progressivoApplicationId;
	}

	public void setProgressivoApplicationId ( Integer progressivoApplicationId ) {
		this.progressivoApplicationId = progressivoApplicationId;
	}

	public List<IuvAttribute> getIuvAttributes () {
		return this.iuvAttributes;
	}

	public void setIuvAttributes ( List<IuvAttribute> iuvAttributes ) {
		this.iuvAttributes = iuvAttributes;
	}

	public IuvAttribute addIuvAttribute ( IuvAttribute iuvAttribute ) {
		getIuvAttributes ().add ( iuvAttribute );
		iuvAttribute.setEnti ( this );

		return iuvAttribute;
	}

	public IuvAttribute removeIuvAttribute ( IuvAttribute iuvAttribute ) {
		getIuvAttributes ().remove ( iuvAttribute );
		iuvAttribute.setEnti ( null );

		return iuvAttribute;
	}

	public List<IuvOttici> getIuvOtticis () {
		return this.iuvOtticis;
	}

	public void setIuvOtticis ( List<IuvOttici> iuvOtticis ) {
		this.iuvOtticis = iuvOtticis;
	}

	public IuvOttici addIuvOttici ( IuvOttici iuvOttici ) {
		getIuvOtticis ().add ( iuvOttici );
		iuvOttici.setEnti ( this );

		return iuvOttici;
	}

	public IuvOttici removeIuvOttici ( IuvOttici iuvOttici ) {
		getIuvOtticis ().remove ( iuvOttici );
		iuvOttici.setEnti ( null );

		return iuvOttici;
	}

	public List<Application> getApplications () {
		return this.applications;
	}

	public void setApplications ( List<Application> applications ) {
		this.applications = applications;
	}

}
