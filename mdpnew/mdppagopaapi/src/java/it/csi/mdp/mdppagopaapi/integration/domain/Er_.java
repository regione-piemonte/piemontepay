/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.792+0200")
@StaticMetamodel(Er.class)
public class Er_ {
	public static volatile SingularAttribute<Er, Integer> idEr;
	public static volatile SingularAttribute<Er, String> applicationId;
	public static volatile SingularAttribute<Er, String> codiceContestoPagamento;
	public static volatile SingularAttribute<Er, String> codiceIdentificativoUnivocoAttestante;
	public static volatile SingularAttribute<Er, Timestamp> dataOraMessaggioEsito;
	public static volatile SingularAttribute<Er, String> denominazioneIstitutoAttestante;
	public static volatile SingularAttribute<Er, String> idDominio;
	public static volatile SingularAttribute<Er, String> identificativoMessaggioEsito;
	public static volatile SingularAttribute<Er, BigDecimal> importoTotaleRevocato;
	public static volatile SingularAttribute<Er, Boolean> invioOkRispostaRevoca;
	public static volatile SingularAttribute<Er, String> iuv;
	public static volatile SingularAttribute<Er, Timestamp> riferimentoDataRevoca;
	public static volatile SingularAttribute<Er, String> riferimentoMessaggioRevoca;
	public static volatile SingularAttribute<Er, byte[]> xmlEr;
	public static volatile ListAttribute<Er, DatiSingoloEsito> datiSingoloEsitos1;
	public static volatile ListAttribute<Er, DatiSingoloEsito> datiSingoloEsitos2;
}
