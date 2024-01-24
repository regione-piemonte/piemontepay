/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.mapper;

import java.util.ArrayList;

import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteAssociazioneCduOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteAssociazioneCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteAssociazioneTematicaOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaUtenteOutputDto;
import it.csi.epay.epaypacatalogweb.model.utente.AssociazioneUtenteCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.AssociazioneUtenteCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.utente.AssociazioneUtenteTematicaVO;
import it.csi.epay.epaypacatalogweb.model.utente.ModificaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.RicercaUtenteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.utente.RisultatoRicercaUtenteVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;


public abstract class UtentiMapper extends ParentMapper {

    public static RicercaUtenteInput map ( RicercaUtenteFiltroVO input ) {
        RicercaUtenteInput mapped = new RicercaUtenteInput ();

        mapped.setCodiceFiscale ( input.getCodiceFiscale () );
        mapped.setCognome ( input.getCognome () );
        mapped.setNome ( input.getNome () );
        mapped.setSoloUtentiInVita ( input.getSoloUtentiInVita () );
        mapped.setEmail ( input.getEmail () );
        mapped.setCodiceCategoriaCdu ( input.getCodiceCategoriaCdu () );
        mapped.setCodiceCdu ( input.getCodiceCdu () );
        mapped.setIdCodiceVersamento ( input.getIdCodiceVersamento () );
        mapped.setCodiceTematica ( input.getCodiceTematica () );

        return mapped;
    }

    public static RisultatoRicercaUtenteVO map ( RicercaUtenteOutputDto input ) {
        RisultatoRicercaUtenteVO mapped = new RisultatoRicercaUtenteVO ();

        mapped.setId ( input.getId () );

        mapped.setCodiceFiscale ( input.getCodiceFiscale () );
        mapped.setCognome ( input.getCognome () );
        mapped.setNome ( input.getNome () );
        mapped.setEmail ( input.getEmail () );

        mapped.setDataInizioValidita ( calendarToDate ( input.getDataInizioValidita () ) );
        mapped.setDataFineValidita ( calendarToDate ( input.getDataFineValidita () ) );

        //FIX A FRONTE RIGENERAZIONE STUB
        mapped.setAdmin ( input.isAdmin () );

        return mapped;
    }

    public static UtenteVO map ( GetUtenteOutputDto input ) {
        UtenteVO mapped = new UtenteVO ();

        mapped.setId ( input.getId () );

        mapped.setCodiceFiscale ( input.getCodiceFiscale () );
        mapped.setCognome ( input.getCognome () );
        mapped.setNome ( input.getNome () );
        mapped.setEmail ( input.getEmail () );

        mapped.setDataInizioValidita ( calendarToDate ( input.getDataInizioValidita () ) );
        mapped.setDataFineValidita ( calendarToDate ( input.getDataFineValidita () ) );

        mapped.setCdu ( new ArrayList<> () );
        if ( input.getCdu () != null ) {
            for ( GetUtenteAssociazioneCduOutputDto inputNested: input.getCdu () ) {
                mapped.getCdu ().add ( map ( inputNested ) );
            }
        }

        mapped.setTematiche ( new ArrayList<> () );
        if ( input.getTematiche () != null ) {
            for ( GetUtenteAssociazioneTematicaOutputDto inputNested: input.getTematiche () ) {
                mapped.getTematiche ().add ( map ( inputNested ) );
            }
        }

        return mapped;
    }

    public static AssociazioneUtenteTematicaVO map ( GetUtenteAssociazioneTematicaOutputDto input ) {
        AssociazioneUtenteTematicaVO mapped = new AssociazioneUtenteTematicaVO ();

        mapped.setId ( input.getId () );
        mapped.setCodice ( input.getCodice () );
        mapped.setDescrizione ( input.getDescrizione () );
        mapped.setFlagVisibilitaTotale ( input.isFlagVisibilitaTotale () );

        mapped.setCodiciVersamento ( new ArrayList<> () );
        if ( input.getCodiciVersamento () != null ) {
            for ( GetUtenteAssociazioneCodiceVersamentoOutputDto inputNested: input.getCodiciVersamento () ) {
                mapped.getCodiciVersamento ().add ( map ( inputNested ) );
            }
        }

        return mapped;
    }

    public static AssociazioneUtenteCodiceVersamentoVO map ( GetUtenteAssociazioneCodiceVersamentoOutputDto input ) {
        AssociazioneUtenteCodiceVersamentoVO mapped = new AssociazioneUtenteCodiceVersamentoVO ();

        mapped.setId ( input.getId () );
        mapped.setCodice ( input.getCodice () );
        mapped.setDescrizione ( input.getDescrizione () );

        return mapped;
    }

    public static AssociazioneUtenteCduVO map ( GetUtenteAssociazioneCduOutputDto input ) {
        AssociazioneUtenteCduVO mapped = new AssociazioneUtenteCduVO ();

        mapped.setId ( input.getId () );
        mapped.setCodice ( input.getCodice () );
        mapped.setDescrizione ( input.getDescrizione () );
        mapped.setCodiceCategoria ( input.getCodiceCategoria () );
        mapped.setDescrizioneCategoria ( input.getDescrizioneCategoria () );

        return mapped;
    }

    public static InserisciUtenteInput mapInserisci ( ModificaUtenteVO input ) {
        InserisciUtenteInput mapped = new InserisciUtenteInput ();

        mapped.setCodiceFiscale ( input.getCodiceFiscale () );
        mapped.setCognome ( input.getCognome () );
        mapped.setNome ( input.getNome () );
        mapped.setEmail ( input.getEmail () );

        mapped.setDataInizioValidita ( dateToCalendar ( input.getDataInizioValidita () ) );
        mapped.setDataFineValidita ( dateToCalendar ( input.getDataFineValidita () ) );

        return mapped;
    }

    public static AggiornaUtenteInput mapAggiorna ( ModificaUtenteVO input ) {
        AggiornaUtenteInput mapped = new AggiornaUtenteInput ();
        mapped.setId ( input.getId () );

        mapped.setCodiceFiscale ( input.getCodiceFiscale () );
        mapped.setCognome ( input.getCognome () );
        mapped.setNome ( input.getNome () );
        mapped.setEmail ( input.getEmail () );

        mapped.setDataInizioValidita ( dateToCalendar ( input.getDataInizioValidita () ) );
        mapped.setDataFineValidita ( dateToCalendar ( input.getDataFineValidita () ) );

        return mapped;
    }

}
