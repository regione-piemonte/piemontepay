/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * DTO for entity "epay_t_tipo_pagamento" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTTipoPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idTipoPagamento; // Id or Primary Key

    @NotNull
    @Size ( min = 1, max = 4 )
    private String codiceVersamento;

    @NotNull
    @Size ( min = 1, max = 140 )
    private String descrizionePortale;

    @Size ( max = 2000 )
    private String compilazioneNote;

    private Date inizioValidita;

    private Date fineValidita;

    @NotNull
    @Size ( min = 1, max = 30 )
    private String idApplicazione;

    @NotNull
    private Long contatoreSelezioni;

    @NotNull
    private Long contatoreCompilazioni;

    @NotNull
    private Long contatorePagamenti;

    @NotNull
    private Boolean pagamentoSpontaneo;

    @Size ( max = 140 )
    private String datiSpecificiRiscossione;

    @NotNull
    private Boolean flagInvioPagamenti;

    @Size ( max = 35 )
    private String numeroAccertamento;

    private Long annoAccertamento;

    @Size ( max = 100 )
    private String chiaveIntersistema;

    @NotNull
    private Boolean flagInvioRt;

    private Boolean bollettinoPostale;
    
    private Boolean flagMultibeneficiario;

    private List<EpayTTipoPagamentoTempReferenceDTO> listOfEpayTTipoPagamentoTemp;

    private List<EpayTPagamentoReferenceDTO> listOfEpayTPagamento;

    private List<EpayTDatiAvvisoPagamentoReferenceDTO> listOfEpayTDatiAvvisoPagamento;

    private EpayTEntiReferenceDTO epayTEnti;

    /**
     * Default constructor
     */
    public EpayTTipoPagamentoDTO () {
        super ();
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idTipoPagamento" field value This field is mapped on the database column "id_tipo_pagamento" ( type "bigserial", NotNull : true )
     * 
     * @param idTipoPagamento
     */
    public void setIdTipoPagamento ( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    /**
     * Get the "idTipoPagamento" field value This field is mapped on the database column "id_tipo_pagamento" ( type "bigserial", NotNull : true )
     * 
     * @return the field value
     */
    public Long getIdTipoPagamento () {
        return this.idTipoPagamento;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "codiceVersamento" field value This field is mapped on the database column "codice_versamento" ( type "bpchar", NotNull : true )
     * 
     * @param codiceVersamento
     */
    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    /**
     * Get the "codiceVersamento" field value This field is mapped on the database column "codice_versamento" ( type "bpchar", NotNull : true )
     * 
     * @return the field value
     */
    public String getCodiceVersamento () {
        return this.codiceVersamento;
    }

    /**
     * Set the "descrizionePortale" field value This field is mapped on the database column "descrizione_portale" ( type "varchar", NotNull : true )
     * 
     * @param descrizionePortale
     */
    public void setDescrizionePortale ( String descrizionePortale ) {
        this.descrizionePortale = descrizionePortale;
    }

    /**
     * Get the "descrizionePortale" field value This field is mapped on the database column "descrizione_portale" ( type "varchar", NotNull : true )
     * 
     * @return the field value
     */
    public String getDescrizionePortale () {
        return this.descrizionePortale;
    }

    /**
     * Set the "compilazioneNote" field value This field is mapped on the database column "compilazione_note" ( type "varchar", NotNull : false )
     * 
     * @param compilazioneNote
     */
    public void setCompilazioneNote ( String compilazioneNote ) {
        this.compilazioneNote = compilazioneNote;
    }

    /**
     * Get the "compilazioneNote" field value This field is mapped on the database column "compilazione_note" ( type "varchar", NotNull : false )
     * 
     * @return the field value
     */
    public String getCompilazioneNote () {
        return this.compilazioneNote;
    }

    /**
     * Set the "inizioValidita" field value This field is mapped on the database column "inizio_validita" ( type "date", NotNull : false )
     * 
     * @param inizioValidita
     */
    public void setInizioValidita ( Date inizioValidita ) {
        this.inizioValidita = inizioValidita;
    }

    /**
     * Get the "inizioValidita" field value This field is mapped on the database column "inizio_validita" ( type "date", NotNull : false )
     * 
     * @return the field value
     */
    public Date getInizioValidita () {
        return this.inizioValidita;
    }

    /**
     * Set the "fineValidita" field value This field is mapped on the database column "fine_validita" ( type "date", NotNull : false )
     * 
     * @param fineValidita
     */
    public void setFineValidita ( Date fineValidita ) {
        this.fineValidita = fineValidita;
    }

    /**
     * Get the "fineValidita" field value This field is mapped on the database column "fine_validita" ( type "date", NotNull : false )
     * 
     * @return the field value
     */
    public Date getFineValidita () {
        return this.fineValidita;
    }

    /**
     * Set the "idApplicazione" field value This field is mapped on the database column "id_applicazione" ( type "varchar", NotNull : true )
     * 
     * @param idApplicazione
     */
    public void setIdApplicazione ( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
    }

    /**
     * Get the "idApplicazione" field value This field is mapped on the database column "id_applicazione" ( type "varchar", NotNull : true )
     * 
     * @return the field value
     */
    public String getIdApplicazione () {
        return this.idApplicazione;
    }

    /**
     * Set the "contatoreSelezioni" field value This field is mapped on the database column "contatore_selezioni" ( type "int8", NotNull : true )
     * 
     * @param contatoreSelezioni
     */
    public void setContatoreSelezioni ( Long contatoreSelezioni ) {
        this.contatoreSelezioni = contatoreSelezioni;
    }

    /**
     * Get the "contatoreSelezioni" field value This field is mapped on the database column "contatore_selezioni" ( type "int8", NotNull : true )
     * 
     * @return the field value
     */
    public Long getContatoreSelezioni () {
        return this.contatoreSelezioni;
    }

    /**
     * Set the "contatoreCompilazioni" field value This field is mapped on the database column "contatore_compilazioni" ( type "int8", NotNull : true )
     * 
     * @param contatoreCompilazioni
     */
    public void setContatoreCompilazioni ( Long contatoreCompilazioni ) {
        this.contatoreCompilazioni = contatoreCompilazioni;
    }

    /**
     * Get the "contatoreCompilazioni" field value This field is mapped on the database column "contatore_compilazioni" ( type "int8", NotNull : true )
     * 
     * @return the field value
     */
    public Long getContatoreCompilazioni () {
        return this.contatoreCompilazioni;
    }

    /**
     * Set the "contatorePagamenti" field value This field is mapped on the database column "contatore_pagamenti" ( type "int8", NotNull : true )
     * 
     * @param contatorePagamenti
     */
    public void setContatorePagamenti ( Long contatorePagamenti ) {
        this.contatorePagamenti = contatorePagamenti;
    }

    /**
     * Get the "contatorePagamenti" field value This field is mapped on the database column "contatore_pagamenti" ( type "int8", NotNull : true )
     * 
     * @return the field value
     */
    public Long getContatorePagamenti () {
        return this.contatorePagamenti;
    }

    /**
     * Set the "pagamentoSpontaneo" field value This field is mapped on the database column "pagamento_spontaneo" ( type "bool", NotNull : true )
     * 
     * @param pagamentoSpontaneo
     */
    public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }

    /**
     * Get the "pagamentoSpontaneo" field value This field is mapped on the database column "pagamento_spontaneo" ( type "bool", NotNull : true )
     * 
     * @return the field value
     */
    public Boolean getPagamentoSpontaneo () {
        return this.pagamentoSpontaneo;
    }

    /**
     * Set the "datiSpecificiRiscossione" field value This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : false
     * )
     * 
     * @param datiSpecificiRiscossione
     */
    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    /**
     * Get the "datiSpecificiRiscossione" field value This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : false
     * )
     * 
     * @return the field value
     */
    public String getDatiSpecificiRiscossione () {
        return this.datiSpecificiRiscossione;
    }

    /**
     * Set the "flagInvioPagamenti" field value This field is mapped on the database column "flag_invio_pagamenti" ( type "bool", NotNull : true )
     * 
     * @param flagInvioPagamenti
     */
    public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }

    /**
     * Get the "flagInvioPagamenti" field value This field is mapped on the database column "flag_invio_pagamenti" ( type "bool", NotNull : true )
     * 
     * @return the field value
     */
    public Boolean getFlagInvioPagamenti () {
        return this.flagInvioPagamenti;
    }

    /**
     * Set the "numeroAccertamento" field value This field is mapped on the database column "numero_accertamento" ( type "varchar", NotNull : false )
     * 
     * @param numeroAccertamento
     */
    public void setNumeroAccertamento ( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    /**
     * Get the "numeroAccertamento" field value This field is mapped on the database column "numero_accertamento" ( type "varchar", NotNull : false )
     * 
     * @return the field value
     */
    public String getNumeroAccertamento () {
        return this.numeroAccertamento;
    }

    /**
     * Set the "annoAccertamento" field value This field is mapped on the database column "anno_accertamento" ( type "int8", NotNull : false )
     * 
     * @param annoAccertamento
     */
    public void setAnnoAccertamento ( Long annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    /**
     * Get the "annoAccertamento" field value This field is mapped on the database column "anno_accertamento" ( type "int8", NotNull : false )
     * 
     * @return the field value
     */
    public Long getAnnoAccertamento () {
        return this.annoAccertamento;
    }

    /**
     * Set the "chiaveIntersistema" field value This field is mapped on the database column "chiave_intersistema" ( type "varchar", NotNull : false )
     * 
     * @param chiaveIntersistema
     */
    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    /**
     * Get the "chiaveIntersistema" field value This field is mapped on the database column "chiave_intersistema" ( type "varchar", NotNull : false )
     * 
     * @return the field value
     */
    public String getChiaveIntersistema () {
        return this.chiaveIntersistema;
    }

    public Boolean getFlagInvioRt () {
        return flagInvioRt;
    }

    public void setFlagInvioRt ( Boolean flagInvioRt ) {
        this.flagInvioRt = flagInvioRt;
    }

    public Boolean getBollettinoPostale () {
        return bollettinoPostale;
    }

    public void setBollettinoPostale ( Boolean bollettinoPostale ) {
        this.bollettinoPostale = bollettinoPostale;
    }
    

    /**
	 * @return the flagMultibeneficiario
	 */
	public Boolean getFlagMultibeneficiario() {
		return flagMultibeneficiario;
	}

	/**
	 * @param flagMultibeneficiario the flagMultibeneficiario to set
	 */
	public void setFlagMultibeneficiario(Boolean flagMultibeneficiario) {
		this.flagMultibeneficiario = flagMultibeneficiario;
	}

	//----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTTipoPagamentoTemp ( List<EpayTTipoPagamentoTempReferenceDTO> listOfEpayTTipoPagamentoTemp ) {
        this.listOfEpayTTipoPagamentoTemp = listOfEpayTTipoPagamentoTemp;
    }

    public List<EpayTTipoPagamentoTempReferenceDTO> getListOfEpayTTipoPagamentoTemp () {
        return this.listOfEpayTTipoPagamentoTemp;
    }

    public void setListOfEpayTPagamento ( List<EpayTPagamentoReferenceDTO> listOfEpayTPagamento ) {
        this.listOfEpayTPagamento = listOfEpayTPagamento;
    }

    public List<EpayTPagamentoReferenceDTO> getListOfEpayTPagamento () {
        return this.listOfEpayTPagamento;
    }

    public void setListOfEpayTDatiAvvisoPagamento ( List<EpayTDatiAvvisoPagamentoReferenceDTO> listOfEpayTDatiAvvisoPagamento ) {
        this.listOfEpayTDatiAvvisoPagamento = listOfEpayTDatiAvvisoPagamento;
    }

    public List<EpayTDatiAvvisoPagamentoReferenceDTO> getListOfEpayTDatiAvvisoPagamento () {
        return this.listOfEpayTDatiAvvisoPagamento;
    }

    public void setEpayTEnti ( EpayTEntiReferenceDTO epayTEnti ) {
        this.epayTEnti = epayTEnti;
    }

    public EpayTEntiReferenceDTO getEpayTEnti () {
        return this.epayTEnti;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString () {
        StringBuffer sb = new StringBuffer ();
        sb.append ( idTipoPagamento );
        sb.append ( "|" );
        sb.append ( codiceVersamento );
        sb.append ( "|" );
        sb.append ( descrizionePortale );
        sb.append ( "|" );
        sb.append ( compilazioneNote );
        sb.append ( "|" );
        sb.append ( inizioValidita );
        sb.append ( "|" );
        sb.append ( fineValidita );
        sb.append ( "|" );
        sb.append ( idApplicazione );
        sb.append ( "|" );
        sb.append ( contatoreSelezioni );
        sb.append ( "|" );
        sb.append ( contatoreCompilazioni );
        sb.append ( "|" );
        sb.append ( contatorePagamenti );
        sb.append ( "|" );
        sb.append ( pagamentoSpontaneo );
        sb.append ( "|" );
        sb.append ( datiSpecificiRiscossione );
        sb.append ( "|" );
        sb.append ( flagInvioPagamenti );
        sb.append ( "|" );
        sb.append ( numeroAccertamento );
        sb.append ( "|" );
        sb.append ( annoAccertamento );
        sb.append ( "|" );
        sb.append ( flagInvioRt );
        sb.append ( "|" );
        sb.append ( bollettinoPostale );
        sb.append ( "|" );
        sb.append ( flagMultibeneficiario );
        sb.append ( "|" );
        sb.append ( chiaveIntersistema );
        return sb.toString ();
    }

}
