/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Persistent class for "EpayTPagamento" entity stored in table "epay_t_pagamento" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_pagamento", schema="epay" )
// Define named queries here
@NamedQueries ( {
    @NamedQuery ( name="EpayTPagamento.countAll",  query="SELECT COUNT(x) FROM EpayTPagamento x" ),
    @NamedQuery ( name="EpayTPagamento.countById", query="SELECT COUNT(x) FROM EpayTPagamento x WHERE x.idPagamento = ?1 " )
} )
public class EpayTPagamento implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static EpayTPagamento reference ( Long idPagamento ) {
        EpayTPagamento reference = new EpayTPagamento ();
        reference.setIdPagamento( idPagamento );
        return reference;
    }

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_pagamento", nullable=false)
    private Long idPagamento ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_inserimento", nullable=false)
    private Date dataInserimento ;

    @Column(name="causale", nullable=false, length=140)
    private String causale ;

    @Column(name="importo", nullable=false)
    private BigDecimal importo ;

    @Column(name="note", length=2000)
    private String note ;

    @Column(name="consenso_privacy", nullable=false)
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

    @Column(name="numero_rata", length=35)
    private String numeroRata ;

    @Column(name="pagamento_spontaneo", nullable=false)
    private Boolean pagamentoSpontaneo ;

    @Column(name="flag_inviato", nullable=false)
    private Boolean flagInviato ;

    @Column(name="utente_ultima_modifica", nullable=false, length=100)
    private String utenteUltimaModifica ;

    @Column(name="flag_pagamento_autenticato", nullable=false)
    private Boolean flagPagamentoAutenticato ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="epayTPagamento", targetEntity=EpayTRegistroVersamenti.class)
    private List<EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti ;

    @OneToMany(mappedBy="epayTPagamento", targetEntity=EpayTDatiSingoliVersamenti.class)
    private List<EpayTDatiSingoliVersamenti> listOfEpayTDatiSingoliVersamenti ;

    @ManyToOne
    @JoinColumn(name="id_stato_corrente", referencedColumnName="id_stato")
    private EpayDStatoPagamento epayDStatoPagamento ;

    @OneToMany(mappedBy="epayTPagamento", targetEntity=EpayTPagamentoRiferimenti.class)
    private List<EpayTPagamentoRiferimenti> listOfEpayTPagamentoRiferimenti ;

    @ManyToMany(targetEntity=EpayTRegistroElaborazioni.class)
    @JoinTable(name="epay_r_pagamento_registro_elaborazioni",
    joinColumns=@JoinColumn(name="id_pagamento", referencedColumnName="id_pagamento"),
    inverseJoinColumns=@JoinColumn(name="id_registro_elaborazioni", referencedColumnName="id")
                    )

    private List<EpayTRegistroElaborazioni> listOfEpayTRegistroElaborazioni ;

    @ManyToOne
    @JoinColumn(name="id_tipo_pagamento", referencedColumnName="id_tipo_pagamento")
    private EpayTTipoPagamento epayTTipoPagamento ;

    @ManyToOne
    @JoinColumn(name="id_anagrafica_pagatore", referencedColumnName="id_anagrafica")
    private EpayTAnagrafica epayTAnagrafica ;

    @OneToMany(mappedBy="epayTPagamento", targetEntity=EpayTPagamentoComponenti.class)
    private List<EpayTPagamentoComponenti> listOfEpayTPagamentoComponenti ;

    //bi-directional many-to-one association to EpayTPagamentoSecondario
    @OneToMany ( mappedBy = "epayTPagamento" )
    private List<EpayTPagamentoSecondario> epayTPagamentoSecondarios;

    @Column ( name = "identificativo_dominio" )
    private String identificativoDominio;

    @Column ( name = "importo_principale", precision = 11, scale = 2 )
    private BigDecimal importoPrincipale;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTPagamento() {
        super();
    }

    @Override
    public Long getPrimaryKey() {
        return getIdPagamento();
    }

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento ;
    }
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
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

    //--- DATABASE MAPPING : numero_rata ( varchar )
    public void setNumeroRata( String numeroRata ) {
        this.numeroRata = numeroRata;
    }
    public String getNumeroRata() {
        return this.numeroRata;
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

    //--- DATABASE MAPPING : flag_pagamento_autenticato ( bool )
    public void setFlagPagamentoAutenticato( Boolean flagPagamentoAutenticato ) {
        this.flagPagamentoAutenticato = flagPagamentoAutenticato;
    }
    public Boolean getFlagPagamentoAutenticato() {
        return this.flagPagamentoAutenticato;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTRegistroVersamenti( List<EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti ) {
        this.listOfEpayTRegistroVersamenti = listOfEpayTRegistroVersamenti;
    }
    public List<EpayTRegistroVersamenti> getListOfEpayTRegistroVersamenti() {
        return this.listOfEpayTRegistroVersamenti;
    }

    public void setListOfEpayTDatiSingoliVersamenti( List<EpayTDatiSingoliVersamenti> listOfEpayTDatiSingoliVersamenti ) {
        this.listOfEpayTDatiSingoliVersamenti = listOfEpayTDatiSingoliVersamenti;
    }
    public List<EpayTDatiSingoliVersamenti> getListOfEpayTDatiSingoliVersamenti() {
        return this.listOfEpayTDatiSingoliVersamenti;
    }

    public void setEpayDStatoPagamento( EpayDStatoPagamento epayDStatoPagamento ) {
        this.epayDStatoPagamento = epayDStatoPagamento;
    }
    public EpayDStatoPagamento getEpayDStatoPagamento() {
        return this.epayDStatoPagamento;
    }

    public void setListOfEpayTPagamentoRiferimenti( List<EpayTPagamentoRiferimenti> listOfEpayTPagamentoRiferimenti ) {
        this.listOfEpayTPagamentoRiferimenti = listOfEpayTPagamentoRiferimenti;
    }
    public List<EpayTPagamentoRiferimenti> getListOfEpayTPagamentoRiferimenti() {
        return this.listOfEpayTPagamentoRiferimenti;
    }

    public void setListOfEpayTRegistroElaborazioni( List<EpayTRegistroElaborazioni> listOfEpayTRegistroElaborazioni ) {
        this.listOfEpayTRegistroElaborazioni = listOfEpayTRegistroElaborazioni;
    }
    public List<EpayTRegistroElaborazioni> getListOfEpayTRegistroElaborazioni() {
        return this.listOfEpayTRegistroElaborazioni;
    }

    public void setEpayTTipoPagamento( EpayTTipoPagamento epayTTipoPagamento ) {
        this.epayTTipoPagamento = epayTTipoPagamento;
    }
    public EpayTTipoPagamento getEpayTTipoPagamento() {
        return this.epayTTipoPagamento;
    }

    public void setEpayTAnagrafica( EpayTAnagrafica epayTAnagrafica ) {
        this.epayTAnagrafica = epayTAnagrafica;
    }
    public EpayTAnagrafica getEpayTAnagrafica() {
        return this.epayTAnagrafica;
    }

    public void setListOfEpayTPagamentoComponenti( List<EpayTPagamentoComponenti> listOfEpayTPagamentoComponenti ) {
        this.listOfEpayTPagamentoComponenti = listOfEpayTPagamentoComponenti;
    }
    public List<EpayTPagamentoComponenti> getListOfEpayTPagamentoComponenti() {
        return this.listOfEpayTPagamentoComponenti;
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
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(idPagamento);
        sb.append("]:");
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
