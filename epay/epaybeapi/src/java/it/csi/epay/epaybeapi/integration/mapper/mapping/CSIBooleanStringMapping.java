/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.integration.mapper.mapping;

import org.mapstruct.Mapper;


/**
 * Mapper astratto per la trasformazione da Boolean a String e viceversa.
 */
@Mapper
public interface CSIBooleanStringMapping {

    default Boolean parseBoolean ( String s ) {
        return ( ( s != null ) && s.equalsIgnoreCase ( "S" ) );
    }

    default String parseString ( Boolean b ) {
        return ( b == null ) ? null : b ? "S" : "N";
    }
}
