/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.frontend.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */

public class MultipartExceptionResolver
implements HandlerExceptionResolver, Ordered {

    @Override
    public int getOrder () {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException (
        HttpServletRequest aReq, HttpServletResponse aRes,
        Object aHandler, Exception anExc ) {

        ModelAndView mav = null;

        if ( anExc instanceof MaxUploadSizeExceededException ) {
            anExc.printStackTrace ();
            mav = new ModelAndView ();
            mav.addObject ( "message", "Impossibile caricare il file. La dimensione massima consentita e' 2MB." );
            mav.setViewName ( "filesizeError" );
        }
        return mav;
    }
}
