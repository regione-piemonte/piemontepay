/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.impl;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPDateTime;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPError;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.xmp.XMPException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.SimpleTimeZone;


public final class ISO8601Converter {

	private ISO8601Converter () {
	}

	public static XMPDateTime parse ( String iso8601String ) throws XMPException {
		return parse ( iso8601String, new XMPDateTimeImpl () );
	}

	public static XMPDateTime parse ( String iso8601String, XMPDateTime binValue ) throws XMPException {
		if ( iso8601String == null ) {
			throw new XMPException ( "Parameter must not be null", XMPError.BADPARAM );
		} else if ( iso8601String.isEmpty () ) {
			return binValue;
		}

		ParseState input = new ParseState ( iso8601String );
		int value;

		if ( input.ch ( 0 ) == '-' ) {
			input.skip ();
		}

		// Extract the year.
		value = input.gatherInt ( "Invalid year in date string", 9999 );
		if ( input.hasNext () && input.ch () != '-' ) {
			throw new XMPException ( "Invalid date string, after year", XMPError.BADVALUE );
		}

		if ( input.ch ( 0 ) == '-' ) {
			value = -value;
		}
		binValue.setYear ( value );
		if ( !input.hasNext () ) {
			return binValue;
		}
		input.skip ();

		// Extract the month.
		value = input.gatherInt ( "Invalid month in date string", 12 );
		if ( input.hasNext () && input.ch () != '-' ) {
			throw new XMPException ( "Invalid date string, after month", XMPError.BADVALUE );
		}
		binValue.setMonth ( value );
		if ( !input.hasNext () ) {
			return binValue;
		}
		input.skip ();

		// Extract the day.
		value = input.gatherInt ( "Invalid day in date string", 31 );
		if ( input.hasNext () && input.ch () != 'T' ) {
			throw new XMPException ( "Invalid date string, after day", XMPError.BADVALUE );
		}
		binValue.setDay ( value );
		if ( !input.hasNext () ) {
			return binValue;
		}
		input.skip ();

		// Extract the hour.
		value = input.gatherInt ( "Invalid hour in date string", 23 );
		binValue.setHour ( value );
		if ( !input.hasNext () ) {
			return binValue;
		}

		// Extract the minute.
		if ( input.ch () == ':' ) {
			input.skip ();
			value = input.gatherInt ( "Invalid minute in date string", 59 );
			if ( input.hasNext () &&
							input.ch () != ':' && input.ch () != 'Z' && input.ch () != '+' && input.ch () != '-' ) {
				throw new XMPException ( "Invalid date string, after minute", XMPError.BADVALUE );
			}
			binValue.setMinute ( value );
		}

		if ( !input.hasNext () ) {
			return binValue;
		} else if ( input.hasNext () && input.ch () == ':' ) {
			input.skip ();
			value = input.gatherInt ( "Invalid whole seconds in date string", 59 );
			if ( input.hasNext () && input.ch () != '.' && input.ch () != 'Z' &&
							input.ch () != '+' && input.ch () != '-' ) {
				throw new XMPException ( "Invalid date string, after whole seconds",
								XMPError.BADVALUE );
			}
			binValue.setSecond ( value );
			if ( input.ch () == '.' ) {
				input.skip ();
				int digits = input.pos ();
				value = input.gatherInt ( "Invalid fractional seconds in date string", 999999999 );
				if ( input.hasNext () &&
								( input.ch () != 'Z' && input.ch () != '+' && input.ch () != '-' ) ) {
					throw new XMPException ( "Invalid date string, after fractional second",
									XMPError.BADVALUE );
				}
				digits = input.pos () - digits;
				for ( ; digits > 9; --digits ) {
					value = value / 10;
				}
				for ( ; digits < 9; ++digits ) {
					value = value * 10;
				}
				binValue.setNanoSecond ( value );
			}
		} else if ( input.ch () != 'Z' && input.ch () != '+' && input.ch () != '-' ) {
			throw new XMPException ( "Invalid date string, after time", XMPError.BADVALUE );
		}

		int tzSign = 0;
		int tzHour = 0;
		int tzMinute = 0;

		if ( !input.hasNext () ) {
			// no Timezone at all
			return binValue;
		} else if ( input.ch () == 'Z' ) {
			input.skip ();
		} else if ( input.hasNext () ) {
			if ( input.ch () == '+' ) {
				tzSign = 1;
			} else if ( input.ch () == '-' ) {
				tzSign = -1;
			} else {
				throw new XMPException ( "Time zone must begin with 'Z', '+', or '-'",
								XMPError.BADVALUE );
			}

			input.skip ();
			// Extract the time zone hour.
			tzHour = input.gatherInt ( "Invalid time zone hour in date string", 23 );
			if ( input.hasNext () ) {
				if ( input.ch () == ':' ) {
					input.skip ();

					// Extract the time zone minute.
					tzMinute = input.gatherInt ( "Invalid time zone minute in date string", 59 );
				} else {
					throw new XMPException ( "Invalid date string, after time zone hour",
									XMPError.BADVALUE );
				}
			}
		}

		// create a corresponding TZ and set it time zone
		int offset = ( tzHour * 3600 * 1000 + tzMinute * 60 * 1000 ) * tzSign;
		binValue.setTimeZone ( new SimpleTimeZone ( offset, "" ) );

		if ( input.hasNext () ) {
			throw new XMPException (
							"Invalid date string, extra chars at end", XMPError.BADVALUE );
		}

		return binValue;
	}

