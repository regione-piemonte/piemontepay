/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Entity
@Table ( name = "epay_t_pagamento", schema = "epay" )
@SuppressWarnings ( "unused" )
public class EpayTPagamento implements Serializable {

	private static final long serialVersionUID = -3920508694810958644L;

	@Id
	@SequenceGenerator ( name = "EPAY_T_PAGAMENTO_IDPAGAMENTO_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_PAGAMENTO_ID_PAGAMENTO_SEQ" )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_PAGAMENTO_IDPAGAMENTO_GENERATOR" )
	@Column ( name = "id_pagamento", unique = true, nullable = false )
	private Long idPagamento;

	@Column ( name = "anno_riferimento" )
	private Integer annoRiferimento;

	@Column ( name = "application_code", length = 2 )
	private String applicationCode;

	@Column ( name = "aux_digit", length = 1 )
	private String auxDigit;

	@Column ( nullable = false, length = 140 )
	private String causale;

	@Column ( name = "codice_pagamento_rif_ente", length = 200 )
	private String codicePagamentoRifEnte;

	@Column ( name = "consenso_privacy", nullable = false )
	private Boolean consensoPrivacy;

	@Column ( name = "data_inserimento", nullable = false )
	private Timestamp dataInserimento;

	@Temporal ( TemporalType.DATE )
	@Column ( name = "data_scadenza" )
	private Date dataScadenza;

	@Temporal ( TemporalType.DATE )
	@Column ( name = "inizio_validita" )
	private Date inizioValidita;

	@Temporal ( TemporalType.DATE )
	@Column ( name = "fine_validita" )
	private Date fineValidita;

	@Column ( nullable = false, precision = 11, scale = 2 )
	private BigDecimal importo;

	@Column ( name = "iuv_numero_avviso", length = 25 )
	private String iuvNumeroAvviso;

	@Column ( length = 2000 )
	private String note;

	@Column ( name = "numero_rata", length = 35 )
	private String numeroRata;

	@Column ( name = "pagamento_spontaneo", nullable = false )
	private Boolean pagamentoSpontaneo;

	@Column ( name = "flag_inviato", nullable = false )
	private Boolean flagInviato;

	@Column ( name = "utente_ultima_modifica", nullable = false, length = 100 )
	private String utenteUltimaModifica;

	@Column ( name = "flg_invio_report_pec" )
	private Boolean flgInvioReportPec;

	@ManyToMany ( mappedBy = "epayTPagamentos" )
	private List<EpayTRegistroElaborazioni> epayTRegistroElaborazionis;

	@ManyToOne
	@JoinColumn ( name = "id_stato_corrente", nullable = false )
	private EpayDStatoPagamento epayDStatoPagamento;

	@ManyToOne
	@JoinColumn ( name = "id_anagrafica_pagatore", nullable = false )
	private EpayTAnagrafica epayTAnagrafica;

	@ManyToOne
	@JoinColumn ( name = "id_tipo_pagamento", nullable = false )
	private EpayTTipoPagamento epayTTipoPagamento;

	@OneToMany ( mappedBy = "epayTPagamento", fetch = FetchType.EAGER )
	private List<EpayTRegistroVersamenti> epayTRegistroVersamentis;

	@OneToMany ( mappedBy = "epayTPagamento", cascade = CascadeType.ALL )
	private List<EpayTPagamentoComponenti> epayTPagamentoComponentis;

	@OneToMany ( mappedBy = "epayTPagamento", cascade = CascadeType.ALL )
	private List<EpayTPagamentoRiferimenti> epayTPagamentoRiferimentis;

	@OneToMany ( mappedBy = "epayTPagamento" )
	private List<EpayTTracciaturaNotify> epayTTracciaturaNotifys;

	@Column ( name = "identificativo_dominio" )
	private String identificativoDominio;

	@OneToMany ( mappedBy = "epayTPagamento" )
	private List<EpayTPagamentoSecondario> epayTPagamentoSecondarios;

	@Column ( name = "importo_principale", precision = 11, scale = 2 )
	private BigDecimal importoPrincipale;

	@Column ( name = "requires_cost_update" )
	private Boolean requiresCostUpdate;

	public EpayTPagamento () {
	}

