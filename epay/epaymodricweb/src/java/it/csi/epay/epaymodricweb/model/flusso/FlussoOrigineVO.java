/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.flusso;

import java.util.Date;


public class FlussoOrigineVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected Integer contatoreTentativi;

    protected Date dataInserimento;

    protected Date dataOraFlusso;

    protected String ibanRiversamentoFlusso;

    protected Long id;

    protected String identificativoFlusso;

    protected String identificativoIstitutoRicevente;

    protected String identificativoIstitutoRiceventeDescrizione;

    protected String identificativoPsp;

    protected String identificativoPspDescrizione;

    protected Double importoTotalePagamenti;

    protected Integer numeroTotalePagamenti;

    protected Long idStato;

    protected String codiceStato;

    protected String descrizioneStato;

    protected Boolean permettiRielaborazione;
    
    protected Integer annoAccertamento;

    protected Integer numeroAccertamento;
    
    protected String flussoPlurintermediato;

    public FlussoOrigineVO () {
        // TODO Auto-generated constructor stub
    }

    public Long getIdStato () {
        return idStato;
    }

    public void setIdStato ( Long idStato ) {
        this.idStato = idStato;
    }

    public String getCodiceStato () {
        return codiceStato;
    }

    public void setCodiceStato ( String codiceStato ) {
        this.codiceStato = codiceStato;
    }

    public String getDescrizioneStato () {
        return descrizioneStato;
    }

    public void setDescrizioneStato ( String descrizioneStato ) {
        this.descrizioneStato = descrizioneStato;
    }

    public Integer getContatoreTentativi () {
        return contatoreTentativi;
    }

    public void setContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }

    public Date getDataInserimento () {
        return dataInserimento;
    }

    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataOraFlusso () {
        return dataOraFlusso;
    }

    public void setDataOraFlusso ( Date dataOraFlusso ) {
        this.dataOraFlusso = dataOraFlusso;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoIstitutoRicevente () {
        return identificativoIstitutoRicevente;
    }

    public void setIdentificativoIstitutoRicevente ( String identificativoIstitutoRicevente ) {
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public Double getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( Double importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public String getIdentificativoIstitutoRiceventeDescrizione () {
        return identificativoIstitutoRiceventeDescrizione;
    }

    public void setIdentificativoIstitutoRiceventeDescrizione ( String identificativoIstitutoRiceventeDescrizione ) {
        this.identificativoIstitutoRiceventeDescrizione = identificativoIstitutoRiceventeDescrizione;
    }

    public String getIdentificativoPspDescrizione () {
        return identificativoPspDescrizione;
    }

    public void setIdentificativoPspDescrizione ( String identificativoPspDescrizione ) {
        this.identificativoPspDescrizione = identificativoPspDescrizione;
    }

    public Boolean getPermettiRielaborazione () {
        return permettiRielaborazione;
    }

    public void setPermettiRielaborazione ( Boolean permettiRielaborazione ) {
        this.permettiRielaborazione = permettiRielaborazione;
    }
    
    public Integer getAnnoAccertamento() {
		return annoAccertamento;
	}

	public void setAnnoAccertamento(Integer annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}

    public Integer getNumeroAccertamento() {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento(Integer numeroAccertamento) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public String getFlussoPlurintermediato() {
		return flussoPlurintermediato;
	}

	public void setFlussoPlurintermediato(String flussoPlurintermediato) {
		this.flussoPlurintermediato = flussoPlurintermediato;
	}

	
	
	
}
