/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import it.csi.epay.epayfeapi.dto.ChiamanteEsternoDTO;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.wildfly.common.Assert.assertTrue;


@QuarkusTest
@QuarkusTestResource ( H2DatabaseTestResource.class )
public class ChiamanteEsternoServiceTest {

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Test
	void whenFindAllChiamanteEsterno_thenChiamanteEsternoShouldBeFound () {
		List<ChiamanteEsternoDTO> list = chiamanteEsternoService.findAllChiamanteEsterno ();
		assertTrue ( list.size () == 2 );
	}
}
