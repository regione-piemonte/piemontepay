/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.frontend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

public abstract class AuthenticatedParentController extends GenericParentController {

    public static final String FORM_FILTER = "filter";

    public static final String MODEL_TEMATICHE = "modelTematiche";

    public static final String MODEL_MACROTIPI = "modelMacrotipi";

    public static final String MODEL_MESSAGGIO_ERRORE = "errorMessage";
    public static final String MODEL_MESSAGGIO_INFO = "infoMessage";

    public abstract void loadComboboxes ( Model model );

    protected RedirectView safeRedirect(String url) {
        RedirectView rv = new RedirectView(url);
        rv.setExposeModelAttributes(false);
        return rv;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getFromSession(HttpServletRequest request, String key, Class<?> T) {
        Object value = request.getSession().getAttribute(key);

        if (value == null || T == null || !T.equals(value.getClass()))
        {
            value = null;
        }

        return (T) value;
    }
}
