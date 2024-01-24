/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.725+0200")
@StaticMetamodel(Application.class)
public class Application_ {
	public static volatile SingularAttribute<Application, String> id;
	public static volatile SingularAttribute<Application, String> applicationname;
	public static volatile SingularAttribute<Application, String> cliente;
	public static volatile SingularAttribute<Application, String> esercemail;
	public static volatile SingularAttribute<Application, String> note;
	public static volatile SingularAttribute<Application, String> numeroverde;
	public static volatile SingularAttribute<Application, String> progetto;
	public static volatile SingularAttribute<Application, BigDecimal> redirectNewmdp;
	public static volatile SingularAttribute<Application, String> referentecsi;
	public static volatile SingularAttribute<Application, Timestamp> validoAl;
	public static volatile SingularAttribute<Application, Timestamp> validoDal;
	public static volatile ListAttribute<Application, Applicationcustomfield> applicationcustomfields;
	public static volatile ListAttribute<Application, Applicationdetail> applicationdetails;
	public static volatile ListAttribute<Application, FlussoSingoloPagamento> flussoSingoloPagamentos;
	public static volatile ListAttribute<Application, Icicodiciimm> icicodiciimms;
	public static volatile ListAttribute<Application, IuvOttici> iuvOtticis;
	public static volatile SingularAttribute<Application, MdpAppwsauth> mdpAppwsauth;
	public static volatile ListAttribute<Application, MdpBckofficegroup> mdpBckofficegroups;
	public static volatile ListAttribute<Application, MdpErrori> mdpErroris;
	public static volatile ListAttribute<Application, NodoErrori> nodoErroris;
	public static volatile ListAttribute<Application, Enti> entis;
}
