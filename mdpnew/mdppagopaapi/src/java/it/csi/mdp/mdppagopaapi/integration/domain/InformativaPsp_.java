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

@Generated(value="Dali", date="2021-09-23T12:14:16.814+0200")
@StaticMetamodel(InformativaPsp.class)
public class InformativaPsp_ {
	public static volatile SingularAttribute<InformativaPsp, Integer> idinformativapsp;
	public static volatile SingularAttribute<InformativaPsp, String> condizionieconomichemassime;
	public static volatile SingularAttribute<InformativaPsp, Timestamp> datainiziovalidita;
	public static volatile SingularAttribute<InformativaPsp, Timestamp> datainserimento;
	public static volatile SingularAttribute<InformativaPsp, Timestamp> datapubblicazione;
	public static volatile SingularAttribute<InformativaPsp, String> descrizioneservizio;
	public static volatile SingularAttribute<InformativaPsp, String> disponibilitaservizio;
	public static volatile SingularAttribute<InformativaPsp, String> identificativocanale;
	public static volatile SingularAttribute<InformativaPsp, String> identificativoflusso;
	public static volatile SingularAttribute<InformativaPsp, String> identificativointermediario;
	public static volatile SingularAttribute<InformativaPsp, String> identificativopsp;
	public static volatile SingularAttribute<InformativaPsp, BigDecimal> modellopagamento;
	public static volatile SingularAttribute<InformativaPsp, BigDecimal> ordinamento;
	public static volatile SingularAttribute<InformativaPsp, String> origine;
	public static volatile SingularAttribute<InformativaPsp, BigDecimal> priorita;
	public static volatile SingularAttribute<InformativaPsp, String> ragionesociale;
	public static volatile SingularAttribute<InformativaPsp, String> statoinserimento;
	public static volatile SingularAttribute<InformativaPsp, BigDecimal> stornopagamento;
	public static volatile SingularAttribute<InformativaPsp, String> tipoversamento;
	public static volatile SingularAttribute<InformativaPsp, String> urlinformazionicanale;
	public static volatile SingularAttribute<InformativaPsp, String> urlinformazionipsp;
	public static volatile ListAttribute<InformativaPsp, FasciaCostoServizio> fasciaCostoServizios;
	public static volatile ListAttribute<InformativaPsp, InformazioniServizio> informazioniServizios;
}
