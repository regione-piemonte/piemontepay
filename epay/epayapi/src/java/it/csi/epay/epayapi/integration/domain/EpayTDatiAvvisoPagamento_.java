/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTDatiAvvisoPagamento
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTDatiAvvisoPagamento.class )
public class EpayTDatiAvvisoPagamento_ {
	public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, Long> idDatiAvvisoPagamento; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> settore; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> indirizzo; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> localita; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> cap; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> siglaProvincia; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> email; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> infoEnte; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> intestatarioCcPostale; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> numeroCcPostale; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> autorizzazioneDaPosteIt; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, String> utenteUltimaModifica; 
    public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, Date> dataUltimaModifica; 
	public static volatile SingularAttribute<EpayTDatiAvvisoPagamento, EpayTTipoPagamento> epayTTipoPagamento;
}
