/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.mapper;

import java.io.Serializable;
import java.util.List;

public interface IMapper<Record, DTO extends Serializable> {
    
    DTO toDTO(Record record);

    List<DTO> toDTO(Iterable<Record> recordList);
    
}
