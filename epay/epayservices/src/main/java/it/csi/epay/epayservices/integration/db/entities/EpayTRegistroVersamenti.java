/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_registro_versamenti database table.
 *
 */
@Entity
@Table(name="epay_t_registro_versamenti")
public class EpayTRegistroVersamenti implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAY_T_REGISTRO_VERSAMENTI_IDREGISTRO_GENERATOR", allocationSize=1, sequenceName="EPAY_T_REGISTRO_VERSAMENTI_ID_REGISTRO_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_REGISTRO_VERSAMENTI_IDREGISTRO_GENERATOR")
    @Column(name="id_registro", unique=true, nullable=false)
    private Long idRegistro;

    @Column(name="data_operazione", nullable=false)
    private Timestamp dataOperazione;

    @Column(name="desc_esito_pagamento", length=255)
    private String descEsitoPagamento;

    @Column(length=25)
    private String iuv;

    @Column(name="origine_inserimento", length=255)
    private String origineInserimento;

	//bi-directional many-to-one association to EpayDOrigineChiamata
	@ManyToOne
	@JoinColumn(name="id_origine_chiamata")
	private EpayDOrigineChiamata epayDOrigineChiamata;

	//bi-directional many-to-one association to EpayTEsitiRicevuti
    @OneToMany(mappedBy="epayTRegistroVersamenti", cascade=CascadeType.ALL)
    private List<EpayTEsitiRicevuti> epayTEsitiRicevutis;

    //bi-directional many-to-one association to EpayDStatoPagamento
    @ManyToOne
    @JoinColumn(name="id_stato", nullable=false)
    private EpayDStatoPagamento epayDStatoPagamento;

    //bi-directional many-to-one association to EpayTAnagrafica
    @ManyToOne
    @JoinColumn(name="id_anagrafica_versante")
    private EpayTAnagrafica epayTAnagrafica;

    //bi-directional many-to-one association to EpayTPagamento
    @ManyToOne
    @JoinColumn(name="id_pagamento", nullable=false)
    private EpayTPagamento epayTPagamento;

    //bi-directional many-to-one association to EpayTTransazioneMdp
    @ManyToOne
    @JoinColumn(name="id_transazione")
    private EpayTTransazioneMdp epayTTransazioneMdp;

    //bi-directional many-to-one association to EpayTRt
    @OneToMany(mappedBy="epayTRegistroVersamenti", cascade=CascadeType.ALL)
    private List<EpayTRt> epayTRts;

    //bi-directional many-to-one association to EpayTPagamento
    @ManyToOne
    @JoinColumn ( name = "id_pagamento_secondario" )
    private EpayTPagamentoSecondario epayTPagamentoSecondario;

    public EpayTRegistroVersamenti() {
    }

    public Long getIdRegistro() {
        return this.idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Timestamp getDataOperazione() {
        return this.dataOperazione;
    }

    public void setDataOperazione(Timestamp dataOperazione) {
        this.dataOperazione = dataOperazione;
    }

    public String getDescEsitoPagamento() {
        return this.descEsitoPagamento;
    }

    public void setDescEsitoPagamento(String descEsitoPagamento) {
        this.descEsitoPagamento = descEsitoPagamento;
    }

    public String getIuv() {
        return this.iuv;
    }

    public void setIuv(String iuv) {
        this.iuv = iuv;
    }

    public String getOrigineInserimento() {
        return this.origineInserimento;
    }

    public void setOrigineInserimento(String origineInserimento) {
        this.origineInserimento = origineInserimento;
    }

	public EpayDOrigineChiamata getEpayDOrigineChiamata() {
		return this.epayDOrigineChiamata;
	}

	public void setEpayDOrigineChiamata(EpayDOrigineChiamata epayDOrigineChiamata) {
		this.epayDOrigineChiamata = epayDOrigineChiamata;
	}

	public List<EpayTEsitiRicevuti> getEpayTEsitiRicevutis() {
        return this.epayTEsitiRicevutis;
    }

    public void setEpayTEsitiRicevutis(List<EpayTEsitiRicevuti> epayTEsitiRicevutis) {
        this.epayTEsitiRicevutis = epayTEsitiRicevutis;
    }

    public EpayTEsitiRicevuti addEpayTEsitiRicevuti(EpayTEsitiRicevuti epayTEsitiRicevuti) {
        getEpayTEsitiRicevutis().add(epayTEsitiRicevuti);
        epayTEsitiRicevuti.setEpayTRegistroVersamenti(this);

        return epayTEsitiRicevuti;
    }

    public EpayTEsitiRicevuti removeEpayTEsitiRicevuti(EpayTEsitiRicevuti epayTEsitiRicevuti) {
        getEpayTEsitiRicevutis().remove(epayTEsitiRicevuti);
        epayTEsitiRicevuti.setEpayTRegistroVersamenti(null);

        return epayTEsitiRicevuti;
    }

    public EpayDStatoPagamento getEpayDStatoPagamento() {
        return this.epayDStatoPagamento;
    }

    public void setEpayDStatoPagamento(EpayDStatoPagamento epayDStatoPagamento) {
        this.epayDStatoPagamento = epayDStatoPagamento;
    }

    public EpayTAnagrafica getEpayTAnagrafica() {
        return this.epayTAnagrafica;
    }

    public void setEpayTAnagrafica(EpayTAnagrafica epayTAnagrafica) {
        this.epayTAnagrafica = epayTAnagrafica;
    }

    public EpayTPagamento getEpayTPagamento() {
        return this.epayTPagamento;
    }

    public void setEpayTPagamento(EpayTPagamento epayTPagamento) {
        this.epayTPagamento = epayTPagamento;
    }

    public EpayTTransazioneMdp getEpayTTransazioneMdp() {
        return this.epayTTransazioneMdp;
    }

    public void setEpayTTransazioneMdp(EpayTTransazioneMdp epayTTransazioneMdp) {
        this.epayTTransazioneMdp = epayTTransazioneMdp;
    }

    public List<EpayTRt> getEpayTRts() {
        return this.epayTRts;
    }

    public void setEpayTRts(List<EpayTRt> epayTRts) {
        this.epayTRts = epayTRts;
    }

    public EpayTRt addEpayTRt(EpayTRt epayTRt) {
        getEpayTRts().add(epayTRt);
        epayTRt.setEpayTRegistroVersamenti(this);

        return epayTRt;
    }

    public EpayTRt removeEpayTRt(EpayTRt epayTRt) {
        getEpayTRts().remove(epayTRt);
        epayTRt.setEpayTRegistroVersamenti(null);

        return epayTRt;
    }

    public EpayTPagamentoSecondario getEpayTPagamentoSecondario () {
        return this.epayTPagamentoSecondario;
    }

    public void setEpayTPagamentoSecondario ( EpayTPagamentoSecondario epayTPagamentoSecondario ) {
        this.epayTPagamentoSecondario = epayTPagamentoSecondario;
    }

}
