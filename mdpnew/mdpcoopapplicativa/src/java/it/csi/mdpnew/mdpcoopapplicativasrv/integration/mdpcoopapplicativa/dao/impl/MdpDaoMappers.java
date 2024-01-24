/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao.impl;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.util.Base64;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ApplicationcustomfieldsDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ApplicationcustomfieldsTempDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.ConfigDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.DescrizioneApplicativoDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.GatewayDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db.IuvAttributeDTO;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.dao.AbstractDao;
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


public class MdpDaoMappers {

    public static class MdpTApplicationMapper implements ParameterizedRowMapper<Object> {

        public MdpTApplication mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTApplication d = new MdpTApplication ();

            d.setId ( rs.getString ( "id" ) );
            d.setApplicationname ( rs.getString ( "applicationname" ) );
            d.setReferentecsi ( rs.getString ( "referentecsi" ) );
            d.setCliente ( rs.getString ( "cliente" ) );
            d.setProgetto ( rs.getString ( "progetto" ) );
            d.setNote ( rs.getString ( "note" ) );
            d.setEsercemail ( rs.getString ( "esercemail" ) );
            d.setNumeroverde ( rs.getString ( "numeroverde" ) );
            d.setValido_dal ( rs.getTimestamp ( "valido_dal" ) );
            d.setValido_al ( rs.getTimestamp ( "valido_al" ) );
            d.setRedirect_newmdp ( rs.getBigDecimal ( "redirect_newmdp" ) );

