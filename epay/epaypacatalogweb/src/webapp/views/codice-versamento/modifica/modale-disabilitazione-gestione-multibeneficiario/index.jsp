<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="modal" tabindex="-1" role="dialog" id="modalDisabilitazioneGestioneMb">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
                    <spring:message code="disabilitazione.gestione.mb.title" />
				</h4>
				<button type="button" id="modalDisabilitazioneGestioneMbClose" class="close"  aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			
			<div class="modal-body">
				<div class="row" >
					<div class="col-sm-12">
                       <spring:message code="disabilitazione.gestione.mb.testo.1" />

					</div>
				</div>
			</div>
			
			<div class="modal-body">
                <div class="row" >
                    <div class="col-sm-12">
                       <spring:message code="disabilitazione.gestione.mb.testo.2" />

                    </div>
                </div>
            </div>
			
			<div class="modal-footer">
				<button type="button" id="modalDisabilitazioneGestioneMbOk" class="btn btn-secondary btn-action" ><spring:message code="button.ok" /></button>
				<button type="button" id="modalDisabilitazioneGestioneMbKo" class="btn btn-secondary btn-action" ><spring:message code="button.delete" /></button>
 
			</div>
			
		</div>
	</div>
</div>

