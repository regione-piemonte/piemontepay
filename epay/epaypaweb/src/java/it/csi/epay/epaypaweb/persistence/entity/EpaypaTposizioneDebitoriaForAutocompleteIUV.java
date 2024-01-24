/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table ( name = "epaypa_t_posizione_debitoria" )
public class EpaypaTposizioneDebitoriaForAutocompleteIUV implements Serializable {

	private static final long serialVersionUID = 2699937188917077719L;

	@Id
	@Column ( name = "id_posizione_debitoria" )
	private Long idPosizioneDebitoria;

	@Column ( name = "id_flusso" )
	private Long idFlusso;

	@Column ( name = "id_posizione_debitoria_est" )
	private String idPosizioneDebitoriaEst;

	@ManyToOne
	@JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
	private EpaypaTFlusso epaypaTFlusso;

	@Column ( name = "iuv" )
	private String iuv;

	public Long getIdPosizioneDebitoria () {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria ( Long idPosizioneDebitoria ) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public Long getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( Long idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public String getIdPosizioneDebitoriaEst () {
		return idPosizioneDebitoriaEst;
	}

	public void setIdPosizioneDebitoriaEst ( String idPosizioneDebitoriaEst ) {
		this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
	}

	public EpaypaTFlusso getEpaypaTFlusso () {
		return epaypaTFlusso;
	}

	public void setEpaypaTFlusso ( EpaypaTFlusso epaypaTFlusso ) {
		this.epaypaTFlusso = epaypaTFlusso;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}
}
