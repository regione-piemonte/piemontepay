/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.business.mdpmultiiuv;

import it.csi.coopdiag.api.CalledResource;
import it.csi.coopdiag.api.InvocationNode;
import it.csi.coopdiag.engine.utils.DefaultSelfChecker;
import it.csi.csi.wrapper.CSIException;
import it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv.*;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.ErrorParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MdpMultiIuvSrvException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MissingParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.integration.mdpmultiiuv.dao.interfacce.MdpDao;
import it.csi.mdpnew.mdpmultiiuvsrv.interfacecsi.mdpmultiiuv.MdpMultiIuvSrv;
import it.csi.mdpnew.mdpmultiiuvsrv.util.MdpMultiIuvConstants;
import it.csi.mdpnew.mdpmultiiuvsrv.util.Utility;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.io.ClassPathResource;

import javax.xml.soap.SOAPException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MdpMultiIuvImpl implements MdpMultiIuvSrv {

	// / Implementazione operazioni esposte dal servizio

	MdpDao mdpDao;

	ClassPathResource cp = new ClassPathResource("/WEB-INF/classes/env.properties");
	private final Properties envProps = new Properties();
	private final static String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SS";
	private final static String DATE_PATTERN = "dd/MM/yyyy";

	private void getAllConfig() throws ErrorParameterException, SOAPException {

		Logger logger = getLogger("ejb");
		logger.debug("[MdpMultiIuvImpl::getAllConfig] - BEGIN");
		logger.debug("[MdpMultiIuvImpl::getAllConfig] - cp.getPath() " + cp.getPath());

		try {
			envProps.load(cp.getInputStream());
			List<ConfigDTO> lconf = mdpDao.findAllConfig();
			for (ConfigDTO c : lconf) {
				if (c != null) {
					logger.debug("[MdpMultiIuvImpl::getAllConfig] config:" + c);
					if (null != c.getValue()) {
						envProps.put(c.getKey(), c.getValue());
					}
				}
			}
		}
		catch (IOException ioe) {
			logger.error("[MdpMultiIuvImpl::getAllConfig] Errore: ", ioe);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_GENERICO);
		}
		logger.debug("[MdpMultiIuvImpl::getAllConfig] - END");

	}

	private List<ApplicationcustomfieldsDTO> getCustomFields(String applicationid, String gatewayid) throws ErrorParameterException, SOAPException,
			IOException {

		Logger logger = getLogger("ejb");
		logger.debug("[MdpMultiIuvImpl::getCustomFields] - START");
		String sKey;
		List<ApplicationcustomfieldsDTO> lstCustomFields;
		FileInputStream stream = null;
		try {
			getAllConfig();
			stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
			stream.close();

			lstCustomFields = mdpDao.getApplicationcustomfields(applicationid, gatewayid, sKey);
		}
		catch (FileNotFoundException e) {
			logger.error("[MdpMultiIuvImpl::getCustomFields] Error: ", e);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_KEYDB);
		}
		catch (IOException ioe) {
			stream.close();
			logger.error("[MdpMultiIuvImpl::getCustomFields] Errore: ", ioe);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_GENERICO);
		}
		finally {
			if (null != stream) {
				stream.close();
			}
			logger.debug("[MdpMultiIuvImpl::getCustomFields] - END");
		}

		return lstCustomFields;

	}

	private DescrizioneApplicativoDTO verificaIdApplicazione(String idApplicazione) throws ErrorParameterException, SOAPException {
		Logger logger = getLogger("ejb");
		logger.debug("[MdpMultiIuvImpl::verificaIdApplicazione] - BEGIN");
		logger.debug(" il valore id_application----> " + idApplicazione);

		// verifico la presenza dell id applicativo
		if (!mdpDao.isPresentIDApplication(idApplicazione)) {
			logger.error("[MdpMultiIuvImpl::verificaIdApplicazione] Errore: " + MdpMultiIuvConstants.ERRORE_ID_APPLICATION_NOT_EXIST);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ID_APPLICATION_NOT_EXIST);
		}

		// verifico la corretta configurazione delle tavole enti,
		// r_application_enti
		DescrizioneApplicativoDTO descrizioneApplicativoDTO;
		try {
			descrizioneApplicativoDTO = mdpDao.isPresentIDEnte(idApplicazione);
		}
		catch (Exception e) {
			logger.error("[MdpMultiIuvImpl::verificaIdApplicazione] Errore Di accesso ai dati ", e);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_CONFIGURATION_APPLICATION);
		}
		logger.debug("[MdpMultiIuvImpl::verificaIdApplicazione] - END");

		return descrizioneApplicativoDTO;
	}

	public IuvComplexResponse getIuvComplexResponse(String idApplication, String codicePagamento, Integer numeroRichiesto, String timeStamp,
			String mac) throws SOAPException, MdpMultiIuvSrvException, MissingParameterException, ErrorParameterException {

		Logger log = getLogger("ejb");
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] BEGIN");

        //TEST - TO REMOVE
        //log.debug ( "CALCULATED MAC :" +Utility.calcolaMAC ( "3", "1235", "PTW_202", "12/12/2012 00:00:00"));
        //TEST - TO REMOVE
        
		IuvComplexResponse ret = new IuvComplexResponse();
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] idApplication---> " + idApplication);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] codicePagamento---> " + codicePagamento);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] numeroRichiesto---> " + numeroRichiesto);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] timeStamp---> " + timeStamp);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] mac---> " + mac);
		// ******** VERIFICA VALORIZZAZIONE PARAMETRI INPUT ***********
		// Controllo idApplication
		if (StringUtils.isEmpty(idApplication) || idApplication.equals("")) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_ID_APPLICATION_IS_NECESSARY);
			throw new MissingParameterException(MdpMultiIuvConstants.ERRORE_ID_APPLICATION_IS_NECESSARY);
		}
		// Controllo Codice Versamento
		if (null == codicePagamento || codicePagamento.trim().equals("") || codicePagamento.equalsIgnoreCase("null")) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_NO_COD_VERS);
			throw new MissingParameterException(MdpMultiIuvConstants.ERRORE_NO_COD_VERS);
		}
		// Controllo timestamp
		if (null == timeStamp || timeStamp.equals("")) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_NO_TIMESTAMP);
			throw new MissingParameterException(MdpMultiIuvConstants.ERRORE_NO_TIMESTAMP);
		}
		// Controllo mac code
		if (null == mac || mac.equals("")) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_NO_MAC);
			throw new MissingParameterException(MdpMultiIuvConstants.ERRORE_NO_MAC);
		}
		// Controllo min numero IUV richiesti: se null oppure <=0 allora imposto
		// 1
		if (null == numeroRichiesto || numeroRichiesto <= 0) {
			numeroRichiesto = 1;
		}
		// ******** VERIFICA CONSISTENZA DATI INPUT **********
		// Verifica consistenza codice pagamento
		String dettaglioPagamVerificato = verificaDettaglioPagamento(codicePagamento);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] DETTAGLIO PAGAMENTO VERIFICATO " + dettaglioPagamVerificato);
		
		// Verifica consistenza id application
		DescrizioneApplicativoDTO descrizioneApplicativoDTO = verificaIdApplicazione(idApplication);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] APPLICATION ID VERIFICATO " + descrizioneApplicativoDTO.getIdApplication());
		
		// ******** CALCOLO AuxDigit, AppCode, MAC **********
		List<GatewayDTO> lstGateway = mdpDao.getGatewayNodoSPC(idApplication);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] LISTA GATEWAY: " + lstGateway);
		
		String auxDigit = "";
		String applicationCode = "";
		String passphrase = "";
		String codiceIdentifEnte = "";
		
		// TODO: attenzione al momento abbiamo un solo gateway con le
		// caratteristiche implementate nel DAO
		// verififare se esiste un modo per definire univocamente la relazione
		if (null != lstGateway) {
			for (GatewayDTO lst : lstGateway) {
				auxDigit = getFieldValue(descrizioneApplicativoDTO.getIdApplication(), lst.getGatewayid(), "auxDigit");
				applicationCode = getFieldValue(descrizioneApplicativoDTO.getIdApplication(), lst.getGatewayid(), "applicationCode");
				passphrase = getFieldValue(descrizioneApplicativoDTO.getIdApplication(), lst.getGatewayid(), "passphrase");
				codiceIdentifEnte = getFieldValue(descrizioneApplicativoDTO.getIdApplication(), lst.getGatewayid(), "codiceIdentificativoUnivocoBeneficiario");
			}
		}

		if (auxDigit.equals("")) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_AUX_DIGIT);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_AUX_DIGIT);
		}
		
		//L'application code viene testato solo in caso di auxDigit NON a 3
		if(!auxDigit.equalsIgnoreCase ( "3")) {
    		if (applicationCode.equals("")) {
    			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_APPCODE);
			    throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_APPCODE);
    		}
		}
		
		if (passphrase.equals("")) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_PASSHPRASE);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_PASSHPRASE);
		}
		if (codiceIdentifEnte.equals("")) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_COD_ENTE);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_COD_ENTE);
		}
		
		// Controllo max numero IUV richiesti:
		String sogliaMaxIuv = envProps.getProperty("mdpnewmultiiuv.soglia.iuv");
		if(sogliaMaxIuv == null || sogliaMaxIuv.equals("") || !Utility.isNumeric(sogliaMaxIuv) || Integer.parseInt(sogliaMaxIuv)<=0){
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: Soglia Massima IUV non valorizzata correttamente!!!");
			throw new MissingParameterException(MdpMultiIuvConstants.ERRORE_GENERICO);
		}		
		if (numeroRichiesto > Integer.parseInt(sogliaMaxIuv)) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_MAX_MULTI_IUV);
			throw new MissingParameterException(MdpMultiIuvConstants.ERRORE_MAX_MULTI_IUV);
		}		
		
		// Verifica consistenza mac
		String macCalcolato = Utility.calcolaMAC(passphrase, dettaglioPagamVerificato, descrizioneApplicativoDTO.getIdApplication(), timeStamp);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] macCalcolato " + macCalcolato);
		if (!macCalcolato.equals(mac)) {
			log.error("[MdpMultiIuvImpl::getIuvComplexResponse] Errore: " + MdpMultiIuvConstants.ERRORE_MAC_NON_RICONOSCIUTO);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_MAC_NON_RICONOSCIUTO);
		}
		// ******** VALORIZZAZIONE DATI COMUNI **********
		DateTime dt = new DateTime();
		DateTimeFormatter fmtTime = DateTimeFormat.forPattern(TIMESTAMP_PATTERN);
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
		String strOutputDate = fmt.print(dt);
		ret.setApplicationID(descrizioneApplicativoDTO.getIdApplication());
		ret.setCodiceIdentificativoEnte(codiceIdentifEnte);
		ret.setCodiceVersamento(dettaglioPagamVerificato);
		// ******** VALORIZZAZIONE DATO COMPLEX **********
		// Ciclo su numero richiesto
		List<IuvComplex> lisCplx = new ArrayList<IuvComplex>();
		for (int i = 0; i < numeroRichiesto; i++) {
			IuvComplex iuvComplex;
			iuvComplex = getIuvComplex(descrizioneApplicativoDTO, auxDigit, applicationCode, strOutputDate);
			iuvComplex.setAuxDigit(auxDigit);
			
			if(!auxDigit.equals ( "3" )) {
			    iuvComplex.setApplicationCode(applicationCode);
			}
			
			lisCplx.add(iuvComplex);
		}
		ret.setIuvComplex(lisCplx);
		int[] totInsert = mdpDao.insertMassiveIuvOttici(ret, descrizioneApplicativoDTO.getIdEnte());
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] totale record inseriti = " + totInsert.length);
		// ******** VALORIZZAZIONE MAC E TIMESTAMP DA RESTITUIRE **********
		String strOutputDateTime = fmtTime.print(dt);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] timeStampCalc " + strOutputDateTime);
		String returnMac = Utility.calcolaMAC(passphrase, dettaglioPagamVerificato, descrizioneApplicativoDTO.getIdApplication(), strOutputDateTime);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] returnMac " + returnMac);
		ret.setTimestamp(strOutputDateTime);
		ret.setMac(returnMac);
		log.debug("[MdpMultiIuvImpl::getIuvComplexResponse] END");

		return ret;
	}

	private IuvComplex getIuvComplex(DescrizioneApplicativoDTO descrizioneApplicativoDTO, String audDigit,
	                                 String applicationCode, String dataOdierna) throws MdpMultiIuvSrvException {

		Logger log = getLogger("ejb");
		log.debug("[MdpMultiIuvImpl::getIuvComplex] BEGIN");

		IuvComplex iuvComplex = new IuvComplex();
		String iuvOttico = "";
		String iuv = "";
		try {
			log.debug("[MdpMultiIuvImpl::getIuvComplex] inizio chiamata makeIuvOttico");
			iuvOttico = makeIuvOttico(descrizioneApplicativoDTO, audDigit, applicationCode, dataOdierna);
			log.debug("[MdpMultiIuvImpl::getIuvComplex] fine chiamata makeIuvOttico");
		}
		catch (Exception e) {
			log.error("[MdpMultiIuvImpl::getIuvComplex] Errore: ", e);
			MdpMultiIuvSrvException mie = new MdpMultiIuvSrvException(MdpMultiIuvConstants.ERRORE_CALCOLO_IUVOTTICO);
			throw new MdpMultiIuvSrvException(mie);
		}
		iuvComplex.setIuvOttico(iuvOttico);
		iuvComplex.setIuv(iuv);
		log.debug("[MdpMultiIuvImpl::getIuvComplex] END");

		return iuvComplex;
	}

	//MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
	private String makeIuvOttico(DescrizioneApplicativoDTO descrizioneApplicativoDTO, String auxDigit,
	                             String applicationCode, String dataOdierna) throws SOAPException {

		Logger log = getLogger("ejb");
	
		log.debug("[MdpMultiIuvImpl::makeIuvOttico] BEGIN");

		String ret = "";
		String progressivoOttico = Long.toString(mdpDao.getProgressivoIuv());
		StringBuilder iuvBase = new StringBuilder();
		StringBuilder baseCalcoloCheckdigit = new StringBuilder();
		
		//------------------------------------------------
        //BASE PER IL CALCOLO DEL CHECK DIGIT
        //------------------------------------------------		
		// aggiunge auxDigit
        baseCalcoloCheckdigit.append(auxDigit);
		
        if(auxDigit != null && auxDigit.equalsIgnoreCase ( "3" )) {
            // aggiunge CodiceSegregazione - A NUOVO -> AUXDIGIT a 3
            baseCalcoloCheckdigit.append(descrizioneApplicativoDTO.getCodiceSegregazione ());            
        } else {
            // aggiunge applicationId - A VECCHIO -> AUXDIGIT a 0
            baseCalcoloCheckdigit.append(applicationCode);
        }        

        //IUV BASE - DATA + PROGRESSIVO
        // aggiungo data giuliana
        baseCalcoloCheckdigit.append(Utility.getDataGiuliana(dataOdierna));
        // aggiungo progressivo ed eventuali zeri di padding
        baseCalcoloCheckdigit.append(Utility.mettiZero(progressivoOttico.toString(), 8));
        //------------------------------------------------      
        
        iuvBase.append(Utility.getDataGiuliana(dataOdierna));		
		iuvBase.append(Utility.mettiZero(progressivoOttico.toString(), 8));
		
		// uppercase della stringa
		String checkDigitUpper = baseCalcoloCheckdigit.toString().toUpperCase();
		String iuvBaseUppercase = iuvBase.toString().toUpperCase();
		
		// aggiungo controcodice (modulo 93 delle prime 16 cifre)
		
        if(auxDigit != null && auxDigit.equalsIgnoreCase ( "3" )) {//A NUOVO
            ret = ret + descrizioneApplicativoDTO.getCodiceSegregazione() + iuvBaseUppercase + Utility.mettiZero(getCheckMod(checkDigitUpper, 93), 2);
		} else {                                                   // A VECCHIO
            ret = ret +                                                     iuvBaseUppercase + Utility.mettiZero(getCheckMod(checkDigitUpper, 93), 2);
		}
		
		
		log.debug("[MdpMultiIuvImpl::makeIuvOttico] END");

		return ret;
	}
	//MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP

	private String getFieldValue(String applicationid, String gatewayid, String fieldName) throws ErrorParameterException, SOAPException {

		Logger log = getLogger("ejb");
		log.debug("[MdpMultiIuvImpl::getFieldValue] BEGIN");
		String ret = "";
		try {
			List<ApplicationcustomfieldsDTO> lstCf = getCustomFields(applicationid, gatewayid);
			for (ApplicationcustomfieldsDTO ls : lstCf) {
				if (ls.getFieldname().equals(fieldName)) {
					ret = ls.getFieldvalue();
				}
			}
		}
		catch (Exception e) {
			log.error("[MdpMultiIuvImpl::getCustomFields] Errore: ", e);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_GENERICO);
		}

		log.debug("[MdpMultiIuvImpl::getFieldValue] END");

		return ret;
	}

	private String getCheckMod(String check, int modulo) throws MdpMultiIuvSrvException {

		Logger log = getLogger("ejb");
		log.debug("[MdpMultiIuvImpl::getCheckMod] BEGIN");
		log.debug("[MdpMultiIuvImpl::getCheckMod] check : " + check);
		log.debug("[MdpMultiIuvImpl::getCheckMod] modulo : " + modulo);
		String ret = "";
		try {
			BigInteger mod = new BigInteger(String.valueOf(modulo));
			BigInteger ca = new BigInteger(check);
			BigInteger checkMod = ca.mod(mod);
			ret = checkMod.toString();
			log.debug("[MdpMultiIuvImpl::getCheckMod] return value : " + ret);
		}
		catch (NumberFormatException nfe) {
			log.error("[MdpMultiIuvImpl::getCheckMod] Errore: ", nfe);
			throw new MdpMultiIuvSrvException(MdpMultiIuvConstants.ERRORE_GENERICO);
		}
		log.debug("[MdpMultiIuvImpl::getCheckMod] END");

		return ret;
	}

	private String verificaDettaglioPagamento(String dettaglioPagamento) throws ErrorParameterException {
		Logger log = getLogger("ejb");
		log.debug("[MdpMultiIuvImpl::verificaDettaglioPagamento] - BEGIN");

		if (dettaglioPagamento.length() > 4) {
			log.error("[MdpMultiIuvImpl::verificaDettaglioPagamento] Errore: " + MdpMultiIuvConstants.ERRORE_LUNGH_DETTAGLIO_PAGAM);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_LUNGH_DETTAGLIO_PAGAM);
		}

		log.debug("Analizzo il dato " + dettaglioPagamento);
		if (!dettaglioPagamento.matches("^[a-zA-Z0-9]+$")) {
			log.error("[MdpMultiIuvImpl::verificaDettaglioPagamento] Errore: " + MdpMultiIuvConstants.ERRORE_CARAT_NO_DETTAGLIO_PAGAM);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_CARAT_NO_DETTAGLIO_PAGAM);
		}

		log.debug("[MdpMultiIuvImpl::verificaDettaglioPagamento] - END");
		return dettaglioPagamento;
	}

	// / dichiarazione del self checker (utilizzato in monitoraggio e
	// diagnostica)
	String _codS = "mdp"; // e' corretto che sia il codice prodotto?
	String _codR = "mdpmultiiuv";
	String[] _suppS = new String[] {

	};
	String[] _suppR = new String[] {

	};

	DefaultSelfChecker schk = new DefaultSelfChecker(_codS, _codR, getLogger(null).getName(), _suppS, _suppR, "/checked_resources_mdpiuv.xml");

	public boolean testResources() throws CSIException {
		Logger logger = getLogger(null);
		logger.debug("[MdpMultiIuvImpl::testResources()] BEGIN");
		try {
			logger.debug("[MdpMultiIuvImpl::testResources()] END");
			return schk.testResources();
		}
		catch (CSIException ex) {
			logger.error("[MdpMultiIuvImpl::testResources()] : si e' verificato un errore  " + ex);
			throw ex;
		}
	}

	public InvocationNode selfCheck(CalledResource[] alreadyChecked) {
		Logger logger = getLogger(null);
		logger.debug("[MdpMultiIuvImpl::selfCheck] - BEGIN");
		InvocationNode in = new InvocationNode();
		try {
			return schk.selfCheck(alreadyChecked);
		}
		catch (CSIException ex) {
			logger.error("[MdpMultiIuvImpl::selfCheck()] si e' verificato un errore  " + ex);
		}
		logger.debug("[MdpMultiIuvImpl::selfCheck] - END");
		// restituisco l?invocation node
		return in;
	}

	public boolean hasSelfCheck() {
		return true;
	}

	// / inizializzazione
	public void init(Object initOptions) {

	}

	protected Logger getLogger(String subsystem) {
		if (subsystem != null)
			return Logger.getLogger(MdpMultiIuvConstants.LOGGER_PREFIX + "." + subsystem);
		else
			return Logger.getLogger(MdpMultiIuvConstants.LOGGER_PREFIX);
	}

	public MdpDao getMdpDao() {
		return mdpDao;
	}

	public void setMdpDao(MdpDao mdpDao) {
		this.mdpDao = mdpDao;
	}

}
