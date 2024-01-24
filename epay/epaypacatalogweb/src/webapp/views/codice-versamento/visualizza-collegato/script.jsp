<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

$( document ).ready(function() {

	function getScenario() {
		return '${scenario}';
	}

	function isNuovo() {
		return getScenario() === 'nuovo';
	}

	function isModifica() {
		return getScenario() === 'modifica';
	}
	
	$("#buttonBack").on('click',function(){
		<c:if test="${pagina_origine_codice_versamento eq 'modifica'}">
		location = "${context}/codici-versamento/modifica/${id_codice_versamento}";
		</c:if>
		<c:if test="${pagina_origine_codice_versamento eq 'visualizza'}">
		location = "${context}/codici-versamento/visualizza/${id_codice_versamento}";
		</c:if>
		<c:if test="${pagina_origine_codice_versamento ne 'visualizza' and pagina_origine_codice_versamento ne 'modifica'}">
		location = "${context}/codici-versamento/ricerca";
		</c:if>
	});
	
});

</script>
