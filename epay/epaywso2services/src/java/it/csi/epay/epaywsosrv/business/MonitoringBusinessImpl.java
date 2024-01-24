/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.business;

import static it.csi.epay.epaywsosrv.util.Util.MONITORING_CONFIGFILENAME;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import it.csi.epay.epaywsosrv.dto.MonitorResultDto;
import it.csi.epay.epaywsosrv.dto.QueueStatusDto;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dad.EPaywsoDad;
import it.csi.epay.epaywsosrv.util.LogAndWatch;

public class MonitoringBusinessImpl extends MonitoringBusinessBase implements MonitoringBusiness {
	static private final String CLASSNAME = MonitoringBusinessImpl.class.getSimpleName();

	private URL activeMQURL;
	private String activeMQUser;
	private String activeMQPassword;

	@Inject
	private EPaywsoDad dad;

	class MyAuthenticator extends Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			char[] activeMQPasswordChars = activeMQPassword.toCharArray();
			return new PasswordAuthentication(activeMQUser, activeMQPasswordChars);
		}

	}

	public MonitoringBusinessImpl() {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "MonitoringBusinessImpl", false);

		try {
			lw.start();

			try {
				Properties props = new Properties();
				props.loadFromXML(MonitoringBusinessImpl.class.getResourceAsStream("/META-INF/" + MONITORING_CONFIGFILENAME));

				activeMQURL = new URL(props.getProperty("activemq.url"));
				activeMQUser = props.getProperty("activemq.user");
				activeMQPassword = props.getProperty("activemq.password");
				//
				lw.info("monitoring service initiated");

			} catch (MalformedURLException e) {
				lw.error("invalid ActiveMQ URL", e);

			} catch (InvalidPropertiesFormatException e) {
				lw.error("error reading " + MONITORING_CONFIGFILENAME, e);

			} catch (IOException e) {
				lw.error("error reading " + MONITORING_CONFIGFILENAME, e);

			}
		} finally {
			lw.stop();
		}
	}

	@Override
	public MonitorResultDto getStatus() {
		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, "getStatus", false);

		MonitorResultDto monitorResultDto = new MonitorResultDto();

		try {
			lw.start();

			// test code messaggi attive
			if (activeMQURL != null) {
				try {
					Authenticator.setDefault(new MyAuthenticator());

					InputStream inputStream = activeMQURL.openConnection().getInputStream();
					Document queuesDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);

					XPath xPath = XPathFactory.newInstance().newXPath();
					NodeList nodeList = (NodeList) xPath.evaluate("/queues/queue", queuesDoc, XPathConstants.NODESET);
					//
					List<QueueStatusDto> queueDtoList = new ArrayList<QueueStatusDto>();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Element queueElement = (Element) nodeList.item(i);
						Element statsElement = (Element) queueElement.getElementsByTagName("stats").item(0);
						//
						QueueStatusDto queueDto = new QueueStatusDto();
						queueDto.setName(queueElement.getAttribute("name"));
						queueDto.setQueueSize(Long.parseLong(statsElement.getAttribute("size")));
						queueDto.setConsumerCount(Long.parseLong(statsElement.getAttribute("consumerCount")));
						queueDto.setEnqueueCount(Long.parseLong(statsElement.getAttribute("enqueueCount")));
						queueDto.setDequeueCount(Long.parseLong(statsElement.getAttribute("dequeueCount")));
						//
						queueDtoList.add(queueDto);
					}
					monitorResultDto.setActiveMQInfo(queueDtoList);
					//
					monitorResultDto.setActiveMQStatus("OK");

				} catch (ConnectException e) {
					lw.error("connect Exception to:" + activeMQURL);
					monitorResultDto.setActiveMQStatus(e.getClass().getName() + ":" + e.getMessage());

				} catch (Throwable e) {
					lw.error("exception", e);
					monitorResultDto.setActiveMQStatus(e.getClass().getName() + ":" + e.getMessage());
				}
			} else {
				monitorResultDto.setActiveMQStatus("Config Error");
			}

			// test connessione database
			try {
				dad.ping();
				monitorResultDto.setDatabaseStatus("OK");

			} catch (PersistenceException e) {
				lw.error("exception", e);
				monitorResultDto.setDatabaseStatus(e.getClass().getName() + ":" + e.getMessage());
			}

		} finally {
			lw.stop();
		}

		return monitorResultDto;
	}

	@Override
	public Boolean isAlive() {
		return Boolean.TRUE;
	}

}
