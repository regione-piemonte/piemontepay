/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.ApplicationDao;
import it.csi.mdp.core.business.dto.Application;
import it.csi.mdp.core.business.dto.ApplicationPk;
import it.csi.mdp.core.business.exceptions.ApplicationDaoException;
import it.csi.mdp.core.util.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.object.StoredProcedure;



public class ApplicationDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Application>, ApplicationDao
{
	protected SimpleJdbcTemplate jdbcTemplate;
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
	protected DataSource dataSource;

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
	public ApplicationPk insert(Application dto)
	{
		jdbcTemplate
				.update(
						"INSERT INTO "
								+ getTableName()
								+ " ( ID, APPLICATIONNAME, REFERENTECSI, CLIENTE, PROGETTO, NOTE, ESERCEMAIL, NUMEROVERDE, VALIDO_DAL, VALIDO_AL ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",
						dto.getId(), dto.getApplicationname(), dto.getReferentecsi(), dto.getCliente(), dto.getProgetto(), dto
								.getNote(), dto.getEsercemail(), dto.getNumeroverde(), dto.getValidoDal(), dto.getValidoAl());
		return new ApplicationPk(dto.getId());
	}

	/**
	 * Updates a single row in the application table.
	 */
	@Transactional
	public void update(ApplicationPk pk, Application dto) throws ApplicationDaoException
	{
		jdbcTemplate
				.update(
						"UPDATE "
								+ getTableName()
								+ " SET id = ?, applicationname = ?, referentecsi = ?, cliente = ?, progetto = ?, note = ?, esercemail = ?, numeroverde = ?, valido_dal = ?, valido_al = ?, redirect_newmdp = ? WHERE id = ?",
						dto.getId(), dto.getApplicationname(), dto.getReferentecsi(), dto.getCliente(), dto.getProgetto(), dto
								.getNote(), dto.getEsercemail(), dto.getNumeroverde(), dto.getValidoDal(), dto.getValidoAl(), dto
								.getRedirectNewmdp(), pk.getId());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return Application
	 */
	public Application mapRow(ResultSet rs, int row) throws SQLException
	{
		Application dto = new Application();
		dto.setId(rs.getString(1));
		dto.setApplicationname(rs.getString(2));
		dto.setReferentecsi(rs.getString(3));
		dto.setCliente(rs.getString(4));
		dto.setProgetto(rs.getString(5));
		dto.setNote(rs.getString(6));
		dto.setEsercemail(rs.getString(7));
		dto.setNumeroverde(rs.getString(8));
		dto.setValidoDal(rs.getTimestamp(9));
		dto.setValidoAl(rs.getTimestamp(10));
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "APPLICATION";
	}

	/**
	 * Returns all rows from the APPLICATION table that match the criteria ''.
	 */
	@Transactional
	public List<Application> findAll(String idgroup) throws ApplicationDaoException
	{
		try
		{
			StringBuffer query = new StringBuffer(
					"SELECT ID, APPLICATIONNAME, REFERENTECSI, CLIENTE, PROGETTO, NOTE, ESERCEMAIL, NUMEROVERDE, VALIDO_DAL, VALIDO_AL FROM "
							+ getTableName() + "");
			if (idgroup != null && !idgroup.trim().equals(""))
			{
				query.append(" WHERE ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = " + idgroup + ")");
			}
			log.debug("ApplicationDao query:" + query.toString());

			return jdbcTemplate.query(query.toString(), this);
		} catch (Exception e)
		{
			throw new ApplicationDaoException("Query failed:" + e.getMessage(), e);
		}

	}

	/**
	 * Returns all rows from the APPLICATION table that match the criteria 'ID =
	 * :id'.
	 */
	@Transactional
	public List<Application> findWhereIdEquals(String id, String idgroup) throws ApplicationDaoException
	{
		try
		{
			StringBuffer query = new StringBuffer(
					"SELECT ID, APPLICATIONNAME, REFERENTECSI, CLIENTE, PROGETTO, NOTE, ESERCEMAIL, NUMEROVERDE, VALIDO_DAL, VALIDO_AL FROM "
							+ getTableName() + " WHERE ID = ? ");
			if (idgroup != null && !idgroup.trim().equals(""))
			{
				query.append(" AND ID IN (SELECT ID FROM vappgroup WHERE IDGROUP = " + idgroup + ")");
			}
			return jdbcTemplate.query(query.toString(), this, id);
		} catch (Exception e)
		{
			throw new ApplicationDaoException("Query failed:" + e.getMessage(), e);
		}

	}

	public void delete(ApplicationPk pk) throws ApplicationDaoException
	{
		jdbcTemplate.update("DELETE FROM " + this.getTableName() + " WHERE ID = ?", pk.getId());

	}

	public void deleteApplicationConfiguration(ApplicationPk pk) throws ApplicationDaoException
	{
		ApplicationStoredProcedure proc = new ApplicationStoredProcedure(this.dataSource);
		Map<String, String> params = new HashMap<String, String>();
		params.put("p_applicationId", pk.getId());
		proc.execute(params);
	}

	@Transactional
	public List<Application> listApplicationByFlussoApplicazione() throws ApplicationDaoException
	{
		try
		{
			StringBuffer query = new StringBuffer(
					" SELECT DISTINCT " +
					" A.ID, " +
					" A.APPLICATIONNAME, " +
					" A.REFERENTECSI, " +
					" A.CLIENTE, " +
					" A.PROGETTO, " +
					" A.NOTE, " +
					" A.ESERCEMAIL, " +
					" A.NUMEROVERDE, " +
					" A.VALIDO_DAL, " +
					" A.VALIDO_AL " +
					" FROM " +
					" APPLICATION A , FLUSSO_SINGOLO_PAGAMENTO B where A.ID = B.APPLICATION_ID ");
			log.debug("ApplicationDao query:" + query.toString());

			return jdbcTemplate.query(query.toString(), this);
		} catch (Exception e)
		{
			throw new ApplicationDaoException("Query failed:" + e.getMessage(), e);
		}

	}

	
	private class ApplicationStoredProcedure extends StoredProcedure
	{
		public static final String SQL = "deleteApplicationConfiguration";

		public ApplicationStoredProcedure(DataSource ds)
		{
			setDataSource(ds);
			setSql(SQL);

			declareParameter(new SqlParameter("p_applicationId", Types.VARCHAR));
			declareParameter(new SqlReturnResultSet("refcur", new ApplicationRowMapper()));
			compile();
		}
	}

	private class ApplicationRowMapper implements RowCallbackHandler
	{
		public void processRow(ResultSet rs) throws SQLException
		{
			// get our ref cursor
			ResultSet cur = (ResultSet) rs.getObject(1);
			while (cur.next())
			{
				System.out.println(cur.getBoolean(1));
			}
			cur.close();
		}
	}

}
