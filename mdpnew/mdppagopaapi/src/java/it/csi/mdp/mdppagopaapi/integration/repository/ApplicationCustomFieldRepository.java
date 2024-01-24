/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.Application;
import it.csi.mdp.mdppagopaapi.integration.domain.Applicationcustomfield;
/**
 * Spring data Jpa repository for "GdeRepository" <br>
 *
 * @author Silvia.Balsamini
 */
@Repository
public interface ApplicationCustomFieldRepository extends IRepository<Applicationcustomfield, Integer> {

    @Query ( value = "SELECT a.applicationid, " +
                    "       a.fieldname as fieldname, " +
                    "       a.fieldvalue, " +
                    "       a.keyid, " +
                    "       a.fielddescription, " +
                    "       a.gateway_id " +
                    "FROM applicationcustomfields a, " +
                    "     applicationdetail ad, " +
                    "     gateway g " +
                    "WHERE a.applicationid = ad.applicationid " +
                    "AND   a.gateway_id = ad.gatewayid " +
                    "AND   ad.gatewayid = g.gateway_id " +
                    "AND   g.flag_nodo IS TRUE " +
                    "AND   ad.enabled = '1' " +
                    "AND   a.applicationid = ?1", nativeQuery = true )
    List<Applicationcustomfield> findAllByApplicationEnabled ( String applicationId );

    @Query ( value = "SELECT a.applicationid, " +
                                "       a.fieldname as fieldname, " +
                                "       a.fieldvalue, " +
                                "       a.keyid, " +
                                "       a.fielddescription, " +
                                "       a.gateway_id " +
                                "FROM applicationcustomfields a, " +
                                "     applicationdetail ad, " +
                                "     gateway g " +
                                "WHERE a.applicationid = ad.applicationid " +
                                "AND   a.gateway_id = ad.gatewayid " +
                                "AND   ad.gatewayid = g.gateway_id " +
                                "AND   g.flag_nodo IS TRUE " +
                                "AND   a.fieldname = ?2 " + // Togliere il trim appena possibile per motivi di performance.
                                "AND   ad.enabled = '1' " +
                                "AND   a.applicationid = ?1", nativeQuery = true )
    Applicationcustomfield findTopByApplicationAndFieldnameIs ( String applicationId, String fieldname );


    @Query ( value = "SELECT a.applicationid, " +
                    "       a.fieldname as fieldname, " +
                    "       a.fieldvalue, " +
                    "       a.keyid, " +
                    "       a.fielddescription, " +
                    "       a.gateway_id " +
                    "FROM applicationcustomfields a, " +
                    "     applicationdetail ad, " +
                    "     gateway g " +
                    "WHERE a.applicationid = ad.applicationid " +
                    "AND   a.gateway_id = ad.gatewayid " +
                    "AND   ad.gatewayid = g.gateway_id " +
                    "AND   g.flag_nodo IS TRUE " +
                    "AND   a.fieldname = ?2 " + // Togliere il trim appena possibile per motivi di performance.
                    "AND   ad.enabled = '1' " +
                    "AND   a.applicationid in (?1)", nativeQuery = true )
    List<Applicationcustomfield> findAllByApplicationInAndFieldnameIs ( Collection<Application> applications, String fieldName );
}
