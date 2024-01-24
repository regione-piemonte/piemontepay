/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaywso_r_app_tiporic_ep database table.
 * 
 */
@Entity
@Table(name="epaywso_r_app_tiporic_ep")
@NamedQueries({
	@NamedQuery(
			name = "EPaywsoRAppTiporicEp.findAllByIdApp",
			query= "SELECT e "
					+ "FROM EPaywsoRAppTiporicEp e "
					+ "WHERE e.ePaywsoTApplicativo.idApplicativo = :idApplicativo "
					+ "  AND e.ePaywsoTApplicativo.dtInizioValidita <= :dt AND (e.ePaywsoTApplicativo.dtFineValidita IS NULL OR :dt <= e.ePaywsoTApplicativo.dtFineValidita) "
					+ "  AND e.ePaywsoTEndpoint   .dtInizioValidita <= :dt AND (e.ePaywsoTEndpoint   .dtFineValidita IS NULL OR :dt <= e.ePaywsoTEndpoint   .dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoRAppTiporicEp.findAllByIdTipoRichista",
			query= "SELECT e "
					+ "FROM EPaywsoRAppTiporicEp e "
					+ "WHERE e.ePaywsoDTipoRichiesta.idTipoRichiesta = :idTipoRichiesta "
					+ "  AND e.ePaywsoTApplicativo.dtInizioValidita <= :dt AND (e.ePaywsoTApplicativo.dtFineValidita IS NULL OR :dt <= e.ePaywsoTApplicativo.dtFineValidita) "
					+ "  AND e.ePaywsoTEndpoint   .dtInizioValidita <= :dt AND (e.ePaywsoTEndpoint   .dtFineValidita IS NULL OR :dt <= e.ePaywsoTEndpoint   .dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoRAppTiporicEp.findAllByIdTipoRichistaAndIdApp",
			query= "SELECT e "
					+ "FROM EPaywsoRAppTiporicEp e "
					+ "WHERE e.ePaywsoDTipoRichiesta.idTipoRichiesta = :idTipoRichiesta "
					+ "  AND e.ePaywsoTApplicativo.idApplicativo = :idApp "
					+ "  AND e.ePaywsoTApplicativo.dtInizioValidita <= :dt AND (e.ePaywsoTApplicativo.dtFineValidita IS NULL OR :dt <= e.ePaywsoTApplicativo.dtFineValidita) "
					+ "  AND e.ePaywsoTEndpoint   .dtInizioValidita <= :dt AND (e.ePaywsoTEndpoint   .dtFineValidita IS NULL OR :dt <= e.ePaywsoTEndpoint   .dtFineValidita)"),
	@NamedQuery(
			name = "EPaywsoRAppTiporicEp.findAllByIdTipoRichistaAndIdApps",
			query= "SELECT e "
					+ "FROM EPaywsoRAppTiporicEp e "
					+ "WHERE e.ePaywsoDTipoRichiesta.idTipoRichiesta = :idTipoRichiesta "
					+ "  AND e.ePaywsoTApplicativo.idApplicativo IN (:idApps) "
					+ "  AND e.ePaywsoTApplicativo.dtInizioValidita <= :dt AND (e.ePaywsoTApplicativo.dtFineValidita IS NULL OR :dt <= e.ePaywsoTApplicativo.dtFineValidita) "
					+ "  AND e.ePaywsoTEndpoint   .dtInizioValidita <= :dt AND (e.ePaywsoTEndpoint   .dtFineValidita IS NULL OR :dt <= e.ePaywsoTEndpoint   .dtFineValidita)"),
	@NamedQuery(
			name ="EPaywsoRAppTiporicEp.findAll",
			query="SELECT e FROM EPaywsoRAppTiporicEp e")
})
public class EPaywsoRAppTiporicEp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EPaywsoRAppTiporicEpPK id;

	//uni-directional many-to-one association to EPaywsoDTipoRichiesta
	@ManyToOne
	@JoinColumn(name="id_tipo_richiesta", updatable=false, insertable=false)
	private EPaywsoDTipoRichiesta ePaywsoDTipoRichiesta;

	//uni-directional many-to-one association to EPaywsoTApplicativo
	@ManyToOne
	@JoinColumn(name="id_applicativo", updatable=false, insertable=false)
	private EPaywsoTApplicativo ePaywsoTApplicativo;

	//uni-directional many-to-one association to EPaywsoTEndpoint
	@ManyToOne
	@JoinColumn(name="id_endpoint", updatable=false, insertable=false)
	private EPaywsoTEndpoint ePaywsoTEndpoint;

	public EPaywsoRAppTiporicEp() {
	}

	public EPaywsoRAppTiporicEpPK getId() {
		return this.id;
	}

	public void setId(EPaywsoRAppTiporicEpPK id) {
		this.id = id;
	}

	public EPaywsoDTipoRichiesta getEPaywsoDTipoRichiesta() {
		return this.ePaywsoDTipoRichiesta;
	}

	public void setEPaywsoDTipoRichiesta(EPaywsoDTipoRichiesta ePaywsoDTipoRichiesta) {
		this.ePaywsoDTipoRichiesta = ePaywsoDTipoRichiesta;
	}

	public EPaywsoTApplicativo getEPaywsoTApplicativo() {
		return this.ePaywsoTApplicativo;
	}

	public void setEPaywsoTApplicativo(EPaywsoTApplicativo ePaywsoTApplicativo) {
		this.ePaywsoTApplicativo = ePaywsoTApplicativo;
	}

	public EPaywsoTEndpoint getEPaywsoTEndpoint() {
		return this.ePaywsoTEndpoint;
	}

	public void setEPaywsoTEndpoint(EPaywsoTEndpoint ePaywsoTEndpoint) {
		this.ePaywsoTEndpoint = ePaywsoTEndpoint;
	}

}
