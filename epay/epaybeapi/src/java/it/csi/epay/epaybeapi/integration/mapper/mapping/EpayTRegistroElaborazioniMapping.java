/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTRegistroElaborazioni;
import it.csi.epay.epaybeapi.integration.dto.EpayTRegistroElaborazioniDTO;

/**
 * MapStruct mapping specifications for "EpayTRegistroElaborazioni" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTRegistroElaborazioniReferenceMapping.class,
    EpayTPagamentoReferenceMapping.class,
    EpayTRegistroElaborazioniFaultReferenceMapping.class,
} )
public interface EpayTRegistroElaborazioniMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "id", target = "id" ),
		@Mapping ( source = "dataInizio", target = "dataInizio" ),
		@Mapping ( source = "dataFine", target = "dataFine" ),
		@Mapping ( source = "operazione", target = "operazione" ),
		@Mapping ( source = "idMessaggio", target = "idMessaggio" ),
		@Mapping ( source = "idEnte", target = "idEnte" ),
		@Mapping ( source = "idTipoPagamento", target = "idTipoPagamento" ),
		@Mapping ( source = "pagamentiSpontanei", target = "pagamentiSpontanei" ),
		@Mapping ( source = "numPagamenti", target = "numPagamenti" ),
		@Mapping ( source = "importoTotPagamenti", target = "importoTotPagamenti" ),
		@Mapping ( source = "esito", target = "esito" ),
		@Mapping ( source = "messageFault", target = "messageFault" ),
		@Mapping ( source = "listOfEpayTPagamento", target = "listOfEpayTPagamento" ),
		@Mapping ( source = "listOfEpayTRegistroElaborazioniFault", target = "listOfEpayTRegistroElaborazioniFault" ),
    	*/
    } )
    EpayTRegistroElaborazioniDTO toDTO ( EpayTRegistroElaborazioni input );

}
