/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RicercaElaborazionePrecedenteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ElaborazioneUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.RicercaElaborazionePrecedenteRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaElaborazionePrecedente;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputRicercaElaborazionePrecedente;


/**
 *
 * @author Gueye
 *
 */

@Service
@Transactional
public class RicercaElaborazionePrecedenteManagerImpl implements RicercaElaborazionePrecedenteManager {

    final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    RicercaElaborazionePrecedenteRepository ricercaElaborazionePrecedenteRepository;

    @Autowired
    EnteRepository enteRepository;

    @Autowired
    ConfigurazioneManager configurazioneManager;

    @Override
    public List<DTOOutputRicercaElaborazionePrecedente> ricercaElaborazionePrecedente ( DTOInputWsRicercaElaborazionePrecedente input ) {
        logger.info ( "RicercaElaborazionePrecedenteManagerImpl.ricercaElaborazionePrecedente: INIZIO" );
        List<DTOOutputRicercaElaborazionePrecedente> listaOutputRicercaElaborazionePrecedente = new ArrayList<DTOOutputRicercaElaborazionePrecedente> ();
        Page<CblTElaborazione> listaRicercaElaborazionePrecedente = null;
//        String limitElabPrecedenti = "";
        CblTEnte ente = new CblTEnte ();
        String idEnte = "";
        //HO IL CAMPO codiceFiscaleEnte NASCOSTO MA VALORIZZATO ( ENTE DA LOGIN).DA LI RICAVO IDENTE
        String codiceFiscaleEnte = input.getCodiceFiscaleEnte ();
        Date dataInizio = input.getDataInizio ();
        Date dataFine = input.getDataFine ();
        String utenteInserimento = input.getCaller ().getPrincipal ().getCodiceFiscale ();
        String statoElaborazione = input.getStatoElaborazione ();
        if ( !"".equals ( codiceFiscaleEnte ) || codiceFiscaleEnte != null ) {
            ente = enteRepository.findByCodiceFiscale ( codiceFiscaleEnte );
            idEnte = ente.getIdEnte ();
        }
        Pageable pageable = new PageRequest ( 0, 1000, new Sort ( Direction.DESC, Costanti.NOME_CAMPO_DATAELABORAZIONE ) );
        java.sql.Timestamp dataBegin = null;
        java.sql.Timestamp dataEnd = null;
        //Trasformazione date in timestamp
        try {
            if ( dataInizio != null )
                dataBegin = new Timestamp ( dataInizio.getTime () );
            if ( dataFine != null ) {
                Calendar cal = Calendar.getInstance ();
                cal.setTime ( dataFine );
                cal.set ( Calendar.HOUR_OF_DAY, 23 );
                cal.set ( Calendar.MINUTE, 59 );
                dataEnd = new Timestamp ( cal.getTimeInMillis () );
            }
        } catch ( Exception e ) {
            logger.warn ( "Errore in fase di conversione delle date. Ente: " + codiceFiscaleEnte, e );
        }
        //#### NB:Al login ho sempre il codiceFiscale ente,utenteInserimento sempre valorizzati ,ricavo idEnte dal codiceFiscale ente ##########

        //Ricerca con  'idEnte'e 'utenteInserimento' Click diretto sul pulsante cerca senza valorizzare nessuno campo
        if ( !"".equals ( utenteInserimento ) && !"".equals ( idEnte ) && "".equals ( statoElaborazione ) && null == dataInizio && null == dataFine ) {
            listaRicercaElaborazionePrecedente
            = ricercaElaborazionePrecedenteRepository.findByCblTEnteIdEnte ( idEnte, pageable );
        }

        //Ricerca con idEnte, utenteInserimento,  dataInizio e dataFine
        if ( !"".equals ( utenteInserimento ) && "".equals ( statoElaborazione ) && dataInizio != null && dataFine != null && !"".equals ( idEnte ) ) {
            pageable = new PageRequest ( 0, 1000, new Sort ( Direction.DESC, "dataElaborazione" ) );
            listaRicercaElaborazionePrecedente
            = ricercaElaborazionePrecedenteRepository.findByCblTEnteIdEnteAndDataInizioBetween ( idEnte, dataBegin, dataEnd, pageable );
        }

        //Ricerca per statoElaborazione (idEnte utenteInserimento che sempre valorizzato dal login)
        if ( !"".equals ( utenteInserimento ) && !"".equals ( statoElaborazione ) && dataInizio == null && dataFine == null && !"".equals ( idEnte ) ) {
            pageable = new PageRequest ( 0, 1000, new Sort ( Direction.DESC, "dataElaborazione" ) );
            listaRicercaElaborazionePrecedente
            = ricercaElaborazionePrecedenteRepository.findByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStatoIgnoreCase ( idEnte, statoElaborazione,
                pageable );
        }
        if ( listaRicercaElaborazionePrecedente != null ) {
            // ciclo per trasformazione in DTO e creazione lista di output
            for ( CblTElaborazione cblTElaborazione: listaRicercaElaborazionePrecedente ) {
                DTOOutputRicercaElaborazionePrecedente dtoOutputElabPrecedente = new DTOOutputRicercaElaborazionePrecedente ();
                dtoOutputElabPrecedente = ElaborazioneUtility.transformaCblTElaborazioneInDTOOutputRicercaElab ( cblTElaborazione );
                listaOutputRicercaElaborazionePrecedente.add ( dtoOutputElabPrecedente );
            }

        }
        logger.info ( "### La lista OutputRicercaElaborazionePrecedente contiene  " + "" + listaOutputRicercaElaborazionePrecedente.size () + " " + "elementi"
                        + " " + "#####" );
        logger.info ( "RicercaElaborazionePrecedenteManagerImpl.ricercaElaborazionePrecedente: INIZIO" );
        return listaOutputRicercaElaborazionePrecedente;
    }
}
