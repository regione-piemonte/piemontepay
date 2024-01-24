<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal fade" id="ModalCoronavirus" tabindex="-1" role="dialog" aria-labelledby="ModalCoronavirus" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div id="content" class="modal-content">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        	<span aria-hidden="true">&times;</span>
        </button>	
        <button id="btnita" class="btn btn-link"><span class="flag-icon flag-icon-it"> </span></button>
        <button id="btnen" class="btn btn-link"><span class="flag-icon flag-icon-gb"> </span></button>
        
        
        
         <div id="content-lang-it">
      		<%@ include file="covid-19HelpModal_it.jsp"%>
         </div>
         <div id="content-lang-en" style="display:none;">
      		<%@ include file="covid-19HelpModal_en.jsp"%>
         </div>
      </div>
   </div>
</div>


<script type="text/javascript">
var el = document.getElementById("btnen");
if (el) {
	el.addEventListener("click", function () {
		document.getElementById("content-lang-it").style.display = "none";
		document.getElementById("content-lang-en").style.display = "block";
	});
}

var il = document.getElementById("btnita");
if (il) {
	il.addEventListener("click", function () {
		document.getElementById("content-lang-en").style.display = "none";
		document.getElementById("content-lang-it").style.display = "block";
	});
}
</script>
