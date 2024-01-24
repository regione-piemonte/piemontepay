/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-29T09:43:34.917+0100")
@StaticMetamodel(EpaypaTEnte.class)
public class EpaypaTEnte_ {
	public static volatile SingularAttribute<EpaypaTEnte, Integer> idEnte;
	public static volatile SingularAttribute<EpaypaTEnte, String> codFiscaleEnte;
	public static volatile SingularAttribute<EpaypaTEnte, String> codGs1Gln;
	public static volatile SingularAttribute<EpaypaTEnte, String> codInterbancario;
	public static volatile SingularAttribute<EpaypaTEnte, String> denominazione;
	public static volatile SingularAttribute<EpaypaTEnte, String> email;
	public static volatile SingularAttribute<EpaypaTEnte, byte[]> logo;
}
