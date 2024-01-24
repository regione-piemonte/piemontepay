/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import it.csi.epay.epaymodric.business.epaymodric.bo.BaseBO;
import it.csi.epay.epaymodric.dto.epaymodric.base.BaseDTO;


@MappedSuperclass
public abstract class DatiTecniciParentEntity extends AbstractCSILogAuditedParentEntity {

    @Column ( name = "utente_inserimento" )
    protected String utenteInserimento;

    @Column ( name = "data_inserimento" )
    protected Timestamp dataInserimento;

    @Column ( name = "utente_modifica" )
    protected String utenteModifica;

    @Column ( name = "data_modifica" )
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

    public void mapBaseFields ( BaseBO bo ) {
        dataInserimento = bo.getDataInserimento ();
        dataModifica = bo.getDataModifica ();
        utenteInserimento = bo.getUtenteInserimento ();
        utenteModifica = bo.getUtenteModifica ();
    }

    public void mapBaseFields ( BaseDTO bo ) {
        dataInserimento = bo.getDataInserimento ();
        dataModifica = bo.getDataModifica ();
        utenteInserimento = bo.getUtenteInserimento ();
        utenteModifica = bo.getUtenteModifica ();
    }

}
