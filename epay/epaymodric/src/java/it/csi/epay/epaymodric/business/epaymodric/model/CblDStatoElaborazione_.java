/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.281+0200")
@StaticMetamodel(CblDStatoElaborazione.class)
public class CblDStatoElaborazione_ {
	public static volatile SingularAttribute<CblDStatoElaborazione, Long> id;
	public static volatile SingularAttribute<CblDStatoElaborazione, String> codiceStato;
	public static volatile SingularAttribute<CblDStatoElaborazione, String> descrizioneStato;
	public static volatile ListAttribute<CblDStatoElaborazione, CblTElaborazione> cblTElaboraziones;
}
