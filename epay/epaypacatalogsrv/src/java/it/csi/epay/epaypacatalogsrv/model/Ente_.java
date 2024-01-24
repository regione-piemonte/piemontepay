/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-12-06T17:40:08.427+0100")
@StaticMetamodel(Ente.class)
public class Ente_ extends AbstractChangeTrackedParentEntity_ {
    public static volatile SingularAttribute<Ente, Long> id;
    public static volatile SingularAttribute<Ente, String> cap;
    public static volatile SingularAttribute<Ente, String> codiceFiscale;
    public static volatile SingularAttribute<Ente, String> denominazione;
    public static volatile SingularAttribute<Ente, Boolean> flagAccertamento;
    public static volatile SingularAttribute<Ente, Boolean> flagEntePlurintermediato;
    public static volatile SingularAttribute<Ente, Boolean> flagRicezioneErrori;
    public static volatile SingularAttribute<Ente, Boolean> flagRiconciliazioneVersamenti;
    public static volatile SingularAttribute<Ente, Boolean> flagRicezioneFlussoBaseRendicontazione;
    public static volatile SingularAttribute<Ente, Boolean> flagQualsiasiCodiceVersamento;
    public static volatile SingularAttribute<Ente, String> indirizzo;
    public static volatile SingularAttribute<Ente, String> localita;
    public static volatile SingularAttribute<Ente, byte[]> logoContent;
    public static volatile SingularAttribute<Ente, String> logoContentType;
    public static volatile SingularAttribute<Ente, String> logoFileName;
    public static volatile SingularAttribute<Ente, Integer> logoFileSize;
    public static volatile SingularAttribute<Ente, String> siglaProvincia;
    public static volatile SingularAttribute<Ente, Integer> giornoSchedulazione;
    public static volatile SingularAttribute<Ente, String> descrizioneErroreAggiornamento;
    public static volatile SingularAttribute<Ente, String> civico;
    public static volatile SingularAttribute<Ente, String> email;
    public static volatile SingularAttribute<Ente, String> gs1Gln;
    public static volatile SingularAttribute<Ente, String> cbill;
    public static volatile SingularAttribute<Ente, String> iban;
    public static volatile SingularAttribute<Ente, String> bic;
    public static volatile SingularAttribute<Ente, String> templateEmailId;
    public static volatile SingularAttribute<Ente, String> urlDominio;
    public static volatile SingularAttribute<Ente, String> codiceIpa;
    public static volatile SingularAttribute<Ente, Utente> utenteAmministratore;
    public static volatile SingularAttribute<Ente, ModalitaAcquisizioneProvvisori> modalitaAcquisizioneProvvisori;
    public static volatile SingularAttribute<Ente, PeriodicitaSchedulazioneRiconciliazione> periodicitaSchedulazioneRiconciliazione;
    public static volatile SingularAttribute<Ente, StatoAggiornamentoEnte> statoAggiornamentoEnte;
    public static volatile SingularAttribute<Ente, TipologiaAccertamento> tipologiaAccertamento;
    public static volatile ListAttribute<Ente, AssociazioneUtenteEnte> utenteEnteList;
    public static volatile SetAttribute<Ente, CodiceVersamento> codiciVersamento;
    public static volatile SetAttribute<Ente, CodiceVersamento> codiciVersamentoCompleti;

    public static volatile SingularAttribute<Ente, String> codiceTipoEnteCreditore;
}
