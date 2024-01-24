/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl;

import java.util.concurrent.TimeUnit;

import it.csi.mdp.mdpetl.application.InoltroFlussiRendicontazioneEstesi;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;

public class MainRiconciliazioneFlussiEstesi {

	static LogUtil log = new LogUtil(Main.class);

	public static void main(String[] args) {
		long time = System.nanoTime();
		try {
			elabora(args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			time = System.nanoTime() - time;
			long milliseconds = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) % 1000;
			long seconds = TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS);
			System.out.printf("Elapsed %d ns: %02d:%02d:%02d.%03d hh:mm:ss", time, (seconds / 3600) % 60, (seconds / 60) % 60, seconds % 60, milliseconds);
		}
		System.out.println("Fine elaborazione.");
		System.exit(0);
	}


	private static void elabora(String[] args) throws Exception {

		byte[] key=StringUtils.getKeyDB();

		elaborate(args, key);
	}


	private static void elaborate(String[] args, byte[] key) {
		System.out.println("Inizio elaborazione.");
		try {
			new InoltroFlussiRendicontazioneEstesi().inoltraFlussi(args[0], key);
		} catch (Exception e) {
			log.error("main", e);
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
