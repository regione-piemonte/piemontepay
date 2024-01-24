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
	<s:form method="POST" action="ente-selected" theme="bootstrap">
		<s:div class="container-fluid">
			<s:div cssClass="row-fluid">
				<s:div cssClass="contentPage">
					<%@ include file="../include/validation-message.jsp"%>
					<h4>Sono disponibili più enti, selezionare l'ente con cui operare:</h4>
					<s:radio id="radioEnti" list="enti" listKey="id" listValue="denominazione" name="idEnte"/>
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
