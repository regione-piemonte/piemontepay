/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTChiamataEsternaNonValida
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTChiamataEsternaNonValida.class )
public class EpayTChiamataEsternaNonValida_ {
	public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, Long> idChiamata; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, String> codiceChiamante; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, String> digest; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, String> iuv; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, String> codiceFiscale; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, Date> timestampChiamata; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, String> remoteHost; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, String> descrizioneErrore; 
    public static volatile SingularAttribute<EpayTChiamataEsternaNonValida, String> identificativoPagamento; 
}
