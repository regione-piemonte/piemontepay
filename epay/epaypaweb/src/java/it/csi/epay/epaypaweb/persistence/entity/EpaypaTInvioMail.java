/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the epaypa_t_invio_mail database table.
 *
 */
@Entity
@Table(name="epaypa_t_invio_mail")
@NamedQueries({
	@NamedQuery(
		name="EpaypaTInvioMail.findAll", query="SELECT e FROM EpaypaTInvioMail e"),
	@NamedQuery(
		name="EpaypaTInvioMail.findNextMailToSend",
		query="SELECT e FROM EpaypaTInvioMail e "
			+ "WHERE "
				+ "(e.dtUltimoTentativo IS NULL OR (e.dtUltimoTentativo IS NOT NULL AND e.dtUltimoTentativo < :limit))"
				+ "AND "
				+ "(e.erroreInvio != 'true' OR e.erroreInvio IS NULL) "
			+ "ORDER BY "
				+ "CASE WHEN e.dtUltimoTentativo > e.dtInserimento "
				+ "THEN e.dtUltimoTentativo "
				+ "ELSE e.dtInserimento "
				+ "END"
		)
})
public class EpaypaTInvioMail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_invio_mail")
	private Long idInvioMail;

	@Column(name="destinatario_cc")
	private String destinatarioCc;

	@Column(name="destinatario_to")
	private String destinatarioTo;

	@Column(name="dt_inserimento")
	private Timestamp dtInserimento;

	@Column(name="dt_ultimo_tentativo")
	private Timestamp dtUltimoTentativo;

	@Column(name="errore_invio")
	private Boolean erroreInvio;

	@Column(name="esito_ultimo_tentativo")
	private String esitoUltimoTentativo;

	//uni-directional many-to-one association to EpaypaDTipoInvioMail
	@ManyToOne
	@JoinColumn(name="id_tipo_invio_mail")
	private EpaypaDTipoInvioMail epaypaDTipoInvioMail;

	@Column(name="id_flusso")
	private Long idFlusso;

	public EpaypaTInvioMail() {
	}

	public Long getIdInvioMail() {
		return this.idInvioMail;
	}

	public void setIdInvioMail(Long idInvioMail) {
		this.idInvioMail = idInvioMail;
	}

	public String getDestinatarioCc() {
		return this.destinatarioCc;
	}

	public void setDestinatarioCc(String destinatarioCc) {
		this.destinatarioCc = destinatarioCc;
	}

	public String getDestinatarioTo() {
		return this.destinatarioTo;
	}

	public void setDestinatarioTo(String destinatarioTo) {
		this.destinatarioTo = destinatarioTo;
	}

	public Timestamp getDtInserimento() {
		return this.dtInserimento;
	}

	public void setDtInserimento(Timestamp dtInserimento) {
		this.dtInserimento = dtInserimento;
	}

	public Timestamp getDtUltimoTentativo() {
		return this.dtUltimoTentativo;
	}

	public void setDtUltimoTentativo(Timestamp dtUltimoTentativo) {
		this.dtUltimoTentativo = dtUltimoTentativo;
	}

	public Boolean getErroreInvio() {
		return this.erroreInvio;
	}

	public void setErroreInvio(Boolean erroreInvio) {
		this.erroreInvio = erroreInvio;
	}

	public String getEsitoUltimoTentativo() {
		return this.esitoUltimoTentativo;
	}

	public void setEsitoUltimoTentativo(String esitoUltimoTentativo) {
		this.esitoUltimoTentativo = esitoUltimoTentativo;
	}

	public EpaypaDTipoInvioMail getEpaypaDTipoInvioMail() {
		return this.epaypaDTipoInvioMail;
	}

	public void setEpaypaDTipoInvioMail(EpaypaDTipoInvioMail epaypaDTipoInvioMail) {
		this.epaypaDTipoInvioMail = epaypaDTipoInvioMail;
	}

	public Long getIdFlusso() {
		return this.idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

}
