/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager;

public interface EntityManager<T> {

	public T getById(Long id);

	public T save(T vo);
	
	public boolean validate(T vo);
}
