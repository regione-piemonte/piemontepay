/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe CsiLogAudit
 */
@Generated( value="EII" )
@StaticMetamodel( CsiLogAudit.class )
public class CsiLogAudit_ {
	public static volatile SingularAttribute<CsiLogAudit, Long> idLog; 
    public static volatile SingularAttribute<CsiLogAudit, Date> dataOra; 
    public static volatile SingularAttribute<CsiLogAudit, String> idApp; 
    public static volatile SingularAttribute<CsiLogAudit, String> ipAddress; 
    public static volatile SingularAttribute<CsiLogAudit, String> utente; 
    public static volatile SingularAttribute<CsiLogAudit, String> operazione; 
    public static volatile SingularAttribute<CsiLogAudit, String> oggOper; 
    public static volatile SingularAttribute<CsiLogAudit, String> keyOper; 
}
