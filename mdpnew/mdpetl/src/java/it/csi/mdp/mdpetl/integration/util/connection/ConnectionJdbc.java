/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.connection;

import it.csi.mdp.mdpetl.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionJdbc extends ConnectionManager{
	LogUtil log = new LogUtil(ConnectionJdbc.class);
	String url = null;
	String driver = null;
	String user = null;
	String pass = null;
	long incrementalRetryMills;
	int maxRetryAttemps;
	long retryAttemps = 0;
	
	protected ConnectionJdbc() throws ClassNotFoundException, IOException ,Exception{
		final String methodName = "ConnectionJdbc";
		InputStream is = ClassLoader.getSystemResourceAsStream("db.properties");
		Properties props = new Properties();
		props.load(is);
		
		driver = props.getProperty("driver");
		url    = props.getProperty("url");
		user   = props.getProperty("user");
		pass   = props.getProperty("pass");
		
//		driver= "org.postgresql.Driver";
//		url="jdbc:postgresql://tst-domdb35.csi.it:5432/PGTST01";
//		user="mdpnew";
//		pass="mypass";
//		incrementalRetryMills=Long.parseLong("1000");
//		maxRetryAttemps = Integer.parseInt("10");
	
//		TEST
//		driver= "org.postgresql.Driver";
//        url="jdbc:postgresql://tst-domdb67.csi.it:5432/PGTST03";
//        user="mdpnew";
//        pass="Kai7ugh3";
//        incrementalRetryMills=Long.parseLong("1000");
//        maxRetryAttemps = Integer.parseInt("10");
		
//		DEV
//      driver= "org.postgresql.Driver";
//      url="jdbc:postgresql://tst-domdb69.csi.it:5432/PGDEV01";
//      user="mdpnew";
//      pass="Sai7Da8a";
//      incrementalRetryMills=Long.parseLong("1000");
//      maxRetryAttemps = Integer.parseInt("10");
		
		
		
		try{
			incrementalRetryMills = Long.parseLong(props.getProperty("incrementalRetryMills"));
		}catch(NumberFormatException nfe){
			incrementalRetryMills = 1000;
		}
		try {
			maxRetryAttemps = Integer.parseInt(props.getProperty("maxRetryAttemps"));
		}catch(NumberFormatException nfe){
			maxRetryAttemps = 10;
		}
		
		
		
		log.debug(methodName, "driver: "+driver);
		log.debug(methodName, "url: "+url);
		log.debug(methodName, "user: "+user);
		log.debug(methodName, "pass: "+pass);
		
		
		log.info(methodName, "incrementalRetryMills: "+incrementalRetryMills);
		log.info(methodName, "maxRetryAttemps: "+maxRetryAttemps);
		
		Class.forName(driver);
	}
	

	/*@Override
	public Connection getConnection() throws SQLException {	
		Connection conn = DriverManager.getConnection(url,user,pass);
		log.info("getConnection", "Connection to return: "+conn);
		return conn;
	}*/
	
	@Override
	public synchronized Connection getConnection() throws SQLException {	
		Connection conn;
		try {
			conn = DriverManager.getConnection(url,user,pass);
			//log.info("getConnection", "Connection to return: "+conn);
			retryAttemps = 0;
			return conn;
		} catch (SQLException e){
			if(e.getMessage().indexOf("The Network Adapter could not establish the connection")!=-1 &&
					retryAttemps < maxRetryAttemps){	
				log.info("getConnection", "The Network Adapter could not establish the connection. Retry attemp: "+(retryAttemps+1));
				incrementalThreadSleep();
				return getConnection();
			}
			throw e;
		}
	}


	private void incrementalThreadSleep() {
		try {
			//System.out.println("sleepping for "+(retryAttemps * incrementalRetryMills));
			Thread.sleep(retryAttemps * incrementalRetryMills);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		retryAttemps++;
	}

	
	public static void main(String[] args) {
		
		try {
			ConnectionJdbc conn = new ConnectionJdbc();
			for(int i = 0; i<1; i++){
				Connection c = conn.getConnection();
				c.close();
				
			}
			

			/*conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			conn.incrementalThreadSleep();
			*/
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
}
