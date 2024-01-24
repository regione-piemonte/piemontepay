<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:jee="http://java.sun.com/xml/ns/javaee">
	<xsl:template match="/jee:application/jee:module[jee:ejb/text() = '@@JARTESTCALLBACK_NAME@@.jar']">
		<!-- TODO: Auto-generated template -->
	</xsl:template>
	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>