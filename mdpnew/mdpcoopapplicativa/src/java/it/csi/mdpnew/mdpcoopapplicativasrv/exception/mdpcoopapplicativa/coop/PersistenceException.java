/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop;

import java.text.MessageFormat;

import it.csi.mdpnew.mdpcoopapplicativasrv.util.coop.IssueEnum;

public class PersistenceException extends MdpcoopException {
	private static final long serialVersionUID = 1L;

	public PersistenceException(IssueEnum issue) {
		this(issue.getCod(), issue.getDes());
	}

	public PersistenceException(IssueEnum issue, String arg0) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0));
	}

	public PersistenceException(IssueEnum issue, String arg0, String arg1) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1));
	}

	public PersistenceException(IssueEnum issue, String arg0, String arg1, String arg2) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2));
	}

	public PersistenceException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3));
	}

	public PersistenceException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4));
	}

	public PersistenceException(IssueEnum issue, String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		this(issue.getCod(), MessageFormat.format(issue.getDes(), arg0, arg1, arg2, arg3, arg4, arg5));
	}

	public PersistenceException(String cod) {
		super(cod);
	}

	public PersistenceException(String cod, String des) {
		super(cod, des);
	}

	public PersistenceException(String cod, String des, Throwable cause) {
		super(cod, des, cause);
	}

}
