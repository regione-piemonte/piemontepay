/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import it.csi.epay.epaypacatalogweb.business.manager.AutorizzazioneCodiceVersamentoManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaTematicheUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaTematicheUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaTematicheUtenteTematicaInputDto;
import it.csi.epay.epaypacatalogweb.model.utente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class AutorizzazioneCodiceVersamentoManagerImpl implements AutorizzazioneCodiceVersamentoManager {

    private final static String CODICE_OK = "OK";

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private EpaypacatalogsrvFacade service;

    @Override
    public void popolaSelezioneTematichePerUtente (
        UtenteVO utente, List<AssociazioneUtenteTematicaVO> alberoTematiche, ModificaAssociazioniUtenteTematicaVO inputUtente ) {

        if ( utente == null || alberoTematiche == null ) {
            throw new java.security.InvalidParameterException ();
        }

        for ( AssociazioneUtenteTematicaVO tematica: alberoTematiche ) {
            verificaAssociazioneTematica ( utente, tematica);
        }
    }

    private void verificaAssociazioneTematica ( UtenteVO utente, AssociazioneUtenteTematicaVO tematica ) {

        tematica.setFlagVisibilitaTotale ( false );

        if ( utente.getTematiche () != null ) {
            for ( AssociazioneUtenteTematicaVO tematicaUtente: utente.getTematiche () ) {
                if ( tematicaUtente.getCodice ().equals ( tematica.getCodice () ) ) {
                    tematica.setFlagVisibilitaTotale ( tematicaUtente.getFlagVisibilitaTotale () );

                    if ( tematica.getFlagVisibilitaTotale () ) {
                        if ( tematica.getCodiciVersamento () != null ) {
                            for ( AssociazioneUtenteCodiceVersamentoVO codice: tematica.getCodiciVersamento () ) {
                                codice.setSelezionato ( false );
                            }
                        }
                    } else {
                        if ( tematica.getCodiciVersamento () != null ) {
                            for ( AssociazioneUtenteCodiceVersamentoVO codice: tematica.getCodiciVersamento () ) {
                                verificaAssociazioneCodiceVersamento (tematicaUtente, codice);
                            }
                        }
                    }

                    return;
                }
            }
        }

        if ( tematica.getCodiciVersamento () != null ) {
            for ( AssociazioneUtenteCodiceVersamentoVO codice: tematica.getCodiciVersamento () ) {
                codice.setSelezionato ( false );
            }
        }
    }

    private void verificaAssociazioneCodiceVersamento (
        AssociazioneUtenteTematicaVO tematicaUtente,
        AssociazioneUtenteCodiceVersamentoVO codiceVersamento ) {

        if ( tematicaUtente.getCodiciVersamento () != null ) {
            for ( AssociazioneUtenteCodiceVersamentoVO codiceUtente: tematicaUtente.getCodiciVersamento () ) {
                if ( codiceUtente.getId ().equals ( codiceVersamento.getId () ) ) {
                    codiceVersamento.setSelezionato ( true );
                    return;
                }
            }
        }

        codiceVersamento.setSelezionato ( false );
    }

    @Override
    public List<AssociazioneUtenteTematicaVO> getTematichePossibili () {
        OpzioniAssociazioneUtenteTematicaVO opzioni = decodificheManager.getListaOpzioniAssociazioneUtenteTematica ();
        List<AssociazioneUtenteTematicaVO> output = new ArrayList<> ();

        for ( OpzioneAssociazioneUtenteTematicaTematicaVO tematicaDto: opzioni.getTematiche () ) {
            AssociazioneUtenteTematicaVO tematicaVO = new AssociazioneUtenteTematicaVO ();

            tematicaVO.setId ( tematicaDto.getId () );
            tematicaVO.setCodice ( tematicaDto.getCodice () );
            tematicaVO.setDescrizione ( tematicaDto.getDescrizione () );
            tematicaVO.setFlagVisibilitaTotale ( false );
						List<AssociazioneUtenteCodiceVersamentoVO> codiciVersamento = new ArrayList<>();

            if ( tematicaDto.getCodiciVersamento () != null ) {
                for ( OpzioneAssociazioneUtenteTematicaCodiceVersamentoVO cdu: tematicaDto.getCodiciVersamento () ) {
                    AssociazioneUtenteCodiceVersamentoVO cvVO = new AssociazioneUtenteCodiceVersamentoVO ();
                    cvVO.setId ( cdu.getId () );
                    cvVO.setCodice ( cdu.getCodice () );
                    cvVO.setDescrizione ( cdu.getDescrizione () );
                    cvVO.setSelezionato ( false );
	                  codiciVersamento.add ( cvVO );
                }
            }
	          Collections.sort ( codiciVersamento );
	          tematicaVO.setCodiciVersamento ( codiciVersamento );

            output.add ( tematicaVO );
        }

        return output;
    }

    @Override
    public void aggiornaSelezioneTematiche ( Long idUtente, ModificaAssociazioniUtenteTematicaVO inputUtente ) throws OperationFailedException {

        AggiornaTematicheUtenteInput aggiornaTematicheUtenteInput = new AggiornaTematicheUtenteInput ();
        aggiornaTematicheUtenteInput.setId ( idUtente );

        for ( AssociazioneUtenteTematicaVO tematicaInput: inputUtente.getAssociazioni () ) {

            if ( tematicaInput.getFlagVisibilitaTotale () != null && tematicaInput.getFlagVisibilitaTotale () ) {

                AggiornaTematicheUtenteTematicaInputDto tematicaDto = new AggiornaTematicheUtenteTematicaInputDto ();
                tematicaDto.setCodice ( tematicaInput.getCodice () );
                tematicaDto.setFlagVisibilitaTotale ( true );
                aggiornaTematicheUtenteInput.getTematiche ().add ( tematicaDto );

            } else if ( tematicaInput.almenoUnCodiceVersamentoSelezionato () ) {

                AggiornaTematicheUtenteTematicaInputDto tematicaDto = new AggiornaTematicheUtenteTematicaInputDto ();
                tematicaDto.setCodice ( tematicaInput.getCodice () );
                tematicaDto.setFlagVisibilitaTotale ( false );
                aggiornaTematicheUtenteInput.getTematiche ().add ( tematicaDto );

                for ( AssociazioneUtenteCodiceVersamentoVO codiceInput: tematicaInput.getCodiciVersamento () ) {
                    if ( codiceInput.getSelezionato () != null && codiceInput.getSelezionato () ) {
                        tematicaDto.getIdCodiciVersamento ().add ( codiceInput.getId () );
                    }
                }
            }
        }

        AggiornaTematicheUtenteOutput rawOutput;

        try {
            rawOutput = service.aggiornaTematicheUtente ( aggiornaTematicheUtenteInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( rawOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( rawOutput.getDescrizioneEsito () );
        }
    }

}
