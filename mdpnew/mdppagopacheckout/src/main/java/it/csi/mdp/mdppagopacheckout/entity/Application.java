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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the application database table.
 */
@Entity
@NamedQuery ( name = "Application.findAll", query = "SELECT a FROM Application a" )
@SuppressWarnings ( "unused" )
public class Application implements Serializable {

	private static final long serialVersionUID = -5430342037187592904L;

	@Id
	@SequenceGenerator ( name = "APPLICATION_ID_GENERATOR", sequenceName = "APPLICATION_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "APPLICATION_ID_GENERATOR" )
	private String id;

	private String applicationname;

	private String cliente;

	private String esercemail;

	private String note;

	private String numeroverde;

	private String progetto;

	@Column ( name = "redirect_newmdp" )
	private BigDecimal redirectNewmdp;

	private String referentecsi;

	@Column ( name = "valido_al" )
	@Temporal ( TemporalType.TIMESTAMP )
	private Date validoAl;

	@Column ( name = "valido_dal" )
	private Date validoDal;

	@OneToMany ( mappedBy = "application" )
	private List<Applicationcustomfield> applicationcustomfields;

	@OneToMany ( mappedBy = "application" )
	private List<Applicationdetail> applicationdetails;

	@OneToMany ( mappedBy = "application" )
	private List<FlussoSingoloPagamento> flussoSingoloPagamentos;

	@OneToMany ( mappedBy = "application" )
	private List<Icicodiciimm> icicodiciimms;

	@OneToMany ( mappedBy = "application" )
	private List<IuvOttici> iuvOtticis;

	@OneToOne ( mappedBy = "application" )
	private MdpAppwsauth mdpAppwsauth;

	@ManyToMany ( mappedBy = "applications" )
	private List<MdpBckofficegroup> mdpBckofficegroups;

	@OneToMany ( mappedBy = "application" )
	private List<MdpErrori> mdpErroris;

	@OneToMany ( mappedBy = "application" )
	private List<NodoErrori> nodoErroris;

	@ManyToMany ( mappedBy = "applications" )
	private List<Enti> entis;

	public Application () {
	}

	public String getId () {
		return this.id;
	}

	public void setId ( String id ) {
		this.id = id;
	}

	public String getApplicationname () {
		return this.applicationname;
	}

	public void setApplicationname ( String applicationname ) {
		this.applicationname = applicationname;
	}

	public String getCliente () {
		return this.cliente;
	}

	public void setCliente ( String cliente ) {
		this.cliente = cliente;
	}

	public String getEsercemail () {
		return this.esercemail;
	}

	public void setEsercemail ( String esercemail ) {
		this.esercemail = esercemail;
	}

	public String getNote () {
		return this.note;
	}

	public void setNote ( String note ) {
		this.note = note;
	}

	public String getNumeroverde () {
		return this.numeroverde;
	}

	public void setNumeroverde ( String numeroverde ) {
		this.numeroverde = numeroverde;
	}

	public String getProgetto () {
		return this.progetto;
	}

	public void setProgetto ( String progetto ) {
		this.progetto = progetto;
	}

	public BigDecimal getRedirectNewmdp () {
		return this.redirectNewmdp;
	}

	public void setRedirectNewmdp ( BigDecimal redirectNewmdp ) {
		this.redirectNewmdp = redirectNewmdp;
	}

	public String getReferentecsi () {
		return this.referentecsi;
	}

	public void setReferentecsi ( String referentecsi ) {
		this.referentecsi = referentecsi;
	}

	public List<Applicationcustomfield> getApplicationcustomfields () {
		return this.applicationcustomfields;
	}

	public void setApplicationcustomfields ( List<Applicationcustomfield> applicationcustomfields ) {
		this.applicationcustomfields = applicationcustomfields;
	}

	public Applicationcustomfield addApplicationcustomfield ( Applicationcustomfield applicationcustomfield ) {
		getApplicationcustomfields ().add ( applicationcustomfield );
		applicationcustomfield.setApplication ( this );

		return applicationcustomfield;
	}

	public Applicationcustomfield removeApplicationcustomfield ( Applicationcustomfield applicationcustomfield ) {
		getApplicationcustomfields ().remove ( applicationcustomfield );
		applicationcustomfield.setApplication ( null );

		return applicationcustomfield;
	}

	public List<Applicationdetail> getApplicationdetails () {
		return this.applicationdetails;
	}

	public void setApplicationdetails ( List<Applicationdetail> applicationdetails ) {
		this.applicationdetails = applicationdetails;
	}

	public Applicationdetail addApplicationdetail ( Applicationdetail applicationdetail ) {
		getApplicationdetails ().add ( applicationdetail );
		applicationdetail.setApplication ( this );

		return applicationdetail;
	}

	public Applicationdetail removeApplicationdetail ( Applicationdetail applicationdetail ) {
		getApplicationdetails ().remove ( applicationdetail );
		applicationdetail.setApplication ( null );

		return applicationdetail;
	}

	public List<FlussoSingoloPagamento> getFlussoSingoloPagamentos () {
		return this.flussoSingoloPagamentos;
	}

	public void setFlussoSingoloPagamentos ( List<FlussoSingoloPagamento> flussoSingoloPagamentos ) {
		this.flussoSingoloPagamentos = flussoSingoloPagamentos;
	}

	public FlussoSingoloPagamento addFlussoSingoloPagamento ( FlussoSingoloPagamento flussoSingoloPagamento ) {
		getFlussoSingoloPagamentos ().add ( flussoSingoloPagamento );
		flussoSingoloPagamento.setApplication ( this );

		return flussoSingoloPagamento;
	}

	public FlussoSingoloPagamento removeFlussoSingoloPagamento ( FlussoSingoloPagamento flussoSingoloPagamento ) {
		getFlussoSingoloPagamentos ().remove ( flussoSingoloPagamento );
		flussoSingoloPagamento.setApplication ( null );

		return flussoSingoloPagamento;
	}

	public List<Icicodiciimm> getIcicodiciimms () {
		return this.icicodiciimms;
	}

	public void setIcicodiciimms ( List<Icicodiciimm> icicodiciimms ) {
		this.icicodiciimms = icicodiciimms;
	}

	public Icicodiciimm addIcicodiciimm ( Icicodiciimm icicodiciimm ) {
		getIcicodiciimms ().add ( icicodiciimm );
		icicodiciimm.setApplication ( this );

		return icicodiciimm;
	}

	public Icicodiciimm removeIcicodiciimm ( Icicodiciimm icicodiciimm ) {
		getIcicodiciimms ().remove ( icicodiciimm );
		icicodiciimm.setApplication ( null );

		return icicodiciimm;
	}

	public List<IuvOttici> getIuvOtticis () {
		return this.iuvOtticis;
	}

	public void setIuvOtticis ( List<IuvOttici> iuvOtticis ) {
		this.iuvOtticis = iuvOtticis;
	}

	public IuvOttici addIuvOttici ( IuvOttici iuvOttici ) {
		getIuvOtticis ().add ( iuvOttici );
		iuvOttici.setApplication ( this );

		return iuvOttici;
	}

	public IuvOttici removeIuvOttici ( IuvOttici iuvOttici ) {
		getIuvOtticis ().remove ( iuvOttici );
		iuvOttici.setApplication ( null );

		return iuvOttici;
	}

	public MdpAppwsauth getMdpAppwsauth () {
		return this.mdpAppwsauth;
	}

	public void setMdpAppwsauth ( MdpAppwsauth mdpAppwsauth ) {
		this.mdpAppwsauth = mdpAppwsauth;
	}

	public List<MdpBckofficegroup> getMdpBckofficegroups () {
		return this.mdpBckofficegroups;
	}

	public void setMdpBckofficegroups ( List<MdpBckofficegroup> mdpBckofficegroups ) {
		this.mdpBckofficegroups = mdpBckofficegroups;
	}

	public List<MdpErrori> getMdpErroris () {
		return this.mdpErroris;
	}

	public void setMdpErroris ( List<MdpErrori> mdpErroris ) {
		this.mdpErroris = mdpErroris;
	}

	public MdpErrori addMdpErrori ( MdpErrori mdpErrori ) {
		getMdpErroris ().add ( mdpErrori );
		mdpErrori.setApplication ( this );

		return mdpErrori;
	}

	public MdpErrori removeMdpErrori ( MdpErrori mdpErrori ) {
		getMdpErroris ().remove ( mdpErrori );
		mdpErrori.setApplication ( null );

		return mdpErrori;
	}

	public List<NodoErrori> getNodoErroris () {
		return this.nodoErroris;
	}

	public void setNodoErroris ( List<NodoErrori> nodoErroris ) {
		this.nodoErroris = nodoErroris;
	}

	public NodoErrori addNodoErrori ( NodoErrori nodoErrori ) {
		getNodoErroris ().add ( nodoErrori );
		nodoErrori.setApplication ( this );

		return nodoErrori;
	}

	public NodoErrori removeNodoErrori ( NodoErrori nodoErrori ) {
		getNodoErroris ().remove ( nodoErrori );
		nodoErrori.setApplication ( null );

		return nodoErrori;
	}

	public List<Enti> getEntis () {
		return this.entis;
	}

	public void setEntis ( List<Enti> entis ) {
		this.entis = entis;
	}

	/**
	 * @return the validoAl
	 */
	public Date getValidoAl () {
		return validoAl;
	}

	/**
	 * @param validoAl the validoAl to set
	 */
	public void setValidoAl ( Date validoAl ) {
		this.validoAl = validoAl;
	}

	/**
	 * @return the validoDal
	 */
	public Date getValidoDal () {
		return validoDal;
	}

	/**
	 * @param validoDal the validoDal to set
	 */
	public void setValidoDal ( Date validoDal ) {
		this.validoDal = validoDal;
	}

}
