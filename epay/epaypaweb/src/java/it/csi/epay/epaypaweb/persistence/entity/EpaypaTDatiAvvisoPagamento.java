/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_dati_avviso_pagamento database table.
 *
 */
@Entity
@Table ( name = "epaypa_t_dati_avviso_pagamento" )
@NamedQueries ( {
    @NamedQuery ( name = "EpaypaTDatiAvvisoPagamento.findAll",
                    query = "SELECT e FROM EpaypaTDatiAvvisoPagamento e" ),
    @NamedQuery (
                    name = "EpaypaTDatiAvvisoPagamento.findDatiAvvisoPagamentoByCodiceVersamentoAndEnte",
                    query = "SELECT dap "
                        + "FROM EpaypaTDatiAvvisoPagamento dap "
                        + "WHERE dap.epaypaTCodiceVersamento.codVersamento = :codiceVersamento "
                        + "AND dap.epaypaTCodiceVersamento.epaypaTEnte.codFiscaleEnte = :codFiscaleEnte")
} )

public class EpaypaTDatiAvvisoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "id_dati_avviso_pagamento" )
    private Integer idDatiAvvisoPagamento;

    @ManyToOne
    @JoinColumn ( name = "id_codice_versamento" )
    private EpaypaTCodiceVersamento epaypaTCodiceVersamento;
    
    private String settore;

    private String indirizzo;

    private String localita;

    private String cap;

    @Column ( name = "sigla_provincia" )
    private String siglaProvincia;

    private String email;

    @Column ( name = "info_ente" )
    private String infoEnte;

    @Column ( name = "intestatario_cc_postale" )
    private String intestatarioCCPostale;

    @Column ( name = "numero_cc_postale" )
    private String numeroCCPostale;

    @Column ( name = "autorizzazione_da_poste_it" )
    private String autorizzazioneDaPosteIt;
    public Integer getIdDatiAvvisoPagamento () {
        return idDatiAvvisoPagamento;
    }

    public void setIdDatiAvvisoPagamento ( Integer idDatiAvvisoPagamento ) {
        this.idDatiAvvisoPagamento = idDatiAvvisoPagamento;
    }

    public EpaypaTCodiceVersamento getEpaypaTCodiceVersamento () {
        return epaypaTCodiceVersamento;
    }


    public void setEpaypaTCodiceVersamento ( EpaypaTCodiceVersamento epaypaTCodiceVersamento ) {
        this.epaypaTCodiceVersamento = epaypaTCodiceVersamento;
    }

    public String getSettore () {
        return settore;
    }

    public void setSettore ( String settore ) {
        this.settore = settore;
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

    public String getCap () {
        return cap;
    }

    public void setCap ( String cap ) {
        this.cap = cap;
    }

    public String getSiglaProvincia () {
        return siglaProvincia;
    }

    public void setSiglaProvincia ( String siglaProvincia ) {
        this.siglaProvincia = siglaProvincia;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getInfoEnte () {
        return infoEnte;
    }

    public void setInfoEnte ( String infoEnte ) {
        this.infoEnte = infoEnte;
    }

    public String getIntestatarioCCPostale () {
        return intestatarioCCPostale;
    }

    public void setIntestatarioCCPostale ( String intestatarioCCPostale ) {
        this.intestatarioCCPostale = intestatarioCCPostale;
    }

    public String getNumeroCCPostale () {
        return numeroCCPostale;
    }

    public void setNumeroCCPostale ( String numeroCCPostale ) {
        this.numeroCCPostale = numeroCCPostale;
    }

    public String getAutorizzazioneDaPosteIt () {
        return autorizzazioneDaPosteIt;
    }

    public void setAutorizzazioneDaPosteIt ( String autorizzazioneDaPosteIt ) {
        this.autorizzazioneDaPosteIt = autorizzazioneDaPosteIt;
    }

}
