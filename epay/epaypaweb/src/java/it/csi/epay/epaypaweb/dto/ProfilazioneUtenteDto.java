/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.List;


public class ProfilazioneUtenteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private EnteDto ente;

    private UtenteDto utente;

    private List<CduDto> listaCdu;

    private List<CategoriaCduDto> categorieCdu;

    private List<CodiceVersamentoDto> codiciVersamentoVisibili;

    public ProfilazioneUtenteDto () {
    }

    public List<CodiceVersamentoDto> getCodiciVersamentoVisibili () {
        return codiciVersamentoVisibili;
    }

    public void setCodiciVersamentoVisibili ( List<CodiceVersamentoDto> codiciVersamentoVisibili ) {
        this.codiciVersamentoVisibili = codiciVersamentoVisibili;
    }

    public UtenteDto getUtente () {
        return utente;
    }

    public void setUtente ( UtenteDto utente ) {
        this.utente = utente;
    }

    public EnteDto getEnte () {
        return ente;
    }

    public void setEnte ( EnteDto ente ) {
        this.ente = ente;
    }

    public List<CduDto> getListaCdu () {
        return listaCdu;
    }

    public void setListaCdu ( List<CduDto> listaCdu ) {
        this.listaCdu = listaCdu;
    }

    public void setCategorieCdu ( List<CategoriaCduDto> categorieCdu ) {
        this.categorieCdu = categorieCdu;
    }

    public List<CategoriaCduDto> getCategorieCdu () {
        return categorieCdu;
    }

}
