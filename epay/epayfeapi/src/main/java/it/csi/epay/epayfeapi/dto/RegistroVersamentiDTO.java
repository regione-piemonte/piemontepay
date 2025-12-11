/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings ( "unused" )
public class RegistroVersamentiDTO implements Serializable {

	private static final long serialVersionUID = -2124598341365368551L;

	private Long idRegistro;

	private Long idPagamento;

	private Date dataOperazione;

	private String iuv;

	private AnagraficaDTO anagraficaVersante;

	private String risultato;

	private String idTransazione;

	private Integer idStato;

	private String origineInserimento;

	private Integer idOrigineChiamata;

	private EsitiRicevutiDTO esitoRicevuto;

	private Long idPagamentoSecondario;

	public Long getIdRegistro () {
		return idRegistro;
	}

	public void setIdRegistro ( Long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	public Long getIdPagamento () {
		return idPagamento;
	}

	public void setIdPagamento ( Long idPagamento ) {
		this.idPagamento = idPagamento;
	}

	public Date getDataOperazione () {
		return dataOperazione;
	}

	public void setDataOperazione ( Date dataOperazione ) {
		this.dataOperazione = dataOperazione;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public AnagraficaDTO getAnagraficaVersante () {
		return anagraficaVersante;
	}

	public void setAnagraficaVersante ( AnagraficaDTO anagraficaVersante ) {
		this.anagraficaVersante = anagraficaVersante;
	}

	public String getRisultato () {
		return risultato;
	}

	public void setRisultato ( String risultato ) {
		this.risultato = risultato;
	}

	public String getIdTransazione () {
		return idTransazione;
	}

	public void setIdTransazione ( String idTransazione ) {
		this.idTransazione = idTransazione;
	}

	public Integer getIdStato () {
		return idStato;
	}

	public void setIdStato ( Integer idStato ) {
		this.idStato = idStato;
	}

	public String getOrigineInserimento () {
		return origineInserimento;
	}

	public void setOrigineInserimento ( String origineInserimento ) {
		this.origineInserimento = origineInserimento;
	}

	public Integer getIdOrigineChiamata () {
		return idOrigineChiamata;
	}

	public void setIdOrigineChiamata ( Integer idOrigineChiamata ) {
		this.idOrigineChiamata = idOrigineChiamata;
	}

	public EsitiRicevutiDTO getEsitoRicevuto () {
		return esitoRicevuto;
	}

	public void setEsitoRicevuto ( EsitiRicevutiDTO esitoRicevuto ) {
		this.esitoRicevuto = esitoRicevuto;
	}

	public Long getIdPagamentoSecondario () {
		return idPagamentoSecondario;
	}

	public void setIdPagamentoSecondario ( Long idPagamentoSecondario ) {
		this.idPagamentoSecondario = idPagamentoSecondario;
	}

	@Override
	public String toString () {
		return "RegistroVersamentiDTO{" +
						"idRegistro=" + idRegistro +
						", idPagamento=" + idPagamento +
						", dataOperazione=" + dataOperazione +
						", iuv='" + iuv + '\'' +
						", anagraficaVersante=" + anagraficaVersante +
						", risultato='" + risultato + '\'' +
						", idTransazione='" + idTransazione + '\'' +
						", idStato=" + idStato +
						", origineInserimento='" + origineInserimento + '\'' +
						", idOrigineChiamata='" + idOrigineChiamata + '\'' +
						", esitoRicevuto=" + esitoRicevuto +
						", idPagamentoSecondario=" + idPagamentoSecondario +
						'}';
	}
}
