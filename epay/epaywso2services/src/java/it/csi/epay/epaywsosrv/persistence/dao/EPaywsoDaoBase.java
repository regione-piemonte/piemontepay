/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao;

import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;

public interface EPaywsoDaoBase<K, E> {

	E findOne(K pk) throws PersistenceException;

	List<E> findAll() throws PersistenceException;

	void persist(E entity) throws PersistenceException;

	void merge(E entity) throws PersistenceException;

	void remove(E entity) throws PersistenceException;

}
