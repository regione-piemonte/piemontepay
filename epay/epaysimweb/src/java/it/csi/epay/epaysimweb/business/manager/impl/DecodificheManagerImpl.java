/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.business.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaysimweb.business.manager.DecodificheManager;
import it.csi.epay.epaysimweb.integration.facade.impl.EpaycatalogWsFacade;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.DecodificaOutputDto;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniModalitaAcquisizioneProvvisoriOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;
import it.csi.epay.epaysimweb.model.GenericVO;


@Service
public class DecodificheManagerImpl implements DecodificheManager {

    @Autowired
    private EpaycatalogWsFacade service;

    @Override
    public List<GenericVO> getListaOpzioniModalitaAcquisizioneProvvisori () {
        GetOpzioniModalitaAcquisizioneProvvisoriOutput opzioni;
        try {
            opzioni = service.getOpzioniModalitaAcquisizioneProvvisori ( new GetOpzioniInput () );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }

        List<GenericVO> o = new ArrayList<> ();
        for ( DecodificaOutputDto opziome: opzioni.getOpzioni () ) {
            o.add ( new GenericVO ( opziome.getId (), opziome.getCodice (), opziome.getDescrizione () ) );
        }
        return o;
    }

}
