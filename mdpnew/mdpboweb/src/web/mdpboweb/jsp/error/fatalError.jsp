<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="globalMessages" />

	<jsp:include page="/jsp/fragments/headerNoStruts.jsp" />

	<!-- ####################### PANNELLO CONTENUTI ###################### -->
	<div id="contentPanel">
		<div id="centerWrapper" class="floatWrapper">
			<div id="centerPanel">
				<div class="wrapper">
<!-- TODO: IMPLEMENTARE CORRETTAMENTE -->
<h3><fmt:message key="error.fatalerror.title" /></h3>
<div id="applicationError">
	<fmt:message key="error.fatalerror.warning" />
	<br />
	<fmt:message key="error.fatalerror.message" />
</div>
<div id="applicationError_links" class="commandPanel navigation">
	<div class="button left">
		<a href="HomePage.do" class="buttonWidget"><fmt:message key="error.fatalerror.btnmsg" /></a>
	</div>
</div>
				</div>
			</div>
		</div>
	</div>
	<!-- #################### FINE PANNELLO CONTENUTI #################### -->

	<jsp:include page="/jsp/fragments/footer.jsp" />

