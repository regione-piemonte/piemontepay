<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../../include/head.jsp"%>

<body>
	<%@ include file="../../include/portal-header.jsp"%>
	<%@ include file="breadcrumb.jsp"%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				
				<form:form 
					id="modifica_riferimento_contabile" 
					class="form-horizontal"
					method="post" 
					commandName="modifica_riferimento_contabile" modelAttribute="modifica_riferimento_contabile"
					action="${context}/riferimenti-contabili/${scenario == 'modifica' ? 'modifica/' :
					 scenario == 'nuovo' && not scenario_alt.equals('duplica') ? 'nuovo':
					 scenario == 'nuovoDaCodiceVersamento'? 'nuovoDaCodiceVersamento/': 
					 'duplica/'}
					 ${scenario == 'nuovo'  && not scenario_alt.equals('duplica') ? '':
					 scenario == 'nuovoDaCodiceVersamento'? codice_versamento_default :       
					 modifica_riferimento_contabile.id }"
					track-changes="true"
				>
					<fieldset>

						<h3>${scenario == 'modifica' ? 'Modifica' : 'Inserisci'} Riferimento Contabile</h3>

						<br />

						<div class="step-content">
							<div class="step-pane active">

								<h4>Parametri</h4>

								<div class="row-fluid">
									<p>
										<span>I campi contrassegnati da "</span><span class="required">*</span><span>" sono obbligatori.</span>
									</p>
								</div>

								<div class="container-fluid">
									<div class="row-fluid" id="formParametriContainer">
										<form:hidden path="id" id="id_duplica"/>
										<%@ include file="form.jsp"%>
									</div>
								</div>

								<div class="row-fluid">
									<p class="margin-medium">
										<a id="buttonClean" class="btn btn-secondary btn-action">Pulisci</a>
									</p>
								</div>
							</div>
						</div>

						<p class="margin-medium">
							<a class="btn btn-secondary btn-action" id="buttonBack">Indietro</a>
							<button type="button" id="buttonSave" class="btn btn-primary btn-action pull-right">Salva</button>
						</p>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="script.jsp"%>
	
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
