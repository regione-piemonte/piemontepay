/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.paymentmanager;

import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.dto.ChiaveValore;
import it.csi.mdp.core.business.dto.DettaglioFruitore;
import it.csi.mdp.core.business.dto.ER;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.InformativePSPScelto;
import it.csi.mdp.core.business.dto.IuvOtticoFruitore;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.dto.ParametriUrlWisp;
import it.csi.mdp.core.business.dto.ParametroWisp;
import it.csi.mdp.core.business.dto.PartAnComune;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.dto.RR;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.business.dto.TransazioneExtraAttribute;
import it.csi.mdp.core.business.dto.Vapplicationcomuni;
import it.csi.mdp.core.business.dto.multicarrello.RPTData;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.paymentmanager.local.AppGateway;
import it.csi.mdp.core.business.paymentmanager.local.AppGatewayInformativa;
import it.csi.mdp.core.interfacecxf.MissingParameterException;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;

//import javax.jws.WebService;

/**
 * Interfaccia EJB che definisce i metodi (servizi) esposti 
 *
 * <p>
 *
 * @version 1.0,  &nbsp; 15-APR-2008
 * @since SDK1.5
 */
 
public interface Payment
  extends javax.ejb.EJBObject
{
	public AppGateway[] getModalitaPagamento (Transazione t,String app_id) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException ;
	public Applicationcustomfields[] getCustomFields(String applicationid, String gatewayid)	throws RemoteException,DaoException, MissingParameterException ;
	
	public Transazione initTransazione (String app_id, String basket_id) throws RemoteException, DaoException, NamingException, CreateException, DatatypeConfigurationException,MissingParameterException;
	
	public String startTransazione (Transazione t, TransazioneExtraAttribute [] tea) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	public Transazione getStatoTransazione(String transaction_id) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException ;
    
	public void setStatoTransazione(String transaction_id, long stato, String gwSstato) throws RemoteException, DaoException, MissingParameterException;
	public void setStatoTransazione(String transaction_id, long stato, String gwSstato, String iuv) throws RemoteException, DaoException, MissingParameterException;
	public ApplicationDetail getApplicationDetail(String applicationid, String gatewayid, String paymentmodeid)	throws RemoteException, DaoException, MissingParameterException; 
	public Application getApplication(String applicationid) throws RemoteException, DaoException, MissingParameterException;
	public Transazione getTransazione(String transaction_id) throws RemoteException, DaoException, NamingException, CreateException , MissingParameterException;
	public TransazioneExtraAttribute[] getTransazioneExtraAttributes(String transaction_id) throws RemoteException, DaoException, NamingException, CreateException , MissingParameterException;
	//public boolean testResources() throws  RemoteException;
	//public boolean isAlive() throws RemoteException;
	public void setTransazione (Transazione t) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	public Transazione getTransazioneByPaymentId (String paymentid) throws RemoteException,DaoException, MissingParameterException;
	public PartAnComune getComuneProvincia (String istatComune) throws RemoteException, DaoException, MissingParameterException;
	public Vapplicationcomuni getPagonetCustomData (String applicationId, String gatewayId) throws RemoteException ,DaoException, MissingParameterException;
	public long getIdOpXpay() throws RemoteException, DaoException;
	public Hashtable<String,String> getDatiPagonet(String tranId) throws RemoteException, DaoException;
	public Properties getConfig () throws RemoteException;
	public Transazione getTransazioneByAuthorNumber(String authorNumber) throws RemoteException,DaoException;
	/* SERVIZI NODO */
	/**
	 * Registra un evento nel giornale eventi
	 * @param gde l'evento da registrare
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public void registraEventoGiornale(GiornaleEvento gde) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	/**
	 * Registra una richiesta di pagamento telematica, quando essa viene inviata al Nodo SPC tramite lo startTrnsazione
	 * @param rpt
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public Integer registraRichiestaRevoca(RR rr) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;	
    /**
     * Registra una richiesta di revoca 
     * @param iuv
     * @param idRR
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */		
	public void updateIdRrByIuv(String iuv, Integer idRR)  throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	   /**
     * Registra una esito revoca 
     * @param rr
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void registraEsitoRevoca(ER er) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	   /**
     * Registra una richiesta di pagamento telematica, quando essa viene inviata al Nodo SPC tramite lo startTrnsazione
     * @param rpt
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
	public void registraRichiestaTelematicaPagamento(RPT rpt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	/**
	 * Registra una richiesta di pagamento telematica, quando essa viene inviata al Nodo SPC tramite lo startTrnsazione
	 * @param rpt
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public void aggiornaRichiestaTelematicaPagamento(RPT rpt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	/**
	 * Registra una Ricevuta Telematica quando il nodo la restituisce oppure viene chiamato getStatoTransazione sull'adapter
	 * @param rt
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public void registraRicevutaTelematica(RT rt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;

    /**
     * Aggiorna una Ricevuta Telematica quando il nodo la restituisce oppure viene chiamato getStatoTransazione sull'adapter
     * @param rt
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public void aggiornaRicevutaTelematica(RT rt) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	/**
	 * 
	 * @param idTransazione
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public RT recuperaRichiestaPerIdTransazione(String idTransazione) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
     * 
     * @param applicationId
     * @param fieldName
     * @return
     * @throws RemoteException
     * @throws DaoException
     * @throws NamingException
     * @throws CreateException
     * @throws MissingParameterException
     */
    public List<Applicationcustomfields> recuperaApplicationCustomFields(String applicationId, String fieldName) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;

    public List<Applicationcustomfields> recuperaApplicationCustomFieldDecoded(String applicationId, String fieldName) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;

    public String recuperaApplicationIdFromIuv(String iuv) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
    
	/**
	 * Recupera Delle richieste di pagamento in base al filtro selezionato (campi tabella)
	 * @param filtro
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public List<RPT> recuperaRichiestaPagamentoConFiltro(RPT filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Recupera l'informativa PSP desiderata quando viene creata la RPT.
	 * @param id
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public InformativePSP recuperaInformativaPerId(Integer id) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Recupera l'informativa PSP desiderata quando viene creata la RPT.
	 * @param id
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public InformativePSP recuperaInformativaPerIdentificativoPSP(String id) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * get modalita' di pagamento estesa
	 * @param t
	 * @param appId
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public AppGatewayInformativa[] getModalitaInformativePagamento(Transazione t, String appId) throws RemoteException, DaoException, NamingException,
	CreateException, MissingParameterException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * Imposta l'attributo IdSession sulla tabella transazione. L'idSession e' un attributo restituito nella URL dal
	 * Nodo SPC al fine di identificare univocamente il redirect di una transazione e viene usato per il ritorno all'applicazione fruitrice
	 * @param transacionId
	 * @param idSession
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 */
	public void setIdSessionTransazione (String transacionId, String idSession) throws RemoteException,DaoException, MissingParameterException;
	
	/**
	 * Imposta l'attributo IdSession sulla tabella transazione. L'idSession e' un attributo restituito nella URL dal
	 * Nodo SPC al fine di identificare univocamente il redirect di una transazione e viene usato per il ritorno all'applicazione fruitrice
	 * @param transacionId
	 * @param idSession
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 */
	public void registraTransazioneIuv (String transacionId, String iuv) throws RemoteException,DaoException, MissingParameterException;
	/**
	 * Al ritorno sulla jsp di planaggio da un pagagamento eseguito tramite Nodo SPC, serve per reperire la transazione di appartenenza
	 * @param idSession
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws MissingParameterException
	 */
	public Transazione findTransazioneByidSession(String idSession) throws RemoteException, DaoException, MissingParameterException;
	
	/**
	 * Aggiorna i campi della RPT
	 * @param datiAggiornati
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public void aggiornaRPT(RPT datiAggiornati) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;	
	
	/**
	 * 
	 * @param elencoErrori
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public void registraErroriNodo(List<NodoErrore> elencoErrori) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;	
	
	/**
	 * Reperisce l'accoppiata iuv ed i dettagli del fruitore per la gestione della chiamata
	 * @param iuvOttico obbligatorio (passato dal nodo)
	 * @param iuvStandard (opzionale nel caso cambino le specifiche
	 * @return Oggetto composto della tabella iuv_ottici piu' descrizione dell'Application (per identificare l'applicazione fruitrice)
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public IuvOtticoFruitore recuperaDatiFruitorePerIuvOttico(String iuvOttico, String iuvStandard) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Reperisce i dettagli del fruitore per la gestione della chiamata
	 * @param applicationId
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public DettaglioFruitore recuperaDatiFruitorePerApplicationId(String applicationId) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Reperisce l'IBAN configurato in base ad applicazione, psp e tipo versamento
	 * @param applicationId
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public IbanEnteApplication recuperaIbanEnteApplication(IbanEnteApplication filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * @param tea
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public void registraTransazioneExtraAttributes(TransazioneExtraAttribute[] tea) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Invia un'email di errore ed effettua la registrazione in mdp_errori
	 * @param appId
	 * @param transaction
	 * @param body
	 */
	public void errorMail(String appId, Transazione transaction, String body) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * PRepara e ritorna l'url per il wisp in base alle scelte del fruitore (con
	 * @param applicationId
	 * @param transactionId
	 * @param paramentriUrlWisp
	 * @param prametriAggiuntivi
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public String getUrlWisp(String applicationId, Transazione transactionId,ParametriUrlWisp paramentriUrlWisp, List<ChiaveValore> prametriAggiuntivi) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Recupera i parametri WISP
	 * @param filtro
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public List<ParametroWisp> reuperaParametriWisp(ParametroWisp filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Cancella un parametro Wisp
	 * @param filtro
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public void deleteParametroWisp(ParametroWisp filtro) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Recuper gli acf per application_id e gateway nodo
	 * @param applicationId
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public Applicationcustomfields[] recuperaApplicationCustomFieldsNodo(String keypa) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Aggiorna la riga di DB che corrisponde ad una richiesta di selezione PSP sul WISP quando il nodo
	 * redirige di nuovo a MDP dopo la scelta / annullamento / timeout
	 * @param daAggiornare
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public int aggiornaParametroWisp(ParametroWisp daAggiornare) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Recupera il PSP scelto dall'utente sulle pagine del wisp.
	 * @param applicationId
	 * @param transactionId
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public InformativePSPScelto getSceltaWisp(String applicationId, String transactionId) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	/**
	 * Start transazione per carrello RPT e multiversamento - WISP 2.0
	 * @param t
	 * @param tea
	 * @param elencoRPT
	 * @return
	 * @throws RemoteException
	 * @throws DaoException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws MissingParameterException
	 */
	public String startTransazione (Transazione t, TransazioneExtraAttribute [] tea, List<RPTData> elencoRPT) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	public int inserisciMultiVersamento(ElementoMultiversamento elem) throws RemoteException, DaoException, NamingException, CreateException, MissingParameterException;
	
	
}
