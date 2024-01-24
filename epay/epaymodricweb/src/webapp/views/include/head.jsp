<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="taglibs.jsp"%>
<c:url value="/." var="context" />

<!DOCTYPE html>
<html lang="it">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
    <title><spring:message code="portal.name"></spring:message></title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

	<script type="text/javascript" src="${webappConfig.staticResourcesPath}/lib/jquery-2.2.4.min.js"></script>
	
	<script type="text/javascript" src="${webappConfig.staticResourcesPath}/lib/bootstrap-3.3.6/dist/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${webappConfig.staticResourcesPath}/lib/bootstrap-3.3.6/dist/css/bootstrap.css">
	
	<script type="text/javascript" src="${webappConfig.staticResourcesPath}/js/datatables/datatables.min.js"></script>
   	<script type="text/javascript" src="${webappConfig.staticResourcesPath}/js/datepicker/bootstrap-datepicker.min.js"></script>
   	
	<link href="${webappConfig.staticResourcesPath}/css/bootstrap-portal.css" rel="stylesheet"/>
	<link href="${webappConfig.staticResourcesPath}/css/skin-portal.css" rel="stylesheet"/>
    <link href="${webappConfig.staticResourcesPath}/css/datepicker.css" rel="stylesheet"/>
    <link href="${webappConfig.staticResourcesPath}/css/flag-icon.min.css" rel="stylesheet"/>
        
    <link href="${webappConfig.staticResourcesPath}/css/portalHeader.css" rel="stylesheet"/>
	<link href="${webappConfig.staticResourcesPath}/css/login.css" rel="stylesheet" type="text/css"  /> 
	 
    <link rel="stylesheet" href="${webappConfig.staticResourcesPath}/css/datatables/datatables.min.css"/>
    <link rel="stylesheet" href="${webappConfig.staticResourcesPath}/css/bootstrap-select.min.css"  />

    <link rel="stylesheet" href="${webappConfig.staticResourcesPath}/css/main.css"  />
    
    <link rel="stylesheet" href="${webappConfig.staticResourcesPath}/lib/fontawesome/css/all.css">    
</head>
   
