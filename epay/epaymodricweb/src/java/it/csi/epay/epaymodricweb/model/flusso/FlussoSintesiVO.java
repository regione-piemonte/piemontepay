/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.flusso;

public class FlussoSintesiVO implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected String codiceVersamento;
    protected String descrizioneVersamento;
    protected String datiSpecificiDiRiscossione;
    protected Long id;
    protected String identificativoFlusso;
    protected Double importoQuotaAggregazione;
    protected Integer numeroVersamentoQuotaAggregazione;
    protected Integer provvisorioAnno;
    protected Integer provvisorioNumero;
    protected Integer annoAccertamento;
    protected Integer numeroAccertamento;

    public Integer getNumeroAccertamento() {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento(Integer numerooAccertamento) {
		this.numeroAccertamento = numerooAccertamento;
	}

	public Integer getAnnoAccertamento() {
		return annoAccertamento;
	}

	public void setAnnoAccertamento(Integer annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}

	public FlussoSintesiVO() {
        // TODO Auto-generated constructor stub
    }

    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    public void setCodiceVersamento(String codiceVersamento) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getDatiSpecificiDiRiscossione() {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione(String datiSpecificiDiRiscossione) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso(String identificativoFlusso) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public Double getImportoQuotaAggregazione() {
        return importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione(Double importoQuotaAggregazione) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public Integer getNumeroVersamentoQuotaAggregazione() {
        return numeroVersamentoQuotaAggregazione;
    }

    public void setNumeroVersamentoQuotaAggregazione(Integer numeroVersamentoQuotaAggregazione) {
        this.numeroVersamentoQuotaAggregazione = numeroVersamentoQuotaAggregazione;
    }

    public Integer getProvvisorioAnno() {
        return provvisorioAnno;
    }

    public void setProvvisorioAnno(Integer provvisorioAnno) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public Integer getProvvisorioNumero() {
        return provvisorioNumero;
    }

    public void setProvvisorioNumero(Integer provvisorioNumero) {
        this.provvisorioNumero = provvisorioNumero;
    }

    public String getDescrizioneVersamento () {
        return descrizioneVersamento;
    }

    public void setDescrizioneVersamento ( String descrizioneVersamento ) {
        this.descrizioneVersamento = descrizioneVersamento;
    }

}
