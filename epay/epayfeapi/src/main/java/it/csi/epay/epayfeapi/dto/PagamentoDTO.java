/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import it.csi.epay.epayfeapi.enumeration.StatoPagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SuppressWarnings ( "unused" )
public class PagamentoDTO implements Serializable {

	private static final long serialVersionUID = 3054088360043653662L;

	private Long idPagamento;

	private String idPagamentoCifrato;

	private String causale;

	private String iuv;

	private String iuvRegistroVersamenti;

	private Timestamp dataInserimento;

	private BigDecimal importo;

	private String note;

	//PAGATORE e' colui che ha la posizione debitoria verso l'amministrazione pubblica
	private AnagraficaDTO pagatore;

	//il VERSANTE e' colui che effettua il pagamento dell'imposta
	private AnagraficaDTO versante;

	private EnteDTO ente;

	private boolean consensoPrivacy = Boolean.FALSE;

	private TipoPagamentoDTO tipoPagamento;

	private Date fineValidita;

	private Date inizioValidita;

	private Integer annoRiferimento;

	private String applicationCode;

	private String auxDigit;

	private String codicePagamentoRifEnte;

	private Date dataScadenza;

	private Integer idStatoCorrente = StatoPagamento.NON_DEFINITO.getId ();

	private String numeroRata;

	private Boolean pagamentoSpontaneo = Boolean.FALSE;

	private Date dataStatoCorrente;

	private String pulsantePdf;

	private String pulsanteXml;

	private String pulsanteReceiptPdf;

	private String utenteUltimaModifica;

	private boolean flagPagamentoAutenticato = Boolean.FALSE;

	private Integer annoAccertamento;

	private Integer numeroAccertamento;

	private List<PagamentoComponentiDTO> componenti;

	private List<PagamentoSecondarioComponentiDTO> componentiSecondari;

	private List<PagamentoRiferimentiDTO> riferimenti;

	private Boolean flgInvioReportPec;

	private String codiceContestoPagamento;

	private String identificativoDominio;

	private BigDecimal importoPrincipale;

	private Boolean requiresCostUpdate;

	public Long getIdPagamento () {
		return idPagamento;
	}

	public void setIdPagamento ( Long idPagamento ) {
		this.idPagamento = idPagamento;
	}

	public String getIdPagamentoCifrato () {
		return idPagamentoCifrato;
	}

	public void setIdPagamentoCifrato ( String idPagamentoCifrato ) {
		this.idPagamentoCifrato = idPagamentoCifrato;
	}

	public String getCausale () {
		return causale;
	}

