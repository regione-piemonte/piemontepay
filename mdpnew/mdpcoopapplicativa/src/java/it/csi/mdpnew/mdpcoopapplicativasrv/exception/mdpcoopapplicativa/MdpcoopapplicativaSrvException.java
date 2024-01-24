/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa;

import it.csi.csi.wrapper.SystemException;
import it.csi.csi.wrapper.UserException;
import it.csi.csi.wrapper.UnrecoverableException;


public class MdpcoopapplicativaSrvException extends RuntimeException
	
{
	public MdpcoopapplicativaSrvException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param String  messaggio
	 * @param Throwable causa
	 */  
	public MdpcoopapplicativaSrvException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MdpcoopapplicativaSrvException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MdpcoopapplicativaSrvException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
} 	
	
