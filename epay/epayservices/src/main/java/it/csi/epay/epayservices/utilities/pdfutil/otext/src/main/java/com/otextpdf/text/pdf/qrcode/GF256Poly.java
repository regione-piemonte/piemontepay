/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities.pdfutil.otext.src.main.java.com.otextpdf.text.pdf.qrcode;

final class GF256Poly {

	private final GF256 field;

	private final int[] coefficients;

	GF256Poly ( GF256 field, int[] coefficients ) {
		if ( coefficients == null || coefficients.length == 0 ) {
			throw new IllegalArgumentException ();
		}
		this.field = field;
		int coefficientsLength = coefficients.length;
		if ( coefficientsLength > 1 && coefficients[0] == 0 ) {
			// Leading term must be non-zero for anything except the constant polynomial "0"
			int firstNonZero = 1;
			while ( firstNonZero < coefficientsLength && coefficients[firstNonZero] == 0 ) {
				firstNonZero++;
			}
			if ( firstNonZero == coefficientsLength ) {
				this.coefficients = field.getZero ().coefficients;
			} else {
				this.coefficients = new int[coefficientsLength - firstNonZero];
				System.arraycopy ( coefficients,
								firstNonZero,
								this.coefficients,
								0,
								this.coefficients.length );
			}
		} else {
			this.coefficients = coefficients;
		}
	}

	int[] getCoefficients () {
		return coefficients;
	}

	int getDegree () {
		return coefficients.length - 1;
	}

	boolean isZero () {
		return coefficients[0] == 0;
	}

	int getCoefficient ( int degree ) {
		return coefficients[coefficients.length - 1 - degree];
	}

	GF256Poly addOrSubtract ( GF256Poly other ) {
		if ( !field.equals ( other.field ) ) {
			throw new IllegalArgumentException ( "GF256Polys do not have same GF256 field" );
		}
		if ( isZero () ) {
			return other;
		}
		if ( other.isZero () ) {
			return this;
		}

		int[] smallerCoefficients = this.coefficients;
		int[] largerCoefficients = other.coefficients;
		if ( smallerCoefficients.length > largerCoefficients.length ) {
			int[] temp = smallerCoefficients;
			smallerCoefficients = largerCoefficients;
			largerCoefficients = temp;
		}
		int[] sumDiff = new int[largerCoefficients.length];
		int lengthDiff = largerCoefficients.length - smallerCoefficients.length;
		// Copy high-order terms only found in higher-degree polynomial's coefficients
		System.arraycopy ( largerCoefficients, 0, sumDiff, 0, lengthDiff );

		for ( int i = lengthDiff; i < largerCoefficients.length; i++ ) {
			sumDiff[i] = GF256.addOrSubtract ( smallerCoefficients[i - lengthDiff], largerCoefficients[i] );
		}

		return new GF256Poly ( field, sumDiff );
	}

	GF256Poly multiply ( GF256Poly other ) {
		if ( !field.equals ( other.field ) ) {
			throw new IllegalArgumentException ( "GF256Polys do not have same GF256 field" );
		}
		if ( isZero () || other.isZero () ) {
			return field.getZero ();
		}
		int[] aCoefficients = this.coefficients;
		int aLength = aCoefficients.length;
		int[] bCoefficients = other.coefficients;
		int bLength = bCoefficients.length;
		int[] product = new int[aLength + bLength - 1];
		for ( int i = 0; i < aLength; i++ ) {
			int aCoeff = aCoefficients[i];
			for ( int j = 0; j < bLength; j++ ) {
				product[i + j] = GF256.addOrSubtract ( product[i + j],
								field.multiply ( aCoeff, bCoefficients[j] ) );
			}
		}
		return new GF256Poly ( field, product );
	}

	GF256Poly multiplyByMonomial ( int degree, int coefficient ) {
		if ( degree < 0 ) {
			throw new IllegalArgumentException ();
		}
		if ( coefficient == 0 ) {
			return field.getZero ();
		}
		int size = coefficients.length;
		int[] product = new int[size + degree];
		for ( int i = 0; i < size; i++ ) {
			product[i] = field.multiply ( coefficients[i], coefficient );
		}
		return new GF256Poly ( field, product );
	}

	GF256Poly[] divide ( GF256Poly other ) {
		if ( !field.equals ( other.field ) ) {
			throw new IllegalArgumentException ( "GF256Polys do not have same GF256 field" );
		}
		if ( other.isZero () ) {
			throw new IllegalArgumentException ( "Divide by 0" );
		}

		GF256Poly quotient = field.getZero ();
		GF256Poly remainder = this;

		int denominatorLeadingTerm = other.getCoefficient ( other.getDegree () );
		int inverseDenominatorLeadingTerm = field.inverse ( denominatorLeadingTerm );

		while ( remainder.getDegree () >= other.getDegree () && !remainder.isZero () ) {
			int degreeDifference = remainder.getDegree () - other.getDegree ();
			int scale = field.multiply ( remainder.getCoefficient ( remainder.getDegree () ), inverseDenominatorLeadingTerm );
			GF256Poly term = other.multiplyByMonomial ( degreeDifference, scale );
			GF256Poly iterationQuotient = field.buildMonomial ( degreeDifference, scale );
			quotient = quotient.addOrSubtract ( iterationQuotient );
			remainder = remainder.addOrSubtract ( term );
		}

		return new GF256Poly[] { quotient, remainder };
	}

	public String toString () {
		StringBuilder result = new StringBuilder ( 8 * getDegree () );
		for ( int degree = getDegree (); degree >= 0; degree-- ) {
			int coefficient = getCoefficient ( degree );
			if ( coefficient != 0 ) {
				if ( coefficient < 0 ) {
					result.append ( " - " );
					coefficient = -coefficient;
				} else {
					if ( result.length () > 0 ) {
						result.append ( " + " );
					}
				}
				if ( degree == 0 || coefficient != 1 ) {
					int alphaPower = field.log ( coefficient );
					if ( alphaPower == 0 ) {
						result.append ( '1' );
					} else if ( alphaPower == 1 ) {
						result.append ( 'a' );
					} else {
						result.append ( "a^" );
						result.append ( alphaPower );
					}
				}
				if ( degree != 0 ) {
					if ( degree == 1 ) {
						result.append ( 'x' );
					} else {
						result.append ( "x^" );
						result.append ( degree );
					}
				}
			}
		}
		return result.toString ();
	}

}
