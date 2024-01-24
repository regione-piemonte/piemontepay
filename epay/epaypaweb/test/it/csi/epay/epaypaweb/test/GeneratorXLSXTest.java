/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import it.csi.epay.epaypaweb.util.spreadsheet.GeneratorXLSX;
import it.csi.epay.epaypaweb.util.spreadsheet.SpreadsheetColumn;
import it.csi.epay.epaypaweb.util.spreadsheet.SpreadsheetGenerator;

public class GeneratorXLSXTest {

	private void writeToFile(byte[] data, File destination) throws IOException {
		FileOutputStream dest = new FileOutputStream(destination);
		dest.write(data);
		dest.close();
	}

	@Test
	public void testKO1() {
		GeneratorXLSX gen = new GeneratorXLSX();

		try {
			gen.newRow();
			Assert.assertTrue(false);
		} catch(IllegalStateException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testKO2() {
		GeneratorXLSX gen = new GeneratorXLSX();

		try {
			gen.setColumnValue("INVALID", 1);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void test() {
		SpreadsheetGenerator gen = new GeneratorXLSX();

		gen.addColumn(new SpreadsheetColumn("PROGR", "progressivo", "0", 0));
		gen.addColumn(new SpreadsheetColumn("NOME", "nome", 1));
		gen.addColumn(new SpreadsheetColumn("IMP", "importo", "#,##0.00", 2));
		gen.addColumn(new SpreadsheetColumn("DATA", "data", "dd/mm/yyyy", 3));
		gen.addColumn(new SpreadsheetColumn("DATA-ORA", "data e ora", "dd/mm/yyyy hh:mm:ss", 4));
		gen.addColumn(new SpreadsheetColumn("ACCENTATA", "e'ащ", 6));
		gen.addColumn(new SpreadsheetColumn("NOTE", "note", 5));

		gen.newRow();
		gen.setColumnValue("PROGR", 1);
		gen.setColumnValue("NOME", "riga1");
		gen.setColumnValue("IMP", 1200.05d);
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("DATA-ORA", new Date());
		gen.setColumnValue("NOTE", "nota riga 1 con ;");

		gen.newRow();
		gen.setColumnValue("PROGR", 2);
		gen.setColumnValue("NOME", "riga2");
		gen.setColumnValue("IMP", 0.3f);
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("DATA-ORA", new Date());
		gen.setColumnValue("NOTE", "nota riga 2 con \"doppio apice e ;\"");

		gen.newRow();
		gen.setColumnValue("PROGR", 3);
		gen.setColumnValue("NOME", "riga3");
		gen.setColumnValue("IMP", new BigDecimal(12345678.90));
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("DATA-ORA", new Date());
		gen.setColumnValue("NOTE", "nota riga 3");

		gen.newRow();
		gen.setColumnValue("PROGR", 4);
		gen.setColumnValue("NOME", "riga4");
		gen.setColumnValue("IMP", 0);
		gen.setColumnValue("DATA", new Date());
		gen.setColumnValue("DATA-ORA", new Date());
		gen.setColumnValue("NOTE", "nota riga 4");

		try {
			writeToFile(gen.getData(), new File("test-output.xlsx"));

			Desktop.getDesktop().open(new File("test-output.xlsx"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
