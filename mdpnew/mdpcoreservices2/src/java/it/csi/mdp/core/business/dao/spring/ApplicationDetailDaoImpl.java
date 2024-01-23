/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.ApplicationDetailDao;
import it.csi.mdp.core.business.dto.ApplicationDetail;
import it.csi.mdp.core.business.dto.ApplicationDetailPk;
import it.csi.mdp.core.business.exceptions.ApplicationDetailDaoException;
import it.csi.mdp.core.util.Constants;
import it.csi.mdp.core.util.EncryptionDecryptionUtil;
import it.csi.mdp.core.util.LoggerUtil;

import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class ApplicationDetailDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ApplicationDetail>,
		ApplicationDetailDao
{
	protected SimpleJdbcTemplate jdbcTemplate;
	private String sKey = null;
	protected DataSource dataSource;
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);

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
	 * @return ApplicationDetailPk
	 */
	@Transactional
	public ApplicationDetailPk insert(ApplicationDetail dto)
	{
		if (this.sKey != null)
		{

			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			// Instantiate the cipher

			byte[] encrypted = null;
			try
			{
				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

				if (dto.getMacAvvio() != null)
				{
					encrypted = cipher.doFinal(dto.getMacAvvio().getBytes());
					log.debug("encrypted string: " + asHex(encrypted));

					dto.setMacAvvio(new String(Base64.encode(encrypted)));
				}

				if (dto.getMacEsito() != null)
				{
					encrypted = cipher.doFinal(dto.getMacEsito().getBytes());
					log.debug("encrypted string: " + asHex(encrypted));
					dto.setMacEsito(new String(Base64.encode(encrypted)));
				}

				if (dto.getMerchantidpassword() != null)
				{
					encrypted = cipher.doFinal(dto.getMerchantidpassword().getBytes());
					log.debug("encrypted string: " + asHex(encrypted));
					dto.setMerchantidpassword(new String(Base64.encode(encrypted)));
				}

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

		jdbcTemplate
				.update(
						"INSERT INTO "
								+ getTableName()
								+ " ( applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",
						dto.getApplicationid(), dto.getGatewayid(), dto.getPaymentmodeid(), dto.getMerchantid(), dto
								.getMerchantidpassword(), dto.isPgactioncodeNull() ? null : dto.getPgactioncode(), dto
								.getFlagreturlmdp(), dto.getApplicationurlresponseok(), dto.getApplicationurlresponseko(), dto
								.getTipocommissione(), dto.getValorecommissionemin(), dto.isSogliadaNull() ? null : dto
								.getSogliada(), dto.isSogliaaNull() ? null : dto.getSogliaa(),
						dto.isValorecommissionemaxNull() ? null : dto.getValorecommissionemax(), dto.getEnabled(), dto
								.isPgcontabcodeNull() ? null : dto.getPgcontabcode(), dto.getApplicationurlback(), dto
								.getMacAvvio(), dto.getMacEsito(), dto.getCodiceistat(), dto.getTipobollettinoposte(), dto
								.getContocorrenteposte(), dto.getTipodocumentoposte(), dto.getMail2buyerok(),
						dto.getMail2buyerko(), dto.getMail2esercko(), dto.getMail2esercok(), dto.getMail2sysko(), dto
								.getMail2sysok(), dto.getApplicationurlcancel(), dto.getApplicationurlerror());
		return dto.createPk();
	}

	/**
	 * Updates a single row in the applicationdetail table.
	 */
	@Transactional
	public void update(ApplicationDetailPk pk, ApplicationDetail dto) throws ApplicationDetailDaoException
	{
		if (this.sKey != null)
		{

			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			// Instantiate the cipher

			byte[] encrypted = null;
			try
			{
				Cipher cipher = Cipher.getInstance("AES");

				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
				if (dto.getMacAvvio() != null)
				{
					encrypted = cipher.doFinal(dto.getMacAvvio().getBytes());
					log.debug("encrypted string: " + asHex(encrypted));
					dto.setMacAvvio(new String(Base64.encode(encrypted)));
				}

				if (dto.getMacEsito() != null)
				{
					encrypted = cipher.doFinal(dto.getMacEsito().getBytes());
					log.debug("encrypted string: " + asHex(encrypted));
					dto.setMacEsito(new String(Base64.encode(encrypted)));
				}

				if (dto.getMerchantidpassword() != null)
				{
					encrypted = cipher.doFinal(dto.getMerchantidpassword().getBytes());
					log.debug("encrypted string: " + asHex(encrypted));
					dto.setMerchantidpassword(new String(Base64.encode(encrypted)));
				}

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

		jdbcTemplate
				.update(
						"UPDATE "
								+ getTableName()
								+ " SET applicationid = ?, gatewayid = ?, paymentmodeid = ?, merchantid = ?, merchantidpassword = ?, pgactioncode = ?, flagreturlmdp = ?, applicationurlresponseok = ?, applicationurlresponseko = ?, tipocommissione = ?, valorecommissionemin = ?, sogliada = ?, sogliaa = ?, valorecommissionemax = ?, enabled = ?, pgcontabcode = ?, applicationurlback = ?, mac_avvio = ?, mac_esito = ?, codiceistat = ?, tipobollettinoposte = ?, contocorrenteposte = ?, tipodocumentoposte = ?, mail2buyerok = ?, mail2buyerko = ?, mail2esercko = ?, mail2esercok = ?, mail2sysko = ?, mail2sysok = ?, applicationurlcancel = ?, applicationurlerror = ? WHERE applicationid = ? AND gatewayid = ? AND paymentmodeid = ?",
						dto.getApplicationid(), dto.getGatewayid(), dto.getPaymentmodeid(), dto.getMerchantid(), dto
								.getMerchantidpassword(), dto.getPgactioncode(), dto.getFlagreturlmdp(), dto
								.getApplicationurlresponseok(), dto.getApplicationurlresponseko(), dto.getTipocommissione(), dto
								.getValorecommissionemin(), dto.getSogliada(), dto.getSogliaa(), dto.getValorecommissionemax(), dto
								.getEnabled(), dto.getPgcontabcode(), dto.getApplicationurlback(), dto.getMacAvvio(), dto
								.getMacEsito(), dto.getCodiceistat(), dto.getTipobollettinoposte(), dto.getContocorrenteposte(),
						dto.getTipodocumentoposte(), dto.getMail2buyerok(), dto.getMail2buyerko(), dto.getMail2esercko(), dto
								.getMail2esercok(), dto.getMail2sysko(), dto.getMail2sysok(), dto.getApplicationurlcancel(), dto
								.getApplicationurlerror(), pk.getApplicationid(), pk.getGatewayid(), pk.getPaymentmodeid());
	}

	/**
	 * Deletes a single row in the applicationdetail table.
	 */
	@Transactional
	public void delete(ApplicationDetailPk pk) throws ApplicationDetailDaoException
	{
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE applicationid = ? AND gatewayid = ? AND paymentmodeid = ?",
				pk.getApplicationid(), pk.getGatewayid(), pk.getPaymentmodeid());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return ApplicationDetail
	 */
	public ApplicationDetail mapRow(ResultSet rs, int row) throws SQLException
	{
		ApplicationDetail dto = new ApplicationDetail();
		dto.setApplicationid(rs.getString("applicationid"));
		dto.setGatewayid(rs.getString("gatewayid"));
		dto.setPaymentmodeid(rs.getString("paymentmodeid"));
		dto.setMerchantid(rs.getString("merchantid"));
		dto.setMerchantidpassword(rs.getString("merchantidpassword"));
		dto.setPgactioncode(rs.getLong("pgactioncode"));
		if (rs.wasNull())
		{
			dto.setPgactioncodeNull(true);
		}

		dto.setFlagreturlmdp(rs.getString("flagreturlmdp"));
		dto.setApplicationurlresponseok(rs.getString("applicationurlresponseok"));
		dto.setApplicationurlresponseko(rs.getString("applicationurlresponseko"));
		dto.setTipocommissione(rs.getString("tipocommissione"));
		dto.setValorecommissionemin(rs.getFloat("valorecommissionemin"));
		dto.setSogliada(rs.getFloat("sogliada"));
		if (rs.wasNull())
		{
			dto.setSogliadaNull(true);
		}

		dto.setSogliaa(rs.getFloat("sogliaa"));
		if (rs.wasNull())
		{
			dto.setSogliaaNull(true);
		}

		dto.setValorecommissionemax(rs.getFloat("valorecommissionemax"));
		if (rs.wasNull())
		{
			dto.setValorecommissionemaxNull(true);
		}

		dto.setEnabled(rs.getString("enabled"));
		dto.setPgcontabcode(rs.getLong("pgcontabcode"));
		if (rs.wasNull())
		{
			dto.setPgcontabcodeNull(true);
		}

		dto.setApplicationurlback(rs.getString("applicationurlback"));
		dto.setMacAvvio(rs.getString("mac_avvio"));
		dto.setMacEsito(rs.getString("mac_esito"));
		dto.setCodiceistat(rs.getString("codiceistat"));
		dto.setTipobollettinoposte(rs.getString("tipobollettinoposte"));
		dto.setContocorrenteposte(rs.getString("contocorrenteposte"));
		dto.setTipodocumentoposte(rs.getString("tipodocumentoposte"));
		dto.setMail2buyerok(rs.getString("mail2buyerok"));
		dto.setMail2buyerko(rs.getString("mail2buyerko"));
		dto.setMail2esercko(rs.getString("mail2esercko"));
		dto.setMail2esercok(rs.getString("mail2esercok"));
		dto.setMail2sysko(rs.getString("mail2sysko"));
		dto.setMail2sysok(rs.getString("mail2sysok"));
		dto.setApplicationurlcancel(rs.getString("applicationurlcancel"));
		dto.setApplicationurlerror(rs.getString("applicationurlerror"));

		if (this.sKey != null)
		{
			
				if (rs.getString(5) != null)
				{
					dto.setMerchantidpassword(EncryptionDecryptionUtil.decrypt(this.getsKey(), rs.getString(5)));
				}
				if (rs.getString(18) != null)
				{
					dto.setMacAvvio(EncryptionDecryptionUtil.decrypt(this.getsKey(), rs.getString(18)));
				}
				if (rs.getString(19) != null)
				{
					dto.setMacEsito(EncryptionDecryptionUtil.decrypt(this.getsKey(), rs.getString(19)));
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
		return "applicationdetail";
	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationid = :applicationid AND gatewayid = :gatewayid AND
	 * paymentmodeid = :paymentmodeid'.
	 */
	@Transactional
	public ApplicationDetail findByPrimaryKey(String applicationid, String gatewayid, String paymentmodeid, String idgroup)
			throws ApplicationDetailDaoException
	{
		try
		{
			StringBuffer query = new StringBuffer(
					"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
							+ getTableName() + " WHERE applicationid = ? AND gatewayid = ? AND paymentmodeid = ?");
			if (idgroup != null && !idgroup.trim().equals(""))
			{
				query.append(" AND applicationid IN (SELECT ID FROM vappgroup WHERE IDGROUP = " + idgroup + ")");
			}
			List<ApplicationDetail> list = jdbcTemplate.query(query.toString(), this, applicationid, gatewayid, paymentmodeid);

			return list.size() == 0 ? null : list.get(0);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * ''.
	 */
	@Transactional
	public List<ApplicationDetail> findAll(String idgroup) throws ApplicationDetailDaoException
	{
		try
		{
			StringBuffer query = new StringBuffer(
					"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
							+ getTableName());
			if (idgroup != null && !idgroup.trim().equals(""))
			{
				query.append(" WHERE applicationid IN (SELECT ID FROM vappgroup WHERE IDGROUP = " + idgroup + ")");
			}
			return jdbcTemplate.query(query.toString(), this);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationid = :applicationid'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereApplicationidEqualsAndNotNodo(String applicationid, String idgroup)
			throws ApplicationDetailDaoException
	{
		try
		{
			StringBuffer query = new StringBuffer(
					"SELECT applicationid, ad.gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
							+ getTableName() + " ad, gateway g WHERE ad.gatewayid = g.gateway_id and applicationid = ? ");
			if (idgroup != null && !idgroup.trim().equals(""))
			{
				query.append(" AND applicationid IN (SELECT ID FROM vappgroup WHERE IDGROUP = " + idgroup + ")");
			}
			query.append(" ORDER BY applicationid, gatewayid, paymentmodeid ");
			return jdbcTemplate.query(query.toString(), this, applicationid);
		} catch (Exception e)
		{
			log.error("ERRORE ", e);
			e.printStackTrace();
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}
	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationid = :applicationid'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereApplicationidEquals(String applicationid, String idgroup)
			throws ApplicationDetailDaoException
			{
		try
		{
			StringBuffer query = new StringBuffer(
					"SELECT applicationid, ad.gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
							+ getTableName() + " ad, gateway g WHERE ad.gatewayid = g.gateway_id and applicationid = ? ");
			if (idgroup != null && !idgroup.trim().equals(""))
			{
				query.append(" AND applicationid IN (SELECT ID FROM vappgroup WHERE IDGROUP = " + idgroup + ")");
			}
			query.append(" ORDER BY applicationid, gatewayid, paymentmodeid ");
			return jdbcTemplate.query(query.toString(), this, applicationid);
		} catch (Exception e)
		{
			log.error("ERRORE ", e);
			e.printStackTrace();
			throw new ApplicationDetailDaoException("Query failed", e);
		}
		
			}
	
	@Transactional
	public ApplicationDetail findGatewayForNodo(String applicationid)
			throws ApplicationDetailDaoException
			{
		try
		{
			StringBuffer query = new StringBuffer(
					"SELECT applicationid, ad.gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
							+ getTableName() + " ad, gateway g WHERE ad.gatewayid = g.gateway_id and g.flag_nodo is true and enabled = '1' and applicationid = ? ");
			query.append(" ORDER BY applicationid, gatewayid, paymentmodeid ");
			
			LoggerUtil.debug ( "QUERY IS : " + query);
            LoggerUtil.debug ( "PARAM1 : " + applicationid);
			
			List<ApplicationDetail> elenco = jdbcTemplate.query(query.toString(), this, applicationid);
			if (CollectionUtils.isEmpty(elenco)) {
				return null;
			} else {
				return elenco.get(0);
			}
		} catch (Exception e)
		{
			log.error("ERRORE ", e);
			e.printStackTrace();
			throw new ApplicationDetailDaoException("Query failed", e);
		}
		
			}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'gatewayid = :gatewayid'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereGatewayidEquals(String gatewayid) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE gatewayid = ? ORDER BY gatewayid", this, gatewayid);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'paymentmodeid = :paymentmodeid'.
	 */
	@Transactional
	public List<ApplicationDetail> findWherePaymentmodeidEquals(String paymentmodeid) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE paymentmodeid = ? ORDER BY paymentmodeid", this, paymentmodeid);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'merchantid = :merchantid'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMerchantidEquals(String merchantid) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE merchantid = ? ORDER BY merchantid", this, merchantid);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'merchantidpassword = :merchantidpassword'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMerchantidpasswordEquals(String merchantidpassword)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE merchantidpassword = ? ORDER BY merchantidpassword", this,
							merchantidpassword);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'pgactioncode = :pgactioncode'.
	 */
	@Transactional
	public List<ApplicationDetail> findWherePgactioncodeEquals(long pgactioncode) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE pgactioncode = ? ORDER BY pgactioncode", this, pgactioncode);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'flagreturlmdp = :flagreturlmdp'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereFlagreturlmdpEquals(String flagreturlmdp) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE flagreturlmdp = ? ORDER BY flagreturlmdp", this, flagreturlmdp);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationurlresponseok = :applicationurlresponseok'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereApplicationurlresponseokEquals(String applicationurlresponseok)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE applicationurlresponseok = ? ORDER BY applicationurlresponseok",
							this, applicationurlresponseok);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationurlresponseko = :applicationurlresponseko'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereApplicationurlresponsekoEquals(String applicationurlresponseko)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE applicationurlresponseko = ? ORDER BY applicationurlresponseko",
							this, applicationurlresponseko);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'tipocommissione = :tipocommissione'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereTipocommissioneEquals(String tipocommissione) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE tipocommissione = ? ORDER BY tipocommissione", this, tipocommissione);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'valorecommissionemin = :valorecommissionemin'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereValorecommissioneminEquals(float valorecommissionemin)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE valorecommissionemin = ? ORDER BY valorecommissionemin", this,
							valorecommissionemin);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'sogliada = :sogliada'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereSogliadaEquals(float sogliada) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE sogliada = ? ORDER BY sogliada", this, sogliada);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'sogliaa = :sogliaa'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereSogliaaEquals(float sogliaa) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE sogliaa = ? ORDER BY sogliaa", this, sogliaa);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'valorecommissionemax = :valorecommissionemax'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereValorecommissionemaxEquals(float valorecommissionemax)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE valorecommissionemax = ? ORDER BY valorecommissionemax", this,
							valorecommissionemax);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'enabled = :enabled'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereEnabledEquals(String enabled) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE enabled = ? ORDER BY enabled", this, enabled);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'pgcontabcode = :pgcontabcode'.
	 */
	@Transactional
	public List<ApplicationDetail> findWherePgcontabcodeEquals(long pgcontabcode) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE pgcontabcode = ? ORDER BY pgcontabcode", this, pgcontabcode);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationurlback = :applicationurlback'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereApplicationurlbackEquals(String applicationurlback)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE applicationurlback = ? ORDER BY applicationurlback", this,
							applicationurlback);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mac_avvio = :macAvvio'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMacAvvioEquals(String macAvvio) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mac_avvio = ? ORDER BY mac_avvio", this, macAvvio);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mac_esito = :macEsito'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMacEsitoEquals(String macEsito) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mac_esito = ? ORDER BY mac_esito", this, macEsito);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'codiceistat = :codiceistat'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereCodiceistatEquals(String codiceistat) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE codiceistat = ? ORDER BY codiceistat", this, codiceistat);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'tipobollettinoposte = :tipobollettinoposte'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereTipobollettinoposteEquals(String tipobollettinoposte)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE tipobollettinoposte = ? ORDER BY tipobollettinoposte", this,
							tipobollettinoposte);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'contocorrenteposte = :contocorrenteposte'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereContocorrenteposteEquals(String contocorrenteposte)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE contocorrenteposte = ? ORDER BY contocorrenteposte", this,
							contocorrenteposte);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'tipodocumentoposte = :tipodocumentoposte'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereTipodocumentoposteEquals(String tipodocumentoposte)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE tipodocumentoposte = ? ORDER BY tipodocumentoposte", this,
							tipodocumentoposte);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mail2buyerok = :mail2buyerok'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMail2buyerokEquals(String mail2buyerok) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mail2buyerok = ? ORDER BY mail2buyerok", this, mail2buyerok);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mail2buyerko = :mail2buyerko'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMail2buyerkoEquals(String mail2buyerko) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mail2buyerko = ? ORDER BY mail2buyerko", this, mail2buyerko);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mail2esercko = :mail2esercko'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMail2eserckoEquals(String mail2esercko) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mail2esercko = ? ORDER BY mail2esercko", this, mail2esercko);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mail2esercok = :mail2esercok'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMail2esercokEquals(String mail2esercok) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mail2esercok = ? ORDER BY mail2esercok", this, mail2esercok);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mail2sysko = :mail2sysko'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMail2syskoEquals(String mail2sysko) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mail2sysko = ? ORDER BY mail2sysko", this, mail2sysko);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'mail2sysok = :mail2sysok'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereMail2sysokEquals(String mail2sysok) throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE mail2sysok = ? ORDER BY mail2sysok", this, mail2sysok);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationurlcancel = :applicationurlcancel'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereApplicationurlcancelEquals(String applicationurlcancel)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE applicationurlcancel = ? ORDER BY applicationurlcancel", this,
							applicationurlcancel);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns all rows from the applicationdetail table that match the criteria
	 * 'applicationurlerror = :applicationurlerror'.
	 */
	@Transactional
	public List<ApplicationDetail> findWhereApplicationurlerrorEquals(String applicationurlerror)
			throws ApplicationDetailDaoException
	{
		try
		{
			return jdbcTemplate
					.query(
							"SELECT applicationid, gatewayid, paymentmodeid, merchantid, merchantidpassword, pgactioncode, flagreturlmdp, applicationurlresponseok, applicationurlresponseko, tipocommissione, valorecommissionemin, sogliada, sogliaa, valorecommissionemax, enabled, pgcontabcode, applicationurlback, mac_avvio, mac_esito, codiceistat, tipobollettinoposte, contocorrenteposte, tipodocumentoposte, mail2buyerok, mail2buyerko, mail2esercko, mail2esercok, mail2sysko, mail2sysok, applicationurlcancel, applicationurlerror FROM "
									+ getTableName() + " WHERE applicationurlerror = ? ORDER BY applicationurlerror", this,
							applicationurlerror);
		} catch (Exception e)
		{
			throw new ApplicationDetailDaoException("Query failed", e);
		}

	}

	/**
	 * Returns the rows from the applicationdetail table that matches the
	 * specified primary-key value.
	 */
	public ApplicationDetail findByPrimaryKey(ApplicationDetailPk pk, String idgroup) throws ApplicationDetailDaoException
	{
		return findByPrimaryKey(pk.getApplicationid(), pk.getGatewayid(), pk.getPaymentmodeid(), idgroup);
	}

	public void setsKey(String sKey)
	{
		this.sKey = sKey;
	}

	public String getsKey()
	{
		return sKey;
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
