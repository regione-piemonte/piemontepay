/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_d_tipo_agg_posizione_debitoria database table.
 * 
 */
@Entity
@Table(name="epaypa_d_tipo_agg_posizione_debitoria")
@NamedQuery(name="EpaypaDTipoAggPosizioneDebitoria.findAll", query="SELECT e FROM EpaypaDTipoAggPosizioneDebitoria e")
public class EpaypaDTipoAggPosizioneDebitoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_agg_posizione_debitoria")
	private String idTipoAggPosizioneDebitoria;

	private String descrizione;

	public EpaypaDTipoAggPosizioneDebitoria() {
	}

	public String getIdTipoAggPosizioneDebitoria() {
		return this.idTipoAggPosizioneDebitoria;
	}

	public void setIdTipoAggPosizioneDebitoria(String idTipoAggPosizioneDebitoria) {
		this.idTipoAggPosizioneDebitoria = idTipoAggPosizioneDebitoria;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
