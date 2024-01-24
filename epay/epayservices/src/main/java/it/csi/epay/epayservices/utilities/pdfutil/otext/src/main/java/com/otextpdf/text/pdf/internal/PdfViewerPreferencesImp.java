/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.internal;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfArray;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfBoolean;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfDictionary;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfName;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfNumber;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfObject;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.PdfWriter;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.interfaces.PdfViewerPreferences;


public class PdfViewerPreferencesImp implements PdfViewerPreferences {

	public static final PdfName[] VIEWER_PREFERENCES = {
					PdfName.HIDETOOLBAR,            // 0
					PdfName.HIDEMENUBAR,            // 1
					PdfName.HIDEWINDOWUI,           // 2
					PdfName.FITWINDOW,              // 3
					PdfName.CENTERWINDOW,            // 4
					PdfName.DISPLAYDOCTITLE,        // 5
					PdfName.NONFULLSCREENPAGEMODE,    // 6
					PdfName.DIRECTION,                // 7
					PdfName.VIEWAREA,                // 8
					PdfName.VIEWCLIP,                // 9
					PdfName.PRINTAREA,                // 10
					PdfName.PRINTCLIP,                // 11
					PdfName.PRINTSCALING,            // 12
					PdfName.DUPLEX,                    // 13
					PdfName.PICKTRAYBYPDFSIZE,        // 14
					PdfName.PRINTPAGERANGE,            // 15
					PdfName.NUMCOPIES                // 16
	};

	public static final PdfName[] NONFULLSCREENPAGEMODE_PREFERENCES = {
					PdfName.USENONE, PdfName.USEOUTLINES, PdfName.USETHUMBS, PdfName.USEOC
	};

	public static final PdfName[] DIRECTION_PREFERENCES = {
					PdfName.L2R, PdfName.R2L
	};

	public static final PdfName[] PAGE_BOUNDARIES = {
					PdfName.MEDIABOX, PdfName.CROPBOX, PdfName.BLEEDBOX, PdfName.TRIMBOX, PdfName.ARTBOX
	};

	public static final PdfName[] PRINTSCALING_PREFERENCES = {
					PdfName.APPDEFAULT, PdfName.NONE
	};

	public static final PdfName[] DUPLEX_PREFERENCES = {
					PdfName.SIMPLEX, PdfName.DUPLEXFLIPSHORTEDGE, PdfName.DUPLEXFLIPLONGEDGE
	};

	private static final int viewerPreferencesMask = 0xfff000;

	private final PdfDictionary viewerPreferences = new PdfDictionary ();

	private int pageLayoutAndMode = 0;

	public void setViewerPreferences ( int preferences ) {
		this.pageLayoutAndMode |= preferences;
		// for backwards compatibility, it is also possible
		// to set the following viewer preferences with this method:
		if ( ( preferences & viewerPreferencesMask ) != 0 ) {
			pageLayoutAndMode = ~viewerPreferencesMask & pageLayoutAndMode;
			if ( ( preferences & PdfWriter.HideToolbar ) != 0 )
				viewerPreferences.put ( PdfName.HIDETOOLBAR, PdfBoolean.PDFTRUE );
			if ( ( preferences & PdfWriter.HideMenubar ) != 0 )
				viewerPreferences.put ( PdfName.HIDEMENUBAR, PdfBoolean.PDFTRUE );
			if ( ( preferences & PdfWriter.HideWindowUI ) != 0 )
				viewerPreferences.put ( PdfName.HIDEWINDOWUI, PdfBoolean.PDFTRUE );
			if ( ( preferences & PdfWriter.FitWindow ) != 0 )
				viewerPreferences.put ( PdfName.FITWINDOW, PdfBoolean.PDFTRUE );
			if ( ( preferences & PdfWriter.CenterWindow ) != 0 )
				viewerPreferences.put ( PdfName.CENTERWINDOW, PdfBoolean.PDFTRUE );
			if ( ( preferences & PdfWriter.DisplayDocTitle ) != 0 )
				viewerPreferences.put ( PdfName.DISPLAYDOCTITLE, PdfBoolean.PDFTRUE );

			if ( ( preferences & PdfWriter.NonFullScreenPageModeUseNone ) != 0 )
				viewerPreferences.put ( PdfName.NONFULLSCREENPAGEMODE, PdfName.USENONE );
			else if ( ( preferences & PdfWriter.NonFullScreenPageModeUseOutlines ) != 0 )
				viewerPreferences.put ( PdfName.NONFULLSCREENPAGEMODE, PdfName.USEOUTLINES );
			else if ( ( preferences & PdfWriter.NonFullScreenPageModeUseThumbs ) != 0 )
				viewerPreferences.put ( PdfName.NONFULLSCREENPAGEMODE, PdfName.USETHUMBS );
			else if ( ( preferences & PdfWriter.NonFullScreenPageModeUseOC ) != 0 )
				viewerPreferences.put ( PdfName.NONFULLSCREENPAGEMODE, PdfName.USEOC );

			if ( ( preferences & PdfWriter.DirectionL2R ) != 0 )
				viewerPreferences.put ( PdfName.DIRECTION, PdfName.L2R );
			else if ( ( preferences & PdfWriter.DirectionR2L ) != 0 )
				viewerPreferences.put ( PdfName.DIRECTION, PdfName.R2L );

			if ( ( preferences & PdfWriter.PrintScalingNone ) != 0 )
				viewerPreferences.put ( PdfName.PRINTSCALING, PdfName.NONE );
		}
	}

