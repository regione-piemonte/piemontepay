/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTEnte;
import it.csi.epay.epaypacatalogsrv.model.EpaypaTUtente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaTUtenteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;


/**
 *
 */
@Service
public class MigratorHelper {

    public final static int OFFSET_ID = 500000;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private EpaypaTEnteRepository epaypaTEnteRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EpaypaTUtenteRepository epaypaTUtenteRepository;

    public Long getIdUtentePADaIdUtenteCAT ( Long idUtenteCAT ) {
        Utente utenteCAT = utenteRepository.findOne ( idUtenteCAT );
        if ( utenteCAT == null ) {
            throw new RuntimeException ( "Utente CAT non trovato con ID " + idUtenteCAT );
        }

        EpaypaTUtente utentePA = epaypaTUtenteRepository.findOneByCodUtente ( utenteCAT.getCodiceFiscale () );
        if ( utentePA == null ) {
            throw new RuntimeException ( "Utente PA non trovato con CF " + utenteCAT.getCodiceFiscale () );
        }

        return utentePA.getIdUtente ();
    }

    public Long getIdUtenteCATDaIdUtentePA ( Long idUtentePA ) {
        EpaypaTUtente utentePA = epaypaTUtenteRepository.findOne ( idUtentePA );
        if ( utentePA == null ) {
            throw new RuntimeException ( "Utente PA non trovato con ID " + idUtentePA );
        }

        Utente utenteCAT = utenteRepository.findOneByCodiceFiscale ( utentePA.getCodUtente () );
        if ( utenteCAT == null ) {
            throw new RuntimeException ( "Utente CAT non trovato con CF " + utentePA.getCodUtente () );
        }

        return utenteCAT.getId ();
    }

    public Integer getIdEntePADaIdEnteCAT ( Long idEnteCAT ) {
        Ente utenteCAT = enteRepository.findOne ( idEnteCAT );
        if ( utenteCAT == null ) {
            throw new RuntimeException ( "Ente CAT non trovato con ID " + idEnteCAT );
        }

        EpaypaTEnte utentePA = epaypaTEnteRepository.findOneByCodFiscaleEnte ( utenteCAT.getCodiceFiscale () );
        if ( utentePA == null ) {
            throw new RuntimeException ( "Ente PA non trovato con CF " + utenteCAT.getCodiceFiscale () );
        }

        return utentePA.getIdEnte ();
    }

    public Long getIdEnteCATDaIdEntePA ( Integer idEntePA ) {
        EpaypaTEnte utentePA = epaypaTEnteRepository.findOne ( idEntePA );
        if ( utentePA == null ) {
            throw new RuntimeException ( "Ente PA non trovato con ID " + idEntePA );
        }

        Ente utenteCAT = enteRepository.findOneByCodiceFiscale ( utentePA.getCodFiscaleEnte () );
        if ( utenteCAT == null ) {
            throw new RuntimeException ( "Ente CAT non trovato con CF " + utentePA.getCodFiscaleEnte () );
        }

        return utenteCAT.getId ();
    }

    public Ente getEnteCATDaEntePASePresente ( EpaypaTEnte utentePA ) {
        Ente utenteCAT = enteRepository.findOneByCodiceFiscale ( utentePA.getCodFiscaleEnte () );
        return utenteCAT;
    }

    public Utente getUtenteCATDaIdUtentePASePresente ( Long idUtentePA ) {

        EpaypaTUtente utentePA = epaypaTUtenteRepository.findOne ( idUtentePA );
        if ( utentePA == null ) {
            return null;
        }

        Utente utenteCAT = utenteRepository.findOneByCodiceFiscale ( utentePA.getCodUtente () );

        return utenteCAT;
    }

}
