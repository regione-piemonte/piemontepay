/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

function listenToButtonsClick() {
	$('body').on('click', 'a.add', function(){
		
		var listElement = $(this).parent().parent().parent();
		
		var outerDiv = listElement.find('div.text-left');
		
		var outerSpan = outerDiv.find('span.pull-right');
		
		var outerAnchor = outerSpan.find('a.btn.btn-xs.btn-default.add');
		
		var innerSpan = outerAnchor.find('span.glyphicon.glyphicon-menu-right');
		
		var outerList = listElement.parent();
		
		var listaAttivate = $("#listaAttivate");
		
		outerDiv.removeClass("text-left");
		outerDiv.addClass("text-right");
		
		outerSpan.removeClass("pull-right");
		outerSpan.addClass("pull-left");
		
		outerAnchor.removeClass("add");
		outerAnchor.addClass("remove");
		
		innerSpan.removeClass("glyphicon-menu-right");
		innerSpan.addClass("glyphicon-menu-left");
		
		listaAttivate.append("<li class='list-group-item'>" + listElement.html() + "</li>");
		
		listElement.remove();
		
		
		
		});
	
	$('body').on('click', 'a.remove', function(){

var listElement = $(this).parent().parent().parent();
		
		var outerDiv = listElement.find('div.text-right');
		
		var outerSpan = outerDiv.find('span.pull-left');
		
		var outerAnchor = outerSpan.find('a.btn.btn-xs.btn-default.remove');
		
		var innerSpan = outerAnchor.find('span.glyphicon.glyphicon-menu-left');
		
		var outerList = listElement.parent();
		
		var listaAttivabili = $("#listaAttivabili");
		
		outerDiv.removeClass("text-right");
		outerDiv.addClass("text-left");
		
		outerSpan.removeClass("pull-left");
		outerSpan.addClass("pull-right");
		
		outerAnchor.removeClass("remove");
		outerAnchor.addClass("add");
		
		innerSpan.removeClass("glyphicon-menu-left");
		innerSpan.addClass("glyphicon-menu-right");
		
		listaAttivabili.append("<li class='list-group-item'>" + listElement.html() + "</li>");
		
		listElement.remove();
		
		
		});
	
}