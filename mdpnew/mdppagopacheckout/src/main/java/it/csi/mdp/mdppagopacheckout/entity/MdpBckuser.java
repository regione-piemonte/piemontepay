/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the mdp_bckusers database table.
 */
@Entity
@Table ( name = "mdp_bckusers" )
@NamedQuery ( name = "MdpBckuser.findAll", query = "SELECT m FROM MdpBckuser m" )
@SuppressWarnings ( "unused" )
public class MdpBckuser implements Serializable {

	private static final long serialVersionUID = 7761394978434685322L;

	@Id
	@SequenceGenerator ( name = "MDP_BCKUSERS_IDUSER_GENERATOR", sequenceName = "MDP_BCKUSERS_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "MDP_BCKUSERS_IDUSER_GENERATOR" )
	private Integer iduser;

	private String codfisc;

	private String email;

	private String firstname;

	private String lastname;

	private String pwdservizio;

	@ManyToMany
	@JoinTable (
					name = "mdp_bckusersgroup"
					, joinColumns = {
					@JoinColumn ( name = "iduser" )
	}
					, inverseJoinColumns = {
					@JoinColumn ( name = "idgroup" )
	}
	)
	private List<MdpBckofficegroup> mdpBckofficegroups;

	public MdpBckuser () {
	}

	public Integer getIduser () {
		return this.iduser;
	}

	public void setIduser ( Integer iduser ) {
		this.iduser = iduser;
	}

	public String getCodfisc () {
		return this.codfisc;
	}

	public void setCodfisc ( String codfisc ) {
		this.codfisc = codfisc;
	}

	public String getEmail () {
		return this.email;
	}

	public void setEmail ( String email ) {
		this.email = email;
	}

	public String getFirstname () {
		return this.firstname;
	}

	public void setFirstname ( String firstname ) {
		this.firstname = firstname;
	}

	public String getLastname () {
		return this.lastname;
	}

	public void setLastname ( String lastname ) {
		this.lastname = lastname;
	}

	public String getPwdservizio () {
		return this.pwdservizio;
	}

	public void setPwdservizio ( String pwdservizio ) {
		this.pwdservizio = pwdservizio;
	}

	public List<MdpBckofficegroup> getMdpBckofficegroups () {
		return this.mdpBckofficegroups;
	}

	public void setMdpBckofficegroups ( List<MdpBckofficegroup> mdpBckofficegroups ) {
		this.mdpBckofficegroups = mdpBckofficegroups;
	}

}
