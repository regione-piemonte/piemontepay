/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaypacatalogsrv.model.ModalitaAcquisizioneProvvisori;

public interface ModalitaAcquisizioneProvvisoriRepository extends JpaRepository<ModalitaAcquisizioneProvvisori, Long> {
	ModalitaAcquisizioneProvvisori findOneByCodice(String codice);
}
