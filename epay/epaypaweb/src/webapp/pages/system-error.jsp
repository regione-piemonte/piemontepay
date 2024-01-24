<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ include file="../include/head.jsp"%>

<body>
	<hr/>

	<div class="container-fluid-banner">
		<%@ include file="../include/portal-header.html"%>
		<%@ include file="navbar-login.jsp"%>
	</div>

	<p>&nbsp;</p>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
				<div class="alert alert-error">
					<h3 class="text-center">Si &egrave; verificato un errore bloccante, contattare l'assistenza</h3>
					<h4 class="text-center">Dettagli:</h4>
					<s:if test="hasActionErrors()">
						<s:actionerror cssClass="text-error"/>
				   	</s:if>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/javascript.html"%>

</body>
</html>
