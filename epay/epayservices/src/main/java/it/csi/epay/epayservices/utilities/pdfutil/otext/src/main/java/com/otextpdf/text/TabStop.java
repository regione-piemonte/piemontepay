/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.draw.DrawInterface;


public class TabStop {

	protected float position;

	protected Alignment alignment;

	protected DrawInterface leader;

	protected char anchorChar;

	public TabStop ( float position ) {
		this ( position, Alignment.LEFT );
	}

	public TabStop ( float position, Alignment alignment ) {
		this ( position, null, alignment );
	}

	public TabStop ( float position, DrawInterface leader, Alignment alignment ) {
		this ( position, leader, alignment, '.' );
	}

	public TabStop ( float position, DrawInterface leader, Alignment alignment, char anchorChar ) {
		this.position = position;
		this.leader = leader;
		this.alignment = alignment;
		this.anchorChar = anchorChar;
	}

	public TabStop ( TabStop tabStop ) {
		this ( tabStop.getPosition (), tabStop.getLeader (), tabStop.getAlignment (), tabStop.getAnchorChar () );
	}

	public static TabStop newInstance ( float currentPosition, float tabInterval ) {
		currentPosition = (float) Math.round ( currentPosition * 1000 ) / 1000;
		tabInterval = (float) Math.round ( tabInterval * 1000 ) / 1000;

		return new TabStop ( currentPosition + tabInterval - currentPosition % tabInterval );
	}

	public float getPosition () {
		return position;
	}

	public void setPosition ( float position ) {
		this.position = position;
	}

	public Alignment getAlignment () {
		return alignment;
	}

	public void setAlignment ( Alignment alignment ) {
		this.alignment = alignment;
	}

	public DrawInterface getLeader () {
		return leader;
	}

	public char getAnchorChar () {
		return anchorChar;
	}

	public float getPosition ( float tabPosition, float currentPosition, float anchorPosition ) {
		float newPosition = position;
		float textWidth = currentPosition - tabPosition;
		switch ( alignment ) {
		case RIGHT:
			if ( tabPosition + textWidth < position ) {
				newPosition = position - textWidth;
			} else {
				newPosition = tabPosition;
			}
			break;
		case CENTER:
			if ( tabPosition + textWidth / 2f < position ) {
				newPosition = position - textWidth / 2f;
			} else {
				newPosition = tabPosition;
			}
			break;
		case ANCHOR:
			if ( !Float.isNaN ( anchorPosition ) ) {
				if ( anchorPosition < position ) {
					newPosition = position - ( anchorPosition - tabPosition );
				} else {
					newPosition = tabPosition;
				}
			} else {
				if ( tabPosition + textWidth < position ) {
					newPosition = position - textWidth;
				} else {
					newPosition = tabPosition;
				}
			}
			break;
		}
		return newPosition;
	}

	public enum Alignment {
		LEFT,
		RIGHT,
		CENTER,
		ANCHOR
	}
}
