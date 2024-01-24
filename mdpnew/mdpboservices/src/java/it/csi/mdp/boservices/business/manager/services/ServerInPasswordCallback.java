/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager.services;

import it.csi.mdp.boservices.business.manager.bean.PasswordManager;
import it.csi.mdp.boservices.util.MdpCypher;
import it.csi.mdp.boservices.util.Constants;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;
import org.apache.ws.security.*;

public class ServerInPasswordCallback implements CallbackHandler {
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		log.debug("Client Password for User: " + pc.getIdentifier());
		
		// ottengo l'id utente che ha richiesto l'azione
		String userID = pc.getIdentifier();
		String pwd = pc.getPassword();
		
		
		// recupero la password per l'IdUtente passato
		String userPWD = PasswordManager.getPassword4User(userID);
		log.debug("Retrieving password ..... "+userPWD + " pwd passata:"+pwd);
		
		log.debug("Retrieving password ..... "+userPWD + " pwd attuale:"+userPWD);
		try
		{
			String sha1 = MdpCypher.getSHA164(userPWD);
			log.debug("****** sha1 pwd *********:"+sha1);
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( !(userPWD.equalsIgnoreCase("")) && (userPWD.length()>0) ) {

			pc.setPassword(userPWD);
			log.debug("****** DIGEST WITH TIMESTAMP and NONCE *********"+pc.getPassword());
			log.debug("****** DIGEST WITH TIMESTAMP and NONCE *********"+pc.getPasswordType());
			log.debug("****** DIGEST WITH TIMESTAMP and NONCE *********"+pc.getUsage());
		}
	}

}
