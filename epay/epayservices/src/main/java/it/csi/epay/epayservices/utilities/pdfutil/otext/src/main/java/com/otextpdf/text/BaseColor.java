/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.error_messages.MessageLocalization;


public class BaseColor {

	public static final BaseColor WHITE = new BaseColor ( 255, 255, 255 );

	public static final BaseColor LIGHT_GRAY = new BaseColor ( 192, 192, 192 );

	public static final BaseColor GRAY = new BaseColor ( 128, 128, 128 );

	public static final BaseColor DARK_GRAY = new BaseColor ( 64, 64, 64 );

	public static final BaseColor BLACK = new BaseColor ( 0, 0, 0 );

	public static final BaseColor RED = new BaseColor ( 255, 0, 0 );

	public static final BaseColor PINK = new BaseColor ( 255, 175, 175 );

	public static final BaseColor ORANGE = new BaseColor ( 255, 200, 0 );

	public static final BaseColor YELLOW = new BaseColor ( 255, 255, 0 );

	public static final BaseColor GREEN = new BaseColor ( 0, 255, 0 );

	public static final BaseColor MAGENTA = new BaseColor ( 255, 0, 255 );

	public static final BaseColor CYAN = new BaseColor ( 0, 255, 255 );

	public static final BaseColor BLUE = new BaseColor ( 0, 0, 255 );

	private static final double FACTOR = 0.7;

	private final int value;

	public BaseColor ( final int red, final int green, final int blue, final int alpha ) {
		validate ( red );
		validate ( green );
		validate ( blue );
		validate ( alpha );
		value = ( ( alpha & 0xFF ) << 24 ) | ( ( red & 0xFF ) << 16 ) | ( ( green & 0xFF ) << 8 ) | ( ( blue & 0xFF ) );
	}

	public BaseColor ( final int red, final int green, final int blue ) {
		this ( red, green, blue, 255 );
	}

	public BaseColor ( final float red, final float green, final float blue, final float alpha ) {
		this ( (int) ( red * 255 + .5 ), (int) ( green * 255 + .5 ), (int) ( blue * 255 + .5 ), (int) ( alpha * 255 + .5 ) );
	}

	public BaseColor ( final float red, final float green, final float blue ) {
		this ( red, green, blue, 1f );
	}

	public BaseColor ( final int argb ) {
		value = argb;
	}

	private static void validate ( final int value ) {
		if ( value < 0 || value > 255 )
			throw new IllegalArgumentException ( MessageLocalization.getComposedMessage ( "color.value.outside.range.0.255" ) );
	}

	public int getRGB () {
		return value;
	}

	public int getRed () {
		return ( getRGB () >> 16 ) & 0xFF;
	}

	public int getGreen () {
		return ( getRGB () >> 8 ) & 0xFF;
	}

	public int getBlue () {
		return ( getRGB () ) & 0xFF;
	}

	public int getAlpha () {
		return ( getRGB () >> 24 ) & 0xff;
	}

	public BaseColor darker () {
		return new BaseColor ( Math.max ( (int) ( getRed () * FACTOR ), 0 ),
						Math.max ( (int) ( getGreen () * FACTOR ), 0 ),
						Math.max ( (int) ( getBlue () * FACTOR ), 0 ) );
	}

	@Override
	public boolean equals ( final Object obj ) {
		return obj instanceof BaseColor && ( (BaseColor) obj ).value == this.value;
	}

	@Override
	public int hashCode () {
		return value;
	}

	@Override
	public String toString () {
		return "Color value[" + Integer.toString ( value, 16 ) + "]";
	}
}
