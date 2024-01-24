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
@StaticMetamodel(ReceiptCodaInvio.class)
public class ReceiptCodaInvio_ {
	public static volatile SingularAttribute<ReceiptCodaInvio, ReceiptCodaInvioPK> id;
	public static volatile SingularAttribute<ReceiptCodaInvio, String> applicationId;
	public static volatile SingularAttribute<ReceiptCodaInvio, Integer> contatoreTentativi;
	public static volatile SingularAttribute<ReceiptCodaInvio, Timestamp> dataInizioTentativi;
	public static volatile SingularAttribute<ReceiptCodaInvio, Timestamp> dataInserimento;
	public static volatile SingularAttribute<ReceiptCodaInvio, Timestamp> dataTentativi;
	public static volatile SingularAttribute<ReceiptCodaInvio, Timestamp> dataUltimaModifica;
	public static volatile SingularAttribute<ReceiptCodaInvio, Integer> numGiorniTentativiKo;
	public static volatile SingularAttribute<ReceiptCodaInvio, String> ultimoEsitoFruitore;
}
