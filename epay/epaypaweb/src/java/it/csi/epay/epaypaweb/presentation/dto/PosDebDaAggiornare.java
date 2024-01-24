/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.dto;

import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class PosDebDaAggiornare implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idFlusso;

	// dati della posizione debitoria da aggiornare - N.B. i dati si riferiscono solo al tipo di aggiornamento modifica
	private Date dataScadenza;

	private Date dataInizioValidita;

	private Date dataFineValidita;

	private BigDecimal importoTotale;

	private String desCausaleVersamento;

	// dati dei componenti importo - N.B. i dati si riferiscono solo al tipo di aggiornamento modifica
	private List<ComponenteImportoDto> componenteImportoDtoList;

	// dati dei riferimento pagamento - N.B. i dati si riferiscono solo al tipo di aggiornamento modifica
	private List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList;

	// dati del soggetto pagatore
	private SoggettoAnagraficoDto soggettoAnagraficoDto;

	public Long getIdFlusso () {
		return idFlusso;
	}

	public void setIdFlusso ( Long idFlusso ) {
		this.idFlusso = idFlusso;
	}

	public Date getDataScadenza () {
		return dataScadenza;
	}

	public void setDataScadenza ( Date dataScadenza ) {
		this.dataScadenza = dataScadenza;
	}

	public Date getDataInizioValidita () {
		return dataInizioValidita;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public BigDecimal getImportoTotale () {
		return importoTotale;
	}

	public void setImportoTotale ( BigDecimal importoTotale ) {
		this.importoTotale = importoTotale;
	}

	public String getDesCausaleVersamento () {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento ( String desCausaleVersamento ) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public List<ComponenteImportoDto> getComponenteImportoDtoList () {
		return componenteImportoDtoList;
	}

	public void setComponenteImportoDtoList ( List<ComponenteImportoDto> componenteImportoDtoList ) {
		this.componenteImportoDtoList = componenteImportoDtoList;
	}

	public List<RiferimentoPagamentoDto> getRiferimentoPagamentoDtoList () {
		return riferimentoPagamentoDtoList;
	}

	public void setRiferimentoPagamentoDtoList ( List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList ) {
		this.riferimentoPagamentoDtoList = riferimentoPagamentoDtoList;
	}

	public SoggettoAnagraficoDto getSoggettoAnagraficoDto () {
		return soggettoAnagraficoDto;
	}

	public void setSoggettoAnagraficoDto ( SoggettoAnagraficoDto soggettoAnagraficoDto ) {
		this.soggettoAnagraficoDto = soggettoAnagraficoDto;
	}

}
