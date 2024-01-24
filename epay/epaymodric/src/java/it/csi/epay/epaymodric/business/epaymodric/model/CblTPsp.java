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
 * The persistent class for the cbl_t_psp database table.
 * 
 */
@Entity
@Table ( name = "cbl_t_psp", schema = "epaymodric" )
@NamedQuery ( name = "CblTPsp.findAll", query = "SELECT c FROM CblTPsp c" )
public class CblTPsp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "CBL_T_PSP_ID_GENERATOR", sequenceName = "epaymodric.CBL_T_PSP_ID_SEQ", schema = "epaymodric", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CBL_T_PSP_ID_GENERATOR" )
    private Long id;

    @Column ( name = "denominazione_psp" )
    private String denominazionePsp;

    @Column ( name = "flag_riconciliabile" )
    private Boolean flagRiconciliabile;

    //bi-directional many-to-one association to CblTFlussoOrigine
    @OneToMany ( mappedBy = "cblTPsp" )
    private List<CblTFlussoOrigine> cblTFlussoOrigines;

    @Column ( name = "identificativo_psp" )
    private String identificativoPsp;

    public CblTPsp () {
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getDenominazionePsp () {
        return this.denominazionePsp;
    }

    public void setDenominazionePsp ( String denominazionePsp ) {
        this.denominazionePsp = denominazionePsp;
    }

    public Boolean getFlagRiconciliabile () {
        return this.flagRiconciliabile;
    }

    public void setFlagRiconciliabile ( Boolean flagRiconciliabile ) {
        this.flagRiconciliabile = flagRiconciliabile;
    }

    public List<CblTFlussoOrigine> getCblTFlussoOrigines () {
        return this.cblTFlussoOrigines;
    }

    public void setCblTFlussoOrigines ( List<CblTFlussoOrigine> cblTFlussoOrigines ) {
        this.cblTFlussoOrigines = cblTFlussoOrigines;
    }

    public CblTFlussoOrigine addCblTFlussoOrigine ( CblTFlussoOrigine cblTFlussoOrigine ) {
        getCblTFlussoOrigines ().add ( cblTFlussoOrigine );
        cblTFlussoOrigine.setCblTPsp ( this );

        return cblTFlussoOrigine;
    }

    public CblTFlussoOrigine removeCblTFlussoOrigine ( CblTFlussoOrigine cblTFlussoOrigine ) {
        getCblTFlussoOrigines ().remove ( cblTFlussoOrigine );
        cblTFlussoOrigine.setCblTPsp ( null );

        return cblTFlussoOrigine;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

}
