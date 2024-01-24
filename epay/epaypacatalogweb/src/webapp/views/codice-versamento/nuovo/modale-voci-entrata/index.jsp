<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="modal" tabindex="-1" role="dialog" id="modalRicercaVoceEntrata">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title"><spring:message code="codiceversamento.modifica.cercavoceentratappay" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row" id="row-filtri-ve">
					<div class="col-sm-12">
						<%@ include file="form.jsp"%>
					</div>
				</div>
				<div class="row" id="row-risultati-ve">
					<div class="col-sm-12">
						
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btn-action" data-dismiss="modal"><spring:message code="button.cancel" /></button>
			</div>
		</div>
	</div>
</div>
