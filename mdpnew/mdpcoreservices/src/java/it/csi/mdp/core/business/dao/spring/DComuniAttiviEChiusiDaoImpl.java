/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.DComuniAttiviEChiusiDao;
import it.csi.mdp.core.business.dto.DComuniAttiviEChiusi;
import it.csi.mdp.core.business.exceptions.DComuniAttiviEChiusiDaoException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class DComuniAttiviEChiusiDaoImpl extends AbstractDAO implements ParameterizedRowMapper<DComuniAttiviEChiusi>, DComuniAttiviEChiusiDao
{
	protected SimpleJdbcTemplate jdbcTemplate;

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
	public void insert(DComuniAttiviEChiusi dto)
	{
		jdbcTemplate.update("INSERT INTO " + getTableName() + " ( ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",dto.getIdComune(),dto.getCodCatasto(),dto.getIstatComune(),dto.getDescComune(),dto.getCap(),dto.isAltitudineNull()?null:dto.getAltitudine(),dto.isSuperficieHm2Null()?null:dto.getSuperficieHm2(),dto.getIstatProvincia(),dto.getIstatZonaAltimetrica(),dto.getDescZonaAltimetrica(),dto.getIstatRegione(),dto.getDataFineValidita(),dto.getAttiva(),dto.getDataUpd());
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return DComuniAttiviEChiusi
	 */
	public DComuniAttiviEChiusi mapRow(ResultSet rs, int row) throws SQLException
	{
		DComuniAttiviEChiusi dto = new DComuniAttiviEChiusi();
		dto.setIdComune( rs.getLong( 1 ) );
		dto.setCodCatasto( rs.getString( 2 ) );
		dto.setIstatComune( rs.getString( 3 ) );
		dto.setDescComune( rs.getString( 4 ) );
		dto.setCap( rs.getString( 5 ) );
		dto.setAltitudine( rs.getLong( 6 ) );
		if (rs.wasNull()) {
			dto.setAltitudineNull( true );
		}
		
		dto.setSuperficieHm2( rs.getLong( 7 ) );
		if (rs.wasNull()) {
			dto.setSuperficieHm2Null( true );
		}
		
		dto.setIstatProvincia( rs.getString( 8 ) );
		dto.setIstatZonaAltimetrica( rs.getString( 9 ) );
		dto.setDescZonaAltimetrica( rs.getString( 10 ) );
		dto.setIstatRegione( rs.getString( 11 ) );
		dto.setDataFineValidita( rs.getTimestamp(12 ) );
		dto.setAttiva( rs.getString( 13 ) );
		dto.setDataUpd( rs.getTimestamp(14 ) );
		return dto;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "D_COMUNI_ATTIVI_E_CHIUSI";
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria ''.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findAll() throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + "", this);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ID_COMUNE = :idComune'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereIdComuneEquals(long idComune) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE ID_COMUNE = ? ORDER BY ID_COMUNE", this,idComune);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'COD_CATASTO = :codCatasto'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereCodCatastoEquals(String codCatasto) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE COD_CATASTO = ? ORDER BY COD_CATASTO", this,codCatasto);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_COMUNE = :istatComune'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereIstatComuneEquals(String istatComune) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE ISTAT_COMUNE = ? ORDER BY ISTAT_COMUNE", this,istatComune);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DESC_COMUNE = :descComune'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereDescComuneEquals(String descComune) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE DESC_COMUNE = ? ORDER BY DESC_COMUNE", this,descComune);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'CAP = :cap'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereCapEquals(String cap) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE CAP = ? ORDER BY CAP", this,cap);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ALTITUDINE = :altitudine'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereAltitudineEquals(long altitudine) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE ALTITUDINE = ? ORDER BY ALTITUDINE", this,altitudine);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'SUPERFICIE_HM2 = :superficieHm2'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereSuperficieHm2Equals(long superficieHm2) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE SUPERFICIE_HM2 = ? ORDER BY SUPERFICIE_HM2", this,superficieHm2);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_PROVINCIA = :istatProvincia'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereIstatProvinciaEquals(String istatProvincia) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE ISTAT_PROVINCIA = ? ORDER BY ISTAT_PROVINCIA", this,istatProvincia);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_ZONA_ALTIMETRICA = :istatZonaAltimetrica'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereIstatZonaAltimetricaEquals(String istatZonaAltimetrica) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE ISTAT_ZONA_ALTIMETRICA = ? ORDER BY ISTAT_ZONA_ALTIMETRICA", this,istatZonaAltimetrica);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DESC_ZONA_ALTIMETRICA = :descZonaAltimetrica'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereDescZonaAltimetricaEquals(String descZonaAltimetrica) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE DESC_ZONA_ALTIMETRICA = ? ORDER BY DESC_ZONA_ALTIMETRICA", this,descZonaAltimetrica);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ISTAT_REGIONE = :istatRegione'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereIstatRegioneEquals(String istatRegione) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE ISTAT_REGIONE = ? ORDER BY ISTAT_REGIONE", this,istatRegione);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DATA_FINE_VALIDITA = :dataFineValidita'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereDataFineValiditaEquals(Date dataFineValidita) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE DATA_FINE_VALIDITA = ? ORDER BY DATA_FINE_VALIDITA", this,dataFineValidita);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'ATTIVA = :attiva'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereAttivaEquals(String attiva) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE ATTIVA = ? ORDER BY ATTIVA", this,attiva);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

	/** 
	 * Returns all rows from the D_COMUNI_ATTIVI_E_CHIUSI table that match the criteria 'DATA_UPD = :dataUpd'.
	 */
	@Transactional
	public List<DComuniAttiviEChiusi> findWhereDataUpdEquals(Date dataUpd) throws DComuniAttiviEChiusiDaoException
	{
		try {
			return jdbcTemplate.query("SELECT ID_COMUNE, COD_CATASTO, ISTAT_COMUNE, DESC_COMUNE, CAP, ALTITUDINE, SUPERFICIE_HM2, ISTAT_PROVINCIA, ISTAT_ZONA_ALTIMETRICA, DESC_ZONA_ALTIMETRICA, ISTAT_REGIONE, DATA_FINE_VALIDITA, ATTIVA, DATA_UPD FROM " + getTableName() + " WHERE DATA_UPD = ? ORDER BY DATA_UPD", this,dataUpd);
		}
		catch (Exception e) {
			throw new DComuniAttiviEChiusiDaoException("Query failed", e);
		}
		
	}

}
