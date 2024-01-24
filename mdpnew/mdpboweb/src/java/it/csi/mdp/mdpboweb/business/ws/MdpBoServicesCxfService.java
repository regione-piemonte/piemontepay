/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public interface MdpBoServicesCxfService extends javax.xml.rpc.Service {
    public java.lang.String getBoServicesPortAddress();

    public it.csi.mdp.mdpboweb.business.ws.BoServices getBoServicesPort() throws javax.xml.rpc.ServiceException;

    public it.csi.mdp.mdpboweb.business.ws.BoServices getBoServicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
