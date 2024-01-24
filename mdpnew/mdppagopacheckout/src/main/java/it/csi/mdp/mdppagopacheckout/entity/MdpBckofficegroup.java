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
 * The persistent class for the mdp_bckofficegroups database table.
 */
@Entity
@Table ( name = "mdp_bckofficegroups" )
@NamedQuery ( name = "MdpBckofficegroup.findAll", query = "SELECT m FROM MdpBckofficegroup m" )
@SuppressWarnings ( "unused" )
public class MdpBckofficegroup implements Serializable {

	private static final long serialVersionUID = -9029225366754963381L;

	@Id
	@SequenceGenerator ( name = "MDP_BCKOFFICEGROUPS_IDGROUP_GENERATOR", sequenceName = "MDP_BCKOFFICEGROUPS_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "MDP_BCKOFFICEGROUPS_IDGROUP_GENERATOR" )
	private Integer idgroup;

	private String description;

	@ManyToMany
	@JoinTable (
					name = "mdp_bckofficegroupappmapping"
					, joinColumns = {
					@JoinColumn ( name = "idgroup" )
	}
					, inverseJoinColumns = {
					@JoinColumn ( name = "idapp" )
	}
	)
	private List<Application> applications;

	@ManyToMany ( mappedBy = "mdpBckofficegroups" )
	private List<MdpBckrole> mdpBckroles;

	@ManyToMany ( mappedBy = "mdpBckofficegroups" )
	private List<MdpBckuser> mdpBckusers;

	public MdpBckofficegroup () {
	}

	public Integer getIdgroup () {
		return this.idgroup;
	}

	public void setIdgroup ( Integer idgroup ) {
		this.idgroup = idgroup;
	}

	public String getDescription () {
		return this.description;
	}

	public void setDescription ( String description ) {
		this.description = description;
	}

	public List<Application> getApplications () {
		return this.applications;
	}

	public void setApplications ( List<Application> applications ) {
		this.applications = applications;
	}

	public List<MdpBckrole> getMdpBckroles () {
		return this.mdpBckroles;
	}

	public void setMdpBckroles ( List<MdpBckrole> mdpBckroles ) {
		this.mdpBckroles = mdpBckroles;
	}

	public List<MdpBckuser> getMdpBckusers () {
		return this.mdpBckusers;
	}

	public void setMdpBckusers ( List<MdpBckuser> mdpBckusers ) {
		this.mdpBckusers = mdpBckusers;
	}

}
