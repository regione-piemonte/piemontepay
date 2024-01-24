/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.presentation.util.ApplicationConfiguration;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("download-template")
@Results({
//@formatter:off
	@Result(name = SUCCESS,	type = "stream", params = { "contentType", "${tipoFormato.contentType}", "inputName", "inputStream", "contentDisposition", "attachment; filename=\"${templateFilename}\"" })
//@formatter:on
})
public class DownloadTemplateAction extends EpaypawebBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = DownloadTemplateAction.class.getSimpleName();
	static private final String SYSTEM_FILE_SEPATATOR = System.getProperty("file.separator");

	private String templateProperty;
	private TipoFormatoFileEnum tipoFormato;
	private InputStream inputStream;

	@Override
	public String execute() {
		String methodName = "execute";
		

		String result;

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			URL url = new URL(eventuallyAddFileSeparatorToPath(getTemplateURL()) + getTemplateFilename());
			log.debug("url:" + url);
			inputStream = url.openStream();
			
			result = SUCCESS;
			
		} catch (IOException e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	private String eventuallyAddFileSeparatorToPath(String path) {
		String resPath;

		if (path != null) {
			resPath = path.endsWith(SYSTEM_FILE_SEPATATOR) ? path : path + SYSTEM_FILE_SEPATATOR;
		} else {
			resPath = SYSTEM_FILE_SEPATATOR;
		}

		return resPath;
	}

	public String getTemplateProperty() {
		return templateProperty;
	}

	public void setTemplateProperty(String templateProperty) {
		this.templateProperty = templateProperty;
	}

	public TipoFormatoFileEnum getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(TipoFormatoFileEnum tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public String getTemplateURL() {
		return ApplicationConfiguration.getApplicationConfiguration().getStringProperty("template.url");
	}

	public String getTemplateFilename() {
		return ApplicationConfiguration.getApplicationConfiguration().getStringProperty(templateProperty);
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
