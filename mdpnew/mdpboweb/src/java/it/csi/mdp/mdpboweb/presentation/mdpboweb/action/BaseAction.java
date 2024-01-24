/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.util.*;
import java.lang.reflect.Method;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import it.csi.mdp.mdpboweb.util.*;
import it.csi.mdp.mdpboweb.dto.*;
import it.csi.mdp.mdpboweb.business.*;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.security.*;

/**
 * Base Action che contiene gli elementi comuni all'applicazione.
 * Tutte le altre Action dell'applicazione dovranno ereditare da questa
 * in modo da ottenere le parti comuni, e dovranno implementare in proprio
 * le funzionalit&agrave; specifiche della pagina.
 * <p/>
 * La classe eredita da {@link com.opensymphony.xwork2.ActionSupport} i
 * metodi di utilit&agrave; necessari ad eseguire le principali operazioni
 * (ad esempio conversione, validazione, ecc...) ed implementa l'interfaccia
 * {@link org.apache.struts2.interceptor.SessionAware}, che permette
 * alla Action di accedere alla sessione. 
 * 
 * @author GuiGen
 */
public abstract class BaseAction extends ActionSupport
		implements
			SessionAware,
			Preparable {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".presentation");

	/** Riferimento alla sessione corrente */
	protected Map session;

	public void setSession(Map session) {
		this.session = session;
	}
	public Map getSession() {
		return this.session;
	}

	protected Map<String, UISecConstraint> allMenuVisibilityConstraints = null;
	protected Map<String, UISecConstraint> allMenuOnOffConstraints = null;

	public void prepare() throws CommandExecutionException {

		// caricamento struttura di constraints
		if (allMenuVisibilityConstraints == null)
			allMenuVisibilityConstraints = getMenuVisibilityUIConstraints();
		if (allMenuOnOffConstraints == null)
			allMenuOnOffConstraints = getMenuONOFFUIConstraints();

	}

	////////////////////////////////////////////////////////////////////////
	//// costruzione/lettura strato model da passare allo strato di logica
	////////////////////////////////////////////////////////////////////////
	public abstract Class modelClass();

	private java.lang.reflect.Method findReadMethod(String name, Class cl)
			throws IntrospectionException {
		name = (name.startsWith("get") || name.startsWith("set") ? name
				.substring(3) : name.startsWith("is")
				? name.substring(2)
				: name);
		BeanInfo bi = java.beans.Introspector.getBeanInfo(cl);
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor currPd = pds[i];
			if (currPd.getName().equalsIgnoreCase(name))
				return currPd.getReadMethod();
		}
		return null;
	}

	private java.lang.reflect.Method findWriteMethod(String name, Class cl)
			throws IntrospectionException {
		name = (name.startsWith("get") || name.startsWith("set") ? name
				.substring(3) : name.startsWith("is")
				? name.substring(2)
				: name);
		BeanInfo bi = java.beans.Introspector.getBeanInfo(cl);
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor currPd = pds[i];
			if (currPd.getName().equalsIgnoreCase(name))
				return currPd.getWriteMethod();
		}
		return null;
	}

	public Object toModel() {
		try {
			Object modelObj = modelClass().newInstance();
			// imposto prima di tutto la session per evitare errori nei setter
			// degli oggetti a scope Session
			((BaseSessionAwareDTO) modelObj).setSession(this.getSession());
			BeanInfo targetBI = java.beans.Introspector
					.getBeanInfo(modelClass());
			PropertyDescriptor[] targetPds = targetBI.getPropertyDescriptors();
			for (int i = 0; i < targetPds.length; i++) {
				PropertyDescriptor currTargetPD = targetPds[i];
				java.lang.reflect.Method srcReadMethod = findReadMethod(
						currTargetPD.getReadMethod().getName(), this.getClass());
				if (srcReadMethod != null) {
					Object srcVal = srcReadMethod.invoke(this, new Object[]{});
					java.lang.reflect.Method currWriteMethod = currTargetPD
							.getWriteMethod();
					if (currWriteMethod != null) {
						currTargetPD.getWriteMethod().invoke(modelObj,
								new Object[]{srcVal});
					}
				}
			}
			return modelObj;
		} catch (Exception e) {
			log.error(
					"[BaseAction::toModel] Errore interno nella costruzione del modello, classe:"
							+ modelClass() + ": " + e, e);
			throw new IllegalArgumentException(
					"Errore interno nella costruzione del modello, classe:"
							+ modelClass() + ": " + e);

		}
	}

	public void fromModel(Object modelObj) {
		try {
			BeanInfo srcBI = java.beans.Introspector.getBeanInfo(modelClass());
			PropertyDescriptor[] srcPds = srcBI.getPropertyDescriptors();
			for (int i = 0; i < srcPds.length; i++) {
				PropertyDescriptor currSrcPD = srcPds[i];
				java.lang.reflect.Method srcReadMethod = currSrcPD
						.getReadMethod();
				if (srcReadMethod != null) {
					Object srcVal = srcReadMethod.invoke(modelObj,
							new Object[]{});
					java.lang.reflect.Method targetWriteMethod = findWriteMethod(
							currSrcPD.getReadMethod().getName(),
							this.getClass());
					if (targetWriteMethod != null) {
						targetWriteMethod.invoke(this, new Object[]{srcVal});
					}
				}
			}
		} catch (Exception e) {
			log.error(
					"[BaseAction::fromModel] Errore interno nella costruzione del modello, classe:"
							+ modelClass() + ": " + e, e);
			throw new IllegalArgumentException(
					"Errore interno nella costruzione del modello, classe:"
							+ modelClass() + ": " + e);

		}
	}

	///////////////////////////////////
	/**
	 * Metodi per visibilita'/abilitazione dei Widget
	 */

	/**
	 * @return true se il widget deve essere reso visibile
	 */
	public boolean isWidgetVisible(String cpName, String widgShortName) {
		Map cpData = (Map) session.get(cpName);
		if (cpData != null) {
			Boolean visibleFlag = (Boolean) cpData.get(widgShortName
					+ "_visible");
			if (visibleFlag != null) {
				return visibleFlag.booleanValue();
			} else
				return true;
		} else
			return true;

	}

	/**
	 * @return true se il widget deve essere disabilitato
	 */
	public boolean isWidgetDisabled(String cpName, String widgShortName) {
		Map cpData = (Map) session.get(cpName);
		if (cpData != null) {
			Boolean enabledFlag = (Boolean) cpData.get(widgShortName
					+ "_enabled");
			if (enabledFlag != null) {
				return !enabledFlag.booleanValue();
			} else
				return false;
		} else
			return false;

	}

	///////////////////////////////////
	/**
	 * Metodi per visibilita'/abilitazione del menu
	 */

	static List<MenuStructure> menuMap = new ArrayList<MenuStructure>();
	static {

		MenuStructure mnuUnoMenu = new MenuStructure("mnuUno");

		MenuStructure mnuApplicazioniMenu = new MenuStructure("mnuApplicazioni");

		mnuUnoMenu.getSubmenus().add(mnuApplicazioniMenu);
		MenuStructure mnuGWModAppMenu = new MenuStructure("mnuGWModApp");

		mnuUnoMenu.getSubmenus().add(mnuGWModAppMenu);
		MenuStructure mnuEntiMenu = new MenuStructure("mnuEnti");

		mnuUnoMenu.getSubmenus().add(mnuEntiMenu);

		menuMap.add(mnuUnoMenu);

		MenuStructure mnuDueMenu = new MenuStructure("mnuDue");

		MenuStructure mnuTransazioniMenu = new MenuStructure("mnuTransazioni");

		mnuDueMenu.getSubmenus().add(mnuTransazioniMenu);
		MenuStructure mnuStatisticheMenu = new MenuStructure("mnuStatistiche");

		mnuDueMenu.getSubmenus().add(mnuStatisticheMenu);

		menuMap.add(mnuDueMenu);

		MenuStructure mnuTreMenu = new MenuStructure("mnuTre");

		MenuStructure mnuAuditingMenu = new MenuStructure("mnuAuditing");

		mnuTreMenu.getSubmenus().add(mnuAuditingMenu);

		menuMap.add(mnuTreMenu);

		MenuStructure mnuConfigurazioneMenu = new MenuStructure(
				"mnuConfigurazione");

		MenuStructure mnuGestConfigMenu = new MenuStructure("mnuGestConfig");

		mnuConfigurazioneMenu.getSubmenus().add(mnuGestConfigMenu);
		MenuStructure mnuGestioneGruppiMenu = new MenuStructure(
				"mnuGestioneGruppi");

		mnuConfigurazioneMenu.getSubmenus().add(mnuGestioneGruppiMenu);
		MenuStructure mnuGestioneUtentiMenu = new MenuStructure(
				"mnuGestioneUtenti");

		mnuConfigurazioneMenu.getSubmenus().add(mnuGestioneUtentiMenu);

		menuMap.add(mnuConfigurazioneMenu);

		MenuStructure mnuStoErroriMenu = new MenuStructure("mnuStoErrori");

		MenuStructure mnuStoricoErroriMenu = new MenuStructure(
				"mnuStoricoErrori");

		mnuStoErroriMenu.getSubmenus().add(mnuStoricoErroriMenu);

		menuMap.add(mnuStoErroriMenu);

		MenuStructure mnuNodoNazionaleMenu = new MenuStructure(
				"mnuNodoNazionale");

		MenuStructure mnuGiornaleEventiMenu = new MenuStructure(
				"mnuGiornaleEventi");

		mnuNodoNazionaleMenu.getSubmenus().add(mnuGiornaleEventiMenu);
		MenuStructure mnuRichiestaPagamentoTelematicoMenu = new MenuStructure(
				"mnuRichiestaPagamentoTelematico");

		mnuNodoNazionaleMenu.getSubmenus().add(
				mnuRichiestaPagamentoTelematicoMenu);
		MenuStructure mnuRicevutaTelematicaMenu = new MenuStructure(
				"mnuRicevutaTelematica");

		mnuNodoNazionaleMenu.getSubmenus().add(mnuRicevutaTelematicaMenu);
		MenuStructure mnuInformativaPspMenu = new MenuStructure(
				"mnuInformativaPsp");

		mnuNodoNazionaleMenu.getSubmenus().add(mnuInformativaPspMenu);
		MenuStructure mnuFlussoRiversamentoMenu = new MenuStructure(
				"mnuFlussoRiversamento");

		mnuNodoNazionaleMenu.getSubmenus().add(mnuFlussoRiversamentoMenu);
		MenuStructure mnuSingoloFlussoPagamentoMenu = new MenuStructure(
				"mnuSingoloFlussoPagamento");

		mnuNodoNazionaleMenu.getSubmenus().add(mnuSingoloFlussoPagamentoMenu);

		menuMap.add(mnuNodoNazionaleMenu);

	}

	/**
	 * @return true se il menu deve essere reso visibile
	 */
	public boolean isMenuVisible(String menuName) {
		UISecConstraint ctr = allMenuVisibilityConstraints.get(menuName);
		boolean status;
		if (ctr != null) {
			try {
				status = ctr.verifyConstraint(session,
						UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
						getSpringSecurityHelper());
			} catch (BEException ex) {
				log.error("[BaseAction::isMenuVisible] errore durante verifica->false");
				return false;
			}
		} else
			status = true;
		return status;
	}

	/**
	 * @return true se almeno uno dei sottomenu del menu dato &egrave; vissibile, false altrimenti
	 */
	public boolean isAtLeastOneSubMenuVisible(String menuName) {

		for (MenuStructure menuStructure : menuMap) {
			if (menuName.equals(menuStructure.getMenuName())) {
				List<MenuStructure> mstr = menuStructure.getSubMenuFlattenList(
						menuStructure, menuName);
				for (MenuStructure subMenu : mstr) {
					if (isMenuVisible(subMenu.getMenuName())) {
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * @return true se il menu deve essere reso abilitato
	 */
	public boolean isMenuEnabled(String menuName) {
		UISecConstraint ctr = allMenuOnOffConstraints.get(menuName);
		boolean status;
		if (ctr != null) {
			try {
				status = ctr.verifyConstraint(session,
						UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
						getSpringSecurityHelper());
			} catch (BEException ex) {
				log.error("[BaseAction::isMenuEnabled] errore durante verifica->false");
				return false;
			}
		} else
			status = true;
		return status;

	}

	/**
	 * @return true se il menu deve essere reso attivo (ovvero &grave; stato cliccato)
	 */
	public boolean isMenuActive(String menuName) {
		String menu = "menu_items_" + menuName;
		return menu.equals((String) session.get("active_menu"));
	}

	/**
	 * @return true se un sottomenu del menu dato (a qualunque livello) &egrave; attivo (ovvero &grave; stato cliccato)
	 */
	public boolean isSubMenuActive(String menuName) {
		for (MenuStructure menuStructure : menuMap) {
			if (menuName.equals(menuStructure.getMenuName())) {
				List<MenuStructure> mstr = menuStructure.getSubMenuFlattenList(
						menuStructure, menuName);
				for (MenuStructure subMenu : mstr) {
					if (isMenuActive(subMenu.getMenuName())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Gestione del reset della paginazione/sorting delle tabelle (Displaytag)
	 * @param tableName nome della tabella
	 * @return true se bisogna resettare paginazione/sorting, false altrimenti
	 */
	public boolean isTableClearStatus(String tableName) {
		String sessionValue = tableName + "_clearStatus";
		Boolean clearStatus = (Boolean) session.get(sessionValue);
		if (clearStatus == null) {
			clearStatus = true;
		}
		if (clearStatus) {
			// la proprieta' e' "usa e getta"
			session.put(sessionValue, Boolean.valueOf(false));
		}
		return clearStatus;
	}

	/**
	 * Restituisce lo stato del menu
	 * @return List<MenuStructure>
	 */
	protected List<MenuStructure> getMenuStatus() {

		List<MenuStructure> menus = new ArrayList<MenuStructure>();
		for (MenuStructure menuStructure : menuMap) {
			//***sul fatclient non ho bisogno dello stato attivo per cui lo imposto a false
			menus.add(new MenuStructure(menuStructure.getMenuName(),
					isMenuVisible(menuStructure.getMenuName()),
					isMenuEnabled(menuStructure.getMenuName()), false));

			for (String subMenu : menuStructure.getAllSubMenu()) {

				menus.add(new MenuStructure(subMenu, isMenuVisible(subMenu),
						isMenuEnabled(subMenu), false));

			}
		}
		return menus;
	}

	/**
	 *	Metodo per la rimozione dalla sessione degli application data a scope
	 *  SAME_PAGE. Deve essere implementato in tutte le sottoclassi associate
	 *  ad un content panel. 
	 */
	public abstract void clearPageScopedAppData(String targetContentPanelName);

	/**
	 * Ripulisce gli eventuali errori di conversione se il metodo richiamato
	 * dichiarava @skipValidation 
	 */
	protected void clearConversionErrorsIfSkipValidation() {
		ActionContext ctx = ActionContext.getContext();
		String methodName = ctx.getActionInvocation().getProxy().getMethod();
		Method m;
		try {
			boolean skipValidation = true;
			m = this.getClass().getDeclaredMethod(methodName, new Class[]{});
			if (m != null) {
				skipValidation = m.isAnnotationPresent(SkipValidation.class);
			}
			if (skipValidation && !m.getName().equalsIgnoreCase("execute")) {
				removeFailedParamsFromRequest(ctx);
				clearErrorsAndMessages();
			}
		} catch (SecurityException e) {
			log.error("[BaseAction::clearConversionErrorsIfSkipValidation] Errore interno: "
					+ e + ", ignoro");
		} catch (NoSuchMethodException e) {
			log.error("[BaseAction::clearConversionErrorsIfSkipValidation] Errore interno: "
					+ e + ", ignoro");
		}
	}

	private void removeFailedParamsFromRequest(ActionContext ctx) {
		ctx.getContextMap();
		ctx.getContextMap().remove("original.property.override");
	}

	private it.csi.mdp.mdpboweb.business.BackEndFacade _backEnd = null;

	public void setSpringBackEnd(it.csi.mdp.mdpboweb.business.BackEndFacade be) {
		_backEnd = be;
	}

	public it.csi.mdp.mdpboweb.business.BackEndFacade getSpringBackEnd() {
		return _backEnd;
	}

	private it.csi.mdp.mdpboweb.business.SecurityHelper _securityHelper = null;

	public void setSpringSecurityHelper(
			it.csi.mdp.mdpboweb.business.SecurityHelper sh) {
		_securityHelper = sh;
	}

	public it.csi.mdp.mdpboweb.business.SecurityHelper getSpringSecurityHelper() {
		return _securityHelper;
	}

	protected java.io.InputStream dataProviderStream = null;

	public java.io.InputStream getDataProviderStream() {
		return dataProviderStream;
	}

	protected Map<String, UISecConstraint> getMenuVisibilityUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();
		// constraints per mnuUno
		UISecConstraint mnuUno_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuUno",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST");

		UISecConstraint mnuUno_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuUno",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST_FULL");

		UISecConstraint[] mnuUno_constraints = new UISecConstraint[]{
				mnuUno_0_ctr, mnuUno_1_ctr};
		UISecConstraint mnuUno_ctr = new ComplexUISecConstraint(
				mnuUno_constraints);
		allConstraints.put("mnuUno", mnuUno_ctr);
		// constraints per mnuApplicazioni
		UISecConstraint mnuApplicazioni_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuApplicazioni",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST");

		UISecConstraint mnuApplicazioni_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuApplicazioni",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST_FULL");

		UISecConstraint[] mnuApplicazioni_constraints = new UISecConstraint[]{
				mnuApplicazioni_0_ctr, mnuApplicazioni_1_ctr};
		UISecConstraint mnuApplicazioni_ctr = new ComplexUISecConstraint(
				mnuApplicazioni_constraints);
		allConstraints.put("mnuApplicazioni", mnuApplicazioni_ctr);

		// constraints per mnuGWModApp
		UISecConstraint mnuGWModApp_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGWModApp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PROVIDER_GEST");

		UISecConstraint[] mnuGWModApp_constraints = new UISecConstraint[]{mnuGWModApp_0_ctr};
		UISecConstraint mnuGWModApp_ctr = new ComplexUISecConstraint(
				mnuGWModApp_constraints);
		allConstraints.put("mnuGWModApp", mnuGWModApp_ctr);

		// constraints per mnuEnti
		UISecConstraint mnuEnti_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuEnti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST");

		UISecConstraint mnuEnti_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuEnti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST_FULL");

		UISecConstraint[] mnuEnti_constraints = new UISecConstraint[]{
				mnuEnti_0_ctr, mnuEnti_1_ctr};
		UISecConstraint mnuEnti_ctr = new ComplexUISecConstraint(
				mnuEnti_constraints);
		allConstraints.put("mnuEnti", mnuEnti_ctr);

		// constraints per mnuDue
		UISecConstraint mnuDue_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuDue",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint mnuDue_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuDue",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "PAGAMENTI");

		UISecConstraint[] mnuDue_constraints = new UISecConstraint[]{
				mnuDue_0_ctr, mnuDue_1_ctr};
		UISecConstraint mnuDue_ctr = new ComplexUISecConstraint(
				mnuDue_constraints);
		allConstraints.put("mnuDue", mnuDue_ctr);

		// constraints per mnuTre
		UISecConstraint mnuTre_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuTre",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "AUDIT");

		UISecConstraint[] mnuTre_constraints = new UISecConstraint[]{mnuTre_0_ctr};
		UISecConstraint mnuTre_ctr = new ComplexUISecConstraint(
				mnuTre_constraints);
		allConstraints.put("mnuTre", mnuTre_ctr);

		// constraints per mnuConfigurazione
		UISecConstraint mnuConfigurazione_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuConfigurazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "MDPNEW_GEST");

		UISecConstraint mnuConfigurazione_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuConfigurazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "UTENTI_GEST");

		UISecConstraint[] mnuConfigurazione_constraints = new UISecConstraint[]{
				mnuConfigurazione_0_ctr, mnuConfigurazione_1_ctr};
		UISecConstraint mnuConfigurazione_ctr = new ComplexUISecConstraint(
				mnuConfigurazione_constraints);
		allConstraints.put("mnuConfigurazione", mnuConfigurazione_ctr);
		// constraints per mnuGestConfig
		UISecConstraint mnuGestConfig_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGestConfig",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "MDPNEW_GEST");

		UISecConstraint[] mnuGestConfig_constraints = new UISecConstraint[]{mnuGestConfig_0_ctr};
		UISecConstraint mnuGestConfig_ctr = new ComplexUISecConstraint(
				mnuGestConfig_constraints);
		allConstraints.put("mnuGestConfig", mnuGestConfig_ctr);

		// constraints per mnuGestioneGruppi
		UISecConstraint mnuGestioneGruppi_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGestioneGruppi",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "UTENTI_GEST");

		UISecConstraint[] mnuGestioneGruppi_constraints = new UISecConstraint[]{mnuGestioneGruppi_0_ctr};
		UISecConstraint mnuGestioneGruppi_ctr = new ComplexUISecConstraint(
				mnuGestioneGruppi_constraints);
		allConstraints.put("mnuGestioneGruppi", mnuGestioneGruppi_ctr);

		// constraints per mnuGestioneUtenti
		UISecConstraint mnuGestioneUtenti_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGestioneUtenti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "UTENTI_GEST");

		UISecConstraint[] mnuGestioneUtenti_constraints = new UISecConstraint[]{mnuGestioneUtenti_0_ctr};
		UISecConstraint mnuGestioneUtenti_ctr = new ComplexUISecConstraint(
				mnuGestioneUtenti_constraints);
		allConstraints.put("mnuGestioneUtenti", mnuGestioneUtenti_ctr);

		// constraints per mnuStoErrori
		UISecConstraint mnuStoErrori_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuStoErrori",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"STORICOERRORI");

		UISecConstraint[] mnuStoErrori_constraints = new UISecConstraint[]{mnuStoErrori_0_ctr};
		UISecConstraint mnuStoErrori_ctr = new ComplexUISecConstraint(
				mnuStoErrori_constraints);
		allConstraints.put("mnuStoErrori", mnuStoErrori_ctr);

		// constraints per mnuNodoNazionale
		UISecConstraint mnuNodoNazionale_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuNodoNazionale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] mnuNodoNazionale_constraints = new UISecConstraint[]{mnuNodoNazionale_0_ctr};
		UISecConstraint mnuNodoNazionale_ctr = new ComplexUISecConstraint(
				mnuNodoNazionale_constraints);
		allConstraints.put("mnuNodoNazionale", mnuNodoNazionale_ctr);

		return allConstraints;
	}

	protected Map<String, UISecConstraint> getMenuONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();
		// constraints per mnuUno
		UISecConstraint mnuUno_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuUno",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST");

		UISecConstraint mnuUno_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuUno",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST_FULL");

		UISecConstraint[] mnuUno_constraints = new UISecConstraint[]{};
		UISecConstraint mnuUno_ctr = new ComplexUISecConstraint(
				mnuUno_constraints);
		allConstraints.put("mnuUno", mnuUno_ctr);
		// constraints per mnuApplicazioni
		UISecConstraint mnuApplicazioni_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuApplicazioni",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST");

		UISecConstraint mnuApplicazioni_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuApplicazioni",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST_FULL");

		UISecConstraint[] mnuApplicazioni_constraints = new UISecConstraint[]{};
		UISecConstraint mnuApplicazioni_ctr = new ComplexUISecConstraint(
				mnuApplicazioni_constraints);
		allConstraints.put("mnuApplicazioni", mnuApplicazioni_ctr);

		// constraints per mnuGWModApp
		UISecConstraint mnuGWModApp_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGWModApp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PROVIDER_GEST");

		UISecConstraint[] mnuGWModApp_constraints = new UISecConstraint[]{};
		UISecConstraint mnuGWModApp_ctr = new ComplexUISecConstraint(
				mnuGWModApp_constraints);
		allConstraints.put("mnuGWModApp", mnuGWModApp_ctr);

		// constraints per mnuEnti
		UISecConstraint mnuEnti_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuEnti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST");

		UISecConstraint mnuEnti_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuEnti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"APPLICAZIONI_GEST_FULL");

		UISecConstraint[] mnuEnti_constraints = new UISecConstraint[]{};
		UISecConstraint mnuEnti_ctr = new ComplexUISecConstraint(
				mnuEnti_constraints);
		allConstraints.put("mnuEnti", mnuEnti_ctr);

		// constraints per mnuDue
		UISecConstraint mnuDue_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuDue",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint mnuDue_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuDue",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "PAGAMENTI");

		UISecConstraint[] mnuDue_constraints = new UISecConstraint[]{};
		UISecConstraint mnuDue_ctr = new ComplexUISecConstraint(
				mnuDue_constraints);
		allConstraints.put("mnuDue", mnuDue_ctr);

		// constraints per mnuTre
		UISecConstraint mnuTre_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuTre",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "AUDIT");

		UISecConstraint[] mnuTre_constraints = new UISecConstraint[]{};
		UISecConstraint mnuTre_ctr = new ComplexUISecConstraint(
				mnuTre_constraints);
		allConstraints.put("mnuTre", mnuTre_ctr);

		// constraints per mnuConfigurazione
		UISecConstraint mnuConfigurazione_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuConfigurazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "MDPNEW_GEST");

		UISecConstraint mnuConfigurazione_1_ctr =

		new UCBasedUISecConstraint("_menu", "mnuConfigurazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "UTENTI_GEST");

		UISecConstraint[] mnuConfigurazione_constraints = new UISecConstraint[]{};
		UISecConstraint mnuConfigurazione_ctr = new ComplexUISecConstraint(
				mnuConfigurazione_constraints);
		allConstraints.put("mnuConfigurazione", mnuConfigurazione_ctr);
		// constraints per mnuGestConfig
		UISecConstraint mnuGestConfig_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGestConfig",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "MDPNEW_GEST");

		UISecConstraint[] mnuGestConfig_constraints = new UISecConstraint[]{};
		UISecConstraint mnuGestConfig_ctr = new ComplexUISecConstraint(
				mnuGestConfig_constraints);
		allConstraints.put("mnuGestConfig", mnuGestConfig_ctr);

		// constraints per mnuGestioneGruppi
		UISecConstraint mnuGestioneGruppi_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGestioneGruppi",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "UTENTI_GEST");

		UISecConstraint[] mnuGestioneGruppi_constraints = new UISecConstraint[]{};
		UISecConstraint mnuGestioneGruppi_ctr = new ComplexUISecConstraint(
				mnuGestioneGruppi_constraints);
		allConstraints.put("mnuGestioneGruppi", mnuGestioneGruppi_ctr);

		// constraints per mnuGestioneUtenti
		UISecConstraint mnuGestioneUtenti_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuGestioneUtenti",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, "UTENTI_GEST");

		UISecConstraint[] mnuGestioneUtenti_constraints = new UISecConstraint[]{};
		UISecConstraint mnuGestioneUtenti_ctr = new ComplexUISecConstraint(
				mnuGestioneUtenti_constraints);
		allConstraints.put("mnuGestioneUtenti", mnuGestioneUtenti_ctr);

		// constraints per mnuStoErrori
		UISecConstraint mnuStoErrori_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuStoErrori",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"STORICOERRORI");

		UISecConstraint[] mnuStoErrori_constraints = new UISecConstraint[]{};
		UISecConstraint mnuStoErrori_ctr = new ComplexUISecConstraint(
				mnuStoErrori_constraints);
		allConstraints.put("mnuStoErrori", mnuStoErrori_ctr);

		// constraints per mnuNodoNazionale
		UISecConstraint mnuNodoNazionale_0_ctr =

		new UCBasedUISecConstraint("_menu", "mnuNodoNazionale",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				"PAGAMENTI_RW");

		UISecConstraint[] mnuNodoNazionale_constraints = new UISecConstraint[]{};
		UISecConstraint mnuNodoNazionale_ctr = new ComplexUISecConstraint(
				mnuNodoNazionale_constraints);
		allConstraints.put("mnuNodoNazionale", mnuNodoNazionale_ctr);

		return allConstraints;
	}

}
