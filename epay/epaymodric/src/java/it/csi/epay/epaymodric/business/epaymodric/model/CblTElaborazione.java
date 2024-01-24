/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_elaborazione database table.
 *
 */
@Entity
@Table(name = "cbl_t_elaborazione", schema="epaymodric")

@NamedQuery(name = "CblTElaborazione.findAll", query = "SELECT c FROM CblTElaborazione c")
public class CblTElaborazione extends DatiTecniciParentEntity implements Serializable {

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CBL_T_ELABORAZIONE_ID_GENERATOR", sequenceName="epaymodric.CBL_T_ELABORAZIONE_ID_SEQ", schema="epaymodric", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBL_T_ELABORAZIONE_ID_GENERATOR")
    private Long id;

    @Column(name = "data_elaborazione")
    private Timestamp dataElaborazione;

    @Column(name = "data_fine")
    private java.sql.Timestamp dataFine;

    @Column(name = "data_inizio")
    private java.sql.Timestamp dataInizio;

    @Column(name = "lista_flussi")
    private String listaFlussi;

    @Column(name = "msg_errore")
    private String msgErrore;

    // bi-directional many-to-one association to CblTFlussoOrigine
    @OneToMany(mappedBy = "cblTElaborazione")
    private List<CblTFlussoOrigine> cblTFlussoOrigines;

    // bi-directional many-to-one association to CblDErrore
    @ManyToOne
    @JoinColumn(name = "id_errore")
    private CblDErrore cblDErrore;

    //bi-directional many-to-one association to CblTStoricoElaborazione
    @OneToMany ( mappedBy = "cblTElaborazione" )
    private List<CblTStoricoElaborazione> cblTStoricoElaboraziones;

    //bi-directional many-to-one association to CblDStatoElaborazione
    @ManyToOne
    @JoinColumn ( name = "stato_elaborazione", referencedColumnName = "codice_stato" )
    private CblDStatoElaborazione cblDStatoElaborazione;

    //bi-directional many-to-one association to CblTEnte
    @ManyToOne
    @JoinColumn ( name = "id_ente", referencedColumnName = "id_ente" )
    private CblTEnte cblTEnte;

    public CblTElaborazione() {
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

    public List<CblTFlussoOrigine> getCblTFlussoOrigines() {
        return cblTFlussoOrigines;
    }

    public void setCblTFlussoOrigines(List<CblTFlussoOrigine> cblTFlussoOrigines) {
        this.cblTFlussoOrigines = cblTFlussoOrigines;
    }

    public CblTFlussoOrigine addCblTFlussoOrigine(CblTFlussoOrigine cblTFlussoOrigine) {
        getCblTFlussoOrigines().add(cblTFlussoOrigine);
        cblTFlussoOrigine.setCblTElaborazione(this);

        return cblTFlussoOrigine;
    }

    public CblTFlussoOrigine removeCblTFlussoOrigine(CblTFlussoOrigine cblTFlussoOrigine) {
        getCblTFlussoOrigines().remove(cblTFlussoOrigine);
        cblTFlussoOrigine.setCblTElaborazione(null);

        return cblTFlussoOrigine;
    }

    public CblDErrore getCblDErrore() {
        return cblDErrore;
    }

    public void setCblDErrore(CblDErrore cblDErrore) {
        this.cblDErrore = cblDErrore;
    }

    public List<CblTStoricoElaborazione> getCblTStoricoElaboraziones () {
        return cblTStoricoElaboraziones;
    }

    public void setCblTStoricoElaboraziones ( List<CblTStoricoElaborazione> cblTStoricoElaboraziones ) {
        this.cblTStoricoElaboraziones = cblTStoricoElaboraziones;
    }

    public CblTStoricoElaborazione addCblTStoricoElaborazione ( CblTStoricoElaborazione cblTStoricoElaborazione ) {
        getCblTStoricoElaboraziones ().add ( cblTStoricoElaborazione );
        cblTStoricoElaborazione.setCblTElaborazione ( this );

        return cblTStoricoElaborazione;
    }

    public CblTStoricoElaborazione removeCblTStoricoElaborazione ( CblTStoricoElaborazione cblTStoricoElaborazione ) {
        getCblTStoricoElaboraziones ().remove ( cblTStoricoElaborazione );
        cblTStoricoElaborazione.setCblTElaborazione ( null );

        return cblTStoricoElaborazione;
    }

    public CblDStatoElaborazione getCblDStatoElaborazione () {
        return this.cblDStatoElaborazione;
    }

    public void setCblDStatoElaborazione ( CblDStatoElaborazione cblDStatoElaborazione ) {
        this.cblDStatoElaborazione = cblDStatoElaborazione;
    }

    public CblTEnte getCblTEnte () {
        return this.cblTEnte;
    }

    public void setCblTEnte ( CblTEnte cblTEnte ) {
        this.cblTEnte = cblTEnte;
    }


}
