/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao;

import java.util.List;

import it.csi.epay.epaypaweb.exception.PersistenceException;

//@formatter:off
public interface EpaypaDaoBase<K, E> {

	E       findOne(K pk)     throws PersistenceException;
	List<E> findAll()         throws PersistenceException;
	void    persist(E entity) throws PersistenceException;
	void    merge  (E entity) throws PersistenceException;
	void    remove (E entity) throws PersistenceException;

}
//@formatter:on
