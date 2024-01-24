/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import it.csi.epay.epaypaweb.util.spreadsheet.GeneratorCSV;
import it.csi.epay.epaypaweb.util.spreadsheet.SpreadsheetColumn;
import it.csi.epay.epaypaweb.util.spreadsheet.SpreadsheetGenerator;

public class GeneratorCSVTest {

	private void writeToFile(byte[] data, File destination) throws IOException {
		FileOutputStream dest = new FileOutputStream(destination);
		dest.write(data);
		dest.close();
	}

	@Test
	public void testKO1() {
		GeneratorCSV gen = new GeneratorCSV();

		try {
			gen.newRow();
			Assert.assertTrue(false);
		} catch(IllegalStateException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testKO2() {
		GeneratorCSV gen = new GeneratorCSV();

		try {
			gen.setColumnValue("INVALID", 1);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void test() {
		SpreadsheetGenerator gen = new GeneratorCSV();

		gen.addColumn(new SpreadsheetColumn("PROGR", "progressivo", "%02d", 0));
		gen.addColumn(new SpreadsheetColumn("NOME", "nome", 1));
		gen.addColumn(new SpreadsheetColumn("IMP", "importo", "%1.2f", 2));
		gen.addColumn(new SpreadsheetColumn("DATA", "data/ora", "%1$te/%1$tm/%1$tY %1$tT", 3));
		gen.addColumn(new SpreadsheetColumn("ACCENTATA", "e'ащ", 5));
		gen.addColumn(new SpreadsheetColumn("NOTE", "note", 4));

		gen.newRow();
		gen.setColumnValue("PROGR", 1);
		gen.setColumnValue("NOME", "riga1");
		gen.setColumnValue("IMP", 100.05f);
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("NOTE", "nota riga 1 con ;");

		gen.newRow();
		gen.setColumnValue("PROGR", 2);
		gen.setColumnValue("NOME", "riga2");
		gen.setColumnValue("IMP", 0.3f);
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("NOTE", "nota riga 2 con \"doppio apice e ;\"");

		gen.newRow();
		gen.setColumnValue("PROGR", 3);
		gen.setColumnValue("NOME", "riga3");
		gen.setColumnValue("IMP", 0.3f);
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("NOTE", "nota riga 3");

		gen.newRow();
		gen.setColumnValue("PROGR", 4);
		gen.setColumnValue("NOME", "riga4");
		gen.setColumnValue("IMP", 0f);
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("NOTE", "nota riga 4");

		try {
			writeToFile(gen.getData(), new File("test-output.csv"));

			Desktop.getDesktop().open(new File("test-output.csv"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
