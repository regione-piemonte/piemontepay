/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.util.Date;
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
 * The persistent class for the sim_t_giornale_di_cassa database table.
 * 
 */
@Entity
@Table(name="sim_t_giornale_di_cassa")
@NamedQuery(name="SimTGiornaleDiCassa.findAll", query="SELECT s FROM SimTGiornaleDiCassa s")
public class SimTGiornaleDiCassa extends DatiTecniciParentEntity<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "SIM_T_GIORNALE_DI_CASSA_ID_GENERATOR", sequenceName = "SIM_T_GIORNALE_DI_CASSA_ID_SEQ", allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIM_T_GIORNALE_DI_CASSA_ID_GENERATOR")
    private Integer id;

    private String xml;

    @Column ( name = "identificativo_flusso_bt" )
    private String identificativoFlussoBt;

    //bi-directional many-to-one association to SimTProvvisorio
    @OneToMany(mappedBy="simTGiornaleDiCassa")
    private List<SimTProvvisorio> simTProvvisorios;

    public SimTGiornaleDiCassa() {
    }

    public SimTGiornaleDiCassa ( Integer id, Date dataInserimento, Date dataModifica, String utenteInserimento, String utenteModifica, String xml,
        String identificativoFlussoBt, List<SimTProvvisorio> simTProvvisorios ) {
        super ();
        this.id = id;
        setDataInserimento ( dataInserimento );
        setUtenteInserimento ( utenteInserimento );
        setUtenteModifica ( utenteModifica );
        setDataModifica ( dataModifica );
        this.xml = xml;
        this.identificativoFlussoBt = identificativoFlussoBt;
        this.simTProvvisorios = simTProvvisorios;
    }

    @Override
    public Integer getId () {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXml() {
        return this.xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getIdentificativoFlussoBT () {
        return identificativoFlussoBt;
    }

    public void setIdentificativoFlussoBT ( String identificativoFlussoBt ) {
        this.identificativoFlussoBt = identificativoFlussoBt;
    }

    public List<SimTProvvisorio> getSimTProvvisorios() {
        return this.simTProvvisorios;
    }

    public void setSimTProvvisorios(List<SimTProvvisorio> simTProvvisorios) {
        this.simTProvvisorios = simTProvvisorios;
    }

    public SimTProvvisorio addSimTProvvisorio(SimTProvvisorio simTProvvisorio) {
        getSimTProvvisorios().add(simTProvvisorio);
        simTProvvisorio.setSimTGiornaleDiCassa(this);

        return simTProvvisorio;
    }

    public SimTProvvisorio removeSimTProvvisorio(SimTProvvisorio simTProvvisorio) {
        getSimTProvvisorios().remove(simTProvvisorio);
        simTProvvisorio.setSimTGiornaleDiCassa(null);

        return simTProvvisorio;
    }

}
