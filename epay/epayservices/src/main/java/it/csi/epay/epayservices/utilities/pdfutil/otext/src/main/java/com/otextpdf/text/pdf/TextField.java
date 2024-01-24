/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf;

import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.BaseColor;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Chunk;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.DocumentException;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Font;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Phrase;
import it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Rectangle;

import java.io.IOException;
import java.util.ArrayList;


public class TextField extends BaseField {

	private String defaultText;

	private String[] choices;

	private ArrayList<Integer> choiceSelections = new ArrayList<> ();

	private int topFirst;

	private float extraMarginLeft;

	private float extraMarginTop;

	private ArrayList<BaseFont> substitutionFonts;

	private BaseFont extensionFont;

	public TextField ( PdfWriter writer, Rectangle box, String fieldName ) {
		super ( writer, box, fieldName );
	}

	private static boolean checkRTL ( String text ) {
		if ( text == null || text.isEmpty () )
			return false;
		char[] cc = text.toCharArray ();
		for ( int c : cc ) {
			if ( c >= 0x590 && c < 0x0780 )
				return true;
		}
		return false;
	}

	private static void changeFontSize ( Phrase p, float size ) {
		for ( it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element element : p )
			( (Chunk) element ).getFont ().setSize ( size );
	}

	public static String removeCRLF ( String text ) {
		if ( text.indexOf ( '\n' ) >= 0 || text.indexOf ( '\r' ) >= 0 ) {
			char[] p = text.toCharArray ();
			StringBuilder sb = new StringBuilder ( p.length );
			for ( int k = 0; k < p.length; ++k ) {
				char c = p[k];
				if ( c == '\n' )
					sb.append ( ' ' );
				else if ( c == '\r' ) {
					sb.append ( ' ' );
					if ( k < p.length - 1 && p[k + 1] == '\n' )
						++k;
				} else
					sb.append ( c );
			}
			return sb.toString ();
		}
		return text;
	}

	public static String obfuscatePassword ( String text ) {
		char[] pchar = new char[text.length ()];
		for ( int i = 0; i < text.length (); i++ )
			pchar[i] = '*';
		return new String ( pchar );
	}

	private Phrase composePhrase ( String text, BaseFont ufont, BaseColor color, float fontSize ) {
		Phrase phrase;
		if ( extensionFont == null && ( substitutionFonts == null || substitutionFonts.isEmpty () ) )
			phrase = new Phrase ( new Chunk ( text, new Font ( ufont, fontSize, 0, color ) ) );
		else {
			FontSelector fs = new FontSelector ();
			fs.addFont ( new Font ( ufont, fontSize, 0, color ) );
			if ( extensionFont != null )
				fs.addFont ( new Font ( extensionFont, fontSize, 0, color ) );
			if ( substitutionFonts != null ) {
				for ( BaseFont substitutionFont : substitutionFonts )
					fs.addFont ( new Font ( substitutionFont, fontSize, 0, color ) );
			}
			phrase = fs.process ( text );
		}
		return phrase;
	}

