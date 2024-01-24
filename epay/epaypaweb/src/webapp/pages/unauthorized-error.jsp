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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 contentPage">
				<div class="alert alert-error">
					<h3 class="text-center">ATTENZIONE!</h3>
					<h4 class="text-center">Si &egrave; cercato di attivare una funzionalità senza avere la necessaria autorizzazione.</h4>
					<h4 class="text-error">Effettuare nuovamente il <s:a action="login">login</s:a></h4>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/javascript.html"%>

</body>
</html>