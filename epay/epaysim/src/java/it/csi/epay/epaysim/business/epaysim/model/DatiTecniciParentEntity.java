/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * classe contenente i campi tecnici di tutte le entities.
 * 
 * @param <ID> Tipo della primary key
 */
@MappedSuperclass
public abstract class DatiTecniciParentEntity<ID extends Serializable> extends AbstractEntity<ID> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column ( name = "data_inserimento" )
    private Date dataInserimento;

    @Temporal ( TemporalType.TIMESTAMP )
    @Column ( name = "data_modifica" )
    private Date dataModifica;

    @Column ( name = "utente_inserimento" )
    private String utenteInserimento;

    @Column ( name = "utente_modifica" )
    private String utenteModifica;

    /**
     * @return the dataInserimento
     */
    public Date getDataInserimento () {
        return dataInserimento;
    }

    /**
     * @param dataInserimento the dataInserimento to set
     */
    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    /**
     * @return the dataModifica
     */
    public Date getDataModifica () {
        return dataModifica;
    }

    /**
     * @param dataModifica the dataModifica to set
     */
    public void setDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
    }

    /**
     * @return the utenteInserimento
     */
    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    /**
     * @param utenteInserimento the utenteInserimento to set
     */
    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    /**
     * @return the utenteModifica
     */
    public String getUtenteModifica () {
        return utenteModifica;
    }

    /**
     * @param utenteModifica the utenteModifica to set
     */
    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

}
