/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.cooppec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table ( name = "epaypa_t_ente_temp", schema = "epaypa" )
@NamedQueries ( {
    @NamedQuery (
                    name = "EpaypaTEnteTemp.findOneByIdOperazione",
                    query = "SELECT e "
                        + "FROM EpaypaTEnteTemp e "
                        + "WHERE e.idOperazione = :idOperazione" ),

    @NamedQuery (
                    name = "EpaypaTEnteTemp.findAll",
                    query = "SELECT e FROM EpaypaTEnteTemp e" )
} )

public class EpaypaTEnteTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAYPA_T_ENTE_TEMP_ID_GENERATOR", sequenceName="epaypa.EPAYPA_T_ENTE_TEMP_SEQ" ,schema="epaypa", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYPA_T_ENTE_TEMP_ID_GENERATOR")
    @Column ( name = "id_ente" )
    private Integer idEnte;

    @Column ( name = "cod_fiscale_ente" )
    private String codFiscaleEnte;

    private String denominazione;

    private String email;

    @Column ( name = "cod_gs1_gln" )
    private String codGs1Gln;

    @Column ( name = "cod_interbancario" )
    private String codInterbancario;

    @Column ( name = "id_operazione" )
    private String idOperazione;

    private byte[] logo;
    
    public EpaypaTEnteTemp () {
    }

    public Integer getIdEnte () {
        return this.idEnte;
    }

    public void setIdEnte ( Integer idEnte ) {
        this.idEnte = idEnte;
    }

    public String getCodFiscaleEnte () {
        return this.codFiscaleEnte;
    }

    public void setCodFiscaleEnte ( String codFiscaleEnte ) {
        this.codFiscaleEnte = codFiscaleEnte;
    }

    public String getDenominazione () {
        return this.denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public String getEmail () {
        return this.email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    public String getCodGs1Gln () {
        return codGs1Gln;
    }

    public void setCodGs1Gln ( String codGs1Gln ) {
        this.codGs1Gln = codGs1Gln;
    }

    public String getCodInterbancario () {
        return codInterbancario;
    }

    public void setCodInterbancario ( String codInterbancario ) {
        this.codInterbancario = codInterbancario;
    }

    
    public byte [] getLogo () {
        return logo;
    }

    
    public void setLogo ( byte [] logo ) {
        this.logo = logo;
    }

}
