/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl;
import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtListaInformativePSP;
import it.csi.mdp.mdpetl.application.InvocaServizio;
import it.csi.mdp.mdpetl.application.SaveToDatabase;
import it.csi.mdp.mdpetl.dto.InformativePSP;
import it.csi.mdp.mdpetl.dto.ParametroNodo;
import it.csi.mdp.mdpetl.integration.util.dao.ParametriNodoSpcDAO;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Main {        

	static LogUtil log = new LogUtil(Main.class);
	public static void main(String[] args) {
		long time = System.nanoTime();
		try {
			elaborate(args);
//			elaborateLocal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			time = System.nanoTime() - time;
			long milliseconds = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) % 1000;
			long seconds = TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS);
			System.out.printf("Elapsed %d ns: %02d:%02d:%02d.%03d hh:mm:ss", time, (seconds/3600)%60, (seconds/60)%60, seconds%60, milliseconds);
		}
		System.out.println("Fine elaborazione.");
		System.exit(0);
	}

	
	@SuppressWarnings("unchecked")
	private static void elaborate(String[] args) throws Exception {
		System.out.println("Inizio elaborazione.");
		 byte[] key = StringUtils.getKeyDB();
		System.out.println("elaborate lancio il dao");
		Set<ParametroNodo> lista = new ParametriNodoSpcDAO ( key ).findDistinctDominiV2 ();
		
		if (lista == null){
			System.out.println( "nessun parametro trovato");
		}else{
			System.out.println("n. parametri trovati " +lista.size());
		}
		System.out.println("elaborate lancio dopo il dao");
		ParametroNodo pm = new ParametroNodo();
		
		if(lista.size()>0){
			Iterator iterator = lista.iterator();
			while(iterator.hasNext()){
				pm = (ParametroNodo) iterator.next();
				break;
			}
			
			String endPointUrl 								= args.length > 4 ? args[4] : pm.getPortaDiDominio();
			String azione      								= args[0];
			String origine 									= args[1];
			String ggStoricizzazione						= args[2];
			int numeroRetry = 3;
			if (args.length > 3)
				numeroRetry									= Integer.parseInt(args[3]);
			
//			String azione      								= "nodoChiediInformativaPSP";
//			String origine 								    = "NODO SPC";
//			String ggStoricizzazione 						= "180";
			String identificativoDominio 					= args.length > 5 ? args[5] : pm.getIdentificativoDominio();
			String identificativoIntermediarioPA 			= args.length > 6 ? args[6] : pm.getIdentificativointermediarioPA();
			String identificativoStazioneIntermediarioPA 	= args.length > 7 ? args[7] : pm.getIdentificativoStazioneIntermediarioPA();
			String password 								= args.length > 8 ? args[8] : pm.getPasswordDominioNodoSpc();
			
			boolean gadEnabled								= args.length > 9 ? Boolean.getBoolean(args[9])    : pm.isGadEnabled();
			
				
					
			/*System.out.println("param "+pm.getPortaDiDominio());
			System.out.println("param "+pm.getIdentificativoDominio());
			System.out.println("param "+pm.getIdentificativointermediarioPA());
			System.out.println("param "+pm.getIdentificativoStazioneIntermediarioPA());
			System.out.println("param "+pm.getPasswordDominioNodoSpc());*/

			
			System.out.println("param "+endPointUrl);
			System.out.println("param "+identificativoDominio);
			System.out.println("param "+identificativoIntermediarioPA);
			
			System.out.println("param "+identificativoStazioneIntermediarioPA);
			System.out.println("param "+password);
			System.out.println("param "+gadEnabled);
			
			
			try {
				System.out.println("Step1 estraggo l'oggetto complesso contenente i dati");
				CtListaInformativePSP ctListaInformativePSP = new InvocaServizio().elabora(endPointUrl,
																							 azione,
																							 identificativoDominio ,
																							 identificativoIntermediarioPA,
																							 identificativoStazioneIntermediarioPA,
																							 password,
																							 numeroRetry,
																							 gadEnabled
																							 );
				
				
				System.out.println("Step2 inserisco i dati in tabella");
				
				SaveToDatabase db = new SaveToDatabase();
				List<InformativePSP> listaInfo = db.getListaInformativaPSP(ctListaInformativePSP,origine);	
				
				System.out.println("listaInfo len " + listaInfo.size());
				
				db.saveToDB(listaInfo,ggStoricizzazione);
				
			}catch (Exception e) {		
				System.out.println("Exception e " + e.getMessage());
				
				e.printStackTrace();
				System.exit(-1);
			}
		}else{
			System.out.println("Fine elaborazione lista parametri nodo non presente");
		}
	}
	
	
	private static void elaborateLocal() throws Exception {
		System.out.println("Inizio elaborazione in Locale.");
		 byte[] key = StringUtils.getKeyDB();
		//ParametroNodo pm = findDistinctDomainLocal(key).get(0);
		Set<ParametroNodo> lista = new ParametriNodoSpcDAO(key).findDistinctDomini();
		ParametroNodo pm = new ParametroNodo();
		
		Iterator iterator = lista.iterator();
		while(iterator.hasNext()){
			pm = (ParametroNodo) iterator.next();
			break;
		}
		
		String endPointUrl 								= pm.getPortaDiDominio();
		String azione      								= "nodoChiediInformativaPSP";
		String origine 								    = "NODO SPC";
		String ggStoricizzazione 						= "1";
		String identificativoDominio 					= pm.getIdentificativoDominio();
		String identificativoIntermediarioPA 			= pm.getIdentificativointermediarioPA();
		String identificativoStazioneIntermediarioPA 	= pm.getIdentificativoStazioneIntermediarioPA();
		String password 								= pm.getPasswordDominioNodoSpc();
		
		boolean gadEnabled								= pm.isGadEnabled();
		

		

		System.out.println("param "+pm.getPortaDiDominio());
		System.out.println("param "+pm.getIdentificativoDominio());
		System.out.println("param "+pm.getIdentificativointermediarioPA());
		System.out.println("param "+pm.getIdentificativoStazioneIntermediarioPA());
		System.out.println("param "+pm.getPasswordDominioNodoSpc());
		
		try {

			System.out.println("Step1 estraggo l'oggetto complesso contenente i dati");
			CtListaInformativePSP ctListaInformativePSP = new InvocaServizio().elabora(endPointUrl,
																						 azione,
																						 identificativoDominio ,
																						 identificativoIntermediarioPA,
																						 identificativoStazioneIntermediarioPA,
																						 password,
																						 5, gadEnabled);
			
			System.out.println("Step2 inserisco i dati in tabella");
			SaveToDatabase db = new SaveToDatabase();
			List<InformativePSP> listaInfo = db.getListaInformativaPSP(ctListaInformativePSP,origine);		
			db.saveToDB(listaInfo,ggStoricizzazione);
			
		}catch (Exception e) {		
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
//	public static List<ParametroNodo> findDistinctDomainLocal(String key){
//		List<ParametroNodo> lista = new ArrayList<ParametroNodo>();
//		ParametroNodo el = new ParametroNodo();
//		el.setPortaDiDominio("http://tst-pdd.ruparpiemonte.it/openspcoop/PD/PDNodoDeiPagamentiDellaPATest/PagamentiTelematiciRPT6T/");
//		el.setIdentificativoDominio("80087670016");
//		el.setIdentificativointermediarioPA("01995120019");
//		el.setIdentificativoStazioneIntermediarioPA("01995120019_01");
//		el.setPasswordDominioNodoSpc("PIEMONT3");
//		lista.add(el);
//
//		return lista;
//	}
}	
