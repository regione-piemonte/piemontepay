/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteElementDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteIUVDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaAutocompleteIUVElementDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.UtenteDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaCaricaRequestDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.dto.common.SalvaPosizioneDebitoriaDaAggiornareRequestDto;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.integration.epaybeapi.impl.AvvisoPagamentoImpl;
import it.csi.epay.epaypaweb.integration.epaybeapi.impl.StatoPosizioneDebitoriaImpl;
import it.csi.epay.epaypaweb.integration.epaybeapi.interf.StatoPosizioneDebitoria;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.AutocompleteResponse;
import it.csi.epay.epaypaweb.presentation.dto.PosizioneDebitoriaDaAggiornarePostAutocomplete;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import it.csi.epay.epaypaweb.presentation.service.EpayapiService;
import it.csi.epay.epaypaweb.util.Util;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.ejb.EJB;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum.ANNULLAMENTO;
import static it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum.MODIFICA;
import static it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum.PERSONA_FISICA;
import static it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum.PERSONA_GIURIDICA;
import static it.csi.epay.epaypaweb.util.Util.blank2null;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
	//@formatter:off
	@Result(name = INPUT, location = "pages/crea-or-modifica-posizionedebitoriadaaggiornare.jsp"),
	@Result(name = SUCCESS, type = "redirect", location = "entry-visualizza-flusso-posizionidebitoriedaaggiornare.do", params = { "init", "false", "idFlusso", "${idFlusso}", "esitoElaborazione", "${esitoElaborazione}", "messaggioEsitoElaborazione", "${messaggioEsitoElaborazione}" } ),
	@Result ( name = "AUTOCOMPLETE", type = "json", params = { "root", "autocompleteResponse", "noCache", "true" } ),
	@Result ( name = "POS_DEB_DA_AGGIORNARE", type = "json", params = { "root", "posizioneDebitoriaDaAggiornarePostAutocomplete", "noCache", "true" } ),
	//@formatter:on
})
public class CreaOrModificaPosizioneDebitoriaDaAggiornareAction extends EpaypawebBaseAction implements Preparable {

	private static final long serialVersionUID = 2911762551618751509L;

	private static final String CLASSNAME = CreaOrModificaPosizioneDebitoriaDaAggiornareAction.class.getSimpleName ();
	protected PosizioneDebitoriaDaAggiornarePostAutocomplete posizioneDebitoriaDaAggiornarePostAutocomplete;

	// per ottenere o creare la posizione debitoria
	private Long idFlusso;
	private Long idPosizioneDebitoriaDaAggiornare;

	// flag per sapere se l'inserimento risulta attivato dalla home o dalla ricerca flusso
	private boolean origineHomePerInserimento;
	private TipoOperativitaEnum operativita;

	// campi in sola lettura della testata del flusso in inserimento o modifica
	private String idMessaggio;
	private Integer idCodVersamento;
	private Integer numeroElementi;
	private Integer addendoElemento;
	//
	// campi usati per la validazione
	private String aggiornamentoIdTipo;
	private TipoAggiornamentoPosizioneDebitoriaEnum aggiornamentoTipo;
	private String idPosizioneDebitoriaEsterna;
	private String motivazione;
	private Date dataInizioValidita;
	private String strDataInizioValidita;
	private Date dataFineValidita;
	private String strDataFineValidita;
	private Date dataScadenza;
	private String strDataScadenza;
	private BigDecimal compimpImportoTotale;
	private String strCompimpImportoTotale;
	private String desCausaleVersamento;
	private Long[] compimpId;
	private BigDecimal[] compimpImporto;
	private String[] strCompimpImporto;
	private String[] compimpCausale;
	private String[] compimpDatiSpec;
	private Integer [] compimpAnnoAccert;
	private String [] strCompimpAnnoAccert;
	private Integer [] compimpNumeroAccert;
	private String [] strCompimpNumeroAccert;
	private Long [] rifpagId;
	private String [] rifpagNome;
	private String [] rifpagValore;

	private List<StrutsOptionsSupport> aggiornamentoIdTipoList;
	private boolean activateValorizzareAlmenoUnCampoTraQuelliSeguenti;

	private String soggettoIdTipo;
	private TipologiaSoggettoEnum soggettoTipo;
	private String soggettoIdUnivocoFiscalePersonaFisica;
	private String soggettoCognome;
	private String soggettoNome;
	private String soggettoIdUnivocoFiscalePersonaGiuridica;
	private String soggettoRagioneSociale;

	private String soggettoIndirizzo;
	private String soggettoCivico;
	private String soggettoCap;
	private String soggettoLocalita;
	private String soggettoProvincia;
	private String soggettoNazione;
	private String soggettoEmail;

	private String idPosDebDaAgg;
	private String iuv;

	private String iuv_text;

	private List<StrutsOptionsSupport> soggettoIdTipoList;
	private List<StrutsOptionsSupport> fakeList;
	private List<StrutsOptionsSupport> fakeListIUV;

	protected AutocompleteResponse autocompleteResponse;

	private String q; //query string dall'autocomplete

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	private String idStringSeleceted;

	private Long idLongSelected;

	private String idPosizioneDebitoriaEsterna_text;

	private String multibeneficiario;

	private static final String NO = "NO";

	private static final String SI = "SI";

	private static final String SYSTEM_ERROR = "system.error";

	private static final String SOGGETTO_CIVICO = "soggettoCivico";

	private static final String ID_POSIZIONE_DEBITORIA_DA_AGGIORNARE = "idPosizioneDebitoriaDaAggiornare";

	private static final String ID_COD_VERSAMENTO = "idCodVersamento";

	private static final String STR_DATA_INIZIO_VALIDITA = "strDataInizioValidita";

	private static final String STR_COMP_IMP_NUMERO_ACCERT = "strCompimpNumeroAccert";

	private static final String STR_COMPI_IMP_IMPORTO_TOTALE = "strCompimpImportoTotale";

	private static final String SOGGETTO_CAP = "soggettoCap";

	private static final String SOGGETTO_LOCALITA = "soggettoLocalita";

	private static final String SOGGETTO_PROVINCIA = "soggettoProvincia";

	private static final String SOGGETTO_NAZIONE = "soggettoNazione";

	private static final String SOGGETTO_INDIRIZZO = "soggettoIndirizzo";

	private static final String STR_COMP_IMP_IMPORTO = "strCompimpImporto";

	private static final String CAMPO_OBBLIGATORIO_CONDIZIONATO = "campo.obbligatorio.condizionato";

	private static final String STR_DATA_FINE_VALIDITA = "strDataFineValidita";

	private static final String STR_COMP_IMP_ANNO_ACCERT = "strCompimpAnnoAccert";

	private String strImportoPrincipale;

	private String strImportoSecondario;

	private BigDecimal importoPrincipale;

	private BigDecimal importoSecondario;

	private String strCompimpImporto0Secondario;

	private String compimpCausale0Secondario;

	private BigDecimal compimpImportoSecondario;

	private Long compimpId0Secondario;

	@Override
	public void prepare() {
		String methodName = "prepare";

		log.info ( CLASSNAME + " " + methodName + " - START" );

		compimpId = new Long[5];
		compimpImporto = new BigDecimal[5];
		strCompimpImporto = new String[5];
		compimpCausale = new String[5];
		compimpDatiSpec = new String[5];
		compimpAnnoAccert = new Integer [5];
		strCompimpAnnoAccert = new String [5];
		compimpNumeroAccert = new Integer [5];
		strCompimpNumeroAccert = new String [5];
		rifpagId = new Long [5];
		rifpagNome = new String [5];
		rifpagValore = new String [5];

		aggiornamentoIdTipoList = new ArrayList<>();
		aggiornamentoIdTipoList.add(new StrutsOptionsSupport(ANNULLAMENTO.getId(), ANNULLAMENTO.getDes()));
		aggiornamentoIdTipoList.add(new StrutsOptionsSupport(MODIFICA.getId(), MODIFICA.getDes()));

		soggettoIdTipoList = new ArrayList<>();
		soggettoIdTipoList.add(new StrutsOptionsSupport(PERSONA_FISICA.getId(), PERSONA_FISICA.getDes()));
		soggettoIdTipoList.add(new StrutsOptionsSupport(PERSONA_GIURIDICA.getId(), PERSONA_GIURIDICA.getDes()));

		fakeList = new ArrayList<> ();
		fakeListIUV = new ArrayList<> ();

		activateValorizzareAlmenoUnCampoTraQuelliSeguenti = false;

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
	}

