/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

/** dto facade <-> business <-> persistence */
public class PosizioneDebitoriaDaAggiornareDto extends PosizioneDebitoriaDaAggiornareLightDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idFlusso;
	
	// dati della posizione debitoria da aggiornare - N.B. i dati si riferiscono solo al tipo di aggiornamento modifica
	private Date dataScadenza;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private BigDecimal importoTotale;
	private String desCausaleVersamento;

	private BigDecimal importoPrincipale;

	private BigDecimal importoSecondarioAltroEnte;

	// dati dei componenti importo - N.B. i dati si riferiscono solo al tipo di aggiornamento modifica
	private List<ComponenteImportoDto> componenteImportoDtoList;

    // dati dei riferimento pagamento - N.B. i dati si riferiscono solo al tipo di aggiornamento modifica
    private List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList;
    
    
 // dati del soggetto pagatore
    private SoggettoAnagraficoDto soggettoAnagraficoDto;
    
    
	public PosizioneDebitoriaDaAggiornareDto() {
		super();
	}

	public PosizioneDebitoriaDaAggiornareDto(Long id) {
		super(id);
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
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

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getDesCausaleVersamento() {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public List<ComponenteImportoDto> getComponenteImportoDtoList() {
		return componenteImportoDtoList;
	}

	public void setComponenteImportoDtoList(List<ComponenteImportoDto> componenteImportoDtoList) {
		this.componenteImportoDtoList = componenteImportoDtoList;
	}

    public List<RiferimentoPagamentoDto> getRiferimentoPagamentoDtoList () {
        return riferimentoPagamentoDtoList;
    }

    public void setRiferimentoPagamentoDtoList ( List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList ) {
        this.riferimentoPagamentoDtoList = riferimentoPagamentoDtoList;
    }
    
	public SoggettoAnagraficoDto getSoggettoAnagraficoDto() {
		return soggettoAnagraficoDto;
	}

	public void setSoggettoAnagraficoDto(SoggettoAnagraficoDto soggettoAnagraficoDto) {
		this.soggettoAnagraficoDto = soggettoAnagraficoDto;
	}

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public BigDecimal getImportoSecondarioAltroEnte () {
		return importoSecondarioAltroEnte;
	}

	public void setImportoSecondarioAltroEnte ( BigDecimal importoSecondarioAltroEnte ) {
		this.importoSecondarioAltroEnte = importoSecondarioAltroEnte;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idFlusso:").append(idFlusso).append(COMMA);
		s.append("posizioneDebitoriaDaAggiornareDto: { ");
		s.append(super.toSuperString()).append(COMMA);
		s.append("dataScadenza:").append(date2strdatetime(dataScadenza)).append(COMMA);
		s.append("dataInizioValidita:").append(date2strdatetime(dataInizioValidita)).append(COMMA);
		s.append("dataFineValidita:").append(date2strdatetime(dataFineValidita)).append(COMMA);
		s.append("importoTotale:").append(importoTotale).append(COMMA);
		s.append("desCausaleVersamento:").append(quote(desCausaleVersamento));
		s.append(" }").append(COMMA);
        s.append ( "componenteImportoDtoList:" ).append ( componenteImportoDtoList ).append ( COMMA );
        s.append ( "riferimentoPagamentoDtoList:" ).append ( riferimentoPagamentoDtoList ).append ( COMMA );
        s.append ( "soggettoAnagraficoDto:" ).append ( soggettoAnagraficoDto );
        
		s.append(" }");
		return s.toString();
	}

}
