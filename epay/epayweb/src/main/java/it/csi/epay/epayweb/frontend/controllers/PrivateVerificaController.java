/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.StatoPagamento;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.IdentitaShibboleth;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/private/verifica")
public class PrivateVerificaController extends _Controller {

    private final static String VIEWVERIFICA = "private/verifica";

    @Autowired
    private PagamentoFacade pagamentoFacade;

    //@Autowired
    //private RiferimentoComplexValidator riferimentoComplexValidator;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String verificaHome(HttpSession session) {
        String methodName = "verificaHome";
        log.debugStart(methodName);
        removeAttribute(session);
        log.debugEnd(methodName);
        return "redirect:verifica";
    }

    @RequestMapping(value = "/verifica" , method = {RequestMethod.GET, RequestMethod.POST})
    public String verifica(HttpSession session,  Model model,
        @RequestParam(required = false) String periodo,
        @RequestParam(required = false) String intervalloInizio,
        @RequestParam(required = false) String intervalloFine) {
        String methodName = "verifica";
        log.debugStart(methodName);

        IdentitaShibboleth identitaShibboleth = (IdentitaShibboleth)session.getAttribute("identitaShibboleth");
        List<Pagamento> elencoPagamenti = null;
        Date dataDa = null;
        Date dataA = null;
        if (periodo == null) {
            periodo = "";
        }
        switch (periodo) {
        case "ultimi2mesi":
            dataDa = Date.from(ZonedDateTime.now().minusMonths(2).toInstant());
            dataA = new Date();
            elencoPagamenti = pagamentoFacade.elencoPagamentiEffettuatiPerCFeIntervallo(identitaShibboleth.getCodiceFiscale(), dataDa, dataA);
            break;
        case "ultimi4mesi":
            dataDa = Date.from(ZonedDateTime.now().minusMonths(4).toInstant());
            dataA = new Date();
            elencoPagamenti = pagamentoFacade.elencoPagamentiEffettuatiPerCFeIntervallo(identitaShibboleth.getCodiceFiscale(), dataDa, dataA);
            break;
        case "ultimi6mesi":
            dataDa = Date.from(ZonedDateTime.now().minusMonths(6).toInstant());
            dataA = new Date();
            elencoPagamenti = pagamentoFacade.elencoPagamentiEffettuatiPerCFeIntervallo(identitaShibboleth.getCodiceFiscale(), dataDa, dataA);
            break;
        case "intervallo":
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dataDa = formatter.parse(intervalloInizio);
                dataA = formatter.parse(intervalloFine);
                elencoPagamenti = pagamentoFacade.elencoPagamentiEffettuatiPerCFeIntervallo(identitaShibboleth.getCodiceFiscale(), dataDa, dataA);
            } catch (ParseException e) {
                elencoPagamenti = pagamentoFacade.elencoPagamentiEffettuatiPerCFeUltimi10(identitaShibboleth.getCodiceFiscale());
                Messaggi messaggi = new Messaggi();
                messaggi.addMessaggio(new Messaggio(LevelMessage.DANGER, "Impostare correttamente intervallo di tempo per la ricerca!"));
                model.addAttribute("messaggi", messaggi);
            }

            break;
        default:
            elencoPagamenti = pagamentoFacade.elencoPagamentiEffettuatiPerCFeUltimi10(identitaShibboleth.getCodiceFiscale());
            break;
        }

        Boolean haveValidPayment = Boolean.FALSE;
        Set<String> esitiSet = new TreeSet<String>();
        Set<String> entiSet = new TreeSet<String>();
        Set<String> dettagliSet = new TreeSet<String>();
        BigDecimal importoMin = BigDecimal.valueOf(Long.MAX_VALUE);
        BigDecimal importoMax = BigDecimal.ZERO;

        //for (Pagamento pagamento : elencoPagamenti) {

        Iterator<Pagamento> pagamentiIterator = elencoPagamenti.iterator();

        while (pagamentiIterator.hasNext()) {
            Pagamento pagamento = pagamentiIterator.next();

            switch (StatoPagamento.findById(pagamento.getIdStatoCorrente())) {
            case DA_PAGARE:
            case NON_DEFINITO:
            case COMPILATO:
            case TRANSAZIONE_INIZIALIZZATA:
                pagamentiIterator.remove();
                continue;
            case TRANSAZIONE_AVVIATA:
            case TRANSAZIONE_ERRORE:
                pagamento.setIdStatoCorrente(StatoPagamento.IN_ATTESA.getId());
                haveValidPayment = Boolean.TRUE;
                break;
            case IN_ATTESA:
            case FALLITO:
            case SUCCESSO:
            case ANNULLATO:
            case INVALIDATO:
            default:
                haveValidPayment = Boolean.TRUE;
                break;
            }

            esitiSet.add(StatoPagamento.findById(pagamento.getIdStatoCorrente()).getDescrizione());
            entiSet.add(pagamento.getEnte().getNome());
            dettagliSet.add(pagamento.getTipoPagamento().getDescrizionePortale());
            pagamento.getImporto();
            if (pagamento.getImporto().compareTo(importoMin) < 0) {
                importoMin = pagamento.getImporto();
            }
            if (pagamento.getImporto().compareTo(importoMax) > 0) {
                importoMax = pagamento.getImporto();
            }

            try {
                Rt rt = pagamentoFacade.ricercaRt(pagamento.getIdPagamento(), StatoPagamento.findById(pagamento.getIdStatoCorrente()));
                if (rt == null) {
                    pagamento.setPulsantePdf("disabled");
                    pagamento.setPulsanteXml("disabled");
                } else {
                    pagamento.setPulsantePdf((rt.getRicevutaPdf() == null || rt.getRicevutaPdf().length == 0) ? "disabled" : "");
                    pagamento.setPulsanteXml((rt.getRtXml() == null || rt.getRtXml().length == 0) ? "disabled" : "");
                }
            } catch (NoDataException e) {
                pagamento.setPulsantePdf("disabled");
                pagamento.setPulsanteXml("disabled");
            }

            try {
                //cercare quietanza se c'e' abilitare tasto
                boolean quietanza = false;

                EsitiRicevuti esito = pagamentoFacade.ricercaEsitiRicevutiByIUV ( pagamento.getIuv () );
                if ( null != esito && esito.getIdQuietanzaEsito () != null )
                    quietanza = true;
                model.addAttribute ( "pulsanteReceiptPdf", ( !quietanza ) ? "disabled" : "" );
            } catch ( NoDataException e ) {

                model.addAttribute ( "pulsanteReceiptPdf", "disabled" );
            }
        }
        log.debug(methodName, toXml(elencoPagamenti));

        model.addAttribute("elencoPagamenti", elencoPagamenti);
        model.addAttribute("esiti", new ArrayList<String>(esitiSet));
        model.addAttribute("enti", new ArrayList<String>(entiSet));
        model.addAttribute("dettagli", new ArrayList<String>(dettagliSet));
        model.addAttribute("importoMin", haveValidPayment ? importoMin : 0);
        model.addAttribute("importoMax", haveValidPayment ? importoMax : 0);
        model.addAttribute("commonData", initCommonData("verifica", 1, Boolean.FALSE, Boolean.FALSE, null, session));

        log.debugEnd(methodName);


        return VIEWVERIFICA;
    }
}
