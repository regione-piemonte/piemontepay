/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;

public class RegistroVersamenti implements Serializable {
	private static final long serialVersionUID = -5914194099045823123L;

	private Long idRegistro;
    private Long idPagamento;
    private Date dataOperazione;
    private String iuv;
    private Anagrafica anagraficaVersante;
    private String risultato;
    private String idTransazione;
    private Integer idStato;
    private String origineInserimento;
    private EsitiRicevuti esitoRicevuto;
    private Integer idOrigineChiamata;
    private String codiceOrigineChiamata;

    private Long idPagamentoSecondario;

    public Long getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    /**
     * @return the idPagamento
     */
    public Long getIdPagamento() {
        return idPagamento;
    }
    /**
     * @param idPagamento the idPagamento to set
     */
    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }
    /**
     * @return the dataOperazione
     */
    public Date getDataOperazione() {
        return dataOperazione;
    }
    /**
     * @param dataOperazione the dataOperazione to set
     */
    public void setDataOperazione(Date dataOperazione) {
        this.dataOperazione = dataOperazione;
    }
    /**
     * @return the anagraficaVersante
     */
    public Anagrafica getAnagraficaVersante() {
        return anagraficaVersante;
    }
    /**
     * @param anagraficaVersante the anagraficaVersante to set
     */
    public void setAnagraficaVersante(Anagrafica anagraficaVersante) {
        this.anagraficaVersante = anagraficaVersante;
    }
    /**
     * @return the risultato
     */
    public String getRisultato() {
        return risultato;
    }
    /**
     * @param risultato the risultato to set
     */
    public void setRisultato(String risultato) {
        this.risultato = risultato;
    }
    /**
     * @return the iuv
     */
    public String getIuv() {
        return iuv;
    }
    /**
     * @param iuv the iuv to set
     */
    public void setIuv(String iuv) {
        this.iuv = iuv;
    }
    /**
     * @return the idTransazione
     */
    public String getIdTransazione() {
        return idTransazione;
    }
    /**
     * @param idTransazione the idTransazione to set
     */
    public void setIdTransazione(String idTransazione) {
        this.idTransazione = idTransazione;
    }
    /**
     * @return the idStato
     */
    public Integer getIdStato() {
        return idStato;
    }
    /**
     * @param idStato the idStato to set
     */
    public void setIdStato(Integer idStato) {
        this.idStato = idStato;
    }
    /**
     * @return the origineInserimento
     */
    public String getOrigineInserimento() {
        return origineInserimento;
    }
    /**
     * @param origineInserimento the origineInserimento to set
     */
    public void setOrigineInserimento(String origineInserimento) {
        this.origineInserimento = origineInserimento;
    }
    public EsitiRicevuti getEsitoRicevuto() {
        return esitoRicevuto;
    }
    public void setEsitoRicevuto(EsitiRicevuti esitoRicevuto) {
        this.esitoRicevuto = esitoRicevuto;
    }

    /**
     * @return the idPagamentoSecondario
     */
    public Long getIdPagamentoSecondario () {
        return idPagamentoSecondario;
    }

    /**
     * @param idPagamento the idPagamentoSecondario to set
     */
    public void setIdPagamentoSecondario ( Long idPagamentoSecondario ) {
        this.idPagamentoSecondario = idPagamentoSecondario;
    }
	public Integer getIdOrigineChiamata () {
		return idOrigineChiamata;
	}

	public void setIdOrigineChiamata ( Integer idOrigineChiamata ) {
		this.idOrigineChiamata = idOrigineChiamata;
	}
	public String getCodiceOrigineChiamata () {
		return codiceOrigineChiamata;
	}
	public void setCodiceOrigineChiamata ( String codiceOrigineChiamata ) {
		this.codiceOrigineChiamata = codiceOrigineChiamata;
	}
}
