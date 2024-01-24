/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.Applicationdetail;
import it.csi.mdp.mdppagopaapi.integration.domain.ApplicationdetailPK;


/**
 * Spring data Jpa repository for "ApplicationdetailRepository" <br>
 *
 * @author Silvia.Balsamini
 */
@SuppressWarnings ( "unused" )
@Repository
public interface ApplicationdetailRepository extends IRepository<Applicationdetail, ApplicationdetailPK> {

    //@Query ( "SELECT a FROM Applicationdetail a where id.applicationid = :applicationid" )
    Applicationdetail findTopById_Applicationid ( String applicationid );
}
