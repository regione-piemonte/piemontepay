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

	<p class="nascosto">
		<a title="A-sommario"></a>
	</p>
	<ul id="sommario" class="nascosto">
		<li><s:a href="#A-contenuti">Salta ai contenuti</s:a></li>
	</ul>
	<hr/>

	<div class="container-fluid-banner">
		<%@ include file="../include/portal-header.html"%>
		<%@ include file="navbar-login.jsp"%>
		<a title="A-contenuti"></a>
	</div>

	<p>&nbsp;</p>
	<s:form method="POST" action="ruolo-profilo-selected" theme="bootstrap">
		<s:div class="container-fluid">
			<s:div cssClass="row-fluid">
				<s:div cssClass="contentPage">
					<%@ include file="../include/validation-message.jsp"%>
					<s:if test="ruoli.size() > 1">
						<h4>Sono disponibili pi&ugrave; ruoli, selezionare il ruolo con cui operare:</h4>
						<s:radio id="radioRuoli" list="ruoli" listKey="id" listValue="des" name="idRuolo" />
					</s:if>
					<s:else>
						<s:hidden name="idRuolo"/>
					</s:else>
					<s:if test="profili.size() > 1">
						<h4>Sono disponibili pi&ugrave; profili, selezionare il profilo con cui operare:</h4>
						<s:radio id="radioProfili" list="profili" listKey="id" listValue="des" name="idProfilo" />
					</s:if>
					<s:else>
						<s:hidden name="idProfilo"/>
					</s:else>
					<s:div cssClass="form-actions">
						<s:submit value="Avanti" cssClass="btn btn-primary"/>
					</s:div>
				</s:div>
			</s:div>
		</s:div>
		<br />
		<%@ include file="../include/portal-footer.html"%>
		<%@ include file="../include/javascript.html"%>
	</s:form>
</body>
</html>
