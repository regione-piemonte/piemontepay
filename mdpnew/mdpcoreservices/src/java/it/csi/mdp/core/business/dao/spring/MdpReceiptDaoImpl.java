/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import it.csi.mdp.core.business.dao.MdpReceiptDao;
import it.csi.mdp.core.business.dto.MdpReceipt;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.util.LoggerUtil;



public class MdpReceiptDaoImpl extends AbstractDAO implements ParameterizedRowMapper<MdpReceipt>, MdpReceiptDao
{
    protected NamedParameterJdbcTemplate jdbcTemplate;

    protected DataSource dataSource;

    /**
     * Method 'setDataSource'
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }



    @Override
    public Integer existMdpReceipt ( String iuv ) throws DaoException {
        LoggerUtil.begin();
        StringBuffer sql = new StringBuffer();
        MapSqlParameterSource params = new MapSqlParameterSource();

        sql.append ( " SELECT count(id) " );
        sql.append ( "  FROM mdp_receipt m" );
        sql.append ( "  WHERE  m.notice_number=:iuv" );

        params.addValue("iuv", iuv, java.sql.Types.VARCHAR);
        
        LoggerUtil.info("String iuv "+ iuv );
        LoggerUtil.info("sql --> " + sql.toString());
        LoggerUtil.end();
        return jdbcTemplate.queryForInt ( sql.toString (), params );

    }

    @Override
    public MdpReceipt mapRow ( ResultSet rs, int index ) throws SQLException {

        return null;
    }

}
