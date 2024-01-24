<!-- Tag del CSI -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.csipiemonte.it/HttpUtils" prefix="h"%>

<%@ tag body-content="scriptless" %>

<%@ attribute name="portal" required="true" type="java.lang.String" %>
<%@ attribute name="resource" required="true" type="java.lang.String" %>

<jsp:doBody var="body" />

<c:out value="${h:include(portal, resource)}" escapeXml="false" />

${body}
