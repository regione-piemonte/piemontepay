/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.soap.SOAPException;

import org.apache.commons.lang.StringUtils;
import org.apache.ws.security.util.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ApplicationcustomfieldsDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ApplicationcustomfieldsTempDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ConfigDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.DescrizioneApplicativoDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.GatewayDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.IuvAttributeDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.ErrorParameterException;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.MdpcoopapplicativaSrvException;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao.AbstractDao;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao.interfacce.MdpDao;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplication;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFields;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFieldsTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetail;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetailTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnte;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnteTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTErrore;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTTemplate;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.MdpcoopapplicativaConstants;
import it.csi.util.performance.StopWatch;


public class MdpDaoImpl extends AbstractDao implements MdpDao {

    public String dataOdierna () {

        log.info ( "[MdpDaoImpl::dataOdierna] BEGIN" );
        String dataDiOggi = "";
        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sqlQuery = new StringBuffer ();
        sqlQuery.append ( "select to_char(CURRENT_TIMESTAMP,'dd/MM/YYYY') from dual " );
        try {

            log.debug ( "query vData odierna  " + sqlQuery );

            dataDiOggi = (String) getNamedJdbcTemplate ().queryForObject ( sqlQuery.toString (), params, java.lang.String.class );

            log.debug ( "data odierna  " + dataDiOggi );

        } catch ( DataAccessException dax ) {
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "dataOdierna()", "invocazione query " + sqlQuery.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::dataOdierna] END" );
        }

