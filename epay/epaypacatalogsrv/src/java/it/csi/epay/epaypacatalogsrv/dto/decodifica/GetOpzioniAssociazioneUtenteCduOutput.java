/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class GetOpzioniAssociazioneUtenteCduOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private List<GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto> categorie;

    public List<GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto> getCategorie () {
        return categorie;
    }

    public void setCategorie ( List<GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto> categorie ) {
        this.categorie = categorie;
    }

}
