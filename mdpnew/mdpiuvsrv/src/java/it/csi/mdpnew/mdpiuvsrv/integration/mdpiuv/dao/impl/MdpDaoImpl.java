/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.integration.mdpiuv.dao.impl;


import it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv.*;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.ErrorParameterException;
import it.csi.mdpnew.mdpiuvsrv.exception.mdpiuv.MdpIuvSrvException;
import it.csi.mdpnew.mdpiuvsrv.integration.mdpiuv.dao.AbstractDao;
import it.csi.mdpnew.mdpiuvsrv.integration.mdpiuv.dao.interfacce.MdpDao;
import it.csi.mdpnew.mdpiuvsrv.util.MdpiuvConstants;
import it.csi.util.performance.StopWatch;
import org.apache.commons.lang.StringUtils;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.util.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.soap.SOAPException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class MdpDaoImpl extends AbstractDao implements MdpDao   {
	
	
	public String dataOdierna() {
		
		log.info("[MdpDaoImpl::dataOdierna] BEGIN");
		String dataDiOggi = "";
		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
			
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select to_char(CURRENT_TIMESTAMP,'dd/MM/YYYY') from dual ");
		try {
			
			log.debug("query vData odierna  " + sqlQuery);
						
			dataDiOggi = (String)getNamedJdbcTemplate().queryForObject(sqlQuery.toString(),params ,java.lang.String.class);
			
			log.debug("data odierna  " + dataDiOggi);
			
		} catch (DataAccessException dax) {
			throw new MdpIuvSrvException(dax.getMessage());
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "dataOdierna()", "invocazione query " + sqlQuery, "(valore input omesso)");
			log.info("[MdpDaoImpl::dataOdierna] END");
		}
		
		
		return dataDiOggi;
	}

	
	public boolean isPresentIDApplication(String idApplication) {
		
		log.info("[MdpDaoImpl::isPresentIDApplication] BEGIN");
		
		boolean ris = false;
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select count(*) from application where id = :idApp ");
		
		
		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {
			
			params.addValue("idApp", idApplication);
			
			log.debug("query "+sb);
			log.debug(" PARAM - idApp :: "+idApplication);
			
			int count = getNamedJdbcTemplate().queryForInt(sb.toString(), params);
			
			if(count>0) ris = true;
			
		} catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::isPresentIDApplication] ERRORE", dax);
			throw new MdpIuvSrvException(dax.getMessage());
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "isPresentIDApplication()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::isPresentIDApplication] END");
		}
		
		
		return ris;
	}
	


	public String getAuxDigit(String idApp) throws ErrorParameterException, SOAPException {
		List<GatewayDTO> lstGateway = getGatewayNodoSPC(idApp);

		String auxDigit = "";
		if (lstGateway != null) {
			log.debug("GATEWAYS FOUND " + lstGateway.size());
			for (GatewayDTO lst : lstGateway) {
				auxDigit = getFieldValue(idApp, lst.getGatewayid(), "auxDigit");
			}
		}
		else {
			log.debug("GATEWAYS FOUND NONE");
		}

		return auxDigit;
	}

	private String getCodiceSegregazione(DescrizioneApplicativoDTO ris) throws ErrorParameterException, SOAPException {
		String csegrega = "";

		log.debug("CODICE SEGREGAZIONE START");
		List<GatewayDTO> lstGateway = getGatewayNodoSPC(ris.getIdApplication());

		GatewayDTO gw = null;
		if (lstGateway != null) {
			log.debug("GATEWAYS FOUND " + lstGateway.size());
			for (GatewayDTO lst : lstGateway) {
				gw = lst;
			}
		}
		else {
			log.debug("GATEWAYS FOUND NONE");
		}

		log.debug("CODICE SEGREGAZIONE DEFAULT " + ris.getCodiceSegregazione());

		csegrega = ris.getCodiceSegregazione();

		String auxDigit = getAuxDigit(ris.getIdApplication());

		log.debug("AUXDIGIT IS " + auxDigit);
		log.debug("ID APPLICATION " + ris.getIdApplication());

		if (gw != null) log.debug("GATEWAY IS DESCR " + gw.getGatewaydescription());
		if (gw != null) log.debug("GATEWAY IS ID " + gw.getGatewayid());

		if ((auxDigit != null) && (gw != null)) {
			if (auxDigit.trim().equalsIgnoreCase("3")) {
				log.debug("CODICE SEGREGAZIONE DA SORGENTE NEW - TABELLA APPLICATION CUSTOM FIELDS - APPL:" + ris.getIdApplication() + " - GW:" + gw.getGatewayid());
				csegrega = getFieldValue(ris.getIdApplication(), gw.getGatewayid(), "codiceSegregazione");
			} else {
				log.debug("CODICE SEGREGAZIONE DA SORGENTE OLD - TABELLA ENTI");
			}
		} else log.debug("CODICE SEGREGAZIONE DA SORGENTE OLD - TABELLA ENTI");

		log.debug("CODICE SEGREGAZIONE STOP AS " + csegrega + " AUXDIGIT " + auxDigit);
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
			throw new ErrorParameterException(MdpiuvConstants.ERRORE_GENERICO);
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

			if ( lconf != null ) {
				log.debug ( "ITEMS IN CONFIG : " + lconf.size () );
				for ( ConfigDTO c : lconf ) {
					if ( null != c ) {
						log.debug ( "[MdpIuvDaoImpl::getAllConfig] config:" + c );
						if ( null != c.getValue () ) {
							envProps.put ( c.getKey (), c.getValue () );
						}
					}

				}
			} else {
				log.debug("NO ITEMS FOUND IN CONFIG");
			}
		} catch (IOException ioe) {
			log.error("[MdpIuvDaoImpl::getAllConfig] Errore: ", ioe);
			throw new ErrorParameterException(MdpiuvConstants.ERRORE_GENERICO);
		}
		log.debug("[MdpIuvDaoImpl::getAllConfig] - END");
	}

	private List<ApplicationcustomfieldsDTO> getCustomFields(String applicationid, String gatewayid) throws ErrorParameterException, IOException, SOAPException {
		log.debug("[MdpDaoImpl::getCustomFields] - START");
		String sKey;
		List<ApplicationcustomfieldsDTO> lstCustomFields;
		FileInputStream stream = null;
		try {
			getAllConfig();

			log.debug("SKEY DB LOCATION : " + envProps.getProperty("sKeyDb")); //TODOPPU Da ripristinare a solo sKeyDb ! ! ! ! ! !

			stream = new FileInputStream(envProps.getProperty("sKeyDb")); //TODOPPU Da ripristinare a solo sKeyDb ! ! ! ! ! !
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
			stream.close();

			log.debug("SKEY DB IS : " + sKey);

			lstCustomFields = getApplicationcustomfields(applicationid, gatewayid, sKey);
		} catch (FileNotFoundException e) {
			log.error("[MdpIuvDaoImpl::getCustomFields] Error: ", e);
			throw new ErrorParameterException(MdpiuvConstants.ERRORE_KEYDB);
		} catch (IOException ioe) {
			stream.close();
			log.error("[MdpIuvDaoImpl::getCustomFields] Errore: ", ioe);
			throw new ErrorParameterException(MdpiuvConstants.ERRORE_GENERICO);
		} finally {
			assert stream != null;
			stream.close();
			log.debug("[MdpIuvDaoImpl::getCustomFields] - END");
		}

		return lstCustomFields;
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
            throw new SOAPException(MdpiuvConstants.ERRORE_ACCESSO_DATI);
		}		
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "findAllConfig()", "invocazione query " + sb.toString(), "(valore input omesso)");
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
		List<ApplicationcustomfieldsDTO> ret = new ArrayList<ApplicationcustomfieldsDTO>();
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
            throw new SOAPException(MdpiuvConstants.ERRORE_ACCESSO_DATI);
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "getApplicationcustomfields()", "invocazione query " + sb.toString(), "(valore input omesso)");
			log.info("[MdpDaoImpl::getApplicationcustomfields] END");
		}
		return ret;
	}

	private static class ApplicationcustomfieldsDTOMapper implements ParameterizedRowMapper<Object> {

		String sKey = null;

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

				byte[] original = null;
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
	 * Lista proprieta' oggetti gateway
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
            throw new SOAPException(MdpiuvConstants.ERRORE_ACCESSO_DATI);
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
    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP
    
	public DescrizioneApplicativoDTO isPresentIDEnte(String idApplication)  {
		
		log.info("[MdpDaoImpl::isPresentIDEnte] BEGIN");
		
		DescrizioneApplicativoDTO ris;
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT descrizione, partita_iva, applicationname, ")
		.append("id, referentecsi, cliente, e.ente_id idente, codice_segregazione ")
		.append("from enti e, application a, r_application_enti rae ")
		.append("where ")
		.append("rae.application_id = a.id ")
		.append("and rae.ente_id = e.ente_id ")
		.append("and a.id = :idApp ");
		
		
		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {
			
			params.addValue("idApp", idApplication);
			
			log.debug("query "+sb);
			log.debug(" PARAM - idApp :: "+idApplication);
			
			List<DescrizioneApplicativoDTO> lista =
					getNamedJdbcTemplate().query(sb.toString(), 
											     params, 
											     new EnteIDApplicationDTOMapper());
			
			
			if(null==lista || lista.size()==0){
				
				log.error("[MdpDaoImpl::isPresentIDEnte] Nessun dato recuperato");
				throw new MdpIuvSrvException("[MdpDaoImpl::isPresentIDEnte] Nessun dato recuperato");
			} else {
				
				// prendo sempre il primo elemento
				ris = lista.get(0);
				
		        //MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
				log.debug ( "GENERAZIONE IUV START" );
		        ris.setCodiceSegregazione ( getCodiceSegregazione ( ris) );
                log.debug ( "GENERAZIONE IUV STOP" );
		        //MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP		        
			}
		
		} catch (DataAccessException dax) {
			log.error("[MdpDaoImpl::isPresentIDEnte] ERROR(data)", dax);
			throw new MdpIuvSrvException(dax.getMessage());
        } catch (ErrorParameterException dax) {
            log.error("[MdpDaoImpl::isPresentIDEnte] ERROR(param)", dax);
            throw new MdpIuvSrvException(dax.getMessage());
		} catch (SOAPException dax) {
            log.error("[MdpDaoImpl::isPresentIDEnte] ERROR(soap)", dax);
            throw new MdpIuvSrvException(dax.getMessage());
        }
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "isPresentIDEnte()", "invocazione query "+sb.toString(), "(valore input omesso)");
			log.info("[MdpDaoImpl::isPresentIDEnte] END");
		}
		
		return ris;		
	}
	
	private static class EnteIDApplicationDTOMapper implements ParameterizedRowMapper{

    	public DescrizioneApplicativoDTO mapRow(ResultSet rs, int row) throws SQLException{
			
    		DescrizioneApplicativoDTO d = new DescrizioneApplicativoDTO();
    		
    		
    		d.setDescrizione(rs.getString("descrizione"));
    		d.setPartitaIva(rs.getString("partita_iva"));
    		d.setApplicationName(rs.getString("applicationname"));
    		d.setIdApplication(rs.getString("id"));
    		d.setReferenteCsi(rs.getString("referentecsi"));
    		d.setCliente(rs.getString("cliente"));
			d.setIdEnte(rs.getString("idente"));
			d.setCodiceSegregazione(StringUtils.trimToEmpty(rs.getString("codice_segregazione"))); 
			return d;
		}
	}
	
	
	public IuvAttributeDTO getIuvCorrente(String idEnte){
		
		log.info("[MdpDaoImpl::getIuvCorrente] BEGIN");
		
		IuvAttributeDTO ris = null;
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		
		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {
			sb.append("select ente_id, to_char(data_validita, 'dd/MM/YYYY') dataValidita , progressivo, ")
			.append("data_dismissione from iuv_attribute where ")
			.append("to_char(CURRENT_TIMESTAMP,'dd/MM/YYYY') = to_char(data_validita, 'dd/MM/YYYY') ")
			.append("and ente_id = :idEnte and data_dismissione is null ");
			
			params.addValue("idEnte", idEnte);
			
			log.debug("query "+sb);
			log.debug(" PARAM - idEnte :: "+idEnte);
			
			List<IuvAttributeDTO> lista = 
					getNamedJdbcTemplate().query(sb.toString(), 
											     params, 
											     new IuvAttributeDTOMapper());
			
			ris = new IuvAttributeDTO();
			
			if(null==lista || lista.size()==0){
				log.info("[MdpDaoImpl::getIuvCorrente] Nessun elemento trovato nella tavola IUV_ATTRIBUTE");
				return null;
				//throw new MdpIuvSrvException("Nessun elemento trovato nella tavola IUV_ATTRIBUTE");
			}
			
			// prendo sempre il primo e unico elemento
			ris = lista.get(0);
			
			
		} catch (DataAccessException dax) {
			log.error("Errore getIuvCorrente "+dax.getMessage());
					
			throw new MdpIuvSrvException(dax.getMessage());
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "getIuvCorrente()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::getIuvCorrente] END");
		}
		
		return ris;
		
	}
	
	
	public void insertNewDay(String idEnte)
	{
		log.info("[MdpDaoImpl::insertNewDay] BEGIN");

		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO iuv_attribute ( ENTE_ID, data_validita, progressivo ) VALUES")
		.append(" ( :idEnte, CURRENT_TIMESTAMP, '1') ");
		
		log.debug(" query di inserimento : "+sb);
		log.debug("PARAM - idEnte "+idEnte);
		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {
		
			params.addValue("idEnte", idEnte);
			
			getNamedJdbcTemplate().update(sb.toString(), params);
			
		} catch (DataAccessException dax) {
			throw new MdpIuvSrvException(dax.getMessage());
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "insertNewDay()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::insertNewDay] END");
		}
		
	}
	
	private static class IuvAttributeDTOMapper implements ParameterizedRowMapper{

    	public IuvAttributeDTO mapRow(ResultSet rs, int row) throws SQLException{
			
    		IuvAttributeDTO d = new IuvAttributeDTO();
    		
    		
    		d.setIdEnte(rs.getString("ente_id"));
    		d.setDataValidita(rs.getString("dataValidita"));
    		d.setProgressivo(new BigInteger(rs.getString("progressivo")));
    		d.setDataDismissione(rs.getString("data_dismissione"));
    	
			return d;
		}
	}
	
	public void updateIncremento(String idEnte, BigInteger progressivo, String data)
	{
		log.info("[MdpDaoImpl::updateIncremento] BEGIN");
		
		IuvAttributeDTO ris = null;
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE iuv_attribute SET ")
		.append("progressivo = :progressivo ")
		.append("WHERE ente_id = :idEnte AND ")
		.append("to_char(data_validita, 'dd/MM/YYYY') = :data ");
		
		log.debug(" query di update : "+sb);
		log.debug("PARAM - idEnte "+idEnte);
		log.debug("PARAM - progressivo "+progressivo);
		log.debug("PARAM - data "+data);
		
		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {
		
			params.addValue("idEnte", idEnte);
			params.addValue("progressivo", progressivo, Types.BIGINT);
			params.addValue("data", data);
			
			getNamedJdbcTemplate().update(sb.toString(), params);
			
		} catch (DataAccessException dax) {
			throw new MdpIuvSrvException(dax.getMessage());
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "updateIncremento()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::updateIncremento] END");
		}
		
	}
	
	public void updateStoricizza(String idEnte)
	{
		log.info("[MdpDaoImpl::updateStoricizza] BEGIN");
				
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE iuv_attribute SET data_dismissione = CURRENT_DATE ")
		.append("where ente_id = :idEnte and ")
		.append("to_char(data_validita, 'dd/MM/YYYY') = (select to_char(max(a.data_validita), 'dd/MM/YYYY') ")
		.append("from iuv_attribute a ")
		.append("where a.ente_id = :idEnte ")
		.append("and a.data_validita < CURRENT_DATE) ");
		
		log.debug(" query di update : "+sb);
		log.debug("PARAM - idEnte "+idEnte);
		it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(LOGGER_PREFIX);
		watcher.start();
		try {
		
			params.addValue("idEnte", idEnte);
			
			getNamedJdbcTemplate().update(sb.toString(), params);
			
		} catch (DataAccessException dax) {
			throw new MdpIuvSrvException(dax.getMessage());
		}
		finally {
			watcher.stop();
			watcher.dumpElapsed("MdpDaoImpl", "updateStoricizza()", "invocazione query " + sb, "(valore input omesso)");
			log.info("[MdpDaoImpl::updateStoricizza] END");
		}
		
	}
	
}
