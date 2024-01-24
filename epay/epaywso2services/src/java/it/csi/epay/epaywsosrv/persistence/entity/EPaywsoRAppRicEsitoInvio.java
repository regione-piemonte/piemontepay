/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaywso_r_app_ric_esito_invio database table.
 * 
 */
@Entity
@Table(name="epaywso_r_app_ric_esito_invio")
@NamedQueries({
	@NamedQuery(
			name = "EPaywsoRAppRicEsitoInvio.findAllByIdRichiestaAndIdApp",
			query= "SELECT e "
					+ "FROM EPaywsoRAppRicEsitoInvio e "
					+ "WHERE e.epaywsoTRichiesta.idRichiesta = :idRichiesta "
					+ "  AND e.epaywsoTApplicativo.idApplicativo = :idApp "),
	@NamedQuery(
			name = "EPaywsoRAppRicEsitoInvio.findAllByIdRichiestaAndIdEsitoInvio",
			query= "SELECT e "
					+ "FROM EPaywsoRAppRicEsitoInvio e "
					+ "WHERE e.epaywsoTRichiesta.idRichiesta = :idRichiesta "
					+ "  AND e.epaywsoTEsitoInvio.idEsitoInvio = :idEsitoInvio "),
	@NamedQuery(
			name="EPaywsoRAppRicEsitoInvio.findAll",
			query="SELECT e FROM EPaywsoRAppRicEsitoInvio e")
})
public class EPaywsoRAppRicEsitoInvio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EPaywsoRAppRicEsitoInvioPK id;

	//uni-directional many-to-one association to EPaywsoTApplicativo
	@ManyToOne
	@JoinColumn(name="id_applicativo", updatable=false, insertable=false)
	private EPaywsoTApplicativo epaywsoTApplicativo;

	//uni-directional many-to-one association to EPaywsoTEsitoInvio
	@ManyToOne
	@JoinColumn(name="id_esito_invio", updatable=false, insertable=false)
	private EPaywsoTEsitoInvio epaywsoTEsitoInvio;

	//uni-directional many-to-one association to EPaywsoTRichiesta
	@ManyToOne
	@JoinColumn(name="id_richiesta", updatable=false, insertable=false)
	private EPaywsoTRichiesta epaywsoTRichiesta;

	public EPaywsoRAppRicEsitoInvio() {
	}

	public EPaywsoRAppRicEsitoInvioPK getId() {
		return this.id;
	}

	public void setId(EPaywsoRAppRicEsitoInvioPK id) {
		this.id = id;
	}

	public EPaywsoTApplicativo getEpaywsoTApplicativo() {
		return this.epaywsoTApplicativo;
	}

	public void setEpaywsoTApplicativo(EPaywsoTApplicativo epaywsoTApplicativo) {
		this.epaywsoTApplicativo = epaywsoTApplicativo;
	}

	public EPaywsoTEsitoInvio getEpaywsoTEsitoInvio() {
		return this.epaywsoTEsitoInvio;
	}

	public void setEpaywsoTEsitoInvio(EPaywsoTEsitoInvio epaywsoTEsitoInvio) {
		this.epaywsoTEsitoInvio = epaywsoTEsitoInvio;
	}

	public EPaywsoTRichiesta getEpaywsoTRichiesta() {
		return this.epaywsoTRichiesta;
	}

	public void setEpaywsoTRichiesta(EPaywsoTRichiesta epaywsoTRichiesta) {
		this.epaywsoTRichiesta = epaywsoTRichiesta;
	}

}
