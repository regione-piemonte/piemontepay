/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.integration.facade.GestioneUtentiFacade;
import it.csi.epay.epaypacatalogweb.vo.AssociazioneTematicaVO;
import it.csi.epay.epaypacatalogweb.vo.FunzioneVO;
import it.csi.epay.epaypacatalogweb.vo.ThemeVO;
import it.csi.epay.epaypacatalogweb.vo.UserVO;

@Service
public class GestioneUtentiFacadeMockImpl implements GestioneUtentiFacade {

    /**
     * users mock data
     */
    static List<UserVO> users;

    public static List<UserVO> getAll () {

        users = new ArrayList<> ();

        users.add ( new UserVO ( 1, "123-456", "Vanessa", "super.vanesa@eng.it", formatDate ( 02, 03, 2015 ), formatDate ( 02, 03, 2015 ) ) );
        users.add ( new UserVO ( 2, "456-456", "Paolo", "super.paolo@eng.it", formatDate ( 22, 01, 2000 ), formatDate ( 02, 03, 2009 ) ) );
        users.add ( new UserVO ( 3, "789-456", "Nuru", "super.nuru@eng.it", formatDate ( 02, 03, 2011 ), formatDate ( 22, 12, 2000 ) ) );
        users.add ( new UserVO ( 4, "111-456", "Lorenzo", "super.lorenzo@eng.it", formatDate ( 02, 03, 2015 ), formatDate ( 25, 12, 2018 ) ) );
        users.add ( new UserVO ( 5, "111-456", "Fabio", "super.fabio@eng.it", formatDate ( 01, 03, 2015 ), formatDate ( 02, 03, 2015 ) ) );
        users.add ( new UserVO ( 6, "222-456", "Ana", "super.ana@eng.it", formatDate ( 02, 05, 2015 ), formatDate ( 12, 03, 2014 ) ) );
        users.add ( new UserVO ( 7, "222-456", "Milos", "super.milos@eng.it", formatDate ( 02, 03, 2015 ), formatDate ( 25, 06, 2015 ) ) );
        users.add ( new UserVO ( 8, "222-456", "Slobodan", "super.slobodan@eng.it", formatDate ( 02, 03, 2015 ), formatDate ( 18, 03, 2015 ) ) );

        return users;
    }

    /*
     * (non-Javadoc)
     * @see it.csi.epay.epaypacatalogweb.integration.facade.impl.GestioneUtentiFacade#getUser(int)
     */
    @Override
    public UserVO getUser ( int id ) {
        return users.get ( id );
    }

    public static Date formatDate ( int day, int month, int year ) {
        GregorianCalendar cal = new GregorianCalendar ( Locale.ITALY );
        cal.set ( year, month, day );

        return cal.getTime ();
    }

    static List<FunzioneVO> functions;

    public static List<FunzioneVO> getAllFunctions () {

        functions = new ArrayList<> ();

        return functions;
    }

    // AUTORIZZAZIONI CODICI VERSAMENTO / payment code authorizations

    static List<AssociazioneTematicaVO> depositCodes;

    public static List<AssociazioneTematicaVO> getAllPaymentDepositCodes () {

        depositCodes = new ArrayList<> ();

        ArrayList<ThemeVO> themes1 = new ArrayList<> ();
        themes1.add ( new ThemeVO ( 1, "Theme 1" ) );
        themes1.add ( new ThemeVO ( 2, "Theme 2" ) );
        themes1.add ( new ThemeVO ( 3, "Theme 3" ) );

        ArrayList<ThemeVO> themes2 = new ArrayList<> ();
        themes2.add ( new ThemeVO ( 4, "Theme 4" ) );
        themes2.add ( new ThemeVO ( 5, "Theme 5" ) );
        themes2.add ( new ThemeVO ( 6, "Theme 6" ) );
        //        themes1.get ( 0 ).setChecked ( true );

        ArrayList<ThemeVO> themes3 = new ArrayList<> ();
        themes3.add ( new ThemeVO ( 7, "Theme 7" ) );
        themes3.add ( new ThemeVO ( 8, "Theme 8" ) );
        themes3.add ( new ThemeVO ( 9, "Theme 9" ) );

        depositCodes.add ( new AssociazioneTematicaVO ( 1, "Deposit Code 1", themes1 ) );
        depositCodes.add ( new AssociazioneTematicaVO ( 2, "Deposit Code 2", themes2 ) );
        depositCodes.add ( new AssociazioneTematicaVO ( 3, "Deposit Code 3", themes3 ) );

        return depositCodes;
    }

    public static AssociazioneTematicaVO getDepositCode ( int depositeId ) {

        for ( AssociazioneTematicaVO depositCode: depositCodes ) {
            if ( depositCode.getId () == depositeId ) {

                return depositCode;

            }
        }

        return null;
    }

}
