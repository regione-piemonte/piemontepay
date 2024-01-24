/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.csi.epay.epaypacatalogweb.business.manager.AuthorizationManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.business.manager.EsportazioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.VoceEntrataManager;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.OpzioneComboVO;
import it.csi.epay.epaypacatalogweb.model.ppay.MacrotipoPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.RicercaVoceEntrataPPayFiltroVO;
import it.csi.epay.epaypacatalogweb.model.ppay.TematicaPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.validators.VoceEntrataValidator;
import it.csi.epay.epaypacatalogweb.security.EntityAction;

@Controller
@RequestMapping(value = "/voci-entrata")
public class VoceEntrataController extends AuthenticatedParentController {

	private static final String PAGINA_RICERCA = "/voce-entrata/index";
	private static final String URL_RICERCA = "/voci-entrata/ricerca";

	private static final String MODEL_FILTRO_RICERCA = "filtro_voci_entrata";
	private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";
	private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

	@Autowired
	private VoceEntrataManager voceEntrataManager;

	@Autowired
	private DecodificheManager decodificheManager;

	@Autowired
	private AuthorizationManager authorizationManager;

	@Autowired
	private EsportazioneManager esportazioneManager;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new VoceEntrataValidator());
	}

	@Override
	public void loadComboboxes(Model model) {
		List<TematicaPPayVO> tematiche = decodificheManager.getListaOpzioniTematicheVociEntrata();
		List<MacrotipoPPayVO> macrotipi = decodificheManager.getListaOpzioniMacrotipiVociEntrata();
        List<VoceEntrataPPayVO> vociEntrata = voceEntrataManager.getOpzioniVoceEntrata ();

        model.addAttribute ( MODEL_TEMATICHE, OpzioneComboVO.getOpzioni ( tematiche ) );
        model.addAttribute ( MODEL_MACROTIPI, OpzioneComboVO.getOpzioni ( macrotipi ) );
        model.addAttribute ( MODEL_VOCI_ENTRATA, OpzioneComboVO.getOpzioni ( vociEntrata ) );
        model.addAttribute ( MODEL_VOCI_ENTRATA + "_JS", serializeForJavascript ( vociEntrata ) );
	}

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_VOCE_ENTRATA + "')" )
    @RequestMapping ( "/pulisci" )
	public String clearVociEntrata(Model model, HttpServletRequest request) {

		request.getSession().removeAttribute(MODEL_FILTRO_RICERCA);
		model.addAttribute(MODEL_FILTRO_RICERCA, null);

		return "redirect:" + URL_RICERCA;
	}

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_VOCE_ENTRATA + "')" )
    @RequestMapping ( "/ricerca" )
	public String viewRicercaVociEntrata(
			Model model,
			HttpServletRequest request
	) {
		loadComboboxes(model);

		List<VoceEntrataPPayVO> list;

		authorizationManager.require(VoceEntrataPPayVO.class, EntityAction.LIST);

		RicercaVoceEntrataPPayFiltroVO filtro = this.getFromSession(request, MODEL_FILTRO_RICERCA, RicercaVoceEntrataPPayFiltroVO.class);

		if (filtro == null) {
			model.addAttribute(MODEL_FILTRO_RICERCA, new RicercaVoceEntrataPPayFiltroVO());
			model.addAttribute(MODEL_RISULTATI_RICERCA, null);
		} else {
			model.addAttribute(MODEL_FILTRO_RICERCA, filtro);
			try {
				list = voceEntrataManager.ricercaVoceEntrata(filtro);
				model.addAttribute(MODEL_RISULTATI_RICERCA, list);
				model.addAttribute(MODEL_NESSUN_RISULTATO_RICERCA, (list.size() < 1));
			} catch (Exception e) {
				model.addAttribute(MODEL_RISULTATI_RICERCA, null);
				model.addAttribute(MODEL_MESSAGGIO_ERRORE, String.format(Messages.ERROR_DURING_SEARCH, e.getMessage()));
			}
		}

		return PAGINA_RICERCA;
	}

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_VOCE_ENTRATA + "')" )
    @RequestMapping ( value = "/ricerca", method = RequestMethod.POST )
	public String ricercaVociEntrata(@Valid @ModelAttribute(MODEL_FILTRO_RICERCA) RicercaVoceEntrataPPayFiltroVO filtro, BindingResult bindingResult,
			Model model, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			loadComboboxes(model);
			model.addAttribute(MODEL_FILTRO_RICERCA, filtro);
			model.addAttribute(MODEL_RISULTATI_RICERCA, null);
			return PAGINA_RICERCA;
		} else {
			request.getSession().setAttribute(MODEL_FILTRO_RICERCA, filtro);
		}

		return "redirect:" + URL_RICERCA;
	}

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_VOCE_ENTRATA + "')" )
    @RequestMapping ( "/esporta-excel" )
	public void esportaXlsx(HttpServletResponse response, HttpServletRequest request) throws IOException {

		authorizationManager.require(VoceEntrataPPayVO.class, EntityAction.LIST);

		List<VoceEntrataPPayVO> vociEntrata;

		RicercaVoceEntrataPPayFiltroVO filtro = this.getFromSession(request, MODEL_FILTRO_RICERCA, RicercaVoceEntrataPPayFiltroVO.class);

		if (filtro == null) {
			throw new NotAllowedException();
		} else {
			try {
				vociEntrata = voceEntrataManager.ricercaVoceEntrata(filtro);
			} catch (OperationFailedException e) {
				throw new RuntimeException(e);
			}
			esportazioneManager.esportaVociSpesaXlsx(response, vociEntrata);
		}
	}

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_VOCE_ENTRATA + "')" )
    @RequestMapping ( "/esporta-csv" )
	public void esportaCsv(HttpServletResponse response, HttpServletRequest request) throws IOException {

		authorizationManager.require(VoceEntrataPPayVO.class, EntityAction.LIST);

		List<VoceEntrataPPayVO> vociEntrata;

		RicercaVoceEntrataPPayFiltroVO filtro = this.getFromSession(request, MODEL_FILTRO_RICERCA, RicercaVoceEntrataPPayFiltroVO.class);

		if (filtro == null) {
			throw new NotAllowedException();
		} else {
			try {
				vociEntrata = voceEntrataManager.ricercaVoceEntrata(filtro);
			} catch (OperationFailedException e) {
				throw new RuntimeException(e);
			}
			esportazioneManager.esportaVociSpesaCsv(response, vociEntrata);
		}
	}

}

