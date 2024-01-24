/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOAuditAction;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


/**
 *
 *
 * @author Gueye
 *
 */

public class DTOInputWsLogAudit extends DTOInputBase {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer uniqueid;

    private String codappmodify;

    private String idaction;

    private String dataOra;

    private String descrizione;

    private Integer utente;

    private DTOAuditAction dtoAuditAction;

    public DTOInputWsLogAudit () {
        super ();
    }

    public Integer getUniqueid () {
        return uniqueid;
    }

    public void setUniqueid ( Integer uniqueid ) {
        this.uniqueid = uniqueid;
    }

    public String getCodappmodify () {
        return codappmodify;
    }

    public void setCodappmodify ( String codappmodify ) {
        this.codappmodify = codappmodify;
    }

    public String getIdaction () {
        return idaction;
    }

    public void setIdaction ( String idaction ) {
        this.idaction = idaction;
    }

    public String getDataOra () {
        return dataOra;
    }

    public void setDataOra ( String dataOra ) {
        this.dataOra = dataOra;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public Integer getUtente () {
        return utente;
    }

    public void setUtente ( Integer utente ) {
        this.utente = utente;
    }

    public DTOAuditAction getDtoAuditAction () {
        return dtoAuditAction;
    }

    public void setDtoAuditAction ( DTOAuditAction dtoAuditAction ) {
        this.dtoAuditAction = dtoAuditAction;
    }

    @Override
    public String toString () {
        return "DTOLogAudit [uniqueid=" + uniqueid + ", codappmodify=" + codappmodify + ", idaction=" + idaction
            + ", dataOra=" + dataOra + ", descrizione=" + descrizione + ", utente=" + utente + ", dtoAuditAction="
            + dtoAuditAction + "]";
    }

}
