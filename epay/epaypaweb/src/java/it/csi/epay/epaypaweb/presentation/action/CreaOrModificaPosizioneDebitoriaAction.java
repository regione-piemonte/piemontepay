/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum.PERSONA_FISICA;
import static it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum.PERSONA_GIURIDICA;
import static it.csi.epay.epaypaweb.util.Util.blank2null;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.dto.UtenteDto;
import it.csi.epay.epaypaweb.dto.common.PosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.dto.common.SalvaPosizioneDebitoriaRequestDto;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;

import it.csi.epay.epaypaweb.util.Util;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
	//@formatter:off
	@Result(name = INPUT, location = "pages/crea-or-modifica-posizionedebitoria.jsp"),
	@Result(name = SUCCESS, type = "redirect", location = "entry-visualizza-listadicarico.do", params = { "init", "false", "idFlusso", "${idFlusso}", "esitoElaborazione", "${esitoElaborazione}", "messaggioEsitoElaborazione", "${messaggioEsitoElaborazione}" } ),
	//@formatter:on
})
public class CreaOrModificaPosizioneDebitoriaAction extends EpaypawebBaseAction implements Preparable {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = CreaOrModificaPosizioneDebitoriaAction.class.getSimpleName ();

	// per ottenere o creare la posizione debitoria
	private Long idFlusso;
	private Long idPosizioneDebitoria;

	// flag per sapere se l'inserimento risulta attivato dalla home o dalla ricerca flusso
	private boolean origineHomePerInserimento;
	private TipoOperativitaEnum operativita;

	// campi in sola lettura della testata del flusso in inserimento o modifica
	private String idMessaggio;
	private Integer idCodVersamento;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private Integer numeroElementi;
	private Integer addendoElemento;
	private BigDecimal importoTotalePosizioniDebitorie;
	//
	// campi usati per la validazione
	private String idPosizioneDebitoriaEsterna;
	private BigDecimal compimpImportoTotale;
	private BigDecimal compimpImportoTotaleOriginal;
	private String strCompimpImportoTotale;
	private String desCausaleVersamento;
	private Date dataScadenza;
	private String strDataScadenza;
	private Integer annoRiferimento;
	private String strAnnoRiferimento;
	private String desRata;
	private String notePerPagatore;
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

	private List<StrutsOptionsSupport> soggettoIdTipoList;

	private String multibeneficiario = NO;
	private BigDecimal compimpImportoTotaleOriginalPrincipale;
	private String strCompimpImportoTotalePrincipale;
	private BigDecimal importoSecondario;
	private String strImportoSecondario;
	private Long[] compimpIdSecondario;
	private BigDecimal[] compimpImportoSecondario;
	private String[] strCompimpImportoSecondario;
	private String[] compimpCausaleSecondario;

	private static final String SI = "SI";
	private static final String NO = "NO";

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

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
		compimpIdSecondario = new Long [1];

