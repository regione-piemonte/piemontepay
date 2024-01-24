/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for "EpayTTipoPagamentoLog" entity stored in table "epay_t_tipo_pagamento_log" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_tipo_pagamento_log", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTTipoPagamentoLog.countAll",  query="SELECT COUNT(x) FROM EpayTTipoPagamentoLog x" ),
  @NamedQuery ( name="EpayTTipoPagamentoLog.countById", query="SELECT COUNT(x) FROM EpayTTipoPagamentoLog x WHERE x.seq = ?1 " )
} )
public class EpayTTipoPagamentoLog implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTTipoPagamentoLog reference ( Long seq ) {
        EpayTTipoPagamentoLog reference = new EpayTTipoPagamentoLog ();
        reference.setSeq( seq );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="seq", nullable=false)
    private Long seq ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="id_tipo_pagamento", nullable=false)
    private Long idTipoPagamento ;

    @Column(name="codice_versamento", nullable=false, length=4)
    private String codiceVersamento ;

    @Column(name="descrizione_portale", nullable=false, length=140)
    private String descrizionePortale ;

    @Column(name="compilazione_note", length=2000)
    private String compilazioneNote ;

    @Temporal(TemporalType.DATE)
    @Column(name="inizio_validita")
    private Date inizioValidita ;

    @Temporal(TemporalType.DATE)
    @Column(name="fine_validita")
    private Date fineValidita ;

    @Column(name="id_applicazione", nullable=false, length=30)
    private String idApplicazione ;

    @Column(name="contatore_selezioni", nullable=false)
    private Long contatoreSelezioni ;

    @Column(name="contatore_compilazioni", nullable=false)
    private Long contatoreCompilazioni ;

    @Column(name="contatore_pagamenti", nullable=false)
    private Long contatorePagamenti ;

    @Column(name="pagamento_spontaneo", nullable=false)
    private Boolean pagamentoSpontaneo ;

    @Column(name="dati_specifici_riscossione", length=140)
    private String datiSpecificiRiscossione ;

    @Column(name="flag_invio_pagamenti", nullable=false)
    private Boolean flagInvioPagamenti ;
    
    @Column ( name = "multibeneficiario" )
    private Boolean flagMultibeneficiario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_trigger")
    private Date dataTrigger ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_ente", referencedColumnName="id_ente")
	private EpayTEnti epayTEnti ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTTipoPagamentoLog() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getSeq();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setSeq( Long seq ) {
        this.seq = seq ;
    }
    public Long getSeq() {
        return this.seq;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : id_tipo_pagamento ( bigserial ) 
    public void setIdTipoPagamento( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }
    public Long getIdTipoPagamento() {
        return this.idTipoPagamento;
    }

    //--- DATABASE MAPPING : codice_versamento ( bpchar ) 
    public void setCodiceVersamento( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }
    public String getCodiceVersamento() {
        return this.codiceVersamento;
    }

    //--- DATABASE MAPPING : descrizione_portale ( varchar ) 
    public void setDescrizionePortale( String descrizionePortale ) {
        this.descrizionePortale = descrizionePortale;
    }
    public String getDescrizionePortale() {
        return this.descrizionePortale;
    }

    //--- DATABASE MAPPING : compilazione_note ( varchar ) 
    public void setCompilazioneNote( String compilazioneNote ) {
        this.compilazioneNote = compilazioneNote;
    }
    public String getCompilazioneNote() {
        return this.compilazioneNote;
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

    //--- DATABASE MAPPING : id_applicazione ( varchar ) 
    public void setIdApplicazione( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
    }
    public String getIdApplicazione() {
        return this.idApplicazione;
    }

    //--- DATABASE MAPPING : contatore_selezioni ( int8 ) 
    public void setContatoreSelezioni( Long contatoreSelezioni ) {
        this.contatoreSelezioni = contatoreSelezioni;
    }
    public Long getContatoreSelezioni() {
        return this.contatoreSelezioni;
    }

    //--- DATABASE MAPPING : contatore_compilazioni ( int8 ) 
    public void setContatoreCompilazioni( Long contatoreCompilazioni ) {
        this.contatoreCompilazioni = contatoreCompilazioni;
    }
    public Long getContatoreCompilazioni() {
        return this.contatoreCompilazioni;
    }

    //--- DATABASE MAPPING : contatore_pagamenti ( int8 ) 
    public void setContatorePagamenti( Long contatorePagamenti ) {
        this.contatorePagamenti = contatorePagamenti;
    }
    public Long getContatorePagamenti() {
        return this.contatorePagamenti;
    }

    //--- DATABASE MAPPING : pagamento_spontaneo ( bool ) 
    public void setPagamentoSpontaneo( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }
    public Boolean getPagamentoSpontaneo() {
        return this.pagamentoSpontaneo;
    }

    //--- DATABASE MAPPING : dati_specifici_riscossione ( varchar ) 
    public void setDatiSpecificiRiscossione( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }
    public String getDatiSpecificiRiscossione() {
        return this.datiSpecificiRiscossione;
    }

    //--- DATABASE MAPPING : flag_invio_pagamenti ( bool ) 
    public void setFlagInvioPagamenti( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }
    public Boolean getFlagInvioPagamenti() {
        return this.flagInvioPagamenti;
    }
    
    //--- DATABASE MAPPING : flag_multibeneficiario ( bool ) 
   	public Boolean getFlagMultibeneficiario() {
   		return flagMultibeneficiario;
   	}

   	public void setFlagMultibeneficiario(Boolean flagMultibeneficiario) {
   		this.flagMultibeneficiario = flagMultibeneficiario;
   	}

    //--- DATABASE MAPPING : data_trigger ( timestamptz ) 
    public void setDataTrigger( Date dataTrigger ) {
        this.dataTrigger = dataTrigger;
    }
    public Date getDataTrigger() {
        return this.dataTrigger;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTEnti( EpayTEnti epayTEnti ) {
        this.epayTEnti = epayTEnti;
    }
    public EpayTEnti getEpayTEnti() {
        return this.epayTEnti;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(seq);
        sb.append("]:"); 
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
        sb.append(flagMultibeneficiario);
        sb.append("|");
        sb.append(dataTrigger);
        return sb.toString(); 
    } 

}
