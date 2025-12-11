/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayTRegistroElaborazioniFault
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTRegistroElaborazioniFault.class )
public class EpayTRegistroElaborazioniFault_ {
	public static volatile SingularAttribute<EpayTRegistroElaborazioniFault, Long> id; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioniFault, Long> idPagamento; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioniFault, String> codicePagamentoRifEnte; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioniFault, String> codiceMessaggio; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioniFault, String> descrizioneMessaggio; 
	public static volatile SingularAttribute<EpayTRegistroElaborazioniFault, EpayTRegistroElaborazioni> epayTRegistroElaborazioni;
}
