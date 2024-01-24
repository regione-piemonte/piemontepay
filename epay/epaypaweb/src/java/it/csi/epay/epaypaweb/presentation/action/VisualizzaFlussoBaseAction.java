/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum;

public class VisualizzaFlussoBaseAction extends EpaypawebBaseAction {

	private static final long serialVersionUID = 1L;

	protected boolean origineHomePerInserimento;
	protected TipoOperativitaEnum operativita;

	// campi in input per inserimento e modifica della testata del flusso in comune tra la lista di carico e il flusso delle posizioni debitorie da aggiornare
	protected String idMessaggio;
	protected Integer idCodVersamento;
	protected Integer numeroElementi;
	protected Integer addendoElemento;

	protected Long idFlusso;
	protected FlussoLightDto flussoSelezionato;

	// parametri tabella
	protected Integer draw;
	protected Integer start;
	protected Integer restartFrom; // per ripartire dalla pagina giusta dopo essere tornati dal dettaglio con scorrimento
	protected Integer length;
	//
	protected String sortingDir;
	protected String sortingCol;
	protected Integer sortingIdx;
	protected Integer pageLength;

	public boolean isOrigineHomePerInserimento() {
		return origineHomePerInserimento;
	}

	public void setOrigineHomePerInserimento(boolean origineHomePerInserimento) {
		this.origineHomePerInserimento = origineHomePerInserimento;
	}

	public TipoOperativitaEnum getOperativita() {
		return operativita;
	}

	public void setOperativita(TipoOperativitaEnum operativita) {
		this.operativita = operativita;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public Integer getIdCodVersamento() {
		return idCodVersamento;
	}

	@RequiredFieldValidator(key = "campo.obbligatorio.codiceversamento")
	public void setIdCodVersamento(Integer idCodVersamento) {
		this.idCodVersamento = idCodVersamento;
	}

	public Integer getNumeroElementi() {
		return numeroElementi;
	}

	public void setNumeroElementi(Integer numeroElementi) {
		this.numeroElementi = numeroElementi;
	}

	public Integer getAddendoElemento() {
		return addendoElemento;
	}

	public void setAddendoElemento(Integer addendoElemento) {
		this.addendoElemento = addendoElemento;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public FlussoLightDto getFlussoSelezionato() {
		return flussoSelezionato;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getRestartFrom() {
		return restartFrom;
	}

	public void setRestartFrom(Integer restartFrom) {
		this.restartFrom = restartFrom;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSortingDir() {
		return sortingDir;
	}

	public void setSortingDir(String sortingDir) {
		this.sortingDir = sortingDir;
	}

	public String getSortingCol() {
		return sortingCol;
	}

	public void setSortingCol(String sortingCol) {
		this.sortingCol = sortingCol;
	}

	public Integer getSortingIdx() {
		return sortingIdx;
	}

	public void setSortingIdx(Integer sortingIdx) {
		this.sortingIdx = sortingIdx;
	}

	public Integer getPageLength() {
		return pageLength;
	}

	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}

}
