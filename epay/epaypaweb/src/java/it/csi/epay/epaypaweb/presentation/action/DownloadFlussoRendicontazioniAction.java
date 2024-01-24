/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.TemplateBusiness;
import it.csi.epay.epaypaweb.dto.ColonnaTemplateDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.TemplateDto;
import it.csi.epay.epaypaweb.dto.mapper.TemplateMapperRiversamento;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.util.ApplicationConfiguration;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("scarica-flusso-rendicontazioni")
@Results({
//@formatter:off
	@Result(name = SUCCESS,	type = "stream", params = { "contentType", "${tipoFormato.contentType}", "inputName", "inputStream", "contentDisposition", "attachment; filename=\"${filename}\"" })
//@formatter:on
})
public class DownloadFlussoRendicontazioniAction extends DownloadFlussoAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = DownloadFlussoRendicontazioniAction.class.getSimpleName();

	@EJB
	private TemplateBusiness templateBusiness;

	@Override
	@Authorization(cdu = "DLD_FR")
	public String execute() {
		String methodName = "execute";
		

		FlussoCompletoDto<RiversamentoDto> flusso = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			String endpoint = ApplicationConfiguration.getApplicationConfiguration ()
					.getStringProperty ("epaywsosrv.url" );

			flusso = gestioneFlussiBusiness.getFlussoRendicontazione(idFlusso );

			TemplateDto template = templateBusiness.getTemplate(flusso.getFlusso().getIdEnte(), flusso.getFlusso().getTipoFlusso().getId());
			//metodo che elimina le ultime 3 colonne dal tempate dei flussi base, 
//			inserite nel template dal momento che ne e stata fatta richiesta nel job job
			//al momento commentato, da scommentare se dovesse presentarsi la richiesta
//			eliminaColonneTemplate(template);


			if (template != null) {
				byte[] data = templateBusiness.generate(flusso, template, new TemplateMapperRiversamento(), tipoFormato).getData();
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

//	private void eliminaColonneTemplate(TemplateDto template) {
//		List<ColonnaTemplateDto> colonneNew = new LinkedList<ColonnaTemplateDto>();
//		for( ColonnaTemplateDto temp: template.getColonneTemplate())
//		{
//
//			if(!CampoFlussoEnum.FREND_ITEM_STATO_FLUSSO_AGGREGATO.equals(temp.getCampoFlusso()) 
//					&& !CampoFlussoEnum.FREND_ITEM_DATI_AGGIUNTIVI_FLUSSO.equals(temp.getCampoFlusso()) 
//					&& !CampoFlussoEnum.FREND_ITEM_DATA_ORA_INZIO_ELABORAZIONE_REPORT.equals(temp.getCampoFlusso()) )
//			{
//				colonneNew.add(temp);
//			}
//		}
//		template.setColonneTemplate(colonneNew);
//	}

}
