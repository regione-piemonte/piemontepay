<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="AlertStampaAvvisoModal" tabindex="-1" role="dialog" aria-labelledby="AlertStampaAvvisoModal" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div id="content" class="modal-content">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>
        
        <div class="modal-header">

    <h3 class="modal-title text-center">ATTENZIONE!!</h3>
</div>
<div class="modal-body">
   
    <p>
       Il pagamento sara' valido e erogabile solo fino al 31.12 dell'anno in corso
    </p>
    <br>
    <br>
    <br>
    <br>
   
    
    
    <p class="text-center mb-1">
            <button type="button" class="btn btn-default" style="padding: 10px 20px !important;" data-dismiss="modal">
            Chiudi</button>
            
            
              <button type="button" class="btn btn-default" style="padding: 10px 20px !important;" data-dismiss="modal"  onclick="window.open('${urlStampa}', '_blank')" >Prosegui</a>
<!--             <a class="btn btn-primary text-center" target="_blank"  onclick="$(document.getElementById('AlertStampaAvvisoModal')).modal().hide(); $('.modal-backdrop').css('display', 'none')" -->
<%--             href="${urlStampa}">Prosegui</a> --%>
<!--             href="/epayweb/accessoLibero/stampaAvviso">Prosegui</a> -->
            
    </p>
</div>
         
         
         
         
         
      </div>
   </div>
</div>

