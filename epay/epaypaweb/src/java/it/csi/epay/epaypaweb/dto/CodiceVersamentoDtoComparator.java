/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.util.Comparator;


public class CodiceVersamentoDtoComparator implements Comparator<CodiceVersamentoDto> {

    @Override
    public int compare ( CodiceVersamentoDto o1, CodiceVersamentoDto o2 ) {
        return o1.getCod ().compareTo ( o2.getCod () );
    }


}
