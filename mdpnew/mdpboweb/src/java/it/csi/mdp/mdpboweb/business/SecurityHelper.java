/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;

import java.util.*;
import java.io.*;
import it.csi.csi.porte.InfoPortaDelegata;
import it.csi.csi.porte.proxy.PDProxy;
import it.csi.csi.util.xml.PDConfigReader;
import it.csi.csi.wrapper.SystemException;
import it.csi.csi.wrapper.UnrecoverableException;
import it.csi.iride2.policy.entity.*;
import it.csi.iride2.policy.exceptions.*;
import it.csi.iride2.policy.interfaces.PolicyEnforcerBaseService;
import it.csi.iride2.iridefed.entity.Ruolo;
import it.csi.iride2.iridefed.exceptions.BadRuoloException;
import it.csi.mdp.mdpboweb.dto.*;
import org.apache.log4j.*;
import it.csi.mdp.mdpboweb.util.*;

/*PROTECTED REGION ID(R-952919469) ENABLED START*/

/*PROTECTED REGION END*/

public class SecurityHelper {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".security");

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi di supporto alla sicurezza.
	//////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////
	//// Metodi per la sicurezza basata su IRIDE2
	///////////////////////////////////////////////////////////////////////////////

	public final static String IRIDE_PEP_RESOURCE = "/META-INF/iride2_pep_defPD_ejb.xml";

	it.csi.iride2.policy.interfaces.PolicyEnforcerBaseService iride = null;

	protected PolicyEnforcerBaseService getIridePEP() {
		if (this.iride == null) {
			InputStream is = getClass().getResourceAsStream(IRIDE_PEP_RESOURCE);
			if (is != null) {
				try {
					InfoPortaDelegata info = PDConfigReader.read(is);
					this.iride = (PolicyEnforcerBaseService) PDProxy
							.newInstance(info);
					return this.iride;
				} catch (Exception e) {
					log.error(
							"[SecurityHelper::getIridePEP] errore nella parsificazione della configurazione di IRIDE2:"
									+ e, e);
					throw new IllegalArgumentException(
							"errore nella parsificazione della configurazione di IRIDE2");
				}
			} else
				log.error("[SecurityHelper::getIridePEP] configurazione di IRIDE2 non trovata");
			throw new IllegalArgumentException(
					"configurazione di IRIDE2 non trovata");
		} else
			return this.iride;
	}

	public static final String IRIDE_ID_SESSIONATTR = "iride2_id";

	protected Identita getCurrentUser(Map session) throws BEException {
		Identita id = (Identita) session.get(IRIDE_ID_SESSIONATTR);
		if (id == null) {
			log.error("[SecurityHelper::getCurrentUser] Errore nel reperimento del current user dalla sessione: attributo non trovato");
			throw new IllegalStateException(
					"Errore nel reperimento del current user dalla sessione: attributo non trovato");
		} else {
			return id;
		}
	}

	protected it.csi.mdp.mdpboweb.dto.common.UserInfo getCurrentUserInfo(
			Map session) throws BEException {
		it.csi.mdp.mdpboweb.dto.common.UserInfo userInfo = (it.csi.mdp.mdpboweb.dto.common.UserInfo) session
				.get("appDatacurrentUser");
		if (userInfo == null) {
			log.error("[SecurityHelper::getCurrentUser] Errore nel reperimento del current user dalla sessione: attributo non trovato");
			throw new IllegalStateException(
					"Errore nel reperimento del current user dalla sessione: attributo non trovato");
		} else {
			return userInfo;
		}
	}

	public final static Application IRIDE2_APPLICATION = new Application(
			"MDPNEW");

	public boolean verifyCurrentUserForRole(Map session, String roleCode,
			String domainCode) throws BEException {
		Identita currentUser = getCurrentUser(session);
		Ruolo rol = new Ruolo();

		rol.setCodiceRuolo(roleCode);
		rol.setCodiceDominio(domainCode);
		try {
			return getIridePEP().isPersonaInRuolo(getCurrentUser(session), rol);
		} catch (InternalException e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForRole] Errore interno in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException("Errore interno in verifyCurrentUserForRole:"
					+ e, e);
		} catch (IdentitaNonAutenticaException e) {
			log.error("[SecurityHelper::verifyCurrentUserForRole] identita' non autentica ["
					+ currentUser + "]," + e);
			throw new BEException(
					"verifyCurrentUserForRole: identita' non autentica ["
							+ currentUser + "]," + e, e);
		} catch (BadRuoloException e) {
			log.error("[SecurityHelper::verifyCurrentUserForRole] ruolo ["
					+ rol + "] non valido" + e);
			throw new BEException("verifyCurrentUserForRole: ruolo [" + rol
					+ "] non valido" + e, e);
		} catch (UnrecoverableException e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForRole] Errore non recuperabile in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException(
					"Errore non recuperabile in verifyCurrentUserForRole:" + e,
					e);
		} catch (SystemException e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForRole] Errore di sistema in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException(
					"Errore di sistema in verifyCurrentUserForRole:" + e, e);
		} catch (Exception e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForRole] Errore interno in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException("Errore interno in verifyCurrentUserForRole:"
					+ e, e);
		}
	}

	/**
	 * estrae il prefisso dal cod ruolo in formato <idruolo>@<coddominio>
	 * @param codRuolo
	 */
	private String getPrefixFromCodRuolo(String codRuolo) {
		return codRuolo.substring(0, codRuolo.indexOf("@"));
	}

	/**
	 * estrae il codice dominio dal cod ruolo in formato <idruolo>@<coddominio>
	 * @param codRuolo
	 */
	private String getDomainFromCodRuolo(String codRuolo) {
		return codRuolo.substring(codRuolo.indexOf("@") + 1);
	}

	public boolean verifyCurrentUserForUC(Map session, String useCaseCode)
			throws BEException {
		Identita currentUser = getCurrentUser(session);
		UseCase uc = new UseCase();

		uc.setAppId(IRIDE2_APPLICATION);
		uc.setId(useCaseCode);
		try {
			it.csi.mdp.mdpboweb.dto.common.UserInfo currentUserInfo = getCurrentUserInfo(session);
			if (currentUserInfo == null
					|| currentUserInfo.getCodRuolo() == null
					|| currentUserInfo.getCodRuolo().length() == 0) {
				// verifica dell'abilitazione allo UC senza tener conto del ruolo corrente
				return getIridePEP().isPersonaAutorizzataInUseCase(
						getCurrentUser(session), uc);
			} else {
				// verifica dell'abilitazione allo UC tenendo conto del ruolo corrente
				Ruolo[] ruoliForPersonaInUseCase = getIridePEP()
						.findRuoliForPersonaInUseCase(currentUser, uc);

				String codDominioRuolo = getDomainFromCodRuolo(currentUserInfo
						.getCodRuolo());
				String codRuoloRuolo = getPrefixFromCodRuolo(currentUserInfo
						.getCodRuolo());
				boolean authorized = false;
				Ruolo currentRole = new Ruolo(codRuoloRuolo, codDominioRuolo);
				for (Ruolo ruolo : ruoliForPersonaInUseCase) {
					if (ruolo != null
							&& currentRole.getMnemonico().equals(
									ruolo.getMnemonico())) {
						authorized = true;
						break;
					}
				}
				return authorized;
			}
		} catch (InternalException e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForUC] Errore interno in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException("Errore interno in verifyCurrentUserForUC:"
					+ e, e);
		} catch (IdentitaNonAutenticaException e) {
			log.error("[SecurityHelper::verifyCurrentUserForUC] identita' non autentica ["
					+ currentUser + "]," + e);
			throw new BEException(
					"verifyCurrentUserForUC: identita' non autentica ["
							+ currentUser + "]," + e, e);
		} catch (NoSuchUseCaseException e) {
			log.error("[SecurityHelper::verifyCurrentUserForUC] use case ["
					+ uc + "] non valido" + e);
			throw new BEException("verifyCurrentUserForUC: use case [" + uc
					+ "] non valido" + e, e);
		} catch (NoSuchApplicationException e) {
			log.error("[SecurityHelper::verifyCurrentUserForUC] applicazione "
					+ IRIDE2_APPLICATION + " non valida" + e);
			throw new BEException("verifyCurrentUserForUC: applicazione "
					+ IRIDE2_APPLICATION + " non valida" + e, e);
		} catch (UnrecoverableException e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForUC] Errore non recuperabile in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException(
					"Errore non recuperabile in verifyCurrentUserForUC:" + e, e);
		} catch (SystemException e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForUC] Errore di sistema in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException(
					"Errore di sistema in verifyCurrentUserForUC:" + e, e);
		} catch (Exception e) {
			log.error(
					"[SecurityHelper::verifyCurrentUserForUC] Errore interno in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException("Errore interno in verifyCurrentUserForUC:"
					+ e, e);
		}
	}

	public boolean verifyCurrentUserForActor(Map session, String actorCode)
			throws BEException {
		Identita currentUser = getCurrentUser(session);
		Actor act = new Actor();

		act.setAppId(IRIDE2_APPLICATION);
		act.setId(actorCode);
		try {
			Actor[] actors = getIridePEP().findActorsForPersonaInApplication(
					currentUser, IRIDE2_APPLICATION);
			if (actors != null) {
				for (int i = 0; i < actors.length; i++) {
					Actor actor = actors[i];
					if (actor.equals(act))
						return true;
				}
				return false;
			} else
				return false;
		} catch (InternalException e) {
			log.error("[SecurityHelper::verifyCurentUserForActor] Errore interno in verifyCurrentUserForUC:"
					+ e);
			throw new BEException("Errore interno in verifyCurrentUserForUC:"
					+ e, e);
		} catch (IdentitaNonAutenticaException e) {
			log.error(
					"[SecurityHelper::verifyCurentUserForActor] identita' non autentica ["
							+ currentUser + "]," + e, e);
			throw new BEException(
					"verifyCurrentUserForUC: identita' non autentica ["
							+ currentUser + "]," + e, e);
		} catch (NoSuchApplicationException e) {
			log.error(
					"[SecurityHelper::verifyCurentUserForActor] applicazione "
							+ IRIDE2_APPLICATION + " non valida" + e, e);
			throw new BEException("verifyCurrentUserForUC: applicazione "
					+ IRIDE2_APPLICATION + " non valida" + e, e);
		} catch (UnrecoverableException e) {
			log.error(
					"[SecurityHelper::verifyCurentUserForActor] Errore non recuperabile in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException(
					"Errore non recuperabile in verifyCurrentUserForUC:" + e, e);
		} catch (SystemException e) {
			log.error(
					"[SecurityHelper::verifyCurentUserForActor] Errore di sistema in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException(
					"Errore di sistema in verifyCurrentUserForUC:" + e, e);
		} catch (Exception e) {
			log.error(
					"[SecurityHelper::verifyCurentUserForActor] Errore interno in verifyCurrentUserForUC:"
							+ e, e);
			throw new BEException("Errore interno in verifyCurrentUserForUC:"
					+ e, e);
		}
	}

	///////////////////////////////////////////////////////////////////////////////
	//// Metodi per la sicurezza custom
	///////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-1742205394) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	/*PROTECTED REGION END*/
}
