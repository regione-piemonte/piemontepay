/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.mail;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class FreemarkerEscapeDirective implements TemplateDirectiveModel {

	@Override
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map map, TemplateModel[] atemplatemodel, TemplateDirectiveBody body) throws TemplateException, IOException {
		if (body != null) {
			body.render(new EscapeEntities(env.getOut()));
		}
	}

	private static class EscapeEntities extends Writer {
		private final Writer out;

		EscapeEntities(Writer out) {
			this.out = out;
		}

		public void write(char[] cbuf, int off, int len) throws IOException {
			out.write(StringEscapeUtils.escapeHtml4(new String(cbuf, off, len)));
		}

		public void flush() throws IOException {
			out.flush();
		}

		public void close() throws IOException {
			out.close();
		}
	}
}