	private int getIndex ( PdfName key ) {
		for ( int i = 0; i < VIEWER_PREFERENCES.length; i++ ) {
			if ( VIEWER_PREFERENCES[i].equals ( key ) )
				return i;
		}
		return -1;
	}

	private boolean isPossibleValue ( PdfName value, PdfName[] accepted ) {
		for ( PdfName pdfName : accepted ) {
			if ( pdfName.equals ( value ) ) {
				return true;
			}
		}
		return false;
	}

	public void addViewerPreference ( PdfName key, PdfObject value ) {
		switch ( getIndex ( key ) ) {
		case 0: // HIDETOOLBAR
		case 1: // HIDEMENUBAR
		case 2: // HIDEWINDOWUI
		case 3: // FITWINDOW
		case 4: // CENTERWINDOW
		case 5: // DISPLAYDOCTITLE
		case 14: // PICKTRAYBYPDFSIZE
			if ( value instanceof PdfBoolean ) {
				viewerPreferences.put ( key, value );
			}
			break;
		case 6: // NONFULLSCREENPAGEMODE
			if ( value instanceof PdfName
							&& isPossibleValue ( (PdfName) value, NONFULLSCREENPAGEMODE_PREFERENCES ) ) {
				viewerPreferences.put ( key, value );
			}
			break;
		case 7: // DIRECTION
			if ( value instanceof PdfName
							&& isPossibleValue ( (PdfName) value, DIRECTION_PREFERENCES ) ) {
				viewerPreferences.put ( key, value );
			}
			break;
		case 8:  // VIEWAREA
		case 9:  // VIEWCLIP
		case 10: // PRINTAREA
		case 11: // PRINTCLIP
			if ( value instanceof PdfName
							&& isPossibleValue ( (PdfName) value, PAGE_BOUNDARIES ) ) {
				viewerPreferences.put ( key, value );
			}
			break;
		case 12: // PRINTSCALING
			if ( value instanceof PdfName
							&& isPossibleValue ( (PdfName) value, PRINTSCALING_PREFERENCES ) ) {
				viewerPreferences.put ( key, value );
			}
			break;
		case 13: // DUPLEX
			if ( value instanceof PdfName
							&& isPossibleValue ( (PdfName) value, DUPLEX_PREFERENCES ) ) {
				viewerPreferences.put ( key, value );
			}
			break;
		case 15: // PRINTPAGERANGE
			if ( value instanceof PdfArray ) {
				viewerPreferences.put ( key, value );
			}
			break;
		case 16: // NUMCOPIES
			if ( value instanceof PdfNumber ) {
				viewerPreferences.put ( key, value );
			}
			break;
		}
	}

	public void addToCatalog ( PdfDictionary catalog ) {
		// Page Layout
		catalog.remove ( PdfName.PAGELAYOUT );
		if ( ( pageLayoutAndMode & PdfWriter.PageLayoutSinglePage ) != 0 )
			catalog.put ( PdfName.PAGELAYOUT, PdfName.SINGLEPAGE );
		else if ( ( pageLayoutAndMode & PdfWriter.PageLayoutOneColumn ) != 0 )
			catalog.put ( PdfName.PAGELAYOUT, PdfName.ONECOLUMN );
		else if ( ( pageLayoutAndMode & PdfWriter.PageLayoutTwoColumnLeft ) != 0 )
			catalog.put ( PdfName.PAGELAYOUT, PdfName.TWOCOLUMNLEFT );
		else if ( ( pageLayoutAndMode & PdfWriter.PageLayoutTwoColumnRight ) != 0 )
			catalog.put ( PdfName.PAGELAYOUT, PdfName.TWOCOLUMNRIGHT );
		else if ( ( pageLayoutAndMode & PdfWriter.PageLayoutTwoPageLeft ) != 0 )
			catalog.put ( PdfName.PAGELAYOUT, PdfName.TWOPAGELEFT );
		else if ( ( pageLayoutAndMode & PdfWriter.PageLayoutTwoPageRight ) != 0 )
			catalog.put ( PdfName.PAGELAYOUT, PdfName.TWOPAGERIGHT );

		// Page Mode
		catalog.remove ( PdfName.PAGEMODE );
		if ( ( pageLayoutAndMode & PdfWriter.PageModeUseNone ) != 0 )
			catalog.put ( PdfName.PAGEMODE, PdfName.USENONE );
		else if ( ( pageLayoutAndMode & PdfWriter.PageModeUseOutlines ) != 0 )
			catalog.put ( PdfName.PAGEMODE, PdfName.USEOUTLINES );
		else if ( ( pageLayoutAndMode & PdfWriter.PageModeUseThumbs ) != 0 )
			catalog.put ( PdfName.PAGEMODE, PdfName.USETHUMBS );
		else if ( ( pageLayoutAndMode & PdfWriter.PageModeFullScreen ) != 0 )
			catalog.put ( PdfName.PAGEMODE, PdfName.FULLSCREEN );
		else if ( ( pageLayoutAndMode & PdfWriter.PageModeUseOC ) != 0 )
			catalog.put ( PdfName.PAGEMODE, PdfName.USEOC );
		else if ( ( pageLayoutAndMode & PdfWriter.PageModeUseAttachments ) != 0 )
			catalog.put ( PdfName.PAGEMODE, PdfName.USEATTACHMENTS );

		// viewer preferences (Table 8.1 of the PDF Reference)
		catalog.remove ( PdfName.VIEWERPREFERENCES );
		if ( viewerPreferences.size () > 0 ) {
			catalog.put ( PdfName.VIEWERPREFERENCES, viewerPreferences );
		}
	}

}
