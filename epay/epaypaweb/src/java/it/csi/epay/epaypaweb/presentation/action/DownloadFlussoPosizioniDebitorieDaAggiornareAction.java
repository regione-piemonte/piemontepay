/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.TemplateBusiness;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapperPosizioneDebitoriaDaAggiornare;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("scarica-flusso-posizionidebitoriedaaggiornare")
@Results({
//@formatter:off
	@Result(name = SUCCESS,	type = "stream", params = { "contentType", "${tipoFormato.contentType}", "inputName", "inputStream", "contentDisposition", "attachment; filename=\"${filename}\"" })
//@formatter:on
})
public class DownloadFlussoPosizioniDebitorieDaAggiornareAction extends DownloadFlussoAction {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = DownloadFlussoPosizioniDebitorieDaAggiornareAction.class.getSimpleName ();

	@EJB
	private TemplateBusiness templateBusiness;

	@Override
	@Authorization(cdu = "DLD_AGPD")
	public String execute() {
		String methodName = "execute";
		

		FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flusso = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			flusso = gestioneFlussiBusiness.getFlussoPosizioniDebitorieDaAggiornare(idFlusso);

			TemplateDto template = templateBusiness.getTemplate(flusso.getFlusso().getIdEnte(), flusso.getFlusso().getTipoFlusso().getId());

			if (template != null) {
				byte[] data = templateBusiness.generate(flusso, template, new TemplateMapperPosizioneDebitoriaDaAggiornare(), tipoFormato).getData();
				filename = templateBusiness.buildFilename(flusso.getFlusso(), tipoFormato);
				inputStream = new ByteArrayInputStream(data);

			} else {
				addActionError(getText("nessun.template.configurato"));
				return "system-error";
			}

		} catch (BusinessException | IOException e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			return "system-error";

		} finally {
			setFileDownloadTokenCookie(pleaseWaitTokenValue);
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return SUCCESS;
	}

}
