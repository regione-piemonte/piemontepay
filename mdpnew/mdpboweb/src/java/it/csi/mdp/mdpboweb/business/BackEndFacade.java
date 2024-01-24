/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;

import java.util.*;

import it.csi.mdp.mdpboweb.dto.*;

import org.apache.log4j.*;
import it.csi.mdp.mdpboweb.util.*;

/*PROTECTED REGION ID(R-1534196706) ENABLED START*/

/*PROTECTED REGION END*/

public class BackEndFacade {

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".business");

	//////////////////////////////////////////////////////////////////////////////
	/// Costanti identificative degli Application Data
	//////////////////////////////////////////////////////////////////////////////

	public final static String APPDATA_CURRENTUSER_CODE = "appDatacurrentUser";

	public final static String APPDATA_ISPOSTBACK_CODE = "appDataisPostBack";

	public final static String APPDATA_LASTWHERECLAUSE_CODE = "appDatalastWhereClause";

	public final static String APPDATA_RICERCATRANSAZIONE_CODE = "appDataricercaTransazione";

	public final static String APPDATA_TRANSAZIONI_CODE = "appDatatransazioni";

	public final static String APPDATA_TRANSAZIONE_CODE = "appDatatransazione";

	public final static String APPDATA_SELETTOREIDTRANSAZIONE_CODE = "appDataselettoreIdTransazione";

	public final static String APPDATA_SELETTOREOPERAZIONE_CODE = "appDataselettoreOperazione";

	public final static String APPDATA_STATITRANSAZIONE_CODE = "appDatastatiTransazione";

	public final static String APPDATA_STATOTRANSAZIONE_CODE = "appDatastatoTransazione";

	public final static String APPDATA_SELETTOREIDAPPLICAZIONE_CODE = "appDataselettoreIdApplicazione";

	public final static String APPDATA_APPLICAZIONE_CODE = "appDataapplicazione";

	public final static String APPDATA_APPLICAZIONI_CODE = "appDataapplicazioni";

	public final static String APPDATA_NUOVOEXTRAATTRIBUTE_CODE = "appDatanuovoExtraAttribute";

	public final static String APPDATA_EXTRAATTRIBUTES_CODE = "appDataextraAttributes";

	public final static String APPDATA_SELETTORECHIAVEATTR_CODE = "appDataselettoreChiaveAttr";

	public final static String APPDATA_NUOVAASSOCIAZIONEGW_MP_CODE = "appDatanuovaAssociazioneGW_MP";

	public final static String APPDATA_ASSOCIAZIONEGW_MP_CODE = "appDataassociazioneGW_MP";

	public final static String APPDATA_ASSOCIAZIONIGW_MP_CODE = "appDataassociazioniGW_MP";

	public final static String APPDATA_SELETTOREITEMASSOCIAZIONEGW_MP_CODE = "appDataselettoreItemAssociazioneGW_MP";

	public final static String APPDATA_CHKABILITAASSGW_MP_CODE = "appDatachkAbilitaAssGW_MP";

	public final static String APPDATA_GATEWAY_CODE = "appDatagateway";

	public final static String APPDATA_PAYMENTMODE_CODE = "appDatapaymentMode";

	public final static String APPDATA_GATEWAYS_CODE = "appDatagateways";

	public final static String APPDATA_PAYMENTMODES_CODE = "appDatapaymentModes";

	public final static String APPDATA_SELETTOREIDGATEWAY_CODE = "appDataselettoreIdGateway";

	public final static String APPDATA_SELETTOREIDPAYMENTMODE_CODE = "appDataselettoreIdPaymentMode";

	public final static String APPDATA_AUDIT_CODE = "appDataaudit";

	public final static String APPDATA_AUDITIES_CODE = "appDataaudities";

	public final static String APPDATA_APPFORAUDIT_CODE = "appDataappForAudit";

	public final static String APPDATA_APPFORAUDITFILTERED_CODE = "appDataappForAuditFiltered";

	public final static String APPDATA_AZIONIFORAUDIT_CODE = "appDataazioniForAudit";

	public final static String APPDATA_AZIONIFORAUDITFILTERED_CODE = "appDataazioniForAuditFiltered";

	public final static String APPDATA_UTENTIFORAUDIT_CODE = "appDatautentiForAudit";

	public final static String APPDATA_UTENTIFORAUDITFILTERED_CODE = "appDatautentiForAuditFiltered";

	public final static String APPDATA_RICERCAAUDIT_CODE = "appDataricercaAudit";

	public final static String APPDATA_SELETTOREAZIONE1_CODE = "appDataselettoreAzione1";

	public final static String APPDATA_SELETTOREAZIONE2_CODE = "appDataselettoreAzione2";

	public final static String APPDATA_SELETTOREAPP1_CODE = "appDataselettoreApp1";

	public final static String APPDATA_SELETTOREAPP2_CODE = "appDataselettoreApp2";

	public final static String APPDATA_SELETTOREUTENTE1_CODE = "appDataselettoreUtente1";

	public final static String APPDATA_SELETTOREUTENTE2_CODE = "appDataselettoreUtente2";

	public final static String APPDATA_AZIONE_CODE = "appDataazione";

	public final static String APPDATA_AZIONI_CODE = "appDataazioni";

	public final static String APPDATA_UTENTE_CODE = "appDatautente";

	public final static String APPDATA_UTENTI_CODE = "appDatautenti";

	public final static String APPDATA_FILTEREDAPPLICATION_CODE = "appDatafilteredApplication";

	public final static String APPDATA_FILTEREDAZIONI_CODE = "appDatafilteredAzioni";

	public final static String APPDATA_FILTEREDUTENTI_CODE = "appDatafilteredUtenti";

	public final static String APPDATA_TIPOLOGIACOMMISSIONI_CODE = "appDatatipologiaCommissioni";

	public final static String APPDATA_SELETTOREIDTIPOLOGIACOMMISSIONE_CODE = "appDataselettoreIdTipologiaCommissione";

	public final static String APPDATA_LINGUA_CODE = "appDatalingua";

	public final static String APPDATA_DIVISA_CODE = "appDatadivisa";

	public final static String APPDATA_DIVISE_CODE = "appDatadivise";

	public final static String APPDATA_LINGUE_CODE = "appDatalingue";

	public final static String APPDATA_CERCAERRORE_CODE = "appDatacercaErrore";

	public final static String APPDATA_ERRORI_CODE = "appDataerrori";

	public final static String APPDATA_ERRORE_CODE = "appDataerrore";

	public final static String APPDATA_STATITRANSAZIONEXCAMBIO_CODE = "appDatastatiTransazionexCambio";

	public final static String APPDATA_REPORTCERCATRANSAZIONI_CODE = "appDatareportCercaTransazioni";

	public final static String APPDATA_SIZEBLOCCO_CODE = "appDatasizeBlocco";

	public final static String APPDATA_SELETTOREPARAMETROCONF_CODE = "appDataselettoreParametroConf";

	public final static String APPDATA_PAGINAZIONETRANS_CODE = "appDatapaginazioneTrans";

	public final static String APPDATA_PARAMETRICONFIGURAZIONEBO_CODE = "appDataparametriConfigurazioneBO";

	public final static String APPDATA_PARAMETROCONFIGURAZIONEBO_CODE = "appDataparametroConfigurazioneBO";

	public final static String APPDATA_SELETTOREIDERRORE_CODE = "appDataselettoreIdErrore";

	public final static String APPDATA_SELETTOREIDLINGUA_CODE = "appDataselettoreIdLingua";

	public final static String APPDATA_SELETTOREIDDIVISA_CODE = "appDataselettoreIdDivisa";

	public final static String APPDATA_SELETTOREUTENTE_CODE = "appDataselettoreUtente";

	public final static String APPDATA_SELETTOREIDGATEWAY2_CODE = "appDataselettoreIdGateway2";

	public final static String APPDATA_EXTRAATTRIBUTESNUOVOGATEWAY_CODE = "appDataextraAttributesNuovoGateway";

	public final static String APPDATA_AZIONEGATEWAY_CODE = "appDataAzioneGateway";

	public final static String APPDATA_GRUPPO_CODE = "appDatagruppo";

	public final static String APPDATA_GRUPPI_CODE = "appDatagruppi";

	public final static String APPDATA_GRUPPORUOLO_CODE = "appDatagrupporuolo";

	public final static String APPDATA_GRUPPIRUOLI_CODE = "appDatagruppiruoli";

	public final static String APPDATA_GRUPPOAPPLICAZIONE_CODE = "appDatagruppoApplicazione";

	public final static String APPDATA_GRUPPIAPPLICAZIONI_CODE = "appDatagruppiApplicazioni";

	public final static String APPDATA_RUOLO_CODE = "appDataruolo";

	public final static String APPDATA_RUOLI_CODE = "appDataruoli";

	public final static String APPDATA_SELETTOREIDRUOLO_CODE = "appDataselettoreIdRuolo";

	public final static String APPDATA_SELETTOREIDGRUPPO_CODE = "appDataselettoreIdGruppo";

	public final static String APPDATA_SELETTOREAPPGROUP_CODE = "appDataselettoreAppGroup";

	public final static String APPDATA_APPFORGROUP_CODE = "appDataappForGroup";

	public final static String APPDATA_APPFORGROUPFILTERED_CODE = "appDataappForGroupFiltered";

	public final static String APPDATA_FILTEREDAPPLICATIONGROUP_CODE = "appDatafilteredApplicationGroup";

	public final static String APPDATA_LINGUECLONE_CODE = "appDatalingueClone";

	public final static String APPDATA_DIVISECLONE_CODE = "appDatadiviseClone";

	public final static String APPDATA_USERINFOEXT_CODE = "appDatauserInfoExt";

	public final static String APPDATA_SELETTOREGRUPPO_CODE = "appDataselettoreGruppo";

	public final static String APPDATA_UTENTIDELGRUPPO_CODE = "appDatautentiDelGruppo";

	public final static String APPDATA_GOTOLOGIN_CODE = "appDatagotologin";

	public final static String APPDATA_RICERCAGIORNALEEVENTI_CODE = "appDataricercaGiornaleEventi";

	public final static String APPDATA_SELETTOREIDGIORNALEEVENTO_CODE = "appDataselettoreIdGiornaleEvento";

	public final static String APPDATA_LISTAGIORNALIEVENTI_CODE = "appDatalistaGiornaliEventi";

	public final static String APPDATA_GIORNALEEVENTI_CODE = "appDatagiornaleEventi";

	public final static String APPDATA_SELETTOREIDRPT_CODE = "appDataselettoreIdRPT";

	public final static String APPDATA_RICERCARPT_CODE = "appDataricercaRPT";

	public final static String APPDATA_RICERCART_CODE = "appDataricercaRT";

	public final static String APPDATA_LISTARPT_CODE = "appDatalistaRPT";

	public final static String APPDATA_LISTART_CODE = "appDatalistaRT";

	public final static String APPDATA_SELETTOREIDRT_CODE = "appDataselettoreIdRT";

	public final static String APPDATA_LISTACODICIESITOPAGAMENTO_CODE = "appDatalistaCodiciEsitoPagamento";

	public final static String APPDATA_LISTASTATIRPT_CODE = "appDatalistaStatiRpt";

	public final static String APPDATA_STATIRPT_CODE = "appDatastatiRpt";

	public final static String APPDATA_CODICIESITOPAGAMENTO_CODE = "appDatacodiciEsitoPagamento";

	public final static String APPDATA_RICHIESTAPAGAMENTOTELEMATICO_CODE = "appDatarichiestaPagamentoTelematico";

	public final static String APPDATA_ENTI_CODE = "appDataenti";

	public final static String APPDATA_RICERCAENTI_CODE = "appDataricercaEnti";

	public final static String APPDATA_LISTAENTI_CODE = "appDatalistaEnti";

	public final static String APPDATA_SELETTOREIDENTE_CODE = "appDataselettoreIdEnte";

	public final static String APPDATA_LISTASTATIATTIVAZIONEENTI_CODE = "appDatalistaStatiAttivazioneEnti";

	public final static String APPDATA_FILTRORICERCAINFORMATIVEPSP_CODE = "appDatafiltroRicercaInformativePsp";

	public final static String APPDATA_INFORMATIVEPSP_CODE = "appDatainformativePsp";

	public final static String APPDATA_SELETTOREINFORMATIVEPSP_CODE = "appDataselettoreInformativePsp";

	public final static String APPDATA_LISTAINFORMATIVEPSP_CODE = "appDatalistaInformativePsp";

	public final static String APPDATA_FLUSSORIVERSAMENTO_CODE = "appDataflussoRiversamento";

	public final static String APPDATA_FLUSSOSINGOLOPAGAMENTO_CODE = "appDataflussoSingoloPagamento";

	public final static String APPDATA_LISTAFLUSSOSINGOLOPAGAMENTO_CODE = "appDatalistaFlussoSingoloPagamento";

	public final static String APPDATA_LISTAFLUSSORIVERSAMENTO_CODE = "appDatalistaFlussoRiversamento";

	public final static String APPDATA_RICERCAFLUSSORIVERSAMENTO_CODE = "appDataricercaFlussoRiversamento";

	public final static String APPDATA_RICERCAFLUSSOSINGOLOPAGAMENTO_CODE = "appDataricercaFlussoSingoloPagamento";

	public final static String APPDATA_SELETTOREFLUSSOSINGOLOPAGAMENTO_CODE = "appDataselettoreFlussoSingoloPagamento";

	public final static String APPDATA_SELETTOREFLUSSORIVERSAMENTO_CODE = "appDataselettoreFlussoRiversamento";

	public final static String APPDATA_STATORPTRISPOSTA_CODE = "appDatastatoRPTRisposta";

	public final static String APPDATA_SINGOLOSTATORPT_CODE = "appDatasingoloStatoRPT";

	public final static String APPDATA_LISTASINGOLOSTATORPT_CODE = "appDatalistaSingoloStatoRpt";

	public final static String APPDATA_SINGOLOSTATOVERSAMENTO_CODE = "appDatasingoloStatoVersamento";

	public final static String APPDATA_LISTASINGOLOSTATOVERSAMENTO_CODE = "appDatalistaSingoloStatoVersamento";

	public final static String APPDATA_LISTATIPOVERSAMENTO_CODE = "appDatalistaTipoversamento";

	public final static String APPDATA_LISTASTATISTICAAPPLICAZIONETRANSAZIONE_CODE = "appDatalistaStatisticaApplicazioneTransazione";

	public final static String APPDATA_STATISTICAAPPLICAZIONETRANSAZIONE_CODE = "appDatastatisticaApplicazioneTransazione";

	public final static String APPDATA_FILTRORICERCASTATISTICAAPPLICAZIONETRANSAZIONE_CODE = "appDatafiltroRicercaStatisticaApplicazioneTransazione";

	public final static String APPDATA_LISTAIBANENTEAPPLICATION_CODE = "appDatalistaIbanEnteApplication";

	public final static String APPDATA_IBANENTEAPPLICATION_CODE = "appDataibanEnteApplication";

	public final static String APPDATA_SELETTOREIBANENTEAPPLICATION_CODE = "appDataselettoreIbanEnteApplication";

	public final static String APPDATA_LISTAATTIVAZIONE_CODE = "appDatalistaAttivazione";

	public final static String APPDATA_KEYVALUE_CODE = "appDatakeyValue";

	//////////////////////////////////////////////////////////////////////////////
	/// Metodi associati alla U.I.
	/// - i metodi relativi a menu e azioni di inizializzazione sono direttamente 
	///   implementati in questa classe
	/// - i metodi relativi ai singoli content panel sono delegati nei rispettivi
	///   bean
	//////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpHomePageModel theModel

	) throws BEException {
		return getCPBECpHomePage().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		return getCPBECpGestioneGiornaleEventi().cerca(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults clear(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		return getCPBECpGestioneGiornaleEventi().clear(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglio(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		return getCPBECpGestioneGiornaleEventi().vaiAlDettaglio(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGiornaleEventiModel theModel

	) throws BEException {
		return getCPBECpGestioneGiornaleEventi().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGiornaleEventiModel theModel

	) throws BEException {
		return getCPBECpDettaglioGiornaleEventi().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGiornaleEventiModel theModel

	) throws BEException {
		return getCPBECpDettaglioGiornaleEventi().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneTransazioni().cerca(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults previousPage(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneTransazioni().previousPage(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nextPage(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneTransazioni().nextPage(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults dettaglioTransazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneTransazioni().dettaglioTransazione(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneTransazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneTransazioni().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cambiaStatoTransazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioTransazione().cambiaStatoTransazione(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioTransazione().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults verificaStato(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioTransazione().verificaStato(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults storno(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioTransazione().storno(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults conferma(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioTransazione().conferma(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioTransazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioTransazione().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneApplicazioni().nuovaApplicazione(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneApplicazioni().modificaApplicazione(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneApplicazioni().cancellaApplicazione(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneApplicazioniModel theModel

	) throws BEException {
		return getCPBECpGestioneApplicazioni().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goToNuovoUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		return getCPBECpGestioneUtenti().goToNuovoUtente(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		return getCPBECpGestioneUtenti().modificaUtente(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		return getCPBECpGestioneUtenti().cancellaUtente(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneUtentiModel theModel

	) throws BEException {
		return getCPBECpGestioneUtenti().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaApplicazione(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().salvaApplicazione(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaAssociazioneGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().modificaAssociazioneGW_MP(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults caricaComboModalitaPagamento(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().caricaComboModalitaPagamento(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults caricaAllegato(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().caricaAllegato(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults caricaAttributoSelezionato(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().caricaAttributoSelezionato(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults completaAttributo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().completaAttributo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaAssociazioneGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione()
				.salvaAssociazioneGW_MP(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults associaApplicazioneEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().associaApplicazioneEnte(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults disattivaIban(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().disattivaIban(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaIban(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().modificaIban(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaIban(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().salvaIban(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazione().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaApplicazione(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneNewModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazioneNew().salvaApplicazione(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneNewModel theModel

	) throws BEException {
		return getCPBECpDettaglioApplicazioneNew().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovoGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		return getCPBECpGestioneGW_PM().nuovoGateway(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults clonaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		return getCPBECpGestioneGW_PM().clonaGateway(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		return getCPBECpGestioneGW_PM().modificaGateway(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		return getCPBECpGestioneGW_PM().cancellaGateway(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovoPaymentMode(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		return getCPBECpGestioneGW_PM().nuovoPaymentMode(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaPaymentMode(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		return getCPBECpGestioneGW_PM().modificaPaymentMode(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel theModel

	) throws BEException {
		return getCPBECpGestioneGW_PM().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults aggiungiAttributo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		return getCPBECpDettaglioGateway().aggiungiAttributo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		return getCPBECpDettaglioGateway().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		return getCPBECpDettaglioGateway().salvaGateway(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults CaricaAssociazioneSelezionataGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		return getCPBECpDettaglioGateway().CaricaAssociazioneSelezionataGW_MP(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaAssociazioneGW_MP(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		return getCPBECpDettaglioGateway().salvaAssociazioneGW_MP(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel theModel

	) throws BEException {
		return getCPBECpDettaglioGateway().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioPaymentModeModel theModel

	) throws BEException {
		return getCPBECpDettaglioPaymentMode().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaModPagamento(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioPaymentModeModel theModel

	) throws BEException {
		return getCPBECpDettaglioPaymentMode().salvaModPagamento(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioPaymentModeModel theModel

	) throws BEException {
		return getCPBECpDettaglioPaymentMode().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaUtente(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioUtentiModel theModel

	) throws BEException {
		return getCPBECpDettaglioUtenti().salvaUtente(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioUtentiModel theModel

	) throws BEException {
		return getCPBECpDettaglioUtenti().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults addApplicazioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().addApplicazioneFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaApplicazioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().eliminaApplicazioneFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults addAzioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().addAzioneFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaAzioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().eliminaAzioneFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults addUtenteFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().addUtenteFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaUtenteFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().eliminaUtenteFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().cerca(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel theModel

	) throws BEException {
		return getCPBECpGestioneAuditing().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel theModel

	) throws BEException {
		return getCPBECpStoricoErrori().cerca(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults showError(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel theModel

	) throws BEException {
		return getCPBECpStoricoErrori().showError(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStoricoErroriModel theModel

	) throws BEException {
		return getCPBECpStoricoErrori().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovo(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpRicercaParametriConfigurazioneBO().nuovo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modifica(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpRicercaParametriConfigurazioneBO().modifica(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults elimina(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpRicercaParametriConfigurazioneBO().elimina(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpRicercaParametriConfigurazioneBO().refreshPanel(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpDettaglioParametroConfigurazioneBO().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salva(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpDettaglioParametroConfigurazioneBO().salva(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpDettaglioParametroConfigurazioneBO().refreshPanel(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpEliminaParametroConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpEliminaParametroConfigurazioneBO().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salva(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpEliminaParametroConfigurazioneBOModel theModel

	) throws BEException {
		return getCPBECpEliminaParametroConfigurazioneBO().salva(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults gotoNuovoGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		return getCPBECpGestioneGruppi().gotoNuovoGruppo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults gotoModificaGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		return getCPBECpGestioneGruppi().gotoModificaGruppo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cancellaGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		return getCPBECpGestioneGruppi().cancellaGruppo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGruppiModel theModel

	) throws BEException {
		return getCPBECpGestioneGruppi().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults addApplicazioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		return getCPBECpDettaglioGruppo().addApplicazioneFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaApplicazioneFiltro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		return getCPBECpDettaglioGruppo().eliminaApplicazioneFiltro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		return getCPBECpDettaglioGruppo().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaGruppo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		return getCPBECpDettaglioGruppo().salvaGruppo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGruppoModel theModel

	) throws BEException {
		return getCPBECpDettaglioGruppo().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults valorizzaDettGateway(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNewClone().valorizzaDettGateway(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaAttributoSelezionato(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNewClone().eliminaAttributoSelezionato(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults aggiungiAttributo(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNewClone().aggiungiAttributo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNewClone().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaGateway(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNewClone().salvaGateway(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewCloneModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNewClone().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaAttributoSelezionato(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNew().eliminaAttributoSelezionato(
				theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults aggiungiAttributo(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNew().aggiungiAttributo(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNew().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults salvaGateway(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNew().salvaGateway(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayNewModel theModel

	) throws BEException {
		return getCPBECpDettaglioGatewayNew().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cercaRTI(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		return getCPBECpGestioneRPT().cercaRTI(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglioRTI(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		return getCPBECpGestioneRPT().vaiAlDettaglioRTI(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRptXml(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		return getCPBECpGestioneRPT().esportaRptXml(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRTAssociata(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		return getCPBECpGestioneRPT().esportaRTAssociata(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults dettStatiRPT(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		return getCPBECpGestioneRPT().dettStatiRPT(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRPTModel theModel

	) throws BEException {
		return getCPBECpGestioneRPT().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneStatiRptModel theModel

	) throws BEException {
		return getCPBECpGestioneStatiRpt().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneStatiRptModel theModel

	) throws BEException {
		return getCPBECpGestioneStatiRpt().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricercaRT(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		return getCPBECpGestioneRT().ricercaRT(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRtXml(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		return getCPBECpGestioneRT().esportaRtXml(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esportaRPTassociata(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		return getCPBECpGestioneRT().esportaRPTassociata(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneRTModel theModel

	) throws BEException {
		return getCPBECpGestioneRT().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goBack(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioRPTModel theModel

	) throws BEException {
		return getCPBECpDettaglioRPT().goBack(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioRPTModel theModel

	) throws BEException {
		return getCPBECpDettaglioRPT().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults cercaEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		return getCPBECpRicercaEnti().cercaEnte(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults clean(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		return getCPBECpRicercaEnti().clean(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults nuovoEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		return getCPBECpRicercaEnti().nuovoEnte(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modificaEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		return getCPBECpRicercaEnti().modificaEnte(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults eliminaEnte(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		return getCPBECpRicercaEnti().eliminaEnte(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaEntiModel theModel

	) throws BEException {
		return getCPBECpRicercaEnti().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults indietro(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		return getCPBECpGestioneEnti().indietro(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults inserisci(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		return getCPBECpGestioneEnti().inserisci(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults modifica(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		return getCPBECpGestioneEnti().modifica(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneEntiModel theModel

	) throws BEException {
		return getCPBECpGestioneEnti().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricercaInformativa(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel theModel

	) throws BEException {
		return getCPBECpInformativePsp().ricercaInformativa(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglio(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel theModel

	) throws BEException {
		return getCPBECpInformativePsp().vaiAlDettaglio(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpInformativePspModel theModel

	) throws BEException {
		return getCPBECpInformativePsp().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goBack(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioInformativaPspModel theModel

	) throws BEException {
		return getCPBECpDettaglioInformativaPsp().goBack(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioInformativaPspModel theModel

	) throws BEException {
		return getCPBECpDettaglioInformativaPsp().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		return getCPBECpFlussoRiversamento().ricerca(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults verificaFlussi(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		return getCPBECpFlussoRiversamento().verificaFlussi(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults vaiAlDettaglio(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		return getCPBECpFlussoRiversamento().vaiAlDettaglio(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoRiversamentoModel theModel

	) throws BEException {
		return getCPBECpFlussoRiversamento().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults esporta(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioFlussoRiversamentoModel theModel

	) throws BEException {
		return getCPBECpDettaglioFlussoRiversamento().esporta(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

			it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioFlussoRiversamentoModel theModel

	) throws BEException {
		return getCPBECpDettaglioFlussoRiversamento().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricercaSingliFlussi(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel theModel

	) throws BEException {
		return getCPBECpFlussoSingoloPagamento().ricercaSingliFlussi(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goToTestata(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel theModel

	) throws BEException {
		return getCPBECpFlussoSingoloPagamento().goToTestata(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpFlussoSingoloPagamentoModel theModel

	) throws BEException {
		return getCPBECpFlussoSingoloPagamento().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults goToFlussi(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpVerificaFlussiModel theModel

	) throws BEException {
		return getCPBECpVerificaFlussi().goToFlussi(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpVerificaFlussiModel theModel

	) throws BEException {
		return getCPBECpVerificaFlussi().refreshPanel(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults ricerca(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel theModel

	) throws BEException {
		return getCPBECpStatisticheTranzazioni().ricerca(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults exportCsv(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel theModel

	) throws BEException {
		return getCPBECpStatisticheTranzazioni().exportCsv(theModel);
	}

	////////////////////////////////////////////////////////////////////////////////////

	public ExecResults refreshPanel(

	it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpStatisticheTranzazioniModel theModel

	) throws BEException {
		return getCPBECpStatisticheTranzazioni().refreshPanel(theModel);
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property relative ai bean spring associati agli specifici content panel
	//////////////////////////////////////////////////////////////////////////////

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpHomePage _CPBECpHomePage = null;

	public void setCPBECpHomePage(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpHomePage bean) {
		_CPBECpHomePage = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpHomePage getCPBECpHomePage() {
		return _CPBECpHomePage;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGiornaleEventi _CPBECpGestioneGiornaleEventi = null;

	public void setCPBECpGestioneGiornaleEventi(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGiornaleEventi bean) {
		_CPBECpGestioneGiornaleEventi = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGiornaleEventi getCPBECpGestioneGiornaleEventi() {
		return _CPBECpGestioneGiornaleEventi;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGiornaleEventi _CPBECpDettaglioGiornaleEventi = null;

	public void setCPBECpDettaglioGiornaleEventi(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGiornaleEventi bean) {
		_CPBECpDettaglioGiornaleEventi = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGiornaleEventi getCPBECpDettaglioGiornaleEventi() {
		return _CPBECpDettaglioGiornaleEventi;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneTransazioni _CPBECpGestioneTransazioni = null;

	public void setCPBECpGestioneTransazioni(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneTransazioni bean) {
		_CPBECpGestioneTransazioni = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneTransazioni getCPBECpGestioneTransazioni() {
		return _CPBECpGestioneTransazioni;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioTransazione _CPBECpDettaglioTransazione = null;

	public void setCPBECpDettaglioTransazione(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioTransazione bean) {
		_CPBECpDettaglioTransazione = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioTransazione getCPBECpDettaglioTransazione() {
		return _CPBECpDettaglioTransazione;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneApplicazioni _CPBECpGestioneApplicazioni = null;

	public void setCPBECpGestioneApplicazioni(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneApplicazioni bean) {
		_CPBECpGestioneApplicazioni = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneApplicazioni getCPBECpGestioneApplicazioni() {
		return _CPBECpGestioneApplicazioni;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneUtenti _CPBECpGestioneUtenti = null;

	public void setCPBECpGestioneUtenti(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneUtenti bean) {
		_CPBECpGestioneUtenti = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneUtenti getCPBECpGestioneUtenti() {
		return _CPBECpGestioneUtenti;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioApplicazione _CPBECpDettaglioApplicazione = null;

	public void setCPBECpDettaglioApplicazione(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioApplicazione bean) {
		_CPBECpDettaglioApplicazione = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioApplicazione getCPBECpDettaglioApplicazione() {
		return _CPBECpDettaglioApplicazione;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioApplicazioneNew _CPBECpDettaglioApplicazioneNew = null;

	public void setCPBECpDettaglioApplicazioneNew(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioApplicazioneNew bean) {
		_CPBECpDettaglioApplicazioneNew = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioApplicazioneNew getCPBECpDettaglioApplicazioneNew() {
		return _CPBECpDettaglioApplicazioneNew;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGW_PM _CPBECpGestioneGW_PM = null;

	public void setCPBECpGestioneGW_PM(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGW_PM bean) {
		_CPBECpGestioneGW_PM = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGW_PM getCPBECpGestioneGW_PM() {
		return _CPBECpGestioneGW_PM;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGateway _CPBECpDettaglioGateway = null;

	public void setCPBECpDettaglioGateway(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGateway bean) {
		_CPBECpDettaglioGateway = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGateway getCPBECpDettaglioGateway() {
		return _CPBECpDettaglioGateway;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioPaymentMode _CPBECpDettaglioPaymentMode = null;

	public void setCPBECpDettaglioPaymentMode(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioPaymentMode bean) {
		_CPBECpDettaglioPaymentMode = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioPaymentMode getCPBECpDettaglioPaymentMode() {
		return _CPBECpDettaglioPaymentMode;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioUtenti _CPBECpDettaglioUtenti = null;

	public void setCPBECpDettaglioUtenti(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioUtenti bean) {
		_CPBECpDettaglioUtenti = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioUtenti getCPBECpDettaglioUtenti() {
		return _CPBECpDettaglioUtenti;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneAuditing _CPBECpGestioneAuditing = null;

	public void setCPBECpGestioneAuditing(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneAuditing bean) {
		_CPBECpGestioneAuditing = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneAuditing getCPBECpGestioneAuditing() {
		return _CPBECpGestioneAuditing;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpStoricoErrori _CPBECpStoricoErrori = null;

	public void setCPBECpStoricoErrori(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpStoricoErrori bean) {
		_CPBECpStoricoErrori = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpStoricoErrori getCPBECpStoricoErrori() {
		return _CPBECpStoricoErrori;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpRicercaParametriConfigurazioneBO _CPBECpRicercaParametriConfigurazioneBO = null;

	public void setCPBECpRicercaParametriConfigurazioneBO(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpRicercaParametriConfigurazioneBO bean) {
		_CPBECpRicercaParametriConfigurazioneBO = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpRicercaParametriConfigurazioneBO getCPBECpRicercaParametriConfigurazioneBO() {
		return _CPBECpRicercaParametriConfigurazioneBO;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioParametroConfigurazioneBO _CPBECpDettaglioParametroConfigurazioneBO = null;

	public void setCPBECpDettaglioParametroConfigurazioneBO(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioParametroConfigurazioneBO bean) {
		_CPBECpDettaglioParametroConfigurazioneBO = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioParametroConfigurazioneBO getCPBECpDettaglioParametroConfigurazioneBO() {
		return _CPBECpDettaglioParametroConfigurazioneBO;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpEliminaParametroConfigurazioneBO _CPBECpEliminaParametroConfigurazioneBO = null;

	public void setCPBECpEliminaParametroConfigurazioneBO(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpEliminaParametroConfigurazioneBO bean) {
		_CPBECpEliminaParametroConfigurazioneBO = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpEliminaParametroConfigurazioneBO getCPBECpEliminaParametroConfigurazioneBO() {
		return _CPBECpEliminaParametroConfigurazioneBO;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGruppi _CPBECpGestioneGruppi = null;

	public void setCPBECpGestioneGruppi(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGruppi bean) {
		_CPBECpGestioneGruppi = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneGruppi getCPBECpGestioneGruppi() {
		return _CPBECpGestioneGruppi;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGruppo _CPBECpDettaglioGruppo = null;

	public void setCPBECpDettaglioGruppo(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGruppo bean) {
		_CPBECpDettaglioGruppo = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGruppo getCPBECpDettaglioGruppo() {
		return _CPBECpDettaglioGruppo;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGatewayNewClone _CPBECpDettaglioGatewayNewClone = null;

	public void setCPBECpDettaglioGatewayNewClone(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGatewayNewClone bean) {
		_CPBECpDettaglioGatewayNewClone = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGatewayNewClone getCPBECpDettaglioGatewayNewClone() {
		return _CPBECpDettaglioGatewayNewClone;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGatewayNew _CPBECpDettaglioGatewayNew = null;

	public void setCPBECpDettaglioGatewayNew(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGatewayNew bean) {
		_CPBECpDettaglioGatewayNew = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioGatewayNew getCPBECpDettaglioGatewayNew() {
		return _CPBECpDettaglioGatewayNew;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneRPT _CPBECpGestioneRPT = null;

	public void setCPBECpGestioneRPT(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneRPT bean) {
		_CPBECpGestioneRPT = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneRPT getCPBECpGestioneRPT() {
		return _CPBECpGestioneRPT;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneStatiRpt _CPBECpGestioneStatiRpt = null;

	public void setCPBECpGestioneStatiRpt(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneStatiRpt bean) {
		_CPBECpGestioneStatiRpt = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneStatiRpt getCPBECpGestioneStatiRpt() {
		return _CPBECpGestioneStatiRpt;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneRT _CPBECpGestioneRT = null;

	public void setCPBECpGestioneRT(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneRT bean) {
		_CPBECpGestioneRT = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneRT getCPBECpGestioneRT() {
		return _CPBECpGestioneRT;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioRPT _CPBECpDettaglioRPT = null;

	public void setCPBECpDettaglioRPT(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioRPT bean) {
		_CPBECpDettaglioRPT = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioRPT getCPBECpDettaglioRPT() {
		return _CPBECpDettaglioRPT;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpRicercaEnti _CPBECpRicercaEnti = null;

	public void setCPBECpRicercaEnti(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpRicercaEnti bean) {
		_CPBECpRicercaEnti = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpRicercaEnti getCPBECpRicercaEnti() {
		return _CPBECpRicercaEnti;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneEnti _CPBECpGestioneEnti = null;

	public void setCPBECpGestioneEnti(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneEnti bean) {
		_CPBECpGestioneEnti = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpGestioneEnti getCPBECpGestioneEnti() {
		return _CPBECpGestioneEnti;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpInformativePsp _CPBECpInformativePsp = null;

	public void setCPBECpInformativePsp(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpInformativePsp bean) {
		_CPBECpInformativePsp = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpInformativePsp getCPBECpInformativePsp() {
		return _CPBECpInformativePsp;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioInformativaPsp _CPBECpDettaglioInformativaPsp = null;

	public void setCPBECpDettaglioInformativaPsp(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioInformativaPsp bean) {
		_CPBECpDettaglioInformativaPsp = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioInformativaPsp getCPBECpDettaglioInformativaPsp() {
		return _CPBECpDettaglioInformativaPsp;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpFlussoRiversamento _CPBECpFlussoRiversamento = null;

	public void setCPBECpFlussoRiversamento(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpFlussoRiversamento bean) {
		_CPBECpFlussoRiversamento = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpFlussoRiversamento getCPBECpFlussoRiversamento() {
		return _CPBECpFlussoRiversamento;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioFlussoRiversamento _CPBECpDettaglioFlussoRiversamento = null;

	public void setCPBECpDettaglioFlussoRiversamento(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioFlussoRiversamento bean) {
		_CPBECpDettaglioFlussoRiversamento = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpDettaglioFlussoRiversamento getCPBECpDettaglioFlussoRiversamento() {
		return _CPBECpDettaglioFlussoRiversamento;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpFlussoSingoloPagamento _CPBECpFlussoSingoloPagamento = null;

	public void setCPBECpFlussoSingoloPagamento(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpFlussoSingoloPagamento bean) {
		_CPBECpFlussoSingoloPagamento = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpFlussoSingoloPagamento getCPBECpFlussoSingoloPagamento() {
		return _CPBECpFlussoSingoloPagamento;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpVerificaFlussi _CPBECpVerificaFlussi = null;

	public void setCPBECpVerificaFlussi(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpVerificaFlussi bean) {
		_CPBECpVerificaFlussi = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpVerificaFlussi getCPBECpVerificaFlussi() {
		return _CPBECpVerificaFlussi;
	}

	private it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpStatisticheTranzazioni _CPBECpStatisticheTranzazioni = null;

	public void setCPBECpStatisticheTranzazioni(
			it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpStatisticheTranzazioni bean) {
		_CPBECpStatisticheTranzazioni = bean;
	}

	public it.csi.mdp.mdpboweb.business.mdpbackoffice.CPBECpStatisticheTranzazioni getCPBECpStatisticheTranzazioni() {
		return _CPBECpStatisticheTranzazioni;
	}

	//////////////////////////////////////////////////////////////////////////////
	/// Property aggiuntive del bean
	//////////////////////////////////////////////////////////////////////////////
	/*PROTECTED REGION ID(R-1264235389) ENABLED START*/
	//// inserire qui le property che si vogliono iniettare in questo bean (es. dao, proxy di pd, ...) 
	/*PROTECTED REGION END*/
}
