/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.307+0200")
@StaticMetamodel(CblDTipoAcquisizione.class)
public class CblDTipoAcquisizione_ {
	public static volatile SingularAttribute<CblDTipoAcquisizione, Long> id;
	public static volatile SingularAttribute<CblDTipoAcquisizione, String> codice;
	public static volatile SingularAttribute<CblDTipoAcquisizione, String> descrizione;
	public static volatile ListAttribute<CblDTipoAcquisizione, CblTFlussoOrigine> cblTFlussoOrigines;
}
