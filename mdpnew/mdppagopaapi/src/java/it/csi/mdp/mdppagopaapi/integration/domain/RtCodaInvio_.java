/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.907+0200")
@StaticMetamodel(RtCodaInvio.class)
public class RtCodaInvio_ {
	public static volatile SingularAttribute<RtCodaInvio, RtCodaInvioPK> id;
	public static volatile SingularAttribute<RtCodaInvio, String> applicationId;
	public static volatile SingularAttribute<RtCodaInvio, Integer> contatoreTentativi;
	public static volatile SingularAttribute<RtCodaInvio, Timestamp> dataInizioTentativi;
	public static volatile SingularAttribute<RtCodaInvio, Timestamp> dataInserimento;
	public static volatile SingularAttribute<RtCodaInvio, Timestamp> dataTentativi;
	public static volatile SingularAttribute<RtCodaInvio, Timestamp> dataUltimaModifica;
	public static volatile SingularAttribute<RtCodaInvio, Integer> numGiorniTentativiKo;
	public static volatile SingularAttribute<RtCodaInvio, String> ultimoEsitoFruitore;
}