	public void setCausale ( String causale ) {
		this.causale = causale;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getIuvRegistroVersamenti () {
		return iuvRegistroVersamenti;
	}

	public void setIuvRegistroVersamenti ( String iuvRegistroVersamenti ) {
		this.iuvRegistroVersamenti = iuvRegistroVersamenti;
	}

	public Timestamp getDataInserimento () {
		return dataInserimento;
	}

	public void setDataInserimento ( Timestamp dataInserimento ) {
		this.dataInserimento = dataInserimento;
	}

	public BigDecimal getImporto () {
		return importo;
	}

	public void setImporto ( BigDecimal importo ) {
		this.importo = importo;
	}

	public String getNote () {
		return note;
	}

	public void setNote ( String note ) {
		this.note = note;
	}

	public AnagraficaDTO getPagatore () {
		return pagatore;
	}

	public void setPagatore ( AnagraficaDTO pagatore ) {
		this.pagatore = pagatore;
	}

	public AnagraficaDTO getVersante () {
		return versante;
	}

	public void setVersante ( AnagraficaDTO versante ) {
		this.versante = versante;
	}

	public EnteDTO getEnte () {
		return ente;
	}

	public void setEnte ( EnteDTO ente ) {
		this.ente = ente;
	}

	public boolean isConsensoPrivacy () {
		return consensoPrivacy;
	}

	public void setConsensoPrivacy ( boolean consensoPrivacy ) {
		this.consensoPrivacy = consensoPrivacy;
	}

	public TipoPagamentoDTO getTipoPagamento () {
		return tipoPagamento;
	}

	public void setTipoPagamento ( TipoPagamentoDTO tipoPagamento ) {
		this.tipoPagamento = tipoPagamento;
	}

	public Date getFineValidita () {
		return fineValidita;
	}

	public void setFineValidita ( Date fineValidita ) {
		this.fineValidita = fineValidita;
	}

	public Date getInizioValidita () {
		return inizioValidita;
	}

	public void setInizioValidita ( Date inizioValidita ) {
		this.inizioValidita = inizioValidita;
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

	public String getCodicePagamentoRifEnte () {
		return codicePagamentoRifEnte;
	}

	public void setCodicePagamentoRifEnte ( String codicePagamentoRifEnte ) {
		this.codicePagamentoRifEnte = codicePagamentoRifEnte;
	}

	public Date getDataScadenza () {
		return dataScadenza;
	}

	public void setDataScadenza ( Date dataScadenza ) {
		this.dataScadenza = dataScadenza;
	}

	public Integer getIdStatoCorrente () {
		return idStatoCorrente;
	}

	public void setIdStatoCorrente ( Integer idStatoCorrente ) {
		this.idStatoCorrente = idStatoCorrente;
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

	public Date getDataStatoCorrente () {
		return dataStatoCorrente;
	}

	public void setDataStatoCorrente ( Date dataStatoCorrente ) {
		this.dataStatoCorrente = dataStatoCorrente;
	}

	public String getPulsantePdf () {
		return pulsantePdf;
	}

	public void setPulsantePdf ( String pulsantePdf ) {
		this.pulsantePdf = pulsantePdf;
	}

	public String getPulsanteXml () {
		return pulsanteXml;
	}

	public void setPulsanteXml ( String pulsanteXml ) {
		this.pulsanteXml = pulsanteXml;
	}

	public String getPulsanteReceiptPdf () {
		return pulsanteReceiptPdf;
	}

	public void setPulsanteReceiptPdf ( String pulsanteReceiptPdf ) {
		this.pulsanteReceiptPdf = pulsanteReceiptPdf;
	}

	public String getUtenteUltimaModifica () {
		return utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica ( String utenteUltimaModifica ) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}

	public boolean isFlagPagamentoAutenticato () {
		return flagPagamentoAutenticato;
	}

	public void setFlagPagamentoAutenticato ( boolean flagPagamentoAutenticato ) {
		this.flagPagamentoAutenticato = flagPagamentoAutenticato;
	}

	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	public void setAnnoAccertamento ( Integer annoAccertamento ) {
		this.annoAccertamento = annoAccertamento;
	}

	public Integer getNumeroAccertamento () {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento ( Integer numeroAccertamento ) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public List<PagamentoComponentiDTO> getComponenti () {
		if ( componenti == null ) {
			componenti = new ArrayList<> ();
		}
		return componenti;
	}

	public void setComponenti ( List<PagamentoComponentiDTO> componenti ) {
		this.componenti = componenti;
	}

	public List<PagamentoSecondarioComponentiDTO> getComponentiSecondari () {
		return componentiSecondari;
	}

	public void setComponentiSecondari ( List<PagamentoSecondarioComponentiDTO> componentiSecondari ) {
		this.componentiSecondari = componentiSecondari;
	}

	public List<PagamentoRiferimentiDTO> getRiferimenti () {
		return riferimenti;
	}

	public void setRiferimenti ( List<PagamentoRiferimentiDTO> riferimenti ) {
		this.riferimenti = riferimenti;
	}

	public Boolean getFlgInvioReportPec () {
		return flgInvioReportPec;
	}

	public void setFlgInvioReportPec ( Boolean flgInvioReportPec ) {
		this.flgInvioReportPec = flgInvioReportPec;
	}

	public String getCodiceContestoPagamento () {
		return codiceContestoPagamento;
	}

	public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}

	public String getIdentificativoDominio () {
		return identificativoDominio;
	}

	public void setIdentificativoDominio ( String identificativoDominio ) {
		this.identificativoDominio = identificativoDominio;
	}

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public String getCodiceAvviso () throws IllegalArgumentException {
		return CodiceAvvisoDTO.codiceAvvisoString ( auxDigit, applicationCode, iuv );
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
						", idPagamentoCifrato:" + idPagamentoCifrato +
						", causale:" + causale +
						", iuv:" + iuv +
						", iuvRegistroVersamenti:" + iuvRegistroVersamenti +
						", dataInserimento:" + dataInserimento +
						", importo:" + importo +
						", note:" + note +
						", pagatore:" + pagatore +
						", versante:" + versante +
						", ente:" + ente +
						", consensoPrivacy:" + consensoPrivacy +
						", tipoPagamento:" + tipoPagamento +
						", fineValidita:" + fineValidita +
						", inizioValidita:" + inizioValidita +
						", annoRiferimento:" + annoRiferimento +
						", applicationCode:" + applicationCode +
						", auxDigit:" + auxDigit +
						", codicePagamentoRifEnte:" + codicePagamentoRifEnte +
						", dataScadenza:" + dataScadenza +
						", idStatoCorrente:" + idStatoCorrente +
						", numeroRata:" + numeroRata +
						", pagamentoSpontaneo:" + pagamentoSpontaneo +
						", dataStatoCorrente:" + dataStatoCorrente +
						", pulsantePdf:" + pulsantePdf +
						", pulsanteXml:" + pulsanteXml +
						", pulsanteReceiptPdf:" + pulsanteReceiptPdf +
						", utenteUltimaModifica:" + utenteUltimaModifica +
						", flagPagamentoAutenticato:" + flagPagamentoAutenticato +
						", annoAccertamento:" + annoAccertamento +
						", numeroAccertamento:" + numeroAccertamento +
						", componenti:" + componenti +
						", componentiSecondari:" + componentiSecondari +
						", riferimenti:" + riferimenti +
						", flgInvioReportPec:" + flgInvioReportPec +
						", codiceContestoPagamento:" + codiceContestoPagamento +
						", identificativoDominio:" + identificativoDominio +
						", importoPrincipale:" + importoPrincipale +
						" }";
	}
}