	private void commonInit() throws BusinessException {
		PosizioneDebitoriaDaAggiornareDto aggposdeb;
		if (idPosizioneDebitoriaDaAggiornare != null) {
			// ottiene la posizione debitoria da modificare
			PosizioneDebitoriaDaAggiornareRequestDto posizioneDebitoriaDaAggiornareRequestDto = new PosizioneDebitoriaDaAggiornareRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idPosizioneDebitoriaDaAggiornare);

			aggposdeb = gestioneFlussiBusiness.getPosizioneDebitoriaDaAggiornare(posizioneDebitoriaDaAggiornareRequestDto);
			//
			idPosizioneDebitoriaEsterna = aggposdeb.getIdPosizioneDebitoriaEsterna();
			motivazione = aggposdeb.getMotivazione();
			aggiornamentoTipo = aggposdeb.getTipoAggiornamento().getTipoAggiornamentoEnum();
			aggiornamentoIdTipo = aggiornamentoTipo.getId();
			switch (aggiornamentoTipo) {
			case ANNULLAMENTO:
				// non ci sono campi esclusivi del tipo di aggiornamento annullamento
				break;
			case MODIFICA:
				dataInizioValidita = aggposdeb.getDataInizioValidita();
				strDataInizioValidita = Util.date2strdate(dataInizioValidita);
				dataFineValidita = aggposdeb.getDataFineValidita();
				strDataFineValidita = Util.date2strdate(dataFineValidita);
				dataScadenza = aggposdeb.getDataScadenza();
				strDataScadenza = Util.date2strdate(dataScadenza);
				compimpImportoTotale = aggposdeb.getImportoTotale();
				strCompimpImportoTotale = Util.bd2str(compimpImportoTotale);
				desCausaleVersamento = aggposdeb.getDesCausaleVersamento();
				//
				List<ComponenteImportoDto> componenteImportoList = aggposdeb.getComponenteImportoDtoList();
				if (componenteImportoList != null) {
					compimpId = new Long[5];
					compimpImporto = new BigDecimal[5];
					strCompimpImporto = new String[5];
					compimpCausale = new String[5];
					compimpDatiSpec = new String[5];
					compimpAnnoAccert = new Integer [5];
					strCompimpAnnoAccert = new String [5];
					compimpNumeroAccert = new Integer [5];
					strCompimpNumeroAccert = new String [5];
					int i = 0;
					for (ComponenteImportoDto dto : componenteImportoList) {
						if (dto != null) {
							if ( dto.getFlagComponenteSecondario () == null || dto.getFlagComponenteSecondario () == false ) {
								compimpId [i] = dto.getId ();
								compimpImporto [i] = dto.getImporto ();
								strCompimpImporto [i] = Util.bd2str ( compimpImporto [i] );
								compimpCausale [i] = dto.getCausale ();
								compimpAnnoAccert [i] = dto.getAnnoAccertamento ();
								strCompimpAnnoAccert [i] = Util.int2str ( compimpAnnoAccert [i] );
								compimpNumeroAccert [i] = dto.getNumeroAccertamento ();
								strCompimpNumeroAccert [i] = Util.int2str ( compimpNumeroAccert [i] );
								i++;
							} else {
								compimpId0Secondario = dto.getId ();
								compimpImportoSecondario = dto.getImporto ();
								strCompimpImporto0Secondario = Util.bd2str ( compimpImportoSecondario );
								compimpCausale0Secondario = dto.getCausale ();
							}
						}
					}
				}

				List<RiferimentoPagamentoDto> riferimentoPagamentoList = aggposdeb.getRiferimentoPagamentoDtoList();
				if (riferimentoPagamentoList != null) {
					rifpagId = new Long[5];
					rifpagNome = new String[5];
					rifpagValore = new String[5];
					int i = 0;
					for (RiferimentoPagamentoDto dto : riferimentoPagamentoList) {
						if (dto != null) {
							rifpagId[i] = dto.getId();
							rifpagNome [i] = dto.getNome ();
							rifpagValore [i] = dto.getValore ();
							i++;
						}
					}
				}

				SoggettoAnagraficoDto soggettoDebitore = aggposdeb.getSoggettoAnagraficoDto();
				if (null != soggettoDebitore)
				{
					soggettoTipo = soggettoDebitore.getTipologiaSoggettoEnum();
					soggettoIdTipo = soggettoTipo.getId();
					switch (soggettoTipo) {
					case PERSONA_FISICA:
						soggettoIdUnivocoFiscalePersonaFisica = soggettoDebitore.getIdUnivocoFiscale();
						soggettoCognome = soggettoDebitore.getCognome();
						soggettoNome = soggettoDebitore.getNome();
						break;
					case PERSONA_GIURIDICA:
						soggettoIdUnivocoFiscalePersonaGiuridica = soggettoDebitore.getIdUnivocoFiscale();
						soggettoRagioneSociale = soggettoDebitore.getRagioneSociale();
						break;
					}
					soggettoIndirizzo = soggettoDebitore.getIndirizzo();
					soggettoCivico = soggettoDebitore.getCivico();
					soggettoCap = soggettoDebitore.getCap();
					soggettoLocalita = soggettoDebitore.getLocalita();
					soggettoProvincia = soggettoDebitore.getProvincia();
					soggettoNazione = soggettoDebitore.getNazione();
					//
					soggettoEmail = soggettoDebitore.getEmail();
				}
				else
				{
					soggettoIdTipo = "";
				}

				break;
			}

		} else {
			// inizializza posizione debitoria da aggiornare
			aggposdeb = new PosizioneDebitoriaDaAggiornareDto();
			aggiornamentoIdTipo = ANNULLAMENTO.getId();
		}
	}

	@Action("entry-creaormodifica-posizionedebitoriadaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	@SkipValidation
	public String entryCreaOrModificaPosizioneDebitoriaDaAggiornare() {
		String methodName = "entryCreaOrModificaPosizioneDebitoriaDaAggiornare";
		String result;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// riottiene l'idMessaggio eventualmente aggiornato (incrementato)
			if ( idFlusso != null ) {
				idMessaggio = gestioneFlussiBusiness.getFlussoLight(idFlusso).getIdMessaggio();
			}

			// salva in sessione i valori iniziali della testata in sola lettura per poterli ripristinarli se richiesto
			ActionScope actionScope = getSessionContext().getActionScope();
			actionScope.putValue("idFlusso", idFlusso);
			actionScope.putValue ( ID_POSIZIONE_DEBITORIA_DA_AGGIORNARE, idPosizioneDebitoriaDaAggiornare );
			actionScope.putValue("idMessaggio", idMessaggio);
			actionScope.putValue ( ID_COD_VERSAMENTO, idCodVersamento );
			actionScope.putValue("numeroElementi", numeroElementi);
			actionScope.putValue("addendoElemento", addendoElemento);

			Boolean flagMbPrimario = getCov ( idCodVersamento ).getFlagMbPrimario ();
			if ( null != flagMbPrimario && flagMbPrimario ) {
				multibeneficiario = SI;
				actionScope.putValue ( "multibeneficiario", SI );
			} else {
				multibeneficiario = NO;
				actionScope.putValue ( "multibeneficiario", NO );
			}

			commonInit();
			result = INPUT;

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			addActionError ( getText ( SYSTEM_ERROR, new String [] { e.getClass ().getName (), e.getMessage () } ) );
			result = "system-error";
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	@Action("ripristina_posizionedebitoriadaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	@SkipValidation
	public String ripristinaPosizioneDebitoriaDaAggiornare() {
		String methodName = "ripristinaPosizioneDebitoriaDaAggiornare";

		log.info ( CLASSNAME + " " + methodName + " - START" );

		// riottiene dalla sessione i dati originali della testata in sola lettura
		ActionScope actionScope = getSessionContext().getActionScope();
		idFlusso = actionScope.getValue("idFlusso");
		idPosizioneDebitoriaDaAggiornare = actionScope.getValue ( ID_POSIZIONE_DEBITORIA_DA_AGGIORNARE );
		idMessaggio = actionScope.getValue("idMessaggio");
		idCodVersamento = actionScope.getValue ( ID_COD_VERSAMENTO );
		numeroElementi = actionScope.getValue("numeroElementi");
		addendoElemento = actionScope.getValue("addendoElemento");

		String result;
		try {
			commonInit();
			result = INPUT;

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			addActionError ( getText ( SYSTEM_ERROR, new String [] { e.getClass ().getName (), e.getMessage () } ) );
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	@Action("salva-posizionedebitoriadaaggiornare")
	@Authorization(cdu = "INS_AGPD")
	public String salvaPosizioneDebitoriaDaAggiornare() {
		String methodName = "salvaPosizioneDebitoriaDaAggiornare";


		String result;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			//EPAY-115
			/*
			 * if ( null != idFlusso ) { FlussoLightDto flussoDB = gestioneFlussiBusiness.getFlussoLight ( idFlusso ); if ( null != flussoDB &&
			 * StringUtils.isBlank ( flussoDB.getIuv () ) ) { esitoElaborazione = "error"; messaggioEsitoElaborazione = getText (
			 * "message.error.salva.noiuv.posizionedebitoriadaaggiornare" ); return ripristinaPosizioneDebitoriaDaAggiornare (); } } else { esitoElaborazione =
			 * "error"; messaggioEsitoElaborazione = getText ( "message.error.salva.noflusso.posizionedebitoriadaaggiornare" ); return
			 * ripristinaPosizioneDebitoriaDaAggiornare (); }
			 */

			// costruisce il dto
			SessionContext sessionContext = getSessionContext();
			UtenteDto utente = sessionContext.getUtente();
			EnteDto ente = sessionContext.getEnte();
			String codVersamento = super.getCodVersamento(idCodVersamento);
			Long idPosizioneDebitoriaDaAgg = sessionContext.getActionScope ().getValue ( ID_POSIZIONE_DEBITORIA_DA_AGGIORNARE );
			//
			FlussoDto flusso = new FlussoDto(idFlusso);
			flusso.setTipoFlusso(TipoFlussoEnum.AGGIORNAMENTO_POSIZIONI_DEBITORIE);
			flusso.setStatoFlusso(StatoFlussoEnum.BOZZA);
			flusso.setUtenteInserimento(utente.getCod());
			flusso.setUtenteUltimaVariazione(utente.getCod());
			flusso.setCodFiscaleEnte(ente.getCodFiscale());
			flusso.setCodVersamento(codVersamento);
			flusso.setNumeroElementi(numeroElementi + addendoElemento);
			//
			PosizioneDebitoriaDaAggiornareDto aggposdeb = new PosizioneDebitoriaDaAggiornareDto(idPosizioneDebitoriaDaAgg);
			aggposdeb.setIdFlusso(idFlusso);
			aggposdeb.setIdPosizioneDebitoriaEsterna(idPosizioneDebitoriaEsterna.trim());  // N.B. necessario trim dato chee lato sportello fa il trim
			aggposdeb.setMotivazione(motivazione);

			TipoAggiornamentoPosizioneDebitoriaDto tipoAggiornamentoPosizioneDebitoria = new TipoAggiornamentoPosizioneDebitoriaDto(aggiornamentoTipo);
			switch (aggiornamentoTipo) {
			case ANNULLAMENTO:
				// non ci sono campi esclusivi del tipo di aggiornamento annullamento
				break;
			case MODIFICA:
				aggposdeb.setDataInizioValidita(dataInizioValidita);
				aggposdeb.setDataFineValidita(dataFineValidita);
				aggposdeb.setDataScadenza(dataScadenza);
				aggposdeb.setImportoTotale(compimpImportoTotale);
				aggposdeb.setDesCausaleVersamento(blank2null(desCausaleVersamento));
				//
				List<ComponenteImportoDto> compimpDtoList = new ArrayList<> ();
				for (int i = 0; i < 5; i++) {
					if (compimpImporto[i] != null) {
						ComponenteImportoDto compimpDto = new ComponenteImportoDto(compimpId[i]);
						compimpDto.setImporto(compimpImporto[i]);
						compimpDto.setCausale(compimpCausale[i]);
						compimpDto.setAnnoAccertamento ( compimpAnnoAccert [i] );
						compimpDto.setNumeroAccertamento ( compimpNumeroAccert [i] );
						compimpDto.setFlagComponenteSecondario ( false );
						compimpDtoList.add ( compimpDto );
					}
				}
				// gestione multibeneficiario
				if (multibeneficiario.equalsIgnoreCase ( SI )) {
					ComponenteImportoDto compimpDto = new ComponenteImportoDto(compimpId0Secondario);
					compimpDto.setImporto ( compimpImportoSecondario );
					compimpDto.setCausale ( compimpCausale0Secondario );
					compimpDto.setFlagComponenteSecondario ( true );
					compimpDtoList.add ( compimpDto );
				}
				aggposdeb.setImportoPrincipale ( importoPrincipale );
				aggposdeb.setImportoSecondarioAltroEnte ( importoSecondario );

				if ( !compimpDtoList.isEmpty () ) {
					aggposdeb.setComponenteImportoDtoList(compimpDtoList);
				}
				//
				List<RiferimentoPagamentoDto> rifpagDtoList = new ArrayList<> ();
				for ( int i = 0; i < 5; i++ ) {
					if ( !isBlank ( rifpagNome [i] ) && !isBlank ( rifpagValore [i] ) ) {
						RiferimentoPagamentoDto rifpagDto = new RiferimentoPagamentoDto ( rifpagId [i] );
						rifpagDto.setNome ( rifpagNome [i] );
						rifpagDto.setValore ( rifpagValore [i] );
						rifpagDtoList.add ( rifpagDto );
					}
				}
				if ( !rifpagDtoList.isEmpty () ) {
					aggposdeb.setRiferimentoPagamentoDtoList ( rifpagDtoList );
				}
				break;
			}
			aggposdeb.setTipoAggiornamento(tipoAggiornamentoPosizioneDebitoria);

			SoggettoAnagraficoDto soggettoDebitore= new SoggettoAnagraficoDto();
			soggettoDebitore.setTipologiaSoggettoEnum(soggettoTipo);
			if (null!= soggettoTipo)
			{
				switch (soggettoTipo) {
				case PERSONA_FISICA:
					soggettoDebitore.setIdUnivocoFiscale(soggettoIdUnivocoFiscalePersonaFisica);
					soggettoDebitore.setCognome(soggettoCognome);
					soggettoDebitore.setNome(soggettoNome);
					break;
				case PERSONA_GIURIDICA:
					soggettoDebitore.setIdUnivocoFiscale(soggettoIdUnivocoFiscalePersonaGiuridica);
					soggettoDebitore.setRagioneSociale(soggettoRagioneSociale);
					break;
				}

				if (!isBlank(soggettoIndirizzo)) {
					soggettoDebitore.setIndirizzo(blank2null(soggettoIndirizzo));
					soggettoDebitore.setCivico(blank2null(soggettoCivico));
					soggettoDebitore.setCap(blank2null(soggettoCap));
					soggettoDebitore.setLocalita(blank2null(soggettoLocalita));
					soggettoDebitore.setProvincia(blank2null(soggettoProvincia));
					soggettoDebitore.setNazione(blank2null(soggettoNazione));
				}
				soggettoDebitore.setEmail(blank2null(soggettoEmail));
				aggposdeb.setSoggettoAnagraficoDto(soggettoDebitore);

			}
			//
			List<PosizioneDebitoriaDaAggiornareDto> aggposdebList = new ArrayList<> ();
			aggposdebList.add(aggposdeb);
			//
			FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flussoCompleto = new FlussoCompletoDto<> ();
			flussoCompleto = new FlussoCompletoDto<> ();
			flussoCompleto.setFlusso(flusso);
			flussoCompleto.setItemList(aggposdebList);

			// lo salva

			SalvaPosizioneDebitoriaDaAggiornareRequestDto salvaPosizioneDebitoriaDaAggiornareRequestDto = new SalvaPosizioneDebitoriaDaAggiornareRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),flussoCompleto);

			idFlusso = gestioneFlussiBusiness.salvaPosizioneDebitoriaDaAggiornare ( salvaPosizioneDebitoriaDaAggiornareRequestDto );
			esitoElaborazione = ( idFlusso != null ) ? "success" : "warning";
			messaggioEsitoElaborazione = getText ( "message." + esitoElaborazione + ".salva.posizionedebitoriadaaggiornare" );
			result = ( idFlusso != null ) ? result = SUCCESS : ripristinaPosizioneDebitoriaDaAggiornare ();

		} catch ( BusinessException e ) {
			log.error ( "Errore imprevisto", e );
			esitoElaborazione = "error";
			messaggioEsitoElaborazione = getText ( "message.error.salva.posizionedebitoriadaaggiornare", new String [] { e.getMessage () } );
			result = ripristinaPosizioneDebitoriaDaAggiornare ();
		}
		finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}


	// prima sono eseguiti controlli sintattici attraverso la validation by annotation, poi si fanno ulteriori controlli semantici sui dati e conversioni
	@Override
	public void validate() {
		String methodName = "validate";


		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			Set<String> fieldErrorKeys = getFieldErrors().keySet();

			if ( !isBlank ( strDataInizioValidita ) && !fieldErrorKeys.contains ( STR_DATA_INIZIO_VALIDITA ) ) {
				dataInizioValidita = sdf.parse(strDataInizioValidita);
			}
			if ( !isBlank ( strDataFineValidita ) && !fieldErrorKeys.contains ( STR_DATA_FINE_VALIDITA ) ) {
				dataFineValidita = sdf.parse(strDataFineValidita);
			}
			if ( !fieldErrorKeys.contains ( STR_DATA_INIZIO_VALIDITA ) && !fieldErrorKeys.contains ( STR_DATA_FINE_VALIDITA ) ) {
				setDataInizioEFineValiditaAndCheckPrecedenza();
			}
			if ( !isBlank ( strDataScadenza ) && !fieldErrorKeys.contains ( "strDataScadenza" ) ) {
				dataScadenza = sdf.parse(strDataScadenza);
			}

			// verifiche in base al tipo di aggiornamento
			aggiornamentoTipo = TipoAggiornamentoPosizioneDebitoriaEnum.fromId(aggiornamentoIdTipo);
			switch (aggiornamentoTipo) {
			case ANNULLAMENTO:
				// non ci sono campi esclusivi del tipo di aggiornamento annullamento
				break;
			case MODIFICA:
				// verifica le componenti importo
				BigDecimal compimpSommaImporti = new BigDecimal(0);
				//
				boolean compimpAtLeastOne = false;
				final String compimpError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "" } );
				for (int i = 4; i >= 0; i--) {
					// verifica che siano interamente valorizzate
					if ( !isBlank ( strCompimpAnnoAccert [i] ) && !fieldErrorKeys.contains ( "strCompimpAnnoAccert [i]" ) ) {
						compimpAnnoAccert [i] = Integer.valueOf ( strCompimpAnnoAccert [i] );
						if ( compimpAnnoAccert [i] < 1900 || compimpAnnoAccert [i] > 2900 ) {
							addFieldError ( STR_COMP_IMP_ANNO_ACCERT + i, getText ( "campo.formato.anno.tra.1900e2900" ) );
						}
					}
					if ( !isBlank ( strCompimpImporto [i] ) && !fieldErrorKeys.contains ( STR_COMP_IMP_IMPORTO + i ) ) {
						compimpImporto[i] = Util.str2importo(strCompimpImporto[i]);
						compimpSommaImporti = compimpSommaImporti.add(compimpImporto[i]);
						if ( !isBlank ( strCompimpAnnoAccert [i] ) && !isBlank ( strCompimpNumeroAccert [i] ) ) {
							compimpNumeroAccert [i] = Integer.valueOf ( strCompimpNumeroAccert [i] );
						}
					}
					if ( !isBlank ( compimpCausale [i] ) && !isBlank ( strCompimpAnnoAccert [i] ) && !isBlank ( strCompimpNumeroAccert [i] ) ) {
						try {
							compimpNumeroAccert [i] = Integer.valueOf ( strCompimpNumeroAccert [i] );
						} catch ( NumberFormatException e ) {
							addFieldError ( STR_COMP_IMP_NUMERO_ACCERT + i, getText ( "campo.formato.intero" ) );
						}
					}

					Integer checkNumeroAccertStr = compimpNumeroAccert [i];
					if ( null != checkNumeroAccertStr && checkNumeroAccertStr < 1 ) {
						addFieldError ( STR_COMP_IMP_NUMERO_ACCERT + i, getText ( "campo.formato.intero.maggiore.di.zero" ) );
					}
					boolean currentCompimpEmpty = isBlank(strCompimpImporto[i]) && isBlank(compimpCausale[i]);
					compimpAtLeastOne |= !currentCompimpEmpty;
					if ( compimpAtLeastOne && currentCompimpEmpty ) {
						addFieldError ( STR_COMP_IMP_IMPORTO + i, getText ( "componente.importo.intermedio.vuoto" ) );
					}
					if ( isBlank ( strCompimpImporto [i] )
									&& ( !isBlank ( compimpCausale [i] ) || !isBlank ( strCompimpAnnoAccert [i] ) || !isBlank ( strCompimpNumeroAccert [i] ) ) ) {
						if ( !fieldErrorKeys.contains ( STR_COMP_IMP_IMPORTO + i ) ) {
							addFieldError ( STR_COMP_IMP_IMPORTO + i, compimpError );
						}
					}
					if ( isBlank ( compimpCausale [i] )
									&& ( !isBlank ( strCompimpImporto [i] ) || !isBlank ( strCompimpAnnoAccert [i] ) || !isBlank ( strCompimpNumeroAccert [i] ) ) ) {
						if ( !fieldErrorKeys.contains ( "compimpCausale" + i ) ) {
							addFieldError ( "compimpCausale" + i, compimpError );
						}
					}
					if ( isBlank ( strCompimpAnnoAccert [i] )
									&& ( !isBlank ( compimpCausale [i] ) || !isBlank ( strCompimpImporto [i] ) || !isBlank ( strCompimpNumeroAccert [i] ) ) ) {
						if ( !fieldErrorKeys.contains ( STR_COMP_IMP_ANNO_ACCERT + i ) ) {
							addFieldError ( STR_COMP_IMP_ANNO_ACCERT + i, compimpError );
						}
					}
					if ( isBlank ( strCompimpNumeroAccert [i] )
									&& ( !isBlank ( compimpCausale [i] ) || !isBlank ( strCompimpAnnoAccert [i] ) || !isBlank ( strCompimpImporto [i] ) ) ) {
						if ( !fieldErrorKeys.contains ( STR_COMP_IMP_NUMERO_ACCERT + i ) ) {
							addFieldError ( STR_COMP_IMP_NUMERO_ACCERT + i, compimpError );
						}
					}
				}

				// verifica i riferimenti pagamento
				boolean rifpagAtLeastOne = false;
				for ( int i = 4; i >= 0; i-- ) {
					// verifica che siano interamente valorizzate
					if ( isBlank ( rifpagNome [i] ) && !isBlank ( rifpagValore [i] ) ) {
						addFieldError ( "rifpagNome" + i, compimpError );
					}
					if ( !isBlank ( rifpagNome [i] ) && isBlank ( rifpagValore [i] ) ) {
						addFieldError ( "rifpagValore" + i, compimpError );
					}

					// verifica la presenza di eventuali componenti riferimenti pagamento vuoti
					boolean currentRifpagEmpty = isBlank ( rifpagNome [i] ) && isBlank ( rifpagValore [i] );
					rifpagAtLeastOne |= !currentRifpagEmpty;
					if ( rifpagAtLeastOne && currentRifpagEmpty ) {
						addFieldError ( "rifpagNome" + i, getText ( "riferimento.pagamento.intermedio.vuoto" ) );
					}
				}

				Boolean flagMbPrimario = getCov ( idCodVersamento ).getFlagMbPrimario ();
				if ( null != flagMbPrimario && flagMbPrimario ) {
					multibeneficiario = SI;
				} else {
					multibeneficiario = NO;
				}
				if ( isBlank ( strCompimpImportoTotale ) ) {
                    addFieldError ( STR_COMPI_IMP_IMPORTO_TOTALE, getText ( "importo.totale.mancante" ) );
                }

				if ( multibeneficiario.equalsIgnoreCase ( NO ) ) {
					// verifica l'importo totale come somma degli importi delle componenti
					if ( !isBlank ( strCompimpImportoTotale ) && !fieldErrorKeys.contains ( STR_COMPI_IMP_IMPORTO_TOTALE ) ) {
						compimpImportoTotale = Util.str2importo ( strCompimpImportoTotale );
						if ( compimpAtLeastOne && compimpImportoTotale.compareTo ( compimpSommaImporti ) != 0 ) {
							addFieldError ( STR_COMPI_IMP_IMPORTO_TOTALE,
								getText ( "somma.componenti.importo.non.quadra", new String [] { compimpSommaImporti.toString () } ) );
						}
					}
				} else {
					//somme
					if ( isBlank ( strImportoPrincipale ) ) {
						addFieldError ( "strImportoPrincipale", getText ( "importo.principale.mancante" ) );
					}


					if (!isBlank ( strImportoPrincipale ) && !isBlank ( strCompimpImportoTotale ) ) {
						importoPrincipale = Util.str2importo ( strImportoPrincipale );
						if ( isBlank ( strImportoSecondario ) ) {
							strImportoSecondario = strCompimpImporto0Secondario;
							compimpImportoSecondario = Util.str2importo ( strImportoSecondario );
						}
						if ( isBlank ( strImportoSecondario ) ) {
							addFieldError ( "strImportoSecondario", getText ( "importo.secondario.mancante" ) );
						} else {
							importoSecondario = Util.str2importo ( strImportoSecondario );
							compimpImportoTotale = Util.str2importo ( strCompimpImportoTotale );
							if ( compimpAtLeastOne && compimpImportoTotale.compareTo ( importoPrincipale.add ( importoSecondario ) ) != 0 ) {
								addFieldError ( STR_COMPI_IMP_IMPORTO_TOTALE,
									getText ( "somma.componenti.importo.non.quadra", new String [] { compimpSommaImporti.toString () } ) );
							}
							compimpImportoSecondario = Util.str2importo ( strCompimpImporto0Secondario );
							if ( compimpAtLeastOne && compimpImportoTotale.compareTo ( importoPrincipale.add ( compimpImportoSecondario ) ) != 0 ) {
								addFieldError ( STR_COMPI_IMP_IMPORTO_TOTALE,
									getText ( "somma.componenti.importo.non.quadra", new String [] { strCompimpImporto0Secondario } ) );
							}
						}
					}
					//valori componente secondaria
					if ( isBlank ( strCompimpImporto0Secondario ) ) {
						addFieldError ( "strCompimpImporto0Secondario",
							getText ( "componente.importo.secondaria.vuota", new String [] { strCompimpImporto0Secondario } ) );
					}
					if ( isBlank ( compimpCausale0Secondario ) ) {
						addFieldError ( "compimpCausale0Secondario",
							getText ( "componente.causale.secondaria.vuota", new String [] { compimpCausale0Secondario } ) );
					}
				}

				String soggettoError;
				soggettoTipo = TipologiaSoggettoEnum.fromId(soggettoIdTipo);
				// verifiche sul soggetto debitore
				if ( null != soggettoTipo ) {
					switch (soggettoTipo) {
					case PERSONA_FISICA:
						if ( !isBlank ( soggettoIdUnivocoFiscalePersonaFisica ) ) {
							if ( !Util.checkCodiceFiscaleValido ( soggettoIdUnivocoFiscalePersonaFisica ) ) {
								addFieldError ( "soggettoIdUnivocoFiscalePersonaFisica", "Codice fiscale non valido" );
							}
						}
						soggettoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dal tipo soggetto " + PERSONA_FISICA.getDes () } );
						if ( isBlank ( soggettoIdUnivocoFiscalePersonaFisica ) ) {
							addFieldError ( "soggettoIdUnivocoFiscalePersonaFisica", soggettoError );
						}
						if ( isBlank ( soggettoCognome ) ) {
							addFieldError ( "soggettoCognome", soggettoError );
						}
						if ( isBlank ( soggettoNome ) ) {
							addFieldError ( "soggettoNome", soggettoError );
						}
						break;
					case PERSONA_GIURIDICA:
						if ( !isBlank ( soggettoIdUnivocoFiscalePersonaGiuridica )
										&& !Util.checkCodicePivaValida ( soggettoIdUnivocoFiscalePersonaGiuridica ) ) {
							addFieldError ( "soggettoIdUnivocoFiscalePersonaGiuridica", "Partita iva  non valida" );
						}
						soggettoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dal tipo soggetto " + PERSONA_GIURIDICA.getDes () } );
						if ( isBlank ( soggettoIdUnivocoFiscalePersonaGiuridica ) ) {
							addFieldError ( "soggettoIdUnivocoFiscalePersonaGiuridica", soggettoError );
						}
						if ( isBlank ( soggettoRagioneSociale ) ) {
							addFieldError ( "soggettoRagioneSociale", soggettoError );
						}
						break;
					}
					if ( !isBlank ( soggettoEmail ) && !soggettoEmail.matches ( Util.EMAIL_FORMAT ) ) {
						addFieldError("soggettoEmail", "Formato mail non valido");
					}
					if (!isBlank(soggettoIndirizzo)) {
						final String indirizzoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dall'indirizzo" } );
						if ( isBlank ( soggettoCivico ) ) {
							addFieldError ( SOGGETTO_CIVICO, indirizzoError );
						}
						if ( isBlank ( soggettoCap ) ) {
							addFieldError ( SOGGETTO_CAP, indirizzoError );
						}
						if ( isBlank ( soggettoLocalita ) ) {
							addFieldError ( SOGGETTO_LOCALITA, indirizzoError );
						}
						if ( isBlank ( soggettoProvincia ) ) {
							addFieldError ( SOGGETTO_PROVINCIA, indirizzoError );
						}
						if ( isBlank ( soggettoNazione ) ) {
							addFieldError ( SOGGETTO_NAZIONE, indirizzoError );
						}
					} else if (!isBlank(soggettoCivico)) {
						final String indirizzoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dal civico" } );
						if ( isBlank ( soggettoIndirizzo ) ) {
							addFieldError ( SOGGETTO_INDIRIZZO, indirizzoError );
						}
						if ( isBlank ( soggettoCap ) ) {
							addFieldError ( SOGGETTO_CAP, indirizzoError );
						}
						if ( isBlank ( soggettoLocalita ) ) {
							addFieldError ( SOGGETTO_LOCALITA, indirizzoError );
						}
						if ( isBlank ( soggettoProvincia ) ) {
							addFieldError ( SOGGETTO_PROVINCIA, indirizzoError );
						}
						if ( isBlank ( soggettoNazione ) ) {
							addFieldError ( SOGGETTO_NAZIONE, indirizzoError );
						}
					} else if (!isBlank(soggettoCap)) {
						final String indirizzoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dal CAP" } );
						if ( isBlank ( soggettoIndirizzo ) ) {
							addFieldError ( SOGGETTO_INDIRIZZO, indirizzoError );
						}
						if ( isBlank ( soggettoCivico ) ) {
							addFieldError ( SOGGETTO_CIVICO, indirizzoError );
						}
						if ( isBlank ( soggettoLocalita ) ) {
							addFieldError ( SOGGETTO_LOCALITA, indirizzoError );
						}
						if ( isBlank ( soggettoProvincia ) ) {
							addFieldError ( SOGGETTO_PROVINCIA, indirizzoError );
						}
						if ( isBlank ( soggettoNazione ) ) {
							addFieldError ( SOGGETTO_NAZIONE, indirizzoError );
						}
					} else if (!isBlank(soggettoLocalita)) {
						final String indirizzoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dalla localita'" } );
						if ( isBlank ( soggettoIndirizzo ) ) {
							addFieldError ( SOGGETTO_INDIRIZZO, indirizzoError );
						}
						if ( isBlank ( soggettoCivico ) ) {
							addFieldError ( SOGGETTO_CIVICO, indirizzoError );
						}
						if ( isBlank ( soggettoCap ) ) {
							addFieldError ( SOGGETTO_CAP, indirizzoError );
						}
						if ( isBlank ( soggettoProvincia ) ) {
							addFieldError ( SOGGETTO_PROVINCIA, indirizzoError );
						}
						if ( isBlank ( soggettoNazione ) ) {
							addFieldError ( SOGGETTO_NAZIONE, indirizzoError );
						}
					} else if (!isBlank(soggettoProvincia)) {
						final String indirizzoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dalla provincia" } );
						if ( isBlank ( soggettoIndirizzo ) ) {
							addFieldError ( SOGGETTO_INDIRIZZO, indirizzoError );
						}
						if ( isBlank ( soggettoCivico ) ) {
							addFieldError ( SOGGETTO_CIVICO, indirizzoError );
						}
						if ( isBlank ( soggettoCap ) ) {
							addFieldError ( SOGGETTO_CAP, indirizzoError );
						}
						if ( isBlank ( soggettoLocalita ) ) {
							addFieldError ( SOGGETTO_LOCALITA, indirizzoError );
						}
						if ( isBlank ( soggettoNazione ) ) {
							addFieldError ( SOGGETTO_NAZIONE, indirizzoError );
						}
					} else if (!isBlank(soggettoNazione)) {
						final String indirizzoError = getText ( CAMPO_OBBLIGATORIO_CONDIZIONATO, new String [] { "dalla nazione" } );
						if ( isBlank ( soggettoIndirizzo ) ) {
							addFieldError ( SOGGETTO_INDIRIZZO, indirizzoError );
						}
						if ( isBlank ( soggettoCivico ) ) {
							addFieldError ( SOGGETTO_CIVICO, indirizzoError );
						}
						if ( isBlank ( soggettoCap ) ) {
							addFieldError ( SOGGETTO_CAP, indirizzoError );
						}
						if ( isBlank ( soggettoLocalita ) ) {
							addFieldError ( SOGGETTO_LOCALITA, indirizzoError );
						}
						if ( isBlank ( soggettoProvincia ) ) {
							addFieldError ( SOGGETTO_PROVINCIA, indirizzoError );
						}
					}

				}

				// verifica che almeno un campo sia stato valorizzato
				if ( !fieldErrorKeys.contains ( STR_COMPI_IMP_IMPORTO_TOTALE ) ) {
					if (   dataInizioValidita == null
									&& dataFineValidita == null
									&& dataScadenza == null
									&& compimpImportoTotale == null
									&& isBlank(desCausaleVersamento)
									&& !compimpAtLeastOne
									&& !rifpagAtLeastOne )
					{
						activateValorizzareAlmenoUnCampoTraQuelliSeguenti = true;
						if ( dataInizioValidita == null ) {
							addFieldError ( STR_DATA_INIZIO_VALIDITA, "" );
						}
						if ( dataFineValidita == null ) {
							addFieldError ( STR_DATA_FINE_VALIDITA, "" );
						}
						if ( dataScadenza == null ) {
							addFieldError("strDataScadenza", "");
						}
						if ( compimpImportoTotale == null ) {
							addFieldError ( STR_COMPI_IMP_IMPORTO_TOTALE, "" );
						}
						if ( isBlank ( desCausaleVersamento ) ) {
							addFieldError("desCausaleVersamento", "");
						}
						if ( !compimpAtLeastOne ) {
							addFieldError("strCompimpImporto0", "");
						}
						if ( !rifpagAtLeastOne ) {
							addFieldError ( "rifpagNome0", "" );
						}
					}
				}

				break;
			}

			if ( hasFieldErrors () ) {
				addActionMessage(getText("message.valorizzare.corpo.aggposdeb"));
			}

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			addActionError ( getText ( SYSTEM_ERROR, new String [] { e.getClass ().getName (), e.getMessage () } ) );

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	@Action ( "autocomplete_posizione_debitoria_esterna" )
	@SkipValidation
	public String autocompletePosizioneDebitoriaEsterna () {
		String methodName = "autocompletePosizioneDebitoriaEsterna";

		log.info ( CLASSNAME + " " + methodName + " - START" );

		ActionScope actionScope = getSessionContext ().getActionScope ();
		Integer idCov = actionScope.getValue ( ID_COD_VERSAMENTO );
		SessionContext sessionContext = getSessionContext ();
		EnteDto enteDto = sessionContext.getEnte ();
		Integer idEnte = enteDto.getId ();

		String result;
		try {
			result = "AUTOCOMPLETE";
			PosizioneDebitoriaAutocompleteDto posizioneDebitoriaAutocompleteDto
				= gestioneFlussiBusiness.getPosizioneDebitoriaAutoComplete ( q, idEnte, idCov );
			autocompleteResponse = new AutocompleteResponse ();
			List<String> listPosDebString = new LinkedList<> ();
			List<Long> listPosDebId = new LinkedList<> ();
			for ( PosizioneDebitoriaAutocompleteElementDto dtoElement: posizioneDebitoriaAutocompleteDto.getPosizioneDebitoriaAutocompleteElementDtoList () ) {
				listPosDebString.add ( dtoElement.getIdPosizioneDebitoriaEst () );
				listPosDebId.add ( dtoElement.getIdPosizioneDebitoria () );
			}
			autocompleteResponse.setResult ( listPosDebString );
			autocompleteResponse.setResultIds ( listPosDebId );
		} catch ( BusinessException e ) {
			log.error ( "Errore imprevisto", e );
			addActionError ( getText ( SYSTEM_ERROR, new String [] { e.getClass ().getName (), e.getMessage () } ) );
			result = "system-error";
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Action ( "autocomplete_iuv" )
	@SkipValidation
	public String autocompleteIUV () {
		String methodName = "autocompleteIUV";

		log.info ( CLASSNAME + " " + methodName + " - START" );

		ActionScope actionScope = getSessionContext ().getActionScope ();
		Integer idCov = actionScope.getValue ( ID_COD_VERSAMENTO );
		SessionContext sessionContext = getSessionContext ();
		EnteDto enteDto = sessionContext.getEnte ();
		Integer idEnte = enteDto.getId ();

		String result;
		try {
			result = "AUTOCOMPLETE";
			PosizioneDebitoriaAutocompleteIUVDto posizioneDebitoriaAutocompleteIUVDtO
							= gestioneFlussiBusiness.getPosizioneDebitoriaAutoCompleteIUV ( q, idEnte, idCov );
			autocompleteResponse = new AutocompleteResponse ();
			List<String> listPosDebString = new LinkedList<> ();
			List<Long> listPosDebId = new LinkedList<> ();
			for ( PosizioneDebitoriaAutocompleteIUVElementDto dtoElement: posizioneDebitoriaAutocompleteIUVDtO.getPosizioneDebitoriaAutocompleteIUVElementDtoList () ) {
				listPosDebString.add ( dtoElement.getIuv () );
				listPosDebId.add ( dtoElement.getIdPosizioneDebitoria () );
			}
			autocompleteResponse.setResult ( listPosDebString );
			autocompleteResponse.setResultIds ( listPosDebId );
		} catch ( BusinessException e ) {
			log.error ( "Errore imprevisto", e );
			addActionError ( getText ( SYSTEM_ERROR, new String [] { e.getClass ().getName (), e.getMessage () } ) );
			result = "system-error";
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Action( "caricaPosizione" )
	@SkipValidation
	public String caricaPosizione () {
		String methodName = "caricaPosizione";
		log.info ( CLASSNAME + " " + methodName + " - START" );
		String result;
		try {
			result = "POS_DEB_DA_AGGIORNARE";
			ActionScope actionScope = getSessionContext ().getActionScope ();
			Integer idCov = actionScope.getValue ( ID_COD_VERSAMENTO );
			SessionContext sessionContext = getSessionContext ();
			EnteDto enteDto = sessionContext.getEnte ();
			Integer idEnte = enteDto.getId ();
			PosizioneDebitoriaCaricaRequestDto posizioeDebitoriaCaricaRequestDto
							= new PosizioneDebitoriaCaricaRequestDto ( getIpAddress (), getIdUtente (), getCodiceFiscaleUtente (), getCodiceApplicazione (), idPosDebDaAgg, iuv, idEnte, idCov );
			PosizioneDebitoriaDto posDebDaAggiornare = gestioneFlussiBusiness.getPosizioneDebitoria ( posizioeDebitoriaCaricaRequestDto );
			posizioneDebitoriaDaAggiornarePostAutocomplete = new PosizioneDebitoriaDaAggiornarePostAutocomplete ();
			posizioneDebitoriaDaAggiornarePostAutocomplete.setIdFlusso ( posDebDaAggiornare.getIdFlusso () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setIdPosizioneDebitoriaEsterna ( posDebDaAggiornare.getIdPosizioneDebitoriaEsterna () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setAnnoRiferimento ( posDebDaAggiornare.getAnnoRiferimento () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDataInizioValidita ( posDebDaAggiornare.getDataInizioValidita () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDataFineValidita ( posDebDaAggiornare.getDataFineValidita () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDesRata ( posDebDaAggiornare.getDesRata () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setNotePerPagatore ( posDebDaAggiornare.getNotePerPagatore () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setCodAvviso ( posDebDaAggiornare.getCodAvviso () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setCodEsito ( posDebDaAggiornare.getCodEsito () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDetEsito ( posDebDaAggiornare.getDetEsito () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setComponenteImportoDtoList ( posDebDaAggiornare.getComponenteImportoDtoList () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setRiferimentoPagamentoDtoList ( posDebDaAggiornare.getRiferimentoPagamentoDtoList () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setIuv ( posDebDaAggiornare.getIUV () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setImportoTotale ( posDebDaAggiornare.getImportoTotale () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDesCausaleVersamento ( posDebDaAggiornare.getDesCausaleVersamento () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDataScadenza ( posDebDaAggiornare.getDataScadenza () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setSoggettoDebitore ( posDebDaAggiornare.getSoggettoDebitore () );
			//gest multibeneficiario
			posizioneDebitoriaDaAggiornarePostAutocomplete.setImportoPrincipale ( posDebDaAggiornare.getImportoPrincipale () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setImportoSecondario ( posDebDaAggiornare.getImportoSecondarioAltroEnte () );
			//
			idStringSeleceted = posizioneDebitoriaDaAggiornarePostAutocomplete.getIdPosizioneDebitoriaEsterna ();
			// determinare se e' aggiornabile o meno
			String cov = null;
			for ( CodiceVersamentoDto c : sessionContext.getCodiciVersamento () ) {
				if ( Objects.equals ( c.getId (), idCov ) ) {
					cov = c.getCod ();
					posizioneDebitoriaDaAggiornarePostAutocomplete.setCodDescription ( c.getDes () );
					break;
				}
			}
			String statoPosizioneDebitoria = null;
			try {
//				statoPosizioneDebitoria = EpayapiService.getStatoPosizioneDebitoriaEsterna ( enteDto.getCodFiscale (), cov,
//								posizioneDebitoriaDaAggiornarePostAutocomplete.getIuv () );
				
			    StatoPosizioneDebitoria statoPosizioneDebitoriasrv = new StatoPosizioneDebitoriaImpl  ();
				statoPosizioneDebitoria = statoPosizioneDebitoriasrv.getStatoPosizioneDebitoria( enteDto.getCodFiscale (), cov,
                    posizioneDebitoriaDaAggiornarePostAutocomplete.getIuv () );
			} catch ( IOException e ) {
				log.info ( "Errore nel cercare lo stato della posizione debitoria con id esterno = " + posizioneDebitoriaDaAggiornarePostAutocomplete.getIdPosizioneDebitoriaEsterna () );
			}
			if ( null == statoPosizioneDebitoria ) {
				posizioneDebitoriaDaAggiornarePostAutocomplete.setStato ( "Non trovato" );
				posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( false );
			} else {
				switch ( statoPosizioneDebitoria ) {
				case "2":
					posizioneDebitoriaDaAggiornarePostAutocomplete.setStato ( "In attesa" );
					posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( false );
					break;
				case "4":
					posizioneDebitoriaDaAggiornarePostAutocomplete.setStato ( "Successo" );
					posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( false );
					break;
				case "6":
					posizioneDebitoriaDaAggiornarePostAutocomplete.setStato ( "Transazione inizializzata" );
					posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( false );
					break;
				case "7":
					posizioneDebitoriaDaAggiornarePostAutocomplete.setStato ( "Transazione avviata" );
					posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( false );
					break;
				case "9":
					posizioneDebitoriaDaAggiornarePostAutocomplete.setStato ( "Invalidato dall'ente" );
					posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( false );
					break;
				case "10":
					posizioneDebitoriaDaAggiornarePostAutocomplete.setStato ( "Pagamento revocato" );
					posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( false );
					break;
				default:
					posizioneDebitoriaDaAggiornarePostAutocomplete.setUpdatable ( true );
				}
			}
			ActionContext.getContext ().getSession ().put ( "idStringSeleceted", getIdStringSeleceted () );
			ActionContext.getContext ().getSession ().put ( "idLongSeleceted", getIdLongSelected () );
		} catch ( BusinessException e ) {
			log.error ( "Errore imprevisto", e );
			addActionError ( getText ( SYSTEM_ERROR, new String [] { e.getClass ().getName (), e.getMessage () } ) );
			result = "system-error";
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Action ( "autocomplete_get_posizione_debitoria_esterna" )
	@SkipValidation
	public String autocompleteGetPosizioneDebitoriaEsterna () {
		String methodName = "autocompleteGetPosizioneDebitoriaEsterna";

		log.info ( CLASSNAME + " " + methodName + " - START" );

		String result;
		try {
			result = "POS_DEB_DA_AGGIORNARE";

			long id = Long.parseLong ( idPosDebDaAgg );
			PosizioneDebitoriaRequestDto posizioeDebitoriaRequestDto
				= new PosizioneDebitoriaRequestDto ( getIpAddress (), getIdUtente (), getCodiceFiscaleUtente (), getCodiceApplicazione (), id );
			PosizioneDebitoriaDto posDebDaAggiornare = gestioneFlussiBusiness.getPosizioneDebitoria ( posizioeDebitoriaRequestDto );
			posizioneDebitoriaDaAggiornarePostAutocomplete = new PosizioneDebitoriaDaAggiornarePostAutocomplete ();
			posizioneDebitoriaDaAggiornarePostAutocomplete.setIdFlusso ( posDebDaAggiornare.getIdFlusso () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setIdPosizioneDebitoriaEsterna ( posDebDaAggiornare.getIdPosizioneDebitoriaEsterna () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setAnnoRiferimento ( posDebDaAggiornare.getAnnoRiferimento () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDataInizioValidita ( posDebDaAggiornare.getDataInizioValidita () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDataFineValidita ( posDebDaAggiornare.getDataFineValidita () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDesRata ( posDebDaAggiornare.getDesRata () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setNotePerPagatore ( posDebDaAggiornare.getNotePerPagatore () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setCodAvviso ( posDebDaAggiornare.getCodAvviso () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setCodEsito ( posDebDaAggiornare.getCodEsito () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDetEsito ( posDebDaAggiornare.getDetEsito () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setComponenteImportoDtoList ( posDebDaAggiornare.getComponenteImportoDtoList () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setRiferimentoPagamentoDtoList ( posDebDaAggiornare.getRiferimentoPagamentoDtoList () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setIuv ( posDebDaAggiornare.getIUV () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setImportoTotale ( posDebDaAggiornare.getImportoTotale () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDesCausaleVersamento ( posDebDaAggiornare.getDesCausaleVersamento () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setDataScadenza ( posDebDaAggiornare.getDataScadenza () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setSoggettoDebitore ( posDebDaAggiornare.getSoggettoDebitore () );
			//gest multibeneficiario
			posizioneDebitoriaDaAggiornarePostAutocomplete.setImportoPrincipale ( posDebDaAggiornare.getImportoPrincipale () );
			posizioneDebitoriaDaAggiornarePostAutocomplete.setImportoSecondario ( posDebDaAggiornare.getImportoSecondarioAltroEnte () );
			//
			idStringSeleceted = posizioneDebitoriaDaAggiornarePostAutocomplete.getIdPosizioneDebitoriaEsterna ();
			idLongSelected = id;
			ActionContext.getContext ().getSession ().put ( "idStringSeleceted", getIdStringSeleceted () );
			ActionContext.getContext ().getSession ().put ( "idLongSeleceted", getIdLongSelected () );
		} catch ( BusinessException e ) {
			log.error ( "Errore imprevisto", e );
			addActionError ( getText ( SYSTEM_ERROR, new String [] { e.getClass ().getName (), e.getMessage () } ) );
			result = "system-error";
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public Long getIdPosizioneDebitoriaDaAggiornare() {
		return idPosizioneDebitoriaDaAggiornare;
	}

	public void setIdPosizioneDebitoriaDaAggiornare(Long idPosizioneDebitoriaDaAggiornare) {
		this.idPosizioneDebitoriaDaAggiornare = idPosizioneDebitoriaDaAggiornare;
	}

	public boolean isOrigineHomePerInserimento() {
		return origineHomePerInserimento;
	}

	public void setOrigineHomePerInserimento(boolean origineHomePerInserimento) {
		this.origineHomePerInserimento = origineHomePerInserimento;
	}

	public TipoOperativitaEnum getOperativita() {
		return operativita;
	}

	public void setOperativita(TipoOperativitaEnum operativita) {
		this.operativita = operativita;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public Integer getIdCodVersamento() {
		return idCodVersamento;
	}

	public void setIdCodVersamento(Integer idCodVersamento) {
		this.idCodVersamento = idCodVersamento;
	}

	public Integer getNumeroElementi() {
		return numeroElementi;
	}

	public void setNumeroElementi(Integer numeroElementi) {
		this.numeroElementi = numeroElementi;
	}

	public Integer getAddendoElemento() {
		return addendoElemento;
	}

	public void setAddendoElemento(Integer addendoElemento) {
		this.addendoElemento = addendoElemento;
	}

	public String getAggiornamentoIdTipo() {
		return aggiornamentoIdTipo;
	}

	public void setAggiornamentoIdTipo(String aggiornamentoIdTipo) {
		this.aggiornamentoIdTipo = aggiornamentoIdTipo;
	}

	public String getIdPosizioneDebitoriaEsterna() {
		return idPosizioneDebitoriaEsterna;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setIdPosizioneDebitoriaEsterna(String idPosizioneDebitoriaEsterna) {
		this.idPosizioneDebitoriaEsterna = idPosizioneDebitoriaEsterna;
	}

	public String getMotivazione() {
		return motivazione;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public String getStrDataInizioValidita() {
		return strDataInizioValidita;
	}

	public void setStrDataInizioValidita(String strDataInizioValidita) {
		this.strDataInizioValidita = strDataInizioValidita;
	}

	public String getStrDataFineValidita() {
		return strDataFineValidita;
	}

	public void setStrDataFineValidita(String strDataFineValidita) {
		this.strDataFineValidita = strDataFineValidita;
	}

	private void setDataInizioEFineValiditaAndCheckPrecedenza() throws ParseException {
		if (!isBlank(strDataInizioValidita) && !isBlank(strDataFineValidita)) {
			dataInizioValidita = sdf.parse(strDataInizioValidita);
			dataFineValidita = sdf.parse(strDataFineValidita);
			if (dataInizioValidita.after(dataFineValidita)) {
				addFieldError ( STR_DATA_INIZIO_VALIDITA, getText ( "message.verificare.precedenza.date" ) );
				addFieldError ( STR_DATA_FINE_VALIDITA, getText ( "message.verificare.precedenza.date" ) );
			}
		}
	}

	public String getStrDataScadenza() {
		return strDataScadenza;
	}

	public void setStrDataScadenza(String strDataScadenza) {
		this.strDataScadenza = strDataScadenza;
	}

	public String getStrCompimpImportoTotale() {
		return strCompimpImportoTotale;
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImportoTotale(String strCompimpImportoTotale) {
		this.strCompimpImportoTotale = strCompimpImportoTotale;
	}

	public String getDesCausaleVersamento() {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public Long getCompimpId0() {
		return compimpId[0];
	}

	public void setCompimpId0(Long compimpId) {
		this.compimpId[0] = compimpId;
	}

	public String getStrCompimpImporto0() {
		return strCompimpImporto[0];
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImporto0(String strCompimpImporto) {
		this.strCompimpImporto[0] = strCompimpImporto;
	}

	public String getCompimpCausale0() {
		return compimpCausale[0];
	}

	public void setCompimpCausale0(String compimpCausale) {
		this.compimpCausale[0] = compimpCausale;
	}

	public String getCompimpDatiSpec0() {
		return compimpDatiSpec[0];
	}

	public void setCompimpDatiSpec0(String compimpDatiSpec) {
		this.compimpDatiSpec[0] = compimpDatiSpec;
	}

	public String getStrCompimpAnnoAccert0 () {
		return strCompimpAnnoAccert [0];
	}

	@RegexFieldValidator ( key = "campo.formato.anno", regex = "^\\d{1,4}" )
	public void setStrCompimpAnnoAccert0 ( String strCompimpAnnoAccert ) {
		this.strCompimpAnnoAccert [0] = strCompimpAnnoAccert;
	}

	public String getStrCompimpNumeroAccert0 () {
		return strCompimpNumeroAccert [0];
	}

	public void setStrCompimpNumeroAccert0 ( String strCompimpNumeroAccert ) {
		this.strCompimpNumeroAccert [0] = strCompimpNumeroAccert;
	}

	public Long getCompimpId1() {
		return compimpId[1];
	}

	public void setCompimpId1(Long compimpId) {
		this.compimpId[1] = compimpId;
	}

	public String getStrCompimpImporto1() {
		return strCompimpImporto[1];
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImporto1(String strCompimpImporto) {
		this.strCompimpImporto[1] = strCompimpImporto;
	}

	public String getCompimpCausale1() {
		return compimpCausale[1];
	}

	public void setCompimpCausale1(String compimpCausale) {
		this.compimpCausale[1] = compimpCausale;
	}

	public String getCompimpDatiSpec1() {
		return compimpDatiSpec[1];
	}

	public void setCompimpDatiSpec1(String compimpDatiSpec) {
		this.compimpDatiSpec[1] = compimpDatiSpec;
	}

	public String getStrCompimpAnnoAccert1 () {
		return strCompimpAnnoAccert [1];
	}

	@RegexFieldValidator ( key = "campo.formato.anno", regex = "^\\d{1,4}" )
	public void setStrCompimpAnnoAccert1 ( String strCompimpAnnoAccert ) {
		this.strCompimpAnnoAccert [1] = strCompimpAnnoAccert;
	}

	public String getStrCompimpNumeroAccert1 () {
		return strCompimpNumeroAccert [1];
	}

	public void setStrCompimpNumeroAccert1 ( String strCompimpNumeroAccert ) {
		this.strCompimpNumeroAccert [1] = strCompimpNumeroAccert;
	}
	public Long getCompimpId2() {
		return compimpId[2];
	}

	public void setCompimpId2(Long compimpId) {
		this.compimpId[2] = compimpId;
	}

	public String getStrCompimpImporto2() {
		return strCompimpImporto[2];
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImporto2(String strCompimpImporto) {
		this.strCompimpImporto[2] = strCompimpImporto;
	}

	public String getCompimpCausale2() {
		return compimpCausale[2];
	}

	public void setCompimpCausale2(String compimpCausale) {
		this.compimpCausale[2] = compimpCausale;
	}

	public String getCompimpDatiSpec2() {
		return compimpDatiSpec[2];
	}

	public void setCompimpDatiSpec2(String compimpDatiSpec) {
		this.compimpDatiSpec[2] = compimpDatiSpec;
	}

	public String getStrCompimpAnnoAccert2 () {
		return strCompimpAnnoAccert [2];
	}

	@RegexFieldValidator ( key = "campo.formato.anno", regex = "^\\d{1,4}" )
	public void setStrCompimpAnnoAccert2 ( String strCompimpAnnoAccert ) {
		this.strCompimpAnnoAccert [2] = strCompimpAnnoAccert;
	}

	public String getStrCompimpNumeroAccert2 () {
		return strCompimpNumeroAccert [2];
	}

	public void setStrCompimpNumeroAccert2 ( String strCompimpNumeroAccert ) {
		this.strCompimpNumeroAccert [2] = strCompimpNumeroAccert;
	}

	public Long getCompimpId3() {
		return compimpId[3];
	}

	public void setCompimpId3(Long compimpId) {
		this.compimpId[3] = compimpId;
	}

	public String getStrCompimpImporto3() {
		return strCompimpImporto[3];
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImporto3(String strCompimpImporto) {
		this.strCompimpImporto[3] = strCompimpImporto;
	}

	public String getCompimpCausale3() {
		return compimpCausale[3];
	}

	public void setCompimpCausale3(String compimpCausale) {
		this.compimpCausale[3] = compimpCausale;
	}

	public String getCompimpDatiSpec3() {
		return compimpDatiSpec[3];
	}

	public void setCompimpDatiSpec3(String compimpDatiSpec) {
		this.compimpDatiSpec[3] = compimpDatiSpec;
	}

	public String getStrCompimpAnnoAccert3 () {
		return strCompimpAnnoAccert [3];
	}

	@RegexFieldValidator ( key = "campo.formato.anno", regex = "^\\d{1,4}" )
	public void setStrCompimpAnnoAccert3 ( String strCompimpAnnoAccert ) {
		this.strCompimpAnnoAccert [3] = strCompimpAnnoAccert;
	}

	public String getStrCompimpNumeroAccert3 () {
		return strCompimpNumeroAccert [3];
	}

	public void setStrCompimpNumeroAccert3 ( String strCompimpNumeroAccert ) {
		this.strCompimpNumeroAccert [3] = strCompimpNumeroAccert;
	}
	public Long getCompimpId4() {
		return compimpId[4];
	}

	public void setCompimpId4(Long compimpId) {
		this.compimpId[4] = compimpId;
	}

	public String getStrCompimpImporto4() {
		return strCompimpImporto[4];
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImporto4(String strCompimpImporto) {
		this.strCompimpImporto[4] = strCompimpImporto;
	}

	public String getCompimpCausale4() {
		return compimpCausale[4];
	}

	public void setCompimpCausale4(String compimpCausale) {
		this.compimpCausale[4] = compimpCausale;
	}

	public String getCompimpDatiSpec4() {
		return compimpDatiSpec[4];
	}

	public void setCompimpDatiSpec4(String compimpDatiSpec) {
		this.compimpDatiSpec[4] = compimpDatiSpec;
	}

	public String getStrCompimpAnnoAccert4 () {
		return strCompimpAnnoAccert [4];
	}

	@RegexFieldValidator ( key = "campo.formato.anno", regex = "^\\d{1,4}" )
	public void setStrCompimpAnnoAccert4 ( String strCompimpAnnoAccert ) {
		this.strCompimpAnnoAccert [4] = strCompimpAnnoAccert;
	}

	public String getStrCompimpNumeroAccert4 () {
		return strCompimpNumeroAccert [4];
	}

	public void setStrCompimpNumeroAccert4 ( String strCompimpNumeroAccert ) {
		this.strCompimpNumeroAccert [4] = strCompimpNumeroAccert;
	}

	public Long getRifpagId0 () {
		return rifpagId [0];
	}

	public void setRifpagId0 ( Long rifpagId ) {
		this.rifpagId [0] = rifpagId;
	}

	public String getRifpagNome0 () {
		return rifpagNome [0];
	}

	public void setRifpagNome0 ( String rifpagNome ) {
		this.rifpagNome [0] = rifpagNome;
	}

	public String getRifpagValore0 () {
		return rifpagValore [0];
	}

	public void setRifpagValore0 ( String rifpagValore ) {
		this.rifpagValore [0] = rifpagValore;
	}

	public Long getRifpagId1 () {
		return rifpagId [1];
	}

	public void setRifpagId1 ( Long rifpagId ) {
		this.rifpagId [1] = rifpagId;
	}

	public String getRifpagNome1 () {
		return rifpagNome [1];
	}

	public void setRifpagNome1 ( String rifpagNome ) {
		this.rifpagNome [1] = rifpagNome;
	}

	public String getRifpagValore1 () {
		return rifpagValore [1];
	}

	public void setRifpagValore1 ( String rifpagValore ) {
		this.rifpagValore [1] = rifpagValore;
	}

	public Long getRifpagId2 () {
		return rifpagId [2];
	}

	public void setRifpagId2 ( Long rifpagId ) {
		this.rifpagId [2] = rifpagId;
	}

	public String getRifpagNome2 () {
		return rifpagNome [2];
	}

	public void setRifpagNome2 ( String rifpagNome ) {
		this.rifpagNome [2] = rifpagNome;
	}

	public String getRifpagValore2 () {
		return rifpagValore [2];
	}

	public void setRifpagValore2 ( String rifpagValore ) {
		this.rifpagValore [2] = rifpagValore;
	}

	public Long getRifpagId3 () {
		return rifpagId [3];
	}

	public void setRifpagId3 ( Long rifpagId ) {
		this.rifpagId [3] = rifpagId;
	}

	public String getRifpagNome3 () {
		return rifpagNome [3];
	}

	public void setRifpagNome3 ( String rifpagNome ) {
		this.rifpagNome [3] = rifpagNome;
	}

	public String getRifpagValore3 () {
		return rifpagValore [3];
	}

	public void setRifpagValore3 ( String rifpagValore ) {
		this.rifpagValore [3] = rifpagValore;
	}

	public Long getRifpagId4 () {
		return rifpagId [4];
	}

	public void setRifpagId4 ( Long rifpagId ) {
		this.rifpagId [4] = rifpagId;
	}

	public String getRifpagNome4 () {
		return rifpagNome [4];
	}

	public void setRifpagNome4 ( String rifpagNome ) {
		this.rifpagNome [4] = rifpagNome;
	}

	public String getRifpagValore4 () {
		return rifpagValore [4];
	}

	public void setRifpagValore4 ( String rifpagValore ) {
		this.rifpagValore [4] = rifpagValore;
	}

	public List<StrutsOptionsSupport> getAggiornamentoIdTipoList() {
		return aggiornamentoIdTipoList;
	}

	public void setAggiornamentoIdTipoList(List<StrutsOptionsSupport> aggiornamentoIdTipoList) {
		this.aggiornamentoIdTipoList = aggiornamentoIdTipoList;
	}

	public boolean isActivateValorizzareAlmenoUnCampoTraQuelliSeguenti() {
		return activateValorizzareAlmenoUnCampoTraQuelliSeguenti;
	}

	public void setActivateValorizzareAlmenoUnCampoTraQuelliSeguenti(boolean activateValorizzareAlmenoUnCampoTraQuelliSeguenti) {
		this.activateValorizzareAlmenoUnCampoTraQuelliSeguenti = activateValorizzareAlmenoUnCampoTraQuelliSeguenti;
	}

	public GestioneFlussiBusiness getGestioneFlussiBusiness() {
		return gestioneFlussiBusiness;
	}

	public void setGestioneFlussiBusiness(GestioneFlussiBusiness gestioneFlussiBusiness) {
		this.gestioneFlussiBusiness = gestioneFlussiBusiness;
	}

	public String getSoggettoIdTipo() {
		return soggettoIdTipo;
	}

	public void setSoggettoIdTipo(String soggettoIdTipo) {
		this.soggettoIdTipo = soggettoIdTipo;
	}

	public TipologiaSoggettoEnum getSoggettoTipo() {
		return soggettoTipo;
	}

	public void setSoggettoTipo(TipologiaSoggettoEnum soggettoTipo) {
		this.soggettoTipo = soggettoTipo;
	}

	public String getSoggettoIdUnivocoFiscalePersonaFisica() {
		return soggettoIdUnivocoFiscalePersonaFisica;
	}

	public void setSoggettoIdUnivocoFiscalePersonaFisica(String soggettoIdUnivocoFiscalePersonaFisica) {
		this.soggettoIdUnivocoFiscalePersonaFisica = soggettoIdUnivocoFiscalePersonaFisica;
	}

	public String getSoggettoCognome() {
		return soggettoCognome;
	}

	public void setSoggettoCognome(String soggettoCognome) {
		this.soggettoCognome = soggettoCognome;
	}

	public String getSoggettoNome() {
		return soggettoNome;
	}

	public void setSoggettoNome(String soggettoNome) {
		this.soggettoNome = soggettoNome;
	}

	public String getSoggettoIdUnivocoFiscalePersonaGiuridica() {
		return soggettoIdUnivocoFiscalePersonaGiuridica;
	}

	public void setSoggettoIdUnivocoFiscalePersonaGiuridica(String soggettoIdUnivocoFiscalePersonaGiuridica) {
		this.soggettoIdUnivocoFiscalePersonaGiuridica = soggettoIdUnivocoFiscalePersonaGiuridica;
	}

	public String getSoggettoRagioneSociale() {
		return soggettoRagioneSociale;
	}

	public void setSoggettoRagioneSociale(String soggettoRagioneSociale) {
		this.soggettoRagioneSociale = soggettoRagioneSociale;
	}

	public List<StrutsOptionsSupport> getSoggettoIdTipoList() {
		return soggettoIdTipoList;
	}

	public void setSoggettoIdTipoList(List<StrutsOptionsSupport> soggettoIdTipoList) {
		this.soggettoIdTipoList = soggettoIdTipoList;
	}

	public List<StrutsOptionsSupport> getFakeList() {
		return fakeList;
	}

	public List<StrutsOptionsSupport> getFakeListIUV () {
		return fakeListIUV;
	}

	public void setFakeListIUV ( List<StrutsOptionsSupport> fakeListIUV ) {
		this.fakeListIUV = fakeListIUV;
	}

	public void setFakeList(List<StrutsOptionsSupport> fakeList) {
		this.fakeList = fakeList;
	}

	public String getSoggettoIndirizzo() {
		return soggettoIndirizzo;
	}

	public void setSoggettoIndirizzo(String soggettoIndirizzo) {
		this.soggettoIndirizzo = soggettoIndirizzo;
	}

	public String getSoggettoCivico() {
		return soggettoCivico;
	}

	public void setSoggettoCivico(String soggettoCivico) {
		this.soggettoCivico = soggettoCivico;
	}

	public String getSoggettoCap() {
		return soggettoCap;
	}

	public void setSoggettoCap(String soggettoCap) {
		this.soggettoCap = soggettoCap;
	}

	public String getSoggettoLocalita() {
		return soggettoLocalita;
	}

	public void setSoggettoLocalita(String soggettoLocalita) {
		this.soggettoLocalita = soggettoLocalita;
	}

	public String getSoggettoProvincia() {
		return soggettoProvincia;
	}

	public void setSoggettoProvincia(String soggettoProvincia) {
		this.soggettoProvincia = soggettoProvincia;
	}

	public String getSoggettoNazione() {
		return soggettoNazione;
	}

	public void setSoggettoNazione(String soggettoNazione) {
		this.soggettoNazione = soggettoNazione;
	}

	public String getSoggettoEmail() {
		return soggettoEmail;
	}

	public AutocompleteResponse getAutocompleteResponse() {
		return autocompleteResponse;
	}

	public void setAutocompleteResponse(AutocompleteResponse autocompleteResponse) {
		this.autocompleteResponse = autocompleteResponse;
	}

	public PosizioneDebitoriaDaAggiornarePostAutocomplete getPosizioneDebitoriaDaAggiornarePostAutocomplete () {
		return posizioneDebitoriaDaAggiornarePostAutocomplete;
	}

	public void
	setPosizioneDebitoriaDaAggiornarePostAutocomplete ( PosizioneDebitoriaDaAggiornarePostAutocomplete posizioneDebitoriaDaAggiornarePostAutocomplete ) {
		this.posizioneDebitoriaDaAggiornarePostAutocomplete = posizioneDebitoriaDaAggiornarePostAutocomplete;
	}

	public String getIdPosDebDaAgg () {
		return idPosDebDaAgg;
	}

	public void setIdPosDebDaAgg ( String idPosDebDaAgg ) {
		this.idPosDebDaAgg = idPosDebDaAgg;
	}

	public String getQ () {
		return q;
	}

	public void setQ ( String q ) {
		this.q = q;
	}

//	@RegexFieldValidator(key = "campo.formato.email", regex = "[A-Za-z0-9_]+([\\-\\+\\.'][A-Za-z0-9_]+)*@[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*\\.[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*")
	@RegexFieldValidator(key = "campo.formato.email", regex = "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*")
	public void setSoggettoEmail(String soggettoEmail) {
		this.soggettoEmail = soggettoEmail;
	}

	public String getMultibeneficiario () {
		return multibeneficiario;
	}

	public void setMultibeneficiario ( String multibeneficiario ) {
		this.multibeneficiario = multibeneficiario;
	}

	public String getStrImportoPrincipale () {
		return strImportoPrincipale;
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrImportoPrincipale ( String strImportoPrincipale ) {
		this.strImportoPrincipale = strImportoPrincipale;
	}

	public String getStrImportoSecondario () {
		return strImportoSecondario;
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrImportoSecondario ( String strImportoSecondario ) {
		this.strImportoSecondario = strImportoSecondario;
	}

	public String getIdPosizioneDebitoriaEsterna_text () {
		return idPosizioneDebitoriaEsterna_text;
	}

	public void setIdPosizioneDebitoriaEsterna_text ( String idPosizioneDebitoriaEsternaText ) {
		this.idPosizioneDebitoriaEsterna_text = idPosizioneDebitoriaEsternaText;
	}

	public String getIdStringSeleceted () {
		return idStringSeleceted;
	}

	public void setIdStringSeleceted ( String idStringSeleceted ) {
		this.idStringSeleceted = idStringSeleceted;
	}

	public Long getIdLongSelected () {
		return idLongSelected;
	}

	public void setIdLongSelected ( Long idLongSelected ) {
		this.idLongSelected = idLongSelected;
	}

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public BigDecimal getImportoSecondario () {
		return importoSecondario;
	}

	public void setImportoSecondario ( BigDecimal importoSecondario ) {
		this.importoSecondario = importoSecondario;
	}

	public String getStrCompimpImporto0Secondario () {
		return strCompimpImporto0Secondario;
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImporto0Secondario ( String strCompimpImporto0Secondario ) {
		this.strCompimpImporto0Secondario = strCompimpImporto0Secondario;
	}

	public String getCompimpCausale0Secondario () {
		return compimpCausale0Secondario;
	}

	public void setCompimpCausale0Secondario ( String compimpCausale0Secondario ) {
		this.compimpCausale0Secondario = compimpCausale0Secondario;
	}

	public BigDecimal getCompimpImportoSecondario () {
		return compimpImportoSecondario;
	}

	public void setCompimpImportoSecondario ( BigDecimal compimpImportoSecondario ) {
		this.compimpImportoSecondario = compimpImportoSecondario;
	}

	public Long getCompimpId0Secondario () {
		return compimpId0Secondario;
	}

	public void setCompimpId0Secondario ( Long compimpId0Secondario ) {
		this.compimpId0Secondario = compimpId0Secondario;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getIuv_text () {
		return iuv_text;
	}

	public void setIuv_text ( String iuv_text ) {
		this.iuv_text = iuv_text;
	}
}
