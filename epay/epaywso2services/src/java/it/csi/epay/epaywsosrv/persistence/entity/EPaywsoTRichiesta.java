/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

import org.hibernate.annotations.Type;

/**
 * The persistent class for the epaywso_t_richiesta database table.
 * 
 */
@Entity
@Table(name="epaywso_t_richiesta")
@NamedQueries({
	@NamedQuery(
			name = "EPaywsoTRichiesta.findMessageUUIDById",
			query= "SELECT e.messageUUID "
					+ "FROM EPaywsoTRichiesta e "
					+ "WHERE e.idRichiesta = :idRichiesta"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findIdByMessageUUID",
			query= "SELECT e.idRichiesta "
					+ "FROM EPaywsoTRichiesta e "
					+ "WHERE e.messageUUID = :messageUUID"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findOneByMessageUUID",
			query= "SELECT e "
					+ "FROM EPaywsoTRichiesta e "
					+ "WHERE e.messageUUID = :messageUUID"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findAll",
			query= "SELECT e FROM EPaywsoTRichiesta e"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findAllRichiesteInErrore",
			query= "SELECT e "
					+ "FROM EPaywsoTRichiesta e "
					+ "WHERE e.ePaywsoDStatoRichiesta.idStatoRichiesta = :idStatoRichiestaErroreInFaseDiElaborazione "
					+ "  AND e.dtUltimaVariazione >= :fromTime AND e.dtUltimaVariazione < :toTime "
					+ "ORDER BY e.idRichiesta"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findAllRichiesteScartate",
			query= "SELECT e "
					+ "FROM EPaywsoTRichiesta e "
					+ "WHERE (    e.ePaywsoDStatoRichiesta.idStatoRichiesta = :idStatoRichiestaInCorsoDiAcquisizione "
					+ "       AND e.dtInserimentoRichiesta >= :fromTimeIns AND e.dtInserimentoRichiesta < :toTimeIns) "
					+ "   OR (    e.ePaywsoDStatoRichiesta.idStatoRichiesta = :idStatoRichiestaErroreInFaseDiAcquisizione "
					+ "       AND (e.dtUltimaVariazione >= :fromTimeExe OR e.dtUltimaVariazione IS NULL)) "
					+ "ORDER BY e.idRichiesta"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findAllRichiesteNonInviate",
			query="SELECT e "
					+ "FROM EPaywsoTRichiesta e "
					+ "WHERE (    e.ePaywsoDStatoRichiesta.idStatoRichiesta = :idStatoRichiestaDaElaborare "
					+ "       AND (e.dtUltimaVariazione < :beforeTimeRDE OR e.dtUltimaVariazione IS NULL)) "
					+ "   OR (    e.ePaywsoDStatoRichiesta.idStatoRichiesta = :idStatoRichiestaErroreInFaseDiElaborazione "
					+ "       AND (e.dtUltimaVariazione < :beforeTimeREE OR e.dtUltimaVariazione IS NULL)) "
					+ "ORDER BY e.idRichiesta"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findfindAllRichiesteByMessaggioEnteTipoRichiesta",
			query= "SELECT e "
					+ "FROM EPaywsoTRichiesta e "
					+ " WHERE concat(e.idMessaggio,e.cfEnteOrigine)in( :idMessaggioCfEnteList )"
					+ " AND e.ePaywsoDTipoRichiesta.idTipoRichiesta = :idTipoRichiesta"
//					+ " AND e.cfEnteOrigine = :codFiscaleEnte  "
					+ " ORDER BY e.idMessaggio, e.dtUltimaVariazione desc"),
	@NamedQuery(
			name = "EPaywsoTRichiesta.findfindAllRichiesteByMessaggioEnte",
			query= "SELECT e "
					+ "FROM EPaywsoTRichiesta e "
					+ " WHERE concat(e.idMessaggio,e.cfEnteOrigine)in( :idMessaggioCfEnteList )"
//					+ " AND e.ePaywsoDTipoRichiesta.idTipoRichiesta = :idTipoRichiesta"
//					+ " AND e.cfEnteOrigine = :codFiscaleEnte  "
					+ " ORDER BY e.idMessaggio, e.dtUltimaVariazione desc")
})
public class EPaywsoTRichiesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_richiesta")
	private Long idRichiesta;

	@Column(name="cf_ente_origine")
	private String cfEnteOrigine;

	@Column(name="cod_esito_callback")
	private String codEsitoCallback;

	@Column(name="cod_versamento")
	private String codVersamento;

	@Type(type = "it.csi.epay.epaywsosrv.persistence.entity.usertype.SQLXMLType")
	@Column(name="contenuto_callback")
	private String contenutoCallback;

	@Type(type = "it.csi.epay.epaywsosrv.persistence.entity.usertype.SQLXMLType")
	@Column(name="contenuto_messaggio")
	private String contenutoMessaggio;

	@Column(name="dettaglio_esito")
	private String dettaglioEsito;

	@Column(name="dettaglio_esito_callback")
	private String dettaglioEsitoCallback;

	@Column(name="dim_messaggio_mb")
	private BigDecimal dimMessaggioMb;

	@Column(name="dt_callback")
	private Timestamp dtCallback;

	@Column(name="dt_inserimento_richiesta")
	private Timestamp dtInserimentoRichiesta;

	@Column(name="dt_ultima_variazione")
	private Timestamp dtUltimaVariazione;

	@Column(name="id_messaggio")
	private String idMessaggio;

	@Column(name="importo_totale")
	private BigDecimal importoTotale;

	@Column(name="message_uuid")
	private String messageUUID;

	@Column(name="num_totale_elementi")
	private Integer numTotaleElementi;

	@Column(name="pagamento_spontaneo")
	private Boolean pagamentoSpontaneo;
	
	@Column(name="id_psp")
	private String idPSP;
	
	@Column(name="id_flusso_rendicontazione")
	private String idFlussoRendicontazione;
	
	//uni-directional many-to-one association to EPaywsoDEsito
	@ManyToOne
	@JoinColumn(name="cod_esito")
	private EPaywsoDEsito ePaywsoDEsito;

	//uni-directional many-to-one association to EPaywsoDStatoRichiesta
	@ManyToOne
	@JoinColumn(name="id_stato_richiesta")
	private EPaywsoDStatoRichiesta ePaywsoDStatoRichiesta;

	//uni-directional many-to-one association to EPaywsoDTipoRichiesta
	@ManyToOne
	@JoinColumn(name="id_tipo_richiesta")
	private EPaywsoDTipoRichiesta ePaywsoDTipoRichiesta;

	//uni-directional many-to-one association to EPaywsoTEnte
	@ManyToOne
	@JoinColumn(name="id_ente")
	private EPaywsoTEnte ePaywsoTEnte;

	public EPaywsoTRichiesta() {
	}

	public Long getIdRichiesta() {
		return this.idRichiesta;
	}

	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getCfEnteOrigine() {
		return this.cfEnteOrigine;
	}

	public void setCfEnteOrigine(String cfEnteOrigine) {
		this.cfEnteOrigine = cfEnteOrigine;
	}

	public String getCodEsitoCallback() {
		return this.codEsitoCallback;
	}

	public void setCodEsitoCallback(String codEsitoCallback) {
		this.codEsitoCallback = codEsitoCallback;
	}

	public String getCodVersamento() {
		return this.codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	public String getContenutoCallback() {
		return this.contenutoCallback;
	}

	public void setContenutoCallback(String contenutoCallback) {
		this.contenutoCallback = contenutoCallback;
	}

	public String getContenutoMessaggio() {
		return this.contenutoMessaggio;
	}

	public void setContenutoMessaggio(String contenutoMessaggio) {
		this.contenutoMessaggio = contenutoMessaggio;
	}

	public String getDettaglioEsito() {
		return this.dettaglioEsito;
	}

	public void setDettaglioEsito(String dettaglioEsito) {
		this.dettaglioEsito = dettaglioEsito;
	}

	public String getDettaglioEsitoCallback() {
		return this.dettaglioEsitoCallback;
	}

	public void setDettaglioEsitoCallback(String dettaglioEsitoCallback) {
		this.dettaglioEsitoCallback = dettaglioEsitoCallback;
	}

	public BigDecimal getDimMessaggioMb() {
		return this.dimMessaggioMb;
	}

	public void setDimMessaggioMb(BigDecimal dimMessaggioMb) {
		this.dimMessaggioMb = dimMessaggioMb;
	}

	public Timestamp getDtCallback() {
		return this.dtCallback;
	}

	public void setDtCallback(Timestamp dtCallback) {
		this.dtCallback = dtCallback;
	}

	public Timestamp getDtInserimentoRichiesta() {
		return this.dtInserimentoRichiesta;
	}

	public void setDtInserimentoRichiesta(Timestamp dtInserimentoRichiesta) {
		this.dtInserimentoRichiesta = dtInserimentoRichiesta;
	}

	public Timestamp getDtUltimaVariazione() {
		return this.dtUltimaVariazione;
	}

	public void setDtUltimaVariazione(Timestamp dtUltimaVariazione) {
		this.dtUltimaVariazione = dtUltimaVariazione;
	}

	public String getIdMessaggio() {
		return this.idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public BigDecimal getImportoTotale() {
		return this.importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getMessageUUID() {
		return this.messageUUID;
	}

	public void setMessageUUID(String messageUUID) {
		this.messageUUID = messageUUID;
	}

	public Integer getNumTotaleElementi() {
		return this.numTotaleElementi;
	}

	public void setNumTotaleElementi(Integer numTotaleElementi) {
		this.numTotaleElementi = numTotaleElementi;
	}

	public Boolean getPagamentoSpontaneo() {
		return this.pagamentoSpontaneo;
	}

	public void setPagamentoSpontaneo(Boolean pagamentoSpontaneo) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	public EPaywsoDEsito getEPaywsoDEsito() {
		return this.ePaywsoDEsito;
	}

	public void setEPaywsoDEsito(EPaywsoDEsito ePaywsoDEsito) {
		this.ePaywsoDEsito = ePaywsoDEsito;
	}

	public EPaywsoDStatoRichiesta getEPaywsoDStatoRichiesta() {
		return this.ePaywsoDStatoRichiesta;
	}

	public void setEPaywsoDStatoRichiesta(EPaywsoDStatoRichiesta ePaywsoDStatoRichiesta) {
		this.ePaywsoDStatoRichiesta = ePaywsoDStatoRichiesta;
	}

	public EPaywsoDTipoRichiesta getEPaywsoDTipoRichiesta() {
		return this.ePaywsoDTipoRichiesta;
	}

	public void setEPaywsoDTipoRichiesta(EPaywsoDTipoRichiesta ePaywsoDTipoRichiesta) {
		this.ePaywsoDTipoRichiesta = ePaywsoDTipoRichiesta;
	}

	public EPaywsoTEnte getEPaywsoTEnte() {
		return this.ePaywsoTEnte;
	}

	public void setEPaywsoTEnte(EPaywsoTEnte ePaywsoTEnte) {
		this.ePaywsoTEnte = ePaywsoTEnte;
	}

	public String getIdPSP() {
		return idPSP;
	}

	public void setIdPSP(String idPSP) {
		this.idPSP = idPSP;
	}

	public String getIdFlussoRendicontazione() {
		return idFlussoRendicontazione;
	}

	public void setIdFlussoRendicontazione(String idFlussoRendicontazione) {
		this.idFlussoRendicontazione = idFlussoRendicontazione;
	}
}
