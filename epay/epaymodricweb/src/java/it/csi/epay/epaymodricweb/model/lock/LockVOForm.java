/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.lock;

import java.util.ArrayList;
import java.util.List;


public class LockVOForm implements java.io.Serializable {

    private static final long serialVersionUID = -9014739383350774324L;

    List<LockVO> risultati = new ArrayList<LockVO>();

    public List<LockVO> getRisultati () {
        return risultati;
    }

    public void setRisultati ( List<LockVO> risultati ) {
        this.risultati = risultati;
    }
}
