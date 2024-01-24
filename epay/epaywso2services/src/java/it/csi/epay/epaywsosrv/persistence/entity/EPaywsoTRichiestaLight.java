/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="epaywso_t_richiesta")
@NamedQueries({
    @NamedQuery(
        name = "EPaywsoTRichiestaLight.findOneByMessageUUIDLight",
        query= "SELECT new EPaywsoTRichiestaLight (e.idRichiesta, e.ePaywsoTEnte, e.ePaywsoDTipoRichiesta) "
                + "FROM EPaywsoTRichiestaLight e "
                + "WHERE e.messageUUID = :messageUUID")
    
})
public class EPaywsoTRichiestaLight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_richiesta")
	private Long idRichiesta;

	@Column(name = "message_uuid")
	private String messageUUID;

	@Column(name = "id_messaggio")
	private String idMessaggio;

	// uni-directional many-to-one association to EPaywsoTEnte
	@ManyToOne
	@JoinColumn(name = "id_ente")
	private EPaywsoTEnte ePaywsoTEnte;

	// uni-directional many-to-one association to EPaywsoDStatoRichiesta
	@ManyToOne
	@JoinColumn(name = "id_stato_richiesta")
	private EPaywsoDStatoRichiesta ePaywsoDStatoRichiesta;

	// uni-directional many-to-one association to EPaywsoDTipoRichiesta
	@ManyToOne
	@JoinColumn(name = "id_tipo_richiesta")
	private EPaywsoDTipoRichiesta ePaywsoDTipoRichiesta;

	@Column(name = "pagamento_spontaneo")
	private Boolean pagamentoSpontaneo;

	@Column(name = "cod_versamento")
	private String codVersamento;

	@Column(name = "cf_ente_origine")
	private String cfEnteOrigine;

	@Column(name="dt_inserimento_richiesta")
	private Timestamp dtInserimentoRichiesta;

	@Column(name = "dt_invio_al_destinatario")
	private Timestamp dtInvioAlDestinatario;

	// uni-directional many-to-one association to EPaywsoDEsito
	@ManyToOne
	@JoinColumn(name = "cod_esito")
	private EPaywsoDEsito ePaywsoDEsito;

	@Column(name = "importo_totale")
	private BigDecimal importoTotale;

	@Column(name = "num_totale_elementi")
	private Integer numTotaleElementi;

	public EPaywsoTRichiestaLight() {
	}
	
	public EPaywsoTRichiestaLight(Long idRichiesta, EPaywsoTEnte ePaywsoTEnte, EPaywsoDTipoRichiesta ePaywsoDTipoRichiesta ) {
	    this.idRichiesta = idRichiesta;
	    this.ePaywsoTEnte = ePaywsoTEnte;
	    this.ePaywsoDTipoRichiesta = ePaywsoDTipoRichiesta;
    }


	public Long getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getMessageUUID() {
		return messageUUID;
	}

	public void setMessageUUID(String messageUUID) {
		this.messageUUID = messageUUID;
	}

	public EPaywsoTEnte getEPaywsoTEnte() {
		return ePaywsoTEnte;
	}

	public void setEPaywsoTEnte(EPaywsoTEnte ePaywsoTEnte) {
		this.ePaywsoTEnte = ePaywsoTEnte;
	}

	public EPaywsoDStatoRichiesta getEPaywsoDStatoRichiesta() {
		return ePaywsoDStatoRichiesta;
	}

	public void setEPaywsoDStatoRichiesta(EPaywsoDStatoRichiesta ePaywsoDStatoRichiesta) {
		this.ePaywsoDStatoRichiesta = ePaywsoDStatoRichiesta;
	}

	public EPaywsoDTipoRichiesta getEPaywsoDTipoRichiesta() {
		return ePaywsoDTipoRichiesta;
	}

	public void setEPaywsoDTipoRichiesta(EPaywsoDTipoRichiesta ePaywsoDTipoRichiesta) {
		this.ePaywsoDTipoRichiesta = ePaywsoDTipoRichiesta;
	}

	public Boolean getPagamentoSpontaneo() {
		return pagamentoSpontaneo;
	}

	public void setPagamentoSpontaneo(Boolean pagamentoSpontaneo) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	public Timestamp getDtInserimentoRichiesta() {
		return this.dtInserimentoRichiesta;
	}

	public void setDtInserimentoRichiesta(Timestamp dtInserimentoRichiesta) {
		this.dtInserimentoRichiesta = dtInserimentoRichiesta;
	}

	public Timestamp getDtInvioAlDestinatario() {
		return dtInvioAlDestinatario;
	}

	public void setDtInvioAlDestinatario(Timestamp dtInvioAlDestinatario) {
		this.dtInvioAlDestinatario = dtInvioAlDestinatario;
	}

	public EPaywsoDEsito getEPaywsoDEsito() {
		return this.ePaywsoDEsito;
	}

	public void setEPaywsoDEsito(EPaywsoDEsito ePaywsoDEsito) {
		this.ePaywsoDEsito = ePaywsoDEsito;
	}

	public BigDecimal getImportoTotale() {
		return this.importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public Integer getNumTotaleElementi() {
		return this.numTotaleElementi;
	}

	public void setNumTotaleElementi(Integer numTotaleElementi) {
		this.numTotaleElementi = numTotaleElementi;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

    public String getCfEnteOrigine () {
        return cfEnteOrigine;
    }

    public void setCfEnteOrigine ( String cfEnteOrigine ) {
        this.cfEnteOrigine = cfEnteOrigine;
    }

}
