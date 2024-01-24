/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile;

public class RisultatoRicercaRiferimentoContabileStoricoVO extends RisultatoRicercaRiferimentoContabileVO {

    private static final long serialVersionUID = 1L;

    private Long idRiferimentoContabilePadre;

    public Long getIdRiferimentoContabilePadre () {
        return idRiferimentoContabilePadre;
    }

    public void setIdRiferimentoContabilePadre ( Long idRiferimentoContabilePadre ) {
        this.idRiferimentoContabilePadre = idRiferimentoContabilePadre;
    }

    @Override
    public boolean isStorico () {
        return true;
    }

}
