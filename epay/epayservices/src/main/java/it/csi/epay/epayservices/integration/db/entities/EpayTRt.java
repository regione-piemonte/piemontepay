/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the epay_t_rt database table.
 *
 */
@Entity
@Table(name="epay_t_rt")
@NamedQuery(name="EpayTRt.findAll", query="SELECT e FROM EpayTRt e")
public class EpayTRt implements Serializable {

	private static final long serialVersionUID = 3412244832751463017L;

	@Id
    @SequenceGenerator(name="EPAY_T_RT_IDRT_GENERATOR", allocationSize=1, sequenceName="EPAY_T_RT_ID_RT_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_RT_IDRT_GENERATOR")
    @Column(name="id_rt", unique=true, nullable=false)
    private Long idRt;

    @Column(name="cod_esito_pagamento")
    private Integer codEsitoPagamento;

    @Column(name="codice_contesto_pagamento")
    private String codiceContestoPagamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dataora_msg_ricevuta")
	private Date dataoraMsgRicevuta;

    @Column(name="desc_esito_pagamento", length=255)
    private String descEsitoPagamento;

    @Column(name="id_applicazione", length=50)
    private String idApplicazione;

    @Column(name="id_msg_ricevuta", length=35)
    private String idMsgRicevuta;

    @Column(name="id_msg_richiesta", length=35)
    private String idMsgRichiesta;

    //bi-directional many-to-one association to EpayTRegistroVersamenti
    @ManyToOne
    @JoinColumn(name="id_registro", nullable=false)
    private EpayTRegistroVersamenti epayTRegistroVersamenti;

    @Column(name="id_rr")
    private Integer idRr;

    @Column(name="id_transazione", length=30)
    private String idTransazione;

    @Column(name="identificativo_dominio")
    private String identificativoDominio;

    @Column(name="importo_totale_pagato", nullable=true, precision=10, scale=2)
    private BigDecimal importoTotalePagato;

    @Column(name="iuv", length=35)
    private String iuv;

    @Column(name="ricevuta_pdf")
    private byte[] ricevutaPdf;

    @Column(name="rt_xml", nullable=false)
    private byte[] rtXml;

    @Column(name="tipo_firma", length=10)
    private String tipoFirma;

	//bi-directional many-to-one association to EpayTQuietanzaDaElaborare
	@OneToMany(mappedBy="epayTRt")
	private List<EpayTQuietanzaDaElaborare> epayTQuietanzaDaElaborares;

	public EpayTRt() {
	}

    public Long getIdRt() {
        return this.idRt;
    }

    public void setIdRt(Long idRt) {
        this.idRt = idRt;
    }

    public Integer getCodEsitoPagamento() {
        return this.codEsitoPagamento;
    }

    public void setCodEsitoPagamento(Integer codEsitoPagamento) {
        this.codEsitoPagamento = codEsitoPagamento;
    }

    public String getCodiceContestoPagamento() {
        return this.codiceContestoPagamento;
    }

    public void setCodiceContestoPagamento(String codiceContestoPagamento) {
        this.codiceContestoPagamento = codiceContestoPagamento;
    }

    public Date getDataoraMsgRicevuta() {
        return this.dataoraMsgRicevuta;
    }

    public void setDataoraMsgRicevuta(Date dataoraMsgRicevuta) {
        this.dataoraMsgRicevuta = dataoraMsgRicevuta;
    }

    public String getDescEsitoPagamento() {
        return this.descEsitoPagamento;
    }

    public void setDescEsitoPagamento(String descEsitoPagamento) {
        this.descEsitoPagamento = descEsitoPagamento;
    }

    public String getIdApplicazione() {
        return this.idApplicazione;
    }

    public void setIdApplicazione(String idApplicazione) {
        this.idApplicazione = idApplicazione;
    }

    public String getIdMsgRicevuta() {
        return this.idMsgRicevuta;
    }

    public void setIdMsgRicevuta(String idMsgRicevuta) {
        this.idMsgRicevuta = idMsgRicevuta;
    }

    public String getIdMsgRichiesta() {
        return this.idMsgRichiesta;
    }

    public void setIdMsgRichiesta(String idMsgRichiesta) {
        this.idMsgRichiesta = idMsgRichiesta;
    }

    public EpayTRegistroVersamenti getEpayTRegistroVersamenti() {
        return epayTRegistroVersamenti;
    }

    public void setEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
        this.epayTRegistroVersamenti = epayTRegistroVersamenti;
    }

    public Integer getIdRr() {
        return this.idRr;
    }

    public void setIdRr(Integer idRr) {
        this.idRr = idRr;
    }

    public String getIdTransazione() {
        return this.idTransazione;
    }

    public void setIdTransazione(String idTransazione) {
        this.idTransazione = idTransazione;
    }

    public String getIdentificativoDominio() {
        return this.identificativoDominio;
    }

    public void setIdentificativoDominio(String identificativoDominio) {
        this.identificativoDominio = identificativoDominio;
    }

    public BigDecimal getImportoTotalePagato() {
        return this.importoTotalePagato;
    }

    public void setImportoTotalePagato(BigDecimal importoTotalePagato) {
        this.importoTotalePagato = importoTotalePagato;
    }

    public String getIuv() {
        return this.iuv;
    }

    public void setIuv(String iuv) {
        this.iuv = iuv;
    }

    public byte[] getRicevutaPdf() {
        return this.ricevutaPdf;
    }

    public void setRicevutaPdf(byte[] ricevutaPdf) {
        this.ricevutaPdf = ricevutaPdf;
    }

    public byte[] getRtXml() {
        return this.rtXml;
    }

    public void setRtXml(byte[] rtXml) {
        this.rtXml = rtXml;
    }

    public String getTipoFirma() {
        return this.tipoFirma;
    }

    public void setTipoFirma(String tipoFirma) {
        this.tipoFirma = tipoFirma;
    }

	public List<EpayTQuietanzaDaElaborare> getEpayTQuietanzaDaElaborares() {
		return this.epayTQuietanzaDaElaborares;
	}

	public void setEpayTQuietanzaDaElaborares(List<EpayTQuietanzaDaElaborare> epayTQuietanzaDaElaborares) {
		this.epayTQuietanzaDaElaborares = epayTQuietanzaDaElaborares;
	}

	public EpayTQuietanzaDaElaborare addEpayTQuietanzaDaElaborare(EpayTQuietanzaDaElaborare epayTQuietanzaDaElaborare) {
		getEpayTQuietanzaDaElaborares().add(epayTQuietanzaDaElaborare);
		epayTQuietanzaDaElaborare.setEpayTRt(this);

		return epayTQuietanzaDaElaborare;
	}

	public EpayTQuietanzaDaElaborare removeEpayTQuietanzaDaElaborare(EpayTQuietanzaDaElaborare epayTQuietanzaDaElaborare) {
		getEpayTQuietanzaDaElaborares().remove(epayTQuietanzaDaElaborare);
		epayTQuietanzaDaElaborare.setEpayTRt(null);

		return epayTQuietanzaDaElaborare;
	}

}
