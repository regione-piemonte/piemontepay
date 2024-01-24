/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommonCliUtil {

	CommandLine line;
		
	public enum Report {
		NODO_SPC("MainReportNodoSpc"),
		TRANSAZIONI("MainReportTransazioni");
		private final String name;
		Report(String name) {
			this.name= name;
		}
		public String getName() {
			return name;
		}
	}
	
	public CommonCliUtil(final Report name, String[] args) {
		Option helpOpt = new Option( "h", "help", false, "stampa questo messaggio" );
		
		Option flagCumulativoOpt  = new Option( "c", "cumulativo", false, "flag per impostare il report cumulativo da inizio anno" );
		
		@SuppressWarnings("static-access")
		Option dataRifOpt  = OptionBuilder.withDescription( "data di riferimento per il report (mm-aaaa) (opzionale)" )
				.withArgName( "dataRif" )
				.hasArgs(2)
                .withValueSeparator('-')
                .withLongOpt("data")
                .create( "d"); 
		
		@SuppressWarnings("static-access")
		Option applicationIdOpt  = OptionBuilder.withDescription( "application id da elaborare (opzionale)" )
				.withArgName( "id" )
				.hasArg()
                .withLongOpt("applicationId")
                .create( "a"); 
		 
		Options options = new Options();
		switch (name) {
		case NODO_SPC:
			options.addOption( helpOpt );
			options.addOption( dataRifOpt );
			options.addOption( flagCumulativoOpt );
			options.addOption( applicationIdOpt );
			break;
		case TRANSAZIONI:
			options.addOption( helpOpt );
			options.addOption( dataRifOpt );
			options.addOption( flagCumulativoOpt );
			break;
		default:
			break;
		}
		
		try {
	        line = (new BasicParser()).parse( options, args );
	    }
	    catch( ParseException exp ) {
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	        printHelp(name.getName(), options);
	        System.exit(-1);
	    }
		
		if (line.hasOption( "help" ) ) {
			printHelp(name.getName(), options);
			System.exit(0);
		}
	}
	
	static private void printHelp(final String name, final Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( name, options );
	}
	
	public Boolean hasDataRif() {
		return line.hasOption( "data" );
	}
	
	public String getDataRifMese() {
		return line.getOptionValues( "data" )[0];
	}
	
	public String getDataRifAnno() {
		return line.getOptionValues( "data" )[1];
	}
	
	public Boolean hasFlagCumulativo() {
		return line.hasOption( "cumulativo" );
	}
	
	public Boolean hasApplicationId() {
		return line.hasOption( "id" );
	}
	
	public String getApplicationId() {
		return line.getOptionValue( "id" );
	}
}
