/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.482+0200")
@StaticMetamodel(CblTPsp.class)
public class CblTPsp_ {
	public static volatile SingularAttribute<CblTPsp, Long> id;
	public static volatile SingularAttribute<CblTPsp, String> denominazionePsp;
	public static volatile SingularAttribute<CblTPsp, Boolean> flagRiconciliabile;
	public static volatile ListAttribute<CblTPsp, CblTFlussoOrigine> cblTFlussoOrigines;
	public static volatile SingularAttribute<CblTPsp, String> identificativoPsp;
}
