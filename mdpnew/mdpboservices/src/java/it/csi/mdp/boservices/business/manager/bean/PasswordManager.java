/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager.bean;


import it.csi.mdp.boservices.exception.AuthException;
import it.csi.mdp.boservices.exception.MissingParameterException;
import it.csi.mdp.core.business.exceptions.DaoException;

import java.rmi.RemoteException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


public class PasswordManager {

    public static String getPassword4User(String userID) throws RemoteException {
        //qui puo essere implementata la chiamata alla business
        //unit che recupera la password per l'utente

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanContext.xml"));
		BoServicesBeanImpl cPayment = (BoServicesBeanImpl) bf.getBean("boServicesBeanImpl");
		String pwd = null;
		try
		{
			pwd = cPayment.getAppWsAuth(userID);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			throw new RemoteException(e.getMessage());
		} 
		
    	
        return pwd;
    }
}
