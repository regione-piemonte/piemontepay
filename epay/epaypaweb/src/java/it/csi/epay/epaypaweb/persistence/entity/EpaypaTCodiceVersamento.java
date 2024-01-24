/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the epaypa_t_codice_versamento database table.
 *
 */
@Entity
@Table(name="epaypa_t_codice_versamento")
@NamedQueries({

    @NamedQuery(
        name = "EpaypaTCodiceVersamento.findAllByIdEnteAndCodiceVersamento",
        query = "SELECT e "
                        + "FROM EpaypaTCodiceVersamento e "
                        + "WHERE e.codVersamento = :codVersamento "
                        + "AND e.epaypaTEnte.idEnte = :idEnte"),
    @NamedQuery(
        name = "EpaypaTCodiceVersamento.findByIdEnte",
        query = "SELECT e "
                        + "FROM EpaypaTCodiceVersamento e "
                        + "WHERE e.epaypaTEnte.idEnte = :idEnte"),

	@NamedQuery(
			name = "EpaypaTCodiceVersamento.findCodVersamentoByIdCodVersamento",
			query = "SELECT e.codVersamento "
					+ "FROM EpaypaTCodiceVersamento e "
					+ "WHERE e.idCodiceVersamento = :idCodiceVersamento"),
	@NamedQuery(
			name = "EpaypaTCodiceVersamento.findIdCodVersamentoByCodVersamentoAndIdEnte",
			query = "SELECT e.idCodiceVersamento "
					+ "FROM EpaypaTCodiceVersamento e "
					+ "WHERE e.codVersamento = :codVersamento "
					+ "AND e.epaypaTEnte.idEnte = :idEnte"),
	@NamedQuery(
			name = "EpaypaTCodiceVersamento.findOneByIdCodVersamento",
			query = "SELECT e "
					+ "FROM EpaypaTCodiceVersamento e "
					+ "WHERE e.idCodiceVersamento = :idCodiceVersamento"),
	@NamedQuery(
			name = "EpaypaTCodiceVersamento.findOneByCodVersamentoAndIdEnte",
			query = "SELECT e "
					+ "FROM EpaypaTCodiceVersamento e "
					+ "WHERE e.codVersamento = :codVersamento "
					+ "AND e.epaypaTEnte.idEnte = :idEnte"),
	@NamedQuery(
			name = "EpaypaTCodiceVersamento.findAllIdCodVersamentoByIdProfiloAndIdEnte",
			query = "SELECT cv.idCodiceVersamento "
					+ "FROM EpaypaTProfilo p, EpaypaTCodiceVersamento cv "
					+ "JOIN p.epaypaTCodiceVersamentos rel "
					+ "WHERE p.idProfilo = :idProfilo "
					+ "AND cv.idCodiceVersamento = rel.idCodiceVersamento "
					+ "AND cv.epaypaTEnte.idEnte = :idEnte"),
    @NamedQuery (
                    name = "EpaypaTCodiceVersamento.findAllByIdProfiloAndIdEnte",
                    query = "SELECT cv "
                        + "FROM EpaypaTProfilo p, EpaypaTCodiceVersamento cv "
                        + "JOIN p.epaypaTCodiceVersamentos rel "
                        + "WHERE p.idProfilo = :idProfilo "
                        + "AND cv.idCodiceVersamento = rel.idCodiceVersamento "
                        + "AND cv.epaypaTEnte.idEnte = :idEnte" ),
	@NamedQuery(
			name="EpaypaTCodiceVersamento.findAll",
                    query = "SELECT e FROM EpaypaTCodiceVersamento e" ),
    @NamedQuery (
                    name = "EpaypaTCodiceVersamento.findAllByIdEnteAndCodVersamentoIn",
                    query = "SELECT cv "
                        + "FROM EpaypaTCodiceVersamento cv "
                        + "WHERE cv.epaypaTEnte.idEnte = :idEnte "
                        + "AND cv.codVersamento IN :codiciVersamento" ),
    @NamedQuery (
                    name = "EpaypaTCodiceVersamento.findAllCodVersamentoByIdEnteAndCodVersamentoLike",
                    query = "SELECT cv "
                        + "FROM EpaypaTCodiceVersamento cv "
                        + "WHERE cv.epaypaTEnte.idEnte = :idEnte "
                        + "AND cv.codVersamento LIKE :codiceExpression" )
})
public class EpaypaTCodiceVersamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAYPA_T_CODICEVERSAMENTO_ID_GENERATOR", sequenceName="epaypa.EPAYPA_T_CODICE_VERSAMENTO_SEQ" ,schema="epaypa", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYPA_T_CODICEVERSAMENTO_ID_GENERATOR")
    @Column(name="id_codice_versamento")
	private Integer idCodiceVersamento;

	@Column(name="cod_versamento")
	private String codVersamento;

	private String descrizione;

	//uni-directional many-to-one association to EpaypaTEnte
	@ManyToOne
	@JoinColumn(name="id_ente")
	private EpaypaTEnte epaypaTEnte;

    @Column ( name = "codice_modalita_integrazione" )
    private String codiceModalitaIntegrazione;

    @Column ( name = "id_tipo_pagamento" )
    private Integer idTipoPagamento;

    @Column ( name = "bollettino_postale" )
    private Boolean bollettinoPostale;

	@Column ( name = "flag_mb_primario" )
	private Boolean flagMbPrimario;

	@Column ( name = "flag_mb_secondario" )
	private Boolean flagMbSecondario;

    /**
	 * @return the bollettinoPostale
	 */
	public Boolean getBollettinoPostale() {
		return bollettinoPostale;
	}

	/**
	 * @param bollettinoPostale the bollettinoPostale to set
	 */
	public void setBollettinoPostale(Boolean bollettinoPostale) {
		this.bollettinoPostale = bollettinoPostale;
	}

    public Integer getIdTipoPagamento () {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento ( Integer idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public EpaypaTCodiceVersamento () {
	}

	public Integer getIdCodiceVersamento() {
		return idCodiceVersamento;
	}

	public void setIdCodiceVersamento(Integer idCodiceVersamento) {
		this.idCodiceVersamento = idCodiceVersamento;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public EpaypaTEnte getEpaypaTEnte() {
		return epaypaTEnte;
	}

    public void setEpaypaTEnte(EpaypaTEnte epaypaTEnte) {
        this.epaypaTEnte = epaypaTEnte;
    }


    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}
}
