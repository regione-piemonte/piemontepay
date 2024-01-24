/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_d_stato_elaborazione database table.
 *
 */
@Entity
@Table(name="cbl_d_stato_elaborazione",schema="epaymodric")
@NamedQuery(name="CblDStatoElaborazione.findAll", query="SELECT c FROM CblDStatoElaborazione c")
public class CblDStatoElaborazione implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CBL_D_STATO_ELABORAZIONE_ID_GENERATOR", sequenceName="epaymodric.CBL_D_STATO_ELABORAZIONE_ID_SEQ" ,schema="epaymodric", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_D_STATO_ELABORAZIONE_ID_GENERATOR")
    private Long id;

    @Column(name="codice_stato")
    private String codiceStato;

    @Column(name="descrizione_stato")
    private String descrizioneStato;

    //bi-directional many-to-one association to CblTElaborazione
    @OneToMany ( mappedBy = "cblDStatoElaborazione" )
    private List<CblTElaborazione> cblTElaboraziones;

    public CblDStatoElaborazione() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceStato() {
        return this.codiceStato;
    }

    public void setCodiceStato(String codiceStato) {
        this.codiceStato = codiceStato;
    }

    public String getDescrizioneStato() {
        return this.descrizioneStato;
    }

    public void setDescrizioneStato(String descrizioneStato) {
        this.descrizioneStato = descrizioneStato;
    }

    public List<CblTElaborazione> getCblTElaboraziones () {
        return this.cblTElaboraziones;
    }

    public void setCblTElaboraziones ( List<CblTElaborazione> cblTElaboraziones ) {
        this.cblTElaboraziones = cblTElaboraziones;
    }

}
