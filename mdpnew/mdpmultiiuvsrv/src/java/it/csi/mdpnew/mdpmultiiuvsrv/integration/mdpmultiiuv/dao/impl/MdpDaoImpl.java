/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.integration.mdpmultiiuv.dao.impl;

import it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv.*;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.ErrorParameterException;
import it.csi.mdpnew.mdpmultiiuvsrv.exception.mdpmultiiuv.MdpMultiIuvSrvException;
import it.csi.mdpnew.mdpmultiiuvsrv.integration.mdpmultiiuv.dao.AbstractDao;
import it.csi.mdpnew.mdpmultiiuvsrv.integration.mdpmultiiuv.dao.interfacce.MdpDao;
import it.csi.mdpnew.mdpmultiiuvsrv.util.MdpMultiIuvConstants;
import it.csi.util.performance.StopWatch;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.util.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.soap.SOAPException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class MdpDaoImpl extends AbstractDao implements MdpDao {

	private PostgreSQLSequenceMaxValueIncrementer sequenceIdIuvOttici;
	private PostgreSQLSequenceMaxValueIncrementer sequenceIdIuvOtticiGen;

	/*
	 * Input: idApplication 
	 * Output: boolean
	 * Restituisce True o False in base all' id applicazione passato
	 * Verifica su tabella application
	 */
	public boolean isPresentIDApplication(String idApplication) throws SOAPException {

		log.info("[MdpDaoImpl::isPresentIDApplication] BEGIN");

		boolean ris = false;

		MapSqlParameterSource params = new MapSqlParameterSource();

		StringBuilder sb = new StringBuilder();

		sb.append("select count(*) from application where id = :idApp ");

		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {

			params.addValue("idApp", idApplication);

			log.debug("query " + sb);
			log.debug(" PARAM - idApp :: " + idApplication);

			int count = getNamedJdbcTemplate().queryForInt(sb.toString(), params);

			if (count > 0) {
				ris = true;
			}

		}
		catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::isPresentIDApplication] ERRORE", dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ACCESSO_DATI);
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "isPresentIDApplication()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::isPresentIDApplication] END");
		}

		return ris;
	}

	private String getCodiceSegregazione(DescrizioneApplicativoDTO ris) throws ErrorParameterException, SOAPException {
		String auxdigit = "";
		String csegrega;

		log.debug("CODICE SEGREGAZIONE START");
		List<GatewayDTO> lstGateway = getGatewayNodoSPC(ris.getIdApplication());

		GatewayDTO gw = null;
		if (lstGateway != null) {
			log.debug("GATEWAYS FOUND " + lstGateway.size());
			for (GatewayDTO lst : lstGateway) {
				auxdigit = getFieldValue(ris.getIdApplication(), lst.getGatewayid(), "auxDigit");
				gw = lst;
			}
		} else {
			log.debug("GATEWAYS FOUND NONE");
		}


		log.debug("CODICE SEGREGAZIONE DEFAULT " + ris.getCodiceSegregazione());

		csegrega = ris.getCodiceSegregazione();

		log.debug("AUXDIGIT IS " + auxdigit);
		log.debug("ID APPLICATION " + ris.getIdApplication());

		if (gw != null) log.debug("GATEWAY IS DESCR " + gw.getGatewaydescription());
		if (gw != null) log.debug("GATEWAY IS ID " + gw.getGatewayid());

		if ((auxdigit != null) && (gw != null)) {
			if (auxdigit.trim().equalsIgnoreCase("3")) {
				log.debug("CODICE SEGREGAZIONE DA SORGENTE NEW - TABELLA APPLICATION CUSTOM FIELDS - APPL:" + ris.getIdApplication() + " - GW:" + gw.getGatewayid());
				csegrega = getFieldValue(ris.getIdApplication(), gw.getGatewayid(), "codiceSegregazione");
			} else {
				log.debug("CODICE SEGREGAZIONE DA SORGENTE OLD - TABELLA ENTI");
			}
		} else log.debug("CODICE SEGREGAZIONE DA SORGENTE OLD - TABELLA ENTI");

		log.debug("CODICE SEGREGAZIONE STOP AS " + csegrega + " AUXDIGIT " + auxdigit);
		return csegrega;
	}

	private String getFieldValue(String applicationid, String gatewayid, String fieldName) throws ErrorParameterException {

		log.debug("[MdpIuvDaoImpl::getFieldValue] BEGIN");
		String ret = "";
		try {
			List<ApplicationcustomfieldsDTO> lstCf = getCustomFields(applicationid, gatewayid);
			for (ApplicationcustomfieldsDTO ls : lstCf) {
				if (ls.getFieldname().equals(fieldName)) {
					ret = ls.getFieldvalue();
				}
			}
		} catch (Exception e) {
			log.error("[MdpIuvDaoImpl::getFieldValue] Errore: ", e);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_GENERICO);
		}

		log.debug("[MdpIuvDaoImpl::getFieldValue] END");

		return ret;
	}

    ClassPathResource cp = new ClassPathResource("/WEB-INF/classes/env.properties");
    private final Properties envProps = new Properties();

	private void getAllConfig() throws ErrorParameterException, SOAPException {

		log.debug("[MdpIuvDaoImpl::getAllConfig] - BEGIN");
		log.debug("[MdpIuvDaoImpl::getAllConfig] - cp.getPath() " + cp.getPath());

		try {
			envProps.load(cp.getInputStream());
			List<ConfigDTO> lconf = findAllConfig();

			if (lconf != null) {
				log.debug("ITEMS IN CONFIG : " + lconf.size());
				for (ConfigDTO c : lconf) {
					if (c != null) {
						log.debug("[MdpIuvDaoImpl::getAllConfig] config:" + c);
						if (null != c.getValue()) {
							envProps.put(c.getKey(), c.getValue());
						}
					}
				}
			} else {
				log.debug("NO ITEMS FOUND IN CONFIG");
			}
		} catch (IOException ioe) {
			log.error("[MdpIuvDaoImpl::getAllConfig] Errore: ", ioe);
			throw new ErrorParameterException(MdpMultiIuvConstants.ERRORE_GENERICO);
		}
		log.debug("[MdpIuvDaoImpl::getAllConfig] - END");

	}

    private List<ApplicationcustomfieldsDTO> getCustomFields(String applicationid, String gatewayid) throws ErrorParameterException, IOException, SOAPException {

        log.debug ( "[MdpDaoImpl::getCustomFields] - START" );
        String sKey;
        List<ApplicationcustomfieldsDTO> lstCustomFields;
        FileInputStream stream = null;
        try {
            getAllConfig();

            log.debug ( "SKEY DB LOCATION : " + envProps.getProperty("sKeyDb"));
            
            stream = new FileInputStream(envProps.getProperty("sKeyDb"));
            int len = stream.available();
            byte[] b = new byte[len];
            stream.read(b);
            b = Base64.decode(new String(b));
            sKey = new String(b);
            stream.close();
        
            log.debug ( "SKEY DB IS : " + sKey);
            
            lstCustomFields = getApplicationcustomfields(applicationid, gatewayid, sKey);
        }
        catch (FileNotFoundException e) {
            log.error("[MdpIuvDaoImpl::getCustomFields] Error: ", e);
	        throw new ErrorParameterException ( MdpMultiIuvConstants.ERRORE_KEYDB );
        }
        catch (IOException ioe) {
            stream.close();
            log.error("[MdpIuvDaoImpl::getCustomFields] Errore: ", ioe);
	        throw new ErrorParameterException ( MdpMultiIuvConstants.ERRORE_GENERICO );
        }
        finally {
	        if (null != stream) {
		        stream.close();
	        }
            log.debug("[MdpIuvDaoImpl::getCustomFields] - END");
        }
        
        return lstCustomFields;    
    }    
    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP
    
	/*
	 * Input: idApplication 
	 * Output: DescrizioneApplicativoDTO
	 * Restituisce oggetto DescrizioneApplicativoDTO
	 * Accesso su tabelle enti e application
	 */

	public DescrizioneApplicativoDTO isPresentIDEnte(String idApplication) throws SOAPException {

		log.info("[MdpDaoImpl::isPresentIDEnte] BEGIN");

		DescrizioneApplicativoDTO ris;
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT descrizione, partita_iva, applicationname, ").append("id, referentecsi, cliente, e.ente_id idente , codice_segregazione ")
				.append("from enti e, application a, r_application_enti rae ").append("where ").append("rae.application_id = a.id ")
				.append("and rae.ente_id = e.ente_id ").append("and a.id = :idApp ");

		StopWatch watcher = new StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {

			params.addValue("idApp", idApplication);

			log.debug("query " + sb);
			log.debug(" PARAM - idApp :: " + idApplication);

			@SuppressWarnings("unchecked")
			List<DescrizioneApplicativoDTO> lista = getNamedJdbcTemplate().query(sb.toString(), params, new EnteIDApplicationDTOMapper());

			if (null == lista || lista.size() == 0) {
				log.error("[MdpDaoImpl::isPresentIDEnte] Nessun dato recuperato");
				throw new MdpMultiIuvSrvException("[MdpDaoImpl::isPresentIDEnte] Nessun dato recuperato");
			} else {
				// prendo sempre il primo elemento
				ris = lista.get(0);
			}

			//MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
			ris.setCodiceSegregazione(getCodiceSegregazione(ris));
			//MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP

		} catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::isPresentIDEnte] ERROR", dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ACCESSO_DATI);
		} catch (ErrorParameterException dax) {
			log.error("[MdpDaoImpl::isPresentIDEnte] ERROR", dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ACCESSO_DATI);
		} finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "isPresentIDEnte()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::isPresentIDEnte] END");
		}

		return ris;

	}

	private static class EnteIDApplicationDTOMapper implements ParameterizedRowMapper<Object> {

		public DescrizioneApplicativoDTO mapRow(ResultSet rs, int row) throws SQLException {

			DescrizioneApplicativoDTO d = new DescrizioneApplicativoDTO();

			d.setDescrizione(rs.getString("descrizione"));
			d.setPartitaIva(rs.getString("partita_iva"));
			d.setApplicationName(rs.getString("applicationname"));
			d.setIdApplication(rs.getString("id"));
			d.setReferenteCsi(rs.getString("referentecsi"));
			d.setCliente(rs.getString("cliente"));
			d.setIdEnte(rs.getString("idente"));
			d.setCodiceSegregazione(rs.getString("codice_segregazione"));
			return d;
		}
	}

	/*
	 * Input: --
	 * Output: List<ConfigDTO>
	 * Restituisce una lista di oggetti ConfigDTO
	 * Lista di parametri applicativi globali
	 * Accesso su tabella config
	 */
	@SuppressWarnings("unchecked")
	public List<ConfigDTO> findAllConfig() throws SOAPException {

		log.info("[MdpDaoImpl::findAllConfig] BEGIN");
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT key, value, descrizione FROM config ");

		StopWatch watcher = new StopWatch(LOGGER_PREFIX);
		watcher.start();

		try {
			log.debug("query " + sb);
			MapSqlParameterSource params = new MapSqlParameterSource();
			return (List<ConfigDTO>) getNamedJdbcTemplate().query(sb.toString(), params, new ConfigDTOMapper());
		}
		catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::findAllConfig] ERROR", dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ACCESSO_DATI);
		}		
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "findAllConfig()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::findAllConfig] END");
		}
	}

	private static class ConfigDTOMapper implements ParameterizedRowMapper<Object> {

		public ConfigDTO mapRow(ResultSet rs, int row) throws SQLException {

			ConfigDTO d = new ConfigDTO();

			d.setKey(rs.getString("key"));
			d.setValue(rs.getString("value"));
			d.setDescrizione(rs.getString("descrizione"));

			return d;
		}
	}

	/*
	 * Input: applicationid, gatewayid, sKey 
	 * Output: List<ApplicationcustomfieldsDTO>
	 * Restituisce una lista di oggetti ApplicationcustomfieldsDTO
	 * Lista di parametri applicativi
	 * Accesso su tabella applicationcustomfields
	 */	
	@SuppressWarnings("unchecked")
	public List<ApplicationcustomfieldsDTO> getApplicationcustomfields(String applicationid, String gatewayid, String sKey) throws SOAPException {

		log.info("[MdpDaoImpl::getApplicationcustomfields] BEGIN");
		List<ApplicationcustomfieldsDTO> ret;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription " + "FROM applicationcustomfields "
				+ "WHERE applicationid = :applicationid AND gateway_id =:gateway_id ORDER BY applicationid");

		StopWatch watcher = new StopWatch(LOGGER_PREFIX);
		watcher.start();

		try {
			log.debug("getApplicationcustomfields query " + sb);
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("applicationid", applicationid);
			params.addValue("gateway_id", gatewayid);
			ret = (List<ApplicationcustomfieldsDTO>) getNamedJdbcTemplate().query(sb.toString(), params, new ApplicationcustomfieldsDTOMapper(sKey));
		}
		catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::getApplicationcustomfields] ERROR", dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ACCESSO_DATI);
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "getApplicationcustomfields()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::getApplicationcustomfields] END");
		}
		return ret;
	}

	private static class ApplicationcustomfieldsDTOMapper implements ParameterizedRowMapper<Object> {

		String sKey;

		private ApplicationcustomfieldsDTOMapper(String sKey) {
			this.sKey = sKey;
		}

		public ApplicationcustomfieldsDTO mapRow(ResultSet rs, int row) throws SQLException {

			ApplicationcustomfieldsDTO d = new ApplicationcustomfieldsDTO();
			d.setKeyid(rs.getString("keyid"));
			d.setApplicationid(rs.getString("applicationid"));
			d.setFieldname(rs.getString("fieldname"));
			d.setFieldvalue(rs.getString("fieldvalue"));
			d.setGatewayId(rs.getString("gateway_id"));
			d.setFielddescription(rs.getString("fielddescription"));

			if (sKey != null && rs.getString(4) != null) {
				byte[] raw = sKey.getBytes();
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

				byte[] original;
				byte[] encrypted = rs.getString(4).getBytes();
				try {
					encrypted = Base64.decode(new String(encrypted));
					Cipher cipher = Cipher.getInstance("AES");

					cipher.init(Cipher.DECRYPT_MODE, skeySpec);
					original = cipher.doFinal(encrypted);
					String originalString = new String(original);
					log.debug("Original string: " + originalString + " " + asHex(original));
					d.setFieldvalue(new String(original));
				}
				catch (InvalidKeyException e) {
					log.error("[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e);
				}
				catch (NoSuchAlgorithmException e) {
					log.error("[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e);
				}
				catch (NoSuchPaddingException e) {
					log.error("[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e);
				}
				catch (IllegalBlockSizeException e) {
					log.error("[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e);
				}
				catch (BadPaddingException e) {
					log.error("[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e);
				}
				catch (WSSecurityException e) {
					log.error("[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e);
				}
			}

			return d;
		}
	}

	private static String asHex(byte[] buf) {
		StringBuilder strbuf = new StringBuilder(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/*
	 * Input: --
	 * Output: List<GatewayDTO>
	 * Restituisce una lista di oggetti GatewayDTO
	 * Lista proprietà oggetti gateway
	 * Accesso su tabella gateway
	 */		
	@SuppressWarnings("unchecked")
	public List<GatewayDTO> getGatewayNodoSPC(String idApplication) throws SOAPException {

		log.info("[MdpDaoImpl::getGatewayNodoSPC] BEGIN");
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT g.gateway_id, g.gateway_description, g.gateway_provider, g.valido_dal, g.valido_al, g.gatewayservicename, g.flag_nodo "
				+ "  FROM gateway g, applicationdetail  ad WHERE ad.gatewayid = g.gateway_id AND flag_nodo = true AND ad.enabled = '1' and ad.applicationid = :appid  AND (valido_al is null OR valido_al > now())");

		StopWatch watcher = new StopWatch(LOGGER_PREFIX);
		watcher.start();

		try {
			log.debug("query " + sb);
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("appid", idApplication);
			return (List<GatewayDTO>) getNamedJdbcTemplate().query(sb.toString(), params, new GatewayDTOMapper());
		}
		catch (DataAccessException dax) {
			dax.printStackTrace();
			log.error("[MdpDaoImpl::getGatewayNodoSPC] ERROR", dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ACCESSO_DATI);
		}		
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "getGatewayNodoSPC()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::getGatewayNodoSPC] END");
		}
	}

	private static class GatewayDTOMapper implements ParameterizedRowMapper<Object> {

		public GatewayDTO mapRow(ResultSet rs, int row) throws SQLException {

			GatewayDTO d = new GatewayDTO();

			d.setGatewayid(rs.getString("gateway_id"));
			d.setGatewaydescription(rs.getString("gateway_description"));
			d.setGatewayprovider(rs.getString("gateway_provider"));
			d.setValidodal(rs.getTimestamp("valido_dal"));
			d.setValidoal(rs.getTimestamp("valido_al"));
			d.setGatewayservicename(rs.getString("gatewayservicename"));
			d.setFlagnodo(rs.getBoolean("flag_nodo"));

			return d;
		}
	}

	/*
	 * Input: IuvComplexResponse iuvCplx, String ente_id
	 * Output: int
	 * Effefttua insert massiva su tabella IUV_OTTICI
	 * restituisce il numero di record inseriti
	 */	
	public int[] insertMassiveIuvOttici(final IuvComplexResponse iuvCplx, final String ente_id) throws SOAPException{

		log.info("[MdpDaoImpl::insertMassiveIuvOttici] BEGIN");

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO iuv_ottici(id,data_creazione,iuv_ottico,iuv_standard,ente_id,cod_versamento,application_id,data_riconciliazione) ")
				.append("VALUES (?,now(),?,?,?,?,?,null) ");
		StopWatch watcher = new StopWatch(LOGGER_PREFIX);
		watcher.start();

		try {
			final List<IuvComplex> iuvComplex = iuvCplx.getIuvComplex();

			return getJdbcTemplate().batchUpdate(sb.toString(), new BatchPreparedStatementSetter() {

				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setLong(1, getSequenceIdIuvOttici().nextLongValue());
					ps.setString(2, iuvComplex.get(i).getIuvOttico());
					ps.setString(3, iuvComplex.get(i).getIuv());
					ps.setString(4, ente_id);
					ps.setString(5, iuvCplx.getCodiceVersamento());
					ps.setString(6, iuvCplx.getApplicationID());
				}

				public int getBatchSize() {
					return iuvComplex.size();
				}
			});

		}
		catch (DataIntegrityViolationException e){
			log.error("[MdpDaoImpl::insertMassiveIuvOttici] Errore ",e);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_UNIQUE_MULTI_IUV);
		}		
		catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::insertMassiveIuvOttici] Errore ",dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_INSERIMENTO_IUV);
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "insertMassiveIuvOttici()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::insertMassiveIuvOttici] END");
		}
	}

	/*
	 * Input: --
	 * Output: Long
	 * Stacca un nuovo numero dalla sequence iuv_ottici_keyid_seq_gen
	 */	
	public Long getProgressivoIuv() throws SOAPException {
		log.info("[MdpDaoImpl::getProgressivoIuv] BEGIN");
		long ret;
		StopWatch watcher = new StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {
			ret = getSequenceIdIuvOtticiGen().nextLongValue();
		}
		catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::getProgressivoIuv] ERROR", dax);
			throw new SOAPException(MdpMultiIuvConstants.ERRORE_ACCESSO_DATI);
		}		
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "getProgressivoIuv()", "sequence", "(valore input omesso)");
			log.info("[MdpDaoImpl::getProgressivoIuv] END");
		}
		return ret;
	}

	public PostgreSQLSequenceMaxValueIncrementer getSequenceIdIuvOttici() {
		return sequenceIdIuvOttici;
	}

	public void setSequenceIdIuvOttici(PostgreSQLSequenceMaxValueIncrementer sequenceIdIuvOttici) {
		this.sequenceIdIuvOttici = sequenceIdIuvOttici;
	}

	public PostgreSQLSequenceMaxValueIncrementer getSequenceIdIuvOtticiGen() {
		return sequenceIdIuvOtticiGen;
	}

	public void setSequenceIdIuvOtticiGen(PostgreSQLSequenceMaxValueIncrementer sequenceIdIuvOtticiGen) {
		this.sequenceIdIuvOtticiGen = sequenceIdIuvOtticiGen;
	}
}
