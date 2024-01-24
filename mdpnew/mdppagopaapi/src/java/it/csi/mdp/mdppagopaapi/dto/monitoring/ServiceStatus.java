/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.dto.monitoring;

/**
 *
 */

public enum ServiceStatus {

        UP ( 10 ),
        WARNING ( 50 ),
        DOWN ( 200 ),
        UNKNOWN ( 100 );

    private int priority;

    ServiceStatus ( int priority ) {
        this.priority = priority;
    }

    public int getPriority () {
        return priority;
    }

}
