/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.BooleanType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;

public class EpayPostgreSQLDialect extends org.hibernate.dialect.PostgreSQL82Dialect {

	public EpayPostgreSQLDialect() {
		super();
		
		registerFunction("afterCurrentDate", new StandardSQLFunction("after_current_date", new BooleanType()));
		
		registerFunction("beforeCurrentDate", new StandardSQLFunction("before_current_date", new BooleanType()));
		
		registerFunction("intoIntervalDate", new StandardSQLFunction("into_interval_date", new BooleanType()));
	
		registerFunction("pagamentoAttivo", new StandardSQLFunction("pagamento_attivo", new BooleanType()));
		
		registerFunction("statoPagamento", new StandardSQLFunction("stato_pagamento", new StringType()));
		
		registerFunction("pgpSymDecryptBytea", new StandardSQLFunction("pgp_sym_decrypt_bytea"));

		registerFunction("toTimestamp", new StandardSQLFunction("to_timestamp"));
		
		
	}

}
