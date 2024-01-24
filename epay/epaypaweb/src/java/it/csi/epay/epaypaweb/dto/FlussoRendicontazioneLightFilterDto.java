/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;

/** dto facade <-> business <-> persistence */
public class FlussoRendicontazioneLightFilterDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idProfilo;
	private Integer idEnte;
	private String idMessaggio;
	private TipoFlussoEnum tipoFlusso;
	private StatoFlussoEnum statoFlusso;
	private Boolean pagamentiSpontanei;
	private List<Integer> idCodVersamentoList;
	private Date dataInserimentoDa;
	private Date dataInserimentoA;
	private Date dataUltimaVariazioneDa;
	private Date dataUltimaVariazioneA;
	private String codEsito;
	private String detEsito;

	public Integer getIdProfilo() {
		return idProfilo;
	}

	public void setIdProfilo(Integer idProfilo) {
		this.idProfilo = idProfilo;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public TipoFlussoEnum getTipoFlusso() {
		return tipoFlusso;
	}

	public void setTipoFlusso(TipoFlussoEnum tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}

	public StatoFlussoEnum getStatoFlusso() {
		return statoFlusso;
	}

	public void setStatoFlusso(StatoFlussoEnum statoFlusso) {
		this.statoFlusso = statoFlusso;
	}

	public Boolean getPagamentiSpontanei() {
		return pagamentiSpontanei;
	}

	public void setPagamentiSpontanei(Boolean pagamentiSpontanei) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}

	public List<Integer> getIdCodVersamentoList() {
		return idCodVersamentoList;
	}

	public void setIdCodVersamentoList(List<Integer> idCodVersamentoList) {
		this.idCodVersamentoList = idCodVersamentoList;
	}

	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}

	public void setDataInserimentoDa(Date dataInserimentoDa) {
		this.dataInserimentoDa = dataInserimentoDa;
	}

	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}

	public void setDataInserimentoA(Date dataInserimentoA) {
		this.dataInserimentoA = dataInserimentoA;
	}

	public Date getDataUltimaVariazioneDa() {
		return dataUltimaVariazioneDa;
	}

	public void setDataUltimaVariazioneDa(Date dataUltimaVariazioneDa) {
		this.dataUltimaVariazioneDa = dataUltimaVariazioneDa;
	}

	public Date getDataUltimaVariazioneA() {
		return dataUltimaVariazioneA;
	}

	public void setDataUltimaVariazioneA(Date dataUltimaVariazioneA) {
		this.dataUltimaVariazioneA = dataUltimaVariazioneA;
	}

	public String getCodEsito() {
		return codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDetEsito() {
		return detEsito;
	}

	public void setDetEsito(String detEsito) {
		this.detEsito = detEsito;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idProfilo:").append(idProfilo).append(COMMA);
		s.append("idEnte:").append(idEnte).append(COMMA);
		s.append("idMessaggio:").append(quote(idMessaggio)).append(COMMA);
		s.append("tipoFlusso:").append(tipoFlusso).append(COMMA);
		s.append("statoFlusso:").append(statoFlusso).append(COMMA);
		s.append("pagamentiSpontanei:").append(pagamentiSpontanei).append(COMMA);
		s.append("idCodVersamentoList:").append(idCodVersamentoList).append(COMMA);
		s.append("dataInserimentoDa:").append(date2strdatetime(dataInserimentoDa)).append(COMMA);
		s.append("dataInserimentoA:").append(date2strdatetime(dataInserimentoA)).append(COMMA);
		s.append("dataUltimaVariazioneDa:").append(date2strdatetime(dataUltimaVariazioneDa)).append(COMMA);
		s.append("dataUltimaVariazioneA:").append(date2strdatetime(dataUltimaVariazioneA)).append(COMMA);
		s.append("codEsito:").append(quote(codEsito)).append(COMMA);
		s.append("detEsito:").append(quote(detEsito));
		s.append(" }");
		return s.toString();
	}

}
