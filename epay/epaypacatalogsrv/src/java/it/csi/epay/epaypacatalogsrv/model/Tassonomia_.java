/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-25T12:10:21.803+0100")
@StaticMetamodel(Tassonomia.class)
public class Tassonomia_ extends AbstractPropagableParentEntity_ {
    public static volatile SingularAttribute<Tassonomia, Long> id;
    public static volatile SingularAttribute<Tassonomia, String> codTipoEnteCreditore;
    public static volatile SingularAttribute<Tassonomia, String> macroArea;
    public static volatile SingularAttribute<Tassonomia, String> nomeMacroArea;
    public static volatile SingularAttribute<Tassonomia, String> descrMacroArea;
    public static volatile SingularAttribute<Tassonomia, String> codTipologiaServizio;
    public static volatile SingularAttribute<Tassonomia, String> tipoServizio;
    public static volatile SingularAttribute<Tassonomia, String> motivoGiuridicoRiscossione;
    public static volatile SingularAttribute<Tassonomia, String> descrTipoServizio;
    public static volatile SingularAttribute<Tassonomia, String> nVersioneTassonomia;
    public static volatile SingularAttribute<Tassonomia, String> datiSpecificiIncasso;
    public static volatile SingularAttribute<Tassonomia, Date> dataFineValidita;
    public static volatile SingularAttribute<Tassonomia, Date> dataInizioValidita;

    public static volatile SingularAttribute<Tassonomia, Boolean> flagAggiornato;

    public static volatile SingularAttribute<Tassonomia, String> tipoEnteCreditore;

    public static volatile SingularAttribute<Tassonomia, Boolean> flagCancellato;

    public static volatile SingularAttribute<Tassonomia, Date> dataCancellazione;
}