	public void addEpayTPagamentoComponenti ( EpayTPagamentoComponenti epayTPagamentoComponenti ) {
		getEpayTPagamentoComponentis ().add ( epayTPagamentoComponenti );
		epayTPagamentoComponenti.setEpayTPagamento ( this );
	}

	public void addEpayTPagamentoRiferimenti ( EpayTPagamentoRiferimenti epayTPagamentoRiferimenti ) {
		getEpayTPagamentoRiferimentis ().add ( epayTPagamentoRiferimenti );
		epayTPagamentoRiferimenti.setEpayTPagamento ( this );
	}

	public Long getIdPagamento () {
		return idPagamento;
	}

	public void setIdPagamento ( Long idPagamento ) {
		this.idPagamento = idPagamento;
	}

	public Integer getAnnoRiferimento () {
		return annoRiferimento;
	}

	public void setAnnoRiferimento ( Integer annoRiferimento ) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getApplicationCode () {
		return applicationCode;
	}

	public void setApplicationCode ( String applicationCode ) {
		this.applicationCode = applicationCode;
	}

	public String getAuxDigit () {
		return auxDigit;
	}

	public void setAuxDigit ( String auxDigit ) {
		this.auxDigit = auxDigit;
	}

	public String getCausale () {
		return causale;
	}

	public void setCausale ( String causale ) {
		this.causale = causale;
	}

	public String getCodicePagamentoRifEnte () {
		return codicePagamentoRifEnte;
	}

	public void setCodicePagamentoRifEnte ( String codicePagamentoRifEnte ) {
		this.codicePagamentoRifEnte = codicePagamentoRifEnte;
	}

	public Boolean getConsensoPrivacy () {
		return consensoPrivacy;
	}

	public void setConsensoPrivacy ( Boolean consensoPrivacy ) {
		this.consensoPrivacy = consensoPrivacy;
	}

	public Timestamp getDataInserimento () {
		return dataInserimento;
	}

	public void setDataInserimento ( Timestamp dataInserimento ) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataScadenza () {
		return dataScadenza;
	}

