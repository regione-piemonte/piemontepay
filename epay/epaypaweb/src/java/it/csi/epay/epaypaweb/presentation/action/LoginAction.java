/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import it.csi.epay.epaypaweb.business.interf.AccessoBusiness;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.dto.UtenteDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.exception.BusinessNotFoundException;
import it.csi.epay.epaypaweb.exception.BusinessTipiFormatoFileEmptyException;
import it.csi.epay.epaypaweb.presentation.Constants;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.*;

import javax.ejb.EJB;
import java.util.List;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;
import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("login")
@Results({
    //@formatter:off
    @Result(name = SUCCESS, type = "redirect", location = "entry-select-ente.do")
    //@formatter:on
})
public class LoginAction extends ActionSupport {
    static private final long serialVersionUID = 1L;
    static private final String CLASSNAME = LoginAction.class.getSimpleName();
    static private final Logger log = LogManager.getLogger(APPLICATION_CODE + ".presentation");

    @EJB
    private AccessoBusiness accessoBusiness;

    private List<EnteDto> enti;

    @Override
    public String execute() {
        String methodName = "execute";
//        LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName);

        SessionContext sessionContext = new SessionContext();

        String codUtente = (String) ActionContext.getContext().get(Constants.USER_CODE_CONTEXT_KEY);

        try {
//            lw.start();
			log.info ( CLASSNAME + " " + methodName + " - START" );

            // UtenteDto utente = accessoBusiness.getUtente(codUtente);
            UtenteDto utente = new UtenteDto ( 0L, codUtente );
            utente.setNome ( codUtente );
            sessionContext.setUtente(utente);

            List<TipoFormatoFileDto> formatiFile = accessoBusiness.getTipoFormatoFileList();
            sessionContext.setFormatiFile(formatiFile);

            Map<String, Object> session = ActionContext.getContext().getSession();
            session.clear();
            session.put(Constants.SESSION_CONTEXT_SESSION_KEY, sessionContext);

        } catch (BusinessNotFoundException e) {
            log.error("Errore imprevisto", e);
            addActionError(getText("login.error.unknown.user", new String[] { codUtente }));
            return "system-error";

        } catch (BusinessTipiFormatoFileEmptyException e) {
            log.error("Errore imprevisto", e);
            addActionError(getText("nessun.tipo.formato.file.configurato"));
            return "system-error";

        } catch (BusinessException e) {
            log.error("Errore imprevisto", e);
            addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
            return "system-error";

        } finally {
//            lw.stop();
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return SUCCESS;
    }

    public List<EnteDto> getEnti() {
        return enti;
    }

    public void setEnti(List<EnteDto> enti) {
        this.enti = enti;
    }

}