        return dataDiOggi;
    }

    public boolean isPresentIDApplication ( String idApplication ) {

        log.info ( "[MdpDaoImpl::isPresentIDApplication] BEGIN" );

        boolean ris = false;

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        sb.append ( "select count(*) from application where id = :idApp " );

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {

            params.addValue ( "idApp", idApplication );

            log.debug ( "query " + sb );
            log.debug ( " PARAM - idApp :: " + idApplication );

            int count = getNamedJdbcTemplate ().queryForInt ( sb.toString (), params );

            if ( count > 0 )
                ris = true;

        } catch ( DataAccessException dax ) {
            log.error ( "[MdpDaoImpl::isPresentIDApplication] ERRORE", dax );
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "isPresentIDApplication()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::isPresentIDApplication] END" );
        }

        return ris;
    }

    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - START

    //  private void passwd(boolean toEncode,String source) throws Exception {
    //      byte [] b = Base64.decode ( new String ( "yYoThaIwl7gff2no8krJ6g==") );
    //        String sKey = new String ( b );
    //        byte [] raw = sKey.getBytes ();
    //        SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );
    //
    //        log.debug ( "Source string is " + source);
    //        
    //        byte [] processed = null;
    //
    //        Cipher cipher = Cipher.getInstance ( "AES" );
    //
    //        if(toEncode == true)  cipher.init ( Cipher.ENCRYPT_MODE, skeySpec );
    //        if(toEncode == false) cipher.init ( Cipher.DECRYPT_MODE, skeySpec );
    //        
    //        if(toEncode == true)  processed = cipher.doFinal ( source.getBytes () );
    //        if(toEncode == false) processed = cipher.doFinal ( Base64.decode ( source ) );
    //
    //        String processedString = new String ( processed );
    //
    //        if(toEncode == true) processedString = Base64.encode(processed);
    //        
    //        log.debug ( "Processed string: " + processedString);
    //  }

    
    public String getAuxDigit ( String idApp ) throws ErrorParameterException, SOAPException {
        List<GatewayDTO> lstGateway = getGatewayNodoSPC ( idApp );

        String auxDigit = "";
        if ( lstGateway != null )
            log.debug ( "GATEWAYS FOUND " + lstGateway.size () );
        else
            log.debug ( "GATEWAYS FOUND NONE" );

        GatewayDTO gw = null;

        for ( GatewayDTO lst: lstGateway ) {
            auxDigit = getFieldValue ( idApp, lst.getGatewayid (), "auxDigit" );
        }

        return auxDigit;
    }

    private String getCodiceSegregazione ( DescrizioneApplicativoDTO ris ) throws ErrorParameterException, SOAPException {
        String csegrega = "";

        log.debug ( "CODICE SEGREGAZIONE START" );

        List<GatewayDTO> lstGateway = getGatewayNodoSPC ( ris.getIdApplication () );

        if ( lstGateway != null )
            log.debug ( "GATEWAYS FOUND " + lstGateway.size () );
        else
            log.debug ( "GATEWAYS FOUND NONE" );

        GatewayDTO gw = null;

        for ( GatewayDTO lst: lstGateway ) {
            gw = lst;
        }

        log.debug ( "CODICE SEGREGAZIONE DEFAULT " + ris.getCodiceSegregazione () );

        csegrega = ris.getCodiceSegregazione ();

        String auxDigit = getAuxDigit ( ris.getIdApplication () );

        log.debug ( "AUXDIGIT IS " + auxDigit );
        log.debug ( "ID APPLICATION " + ris.getIdApplication () );

        if ( gw != null )
            log.debug ( "GATEWAY IS DESCR " + gw.getGatewaydescription () );
        if ( gw != null )
            log.debug ( "GATEWAY IS ID " + gw.getGatewayid () );

        if ( ( auxDigit != null ) && ( gw != null ) ) {
            if ( auxDigit.trim ().equalsIgnoreCase ( "3" ) ) {
                log.debug ( "CODICE SEGREGAZIONE DA SORGENTE NEW - TABELLA APPLICATION CUSTOM FIELDS - APPL:" + ris.getIdApplication () + " - GW:"
                    + gw.getGatewayid () );
                csegrega = getFieldValue ( ris.getIdApplication (), gw.getGatewayid (), "codiceSegregazione" );
            } else {
                log.debug ( "CODICE SEGREGAZIONE DA SORGENTE OLD - TABELLA ENTI" );
            }
        } else
            log.debug ( "CODICE SEGREGAZIONE DA SORGENTE OLD - TABELLA ENTI" );

        log.debug ( "CODICE SEGREGAZIONE STOP AS " + csegrega + " AUXDIGIT " + auxDigit );
        return csegrega;
    }

    private String getFieldValue ( String applicationid, String gatewayid, String fieldName ) throws ErrorParameterException {

        log.debug ( "[MdpIuvDaoImpl::getFieldValue] BEGIN" );
        String ret = "";
        try {
            List<ApplicationcustomfieldsDTO> lstCf = getCustomFields ( applicationid, gatewayid );
            for ( ApplicationcustomfieldsDTO ls: lstCf ) {
                if ( ls.getFieldname ().equals ( fieldName ) ) {
                    ret = ls.getFieldvalue ();
                }
            }
        } catch ( Exception e ) {
            log.error ( "[MdpIuvDaoImpl::getFieldValue] Errore: ", e );
            ErrorParameterException epm = new ErrorParameterException ( MdpcoopapplicativaConstants.ERRORE_GENERICO );
            throw epm;
        }

        log.debug ( "[MdpIuvDaoImpl::getFieldValue] END" );

        return ret;
    }

    ClassPathResource cp = new ClassPathResource ( "/WEB-INF/classes/env.properties" );

    private Properties envProps = new Properties ();

    private Properties getAllConfig () throws ErrorParameterException, SOAPException {

        log.debug ( "[MdpCoopApplicativa-MdpDaoImpl::getAllConfig] - BEGIN" );
        log.debug ( "[MdpCoopApplicativa-MdpDaoImpl::getAllConfig] - cp.getPath() " + cp.getPath () );

        try {
            envProps.load ( cp.getInputStream () );
            List<ConfigDTO> lconf = findAllConfig ();

            if ( lconf != null )
                log.debug ( "ITEMS IN CONFIG : " + lconf.size () );
            else
                log.debug ( "NO ITEMS FOUND IN CONFIG" );

            for ( int i = 0; i < lconf.size (); i++ ) {
                ConfigDTO c = lconf.get ( i );
                log.debug ( "[MdpCoopApplicativa-MdpDaoImpl::getAllConfig] config:" + c.toString () );
                envProps.put ( c.getKey (), c.getValue () );
            }
        } catch ( IOException ioe ) {
            log.error ( "[MdpCoopApplicativa-MdpDaoImpl::getAllConfig] Errore: ", ioe );
            ErrorParameterException epm = new ErrorParameterException ( MdpcoopapplicativaConstants.ERRORE_GENERICO );
            throw epm;
        }
        log.debug ( "[MdpCoopApplicativa-MdpDaoImpl::getAllConfig] - END" );

        return envProps;
    }

    private List<ApplicationcustomfieldsDTO> getCustomFields ( String applicationid, String gatewayid )
                    throws ErrorParameterException, IOException, SOAPException {

        log.debug ( "[MdpDaoImpl::getCustomFields] - START" );
        String sKey = null;
        List<ApplicationcustomfieldsDTO> lstCustomFields = new ArrayList<ApplicationcustomfieldsDTO> ();
        FileInputStream stream = null;
        try {
            getAllConfig ();

            log.debug ( "SKEY DB LOCATION : " + envProps.getProperty ( "sKeyDb" ) );

            stream = new FileInputStream ( envProps.getProperty ( "sKeyDb" ) );
            int len = stream.available ();
            byte [] b = new byte [len];
            stream.read ( b );
            b = Base64.decode ( new String ( b ) );
            sKey = new String ( b );
            stream.close ();

            log.debug ( "SKEY DB IS : " + sKey );

            lstCustomFields = getApplicationcustomfields ( applicationid, gatewayid, sKey );
        } catch ( FileNotFoundException e ) {
            log.error ( "[MdpIuvDaoImpl::getCustomFields] Error: ", e );
            ErrorParameterException mex = new ErrorParameterException ( MdpcoopapplicativaConstants.ERRORE_KEYDB );
            throw mex;
        } catch ( IOException ioe ) {
            stream.close ();
            log.error ( "[MdpIuvDaoImpl::getCustomFields] Errore: ", ioe );
            ErrorParameterException epm = new ErrorParameterException ( MdpcoopapplicativaConstants.ERRORE_GENERICO );
            throw epm;
        } finally {
            stream.close ();
            log.debug ( "[MdpIuvDaoImpl::getCustomFields] - END" );
        }

        return lstCustomFields;
    }

    /*
     * Input: -- Output: List<ConfigDTO> Restituisce una lista di oggetti ConfigDTO Lista di parametri applicativi globali Accesso su tabella config
     */
    @SuppressWarnings ( "unchecked" )
    public List<ConfigDTO> findAllConfig () throws SOAPException {

        log.info ( "[MdpDaoImpl::findAllConfig] BEGIN" );
        StringBuffer sb = new StringBuffer ();
        sb.append ( "SELECT key, value, descrizione FROM config " );

        StopWatch watcher = new StopWatch ( LOGGER_PREFIX );
        watcher.start ();

        try {
            log.debug ( "query " + sb );
            MapSqlParameterSource params = new MapSqlParameterSource ();
            return (List<ConfigDTO>) getNamedJdbcTemplate ().query ( sb.toString (), params, new MdpDaoMappers.ConfigDTOMapper () );
        } catch ( DataAccessException dax ) {
            log.error ( "[MdpDaoImpl::findAllConfig] ERROR", dax );
            throw new SOAPException ( MdpcoopapplicativaConstants.ERRORE_ACCESSO_DATI );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "findAllConfig()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::findAllConfig] END" );
        }
    }

    /*
     * Input: applicationid, gatewayid, sKey Output: List<ApplicationcustomfieldsDTO> Restituisce una lista di oggetti ApplicationcustomfieldsDTO Lista di
     * parametri applicativi Accesso su tabella applicationcustomfields
     */
    
    @SuppressWarnings ( "unchecked" )
    public List<ApplicationcustomfieldsDTO> getApplicationcustomfields ( String applicationid, String gatewayid, String sKey ) throws SOAPException {

        log.info ( "[MdpDaoImpl::getApplicationcustomfields] BEGIN" );
        List<ApplicationcustomfieldsDTO> ret = new ArrayList<ApplicationcustomfieldsDTO> ();
        StringBuffer sb = new StringBuffer ();
        sb.append ( "SELECT keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription " + "FROM applicationcustomfields "
            + "WHERE applicationid = :applicationid AND gateway_id =:gateway_id ORDER BY applicationid" );

        StopWatch watcher = new StopWatch ( LOGGER_PREFIX );
        watcher.start ();

        try {
            log.debug ( "getApplicationcustomfields query " + sb );
            MapSqlParameterSource params = new MapSqlParameterSource ();
            params.addValue ( "applicationid", applicationid );
            params.addValue ( "gateway_id", gatewayid );
            ret = (List<ApplicationcustomfieldsDTO>) getNamedJdbcTemplate ().query ( sb.toString (), params,
                new MdpDaoMappers.ApplicationcustomfieldsDTOMapper ( sKey ) );
        } catch ( DataAccessException dax ) {
            log.error ( "[MdpDaoImpl::getApplicationcustomfields] ERROR", dax );
            throw new SOAPException ( MdpcoopapplicativaConstants.ERRORE_ACCESSO_DATI );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "getApplicationcustomfields()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::getApplicationcustomfields] END" );
        }
        return ret;
    }

    /*
     * Input: -- Output: List<GatewayDTO> Restituisce una lista di oggetti GatewayDTO Lista proprieta' oggetti gateway Accesso su tabella gateway
     */
    @SuppressWarnings ( "unchecked" )
    public List<GatewayDTO> getGatewayNodoSPC ( String idApplication ) throws SOAPException {

        log.info ( "[MdpDaoImpl::getGatewayNodoSPC] BEGIN" );
        StringBuffer sb = new StringBuffer ();
        sb.append ( "SELECT g.gateway_id, g.gateway_description, g.gateway_provider, g.valido_dal, g.valido_al, g.gatewayservicename, g.flag_nodo "
            + "  FROM gateway g, applicationdetail  ad WHERE ad.gatewayid = g.gateway_id AND flag_nodo = true AND ad.enabled = '1' and ad.applicationid = :appid  AND (valido_al is null OR valido_al > now())" );

        StopWatch watcher = new StopWatch ( LOGGER_PREFIX );
        watcher.start ();

        try {
            log.debug ( "query " + sb );
            MapSqlParameterSource params = new MapSqlParameterSource ();
            params.addValue ( "appid", idApplication );
            return (List<GatewayDTO>) getNamedJdbcTemplate ().query ( sb.toString (), params, new MdpDaoMappers.GatewayDTOMapper () );
        } catch ( DataAccessException dax ) {
            dax.printStackTrace ();
            log.error ( "[MdpDaoImpl::getGatewayNodoSPC] ERROR", dax );
            throw new SOAPException ( MdpcoopapplicativaConstants.ERRORE_ACCESSO_DATI );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "getGatewayNodoSPC()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::getGatewayNodoSPC] END" );
        }
    }

    private byte [] getKey () throws Exception {
        getAllConfig ();

        log.debug ( "SKEY DB LOCATION : " + envProps.getProperty ( "sKeyDb" ) );
        FileInputStream stream = new FileInputStream ( envProps.getProperty ( "sKeyDb" ) );
        int len = stream.available ();
        byte [] b = new byte [len];
        stream.read ( b );
        b = Base64.decode ( new String ( b ) );
        stream.close ();

        return b;
    }

    public String encode ( String toEncode ) {

        try {
            byte [] raw = getKey ();

            SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );

            Cipher cipher = Cipher.getInstance ( "AES" );

            cipher.init ( Cipher.ENCRYPT_MODE, skeySpec );

            if(toEncode == null) toEncode = "";
            
            byte [] processed = cipher.doFinal ( toEncode.getBytes () );

            return Base64.encode ( processed );
        } catch ( Exception dax ) {
            log.error ( "[MdpDaoImpl::encode] ERROR(data)", dax );
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        }
    }

    @SuppressWarnings ( "unchecked" )
    public MdpTTemplate getGateway () {

        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "chiave", "currentGateway" );
        values.put ( "flag_attivo", true);

        String ask
            = "select * from template_configurazione_epay where chiave = :chiave and flag_attivo = :flag_attivo";

        List<MdpTTemplate> gw = (List<MdpTTemplate>) query_select ( ask, values, new MdpDaoMappers.MdpTTemplateMapper ());

        if ( gw != null && gw.size () > 0 )
            return gw.get ( 0 );

        return null;
    }

    //MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP

    
    public DescrizioneApplicativoDTO isPresentIDEnte ( String idApplication ) {

        log.info ( "[MdpDaoImpl::isPresentIDEnte] BEGIN" );

        DescrizioneApplicativoDTO ris = null;

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        sb.append ( "SELECT descrizione, partita_iva, applicationname, " )
            .append ( "id, referentecsi, cliente, e.ente_id idente, codice_segregazione " )
            .append ( "from enti e, application a, r_application_enti rae " )
            .append ( "where " )
            .append ( "rae.application_id = a.id " )
            .append ( "and rae.ente_id = e.ente_id " )
            .append ( "and a.id = :idApp " );

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {

            params.addValue ( "idApp", idApplication );

            log.debug ( "query " + sb );
            log.debug ( " PARAM - idApp :: " + idApplication );

            List<DescrizioneApplicativoDTO> lista = getNamedJdbcTemplate ().query ( sb.toString (), params, new MdpDaoMappers.EnteIDApplicationDTOMapper () );

            if ( null == lista || lista.size () == 0 ) {

                log.error ( "[MdpDaoImpl::isPresentIDEnte] Nessun dato recuperato" );
                throw new MdpcoopapplicativaSrvException ( "[MdpDaoImpl::isPresentIDEnte] Nessun dato recuperato" );
            } else {

                // prendo sempre il primo elemento
                ris = lista.get ( 0 );

                //MODIFICA ADEGUAMENTO DICEMBRE 2018 - START
                log.debug ( "GENERAZIONE IUV START" );
                ris.setCodiceSegregazione ( getCodiceSegregazione ( ris ) );
                log.debug ( "GENERAZIONE IUV STOP" );
                //MODIFICA ADEGUAMENTO DICEMBRE 2018 - STOP             
            }

        } catch ( DataAccessException dax ) {
            log.error ( "[MdpDaoImpl::isPresentIDEnte] ERROR(data)", dax );
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } catch ( ErrorParameterException dax ) {
            log.error ( "[MdpDaoImpl::isPresentIDEnte] ERROR(param)", dax );
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } catch ( SOAPException dax ) {
            log.error ( "[MdpDaoImpl::isPresentIDEnte] ERROR(soap)", dax );
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "isPresentIDEnte()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::isPresentIDEnte] END" );
        }

        return ris;
    }

    
    public IuvAttributeDTO getIuvCorrente ( String idEnte ) {

        log.info ( "[MdpDaoImpl::getIuvCorrente] BEGIN" );

        IuvAttributeDTO ris = null;

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {
            sb.append ( "select ente_id, to_char(data_validita, 'dd/MM/YYYY') dataValidita , progressivo, " )
                .append ( "data_dismissione from iuv_attribute where " )
                .append ( "to_char(CURRENT_TIMESTAMP,'dd/MM/YYYY') = to_char(data_validita, 'dd/MM/YYYY') " )
                .append ( "and ente_id = :idEnte and data_dismissione is null " );

            params.addValue ( "idEnte", idEnte );

            log.debug ( "query " + sb );
            log.debug ( " PARAM - idEnte :: " + idEnte );

            List<IuvAttributeDTO> lista = getNamedJdbcTemplate ().query ( sb.toString (), params, new MdpDaoMappers.IuvAttributeDTOMapper () );

            ris = new IuvAttributeDTO ();

            if ( null == lista || lista.size () == 0 ) {
                log.info ( "[MdpDaoImpl::getIuvCorrente] Nessun elemento trovato nella tavola IUV_ATTRIBUTE" );
                ris = null;
                return ris;
                //throw new MdpIuvSrvException("Nessun elemento trovato nella tavola IUV_ATTRIBUTE");
            }

            // prendo sempre il primo e unico elemento
            ris = lista.get ( 0 );

        } catch ( DataAccessException dax ) {
            log.error ( "Errore getIuvCorrente " + dax.getMessage () );

            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "getIuvCorrente()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::getIuvCorrente] END" );
        }

        return ris;

    }

    
    public void insertNewDay ( String idEnte ) {
        log.info ( "[MdpDaoImpl::insertNewDay] BEGIN" );

        IuvAttributeDTO ris = null;

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        sb.append ( "INSERT INTO iuv_attribute ( ENTE_ID, data_validita, progressivo ) VALUES" )
            .append ( " ( :idEnte, CURRENT_TIMESTAMP, '1') " );

        log.debug ( " query di inserimento : " + sb );
        log.debug ( "PARAM - idEnte " + idEnte );
        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {

            params.addValue ( "idEnte", idEnte );

            getNamedJdbcTemplate ().update ( sb.toString (), params );

        } catch ( DataAccessException dax ) {
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "insertNewDay()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::insertNewDay] END" );
        }

    }

    
    public void updateIncremento ( String idEnte, BigInteger progressivo, String data ) {
        log.info ( "[MdpDaoImpl::updateIncremento] BEGIN" );

        IuvAttributeDTO ris = null;

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        sb.append ( "UPDATE iuv_attribute SET " )
            .append ( "progressivo = :progressivo " )
            .append ( "WHERE ente_id = :idEnte AND " )
            .append ( "to_char(data_validita, 'dd/MM/YYYY') = :data " );

        log.debug ( " query di update : " + sb );
        log.debug ( "PARAM - idEnte " + idEnte );
        log.debug ( "PARAM - progressivo " + progressivo );
        log.debug ( "PARAM - data " + data );

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {

            params.addValue ( "idEnte", idEnte );
            params.addValue ( "progressivo", progressivo, Types.BIGINT );
            params.addValue ( "data", data );

            getNamedJdbcTemplate ().update ( sb.toString (), params );

        } catch ( DataAccessException dax ) {
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "updateIncremento()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::updateIncremento] END" );
        }

    }

    
    public void updateStoricizza ( String idEnte ) {
        log.info ( "[MdpDaoImpl::updateStoricizza] BEGIN" );

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        sb.append ( "UPDATE iuv_attribute SET data_dismissione = CURRENT_DATE " )
            .append ( "where ente_id = :idEnte and " )
            .append ( "to_char(data_validita, 'dd/MM/YYYY') = (select to_char(max(a.data_validita), 'dd/MM/YYYY') " )
            .append ( "from iuv_attribute a " )
            .append ( "where a.ente_id = :idEnte " )
            .append ( "and a.data_validita < CURRENT_DATE) " );

        log.debug ( " query di update : " + sb );
        log.debug ( "PARAM - idEnte " + idEnte );
        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {

            params.addValue ( "idEnte", idEnte );

            getNamedJdbcTemplate ().update ( sb.toString (), params );

        } catch ( DataAccessException dax ) {
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "updateStoricizza()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::updateStoricizza] END" );
        }
    }

    
    @SuppressWarnings ( "unchecked" )
    public List<MdpTTemplate> findAllTemplates ( String gateway, String progetto ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "gateway_id", gateway );
        values.put ( "progetto", progetto );
        values.put ( "attivo", true );

        String ask = "select * from template_configurazione_epay where gateway_id = :gateway_id and progetto = :progetto and flag_attivo = :attivo";

        return (List<MdpTTemplate>) query_select ( ask, values, new MdpDaoMappers.MdpTTemplateMapper () );
    }

    
    @SuppressWarnings ( "unchecked" )
    public MdpTApplication getApplication ( String idApplication ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id", idApplication );

//        String ask
//            = "select * from application where id = :id and (valido_dal isnull or valido_dal <= CURRENT_DATE) and (valido_al isnull or valido_al >= CURRENT_DATE) limit 1";

        String ask
            = "select * from application where id = :id limit 1";

        List<MdpTApplication> items = (List<MdpTApplication>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationMapper () );

        if ( items != null && items.size () > 0 )
            return items.get ( 0 );

        return null;
    }

    
    @SuppressWarnings ( "unchecked" )
    public MdpTEnte findOneEnte ( String cfEnte ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "partita_iva", cfEnte );
        values.put ( "attivo", "1" );

        String ask = "select * from enti where partita_iva = :partita_iva and attivo = :attivo";

        List<MdpTEnte> items = (List<MdpTEnte>) query_select ( ask, values, new MdpDaoMappers.MdpTEnteMapper () );

        if ( items != null && items.size () > 0 )
            return items.get ( 0 );

        return null;
    }

    public List<MdpTApplicationCustomFields> findAllApplicationCustomFields(String appIdEnte) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "applicationid", appIdEnte);

        String ask = "select * from applicationcustomfields where applicationid = :applicationid";

        List<MdpTApplicationCustomFields> items = (List<MdpTApplicationCustomFields>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationCustomFieldsMapper() );

        return items;
    }
    
    public List<MdpTApplicationCustomFields> findAllApplicationCustomFieldsByAppidParziale(String appIdParziale) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "applicationid", appIdParziale + "%");

        String ask = "select * from applicationcustomfields where applicationid like :applicationid";

        List<MdpTApplicationCustomFields> items = (List<MdpTApplicationCustomFields>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationCustomFieldsMapper() );

        return items;
    }
    
    public void removeApplicationTemp ( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "delete from application_temp where id_operazione = :id_operazione";

        query_delete ( ask, values );
    }

    
    public void removeApplicationCustomTemp ( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "delete from applicationcustomfields_temp where id_operazione = :id_operazione";

        query_delete ( ask, values );
    }

    
    public void removeApplicationDetailTemp ( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "delete from applicationdetail_temp where id_operazione = :id_operazione";

        query_delete ( ask, values );
    }

    
    public void removeEnteTemp ( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "delete from enti_temp where id_operazione = :id_operazione";

        query_delete ( ask, values );
    }

    private List<?> query_select ( String query, HashMap<String, Object> items, ParameterizedRowMapper<?> mapper ) {
        return _query_ ( false, query, items, mapper );
    }

    private void query_update ( String tabella, HashMap<String, Object> items, HashMap<String, Object> pk ) {

        String itemStr = "";
        String pkStr = "";

        for ( Map.Entry<String, Object> entry: items.entrySet () ) {
            String key = entry.getKey ();

            itemStr += key + " = :" + key + " , ";
        }

        for ( Map.Entry<String, Object> entry: pk.entrySet () ) {
            String key = entry.getKey ();
            Object val = entry.getValue ();

            pkStr += key + " =  :" + key + " and ";
        }

        itemStr = itemStr.trim ();
        pkStr = pkStr.trim ();

        if ( itemStr.endsWith ( "," ) )
            itemStr = itemStr.substring ( 0, itemStr.lastIndexOf ( "," ) );

        if ( pkStr.endsWith ( "and" ) )
            pkStr = pkStr.substring ( 0, pkStr.lastIndexOf ( "and" ) );

        items.putAll ( pk );

        String query = "update " + tabella + " set " + itemStr + " where " + pkStr;

        _query_ ( true, query, items, null );
    }

    private void query_delete ( String query, HashMap<String, Object> items ) {
        _query_ ( true, query, items, null );
    }

    private void query_insert ( String tabella, HashMap<String, Object> items ) {

        String colonne = "";
        String values = "";

        for ( Map.Entry<String, Object> entry: items.entrySet () ) {
            String key = entry.getKey ();

            colonne += key + " , ";
            values += " :" + key + " , ";
        }

        colonne = colonne.trim ();
        values = values.trim ();

        if ( colonne.endsWith ( "," ) )
            colonne = colonne.substring ( 0, colonne.lastIndexOf ( "," ) );

        if ( values.endsWith ( "," ) )
            values = values.substring ( 0, values.lastIndexOf ( "," ) );

        String query = "insert into " + tabella + " (" + colonne + ") VALUES(" + values + ");";

        _query_ ( true, query, items, null );
    }

    private List<?> _query_ ( boolean update, String query, HashMap<String, Object> items, ParameterizedRowMapper<?> mapper ) {
        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        sb.append ( query );
        log.debug ( " query in esecuzione : " + sb );

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );

        watcher.start ();

        try {
            if ( items != null ) {
                for ( Map.Entry<String, Object> entry: items.entrySet () ) {
                    String key = entry.getKey ();
                    Object value = entry.getValue ();

                    if ( value instanceof BigDecimal ) {
                        params.addValue ( key, value, Types.DECIMAL );
                    } else {
                        params.addValue ( key, value );
                    }
                }
            }

            if ( update == true ) {
                getNamedJdbcTemplate ().update ( sb.toString (), params );
                return null;
            } else {
                return getNamedJdbcTemplate ().query ( sb.toString (), params, mapper );
            }
        } catch ( DataAccessException dax ) {
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "query", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::query] END" );
        }
    }

    
    @SuppressWarnings ( "unchecked" )
    public List<MdpTApplicationTemp> findAllApplicationTemp ( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "select * from application_temp where id_operazione = :id_operazione";

        return (List<MdpTApplicationTemp>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationTempMapper () );
    }

    
    @SuppressWarnings ( "unchecked" )
    public List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomTemp ( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "select * from applicationcustomfields_temp where id_operazione = :id_operazione";

        return (List<MdpTApplicationCustomFieldsTemp>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationCustomFieldsTempMapper () );
    }

    
    @SuppressWarnings ( "unchecked" )
    public List<MdpTApplicationDetailTemp> findAllApplicationDetailTemp ( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "select * from applicationdetail_temp where id_operazione = :id_operazione";

        return (List<MdpTApplicationDetailTemp>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationDetailTempMapper () );
    }

    
    @SuppressWarnings ( "unchecked" )
    public MdpTErrore findOneErroreByCodiceErrore ( String codiceErrore ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "codice_errore", codiceErrore );

        String ask = "select * from errore where codice_errore = :codice_errore limit 1";

        List<MdpTErrore> errList = (List<MdpTErrore>) query_select ( ask, values, new MdpDaoMappers.MdpTErroreMapper () );
        
        if((errList != null) && (errList.size () > 0)) return errList.get ( 0 );
        
        return new MdpTErrore ();
    }

    
    public void updateEnte ( MdpTEnte app ) {

        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "partita_iva", app.getPartita_iva () );
        values.put ( "descrizione", app.getDescrizione () );
        values.put ( "attivo", app.getAttivo () );
        values.put ( "codice_segregazione", app.getCodice_segregazione () );
        values.put ( "flag_invio_flusso_base", app.getFlag_invio_flusso_base () );
        values.put ( "flag_invio_flusso_esteso", app.getFlag_invio_flusso_esteso () );
        values.put ( "progressivo_application_id", app.getProgressivo_application_id () );

        HashMap<String, Object> pk = new HashMap<String, Object> ();
        pk.put ( "ente_id", app.getEnte_id () );

        query_update ( "enti", values, pk );
    }

    public void persistApp ( MdpTApplication app, boolean present ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id", app.getId () );
        values.put ( "applicationname", app.getApplicationname () );
        values.put ( "referentecsi", app.getReferentecsi () );
        values.put ( "cliente", app.getCliente () );
        values.put ( "progetto", app.getProgetto () );
        values.put ( "note", app.getNote () );
        values.put ( "esercemail", app.getEsercemail () );
        values.put ( "numeroverde", app.getNumeroverde () );
        values.put ( "valido_dal", app.getValido_dal () );
        values.put ( "valido_al", app.getValido_al () );
        values.put ( "redirect_newmdp", app.getRedirect_newmdp () );

        HashMap<String, Object> pk = new HashMap<String, Object> ();
        pk.put ( "id", app.getId () );
               
        if(present == false) 
            query_insert ( "application", values );
        else 
            query_update ( "application", values, pk );
    }

    public void removeEnteTempByPiva(String piva) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "partita_iva", piva);

        String ask = "delete from enti_temp where partita_iva = :partita_iva";

        query_delete ( ask, values );
    }
    
    public void persistAppTemp ( MdpTApplicationTemp app ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id", app.getId () );
        values.put ( "applicationname", app.getApplicationname () );
        values.put ( "referentecsi", app.getReferentecsi () );
        values.put ( "cliente", app.getCliente () );
        values.put ( "progetto", app.getProgetto () );
        values.put ( "note", app.getNote () );
        values.put ( "esercemail", app.getEsercemail () );
        values.put ( "numeroverde", app.getNumeroverde () );
        values.put ( "valido_dal", app.getValido_dal () );
        values.put ( "valido_al", app.getValido_al () );
        values.put ( "redirect_newmdp", app.getRedirect_newmdp () );
        values.put ( "id_operazione", app.getIdOperazione () );

        query_insert ( "application_temp", values );
    }

    public void persistAppCustom ( List<MdpTApplicationCustomFields> apps,boolean present, boolean toEncode ) {
        for ( MdpTApplicationCustomFields app: apps ) {
            HashMap<String, Object> values = new HashMap<String, Object> ();
            
            if(app.getFieldvalue () != null) {
                if(toEncode == true)  values.put ( "fieldvalue", encode(app.getFieldvalue () ));
                if(toEncode == false) values.put ( "fieldvalue",        app.getFieldvalue ()  );
            } else {
                values.put ( "fieldvalue", null);                
            }
            
            values.put ( "fielddescription", app.getFielddescription () );
            values.put ( "fieldname",        app.getFieldname () );
            values.put ( "applicationid",    app.getApplicationid () );
            values.put ( "gateway_id",       app.getGateway_id () );

            HashMap<String, Object> pk = new HashMap<String, Object> ();
            pk.put ( "fieldname",     app.getFieldname () );
            pk.put ( "applicationid", app.getApplicationid () );
            pk.put ( "gateway_id",    app.getGateway_id () );

            if(present)
                query_update ( "applicationcustomfields", values, pk );
            else
                query_insert ( "applicationcustomfields", values );
        }
    }
    
    public void persistAppCustomTemp ( List<MdpTApplicationCustomFieldsTemp> apps ) {
        for ( MdpTApplicationCustomFieldsTemp app: apps ) {
            HashMap<String, Object> values = new HashMap<String, Object> ();
            values.put ( "applicationid",    app.getApplicationid () );
            values.put ( "fieldname",        app.getFieldname () );            
            values.put ( "fieldvalue",       app.getFieldvalue () );
            values.put ( "gateway_id",       app.getGateway_id () );
            values.put ( "fielddescription", app.getFielddescription () );
            values.put ( "id_operazione",    app.getIdOperazione () );

            query_insert ( "applicationcustomfields_temp", values );
        }
    }

    public void persistAppCustomTemp ( MdpTApplicationCustomFieldsTemp app ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "applicationid",    app.getApplicationid () );
        values.put ( "fieldname",        app.getFieldname () );
        values.put ( "fieldvalue",       app.getFieldvalue () );
        values.put ( "gateway_id",       app.getGateway_id () );
        values.put ( "fielddescription", app.getFielddescription () );
        values.put ( "id_operazione",    app.getIdOperazione () );

        query_insert ( "applicationcustomfields_temp", values );
    }
    
    public void persistAppDetail ( List<MdpTApplicationDetail> apps, boolean present ) {
        for ( MdpTApplicationDetail app: apps ) {
            HashMap<String, Object> values = new HashMap<String, Object> ();
            values.put ( "applicationid", app.getApplicationid () );
            values.put ( "gatewayid", app.getGatewayid () );
            values.put ( "paymentmodeid", app.getPaymentmodeid () );
            values.put ( "merchantid", app.getMerchantid () );
            values.put ( "merchantidpassword", app.getMerchantidpassword () );
            values.put ( "pgactioncode", app.getPgactioncode () );
            values.put ( "flagreturlmdp", app.getFlagreturlmdp () );
            values.put ( "applicationurlresponseok", app.getApplicationurlresponseok () );
            values.put ( "applicationurlresponseko", app.getApplicationurlresponseko () );
            values.put ( "tipocommissione", app.getTipocommissione () );
            values.put ( "valorecommissionemin", app.getValorecommissionemin () );
            values.put ( "sogliada", app.getSogliada () );
            values.put ( "sogliaa", app.getSogliaa () );
            values.put ( "valorecommissionemax", app.getValorecommissionemax () );
            values.put ( "enabled", app.getEnabled () );
            values.put ( "pgcontabcode", app.getPgcontabcode () );
            values.put ( "applicationurlback", app.getApplicationurlback () );
            values.put ( "mac_avvio", app.getMac_avvio () );
            values.put ( "mac_esito", app.getMac_esito () );
            values.put ( "codiceistat", app.getCodiceistat () );
            values.put ( "tipobollettinoposte", app.getTipobollettinoposte () );
            values.put ( "contocorrenteposte", app.getContocorrenteposte () );
            values.put ( "tipodocumentoposte", app.getTipodocumentoposte () );
            values.put ( "mail2buyerko", app.getMail2buyerko () );
            values.put ( "mail2buyerok", app.getMail2buyerok () );
            values.put ( "mail2esercko", app.getMail2esercko () );
            values.put ( "mail2esercok", app.getMail2esercok () );
            values.put ( "mail2sysko", app.getMail2sysko () );
            values.put ( "mail2sysok", app.getMail2sysok () );
            values.put ( "applicationurlcancel", app.getApplicationurlcancel () );
            values.put ( "applicationurlerror", app.getApplicationurlerror () );

            //CONSTRAINT applicationdetail_pk PRIMARY KEY (applicationid,gatewayid,paymentmodeid),
            HashMap<String, Object> pk = new HashMap<String, Object> ();
            pk.put ( "applicationid",   app.getApplicationid () );
            pk.put ( "gatewayid",       app.getGatewayid () );
            pk.put ( "paymentmodeid",   app.getPaymentmodeid () );

            if(present == false)
                query_insert ( "applicationdetail", values );
            else
                query_update( "applicationdetail", values, pk);
        }
    }
    
    public void persistAppDetailTemp ( List<MdpTApplicationDetailTemp> apps ) {
        for ( MdpTApplicationDetailTemp app: apps ) {
            HashMap<String, Object> values = new HashMap<String, Object> ();
            values.put ( "applicationid", app.getApplicationid () );
            values.put ( "gatewayid", app.getGatewayid () );
            values.put ( "paymentmodeid", app.getPaymentmodeid () );
            values.put ( "merchantid", app.getMerchantid () );
            values.put ( "merchantidpassword", app.getMerchantidpassword () );
            values.put ( "pgactioncode", app.getPgactioncode () );
            values.put ( "flagreturlmdp", app.getFlagreturlmdp () );
            values.put ( "applicationurlresponseok", app.getApplicationurlresponseok () );
            values.put ( "applicationurlresponseko", app.getApplicationurlresponseko () );
            values.put ( "tipocommissione", app.getTipocommissione () );
            values.put ( "sogliada", app.getSogliada () );
            values.put ( "sogliaa", app.getSogliaa () );
            values.put ( "valorecommissionemin", app.getValorecommissionemin () );
            values.put ( "valorecommissionemax", app.getValorecommissionemax () );
            values.put ( "enabled", app.getEnabled () );
            values.put ( "pgcontabcode", app.getPgcontabcode () );
            values.put ( "applicationurlback", app.getApplicationurlback () );
            values.put ( "mac_avvio", app.getMac_avvio () );
            values.put ( "mac_esito", app.getMac_esito () );
            values.put ( "codiceistat", app.getCodiceistat () );
            values.put ( "tipobollettinoposte", app.getTipobollettinoposte () );
            values.put ( "contocorrenteposte", app.getContocorrenteposte () );
            values.put ( "tipodocumentoposte", app.getTipodocumentoposte () );
            values.put ( "mail2buyerko", app.getMail2buyerko () );
            values.put ( "mail2buyerok", app.getMail2buyerok () );
            values.put ( "mail2esercko", app.getMail2esercko () );
            values.put ( "mail2esercok", app.getMail2esercok () );
            values.put ( "mail2sysko", app.getMail2sysko () );
            values.put ( "mail2sysok", app.getMail2sysok () );
            values.put ( "applicationurlcancel", app.getApplicationurlcancel () );
            values.put ( "applicationurlerror", app.getApplicationurlerror () );
            values.put ( "id_operazione", app.getIdOperazione () );

            query_insert ( "applicationdetail_temp", values );
        }
    }

    public void persistEnteTemp ( MdpTEnteTemp enteTemp ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "ente_id", enteTemp.getEnte_id () );
        values.put ( "partita_iva", enteTemp.getPartita_iva () );
        values.put ( "descrizione", enteTemp.getDescrizione () );
        values.put ( "attivo", enteTemp.getAttivo () );
        values.put ( "codice_segregazione", enteTemp.getCodice_segregazione () );
        values.put ( "flag_invio_flusso_base", enteTemp.getFlag_invio_flusso_base () );
        values.put ( "flag_invio_flusso_esteso", enteTemp.getFlag_invio_flusso_esteso () );
        values.put ( "progressivo_application_id", enteTemp.getProgressivo_application_id () );
        values.put ( "id_operazione", enteTemp.getIdOperazione () );

        query_insert ( "enti_temp", values );
    }
    
    public void persistEnte ( MdpTEnte ente , boolean present) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "ente_id", ente.getEnte_id () );
        values.put ( "partita_iva", ente.getPartita_iva () );
        values.put ( "descrizione", ente.getDescrizione () );
        values.put ( "attivo", ente.getAttivo () );
        values.put ( "codice_segregazione", ente.getCodice_segregazione () );
        values.put ( "flag_invio_flusso_base", ente.getFlag_invio_flusso_base () );
        values.put ( "flag_invio_flusso_esteso", ente.getFlag_invio_flusso_esteso () );
        values.put ( "progressivo_application_id", ente.getProgressivo_application_id () );

        HashMap<String, Object> pk = new HashMap<String, Object> ();
        pk.put ( "ente_id", ente.getEnte_id () );

        if(present)
            query_update ( "enti", values, pk );
        else
            query_insert ( "enti", values );
    }    
    
    
    @SuppressWarnings ( "unchecked" )
    public List<MdpTEnteTemp> findEnteTempByIdOperazione ( String idOperazione ) {

        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "select * from enti_temp where id_operazione = :id_operazione";

        return (List<MdpTEnteTemp>) query_select ( ask, values, new MdpDaoMappers.MdpTEnteTempMapper () );
    }

    
    public Integer getEnteMaxId () {
        HashMap<String, Object> values = new HashMap<String, Object> ();

        String ask = "" +
            "select regexp_replace(ente_id, '[[:alpha:]]', '', 'g')::int as replaced " +
            "from enti " +
            "where regexp_replace(ente_id, '[[:alpha:]]', '', 'g') != '' " +
            "order by replaced desc " +
            "limit 1";
        
        Integer count = getNamedJdbcTemplate ().queryForInt ( ask, values );

        return count;
    }

    private void updateFlussoRiversamento ( boolean flussoBase, String partita_iva ) {
        log.info ( "[MdpDaoImpl::updateIncremento] BEGIN" );

        MapSqlParameterSource params = new MapSqlParameterSource ();
        
        StringBuffer sb = new StringBuffer ();

        String tipo = "base";
        
        if(flussoBase == false) tipo = "esteso";
        
        sb.append ( "UPDATE flusso_riversamento SET " )
            .append ( "stato_invio_flusso_" + tipo + " = :stato_new " )
            .append ( "WHERE stato_invio_flusso_" + tipo + " = :stato_old AND identificativoistitutoricevente = :piva" );

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {

            params.addValue ( "piva", partita_iva);
            params.addValue ( "stato_old", "1");
            params.addValue ( "stato_new", "0");

            getNamedJdbcTemplate ().update ( sb.toString (), params );

        } catch ( DataAccessException dax ) {
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "updateFlussoRiversamento()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::updateFlussoRiversamento] END" );
        }
    }

    public void persistRelation ( String ente_id, String appId ) {
        log.info ( "[MdpDaoImpl::persistRelation] BEGIN" );
        
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "ente_id", ente_id );
        values.put ( "application_id", appId );

        query_insert ( "r_application_enti", values );
        
        log.info ( "[MdpDaoImpl::persistRelation] END" );
    }
    
    public String richiediApplicationId(String codiceFiscaleEnte, String iban) {
        return richiediApplicationId ( false, codiceFiscaleEnte, iban );
    }
    
    public String richiediApplicationIdTmp(String codiceFiscaleEnte, String iban) {
        return richiediApplicationId ( true, codiceFiscaleEnte, iban );
    }
    
	private String richiediApplicationId(boolean tmp, String codiceFiscaleEnte, String iban) {
		
		log.info ( "[MdpDaoImpl::dataOdierna] BEGIN" );
		
		String applicationId = null;
		
		HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "chiave", MdpcoopapplicativaConstants.APPLICATIONID_DEFAULT_FIELDDESCRIPTION );
		
		String askConfig = "select * from config where key = :chiave";
		
		List<ConfigDTO> results = (List<ConfigDTO>) query_select ( askConfig, values, new MdpDaoMappers.ConfigDTOMapper () );
		
		if (null == results || results.size() == 0 || (results.size() == 1 && StringUtils.isBlank(results.get(0).getValue()))) {
			throw new MdpcoopapplicativaSrvException(String.format("Chiave di configurazione %s non presente", MdpcoopapplicativaConstants.APPLICATIONID_DEFAULT_FIELDDESCRIPTION));
		}
		
		if (results.size() > 1) {
			throw new MdpcoopapplicativaSrvException(String.format("Chiave di configurazione %s non univoca", MdpcoopapplicativaConstants.APPLICATIONID_DEFAULT_FIELDDESCRIPTION));
		}
		
		try {
            getAllConfig ();
        } catch ( ErrorParameterException e1 ) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch ( SOAPException e1 ) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		String fieldDescription = results.get(0).getValue();
		
