/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.thoughtworks.xstream.XStream;

import it.csi.mdp.mdpetl.application.AccessoDatabaseFlussiRendicontazione;
import it.csi.mdp.mdpetl.application.InvocaServizioFlussiRendicontazione;
import it.csi.mdp.mdpetl.dto.ParametroNodo;
import it.csi.mdp.mdpetl.integration.util.dao.ParametriNodoSpcDAO;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;

public class MainFlussiRendicontazione {

	static LogUtil log = new LogUtil(Main.class);

	public static void main(String[] args) {
		long time = System.nanoTime();
		try {
			cicloElaborate(args);
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


	private static void cicloElaborate(String[] args) throws Exception {

		byte[] key=StringUtils.getKeyDB();

        Set<ParametroNodo> lista = new ParametriNodoSpcDAO ( key ).findDistinctDominiV2 ();
		System.out.println("IDENTIFICATIVO DOMINIO " + lista.size());
		XStream xs = new XStream();
		System.out.println("DOMINIO: " + xs.toXML(lista));
		Iterator iterator = lista.iterator();
        ParametroNodo pm = new ParametroNodo();
		while(iterator.hasNext()) {
            pm = (ParametroNodo) iterator.next ();

			elaborate(args,pm, key);
//			elaborateLocal(pm, key);
		}
	}


    private static void elaborateLocal ( ParametroNodo pm, byte [] key ) {
		System.out.println("Inizio elaborazione.");

		String endPointUrl 								= pm.getPortaDiDominio();
		String azione 									= "nodoChiediElencoFlussiRendicontazione";
		String identificativoDominio 					= pm.getIdentificativoDominio();
		String identificativoIntermediarioPA 			= pm.getIdentificativointermediarioPA();
		String identificativoStazioneIntermediarioPA	= pm.getIdentificativoStazioneIntermediarioPA();

		String password 								= pm.getPasswordDominioNodoSpc();
		boolean gadEnabled								= pm.isGadEnabled();
		
		
		//			System.out.println("param "+pm.getPortaDiDominio());
		//			System.out.println("param "+pm.getIdentificativoDominio());
		//			System.out.println("param "+pm.getIdentificativointermediarioPA());
		//			System.out.println("param "+pm.getIdentificativoStazioneIntermediarioPA());
		//			System.out.println("param "+pm.getPasswordDominioNodoSpc());

        System.out.println (
            "Avvio elaborazione LOCAL per ente " + identificativoDominio + " con stazione" + pm.getIdentificativoStazioneIntermediarioPA() );

       
            try {
                System.out.println ( "Reperimento elenco dei flussi rendicontazione" );
                AccessoDatabaseFlussiRendicontazione db = new AccessoDatabaseFlussiRendicontazione ();
                //			List<String> listaInfo = db.getElencoPSP();

                System.out.println ( "Elenco dei flussi rendicontazione reperito" );

                //			for (String idPSP : listaInfo) {
                //				System.out.println(idPSP);
                new InvocaServizioFlussiRendicontazione ().elaborazioneFlussiPerPSP ( endPointUrl, azione, identificativoDominio, identificativoIntermediarioPA,
                    identificativoStazioneIntermediarioPA, password, null, key, 5, gadEnabled );
                //			}

            } catch ( Exception e ) {
                log.error ( "main", e );
                e.printStackTrace ();
                System.exit ( -1 );
            }
        
	}

    private static void elaborate ( String [] args, ParametroNodo pm, byte [] key ) {
		System.out.println("Inizio elaborazione.");

		String endPointUrl 								= pm.getPortaDiDominio();
		String azione 									= args[0];
		String identificativoDominio 					= pm.getIdentificativoDominio();
		String identificativoIntermediarioPA 			= pm.getIdentificativointermediarioPA();
		String identificativoStazioneIntermediarioPA	= pm.getIdentificativoStazioneIntermediarioPA();
		
		String password 								= pm.getPasswordDominioNodoSpc();
		boolean gadEnabled								= pm.isGadEnabled();
		int numeroRetry									= Integer.valueOf(args[1]);
		boolean errore = false;

              

            System.out.println ( "Avvio elaborazione per ente " + identificativoDominio + " e stazione " + identificativoStazioneIntermediarioPA );

            try {
                System.out.println ( "Reperimento elenco dei flussi rendicontazione" );
                AccessoDatabaseFlussiRendicontazione db = new AccessoDatabaseFlussiRendicontazione ();
                try {
                    errore = errore | new InvocaServizioFlussiRendicontazione ().elaborazioneFlussiPerPSP ( endPointUrl, azione, identificativoDominio,
                        identificativoIntermediarioPA,
                        identificativoStazioneIntermediarioPA, password, null, key, numeroRetry, gadEnabled );
                } catch ( Exception e ) {
                    log.error ( "elaborate", "Errore a livello di ciclo PSP" );
                    e.printStackTrace ();
                    errore = true;
                }
                //	          }
                if ( errore ) {
                    log.error ( "main", "Si e' verificato un errore" );
                    System.exit ( -1 );
                }

            } catch ( Exception e ) {
                log.error ( "main", e );
                e.printStackTrace ();
                System.exit ( -1 );
            }

		


	}




	//DA ELIMINARE
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

