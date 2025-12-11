/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.dto.v1;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;


public class AvvisoPagamentoDTO implements Serializable {

	private static final long serialVersionUID = -8613139917926560246L;
	private Image ecLogo;

	private String title;

	private String cfEnte;

	private String enteCreditore;

	private String settoreEnte;

	private String infoEnte;

	private String cfDestinatario;

	private String nomeCognomeDestinatario;

	private String indirizzoDestinatario;

	private String datiDestinatario;

	private String pagamentoRateale;

	private Double importo;

	private Date scadenza;

	private String rate;

	private String allaRata;

	private String modalitaPagamento;

	private String modalitaPagamentoEnteCreditore;

	private String urlPagamentoEnteCreditore;

	private Image qrCodeRataUnica;

	private String oggettoPagamento;

	private String cbill;

	private String codiceAvviso;

	private String autorizzazione;

	private String numeroCCPostale;

	private String intestatarioNumeroCCPostale;

	private Image dataMatrixRataUnica;

	private Image pagoPA;

	private Image avvisoDiPagamento;

	private Image canaliDigitali;

	private Image canaliFisici;

	private Image posteItaliane;

	private Image bollettinoPostale;

	private Image euro;

	private Image forbici;

	private boolean showBollettino;

	public Image getEcLogo () {
		return ecLogo;
	}

