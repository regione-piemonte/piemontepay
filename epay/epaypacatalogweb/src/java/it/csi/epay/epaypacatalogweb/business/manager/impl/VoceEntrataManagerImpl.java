/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.VoceEntrataManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.mapper.VociEntrataMapper;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataDisponibileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataOutput;
import it.csi.epay.epaypacatalogweb.model.ppay.RicercaVoceEntrataPPayFiltroVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;


/**
 *
 */

@Service
public class VoceEntrataManagerImpl implements VoceEntrataManager {

    private final static String CODICE_OK = "OK";

    @Autowired
    private EpaypacatalogsrvFacade facade;

    @Override
    public List<VoceEntrataPPayVO> ricercaVoceEntrata ( RicercaVoceEntrataPPayFiltroVO input ) throws OperationFailedException {
        RicercaVoceEntrataInput mappedInput;
        RicercaVoceEntrataOutput rawOutput;

        try {
            mappedInput = VociEntrataMapper.map ( input );
            rawOutput = facade.ricercaVoceEntrata ( mappedInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( rawOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( rawOutput.getDescrizioneEsito () );
        }

        List<VoceEntrataPPayVO> mappedOutput = VociEntrataMapper.mapListRicercaVoceEntrataOutputDto (
            rawOutput.getRisultati () );

        return mappedOutput;
    }

    @Override
    public List<VoceEntrataPPayVO> ricercaVoceEntrataDisponibile ( RicercaVoceEntrataPPayFiltroVO input ) throws OperationFailedException {
        RicercaVoceEntrataDisponibileInput mappedInput;
        RicercaVoceEntrataDisponibileOutput rawOutput;

        try {
            mappedInput = VociEntrataMapper.mapToRicercaVoceEntrataDisponibileInput ( input );
            rawOutput = facade.ricercaVoceEntrataDisponibile ( mappedInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( rawOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( rawOutput.getDescrizioneEsito () );
        }

        List<VoceEntrataPPayVO> mappedOutput = VociEntrataMapper.mapListRicercaVoceEntrataDisponibileOutputDto (
            rawOutput.getRisultati () );

        return mappedOutput;
    }

    @Override
    public VoceEntrataPPayVO getVoceEntrataByCodice ( String codice ) throws OperationFailedException {
        RicercaVoceEntrataPPayFiltroVO filter = new RicercaVoceEntrataPPayFiltroVO ();
        filter.setCodice ( codice );
        List<VoceEntrataPPayVO> result;
        try {
            result = ricercaVoceEntrata ( filter );
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
        if ( result != null && result.size () > 0 ) {
            return result.get ( 0 );
        }
        return null;
    }

    @Override
    public List<VoceEntrataPPayVO> getOpzioniVoceEntrata () {
        RicercaVoceEntrataPPayFiltroVO filter = new RicercaVoceEntrataPPayFiltroVO ();
        List<VoceEntrataPPayVO> result;
        try {
            result = ricercaVoceEntrata ( filter );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
        return result;
    }

}
