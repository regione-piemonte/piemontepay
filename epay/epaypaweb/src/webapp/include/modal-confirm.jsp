<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<!-- pop-up modale per confermare un'azione -->
<div class="modal fade" id="confirm-action-modal" tabindex="-1" role="dialog" aria-labelledby="confirm-action-label">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Chiudi"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="confirm-action-label">Confermare l'azione</h4>
			</div>
			<div class="modal-body" id="confirm-action-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
				<button type="button" id="confirm-action-button" class="btn btn-primary">OK</button>
			</div>
		</div>
	</div>
</div>
