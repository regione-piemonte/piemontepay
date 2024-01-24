/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.application;

import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtFasciaCostoServizio;
import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtInformativaDetail;
import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtInformativaPSP;
import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtInformazioniServizio;
import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtListaInformativaDetail;
import it.csi.mdp.generatedvo.catalogodatiinformatvi.CtListaInformativePSP;
import it.csi.mdp.mdpetl.dto.FasciaCostoServizio;
import it.csi.mdp.mdpetl.dto.InformativePSP;
import it.csi.mdp.mdpetl.dto.InformazioniServizio;
import it.csi.mdp.mdpetl.dto.KeyValue;
import it.csi.mdp.mdpetl.integration.util.dao.ArchiviaFasceCostoServizioOldDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ArchiviaInformazioniServizioOldDAO;
import it.csi.mdp.mdpetl.integration.util.dao.DeleteInformativePSPDAO;
import it.csi.mdp.mdpetl.integration.util.dao.EstraiListaTipoVersamentoNotSupportedDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciFasciaCostoServizioBCKDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciFasciaCostoServizioDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciInformativePSPBCKDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciInformativePSPDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciInformazioniServizioBCKDAO;
import it.csi.mdp.mdpetl.integration.util.dao.InserisciInformazioniServizioDAO;
import it.csi.mdp.mdpetl.integration.util.dao.ModificaStatoInformativePSPDAO;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;

public class SaveToDatabase {

	static LogUtil log = new LogUtil(SaveToDatabase.class);

