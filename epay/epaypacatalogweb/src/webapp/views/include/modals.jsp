<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<div id="commonModalTemplate_empty">
<div class="modal fade" tabindex="-1" role="dialog" id="commonModal_empty">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" aria-label="Close" id="commonModalButtonCancelTop">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"><spring:message code="include.modals.info" /></h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</div>
</div>

<div class="modal fade" id="modal-info" tabindex="-1" role="dialog" aria-labelledby="modal-info-label">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modal-info-label"></h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-action" data-dismiss="modal" id="modal-info-button-ok"><spring:message code="include.modals.ok" /></button>
			</div>
		</div>
	</div>
</div>

<div id="containerSpawnedModals">
</div>

<div id="commonModalTemplate_info">
<div class="modal fade" tabindex="-1" role="dialog" id="commonModal_info">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" aria-label="Close" id="commonModalButtonCancelTop">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"><spring:message code="include.modals.informazione" /></h4>
			</div>
			<div class="modal-body">
				<!-- text here -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-action" data-dismiss="modal" id="commonModalButtonOK">
					<spring:message code="button.ok" />
				</button>
			</div>
		</div>
	</div>
</div>
</div>

<div id="commonModalTemplate_confirmDeletion">
<div class="modal fade" tabindex="-1" role="dialog" id="commonModal_confirmDeletion">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" aria-label="Close" id="commonModalButtonCancelTop">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"><spring:message code="include.modals.confermaeliminazione" /></h4>
			</div>
			<div class="modal-body">
				<spring:message code="include.modals.effettuareoperazionerichiesta" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default btn-action" id="commonModalButtonCancel">
					<spring:message code="button.cancel" />
				</button>
				<button type="button" class="btn btn-danger btn-action" id="commonModalButtonOK">
					<span class="glyphicon glyphicon-trash"></span> 
					<spring:message code="include.modals.confermaeliminazione" />
				</button>
			</div>
		</div>
	</div>
</div>
</div>

<div id="commonModalTemplate_confirmWarning">
<div class="modal fade" tabindex="-1" role="dialog" id="commonModal_confirmWarning">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" aria-label="Close" id="commonModalButtonCancelTop">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"><spring:message code="include.modals.confermaazione" /></h4>
			</div>
			<div class="modal-body">
				<spring:message code="include.modals.effettuareoperazionerichiesta" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default btn-action" id="commonModalButtonCancel">
					<spring:message code="button.cancel" />
				</button>
				<button type="button" class="btn btn-warning btn-action" id="commonModalButtonOK">
					<span class="glyphicon glyphicon-alert"></span> 
					<spring:message code="button.proceed" />
				</button>
			</div>
		</div>
	</div>
</div>
</div>

<div id="commonModalTemplate_confirmProceed">
<div class="modal fade" tabindex="-1" role="dialog" id="commonModal_confirmProceed">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" aria-label="Close" id="commonModalButtonCancelTop">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"><spring:message code="include.modals.confermaazione" /></h4>
			</div>
			<div class="modal-body">
				<spring:message code="include.modals.effettuareoperazionerichiesta" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default btn-action" id="commonModalButtonCancel">
					<spring:message code="button.cancel" />
				</button>
				<button type="button" class="btn btn-primary btn-action" id="commonModalButtonOK">
					<span class="glyphicon glyphicon-share-alt"></span> 
					<spring:message code="button.proceed" />
				</button>
			</div>
		</div>
	</div>
</div>
</div>
	<!-- pop-up modale per confermare un'azione -->
<div class="modal fade" id="confirm-action-modal" tabindex="-1" role="dialog" aria-labelledby="confirm-action-label">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Chiudi"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="confirm-action-label"><spring:message code="include.modals.confermaazione" /></h4>
			</div>
			<div class="modal-body" id="confirm-action-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default btn-action" data-dismiss="modal"><spring:message code="button.cancel" /></button>
				<button type="button" id="confirm-action-button" class="btn btn-primary btn-action"><spring:message code="button.ok" /></button>
			</div>
		</div>
	</div>
</div>

	<!-- pop-up modale per attendere risposta dal server -->
<div class="modal fade" id="modal-pleasewait" tabindex="-1" role="dialog" aria-labelledby="header-title-label" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="header-title-label"><spring:message code="include.modals.attendereelaborazioneincorso" /></h4>
			</div>
			<div class="modal-body" id="pleasewait-body">
				<div class='progress progress-striped active'>
					<div class='bar' style='width: 100%;'></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

function mostraInfo(message, callback) {
	showModal("info", message);
}

function mostraInfoEdEseguiLogica(message, logicaDaEseguire) {
    showModal("info", message, logicaDaEseguire);
}

function mostraErrore(message) {
	console.error(message);
	showModal("info", "<p class='alert alert-danger'>" + message + "<p>");
}

function chiediConfermaProcedi(messaggio, procedi, annulla, skipAndProceed) {
	if (skipAndProceed) return procedi();
	
	return showModal("confirmProceed", {
			text : messaggio,
			allowSoftDismiss : false
		},
		procedi, annulla
	);
}

