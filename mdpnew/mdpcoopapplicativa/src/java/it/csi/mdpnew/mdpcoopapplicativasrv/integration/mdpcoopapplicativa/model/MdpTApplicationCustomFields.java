/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;


public class MdpTApplicationCustomFields implements Serializable {

    private static final long serialVersionUID = 1L;

    String applicationid;

    String fieldname;

    String fieldvalue;

    String gateway_id;

    String fielddescription;

    public String getApplicationid () {
        return applicationid;
    }

    public void setApplicationid ( String applicationid ) {
        this.applicationid = applicationid;
    }

    public String getFieldname () {
        return fieldname;
    }

    public void setFieldname ( String fieldname ) {
        this.fieldname = fieldname;
    }

    public String getFieldvalue () {
        return fieldvalue;
    }

    public void setFieldvalue ( String fieldvalue ) {
        this.fieldvalue = fieldvalue;
    }

    public String getGateway_id () {
        return gateway_id;
    }

    public void setGateway_id ( String gateway_id ) {
        this.gateway_id = gateway_id;
    }

    public String getFielddescription () {
        return fielddescription;
    }

    public void setFielddescription ( String fielddescription ) {
        this.fielddescription = fielddescription;
    }

    @Override
    public String toString () {
        return "MdpTApplicationCustomFields [applicationid=" + applicationid + ", fieldname=" + fieldname + ", fieldvalue=" + fieldvalue + ", gateway_id="
            + gateway_id + ", fielddescription=" + fielddescription + "]";
    }

    
}
