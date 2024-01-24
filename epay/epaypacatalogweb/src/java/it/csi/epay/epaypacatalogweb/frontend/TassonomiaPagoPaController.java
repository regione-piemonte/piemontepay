/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.jboss.remoting3.security.AuthorizingCallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import it.csi.epay.epaypacatalogweb.business.manager.EsportazioneManager;
import it.csi.epay.epaypacatalogweb.business.manager.TassonomiaManager;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RicercaRiferimentoContabileFiltroVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.ComboBoxTassonomiaVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.RicercaTassonomiaFiltroVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.TassonomiaVO;
import it.csi.epay.epaypacatalogweb.security.EntityAction;


@Controller
@RequestMapping ( value = "/tassonomia-pagopa" )
public class TassonomiaPagoPaController extends AuthenticatedParentController {


	private static final String PAGINA_RICERCA = "/tassonomia-pagopa/ricerca/index";

	private static final String URL_RICERCA = "/tassonomia-pagopa/ricerca";

	private static final String MODEL_FILTRO_RICERCA = "filtro_ricerca_tassonomia_pagopa";

	private static final String MODEL_RISULTATI_RICERCA = "lista_risultati";

	private static final String MODEL_NESSUN_RISULTATO_RICERCA = "lista_risultati_vuota";

	private static final String MODEL_MACRO_AREA = "model_macro_area";

	private static final String MODEL_TIPO_SERVIZIO = "model_tipo_servizio";

	private static final String MODEL_DATI_SPECIFICI_INCASSO = "model_dati_specifici_incasso";

	@Autowired
	private TassonomiaManager tassonomiaManager;

	@Autowired
	private AuthorizationManager authorizationManager;
	
	 @Autowired
	private EsportazioneManager esportazioneManager;

