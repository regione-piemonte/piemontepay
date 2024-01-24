/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import java.util.List;


public class TabSettings {

	static public final float DEFAULT_TAB_INTERVAL = 36;

	private final List<TabStop> tabStops;

	private final float tabInterval;

	public TabSettings ( List<TabStop> tabStops, float tabInterval ) {
		this.tabStops = tabStops;
		this.tabInterval = tabInterval;
	}

	public static TabStop getTabStopNewInstance ( float currentPosition, TabSettings tabSettings ) {
		if ( tabSettings != null )
			return tabSettings.getTabStopNewInstance ( currentPosition );
		return TabStop.newInstance ( currentPosition, DEFAULT_TAB_INTERVAL );
	}

	public TabStop getTabStopNewInstance ( float currentPosition ) {
		TabStop tabStop = null;
		if ( tabStops != null ) {
			for ( TabStop currentTabStop : tabStops ) {
				if ( currentTabStop.getPosition () - currentPosition > 0.001 ) {
					tabStop = new TabStop ( currentTabStop );
					break;
				}
			}
		}

		if ( tabStop == null ) {
			tabStop = TabStop.newInstance ( currentPosition, tabInterval );
		}

		return tabStop;
	}
}
