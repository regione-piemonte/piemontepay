/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import it.csi.mdp.core.business.dao.RRDao;
import it.csi.mdp.core.business.dto.DatiSingolaRevoca;
import it.csi.mdp.core.business.dto.RR;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.util.LoggerUtil;

public class RRDaoImpl extends AbstractDAO implements ParameterizedRowMapper<RR>, RRDao{
    
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

    @Transactional
    public Integer createRRData ( RR dto ) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
                
        String addRRQuery = "INSERT INTO rr ("
                        + "id_dominio,"
                        + "application_id, "
                        + "identificativo_messaggio_revoca, "
                        + "data_ora_messaggio_revoca,"
                        + "codice_identificativo_univoco_attestante,"
                        + "denominazione_istituto_attestante,"
                        + "importo_totale_revocato,"
                        + "iuv,"
                        + "codice_contesto_pagamento,"
                        + "tipo_revoca,"
                        + "xml_rr"
                        + " ) VALUES ( "
                        + " :idDominio,"
                        + " :applicationId,"
                        + " :identificativoMessaggioRevoca,"
                        + " :dataOraMessaggioRevoca,"
                        + " :codiceIdentificativoUnivocoAttestante,"
                        + " :denominazioneIstitutoAttestante,"
                        + " :importoTotaleRevocato,"
                        + " :iuv,"
                        + " :codiceContestoPagamento,"
                        + " :tipoRevoca,"
                        + " :xmlRR"
                        + ")";
        
        params.addValue ( "idDominio", dto.getIdDominio (), Types.VARCHAR );
        params.addValue ( "applicationId", dto.getApplicationId (), Types.VARCHAR );
        params.addValue ( "identificativoMessaggioRevoca", dto.getIdentificativoMessaggioRevoca (), Types.VARCHAR  );
        params.addValue ( "dataOraMessaggioRevoca", dto.getDataOraMessaggioRevoca (), Types.TIMESTAMP );
        params.addValue ( "codiceIdentificativoUnivocoAttestante", dto.getCodiceIdentificativoUnivocoAttestante (), Types.VARCHAR );
        params.addValue ( "denominazioneIstitutoAttestante", dto.getDenominazioneIstitutoAttestante (), Types.VARCHAR );
        params.addValue ( "importoTotaleRevocato", dto.getImportoTotaleRevocato (),Types.DOUBLE );
        params.addValue ( "iuv", dto.getIuv (), Types.VARCHAR );
        params.addValue ( "codiceContestoPagamento", dto.getCodiceContestoPagamento (), Types.VARCHAR );
        params.addValue ( "tipoRevoca", dto.getTipoRevoca (), Types.DOUBLE );
        params.addValue ( "xmlRR", dto.getXmlRR (), Types.BINARY );
                                
        jdbcTemplate.update (
            addRRQuery,
            params,
            keyHolder );
        
        Integer lastKey = (Integer) keyHolder.getKeys ().get ( "id_rr" );
                                        
        String singolaRevocaQuery = "INSERT INTO dati_singola_revoca (" 
                        + "id_RR,"
                        + "singolo_importo_revocato, "
                        + "IUR, "
                        + "causale_revoca,"
                        + "dati_aggiuntivi_revoca"
                        + " ) VALUES ( "
                        + " :idRR,"
                        + " :singoloImportoRevocato,"
                        + " :IUR,"
                        + " :causaleRevoca,"
                        + " :datiAggiuntiviRevoca"
                        + ")";
        
        LoggerUtil.debug ( "Start persisting DatiSingolaRevoca" );
        
        for (DatiSingolaRevoca datiSingolaRevoca : dto.getListaDatiSingolaRevoca ()) {
            MapSqlParameterSource paramssr = new MapSqlParameterSource();
            paramssr.addValue ( "idRR",lastKey );
            paramssr.addValue ( "singoloImportoRevocato", datiSingolaRevoca.getSingoloImportoRevocato (), Types.DOUBLE );
            paramssr.addValue ( "IUR", datiSingolaRevoca.getIur (), Types.VARCHAR );
            paramssr.addValue ( "causaleRevoca", datiSingolaRevoca.getCausaleRevoca (), Types.VARCHAR );
            paramssr.addValue ( "datiAggiuntiviRevoca", datiSingolaRevoca.getDatiAggiuntiviRevoca () );
            
            jdbcTemplate.update (
                singolaRevocaQuery,
                paramssr);
        }
        
        LoggerUtil.debug ( "End persisting DatiSingolaRevoca" );
        
        return lastKey;
    }

    public void update ( RR dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public void delete ( RR dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public RR mapRow ( ResultSet arg0, int arg1 ) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void insert ( RR dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }





   

}
