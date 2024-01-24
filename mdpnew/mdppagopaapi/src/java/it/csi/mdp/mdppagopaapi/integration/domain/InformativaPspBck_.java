/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.816+0200")
@StaticMetamodel(InformativaPspBck.class)
public class InformativaPspBck_ {
	public static volatile SingularAttribute<InformativaPspBck, Long> idinformativapsp;
	public static volatile SingularAttribute<InformativaPspBck, String> condizionieconomichemassime;
	public static volatile SingularAttribute<InformativaPspBck, String> datainiziovalidita;
	public static volatile SingularAttribute<InformativaPspBck, Date> datainserimento;
	public static volatile SingularAttribute<InformativaPspBck, String> datapubblicazione;
	public static volatile SingularAttribute<InformativaPspBck, String> descrizioneservizio;
	public static volatile SingularAttribute<InformativaPspBck, String> disponibilitaservizio;
	public static volatile SingularAttribute<InformativaPspBck, String> identificativocanale;
	public static volatile SingularAttribute<InformativaPspBck, String> identificativoflusso;
	public static volatile SingularAttribute<InformativaPspBck, String> identificativointermediario;
	public static volatile SingularAttribute<InformativaPspBck, String> identificativopsp;
	public static volatile SingularAttribute<InformativaPspBck, BigDecimal> modellopagamento;
	public static volatile SingularAttribute<InformativaPspBck, BigDecimal> ordinamento;
	public static volatile SingularAttribute<InformativaPspBck, String> origine;
	public static volatile SingularAttribute<InformativaPspBck, BigDecimal> priorita;
	public static volatile SingularAttribute<InformativaPspBck, String> ragionesociale;
	public static volatile SingularAttribute<InformativaPspBck, String> statoinserimento;
	public static volatile SingularAttribute<InformativaPspBck, BigDecimal> stornopagamento;
	public static volatile SingularAttribute<InformativaPspBck, String> tipoversamento;
	public static volatile SingularAttribute<InformativaPspBck, String> urlinformazionicanale;
	public static volatile SingularAttribute<InformativaPspBck, String> urlinformazionipsp;
}
