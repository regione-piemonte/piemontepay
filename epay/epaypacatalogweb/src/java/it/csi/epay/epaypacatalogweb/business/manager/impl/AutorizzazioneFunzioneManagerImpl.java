/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.AutorizzazioneFunzioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCduUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCduUtenteOutput;
import it.csi.epay.epaypacatalogweb.model.utente.AssociazioneUtenteCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteCduCategoriaCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteCduCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioniAssociazioneUtenteCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;
import it.csi.epay.epaypacatalogweb.vo.FunzioneVO;


@Service
public class AutorizzazioneFunzioneManagerImpl implements AutorizzazioneFunzioneManager {

    private final static String CODICE_OK = "OK";

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private EpaypacatalogsrvFacade service;

    @Override
    public List<FunzioneVO> getCduPossibili () {
        OpzioniAssociazioneUtenteCduVO opzioni = decodificheManager.getListaOpzioniAssociazioneUtenteCdu ();
        List<FunzioneVO> output = new ArrayList<> ();

        for ( OpzioneAssociazioneUtenteCduCategoriaCduVO categoria: opzioni.getCategorie () ) {
            if ( categoria.getCdu () != null ) {
                for ( OpzioneAssociazioneUtenteCduCduVO cdu: categoria.getCdu () ) {
                    FunzioneVO vo = new FunzioneVO ();
                    vo.setId ( cdu.getId () );
                    vo.setCodice ( cdu.getCodice () );
                    vo.setDescrizione ( cdu.getDescrizione () );
                    vo.setCodiceCategoria ( categoria.getCodice () );
                    vo.setDescrizioneCategoria ( categoria.getDescrizione () );
                    output.add ( vo );
                }
            }
        }

        return output;
    }

    @Override
    public List<FunzioneVO> getCduNonAssegnati ( UtenteVO utente, List<FunzioneVO> tutteLeFunzioni ) {
        if ( utente.getCdu () == null || utente.getCdu ().size () < 1 ) {
            return tutteLeFunzioni;
        }

        List<FunzioneVO> output = new ArrayList<> ();

        for ( FunzioneVO funzione: tutteLeFunzioni ) {
            boolean presente = false;
            for ( AssociazioneUtenteCduVO cduUtente: utente.getCdu () ) {
                if ( cduUtente.getCodice ().equals ( funzione.getCodice () ) ) {
                    presente = true;
                    break;
                }
            }

            if ( !presente ) {
                output.add ( funzione );
            }
        }

        return output;
    }

    @Override
    public List<FunzioneVO> getCduAssegnati ( UtenteVO utente, List<FunzioneVO> tutteLeFunzioni ) {
        List<FunzioneVO> output = new ArrayList<> ();

        for ( FunzioneVO funzione: tutteLeFunzioni ) {
            boolean presente = false;
            for ( AssociazioneUtenteCduVO cduUtente: utente.getCdu () ) {
                if ( cduUtente.getCodice ().equals ( funzione.getCodice () ) ) {
                    presente = true;
                    break;
                }
            }

            if ( presente ) {
                output.add ( funzione );
            }
        }

        return output;
    }

    @Override
    public void salvaAssociazioneCdu ( Long idUtente, String [] ids ) throws OperationFailedException {

        AggiornaCduUtenteInput aggiornaCduUtenteInput = new AggiornaCduUtenteInput ();

        aggiornaCduUtenteInput.setId ( idUtente );
        aggiornaCduUtenteInput.getCodiciCdu ().clear ();
        if ( ids != null ) {
            for ( String str: ids ) {
                aggiornaCduUtenteInput.getCodiciCdu ().add ( str );
            }
        }

        AggiornaCduUtenteOutput rawOutput;
        try {
            rawOutput = service.aggiornaCduUtente ( aggiornaCduUtenteInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( rawOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( rawOutput.getDescrizioneEsito () );
        }
    }

}
