<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<script>

$(function(){
	$("#divFileContent").on('click',function(e){
		$("#fileContent").click();
	});
});

$("#fileContent").on('change',function(){
	var fileList = $("#fileContent")[0].files;
	if (fileList.length > 0) {
		var f = fileList[0];
		var size = f.size;
		var validSize = (size <= 2048000);
		var fnamelc = f.name.toLowerCase();
		var validExt = fnamelc.endsWith(".csv") || fnamelc.endsWith(".xlsx")
				|| fnamelc.endsWith(".xlsx");
		
		if (!validSize || !validExt) {
			$("#fileContent").val("");
			
			if (!fnamelc) {
				alert("Il file selezionato e' troppo grosso.");
			} else if (!validExt) {
				alert("Il file selezionato non e' valido. Devi selezionare un file di tipo CSV o Excel (.xlsx)");
			}
			
		} else {
			$("#fileDetailsContainerName").html(f.name);
			$("#fileDetailsContainerSize").html(Math.round(size / 1024) + " kB");
			
			$("#fileDetailsContainer").show();
			

			var reader  = new FileReader();
			
			if (f) {
			    reader.readAsDataURL(f);
			} else {
			    preview.src = "";
			}
		}
		
	} else {
		clearLogo();
	}
});

</script>
