/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.exception;

import javax.xml.ws.WebFault;

@WebFault //(name="MyFault" ,targetNamespace="http://interfacecxf.core.mdp.csi.it/",faultBean="MyFaultBean")
public class AuthException extends Exception
{

	//private static final long serialVersionUID = -6018110965593945731L;
	
	//protected Throwable throwable;
	//private CheckFaultBean faultInfo;
//	public MissingParameterException()
//	{
//		super();
//	}
	/**
	 * Method 'MissingParameterException'
	 * 
	 * @param message
	 */
	public AuthException(String message) {
        super(message);
       
    }
    public AuthException(String message, Throwable cause) {
        super(message,cause);
    	//this.throwable = cause;

    }
    
//	public MissingParameterException(String message, CheckFaultBean faultInfo) {
//        super(message);
//        this.faultInfo = faultInfo;
//    }

	/**
	 * Method 'MissingParameterException'
	 * 
	 * @param message
	 * @param throwable
	 */
    
//	public MissingParameterException(String message, CheckFaultBean faultInfo, Throwable throwable)
//	{
//		super(message, throwable);
//		this.faultInfo = faultInfo;
//		//this.throwable = throwable;
//	}

	/**
	 * Method 'getCause'
	 * 
	 * @return Throwable
	 */
	public Throwable getCause()
	{
		return super.getCause();
	}
//	
//    public String getFaultInfo() {
//        return "faultInfo:"+this.getMessage();
//    }
    

}