function chiediConfermaEliminazione(messaggio, procedi, annulla) {
	showModal("confirmDeletion", {
			text : messaggio,
			allowSoftDismiss : false
		},
		procedi, annulla
	);
}

function chiediConfermaWarning(messaggio, procedi, annulla) {
	showModal("confirmWarning", {
			text : messaggio,
			allowSoftDismiss : false
		},
		procedi, annulla
	);
}

function showModal(type, options, callbackProceed, callbackCancel, callbackFinal) {
	if (typeof type != "string") {
		callbackFinal = callbackCancel;
		callbackCancel = callbackProceed;
		callbackProceed = options;
		options = type; 
		type = "empty";
	}

	if (!showModal.uid) showModal.uid = 0;
	
	if (typeof options == "string") options = {text : options};

	options.uid = (++ showModal.uid); 
	options.allowSoftDismiss = (options.allowSoftDismiss === false ? false : true);
	
	var template = $("#commonModalTemplate_" + type).html();
	template = template.replace("id=\"commonModal_" + type, "id=\"spawnedModal" + options.uid);

	$("#containerSpawnedModals").append(template);
	
	var separator = "@@@@";
	
	var spawnedModal = $("#spawnedModal" + options.uid);
	if (options.title) spawnedModal.find(".modal-title").html(options.title);
	if (options.text) {
		var txtFinal=options.text;
		if (txtFinal.search(separator) > 0) {
			var txt1=txtFinal.substring(0, txtFinal.search(separator));
			var txt2='<p class="modal-alert">'+txtFinal.substring(txtFinal.search(separator)+separator.length,txtFinal.length)+'</p>';
			txtFinal=txt2+txt1;
		}	
		spawnedModal.find(".modal-body").html(txtFinal);
	}

	var buttonContainer = spawnedModal.find(".modal-footer");
	var autoIdCounter = 0;
	
	if (options.buttons) {
		$.each(options.buttons, function(i, button) {
			if (!button.id) button.id = "autoGenButton" + (++autoIdCounter);
			if (!button.class) button.class = "btn-primary"; 
			
			var b = $('<button type="button" class="btn btn-action" id="generatedModalButton' + button.id 
				+ '" ' + (button.disabled ? 'disabled="disabled"' : '') + '></button>');
			var buttonText = "";
			
			if (button.glyphicon) buttonText += '<span class="glyphicon glyphicon-' + button.glyphicon + '"></span> ';
			if (button.text) buttonText += button.text; 
			b.html(buttonText);
			
			if (button.class) b.addClass(button.class);
			
			buttonContainer.append(b);
			if (!button.disabled) {
				b.on("click", function(event) {
					if (button.checkBefore && !button.checkBefore(event, spawnedModal, button)) {
						return;
					}
			
					spawnedModal.activeDismiss = true;
					spawnedModal.modal("hide");
					setTimeout(function() { spawnedModal.remove(); }, 5000);
					
					if (button.callback) button.callback();
					if (callbackFinal) callbackFinal(event, spawnedModal, 'topCancel');
				});
			}
		});
	}

	spawnedModal.find("#commonModalButtonCancelTop").on("click", function(event) {
		if (options.checkBeforeDismiss && !options.checkBeforeDismiss(event, spawnedModal, 'topCancel')) {
			return;
		}

		spawnedModal.activeDismiss = true;
		spawnedModal.modal("hide");
		setTimeout(function() { spawnedModal.remove(); }, 5000);
		
		if (callbackCancel) callbackCancel(event, spawnedModal, 'topCancel');
		if (callbackFinal) callbackFinal(event, spawnedModal, 'topCancel');
	});

	spawnedModal.find("#commonModalButtonCancel").on("click", function(event) {
		if (options.checkBeforeDismiss && !options.checkBeforeDismiss(event, spawnedModal, 'buttonCancel')) {
			return;
		}

		spawnedModal.activeDismiss = true;
		spawnedModal.modal("hide");
		setTimeout(function() { spawnedModal.remove(); }, 5000);
		
		if (callbackCancel) callbackCancel(event, spawnedModal, 'buttonCancel');
		if (callbackFinal) callbackFinal(event, spawnedModal, 'buttonCancel');
	});

	spawnedModal.find("#commonModalButtonOK").on("click", function(event) {
		if (options.checkBeforeOK && !options.checkBeforeOK(event, spawnedModal, 'buttonOK')) {
			return;
		}

		spawnedModal.activeDismiss = true;
		spawnedModal.modal("hide");
		setTimeout(function() { spawnedModal.remove(); }, 5000);
		
		if (callbackProceed) callbackProceed(event, spawnedModal, 'buttonOK');
		if (callbackFinal) callbackFinal(event, spawnedModal, 'buttonOK');
	});

	spawnedModal.on('hidden.bs.modal', function () {
	    if (!spawnedModal.activeDismiss) {
			if (callbackCancel) callbackCancel(null, spawnedModal, 'softDismiss');
			if (callbackFinal) callbackFinal(event, spawnedModal, 'softDismiss');
		}
	})

	var modalOptions = {
		show : true,
	};

	if (!options.allowSoftDismiss) {
		modalOptions.backdrop = 'static';
		modalOptions.keyboard = false;
	}
	
	spawnedModal.modal(modalOptions);
}

</script>
