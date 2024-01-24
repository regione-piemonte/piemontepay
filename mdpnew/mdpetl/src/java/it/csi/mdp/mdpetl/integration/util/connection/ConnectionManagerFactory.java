/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.connection;
import it.csi.mdp.mdpetl.util.LogUtil;

public class ConnectionManagerFactory {
	LogUtil log = new LogUtil(ConnectionManagerFactory.class);
	static ConnectionManager connManagerInstance = null;
	
	private ConnectionManagerFactory() {
		
	}
	
	public static ConnectionManager getInstance() throws Exception{
		if(connManagerInstance == null){
			connManagerInstance = new ConnectionJdbc();
		}
		return connManagerInstance;
	}
	
	
		
	

}

