/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv;

import java.util.Date;

public class IuvOtticiDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date data_creazione;
	private String iuv_ottico;
	private String iuv_standard;
	private String ente_id;
	private String cod_versamento;
	private String application_id;
	private Date data_riconciliazione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_creazione() {
		return (Date) data_creazione.clone();
	}

	public void setData_creazione(Date data_creazione) {
		this.data_creazione = (Date) data_creazione.clone();
	}

	public String getIuv_ottico() {
		return iuv_ottico;
	}

	public void setIuv_ottico(String iuv_ottico) {
		this.iuv_ottico = iuv_ottico;
	}

	public String getIuv_standard() {
		return iuv_standard;
	}

	public String getEnte_id() {
		return ente_id;
	}

	public void setEnte_id(String ente_id) {
		this.ente_id = ente_id;
	}

	public void setIuv_standard(String iuv_standard) {
		this.iuv_standard = iuv_standard;
	}

	public String getCod_versamento() {
		return cod_versamento;
	}

	public void setCod_versamento(String cod_versamento) {
		this.cod_versamento = cod_versamento;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public Date getData_riconciliazione() {
		return (Date) data_riconciliazione.clone();
	}

	public void setData_riconciliazione(Date data_riconciliazione) {
		this.data_riconciliazione = (Date) data_riconciliazione.clone();
	}

}