//        String sKey = "yYoThaIwl7gff2no8krJ6g==";
        String sKey = "";
        
        log.debug ( "SKEY DB LOCATION : " + envProps.getProperty ( "sKeyDb" ) );

        FileInputStream stream = null;
        
		try {
			stream = new FileInputStream(envProps.getProperty("sKeyDb"));
			int len = stream.available();
			byte[] b = new byte[len];
			stream.read(b);
			b = Base64.decode(new String(b));
			sKey = new String(b);
			stream.close();
		} catch (FileNotFoundException e) {
			throw new MdpcoopapplicativaSrvException("SKEY DB LOCATION : " + envProps.getProperty ( "sKeyDb" ) + " " + e.getLocalizedMessage ());
		}
		catch (IOException e) {
			throw new MdpcoopapplicativaSrvException(e);
		}

        log.debug ( "SKEY DB IS : " + sKey );
       
		try {
			byte[] raw;
			raw = new String ( sKey ).getBytes();
			 SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );
		        Cipher cipher;
			cipher = Cipher.getInstance ( "AES" );
			cipher.init ( Cipher.ENCRYPT_MODE, skeySpec );
			
			if(codiceFiscaleEnte == null) {
                log.debug ( "CF ENTE NULLO !! ####################");
                
			    codiceFiscaleEnte = "";
			}
			if(iban == null) {
			    log.debug ( "IBAN NULLO !! ####################");
	            
			    iban = "";
			}
			
	        byte[] encryptedCF = cipher.doFinal ( codiceFiscaleEnte.getBytes() );
	        byte[] encryptedIban = cipher.doFinal ( iban.getBytes() );
	        
	        values = new HashMap<String, Object> ();
	        values.put ( "fielddescription", fieldDescription );
	        values.put ( "cfente", Base64.encode(encryptedCF) );
