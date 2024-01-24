/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the epaywso_t_ente database table.
 * 
 */
@Entity
@Table(name="epaywso_t_ente")
@NamedQueries({
	@NamedQuery(
			name = "EPaywsoTEnte.findCodFiscaleByIdEnte",
			query = "SELECT e.codFiscaleEnte "
					+ "FROM EPaywsoTEnte e "
					+ "WHERE e.idEnte = :idEnte "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoTEnte.findIdEnteByCodFiscale",
			query = "SELECT e.idEnte "
					+ "FROM EPaywsoTEnte e "
					+ "WHERE e.codFiscaleEnte = :codFiscaleEnte "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoTEnte.findOneByIdEnte",
			query = "SELECT e "
					+ "FROM EPaywsoTEnte e "
					+ "WHERE e.idEnte = :idEnte "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoTEnte.findOneByCodFiscale",
			query = "SELECT e "
					+ "FROM EPaywsoTEnte e "
					+ "WHERE e.codFiscaleEnte = :codFiscaleEnte "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name="EPaywsoTEnte.findAll",
			query="SELECT e FROM EPaywsoTEnte e"),
	

   @NamedQuery(
           name = "EPaywsoTEnte.findCodFiscaleEnteByIdEnte",
           query = "SELECT e.codFiscaleEnte "
                   + "FROM EPaywsoTEnte e "
                   + "WHERE e.idEnte = :idEnte"),
   @NamedQuery(
           name = "EPaywsoTEnte.findIdEnteByCodFiscaleEnte",
           query = "SELECT e.idEnte "
                   + "FROM EPaywsoTEnte e "
                   + "WHERE e.codFiscaleEnte = :codFiscaleEnte"),
   @NamedQuery(
           name = "EPaywsoTEnte.findOneByCodFiscaleEnte",
           query = "SELECT e "
                   + "FROM EPaywsoTEnte e "
                   + "WHERE e.codFiscaleEnte = :codFiscaleEnte"),

   @NamedQuery(
       name="EPaywsoTEnte.findIdEnteMax",
       query = "select u from EPaywsoTEnte u order by u.idEnte DESC"),
   
})
public class EPaywsoTEnte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAYWSO_T_ENTE_SEQ_ID_GENERATOR", sequenceName="epaywso.epaywso_t_ente_seq" ,schema="epaywso", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYWSO_T_ENTE_SEQ_ID_GENERATOR")
	@Column(name="id_ente")
	private Integer idEnte;

	@Column(name="cod_fiscale_ente")
	private String codFiscaleEnte;

	private String denominazione;

	@Column(name="dt_fine_validita")
	private Timestamp dtFineValidita;

	@Column(name="dt_inizio_validita")
	private Timestamp dtInizioValidita;

	public EPaywsoTEnte() {
	}

	public Integer getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getCodFiscaleEnte() {
		return this.codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
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

}
