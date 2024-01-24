/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_t_codice_versamento database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_codice_versamento" )
@NamedQuery ( name = "CodiceVersamentoLight.findAll", query = "SELECT c FROM CodiceVersamentoLight c" )
public class CodiceVersamentoLight extends AbstractPropagableParentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;


    private String codice;

    private String descrizione;


    @Column ( name = "flag_mb_secondario" )
    private Boolean flagMbSecondario;
    
    @Column ( name = "flag_mb_primario" )
    private Boolean flagMbPrimario;

    @Column ( name = "flag_annullato" )
    private Boolean flagAnnullato;
    
    //uni-directional many-to-one association to TipoPagamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_tipo_pagamento", referencedColumnName = "codice" )
    private TipoPagamento tipoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_ente" )
    private Ente ente;

  //uni-directional many-to-one association to StatoMultibeneficiario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "stato_mb_config", referencedColumnName = "codice" )
    private  StatoMultibeneficiario statoMultibeneficiario;
    
    public CodiceVersamentoLight ( Long id ) {
        super ();
        this.id = id;
    }

    /* DEV/CSI_PAG-2416 - BEGIN */
    public CodiceVersamentoLight ( Long id, String codice, String descrizione ) {
        this( id );
        this.codice = codice;
        this.descrizione = descrizione;
    }
    /* DEV/CSI_PAG-2416 - END */

    public CodiceVersamentoLight () {
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

   

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

   
    public TipoPagamento getTipoPagamento () {
        return tipoPagamento;
    }

    public void setTipoPagamento ( TipoPagamento tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    
   

    public Ente getEnte () {
        return ente;
    }

    public void setEnte ( Ente ente ) {
        this.ente = ente;
    }

   
    
    // RD10   aggiungere campi di appoggio
   

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}

	public StatoMultibeneficiario getStatoMultibeneficiario() {
		return statoMultibeneficiario;
	}

	public void setStatoMultibeneficiario(StatoMultibeneficiario statoMultibeneficiario) {
		this.statoMultibeneficiario = statoMultibeneficiario;
	}

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

    /**
     * @return the flagAnnullato
     */
    public Boolean getFlagAnnullato () {
        return flagAnnullato;
    }

    /**
     * @param flagAnnullato the flagAnnullato to set
     */
    public void setFlagAnnullato ( Boolean flagAnnullato ) {
        this.flagAnnullato = flagAnnullato;
    }

    @Override
    public String toString() {
        return "CodiceVersamentoLight [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
    }
    
}
