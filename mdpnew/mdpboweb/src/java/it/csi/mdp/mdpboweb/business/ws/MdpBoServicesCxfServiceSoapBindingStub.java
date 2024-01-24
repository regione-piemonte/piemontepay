/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws;

public class MdpBoServicesCxfServiceSoapBindingStub extends org.apache.axis.client.Stub implements it.csi.mdp.mdpboweb.business.ws.BoServices {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[96];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
        _initOperationDesc8();
        _initOperationDesc9();
        _initOperationDesc10();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGatewayDetail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetail"), it.csi.mdp.mdpboweb.business.ws.GetGatewayDetail.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "gatewayDetailList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insertIbanEnteApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertIbanEnteApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertIbanEnteApplication"), it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertIbanEnteApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplicationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertIbanEnteApplicationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setApplicationDetail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationDetail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationDetail"), it.csi.mdp.mdpboweb.business.ws.SetApplicationDetail.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationDetailResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetApplicationDetailResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationDetailResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delRelEnteApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "delRelEnteApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "delRelEnteApplication"), it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "delRelEnteApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "delRelEnteApplicationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setBoConfig");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setBoConfig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setBoConfig"), it.csi.mdp.mdpboweb.business.ws.SetBoConfig.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setBoConfigResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetBoConfigResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setBoConfigResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getApplicationCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFields"), it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFields.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "applicationCustomFieldsList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransazioneViewById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewById"), it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Vtransazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewByIdResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "transazioneViewList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteGatewayConfiguration");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayConfiguration"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayConfiguration"), it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfiguration.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayConfigurationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfigurationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayConfigurationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listApplicationByFlussoApplicazione");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "listApplicationByFlussoApplicazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "listApplicationByFlussoApplicazione"), it.csi.mdp.mdpboweb.business.ws.ListApplicationByFlussoApplicazione.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "listApplicationByFlussoApplicazioneResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Application[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "listApplicationByFlussoApplicazioneResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAuditActionsList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditActionsList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditActionsList"), it.csi.mdp.mdpboweb.business.ws.GetAuditActionsList.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditActionsListResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Auditactions[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditActionsListResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "actionsList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGiornaleEventoByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByParam"), it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadMethodForApplicationGateway");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationGateway"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationGateway"), it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGateway.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationGatewayResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGatewayResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationGatewayResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "IOException"),
                      "it.csi.mdp.mdpboweb.business.ws.IOException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "IOException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setMdpGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroup"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroup"), it.csi.mdp.mdpboweb.business.ws.SetMdpGroup.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroupResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetMdpGroupResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroupResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isAlive");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "isAlive"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "isAlive"), it.csi.mdp.mdpboweb.business.ws.IsAlive.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "isAliveResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.IsAliveResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "isAliveResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setMdpCurrency");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpCurrency"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpCurrency"), it.csi.mdp.mdpboweb.business.ws.SetMdpCurrency.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpCurrencyResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetMdpCurrencyResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpCurrencyResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransazioneViewPaged");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewPaged"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewPaged"), it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPaged.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewPagedResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPagedResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewPagedResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("findEntiAll");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "findEntiAll"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "findEntiAll"), it.csi.mdp.mdpboweb.business.ws.FindEntiAll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "findEntiAllResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "findEntiAllResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteApplicationDetail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationDetail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationDetail"), it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetail.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationDetailResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetailResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationDetailResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getApplicationDetailById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailById"), it.csi.mdp.mdpboweb.business.ws.GetApplicationDetailById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailByIdResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "applicationDetailList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpCurrency");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrency"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrency"), it.csi.mdp.mdpboweb.business.ws.GetMdpCurrency.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.MdpCurrency[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "mdpCurrencyList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getIbanEnteApplicationByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getIbanEnteApplicationByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getIbanEnteApplicationByParam"), it.csi.mdp.mdpboweb.business.ws.GetIbanEnteApplicationByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getIbanEnteApplicationByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getIbanEnteApplicationByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setMdpRole");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpRole"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpRole"), it.csi.mdp.mdpboweb.business.ws.SetMdpRole.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpRoleResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetMdpRoleResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpRoleResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplication"), it.csi.mdp.mdpboweb.business.ws.GetApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Application[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "applicationList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRTByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRTByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRTByParam"), it.csi.mdp.mdpboweb.business.ws.GetRTByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRTByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Rtdto[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRTByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransazioneByApp");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByApp"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByApp"), it.csi.mdp.mdpboweb.business.ws.GetTransazioneByApp.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByAppResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Transazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByAppResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "transazioneList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGatewayById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayById"), it.csi.mdp.mdpboweb.business.ws.GetGatewayById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetGatewayByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransazione");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazione"), it.csi.mdp.mdpboweb.business.ws.GetTransazione.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Transazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "transazioneList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateEnte");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateEnte"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateEnte"), it.csi.mdp.mdpboweb.business.ws.UpdateEnte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateEnteResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.UpdateEnteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateEnteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteMdpBckUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckUser"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckUser"), it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUser.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckUserResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUserResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckUserResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setPaymentMode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setPaymentMode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setPaymentMode"), it.csi.mdp.mdpboweb.business.ws.SetPaymentMode.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setPaymentModeResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetPaymentModeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setPaymentModeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteApplicationCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationCustomFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationCustomFields"), it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFields.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationCustomFieldsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFieldsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationCustomFieldsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("estraiFlussiDaServizio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "estraiFlussiDaServizio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "estraiFlussiDaServizio"), it.csi.mdp.mdpboweb.business.ws.EstraiFlussiDaServizio.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "estraiFlussiDaServizioResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "estraiFlussiDaServizioResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPaymentMode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentMode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentMode"), it.csi.mdp.mdpboweb.business.ws.GetPaymentMode.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Paymentmode[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "paymentmodeList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testResources");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "testResources"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "testResources"), it.csi.mdp.mdpboweb.business.ws.TestResources.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "testResourcesResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.TestResourcesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "testResourcesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "SystemException"),
                      "it.csi.mdp.mdpboweb.business.ws.SystemException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "SystemException"), 
                      true
                     ));
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpCurrencyByKey");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByKey"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByKey"), it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKey.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByKeyResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKeyResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByKeyResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getErrorList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getErrorList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getErrorList"), it.csi.mdp.mdpboweb.business.ws.GetErrorList.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getErrorListResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Verrori[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getErrorListResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "errorList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateIbanEnteApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateIbanEnteApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateIbanEnteApplication"), it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateIbanEnteApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplicationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateIbanEnteApplicationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setMdpUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUser"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUser"), it.csi.mdp.mdpboweb.business.ws.SetMdpUser.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUserResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetMdpUserResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUserResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteEnte");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteEnte"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteEnte"), it.csi.mdp.mdpboweb.business.ws.DeleteEnte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteEnteResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteEnteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteEnteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setLanguage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setLanguage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setLanguage"), it.csi.mdp.mdpboweb.business.ws.SetLanguage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setLanguageResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetLanguageResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setLanguageResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCodiciEsitoPagamentoAll");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getCodiciEsitoPagamentoAll"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getCodiciEsitoPagamentoAll"), it.csi.mdp.mdpboweb.business.ws.GetCodiciEsitoPagamentoAll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getCodiciEsitoPagamentoAllResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getCodiciEsitoPagamentoAllResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insRelEnteApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insRelEnteApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insRelEnteApplication"), it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insRelEnteApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insRelEnteApplicationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getListaTipoversamento");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getListaTipoversamento"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getListaTipoversamento"), it.csi.mdp.mdpboweb.business.ws.GetListaTipoversamento.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getListaTipoversamentoResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getListaTipoversamentoResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStatiTransazione");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiTransazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiTransazione"), it.csi.mdp.mdpboweb.business.ws.GetStatiTransazione.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiTransazioneResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.StatoTransazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiTransazioneResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "statiTransazioneList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setGateway");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGateway"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGateway"), it.csi.mdp.mdpboweb.business.ws.SetGateway.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetGatewayResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getApplicationConfiguration");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationConfiguration"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationConfiguration"), it.csi.mdp.mdpboweb.business.ws.GetApplicationConfiguration.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationConfigurationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationConfigurationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getApplicationDetail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetail"), it.csi.mdp.mdpboweb.business.ws.GetApplicationDetail.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "applicationDetailList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("refundPayment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "refundPayment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "refundPayment"), it.csi.mdp.mdpboweb.business.ws.RefundPayment.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "refundPaymentResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.RefundPaymentResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "refundPaymentResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setGatewayCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayCustomFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayCustomFields"), it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFields.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayCustomFieldsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFieldsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayCustomFieldsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransazioneViewWithFiltersPaged");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewWithFiltersPaged"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewWithFiltersPaged"), it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPaged.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewWithFiltersPagedResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPagedResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewWithFiltersPagedResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteGatewayDetail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayDetail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayDetail"), it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetail.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayDetailResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetailResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayDetailResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadMethodForApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplication"), it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "IOException"),
                      "it.csi.mdp.mdpboweb.business.ws.IOException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "IOException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplication"), it.csi.mdp.mdpboweb.business.ws.DeleteApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpCurrencyByGatewayId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByGatewayId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByGatewayId"), it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByGatewayId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByGatewayIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.MdpCurrency[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByGatewayIdResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "mdpCurrencyList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setApplicationCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationCustomFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationCustomFields"), it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFields.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationCustomFieldsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationCustomFieldsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteApplicationConfiguration");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationConfiguration"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationConfiguration"), it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfiguration.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationConfigurationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfigurationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationConfigurationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLanguages");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguages"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguages"), it.csi.mdp.mdpboweb.business.ws.GetLanguages.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Language[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "mdpLanguageList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGatewayDetailById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailById"), it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIdResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "gatewayDetailList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEntiByApplicationId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByApplicationId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByApplicationId"), it.csi.mdp.mdpboweb.business.ws.GetEntiByApplicationId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByApplicationIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByApplicationIdResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPaymentModeById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeById"), it.csi.mdp.mdpboweb.business.ws.GetPaymentModeById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetPaymentModeByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpBckGroupsByCfisc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByCfisc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByCfisc"), it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByCfisc.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByCfiscResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByCfiscResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "groupList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[60] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("confirmPayment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "confirmPayment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "confirmPayment"), it.csi.mdp.mdpboweb.business.ws.ConfirmPayment.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "confirmPaymentResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.ConfirmPaymentResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "confirmPaymentResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        _operations[61] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransazioneById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneById"), it.csi.mdp.mdpboweb.business.ws.GetTransazioneById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Transazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByIdResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "transazioneList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[62] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteMdpBckGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckGroup"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckGroup"), it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroup.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckGroupResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckGroupResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[63] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTransazioneWithFilters");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneWithFilters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneWithFilters"), it.csi.mdp.mdpboweb.business.ws.GetTransazioneWithFilters.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneWithFiltersResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Vtransazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneWithFiltersResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "transazioneViewList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[64] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStatisticaApplicazioneTransazione");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatisticaApplicazioneTransazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatisticaApplicazioneTransazione"), it.csi.mdp.mdpboweb.business.ws.GetStatisticaApplicazioneTransazione.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatisticaApplicazioneTransazioneResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatisticaApplicazioneTransazioneResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[65] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpUsersById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersById"), it.csi.mdp.mdpboweb.business.ws.GetMdpUsersById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[66] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTipoCommissione");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTipoCommissione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTipoCommissione"), it.csi.mdp.mdpboweb.business.ws.GetTipoCommissione.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTipoCommissioneResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Commission[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTipoCommissioneResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "tipocommissioneList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[67] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGatewayDetailByIds");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIds"), it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIds.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIdsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIdsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIdsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[68] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpUsersByCfisc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByCfisc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByCfisc"), it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfisc.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByCfiscResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfiscResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByCfiscResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[69] = oper;

    }

    private static void _initOperationDesc8(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteGateway");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGateway"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGateway"), it.csi.mdp.mdpboweb.business.ws.DeleteGateway.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[70] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLanguagesByGatewayId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesByGatewayId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesByGatewayId"), it.csi.mdp.mdpboweb.business.ws.GetLanguagesByGatewayId.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesByGatewayIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Language[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesByGatewayIdResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "mdpLanguageList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[71] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteGatewayCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayCustomFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayCustomFields"), it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFields.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayCustomFieldsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFieldsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayCustomFieldsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[72] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpUsers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsers"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsers"), it.csi.mdp.mdpboweb.business.ws.GetMdpUsers.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.MdpBckusers[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "usersList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[73] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGateways");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGateways"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGateways"), it.csi.mdp.mdpboweb.business.ws.GetGateways.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewaysResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Gateway[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewaysResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "gatewayList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[74] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFlussoSingoloPagamentoByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoSingoloPagamentoByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoSingoloPagamentoByParam"), it.csi.mdp.mdpboweb.business.ws.GetFlussoSingoloPagamentoByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoSingoloPagamentoByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoSingoloPagamentoByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[75] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGatewayCustomFields");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayCustomFields"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayCustomFields"), it.csi.mdp.mdpboweb.business.ws.GetGatewayCustomFields.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayCustomFieldsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayCustomFieldsResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "gatewayCustomFieldsList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[76] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getApplicationCustomFieldsByGateway");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsByGateway"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsByGateway"), it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFieldsByGateway.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsByGatewayResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsByGatewayResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "applicationCustomFieldsList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[77] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insertEnte");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertEnte"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertEnte"), it.csi.mdp.mdpboweb.business.ws.InsertEnte.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertEnteResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.InsertEnteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertEnteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[78] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setApplication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplication"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplication"), it.csi.mdp.mdpboweb.business.ws.SetApplication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[79] = oper;

    }

    private static void _initOperationDesc9(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteBoConfig");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteBoConfig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteBoConfig"), it.csi.mdp.mdpboweb.business.ws.DeleteBoConfig.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteBoConfigResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.DeleteBoConfigResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteBoConfigResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[80] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadMethod");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethod"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethod"), it.csi.mdp.mdpboweb.business.ws.UploadMethod.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.UploadMethodResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "IOException"),
                      "it.csi.mdp.mdpboweb.business.ws.IOException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "IOException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[81] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getApplicationById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationById"), it.csi.mdp.mdpboweb.business.ws.GetApplicationById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetApplicationByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[82] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRPTByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRPTByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRPTByParam"), it.csi.mdp.mdpboweb.business.ws.GetRPTByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRPTByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Rptdto[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRPTByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[83] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setTransazione");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setTransazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setTransazione"), it.csi.mdp.mdpboweb.business.ws.SetTransazione.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setTransazioneResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setTransazioneResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[84] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGiornaleEventoById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoById"), it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[85] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpBckGroupsById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsById"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsById"), it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsById.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByIdResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[86] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpBckGroups");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroups"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroups"), it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroups.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "groupList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[87] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStatiRptAll");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiRptAll"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiRptAll"), it.csi.mdp.mdpboweb.business.ws.GetStatiRptAll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiRptAllResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.StatiRptDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiRptAllResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[88] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFlussoRiversamentoByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoRiversamentoByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoRiversamentoByParam"), it.csi.mdp.mdpboweb.business.ws.GetFlussoRiversamentoByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoRiversamentoByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoRiversamentoByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[89] = oper;

    }

    private static void _initOperationDesc10(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInformativePSPByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getInformativePSPByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getInformativePSPByParam"), it.csi.mdp.mdpboweb.business.ws.GetInformativePSPByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getInformativePSPByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getInformativePSPByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[90] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAuditing");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditing"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditing"), it.csi.mdp.mdpboweb.business.ws.GetAuditing.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditingResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.CsiLogAudit[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditingResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "auditingList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[91] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBoConfig");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getBoConfig"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getBoConfig"), it.csi.mdp.mdpboweb.business.ws.GetBoConfig.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getBoConfigResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.Config[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getBoConfigResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "configList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[92] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setGatewayDetail");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayDetail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayDetail"), it.csi.mdp.mdpboweb.business.ws.SetGatewayDetail.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayDetailResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.SetGatewayDetailResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayDetailResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[93] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMdpBckRoles");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckRoles"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckRoles"), it.csi.mdp.mdpboweb.business.ws.GetMdpBckRoles.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckRolesResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.MdpBckroles[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckRolesResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "mdpRolesList"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[94] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEntiByParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByParam"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByParam"), it.csi.mdp.mdpboweb.business.ws.GetEntiByParam.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByParamResponse"));
        oper.setReturnClass(it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByParamResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"),
                      "it.csi.mdp.mdpboweb.business.ws.DaoException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"),
                      "it.csi.mdp.mdpboweb.business.ws.NamingException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"),
                      "it.csi.mdp.mdpboweb.business.ws.MissingParameterException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"),
                      "it.csi.mdp.mdpboweb.business.ws.AuthException",
                      new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException"), 
                      true
                     ));
        _operations[95] = oper;

    }

    public MdpBoServicesCxfServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public MdpBoServicesCxfServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public MdpBoServicesCxfServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
        addBindings2();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "application");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Application.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationConfiguration");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ApplicationConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationcustomfields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationDetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ApplicationDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationGatewayConfiguration");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ApplicationGatewayConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "auditactions");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Auditactions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "AuthException");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.AuthException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "baseDto");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.BaseDto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "codiciEsitoPagamentoDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "commission");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Commission.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "config");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Config.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "confirmPayment");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ConfirmPayment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "confirmPaymentResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ConfirmPaymentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "credentials");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Credentials.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "csiLogAudit");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.CsiLogAudit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "DaoException");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DaoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationConfiguration");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationConfigurationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfigurationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationCustomFields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationCustomFieldsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFieldsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationDetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationDetailResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetailResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteApplicationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteBoConfig");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteBoConfig.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteBoConfigResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteBoConfigResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteEnte");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteEnte.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteEnteResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteEnteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGateway");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGateway.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayConfiguration");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayConfigurationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfigurationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayCustomFields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayCustomFieldsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFieldsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayDetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayDetailResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetailResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteGatewayResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteGatewayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckGroup");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroup.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckGroupResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckUser");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "deleteMdpBckUserResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUserResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "delRelEnteApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "delRelEnteApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "entiDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.EntiDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "estraiFlussiDaServizio");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.EstraiFlussiDaServizio.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "estraiFlussiDaServizioResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "flussoRiversamentoDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "findEntiAll");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.FindEntiAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "findEntiAllResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "entiDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "flussoRiversamentoDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "flussoSingoloPagamentoDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gateway");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Gateway.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gatewaycustomfields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gatewaydetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Gatewaydetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationConfiguration");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationConfiguration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationConfigurationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsByGateway");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFieldsByGateway.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsByGatewayResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationcustomfields");
            qName2 = new javax.xml.namespace.QName("", "applicationCustomFieldsList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationCustomFieldsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationcustomfields");
            qName2 = new javax.xml.namespace.QName("", "applicationCustomFieldsList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetApplicationDetailById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationDetail");
            qName2 = new javax.xml.namespace.QName("", "applicationDetailList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationDetailResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "applicationDetail");
            qName2 = new javax.xml.namespace.QName("", "applicationDetailList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Application[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "application");
            qName2 = new javax.xml.namespace.QName("", "applicationList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditActionsList");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetAuditActionsList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditActionsListResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Auditactions[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "auditactions");
            qName2 = new javax.xml.namespace.QName("", "actionsList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditing");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetAuditing.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getAuditingResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.CsiLogAudit[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "csiLogAudit");
            qName2 = new javax.xml.namespace.QName("", "auditingList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getBoConfig");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetBoConfig.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getBoConfigResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Config[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "config");
            qName2 = new javax.xml.namespace.QName("", "configList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getCodiciEsitoPagamentoAll");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetCodiciEsitoPagamentoAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getCodiciEsitoPagamentoAllResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "codiciEsitoPagamentoDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByApplicationId");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetEntiByApplicationId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByApplicationIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "entiDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetEntiByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getEntiByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "entiDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getErrorList");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetErrorList.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getErrorListResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Verrori[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "verrori");
            qName2 = new javax.xml.namespace.QName("", "errorList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoRiversamentoByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetFlussoRiversamentoByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoRiversamentoByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "flussoRiversamentoDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoSingoloPagamentoByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetFlussoSingoloPagamentoByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getFlussoSingoloPagamentoByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "flussoSingoloPagamentoDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGatewayById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGatewayByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayCustomFields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGatewayCustomFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayCustomFieldsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gatewaycustomfields");
            qName2 = new javax.xml.namespace.QName("", "gatewayCustomFieldsList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGatewayDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gatewaydetail");
            qName2 = new javax.xml.namespace.QName("", "gatewayDetailList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIds");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIds.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailByIdsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIdsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewayDetailResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gatewaydetail");
            qName2 = new javax.xml.namespace.QName("", "gatewayDetailList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGateways");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGateways.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGatewaysResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Gateway[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "gateway");
            qName2 = new javax.xml.namespace.QName("", "gatewayList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getGiornaleEventoByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "giornaleEventoDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getIbanEnteApplicationByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetIbanEnteApplicationByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getIbanEnteApplicationByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "ibanEnteApplicationDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getInformativePSPByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetInformativePSPByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getInformativePSPByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "informativePSPDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguages");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetLanguages.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesByGatewayId");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetLanguagesByGatewayId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesByGatewayIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Language[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "language");
            qName2 = new javax.xml.namespace.QName("", "mdpLanguageList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getLanguagesResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Language[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "language");
            qName2 = new javax.xml.namespace.QName("", "mdpLanguageList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getListaTipoversamento");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetListaTipoversamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getListaTipoversamentoResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "tipoVersamentoDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroups");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroups.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByCfisc");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByCfisc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByCfiscResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroups");
            qName2 = new javax.xml.namespace.QName("", "groupList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckGroupsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroups");
            qName2 = new javax.xml.namespace.QName("", "groupList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckRoles");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpBckRoles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpBckRolesResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckroles[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckroles");
            qName2 = new javax.xml.namespace.QName("", "mdpRolesList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrency");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpCurrency.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByGatewayId");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByGatewayId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByGatewayIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpCurrency[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpCurrency");
            qName2 = new javax.xml.namespace.QName("", "mdpCurrencyList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByKey");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyByKeyResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKeyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpCurrencyResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpCurrency[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpCurrency");
            qName2 = new javax.xml.namespace.QName("", "mdpCurrencyList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsers");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpUsers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByCfisc");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfisc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByCfiscResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfiscResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpUsersById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getMdpUsersResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckusers[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusers");
            qName2 = new javax.xml.namespace.QName("", "usersList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentMode");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetPaymentMode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetPaymentModeById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetPaymentModeByIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getPaymentModeResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Paymentmode[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "paymentmode");
            qName2 = new javax.xml.namespace.QName("", "paymentmodeList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRPTByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetRPTByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRPTByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Rptdto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "rptdto");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRTByParam");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetRTByParam.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getRTByParamResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Rtdto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "rtdto");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiRptAll");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetStatiRptAll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiRptAllResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.StatiRptDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statiRptDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatisticaApplicazioneTransazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetStatisticaApplicazioneTransazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatisticaApplicazioneTransazioneResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statisticaApplicazioneTransazioneDTO");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiTransazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetStatiTransazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getStatiTransazioneResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.StatoTransazione[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statoTransazione");
            qName2 = new javax.xml.namespace.QName("", "statiTransazioneList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTipoCommissione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTipoCommissione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTipoCommissioneResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Commission[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "commission");
            qName2 = new javax.xml.namespace.QName("", "tipocommissioneList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByApp");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneByApp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByAppResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Transazione[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "Transazione");
            qName2 = new javax.xml.namespace.QName("", "transazioneList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Transazione[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "Transazione");
            qName2 = new javax.xml.namespace.QName("", "transazioneList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Transazione[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "Transazione");
            qName2 = new javax.xml.namespace.QName("", "transazioneList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewById");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewById.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewByIdResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Vtransazione[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "vtransazione");
            qName2 = new javax.xml.namespace.QName("", "transazioneViewList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewPaged");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPaged.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewPagedResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPagedResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewWithFiltersPaged");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPaged.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneViewWithFiltersPagedResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPagedResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneWithFilters");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GetTransazioneWithFilters.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "getTransazioneWithFiltersResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Vtransazione[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "vtransazione");
            qName2 = new javax.xml.namespace.QName("", "transazioneViewList");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "giornaleEventoDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "ibanEnteApplicationDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "informativePSPDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertEnte");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InsertEnte.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertEnteResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InsertEnteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertIbanEnteApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insertIbanEnteApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplicationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insRelEnteApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "insRelEnteApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "IOException");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.IOException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "isAlive");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.IsAlive.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "isAliveResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.IsAliveResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "language");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Language.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "listApplicationByFlussoApplicazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.ListApplicationByFlussoApplicazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "listApplicationByFlussoApplicazioneResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Application[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "application");
            qName2 = new javax.xml.namespace.QName("", "return");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroupappmapping");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroupappmapping.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckofficegroups");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckroles");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckroles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckrolesgroupmap");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckrolesgroupmap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusers");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckusers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpBckusersgroup");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpBckusersgroup.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "mdpCurrency");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MdpCurrency.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "MissingParameterException");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.MissingParameterException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "NamingException");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.NamingException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "paymentmode");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Paymentmode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "refundPayment");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.RefundPayment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "refundPaymentResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.RefundPaymentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "rptdto");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Rptdto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "rtdto");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Rtdto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationCustomFields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationCustomFieldsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationDetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetApplicationDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationDetailResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetApplicationDetailResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setBoConfig");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetBoConfig.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setBoConfigResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetBoConfigResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGateway");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetGateway.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayCustomFields");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFields.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayCustomFieldsResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFieldsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings2() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayDetail");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetGatewayDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayDetailResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetGatewayDetailResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setGatewayResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetGatewayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setLanguage");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetLanguage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setLanguageResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetLanguageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpCurrency");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpCurrency.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpCurrencyResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpCurrencyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroup");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpGroup.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpGroupResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpGroupResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpRole");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpRole.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpRoleResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpRoleResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUser");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpUser.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setMdpUserResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetMdpUserResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setPaymentMode");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetPaymentMode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setPaymentModeResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetPaymentModeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setTransazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetTransazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "setTransazioneResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statiRptDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.StatiRptDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statisticaApplicazioneTransazioneDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "statoTransazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.StatoTransazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "SystemException");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.SystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "testResources");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.TestResources.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "testResourcesResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.TestResourcesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "tipoVersamentoDTO");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "Transazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Transazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateEnte");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UpdateEnte.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateEnteResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UpdateEnteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateIbanEnteApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "updateIbanEnteApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplicationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethod");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UploadMethod.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplication");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationGateway");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGateway.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationGatewayResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGatewayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodForApplicationResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "uploadMethodResponse");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.UploadMethodResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "verrori");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Verrori.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "vtransazione");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.Vtransazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://interfacecxf.boservices.mdp.csi.it/", "vtransazioneResult");
            cachedSerQNames.add(qName);
            cls = it.csi.mdp.mdpboweb.business.ws.VtransazioneResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[] getGatewayDetail(it.csi.mdp.mdpboweb.business.ws.GetGatewayDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGatewayDetail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplicationResponse insertIbanEnteApplication(it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "insertIbanEnteApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplicationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplicationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.InsertIbanEnteApplicationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetApplicationDetailResponse setApplicationDetail(it.csi.mdp.mdpboweb.business.ws.SetApplicationDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setApplicationDetail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetApplicationDetailResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetApplicationDetailResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetApplicationDetailResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse delRelEnteApplication(it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "delRelEnteApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DelRelEnteApplicationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetBoConfigResponse setBoConfig(it.csi.mdp.mdpboweb.business.ws.SetBoConfig parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setBoConfig"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetBoConfigResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetBoConfigResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetBoConfigResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] getApplicationCustomFields(it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getApplicationCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Vtransazione[] getTransazioneViewById(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTransazioneViewById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Vtransazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Vtransazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Vtransazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfigurationResponse deleteGatewayConfiguration(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfiguration parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteGatewayConfiguration"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfigurationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfigurationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteGatewayConfigurationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Application[] listApplicationByFlussoApplicazione(it.csi.mdp.mdpboweb.business.ws.ListApplicationByFlussoApplicazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "listApplicationByFlussoApplicazione"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Application[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Application[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Application[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Auditactions[] getAuditActionsList(it.csi.mdp.mdpboweb.business.ws.GetAuditActionsList parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAuditActionsList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Auditactions[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Auditactions[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Auditactions[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO[] getGiornaleEventoByParam(it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGiornaleEventoByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GiornaleEventoDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGatewayResponse uploadMethodForApplicationGateway(it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.IOException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "uploadMethodForApplicationGateway"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGatewayResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGatewayResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationGatewayResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.IOException) {
              throw (it.csi.mdp.mdpboweb.business.ws.IOException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetMdpGroupResponse setMdpGroup(it.csi.mdp.mdpboweb.business.ws.SetMdpGroup parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setMdpGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpGroupResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpGroupResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetMdpGroupResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.IsAliveResponse isAlive(it.csi.mdp.mdpboweb.business.ws.IsAlive parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "isAlive"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.IsAliveResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.IsAliveResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.IsAliveResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetMdpCurrencyResponse setMdpCurrency(it.csi.mdp.mdpboweb.business.ws.SetMdpCurrency parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setMdpCurrency"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpCurrencyResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpCurrencyResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetMdpCurrencyResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPagedResponse getTransazioneViewPaged(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPaged parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTransazioneViewPaged"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPagedResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPagedResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewPagedResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.EntiDTO[] findEntiAll(it.csi.mdp.mdpboweb.business.ws.FindEntiAll parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "findEntiAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.EntiDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.EntiDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetailResponse deleteApplicationDetail(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteApplicationDetail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetailResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetailResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteApplicationDetailResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[] getApplicationDetailById(it.csi.mdp.mdpboweb.business.ws.GetApplicationDetailById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getApplicationDetailById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpCurrency[] getMdpCurrency(it.csi.mdp.mdpboweb.business.ws.GetMdpCurrency parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpCurrency"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.MdpCurrency[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.MdpCurrency[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.MdpCurrency[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO[] getIbanEnteApplicationByParam(it.csi.mdp.mdpboweb.business.ws.GetIbanEnteApplicationByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getIbanEnteApplicationByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.IbanEnteApplicationDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetMdpRoleResponse setMdpRole(it.csi.mdp.mdpboweb.business.ws.SetMdpRole parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setMdpRole"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpRoleResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpRoleResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetMdpRoleResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Application[] getApplication(it.csi.mdp.mdpboweb.business.ws.GetApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Application[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Application[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Application[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Rtdto[] getRTByParam(it.csi.mdp.mdpboweb.business.ws.GetRTByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRTByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Rtdto[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Rtdto[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Rtdto[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Transazione[] getTransazioneByApp(it.csi.mdp.mdpboweb.business.ws.GetTransazioneByApp parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTransazioneByApp"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Transazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Transazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Transazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetGatewayByIdResponse getGatewayById(it.csi.mdp.mdpboweb.business.ws.GetGatewayById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGatewayById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetGatewayByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetGatewayByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetGatewayByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Transazione[] getTransazione(it.csi.mdp.mdpboweb.business.ws.GetTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTransazione"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Transazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Transazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Transazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.UpdateEnteResponse updateEnte(it.csi.mdp.mdpboweb.business.ws.UpdateEnte parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateEnte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.UpdateEnteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.UpdateEnteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.UpdateEnteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUserResponse deleteMdpBckUser(it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUser parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteMdpBckUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUserResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUserResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckUserResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetPaymentModeResponse setPaymentMode(it.csi.mdp.mdpboweb.business.ws.SetPaymentMode parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setPaymentMode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetPaymentModeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetPaymentModeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetPaymentModeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFieldsResponse deleteApplicationCustomFields(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteApplicationCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFieldsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFieldsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteApplicationCustomFieldsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[] estraiFlussiDaServizio(it.csi.mdp.mdpboweb.business.ws.EstraiFlussiDaServizio parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "estraiFlussiDaServizio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Paymentmode[] getPaymentMode(it.csi.mdp.mdpboweb.business.ws.GetPaymentMode parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getPaymentMode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Paymentmode[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Paymentmode[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Paymentmode[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.TestResourcesResponse testResources(it.csi.mdp.mdpboweb.business.ws.TestResources parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.SystemException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "testResources"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.TestResourcesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.TestResourcesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.TestResourcesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.SystemException) {
              throw (it.csi.mdp.mdpboweb.business.ws.SystemException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKeyResponse getMdpCurrencyByKey(it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKey parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpCurrencyByKey"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKeyResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKeyResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByKeyResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Verrori[] getErrorList(it.csi.mdp.mdpboweb.business.ws.GetErrorList parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getErrorList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Verrori[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Verrori[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Verrori[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplicationResponse updateIbanEnteApplication(it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "updateIbanEnteApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplicationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplicationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.UpdateIbanEnteApplicationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetMdpUserResponse setMdpUser(it.csi.mdp.mdpboweb.business.ws.SetMdpUser parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setMdpUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpUserResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetMdpUserResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetMdpUserResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteEnteResponse deleteEnte(it.csi.mdp.mdpboweb.business.ws.DeleteEnte parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteEnte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteEnteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteEnteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteEnteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetLanguageResponse setLanguage(it.csi.mdp.mdpboweb.business.ws.SetLanguage parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setLanguage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetLanguageResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetLanguageResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetLanguageResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO[] getCodiciEsitoPagamentoAll(it.csi.mdp.mdpboweb.business.ws.GetCodiciEsitoPagamentoAll parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getCodiciEsitoPagamentoAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.CodiciEsitoPagamentoDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse insRelEnteApplication(it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "insRelEnteApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.InsRelEnteApplicationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO[] getListaTipoversamento(it.csi.mdp.mdpboweb.business.ws.GetListaTipoversamento parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getListaTipoversamento"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.TipoVersamentoDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.StatoTransazione[] getStatiTransazione(it.csi.mdp.mdpboweb.business.ws.GetStatiTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getStatiTransazione"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.StatoTransazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.StatoTransazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.StatoTransazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetGatewayResponse setGateway(it.csi.mdp.mdpboweb.business.ws.SetGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setGateway"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetGatewayResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetGatewayResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetGatewayResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse getApplicationConfiguration(it.csi.mdp.mdpboweb.business.ws.GetApplicationConfiguration parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getApplicationConfiguration"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetApplicationConfigurationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[] getApplicationDetail(it.csi.mdp.mdpboweb.business.ws.GetApplicationDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getApplicationDetail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.ApplicationDetail[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.RefundPaymentResponse refundPayment(it.csi.mdp.mdpboweb.business.ws.RefundPayment parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "refundPayment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.RefundPaymentResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.RefundPaymentResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.RefundPaymentResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFieldsResponse setGatewayCustomFields(it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setGatewayCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFieldsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFieldsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetGatewayCustomFieldsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPagedResponse getTransazioneViewWithFiltersPaged(it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPaged parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTransazioneViewWithFiltersPaged"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPagedResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPagedResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetTransazioneViewWithFiltersPagedResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetailResponse deleteGatewayDetail(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteGatewayDetail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetailResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetailResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteGatewayDetailResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationResponse uploadMethodForApplication(it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.IOException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "uploadMethodForApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.UploadMethodForApplicationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.IOException) {
              throw (it.csi.mdp.mdpboweb.business.ws.IOException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationResponse deleteApplication(it.csi.mdp.mdpboweb.business.ws.DeleteApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteApplicationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpCurrency[] getMdpCurrencyByGatewayId(it.csi.mdp.mdpboweb.business.ws.GetMdpCurrencyByGatewayId parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpCurrencyByGatewayId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.MdpCurrency[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.MdpCurrency[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.MdpCurrency[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse setApplicationCustomFields(it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setApplicationCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetApplicationCustomFieldsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfigurationResponse deleteApplicationConfiguration(it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfiguration parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteApplicationConfiguration"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfigurationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfigurationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteApplicationConfigurationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Language[] getLanguages(it.csi.mdp.mdpboweb.business.ws.GetLanguages parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getLanguages"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Language[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Language[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Language[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[] getGatewayDetailById(it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGatewayDetailById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Gatewaydetail[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.EntiDTO[] getEntiByApplicationId(it.csi.mdp.mdpboweb.business.ws.GetEntiByApplicationId parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getEntiByApplicationId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.EntiDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.EntiDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetPaymentModeByIdResponse getPaymentModeById(it.csi.mdp.mdpboweb.business.ws.GetPaymentModeById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getPaymentModeById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetPaymentModeByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetPaymentModeByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetPaymentModeByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[] getMdpBckGroupsByCfisc(it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByCfisc parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpBckGroupsByCfisc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.ConfirmPaymentResponse confirmPayment(it.csi.mdp.mdpboweb.business.ws.ConfirmPayment parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[61]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "confirmPayment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.ConfirmPaymentResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.ConfirmPaymentResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.ConfirmPaymentResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Transazione[] getTransazioneById(it.csi.mdp.mdpboweb.business.ws.GetTransazioneById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[62]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTransazioneById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Transazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Transazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Transazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse deleteMdpBckGroup(it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroup parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[63]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteMdpBckGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteMdpBckGroupResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Vtransazione[] getTransazioneWithFilters(it.csi.mdp.mdpboweb.business.ws.GetTransazioneWithFilters parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[64]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTransazioneWithFilters"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Vtransazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Vtransazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Vtransazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO[] getStatisticaApplicazioneTransazione(it.csi.mdp.mdpboweb.business.ws.GetStatisticaApplicazioneTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[65]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getStatisticaApplicazioneTransazione"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.StatisticaApplicazioneTransazioneDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByIdResponse getMdpUsersById(it.csi.mdp.mdpboweb.business.ws.GetMdpUsersById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[66]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpUsersById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Commission[] getTipoCommissione(it.csi.mdp.mdpboweb.business.ws.GetTipoCommissione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[67]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTipoCommissione"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Commission[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Commission[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Commission[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIdsResponse getGatewayDetailByIds(it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIds parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[68]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGatewayDetailByIds"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIdsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIdsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetGatewayDetailByIdsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfiscResponse getMdpUsersByCfisc(it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfisc parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[69]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpUsersByCfisc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfiscResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfiscResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetMdpUsersByCfiscResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayResponse deleteGateway(it.csi.mdp.mdpboweb.business.ws.DeleteGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[70]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteGateway"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteGatewayResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Language[] getLanguagesByGatewayId(it.csi.mdp.mdpboweb.business.ws.GetLanguagesByGatewayId parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[71]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getLanguagesByGatewayId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Language[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Language[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Language[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFieldsResponse deleteGatewayCustomFields(it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[72]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteGatewayCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFieldsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFieldsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteGatewayCustomFieldsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckusers[] getMdpUsers(it.csi.mdp.mdpboweb.business.ws.GetMdpUsers parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[73]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpUsers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckusers[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckusers[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.MdpBckusers[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Gateway[] getGateways(it.csi.mdp.mdpboweb.business.ws.GetGateways parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[74]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGateways"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Gateway[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Gateway[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Gateway[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO[] getFlussoSingoloPagamentoByParam(it.csi.mdp.mdpboweb.business.ws.GetFlussoSingoloPagamentoByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[75]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getFlussoSingoloPagamentoByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.FlussoSingoloPagamentoDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[] getGatewayCustomFields(it.csi.mdp.mdpboweb.business.ws.GetGatewayCustomFields parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[76]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGatewayCustomFields"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Gatewaycustomfields[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[] getApplicationCustomFieldsByGateway(it.csi.mdp.mdpboweb.business.ws.GetApplicationCustomFieldsByGateway parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[77]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getApplicationCustomFieldsByGateway"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Applicationcustomfields[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.InsertEnteResponse insertEnte(it.csi.mdp.mdpboweb.business.ws.InsertEnte parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[78]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "insertEnte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.InsertEnteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.InsertEnteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.InsertEnteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse setApplication(it.csi.mdp.mdpboweb.business.ws.SetApplication parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[79]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setApplication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetApplicationResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.DeleteBoConfigResponse deleteBoConfig(it.csi.mdp.mdpboweb.business.ws.DeleteBoConfig parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[80]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "deleteBoConfig"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteBoConfigResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.DeleteBoConfigResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.DeleteBoConfigResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.UploadMethodResponse uploadMethod(it.csi.mdp.mdpboweb.business.ws.UploadMethod parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.IOException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[81]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "uploadMethod"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.UploadMethodResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.UploadMethodResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.UploadMethodResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.IOException) {
              throw (it.csi.mdp.mdpboweb.business.ws.IOException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetApplicationByIdResponse getApplicationById(it.csi.mdp.mdpboweb.business.ws.GetApplicationById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[82]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getApplicationById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetApplicationByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetApplicationByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetApplicationByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Rptdto[] getRPTByParam(it.csi.mdp.mdpboweb.business.ws.GetRPTByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[83]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRPTByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Rptdto[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Rptdto[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Rptdto[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse setTransazione(it.csi.mdp.mdpboweb.business.ws.SetTransazione parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[84]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setTransazione"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetTransazioneResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse getGiornaleEventoById(it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[85]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGiornaleEventoById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetGiornaleEventoByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByIdResponse getMdpBckGroupsById(it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsById parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[86]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpBckGroupsById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroupsByIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[] getMdpBckGroups(it.csi.mdp.mdpboweb.business.ws.GetMdpBckGroups parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[87]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpBckGroups"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.MdpBckofficegroups[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.StatiRptDTO[] getStatiRptAll(it.csi.mdp.mdpboweb.business.ws.GetStatiRptAll parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[88]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getStatiRptAll"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.StatiRptDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.StatiRptDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.StatiRptDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[] getFlussoRiversamentoByParam(it.csi.mdp.mdpboweb.business.ws.GetFlussoRiversamentoByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[89]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getFlussoRiversamentoByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.FlussoRiversamentoDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO[] getInformativePSPByParam(it.csi.mdp.mdpboweb.business.ws.GetInformativePSPByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[90]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getInformativePSPByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.InformativePSPDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.CsiLogAudit[] getAuditing(it.csi.mdp.mdpboweb.business.ws.GetAuditing parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[91]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAuditing"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.CsiLogAudit[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.CsiLogAudit[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.CsiLogAudit[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.Config[] getBoConfig(it.csi.mdp.mdpboweb.business.ws.GetBoConfig parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[92]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getBoConfig"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.Config[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.Config[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.Config[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.SetGatewayDetailResponse setGatewayDetail(it.csi.mdp.mdpboweb.business.ws.SetGatewayDetail parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[93]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setGatewayDetail"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.SetGatewayDetailResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.SetGatewayDetailResponse) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.SetGatewayDetailResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.MdpBckroles[] getMdpBckRoles(it.csi.mdp.mdpboweb.business.ws.GetMdpBckRoles parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[94]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getMdpBckRoles"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckroles[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.MdpBckroles[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.MdpBckroles[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.mdp.mdpboweb.business.ws.EntiDTO[] getEntiByParam(it.csi.mdp.mdpboweb.business.ws.GetEntiByParam parameters) throws java.rmi.RemoteException, it.csi.mdp.mdpboweb.business.ws.DaoException, it.csi.mdp.mdpboweb.business.ws.NamingException, it.csi.mdp.mdpboweb.business.ws.MissingParameterException, it.csi.mdp.mdpboweb.business.ws.AuthException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[95]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getEntiByParam"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.mdp.mdpboweb.business.ws.EntiDTO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.mdp.mdpboweb.business.ws.EntiDTO[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.mdp.mdpboweb.business.ws.EntiDTO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.DaoException) {
              throw (it.csi.mdp.mdpboweb.business.ws.DaoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.NamingException) {
              throw (it.csi.mdp.mdpboweb.business.ws.NamingException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.MissingParameterException) {
              throw (it.csi.mdp.mdpboweb.business.ws.MissingParameterException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.mdp.mdpboweb.business.ws.AuthException) {
              throw (it.csi.mdp.mdpboweb.business.ws.AuthException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
