/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;

import java.util.MissingResourceException;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>Provides access to MDPBO messages in a properties file.</p>
 *
 * @author 70056	
 */
@SuppressWarnings("serial")
public class PropertiesUtil extends ActionSupport
{
  /**
   * <p>Retrieves a localized message based on a String key.</p>
   *
   * @param pKey
   * @return String
   * @throws MissingResourceException
   */
  public String getMessage(String pKey)
  {
      String message = getText(pKey);
      if (message == null || message.equals("")) {
    	  message = pKey+" "+getText("errore.messaggiononreperito");
      } else {
          if (!message.substring(0,2).equals("@@")) {
        	  message = pKey+" "+message;
          } else {
        	  message = message.substring(2);
          }
      }
      return message;
  }
}


