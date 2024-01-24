/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	var els = $('li[data-menuanchor]');
	var colors = ['#ccc', '#eee'];
	
	var anchors = els.map(function(idx, el) {
		return $(el).data('menuanchor');
	}).get();
	var sectionColors = els.map(function(idx) {
		return colors[idx % colors.length];
	}).get();
	

});

$(function() {
	$("a.offcanvas-toggle").click(function(e) {
		var opened = $("#offcanvas-sidebar").hasClass("on");
		console.log(opened);
		if (opened) {

			$("#offcanvas-sidebar").animate({
				"right" : '-400px'
			}, 500, function() {
				$(this).removeClass("on");
				$(this).addClass("collapse");
				$(this).hide();
			});

			$(this).find("i.fa").removeClass("fa-times").addClass("fa-bars");
			console.log("Closed!");
		} else {
			$("#offcanvas-sidebar").removeClass("collapse");
			$("#offcanvas-sidebar").show().animate({
				"right" : '0px'
			}, 500, function() {
				$(this).addClass("on");
			});
			$(this).find("i.fa").removeClass("fa-bars").addClass("fa-times");
			console.log("Opened!");
		}
	});

});