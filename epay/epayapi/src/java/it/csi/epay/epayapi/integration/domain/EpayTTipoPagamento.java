/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Persistent class for "EpayTTipoPagamento" entity stored in table "epay_t_tipo_pagamento" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_tipo_pagamento", schema="epay" )
// Define named queries here
@NamedQueries ( {
    @NamedQuery ( name="EpayTTipoPagamento.countAll",  query="SELECT COUNT(x) FROM EpayTTipoPagamento x" ),
    @NamedQuery ( name="EpayTTipoPagamento.countById", query="SELECT COUNT(x) FROM EpayTTipoPagamento x WHERE x.idTipoPagamento = ?1 " )
} )
public class EpayTTipoPagamento implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static EpayTTipoPagamento reference ( Long idTipoPagamento ) {
        EpayTTipoPagamento reference = new EpayTTipoPagamento ();
        reference.setIdTipoPagamento( idTipoPagamento );
        return reference;
    }

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_tipo_pagamento", nullable=false)
    private Long idTipoPagamento ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
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

    @Column(name="numero_accertamento", length=35)
    private String numeroAccertamento ;

    @Column(name="anno_accertamento")
    private Long annoAccertamento ;

    @Column ( name = "multibeneficiario" )
    private Boolean flagMultibeneficiario;

    @Column(name="chiave_intersistema", length=100)
    private String chiaveIntersistema ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="epayTTipoPagamento", targetEntity=EpayTTipoPagamentoTemp.class)
    private List<EpayTTipoPagamentoTemp> listOfEpayTTipoPagamentoTemp ;

    @OneToMany(mappedBy="epayTTipoPagamento", targetEntity=EpayTPagamento.class)
    private List<EpayTPagamento> listOfEpayTPagamento ;

    @OneToMany(mappedBy="epayTTipoPagamento", targetEntity=EpayTDatiAvvisoPagamento.class)
    private List<EpayTDatiAvvisoPagamento> listOfEpayTDatiAvvisoPagamento ;

    @ManyToOne
    @JoinColumn(name="id_ente", referencedColumnName="id_ente")
    private EpayTEnti epayTEnti ;

    //bi-directional many-to-one association to EpayTPagamentoSecondario
    @OneToMany ( mappedBy = "epayTTipoPagamento" )
    private List<EpayTPagamentoSecondario> epayTPagamentoSecondarios;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTTipoPagamento() {
        super();
    }

    @Override
    public Long getPrimaryKey() {
        return getIdTipoPagamento();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdTipoPagamento( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento ;
    }
    public Long getIdTipoPagamento() {
        return this.idTipoPagamento;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
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


    //--- DATABASE MAPPING : flagMultibeneficiario ( bool )
    public Boolean getFlagMultibeneficiario() {
        return flagMultibeneficiario;
    }

    public void setFlagMultibeneficiario(Boolean flagMultibeneficiario) {
        this.flagMultibeneficiario = flagMultibeneficiario;
    }

    //--- DATABASE MAPPING : chiave_intersistema ( varchar )
    public void setChiaveIntersistema( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }
    public String getChiaveIntersistema() {
        return this.chiaveIntersistema;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTTipoPagamentoTemp( List<EpayTTipoPagamentoTemp> listOfEpayTTipoPagamentoTemp ) {
        this.listOfEpayTTipoPagamentoTemp = listOfEpayTTipoPagamentoTemp;
    }
    public List<EpayTTipoPagamentoTemp> getListOfEpayTTipoPagamentoTemp() {
        return this.listOfEpayTTipoPagamentoTemp;
    }

    public void setListOfEpayTPagamento( List<EpayTPagamento> listOfEpayTPagamento ) {
        this.listOfEpayTPagamento = listOfEpayTPagamento;
    }
    public List<EpayTPagamento> getListOfEpayTPagamento() {
        return this.listOfEpayTPagamento;
    }

    public void setListOfEpayTDatiAvvisoPagamento( List<EpayTDatiAvvisoPagamento> listOfEpayTDatiAvvisoPagamento ) {
        this.listOfEpayTDatiAvvisoPagamento = listOfEpayTDatiAvvisoPagamento;
    }
    public List<EpayTDatiAvvisoPagamento> getListOfEpayTDatiAvvisoPagamento() {
        return this.listOfEpayTDatiAvvisoPagamento;
    }

    public void setEpayTEnti( EpayTEnti epayTEnti ) {
        this.epayTEnti = epayTEnti;
    }
    public EpayTEnti getEpayTEnti() {
        return this.epayTEnti;
    }

    /**
     * @return the epayTPagamentoSecondarios
     */
    public List<EpayTPagamentoSecondario> getEpayTPagamentoSecondarios () {
        return epayTPagamentoSecondarios;
    }

    /**
     * @param epayTPagamentoSecondarios the epayTPagamentoSecondarios to set
     */
    public void setEpayTPagamentoSecondarios ( List<EpayTPagamentoSecondario> epayTPagamentoSecondarios ) {
        this.epayTPagamentoSecondarios = epayTPagamentoSecondarios;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(idTipoPagamento);
        sb.append("]:");
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
        sb.append("|");
        sb.append ( flagMultibeneficiario );
        sb.append("|");
        sb.append(chiaveIntersistema);
        return sb.toString();
    }

}
