/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTErrori
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTErrori.class )
public class EpayTErrori_ {
	public static volatile SingularAttribute<EpayTErrori, Long> id; 
    public static volatile SingularAttribute<EpayTErrori, Date> data; 
    public static volatile SingularAttribute<EpayTErrori, String> descrizione; 
    public static volatile SingularAttribute<EpayTErrori, Long> idPagamento; 
    public static volatile SingularAttribute<EpayTErrori, Long> idRegistroVersamento; 
    public static volatile SingularAttribute<EpayTErrori, String> iuv; 
    public static volatile SingularAttribute<EpayTErrori, Long> idTransazione; 
    public static volatile SingularAttribute<EpayTErrori, String> descCorrezione; 
    public static volatile SingularAttribute<EpayTErrori, String> applicativo; 
	public static volatile SingularAttribute<EpayTErrori, EpayDStatoErrore> epayDStatoErrore;
}
