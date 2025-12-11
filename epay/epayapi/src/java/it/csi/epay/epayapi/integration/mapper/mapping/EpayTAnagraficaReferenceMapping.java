/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTAnagrafica;
import it.csi.epay.epayapi.integration.dto.EpayTAnagraficaReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTAnagrafica" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTAnagraficaReferenceMapping {

    @Mappings ( { 
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
    } )
    EpayTAnagraficaReferenceDTO toDTO ( EpayTAnagrafica input );

}
