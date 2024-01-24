/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_storico_elaborazione database table.
 *
 */
@Entity
@Table(name="cbl_t_storico_elaborazione",schema="epaymodric")
@NamedQuery(name="CblTStoricoElaborazione.findAll", query="SELECT c FROM CblTStoricoElaborazione c")
public class CblTStoricoElaborazione extends DatiTecniciParentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    @Id
    @SequenceGenerator(name="CBL_T_STORICO_ELABORAZIONE_ID_GENERATOR", sequenceName="epaymodric.CBL_T_STORICO_ELABORAZIONE_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_STORICO_ELABORAZIONE_ID_GENERATOR")
    private Long id;

    @Column(name="data_elaborazione")
    private Timestamp dataElaborazione;

    @Column(name="data_fine")
    private Timestamp dataFine;

    @Column(name="data_inizio")
    private Timestamp dataInizio;

    @Column(name="id_ente")
    private String idEnte;

    @Column(name="lista_flussi")
    private String listaFlussi;

    @Column(name="msg_errore")
    private String msgErrore;

    @Column(name="stato_elaborazione")
    private String statoElaborazione;

    //bi-directional many-to-one association to CblDErrore
    @ManyToOne
    @JoinColumn(name="id_errore")
    private CblDErrore cblDErrore;

    //bi-directional many-to-one association to CblTElaborazione
    @ManyToOne
    @JoinColumn ( name = "id_elaborazione" )
    private CblTElaborazione cblTElaborazione;

    public CblTStoricoElaborazione() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDataElaborazione() {
        return dataElaborazione;
    }

    public void setDataElaborazione(Timestamp dataElaborazione) {
        this.dataElaborazione = dataElaborazione;
    }

    public Timestamp getDataFine() {
        return dataFine;
    }

    public void setDataFine(Timestamp dataFine) {
        this.dataFine = dataFine;
    }

    public Timestamp getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Timestamp dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getIdEnte() {
        return idEnte;
    }

    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }

    public String getListaFlussi() {
        return listaFlussi;
    }

    public void setListaFlussi(String listaFlussi) {
        this.listaFlussi = listaFlussi;
    }

    public String getMsgErrore() {
        return msgErrore;
    }

    public void setMsgErrore(String msgErrore) {
        this.msgErrore = msgErrore;
    }

    public String getStatoElaborazione() {
        return statoElaborazione;
    }

    public void setStatoElaborazione(String statoElaborazione) {
        this.statoElaborazione = statoElaborazione;
    }

    public CblDErrore getCblDErrore() {
        return cblDErrore;
    }

    public void setCblDErrore(CblDErrore cblDErrore) {
        this.cblDErrore = cblDErrore;
    }

    public CblTElaborazione getCblTElaborazione () {
        return cblTElaborazione;
    }

    public void setCblTElaborazione ( CblTElaborazione cblTElaborazione ) {
        this.cblTElaborazione = cblTElaborazione;
    }

}
