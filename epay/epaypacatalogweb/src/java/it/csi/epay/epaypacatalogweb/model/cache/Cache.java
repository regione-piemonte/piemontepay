/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.cache;

import java.util.HashMap;
import java.util.Map;


public class Cache {

	private static final Cache instance = new Cache();

	private static final long TIMEOUT = 86400000;

	public static String CODIFICHE_ERRORI = "CODIFICHE_ERRORI ";

	private Map<String, CacheElement> elements = new HashMap<String, CacheElement>();

	private Cache() {
	}

	public static Cache getInstance() {
		return instance;
	}

	public CacheElement get(String key) {
		CacheElement el = elements.get(key);
		if (el == null)
			return null;
		if ((System.currentTimeMillis() - el.getTime()) > TIMEOUT)
			el.setTimeouted(true);
		return el;
	}

	public void set(String key, Object value) {
		elements.put(key, new CacheElement(value));
	}

	public void evictAll() {
		elements.clear();
	}
}
