/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.util.CollectionUtils;

import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.DecodificaOutputDto;
import it.csi.epay.epaymodricweb.model.GenericVO;

public class GenericVOMapper extends ParentMapper {

    public static GenericVO parseDecodificaVO(DecodificaOutputDto dto) {
        GenericVO mapped = new GenericVO();
		mapped.setId(dto.getId ());
		mapped.setCodice ( dto.getCodice () );
		mapped.setDescrizione ( dto.getDescrizione () );
		return mapped;
	}

    public static List<GenericVO> parseListDecodificaVO ( List<DecodificaOutputDto> dtoList ) {
        List<GenericVO> retList = new LinkedList<> ();
        if ( !CollectionUtils.isEmpty ( dtoList ) ) {
            dtoList.forEach ( new Consumer<DecodificaOutputDto> () {

                @Override
                public void accept ( DecodificaOutputDto t ) {
                    retList.add ( parseDecodificaVO ( t ) );
                }
            } );
        }
        return retList;
    }

}
