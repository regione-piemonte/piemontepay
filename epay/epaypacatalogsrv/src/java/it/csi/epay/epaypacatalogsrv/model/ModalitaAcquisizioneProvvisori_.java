/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.631+0200")
@StaticMetamodel(ModalitaAcquisizioneProvvisori.class)
public class ModalitaAcquisizioneProvvisori_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<ModalitaAcquisizioneProvvisori, Integer> id;
	public static volatile SingularAttribute<ModalitaAcquisizioneProvvisori, String> codice;
	public static volatile SingularAttribute<ModalitaAcquisizioneProvvisori, String> descrizione;
}
