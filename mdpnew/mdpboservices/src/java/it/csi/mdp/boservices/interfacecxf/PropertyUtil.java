/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.interfacecxf;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;


/**
 * Classe di utilit&agrave; per il recupero dei parametri dal file di properties
 * 
 * @author 71735
 */
public class PropertyUtil {
   
    /**
     * Metodo per il recupero dei parametri dal file di properties
     * 
     * @param field
     * @return
     * @author 71725
     */
    public static String getPropsValue(String field) {
    	
    	//create an instance of properties class
    	Properties props = new Properties();
    	
    	String value = null;

    	//try retrieve data from file
    	try {
    		ClassPathResource cp = new ClassPathResource("/env.properties");
    		props.load(cp.getInputStream());

    		// assign value to message variable only if it is not null
    		if ( props.getProperty(field) != null ) {
    			value = props.getProperty(field);
    		}
    		System.out.println("-----> [SERVER][PropertyUtil::getPropsValue] got value: " + field + " = " + value);
    	}
    	catch(IOException e)
    	{
    		//catch exception in case properties file does not exist
    		System.out.println("-----> [SERVER][PropertyUtil::getPropsValue] CANNOT FIND PROPS FILE! PLEASE VERIFY WHERE IT IS LOCATED");
    		System.out.println(e);
    	}
    	
    	return value;
    }

}