	public void setDataScadenza ( Date dataScadenza ) {
		this.dataScadenza = dataScadenza;
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

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getIuvNumeroAvviso () {
		return iuvNumeroAvviso;
	}

	public void setIuvNumeroAvviso ( String iuvNumeroAvviso ) {
		this.iuvNumeroAvviso = iuvNumeroAvviso;
	}

	public String getNote () {
		return note;
	}

	public void setNote ( String note ) {
		this.note = note;
	}

	public String getNumeroRata () {
		return numeroRata;
	}

	public void setNumeroRata ( String numeroRata ) {
		this.numeroRata = numeroRata;
	}

	public Boolean getPagamentoSpontaneo () {
		return pagamentoSpontaneo;
	}

	public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	public Boolean getFlagInviato () {
		return flagInviato;
	}

	public void setFlagInviato ( Boolean flagInviato ) {
		this.flagInviato = flagInviato;
	}

	public String getUtenteUltimaModifica () {
		return utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica ( String utenteUltimaModifica ) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}

	public Boolean getFlgInvioReportPec () {
		return flgInvioReportPec;
	}

	public void setFlgInvioReportPec ( Boolean flgInvioReportPec ) {
		this.flgInvioReportPec = flgInvioReportPec;
	}

	public List<EpayTRegistroElaborazioni> getEpayTRegistroElaborazionis () {
		return epayTRegistroElaborazionis;
	}

	public void setEpayTRegistroElaborazionis ( List<EpayTRegistroElaborazioni> epayTRegistroElaborazionis ) {
		this.epayTRegistroElaborazionis = epayTRegistroElaborazionis;
	}

	public EpayDStatoPagamento getEpayDStatoPagamento () {
		return epayDStatoPagamento;
	}

	public void setEpayDStatoPagamento ( EpayDStatoPagamento epayDStatoPagamento ) {
		this.epayDStatoPagamento = epayDStatoPagamento;
	}

	public EpayTAnagrafica getEpayTAnagrafica () {
		return epayTAnagrafica;
	}

	public void setEpayTAnagrafica ( EpayTAnagrafica epayTAnagrafica ) {
		this.epayTAnagrafica = epayTAnagrafica;
	}

	public EpayTTipoPagamento getEpayTTipoPagamento () {
		return epayTTipoPagamento;
	}

	public void setEpayTTipoPagamento ( EpayTTipoPagamento epayTTipoPagamento ) {
		this.epayTTipoPagamento = epayTTipoPagamento;
	}

	public List<EpayTRegistroVersamenti> getEpayTRegistroVersamentis () {
		return epayTRegistroVersamentis;
	}

	public void setEpayTRegistroVersamentis ( List<EpayTRegistroVersamenti> epayTRegistroVersamentis ) {
		this.epayTRegistroVersamentis = epayTRegistroVersamentis;
	}

	public List<EpayTPagamentoComponenti> getEpayTPagamentoComponentis () {
		return epayTPagamentoComponentis;
	}

	public void setEpayTPagamentoComponentis ( List<EpayTPagamentoComponenti> epayTPagamentoComponentis ) {
		this.epayTPagamentoComponentis = epayTPagamentoComponentis;
	}

	public List<EpayTPagamentoRiferimenti> getEpayTPagamentoRiferimentis () {
		return epayTPagamentoRiferimentis;
	}

	public void setEpayTPagamentoRiferimentis ( List<EpayTPagamentoRiferimenti> epayTPagamentoRiferimentis ) {
		this.epayTPagamentoRiferimentis = epayTPagamentoRiferimentis;
	}

	public List<EpayTTracciaturaNotify> getEpayTTracciaturaNotifys () {
		return epayTTracciaturaNotifys;
	}

	public void setEpayTTracciaturaNotifys ( List<EpayTTracciaturaNotify> epayTTracciaturaNotifys ) {
		this.epayTTracciaturaNotifys = epayTTracciaturaNotifys;
	}

	public String getIdentificativoDominio () {
		return identificativoDominio;
	}

	public void setIdentificativoDominio ( String identificativoDominio ) {
		this.identificativoDominio = identificativoDominio;
	}

	public List<EpayTPagamentoSecondario> getEpayTPagamentoSecondarios () {
		return epayTPagamentoSecondarios;
	}

	public void setEpayTPagamentoSecondarios ( List<EpayTPagamentoSecondario> epayTPagamentoSecondarios ) {
		this.epayTPagamentoSecondarios = epayTPagamentoSecondarios;
	}

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public Boolean getRequiresCostUpdate () {
		return requiresCostUpdate;
	}

	public void setRequiresCostUpdate ( Boolean requiresCostUpdate ) {
		this.requiresCostUpdate = requiresCostUpdate;
	}

	@Override
	public String toString () {
		return "{ " +
						"idPagamento:" + idPagamento +
						", annoRiferimento:" + annoRiferimento +
						", applicationCode:" + applicationCode +
						", auxDigit:" + auxDigit +
						", causale:" + causale +
						", codicePagamentoRifEnte:" + codicePagamentoRifEnte +
						", consensoPrivacy:" + consensoPrivacy +
						", dataInserimento:" + dataInserimento +
						", dataScadenza:" + dataScadenza +
						", inizioValidita:" + inizioValidita +
						", fineValidita:" + fineValidita +
						", importo:" + importo +
						", iuvNumeroAvviso:" + iuvNumeroAvviso +
						", note:" + note +
						", numeroRata:" + numeroRata +
						", pagamentoSpontaneo:" + pagamentoSpontaneo +
						", flagInviato:" + flagInviato +
						", utenteUltimaModifica:" + utenteUltimaModifica +
						", flgInvioReportPec:" + flgInvioReportPec +
						// non esporre epayTRegistroElaborazionis
						// non esporre epayDStatoPagamento
						// non esporre epayTAnagrafica
						// non esporre epayTTipoPagamento
						// non esporre epayTRegistroVersamentis
						// non esporre epayTPagamentoComponentis
						// non esporre epayTPagamentoRiferimentis
						// non esporre epayTTracciaturaNotifys
						", identificativoDominio:" + identificativoDominio +
						// non esporre epayTPagamentoSecondarios
						", importoPrincipale:" + importoPrincipale +
						" }";
	}
}
