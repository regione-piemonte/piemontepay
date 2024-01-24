/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_v_dati_accertamento database table.
 *
 */
@Entity
@Table(name="cbl_v_dati_accertamento",schema="epaymodric")
@NamedQuery(name="CblVDatiAccertamento.findAll", query="SELECT c FROM CblVDatiAccertamento c")
public class CblVDatiAccertamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="accertamento_anno")
    private Integer accertamentoAnno;

    @Column(name="accertamento_nro")
    private Integer accertamentoNro;

    @Column ( name = "anno_esercizio" )
    private Integer annoEsercizio;

    @Column ( name = "codice_versamento" )
    private String codiceVersamento;

    @Column ( name = "data_fine_validita" )
    private Timestamp dataFineValidita;

    @Column ( name = "data_inizio_validita" )
    private Timestamp dataInizioValidita;

    @Column ( name = "dati_specifici_riscossione" )
    private String datiSpecificiRiscossione;

    @Id
    private Long id;

    @Column ( name = "id_ente" )
    private String idEnte;

    @Column(name="capitolo")
    private String capitolo;

    @Column(name="articolo")
    private Integer articolo;

    @Column(name="piano_dei_conti")
    private String pianoDeiConti;

    @Column(name="descrizione_codice_versamento")
    private String descrizioneCodiceVersamento;

    @Column ( name = "id_lista_di_carico" )
    private Long idListaDiCarico;

    private String pdc;

    private Integer priorita;

    public CblVDatiAccertamento() {
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

    public Integer getAnnoEsercizio () {
        return this.annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    public String getCodiceVersamento () {
        return this.codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public Timestamp getDataFineValidita () {
        return this.dataFineValidita;
    }

    public void setDataFineValidita ( Timestamp dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public Timestamp getDataInizioValidita () {
        return this.dataInizioValidita;
    }

    public void setDataInizioValidita ( Timestamp dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public String getDatiSpecificiRiscossione () {
        return this.datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdEnte () {
        return this.idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getPdc() {
        return this.pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
    }

    public Integer getPriorita() {
        return this.priorita;
    }

    public void setPriorita(Integer priorita) {
        this.priorita = priorita;
    }

    public String getCapitolo() {
        return capitolo;
    }

    public void setCapitolo(String capitolo) {
        this.capitolo = capitolo;
    }

    public Integer getArticolo() {
        return articolo;
    }

    public void setArticolo(Integer articolo) {
        this.articolo = articolo;
    }

    public String getPianoDeiConti() {
        return pianoDeiConti;
    }

    public void setPianoDeiConti(String pianoDeiConti) {
        this.pianoDeiConti = pianoDeiConti;
    }

    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento(String descrizioneCodiceVersamento) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public Long getIdListaDiCarico () {
        return idListaDiCarico;
    }

    public void setIdListaDiCarico ( Long idListaDiCarico ) {
        this.idListaDiCarico = idListaDiCarico;
    }

}
