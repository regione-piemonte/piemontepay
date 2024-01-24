/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the applicationcustomfields database table.
 *
 */
@Entity
@Table(name="applicationcustomfields")
@NamedQuery(name="Applicationcustomfield.findAll", query="SELECT a FROM Applicationcustomfield a")
public class Applicationcustomfield implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="APPLICATIONCUSTOMFIELDS_KEYID_GENERATOR", sequenceName="APPLICATIONCUSTOMFIELDS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICATIONCUSTOMFIELDS_KEYID_GENERATOR")
    private Integer keyid;

    private String fielddescription;

    private String fieldname;

    private String fieldvalue;

    //bi-directional many-to-one association to Application
    @ManyToOne ( fetch = FetchType.LAZY )
    @JoinColumn ( name = "applicationid" )
    private Application application;

    //bi-directional many-to-one association to Gateway
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn(name="gateway_id")
    private Gateway gateway;

    public Applicationcustomfield() {
    }

    public Integer getKeyid() {
        return this.keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public String getFielddescription() {
        return this.fielddescription;
    }

    public void setFielddescription(String fielddescription) {
        this.fielddescription = fielddescription;
    }

    public String getFieldname() {
        return this.fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldvalue() {
        return this.fieldvalue;
    }

    public void setFieldvalue(String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Gateway getGateway() {
        return this.gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

}
