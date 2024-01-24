/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoPagamentoEnum {
	TIPO_PAGAMENTO_TUTTI(1),
	TIPO_PAGAMENTO_SPONTANEI(2),
	TIPO_PAGAMENTO_DOVUTI(3);
	
	private Integer id;
	
	private TipoPagamentoEnum (Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	static public TipoPagamentoEnum fromId(Integer id) {
		for (TipoPagamentoEnum en : TipoPagamentoEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}
}
