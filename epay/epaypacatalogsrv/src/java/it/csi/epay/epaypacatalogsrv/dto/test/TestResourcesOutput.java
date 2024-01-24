/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.test;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class TestResourcesOutput extends ParentOutput {

	private static final long serialVersionUID = 1L;

	public static TestResourcesOutput ok() {
		TestResourcesOutput output = ok(TestResourcesOutput.class);
		return output;
	}

}
