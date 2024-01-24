/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epaybeapi.integration.domain.EpayTDatiAvvisoPagamento;
import it.csi.epay.epaybeapi.integration.dto.EpayTDatiAvvisoPagamentoReferenceDTO;

/**
 * MapStruct mapping specifications for "EpayTDatiAvvisoPagamento" 
 *
 * @author EII
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {} )
public interface EpayTDatiAvvisoPagamentoReferenceMapping {

    @Mappings ( { 
		@Mapping ( source = "idDatiAvvisoPagamento", target = "idDatiAvvisoPagamento" ),
		@Mapping ( source = "settore", target = "settore" ),
		@Mapping ( source = "indirizzo", target = "indirizzo" ),
		@Mapping ( source = "localita", target = "localita" ),
		@Mapping ( source = "cap", target = "cap" ),
		@Mapping ( source = "siglaProvincia", target = "siglaProvincia" ),
		@Mapping ( source = "email", target = "email" ),
		@Mapping ( source = "infoEnte", target = "infoEnte" ),
		@Mapping ( source = "intestatarioCcPostale", target = "intestatarioCcPostale" ),
		@Mapping ( source = "numeroCcPostale", target = "numeroCcPostale" ),
		@Mapping ( source = "autorizzazioneDaPosteIt", target = "autorizzazioneDaPosteIt" ),
		@Mapping ( source = "utenteUltimaModifica", target = "utenteUltimaModifica" ),
		@Mapping ( source = "dataUltimaModifica", target = "dataUltimaModifica" ),
    } )
    EpayTDatiAvvisoPagamentoReferenceDTO toDTO ( EpayTDatiAvvisoPagamento input );

}
