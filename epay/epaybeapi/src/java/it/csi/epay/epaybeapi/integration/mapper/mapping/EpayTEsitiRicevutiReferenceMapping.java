/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTEsitiRicevuti;
import it.csi.epay.epaybeapi.integration.dto.EpayTEsitiRicevutiReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTEsitiRicevuti" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTEsitiRicevutiReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idEsito", target = "idEsito" ),
		@Mapping ( source = "idModalitaRicezione", target = "idModalitaRicezione" ),
		@Mapping ( source = "idApplicazione", target = "idApplicazione" ),
		@Mapping ( source = "idTransazione", target = "idTransazione" ),
		@Mapping ( source = "dataPagamento", target = "dataPagamento" ),
		@Mapping ( source = "codEsitoPagamento", target = "codEsitoPagamento" ),
		@Mapping ( source = "descEsitoPagamento", target = "descEsitoPagamento" ),
		@Mapping ( source = "importo", target = "importo" ),
		@Mapping ( source = "identificativoPsp", target = "identificativoPsp" ),
		@Mapping ( source = "ragioneSocialePsp", target = "ragioneSocialePsp" ),
		@Mapping ( source = "iur", target = "iur" ),
    } )
    EpayTEsitiRicevutiReferenceDTO toDTO ( EpayTEsitiRicevuti input );

}
