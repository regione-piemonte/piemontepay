/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;

public class Mail implements Serializable {
	private static final long serialVersionUID = -492398113330119382L;
	
	private String from;
	private String alias;
	private Set<InternetAddress> to;
	private Set<InternetAddress> cc;
	private Set<InternetAddress> bcc;
	private String subject;
	private String text;
	private String contentType;
	private Set<Attached> attachedFiles;
	
	//protected LogUtil log = new LogUtil(this.getClass());
	
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}	
			
	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public InternetAddress getFromAndAlias() throws UnsupportedEncodingException {
		return new InternetAddress(from, alias);
	}
	
	/**
	 * @return the to
	 */
	public InternetAddress[] getTo() {
		if (to == null) {
			return null;
		}
		InternetAddress[] ia = new InternetAddress[to.size()];
		ia = to.toArray(ia);
		return ia;
	}
	
	public boolean IsEmptyTo() {
		if (to == null) return true;
		return to.isEmpty();
	}
	
	/**
	 * @param single to the to to set
	 * @throws AddressException 
	 */
	public void setTo(String to) throws AddressException {
		//final String methodName = "setTo";
		if (StringUtils.isBlank(to)) {
			return;
		}

		InternetAddress ia = new InternetAddress(to);
		if (this.to == null) {
			this.to = new HashSet<InternetAddress>();
		}
		this.to.add(ia);
	}
	
	/**
	 * @return the cc
	 */
	public InternetAddress[] getCc() {
		if (cc == null) {
			return null;
		}
		InternetAddress[] ia = new InternetAddress[cc.size()];
		ia = cc.toArray(ia);
		return ia;
	}
	/**
	 * @param cc the cc to set
	 * @throws AddressException 
	 */
	public void setCc(String cc) throws AddressException {
		if (this.cc == null) {
			this.cc = new HashSet<InternetAddress>();
		}
		this.cc.add(new InternetAddress(cc));
	}
	public boolean IsEmptyCc() {
		if (cc == null) return true;
		return cc.isEmpty();
	}
	/**
	 * @return the bcc
	 */
	public InternetAddress[] getBcc() {
		if (bcc == null) {
			return null;
		}
		InternetAddress[] ia = new InternetAddress[bcc.size()];
		ia = bcc.toArray(ia);
		return ia;
	}
	public boolean IsEmptyBcc() {
		if (bcc == null) return true;
		return bcc.isEmpty();
	}/**
	 * @param single bcc the bcc to set
	 * @throws AddressException 
	 */
	public void setBcc(String bcc) throws AddressException {
		if (this.bcc == null) {
			this.bcc = new HashSet<InternetAddress>();
		}
		this.bcc.add(new InternetAddress(bcc));
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the attachedFiles
	 */
	public Set<Attached> getAttachedFiles() {
		if (attachedFiles == null) {
			attachedFiles = new HashSet<Attached>();
		}
		return attachedFiles;
	}

	/**
	 * @param attachedFiles the attachedFiles to set
	 */
	public void setAttachedFiles(Set<Attached> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}

	public boolean hasAttachedFiles() {
		return (attachedFiles != null && !attachedFiles.isEmpty()) ? true : false;
	}

}