            return d;
        }
    }

    public static class MdpTErroreMapper implements ParameterizedRowMapper<Object> {

        public MdpTErrore mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTErrore d = new MdpTErrore ();

            d.setId ( rs.getLong ( "id" ) );
            d.setCodiceErrore ( rs.getString ( "codice_errore" ) );
            d.setDescrizioneErrore ( rs.getString ( "descrizione_errore" ) );
            d.setTipologia ( rs.getString ( "tipologia" ) );
            d.setFlagMail ( rs.getBoolean ( "flag_mail" ) );
            d.setFlagRielaborazione ( rs.getBoolean ( "flag_rielaborazione" ) );

            return d;
        }
    }

    public static class MdpTApplicationTempMapper implements ParameterizedRowMapper<Object> {

        public MdpTApplicationTemp mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTApplicationTemp d = new MdpTApplicationTemp ();

            d.setId ( rs.getString ( "id" ) );
            d.setApplicationname ( rs.getString ( "applicationname" ) );
            d.setReferentecsi ( rs.getString ( "referentecsi" ) );
            d.setCliente ( rs.getString ( "cliente" ) );
            d.setProgetto ( rs.getString ( "progetto" ) );
            d.setNote ( rs.getString ( "note" ) );
            d.setEsercemail ( rs.getString ( "esercemail" ) );
            d.setNumeroverde ( rs.getString ( "numeroverde" ) );
            d.setValido_dal ( rs.getTimestamp ( "valido_dal" ) );
            d.setValido_al ( rs.getTimestamp ( "valido_al" ) );
            d.setRedirect_newmdp ( rs.getBigDecimal ( "redirect_newmdp" ) );
            d.setIdOperazione ( rs.getString ( "id_operazione" ) );

            return d;
        }
    }

    public static class MdpTApplicationCustomFieldsTempMapper implements ParameterizedRowMapper<Object> {

        public MdpTApplicationCustomFieldsTemp mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTApplicationCustomFieldsTemp d = new MdpTApplicationCustomFieldsTemp ();

            d.setApplicationid ( rs.getString ( "applicationid" ) );
            d.setFielddescription ( rs.getString ( "fielddescription" ) );
            d.setFieldname ( rs.getString ( "fieldname" ) );
            d.setFieldvalue ( rs.getString ( "fieldvalue" ) );
            d.setGateway_id ( rs.getString ( "gateway_id" ) );
            d.setIdOperazione ( rs.getString ( "id_operazione" ) );

            return d;
        }
    }

    public static class MdpTApplicationDetailMapper implements ParameterizedRowMapper<Object> {

        public MdpTApplicationDetail mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTApplicationDetail d = new MdpTApplicationDetail ();
            
            d.setApplicationid ( rs.getString ( "applicationid" ) );
            d.setApplicationurlback ( rs.getString ( "applicationurlback" ) );
            d.setApplicationurlcancel ( rs.getString ( "applicationurlcancel" ) );
            d.setApplicationurlerror ( rs.getString ( "applicationurlerror" ) );
            d.setApplicationurlresponseko ( rs.getString ( "applicationurlresponseko" ) );
            d.setApplicationurlresponseok ( rs.getString ( "applicationurlresponseok" ) );
            d.setCodiceistat ( rs.getString ( "codiceistat" ) );
            d.setContocorrenteposte ( rs.getString ( "contocorrenteposte" ) );
            d.setEnabled ( rs.getString ( "enabled" ) );
            d.setFlagreturlmdp ( rs.getString ( "flagreturlmdp" ) );
            d.setGatewayid ( rs.getString ( "gatewayid" ) );
            d.setMac_avvio ( rs.getString ( "mac_avvio" ) );
            d.setMac_esito ( rs.getString ( "mac_esito" ) );
            d.setMail2buyerko ( rs.getString ( "mail2buyerko" ) );
            d.setMail2buyerok ( rs.getString ( "mail2buyerok" ) );
            d.setMail2esercko ( rs.getString ( "mail2esercko" ) );
            d.setMail2esercok ( rs.getString ( "mail2esercok" ) );
            d.setMail2sysko ( rs.getString ( "mail2sysko" ) );
            d.setMail2sysok ( rs.getString ( "mail2sysok" ) );
            d.setMerchantid ( rs.getString ( "merchantid" ) );
            d.setMerchantidpassword ( rs.getString ( "merchantidpassword" ) );
            d.setPaymentmodeid ( rs.getString ( "paymentmodeid" ) );
            d.setPgactioncode ( rs.getBigDecimal ( "pgactioncode" ) );
            d.setPgcontabcode ( rs.getBigDecimal ( "pgcontabcode" ) );
            d.setSogliaa ( rs.getBigDecimal ( "sogliaa" ) );;
            d.setSogliada ( rs.getBigDecimal ( "sogliada" ) );
            d.setTipobollettinoposte ( rs.getString ( "tipobollettinoposte" ) );
            d.setTipocommissione ( rs.getString ( "tipocommissione" ) );
            d.setTipodocumentoposte ( rs.getString ( "tipodocumentoposte" ) );
            d.setValorecommissionemax ( rs.getBigDecimal ( "valorecommissionemax" ) );
            d.setValorecommissionemin ( rs.getBigDecimal ( "valorecommissionemin" ) );

            return d;            
        }
    }
    
    public static class MdpTApplicationDetailTempMapper implements ParameterizedRowMapper<Object> {

        public MdpTApplicationDetailTemp mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTApplicationDetailTemp d = new MdpTApplicationDetailTemp ();

            d.setApplicationid ( rs.getString ( "applicationid" ) );
            d.setApplicationurlback ( rs.getString ( "applicationurlback" ) );
            d.setApplicationurlcancel ( rs.getString ( "applicationurlcancel" ) );
            d.setApplicationurlerror ( rs.getString ( "applicationurlerror" ) );
            d.setApplicationurlresponseko ( rs.getString ( "applicationurlresponseko" ) );
            d.setApplicationurlresponseok ( rs.getString ( "applicationurlresponseok" ) );
            d.setCodiceistat ( rs.getString ( "codiceistat" ) );
            d.setContocorrenteposte ( rs.getString ( "contocorrenteposte" ) );
            d.setEnabled ( rs.getString ( "enabled" ) );
            d.setFlagreturlmdp ( rs.getString ( "flagreturlmdp" ) );
            d.setGatewayid ( rs.getString ( "gatewayid" ) );
            d.setIdOperazione ( rs.getString ( "id_operazione" ) );
            d.setMac_avvio ( rs.getString ( "mac_avvio" ) );
            d.setMac_esito ( rs.getString ( "mac_esito" ) );
            d.setMail2buyerko ( rs.getString ( "mail2buyerko" ) );
            d.setMail2buyerok ( rs.getString ( "mail2buyerok" ) );
            d.setMail2esercko ( rs.getString ( "mail2esercko" ) );
            d.setMail2esercok ( rs.getString ( "mail2esercok" ) );
            d.setMail2sysko ( rs.getString ( "mail2sysko" ) );
            d.setMail2sysok ( rs.getString ( "mail2sysok" ) );
            d.setMerchantid ( rs.getString ( "merchantid" ) );
            d.setMerchantidpassword ( rs.getString ( "merchantidpassword" ) );
            d.setPaymentmodeid ( rs.getString ( "paymentmodeid" ) );
            d.setPgactioncode ( rs.getBigDecimal ( "pgactioncode" ) );
            d.setPgcontabcode ( rs.getBigDecimal ( "pgcontabcode" ) );
            d.setSogliaa ( rs.getBigDecimal ( "sogliaa" ) );;
            d.setSogliada ( rs.getBigDecimal ( "sogliada" ) );
            d.setTipobollettinoposte ( rs.getString ( "tipobollettinoposte" ) );
            d.setTipocommissione ( rs.getString ( "tipocommissione" ) );
            d.setTipodocumentoposte ( rs.getString ( "tipodocumentoposte" ) );
            d.setValorecommissionemax ( rs.getBigDecimal ( "valorecommissionemax" ) );
            d.setValorecommissionemin ( rs.getBigDecimal ( "valorecommissionemin" ) );

            return d;
        }
    }

    public static class MdpTApplicationCustomFieldsMapper implements ParameterizedRowMapper<Object> {

        public MdpTApplicationCustomFields mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTApplicationCustomFields d = new MdpTApplicationCustomFields ();

            d.setApplicationid ( rs.getString ( "applicationid" ) );
            d.setFielddescription ( rs.getString ( "fielddescription" ) );
            d.setFieldname ( rs.getString ( "fieldname" ) );
            d.setFieldvalue ( rs.getString ( "fieldvalue" ) );
            d.setGateway_id ( rs.getString ( "gateway_id" ) );
            
            return d;
        }
    }

    public static class MdpTEnteMapper implements ParameterizedRowMapper<Object> {

        public MdpTEnte mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTEnte d = new MdpTEnte ();

            d.setAttivo ( rs.getString ( "attivo" ) );
            d.setCodice_segregazione ( rs.getString ( "codice_segregazione" ) );
            d.setDescrizione ( rs.getString ( "descrizione" ) );
            d.setEnte_id ( rs.getString ( "ente_id" ) );
            d.setFlag_invio_flusso_base ( rs.getBoolean ( "flag_invio_flusso_base" ) );
            d.setFlag_invio_flusso_esteso ( rs.getBoolean ( "flag_invio_flusso_esteso" ) );
            d.setPartita_iva ( rs.getString ( "partita_iva" ) );
            d.setProgressivo_application_id ( rs.getInt ( "progressivo_application_id" ) );

            return d;
        }
    }    
    public static class MdpTEnteTempMapper implements ParameterizedRowMapper<Object> {

        public MdpTEnteTemp mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTEnteTemp d = new MdpTEnteTemp ();

            d.setAttivo ( rs.getString ( "attivo" ) );
            d.setCodice_segregazione ( rs.getString ( "codice_segregazione" ) );
            d.setDescrizione ( rs.getString ( "descrizione" ) );
            d.setEnte_id ( rs.getString ( "ente_id" ) );
            d.setFlag_invio_flusso_base ( rs.getBoolean ( "flag_invio_flusso_base" ) );
            d.setFlag_invio_flusso_esteso ( rs.getBoolean ( "flag_invio_flusso_esteso" ) );
            d.setPartita_iva ( rs.getString ( "partita_iva" ) );
            d.setProgressivo_application_id ( rs.getInt ( "progressivo_application_id" ) );
            d.setIdOperazione (rs.getString ( "id_operazione" ) );
            
            return d;
        }
    }    
    public static class MdpTTemplateMapper implements ParameterizedRowMapper<Object> {

        public MdpTTemplate mapRow ( ResultSet rs, int row ) throws SQLException {

            MdpTTemplate d = new MdpTTemplate ();

            d.setGateway_id ( rs.getString ( "gateway_id" ) );
            d.setChiave ( rs.getString ( "chiave" ) );
            d.setFlag_attivo ( rs.getBoolean ( "flag_attivo" ) );
            d.setProgetto ( rs.getString ( "progetto" ) );
            d.setTabella_riferimento ( rs.getString ( "tabella_riferimento" ) );
            d.setValore ( rs.getString ( "valore" ) );
            d.setDescrizione ( rs.getString ( "descrizione" ) );
            d.setCampo_cifrato ( rs.getBoolean ( "campo_cifrato" ));
            
            return d;
        }
    }

    public static class MdpTStringMapper implements ParameterizedRowMapper<Object> {

        public String mapRow ( ResultSet rs, int row ) throws SQLException {

            String d = new String ();

            d = rs.getString ( 0 );

            return d;
        }
    }

    protected static Logger log = Logger.getLogger ( AbstractDao.LOGGER_PREFIX + ".dao" );

    public static class ConfigDTOMapper implements ParameterizedRowMapper<Object> {

        public ConfigDTO mapRow ( ResultSet rs, int row ) throws SQLException {

            ConfigDTO d = new ConfigDTO ();

            d.setKey ( rs.getString ( "key" ) );
            d.setValue ( rs.getString ( "value" ) );
            d.setDescrizione ( rs.getString ( "descrizione" ) );

            return d;
        }
    }

    public static class EnteIDApplicationDTOMapper implements ParameterizedRowMapper<Object> {

        public DescrizioneApplicativoDTO mapRow ( ResultSet rs, int row ) throws SQLException {

            DescrizioneApplicativoDTO d = new DescrizioneApplicativoDTO ();

            d.setDescrizione ( rs.getString ( "descrizione" ) );
            d.setPartitaIva ( rs.getString ( "partita_iva" ) );
            d.setApplicationName ( rs.getString ( "applicationname" ) );
            d.setIdApplication ( rs.getString ( "id" ) );
            d.setReferenteCsi ( rs.getString ( "referentecsi" ) );
            d.setCliente ( rs.getString ( "cliente" ) );
            d.setIdEnte ( rs.getString ( "idente" ) );
            d.setCodiceSegregazione ( StringUtils.trimToEmpty ( rs.getString ( "codice_segregazione" ) ) );
            return d;
        }
    }

    public static class IuvAttributeDTOMapper implements ParameterizedRowMapper<Object> {

        public IuvAttributeDTO mapRow ( ResultSet rs, int row ) throws SQLException {

            IuvAttributeDTO d = new IuvAttributeDTO ();

            d.setIdEnte ( rs.getString ( "ente_id" ) );
            d.setDataValidita ( rs.getString ( "dataValidita" ) );
            d.setProgressivo ( new BigInteger ( rs.getString ( "progressivo" ) ) );
            d.setDataDismissione ( rs.getString ( "data_dismissione" ) );

            return d;
        }
    }

    public static class GatewayDTOMapper implements ParameterizedRowMapper<Object> {

        public GatewayDTO mapRow ( ResultSet rs, int row ) throws SQLException {

            GatewayDTO d = new GatewayDTO ();

            d.setGatewayid ( rs.getString ( "gateway_id" ) );
            d.setGatewaydescription ( rs.getString ( "gateway_description" ) );
            d.setGatewayprovider ( rs.getString ( "gateway_provider" ) );
            d.setValidodal ( rs.getTimestamp ( "valido_dal" ) );
            d.setValidoal ( rs.getTimestamp ( "valido_al" ) );
            d.setGatewayservicename ( rs.getString ( "gatewayservicename" ) );
            d.setFlagnodo ( rs.getBoolean ( "flag_nodo" ) );

            return d;
        }
    }

    public static class ApplicationcustomfieldsDTOMapper implements ParameterizedRowMapper<Object> {

        String sKey = null;

        public ApplicationcustomfieldsDTOMapper ( String sKey ) {
            this.sKey = sKey;
        }

        public ApplicationcustomfieldsDTO mapRow ( ResultSet rs, int row ) throws SQLException {

            ApplicationcustomfieldsDTO d = new ApplicationcustomfieldsDTO ();
            d.setKeyid ( rs.getString ( "keyid" ) );
            d.setApplicationid ( rs.getString ( "applicationid" ) );
            d.setFieldname ( rs.getString ( "fieldname" ) );
            d.setFieldvalue ( rs.getString ( "fieldvalue" ) );
            d.setGatewayId ( rs.getString ( "gateway_id" ) );
            d.setFielddescription ( rs.getString ( "fielddescription" ) );

            if ( sKey != null && d.getFieldvalue () != null ) {
                byte [] raw = sKey.getBytes ();
                SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );

                byte [] original = null;
                byte [] encrypted = d.getFieldvalue ().getBytes ();
                try {
                    encrypted = Base64.decode ( new String ( encrypted ) );
                    Cipher cipher = Cipher.getInstance ( "AES" );

                    cipher.init ( Cipher.DECRYPT_MODE, skeySpec );
                    original = cipher.doFinal ( encrypted );
                    String originalString = new String ( original );
                    log.debug ( "Original string: " + originalString + " " + asHex ( original ) );
                    d.setFieldvalue ( new String ( original ) );
                } catch ( InvalidKeyException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e );
                } catch ( NoSuchAlgorithmException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e );
                } catch ( NoSuchPaddingException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e );
                } catch ( IllegalBlockSizeException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e );
                } catch ( BadPaddingException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e );
                } catch ( WSSecurityException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsDTO::mapRow] ERROR", e );
                }
            }

            return d;
        }
    }

    public static class ApplicationcustomfieldsTempDTOMapper implements ParameterizedRowMapper<Object> {

        String sKey = null;

        public ApplicationcustomfieldsTempDTOMapper ( String sKey ) {
            this.sKey = sKey;
        }

        public ApplicationcustomfieldsTempDTO mapRow ( ResultSet rs, int row ) throws SQLException {

            ApplicationcustomfieldsTempDTO d = new ApplicationcustomfieldsTempDTO ();
            d.setApplicationid ( rs.getString ( "applicationid" ) );
            d.setFieldname ( rs.getString ( "fieldname" ) );
            d.setFieldvalue ( rs.getString ( "fieldvalue" ) );
            d.setGatewayId ( rs.getString ( "gateway_id" ) );
            d.setFielddescription ( rs.getString ( "fielddescription" ) );

            log.debug ( "DECODING: " + d.getFieldvalue () );
            
            if ( sKey != null && d.getFieldvalue () != null ) {
                byte [] raw = sKey.getBytes ();
                SecretKeySpec skeySpec = new SecretKeySpec ( raw, "AES" );

                byte [] original = null;
                byte [] encrypted = d.getFieldvalue ().getBytes ();
                try {
                    encrypted = Base64.decode ( new String ( encrypted ) );
                    Cipher cipher = Cipher.getInstance ( "AES" );

                    cipher.init ( Cipher.DECRYPT_MODE, skeySpec );
                    original = cipher.doFinal ( encrypted );
                    String originalString = new String ( original );
                    log.debug ( "Original string: " + originalString + " " + asHex ( original ) );
                    d.setFieldvalue ( new String ( original ) );
                } catch ( InvalidKeyException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsTempDTO::mapRow] ERROR", e );
                } catch ( NoSuchAlgorithmException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsTempDTO::mapRow] ERROR", e );
                } catch ( NoSuchPaddingException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsTempDTO::mapRow] ERROR", e );
                } catch ( IllegalBlockSizeException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsTempDTO::mapRow] ERROR", e );
                } catch ( BadPaddingException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsTempDTO::mapRow] ERROR", e );
                } catch ( WSSecurityException e ) {
                    log.error ( "[MdpDaoImpl::ApplicationcustomfieldsTempDTO::mapRow] ERROR", e );
                }
            }

            return d;
        }
    }    
    public static String asHex ( byte buf[] ) {
        StringBuffer strbuf = new StringBuffer ( buf.length * 2 );
        int i;

        for ( i = 0; i < buf.length; i++ ) {
            if ( ( (int) buf [i] & 0xff ) < 0x10 )
                strbuf.append ( "0" );

            strbuf.append ( Long.toString ( (int) buf [i] & 0xff, 16 ) );
        }

        return strbuf.toString ();
    }
    
    
    //CSI_PAG-316
	public static class ApplicationIdStringMapper implements ParameterizedRowMapper<Object> {

		public String mapRow(ResultSet rs, int row) throws SQLException {

			String d = new String();

			d = rs.getString("APPLICATIONID");

			return d;
		}
	}

}
