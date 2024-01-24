/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.exception.epaysim;

import javax.xml.ws.WebFault;

@WebFault
public class EpaysimulatorException
extends
it.csi.epay.epaysim.exception.epaysim.UserException {

    /**
     * 
     */
    private static final long serialVersionUID = -8751121532792901520L;

    /**
     * @generated
     */
    public EpaysimulatorException() {
        super();
    }

    /**
     * @param message
     * @generated
     */
    public EpaysimulatorException ( String codice, String message ) {
        super ( codice, message );
    }

    /**
     * @param message
     * @param cause
     * @generated
     */
    public EpaysimulatorException ( String codice, String message, Throwable cause ) {
        super ( codice, message, cause );
    }

}
