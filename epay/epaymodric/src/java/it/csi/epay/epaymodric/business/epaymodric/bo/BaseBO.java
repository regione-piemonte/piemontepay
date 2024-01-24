/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import it.csi.epay.epaymodric.business.epaymodric.model.DatiTecniciParentEntity;


/**
 *
 */

public class BaseBO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2058548704883638732L;

    protected String utenteInserimento;

    protected Timestamp dataInserimento;

    protected String utenteModifica;

    protected Timestamp dataModifica;

    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    public Timestamp getDataInserimento () {
        return dataInserimento;
    }

    public void setDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public String getUtenteModifica () {
        return utenteModifica;
    }

    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

    public Timestamp getDataModifica () {
        return dataModifica;
    }

    public void setDataModifica ( Timestamp dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public void mapBaseFields ( DatiTecniciParentEntity entity ) {
        this.dataInserimento = entity.getDataInserimento ();
        this.dataModifica = entity.getDataModifica ();
        this.utenteInserimento = entity.getUtenteInserimento ();
        this.utenteModifica = entity.getUtenteModifica ();
    }

    public void mapBaseFields ( BaseBO bo ) {
        this.dataInserimento = bo.getDataInserimento ();
        this.dataModifica = bo.getDataModifica ();
        this.utenteInserimento = bo.getUtenteInserimento ();
        this.utenteModifica = bo.getUtenteModifica ();
    }

}
