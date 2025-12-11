/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import java.util.Date;
/**
 * Reference DTO for entity "epay_t_tipo_pagamento" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author fabio.fenoglio
 *
 */
public class EpayTTipoPagamentoReferenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long idTipoPagamento ; // Long // Id or Primary Key

    @NotNull
    @Size( min = 1, max = 4 )
    private String codiceVersamento ;  // String 
    @NotNull
    @Size( min = 1, max = 140 )
    private String descrizionePortale ;  // String 
    @Size( max = 2000 )
    private String compilazioneNote ;  // String 

    private Date inizioValidita ;  // Date 

    private Date fineValidita ;  // Date 
    @NotNull
    @Size( min = 1, max = 30 )
    private String idApplicazione ;  // String 
    @NotNull
    private Long contatoreSelezioni ;  // Long 
    @NotNull
    private Long contatoreCompilazioni ;  // Long 
    @NotNull
    private Long contatorePagamenti ;  // Long 
    @NotNull
    private Boolean pagamentoSpontaneo ;  // Boolean 
    @Size( max = 140 )
    private String datiSpecificiRiscossione ;  // String 
    @NotNull
    private Boolean flagInvioPagamenti ;  // Boolean 
    @Size( max = 35 )
    private String numeroAccertamento ;  // String 

    private Long annoAccertamento ;  // Long 
    
    private Boolean flagMultibeneficiario; //Boolean
    
    @Size( max = 100 )
    private String chiaveIntersistema ;  // String 

    /**
     * Default constructor
     */
    public EpayTTipoPagamentoReferenceDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idTipoPagamento" field value
     * This field is mapped on the database column "id_tipo_pagamento" ( type "bigserial", NotNull : true ) 
     * @param idTipoPagamento
     */
	public void setIdTipoPagamento( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento ;
    }
    /**
     * Get the "idTipoPagamento" field value
     * This field is mapped on the database column "id_tipo_pagamento" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getIdTipoPagamento() {
        return this.idTipoPagamento;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "codiceVersamento" field value
     * This field is mapped on the database column "codice_versamento" ( type "bpchar", NotNull : true ) 
     * @param codiceVersamento
     */
    public void setCodiceVersamento( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }
    /**
     * Get the "codiceVersamento" field value
     * This field is mapped on the database column "codice_versamento" ( type "bpchar", NotNull : true ) 
     * @return the field value
     */
    public String getCodiceVersamento() {
        return this.codiceVersamento;
    }

    /**
     * Set the "descrizionePortale" field value
     * This field is mapped on the database column "descrizione_portale" ( type "varchar", NotNull : true ) 
     * @param descrizionePortale
     */
    public void setDescrizionePortale( String descrizionePortale ) {
        this.descrizionePortale = descrizionePortale;
    }
    /**
     * Get the "descrizionePortale" field value
     * This field is mapped on the database column "descrizione_portale" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getDescrizionePortale() {
        return this.descrizionePortale;
    }

    /**
     * Set the "compilazioneNote" field value
     * This field is mapped on the database column "compilazione_note" ( type "varchar", NotNull : false ) 
     * @param compilazioneNote
     */
    public void setCompilazioneNote( String compilazioneNote ) {
        this.compilazioneNote = compilazioneNote;
    }
    /**
     * Get the "compilazioneNote" field value
     * This field is mapped on the database column "compilazione_note" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCompilazioneNote() {
        return this.compilazioneNote;
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
     * Set the "idApplicazione" field value
     * This field is mapped on the database column "id_applicazione" ( type "varchar", NotNull : true ) 
     * @param idApplicazione
     */
    public void setIdApplicazione( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
    }
    /**
     * Get the "idApplicazione" field value
     * This field is mapped on the database column "id_applicazione" ( type "varchar", NotNull : true ) 
     * @return the field value
     */
    public String getIdApplicazione() {
        return this.idApplicazione;
    }

    /**
     * Set the "contatoreSelezioni" field value
     * This field is mapped on the database column "contatore_selezioni" ( type "int8", NotNull : true ) 
     * @param contatoreSelezioni
     */
    public void setContatoreSelezioni( Long contatoreSelezioni ) {
        this.contatoreSelezioni = contatoreSelezioni;
    }
    /**
     * Get the "contatoreSelezioni" field value
     * This field is mapped on the database column "contatore_selezioni" ( type "int8", NotNull : true ) 
     * @return the field value
     */
    public Long getContatoreSelezioni() {
        return this.contatoreSelezioni;
    }

    /**
     * Set the "contatoreCompilazioni" field value
     * This field is mapped on the database column "contatore_compilazioni" ( type "int8", NotNull : true ) 
     * @param contatoreCompilazioni
     */
    public void setContatoreCompilazioni( Long contatoreCompilazioni ) {
        this.contatoreCompilazioni = contatoreCompilazioni;
    }
    /**
     * Get the "contatoreCompilazioni" field value
     * This field is mapped on the database column "contatore_compilazioni" ( type "int8", NotNull : true ) 
     * @return the field value
     */
    public Long getContatoreCompilazioni() {
        return this.contatoreCompilazioni;
    }

    /**
     * Set the "contatorePagamenti" field value
     * This field is mapped on the database column "contatore_pagamenti" ( type "int8", NotNull : true ) 
     * @param contatorePagamenti
     */
    public void setContatorePagamenti( Long contatorePagamenti ) {
        this.contatorePagamenti = contatorePagamenti;
    }
    /**
     * Get the "contatorePagamenti" field value
     * This field is mapped on the database column "contatore_pagamenti" ( type "int8", NotNull : true ) 
     * @return the field value
     */
    public Long getContatorePagamenti() {
        return this.contatorePagamenti;
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
     * Set the "datiSpecificiRiscossione" field value
     * This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : false ) 
     * @param datiSpecificiRiscossione
     */
    public void setDatiSpecificiRiscossione( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }
    /**
     * Get the "datiSpecificiRiscossione" field value
     * This field is mapped on the database column "dati_specifici_riscossione" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDatiSpecificiRiscossione() {
        return this.datiSpecificiRiscossione;
    }

    /**
     * Set the "flagInvioPagamenti" field value
     * This field is mapped on the database column "flag_invio_pagamenti" ( type "bool", NotNull : true ) 
     * @param flagInvioPagamenti
     */
    public void setFlagInvioPagamenti( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }
    /**
     * Get the "flagInvioPagamenti" field value
     * This field is mapped on the database column "flag_invio_pagamenti" ( type "bool", NotNull : true ) 
     * @return the field value
     */
    public Boolean getFlagInvioPagamenti() {
        return this.flagInvioPagamenti;
    }

    /**
     * Set the "numeroAccertamento" field value
     * This field is mapped on the database column "numero_accertamento" ( type "varchar", NotNull : false ) 
     * @param numeroAccertamento
     */
    public void setNumeroAccertamento( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }
    /**
     * Get the "numeroAccertamento" field value
     * This field is mapped on the database column "numero_accertamento" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getNumeroAccertamento() {
        return this.numeroAccertamento;
    }

    /**
     * Set the "annoAccertamento" field value
     * This field is mapped on the database column "anno_accertamento" ( type "int8", NotNull : false ) 
     * @param annoAccertamento
     */
    public void setAnnoAccertamento( Long annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }
    /**
     * Get the "annoAccertamento" field value
     * This field is mapped on the database column "anno_accertamento" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getAnnoAccertamento() {
        return this.annoAccertamento;
    }
    
    /**
     * Get the "flagMultibeneficiario" field value
     * This field is mapped on the database column "flagMultibeneficiario" ( type "bool", NotNull : false ) 
     * @return the field value
     */
	public Boolean getFlagMultibeneficiario() {
		return flagMultibeneficiario;
	}

	
	 /**
     * Set the "flagMultibeneficiario" field value
     * This field is mapped on the database column "flagMultibeneficiario" ( type "bool", NotNull : false ) 
     * @param flagMultibeneficiario
     */
	public void setFlagMultibeneficiario(Boolean flagMultibeneficiario) {
		this.flagMultibeneficiario = flagMultibeneficiario;
	}


    /**
     * Set the "chiaveIntersistema" field value
     * This field is mapped on the database column "chiave_intersistema" ( type "varchar", NotNull : false ) 
     * @param chiaveIntersistema
     */
    public void setChiaveIntersistema( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }
    /**
     * Get the "chiaveIntersistema" field value
     * This field is mapped on the database column "chiave_intersistema" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getChiaveIntersistema() {
        return this.chiaveIntersistema;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idTipoPagamento);
        sb.append("|");
        sb.append(codiceVersamento);
        sb.append("|");
        sb.append(descrizionePortale);
        sb.append("|");
        sb.append(compilazioneNote);
        sb.append("|");
        sb.append(inizioValidita);
        sb.append("|");
        sb.append(fineValidita);
        sb.append("|");
        sb.append(idApplicazione);
        sb.append("|");
        sb.append(contatoreSelezioni);
        sb.append("|");
        sb.append(contatoreCompilazioni);
        sb.append("|");
        sb.append(contatorePagamenti);
        sb.append("|");
        sb.append(pagamentoSpontaneo);
        sb.append("|");
        sb.append(datiSpecificiRiscossione);
        sb.append("|");
        sb.append(flagInvioPagamenti);
        sb.append("|");
        sb.append(numeroAccertamento);
        sb.append("|");
        sb.append(annoAccertamento);
        sb.append ( "|" );
        sb.append ( flagMultibeneficiario );
        sb.append("|");
        sb.append(chiaveIntersistema);
        return sb.toString(); 
    } 



}
