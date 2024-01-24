/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.utilities;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommonCliUtil {

	CommandLine line;
	
	public CommonCliUtil(String[] args) {
		Option helpOpt = new Option( "h", "help", false, "stampa questo messaggio" );
		
		@SuppressWarnings("static-access")
		Option serverListOpt  = OptionBuilder.withArgName( "name:port[,name:port]" )
                .hasArg()
                .isRequired()
                .withDescription( "lista dei server ejb" )
                .withLongOpt("serverList")
                .create( "s"); 
		
		@SuppressWarnings("static-access")
		Option usernameOpt  = OptionBuilder.withArgName( "name" )
                .hasArg()
                .isRequired()
                .withDescription( "nome dell'utente che accede ai servizi ejb" )
                .withLongOpt("username")
                .create( "u"); 
		
		@SuppressWarnings("static-access")
		Option passwordOpt  = OptionBuilder.withArgName( "password" )
                .hasArg()
                .isRequired()
                .withDescription( "password dell'utente che accede ai servizi ejb" )
                .withLongOpt("password")
                .create( "p");  
		
		Options options = new Options();
		options.addOption( helpOpt );
		options.addOption( serverListOpt );
		options.addOption( usernameOpt );
		options.addOption( passwordOpt );
		
		try {
	        line = (new BasicParser()).parse( options, args );
	    }
	    catch( ParseException exp ) {
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	        printHelp(options);
	        System.exit(-1);
	    }
		
		if (line.hasOption( "help" ) ) {
			printHelp(options);
			System.exit(0);
		}
	}
	
	static private void printHelp(final Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "InviaPagamenti", options );
	}
	
	public String getServers() {
		return line.getOptionValue( "serverList" );
	}
	
	public String getUsername() {
		return line.getOptionValue( "username" );
	}
	
	public String getPassword() {
		return line.getOptionValue( "password" );
	}
}
