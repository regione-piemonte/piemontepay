/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import it.csi.epay.epaypaweb.presentation.util.CSVUtil;

public class FileReadingTest {

	@Test
	public void readFileBufferedReader() {

		try {
			File csvFile = new File(FileReadingTest.class.getResource("/CSV_DI_PROVA_KO_con_solo_una_riga_con_il_numero_di_colonne_giusto.csv").toURI());

			String csvLine = null;
			BufferedReader reader = new BufferedReader(new FileReader(csvFile));
			while((csvLine=reader.readLine()) != null) {
				System.out.println(">>>> " + csvLine);
				System.out.println("colonne: " + CSVUtil.parseLine(csvLine).size());
			}
			reader.close();
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void readFileScanner() {

		try {
			File csvFile = new File(FileReadingTest.class.getResource("/CSV_DI_PROVA_KO_con_solo_una_riga_con_il_numero_di_colonne_giusto.csv").toURI());

			Scanner scanner = new Scanner(csvFile);
			scanner.useDelimiter("\n");
			while(scanner.hasNextLine()) {
				String csvLine = scanner.nextLine();
				System.out.println(">>>> " + csvLine);
				System.out.println("colonne: " + CSVUtil.parseLine(csvLine).size());
			}
			scanner.close();
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}

