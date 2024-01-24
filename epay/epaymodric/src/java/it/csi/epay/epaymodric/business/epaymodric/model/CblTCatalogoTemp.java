/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the cbl_t_catalogo database table.
 *
 */
@Entity
@Table(name="cbl_t_catalogo_temp",schema="epaymodric")
@NamedQuery(name="CblTCatalogoTemp.findAll", query="SELECT c FROM CblTCatalogoTemp c")
@SuppressWarnings ( "unused" )
public class CblTCatalogoTemp extends DatiTecniciParentEntity implements Serializable, Comparable<CblTCatalogo> {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CBL_T_CATALOGO_TEMP_ID_GENERATOR", sequenceName="epaymodric.CBL_T_CATALOGO_TEMP_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_CATALOGO_TEMP_ID_GENERATOR")
    private Long id;

    @Column(name="accertamento_anno")
    private Integer accertamentoAnno;

    @Column(name="accertamento_desc")
    private String accertamentoDesc;

    @Column(name="accertamento_nro")
    private Integer accertamentoNro;

    @Column(name="anno_esercizio")
    private Integer annoEsercizio;

    private BigDecimal articolo;

    private String capitolo;

    private String categoria;

    @Column(name="codice_fiscale_ente")
    private String codiceFiscaleEnte;

    @Column(name="codice_versamento")
    private String codiceVersamento;

    @Column(name="data_fine_validita")
    private Timestamp dataFineValidita;

    @Column(name="data_inizio_validita")
    private Timestamp dataInizioValidita;

    @Column(name="dati_specifici_riscossione")
    private String datiSpecificiRiscossione;

    @Column(name="descrizione_versamento")
    private String descrizioneVersamento;

    @Column(name="id_ente")
    private String idEnte;

    @Column(name="piano_dei_conti")
    private String pianoDeiConti;

    private String sottovoce;

    private String tipologia;

    private String titolo;

    @Column ( name = "id_operazione" )
    private String idOperazione;

    @Column ( name = "azione" )
    private String azione;

    @Column ( name = "flag_annullato" )
    private Boolean flagAnnullato;

    @Column ( name = "chiave_intersistema" )
    private String chiaveIntersistema;

	@Column ( name = "codice_tributo" )
	private String codiceTributo;

    public CblTCatalogoTemp() {
    }

    @Override
    public int compareTo ( CblTCatalogo o ) {
        return this.chiaveIntersistema == null ? ( o.getChiaveIntersistema () == null ? 0 : -1 )
                        : ( o.getChiaveIntersistema () == null ? +1 : this.chiaveIntersistema.compareToIgnoreCase ( o.getChiaveIntersistema () ) );
    }

    public Integer getAccertamentoAnno() {
        return accertamentoAnno;
    }

    public String getAccertamentoDesc() {
        return accertamentoDesc;
    }

    public Integer getAccertamentoNro() {
        return accertamentoNro;
    }

    public Integer getAnnoEsercizio() {
        return annoEsercizio;
    }

    public BigDecimal getArticolo() {
        return articolo;
    }

    /**
     * @return the azione
     */
    public String getAzione () {
        return azione;
    }

    public String getCapitolo() {
        return capitolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    public Timestamp getDataFineValidita() {
        return dataFineValidita;
    }

    public Timestamp getDataInizioValidita() {
        return dataInizioValidita;
    }

    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    public String getDescrizioneVersamento() {
        return descrizioneVersamento;
    }

    public Boolean getFlagAnnullato () {
        return flagAnnullato;
    }

    public Long getId() {
        return id;
    }

    public String getIdEnte() {
        return idEnte;
    }

    /**
     * @return the idOperazione
     */
    public String getIdOperazione () {
        return idOperazione;
    }

    public String getPianoDeiConti() {
        return pianoDeiConti;
    }

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    public String getSottovoce() {
        return sottovoce;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setAccertamentoAnno(Integer accertamentoAnno) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public void setAccertamentoDesc(String accertamentoDesc) {
        this.accertamentoDesc = accertamentoDesc;
    }

    public void setAccertamentoNro(Integer accertamentoNro) {
        this.accertamentoNro = accertamentoNro;
    }

    public void setAnnoEsercizio(Integer annoEsercizio) {
        this.annoEsercizio = annoEsercizio;
    }

    public void setArticolo(BigDecimal articolo) {
        this.articolo = articolo;
    }

    /**
     * @param azione the azione to set
     */
    public void setAzione ( String azione ) {
        this.azione = azione;
    }

    public void setCapitolo(String capitolo) {
        this.capitolo = capitolo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public void setCodiceVersamento(String codiceVersamento) {
        this.codiceVersamento = codiceVersamento;
    }

    public void setDataFineValidita(Timestamp dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

    public void setDataInizioValidita(Timestamp dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public void setDescrizioneVersamento(String descrizioneVersamento) {
        this.descrizioneVersamento = descrizioneVersamento;
    }

    public void setFlagAnnullato ( Boolean flagAnnullato ) {
        this.flagAnnullato = flagAnnullato;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }

    /**
     * @param idOperazione the idOperazione to set
     */
    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    public void setPianoDeiConti(String pianoDeiConti) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public void setSottovoce(String sottovoce) {
        this.sottovoce = sottovoce;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}
}
