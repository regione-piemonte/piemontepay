/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the epaypacat_t_ente database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_ente" )
@NamedQuery ( name = "Ente.findAll", query = "SELECT e FROM Ente e" )
public class Ente extends AbstractChangeTrackedParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    public String getDescrizioneEsitoAggiornamento () {

        if ( descrizioneErroreAggiornamento != null && !descrizioneErroreAggiornamento.trim ().isEmpty () ) {
            return getStatoAggiornamentoEnte ().getDescrizione () + " - " + getDescrizioneErroreAggiornamento ();
        } else {
            return getStatoAggiornamentoEnte ().getDescrizione ();
        }

    }

    @Id
    private Long id;

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

	@Column ( name = "flag_adesione_cittafacile" )
	private Boolean flagAdesioneCittaFacile;

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

    private String bic;

    @Column ( name = "flag_gestione_ppay" )
    private boolean flagGestionePpay;

    @Column ( name = "codice_tipo_ente_creditore"  )
    private String codiceTipoEnteCreditore;

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

    //bi-directional many-to-one association to EpaycatRUtenteEnte
    @OneToMany ( mappedBy = "ente", fetch = FetchType.LAZY )
    private List<AssociazioneUtenteEnte> utenteEnteList;

    @ManyToMany
    @JoinTable (
        name = "epaycat_r_ente_codice_versamento",
        joinColumns = { @JoinColumn ( name = "id_ente", referencedColumnName = "id" ) },
        inverseJoinColumns = { @JoinColumn ( name = "id_codice_versamento", referencedColumnName = "id" ) } )
    Set<CodiceVersamento> codiciVersamento = new HashSet<> ();

	@Column ( name = "codice_istat"  )
	private String codiceIstat;


    @Column ( name = "template_email_id", length = 50 )
    private String templateEmailId;
    
    @Column ( name = "url_dominio", length = 50 )
    private String urlDominio;
    
    
    @Column ( name = "codice_ipa", length = 10 )
    private String codiceIpa;
    

    public Ente () {
    }

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the cap
     */
    public String getCap () {
        return cap;
    }

    /**
     * @param cap the cap to set
     */
    public void setCap ( String cap ) {
        this.cap = cap;
    }

    /**
     * @return the codiceFiscale
     */
    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    /**
     * @param codiceFiscale the codiceFiscale to set
     */
    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * @return the denominazione
     */
    public String getDenominazione () {
        return denominazione;
    }

    /**
     * @param denominazione the denominazione to set
     */
    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    /**
     * @return the flagAccertamento
     */
    public Boolean getFlagAccertamento () {
        return flagAccertamento;
    }

    /**
     * @param flagAccertamento the flagAccertamento to set
     */
    public void setFlagAccertamento ( Boolean flagAccertamento ) {
        this.flagAccertamento = flagAccertamento;
    }

    /**
     * @return the flagEntePlurintermediato
     */
    public Boolean getFlagEntePlurintermediato () {
        return flagEntePlurintermediato;
    }

    /**
     * @param flagEntePlurintermediato the flagEntePlurintermediato to set
     */
    public void setFlagEntePlurintermediato ( Boolean flagEntePlurintermediato ) {
        this.flagEntePlurintermediato = flagEntePlurintermediato;
    }

    /**
     * @return the flagRicezioneErrori
     */
    public Boolean getFlagRicezioneErrori () {
        return flagRicezioneErrori;
    }

    /**
     * @param flagRicezioneErrori the flagRicezioneErrori to set
     */
    public void setFlagRicezioneErrori ( Boolean flagRicezioneErrori ) {
        this.flagRicezioneErrori = flagRicezioneErrori;
    }

    /**
     * @return the flagRiconciliazioneVersamenti
     */
    public Boolean getFlagRiconciliazioneVersamenti () {
        return flagRiconciliazioneVersamenti;
    }

    /**
     * @param flagRiconciliazioneVersamenti the flagRiconciliazioneVersamenti to set
     */
    public void setFlagRiconciliazioneVersamenti ( Boolean flagRiconciliazioneVersamenti ) {
        this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
    }

    /**
     * @return the flagRicezioneFlussoBaseRendicontazione
     */
    public Boolean getFlagRicezioneFlussoBaseRendicontazione () {
        return flagRicezioneFlussoBaseRendicontazione;
    }

    /**
     * @param flagRicezioneFlussoBaseRendicontazione the flagRicezioneFlussoBaseRendicontazione to set
     */
    public void setFlagRicezioneFlussoBaseRendicontazione ( Boolean flagRicezioneFlussoBaseRendicontazione ) {
        this.flagRicezioneFlussoBaseRendicontazione = flagRicezioneFlussoBaseRendicontazione;
    }

    /**
     * @return the flagQualsiasiCodiceVersamento
     */
    public Boolean getFlagQualsiasiCodiceVersamento () {
        return flagQualsiasiCodiceVersamento;
    }

    /**
     * @param flagQualsiasiCodiceVersamento the flagQualsiasiCodiceVersamento to set
     */
    public void setFlagQualsiasiCodiceVersamento ( Boolean flagQualsiasiCodiceVersamento ) {
        this.flagQualsiasiCodiceVersamento = flagQualsiasiCodiceVersamento;
    }

	public Boolean getFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}

	public void setFlagAdesioneCittaFacile ( Boolean flag ) {
		this.flagAdesioneCittaFacile = flag;
	}

    /**
     * @return the indirizzo
     */
    public String getIndirizzo () {
        return indirizzo;
    }

    /**
     * @param indirizzo the indirizzo to set
     */
    public void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    /**
     * @return the localita
     */
    public String getLocalita () {
        return localita;
    }

    /**
     * @param localita the localita to set
     */
    public void setLocalita ( String localita ) {
        this.localita = localita;
    }

    /**
     * @return the logoContent
     */
    public byte [] getLogoContent () {
        return logoContent;
    }

    /**
     * @param logoContent the logoContent to set
     */
    public void setLogoContent ( byte [] logoContent ) {
        this.logoContent = logoContent;
    }

    /**
     * @return the logoContentType
     */
    public String getLogoContentType () {
        return logoContentType;
    }

    /**
     * @param logoContentType the logoContentType to set
     */
    public void setLogoContentType ( String logoContentType ) {
        this.logoContentType = logoContentType;
    }

    /**
     * @return the logoFileName
     */
    public String getLogoFileName () {
        return logoFileName;
    }

    /**
     * @param logoFileName the logoFileName to set
     */
    public void setLogoFileName ( String logoFileName ) {
        this.logoFileName = logoFileName;
    }

    /**
     * @return the logoFileSize
     */
    public Integer getLogoFileSize () {
        return logoFileSize;
    }

    /**
     * @param logoFileSize the logoFileSize to set
     */
    public void setLogoFileSize ( Integer logoFileSize ) {
        this.logoFileSize = logoFileSize;
    }

    /**
     * @return the siglaProvincia
     */
    public String getSiglaProvincia () {
        return siglaProvincia;
    }

    /**
     * @param siglaProvincia the siglaProvincia to set
     */
    public void setSiglaProvincia ( String siglaProvincia ) {
        this.siglaProvincia = siglaProvincia;
    }

    /**
     * @return the giornoSchedulazione
     */
    public Integer getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    /**
     * @param giornoSchedulazione the giornoSchedulazione to set
     */
    public void setGiornoSchedulazione ( Integer giornoSchedulazione ) {
        this.giornoSchedulazione = giornoSchedulazione;
    }

    /**
     * @return the descrizioneErroreAggiornamento
     */
    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    /**
     * @param descrizioneErroreAggiornamento the descrizioneErroreAggiornamento to set
     */
    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    /**
     * @return the civico
     */
    public String getCivico () {
        return civico;
    }

    /**
     * @param civico the civico to set
     */
    public void setCivico ( String civico ) {
        this.civico = civico;
    }

    /**
     * @return the email
     */
    public String getEmail () {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail ( String email ) {
        this.email = email;
    }

    /**
     * @return the gs1Gln
     */
    public String getGs1Gln () {
        return gs1Gln;
    }

    /**
     * @param gs1Gln the gs1Gln to set
     */
    public void setGs1Gln ( String gs1Gln ) {
        this.gs1Gln = gs1Gln;
    }

    /**
     * @return the cbill
     */
    public String getCbill () {
        return cbill;
    }

    /**
     * @param cbill the cbill to set
     */
    public void setCbill ( String cbill ) {
        this.cbill = cbill;
    }

    /**
     * @return the iban
     */
    public String getIban () {
        return iban;
    }

    /**
     * @param iban the iban to set
     */
    public void setIban ( String iban ) {
        this.iban = iban;
    }

    /**
     * @return the bic
     */
    public String getBic () {
        return bic;
    }

    /**
     * @param bic the bic to set
     */
    public void setBic ( String bic ) {
        this.bic = bic;
    }

    /**
     * @return the utenteAmministratore
     */
    public Utente getUtenteAmministratore () {
        return utenteAmministratore;
    }

    /**
     * @param utenteAmministratore the utenteAmministratore to set
     */
    public void setUtenteAmministratore ( Utente utenteAmministratore ) {
        this.utenteAmministratore = utenteAmministratore;
    }

    /**
     * @return the modalitaAcquisizioneProvvisori
     */
    public ModalitaAcquisizioneProvvisori getModalitaAcquisizioneProvvisori () {
        return modalitaAcquisizioneProvvisori;
    }

    /**
     * @param modalitaAcquisizioneProvvisori the modalitaAcquisizioneProvvisori to set
     */
    public void setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisori modalitaAcquisizioneProvvisori ) {
        this.modalitaAcquisizioneProvvisori = modalitaAcquisizioneProvvisori;
    }

    /**
     * @return the periodicitaSchedulazioneRiconciliazione
     */
    public PeriodicitaSchedulazioneRiconciliazione getPeriodicitaSchedulazioneRiconciliazione () {
        return periodicitaSchedulazioneRiconciliazione;
    }

    /**
     * @param periodicitaSchedulazioneRiconciliazione the periodicitaSchedulazioneRiconciliazione to set
     */
    public void setPeriodicitaSchedulazioneRiconciliazione (
        PeriodicitaSchedulazioneRiconciliazione periodicitaSchedulazioneRiconciliazione ) {
        this.periodicitaSchedulazioneRiconciliazione = periodicitaSchedulazioneRiconciliazione;
    }

    /**
     * @return the statoAggiornamentoEnte
     */
    public StatoAggiornamentoEnte getStatoAggiornamentoEnte () {
        return statoAggiornamentoEnte;
    }

    /**
     * @param statoAggiornamentoEnte the statoAggiornamentoEnte to set
     */
    public void setStatoAggiornamentoEnte ( StatoAggiornamentoEnte statoAggiornamentoEnte ) {
        this.statoAggiornamentoEnte = statoAggiornamentoEnte;
    }

    /**
     * @return the tipologiaAccertamento
     */
    public TipologiaAccertamento getTipologiaAccertamento () {
        return tipologiaAccertamento;
    }

    /**
     * @param tipologiaAccertamento the tipologiaAccertamento to set
     */
    public void setTipologiaAccertamento ( TipologiaAccertamento tipologiaAccertamento ) {
        this.tipologiaAccertamento = tipologiaAccertamento;
    }

    /**
     * @return the codiciVersamento
     */
    public Set<CodiceVersamento> getCodiciVersamento () {
        return codiciVersamento;
    }

    /**
     * @param codiciVersamento the codiciVersamento to set
     */
    public void setCodiciVersamento ( Set<CodiceVersamento> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }


    /**
     * @return the codiceTipoEnteCreditore
     */
    public String getCodiceTipoEnteCreditore() {
        return codiceTipoEnteCreditore;
    }

    /**
     * @param codiceTipoEnteCreditore the codiceTipoEnteCreditore to set
     */
    public void setCodiceTipoEnteCreditore(String codiceTipoEnteCreditore) {
        this.codiceTipoEnteCreditore = codiceTipoEnteCreditore;
    }

    @Override
    public String toString() {
        return "Ente [id=" + id + ", cap=" + cap + ", codiceFiscale=" + codiceFiscale + ", denominazione="
                        + denominazione + "]";
    }

    public List<AssociazioneUtenteEnte> getUtenteEnteList () {
        return utenteEnteList;
    }

    public void setUtenteEnteList ( List<AssociazioneUtenteEnte> utenteEnteList ) {
        this.utenteEnteList = utenteEnteList;
    }

    public boolean isFlagGestionePpay () {
        return flagGestionePpay;
    }

    public void setFlagGestionePpay ( boolean flagGestionePpay ) {
        this.flagGestionePpay = flagGestionePpay;
    }

	public String getCodiceIstat () {
		return codiceIstat;
	}

	public void setCodiceIstat ( String codiceIstat ) {
		this.codiceIstat = codiceIstat;
	}

    
    public String getTemplateEmailId () {
        return templateEmailId;
    }

    
    public void setTemplateEmailId ( String templateEmailId ) {
        this.templateEmailId = templateEmailId;
    }

    
    public String getUrlDominio () {
        return urlDominio;
    }

    
    public void setUrlDominio ( String urlDominio ) {
        this.urlDominio = urlDominio;
    }

    
    public String getCodiceIpa () {
        return codiceIpa;
    }

    
    public void setCodiceIpa ( String codiceIpa ) {
        this.codiceIpa = codiceIpa;
    }
	
    
    
	
}
