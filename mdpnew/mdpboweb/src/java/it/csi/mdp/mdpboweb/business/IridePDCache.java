/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;

import java.util.HashMap;
import java.util.Hashtable;

import it.csi.csi.pfh.FHResult;
import it.csi.csi.pfh.FunctHandler;
import it.csi.csi.util.Param;
import it.csi.csi.wrapper.CSIException;
import it.csi.iride2.policy.entity.*;
import it.csi.iride2.iridefed.entity.Ruolo;

public class IridePDCache implements FunctHandler {

	static HashMap<Iride2CallKey, Iride2CallValue> cache = new HashMap<Iride2CallKey, Iride2CallValue>();

	static final long TTL = 60 * 60 * 1000; // TTL = 1h

	public FHResult doOnInvokeSynch(String methodName, Param[] params,
			FHResult prevres, Hashtable bag, boolean inPreChain)
			throws CSIException {
		if (mustCache(methodName)) {

			Iride2CallKey call = new Iride2CallKey(methodName, params);
			if (inPreChain) {
				Iride2CallValue cachedResult = cache.get(call);
				if (cachedResult != null) {
					// System.out.println("PDCache: found result for " + call);
					if ((System.currentTimeMillis() - cachedResult.birthTime) < TTL) {
						prevres.setResult(cachedResult.result);
						prevres.setSkipInvoke(true);
					} else
						cache.remove(call);
				}
				// else continue and call PA...
			}

			else { // post chain
				if (prevres.getException() == null) {
					// metti in cache il risultato
					// System.out.println("PDCache: caching result for " +
					// call);
					Iride2CallValue newVal = new Iride2CallValue(
							System.currentTimeMillis(), prevres.getResult());
					cache.put(call, newVal);
				}
			}
		}
		return prevres;
	}

	private boolean mustCache(String methodName) {
		if ("isPersonaInRuolo".equals(methodName)
				|| "isPersonaAutorizzataInUseCase".equals(methodName)
				|| "findActorsForPersonaInApplication".equals(methodName))
			return true;
		else
			return false;

	}

	class Iride2CallValue {
		public long birthTime;
		public Param result;

		public Iride2CallValue(long bt, Param p) {
			birthTime = bt;
			result = p;
		}
	}

	class Iride2CallKey {
		public String paramsKey;

		public Iride2CallKey(String methodName, Param[] params) {
			paramsKey += methodName + ":";
			Identita id = (Identita) (params[0].getValue());
			paramsKey += id.getCodFiscale();
			if (params[1].getType() == UseCase.class) {
				paramsKey += ",UC:"
						+ ((UseCase) (params[1].getValue())).getId();
			} else if (params[1].getType() == Actor.class) {
				paramsKey += ",ACT:" + ((Actor) (params[1].getValue())).getId();
			} else if (params[1].getType() == Ruolo.class) {
				paramsKey += ",ROL:"
						+ ((Ruolo) (params[1].getValue())).getCodiceDominio()
						+ "-"
						+ ((Ruolo) (params[1].getValue())).getCodiceRuolo();
			}
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Iride2CallKey) {
				Iride2CallKey other = (Iride2CallKey) obj;
				return paramsKey.equals(other.paramsKey);
			} else
				return false;
		}

		@Override
		public int hashCode() {

			return paramsKey.hashCode();
		}

		@Override
		public String toString() {
			return paramsKey;
		}

	}
}
