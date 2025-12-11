/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;


import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for "EpayTTipoPagamentoTemp" entity stored in table "epay_t_tipo_pagamento_temp" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_tipo_pagamento_temp", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTTipoPagamentoTemp.countAll",  query="SELECT COUNT(x) FROM EpayTTipoPagamentoTemp x" ),
  @NamedQuery ( name="EpayTTipoPagamentoTemp.countById", query="SELECT COUNT(x) FROM EpayTTipoPagamentoTemp x WHERE x.idTipoPagamentoTemp = ?1 " )
} )
public class EpayTTipoPagamentoTemp implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTTipoPagamentoTemp reference ( Long idTipoPagamentoTemp ) {
        EpayTTipoPagamentoTemp reference = new EpayTTipoPagamentoTemp ();
        reference.setIdTipoPagamentoTemp( idTipoPagamentoTemp );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_tipo_pagamento_temp", nullable=false)
    private Long idTipoPagamentoTemp ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="id_operazione", nullable=false, length=100)
    private String idOperazione ;

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

    @Column(name="id_applicazione", length=30)
    private String idApplicazione ;

    @Column(name="contatore_selezioni")
    private Long contatoreSelezioni ;

    @Column(name="contatore_compilazioni")
    private Long contatoreCompilazioni ;

    @Column(name="contatore_pagamenti")
    private Long contatorePagamenti ;

    @Column(name="pagamento_spontaneo")
    private Boolean pagamentoSpontaneo ;

    @Column(name="dati_specifici_riscossione", length=140)
    private String datiSpecificiRiscossione ;

    @Column(name="flag_invio_pagamenti")
    private Boolean flagInvioPagamenti ;
    
    @Column ( name = "multibeneficiario" )
    private Boolean flagMultibeneficiario;

    @Column(name="numero_accertamento", length=35)
    private String numeroAccertamento ;

    @Column(name="anno_accertamento")
    private Long annoAccertamento ;

    @Column(name="chiave_intersistema", length=100)
    private String chiaveIntersistema ;

    @Column(name="tipo_operazione", nullable=false, length=100)
    private String tipoOperazione ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_ente", referencedColumnName="id_ente")
	private EpayTEnti epayTEnti ;
    
    @ManyToOne
    @JoinColumn(name="id_tipo_pagamento", referencedColumnName="id_tipo_pagamento")
	private EpayTTipoPagamento epayTTipoPagamento ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTTipoPagamentoTemp() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdTipoPagamentoTemp();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdTipoPagamentoTemp( Long idTipoPagamentoTemp ) {
        this.idTipoPagamentoTemp = idTipoPagamentoTemp ;
    }
    public Long getIdTipoPagamentoTemp() {
        return this.idTipoPagamentoTemp;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : id_operazione ( varchar ) 
    public void setIdOperazione( String idOperazione ) {
        this.idOperazione = idOperazione;
    }
    public String getIdOperazione() {
        return this.idOperazione;
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
   	

    //--- DATABASE MAPPING : numero_accertamento ( varchar ) 
    public void setNumeroAccertamento( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }
    public String getNumeroAccertamento() {
        return this.numeroAccertamento;
    }

    //--- DATABASE MAPPING : anno_accertamento ( int8 ) 
    public void setAnnoAccertamento( Long annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }
    public Long getAnnoAccertamento() {
        return this.annoAccertamento;
    }

    //--- DATABASE MAPPING : chiave_intersistema ( varchar ) 
    public void setChiaveIntersistema( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }
    public String getChiaveIntersistema() {
        return this.chiaveIntersistema;
    }

    //--- DATABASE MAPPING : tipo_operazione ( varchar ) 
    public void setTipoOperazione( String tipoOperazione ) {
        this.tipoOperazione = tipoOperazione;
    }
    public String getTipoOperazione() {
        return this.tipoOperazione;
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
    
    public void setEpayTTipoPagamento( EpayTTipoPagamento epayTTipoPagamento ) {
        this.epayTTipoPagamento = epayTTipoPagamento;
    }
    public EpayTTipoPagamento getEpayTTipoPagamento() {
        return this.epayTTipoPagamento;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idTipoPagamentoTemp);
        sb.append("]:"); 
        sb.append(idOperazione);
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
        sb.append(numeroAccertamento);
        sb.append("|");
        sb.append(annoAccertamento);
        sb.append("|");
        sb.append(chiaveIntersistema);
        sb.append("|");
        sb.append(tipoOperazione);
        return sb.toString(); 
    } 

}
