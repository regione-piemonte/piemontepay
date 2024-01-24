/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.255+0200")
@StaticMetamodel(CblDErrore.class)
public class CblDErrore_ {
	public static volatile SingularAttribute<CblDErrore, Long> id;
	public static volatile SingularAttribute<CblDErrore, String> codiceErrore;
	public static volatile SingularAttribute<CblDErrore, String> descrizioneErrore;
	public static volatile SingularAttribute<CblDErrore, Boolean> flagMail;
	public static volatile SingularAttribute<CblDErrore, Boolean> flagRielaborazione;
	public static volatile SingularAttribute<CblDErrore, String> tipologia;
	public static volatile ListAttribute<CblDErrore, CblTElaborazione> cblTElaboraziones;
}
