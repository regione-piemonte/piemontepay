/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.921+0200")
@StaticMetamodel(TemplateConfigurazioneEpay.class)
public class TemplateConfigurazioneEpay_ {
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, Integer> id;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, Boolean> campoCifrato;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, String> chiave;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, String> descrizione;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, Boolean> flagAttivo;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, String> gatewayId;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, String> progetto;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, String> tabellaRiferimento;
	public static volatile SingularAttribute<TemplateConfigurazioneEpay, String> valore;
}
