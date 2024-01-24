/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_lista_di_carico database table.
 *
 */
@Entity
@Table(name="cbl_t_lista_di_carico",schema="epaymodric")
@NamedQuery(name="CblTListaDiCarico.findAll", query="SELECT c FROM CblTListaDiCarico c")
public class CblTListaDiCarico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CBL_T_LISTA_DI_CARICO_ID_GENERATOR", sequenceName="epaymodric.CBL_T_LISTA_DI_CARICO_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_LISTA_DI_CARICO_ID_GENERATOR")
    private Long id;

    @Column(name="accertamento_anno")
    private Integer accertamentoAnno;

    @Column(name="accertamento_nro")
    private Integer accertamentoNro;

    @Column(name="anno_esercizio")
    private Integer annoEsercizio;

    private BigDecimal articolo;

    private String capitolo;

    @Column(name="codice_versamento")
    private String codiceVersamento;

    @Column(name="data_fine_validita")
    private Timestamp dataFineValidita;

    @Column(name="data_inizio_validita")
    private Timestamp dataInizioValidita;

    @Column(name="dati_specifici_riscossione")
    private String datiSpecificiRiscossione;

    @Column(name="descrizione_causale")
    private String descrizioneCausale;

    @Column(name="id_ente")
    private String idEnte;

    @Column(name="importo_versamento")
    private BigDecimal importoVersamento;

    @Column(name="piano_dei_conti")
    private String pianoDeiConti;

    @Column(name="rif_posizione_debitoria")
    private Integer rifPosizioneDebitoria;

    private Boolean attivo;

    public CblTListaDiCarico() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccertamentoAnno() {
        return this.accertamentoAnno;
    }

    public void setAccertamentoAnno(Integer accertamentoAnno) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public Integer getAccertamentoNro() {
        return this.accertamentoNro;
    }

    public void setAccertamentoNro(Integer accertamentoNro) {
        this.accertamentoNro = accertamentoNro;
    }

    public Integer getAnnoEsercizio() {
        return this.annoEsercizio;
    }

    public void setAnnoEsercizio(Integer annoEsercizio) {
        this.annoEsercizio = annoEsercizio;
    }

    public BigDecimal getArticolo() {
        return this.articolo;
    }

    public void setArticolo(BigDecimal articolo) {
        this.articolo = articolo;
    }

    public String getCapitolo() {
        return this.capitolo;
    }

    public void setCapitolo(String capitolo) {
        this.capitolo = capitolo;
    }

    public String getCodiceVersamento() {
        return this.codiceVersamento;
    }

    public void setCodiceVersamento(String codiceVersamento) {
        this.codiceVersamento = codiceVersamento;
    }

    public Timestamp getDataFineValidita() {
        return this.dataFineValidita;
    }

    public void setDataFineValidita(Timestamp dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

    public Timestamp getDataInizioValidita() {
        return this.dataInizioValidita;
    }

    public void setDataInizioValidita(Timestamp dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDatiSpecificiRiscossione() {
        return this.datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public String getDescrizioneCausale() {
        return this.descrizioneCausale;
    }

    public void setDescrizioneCausale(String descrizioneCausale) {
        this.descrizioneCausale = descrizioneCausale;
    }

    public String getIdEnte() {
        return this.idEnte;
    }

    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }

    public BigDecimal getImportoVersamento() {
        return this.importoVersamento;
    }

    public void setImportoVersamento(BigDecimal importoVersamento) {
        this.importoVersamento = importoVersamento;
    }

    public String getPianoDeiConti() {
        return this.pianoDeiConti;
    }

    public void setPianoDeiConti(String pianoDeiConti) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public Integer getRifPosizioneDebitoria() {
        return this.rifPosizioneDebitoria;
    }

    public void setRifPosizioneDebitoria(Integer rifPosizioneDebitoria) {
        this.rifPosizioneDebitoria = rifPosizioneDebitoria;
    }

    public Boolean getAttivo () {
        return attivo;
    }

    public void setAttivo ( Boolean attivo ) {
        this.attivo = attivo;
    }

}