	public static String render ( XMPDateTime dateTime ) {
		StringBuilder buffer = new StringBuilder ();

		if ( dateTime.hasDate () ) {
			// year is rendered in any case, even 0000
			DecimalFormat df = new DecimalFormat ( "0000", new DecimalFormatSymbols ( Locale.ENGLISH ) );
			buffer.append ( df.format ( dateTime.getYear () ) );
			if ( dateTime.getMonth () == 0 ) {
				return buffer.toString ();
			}

			// month
			df.applyPattern ( "'-'00" );
			buffer.append ( df.format ( dateTime.getMonth () ) );
			if ( dateTime.getDay () == 0 ) {
				return buffer.toString ();
			}

			// day
			buffer.append ( df.format ( dateTime.getDay () ) );

			// time, rendered if any time field is not zero
			if ( dateTime.hasTime () ) {
				// hours and minutes
				buffer.append ( 'T' );
				df.applyPattern ( "00" );
				buffer.append ( df.format ( dateTime.getHour () ) );
				buffer.append ( ':' );
				buffer.append ( df.format ( dateTime.getMinute () ) );

				// seconds and nanoseconds
				if ( dateTime.getSecond () != 0 || dateTime.getNanoSecond () != 0 ) {
					double seconds = dateTime.getSecond () + dateTime.getNanoSecond () / 1e9d;

					df.applyPattern ( ":00.#########" );
					buffer.append ( df.format ( seconds ) );
				}

				// time zone
				if ( dateTime.hasTimeZone () ) {
					// used to calculate the time zone offset incl. Daylight Savings
					long timeInMillis = dateTime.getCalendar ().getTimeInMillis ();
					int offset = dateTime.getTimeZone ().getOffset ( timeInMillis );
					if ( offset == 0 ) {
						// UTC
						buffer.append ( 'Z' );
					} else {
						int thours = offset / 3600000;
						int tminutes = Math.abs ( offset % 3600000 / 60000 );
						df.applyPattern ( "+00;-00" );
						buffer.append ( df.format ( thours ) );
						df.applyPattern ( ":00" );
						buffer.append ( df.format ( tminutes ) );
					}
				}
			}
		}
		return buffer.toString ();
	}

}


class ParseState {

	private final String str;

	private int pos = 0;

	public ParseState ( String str ) {
		this.str = str;
	}

	public int length () {
		return str.length ();
	}

	public boolean hasNext () {
		return pos < str.length ();
	}

	public char ch ( int index ) {
		return index < str.length () ?
						str.charAt ( index ) :
						0x0000;
	}

	public char ch () {
		return pos < str.length () ?
						str.charAt ( pos ) :
						0x0000;
	}

	public void skip () {
		pos++;
	}

	public int pos () {
		return pos;
	}

	public int gatherInt ( String errorMsg, int maxValue ) throws XMPException {
		int value = 0;
		boolean success = false;
		char ch = ch ( pos );
		while ( '0' <= ch && ch <= '9' ) {
			value = ( value * 10 ) + ( ch - '0' );
			success = true;
			pos++;
			ch = ch ( pos );
		}

		if ( success ) {
			if ( value > maxValue ) {
				return maxValue;
			} else
				return Math.max ( value, 0 );
		} else {
			throw new XMPException ( errorMsg, XMPError.BADVALUE );
		}
	}
}	


