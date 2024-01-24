/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodricweb.business.manager.RilanciaElaborazioneManager;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.facade.EpaymodricWsFacade;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.inseriscielaborazione.InserisciElaborazioneFiltroVO;
import it.csi.epay.epaymodricweb.security.UserDetails;
import it.csi.epay.epaymodricweb.util.SecurityUtils;


@Service
public class RilanciaElaborazioneManagerImpl implements RilanciaElaborazioneManager {

    @Autowired
    private EpaymodricWsFacade service;

    @Override
    public void rilanciaElaborazione ( Long id )
                    throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
        //AttivaElaborazioneFiltroVO input = new AttivaElaborazioneFiltroVO();
        InserisciElaborazioneFiltroVO input = new InserisciElaborazioneFiltroVO ();
        UserDetails userDetails = SecurityUtils.getUser ();
        input.setCodiceFiscaleEnte ( userDetails.getEnte ().getCodiceFiscale () );
        input.setIdElaborazionePrecedente ( id );
        input.setIdEnte ( userDetails.getEnte ().getIdEnte () );
        input.setUtenteElaborazione ( userDetails.getUtente ().getCodiceFiscale () );
        input.setStatoDaImpostare ( "FORZATA" );

        service.inserisciElaborazione ( input );

    }

    public EpaymodricWsFacade getService () {
        return service;
    }

    public void setService ( EpaymodricWsFacade service ) {
        this.service = service;
    }

}
