/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.mapper.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import it.csi.epay.epayapi.integration.domain.EpayTRegistroVersamenti;
import it.csi.epay.epayapi.integration.dto.EpayTRegistroVersamentiDTO;

/**
 * MapStruct mapping specifications for "EpayTRegistroVersamenti" 
 *
 * @author fabio.fenoglio
 *
 */
@Mapper ( unmappedTargetPolicy = ReportingPolicy.ERROR, uses = { 
	EpayTRegistroVersamentiReferenceMapping.class,
    EpayTEsitiRicevutiReferenceMapping.class,
	EpayTTransazioneMdpReferenceMapping.class,
	EpayTPagamentoReferenceMapping.class,
	EpayDStatoPagamentoReferenceMapping.class,
	EpayTAnagraficaReferenceMapping.class,
    EpayTRtReferenceMapping.class,
} )
public interface EpayTRegistroVersamentiMapping {

    @Mappings ( { 
    	/*
		@Mapping ( source = "dataOperazione", target = "dataOperazione" ),
		@Mapping ( source = "iuv", target = "iuv" ),
		@Mapping ( source = "idRegistro", target = "idRegistro" ),
		@Mapping ( source = "descEsitoPagamento", target = "descEsitoPagamento" ),
		@Mapping ( source = "origineInserimento", target = "origineInserimento" ),
		@Mapping ( source = "listOfEpayTEsitiRicevuti", target = "listOfEpayTEsitiRicevuti" ),
		@Mapping ( source = "epayTTransazioneMdp", target = "epayTTransazioneMdp" ),
		@Mapping ( source = "epayTPagamento", target = "epayTPagamento" ),
		@Mapping ( source = "epayDStatoPagamento", target = "epayDStatoPagamento" ),
		@Mapping ( source = "epayTAnagrafica", target = "epayTAnagrafica" ),
		@Mapping ( source = "listOfEpayTRt", target = "listOfEpayTRt" ),
    	*/
    } )
    EpayTRegistroVersamentiDTO toDTO ( EpayTRegistroVersamenti input );

}
