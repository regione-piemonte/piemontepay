/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBoServicesCxfServiceLocator extends org.apache.axis.client.Service implements it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfService {

    public MdpBoServicesCxfServiceLocator() {
    }


    public MdpBoServicesCxfServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MdpBoServicesCxfServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BoServicesPort
    private java.lang.String BoServicesPort_address = "https://tst-secure.ruparpiemonte.it/mdpboservices/MdpBoServicesCxf/MdpBoServicesCxf";

    public java.lang.String getBoServicesPortAddress() {
        return BoServicesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BoServicesPortWSDDServiceName = "BoServicesPort";

    public java.lang.String getBoServicesPortWSDDServiceName() {
        return BoServicesPortWSDDServiceName;
    }

    public void setBoServicesPortWSDDServiceName(java.lang.String name) {
        BoServicesPortWSDDServiceName = name;
    }

    public it.csi.mdp.mdpboweb.business.ws.BoServices getBoServicesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BoServicesPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBoServicesPort(endpoint);
    }

    public it.csi.mdp.mdpboweb.business.ws.BoServices getBoServicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfServiceSoapBindingStub _stub = new it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getBoServicesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBoServicesPortEndpointAddress(java.lang.String address) {
        BoServicesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.mdp.mdpboweb.business.ws.BoServices.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfServiceSoapBindingStub _stub = new it.csi.mdp.mdpboweb.business.ws.MdpBoServicesCxfServiceSoapBindingStub(new java.net.URL(BoServicesPort_address), this);
                _stub.setPortName(getBoServicesPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BoServicesPort".equals(inputPortName)) {
            return getBoServicesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MdpBoServicesCxfService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "BoServicesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BoServicesPort".equals(portName)) {
            setBoServicesPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
