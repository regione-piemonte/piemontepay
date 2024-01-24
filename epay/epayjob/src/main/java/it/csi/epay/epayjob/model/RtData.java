/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.util.Map;

import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.Rt;

public class RtData {
	private	Map<ParamNameXPdf, Object> param;
	private	Rt rt;
	private	Pagamento pagamento;
	
	public Map<ParamNameXPdf, Object> getParam() {
		return param;
	}
	public void setParam(Map<ParamNameXPdf, Object> param) {
		this.param = param;
	}
	public Rt getRt() {
		return rt;
	}
	public void setRt(Rt rt) {
		this.rt = rt;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	
}
