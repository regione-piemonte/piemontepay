<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java"
	import="it.csi.mdp.intnodospc.util.*,org.apache.log4j.Logger,java.text.*,java.util.*,javax.naming.*,it.csi.mdp.core.business.paymentmanager.*,it.csi.mdp.core.business.dto.*,javax.rmi.*,java.net.*"
%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@ taglib uri="/WEB-INF/mdp/mdp.tld" prefix="mdp"%>
<c:set var="portal" value="" scope="page"/>
<c:set var="transazione" value="" scope="page"/> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<script type="text/javascript">
		function submitThisPage() {
			document.getElementById("submitAutomatico").submit();
		}
	</script>
<head>
</head>
<body onload="javascript:submitThisPage();">
	<form action='<c:out value="${requestScope.paginaWisp}"/>' method="post" id="submitAutomatico" accept-charset="UTF-8">
	<c:if test="${!(empty requestScope.parametroWisp.keyPA)}">
		<input type="hidden" name="keyPA" value='<c:out value="${requestScope.parametroWisp.keyPA}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.idDominio)}">
		<input type="hidden" name="idDominio" value='<c:out value="${requestScope.parametroWisp.idDominio}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.enteCreditore)}">
		<input type="hidden" name="enteCreditore" value='<c:out value="${requestScope.parametroWisp.enteCreditore}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.urlReturn)}">
		<input type="hidden" name="urlReturn" value='<c:out value="${requestScope.parametroWisp.urlGestione}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.urlBack)}">
		<input type="hidden" name="urlBack" value='<c:out value="${requestScope.parametroWisp.urlGestione}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.primitiva)}">
		<input type="hidden" name="primitiva" value='<c:out value="${requestScope.parametroWisp.primitiva}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.numPagamentiRPT)}">
		<input type="hidden" name="numPagamentiRPT" value='<c:out value="${requestScope.parametroWisp.numPagamentiRPT}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.stornoPagamento)}">
		<input type="hidden" name="stornoPagamento" value='<c:out value="${requestScope.parametroWisp.stornoPagamento}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.bolloDigitale)}">
		<input type="hidden" name="bolloDigitale" value='<c:out value="${requestScope.parametroWisp.bolloDigitale}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.terzoModelloPagamento)}">
		<input type="hidden" name="terzoModelloPagamento" value='<c:out value="${requestScope.parametroWisp.terzoModelloPagamento}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.idPsp)}">
		<input type="hidden" name="idPsp" value='<c:out value="${requestScope.parametroWisp.idPsp}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.tipoVersamento)}">
		<input type="hidden" name="tipoVersamento" value='<c:out value="${requestScope.parametroWisp.tipoVersamento}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.importoTransazione)}">
		<input type="hidden" name="importoTransazione" value='<c:out value="${requestScope.parametroWisp.importoTransazione}"></c:out>'/>
	</c:if>
	
	<c:if test="${!(empty requestScope.parametroWisp.versioneInterfacciaWisp)}">
		<input type="hidden" name="versioneInterfacciaWISP" value='<c:out value="${requestScope.parametroWisp.versioneInterfacciaWisp}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.contoPoste)}">
		<input type="hidden" name="contoPoste" value='<c:out value="${requestScope.parametroWisp.contoPoste}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.pagamentiModello2)}">
		<input type="hidden" name="pagamentiModello2" value='<c:out value="${requestScope.parametroWisp.pagamentiModello2}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.ibanAccredito)}">
		<input type="hidden" name="ibanAccredito" value='<c:out value="${requestScope.parametroWisp.ibanAccredito}"></c:out>'/>
	</c:if>
	<c:if test="${!(empty requestScope.parametroWisp.codiceLingua)}">
		<input type="hidden" name="codiceLingua" value='<c:out value="${requestScope.parametroWisp.codiceLingua}"></c:out>'/>
	</c:if>
	</form>
	
</body>
</html>