	public PdfAppearance getAppearance () throws IOException, DocumentException {
		PdfAppearance app = getBorderAppearance ();
		app.beginVariableText ();
		if ( text == null || text.isEmpty () ) {
			app.endVariableText ();
			return app;
		}

		boolean borderExtra = borderStyle == PdfBorderDictionary.STYLE_BEVELED || borderStyle == PdfBorderDictionary.STYLE_INSET;
		float h = box.getHeight () - borderWidth * 2 - extraMarginTop;
		float bw2 = borderWidth;
		if ( borderExtra ) {
			h -= borderWidth * 2;
			bw2 *= 2;
		}
		float offsetX = Math.max ( bw2, 1 );
		float offX = Math.min ( bw2, offsetX );
		app.saveState ();
		app.rectangle ( offX, offX, box.getWidth () - 2 * offX, box.getHeight () - 2 * offX );
		app.clip ();
		app.newPath ();
		String ptext;
		if ( ( options & PASSWORD ) != 0 )
			ptext = obfuscatePassword ( text );
		else if ( ( options & MULTILINE ) == 0 )
			ptext = removeCRLF ( text );
		else
			ptext = text;
		BaseFont ufont = getRealFont ();
		BaseColor fcolor = textColor == null ? GrayColor.GRAYBLACK : textColor;
		int rtl = checkRTL ( ptext ) ? PdfWriter.RUN_DIRECTION_LTR : PdfWriter.RUN_DIRECTION_NO_BIDI;
		float usize = fontSize;
		Phrase phrase = composePhrase ( ptext, ufont, fcolor, usize );
		if ( ( options & MULTILINE ) != 0 ) {
			float width = box.getWidth () - 4 * offsetX - extraMarginLeft;
			float factor = ufont.getFontDescriptor ( BaseFont.BBOXURY, 1 ) - ufont.getFontDescriptor ( BaseFont.BBOXLLY, 1 );
			ColumnText ct = new ColumnText ( null );
			if ( usize == 0 ) {
				usize = h / factor;
				if ( usize > 4 ) {
					if ( usize > 12 )
						usize = 12;
					float step = Math.max ( ( usize - 4 ) / 10, 0.2f );
					ct.setSimpleColumn ( 0, -h, width, 0 );
					ct.setAlignment ( alignment );
					ct.setRunDirection ( rtl );
					for ( ; usize > 4; usize -= step ) {
						ct.setYLine ( 0 );
						changeFontSize ( phrase, usize );
						ct.setText ( phrase );
						ct.setLeading ( factor * usize );
						int status = ct.go ( true );
						if ( ( status & ColumnText.NO_MORE_COLUMN ) == 0 )
							break;
					}
				}
				if ( usize < 4 )
					usize = 4;
			}
			changeFontSize ( phrase, usize );
			ct.setCanvas ( app );
			float leading = usize * factor;
			float offsetY = offsetX + h - ufont.getFontDescriptor ( BaseFont.BBOXURY, usize );
			ct.setSimpleColumn ( extraMarginLeft + 2 * offsetX, -20000, box.getWidth () - 2 * offsetX, offsetY + leading );
			ct.setLeading ( leading );
			ct.setAlignment ( alignment );
			ct.setRunDirection ( rtl );
			ct.setText ( phrase );
			ct.go ();
		} else {
			if ( usize == 0 ) {
				float maxCalculatedSize = h / ( ufont.getFontDescriptor ( BaseFont.BBOXURX, 1 ) - ufont.getFontDescriptor ( BaseFont.BBOXLLY, 1 ) );
				changeFontSize ( phrase, 1 );
				float wd = ColumnText.getWidth ( phrase, rtl, 0 );
				if ( wd == 0 )
					usize = maxCalculatedSize;
				else
					usize = Math.min ( maxCalculatedSize, ( box.getWidth () - extraMarginLeft - 4 * offsetX ) / wd );
				if ( usize < 4 )
					usize = 4;
			}
			changeFontSize ( phrase, usize );
			float offsetY = offX + ( box.getHeight () - 2 * offX - ufont.getFontDescriptor ( BaseFont.ASCENT, usize ) ) / 2;
			if ( offsetY < offX )
				offsetY = offX;
			if ( offsetY - offX < -ufont.getFontDescriptor ( BaseFont.DESCENT, usize ) ) {
				float ny = -ufont.getFontDescriptor ( BaseFont.DESCENT, usize ) + offX;
				float dy = box.getHeight () - offX - ufont.getFontDescriptor ( BaseFont.ASCENT, usize );
				offsetY = Math.min ( ny, Math.max ( offsetY, dy ) );
			}
			if ( ( options & COMB ) != 0 && maxCharacterLength > 0 ) {
				int textLen = Math.min ( maxCharacterLength, ptext.length () );
				int position = 0;
				if ( alignment == Element.ALIGN_RIGHT )
					position = maxCharacterLength - textLen;
				else if ( alignment == Element.ALIGN_CENTER )
					position = ( maxCharacterLength - textLen ) / 2;
				float step = ( box.getWidth () - extraMarginLeft ) / maxCharacterLength;
				float start = step / 2 + position * step;
				if ( textColor == null )
					app.setGrayFill ( 0 );
				else
					app.setColorFill ( textColor );
				app.beginText ();
				for ( it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.Element element : phrase ) {
					Chunk ck = (Chunk) element;
					BaseFont bf = ck.getFont ().getBaseFont ();
					app.setFontAndSize ( bf, usize );
					StringBuffer sb = ck.append ( "" );
					for ( int j = 0; j < sb.length (); ++j ) {
						String c = sb.substring ( j, j + 1 );
						float wd = bf.getWidthPoint ( c, usize );
						app.setTextMatrix ( extraMarginLeft + start - wd / 2, offsetY - extraMarginTop );
						app.showText ( c );
						start += step;
					}
				}
				app.endText ();
			} else {
				float x;
				switch ( alignment ) {
				case Element.ALIGN_RIGHT:
					x = extraMarginLeft + box.getWidth () - 2 * offsetX;
					break;
				case Element.ALIGN_CENTER:
					x = extraMarginLeft + box.getWidth () / 2;
					break;
				default:
					x = extraMarginLeft + 2 * offsetX;
				}
				ColumnText.showTextAligned ( app, alignment, phrase, x, offsetY - extraMarginTop, 0, rtl, 0 );
			}
		}
		app.restoreState ();
		app.endVariableText ();
		return app;
	}

