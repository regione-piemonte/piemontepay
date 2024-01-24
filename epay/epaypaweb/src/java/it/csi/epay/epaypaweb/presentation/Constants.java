/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation;

public interface Constants {

	public static final String USER_CODE_CONTEXT_KEY = "user.code.context.key";
	public static final String IRIDE_AUTH_TOKEN_KEY = "iride.auth.token.key";
	public static final String SESSION_CONTEXT_SESSION_KEY = "session.context.session.key";
	public static final String ENTI_KEY = "ENTI_KEY";
	public static final String RUOLI_KEY = "RUOLI_KEY";
	public static final String PROFILI_KEY = "PROFILI_KEY";
	public static final String ENCODING = "ISO-8859-1";

	public static class RESULT_CODES {

		public static final String OK = "OK";

		public static final String NOT_ALLOWED = "NOT_ALLOWED";

		public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

		public static final String BAD_REQUEST = "BAD_REQUEST";

		public static final String NOT_FOUND = "NOT_FOUND";

		public static final String WSO2_BROADCASTING_FAILED = "WSO2_BROADCASTING_FAILED";

	}
}