//	        values.put ( "iban", Base64.encode(encryptedIban) );
	        
	        String askAppCustomDominio = "";
	        String askAppCustomDominioTmp = "";
	        
	        if(tmp == false) askAppCustomDominio    = "select * from applicationcustomfields      where applicationid like 'EPAY%' and fielddescription = :fielddescription and  (fieldname like 'identificativoDominio%' and fieldvalue = :cfente) order by keyid";
	        if(tmp == true)  askAppCustomDominioTmp = "select * from applicationcustomfields_temp where applicationid like 'EPAY%' and fielddescription = :fielddescription and  (fieldname like 'identificativoDominio%' and fieldvalue = :cfente) order by keyid";
	                
            List<ApplicationcustomfieldsDTO>     resultsCustomFieldsIdentificativoDominio     = new ArrayList<ApplicationcustomfieldsDTO>();
            List<ApplicationcustomfieldsTempDTO> resultsCustomFieldsIdentificativoDominioTemp = new ArrayList<ApplicationcustomfieldsTempDTO>();
            
	        if(!StringUtils.isEmpty ( askAppCustomDominio ))   resultsCustomFieldsIdentificativoDominio     = (List<ApplicationcustomfieldsDTO>)     query_select ( askAppCustomDominio,    values, new MdpDaoMappers.ApplicationcustomfieldsDTOMapper (sKey) );
            if(!StringUtils.isEmpty ( askAppCustomDominioTmp ))resultsCustomFieldsIdentificativoDominioTemp = (List<ApplicationcustomfieldsTempDTO>) query_select ( askAppCustomDominioTmp, values, new MdpDaoMappers.ApplicationcustomfieldsTempDTOMapper (sKey) );
	        
            if(resultsCustomFieldsIdentificativoDominioTemp != null) {
                for(ApplicationcustomfieldsTempDTO currItem:resultsCustomFieldsIdentificativoDominioTemp) {
                    ApplicationcustomfieldsDTO item = new ApplicationcustomfieldsDTO ();
                    
                    BeanUtils.copyProperties ( currItem, item );
                    
                    resultsCustomFieldsIdentificativoDominio.add ( item );
                }
            }
            
	        values = new HashMap<String, Object> ();
	        values.put ( "fielddescription", fieldDescription );
