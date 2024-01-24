/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import it.csi.epay.epayservices.interfaces.ejb.EnteFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.TipoPagamentoLight;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/ajax")
public class AjaxController extends _Controller{

	@Autowired
	private PagamentoFacade pagamentoFacade;
	
    @Autowired
    private EnteFacade enteFacade;

	@RequestMapping(value = "/tasse", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TipoPagamentoLight>  riferimentiTasse(@RequestParam Long idEnte) {
		final String methodName = "riferimentiTasse";
		log.debugStart(methodName);
		
		Ente ente = new Ente();
		ente.setIdEnte(idEnte);
//		List<TipoPagamentoLight> listaTasse = pagamentoFacade.elencoTipoPagamentoPerEnte(ente);
		List<TipoPagamentoLight> listaTasse = pagamentoFacade.elencoTipoPagamentoVisibiliPerEnte(ente);
		listaTasse = listaTasse.stream().filter(eliminazioneTipoAfferenteMarca()).collect(Collectors.toCollection ( new Supplier<LinkedList<TipoPagamentoLight>> () {
            @Override
            public LinkedList<TipoPagamentoLight> get () {
               return new LinkedList<> ();
            }
        }));
		log.debugEnd(methodName);
		return listaTasse;
  	}

	private Predicate<? super TipoPagamentoLight> eliminazioneTipoAfferenteMarca() {
		return new Predicate<TipoPagamentoLight> () {

            @Override
            public boolean test ( TipoPagamentoLight t ) {
                return (t.getTipologiaPagamento() == null || 
                		(t.getTipologiaPagamento() != null && !"MABL".equalsIgnoreCase(t.getTipologiaPagamento().getCodice()) 
                			&&  !"PABL".equalsIgnoreCase(t.getTipologiaPagamento().getCodice()) 
                		));
            }
        };
	}

    @RequestMapping ( value = "/listaEnti", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody List<Ente> listaEnti ( @RequestParam String nomeEnte ) {
        final String methodName = "listaEnti";
        log.debugStart ( methodName );

//        List<Ente> listaEnti = enteFacade.listaEntiConTasse ( nomeEnte );
        List<Ente> listaEnti = enteFacade.getListaEntiConTasseVisibili (nomeEnte  );
        log.debugEnd ( methodName );
        return listaEnti;
    }

    @RequestMapping ( value = "/pagamentiSpontanei", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody List<Ente> pagamentiSpontanei () {
        final String methodName = "listaEnti";
        log.debugStart ( methodName );

        List<Ente> listaEnti = enteFacade.listaEntiConPagamentiSpontanei ();
        
//        List<Ente> listaEnti = enteFacade.listaEntiConPagamentiSpontaneiVisibili ();
        log.debugEnd ( methodName );
        return listaEnti;
    }
	
    @RequestMapping ( value = "/pagamentiConIuv", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody List<Ente> pagamentiConIuv () {
        final String methodName = "listaEnti";
        log.debugStart ( methodName );

        List<Ente> listaEnti = enteFacade.listaEntiConPagamentiConIuvPagabili ();
        log.debugEnd ( methodName );
        return listaEnti;
    }
}
