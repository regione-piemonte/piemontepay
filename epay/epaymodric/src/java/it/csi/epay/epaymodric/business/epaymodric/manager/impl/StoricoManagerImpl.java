/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoDettaglioManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoOrigineManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StoricoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiStoricoUtilityWS;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTStoricoElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTStoricoFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTStoricoFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTStoricoFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.repository.ElaborazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoDettaglioRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoSintesiRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StoricoElaborazioneRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StoricoFlussoDettaglioRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StoricoFlussoOrigineRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StoricoFlussoSintesiRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStoricoFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStoricoFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStoricoFlussoSintesi;


/**
 * @author vsgro use case 2.2.13
 */
@Service
public class StoricoManagerImpl implements StoricoManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    StoricoFlussoOrigineRepository storicoFlussoOrigineRepository;

    @Autowired
    StoricoFlussoSintesiRepository storicoFlussoSintesiRepository;

    @Autowired
    StoricoFlussoDettaglioRepository storicoFlussoDettaglioRepository;

    @Autowired
    StoricoElaborazioneRepository storicoElaborazioneRepository;

    @Autowired
    ElaborazioneRepository elaborazioneRepository;

    @Autowired
    FlussoManager flussoManager;

    @Autowired
    FlussoOrigineManager flussoOrigineManager;

    @Autowired
    FlussoDettaglioManager flussoDettaglioManager;

    @Autowired
    FlussoSintesiRepository flussoSintesiRepository;

    @Autowired
    FlussoDettaglioRepository flussoDettaglioRepository;

    public StoricoFlussoDettaglioRepository getStoricoFlussoDettaglioRepository () {
        return storicoFlussoDettaglioRepository;
    }

    public StoricoFlussoOrigineRepository getStoricoFlussoOrigineRepository () {
        return storicoFlussoOrigineRepository;
    }

    public StoricoFlussoSintesiRepository getStoricoFlussoSintesiRepository () {
        return storicoFlussoSintesiRepository;
    }

    @SuppressWarnings ( "unused" )
    private void salva ( DTOStoricoFlussoDettaglio dtoStoricoFlussoDettaglio ) {
        logger.info ( "StoricoFlussiManagerImpl.salva (DTOStoricoFlussoOrigine): INIZIO" );
        try {
            CblTStoricoFlussoDettaglio cblTStoricoFlussoDettaglio = FlussiStoricoUtilityWS.getDTOStoricoFlussoDettaglio ( dtoStoricoFlussoDettaglio );

            cblTStoricoFlussoDettaglio.setUtenteInserimento ( StringUtils.hasText ( dtoStoricoFlussoDettaglio.getUtenteModifica () )? dtoStoricoFlussoDettaglio.getUtenteModifica ():Costanti.DEFAULT_UTENTE_SISTEMA );
            cblTStoricoFlussoDettaglio.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );

            storicoFlussoDettaglioRepository.saveAndFlush ( cblTStoricoFlussoDettaglio );
        } catch ( Exception e ) {
            logger.error ( "dtoStoricoFlussoDettaglio" );
        }
        logger.info ( "StoricoFlussiManagerImpl.salva (DTOStoricoFlussoOrigine): FINE" );
    }

    private void salva ( DTOStoricoFlussoOrigine dtoStoricoFlussoOrigine ) {
        logger.info ( "StoricoFlussiManagerImpl.salva (DTOStoricoFlussoOrigine): INIZIO" );
        try {
            CblTStoricoFlussoOrigine cblTStoricoFlussoOrigine = FlussiStoricoUtilityWS.getDTOStoricoFlussoOrigine ( dtoStoricoFlussoOrigine );

            cblTStoricoFlussoOrigine.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
            cblTStoricoFlussoOrigine.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );

            storicoFlussoOrigineRepository.saveAndFlush ( cblTStoricoFlussoOrigine );
        } catch ( Exception e ) {
            logger.error ( "dtoStoricoFlussoOrigine" );
        }
        logger.info ( "StoricoFlussiManagerImpl.salva (DTOStoricoFlussoOrigine): FINE" );
    }

    private void salva ( DTOStoricoFlussoSintesi dtoStoricoFlussoSintesi ) {
        logger.info ( "StoricoFlussiManagerImpl.salva (DTOStoricoFlussoOrigine): INIZIO" );
        try {
            CblTStoricoFlussoSintesi cblTStoricoFlussoSintesi = FlussiStoricoUtilityWS.getDTOStoricoFlussoSintesi ( dtoStoricoFlussoSintesi );
            storicoFlussoSintesiRepository.save ( cblTStoricoFlussoSintesi );
        } catch ( Exception e ) {
            logger.error ( "dtoStoricoFlussoSintesi" );
        }
        logger.info ( "StoricoFlussiManagerImpl.salva (DTOStoricoFlussoOrigine): FINE" );
    }

    public void setStoricoFlussoDettaglioRepository ( StoricoFlussoDettaglioRepository storicoFlussoDettaglioRepository ) {
        this.storicoFlussoDettaglioRepository = storicoFlussoDettaglioRepository;
    }

    public void setStoricoFlussoOrigineRepository ( StoricoFlussoOrigineRepository storicoFlussoOrigineRepository ) {
        this.storicoFlussoOrigineRepository = storicoFlussoOrigineRepository;
    }

    public void setStoricoFlussoSintesiRepository ( StoricoFlussoSintesiRepository storicoFlussoSintesiRepository ) {
        this.storicoFlussoSintesiRepository = storicoFlussoSintesiRepository;
    }

    private void storicizza(DTOStoricoFlussoOrigine dtoStoricoFlussoOrigine, Long idFLussoOrigine)  {
        salva ( dtoStoricoFlussoOrigine );
        List<FlussoSintesi> flussiSintesi = flussoManager.leggiPerIdFlussoOrigine ( idFLussoOrigine );
        for ( int i = 0; i < flussiSintesi.size (); i++ ) {
            FlussoSintesi flussoSintesi = flussiSintesi.get ( i );
            CblTFlussoSintesi flussoSintesiEntity = flussoSintesiRepository.findOne ( flussoSintesi.getId () );
            DTOStoricoFlussoSintesi dtoStoricoFlussoSintesi = FlussiStoricoUtilityWS.getDTOStoricoFlussoSintesi ( flussoSintesiEntity );
            salva ( dtoStoricoFlussoSintesi );
            //          Per ora non e' necessario storicizzare il dettaglio
            //            List<FlussoDettaglio> flussiDettaglio = flussoSintesi.getListaPagamenti ();
            //            if ( null != flussiDettaglio ) {
            //                for ( int d = 0; d < flussiDettaglio.size (); d++ ) {
            //                    FlussoDettaglio flussoDettaglio = flussiDettaglio.get ( d );
            //                    DTOStoricoFlussoDettaglio dtoStoricoFlussoDettaglio
            //                        = FlussiStoricoUtilityWS.getDTOStoricoFlussoDettaglio ( flussoDettaglioRepository.findOne ( flussoDettaglio.getId () ),
            //                            flussoSintesi.getId () );
            //                    salva ( dtoStoricoFlussoDettaglio );
            //                }
            //            }
        }
    }

    @Override
    @Transactional
    public void storicizzaDatiFlusso(List<CblTFlussoOrigine> flussiOrigineEntityList) {
        //        CallerInputDto utenteModifica = RequestContextUtils.getRequestContext ().getCaller ();

        for (CblTFlussoOrigine flussoOrigineEntity : flussiOrigineEntityList) {
            DTOStoricoFlussoOrigine dtoStoricoFlussoOrigine = FlussiStoricoUtilityWS
                            .getDTOStoricoFlussoOrigine ( FlussiUtility.getFlussoOrigine ( flussoOrigineEntity ), Costanti.DEFAULT_UTENTE_SISTEMA );
            storicizza(dtoStoricoFlussoOrigine, flussoOrigineEntity.getId());
        }
    }

    @Override
    @Transactional
    public void storicizzaDatiFlusso ( Long idOrigine ) {
        logger.info ( "StoricoFlussiServiceImpl.storicizzaDatiFlusso: INIZIO" );
        //        CallerInputDto utenteModifica = RequestContextUtils.getRequestContext ().getCaller ();

        FlussoOrigine flussoOrigine = flussoOrigineManager.leggi ( idOrigine );
        DTOStoricoFlussoOrigine dtoStoricoFlussoOrigine
        = FlussiStoricoUtilityWS.getDTOStoricoFlussoOrigine ( flussoOrigine, Costanti.DEFAULT_UTENTE_SISTEMA );
        storicizza(dtoStoricoFlussoOrigine, idOrigine);
        logger.info ( "StoricoFlussiServiceImpl.storicizzaDatiFlusso: FINE" );
    }

    @Override
    public void storicizzaElaborazione(Elaborazione elaborazione) {

        logger.info ( "StoricoFlussiServiceImpl.storicizzaElaborazione: INIZIO" );

        CblTStoricoElaborazione  cblTStoricoElaborazione = FlussiStoricoUtilityWS.getStoricoElaborazioneEntity(elaborazione);

        CblTElaborazione cblTElaborazione = elaborazioneRepository.findOne ( elaborazione.getId () );

        cblTStoricoElaborazione.setCblTElaborazione ( cblTElaborazione );

        cblTStoricoElaborazione.setUtenteInserimento (
            StringUtils.hasText ( elaborazione.getUtenteModifica () ) ? elaborazione.getUtenteModifica () : Costanti.DEFAULT_UTENTE_SISTEMA );
        cblTStoricoElaborazione.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );

        storicoElaborazioneRepository.saveAndFlush(cblTStoricoElaborazione);

        logger.info ( "StoricoFlussiServiceImpl.storicizzaElaborazione: FINE" );

    }

}
