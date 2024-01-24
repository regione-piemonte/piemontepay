/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.util.Map;

import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.ParamNameXPdf;

public class ErData {
    private	Map<ParamNameXPdf, Object> param;
    private EsitiRicevuti er;
    private	Pagamento pagamento;

    private byte [] quietanza;

    public byte [] getQuietanza () {
        return quietanza;
    }

    public void setQuietanza ( byte [] quietanza ) {
        this.quietanza = quietanza;
    }
    public Map<ParamNameXPdf, Object> getParam() {
        return param;
    }
    public void setParam(Map<ParamNameXPdf, Object> param) {
        this.param = param;
    }

    public EsitiRicevuti getEr () {
        return er;
    }

    public void setEr ( EsitiRicevuti er ) {
        this.er = er;
    }
    public Pagamento getPagamento() {
        return pagamento;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }


}
