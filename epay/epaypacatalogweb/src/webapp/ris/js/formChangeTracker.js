/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


$.fn.extend({
	trackChanges : function(options) {
		var trackedForm = this;
		if (!options) {
			options = {};
		}
		
		$(trackedForm).submit(function() {
			$(trackedForm).data("submitting", true);
		});
		
		$(trackedForm).data("changed", false);
		
		options.active = true;
		
		$(trackedForm).data("changeTracker", options );
		
		$(":input", trackedForm).change(function(e) {
			$(this.form).data("changed", true);
			if (options.changeCallback) {
				options.changeCallback(this.form, e, options);
			}
		});
	},
	isChanged : function() {
		return this.data("changed");
	},
	isSubmitting : function() {
		return this.data("submitting");
	},
	setDirty : function(val) {
		if (val !== false) {
			val = true;
		}
		$(this).data("changed", true);
	}
});

$( document ).ready(function() {
	var trackedForms = $('form[track-changes],div[track-changes]');
	if (trackedForms.size()) {
		// console.log("activating change tracking on " + trackedForms.size() + " form(s)");
		
		var changeTrackingHandler = {
			trackedForms : trackedForms,
			changes : 0
		};
		
		trackedForms.trackChanges({
			changeCallback : function(form, event, options) {
				// console.log("change event in tracked form");
				options.handler.changes ++ ;
				// console.log("recorded " + options.handler.changes + " changes");
				// console.log("change tracking status report", changeTrackingHandler);
			},
			handler : changeTrackingHandler
		});
		
		window.onbeforeunload = function() {
			if (changeTrackingHandler.trackedForms.toArray().some(function(candidate) {
				return $(candidate).isChanged() && !$(candidate).isSubmitting();
			})) {
				return "Ci sono delle modifiche non salvate. Sei sicuro di voler abbandonare la pagina corrente ?";
			} else {
				// NOP
			}
		}

	}
});