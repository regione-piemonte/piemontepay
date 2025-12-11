/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.dto.v1;

import java.io.Serializable;
import java.util.Date;


public class InfoPagamentoAvvisoPagamentoDTO implements Serializable {

	private static final long serialVersionUID = 1875829097240549544L;

	private String oggettoDelPagamento;

	private String pagamentoReteale;

	private Double importo;

	private Date data;

	private String rate;

	private String allaRata;

	private String modalitaPagamento;

	private String modalitaPagamentoEnteCreditore;

	private String urlPagamentoEnteCreditore;

	private String codiceAvviso;

	private String iuv;

	public String getOggettoDelPagamento () {
		return oggettoDelPagamento;
	}

	public void setOggettoDelPagamento ( String oggettoDelPagamento ) {
		this.oggettoDelPagamento = oggettoDelPagamento;
	}

	public String getPagamentoReteale () {
		return pagamentoReteale;
	}

	public void setPagamentoReteale ( String pagamentoReteale ) {
		this.pagamentoReteale = pagamentoReteale;
	}

	public Double getImporto () {
		return importo;
	}

	public void setImporto ( Double importo ) {
		this.importo = importo;
	}

	public Date getData () {
		return data;
	}

	public void setData ( Date data ) {
		this.data = data;
	}

	public String getRate () {
		return rate;
	}

	public void setRate ( String rate ) {
		this.rate = rate;
	}

	public String getAllaRata () {
		return allaRata;
	}

	public void setAllaRata ( String allaRata ) {
		this.allaRata = allaRata;
	}

	public String getModalitaPagamento () {
		return modalitaPagamento;
	}

	public void setModalitaPagamento ( String modalitaPagamento ) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public String getModalitaPagamentoEnteCreditore () {
		return modalitaPagamentoEnteCreditore;
	}

	public void setModalitaPagamentoEnteCreditore ( String modalitaPagamentoEnteCreditore ) {
		this.modalitaPagamentoEnteCreditore = modalitaPagamentoEnteCreditore;
	}

	public String getUrlPagamentoEnteCreditore () {
		return urlPagamentoEnteCreditore;
	}

	public void setUrlPagamentoEnteCreditore ( String urlPagamentoEnteCreditore ) {
		this.urlPagamentoEnteCreditore = urlPagamentoEnteCreditore;
	}

	public String getCodiceAvviso () {
		return codiceAvviso;
	}

	public void setCodiceAvviso ( String codiceAvviso ) {
		this.codiceAvviso = codiceAvviso;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	@Override public String toString () {
		return "InfoPagamentoAvvisoPagamentoDTO{" +
						"oggettoDelPagamento='" + oggettoDelPagamento + '\'' +
						", pagamentoReteale='" + pagamentoReteale + '\'' +
						", importo=" + importo +
						", data=" + data +
						", rate='" + rate + '\'' +
						", allaRata='" + allaRata + '\'' +
						", modalitaPagamento='" + modalitaPagamento + '\'' +
						", modalitaPagamentoEnteCreditore='" + modalitaPagamentoEnteCreditore + '\'' +
						", urlPagamentoEnteCreditore='" + urlPagamentoEnteCreditore + '\'' +
						", codiceAvviso='" + codiceAvviso + '\'' +
						", iuv='" + iuv + '\'' +
						'}';
	}
}
