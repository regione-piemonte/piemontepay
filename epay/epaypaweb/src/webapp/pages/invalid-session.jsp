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

	<div class="container-fluid">
		<%@ include file="../include/portal-header.html"%>
		<div class="navbarLogin"></div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
				<div class="alert alert-error">
					<h3 class="text-error">Sessione scaduta</h3>
					<h4 class="text-error">Effettuare nuovamente il <s:a action="login">login</s:a></h4>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/javascript.html"%>

</body>
</html>
