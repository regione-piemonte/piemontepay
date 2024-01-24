/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.exception;

import java.text.MessageFormat;

import it.csi.epay.epaywsosrv.enumeration.IssueEnum;

public class FacadeException extends EPaywsoException {
	private static final long serialVersionUID = 1L;

	public FacadeException(IssueEnum issue) {
		this(issue.getCod(), issue.getDes());
	}

	public FacadeException(IssueEnum issue, String arg0) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, ""));
	}

	public FacadeException(IssueEnum issue, String arg0, String arg1) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, ""));
	}

	public FacadeException(IssueEnum issue, String arg0, String arg1, String arg2) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2));
	}

	public FacadeException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3));
	}

	public FacadeException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4));
	}

	public FacadeException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4, String decscriptionArg5) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4, decscriptionArg5));
	}

	public FacadeException(String cod) {
		super(cod);
	}

	public FacadeException(String cod, String des) {
		super(cod, des);
	}

	public FacadeException(String cod, String des, Throwable cause) {
		super(cod, des, cause);
	}

}
