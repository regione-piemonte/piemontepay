/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.util.List;

/**
 * 
 */
public class UpdateEsitoStatoElaborazioneFlussoInputDTO extends ParentInput {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<String> identificativiFlussoOrigineList;

    private Boolean nuovoStato;

    public Boolean getNuovoStato () {
        return nuovoStato;
    }

    public void setNuovoStato ( Boolean nuovoStato ) {
        this.nuovoStato = nuovoStato;
    }

    public List<String> getIdentificativiFlussoOrigineList () {
        return identificativiFlussoOrigineList;
    }

    public void setIdentificativiFlussoOrigineList ( List<String> identificativiFlussoOrigineList ) {
        this.identificativiFlussoOrigineList = identificativiFlussoOrigineList;
    }

}
