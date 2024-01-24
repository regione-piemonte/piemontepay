/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

import java.io.Serializable;
import java.util.List;


public class GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private List<GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto> codiciVersamento;

    public List<GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto> getCodiciVersamento () {
        return codiciVersamento;
    }

    public void setCodiciVersamento ( List<GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

}
