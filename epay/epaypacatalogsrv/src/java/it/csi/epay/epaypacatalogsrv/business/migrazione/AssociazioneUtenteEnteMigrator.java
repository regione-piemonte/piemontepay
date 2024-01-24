/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.migrazione;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.EpaypaRUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.EpaypaRUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.vo.migrazione.CodiceEntitaMigrazione;


/**
 *
 */
@Service
public class AssociazioneUtenteEnteMigrator {

    @Autowired
    private MigratorHelper migratorHelper;

    @Autowired
    private AssociazioneUtenteEnteRepository epaycatRUtenteEnteRepository;

    @Autowired
    private EpaypaRUtenteEnteRepository epaypaRUtenteEnteRepository;

    public void inserisciRelazioniUtenteEnte ( MigrationContext context, Utente nuovoUtente ) {

        Long idUtenteNuovoCAT = nuovoUtente.getId ();
        Long idUtenteNuovoPA = migratorHelper.getIdUtentePADaIdUtenteCAT ( idUtenteNuovoCAT );

        List<EpaypaRUtenteEnte> relazioniPA = epaypaRUtenteEnteRepository.findByIdUtente ( idUtenteNuovoPA );

        for ( EpaypaRUtenteEnte relazionePA: relazioniPA ) {

            Long idEnteCAT = migratorHelper.getIdEnteCATDaIdEntePA ( relazionePA.getId ().getIdEnte () );

            AssociazioneUtenteEnte relazioneCAT = new AssociazioneUtenteEnte ();
            AssociazioneUtenteEntePK relazioneCATID = new AssociazioneUtenteEntePK ();
            relazioneCATID.setIdEnte ( idEnteCAT.intValue () );
            relazioneCATID.setIdUtente ( idUtenteNuovoCAT );
            relazioneCAT.setId ( relazioneCATID );

            AssociazioneUtenteEnte inserito = epaycatRUtenteEnteRepository.save ( relazioneCAT );

            context.info ( CodiceEntitaMigrazione.UTENTE,
                "inserita associazione fra utente " + nuovoUtente.getCodiceFiscale () + " ed ente " + inserito.getId ().getIdEnte () );
        }

    }

    public void eliminaRelazioniUtenteEnte ( MigrationContext context, Utente utente ) {
        epaycatRUtenteEnteRepository.deleteByIdUtente ( utente.getId () );
    }
}
