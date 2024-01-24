/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.frontend;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import it.csi.epay.epaysimweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;


public class GenericParentController {

    public static final String SEARCH_BEAN = "searchBean";

    public static final String MODIFY_BEAN = "modifyBean";

    public void clearSession ( HttpSession session ) {
        session.setAttribute ( SEARCH_BEAN, null );
        session.setAttribute ( MODIFY_BEAN, null );
    }

    @ExceptionHandler ( { NotAllowedException.class } )
    public ModelAndView handleException ( HttpServletResponse response ) {
        response.setStatus ( HttpStatus.FORBIDDEN.value () );

        ModelAndView mav = new ModelAndView ();
        mav.addObject ( "message", "Accesso negato. L'utente non e' autorizzato ad eseguire l'operazione richiesta." );
        mav.setViewName ( "error" );
        return mav;
    }

    @ExceptionHandler ( { AccessDeniedException.class } )
    public ModelAndView handleExceptionAccessDenied ( HttpServletResponse response ) {
        response.setStatus ( HttpStatus.FORBIDDEN.value () );

        ModelAndView mav = new ModelAndView ();
        mav.setViewName ( "accessdenied" );
        return mav;
    }

    @ExceptionHandler ( { Exception.class } )
    public ModelAndView handleGenericException ( HttpServletResponse response, Exception e ) {
        response.setStatus ( HttpStatus.INTERNAL_SERVER_ERROR.value () );

        ModelAndView mav = new ModelAndView ();
        mav.addObject ( "message", "Si e' verificato un errore imprevisto. Si prega di contattare l'assistenza: " + e.getMessage () );
        mav.setViewName ( "error" );
        return mav;
    }

    @ExceptionHandler ( { OperationFailedException.class } )
    public ModelAndView handleOperationFailedException ( HttpServletResponse response, OperationFailedException e ) {
        response.setStatus ( HttpStatus.INTERNAL_SERVER_ERROR.value () );

        ModelAndView mav = new ModelAndView ();
        mav.addObject ( "message", "Si e' verificato un errore nell'esecuzione dell'operazione richiesta: " + e.getMessage () + "." );
        mav.setViewName ( "error" );
        return mav;
    }
}
