/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager.services;

import it.csi.mdp.boservices.business.manager.bean.PasswordManager;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ServerOutPasswordHandler implements CallbackHandler {

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		
		for (int i=0;i<callbacks.length;i++)
		{
			System.out.println("callback["+i+"] classe:"+callbacks[i].getClass().getName());
			System.out.println(pc.getCustomToken());
			System.out.println(pc.getIdentifier());
			System.out.println(pc.getKeyType());
			System.out.println(pc.getUsage());
			System.out.println(pc.getKey());
			
		}
		
		System.out.println("Client Password for User: " + pc.getIdentifier());
		String userID = pc.getIdentifier();
		// set the password for the outbound message.
		String userPWD = PasswordManager.getPassword4User(userID);
		pc.setPassword(userPWD);
 
	}

}