	@InitBinder
	protected void initBinder ( WebDataBinder binder ) {
		binder.registerCustomEditor ( String.class, new StringTrimmerEditor ( true ) );

		//        binder.setValidator ( new GestioneRiferimentoContabileValidator () );

		SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
		dateFormat.setLenient ( false );
		binder.registerCustomEditor ( Date.class, new CustomDateEditor ( dateFormat, true ) );
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_TASSONOMIA + "')" )
	@RequestMapping ( "/ricerca" )
	public String viewRicerca (
			Model model,
			HttpServletRequest request ) {
		List<TassonomiaVO> list;
		
		authorizationManager.require ( TassonomiaVO.class, EntityAction.LIST );
		
		RicercaTassonomiaFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaTassonomiaFiltroVO.class );
		//
		if ( filtro == null ) {
			model.addAttribute ( MODEL_FILTRO_RICERCA, new RicercaTassonomiaFiltroVO () );
			model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
		} else {
			model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
			try {
				list = tassonomiaManager.ricercaTassonomia ( filtro );

				model.addAttribute ( MODEL_RISULTATI_RICERCA, list );
				model.addAttribute ( MODEL_NESSUN_RISULTATO_RICERCA, ( list.size () < 1 ) );
			} catch ( OperationFailedException e ) {
				model.addAttribute ( MODEL_RISULTATI_RICERCA, null );
				model.addAttribute ( MODEL_MESSAGGIO_ERRORE, String.format ( Messages.ERROR_DURING_SEARCH, e.getMessage () ) );
			}
		}
		//
		loadComboboxes ( model );
		return PAGINA_RICERCA;
	}

	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_TASSONOMIA + "')" )
	@RequestMapping ( "/pulisci" )
	public String clearRicerca ( Model model, HttpServletRequest request ) {
		request.getSession ().removeAttribute ( MODEL_FILTRO_RICERCA );
		model.addAttribute ( MODEL_FILTRO_RICERCA, null );
		return "redirect:" + URL_RICERCA;
	}


	@PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_TASSONOMIA + "')" )
	@RequestMapping ( value = "/ricerca", method = RequestMethod.POST )
	public String doRicerca ( @Valid @ModelAttribute ( MODEL_FILTRO_RICERCA ) RicercaTassonomiaFiltroVO filtro, BindingResult bindingResult,
			Model model, HttpServletRequest request ) {

		if ( bindingResult.hasErrors () ) {
			model.addAttribute ( MODEL_FILTRO_RICERCA, filtro );
			model.addAttribute ( MODEL_RISULTATI_RICERCA, null );

			loadComboboxes ( model );
			return PAGINA_RICERCA;
		} else {
			request.getSession ().setAttribute ( MODEL_FILTRO_RICERCA, filtro );
		}

		return "redirect:" + URL_RICERCA;
	}
	
	 @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_TASSONOMIA + "')" )
	    @RequestMapping ( "/esporta-excel" )
	    public void esportaXlsx ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

	        authorizationManager.require ( TassonomiaVO.class, EntityAction.LIST );

	        List<TassonomiaVO> list;

	        RicercaTassonomiaFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaTassonomiaFiltroVO.class );

	        if ( filtro == null ) {
	            throw new NotAllowedException ();
	        } else {
	            try {
	                list = tassonomiaManager.ricercaTassonomia ( filtro );
	            } catch ( OperationFailedException e ) {
	                throw new RuntimeException ( e );
	            }
	            esportazioneManager.esportaTassonomiaXlsx( response, list );
	        }
	    }
	 
	 @PreAuthorize ( "hasRole('" + Constants.USE_CASES.RICERCA_TASSONOMIA + "')" )
	    @RequestMapping ( "/esporta-csv" )
	    public void esportaCsv ( HttpServletResponse response, HttpServletRequest request ) throws IOException {

	        authorizationManager.require ( TassonomiaVO.class, EntityAction.LIST );

	        List<TassonomiaVO> list;

	        RicercaTassonomiaFiltroVO filtro = this.getFromSession ( request, MODEL_FILTRO_RICERCA, RicercaTassonomiaFiltroVO.class );

	        if ( filtro == null ) {
	            throw new NotAllowedException ();
	        } else {
	            try {
	                list = tassonomiaManager.ricercaTassonomia ( filtro );
	            } catch ( OperationFailedException e ) {
	                throw new RuntimeException ( e );
	            }
	            esportazioneManager.esportaTassonomiaCsv( response, list );
	        }
	    }

	//
	@Override
	public void loadComboboxes ( Model model ) {

		List<TassonomiaVO> listTassonomia = tassonomiaManager.getElencoTassonomiaPerCodiceTipoEnteCreditore();


		ComboBoxTassonomiaVO ct= getComboBoxTassonomia(listTassonomia);

		model.addAttribute ( MODEL_MACRO_AREA, ct.getMacroArea() );
		model.addAttribute ( MODEL_DATI_SPECIFICI_INCASSO, ct.getDatiSpecificiIncasso() );
		model.addAttribute ( MODEL_TIPO_SERVIZIO, ct.getTipoServizio() );

	}

	private ComboBoxTassonomiaVO  getComboBoxTassonomia(List<TassonomiaVO> listTassonomia) {
		ComboBoxTassonomiaVO ct= new ComboBoxTassonomiaVO();
		Map<String ,GenericVO> listMacroArea = new HashMap<String,GenericVO>();

		Map<String ,GenericVO> listDatiSpecificiIncasso = new HashMap<String,GenericVO>();

		Map<String ,GenericVO> listTipoServizio = new HashMap<String,GenericVO>();

		Comparator<GenericVO > comparator = new Comparator<GenericVO>() {

			@Override
			public int compare(GenericVO o1, GenericVO o2) {
				return o1.getDescrizione().compareTo(o2.getDescrizione());
			}
		}
		;
		for (TassonomiaVO tassonomia : listTassonomia)
		{
			if (!listMacroArea.containsKey(tassonomia.getMacroArea()))
			{
				listMacroArea.put(tassonomia.getNomeMacroArea(),  new GenericVO(tassonomia.getNomeMacroArea(),tassonomia.getNomeMacroArea() ));
			}
			if (!listDatiSpecificiIncasso.containsKey(tassonomia.getDatiSpecificiIncasso()))
			{
				listDatiSpecificiIncasso.put(tassonomia.getDatiSpecificiIncasso(), new GenericVO(tassonomia.getDatiSpecificiIncasso(),tassonomia.getDatiSpecificiIncasso() ));
			}
			if (!listTipoServizio.containsKey(tassonomia.getTipoServizio()))
			{
				listTipoServizio.put(tassonomia.getTipoServizio(), new GenericVO(tassonomia.getTipoServizio(),tassonomia.getTipoServizio() ));
			}
		}

		ct.setMacroArea(new ArrayList<GenericVO>(listMacroArea.values()));
		ct.getMacroArea().sort(comparator);
		ct.setDatiSpecificiIncasso(new ArrayList<GenericVO>(listDatiSpecificiIncasso.values()));
		ct.getDatiSpecificiIncasso().sort(comparator);
		ct.setTipoServizio(new ArrayList<GenericVO>(listTipoServizio.values()));
		ct.getTipoServizio().sort(comparator);
		return  ct;
	}





}
