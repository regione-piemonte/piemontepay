/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.MdpBckusersDao;
import it.csi.mdp.core.business.dto.MdpBckusers;
import it.csi.mdp.core.business.dto.MdpBckusersPk;
import it.csi.mdp.core.business.exceptions.MdpBckusersDaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.apache.ws.security.util.Base64;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class MdpBckusersDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpBckusers>, MdpBckusersDao
{
	protected SimpleJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;
	private String sKey = null;
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
	 * @return MdpBckusersPk
	 */
	@Transactional
	public MdpBckusersPk insert(MdpBckusers dto)
	{
		if (this.sKey != null && dto.getPwdservizio()!=null)
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
				
				encrypted = cipher.doFinal(dto.getPwdservizio().getBytes());
				
				dto.setPwdservizio(new String(Base64.encode(encrypted)));
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
		
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( firstname, lastname, codfisc, email, pwdservizio ) VALUES ( ?, ?, ?, ?, ? )",dto.getFirstname(),dto.getLastname(),dto.getCodfisc(),dto.getEmail(),dto.getPwdservizio());
		int iduser = jdbcTemplate.queryForInt("SELECT MAX(IDUSER) FROM " + getTableName());
		MdpBckusersPk upk = new MdpBckusersPk(iduser);
		return upk;
	}

	/** 
	 * Updates a single row in the mdp_bckusers table.
	 */
	@Transactional
	public void update(MdpBckusersPk pk, MdpBckusers dto) throws MdpBckusersDaoException
	{
		if (this.sKey != null && dto.getPwdservizio()!=null)
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
				
				encrypted = cipher.doFinal(dto.getPwdservizio().getBytes());
				
				dto.setPwdservizio(new String(Base64.encode(encrypted)));
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
		jdbcTemplate.update("UPDATE " + getTableName() + " SET firstname = ?, lastname = ?, codfisc = ?, email = ?, iduser = ?, pwdservizio = ? WHERE iduser = ?",dto.getFirstname(),dto.getLastname(),dto.getCodfisc(),dto.getEmail(),dto.getIduser(),dto.getPwdservizio(),pk.getIduser());
	}

	/** 
	 * Deletes a single row in the mdp_bckusers table.
	 */
	@Transactional
	public void delete(MdpBckusersPk pk) throws MdpBckusersDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE iduser = ?",pk.getIduser());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MdpBckusers
	 */
	public MdpBckusers mapRow(ResultSet rs, int row) throws SQLException
	{
		MdpBckusers dto = new MdpBckusers();
		dto.setFirstname( rs.getString( 1 ) );
		dto.setLastname( rs.getString( 2 ) );
		dto.setCodfisc( rs.getString( 3 ) );
		dto.setEmail( rs.getString( 4 ) );
		dto.setIduser( rs.getInt( 5 ) );
		dto.setPwdservizio( rs.getString( 6 ) );
		
		if (this.sKey != null && rs.getString(6)!=null)
		{
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			// Instantiate the cipher
  
			byte[] original = null;
			byte[] encrypted = rs.getString(6).getBytes();
			try
			{
				encrypted = Base64.decode(new String (encrypted));
				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				original = cipher.doFinal(encrypted);
				
				
				dto.setPwdservizio(new String(original));
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
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
		return "mdp_bckusers";
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'iduser = :iduser'.
	 */
	@Transactional
	public MdpBckusers findByPrimaryKey(int iduser) throws MdpBckusersDaoException
	{
		try {
			List<MdpBckusers> list = jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " WHERE iduser = ?", this,iduser);
			return list.size() == 0 ? null : list.get(0);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria ''.
	 */
	@Transactional
	public List<MdpBckusers> findAll() throws MdpBckusersDaoException
	{
		try {
			return jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " ORDER BY iduser", this);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'firstname = :firstname'.
	 */
	@Transactional
	public List<MdpBckusers> findWhereFirstnameEquals(String firstname) throws MdpBckusersDaoException
	{
		try {
			return jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " WHERE firstname = ? ORDER BY firstname", this,firstname);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'lastname = :lastname'.
	 */
	@Transactional
	public List<MdpBckusers> findWhereLastnameEquals(String lastname) throws MdpBckusersDaoException
	{
		try {
			return jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " WHERE lastname = ? ORDER BY lastname", this,lastname);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'codfisc = :codfisc'.
	 */
	@Transactional
	public List<MdpBckusers> findWhereCodfiscEquals(String codfisc) throws MdpBckusersDaoException
	{
		try {
			return jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " WHERE codfisc = ? ORDER BY codfisc", this,codfisc);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'email = :email'.
	 */
	@Transactional
	public List<MdpBckusers> findWhereEmailEquals(String email) throws MdpBckusersDaoException
	{
		try {
			return jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " WHERE email = ? ORDER BY email", this,email);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'iduser = :iduser'.
	 */
	@Transactional
	public List<MdpBckusers> findWhereIduserEquals(int iduser) throws MdpBckusersDaoException
	{
		try {
			return jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " WHERE iduser = ? ORDER BY iduser", this,iduser);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the mdp_bckusers table that match the criteria 'pwdservizio = :pwdservizio'.
	 */
	@Transactional
	public List<MdpBckusers> findWherePwdservizioEquals(String pwdservizio) throws MdpBckusersDaoException
	{
		try {
			return jdbcTemplate.query("SELECT firstname, lastname, codfisc, email, iduser, pwdservizio FROM " + getTableName() + " WHERE pwdservizio = ? ORDER BY pwdservizio", this,pwdservizio);
		}
		catch (Exception e) {
			throw new MdpBckusersDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns the rows from the mdp_bckusers table that matches the specified primary-key value.
	 */
	public MdpBckusers findByPrimaryKey(MdpBckusersPk pk) throws MdpBckusersDaoException
	{
		return findByPrimaryKey( pk.getIduser() );
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

}
