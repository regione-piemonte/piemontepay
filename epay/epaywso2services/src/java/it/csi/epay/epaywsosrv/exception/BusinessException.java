/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.exception;

import java.text.MessageFormat;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;

public class BusinessException extends EPaywsoException {
	private static final long serialVersionUID = 1L;

	public BusinessException(IssueEnum issue) {
		this(issue.getCod(), issue.getDes());
	}

	public BusinessException(IssueEnum issue, Throwable cause) {
		this(issue.getCod(), issue.getDes(), cause);
	}

	public BusinessException(IssueEnum issue, String arg0) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0));
	}

	public BusinessException(IssueEnum issue, String arg0, Throwable cause) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0), cause);
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1));
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, Throwable cause) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1), cause);
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2));
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2, Throwable cause) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2), cause);
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3));
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, Throwable cause) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3), cause);
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4));
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4, Throwable cause) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4), cause);
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4, arg5));
	}

	public BusinessException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, Throwable cause) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4, arg5), cause);
	}

	public BusinessException(String cod) {
		super(cod);
	}

	public BusinessException(String cod, String des) {
		super(cod, des);
	}

	public BusinessException(String cod, String des, Throwable cause) {
		super(cod, des, cause);
	}

}
