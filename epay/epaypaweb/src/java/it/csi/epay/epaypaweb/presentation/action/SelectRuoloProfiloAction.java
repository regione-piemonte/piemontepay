/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

import it.csi.epay.epaypaweb.business.interf.AccessoBusiness;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.presentation.Constants;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;



/*
 * Questa action viene resa deprecata dall'integrazione con CATALOG
 * che comporta la rimozione di ruolo/profilo.
 * Le autorizzazioni (CDU, codici di versamento) sono ora indipendenti da ruolo e profilo
 */

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = INPUT, location = "pages/select-ruolo-profilo.jsp"),
	@Result(name = SUCCESS, type = "redirect", location = "entry-main-menu.do")
//@formatter:on
})
@Deprecated
public class SelectRuoloProfiloAction extends EpaypawebBaseAction implements Preparable {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = SelectRuoloProfiloAction.class.getSimpleName();

	private Integer idRuolo;
	private List<RuoloDto> ruoli;

	private Integer idProfilo;
	private List<ProfiloDto> profili;

	@EJB
	private AccessoBusiness accessoBusiness;

	@SuppressWarnings("unchecked")
	@Override
	public void prepare() throws Exception {
		String methodName = "prepare";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		Integer idEnte = getSessionContext().getEnte().getId();
		Long idUtente = getSessionContext().getUtente().getId();

		if (idEnte != null && idUtente != null) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			if (!session.containsKey(Constants.RUOLI_KEY)) {
				ruoli = accessoBusiness.getRuoloList(idUtente, idEnte);
				session.put(Constants.RUOLI_KEY, ruoli);
			} else {
				ruoli = (List<RuoloDto>) session.get(Constants.RUOLI_KEY);
			}

			if (!session.containsKey(Constants.PROFILI_KEY)) {
				profili = accessoBusiness.getProfiloList(idUtente, idEnte);
				session.put(Constants.PROFILI_KEY, profili);
			} else {
				profili = (List<ProfiloDto>) session.get(Constants.PROFILI_KEY);
			}
		}
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
	}

	@Action("entry-select-ruolo-profilo")
	@SkipValidation
	public String entrySelectRuoloProfilo() {
        /*
         * POST INTEGRAZIONE: QUESTA PAGINA NON DEVE PIU' ESSERE UTILIZZATA
         */
        if ( true ) {
            throw new RuntimeException ( "Action deprecata" );
        }

		String methodName = "entrySelectRuoloProfilo";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		String result = null;

		SessionContext sessionContext = getSessionContext();
		if (ruoli.size() == 0) {
            String message = getText ( "nessun.ruolo.configurato",
                new String [] { sessionContext.getUtente ().getCod (), sessionContext.getUtente ().getNomeCompleto () } );
			log.error(message);
			addActionError(message);

		} else if (ruoli.size() == 1) {
			sessionContext.setRuolo(ruoli.get(0));
			setIdRuolo(ruoli.get(0).getId());
		}

		if (profili.size() == 0) {
            String message = getText ( "nessun.profilo.configurato",
                new String [] { sessionContext.getUtente ().getCod (), sessionContext.getUtente ().getNomeCompleto () } );
			log.error(message);
			addActionError(message);

		} else if (profili.size() == 1) {
			sessionContext.setProfilo(profili.get(0));
			setIdProfilo(profili.get(0).getId());
		}

		if (ruoli.size() == 1 && profili.size() == 1) {
			ActionContext.getContext().getSession().remove(Constants.RUOLI_KEY);
			ActionContext.getContext().getSession().remove(Constants.PROFILI_KEY);

			result = SUCCESS;

		} else if (ruoli.size() == 0 || profili.size() == 0) {
			result = "system-error";

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

	@Action("ruolo-profilo-selected")
	public String ruoloProfiloSelected() throws Exception {
        // POST INTEGRAZIONE: QUESTA PAGINA NON DEVE PIU' ESSERE UTILIZZATA
        if ( true ) {
            throw new RuntimeException ( "Pagina deprecata" );
        }

		String methodName = "ruoloProfiloSelected";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		getSessionContext().setRuolo(accessoBusiness.getRuolo(idRuolo));
		ActionContext.getContext().getSession().remove(Constants.RUOLI_KEY);

		getSessionContext().setProfilo(accessoBusiness.getProfilo(idProfilo));
		ActionContext.getContext().getSession().remove(Constants.PROFILI_KEY);

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return SUCCESS;
	}

	public Integer getIdRuolo() {
		return idRuolo;
	}

	@RequiredFieldValidator(key = "campo.obbligatorio.ruolo")
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}

	public List<RuoloDto> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<RuoloDto> ruoli) {
		this.ruoli = ruoli;
	}

	public Integer getIdProfilo() {
		return idProfilo;
	}

	@RequiredFieldValidator(key = "campo.obbligatorio.profilo")
	public void setIdProfilo(Integer idProfilo) {
		this.idProfilo = idProfilo;
	}

	public List<ProfiloDto> getProfili() {
		return profili;
	}

	public void setProfili(List<ProfiloDto> profili) {
		this.profili = profili;
	}

}
