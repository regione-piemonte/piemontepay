/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.core.business.dao.ApplicationcustomfieldsDao;
import it.csi.mdp.core.business.dto.Applicationcustomfields;
import it.csi.mdp.core.business.exceptions.ApplicationcustomfieldsDaoException;
import it.csi.mdp.core.util.Constants;
import it.csi.mdp.core.util.EncryptionDecryptionUtil;

public class ApplicationcustomfieldsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Applicationcustomfields>,
		ApplicationcustomfieldsDao
{
	protected SimpleJdbcTemplate jdbcTemplate;
	private String sKey = null;
	protected DataSource dataSource;
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);

	public void setsKey(String sKey)
	{
		this.sKey = sKey;
	}

	public String getsKey()
	{
		return sKey;
	}

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	@Transactional
	public void insert(Applicationcustomfields dto)
	{
		

		if (this.sKey != null && dto.getFieldvalue()!=null)
		{

			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			// Instantiate the cipher

			byte[] original = null;
			byte[] encrypted = null;
			try
			{
				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
				
				encrypted = cipher.doFinal(dto.getFieldvalue().getBytes());
				log.debug("encrypted string: " + asHex(encrypted));
				dto.setFieldvalue(new String(org.apache.ws.security.util.Base64.encode(encrypted)));
				
			} catch (InvalidKeyException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		jdbcTemplate.update("INSERT INTO " + getTableName() + 
				" ( applicationid, fieldname, fieldvalue, gateway_id, fielddescription ) VALUES ( ?, ?, ?, ?, ? )",
				dto.getApplicationid(),dto.getFieldname(),dto.getFieldvalue(),dto.getGatewayId(),dto.getFielddescription());
		
	}

	/**
	 * Method 'insert'
	 * 
	 * @param dto
	 */
	@Transactional
	public void update(Applicationcustomfields dto)
	{

		if (this.sKey != null && dto.getFieldvalue()!=null)
		{
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			byte[] original = null;
			byte[] encrypted = null;
			try
			{
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
				encrypted = cipher.doFinal(dto.getFieldvalue().getBytes());
				log.debug("encrypted string: " + asHex(encrypted));
				dto.setFieldvalue(new String(org.apache.ws.security.util.Base64.encode(encrypted)));
			} catch (InvalidKeyException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		jdbcTemplate.update("UPDATE " + getTableName() + " SET  FIELDVALUE = ? where APPLICATIONID = ? AND FIELDNAME = ? AND gateway_id = ?", dto
				.getFieldvalue(), dto.getApplicationid(), dto.getFieldname(), dto.getGatewayId());
	}

	
	/*** SOSTITUITA CON EncryptionDecryptionUtil.decrypt
	 * 
	private String decryptValueWithSkey(String value) {
		
		
		System.out.println("value = "+value);
		
		//this.setsKey("yYoThaIwl7gff2no8krJ6g==");
		
		System.out.println("sKey = "+this.getsKey());
		
		
	    byte[] decodedKey = Base64.getDecoder().decode(this.getsKey());

	    try {
	    	
	        Cipher cipher = Cipher.getInstance("AES");
	        
	        SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
	        cipher.init(Cipher.DECRYPT_MODE, originalKey);
	        byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(value));
	        return new String(cipherText);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        log.error("decryptValueWithSkey: decrypt fallita!\n"+e.getMessage());
	    }
		
		return null;
	}
	***/
	
	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Applicationcustomfields
	 */
	public Applicationcustomfields mapRow(ResultSet rs, int row) throws SQLException
	{
		Applicationcustomfields dto = new Applicationcustomfields();
		dto.setKeyid(rs.getString(1));
		dto.setApplicationid(rs.getString(2));
		dto.setFieldname(rs.getString(3));
		dto.setFieldvalue(rs.getString(4));
		
		//System.out.println("MIKY MAPROW: "+dto.getFieldvalue());
		//log.debug("mapRow - dto.getFieldvalue() = "+dto.getFieldvalue());
		
		dto.setGatewayId( rs.getString( 5 ) );
		dto.setFielddescription( rs.getString( 6 ) );
		
		if (this.sKey != null && rs.getString(4)!=null)
		{
			
			//System.out.println("MIKY rs.getString(4): "+rs.getString(4));
			
			//dto.setFieldvalue(this.decryptValueWithSkey(rs.getString(4)));
			
			dto.setFieldvalue(EncryptionDecryptionUtil.decrypt(this.getsKey(), rs.getString(4)));
			
			//System.out.println("MIKY  = dto.getFieldvalue(): "+dto.getFieldvalue());
			
			/****
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			// Instantiate the cipher
  
			byte[] original = null;
			byte[] encrypted = rs.getString(4).getBytes();
			try
			{
				encrypted = Base64.decode(new String (encrypted));
				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				original = cipher.doFinal(encrypted);
				String originalString = new String(original);
				log.debug("Original string: " + originalString + " " + asHex(original));
				dto.setFieldvalue(new String(original));
			} catch (InvalidKeyException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			catch (WSSecurityException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
 catch (WSSecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			****/
		}
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "APPLICATIONCUSTOMFIELDS";
	}

	/**
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the
	 * criteria ''.
	 */
	@Transactional
	public List<Applicationcustomfields> findAll() throws ApplicationcustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription FROM " + getTableName() , this);
			//return jdbcTemplate.query("SELECT KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE FROM " + getTableName() + "", this);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the
	 * criteria 'KEYID = :keyid'.
	 */
	@Transactional
	public List<Applicationcustomfields> findWhereKeyidEquals(long keyid) throws ApplicationcustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription FROM " + getTableName() + 
					" WHERE keyid = ? ORDER BY keyid", this,keyid);
			//return jdbcTemplate.query("SELECT KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE FROM " + getTableName()
			//		+ " WHERE KEYID = ? ORDER BY KEYID", this, keyid);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the
	 * criteria 'APPLICATIONID = :applicationid'.
	 */
	@Transactional
	public List<Applicationcustomfields> findWhereApplicationidEquals(String applicationid)
			throws ApplicationcustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription FROM " + getTableName() + 
					" WHERE applicationid = ? ORDER BY applicationid", this,applicationid);
			//return jdbcTemplate.query("SELECT KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE FROM " + getTableName()
			//		+ " WHERE APPLICATIONID = ? ORDER BY APPLICATIONID", this, applicationid);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the
	 * criteria 'APPLICATIONID = :applicationid'.
	 */
	@Transactional
	public List<Applicationcustomfields> findWhereApplicationidAndFieldNameEquals(String applicationid, String fieldname)
			throws ApplicationcustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE, gateway_id, fielddescription FROM " + getTableName()
					+ " WHERE APPLICATIONID = ? AND FIELDNAME = ? ORDER BY APPLICATIONID", this, applicationid, fieldname);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}

	}

	@Transactional
	public List<Applicationcustomfields> findWhereApplicationidAndGatewayIdAndFieldNameEquals(String applicationid, String gatewayid, String fieldname)
			throws ApplicationcustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE, gateway_id, fielddescription FROM " + getTableName()
					+ " WHERE APPLICATIONID = ? AND gateway_id = ? AND FIELDNAME = ? ORDER BY APPLICATIONID", this, applicationid,gatewayid, fieldname);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}

	}
	
	/**
	 * Returns all rows from the APPLICATIONCUSTOMFIELDS table that match the
	 * criteria 'FIELDNAME = :fieldname'.
	 */
	@Transactional
	public List<Applicationcustomfields> findWhereFieldnameEquals(String fieldname) throws ApplicationcustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE, gateway_id, fielddescription FROM " + getTableName()
					+ " WHERE FIELDNAME = ? ORDER BY FIELDNAME", this, fieldname);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}

	}
	
	
	public List<Applicationcustomfields> findWhereApplicationidAndGatewayIdEquals(String applicationid, String gatewayid) throws ApplicationcustomfieldsDaoException
	{
		try
		{
			return jdbcTemplate.query("SELECT KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE, gateway_id, fielddescription FROM " + getTableName()
					+ " WHERE APPLICATIONID = ? AND gateway_id = ? ORDER BY APPLICATIONID", this, applicationid, gatewayid);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}
	}

 
	public static String asHex(byte buf[])
	{
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++)
		{
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	public void delete(String appid, String gwid)
	{
		jdbcTemplate.update("DELETE FROM "+ getTableName()+" WHERE applicationid = ? AND gateway_id = ? ", appid,gwid);
		
	}

	@Transactional
	public List<Applicationcustomfields> findDistinctDomini() throws ApplicationcustomfieldsDaoException
	{
		try
		{
			String strSql=" select KEYID, APPLICATIONID, FIELDNAME, FIELDVALUE, gateway_id, fielddescription from applicationcustomfields ";
			strSql=strSql+" where  ";
			strSql=strSql+" applicationid in ( ";
			strSql=strSql+" select distinct applicationid from applicationcustomfields where fieldname = 'identificativointermediarioPA' ";
			strSql=strSql+" )  ";
			strSql=strSql+" and TRIM(fieldname) in ('identificativoDominio','identificativointermediarioPA','identificativoStazioneIntermediarioPA','passwordDominioNodoSpc','portaDiDominio') order by 1,2 ";

			
			return jdbcTemplate.query(strSql, this);
		} catch (Exception e)
		{
			throw new ApplicationcustomfieldsDaoException("Query failed", e);
		}
	}
	
	
}
