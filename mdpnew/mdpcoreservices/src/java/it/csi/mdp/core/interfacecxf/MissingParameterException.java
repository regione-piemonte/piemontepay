/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.interfacecxf;



import javax.xml.ws.WebFault;

@WebFault //(name="MyFault" ,targetNamespace="http://interfacecxf.core.mdp.csi.it/",faultBean="MyFaultBean")
public class MissingParameterException extends Exception
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1871020648276382480L;
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
	public MissingParameterException(String message) {
        super(message);
       
    }
    public MissingParameterException(String message, Throwable cause) {
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
//	public Throwable getCause()
//	{
//		return super.getCause();
//	}
//	
//    public String getFaultInfo() {
//        return "faultInfo:"+this.getMessage();
//    }
    

}
