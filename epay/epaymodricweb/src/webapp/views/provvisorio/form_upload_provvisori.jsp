<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<div class="accordion-heading">
			<h4>
				<spring:message
					code="provvisorio.formuploadprovvisori.uploadprovvisori" />
				<span class="pull-right clickable"> <a
					href="#collapseFilterPanel" data-toggle="collapse"
					aria-expanded="true" data-parent="#filterPanel"> <i
						class="glyphicon glyphicon-chevron-up"></i> <i
						class="glyphicon glyphicon-chevron-down"></i>
				</a>
				</span>
			</h4>
		</div>
		<div id="collapseFilterPanel" class="collapse in">
			<form:hidden path="estensione" id="estensione" name="estensione" />
			<div class="container-fluid">
				<div class="row-fluid">

					<div class="step-content">
						<div class="step-pane active" id="templateDownloadPanel">
							<div class="accordion-heading">
								<h4>
									Template <span class="pull-right clickable"> <s:a
											data-toggle="collapse" href="#collapseTemplateDownloadPanel"
											data-parent="#templateDownloadPanel" aria-expanded="true">
											<i class="glyphicon glyphicon-chevron-up"></i>
											<i class="glyphicon glyphicon-chevron-down"></i>
										</s:a>
									</span>
								</h4>
							</div>
							<div id="collapseTemplateDownloadPanel" class="collapse in">
								<div class="row-fluid">
									<p>Scarica il template per preparare il provvisorio.</p>
									<p class="margin-medium">
										<c:if test="${provvisori_file.estensione == 'csv'}">
											<a id="buttonCSV" class="btn btn-secondary"
												href="../resources/Template_provvisori.csv" download>
												Scarica template CSV </a>
										</c:if>
										<c:if test="${provvisori_file.estensione == 'xlsx'}">
											<a id="buttonCSV" class="btn btn-secondary"
												href="../resources/Template_provvisori.xlsx"> Scarica
												template Excel </a>
										</c:if>
									</p>
								</div>
							</div>
						</div>
					</div>

					<br />
					<div class="step-content">
						<div class="step-pane active" id="filterPanel">

							<div class="form-group ">
								<label class="col-sm-3 control-label required"
									for="logoEnteContent"><spring:message
										code="provvisorio.formuploadprovvisori.selezionailfiledacaricare" /></label>
								<div class="col-sm-9 controls upload">
									<div id="logoDetailsContainer" class="well">
										<div class="row">
											<div class="col-sm-12">
												<i class="glyphicon glyphicon-paperclip"></i> <span
													id="fileDetailsContainerName"> </span> <br /> <span
													id="fileDetailsContainerSize"> </span>
											</div>
										</div>
									</div>
									<div class="file btn btn-lg btn-action btn-primary"
										id="divFileContent">
										<spring:message
											code="provvisorio.formuploadprovvisori.selezionafile" />
									</div>
									<input type="file" name="fileContent" id="fileContent"
										class="upload" accept=".${provvisori_file.estensione}">
								</div>
								<div class="col-sm-3"></div>
								<div class="col-sm-9">
									<form:errors path="fileContent" cssClass="error" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
