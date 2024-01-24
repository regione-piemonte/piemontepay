/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pagamento implements Serializable {

    private static final long serialVersionUID = -520928713011664413L;

    private Long idPagamento;

    private String idPagamentoCifrato;

    private String causale;

    private String iuv;

    private String iuvRegistroVersamenti;

    private Timestamp dataInserimento;

    private BigDecimal importo;

    private String note;

    //PAGATORE e' colui che ha la posizione debitoria verso l'amministrazione pubblica
    private Anagrafica pagatore;

    //il VERSANTE e' colui che effettua il pagamento dell'imposta
    private Anagrafica versante;

    private Ente ente;

    private boolean consensoPrivacy = Boolean.FALSE;

    private TipoPagamento tipoPagamento;

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

    private List<PagamentoComponenti> componenti;

    private List<PagamentoSecondarioComponenti> componentiSecondari;

    private List<PagamentoRiferimenti> riferimenti;

    private Boolean flgInvioReportPec;

    private String codiceContestoPagamento;

    private String identificativoDominio;

    private BigDecimal importoPrincipale;
    
    private List<Ente> entiSecondari;
    
    private Boolean requiresCostUpdate;

    public Boolean getFlgInvioReportPec () {
        return flgInvioReportPec;
    }

    public void setFlgInvioReportPec ( Boolean flgInvioReportPec ) {
        this.flgInvioReportPec = flgInvioReportPec;
    }

    /**
     * @return the idPagamento
     */
    public Long getIdPagamento () {
        return idPagamento;
    }

    /**
     * @param idPagamento the idPagamento to set
     */

    public void setIdPagamento ( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }

    /**
     * @return the causale
     */
    public String getCausale () {
        return causale;
    }

    /**
     * @param causale the causale to set
     */
    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    /**
     * @return the iuv
     */
    public String getIuv () {
        return iuv;
    }

    /**
     * @param iuv the iuv to set
     */
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    /**
     * @return the dataInserimento
     */
    public Timestamp getDataInserimento () {
        return dataInserimento;
    }

    /**
     * @param dataInserimento the dataInserimento to set
     */
    public void setDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    /**
     * @return the importo
     */
    public BigDecimal getImporto () {
        return importo;
    }

    /**
     * @param importo the importo to set
     */
    public void setImporto ( BigDecimal importo ) {
        this.importo = importo;
    }

    /**
     * @return the note
     */
    public String getNote () {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote ( String note ) {
        this.note = note;
    }

    /**
     * @return the pagatore
     */
    public Anagrafica getPagatore () {
        return pagatore;
    }

    /**
     * @param pagatore the pagatore to set
     */
    public void setPagatore ( Anagrafica pagatore ) {
        this.pagatore = pagatore;
    }

    /**
     * @return the versante
     */
    public Anagrafica getVersante () {
        return versante;
    }

    /**
     * @param versante the versante to set
     */
    public void setVersante ( Anagrafica versante ) {
        this.versante = versante;
    }

    /**
     * @return the ente
     */
    public Ente getEnte () {
        return ente;
    }

    /**
     * @param ente the ente to set
     */
    public void setEnte ( Ente ente ) {
        this.ente = ente;
    }

    /**
     * @return the consensoPrivacy
     */
    public boolean getConsensoPrivacy () {
        return consensoPrivacy;
    }

    /**
     * @param consensoPrivacy the consensoPrivacy to set
     */
    public void setConsensoPrivacy ( boolean consensoPrivacy ) {
        this.consensoPrivacy = consensoPrivacy;
    }

    /**
     * @return the tipoPagamento
     */
    public TipoPagamento getTipoPagamento () {
        return tipoPagamento;
    }

    /**
     * @param tipoPagamento the tipoPagamento to set
     */
    public void setTipoPagamento ( TipoPagamento tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    /**
     * @return the fineValidita
     */
    public Date getFineValidita () {
        return fineValidita;
    }

    /**
     * @param fineValidita the fineValidita to set
     */
    public void setFineValidita ( Date fineValidita ) {
        this.fineValidita = fineValidita;
    }

    /**
     * @return the inizioValidita
     */
    public Date getInizioValidita () {
        return inizioValidita;
    }

    /**
     * @param inizioValidita the inizioValidita to set
     */
    public void setInizioValidita ( Date inizioValidita ) {
        this.inizioValidita = inizioValidita;
    }

    /**
     * @return the annoRiferimento
     */
    public Integer getAnnoRiferimento () {
        return annoRiferimento;
    }

    /**
     * @param annoRiferimento the annoRiferimento to set
     */
    public void setAnnoRiferimento ( Integer annoRiferimento ) {
        this.annoRiferimento = annoRiferimento;
    }

    /**
     * @return the applicationCode
     */
    public String getApplicationCode () {
        return applicationCode;
    }

    /**
     * @param applicationCode the applicationCode to set
     */
    public void setApplicationCode ( String applicationCode ) {
        this.applicationCode = applicationCode;
    }

    /**
     * @return the auxDigit
     */
    public String getAuxDigit () {
        return auxDigit;
    }

    /**
     * @param auxDigit the auxDigit to set
     */
    public void setAuxDigit ( String auxDigit ) {
        this.auxDigit = auxDigit;
    }

    /**
     * @return the codicePagamentoRifEnte
     */
    public String getCodicePagamentoRifEnte () {
        return codicePagamentoRifEnte;
    }

    /**
     * @param codicePagamentoRifEnte the codicePagamentoRifEnte to set
     */
    public void setCodicePagamentoRifEnte ( String codicePagamentoRifEnte ) {
        this.codicePagamentoRifEnte = codicePagamentoRifEnte;
    }

    /**
     * @return the dataScadenza
     */
    public Date getDataScadenza () {
        return dataScadenza;
    }

    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * @return the idStatoCorrente
     */
    public Integer getIdStatoCorrente () {
        return idStatoCorrente;
    }

    /**
     * @param idStatoCorrente the idStatoCorrente to set
     */
    public void setIdStatoCorrente ( Integer idStatoCorrente ) {
        this.idStatoCorrente = idStatoCorrente;
    }

    /**
     * @return description of the idStatoCorrente
     */
    public String getDescStatoCorrente () {
        return StatoPagamento.findById ( idStatoCorrente ).getDescrizione ();
    }

    /**
     * @return the numeroRata
     */
    public String getNumeroRata () {
        return numeroRata;
    }

    /**
     * @param numeroRata the numeroRata to set
     */
    public void setNumeroRata ( String numeroRata ) {
        this.numeroRata = numeroRata;
    }

    public Boolean getPagamentoSpontaneo () {
        return pagamentoSpontaneo;
    }

    public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }

    /* Pagamento model emette IllegalArgumentException che va gestita esternamente - BEGIN */
	public String getCodiceAvviso () throws IllegalArgumentException {
		return CodiceAvviso.codiceAvvisoString ( auxDigit, applicationCode, iuv );
	}
	/* Pagamento model emette IllegalArgumentException che va gestita esternamente - END */

    public String getIuvRegistroVersamenti () {
        return iuvRegistroVersamenti;
    }

    public void setIuvRegistroVersamenti ( String iuvRegistroVersamenti ) {
        this.iuvRegistroVersamenti = iuvRegistroVersamenti;
    }

    /**
     * @return the dataStatoCorrente
     */
    public Date getDataStatoCorrente () {
        return dataStatoCorrente;
    }

    /**
     * @param dataStatoCorrente the dataStatoCorrente to set
     */
    public void setDataStatoCorrente ( Date dataStatoCorrente ) {
        this.dataStatoCorrente = dataStatoCorrente;
    }

    /**
     * @return the pulsantePdf
     */
    public String getPulsantePdf () {
        return pulsantePdf;
    }

    /**
     * @param pulsantePdf the pulsantePdf to set
     */
    public void setPulsantePdf ( String pulsantePdf ) {
        this.pulsantePdf = pulsantePdf;
    }

    /**
     * @return the pulsanteXml
     */
    public String getPulsanteXml () {
        return pulsanteXml;
    }

    /**
     * @param pulsanteXml the pulsanteXml to set
     */
    public void setPulsanteXml ( String pulsanteXml ) {
        this.pulsanteXml = pulsanteXml;
    }

    /**
     * @return the componenti
     */
    public List<PagamentoComponenti> getComponenti () {
        if ( componenti == null ) {
            componenti = new ArrayList<> ();
        }
        return componenti;
    }

    /**
     * @param componenti the componenti to set
     */
    public void setComponenti ( List<PagamentoComponenti> componenti ) {
        this.componenti = componenti;
    }

    /**
     *
     * @return i riferimenti pagamento
     */
    public List<PagamentoRiferimenti> getRiferimenti () {
        if ( riferimenti == null ) {
            riferimenti = new ArrayList<> ();
        }
        return riferimenti;
    }

    /**
     *
     * @param riferimenti dei pagamenti
     */
    public void setRiferimenti ( List<PagamentoRiferimenti> riferimenti ) {
        this.riferimenti = riferimenti;
    }

    /**
     * @return the utenteUltimaModifica
     */
    public String getUtenteUltimaModifica () {
        return utenteUltimaModifica;
    }

    /**
     * @param utenteUltimaModifica the utenteUltimaModifica to set
     */
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

    public String getIdPagamentoCifrato () {
        return idPagamentoCifrato;
    }

    public void setIdPagamentoCifrato ( String idPagamentoCifrato ) {
        this.idPagamentoCifrato = idPagamentoCifrato;
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

    public List<PagamentoSecondarioComponenti> getComponentiSecondari () {
        return componentiSecondari;
    }

    public void setComponentiSecondari ( List<PagamentoSecondarioComponenti> componentiSecondari ) {
        this.componentiSecondari = componentiSecondari;
    }

    public String getPulsanteReceiptPdf () {
        return pulsanteReceiptPdf;
    }

    public void setPulsanteReceiptPdf ( String pulsanteReceiptPdf ) {
        this.pulsanteReceiptPdf = pulsanteReceiptPdf;
    }
    
    public Boolean getRequiresCostUpdate() {
		return requiresCostUpdate;
	}

	public void setRequiresCostUpdate(Boolean requiresCostUpdate) {
		this.requiresCostUpdate = requiresCostUpdate;
	}

	public List<Ente> getEntiSecondari () {
        return entiSecondari;
    }

    
    public void setEntiSecondari ( List<Ente> entiSecondari ) {
        this.entiSecondari = entiSecondari;
    }

   
}