	public void setEcLogo ( Image ecLogo ) {
		this.ecLogo = ecLogo;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle ( String title ) {
		this.title = title;
	}

	public String getCfEnte () {
		return cfEnte;
	}

	public void setCfEnte ( String cfEnte ) {
		this.cfEnte = cfEnte;
	}

	public String getEnteCreditore () {
		return enteCreditore;
	}

	public void setEnteCreditore ( String enteCreditore ) {
		this.enteCreditore = enteCreditore;
	}

	public String getSettoreEnte () {
		return settoreEnte;
	}

	public void setSettoreEnte ( String settoreEnte ) {
		this.settoreEnte = settoreEnte;
	}

	public String getInfoEnte () {
		return infoEnte;
	}

	public void setInfoEnte ( String infoEnte ) {
		this.infoEnte = infoEnte;
	}

	public String getCfDestinatario () {
		return cfDestinatario;
	}

	public void setCfDestinatario ( String cfDestinatario ) {
		this.cfDestinatario = cfDestinatario;
	}

	public String getNomeCognomeDestinatario () {
		return nomeCognomeDestinatario;
	}

	public void setNomeCognomeDestinatario ( String nomeCognomeDestinatario ) {
		this.nomeCognomeDestinatario = nomeCognomeDestinatario;
	}

	public String getIndirizzoDestinatario () {
		return indirizzoDestinatario;
	}

	public void setIndirizzoDestinatario ( String indirizzoDestinatario ) {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}

	public String getDatiDestinatario () {
		return datiDestinatario;
	}

	public void setDatiDestinatario ( String datiDestinatario ) {
		this.datiDestinatario = datiDestinatario;
	}

	public String getPagamentoRateale () {
		return pagamentoRateale;
	}

	public void setPagamentoRateale ( String pagamentoRateale ) {
		this.pagamentoRateale = pagamentoRateale;
	}

	public Double getImporto () {
		return importo;
	}

	public void setImporto ( Double importo ) {
		this.importo = importo;
	}

	public Date getScadenza () {
		return scadenza;
	}

	public void setScadenza ( Date scadenza ) {
		this.scadenza = scadenza;
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

	public Image getQrCodeRataUnica () {
		return qrCodeRataUnica;
	}

	public void setQrCodeRataUnica ( Image qrCodeRataUnica ) {
		this.qrCodeRataUnica = qrCodeRataUnica;
	}

	public String getOggettoPagamento () {
		return oggettoPagamento;
	}

	public void setOggettoPagamento ( String oggettoPagamento ) {
		this.oggettoPagamento = oggettoPagamento;
	}

	public String getCbill () {
		return cbill;
	}

	public void setCbill ( String cbill ) {
		this.cbill = cbill;
	}

	public String getCodiceAvviso () {
		return codiceAvviso;
	}

	public void setCodiceAvviso ( String codiceAvviso ) {
		this.codiceAvviso = codiceAvviso;
	}

	public String getAutorizzazione () {
		return autorizzazione;
	}

	public void setAutorizzazione ( String autorizzazione ) {
		this.autorizzazione = autorizzazione;
	}

	public String getNumeroCCPostale () {
		return numeroCCPostale;
	}

	public void setNumeroCCPostale ( String numeroCCPostale ) {
		this.numeroCCPostale = numeroCCPostale;
	}

	public String getIntestatarioNumeroCCPostale () {
		return intestatarioNumeroCCPostale;
	}

	public void setIntestatarioNumeroCCPostale ( String intestatarioNumeroCCPostale ) {
		this.intestatarioNumeroCCPostale = intestatarioNumeroCCPostale;
	}

	public Image getDataMatrixRataUnica () {
		return dataMatrixRataUnica;
	}

	public void setDataMatrixRataUnica ( Image dataMatrixRataUnica ) {
		this.dataMatrixRataUnica = dataMatrixRataUnica;
	}

	public Image getPagoPA () {
		return pagoPA;
	}

	public void setPagoPA ( Image pagoPA ) {
		this.pagoPA = pagoPA;
	}

	public Image getAvvisoDiPagamento () {
		return avvisoDiPagamento;
	}

	public void setAvvisoDiPagamento ( Image avvisoDiPagamento ) {
		this.avvisoDiPagamento = avvisoDiPagamento;
	}

	public Image getCanaliDigitali () {
		return canaliDigitali;
	}

	public void setCanaliDigitali ( Image canaliDigitali ) {
		this.canaliDigitali = canaliDigitali;
	}

	public Image getCanaliFisici () {
		return canaliFisici;
	}

	public void setCanaliFisici ( Image canaliFisici ) {
		this.canaliFisici = canaliFisici;
	}

	public Image getPosteItaliane () {
		return posteItaliane;
	}

	public void setPosteItaliane ( Image posteItaliane ) {
		this.posteItaliane = posteItaliane;
	}

	public Image getBollettinoPostale () {
		return bollettinoPostale;
	}

	public void setBollettinoPostale ( Image bollettinoPostale ) {
		this.bollettinoPostale = bollettinoPostale;
	}

	public Image getEuro () {
		return euro;
	}

	public void setEuro ( Image euro ) {
		this.euro = euro;
	}

	public Image getForbici () {
		return forbici;
	}

	public void setForbici ( Image forbici ) {
		this.forbici = forbici;
	}

	public boolean isShowBollettino () {
		return showBollettino;
	}

	public void setShowBollettino ( boolean showBollettino ) {
		this.showBollettino = showBollettino;
	}

	@Override public String toString () {
		return "AvvisoPagamentoDTO{" +
						"ecLogo=" + ecLogo +
						", title='" + title + '\'' +
						", cfEnte='" + cfEnte + '\'' +
						", enteCreditore='" + enteCreditore + '\'' +
						", settoreEnte='" + settoreEnte + '\'' +
						", infoEnte='" + infoEnte + '\'' +
						", cfDestinatario='" + cfDestinatario + '\'' +
						", nomeCognomeDestinatario='" + nomeCognomeDestinatario + '\'' +
						", indirizzoDestinatario='" + indirizzoDestinatario + '\'' +
						", datiDestinatario='" + datiDestinatario + '\'' +
						", pagamentoRateale='" + pagamentoRateale + '\'' +
						", importo=" + importo +
						", scadenza=" + scadenza +
						", rate='" + rate + '\'' +
						", allaRata='" + allaRata + '\'' +
						", modalitaPagamento='" + modalitaPagamento + '\'' +
						", modalitaPagamentoEnteCreditore='" + modalitaPagamentoEnteCreditore + '\'' +
						", urlPagamentoEnteCreditore='" + urlPagamentoEnteCreditore + '\'' +
						", qrCodeRataUnica=" + qrCodeRataUnica +
						", oggettoPagamento='" + oggettoPagamento + '\'' +
						", cbill='" + cbill + '\'' +
						", codiceAvviso='" + codiceAvviso + '\'' +
						", autorizzazione='" + autorizzazione + '\'' +
						", numeroCCPostale='" + numeroCCPostale + '\'' +
						", intestatarioNumeroCCPostale='" + intestatarioNumeroCCPostale + '\'' +
						", dataMatrixRataUnica=" + dataMatrixRataUnica +
						", pagoPA=" + pagoPA +
						", avvisoDiPagamento=" + avvisoDiPagamento +
						", canaliDigitali=" + canaliDigitali +
						", canaliFisici=" + canaliFisici +
						", posteItaliane=" + posteItaliane +
						", bollettinoPostale=" + bollettinoPostale +
						", euro=" + euro +
						", forbici=" + forbici +
						", showBollettino=" + showBollettino +
						'}';
	}
}
