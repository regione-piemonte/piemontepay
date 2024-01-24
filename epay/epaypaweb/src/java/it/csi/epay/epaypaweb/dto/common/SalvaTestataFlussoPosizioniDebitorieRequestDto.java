/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import java.util.Date;

import it.csi.epay.epaypaweb.dto.FlussoDto;

public class SalvaTestataFlussoPosizioniDebitorieRequestDto extends PrincipalDto {

	private static final long serialVersionUID = 1L;

	private FlussoDto testataFlusso;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	
	public SalvaTestataFlussoPosizioniDebitorieRequestDto(String ipAddress,  Long idUtente,
			String codiceFiscaleUtente, String codiceApplicazione, FlussoDto testataFlusso, Date dataInizioValidita,
			Date dataFineValidita) {
		super(ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione);
		this.testataFlusso = testataFlusso;
		this.dataInizioValidita = dataInizioValidita;
		this.dataFineValidita = dataFineValidita;
	}

	public FlussoDto getTestataFlusso() {
		return testataFlusso;
	}

	public void setTestataFlusso(FlussoDto testataFlusso) {
		this.testataFlusso = testataFlusso;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	} 
}
