/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 * The persistent class for the sim_t_flusso_origine_pagopa database table.
 * 
 */
@Entity
@Table ( name = "sim_t_flusso_origine_pagopa" )
@NamedQuery ( name = "SimTFlussoOriginePagopa.findAll", query = "SELECT s FROM SimTFlussoOriginePagopa s" )
public class SimTFlussoOriginePagopa extends DatiTecniciParentEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "SIM_T_FLUSSO_ORIGINE_PAGOPA_ID_GENERATOR", sequenceName = "SIM_T_FLUSSO_ORIGINE_PAGOPA_ID_SEQ", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "SIM_T_FLUSSO_ORIGINE_PAGOPA_ID_GENERATOR" )
    private Long id;

    @Column ( name = "cf_ente_creditore" )
    private String cfEnteCreditore;

    @Column ( name = "data_ora_messaggio" )
    private Timestamp dataOraMessaggio;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "data_regolamento" )
    private Date dataRegolamento;

    @Column ( name = "denominazione_ente" )
    private String denominazioneEnte;

    @Column ( name = "denominazione_psp" )
    private String denominazionePsp;

    @Column ( name = "id_messaggio" )
    private String idMessaggio;

    @Column ( name = "identificativo_flusso" )
    private String identificativoFlusso;

    @Column ( name = "identificativo_psp" )
    private String identificativoPsp;

    @Column ( name = "identificativo_univoco_regolamento" )
    private String identificativoUnivocoRegolamento;

    @Column ( name = "importo_totale_pagamenti" )
    private BigDecimal importoTotalePagamenti;

    @Column ( name = "importo_totale_pagamenti_intermediati" )
    private BigDecimal importoTotalePagamentiIntermediati;

    @Column ( name = "numero_totale_pagamenti" )
    private Integer numeroTotalePagamenti;

    @Column ( name = "numero_totale_pagamenti_intermediati" )
    private Integer numeroTotalePagamentiIntermediati;

    @Column ( name = "provvisorio_anno" )
    private Integer provvisorioAnno;

    @Column ( name = "provvisorio_numero" )
    private Integer provvisorioNumero;

    @Column ( name = "stato_elaborazione_flusso" )
    private Boolean statoElaborazioneFlusso;

    //bi-directional many-to-one association to SimTFlussoSintesiPagopa
    @OneToMany ( mappedBy = "simTFlussoOriginePagopa", cascade = CascadeType.ALL )
    @LazyCollection ( LazyCollectionOption.FALSE )
    private List<SimTFlussoSintesiPagopa> simTFlussoSintesiPagopas;

    public SimTFlussoOriginePagopa () {
    }

    @Override
    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCfEnteCreditore () {
        return this.cfEnteCreditore;
    }

    public void setCfEnteCreditore ( String cfEnteCreditore ) {
        this.cfEnteCreditore = cfEnteCreditore;
    }

    public Timestamp getDataOraMessaggio () {
        return this.dataOraMessaggio;
    }

    public void setDataOraMessaggio ( Timestamp dataOraMessaggio ) {
        this.dataOraMessaggio = dataOraMessaggio;
    }

    public Date getDataRegolamento () {
        return this.dataRegolamento;
    }

    public void setDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public String getDenominazioneEnte () {
        return this.denominazioneEnte;
    }

    public void setDenominazioneEnte ( String denominazioneEnte ) {
        this.denominazioneEnte = denominazioneEnte;
    }

    public String getDenominazionePsp () {
        return this.denominazionePsp;
    }

    public void setDenominazionePsp ( String denominazionePsp ) {
        this.denominazionePsp = denominazionePsp;
    }

    public String getIdMessaggio () {
        return this.idMessaggio;
    }

    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

    public String getIdentificativoFlusso () {
        return this.identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoPsp () {
        return this.identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public String getIdentificativoUnivocoRegolamento () {
        return this.identificativoUnivocoRegolamento;
    }

    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return this.importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return this.importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Integer getNumeroTotalePagamenti () {
        return this.numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return this.numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    public Integer getProvvisorioAnno () {
        return this.provvisorioAnno;
    }

    public void setProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public Integer getProvvisorioNumero () {
        return this.provvisorioNumero;
    }

    public void setProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
    }

    public Boolean getStatoElaborazioneFlusso () {
        return this.statoElaborazioneFlusso;
    }

    public void setStatoElaborazioneFlusso ( Boolean statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
    }

    public List<SimTFlussoSintesiPagopa> getSimTFlussoSintesiPagopas () {
        return this.simTFlussoSintesiPagopas;
    }

    public void setSimTFlussoSintesiPagopas ( List<SimTFlussoSintesiPagopa> simTFlussoSintesiPagopas ) {
        this.simTFlussoSintesiPagopas = simTFlussoSintesiPagopas;
    }

    public SimTFlussoSintesiPagopa addSimTFlussoSintesiPagopa ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa ) {
        getSimTFlussoSintesiPagopas ().add ( simTFlussoSintesiPagopa );
        simTFlussoSintesiPagopa.setSimTFlussoOriginePagopa ( this );

        return simTFlussoSintesiPagopa;
    }

    public SimTFlussoSintesiPagopa removeSimTFlussoSintesiPagopa ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa ) {
        getSimTFlussoSintesiPagopas ().remove ( simTFlussoSintesiPagopa );
        simTFlussoSintesiPagopa.setSimTFlussoOriginePagopa ( null );

        return simTFlussoSintesiPagopa;
    }

    public SimTFlussoOriginePagopa ( Long id, String cfEnteCreditore, Timestamp dataOraMessaggio, Date dataRegolamento, String denominazioneEnte,
        String denominazionePsp, String idMessaggio, String identificativoFlusso, String identificativoPsp, String identificativoUnivocoRegolamento,
        BigDecimal importoTotalePagamenti, BigDecimal importoTotalePagamentiIntermediati, Integer numeroTotalePagamenti,
        Integer numeroTotalePagamentiIntermediati, Integer provvisorioAnno, Integer provvisorioNumero, Boolean statoElaborazioneFlusso,
        List<SimTFlussoSintesiPagopa> simTFlussoSintesiPagopas, Date dataInserimento, Date dataModifica, String utenteInserimento, String utenteModifica ) {
        super ();
        this.id = id;
        this.cfEnteCreditore = cfEnteCreditore;
        this.dataOraMessaggio = dataOraMessaggio;
        this.dataRegolamento = dataRegolamento;
        this.denominazioneEnte = denominazioneEnte;
        this.denominazionePsp = denominazionePsp;
        this.idMessaggio = idMessaggio;
        this.identificativoFlusso = identificativoFlusso;
        this.identificativoPsp = identificativoPsp;
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
        this.importoTotalePagamenti = importoTotalePagamenti;
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
        this.numeroTotalePagamenti = numeroTotalePagamenti;
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
        this.provvisorioAnno = provvisorioAnno;
        this.provvisorioNumero = provvisorioNumero;
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
        this.simTFlussoSintesiPagopas = simTFlussoSintesiPagopas;
        setDataInserimento ( dataInserimento );
        setDataModifica ( dataModifica );
        setUtenteInserimento ( utenteInserimento );
        setUtenteModifica ( utenteModifica );
    }

}
