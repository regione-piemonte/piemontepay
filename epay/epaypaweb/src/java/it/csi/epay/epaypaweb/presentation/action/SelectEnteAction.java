/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import it.csi.epay.epaypaweb.business.interf.AccessoBusiness;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfilazioneEpayPaCatalogSrvDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.interf.EpayPaCatalogSrvServiceMgr;
import it.csi.epay.epaypaweb.presentation.Constants;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import it.csi.epay.epaypaweb.presentation.util.ApplicationConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.ejb.EJB;
import java.util.List;
import java.util.Map;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = INPUT, location = "pages/select-ente.jsp"),
    /* PRE INTEGRAZIONE : */ // @Result(name = SUCCESS, type = "redirect", location = "entry-select-ruolo-profilo.do")
    /* POST INTEGRAZIONE : */ @Result ( name = SUCCESS, type = "redirect", location = "entry-main-menu.do" )
//@formatter:on
})
public class SelectEnteAction extends EpaypawebBaseAction implements Preparable {
	private static final String CODICE_CDU_LOGIN = "LOGIN";
    static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = SelectEnteAction.class.getSimpleName();

    static protected Logger log = LogManager.getLogger ( APPLICATION_CODE + ".presentation.accesso" );

	@EJB
	private AccessoBusiness accessoBusiness;

	private Integer idEnte;
	private List<EnteDto> enti;

    private String getEyapPaCatalogSrvEndpoint () {
        String key = EpayPaCatalogSrvServiceMgr.URL_PROPERTY;

        String endpoint = ApplicationConfiguration.getApplicationConfiguration ()
            .getStringProperty ( key );

        if ( StringUtils.isEmpty ( endpoint ) ) {
            throw new RuntimeException ( "Parametro di configurazione non disponibile: " + key );
        }

        return endpoint;
    }

    private List<EnteDto> caricaEnti () throws BusinessException {
        String methodName = "caricaEnti";
        log.info ( CLASSNAME + " " + methodName + " - START" );

        try {
            SessionContext sessionContext = getSessionContext ();
            if ( sessionContext != null ) {
                try {
                    return accessoBusiness.getEnteList (
                        getEyapPaCatalogSrvEndpoint (),
                        sessionContext.getUtente ().getCod () );
                } catch ( Exception e ) {
                    String message = "Errore nel recupero della lista degli enti abilitati: " + e.getMessage ();
                    log.error ( message );
                    addActionError ( message );
                    throw e;
                }
            } else {
                throw new RuntimeException ( "SessionContext non disponibile" );
            }
        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public void prepare() throws Exception {
		String methodName = "prepare";
		log.info ( CLASSNAME + " " + methodName + " - START" );

		Map<String, Object> session = ActionContext.getContext().getSession();
		if (!session.containsKey(Constants.ENTI_KEY)) {
			SessionContext sessionContext = getSessionContext();
			if (sessionContext != null) {
                enti = caricaEnti ();
				session.put(Constants.ENTI_KEY, enti);
			}
		} else {
			enti = (List<EnteDto>) session.get(Constants.ENTI_KEY);
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
	}

	@Action("entry-select-ente")
	@SkipValidation
    public String entrySelectEnte () throws Exception {
		String methodName = "entrySelectEnte";
		log.info ( CLASSNAME + " " + methodName + " - START" );

		String result = null;

		SessionContext sessionContext = getSessionContext();
		if (enti.size() == 0) {
            String message = getText ( "nessun.ente.configurato",
                new String [] { sessionContext.getUtente ().getCod (), sessionContext.getUtente ().getNomeCompleto () } );
			log.error(message);
			addActionError(message);
			result = "system-error";

		} else if (enti.size() == 1) {
		    /*
	         * POST INTEGRAZIONE CONFIGURATORE: CARICO GIA' QUI' LE AUTORIZZAZIONI
	         * IN QUANTO NON DIPENDENTI DALLA SUCCESSIVA SELEZIONE DI RUOLO E PROFILO
	        */
            caricaAutenticazione ( enti.get ( 0 ) );
			ActionContext.getContext().getSession().remove(Constants.ENTI_KEY);
			result = SUCCESS;

		} else {
			result = INPUT;
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

	@Override
	public void validate() {
		String methodName = "validate";
		log.info ( CLASSNAME + " " + methodName + " - START" );

		if (hasFieldErrors()) {
			addActionMessage(getText("message.verificare.gli.errori"));
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
	}

    private void caricaAutenticazione ( EnteDto ente ) throws BusinessException {
		String methodName = "loadAuth";
        log.info ( CLASSNAME + " " + methodName + " - START" );

        try {
            SessionContext sessionContext = getSessionContext ();

            ProfilazioneEpayPaCatalogSrvDto profilazione = accessoBusiness.getProfilazione (
                getEyapPaCatalogSrvEndpoint (),
                sessionContext.getUtente ().getCod (),
                ente.getCodFiscale () );

            sessionContext.setEnte ( profilazione.getEnte () );
            sessionContext.setListaCdu ( profilazione.getListaCdu () );
            sessionContext.setUtente ( profilazione.getUtente () );
            sessionContext.setCategorieCdu ( profilazione.getCategorieCdu () );
            sessionContext.setCodiciVersamento ( profilazione.getCodiciVersamento () );
            sessionContext.setCodiciVersamentoVisibili ( profilazione.getCodiciVersamentoVisibili () );
            sessionContext.setCodiciVersamentoListeDiCarico( profilazione.getCodiciVersamentoListeDiCarico());
            sessionContext.setRuolo ( profilazione.getRuolo () );
            sessionContext.setProfilo ( profilazione.getProfilo () );

            log.debug ( "Caricata profilazione utente da EpayPaCatalogSrv:\n\n" + profilazione + "\n\n" );

            if ( !isUseCaseEnabled ( CODICE_CDU_LOGIN ) ) {
                throw new RuntimeException ( "Accesso all'applicativo non consentito. Contattare l'amministratore dell'Ente per verificare i dati di accesso concessi." );
            }

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

    }

	@Action("ente-selected")
	public String enteSelected() throws Exception {
		String methodName = "enteSelected";
		log.info ( CLASSNAME + " " + methodName + " - START" );

        if ( enti == null ) {
            /*
             * e' possibile che la lista in sessione sia andata persa? in caso ricarico comunque
             */
            enti = caricaEnti ();
        }

        EnteDto enteSelezionato = accessoBusiness.getEnte ( idEnte, enti );
		ActionContext.getContext().getSession().remove(Constants.ENTI_KEY);

        /*
         * POST INTEGRAZIONE CONFIGURATORE: CARICO GIA' QUI' LE AUTORIZZAZIONI
         * IN QUANTO NON DIPENDENTI DALLA SUCCESSIVA SELEZIONE DI RUOLO E PROFILO
        */
        caricaAutenticazione ( enteSelezionato );

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return SUCCESS;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	@RequiredFieldValidator(key = "campo.obbligatorio.ente")
	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public List<EnteDto> getEnti() {
		return enti;
	}

	public void setEnti(List<EnteDto> enti) {
		this.enti = enti;
	}

}
