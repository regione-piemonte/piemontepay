/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalCodiceVersamentoVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalTematicaVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


public abstract class SecurityUtils {

    public static void impersonateIfAnonymous ( PrincipalVO user ) {
        PrincipalVO currentPrincipal = getPrincipalIfPresent ();
        if ( currentPrincipal == null ) {
            impersonate ( user );
        }
    }

    public static void impersonate ( PrincipalVO user ) {
        setPrincipal ( user );
    }

    public static void setPrincipal ( PrincipalVO principal ) {

        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes () ).getRequest ();
        if ( request == null ) {
            throw new RuntimeException ( "richiesta non trovata" );
        } else {
            request.setAttribute ( Constants.REQUEST_ATTRIBUTE_PRINCIPAL, principal );
        }
    }

    public static PrincipalVO getPrincipal () {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes () ).getRequest ();
        if ( request == null ) {
            throw new RuntimeException ( "richiesta non trovata" );
        } else {
            return (PrincipalVO) request.getAttribute ( Constants.REQUEST_ATTRIBUTE_PRINCIPAL );
        }
    }

    public static PrincipalVO getPrincipalIfPresent () {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes () ).getRequest ();
        if ( request == null ) {
            return null;
        } else {
            return (PrincipalVO) request.getAttribute ( Constants.REQUEST_ATTRIBUTE_PRINCIPAL );
        }
    }

    public static void assertValidPrincipal () {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null || !principal.getUtente ().isValid () ) {
            throw new NotAuthorizedException ();
        }

    }

    public static void assertValidFruitore () {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null || !principal.getFruitore ().isValid () ) {
            throw new NotAuthorizedException ();
        }

    }

    public static void assertUseCase ( String uc ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null || !principal.getUtente ().isValid () ) {
            throw new NotAuthorizedException ();
        } else {
            if ( !principal.hasUseCase ( uc ) ) {
                throw new NotAuthorizedException ();
            }
        }

    }

    public static boolean hasUseCase ( String uc ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null || !principal.getUtente ().isValid () ) {
            return false;
        } else {
            if ( !principal.hasUseCase ( uc ) ) {
                return false;
            }
        }

        return true;
    }

    public static void assertAnyUseCase ( String... ucs ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null || !principal.getUtente ().isValid () ) {
            throw new NotAuthorizedException ();
        } else {
            for ( String uc: ucs ) {
                if ( principal.hasUseCase ( uc ) ) {
                    return;
                }
            }
            throw new NotAuthorizedException ();
        }

    }

    public static void assertScope ( String scope ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null || !principal.getFruitore ().isValid () ) {
            throw new NotAuthorizedException ();
        } else {
            if ( !principal.hasScope ( scope ) ) {
                throw new NotAuthorizedException ();
            }
        }
    }

    public static void assertAnyScope ( String... scopes ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null || !principal.getFruitore ().isValid () ) {
            throw new NotAuthorizedException ();
        } else {
            for ( String scope: scopes ) {
                if ( principal.hasScope ( scope ) ) {
                    return;
                }
            }
            throw new NotAuthorizedException ();
        }

    }

    public static Long getCurrentIdEnte () {
        PrincipalVO principal = getPrincipalIfPresent ();
        if ( principal != null && principal.getEnte () != null ) {
            return principal.getEnte ().getId ();
        }
        return null;
    }

    public static String getCurrentCodiceFiscaleEnte () {
        PrincipalVO principal = getPrincipalIfPresent ();
        if ( principal != null && principal.getEnte () != null ) {
            return principal.getEnte ().getCodiceFiscale ();
        }
        return null;
    }

    public static void assertVisibilitaTotaleSuTematica ( String codice ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null ) {
            throw new NotAuthorizedException ();
        } else {
            PrincipalTematicaVO assoc = principal.cercaAssociazioneTematica ( codice );
            if ( assoc == null ) {
                throw new NotAuthorizedException ( Constants.MESSAGES.NESSUNA_VISIBILITA_GARANTITA_SU_TEMATICA );
            }
            if ( !EntityUtils.isTrue ( assoc.getFlagVisibilitaTotale () ) ) {
                throw new NotAuthorizedException ( Constants.MESSAGES.VISIBILITA_TOTALE_NON_GARANTITA );
            }
        }
    }

    public static void assertVisibilitaParzialeSuTematica ( String codice ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null ) {
            throw new NotAuthorizedException ();
        } else {
            PrincipalTematicaVO assoc = principal.cercaAssociazioneTematica ( codice );
            if ( assoc == null ) {
                throw new NotAuthorizedException ( Constants.MESSAGES.NESSUNA_VISIBILITA_GARANTITA_SU_TEMATICA );
            }
        }
    }

    public static void assertVisibilitaSuCodiceVersamento ( Long idCodiceVersamento, String codiceTematica ) {

        PrincipalVO principal = getPrincipal ();
        if ( principal == null ) {
            throw new NotAuthorizedException ();
        } else {
            PrincipalTematicaVO assoc = principal.cercaAssociazioneTematica ( codiceTematica );
            if ( assoc == null ) {
                throw new NotAuthorizedException ( Constants.MESSAGES.NESSUNA_VISIBILITA_GARANTITA_SU_TEMATICA );
            }

            if ( !EntityUtils.isTrue ( assoc.getFlagVisibilitaTotale () ) ) {
                if ( assoc.getCodiciVersamento () != null ) {
                    for ( PrincipalCodiceVersamentoVO cva: assoc.getCodiciVersamento () ) {
                        if ( cva.getId ().equals ( idCodiceVersamento ) ) {
                            return;
                        }
                    }
                }
                throw new NotAuthorizedException ( Constants.MESSAGES.VISIBILITA_CODICE_VERSAMENTO_NON_GARANTITA );
            }

        }
    }

    public static void assertVisibilitaSuCodiceVersamento ( CodiceVersamento cv ) {
        assertVisibilitaSuCodiceVersamento ( cv.getId (), cv.getVoceEntrata ().getTematica ().getCodice () );
    }

    public static void assertAmministratoreEnteCorrente () {
        PrincipalVO principal = getPrincipal ();

        if ( hasUseCase ( Constants.USE_CASES.AMMINISTRATORE_ENTE ) ) {
            return;
        }

        boolean found = false;
        for(Long admin:principal.getEnte ().getAdmins ()) {
            if (admin.equals ( principal.getUtente ().getId () ) ) {
                found = true;
            }
        }
        if(!found) {
            throw new NotAuthorizedException ( Constants.MESSAGES.USER_NOT_ADMIN_OF_ENTE );
        }
    }
}
