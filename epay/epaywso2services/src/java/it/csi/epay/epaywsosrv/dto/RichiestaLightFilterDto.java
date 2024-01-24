/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;
import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.csi.epay.epaywsosrv.enumeration.StatoRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;

public class RichiestaLightFilterDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idMessaggio;
	private String codFiscaleEnte;
	private TipoRichiestaEnum tipoRichiestaEnum;
	private Boolean pagamentoSpontaneo;
	private List<StatoRichiestaEnum> statoRichiestaEnumList;
	private List<String> codVersamentoList;
	private Date dataInserimentoRichiestaDa;
	private Date dataInserimentoRichiestaA;
	private Date dataInvioAlDestinatarioDa;
	private Date dataInvioAlDestinatarioA;

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public TipoRichiestaEnum getTipoRichiestaEnum() {
		return tipoRichiestaEnum;
	}

	public void setTipoRichiestaEnum(TipoRichiestaEnum tipoRichiestaEnum) {
		this.tipoRichiestaEnum = tipoRichiestaEnum;
	}

	public Boolean getPagamentoSpontaneo() {
		return pagamentoSpontaneo;
	}

	public void setPagamentoSpontaneo(Boolean pagamentoSpontaneo) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	public List<Integer> getIdStatoRichiestaList() {
		List<Integer> idList = new ArrayList<Integer>();
		for (StatoRichiestaEnum statoRichiestaEnum : statoRichiestaEnumList) {
			idList.add(statoRichiestaEnum.getId());
		}
		return idList;
	}

	public List<StatoRichiestaEnum> getStatoRichiestaEnumList() {
		return statoRichiestaEnumList;
	}

	public void setStatoRichiestaEnumList(List<StatoRichiestaEnum> statoRichiestaEnumList) {
		this.statoRichiestaEnumList = statoRichiestaEnumList;
	}

	public List<String> getCodVersamentoList() {
		return codVersamentoList;
	}

	public void setCodVersamentoList(List<String> codVersamentoList) {
		this.codVersamentoList = codVersamentoList;
	}

	public Date getDataInserimentoRichiestaDa() {
		return dataInserimentoRichiestaDa;
	}

	public void setDataInserimentoRichiestaDa(Date dataInserimentoRichiestaDa) {
		this.dataInserimentoRichiestaDa = dataInserimentoRichiestaDa;
	}

	public Date getDataInserimentoRichiestaA() {
		return dataInserimentoRichiestaA;
	}

	public void setDataInserimentoRichiestaA(Date dataInserimentoRichiestaA) {
		this.dataInserimentoRichiestaA = dataInserimentoRichiestaA;
	}

	public Date getDataInvioAlDestinatarioDa() {
		return dataInvioAlDestinatarioDa;
	}

	public void setDataInvioAlDestinatarioDa(Date dataInvioAlDestinatarioDa) {
		this.dataInvioAlDestinatarioDa = dataInvioAlDestinatarioDa;
	}

	public Date getDataInvioAlDestinatarioA() {
		return dataInvioAlDestinatarioA;
	}

	public void setDataInvioAlDestinatarioA(Date dataInvioAlDestinatarioA) {
		this.dataInvioAlDestinatarioA = dataInvioAlDestinatarioA;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idMessaggio:").append(quote(idMessaggio)).append(COMMA);
		s.append("codFiscaleEnte:").append(quote(codFiscaleEnte)).append(COMMA);
		s.append("tipoRichiestaEnum:").append(tipoRichiestaEnum).append(COMMA);
		s.append("pagamentoSpontaneo:").append(pagamentoSpontaneo).append(COMMA);
		s.append("statoRichiestaEnumList:").append(statoRichiestaEnumList).append(COMMA);
		s.append("codVersamentoList:").append(codVersamentoList).append(COMMA);
		s.append("dataInserimentoRichiestaDa:").append(date2strdatetime(dataInserimentoRichiestaDa)).append(COMMA);
		s.append("dataInserimentoRichiestaA:").append(date2strdatetime(dataInserimentoRichiestaA)).append(COMMA);
		s.append("dataInvioAlDestinatarioDa:").append(date2strdatetime(dataInvioAlDestinatarioDa)).append(COMMA);
		s.append("dataInvioAlDestinatarioA:").append(date2strdatetime(dataInvioAlDestinatarioA));
		s.append(" }");
		return s.toString();
	}
}
