<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only"><spring:message code="include.navbar.togglenavigation"/></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><spring:message code="include.navbar.projectname"/></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><spring:message code="navigation.dashboard"></spring:message></a></li>
				<li><a href="#"><spring:message code="navigation.settings"></spring:message></a></li>
				<li><a href="#"><spring:message code="navigation.profile"></spring:message></a></li>
				<li><a href="#"><spring:message code="navigation.help"></spring:message></a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder=<spring:message code="navigation.search"></spring:message>>
			</form>
		</div>
	</div>
</nav>
