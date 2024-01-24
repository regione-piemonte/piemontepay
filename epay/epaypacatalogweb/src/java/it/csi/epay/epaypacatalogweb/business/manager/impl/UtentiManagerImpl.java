/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.UtentiManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.GestioneUtentiFacadeMockImpl;
import it.csi.epay.epaypacatalogweb.integration.mapper.UtentiMapper;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaUtenteOutputDto;
import it.csi.epay.epaypacatalogweb.model.utente.ModificaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.RicercaUtenteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.utente.RisultatoRicercaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;
import it.csi.epay.epaypacatalogweb.vo.UserVO;


@Service
public class UtentiManagerImpl implements UtentiManager {

    private final static String CODICE_OK = "OK";

    @Autowired
    private EpaypacatalogsrvFacade facade;

    private List<UserVO> list;

    public UtentiManagerImpl () {

        list = GestioneUtentiFacadeMockImpl.getAll ();

    }

    @Override
    public List<UserVO> ricerca () {
        return list;
    }

    @Override
    public UserVO get ( int id ) {
        return list.get ( id );
    }

    @Override
    public void modifica ( UserVO UserVO ) {

        UserVO user = list.get ( UserVO.getId () - 1 );

        user.setSsnNumber ( UserVO.getSsnNumber () );
        user.setName ( UserVO.getName () );
        user.setEmail ( UserVO.getEmail () );
        user.setStartDate ( UserVO.getStartDate () );
        user.setExpirationDate ( UserVO.getExpirationDate () );

    }

    @Override
    public void aggiungi ( UserVO userVO ) {
        if ( userVO.getId () == 0 ) {
            UserVO user = list.get ( list.size () - 1 );
            int id = user.getId ();
            userVO.setId ( ++id );
            //            user.add ( userVO );
            list.add ( userVO );
            //call to remote method
        }

    }

    @Override
    public boolean elimina ( int userId ) {

        UserVO user = get ( userId - 1 );

        if ( user.getId () == userId ) {
            list.remove ( user );
            return true;
        }
        return false;
    }

    @Override
    public void save () {
        // TODO Auto-generated method stub

    }

    @Override
    public List<UserVO> search ( UserVO userVO ) {

        String ssn = userVO.getSsnNumber ();
        String name = userVO.getName ().toLowerCase ().trim ();
        boolean active = userVO.isActive ();

        List<UserVO> temp = new ArrayList<> ();

        if ( ( ssn != null && !ssn.isEmpty () ) || ( name != null && !name.isEmpty () ) || ( active != false ) ) {

            for ( UserVO user: list ) {
                System.out.println ( user.isActive () );
                if ( ( user.getSsnNumber ().equals ( ssn ) )
                    || ( user.getName ().toLowerCase ().contains ( name ) && !name.equals ( "" ) )
                    || ( user.isActive () == active ) ) {

                    temp.add ( user );

                }

            }

        }
        return temp;

    }

    @Override
    public List<RisultatoRicercaUtenteVO> ricerca ( RicercaUtenteFiltroVO filtro ) throws OperationFailedException {

        RicercaUtenteInput ricercaUtenteInput = UtentiMapper.map ( filtro );
        RicercaUtenteOutput serviceOutput;
        try {
            serviceOutput = facade.ricercaUtente ( ricercaUtenteInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

        List<RisultatoRicercaUtenteVO> output = new ArrayList<> ();

        for ( RicercaUtenteOutputDto serviceOutputDto: serviceOutput.getRisultati () ) {
            RisultatoRicercaUtenteVO mappedDto = UtentiMapper.map ( serviceOutputDto );
            output.add ( mappedDto );
        }

        return output;
    }

    @Override
    public UtenteVO get ( Long id ) throws OperationFailedException {

        GetUtenteInput getUtenteInput = new GetUtenteInput ();
        getUtenteInput.setId ( id );

        GetUtenteOutput serviceOutput;
        try {
            serviceOutput = facade.getUtente ( getUtenteInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

        UtenteVO o = UtentiMapper.map ( serviceOutput.getUtente () );
        return o;
    }

    @Override
    public void inserisci ( ModificaUtenteVO userInput ) throws OperationFailedException {

        InserisciUtenteInput serviceInput = UtentiMapper.mapInserisci ( userInput );

        InserisciUtenteOutput serviceOutput;
        try {

            serviceOutput = facade.inserisciUtente ( serviceInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

        return;
    }

    @Override
    public void aggiorna ( ModificaUtenteVO userInput ) throws OperationFailedException {

        AggiornaUtenteInput serviceInput = UtentiMapper.mapAggiorna ( userInput );

        AggiornaUtenteOutput serviceOutput;
        try {

            serviceOutput = facade.aggiornaUtente ( serviceInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }
    }

    @Override
    public void elimina ( Long id ) throws OperationFailedException {

        EliminaUtenteInput serviceInput = new EliminaUtenteInput ();
        serviceInput.setId ( id );

        EliminaUtenteOutput serviceOutput;
        try {

            serviceOutput = facade.eliminaUtente ( serviceInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

        return;
    }

    @Override
    public UtenteVO merge ( Long id, ModificaUtenteVO input ) throws OperationFailedException {

        UtenteVO o = id == null ? istanzia () : get ( id );

        o.setCodiceFiscale ( input.getCodiceFiscale () );
        o.setCognome ( input.getCognome () );
        o.setDataFineValidita ( input.getDataFineValidita () );
        o.setDataInizioValidita ( input.getDataInizioValidita () );
        o.setEmail ( input.getEmail () );
        o.setNome ( input.getNome () );

        return o;
    }

    @Override
    public UtenteVO istanzia () {
        UtenteVO o = new UtenteVO ();

        return o;
    }
}
