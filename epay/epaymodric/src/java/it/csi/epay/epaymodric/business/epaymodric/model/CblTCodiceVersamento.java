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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;


/**
 * The persistent class for the cbl_t_codice_versamento database table.
 * 
 */
@Entity
@Table ( name = "cbl_t_codice_versamento" )
@NamedQuery ( name = "CblTCodiceVersamento.findAll", query = "SELECT c FROM CblTCodiceVersamento c" )
public class CblTCodiceVersamento extends DatiTecniciParentEntity implements Serializable, Comparable<CblTCodiceVersamentoTemp> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "CBL_T_CODICE_VERSAMENTO_ID_GENERATOR", sequenceName = "CBL_T_CODICE_VERSAMENTO_ID_SEQ", schema="epaymodric", allocationSize=1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CBL_T_CODICE_VERSAMENTO_ID_GENERATOR" )
    private Long id;

    @Column ( name = "id_ente" )
    private String idEnte;

    @Column ( name = "codice_versamento" )
    private String codiceVersamento;

    @Column ( name = "codice_fiscale_ente" )
    private String codiceFiscaleEnte;

    @Column ( name = "data_fine_validita" )
    private Timestamp dataFineValidita;

    @Column ( name = "data_inizio_validita" )
    private Timestamp dataInizioValidita;

    @Column ( name = "descrizione_versamento" )
    private String descrizioneVersamento;

    @Column ( name = "flag_annullato" )
    private Boolean flagAnnullato;

    @Column ( name = "codice_modalita_integrazione" )
    private String codiceModalitaIntegrazione;
    
    @Column ( name = "fattura" )
    private Boolean fattura;

    //bi-directional many-to-one association to CblTCatalogo
    @OneToMany ( mappedBy = "cblTCodiceVersamento" )
    private List<CblTCatalogo> cblTCatalogos;

    public CblTCodiceVersamento () {
    }

    @Override
    public int compareTo ( CblTCodiceVersamentoTemp o ) {
        return new CompareToBuilder ().append ( codiceVersamento, o.getCodiceVersamento () ).append ( idEnte, o.getIdEnte () ).toComparison ();
    }

    public List<CblTCatalogo> getCblTCatalogos () {
        return this.cblTCatalogos;
    }

    public String getCodiceFiscaleEnte () {
        return this.codiceFiscaleEnte;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public Timestamp getDataFineValidita () {
        return this.dataFineValidita;
    }

    public Timestamp getDataInizioValidita () {
        return this.dataInizioValidita;
    }

    public String getDescrizioneVersamento () {
        return this.descrizioneVersamento;
    }

    public Boolean getFlagAnnullato () {
        return this.flagAnnullato;
    }

    public Long getId () {
        return this.id;
    }

    public String getIdEnte () {
        return idEnte;
    }

    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    public void setCblTCatalogos ( List<CblTCatalogo> cblTCatalogos ) {
        this.cblTCatalogos = cblTCatalogos;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public void setDataFineValidita ( Timestamp dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public void setDataInizioValidita ( Timestamp dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public void setDescrizioneVersamento ( String descrizioneVersamento ) {
        this.descrizioneVersamento = descrizioneVersamento;
    }

    public void setFlagAnnullato ( Boolean flagAnnullato ) {
        this.flagAnnullato = flagAnnullato;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

    
    public Boolean getFattura () {
        return fattura;
    }

    
    public void setFattura ( Boolean fattura ) {
        this.fattura = fattura;
    }
    
    

}
