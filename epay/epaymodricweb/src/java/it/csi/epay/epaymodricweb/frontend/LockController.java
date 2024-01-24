/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.frontend;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import it.csi.epay.epaymodricweb.business.manager.EnteManager;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.model.flusso.FlussoValidator;


@Controller
public class LockController extends AuthenticatedParentController {

    public final static String URL_BASE = "/lock";

    public final static String URL_RICERCA = URL_BASE + "/ricerca";

    public final static String URL_PULISCI = URL_BASE + "/pulisci";

    public final static String URL_ELIMINA = URL_BASE + "/elimina";

    public final static String VIEW_RICERCA = "lock/ricerca";

    public final static String VIEW_ELIMINA = VIEW_RICERCA;

    private static final String MODEL_OPZIONI_ENTE = "opzioni_ente";

    @Autowired
    private EnteManager enteManager;

    @InitBinder
    public void initBinder ( WebDataBinder binder ) {
        binder.setValidator ( new FlussoValidator () );

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        dateFormat.setLenient ( false );
        binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
    }

    public void loadComboboxesFromService ( Model model ) throws OperationFailedException {
        model.addAttribute ( MODEL_OPZIONI_ENTE, enteManager.getListaOpzioniEnte () );
    }


}