//	        values.put ( "cfente", Base64.encode(encryptedCF) );
	        values.put ( "iban", Base64.encode(encryptedIban) );
	        
//	        if (null == resultsCustomFieldsIdentificativoDominio || resultsCustomFieldsIdentificativoDominio.size() == 0 || (resultsCustomFieldsIdentificativoDominio.size() == 2 && (StringUtils.isBlank(resultsCustomFieldsIdentificativoDominio.get(0).getApplicationid()) || StringUtils.isBlank(resultsCustomFieldsIdentificativoDominio.get(1).getApplicationid())))) {
//	        	log.warn("Application ID non disponibile!");
//	        } else if (results.size() > 2 || (results.size() == 2 && !resultsCustomFields.get(0).getApplicationid().equalsIgnoreCase(resultsCustomFields.get(1).getApplicationid()))) {
//	        	throw new MdpcoopapplicativaSrvException("Application id non univoco!");
//	        } else {
//	        	applicationId = resultsCustomFields.get(0).getApplicationid();
//	        }
	        
	        String askAppCustomIban = "";
	        String askAppCustomIbanTemp = "";
	        
	        if(tmp == false) {
	            askAppCustomIban = "select * from applicationcustomfields where applicationid like 'EPAY%' and fielddescription = :fielddescription and  (fieldname = 'ibanAccredito' and fieldvalue = :iban) order by keyid";
	        }
            if(tmp == true)  {
                askAppCustomIbanTemp = "select * from applicationcustomfields_temp where applicationid like 'EPAY%' and fielddescription = :fielddescription and  (fieldname = 'ibanAccredito' and fieldvalue = :iban) order by keyid";
            }
	        
            List<ApplicationcustomfieldsDTO>     resultsCustomFieldsIban     = new ArrayList<ApplicationcustomfieldsDTO>();
            List<ApplicationcustomfieldsTempDTO> resultsCustomFieldsTempIban = new ArrayList<ApplicationcustomfieldsTempDTO>();
            
	        if(!StringUtils.isEmpty ( askAppCustomIban ))     resultsCustomFieldsIban     = (List<ApplicationcustomfieldsDTO>)     query_select ( askAppCustomIban,     values, new MdpDaoMappers.ApplicationcustomfieldsDTOMapper (sKey) );
	        if(!StringUtils.isEmpty ( askAppCustomIbanTemp )) resultsCustomFieldsTempIban = (List<ApplicationcustomfieldsTempDTO>) query_select ( askAppCustomIbanTemp, values, new MdpDaoMappers.ApplicationcustomfieldsTempDTOMapper (sKey) );

	        if(resultsCustomFieldsTempIban != null) {
                for(ApplicationcustomfieldsTempDTO currItem:resultsCustomFieldsTempIban) {
                    ApplicationcustomfieldsDTO item = new ApplicationcustomfieldsDTO ();
                    
                    BeanUtils.copyProperties ( currItem, item );
                    
                    resultsCustomFieldsIban.add ( item );
                }
            }
	        
	        log.debug ( "IBAN CERCATO " +  Base64.encode(encryptedIban));
	        log.debug ( "RECORD TROVATI : " + resultsCustomFieldsIban.size ());
	        
	        //Set<String> matchedStrings = new HashSet<String>();
	        List<String> matchedStrings = new ArrayList<String>();
	        
	        if (null != resultsCustomFieldsIdentificativoDominio && null != resultsCustomFieldsIban) {
	        	for (ApplicationcustomfieldsDTO appDominio : resultsCustomFieldsIdentificativoDominio) {
	        	    
	                log.debug ( "APP DOMINIO : " + appDominio.toString () );

	        		for (ApplicationcustomfieldsDTO appIban : resultsCustomFieldsIban) {
	        		    
	        		    log.debug ( "APP IBAN : " + appIban.toString ()); 
	        		    
	        			if (null != appDominio && null != appIban && appDominio.getApplicationid().equals(appIban.getApplicationid())) {
	        				matchedStrings.add(appDominio.getApplicationid());
	        				
	        				log.debug ( "MATCH OK : APP DOMINIO " + appDominio.getApplicationid () + " APP IBAN : " + appIban.getApplicationid ());
	        			}
	        		}
	        	}
	        }
	        
			/*
			 * if (matchedStrings.size() == 0) {
			 * log.warn("Application ID non disponibile!"); } else if (matchedStrings.size()
			 * > 1) { throw new
			 * MdpcoopapplicativaSrvException("Application id non univoco!"); } else {
			 * Iterator<String> iterator = matchedStrings.iterator();
			 * 
			 * while (iterator.hasNext()) { applicationId = iterator.next(); } }
			 */
	        if (matchedStrings.size() == 0) {
	        	log.warn("Application ID non disponibile!");
	        } else {
	        	applicationId = matchedStrings.get(0);
	        }
	        
	        log.debug ( " APPLICATION ID SELEZIONATO : " + applicationId );
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	} catch (WSSecurityException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
// catch (WSSecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        
		log.info ( "[MdpDaoImpl::dataOdierna] END" );
		return applicationId;
	}

    
    public Boolean isPresentRelazEnteApp ( String ente_id, String appId ) {
        log.info ( "[MdpDaoImpl::findOneRela] BEGIN" );

        boolean ris = false;

        MapSqlParameterSource params = new MapSqlParameterSource ();

        StringBuffer sb = new StringBuffer ();

        sb.append ( "select count(*) from r_application_enti where application_id = :idApp AND ente_id = :enteId" );

        it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch ( LOGGER_PREFIX );
        watcher.start ();
        try {

            params.addValue ( "idApp", appId );
            params.addValue ( "enteId", ente_id);
            
            log.debug ( "query " + sb );
            log.debug ( " PARAM - idApp :: " + appId );
            log.debug ( " PARAM - enteId :: " + ente_id );

            int count = getNamedJdbcTemplate ().queryForInt ( sb.toString (), params );

            if ( count > 0 )
                ris = true;

        } catch ( DataAccessException dax ) {
            log.error ( "[MdpDaoImpl::findOneRela] ERRORE", dax );
            throw new MdpcoopapplicativaSrvException ( dax.getMessage () );
        } finally {
            watcher.stop ();
            watcher.dumpElapsed ( "MdpDaoImpl", "findOneRela()", "invocazione query " + sb.toString (), "(valore input omesso)" );
            log.info ( "[MdpDaoImpl::findOneRela] END" );
        }

        return ris;        
    }

    
    public List<MdpTApplicationDetail> findAllApplicationDetail ( String appId_OLD ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "appid", appId_OLD );

        String ask = "select * from applicationdetail where applicationid = :appid";

        return (List<MdpTApplicationDetail>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationDetailMapper () );
    }
    
    public MdpTApplication findMaxApplication(String startWith) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id", startWith + "%");
        
        String ask = "select * from application where id like :id order by id";

        List<MdpTApplication> items = (List<MdpTApplication>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationMapper() );
        
        if((items == null) || (items.size () <= 0)) return null;
        
        MdpTApplication max = null;
        int lastMax = -1;
        
        for(MdpTApplication item:items) {
            String[] app = item.getId ().split ( "_" );
            
            if((app != null) && (app.length == 3)) {
                Integer currMax = Integer.parseInt ( app[2] );
                if(currMax > lastMax) {
                    max = item;
                    lastMax = currMax;
                    
                    log.debug ( "Curr max progressivo ente id " + lastMax);
                }
            }
        }
        
        return max;
    }
    
    public List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomFieldsTempByAppId( String appID ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "appID", appID );

        String ask = "select * from applicationcustomfields_temp where applicationid = :appID";

        return (List<MdpTApplicationCustomFieldsTemp>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationCustomFieldsTempMapper() );
    }
    
    public List<MdpTApplicationCustomFieldsTemp> findAllApplicationCustomFieldsTemp( String idOperazione ) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id_operazione", idOperazione );

        String ask = "select * from applicationcustomfields_temp where id_operazione = :id_operazione";

        return (List<MdpTApplicationCustomFieldsTemp>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationCustomFieldsTempMapper() );
    }
    
    public MdpTApplication findOneApplication(String id) {
        HashMap<String, Object> values = new HashMap<String, Object> ();
        values.put ( "id", id );

        String ask = "select * from application where id = :id";

        List<MdpTApplication> items = (List<MdpTApplication>) query_select ( ask, values, new MdpDaoMappers.MdpTApplicationMapper () );
        
        if((items == null) || (items.size () <= 0)) return null;
        
        return items.get ( 0 );
    }
    
  //CSI_PAG-316
  	public String richiediApplicationIdNew(String codiceFiscaleEnte, String iban, String ibanAppoggio, String idOperazione){
  		final String methodName = "[MdpDaoImpl::richiediApplicationIdNew] ";

  		log.info(methodName + "BEGIN");

  		String applicationId = null;

  		HashMap<String, Object> values = new HashMap<String, Object>();
  		values.put("chiaveApp", MdpcoopapplicativaConstants.APPLICATIONID_DEFAULT_FIELDDESCRIPTION);

  		String askConfig = "select * from config where key = :chiaveApp";
  		List<ConfigDTO> results = (List<ConfigDTO>) query_select(askConfig, values, new MdpDaoMappers.ConfigDTOMapper());

  		if (null == results || (null != results && results.isEmpty())) {
  			throw new MdpcoopapplicativaSrvException(String.format("Chiave di configurazione %s non presente", MdpcoopapplicativaConstants.APPLICATIONID_DEFAULT_FIELDDESCRIPTION));
  		} else if (results.size() > 1) {
  			throw new MdpcoopapplicativaSrvException(String.format("Chiave di configurazione %s non univoca", MdpcoopapplicativaConstants.APPLICATIONID_DEFAULT_FIELDDESCRIPTION));
  		} 
  		String fieldDescription = results.get(0).getValue();

  		try {
  			getAllConfig();
  		} catch (Exception e) {
  			throw new MdpcoopapplicativaSrvException("Error ALL_CONFIG load from environmet properties");
  		}
  		
  		log.info(methodName + "SKEY DB LOCATION : " + envProps.getProperty(MdpcoopapplicativaConstants.KEYDB_CONFIG_ENV));
  		FileInputStream stream = null;
  		String sKey = "";
  		try {
  			stream = new FileInputStream(envProps.getProperty(MdpcoopapplicativaConstants.KEYDB_CONFIG_ENV));
  			int len = stream.available();
  			byte[] b = new byte[len];
  			stream.read(b);
  			b = Base64.decode(new String(b));
  			sKey = new String(b);
  			stream.close();
  		} catch (FileNotFoundException e) {
  			throw new MdpcoopapplicativaSrvException("SKEY DB LOCATION : " + envProps.getProperty(MdpcoopapplicativaConstants.KEYDB_CONFIG_ENV) + " " + e.getLocalizedMessage());
  		} catch (IOException e) {
  			throw new MdpcoopapplicativaSrvException(e);
  		}

  		log.info(methodName + "SKEY DB IS : " + sKey);

  		byte[] encryptedIban = "".getBytes();
  		byte[] encryptedIbanAppoggio = "".getBytes();
  		byte[] encryptedIbanAppoggioDefaultValue = "".getBytes();
		byte[] encryptedIbanAppoggioEmptyValue = "".getBytes();
  		try {
  			byte[] raw;
  			raw = new String(sKey).getBytes();
  			SecretKeySpec skeySpec = new SecretKeySpec(raw, MdpcoopapplicativaConstants.ENCRYPTION_MODE_AES);
  			Cipher cipher;
  			cipher = Cipher.getInstance(MdpcoopapplicativaConstants.ENCRYPTION_MODE_AES);
  			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

  			if (null != iban) {
  				encryptedIban = cipher.doFinal(iban.getBytes());
  			}
  			if (null != ibanAppoggio) {
	  				encryptedIbanAppoggio = cipher.doFinal(ibanAppoggio.getBytes());
	  		}
  			encryptedIbanAppoggioDefaultValue = cipher.doFinal(MdpcoopapplicativaConstants.IBAN_APPOGGIO_DEFAULT_VALUE.getBytes());
	  		encryptedIbanAppoggioEmptyValue = cipher.doFinal(MdpcoopapplicativaConstants.IBAN_APPOGGIO_EMPTY_VALUE.getBytes());
	  			
  		} catch (Exception e) {
  			throw new MdpcoopapplicativaSrvException("Error on ecrypted values");
  		} 
  		
  		values = new HashMap<String, Object>();
  		values.put("fielddescription", fieldDescription);
  		values.put("cfente", codiceFiscaleEnte);
  		values.put("iban", Base64.encode(encryptedIban));
  		
  		values.put("enabledTrue", MdpcoopapplicativaConstants.APPLICATION_DETAIL_ENABLED_TRUE);
  		values.put("chiave", MdpcoopapplicativaConstants.CHIAVE_TEMPL_CONFIG_EPAY);
  		values.put("ibanAcc", MdpcoopapplicativaConstants.FIELDNAME_APPL_CUST_FIELDS_IBAN_ACC);
  		
  		values.put("ibanAppoggio", Base64.encode(encryptedIbanAppoggio));
	  	values.put("ibanAppoggioFieldName", MdpcoopapplicativaConstants.FIELDNAME_APPL_CUST_FIELDS_IBAN_APPOGGIO);
	  	
	  	values.put("ibanAppoggioDefaultValue", Base64.encode(encryptedIbanAppoggioDefaultValue));
		values.put("ibanAppoggioEmptyValue", Base64.encode(encryptedIbanAppoggioEmptyValue));
	  	values.put("ibanAppoggioFieldName", MdpcoopapplicativaConstants.FIELDNAME_APPL_CUST_FIELDS_IBAN_APPOGGIO);
  		
  		log.info(methodName + "PARAMETRI QUERY RICERCA APPLICAZIONE: " + values.toString());
  		
  		String queryIntersect =   " SELECT APPLICATIONID "
  				+ " FROM APPLICATIONDETAIL "
  				+ " WHERE ENABLED = :enabledTrue "
  				+ " AND MERCHANTID = :cfente "
  				+ " AND GATEWAYID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  				+ " AND APPLICATIONID LIKE 'EPAY%' "
  				+ " INTERSECT "
  				+ " SELECT APPLICATIONID "
  				+ " FROM APPLICATIONCUSTOMFIELDS "
  				+ " WHERE FIELDNAME = :ibanAcc "
  				+ " AND FIELDDESCRIPTION = :fielddescription "
  				+ " AND GATEWAY_ID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  				+ " AND FIELDVALUE = :iban "
  				+ " AND APPLICATIONID LIKE 'EPAY%'";
  		
  		if (!StringUtils.isBlank(ibanAppoggio)) {
  			queryIntersect += " INTERSECT "
  	  				+ " SELECT APPLICATIONID "
  	  				+ " FROM APPLICATIONCUSTOMFIELDS "
  	  				+ " WHERE FIELDNAME = :ibanAppoggioFieldName "
  	  				+ " AND FIELDDESCRIPTION = :fielddescription "
  	  				+ " AND GATEWAY_ID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  	  				+ " AND FIELDVALUE = :ibanAppoggio "
  	  				+ " AND APPLICATIONID LIKE 'EPAY%'";
  		}else {
  			queryIntersect += " INTERSECT "
  	  				+ " SELECT APPLICATIONID "
  	  				+ " FROM APPLICATIONCUSTOMFIELDS "
  	  				+ " WHERE FIELDNAME = :ibanAppoggioFieldName "
  	  				+ " AND FIELDDESCRIPTION = :fielddescription "
  	  				+ " AND GATEWAY_ID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  	  				+ " AND (FIELDVALUE = :ibanAppoggioDefaultValue OR FIELDVALUE = :ibanAppoggioEmptyValue OR FIELDVALUE IS NULL)"
  	  				+ " AND APPLICATIONID LIKE 'EPAY%'";
  		}
  		
  		List<String> listApplicationIdIntersect = new ArrayList<String>();
  		listApplicationIdIntersect = (List<String>) query_select(queryIntersect, values, new MdpDaoMappers.ApplicationIdStringMapper());

  		if (null == listApplicationIdIntersect || (null != listApplicationIdIntersect && listApplicationIdIntersect.isEmpty())) {
  			log.error(methodName + "NESSUN ApplicationID corrispondente alla coppia di valori : " +codiceFiscaleEnte + " - " + iban);
  			values.put("idOperazione", idOperazione);
  			applicationId = richiediTempApplicationId(values);
  		} else if (listApplicationIdIntersect.size() > 1) {
  			throw new MdpcoopapplicativaSrvException("ApplicationID NON UNIVOCO per la coppia di valori : " +codiceFiscaleEnte + " - " + iban);
  		} else {
  			applicationId = listApplicationIdIntersect.get(0);
  		}
  		  		
  		log.info(methodName + "APPLICATION ID SELEZIONATO : " + applicationId);

  		log.info(methodName + "END");
  		return applicationId;
  	}

  	private String richiediTempApplicationId(HashMap<String, Object> queryParameters) {
  		final String methodName = "[MdpDaoImpl::richiediApplicationIdNew] ";
  		log.info(methodName + "BEGIN");

  		String applicationId = null;
  		String ibanAppoggio = (queryParameters.get("ibanAppoggio")!=null?queryParameters.get("ibanAppoggio").toString():null);
  		
  		String query =   " SELECT APPLICATIONID "
  				+ " FROM APPLICATIONDETAIL_TEMP "
  				+ " WHERE ENABLED = :enabledTrue "
  				+ " AND MERCHANTID = :cfente "
  				+ " AND GATEWAYID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  				+ " AND APPLICATIONID LIKE 'EPAY%' "
  				+ " AND ID_OPERAZIONE = :idOperazione "
  				+ " INTERSECT "
  				+ " SELECT APPLICATIONID "
  				+ " FROM APPLICATIONCUSTOMFIELDS_TEMP "
  				+ " WHERE FIELDNAME = :ibanAcc "
  				+ " AND FIELDDESCRIPTION = :fielddescription "
  				+ " AND GATEWAY_ID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  				+ " AND FIELDVALUE = :iban "
  				+ " AND APPLICATIONID LIKE 'EPAY%' "
  				+ " AND ID_OPERAZIONE = :idOperazione ";
  				
  		if (!StringUtils.isBlank(ibanAppoggio)) {
  			
  			query += " INTERSECT "
  	  				+ " SELECT APPLICATIONID "
  	  				+ " FROM APPLICATIONCUSTOMFIELDS_TEMP "
  	  				+ " WHERE FIELDNAME = :ibanAppoggioFieldName "
  	  				+ " AND FIELDDESCRIPTION = :fielddescription "
  	  				+ " AND GATEWAY_ID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  	  				+ " AND FIELDVALUE = :ibanAppoggio "
  	  				+ " AND APPLICATIONID LIKE 'EPAY%'"
  	  				+ " AND ID_OPERAZIONE = :idOperazione ";
  		}else {
  			query += " INTERSECT "
  	  				+ " SELECT APPLICATIONID "
  	  				+ " FROM APPLICATIONCUSTOMFIELDS_TEMP "
  	  				+ " WHERE FIELDNAME = :ibanAppoggioFieldName "
  	  				+ " AND FIELDDESCRIPTION = :fielddescription "
  	  				+ " AND GATEWAY_ID IN (SELECT VALORE FROM TEMPLATE_CONFIGURAZIONE_EPAY WHERE CHIAVE = :chiave) "
  	  				+ " AND (FIELDVALUE = :ibanAppoggioDefaultValue OR FIELDVALUE = :ibanAppoggioEmptyValue OR FIELDVALUE IS NULL)"
  	  				+ " AND APPLICATIONID LIKE 'EPAY%'"
  	  				+ " AND ID_OPERAZIONE = :idOperazione ";
  		}
  		
  		List<String> listApplicationIdIntersect = new ArrayList<String>();
  		listApplicationIdIntersect = (List<String>) query_select(query, queryParameters, new MdpDaoMappers.ApplicationIdStringMapper());

  		if (null == listApplicationIdIntersect || (null != listApplicationIdIntersect && listApplicationIdIntersect.isEmpty())) {
  			log.error(methodName + "NESSUN ApplicationID presente nella TABELLA TEMPORANEA corrispondente all'id operazione : " + queryParameters.get("idOperazione"));
  		} else if (listApplicationIdIntersect.size() > 1) {
  			throw new MdpcoopapplicativaSrvException("ApplicationID NON UNIVOCO presente nella TABELLA TEMPORANEA corrispondente all'id operazione : " +queryParameters.get("idOperazione"));
  		} else {
  			applicationId = listApplicationIdIntersect.get(0);
  		}

  		log.info(methodName + "APPLICATION ID SELEZIONATO DALLA TABELLA TEMPORANEA: " + applicationId);
  		log.info(methodName + "END");

  		return applicationId;
  	}
}
