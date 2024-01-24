/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistent class for "EpayTPagamentoStorico" entity stored in table "epay_t_pagamento_storico" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_pagamento_storico", schema="epay" )
// Define named queries here
@NamedQueries ( {
    @NamedQuery ( name="EpayTPagamentoStorico.countAll",  query="SELECT COUNT(x) FROM EpayTPagamentoStorico x" ),
    @NamedQuery ( name="EpayTPagamentoStorico.countById", query="SELECT COUNT(x) FROM EpayTPagamentoStorico x WHERE x.id = ?1 " )
} )
public class EpayTPagamentoStorico implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static EpayTPagamentoStorico reference ( Long id ) {
        EpayTPagamentoStorico reference = new EpayTPagamentoStorico ();
        reference.setId( id );
        return reference;
    }

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_storicizzazione", nullable=false)
    private Date dataStoricizzazione ;

    @Column(name="id_pagamento", nullable=false)
    private Long idPagamento ;

    @Column(name="id_tipo_pagamento")
    private Long idTipoPagamento ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_inserimento")
    private Date dataInserimento ;

    @Column(name="causale", length=140)
    private String causale ;

    @Column(name="importo")
    private BigDecimal importo ;

    @Column(name="note", length=2000)
    private String note ;

    @Column(name="consenso_privacy")
    private Boolean consensoPrivacy ;

    @Temporal(TemporalType.DATE)
    @Column(name="inizio_validita")
    private Date inizioValidita ;

    @Temporal(TemporalType.DATE)
    @Column(name="fine_validita")
    private Date fineValidita ;

    @Column(name="iuv_numero_avviso", length=25)
    private String iuvNumeroAvviso ;

    @Column(name="aux_digit", length=1)
    private String auxDigit ;

    @Column(name="application_code", length=2)
    private String applicationCode ;

    @Column(name="codice_pagamento_rif_ente", length=200)
    private String codicePagamentoRifEnte ;

    @Column(name="anno_riferimento")
    private Integer annoRiferimento ;

    @Temporal(TemporalType.DATE)
    @Column(name="data_scadenza")
    private Date dataScadenza ;

    @Column(name="id_stato_corrente")
    private Short idStatoCorrente ;

    @Column(name="numero_rata", length=35)
    private String numeroRata ;

    @Column(name="id_anagrafica_pagatore")
    private Long idAnagraficaPagatore ;

    @Column(name="pagamento_spontaneo")
    private Boolean pagamentoSpontaneo ;

    @Column(name="flag_inviato")
    private Boolean flagInviato ;

    @Column(name="utente_ultima_modifica", nullable=false, length=100)
    private String utenteUltimaModifica ;

    @Column ( name = "identificativo_dominio" )
    private String identificativoDominio;

    @Column ( name = "importo_principale", precision = 10, scale = 2 )
    private BigDecimal importoPrincipale;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTPagamentoStorico() {
        super();
    }

    @Override
    public Long getPrimaryKey() {
        return getId();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : data_storicizzazione ( timestamp )
    public void setDataStoricizzazione( Date dataStoricizzazione ) {
        this.dataStoricizzazione = dataStoricizzazione;
    }
    public Date getDataStoricizzazione() {
        return this.dataStoricizzazione;
    }

    //--- DATABASE MAPPING : id_pagamento ( int8 )
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    //--- DATABASE MAPPING : id_tipo_pagamento ( int8 )
    public void setIdTipoPagamento( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }
    public Long getIdTipoPagamento() {
        return this.idTipoPagamento;
    }

    //--- DATABASE MAPPING : data_inserimento ( timestamp )
    public void setDataInserimento( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }
    public Date getDataInserimento() {
        return this.dataInserimento;
    }

    //--- DATABASE MAPPING : causale ( varchar )
    public void setCausale( String causale ) {
        this.causale = causale;
    }
    public String getCausale() {
        return this.causale;
    }

    //--- DATABASE MAPPING : importo ( numeric )
    public void setImporto( BigDecimal importo ) {
        this.importo = importo;
    }
    public BigDecimal getImporto() {
        return this.importo;
    }

    //--- DATABASE MAPPING : note ( varchar )
    public void setNote( String note ) {
        this.note = note;
    }
    public String getNote() {
        return this.note;
    }

    //--- DATABASE MAPPING : consenso_privacy ( bool )
    public void setConsensoPrivacy( Boolean consensoPrivacy ) {
        this.consensoPrivacy = consensoPrivacy;
    }
    public Boolean getConsensoPrivacy() {
        return this.consensoPrivacy;
    }

    //--- DATABASE MAPPING : inizio_validita ( date )
    public void setInizioValidita( Date inizioValidita ) {
        this.inizioValidita = inizioValidita;
    }
    public Date getInizioValidita() {
        return this.inizioValidita;
    }

    //--- DATABASE MAPPING : fine_validita ( date )
    public void setFineValidita( Date fineValidita ) {
        this.fineValidita = fineValidita;
    }
    public Date getFineValidita() {
        return this.fineValidita;
    }

    //--- DATABASE MAPPING : iuv_numero_avviso ( varchar )
    public void setIuvNumeroAvviso( String iuvNumeroAvviso ) {
        this.iuvNumeroAvviso = iuvNumeroAvviso;
    }
    public String getIuvNumeroAvviso() {
        return this.iuvNumeroAvviso;
    }

    //--- DATABASE MAPPING : aux_digit ( bpchar )
    public void setAuxDigit( String auxDigit ) {
        this.auxDigit = auxDigit;
    }
    public String getAuxDigit() {
        return this.auxDigit;
    }

    //--- DATABASE MAPPING : application_code ( bpchar )
    public void setApplicationCode( String applicationCode ) {
        this.applicationCode = applicationCode;
    }
    public String getApplicationCode() {
        return this.applicationCode;
    }

    //--- DATABASE MAPPING : codice_pagamento_rif_ente ( varchar )
    public void setCodicePagamentoRifEnte( String codicePagamentoRifEnte ) {
        this.codicePagamentoRifEnte = codicePagamentoRifEnte;
    }
    public String getCodicePagamentoRifEnte() {
        return this.codicePagamentoRifEnte;
    }

    //--- DATABASE MAPPING : anno_riferimento ( int4 )
    public void setAnnoRiferimento( Integer annoRiferimento ) {
        this.annoRiferimento = annoRiferimento;
    }
    public Integer getAnnoRiferimento() {
        return this.annoRiferimento;
    }

    //--- DATABASE MAPPING : data_scadenza ( date )
    public void setDataScadenza( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }
    public Date getDataScadenza() {
        return this.dataScadenza;
    }

    //--- DATABASE MAPPING : id_stato_corrente ( int2 )
    public void setIdStatoCorrente( Short idStatoCorrente ) {
        this.idStatoCorrente = idStatoCorrente;
    }
    public Short getIdStatoCorrente() {
        return this.idStatoCorrente;
    }

    //--- DATABASE MAPPING : numero_rata ( varchar )
    public void setNumeroRata( String numeroRata ) {
        this.numeroRata = numeroRata;
    }
    public String getNumeroRata() {
        return this.numeroRata;
    }

    //--- DATABASE MAPPING : id_anagrafica_pagatore ( int8 )
    public void setIdAnagraficaPagatore( Long idAnagraficaPagatore ) {
        this.idAnagraficaPagatore = idAnagraficaPagatore;
    }
    public Long getIdAnagraficaPagatore() {
        return this.idAnagraficaPagatore;
    }

    //--- DATABASE MAPPING : pagamento_spontaneo ( bool )
    public void setPagamentoSpontaneo( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }
    public Boolean getPagamentoSpontaneo() {
        return this.pagamentoSpontaneo;
    }

    //--- DATABASE MAPPING : flag_inviato ( bool )
    public void setFlagInviato( Boolean flagInviato ) {
        this.flagInviato = flagInviato;
    }
    public Boolean getFlagInviato() {
        return this.flagInviato;
    }

    //--- DATABASE MAPPING : utente_ultima_modifica ( varchar )
    public void setUtenteUltimaModifica( String utenteUltimaModifica ) {
        this.utenteUltimaModifica = utenteUltimaModifica;
    }
    public String getUtenteUltimaModifica() {
        return this.utenteUltimaModifica;
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

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(id);
        sb.append("]:");
        sb.append(dataStoricizzazione);
        sb.append("|");
        sb.append(idPagamento);
        sb.append("|");
        sb.append(idTipoPagamento);
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
        sb.append(idStatoCorrente);
        sb.append("|");
        sb.append(numeroRata);
        sb.append("|");
        sb.append(idAnagraficaPagatore);
        sb.append("|");
        sb.append(pagamentoSpontaneo);
        sb.append("|");
        sb.append(flagInviato);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        sb.append ( "|" );
        sb.append ( importoPrincipale );
        sb.append ( "|" );
        sb.append ( identificativoDominio );
        return sb.toString();
    }

}