	public List<InformativePSP> getListaInformativaPSP(CtListaInformativePSP ctListaInformativePSP,String origine) {
		String methodName="getListaInformativaPSP";
		log.startMethod(methodName);
		List<InformativePSP> listaInformativaPSP = new ArrayList<InformativePSP>();
		
		List<KeyValue> listaNotSupported = getListaParametriNotSupported();

		int ordinamento=0;		
		log.info(methodName,"lunghezza " +  ctListaInformativePSP.getInformativaPSP().size());
		
		try {
			for(CtInformativaPSP infoMaster: ctListaInformativePSP.getInformativaPSP()){
				
				
				
				CtListaInformativaDetail listaDetail = infoMaster.getListaInformativaDetail();
				for(CtInformativaDetail infoDetail: listaDetail.getInformativaDetail()){
					InformativePSP infoPspDTO = new InformativePSP();				

					//MASTER
					log.debug(methodName, "id flusso start = " + infoMaster.getIdentificativoFlusso());
					infoPspDTO.setIdentificativoFlusso(infoMaster.getIdentificativoFlusso());
					infoPspDTO.setIdentificativoPSP(infoMaster.getIdentificativoPSP());
					infoPspDTO.setRagioneSociale(infoMaster.getRagioneSociale());				
					infoPspDTO.setDataInizioValidita((String.valueOf(infoMaster.getInformativaMaster().getDataInizioValidita())));
					infoPspDTO.setDataPubblicazione((String.valueOf(infoMaster.getInformativaMaster().getDataPubblicazione())));
					infoPspDTO.setStornoPagamento(infoMaster.getInformativaMaster().getStornoPagamento());
					infoPspDTO.setUrlInformazioniPSP(infoMaster.getInformativaMaster().getUrlInformazioniPSP());
					log.debug(methodName, "id flusso end = " + infoMaster.getIdentificativoFlusso());
					//FINE MASTER
					
					/*
					 * JIRA MDPNEW-55: nuovo CatalogoDatiInformativi_1_0_11.xsd contiene le fasce di costo e elenco di informazioni per lingua: aggiunte le due tabelle
					 * e per retrocompatibilita' si impostano le informazioni preesistenti a livello informativa PSP cercando in ordine: info in IT, EN, l'ultima trovata.
					 * Per i costi massimi si reperisce il costo massimo della fascia piu' alta.
					 */
					StringBuilder condizioniEconomicheMassime = new StringBuilder();
					CtInformazioniServizio infoUsata = null;
					
					if (infoDetail.getCostiServizio() != null && infoDetail.getCostiServizio().getListaFasceCostoServizio() !=null ){
						for (CtFasciaCostoServizio costo : infoDetail.getCostiServizio().getListaFasceCostoServizio().getFasciaCostoServizio()) {
							FasciaCostoServizio fascia = new FasciaCostoServizio();
							fascia.setCostoFisso(costo.getCostoFisso());
							fascia.setImportoMassimoFascia(costo.getImportoMassimoFascia());
							fascia.setValoreCommissione(costo.getValoreCommissione());
							infoPspDTO.getElencoFasceCostoServizio().add(fascia);
							condizioniEconomicheMassime.append("fino a " + costo.getImportoMassimoFascia() + " costo fisso = " + costo.getCostoFisso() + " commissioni = " + costo.getValoreCommissione() + ";\r\n");
						}
					}
					
					
					if (infoDetail.getListaInformazioniServizio() != null) {
						for (CtInformazioniServizio info : infoDetail.getListaInformazioniServizio().getInformazioniServizio()) {
							InformazioniServizio infoServizio = new InformazioniServizio();
							infoServizio.setCodiceLingua(info.getCodiceLingua().value());
							infoServizio.setDescrizioneServizio(info.getDescrizioneServizio());
							infoServizio.setDisponibilitaServizio(info.getDisponibilitaServizio());
							infoServizio.setLimitazioniServzio(info.getLimitazioniServizio());
							infoServizio.setUrlInformazioniServizio(info.getUrlInformazioniCanale());
							infoPspDTO.getElencoInfoServizio().add(infoServizio);
							if ("IT".equals(info.getCodiceLingua().value())) {
								infoUsata = info;
								break;
							} else if ("EN".equals(info.getCodiceLingua().value())) {
								infoUsata = info;
								break;
							}
							infoUsata = info;
						}
					}
					
					if (infoUsata == null)
						log.debug(methodName, "infoUsata  is null" );
					
					log.debug(methodName, "setting dei dati = " + infoMaster.getIdentificativoFlusso());
					
					if (condizioniEconomicheMassime != null)
						infoPspDTO.setCondizioniEconomicheMassime(condizioniEconomicheMassime.toString());
					
					if (infoDetail.getIdentificazioneServizio() != null)
					    infoPspDTO.setDescrizioneServizio(infoDetail.getIdentificazioneServizio().getNomeServizio());
					
					infoPspDTO.setDisponibilitaServizio(infoUsata.getDisponibilitaServizio());
					infoPspDTO.setIdentificativoCanale(infoDetail.getIdentificativoCanale());
					infoPspDTO.setIdentificativoIntermediario(infoDetail.getIdentificativoIntermediario());
					infoPspDTO.setModelloPagamento(infoDetail.getModelloPagamento());
					infoPspDTO.setPriorita(infoDetail.getPriorita());	
					infoPspDTO.setUrlInformazioniCanale(infoUsata.getUrlInformazioniCanale());
					
					if (infoDetail.getTipoVersamento()!= null)
					 infoPspDTO.setTipoVersamento(infoDetail.getTipoVersamento().value());
					
					log.debug(methodName, "fine setting dei dati = " + infoMaster.getIdentificativoFlusso());
					
					//attriguti extra
					ordinamento++;
					infoPspDTO.setOrdinamento(ordinamento);
					infoPspDTO.setOrigine(origine);
				
					boolean isSupportato = isInformativaSupportata(listaNotSupported,infoPspDTO); 
					infoPspDTO.setStatoinserimento("NOT_SUPPORTED");
					if(isSupportato){
						infoPspDTO.setStatoinserimento("NEW");
					}

					listaInformativaPSP.add(infoPspDTO);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(methodName, e.getCause());
			e.printStackTrace();
		}
		log.info(methodName, "listaInformativaPSP len "+listaInformativaPSP.size());
		log.stopMethod(methodName);
		return listaInformativaPSP;
	}

	private boolean isInformativaSupportata(List<KeyValue> listaNotSupported, InformativePSP infoPspDTO) {
		String methodName="isInformativaSupportata";
		log.startMethod(methodName);
		String metodo ="";
		for( KeyValue kv: listaNotSupported){
			 try { 
				 String valoreDiConfronto = kv.getValue();
				 metodo = "get"+StringUtils.capitalize(kv.getKey());
				 
				 String valueOgg ="";
				 Object obj = InformativePSP.class.getMethod(metodo).invoke(infoPspDTO);
				 
				 log.info(methodName, obj.getClass());
				 
				 if(obj instanceof String) {
					 valueOgg = (String)obj;
				 } else {
					 valueOgg = invokeValueOnObject(obj);
				 }
				 
//				 log.info(methodName, "valoreDiConfronto" + valoreDiConfronto);
//				 log.info(methodName, "valueOgg" + valueOgg);
//				 log.info(methodName, "confronto " + (valoreDiConfronto.indexOf(valueOgg)>=0));

				 if(valoreDiConfronto.indexOf(valueOgg)>=0){
					 return false;					 
				 }
				 
			 } catch(NoSuchMethodException e) {
				 log.error(methodName,"metodo non mappato nell'oggetto infoDetail controllare la stringa che sia ben formattata dul db metodo " + metodo);
		     } catch(Exception e) {
				 log.error(methodName,"Errore generico: " + (e != null ? e.getMessage() : "null"));
		     }
		}
		
		log.startMethod(methodName);
		return true;
	}

	private String invokeValueOnObject(Object obj) {
		final String methodName = "invokeValueOnObject";
		Class<?> clazz = obj.getClass();
		try {
			Method method = clazz.getMethod("value");
			Object res = method.invoke(obj);
			return (String)res;
		} catch (Exception e) {
			log.info(methodName, e.getClass().getSimpleName() + ": " + e.getMessage());
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 
	 * @param listaInfo
	 * @throws SQLException 
	 * @throws SerialException 
	 * @throws Exception 
	 * @throws NamingException 
	 */
	public void saveToDB(List<InformativePSP> listaInfo,String ggStoricizzazione) {
		String methodName="saveToDB";
		log.startMethod(methodName);
		//Integer ggStor = 0;
		//Date oggi = new Date();
		try {
			new ModificaStatoInformativePSPDAO("PREOLD","NEW").executeUpdate();	
			new ArchiviaFasceCostoServizioOldDAO().executeUpdate();
			new ArchiviaInformazioniServizioOldDAO().executeUpdate();
			for(InformativePSP infoPsp: listaInfo){
				
				int chiave = new InserisciInformativePSPDAO(infoPsp).executeUpdate(true);
				log.info(methodName, "CHIAVE PSP: "+ chiave);
				
				for (InformazioniServizio info : infoPsp.getElencoInfoServizio()) {
					info.setIdinformativapsp(chiave);
					new InserisciInformazioniServizioDAO(info).executeUpdate();
				}
				
				for (FasciaCostoServizio info : infoPsp.getElencoFasceCostoServizio()) {
					info.setIdinformativapsp(chiave);
					new InserisciFasciaCostoServizioDAO(info).executeUpdate();
				}
			}
			new ModificaStatoInformativePSPDAO("OLD","PREOLD").executeUpdate();	
			try{
				Integer.parseInt(ggStoricizzazione);
				
				log.info(methodName, "Storicizzo a "+ ggStoricizzazione + " giorni");
				new InserisciInformativePSPBCKDAO(Integer.parseInt(ggStoricizzazione)).executeUpdate();
				new InserisciFasciaCostoServizioBCKDAO(Integer.parseInt(ggStoricizzazione)).executeUpdate();
				new InserisciInformazioniServizioBCKDAO(Integer.parseInt(ggStoricizzazione)).executeUpdate();
				
				log.info(methodName, "dopo la storicizzazione cancello dalla tabella informativa_psp");
				new DeleteInformativePSPDAO(Integer.parseInt(ggStoricizzazione)).executeUpdate();	
			}catch (Exception e) {
				log.error(methodName, "Exception Controllare il parametro passato per la storicizzazione par: " + ggStoricizzazione);
			}
		} catch (SerialException e) {
			log.error(methodName, "SerialException " + e);
		} catch (NamingException e) {
			log.error(methodName, "NamingException " +e);
		} catch (SQLException e) {
			log.error(methodName, "SQLException " +e);
		} catch (Exception e) {
			log.error(methodName, "Exception " +e);
		}
		log.stopMethod(methodName);
	}
	
	private List<KeyValue> getListaParametriNotSupported() {
		String methodName="getListaParametriNotSupported";
		log.startMethod(methodName);
		List<KeyValue> ris=new ArrayList<KeyValue>() ;

		try {
			ris = new EstraiListaTipoVersamentoNotSupportedDAO().executeQuery();
//			log.warn(methodName, "lista tipologieversamento non supportate " + ris);	
//			if(ris.equalsIgnoreCase("")){
//				log.warn(methodName, "***********************************************************************************************************************************************");
//				log.warn(methodName, "ATTENZIONE VERIFICARE CHE NELLA TABELLA CONFIG ESISTA LA PROPRIETA mdpetl.informativapsp.notsupported.tipoVersamento e sia se serve valorizzato");
//				log.warn(methodName, "***********************************************************************************************************************************************");
//			}
		} catch (Exception e) {
			log.error(methodName,e.getMessage());
			e.printStackTrace();
		}finally{
			log.stopMethod(methodName);
		}
		return ris;
	}

	//public static void main (String[] args){
	//	String listaNotSupported ="p";
	//	String tv = "";
	//	System.out.println("ris   -->  " + listaNotSupported.indexOf(tv ) );
	//}
}
