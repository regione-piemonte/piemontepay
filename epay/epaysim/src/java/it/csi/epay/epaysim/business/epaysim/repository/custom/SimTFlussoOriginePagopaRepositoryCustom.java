/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.repository.custom;

import java.util.List;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.dto.RicercaFlussoInputDTO;


/**
 *
 */

public interface SimTFlussoOriginePagopaRepositoryCustom {

    /**
     * Metodo di ricerca per specification
     * 
     * @param ricercaFlussoInputDTO input criteria
     * @return List<SimTFlussoOriginePagopa> lista di entities trovate.
     */
    List<SimTFlussoOriginePagopa> findAll ( RicercaFlussoInputDTO ricercaFlussoInputDTO );

    /**
     * Metodo che ricerca un SimTFlussoOriginePagopa a partire dall'ID e recupera tutti i figli con le join condition.
     * 
     * @param idSimTFlussoOriginePagopa id SimTFlussoOriginePagopa
     * @return List<SimTFlussoOriginePagopa> lista di entities trovate.
     */
    SimTFlussoOriginePagopa findOneWithchildren ( Long idSimTFlussoOriginePagopa );
}
