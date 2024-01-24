/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;

public class Errori implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date data;
	private String descrizione;
	private Long id_pagamento;
	private Long id_registro_versamento;
	private String iuv;
	private Long id_transazione;
	private Integer id_stato_errore;
	private String desc_correzione;
	private String applicativo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Long getId_pagamento() {
		return id_pagamento;
	}
	public void setId_pagamento(Long id_pagamento) {
		this.id_pagamento = id_pagamento;
	}
	public Long getId_registro_versamento() {
		return id_registro_versamento;
	}
	public void setId_registro_versamento(Long id_registro_versamento) {
		this.id_registro_versamento = id_registro_versamento;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public Long getId_transazione() {
		return id_transazione;
	}
	public void setId_transazione(Long id_transazione) {
		this.id_transazione = id_transazione;
	}
	public Integer getId_stato_errore() {
		return id_stato_errore;
	}
	public void setId_stato_errore(Integer id_stato_errore) {
		this.id_stato_errore = id_stato_errore;
	}
	public String getDesc_correzione() {
		return desc_correzione;
	}
	public void setDesc_correzione(String desc_correzione) {
		this.desc_correzione = desc_correzione;
	}
	/**
	 * @return the applicativo
	 */
	public String getApplicativo() {
		return applicativo;
	}
	/**
	 * @param applicativo the applicativo to set
	 */
	public void setApplicativo(String applicativo) {
		this.applicativo = applicativo;
	}	

}
