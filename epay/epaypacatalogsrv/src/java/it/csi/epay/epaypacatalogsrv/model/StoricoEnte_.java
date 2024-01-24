/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-27T14:59:18.493+0100")
@StaticMetamodel(StoricoEnte.class)
public class StoricoEnte_ extends AbstractChangeTrackedParentEntity_ {
	public static volatile SingularAttribute<StoricoEnte, Long> id;
	public static volatile SingularAttribute<StoricoEnte, Long> idEnte;
	public static volatile SingularAttribute<StoricoEnte, String> cap;
	public static volatile SingularAttribute<StoricoEnte, String> codiceFiscale;
	public static volatile SingularAttribute<StoricoEnte, String> denominazione;
	public static volatile SingularAttribute<StoricoEnte, Boolean> flagAccertamento;
	public static volatile SingularAttribute<StoricoEnte, Boolean> flagEntePlurintermediato;
	public static volatile SingularAttribute<StoricoEnte, Boolean> flagRicezioneErrori;
	public static volatile SingularAttribute<StoricoEnte, Boolean> flagRiconciliazioneVersamenti;
	public static volatile SingularAttribute<StoricoEnte, Boolean> flagRicezioneFlussoBaseRendicontazione;
	public static volatile SingularAttribute<StoricoEnte, Boolean> flagQualsiasiCodiceVersamento;
	public static volatile SingularAttribute<StoricoEnte, String> indirizzo;
	public static volatile SingularAttribute<StoricoEnte, String> localita;
	public static volatile SingularAttribute<StoricoEnte, byte[]> logoContent;
	public static volatile SingularAttribute<StoricoEnte, String> logoContentType;
	public static volatile SingularAttribute<StoricoEnte, String> logoFileName;
	public static volatile SingularAttribute<StoricoEnte, Integer> logoFileSize;
	public static volatile SingularAttribute<StoricoEnte, String> siglaProvincia;
	public static volatile SingularAttribute<StoricoEnte, Integer> giornoSchedulazione;
	public static volatile SingularAttribute<StoricoEnte, String> descrizioneErroreAggiornamento;
	public static volatile SingularAttribute<StoricoEnte, String> civico;
	public static volatile SingularAttribute<StoricoEnte, String> email;
	public static volatile SingularAttribute<StoricoEnte, String> gs1Gln;
	public static volatile SingularAttribute<StoricoEnte, String> cbill;
	public static volatile SingularAttribute<StoricoEnte, String> iban;
    public static volatile SingularAttribute<StoricoEnte, String> templateEmailId;
    public static volatile SingularAttribute<StoricoEnte, String> urlDominio;
    public static volatile SingularAttribute<StoricoEnte, String> codiceIpa;
	public static volatile SingularAttribute<StoricoEnte, Utente> utenteAmministratore;
	public static volatile SingularAttribute<StoricoEnte, ModalitaAcquisizioneProvvisori> modalitaAcquisizioneProvvisori;
	public static volatile SingularAttribute<StoricoEnte, PeriodicitaSchedulazioneRiconciliazione> periodicitaSchedulazioneRiconciliazione;
	public static volatile SingularAttribute<StoricoEnte, StatoAggiornamentoEnte> statoAggiornamentoEnte;
	public static volatile SingularAttribute<StoricoEnte, TipologiaAccertamento> tipologiaAccertamento;
}
