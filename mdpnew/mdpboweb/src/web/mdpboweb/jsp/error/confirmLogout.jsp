<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
	<s:include value="../fragments/header.jsp" ></s:include>

	<!-- ####################### PANNELLO CONTENUTI ###################### -->
	<div id="contentPanel">
		<div id="centerWrapper" class="floatWrapper">
			<div id="centerPanel">
				<div class="wrapper">
<h3><s:text name="message.confirmlogout.title" /></h3>
<div id="applicationError">
	<s:text name="message.confirmlogout.warning" />
	<br />
	<s:text name="message.confirmlogout.closemessage" />
	<br />
	<s:text name="message.confirmlogout.certmessage" />
</div>
<div id="applicationError_links" class="commandPanel navigation">
	<div class="button left">
		<s:url id="logoutUrl" action="Logout" method="localLogout" />
		<s:a href="%{logoutUrl}" cssClass="buttonWidget"><s:text name="message.confirmlogout.btnmsglogout" /></s:a>
	</div>
	<div class="button right">
		<s:url id="homePageUrl" action="HomePage" />
		<s:a href="%{homePageUrl}" cssClass="buttonWidget"><s:text name="message.confirmlogout.btnmsghomepage" /></s:a>
	</div>
</div>
				</div>
			</div>
		</div>
	</div>
	<!-- #################### FINE PANNELLO CONTENUTI #################### -->

	<s:include value="../fragments/footer.jsp" ></s:include>
