/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.926+0200")
@StaticMetamodel(TransazioneExtraAttribute.class)
public class TransazioneExtraAttribute_ {
	public static volatile SingularAttribute<TransazioneExtraAttribute, TransazioneExtraAttributePK> id;
	public static volatile SingularAttribute<TransazioneExtraAttribute, String> value;
	public static volatile SingularAttribute<TransazioneExtraAttribute, Transazione> transazione;
}
