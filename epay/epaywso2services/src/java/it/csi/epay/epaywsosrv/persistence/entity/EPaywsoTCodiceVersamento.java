/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the epaywso_t_codice_versamento database table.
 * 
 */
@Entity
@Table(name="epaywso_t_codice_versamento")
@NamedQueries({
	@NamedQuery(
			name = "EPaywsoTCodiceVersamento.findCodVersamentoByIdCodiceVersamento",
			query = "SELECT e.codVersamento "
					+ "FROM EPaywsoTCodiceVersamento e "
					+ "WHERE e.idCodiceVersamento = :idCodiceVersamento "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoTCodiceVersamento.findIdCodiceVersamentoByCodVersamento",
			query = "SELECT e.idCodiceVersamento "
					+ "FROM EPaywsoTCodiceVersamento e "
					+ "WHERE e.codVersamento = :codVersamento "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoTCodiceVersamento.findOneByIdCodiceVersamento",
			query = "SELECT e "
					+ "FROM EPaywsoTCodiceVersamento e "
					+ "WHERE e.idCodiceVersamento = :idCodiceVersamento "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoTCodiceVersamento.findAllByCodVersamento",
			query = "SELECT e "
					+ "FROM EPaywsoTCodiceVersamento e "
					+ "WHERE e.codVersamento = :codVersamento "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name="EPaywsoTCodiceVersamento.findAll",
			query="SELECT e FROM EPaywsoTCodiceVersamento e"),
	
    @NamedQuery(
        name = "EPaywsoTCodiceVersamento.findAllByIdEnteAndCodiceVersamento",
        query = "SELECT c FROM EPaywsoTCodiceVersamento c WHERE c.codVersamento = :codVersamento AND "
            + "c.epaywsoTApplicativo.ePaywsoTEnte.idEnte = :idEnte"),

    @NamedQuery(
        name = "EPaywsoTCodiceVersamento.findOneByCodVersamentoAndIdEnteAttivo",
        query = "SELECT e "
                + "FROM EPaywsoTCodiceVersamento e "
                + "WHERE e.codVersamento = :codVersamento AND "
                + "e.epaywsoTApplicativo.ePaywsoTEnte.idEnte = :idEnte and (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita) order by e.idCodiceVersamento desc"),
    
    @NamedQuery(
        name="EPaywsoTCodiceVersamento.findAllDesc",
        query="SELECT e FROM EPaywsoTCodiceVersamento e ORDER BY e.idCodiceVersamento DESC"),

    @NamedQuery(
        name = "EPaywsoTCodiceVersamento.findByIdEnte",
        query = "SELECT c FROM EPaywsoTCodiceVersamento c WHERE "
            + "c.epaywsoTApplicativo.ePaywsoTEnte.idEnte = :idEnte"),
    	
})
public class EPaywsoTCodiceVersamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAYWSO_T_CODICE_VERSAMENTO_ID_GENERATOR", sequenceName="epaywso.epaywso_t_codice_versamento_seq" ,schema="epaywso", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYWSO_T_CODICE_VERSAMENTO_ID_GENERATOR")
	@Column(name="id_codice_versamento")
	private Integer idCodiceVersamento;

	@Column(name="cod_versamento")
	private String codVersamento;

	private String descrizione;

	@Column(name="dt_fine_validita")
	private Timestamp dtFineValidita;

	@Column(name="dt_inizio_validita")
	private Timestamp dtInizioValidita;

	//uni-directional many-to-one association to EPaywsoTApplicativo
	@ManyToOne
	@JoinColumn(name="id_applicativo")
	private EPaywsoTApplicativo epaywsoTApplicativo;

	public EPaywsoTCodiceVersamento() {

	}

	public Integer getIdCodiceVersamento() {
		return this.idCodiceVersamento;
	}

	public void setIdCodiceVersamento(Integer idCodiceVersamento) {
		this.idCodiceVersamento = idCodiceVersamento;
	}

	public String getCodVersamento() {
		return this.codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Timestamp getDtFineValidita() {
		return this.dtFineValidita;
	}

	public void setDtFineValidita(Timestamp dtFineValidita) {
		this.dtFineValidita = dtFineValidita;
	}

	public Timestamp getDtInizioValidita() {
		return this.dtInizioValidita;
	}

	public void setDtInizioValidita(Timestamp dtInizioValidita) {
		this.dtInizioValidita = dtInizioValidita;
	}

	public EPaywsoTApplicativo getEpaywsoTApplicativo() {
		return this.epaywsoTApplicativo;
	}

	public void setEpaywsoTApplicativo(EPaywsoTApplicativo epaywsoTApplicativo) {
		this.epaywsoTApplicativo = epaywsoTApplicativo;
	}

}
