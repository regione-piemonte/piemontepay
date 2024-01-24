/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.ente;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class AggiornaEnteOutput extends ParentOutput {

	private static final long serialVersionUID = 1L;

    private String codiceRisultatoSincronizzazione;

    private String descrizioneRisultatoSincronizzazione;

    public String getCodiceRisultatoSincronizzazione () {
        return codiceRisultatoSincronizzazione;
    }

    public void setCodiceRisultatoSincronizzazione ( String codiceRisultatoSincronizzazione ) {
        this.codiceRisultatoSincronizzazione = codiceRisultatoSincronizzazione;
    }

    public String getDescrizioneRisultatoSincronizzazione () {
        return descrizioneRisultatoSincronizzazione;
    }

    public void setDescrizioneRisultatoSincronizzazione ( String descrizioneRisultatoSincronizzazione ) {
        this.descrizioneRisultatoSincronizzazione = descrizioneRisultatoSincronizzazione;
    }
}
