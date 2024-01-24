/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule.jobimpl;

import it.csi.epay.epaywsosrv.dto.RichiestaLightDto;
import it.csi.epay.epaywsosrv.util.LogAndWatch;
import it.csi.epay.epaywsosrv.util.MailHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.mail.Session;
import java.util.*;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

public class JobBase {
	static private final String CLASSNAME = JobBase.class.getSimpleName();
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".scheduler");

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;

	/**
	 * Raggruppa per id la lista di richieste ricevuta, ottenendo una lista di liste di richieste.
	 * N.B. Si assume che la lista ricevuta sia ordinata per id.
	 * 
	 * @param richiestaSortedList
	 *            lista di richieste ordinata per id
	 * @return lista di liste di richieste, ogni lista contiene richieste con uguale id
	 */
	protected List<List<RichiestaLightDto>> groupRichiesteById(List<RichiestaLightDto> richiestaSortedList) {
		List<List<RichiestaLightDto>> richiestaGroupedByIdList = new ArrayList<List<RichiestaLightDto>>();

		// raggruppa le richieste facendo uso di una hash-map temporanea
		if (richiestaSortedList != null) {
			HashMap<Long, List<RichiestaLightDto>> map = new HashMap<Long, List<RichiestaLightDto>>();
			ArrayList<Long> sortedIdList = new ArrayList<Long>();
			//
			for (RichiestaLightDto dto : richiestaSortedList) {
				Long id = dto.getId();
				if (map.containsKey(id))
					map.get(id).add(dto);
				else {
					ArrayList<RichiestaLightDto> list = new ArrayList<RichiestaLightDto>();
					list.add(dto);
					map.put(id, list);
				}

				if (!sortedIdList.contains(id))
					sortedIdList.add(id);
			}

			// appiattisce il raggruppamento nella hash-map temporanea in una lista di liste secondo l'ordine degli id originale
			for (Long sortedId : sortedIdList) {
				richiestaGroupedByIdList.add(map.get(sortedId));
			}
		}

		return richiestaGroupedByIdList;
	}

	protected void sendEmail(String templateName, Map<String, Object> templateCtx, Properties params) {
		String methodName = "sendEmail";
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);

		try {
			lw.start();

			String mailSender = params.getProperty("mail.sender");
			String mailRecipientsTo = params.getProperty("mail.recipients.to");
			String mailRecipientsCC = params.getProperty("mail.recipients.cc");
			String mailSubject = params.getProperty("mail.subject");

			lw.debug("mailSender:" + mailSender);
			lw.debug("mailRecipientsTo:" + mailRecipientsTo);
			lw.debug("mailRecipientsCC:" + mailRecipientsCC);
			lw.debug("mailSubject:" + mailSubject);

			//@formatter:off
			MailHelper mailHelper = new MailHelper(mailSession);
			mailHelper.setSender(mailSender)
				.setToRecipients(mailRecipientsTo)
				.setCcRecipients(mailRecipientsCC)
				.setSubject(mailSubject)
				.setTemplateName(templateName)
				.setTemplateContext(templateCtx)
				.setContentType("text/html").sendMail();
			//@formatter:on

			lw.info("alert mail sent");

		} catch (Exception e) {
			lw.error("cannot send mail", e);

		} finally {
			lw.stop();
		}
	}

}
