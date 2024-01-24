/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTAnagrafica;
import it.csi.epay.epaybeapi.integration.dto.EpayTAnagraficaDTO;

/**
 * MapStruct mapping specifications for "EpayTAnagrafica" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTAnagraficaReferenceMapping.class,
    EpayTPagamentoReferenceMapping.class,
    EpayTRegistroVersamentiReferenceMapping.class,
} )
public interface EpayTAnagraficaMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "idAnagrafica", target = "idAnagrafica" ),
		@Mapping ( source = "nome", target = "nome" ),
		@Mapping ( source = "cognome", target = "cognome" ),
		@Mapping ( source = "ragioneSociale", target = "ragioneSociale" ),
		@Mapping ( source = "codiceFiscale", target = "codiceFiscale" ),
		@Mapping ( source = "indirizzo", target = "indirizzo" ),
		@Mapping ( source = "email", target = "email" ),
		@Mapping ( source = "telefono", target = "telefono" ),
		@Mapping ( source = "cellulare", target = "cellulare" ),
		@Mapping ( source = "civico", target = "civico" ),
		@Mapping ( source = "cap", target = "cap" ),
		@Mapping ( source = "localita", target = "localita" ),
		@Mapping ( source = "provincia", target = "provincia" ),
		@Mapping ( source = "nazione", target = "nazione" ),
		@Mapping ( source = "flagPersonaFisica", target = "flagPersonaFisica" ),
		@Mapping ( source = "listOfEpayTPagamento", target = "listOfEpayTPagamento" ),
		@Mapping ( source = "listOfEpayTRegistroVersamenti", target = "listOfEpayTRegistroVersamenti" ),
    	*/
    } )
    EpayTAnagraficaDTO toDTO ( EpayTAnagrafica input );

}