	PdfAppearance getListAppearance () throws IOException, DocumentException {
		PdfAppearance app = getBorderAppearance ();
		if ( choices == null || choices.length == 0 ) {
			return app;
		}
		app.beginVariableText ();

		int topChoice = getTopChoice ();

		BaseFont ufont = getRealFont ();
		float usize = fontSize;
		if ( usize == 0 )
			usize = 12;

		boolean borderExtra = borderStyle == PdfBorderDictionary.STYLE_BEVELED || borderStyle == PdfBorderDictionary.STYLE_INSET;
		float h = box.getHeight () - borderWidth * 2;
		float offsetX = borderWidth;
		if ( borderExtra ) {
			h -= borderWidth * 2;
			offsetX *= 2;
		}

		float leading = ufont.getFontDescriptor ( BaseFont.BBOXURY, usize ) - ufont.getFontDescriptor ( BaseFont.BBOXLLY, usize );
		int maxFit = (int) ( h / leading ) + 1;
		int first;
		int last;
		first = topChoice;
		last = first + maxFit;
		if ( last > choices.length )
			last = choices.length;
		topFirst = first;
		app.saveState ();
		app.rectangle ( offsetX, offsetX, box.getWidth () - 2 * offsetX, box.getHeight () - 2 * offsetX );
		app.clip ();
		app.newPath ();
		BaseColor fcolor = textColor == null ? GrayColor.GRAYBLACK : textColor;

		app.setColorFill ( new BaseColor ( 10, 36, 106 ) );
		for ( Integer choiceSelection : choiceSelections ) {
			int curChoice = choiceSelection;
			if ( curChoice >= first && curChoice <= last ) {
				app.rectangle ( offsetX, offsetX + h - ( curChoice - first + 1 ) * leading, box.getWidth () - 2 * offsetX, leading );
				app.fill ();
			}
		}
		float xp = offsetX * 2;
		float yp = offsetX + h - ufont.getFontDescriptor ( BaseFont.BBOXURY, usize );
		for ( int idx = first; idx < last; ++idx, yp -= leading ) {
			String ptext = choices[idx];
			int rtl = checkRTL ( ptext ) ? PdfWriter.RUN_DIRECTION_LTR : PdfWriter.RUN_DIRECTION_NO_BIDI;
			ptext = removeCRLF ( ptext );
			BaseColor textCol = choiceSelections.contains ( idx ) ? GrayColor.GRAYWHITE : fcolor;
			Phrase phrase = composePhrase ( ptext, ufont, textCol, usize );
			ColumnText.showTextAligned ( app, Element.ALIGN_LEFT, phrase, xp, yp, 0, rtl, 0 );
		}
		app.restoreState ();
		app.endVariableText ();
		return app;
	}

	private int getTopChoice () {
		if ( choiceSelections == null || choiceSelections.isEmpty () ) {
			return 0;
		}

		Integer firstValue = choiceSelections.get ( 0 );

		if ( firstValue == null ) {
			return 0;
		}

		int topChoice = 0;
		if ( choices != null ) {
			topChoice = firstValue;
			topChoice = Math.min ( topChoice, choices.length );
			topChoice = Math.max ( 0, topChoice );
		}
		return topChoice;
	}

	public void setChoices ( String[] choices ) {
		this.choices = choices;
	}

	public void setChoiceExports () {
	}

	public void setChoiceSelections ( ArrayList<Integer> selections ) {
		if ( selections != null ) {
			choiceSelections = new ArrayList<> ( selections );
			if ( choiceSelections.size () > 1 && ( options & BaseField.MULTISELECT ) == 0 ) {
				while ( choiceSelections.size () > 1 ) {
					choiceSelections.remove ( 1 );
				}
			}

		} else {
			choiceSelections.clear ();
		}
	}

	int getTopFirst () {
		return topFirst;
	}

	public void setExtraMargin ( float extraMarginLeft, float extraMarginTop ) {
		this.extraMarginLeft = extraMarginLeft;
		this.extraMarginTop = extraMarginTop;
	}

	public void setSubstitutionFonts ( ArrayList<BaseFont> substitutionFonts ) {
		this.substitutionFonts = substitutionFonts;
	}

	public void setExtensionFont ( BaseFont extensionFont ) {
		this.extensionFont = extensionFont;
	}
}
