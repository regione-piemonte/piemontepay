/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for entity "epay_t_pagamento" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 *
 * @author fabio.fenoglio
 *
 */
public class EpayTPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idPagamento ; // Id or Primary Key

    @NotNull
    private Date dataInserimento ;
    @NotNull
    @Size( min = 1, max = 140 )
    private String causale ;
    @NotNull
    private BigDecimal importo ;
    @Size( max = 2000 )
    private String note ;
    @NotNull
    private Boolean consensoPrivacy ;

    private Date inizioValidita ;

    private Date fineValidita ;
    @Size( max = 25 )
    private String iuvNumeroAvviso ;
    @Size( max = 1 )
    private String auxDigit ;
    @Size( max = 2 )
    private String applicationCode ;
    @Size( max = 200 )
    private String codicePagamentoRifEnte ;

    private Integer annoRiferimento ;

    private Date dataScadenza ;
    @Size( max = 35 )
    private String numeroRata ;
    @NotNull
    private Boolean pagamentoSpontaneo ;
    @NotNull
    private Boolean flagInviato ;
    @NotNull
    @Size( min = 1, max = 100 )
    private String utenteUltimaModifica ;
    @NotNull
    private Boolean flagPagamentoAutenticato ;

    private BigDecimal importoPrincipale;

    @Size ( max = 35 )
    private String identificativoDominio;

    private List<EpayTRegistroVersamentiReferenceDTO> listOfEpayTRegistroVersamenti ;

    private List<EpayTDatiSingoliVersamentiReferenceDTO> listOfEpayTDatiSingoliVersamenti ;

    private EpayDStatoPagamentoReferenceDTO epayDStatoPagamento ;

    private List<EpayTPagamentoRiferimentiReferenceDTO> listOfEpayTPagamentoRiferimenti ;

    private List<EpayTRegistroElaborazioniReferenceDTO> listOfEpayTRegistroElaborazioni ;

    private EpayTTipoPagamentoReferenceDTO epayTTipoPagamento ;

    private EpayTAnagraficaReferenceDTO epayTAnagrafica ;

    private List<EpayTPagamentoComponentiReferenceDTO> listOfEpayTPagamentoComponenti ;


    /**
     * Default constructor
     */
    public EpayTPagamentoDTO() {
        super();
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY
    //----------------------------------------------------------------------
    /**
     * Set the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "bigserial", NotNull : true )
     * @param idPagamento
     */
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento ;
    }
    /**
     * Get the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "bigserial", NotNull : true )
     * @return the field value
     */
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS
    //----------------------------------------------------------------------

    /**
     * Set the "dataInserimento" field value
     * This field is mapped on the database column "data_inserimento" ( type "timestamp", NotNull : true )
     * @param dataInserimento
     */
    public void setDataInserimento( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }
    /**
     * Get the "dataInserimento" field value
     * This field is mapped on the database column "data_inserimento" ( type "timestamp", NotNull : true )
     * @return the field value
     */
    public Date getDataInserimento() {
        return this.dataInserimento;
    }

    /**
     * Set the "causale" field value
     * This field is mapped on the database column "causale" ( type "varchar", NotNull : true )
     * @param causale
     */
    public void setCausale( String causale ) {
        this.causale = causale;
    }
    /**
     * Get the "causale" field value
     * This field is mapped on the database column "causale" ( type "varchar", NotNull : true )
     * @return the field value
     */
    public String getCausale() {
        return this.causale;
    }

    /**
     * Set the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : true )
     * @param importo
     */
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    /**
     * Get the "importo" field value
     * This field is mapped on the database column "importo" ( type "numeric", NotNull : true )
     * @return the field value
     */
    public BigDecimal getImporto() {
        return this.importo;
    }

    /**
     * Set the "note" field value
     * This field is mapped on the database column "note" ( type "varchar", NotNull : false )
     * @param note
     */
    public void setNote( String note ) {
        this.note = note;
    }
    /**
     * Get the "note" field value
     * This field is mapped on the database column "note" ( type "varchar", NotNull : false )
     * @return the field value
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Set the "consensoPrivacy" field value
     * This field is mapped on the database column "consenso_privacy" ( type "bool", NotNull : true )
     * @param consensoPrivacy
     */
    public void setConsensoPrivacy( Boolean consensoPrivacy ) {
        this.consensoPrivacy = consensoPrivacy;
    }
    /**
     * Get the "consensoPrivacy" field value
     * This field is mapped on the database column "consenso_privacy" ( type "bool", NotNull : true )
     * @return the field value
     */
    public Boolean getConsensoPrivacy() {
        return this.consensoPrivacy;
    }

    /**
     * Set the "inizioValidita" field value
     * This field is mapped on the database column "inizio_validita" ( type "date", NotNull : false )
     * @param inizioValidita
     */
    public void setInizioValidita( Date inizioValidita ) {
        this.inizioValidita = inizioValidita;
    }
    /**
     * Get the "inizioValidita" field value
     * This field is mapped on the database column "inizio_validita" ( type "date", NotNull : false )
     * @return the field value
     */
    public Date getInizioValidita() {
        return this.inizioValidita;
    }

    /**
     * Set the "fineValidita" field value
     * This field is mapped on the database column "fine_validita" ( type "date", NotNull : false )
     * @param fineValidita
     */
    public void setFineValidita( Date fineValidita ) {
        this.fineValidita = fineValidita;
    }
    /**
     * Get the "fineValidita" field value
     * This field is mapped on the database column "fine_validita" ( type "date", NotNull : false )
     * @return the field value
     */
    public Date getFineValidita() {
        return this.fineValidita;
    }

    /**
     * Set the "iuvNumeroAvviso" field value
     * This field is mapped on the database column "iuv_numero_avviso" ( type "varchar", NotNull : false )
     * @param iuvNumeroAvviso
     */
    public void setIuvNumeroAvviso( String iuvNumeroAvviso ) {
        this.iuvNumeroAvviso = iuvNumeroAvviso;
    }
    /**
     * Get the "iuvNumeroAvviso" field value
     * This field is mapped on the database column "iuv_numero_avviso" ( type "varchar", NotNull : false )
     * @return the field value
     */
    public String getIuvNumeroAvviso() {
        return this.iuvNumeroAvviso;
    }

    /**
     * Set the "auxDigit" field value
     * This field is mapped on the database column "aux_digit" ( type "bpchar", NotNull : false )
     * @param auxDigit
     */
    public void setAuxDigit( String auxDigit ) {
        this.auxDigit = auxDigit;
    }
    /**
     * Get the "auxDigit" field value
     * This field is mapped on the database column "aux_digit" ( type "bpchar", NotNull : false )
     * @return the field value
     */
    public String getAuxDigit() {
        return this.auxDigit;
    }

    /**
     * Set the "applicationCode" field value
     * This field is mapped on the database column "application_code" ( type "bpchar", NotNull : false )
     * @param applicationCode
     */
    public void setApplicationCode( String applicationCode ) {
        this.applicationCode = applicationCode;
    }
    /**
     * Get the "applicationCode" field value
     * This field is mapped on the database column "application_code" ( type "bpchar", NotNull : false )
     * @return the field value
     */
    public String getApplicationCode() {
        return this.applicationCode;
    }

    /**
     * Set the "codicePagamentoRifEnte" field value
     * This field is mapped on the database column "codice_pagamento_rif_ente" ( type "varchar", NotNull : false )
     * @param codicePagamentoRifEnte
     */
    public void setCodicePagamentoRifEnte( String codicePagamentoRifEnte ) {
        this.codicePagamentoRifEnte = codicePagamentoRifEnte;
    }
    /**
     * Get the "codicePagamentoRifEnte" field value
     * This field is mapped on the database column "codice_pagamento_rif_ente" ( type "varchar", NotNull : false )
     * @return the field value
     */
    public String getCodicePagamentoRifEnte() {
        return this.codicePagamentoRifEnte;
    }

    /**
     * Set the "annoRiferimento" field value
     * This field is mapped on the database column "anno_riferimento" ( type "int4", NotNull : false )
     * @param annoRiferimento
     */
    public void setAnnoRiferimento( Integer annoRiferimento ) {
        this.annoRiferimento = annoRiferimento;
    }
    /**
     * Get the "annoRiferimento" field value
     * This field is mapped on the database column "anno_riferimento" ( type "int4", NotNull : false )
     * @return the field value
     */
    public Integer getAnnoRiferimento() {
        return this.annoRiferimento;
    }

    /**
     * Set the "dataScadenza" field value
     * This field is mapped on the database column "data_scadenza" ( type "date", NotNull : false )
     * @param dataScadenza
     */
    public void setDataScadenza( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }
    /**
     * Get the "dataScadenza" field value
     * This field is mapped on the database column "data_scadenza" ( type "date", NotNull : false )
     * @return the field value
     */
    public Date getDataScadenza() {
        return this.dataScadenza;
    }

    /**
     * Set the "numeroRata" field value
     * This field is mapped on the database column "numero_rata" ( type "varchar", NotNull : false )
     * @param numeroRata
     */
    public void setNumeroRata( String numeroRata ) {
        this.numeroRata = numeroRata;
    }
    /**
     * Get the "numeroRata" field value
     * This field is mapped on the database column "numero_rata" ( type "varchar", NotNull : false )
     * @return the field value
     */
    public String getNumeroRata() {
        return this.numeroRata;
    }

    /**
     * Set the "pagamentoSpontaneo" field value
     * This field is mapped on the database column "pagamento_spontaneo" ( type "bool", NotNull : true )
     * @param pagamentoSpontaneo
     */
    public void setPagamentoSpontaneo( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }
    /**
     * Get the "pagamentoSpontaneo" field value
     * This field is mapped on the database column "pagamento_spontaneo" ( type "bool", NotNull : true )
     * @return the field value
     */
    public Boolean getPagamentoSpontaneo() {
        return this.pagamentoSpontaneo;
    }

    /**
     * Set the "flagInviato" field value
     * This field is mapped on the database column "flag_inviato" ( type "bool", NotNull : true )
     * @param flagInviato
     */
    public void setFlagInviato( Boolean flagInviato ) {
        this.flagInviato = flagInviato;
    }
    /**
     * Get the "flagInviato" field value
     * This field is mapped on the database column "flag_inviato" ( type "bool", NotNull : true )
     * @return the field value
     */
    public Boolean getFlagInviato() {
        return this.flagInviato;
    }

    /**
     * Set the "utenteUltimaModifica" field value
     * This field is mapped on the database column "utente_ultima_modifica" ( type "varchar", NotNull : true )
     * @param utenteUltimaModifica
     */
    public void setUtenteUltimaModifica( String utenteUltimaModifica ) {
        this.utenteUltimaModifica = utenteUltimaModifica;
    }
    /**
     * Get the "utenteUltimaModifica" field value
     * This field is mapped on the database column "utente_ultima_modifica" ( type "varchar", NotNull : true )
     * @return the field value
     */
    public String getUtenteUltimaModifica() {
        return this.utenteUltimaModifica;
    }

    /**
     * Set the "flagPagamentoAutenticato" field value
     * This field is mapped on the database column "flag_pagamento_autenticato" ( type "bool", NotNull : true )
     * @param flagPagamentoAutenticato
     */
    public void setFlagPagamentoAutenticato( Boolean flagPagamentoAutenticato ) {
        this.flagPagamentoAutenticato = flagPagamentoAutenticato;
    }

    /**
     * Get the "flagPagamentoAutenticato" field value
     * This field is mapped on the database column "flag_pagamento_autenticato" ( type "bool", NotNull : true )
     * @return the field value
     */
    public Boolean getFlagPagamentoAutenticato() {
        return this.flagPagamentoAutenticato;
    }

    public BigDecimal getImportoPrincipale () {
        return importoPrincipale;
    }

    public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
        this.importoPrincipale = importoPrincipale;
    }

    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTRegistroVersamenti( List<EpayTRegistroVersamentiReferenceDTO> listOfEpayTRegistroVersamenti ) {
        this.listOfEpayTRegistroVersamenti = listOfEpayTRegistroVersamenti;
    }
    public List<EpayTRegistroVersamentiReferenceDTO> getListOfEpayTRegistroVersamenti() {
        return this.listOfEpayTRegistroVersamenti;
    }
    public void setListOfEpayTDatiSingoliVersamenti( List<EpayTDatiSingoliVersamentiReferenceDTO> listOfEpayTDatiSingoliVersamenti ) {
        this.listOfEpayTDatiSingoliVersamenti = listOfEpayTDatiSingoliVersamenti;
    }
    public List<EpayTDatiSingoliVersamentiReferenceDTO> getListOfEpayTDatiSingoliVersamenti() {
        return this.listOfEpayTDatiSingoliVersamenti;
    }
    public void setEpayDStatoPagamento( EpayDStatoPagamentoReferenceDTO epayDStatoPagamento ) {
        this.epayDStatoPagamento = epayDStatoPagamento;
    }
    public EpayDStatoPagamentoReferenceDTO getEpayDStatoPagamento() {
        return this.epayDStatoPagamento;
    }
    public void setListOfEpayTPagamentoRiferimenti( List<EpayTPagamentoRiferimentiReferenceDTO> listOfEpayTPagamentoRiferimenti ) {
        this.listOfEpayTPagamentoRiferimenti = listOfEpayTPagamentoRiferimenti;
    }
    public List<EpayTPagamentoRiferimentiReferenceDTO> getListOfEpayTPagamentoRiferimenti() {
        return this.listOfEpayTPagamentoRiferimenti;
    }
    public void setListOfEpayTRegistroElaborazioni( List<EpayTRegistroElaborazioniReferenceDTO> listOfEpayTRegistroElaborazioni ) {
        this.listOfEpayTRegistroElaborazioni = listOfEpayTRegistroElaborazioni;
    }
    public List<EpayTRegistroElaborazioniReferenceDTO> getListOfEpayTRegistroElaborazioni() {
        return this.listOfEpayTRegistroElaborazioni;
    }
    public void setEpayTTipoPagamento( EpayTTipoPagamentoReferenceDTO epayTTipoPagamento ) {
        this.epayTTipoPagamento = epayTTipoPagamento;
    }
    public EpayTTipoPagamentoReferenceDTO getEpayTTipoPagamento() {
        return this.epayTTipoPagamento;
    }
    public void setEpayTAnagrafica( EpayTAnagraficaReferenceDTO epayTAnagrafica ) {
        this.epayTAnagrafica = epayTAnagrafica;
    }
    public EpayTAnagraficaReferenceDTO getEpayTAnagrafica() {
        return this.epayTAnagrafica;
    }
    public void setListOfEpayTPagamentoComponenti( List<EpayTPagamentoComponentiReferenceDTO> listOfEpayTPagamentoComponenti ) {
        this.listOfEpayTPagamentoComponenti = listOfEpayTPagamentoComponenti;
    }
    public List<EpayTPagamentoComponentiReferenceDTO> getListOfEpayTPagamentoComponenti() {
        return this.listOfEpayTPagamentoComponenti;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(idPagamento);
        sb.append("|");
        sb.append(dataInserimento);
        sb.append("|");
        sb.append(causale);
        sb.append("|");
        sb.append(importo);
        sb.append("|");
        sb.append(note);
        sb.append("|");
        sb.append(consensoPrivacy);
        sb.append("|");
        sb.append(inizioValidita);
        sb.append("|");
        sb.append(fineValidita);
        sb.append("|");
        sb.append(iuvNumeroAvviso);
        sb.append("|");
        sb.append(auxDigit);
        sb.append("|");
        sb.append(applicationCode);
        sb.append("|");
        sb.append(codicePagamentoRifEnte);
        sb.append("|");
        sb.append(annoRiferimento);
        sb.append("|");
        sb.append(dataScadenza);
        sb.append("|");
        sb.append(numeroRata);
        sb.append("|");
        sb.append(pagamentoSpontaneo);
        sb.append("|");
        sb.append(flagInviato);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        sb.append("|");
        sb.append(flagPagamentoAutenticato);
        sb.append ( "|" );
        sb.append ( importoPrincipale );
        sb.append ( "|" );
        sb.append ( identificativoDominio );
        return sb.toString();
    }



}
