/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv;

import it.csi.csi.wrapper.SystemException;
import it.csi.csi.wrapper.UserException;
import it.csi.csi.wrapper.UnrecoverableException;


public class MdpIuvSrvException extends RuntimeException
	
{
	public MdpIuvSrvException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param String  messaggio
	 * @param Throwable causa
	 */  
	public MdpIuvSrvException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MdpIuvSrvException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MdpIuvSrvException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
} 	
	
