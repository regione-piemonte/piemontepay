/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the opt_attr database table.
 */
@Entity
@Table ( name = "opt_attr" )
@NamedQuery ( name = "OptAttr.findAll", query = "SELECT o FROM OptAttr o" )
@SuppressWarnings ( "unused" )
public class OptAttr implements Serializable {

	private static final long serialVersionUID = 2066110151163476991L;

	@Id
	@SequenceGenerator ( name = "OPT_ATTR_OPTATTRID_GENERATOR", sequenceName = "OPT_ATTR_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "OPT_ATTR_OPTATTRID_GENERATOR" )
	@Column ( name = "opt_attr_id" )
	private long optAttrId;

	@Column ( name = "buyer_code" )
	private String buyerCode;

	@Column ( name = "buyer_name" )
	private String buyerName;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "transaction_id" )
	private Transazione transazione;

	@OneToMany ( mappedBy = "optAttr" )
	private List<OptAttrExtraAttribute> optAttrExtraAttributes;

	public OptAttr () {
	}

	public long getOptAttrId () {
		return this.optAttrId;
	}

	public void setOptAttrId ( long optAttrId ) {
		this.optAttrId = optAttrId;
	}

	public String getBuyerCode () {
		return this.buyerCode;
	}

	public void setBuyerCode ( String buyerCode ) {
		this.buyerCode = buyerCode;
	}

	public String getBuyerName () {
		return this.buyerName;
	}

	public void setBuyerName ( String buyerName ) {
		this.buyerName = buyerName;
	}

	public Transazione getTransazione () {
		return this.transazione;
	}

	public void setTransazione ( Transazione transazione ) {
		this.transazione = transazione;
	}

	public List<OptAttrExtraAttribute> getOptAttrExtraAttributes () {
		return this.optAttrExtraAttributes;
	}

	public void setOptAttrExtraAttributes ( List<OptAttrExtraAttribute> optAttrExtraAttributes ) {
		this.optAttrExtraAttributes = optAttrExtraAttributes;
	}

	public OptAttrExtraAttribute addOptAttrExtraAttribute ( OptAttrExtraAttribute optAttrExtraAttribute ) {
		getOptAttrExtraAttributes ().add ( optAttrExtraAttribute );
		optAttrExtraAttribute.setOptAttr ( this );

		return optAttrExtraAttribute;
	}

	public OptAttrExtraAttribute removeOptAttrExtraAttribute ( OptAttrExtraAttribute optAttrExtraAttribute ) {
		getOptAttrExtraAttributes ().remove ( optAttrExtraAttribute );
		optAttrExtraAttribute.setOptAttr ( null );

		return optAttrExtraAttribute;
	}

}
