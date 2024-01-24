/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTRegistroElaborazioniFault;
import it.csi.epay.epaybeapi.integration.dto.EpayTRegistroElaborazioniFaultReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTRegistroElaborazioniFault" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTRegistroElaborazioniFaultReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "id", target = "id" ),
		@Mapping ( source = "idPagamento", target = "idPagamento" ),
		@Mapping ( source = "codicePagamentoRifEnte", target = "codicePagamentoRifEnte" ),
		@Mapping ( source = "codiceMessaggio", target = "codiceMessaggio" ),
		@Mapping ( source = "descrizioneMessaggio", target = "descrizioneMessaggio" ),
    } )
    EpayTRegistroElaborazioniFaultReferenceDTO toDTO ( EpayTRegistroElaborazioniFault input );

}
