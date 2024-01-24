/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayweb.frontend.models.IdentitaShibboleth;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/private")
public class PrivateController extends _Controller {

    private final static String VIEWHOME = "private/home/home";

    @Value("${url.logout.shibboleth}")
    private String VIEWLOGOUT;

    @Autowired
    private PagamentoFacade pagamentoFacade;

    @RequestMapping(value = { "/home" })
    public String home(HttpSession session, Model model) {
        // String methodName = "home";
        System.out.println("Call to PrivateController.home");

        IdentitaShibboleth identitaShibboleth = (IdentitaShibboleth)session.getAttribute("identitaShibboleth");
        if ( identitaShibboleth != null && StringUtils.isNoneBlank ( identitaShibboleth.getCodiceFiscale () ) ) {
            List<Pagamento> elencoPagamenti = pagamentoFacade.ricercaPagamentiAttiviNonSpontaneiPerCF(identitaShibboleth.getCodiceFiscale());
			elencoPagamenti = elencoPagamenti.stream().filter(filtraPagamentiNonMarcaBollo()).collect(
							Collectors.toCollection ( new Supplier<LinkedList<Pagamento>> () {
								@Override
								public LinkedList<Pagamento> get () {
									return new LinkedList<> ();
								}
							}));
            model.addAttribute("numeroPagamenti", elencoPagamenti.size());
        } else {
            throw new RuntimeException("Errore durante autenticazione.");
        }

        model.addAttribute("commonData", initCommonData(null, 0, Boolean.FALSE, Boolean.FALSE, null, session));
        return VIEWHOME;
    }

    @RequestMapping(value = { "/logout" })
    public String logout(HttpSession session, Model model) {
        // String methodName = "logout";
        System.out.println("Call to PrivateController.logout");
        session.invalidate();

        return redirect(VIEWLOGOUT);
        /*
https://secure.sistemapiemonte.it/ssp_liv1_spid_GASP_REGIONE/logout.do
https://secure.sistemapiemonte.it/ssp_liv2_spid_GASP_REGIONE/logout.do
https://secure.sistemapiemonte.it/ssp_liv1_sisp_liv1_spid_GASP_REGIONE/logout.do
https://secure.sistemapiemonte.it/ssp_liv2_sisp_liv2_spid_GASP_REGIONE/logout.do
https://secure.sistemapiemonte.it/ssp_liv3_sisp_liv2_spid_GASP_REGIONE/logout.do
         */
	}

    @RequestMapping(value = "/pagaConIuv", method = RequestMethod.GET)
    public String pagaConIuv() {
        return redirect("pagaConIuv/");
    }

    @RequestMapping ( value = "/pagaAltroRiferimentoConIuv", method = RequestMethod.GET )
    public String pagaAltroRiferimentoConIuv () {
        return redirect ( "pagaAltroRiferimentoConIuv/" );
    }

    @RequestMapping(value = "/pagaSenzaIuv", method = RequestMethod.GET)
    public String pagaSenzaIuv() {
        return redirect("pagaSenzaIuv/");
    }

    @RequestMapping(value = "/verifica", method = RequestMethod.GET)
    public String verifica(HttpSession session) {
        return redirect("verifica/");
    }

    @RequestMapping(value = "/stampaAvviso", method = RequestMethod.GET)
    public String stampa(HttpSession httpSession) {
        return redirect("stampaAvviso/");
    }

}
