/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.filter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table ( name = "epaypa_t_rendicontazione" )
public class EpaypaTRendicontazioneFilter implements Serializable {

	private static final long serialVersionUID = -2517157992121519640L;

	@Id
	@Column ( name = "id_flusso" )
	private Long idFlusso;

	@Column ( name = "cod_bic_psp" )
	private String codBicPsp;

	@Column ( name = "cod_id_univoco_mittente" )
	private String codIdUnivocoMittente;

	@Column ( name = "denominazione_mittente" )
	private String denominazioneMittente;

	@Column ( name = "dt_ora_flusso" )
	private Timestamp dtOraFlusso;

	@Column ( name = "dt_regolamento" )
	private Timestamp dtRegolamento;

	@Column ( name = "id_flusso_rendicontazione" )
	private String idFlussoRendicontazione;

	@Column ( name = "id_univoco_regolamento" )
	private String idUnivocoRegolamento;

	@Column ( name = "tipo_id_mittente" )
	private String tipoIdMittente;

	public EpaypaTRendicontazioneFilter () {
	}

	public Long getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( Long idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public String getCodBicPsp () {
		return codBicPsp;
	}

	public void setCodBicPsp ( String codBicPsp ) {
		this.codBicPsp = codBicPsp;
	}

	public String getCodIdUnivocoMittente () {
		return codIdUnivocoMittente;
	}

	public void setCodIdUnivocoMittente ( String codIdUnivocoMittente ) {
		this.codIdUnivocoMittente = codIdUnivocoMittente;
	}

	public String getDenominazioneMittente () {
		return denominazioneMittente;
	}

	public void setDenominazioneMittente ( String denominazioneMittente ) {
		this.denominazioneMittente = denominazioneMittente;
	}

	public Timestamp getDtOraFlusso () {
		return dtOraFlusso;
	}

	public void setDtOraFlusso ( Timestamp dtOraFlusso ) {
		this.dtOraFlusso = dtOraFlusso;
	}

	public Timestamp getDtRegolamento () {
		return dtRegolamento;
	}

	public void setDtRegolamento ( Timestamp dtRegolamento ) {
		this.dtRegolamento = dtRegolamento;
	}

	public String getIdFlussoRendicontazione () {
		return idFlussoRendicontazione;
	}

	public void setIdFlussoRendicontazione ( String idFlussoRendicontazione ) {
		this.idFlussoRendicontazione = idFlussoRendicontazione;
	}

	public String getIdUnivocoRegolamento () {
		return idUnivocoRegolamento;
	}

	public void setIdUnivocoRegolamento ( String idUnivocoRegolamento ) {
		this.idUnivocoRegolamento = idUnivocoRegolamento;
	}

	public String getTipoIdMittente () {
		return tipoIdMittente;
	}

	public void setTipoIdMittente ( String tipoIdMittente ) {
		this.tipoIdMittente = tipoIdMittente;
	}
}