		soggettoIdTipoList = new ArrayList<> ();
		soggettoIdTipoList.add(new StrutsOptionsSupport(PERSONA_FISICA.getId(), PERSONA_FISICA.getDes()));
		soggettoIdTipoList.add(new StrutsOptionsSupport(PERSONA_GIURIDICA.getId(), PERSONA_GIURIDICA.getDes()));
		strCompimpImportoSecondario = new String[1];
		compimpCausaleSecondario = new String[1];

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
	}

	private void commonInit() throws BusinessException {
		PosizioneDebitoriaDto posdeb;
		if (idPosizioneDebitoria != null) {
			// ottiene la posizione debitoria da modificare
			PosizioneDebitoriaRequestDto posizioneDebitoriaRequestDto = new PosizioneDebitoriaRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idPosizioneDebitoria);
			posdeb = gestioneFlussiBusiness.getPosizioneDebitoria(posizioneDebitoriaRequestDto);
			idPosizioneDebitoria = posdeb.getId ();
			idPosizioneDebitoriaEsterna = posdeb.getIdPosizioneDebitoriaEsterna();
			compimpImportoTotale = posdeb.getImportoTotale();
			strCompimpImportoTotale = Util.bd2str(compimpImportoTotale);
			compimpImportoTotaleOriginal = posdeb.getImportoTotale();
			desCausaleVersamento = posdeb.getDesCausaleVersamento();
			dataScadenza = posdeb.getDataScadenza();
			strDataScadenza = Util.date2strdate(dataScadenza);
			annoRiferimento = posdeb.getAnnoRiferimento ();
			strAnnoRiferimento = Util.int2str ( annoRiferimento );
			desRata = posdeb.getDesRata();
			notePerPagatore = posdeb.getNotePerPagatore();
			importoSecondario = posdeb.getImportoSecondarioAltroEnte ();
			strImportoSecondario = Util.bd2str ( importoSecondario );
			strCompimpImportoTotalePrincipale = Util.bd2str ( posdeb.getImportoPrincipale () );
			//
			List<ComponenteImportoDto> componenteImportoList = posdeb.getComponenteImportoDtoList();
			if (componenteImportoList != null) {
				compimpId = new Long[5];
				compimpImporto = new BigDecimal[5];
				strCompimpImporto = new String[5];
				strCompimpImportoSecondario = new String[1];
				compimpCausale = new String[5];
				compimpCausaleSecondario = new String[1];
				compimpDatiSpec = new String[5];
				compimpAnnoAccert = new Integer [5];
				strCompimpAnnoAccert = new String [5];
				compimpNumeroAccert = new Integer [5];
				strCompimpNumeroAccert = new String [5];
				compimpImportoSecondario = new BigDecimal[1];
				int i = 0;
				for (ComponenteImportoDto dto : componenteImportoList) {
					if (dto != null) {
						if ( dto.getFlagComponenteSecondario () != null && dto.getFlagComponenteSecondario () ) {
							compimpImportoSecondario [0] = dto.getImporto ();
							strCompimpImportoSecondario [0] = Util.bd2str ( compimpImportoSecondario [0] );
							compimpCausaleSecondario [0] = dto.getCausale ();
							continue;
						}
						compimpId[i] = dto.getId();
						compimpImporto[i] = dto.getImporto();
						strCompimpImporto[i] = Util.bd2str(compimpImporto[i]);
						compimpCausale[i] = dto.getCausale();
						compimpAnnoAccert [i] = dto.getAnnoAccertamento ();
						strCompimpAnnoAccert [i] = Util.int2str ( compimpAnnoAccert [i] );
						compimpNumeroAccert [i] = dto.getNumeroAccertamento ();
						strCompimpNumeroAccert [i] = Util.int2str ( compimpNumeroAccert [i] );
						i++;
					}
				}
			}

			List<RiferimentoPagamentoDto> riferimentoPagamentoList = posdeb.getRiferimentoPagamentoDtoList ();
			if ( riferimentoPagamentoList != null ) {
				rifpagId = new Long [5];
				rifpagNome = new String [5];
				rifpagValore = new String [5];
				int i = 0;
				for ( RiferimentoPagamentoDto dto: riferimentoPagamentoList ) {
					if ( dto != null ) {
						rifpagId [i] = dto.getId ();
						rifpagNome [i] = dto.getNome ();
						rifpagValore [i] = dto.getValore ();
						i++;
					}
				}
			}
			SoggettoAnagraficoDto soggettoDebitore = posdeb.getSoggettoDebitore();
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

		} else {
			// inizializza posizione debitoria
			ActionScope actionScope = getSessionContext ().getActionScope ();
			multibeneficiario = actionScope.getValue ( "multibeneficiario" );
			compimpImportoTotaleOriginal = new BigDecimal(0);
			soggettoIdTipo = PERSONA_FISICA.getId();
			
			/* DEV/CSI_PAG-2411 - BEGIN */
			// proposizione su Caricamento Singolo della chiave generata automaticamente
			SessionContext sessionContext = getSessionContext ();
			String codFiscaleEnte = sessionContext.getEnte ().getCodFiscale ();
			Long idUtente = sessionContext.getUtente ().getId ();
			idPosizioneDebitoriaEsterna = Util.buildIdPosioneDebitoriaEsterna( codFiscaleEnte, idUtente );
			/* DEV/CSI_PAG-2411 - END */
		}
	}

	@Action("entry-creaormodifica-posizionedebitoria")
	@Authorization(cdu = "INS_LDC")
	@SkipValidation
	public String entryCreaOrModificaPosizioneDebitoria() {
		String methodName = "entryCreaOrModificaPosizioneDebitoria";
		

		String result;

		try {//prima volta che atterri in pagina inserimento
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// riottiene l'idMessaggio eventualmente aggiornato (incrementato)
			if (idFlusso != null)
				idMessaggio = gestioneFlussiBusiness.getFlussoLight(idFlusso).getIdMessaggio();

			// salva in sessione i valori iniziali della testata in sola lettura per poterli ripristinarli se richiesto
			ActionScope actionScope = getSessionContext().getActionScope();
			actionScope.putValue("idFlusso", idFlusso);
			actionScope.putValue ( "idPosizioneDebitoria", idPosizioneDebitoria );
			actionScope.putValue("idMessaggio", idMessaggio);
			actionScope.putValue("idCodVersamento", idCodVersamento);
			Boolean flagMbPrimario = super.getCov ( idCodVersamento ).getFlagMbPrimario ();
			if ( null != flagMbPrimario && flagMbPrimario ) {
				actionScope.putValue ( "multibeneficiario", SI );
				multibeneficiario = SI;
			} else {
				actionScope.putValue ( "multibeneficiario", NO );
				multibeneficiario = NO;
			}
			actionScope.putValue("dataInizioValidita", dataInizioValidita);
			actionScope.putValue("dataFineValidita", dataFineValidita);
			actionScope.putValue("numeroElementi", numeroElementi);
			actionScope.putValue("addendoElemento", addendoElemento);
			actionScope.putValue("importoTotalePosizioniDebitorie", importoTotalePosizioniDebitorie);
			actionScope.putValue("compimpImportoTotaleOriginal", compimpImportoTotaleOriginal);

			commonInit();

			/* DEV/CSI_PAG-2411 - BEGIN */
			actionScope.putValue("idPosizioneDebitoriaEsterna", idPosizioneDebitoriaEsterna);
			/* DEV/CSI_PAG-2411 - END */

			result = INPUT;

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	@Action("ripristina_posizionedebitoria")
	@Authorization(cdu = "INS_LDC")
	@SkipValidation
	public String ripristinaPosizioneDebitoria() {
		String methodName = "ripristinaPosizioneDebitoria";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		// riottiene dalla sessione i dati originali della testata in sola lettura
		ActionScope actionScope = getSessionContext().getActionScope();
		idPosizioneDebitoria = actionScope.getValue("idPosizioneDebitoria");
		idFlusso = actionScope.getValue("idFlusso");
		idMessaggio = actionScope.getValue("idMessaggio");
		idCodVersamento = actionScope.getValue("idCodVersamento");
		multibeneficiario = actionScope.getValue("multibeneficiario");
		dataInizioValidita = actionScope.getValue("dataInizioValidita");
		dataFineValidita = actionScope.getValue("dataFineValidita");
		numeroElementi = actionScope.getValue("numeroElementi");
		addendoElemento = actionScope.getValue("addendoElemento");
		importoTotalePosizioniDebitorie = actionScope.getValue("importoTotalePosizioniDebitorie");
		compimpImportoTotaleOriginal = actionScope.getValue("compimpImportoTotaleOriginal");

		String result = null;
		try {
			commonInit();
			result = INPUT;

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	@Action("salva-posizionedebitoria")
	@Authorization(cdu = "INS_LDC")
	public String salvaPosizioneDebitoria() {
		String methodName = "salvaPosizioneDebitoria";
		

		String result;

		try {// on click on save posizione debitoria da pagina inserimento
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// costruisce il dto
			SessionContext sessionContext = getSessionContext();
			UtenteDto utente = sessionContext.getUtente();
			EnteDto ente = sessionContext.getEnte();
			String codVersamento = super.getCodVersamento(idCodVersamento);
			Long idPosizioneDebitoriaLong = sessionContext.getActionScope().getValue("idPosizioneDebitoria");

			//TODO CONTROLLARE SE QUESTO CONTROLLO SERVIVA VERAMENTE
			//controllo se la la posizione debitoria e' gia' presente su DB
//			Long numTrovate = gestioneFlussiBusiness.countAllPosizioneDebitoriaByIdEnteIdCovPosDebEst ( idPosizioneDebitoriaEsterna.trim (), ente.getId (), idCodVersamento );
//			if(numTrovate.compareTo ( 0L ) != 0) {
//				throw new BusinessException ( "Identificativo posizioneDebitoria esterna presente in base dati!" );
//			}
			//
			FlussoDto flusso = new FlussoDto(idFlusso);
			flusso.setTipoFlusso(TipoFlussoEnum.LISTE_DI_CARICO);
			flusso.setStatoFlusso(StatoFlussoEnum.BOZZA);
			flusso.setUtenteInserimento(utente.getCod());
			flusso.setUtenteUltimaVariazione(utente.getCod());
			flusso.setCodFiscaleEnte(ente.getCodFiscale());
			flusso.setCodVersamento(codVersamento);
			flusso.setNumeroElementi(numeroElementi + addendoElemento);
			flusso.setImportoTotale(importoTotalePosizioniDebitorie.subtract(compimpImportoTotaleOriginal).add(compimpImportoTotale));
			flusso.setPagamentiSpontanei(false);
			//
			PosizioneDebitoriaDto posdeb = new PosizioneDebitoriaDto(idPosizioneDebitoriaLong);
			posdeb.setIdFlusso(idFlusso);
			posdeb.setDataInizioValidita(dataInizioValidita);
			posdeb.setDataFineValidita(dataFineValidita);
			posdeb.setIdPosizioneDebitoriaEsterna(idPosizioneDebitoriaEsterna.trim());  // N.B. necessario trim dato chee lato sportello fa il trim
			posdeb.setImportoTotale(compimpImportoTotale);
			posdeb.setDesCausaleVersamento(blank2null(desCausaleVersamento));
			posdeb.setDataScadenza(dataScadenza);
			posdeb.setAnnoRiferimento(annoRiferimento);
			posdeb.setDesRata(blank2null(desRata));
			posdeb.setNotePerPagatore(blank2null(notePerPagatore));
			if ( multibeneficiario.equalsIgnoreCase ( SI ) ) {
				posdeb.setImportoPrincipale ( Util.str2importo ( strCompimpImportoTotalePrincipale ) );
				posdeb.setImportoSecondarioAltroEnte ( Util.str2importo ( strImportoSecondario ) );
			}
			//
			List<ComponenteImportoDto> compimpDtoList = new ArrayList<> ();
			for (int i = 0; i < 5; i++) {
				if (compimpImporto[i] != null) {
					ComponenteImportoDto compimpDto = new ComponenteImportoDto(compimpId[i]);
					compimpDto.setImporto(compimpImporto[i]);
					compimpDto.setCausale(compimpCausale[i]);
					compimpDto.setAnnoAccertamento ( compimpAnnoAccert [i] );
					compimpDto.setNumeroAccertamento ( compimpNumeroAccert [i] );
					compimpDtoList.add(compimpDto);
				}
			}
			if ( multibeneficiario.equalsIgnoreCase ( SI ) ) {
				ComponenteImportoDto compimpDto = new ComponenteImportoDto ();
				compimpDto.setImporto ( Util.str2importo ( strCompimpImportoSecondario [0] ) );
				compimpDto.setCausale ( compimpCausaleSecondario [0] );
				compimpDto.setFlagComponenteSecondario ( true );
				compimpDtoList.add ( compimpDto );
			}
			if ( !compimpDtoList.isEmpty () ) {
				posdeb.setComponenteImportoDtoList(compimpDtoList);
			}

			List<RiferimentoPagamentoDto> riferimentoPagamentoDtoList = new ArrayList<> ();
			for ( int i = 0; i < 5; i++ ) {
				if ( !isBlank ( rifpagNome [i] ) && !isBlank ( rifpagValore [i] ) ) {
					RiferimentoPagamentoDto rifpagDto = new RiferimentoPagamentoDto ( rifpagId [i] );
					rifpagDto.setNome ( rifpagNome [i] );
					rifpagDto.setValore ( rifpagValore [i] );
					riferimentoPagamentoDtoList.add ( rifpagDto );
				}
			}
			if ( !riferimentoPagamentoDtoList.isEmpty () ) {
				posdeb.setRiferimentoPagamentoDtoList ( riferimentoPagamentoDtoList );
			}

			SoggettoAnagraficoDto soggettoDebitore = new SoggettoAnagraficoDto();
			soggettoDebitore.setTipologiaSoggettoEnum(soggettoTipo);
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
			posdeb.setSoggettoDebitore(soggettoDebitore);
			//
			List<PosizioneDebitoriaDto> posdebList = new ArrayList<> ();
			posdebList.add(posdeb);
			//
			FlussoCompletoDto<PosizioneDebitoriaDto> flussoCompleto = new FlussoCompletoDto<> ();
			flussoCompleto = new FlussoCompletoDto<> ();
			flussoCompleto.setFlusso(flusso);
			flussoCompleto.setItemList(posdebList);

			SalvaPosizioneDebitoriaRequestDto salvaPosizioneDebitoriaRequestDto = new SalvaPosizioneDebitoriaRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),flussoCompleto);

			// lo salva
			idFlusso = gestioneFlussiBusiness.salvaPosizioneDebitoria ( salvaPosizioneDebitoriaRequestDto );

			esitoElaborazione = ( idFlusso != null ) ? "success" : "warning";
			messaggioEsitoElaborazione = getText ( "message." + esitoElaborazione + ".salva.posizionedebitoria" );
			result = ( idFlusso != null ) ? result = SUCCESS : ripristinaPosizioneDebitoria ();

		} catch ( BusinessException e ) {
			log.error ( "Errore imprevisto", e );
			esitoElaborazione = "error";
			messaggioEsitoElaborazione = getText ( "message.error.salva.posizionedebitoria", new String [] { e.getMessage () } );
			result = ripristinaPosizioneDebitoria ();
		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	@Override
	// prima sono eseguiti controlli sintattici attraverso la validation by annotation, poi si fanno ulteriori controlli semantici sui dati e conversioni
	public void validate() {
		String methodName = "validate";
		

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			Set<String> fieldErrorKeys = getFieldErrors().keySet();

			if ( !isBlank ( strDataScadenza ) && !fieldErrorKeys.contains ( "strDataScadenza" ) ) {
				dataScadenza = sdf.parse(strDataScadenza);
			}

			if (!isBlank(strAnnoRiferimento) && !fieldErrorKeys.contains("strAnnoRiferimento")) {
				try {
					annoRiferimento = Integer.valueOf ( strAnnoRiferimento );
					if ( annoRiferimento < 1900 || annoRiferimento > 2900 ) {
						addFieldError ( "strAnnoRiferimento", getText ( "campo.formato.anno.tra.1900e2900" ) );
					}
				} catch ( NumberFormatException e ) {
					addFieldError ( "strAnnoRiferimento", getText ( "campo.formato.anno.tra.1900e2900" ) );
				}
			}

			// verifica le componenti importo
			BigDecimal compimpSommaImporti = new BigDecimal(0);
			//
			boolean compimpAtLeastOne = false;
			final String compimpError = getText("campo.obbligatorio.condizionato", new String[] { "" });
			for (int i = 4; i >= 0; i--) {
				// verifica che siano interamente valorizzate
				if ( !isBlank ( strCompimpAnnoAccert [i] ) && !fieldErrorKeys.contains ( "strCompimpAnnoAccert [i]" ) ) {
					try {
						compimpAnnoAccert [i] = Integer.valueOf ( strCompimpAnnoAccert [i] );
						if ( compimpAnnoAccert [i] < 1900 || compimpAnnoAccert [i] > 2900 ) {
							addFieldError ( "strCompimpAnnoAccert" + i, getText ( "campo.formato.anno.tra.1900e2900" ) );
						}
					} catch ( NumberFormatException e ) {
						addFieldError ( "strCompimpAnnoAccert" + i, getText ( "campo.formato.anno.tra.1900e2900" ) );
					}
				}
				
				
				//21/03/2022 da richiesta di Alessandra Rotundi, se uno dei campi della componente importo viene compilata, lo devono essere tutte
				if ((!isBlank(strCompimpImporto[i]) ||!isBlank(compimpCausale[i]) || !isBlank ( strCompimpNumeroAccert [i] )
				                || !isBlank ( strCompimpAnnoAccert [i] )) 
				                && 
				                (isBlank(strCompimpImporto[i]) ||isBlank(compimpCausale[i]) || isBlank ( strCompimpNumeroAccert [i] )
				                                || isBlank ( strCompimpAnnoAccert [i] )))
				{
				    if (isBlank(strCompimpImporto[i]))
				    {
				        addFieldError("strCompimpImporto" + i, compimpError);
				    }
				    if (isBlank(compimpCausale[i]))
                    {
                        addFieldError("compimpCausale" + i, compimpError);
                    }
				    if (isBlank(strCompimpAnnoAccert[i]))
                    {
                        addFieldError("strCompimpAnnoAccert" + i, compimpError);
                    }
				    if (isBlank(strCompimpNumeroAccert[i]))
                    {
                        addFieldError("strCompimpNumeroAccert" + i, compimpError);
                    }
				    
				}
				else if (!isBlank(strCompimpImporto[i])) {
					if (!fieldErrorKeys.contains("strCompimpImporto" + i)) {
						compimpImporto[i] = Util.str2importo(strCompimpImporto[i]);
						compimpSommaImporti = compimpSommaImporti.add(compimpImporto[i]);
						//
						if ( isBlank ( compimpCausale [i] ) ) {
							addFieldError("compimpCausale" + i, compimpError);
						}
						if ( isBlank ( strCompimpAnnoAccert [i] ) && !isBlank ( strCompimpNumeroAccert [i] ) ) {
							addFieldError ( "strCompimpAnnoAccert" + i, compimpError );
						} else if ( !isBlank ( strCompimpAnnoAccert [i] ) && isBlank ( strCompimpNumeroAccert [i] ) ) {
							addFieldError ( "strCompimpNumeroAccert" + i, compimpError );
						} else if ( !isBlank ( strCompimpAnnoAccert [i] ) && !isBlank ( strCompimpNumeroAccert [i] ) ) {
							try {
								compimpNumeroAccert [i] = Integer.valueOf ( strCompimpNumeroAccert [i] );
								if ( compimpNumeroAccert [i] == 0 ) {
									addFieldError ( "strCompimpNumeroAccert" + i, getText ( "inserimento.del.valore.zero.nel.campo.numero.accertamento" ) );
								}
							} catch ( NumberFormatException e ) {
								addFieldError ( "strCompimpNumeroAccert" + i, getText ( "campo.formato.intero" ) );
							}
						}
					}
				} else {
					if (!isBlank(compimpCausale[i])) {
						addFieldError("strCompimpImporto" + i, compimpError);
						if ( isBlank ( strCompimpAnnoAccert [i] ) && !isBlank ( strCompimpNumeroAccert [i] ) ) {
							addFieldError ( "strCompimpAnnoAccert" + i, compimpError );
						} else if ( !isBlank ( strCompimpAnnoAccert [i] ) && isBlank ( strCompimpNumeroAccert [i] ) ) {
							addFieldError ( "strCompimpNumeroAccert" + i, compimpError );
						} else if ( !isBlank ( strCompimpAnnoAccert [i] ) && !isBlank ( strCompimpNumeroAccert [i] ) ) {
							try {
								compimpNumeroAccert [i] = Integer.valueOf ( strCompimpNumeroAccert [i] );
								if ( compimpNumeroAccert [i] == 0 ) {
									addFieldError ( "strCompimpNumeroAccert" + i, getText ( "inserimento.del.valore.zero.nel.campo.numero.accertamento" ) );
								}
							} catch ( NumberFormatException e ) {
								addFieldError ( "strCompimpNumeroAccert" + i, getText ( "campo.formato.intero" ) );
							}
						}
					} else {
						if ( null != compimpNumeroAccert [i] && compimpNumeroAccert [i] == 0 ) {
							addFieldError ( "strCompimpNumeroAccert" + i, getText ( "inserimento.del.valore.zero.nel.campo.numero.accertamento" ) );
						}
					}
				}

				// verifica la presenza di eventuali componenti importo intermedie vuote
				boolean currentCompimpEmpty = isBlank ( strCompimpImporto [i] ) && isBlank ( compimpCausale [i] ) ;
				compimpAtLeastOne |= !currentCompimpEmpty;
				if ( compimpAtLeastOne && currentCompimpEmpty ) {
					addFieldError("strCompimpImporto" + i, getText("componente.importo.intermedio.vuoto"));
				}
			}

			// verifica i riferimenti pagamento
			boolean rifPagExist = false;
			boolean rifpagAtLeastOne = false;
			for ( int i = 4; i >= 0; i-- ) {
				// verifica che siano interamente valorizzate
				if ( isBlank ( rifpagNome [i] ) && !isBlank ( rifpagValore [i] ) ) {
					addFieldError ( "rifpagNome" + i, compimpError );
				}
				if ( !isBlank ( rifpagNome [i] ) && isBlank ( rifpagValore [i] ) ) {
					addFieldError ( "rifpagValore" + i, compimpError );
				}
				if ( !isBlank ( rifpagNome [i] ) && !isBlank ( rifpagValore [i] ) ) {
					rifPagExist = true;
				}
				// verifica la presenza di eventuali componenti riferimenti pagamento vuoti
				boolean currentRifpagEmpty = isBlank ( rifpagNome [i] ) && isBlank ( rifpagValore [i] );
				rifpagAtLeastOne |= !currentRifpagEmpty;
				if ( rifpagAtLeastOne && currentRifpagEmpty ) {
					addFieldError ( "rifpagNome" + i, getText ( "riferimento.pagamento.intermedio.vuoto" ) );
				}
			}

			// verifiche sul soggetto debitore
			String soggettoError;
			soggettoTipo = TipologiaSoggettoEnum.fromId(soggettoIdTipo);
			String anonimo = "ANONIMO";
			switch (soggettoTipo) {
			case PERSONA_FISICA:
				if ( rifPagExist ) {
					soggettoError = getText ( "campo.obbligatorio.condizionato", new String [] { "dalla presenza dei riferimenti pagamento" } );
					if ( isBlank ( soggettoIdUnivocoFiscalePersonaFisica ) || !anonimo.equals ( soggettoIdUnivocoFiscalePersonaFisica ) ) {
						addFieldError ( "soggettoIdUnivocoFiscalePersonaFisica", soggettoError );
					} else {
						if ( !isBlank ( soggettoCognome ) && !anonimo.equals ( soggettoCognome ) ) {
							addFieldError ( "soggettoCognome", soggettoError );
						} else {
							setSoggettoCognome ( anonimo );
						}
						if ( !isBlank ( soggettoNome ) && !anonimo.equals ( soggettoNome ) ) {
							addFieldError ( "soggettoNome", soggettoError );
						} else {
							setSoggettoNome ( anonimo );
						}
					}
				} else {
					soggettoError = getText ( "campo.obbligatorio.condizionato", new String [] { "dal tipo soggetto " + PERSONA_FISICA.getDes () } );
					if ( isBlank ( soggettoIdUnivocoFiscalePersonaFisica ) ) {
						addFieldError ( "soggettoIdUnivocoFiscalePersonaFisica", soggettoError );
					}
					if ( isBlank ( soggettoCognome ) ) {
						addFieldError ( "soggettoCognome", soggettoError );
					}
					if ( isBlank ( soggettoNome ) ) {
						addFieldError ( "soggettoNome", soggettoError );
					}
				}
				break;
			case PERSONA_GIURIDICA:
				if ( rifPagExist ) {
					soggettoError = getText ( "campo.obbligatorio.condizionato", new String [] { "dalla presenza del riferimento pagamento" } );
					if ( isBlank ( soggettoIdUnivocoFiscalePersonaGiuridica ) || !anonimo.equals ( soggettoIdUnivocoFiscalePersonaGiuridica ) ) {
						addFieldError ( "soggettoIdUnivocoFiscalePersonaGiuridica", soggettoError );
					} else {
						if ( !isBlank ( soggettoRagioneSociale ) && !anonimo.equals ( soggettoRagioneSociale ) ) {
							addFieldError ( "soggettoRagioneSociale", soggettoError );
						} else {
							setSoggettoRagioneSociale ( anonimo );
						}
					}
				} else {
					soggettoError = getText ( "campo.obbligatorio.condizionato", new String [] { "dal tipo soggetto " + PERSONA_GIURIDICA.getDes () } );
					if ( isBlank ( soggettoIdUnivocoFiscalePersonaGiuridica ) ) {
						addFieldError ( "soggettoIdUnivocoFiscalePersonaGiuridica", soggettoError );
					}
					if ( isBlank ( soggettoRagioneSociale ) ) {
						addFieldError ( "soggettoRagioneSociale", soggettoError );
					}
				}
				break;
			}

			// verifiche sull'indirizzo
			if (!isBlank(soggettoIndirizzo)) {
				final String indirizzoError = getText("campo.obbligatorio.condizionato", new String[] { "dall'indirizzo" });
				if ( isBlank ( soggettoCivico ) ) {
					addFieldError("soggettoCivico", indirizzoError);
				}
				if ( isBlank ( soggettoCap ) ) {
					addFieldError("soggettoCap", indirizzoError);
				}
				if ( isBlank ( soggettoLocalita ) ) {
					addFieldError("soggettoLocalita", indirizzoError);
				}
				if ( isBlank ( soggettoProvincia ) ) {
					addFieldError("soggettoProvincia", indirizzoError);
				}
				if ( isBlank ( soggettoNazione ) ) {
					addFieldError("soggettoNazione", indirizzoError);
				}
			} else if (!isBlank(soggettoCivico)) {
				final String indirizzoError = getText("campo.obbligatorio.condizionato", new String[] { "dal civico" });
				if ( isBlank ( soggettoIndirizzo ) ) {
					addFieldError("soggettoIndirizzo", indirizzoError);
				}
				if ( isBlank ( soggettoCap ) ) {
					addFieldError("soggettoCap", indirizzoError);
				}
				if ( isBlank ( soggettoLocalita ) ) {
					addFieldError("soggettoLocalita", indirizzoError);
				}
				if ( isBlank ( soggettoProvincia ) ) {
					addFieldError("soggettoProvincia", indirizzoError);
				}
				if ( isBlank ( soggettoNazione ) ) {
					addFieldError("soggettoNazione", indirizzoError);
				}
			} else if (!isBlank(soggettoCap)) {
				final String indirizzoError = getText("campo.obbligatorio.condizionato", new String[] { "dal CAP" });
				if ( isBlank ( soggettoIndirizzo ) ) {
					addFieldError("soggettoIndirizzo", indirizzoError);
				}
				if ( isBlank ( soggettoCivico ) ) {
					addFieldError("soggettoCivico", indirizzoError);
				}
				if ( isBlank ( soggettoLocalita ) ) {
					addFieldError("soggettoLocalita", indirizzoError);
				}
				if ( isBlank ( soggettoProvincia ) ) {
					addFieldError("soggettoProvincia", indirizzoError);
				}
				if ( isBlank ( soggettoNazione ) ) {
					addFieldError("soggettoNazione", indirizzoError);
				}
			} else if (!isBlank(soggettoLocalita)) {
				final String indirizzoError = getText("campo.obbligatorio.condizionato", new String[] { "dalla localita'" });
				if ( isBlank ( soggettoIndirizzo ) ) {
					addFieldError("soggettoIndirizzo", indirizzoError);
				}
				if ( isBlank ( soggettoCivico ) ) {
					addFieldError("soggettoCivico", indirizzoError);
				}
				if ( isBlank ( soggettoCap ) ) {
					addFieldError("soggettoCap", indirizzoError);
				}
				if ( isBlank ( soggettoProvincia ) ) {
					addFieldError("soggettoProvincia", indirizzoError);
				}
				if ( isBlank ( soggettoNazione ) ) {
					addFieldError("soggettoNazione", indirizzoError);
				}
			} else if (!isBlank(soggettoProvincia)) {
				final String indirizzoError = getText("campo.obbligatorio.condizionato", new String[] { "dalla provincia" });
				if ( isBlank ( soggettoIndirizzo ) ) {
					addFieldError("soggettoIndirizzo", indirizzoError);
				}
				if ( isBlank ( soggettoCivico ) ) {
					addFieldError("soggettoCivico", indirizzoError);
				}
				if ( isBlank ( soggettoCap ) ) {
					addFieldError("soggettoCap", indirizzoError);
				}
				if ( isBlank ( soggettoLocalita ) ) {
					addFieldError("soggettoLocalita", indirizzoError);
				}
				if ( isBlank ( soggettoNazione ) ) {
					addFieldError("soggettoNazione", indirizzoError);
				}
			} else if (!isBlank(soggettoNazione)) {
				final String indirizzoError = getText("campo.obbligatorio.condizionato", new String[] { "dalla nazione" });
				if ( isBlank ( soggettoIndirizzo ) ) {
					addFieldError("soggettoIndirizzo", indirizzoError);
				}
				if ( isBlank ( soggettoCivico ) ) {
					addFieldError("soggettoCivico", indirizzoError);
				}
				if ( isBlank ( soggettoCap ) ) {
					addFieldError("soggettoCap", indirizzoError);
				}
				if ( isBlank ( soggettoLocalita ) ) {
					addFieldError("soggettoLocalita", indirizzoError);
				}
				if ( isBlank ( soggettoProvincia ) ) {
					addFieldError("soggettoProvincia", indirizzoError);
				}
			}

			this.importoSecondario = Util.str2importo ( strImportoSecondario );
			// verifica l'importo totale come somma degli importi delle componenti
			if (!fieldErrorKeys.contains("strCompimpImportoTotale")) {
				compimpImportoTotale = Util.str2importo(strCompimpImportoTotale);
				if ( multibeneficiario.equalsIgnoreCase ( SI ) ) {
					if ( null == importoSecondario ) {
						addFieldError ( "strImportoSecondario", getText ( "importo.secondario.mancante" ) );
					} else {
						if ( compimpAtLeastOne && compimpImportoTotale.compareTo ( compimpSommaImporti.add ( importoSecondario ) ) != 0 ) {
							addFieldError ( "strCompimpImportoTotale",
								getText ( "somma.componenti.importo.non.quadra",
									new String [] { compimpSommaImporti.add ( importoSecondario ).toString () } ) );
						}
					}
				} else {
					if ( compimpAtLeastOne && compimpImportoTotale.compareTo ( compimpSommaImporti ) != 0 ) {
						addFieldError ( "strCompimpImportoTotale", getText ( "somma.componenti.importo.non.quadra", new String [] { compimpSommaImporti.toString () } ) );
					}
				}
			}

			if ( multibeneficiario.equalsIgnoreCase ( SI ) && isBlank ( compimpCausaleSecondario [0] ) ) {
				addFieldError ( "compimpCausale0Secondario",
					getText ( "componente.causale.secondaria.vuota", "compimpCausale0Secondario" ) );
			}

			if ( multibeneficiario.equalsIgnoreCase ( SI ) ) {
				if ( strCompimpImportoTotalePrincipale.isEmpty () ) {
					addFieldError ( "strCompimpImportoTotalePrincipale",
						getText ( "importo.principale.mancante", "strCompimpImportoTotalePrincipale" ) );
				}
			}

			//EPAY-49 : verifiche posizione debitoria esterna gia inserita
			ActionScope actionScope = getSessionContext ().getActionScope ();
			if ( null != idFlusso ) {
				FlussoCompletoDto<PosizioneDebitoriaDto> flusso = gestioneFlussiBusiness.getFlussoPosizioniDebitorie ( idFlusso );
				if ( null != flusso && null != flusso.getItemList () && !flusso.getItemList ().isEmpty () ) {
					idPosizioneDebitoria = actionScope.getValue ( "idPosizioneDebitoria" );
					for ( PosizioneDebitoriaDto posizioneDebEst: flusso.getItemList () ) {
						if ( idPosizioneDebitoriaEsterna.equalsIgnoreCase ( posizioneDebEst.getIdPosizioneDebitoriaEsterna () )
										&& ! ( posizioneDebEst.getId ().equals ( idPosizioneDebitoria ) ) ) {
							addFieldError ( "idPosizioneDebitoriaEsterna", getText ( "campo.univoco" ) );
						}
					}
				}
			}

			if (hasFieldErrors()) {
				addActionMessage(getText("message.valorizzare.corpo.posdeb"));
			}

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(Long idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
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

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
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

	public BigDecimal getImportoTotalePosizioniDebitorie() {
		return importoTotalePosizioniDebitorie;
	}

	public void setImportoTotalePosizioniDebitorie(BigDecimal importoTotalePosizioniDebitorie) {
		this.importoTotalePosizioniDebitorie = importoTotalePosizioniDebitorie;
	}

	public String getIdPosizioneDebitoriaEsterna() {
		return idPosizioneDebitoriaEsterna;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setIdPosizioneDebitoriaEsterna(String idPosizioneDebitoriaEsterna) {
		this.idPosizioneDebitoriaEsterna = idPosizioneDebitoriaEsterna;
	}

	public String getStrCompimpImportoTotale() {
		return strCompimpImportoTotale;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImportoTotale(String strCompimpImportoTotale) {
		this.strCompimpImportoTotale = strCompimpImportoTotale;
	}

	public BigDecimal getCompimpImportoTotaleOriginal() {
		return compimpImportoTotaleOriginal;
	}

	public void setCompimpImportoTotaleOriginal(BigDecimal compimpImportoTotaleOriginal) {
		this.compimpImportoTotaleOriginal = compimpImportoTotaleOriginal;
	}

	public String getDesCausaleVersamento() {
		return desCausaleVersamento;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public String getStrDataScadenza() {
		return strDataScadenza;
	}

	public void setStrDataScadenza(String strDataScadenza) {
		this.strDataScadenza = strDataScadenza;
	}

	public String getStrAnnoRiferimento() {
		return strAnnoRiferimento;
	}

	@RegexFieldValidator(key = "campo.formato.anno", regex = "^\\d{1,4}")
	public void setStrAnnoRiferimento(String strAnnoRiferimento) {
		this.strAnnoRiferimento = strAnnoRiferimento;
	}

	public String getDesRata() {
		return desRata;
	}

	public void setDesRata(String desRata) {
		this.desRata = desRata;
	}

	public String getNotePerPagatore() {
		return notePerPagatore;
	}

	public void setNotePerPagatore(String notePerPagatore) {
		this.notePerPagatore = notePerPagatore;
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

	public void setRifpagNome0 ( String rifpagNome ) {
		this.rifpagNome [0] = rifpagNome;
	}

	public String getRifpagNome0 () {
		return rifpagNome [0];
	}

	public void setRifpagValore0 ( String rifpagValore ) {
		this.rifpagValore [0] = rifpagValore;
	}

	public String getRifpagValore0 () {
		return rifpagValore [0];
	}

	public Long getRifpagId1 () {
		return rifpagId [1];
	}

	public void setRifpagId1 ( Long rifpagId ) {
		this.rifpagId [1] = rifpagId;
	}

	public void setRifpagNome1 ( String rifpagNome ) {
		this.rifpagNome [1] = rifpagNome;
	}

	public String getRifpagNome1 () {
		return rifpagNome [1];
	}

	public void setRifpagValore1 ( String rifpagValore ) {
		this.rifpagValore [1] = rifpagValore;
	}

	public String getRifpagValore1 () {
		return rifpagValore [1];
	}

	public Long getRifpagId2 () {
		return rifpagId [2];
	}

	public void setRifpagId2 ( Long rifpagId ) {
		this.rifpagId [2] = rifpagId;
	}

	public void setRifpagNome2 ( String rifpagNome ) {
		this.rifpagNome [2] = rifpagNome;
	}

	public String getRifpagNome2 () {
		return rifpagNome [2];
	}

	public void setRifpagValore2 ( String rifpagValore ) {
		this.rifpagValore [2] = rifpagValore;
	}

	public String getRifpagValore2 () {
		return rifpagValore [2];
	}

	public Long getRifpagId3 () {
		return rifpagId [3];
	}

	public void setRifpagId3 ( Long rifpagId ) {
		this.rifpagId [3] = rifpagId;
	}

	public void setRifpagNome3 ( String rifpagNome ) {
		this.rifpagNome [3] = rifpagNome;
	}

	public String getRifpagNome3 () {
		return rifpagNome [3];
	}

	public void setRifpagValore3 ( String rifpagValore ) {
		this.rifpagValore [3] = rifpagValore;
	}

	public String getRifpagValore3 () {
		return rifpagValore [3];
	}

	public Long getRifpagId4 () {
		return rifpagId [4];
	}

	public void setRifpagId4 ( Long rifpagId ) {
		this.rifpagId [4] = rifpagId;
	}

	public void setRifpagNome4 ( String rifpagNome ) {
		this.rifpagNome [4] = rifpagNome;
	}

	public String getRifpagNome4 () {
		return rifpagNome [4];
	}

	public void setRifpagValore4 ( String rifpagValore ) {
		this.rifpagValore [4] = rifpagValore;
	}

	public String getRifpagValore4 () {
		return rifpagValore [4];
	}

	public String getSoggettoIdTipo() {
		return soggettoIdTipo;
	}

	public void setSoggettoIdTipo(String soggettoIdTipo) {
		this.soggettoIdTipo = soggettoIdTipo;
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

//	@RegexFieldValidator(key = "campo.formato.email", regex = "[A-Za-z0-9_]+([\\-\\+\\.'][A-Za-z0-9_]+)*@[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*\\.[A-Za-z0-9_]+([\\-\\.][A-Za-z0-9_]+)*")
	@RegexFieldValidator(key = "campo.formato.email", regex = "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*")
	
	
	public void setSoggettoEmail(String soggettoEmail) {
		this.soggettoEmail = soggettoEmail;
	}

	public List<StrutsOptionsSupport> getSoggettoIdTipoList() {
		return soggettoIdTipoList;
	}

	public void setSoggettoIdTipoList(List<StrutsOptionsSupport> soggettoIdTipoList) {
		this.soggettoIdTipoList = soggettoIdTipoList;
	}

	public GestioneFlussiBusiness getGestioneFlussiBusiness() {
		return gestioneFlussiBusiness;
	}

	public void setGestioneFlussiBusiness(GestioneFlussiBusiness gestioneFlussiBusiness) {
		this.gestioneFlussiBusiness = gestioneFlussiBusiness;
	}

	public String getMultibeneficiario() {
		return multibeneficiario;
	}

	public void setMultibeneficiario(String multibeneficiario) {
		this.multibeneficiario = multibeneficiario;
	}

	public BigDecimal getCompimpImportoTotaleOriginalPrincipale() {
		return compimpImportoTotaleOriginalPrincipale;
	}

	public void setCompimpImportoTotaleOriginalPrincipale(BigDecimal compimpImportoTotaleOriginalPrincipale) {
		this.compimpImportoTotaleOriginalPrincipale = compimpImportoTotaleOriginalPrincipale;
	}

	public String getStrCompimpImportoTotalePrincipale() {
		return strCompimpImportoTotalePrincipale;
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImportoTotalePrincipale(String strCompimpImportoTotalePrincipale) {
		this.strCompimpImportoTotalePrincipale = strCompimpImportoTotalePrincipale;
	}

	public BigDecimal getImportoSecondario() {
		return importoSecondario;
	}

	public void setImportoSecondario(BigDecimal importoSecondario) {
		this.importoSecondario = importoSecondario;
	}

	public String getStrImportoSecondario() {
		return strImportoSecondario;
	}

	public void setStrImportoSecondario(String strImportoSecondario) {
		this.strImportoSecondario = strImportoSecondario;
	}

	public Long getCompimpId0Secondario() {
		return compimpIdSecondario[0];
	}

	public void setCompimpId0Secondario(Long compimpId) {
		this.compimpIdSecondario[0] = compimpId;
	}

	public String getStrCompimpImporto0Secondario() {
		return strCompimpImportoSecondario[0];
	}

	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrCompimpImporto0Secondario(String strCompimpImporto) {
		this.strCompimpImportoSecondario[0] = strCompimpImporto;
	}

	public String getCompimpCausale0Secondario() {
		return compimpCausaleSecondario[0];
	}

	public void setCompimpCausale0Secondario(String compimpCausale) {
		this.compimpCausaleSecondario[0] = compimpCausale;
	}

}
