<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<!-- pop-up modale per attendere risposta dal server -->
<div class="modal fade" id="modal-pleasewait" tabindex="-1" role="dialog" aria-labelledby="header-title-label" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="header-title-label">Attendere prego, elaborazione in corso...</h4>
			</div>
			<div class="modal-body" id="pleasewait-body">
				<div class='progress progress-striped active'>
					<div class='bar' style='width: 100%;'></div>
				</div>
			</div>
		</div>
	</div>
</div>
