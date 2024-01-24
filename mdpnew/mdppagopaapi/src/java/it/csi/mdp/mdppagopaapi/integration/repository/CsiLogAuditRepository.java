/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.CsiLogAudit;
import it.csi.mdp.mdppagopaapi.integration.repository.IRepository;
/**
 * Spring data Jpa repository for "CsiLogAudit" <br>
 *
 * @author fabio.fenoglio
 */
@SuppressWarnings ( "unused" )
@Repository
public interface CsiLogAuditRepository extends IRepository<CsiLogAudit, Long> {
}
