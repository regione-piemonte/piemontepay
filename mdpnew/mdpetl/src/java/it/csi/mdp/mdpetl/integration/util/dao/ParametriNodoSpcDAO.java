/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.apache.ws.security.util.Base64;

import it.csi.mdp.mdpetl.dto.ParametroNodo;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManager;
import it.csi.mdp.mdpetl.integration.util.connection.ConnectionManagerFactory;
import it.csi.mdp.mdpetl.util.LogUtil;


public class ParametriNodoSpcDAO {
	LogUtil log = new LogUtil(ParametriNodoSpcDAO.class);

	private byte[] sKey = null;

	public ParametriNodoSpcDAO(byte[] sKey) {
		this.sKey = sKey;
	}

	/**
	 * Reperisce i parametri dinamici per l'invocazione dei servizi del nodo SPC
	 * @return
	 * @throws Exception
	 */
	public Set<ParametroNodo> findDistinctDomini() throws Exception {
		String METHOD_NAME = "findDistinctDomini";

		log.info(METHOD_NAME,super.getClass().getSimpleName());

		Connection conn = null;
		PreparedStatement selApp = null;
		PreparedStatement selFields = null;
		ResultSet rsApp = null;
		ResultSet rsFields = null;

		try {

			log.debug(METHOD_NAME, "Ottengo la connessione");
			conn = ConnectionManagerFactory.getInstance().getConnection();

			log.debug(METHOD_NAME, "Connessione ottenuta, creo lo statement applicazioni");
			// RDI-16: modificata query per considerare solo le applicazioni con Nodospc v.2 e attive
			selApp = conn.prepareStatement("select distinct c.applicationid from applicationcustomfields c, applicationdetail d, gateway g "
					+ "where d.applicationid = c.applicationid and d.gatewayid = g.gateway_id and c.gateway_id = g.gateway_id "
					+ "and d.enabled = '1' and c.fieldname = 'identificativointermediarioPA' "
					+ "and c.fieldvalue is not null ");

			log.info(METHOD_NAME, "Statement ottenuto, eseguo la query applicazioni");
			List<String> listAppId = new ArrayList<String>();
			rsApp = selApp.executeQuery();

			while (rsApp.next()) {
				listAppId.add(rsApp.getString(1));
			}

			log.info(METHOD_NAME, "applicazioni rintracciate, creo lo statement per i parametri");

			StringBuilder inClauseApp = new StringBuilder("");
			if (listAppId.size() > 0) {
				for (int i=0; i<listAppId.size(); i++) {
					inClauseApp.append("'").append(listAppId.get(i)).append("'");
					if (!(i==(listAppId.size() -1))) {
						inClauseApp.append(",");
					}
				}
			}
			
			// RDI-16: aggiunta keyid per cambiare criterio di ordinamento - aggiunta join a.gateway_id = g.gateway_id
			String sql = "select a.applicationid,a.fieldname, a.fieldvalue, a.keyid from applicationcustomfields a, applicationdetail ad, gateway g WHERE a.applicationid = ad.applicationid "
					+ "AND ad.gatewayid = a.gateway_id "
					+ "and ad.gatewayid = g.gateway_id  "
					+ "and g.flag_nodo is true and enabled = '1' and a.applicationid in (" + inClauseApp + ") and trim(fieldname) "
					+ "in ('identificativoDominio','identificativointermediarioPA','identificativoStazioneIntermediarioPA',"
					+ "'passwordDominioNodoSpc','portaDiDominio') order by 4 ";
			log.info("findDistinctDomini", sql);

			selFields = conn.prepareStatement(sql);
			rsFields = selFields.executeQuery();

			log.debug(METHOD_NAME, "Query patrametri eseguita, ciclo");

			String oldAppId = "";
			List<ParametroNodo> elencoProvvisorio = new ArrayList<ParametroNodo>();
			Set<ParametroNodo> elenco = new HashSet<ParametroNodo>();
			ParametroNodo parametroNodo = null;

			while (rsFields.next()) {
				String appId = rsFields.getString(1);
				if (!appId.equals(oldAppId)) {
					oldAppId = appId;
					parametroNodo = new ParametroNodo();
					elencoProvvisorio.add(parametroNodo);
				}
				String fieldName = rsFields.getString("fieldname").trim();
				String fieldvalue = decifraFieldValue(rsFields.getString("fieldvalue"));


				if("identificativoDominio".equals(fieldName)) {
                    parametroNodo.setIdentificativoDominio(fieldvalue);
                } else if("identificativointermediarioPA".equals(fieldName)) {
                    parametroNodo.setIdentificativointermediarioPA(fieldvalue);
                } else if("identificativoStazioneIntermediarioPA".equals(fieldName)) {
                    parametroNodo.setIdentificativoStazioneIntermediarioPA(fieldvalue);
                } else if ("passwordDominioNodoSpc".equals(fieldName)) {
                    parametroNodo.setPasswordDominioNodoSpc(fieldvalue);
                } else if ("portaDiDominio".equals(fieldName)) {
                    parametroNodo.setPortaDiDominio(fieldvalue);
                } else if ("gadEnable".equals(fieldName)){
                	parametroNodo.setGadEnabled("true".equalsIgnoreCase(fieldvalue));
                }
				
			}
			for (ParametroNodo elem : elencoProvvisorio) {
				if (elem != null && !dominioPresente(elenco, elem)) {
                    elenco.add(elem);
                }
			}

			log.info(METHOD_NAME, "parametri popolati, fine elaborazione: " +elenco.size());
			return elenco;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "SQLException", e);
			throw e;
		}  catch (NamingException e) {
			e.printStackTrace();
			log.error(METHOD_NAME, "NamingException", e);
			throw e;
		} catch (Exception e) {

			e.printStackTrace();
			log.error(METHOD_NAME, "Exception", e);
			throw e;
		} finally {

			ConnectionManager.closeResultSet(rsApp);
			ConnectionManager.closeResultSet(rsFields);
			ConnectionManager.closeStatement(selApp);
			ConnectionManager.closeStatement(selFields);
			ConnectionManager.closeConnection(conn);
		}
	 }

    /**
     * Reperisce i parametri dinamici per l'invocazione dei servizi del nodo SPC
     *
     * @return
     * @throws Exception
     */
    public Set<ParametroNodo> findDistinctDominiV2 () throws Exception {
        String METHOD_NAME = "findDistinctDominiV2";

        log.info ( METHOD_NAME, super.getClass ().getSimpleName () );

        Connection conn = null;
        PreparedStatement selApp = null;
        PreparedStatement selFields = null;
        ResultSet rsApp = null;
        ResultSet rsFields = null;

        try {

            log.debug ( METHOD_NAME, "Ottengo la connessione" );
            conn = ConnectionManagerFactory.getInstance ().getConnection ();

            log.debug ( METHOD_NAME, "Connessione ottenuta, creo lo statement applicazioni" );
            selApp = conn.prepareStatement ( "select distinct applicationid from applicationcustomfields where fieldname = 'identificativointermediarioPA'" );

            log.info ( METHOD_NAME, "Statement ottenuto, eseguo la query applicazioni" );
            List<String> listAppId = new ArrayList<String> ();
            rsApp = selApp.executeQuery ();

            while ( rsApp.next () ) {
                listAppId.add ( rsApp.getString ( 1 ) );
            }

            log.info ( METHOD_NAME, "applicazioni rintracciate, creo lo statement per i parametri" );

            StringBuilder inClauseApp = new StringBuilder ( "" );
            if ( listAppId.size () > 0 ) {
                for ( int i = 0; i < listAppId.size (); i++ ) {
                    inClauseApp.append ( "'" ).append ( listAppId.get ( i ) ).append ( "'" );
                    if ( ! ( i == ( listAppId.size () - 1 ) ) ) {
                        inClauseApp.append ( "," );
                    }
                }
            }
            // MDPNEW-85: aggiunta distinct per eliminare doppie occorrenze (nodospc / nodospc v2)
            String sql
                = "select distinct a.applicationid,a.fieldname, a.fieldvalue from applicationcustomfields a, applicationdetail ad, gateway g WHERE a.applicationid = ad.applicationid " +
                		"AND a.gateway_id=ad.gatewayid AND ad.gatewayid = g.gateway_id and g.flag_nodo is true and ad.enabled = '1' and a.applicationid in ("
                    + inClauseApp
                    + ") and trim(fieldname) in ('identificativoDominio','identificativointermediarioPA','identificativoStazioneIntermediarioPA','passwordDominioNodoSpc','portaDiDominio','gadEnabled') order by 1,2";
            log.info ( "findDistinctDomini", sql );

            selFields = conn.prepareStatement ( sql );
            rsFields = selFields.executeQuery ();

            log.debug ( METHOD_NAME, "Query patrametri eseguita, ciclo" );

            Map<String,ParametroNodo> elencoProvvisorio = new HashMap<String,ParametroNodo> ();
            Set<ParametroNodo> elenco = new HashSet<ParametroNodo> ();
            ParametroNodo parametroNodo = null;

            while ( rsFields.next () ) {
                String appId = rsFields.getString ( "applicationid" );
                
                parametroNodo = elencoProvvisorio.get(appId);
                
                if (parametroNodo == null){
                	parametroNodo = new ParametroNodo();
                	log.info(METHOD_NAME, "+++ parametroNodo null - creato uno nuovo ");
                	
                }
                	
                
                elencoProvvisorio.put(appId,parametroNodo);
                
               
                String fieldName = rsFields.getString ( "fieldname" ).trim ();
                String fieldvalue = decifraFieldValue ( rsFields.getString ( "fieldvalue" ) );
                
                log.info(METHOD_NAME, "+++ applicationid = " + appId + " fieldname = " + fieldName + " fieldvalue = " + fieldvalue);

                if ( "identificativoDominio".equals ( fieldName ) ) {
                	elencoProvvisorio.get(appId).setIdentificativoDominio ( fieldvalue );
                } else if ( "identificativointermediarioPA".equals ( fieldName ) ) {
                    parametroNodo.setIdentificativointermediarioPA ( fieldvalue );
                } else if ( "identificativoStazioneIntermediarioPA".equals ( fieldName ) ) {
                	elencoProvvisorio.get(appId).setIdentificativoStazioneIntermediarioPA(fieldvalue );
                } else if ( "passwordDominioNodoSpc".equals ( fieldName ) ) {
                	elencoProvvisorio.get(appId).setPasswordDominioNodoSpc ( fieldvalue );
                } else if ( "portaDiDominio".equals ( fieldName ) ) {
                	elencoProvvisorio.get(appId).setPortaDiDominio ( fieldvalue );
                } else if ("gadEnabled".equals(fieldName)){
                	elencoProvvisorio.get(appId).setGadEnabled("true".equalsIgnoreCase(fieldvalue));
                }
                
                

            }
            for ( ParametroNodo elem: elencoProvvisorio.values() ) {
            	            	
                if ( elem != null && elem.isComplete() && !dominioPresenteV2 ( elenco, elem ) ) {
                	log.info(METHOD_NAME, "elemento non presente, si aggiunge =[" + elem.getIdentificativoDominio() + "|" + 
                			elem.getIdentificativointermediarioPA() + "|" + elem.getIdentificativoStazioneIntermediarioPA() + "|" +
                			elem.getPortaDiDominio());
                    elenco.add ( elem );
                }
            }

            log.info ( METHOD_NAME, "parametri popolati, fine elaborazione: " + elenco.size () );
            return elenco;

        } catch ( SQLException e ) {
            e.printStackTrace ();
            log.error ( METHOD_NAME, "SQLException", e );
            throw e;
        } catch ( NamingException e ) {
            e.printStackTrace ();
            log.error ( METHOD_NAME, "NamingException", e );
            throw e;
        } catch ( Exception e ) {

            e.printStackTrace ();
            log.error ( METHOD_NAME, "Exception", e );
            throw e;
        } finally {

            ConnectionManager.closeResultSet ( rsApp );
            ConnectionManager.closeResultSet ( rsFields );
            ConnectionManager.closeStatement ( selApp );
            ConnectionManager.closeStatement ( selFields );
            ConnectionManager.closeConnection ( conn );
        }
    }

	private boolean dominioPresente(Set<ParametroNodo> elenco, ParametroNodo elem) {

		if (elenco!= null) {
			for (ParametroNodo elementoGiaPresente : elenco) {
				if (elementoGiaPresente == null) {
                    return true;
                }
				if (elementoGiaPresente.getIdentificativoDominio() == null) {
                    return true;
                }
				if (elem.getIdentificativoDominio() == null) {
                    return true;
                }
				if (elementoGiaPresente.getIdentificativoDominio().equals(elem.getIdentificativoDominio())) {
					return true;
				}
	 		}
		}

		return false;
	}

    private boolean dominioPresenteV2 ( Set<ParametroNodo> elenco, ParametroNodo elem ) {
    	
    	if ( elenco != null && elenco.size() > 0 ) {
    		 for ( ParametroNodo elementoGiaPresente: elenco ) {
    			 if ( elementoGiaPresente.equals(elem)){
                  	return true;
                  }
             }
        }

        return false;
    }
	/**
	 * Estrae l'elenco degli application custom field per un'application
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getMappaApplicationCustomFields(String applicationId) throws Exception {
		String METHOD_NAME = "getMappaApplicationCustomFields";

		log.info(METHOD_NAME,super.getClass().getSimpleName());

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Map<String, String> mappaAppCustomFields = new HashMap<String, String>();

		try {

			log.debug(METHOD_NAME, "Ottengo la connessione");
			conn = ConnectionManagerFactory.getInstance().getConnection();

			log.debug(METHOD_NAME, "Connessione ottenuta, creo lo statement applicazioni");
			stmt = conn.prepareStatement("select fieldname, fieldvalue from applicationcustomfields where applicationid = ? ");

			stmt.setString(1, applicationId);

			log.info(METHOD_NAME, "Statement ottenuto, eseguo la query applicazioni");
			rs = stmt.executeQuery();

			while (rs.next()) {
				mappaAppCustomFields.put(StringUtils.trimToEmpty(rs.getString("fieldname")), decifraFieldValue(rs.getString("fieldvalue")));
			}


			log.debug(METHOD_NAME, "parametri popolati, fine elaborazione");
			return mappaAppCustomFields;

		} catch (Exception e) {
			e.printStackTrace();
			log.error(METHOD_NAME, e.getClass(), e);
			throw e;
		} finally {
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(stmt);
			ConnectionManager.closeConnection(conn);
		}
	}
	
	/**
	 * Estrae l'elenco degli application custom field per un'application
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getMappaApplicationCustomFieldsEnabled(String applicationId) throws Exception {
		String METHOD_NAME = "getMappaApplicationCustomFieldsEnabled";

		log.info(METHOD_NAME,super.getClass().getSimpleName());

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Map<String, String> mappaAppCustomFields = new HashMap<String, String>();

		try {

			log.debug(METHOD_NAME, "Ottengo la connessione");
			conn = ConnectionManagerFactory.getInstance().getConnection();

			log.debug(METHOD_NAME, "Connessione ottenuta, creo lo statement applicazioni");
			stmt = conn.prepareStatement("select a.fieldname, a.fieldvalue "
					+ "from applicationcustomfields a, applicationdetail ad,  gateway g "
					+ "where a.applicationid = ad.applicationid  "
					+ "and a.gateway_id=ad.gatewayid "
					+ "and ad.gatewayid = g.gateway_id "
					+ "and g.flag_nodo is true "
					+ "and ad.enabled = '1' "
					+ "and a.fieldvalue is not null "
					+ "and a.applicationid = ? ");
			
			
			stmt.setString(1, applicationId);

			log.info(METHOD_NAME, "Statement ottenuto, eseguo la query applicazioni");
			rs = stmt.executeQuery();

			while (rs.next()) {
				mappaAppCustomFields.put(StringUtils.trimToEmpty(rs.getString("fieldname")), decifraFieldValue(rs.getString("fieldvalue")));
			}


			log.debug(METHOD_NAME, "parametri popolati, fine elaborazione");
			return mappaAppCustomFields;

		} catch (Exception e) {
			e.printStackTrace();
			log.error(METHOD_NAME, e.getClass(), e);
			throw e;
		} finally {
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(stmt);
			ConnectionManager.closeConnection(conn);
		}
	}

	private String decifraFieldValue(String fieldValueCifrato) throws SQLException {
		String fieldValueDecifrato = null;
		if (sKey != null && fieldValueCifrato!=null)
		{

			//SecretKeySpec skeySpec = new SecretKeySpec(Arrays.copyOf(sKey, 16), "AES");
			SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
			byte[] original = null;
			byte[] encrypted = fieldValueCifrato.getBytes();
			try
			{
				encrypted = Base64.decode(new String (encrypted));
				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				original = cipher.doFinal(encrypted);

				fieldValueDecifrato = new String(original);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fieldValueDecifrato;
	}


	public static String asHex(byte buf[])
	{
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++)
		{
			if ((buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

			strbuf.append(Long.toString(buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

}
