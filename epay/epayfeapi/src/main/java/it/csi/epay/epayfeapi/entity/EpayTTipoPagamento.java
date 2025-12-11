/*
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 *
 * SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Table ( name = "epay_t_tipo_pagamento" )
@SuppressWarnings ( "unused" )
public class EpayTTipoPagamento implements Serializable {

	private static final long serialVersionUID = -3701792927362269456L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_TIPO_PAGAMENTO_IDTIPOPAGAMENTO_GENERATOR", allocationSize = 1,
					sequenceName = "EPAY_T_TIPO_PAGAMENTO_ID_TIPO_PAGAMENTO_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_TIPO_PAGAMENTO_IDTIPOPAGAMENTO_GENERATOR" )
	@Column ( name = "id_tipo_pagamento", unique = true, nullable = false )
	private Long idTipoPagamento;

	@Column ( name = "codice_versamento", nullable = false, length = 4 )
	private String codiceVersamento;

	@Column ( name = "compilazione_note", length = 2000 )
	private String compilazioneNote;

	@Column ( name = "contatore_compilazioni", nullable = false )
	private Long contatoreCompilazioni;

	@Column ( name = "contatore_pagamenti", nullable = false )
	private Long contatorePagamenti;

	@Column ( name = "contatore_selezioni", nullable = false )
	private Long contatoreSelezioni;

	@Column ( name = "dati_specifici_riscossione", length = 140 )
	private String datiSpecificiRiscossione;

	@Column ( name = "descrizione_portale", nullable = false, length = 140 )
	private String descrizionePortale;

	@Temporal ( TemporalType.DATE )
	@Column ( name = "inizio_validita", nullable = false )
	private Date inizioValidita;

	@Temporal ( TemporalType.DATE )
	@Column ( name = "fine_validita" )
	private Date fineValidita;

	@Column ( name = "flag_invio_pagamenti", nullable = false )
	private Boolean flagInvioPagamenti;

	@Column ( name = "id_applicazione", nullable = false, length = 30 )
	private String idApplicazione;

	@Column ( name = "pagamento_spontaneo", nullable = false )
	private Boolean pagamentoSpontaneo;

	@Column ( name = "bollettino_postale" )
	private Boolean flagPresenzaBollettinoPostale;

	@OneToMany ( mappedBy = "epayTTipoPagamento" )
	private List<EpayTPagamento> epayTPagamentos;

	@ManyToOne
	@JoinColumn ( name = "id_ente", nullable = false )
	private EpayTEnti epayTEnti;

	@Column ( name = "numero_accertamento", length = 35 )
	private String numeroAccertamento;

	@Column ( name = "anno_accertamento" )
	private Long annoAccertamento;

	@Column ( name = "chiave_intersistema", length = 100 )
	private String chiaveIntersistema;

	@Column ( name = "flag_invio_rt", nullable = false )
	private Boolean flagInvioRT;

	@Column ( name = "multibeneficiario" )
	private Boolean flagMultibeneficiario;

	@ManyToOne
	@JoinColumn ( name = "id_tipologia_pagamento" )
	private EpayDTipologiaPagamento tipologiaPagamento;

	@OneToMany ( mappedBy = "epayTTipoPagamento" )
	private List<EpayTPagamentoSecondario> epayTPagamentoSecondarios;

	@Column ( name = "importo_pagamento_spontaneo" )
	private BigDecimal importoPagamentoSpontaneo;

	@Column ( name = "flag_visualizza_da_sportello" )
	Boolean flagVisualizzaDaSportello;

	public EpayTTipoPagamento () {
	}

	public Long getIdTipoPagamento () {
		return idTipoPagamento;
	}

	public void setIdTipoPagamento ( Long idTipoPagamento ) {
		this.idTipoPagamento = idTipoPagamento;
	}

	public String getCodiceVersamento () {
		return codiceVersamento;
	}

	public void setCodiceVersamento ( String codiceVersamento ) {
		this.codiceVersamento = codiceVersamento;
	}

	public String getCompilazioneNote () {
		return compilazioneNote;
	}

	public void setCompilazioneNote ( String compilazioneNote ) {
		this.compilazioneNote = compilazioneNote;
	}

	public Long getContatoreCompilazioni () {
		return contatoreCompilazioni;
	}

	public void setContatoreCompilazioni ( Long contatoreCompilazioni ) {
		this.contatoreCompilazioni = contatoreCompilazioni;
	}

	public Long getContatorePagamenti () {
		return contatorePagamenti;
	}

	public void setContatorePagamenti ( Long contatorePagamenti ) {
		this.contatorePagamenti = contatorePagamenti;
	}

	public Long getContatoreSelezioni () {
		return contatoreSelezioni;
	}

	public void setContatoreSelezioni ( Long contatoreSelezioni ) {
		this.contatoreSelezioni = contatoreSelezioni;
	}

	public String getDatiSpecificiRiscossione () {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	public String getDescrizionePortale () {
		return descrizionePortale;
	}

	public void setDescrizionePortale ( String descrizionePortale ) {
		this.descrizionePortale = descrizionePortale;
	}

	public Date getInizioValidita () {
		return inizioValidita;
	}

	public void setInizioValidita ( Date inizioValidita ) {
		this.inizioValidita = inizioValidita;
	}

	public Date getFineValidita () {
		return fineValidita;
	}

	public void setFineValidita ( Date fineValidita ) {
		this.fineValidita = fineValidita;
	}

	public Boolean getFlagInvioPagamenti () {
		return flagInvioPagamenti;
	}

	public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
		this.flagInvioPagamenti = flagInvioPagamenti;
	}

	public String getIdApplicazione () {
		return idApplicazione;
	}

	public void setIdApplicazione ( String idApplicazione ) {
		this.idApplicazione = idApplicazione;
	}

	public Boolean getPagamentoSpontaneo () {
		return pagamentoSpontaneo;
	}

	public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	public Boolean getFlagPresenzaBollettinoPostale () {
		return flagPresenzaBollettinoPostale;
	}

	public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
		this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	}

	public List<EpayTPagamento> getEpayTPagamentos () {
		return epayTPagamentos;
	}

	public void setEpayTPagamentos ( List<EpayTPagamento> epayTPagamentos ) {
		this.epayTPagamentos = epayTPagamentos;
	}

	public EpayTEnti getEpayTEnti () {
		return epayTEnti;
	}

	public void setEpayTEnti ( EpayTEnti epayTEnti ) {
		this.epayTEnti = epayTEnti;
	}

	public String getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( String numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public Long getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Long annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public String getChiaveIntersistema () {
		return chiaveIntersistema;
	}

	public void setChiaveIntersistema ( String chiaveIntersistema ) {
		this.chiaveIntersistema = chiaveIntersistema;
	}

	public Boolean getFlagInvioRT () {
		return flagInvioRT;
	}

	public void setFlagInvioRT ( Boolean flagInvioRT ) {
		this.flagInvioRT = flagInvioRT;
	}

	public Boolean getFlagMultibeneficiario () {
		return flagMultibeneficiario;
	}

	public void setFlagMultibeneficiario ( Boolean flagMultibeneficiario ) {
		this.flagMultibeneficiario = flagMultibeneficiario;
	}

	public EpayDTipologiaPagamento getTipologiaPagamento () {
		return tipologiaPagamento;
	}

	public void setTipologiaPagamento ( EpayDTipologiaPagamento tipologiaPagamento ) {
		this.tipologiaPagamento = tipologiaPagamento;
	}

	public List<EpayTPagamentoSecondario> getEpayTPagamentoSecondarios () {
		return epayTPagamentoSecondarios;
	}

	public void setEpayTPagamentoSecondarios ( List<EpayTPagamentoSecondario> epayTPagamentoSecondarios ) {
		this.epayTPagamentoSecondarios = epayTPagamentoSecondarios;
	}

	public BigDecimal getImportoPagamentoSpontaneo () {
		return importoPagamentoSpontaneo;
	}

	public void setImportoPagamentoSpontaneo ( BigDecimal importoPagamentoSpontaneo ) {
		this.importoPagamentoSpontaneo = importoPagamentoSpontaneo;
	}

	public Boolean getFlagVisualizzaDaSportello () {
		return flagVisualizzaDaSportello;
	}

	public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
		this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
	}

	@Override
	public String toString () {
		return "{ " +
						"idTipoPagamento:" + idTipoPagamento +
						", codiceVersamento:" + codiceVersamento +
						", compilazioneNote:" + compilazioneNote +
						", contatoreCompilazioni:" + contatoreCompilazioni +
						", contatorePagamenti:" + contatorePagamenti +
						", contatoreSelezioni:" + contatoreSelezioni +
						", datiSpecificiRiscossione:" + datiSpecificiRiscossione +
						", descrizionePortale:" + descrizionePortale +
						", inizioValidita:" + inizioValidita +
						", fineValidita:" + fineValidita +
						", flagInvioPagamenti:" + flagInvioPagamenti +
						", idApplicazione:" + idApplicazione +
						", pagamentoSpontaneo:" + pagamentoSpontaneo +
						", flagPresenzaBollettinoPostale:" + flagPresenzaBollettinoPostale +
						// non esporre epayTPagamentos
						// non esporre epayTEnti
						", numeroAccertamento:" + numeroAccertamento +
						", annoAccertamento:" + annoAccertamento +
						", chiaveIntersistema:" + chiaveIntersistema +
						", flagInvioRT:" + flagInvioRT +
						", flagMultibeneficiario:" + flagMultibeneficiario +
						", tipologiaPagamento:" + tipologiaPagamento +
						// non esporre epayTPagamentoSecondarios
						" }";
	}
}
