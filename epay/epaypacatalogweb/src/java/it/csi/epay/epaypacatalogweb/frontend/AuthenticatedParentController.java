/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.RedirectView;

public abstract class AuthenticatedParentController extends GenericParentController {

    public static final String FORM_FILTER = "filter";

	public static final String MODEL_TEMATICHE = "modelTematiche";

	public static final String MODEL_MACROTIPI = "modelMacrotipi";

    public static final String MODEL_TIPI_PAGAMENTO = "modelTipiPagamento";

	public static final String MODEL_MESSAGGIO_ERRORE = "errorMessage";
	public static final String MODEL_MESSAGGIO_INFO = "infoMessage";

    public static final String MODEL_SCENARIO = "scenario";

    public static final String MODEL_VOCI_ENTRATA = "modelVociEntrata";

    public static final String MODEL_CODICI_VERSAMENTO = "modelCodiciVersamento";

    public static final String MODEL_TIPOLOGIE_DATI_SINGOLA_RISCOSSIONE = "modelTipologieDatiSingolaRiscossione";

    public static final String MODEL_AS_JSON_POSTFIX = "_JS";

    public void loadComboboxes ( Model model ) {
        // NOP
    }

    protected HttpServletRequest getCurrentRequest () {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes () ).getRequest ();
        return request;
    }

    protected HttpSession getCurrentSession () {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes () ).getRequest ();
        return request != null ? request.getSession () : null;
    }

    protected String serializeForJavascript ( Object o ) {
        if ( o == null ) {
            return "null";
        }
        ObjectMapper mapper = new ObjectMapper ();

        String serialized;
        try {
            serialized = mapper.writeValueAsString ( o );
        } catch ( IOException e ) {
            throw new RuntimeException ( "errore nella costruzione dei dati Javascript", e );
        }
        if ( StringUtils.isBlank ( serialized ) ) {
            return "null";
        }
        return serialized;
    }

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
