/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class AutorizzaEsportazioneDatiInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private String entity;

    private List<String> idList;

    public String getEntity () {
        return entity;
    }

    public void setEntity ( String entity ) {
        this.entity = entity;
    }

    public List<String> getIdList () {
        return idList;
    }

    public void setIdList ( List<String> idList ) {
        this.idList = idList;
    }

}
