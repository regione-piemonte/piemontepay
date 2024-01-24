/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypacat_t_ente database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_storico_ente" )
@NamedQuery ( name = "StoricoEnte.findAll", query = "SELECT e FROM StoricoEnte e" )
public class StoricoEnte extends AbstractChangeTrackedParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column ( name = "id_ente" )
    private Long idEnte;

    private String cap;

    @Column ( name = "codice_fiscale" )
    private String codiceFiscale;

    private String denominazione;

    @Column ( name = "flag_accertamento" )
    private Boolean flagAccertamento;

    @Column ( name = "flag_ente_plurintermediato" )
    private Boolean flagEntePlurintermediato;

    @Column ( name = "flag_ricezione_errori" )
    private Boolean flagRicezioneErrori;

    @Column ( name = "flag_riconciliazione_versamenti" )
    private Boolean flagRiconciliazioneVersamenti;

    @Column ( name = "flag_ricezione_flusso_base_rendicontazione" )
    private Boolean flagRicezioneFlussoBaseRendicontazione;

    @Column ( name = "flag_qualsiasi_codice_versamento" )
    private Boolean flagQualsiasiCodiceVersamento;

    private String indirizzo;

    private String localita;

    @Column ( name = "logo_content" )
    private byte [] logoContent;

    @Column ( name = "logo_content_type" )
    private String logoContentType;

    @Column ( name = "logo_file_name" )
    private String logoFileName;

    @Column ( name = "logo_file_size" )
    private Integer logoFileSize;

    @Column ( name = "sigla_provincia" )
    private String siglaProvincia;

    @Column ( name = "giorno_schedulazione" )
    private Integer giornoSchedulazione;

    @Column ( name = "descrizione_errore_aggiornamento" )
    private String descrizioneErroreAggiornamento;

    private String civico;

    private String email;

    @Column ( name = "gs1_gln" )
    private String gs1Gln;

    private String cbill;

    private String iban;
    

    @Column ( name = "template_email_id", length = 50 )
    private String templateEmailId;
    
    @Column ( name = "url_dominio", length = 50 )
    private String urlDominio;
    
    
    @Column ( name = "codice_ipa", length = 10 )
    private String codiceIpa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_utente_amministratore", referencedColumnName = "id" )
    private Utente utenteAmministratore;

    //uni-directional many-to-one association to EpaypacatDModalitaAcquisizioneProvvisori
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_modalita_acquisizione_provvisori", referencedColumnName = "codice" )
    private ModalitaAcquisizioneProvvisori modalitaAcquisizioneProvvisori;

    //uni-directional many-to-one association to EpaypacatDPeriodicitaSchedulazioneRiconciliazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_periodicita_schedulazione_riconciliazione", referencedColumnName = "codice" )
    private PeriodicitaSchedulazioneRiconciliazione periodicitaSchedulazioneRiconciliazione;

    //uni-directional many-to-one association to EpaypacatDStatoAttivita
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_stato_aggiornamento_ente", referencedColumnName = "codice" )
    private StatoAggiornamentoEnte statoAggiornamentoEnte;

    //uni-directional many-to-one association to EpaypacatDTipologiaAccertamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_tipologia_accertamento", referencedColumnName = "codice" )
    private TipologiaAccertamento tipologiaAccertamento;

    public StoricoEnte () {
    }

    public String getCivico () {
        return civico;
    }

    public void setCivico ( String civico ) {
        this.civico = civico;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getGs1Gln () {
        return gs1Gln;
    }

    public void setGs1Gln ( String gs1Gln ) {
        this.gs1Gln = gs1Gln;
    }

    public String getCbill () {
        return cbill;
    }

    public void setCbill ( String cbill ) {
        this.cbill = cbill;
    }

    public String getIban () {
        return iban;
    }

    public void setIban ( String iban ) {
        this.iban = iban;
    }

    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    public Long getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( Long idEnte ) {
        this.idEnte = idEnte;
    }

    public Utente getUtenteAmministratore () {
        return utenteAmministratore;
    }

    public void setUtenteAmministratore ( Utente utenteAmministratore ) {
        this.utenteAmministratore = utenteAmministratore;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCap () {
        return cap;
    }

    public void setCap ( String cap ) {
        this.cap = cap;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione () {
        return denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public String getIndirizzo () {
        return indirizzo;
    }

    public void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    public String getLocalita () {
        return localita;
    }

    public void setLocalita ( String localita ) {
        this.localita = localita;
    }

    public byte [] getLogoContent () {
        return logoContent;
    }

    public void setLogoContent ( byte [] logoContent ) {
        this.logoContent = logoContent;
    }

    public String getLogoContentType () {
        return logoContentType;
    }

    public void setLogoContentType ( String logoContentType ) {
        this.logoContentType = logoContentType;
    }

    public String getLogoFileName () {
        return logoFileName;
    }

    public void setLogoFileName ( String logoFileName ) {
        this.logoFileName = logoFileName;
    }

    public Integer getLogoFileSize () {
        return logoFileSize;
    }

    public void setLogoFileSize ( Integer logoFileSize ) {
        this.logoFileSize = logoFileSize;
    }

    public String getSiglaProvincia () {
        return siglaProvincia;
    }

    public void setSiglaProvincia ( String siglaProvincia ) {
        this.siglaProvincia = siglaProvincia;
    }

    public ModalitaAcquisizioneProvvisori getModalitaAcquisizioneProvvisori () {
        return modalitaAcquisizioneProvvisori;
    }

    public void setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisori modalitaAcquisizioneProvvisori ) {
        this.modalitaAcquisizioneProvvisori = modalitaAcquisizioneProvvisori;
    }

    public PeriodicitaSchedulazioneRiconciliazione getPeriodicitaSchedulazioneRiconciliazione () {
        return periodicitaSchedulazioneRiconciliazione;
    }

    public void setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneRiconciliazione periodicitaSchedulazioneRiconciliazione ) {
        this.periodicitaSchedulazioneRiconciliazione = periodicitaSchedulazioneRiconciliazione;
    }

    public StatoAggiornamentoEnte getStatoAggiornamentoEnte () {
        return statoAggiornamentoEnte;
    }

    public void setStatoAggiornamentoEnte ( StatoAggiornamentoEnte statoAggiornamentoEnte ) {
        this.statoAggiornamentoEnte = statoAggiornamentoEnte;
    }

    public TipologiaAccertamento getTipologiaAccertamento () {
        return tipologiaAccertamento;
    }

    public void setTipologiaAccertamento ( TipologiaAccertamento tipologiaAccertamento ) {
        this.tipologiaAccertamento = tipologiaAccertamento;
    }

    public Boolean getFlagAccertamento () {
        return flagAccertamento;
    }

    public void setFlagAccertamento ( Boolean flagAccertamento ) {
        this.flagAccertamento = flagAccertamento;
    }

    public Boolean getFlagEntePlurintermediato () {
        return flagEntePlurintermediato;
    }

    public void setFlagEntePlurintermediato ( Boolean flagEntePlurintermediato ) {
        this.flagEntePlurintermediato = flagEntePlurintermediato;
    }

    public Boolean getFlagRicezioneErrori () {
        return flagRicezioneErrori;
    }

    public void setFlagRicezioneErrori ( Boolean flagRicezioneErrori ) {
        this.flagRicezioneErrori = flagRicezioneErrori;
    }

    public Boolean getFlagRiconciliazioneVersamenti () {
        return flagRiconciliazioneVersamenti;
    }

    public void setFlagRiconciliazioneVersamenti ( Boolean flagRiconciliazioneVersamenti ) {
        this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
    }

    public Boolean getFlagRicezioneFlussoBaseRendicontazione () {
        return flagRicezioneFlussoBaseRendicontazione;
    }

    public void setFlagRicezioneFlussoBaseRendicontazione ( Boolean flagRicezioneFlussoBaseRendicontazione ) {
        this.flagRicezioneFlussoBaseRendicontazione = flagRicezioneFlussoBaseRendicontazione;
    }

    public Boolean getFlagQualsiasiCodiceVersamento () {
        return flagQualsiasiCodiceVersamento;
    }

    public void setFlagQualsiasiCodiceVersamento ( Boolean flagQualsiasiCodiceVersamento ) {
        this.flagQualsiasiCodiceVersamento = flagQualsiasiCodiceVersamento;
    }

    public Integer getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    public void setGiornoSchedulazione ( Integer giornoSchedulazione ) {
        this.giornoSchedulazione = giornoSchedulazione;
    }

	@Override
	public String toString() {
		return "StoricoEnte [id=" + id + "]";
	}

    
    /**
     * @return the templateEmailId
     */
    public String getTemplateEmailId () {
        return templateEmailId;
    }

    
    /**
     * @param templateEmailId the templateEmailId to set
     */
    public void setTemplateEmailId ( String templateEmailId ) {
        this.templateEmailId = templateEmailId;
    }

    
    /**
     * @return the urlDominio
     */
    public String getUrlDominio () {
        return urlDominio;
    }

    
    /**
     * @param urlDominio the urlDominio to set
     */
    public void setUrlDominio ( String urlDominio ) {
        this.urlDominio = urlDominio;
    }

    
    /**
     * @return the codiceIpa
     */
    public String getCodiceIpa () {
        return codiceIpa;
    }

    
    /**
     * @param codiceIpa the codiceIpa to set
     */
    public void setCodiceIpa ( String codiceIpa ) {
        this.codiceIpa = codiceIpa;
    }
	
	

}
