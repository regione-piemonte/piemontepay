/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;
public class WidgetsStructure implements java.io.Serializable {

	private String widgetName;
	private boolean visible;
	private boolean disabled;

	public String getWidgetName() {
		return widgetName;
	}
	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public WidgetsStructure() {
	}

	public WidgetsStructure(String widgetName, boolean visible, boolean disabled) {
		this.widgetName = widgetName;
		this.visible = visible;
		this.disabled = disabled;
	}
}
