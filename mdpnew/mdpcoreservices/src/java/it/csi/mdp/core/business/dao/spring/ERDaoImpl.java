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

import it.csi.mdp.core.business.dao.ERDao;
import it.csi.mdp.core.business.dto.DatiSingoloEsito;
import it.csi.mdp.core.business.dto.ER;
import it.csi.mdp.core.business.exceptions.DaoException;

public class ERDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ER>, ERDao{
    
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
    public void insert ( ER dto ) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        String addRRQuery = "INSERT INTO er ("
                        + "id_dominio,"
                        + "application_id, "
                        + "identificativo_messaggio_esito, "
                        + "data_ora_messaggio_esito,"
                        + "riferimento_messaggio_revoca,"
                        + "riferimento_data_revoca,"
                        + "codice_identificativo_univoco_attestante,"
                        + "denominazione_istituto_attestante,"
                        + "importo_totale_revocato,"
                        + "iuv,"
                        + "codice_contesto_pagamento,"
                        + "xml_er"
                        + ") VALUES ("
                        + ":idDominio,"
                        + ":applicationId,"
                        + ":identificativoMessaggioEsito,"
                        + ":dataOraMessaggioEsito,"
                        + ":riferimentoMessaggioRevoca,"
                        + ":riferimentoDataRevoca,"
                        + ":codiceIdentificativoUnivocoAttestante,"
                        + ":denominazioneIstitutoAttestante,"
                        + ":importoTotaleRevocato,"
                        + ":iuv,"
                        + ":codiceContestoPagamento,"
                        + ":xmlER"
                        + ")";
        
        params.addValue ( "idDominio", dto.getIdDominio (), Types.VARCHAR );
        params.addValue ( "applicationId", dto.getApplicationId (), Types.VARCHAR );
        params.addValue ( "identificativoMessaggioEsito", dto.getIdentificativoMessaggioEsito (), Types.VARCHAR );
        params.addValue ( "dataOraMessaggioEsito", dto.getDataOraMessaggioEsito (), Types.TIMESTAMP );
        params.addValue ( "riferimentoMessaggioRevoca", dto.getRiferimentoMessaggioRevoca (), Types.VARCHAR );
        params.addValue ( "riferimentoDataRevoca", dto.getRiferimentoDataRevoca (), Types.TIMESTAMP );
        params.addValue ( "codiceIdentificativoUnivocoAttestante", dto.getCodiceIdentificativoUnivocoAttestante (), Types.VARCHAR );
        params.addValue ( "denominazioneIstitutoAttestante", dto.getDenominazioneIstitutoAttestante (), Types.VARCHAR );
        params.addValue ( "importoTotaleRevocato", dto.getImportoTotaleRevocato (), Types.DOUBLE );
        params.addValue ( "iuv", dto.getIuv (), Types.VARCHAR );
        params.addValue ( "codiceContestoPagamento", dto.getCodiceContestoPagamento (), Types.VARCHAR );
        params.addValue ( "xmlER", dto.getXmlEr (), Types.BINARY );        
                                
        jdbcTemplate.update (
            addRRQuery,
            params,
            keyHolder );
        
        Integer lastKey = (Integer) keyHolder.getKeys ().get ( "id_er" );
        
        String singoloEsitoQuery = "INSERT INTO dati_singolo_esito (" 
                        + "id_er,"
                        + "singolo_importo_revocato, "
                        + "iur, "
                        + "causale_esito,"
                        + "dati_aggiuntivi_esito"
                        + " ) VALUES ( "
                        + " :ider,"
                        + " :singoloImportoRevocato,"
                        + " :iur,"
                        + " :causaleEsito,"
                        + " :datiAggiuntiviEsito"
                        + ")";
        
        
        for(DatiSingoloEsito datiSingoloEsito: dto.getListaDatiSingoloEsito ()) {
            MapSqlParameterSource paramsse = new MapSqlParameterSource();
            paramsse.addValue ( "ider", lastKey, Types.INTEGER );
            paramsse.addValue ( "singoloImportoRevocato", datiSingoloEsito.getSingoloImportoRevocato (), Types.DOUBLE );
            paramsse.addValue ( "iur", datiSingoloEsito.getIur (), Types.VARCHAR );
            paramsse.addValue ( "causaleEsito", datiSingoloEsito.getCausaleEsito (), Types.VARCHAR );
            paramsse.addValue ( "datiAggiuntiviEsito", datiSingoloEsito.getDatiAggiuntiviEsito (), Types.VARCHAR );
            
            jdbcTemplate.update (
                singoloEsitoQuery,
                paramsse);
        }
    }

    public void update ( ER dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }

    public void delete ( ER dto ) throws DaoException {
        // TODO Auto-generated method stub
        
    }
    
    public ER mapRow ( ResultSet arg0, int arg1 ) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
