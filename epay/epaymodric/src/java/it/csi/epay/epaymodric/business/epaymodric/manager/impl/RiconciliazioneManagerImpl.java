/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Catalogo;
import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.business.epaymodric.bo.Errore;
import it.csi.epay.epaymodric.business.epaymodric.bo.Flusso;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.bo.Provvisori;
import it.csi.epay.epaymodric.business.epaymodric.bo.Psp;
import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.facade.rest.ComponentiPagamentoDaSportello;
import it.csi.epay.epaymodric.business.epaymodric.manager.CatalogoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.CodiciVersamentoConfigManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.CodiciVersamentoDaEscudereManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ElaborazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EnteManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ErroreManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoDettaglioManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoOrigineManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoRendicontazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.InvioMailManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.ProvvisoriManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.PspManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.RiconciliazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.StatoFlussoErroreManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.TrasmissioneFlussiManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.ProvvisoriUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.StatoFlussoErroreUtility;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOErroreFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.rest.ComponentiImportoOutput;
import it.csi.epay.epaymodric.dto.epaymodric.rest.SingoloComponenteImportoOutput;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSpacchettamentoFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.ElaborazioneException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.FlussoOrigineException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.FlussoSintesiException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.InputNotValidException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.RiconciliazioneException;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ResponseType;

@Service
public class RiconciliazioneManagerImpl implements RiconciliazioneManager {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    public static final String DEFAULT_PROCESSING_ERROR_MESSAGE = "Elaborazione in errore.";

    public static final String DEFAULT_PROCESSING_ERROR_MESSAGE_SOFT = "L'elaborazione e' terminata con uno o piu' flussi in errore";

    @Autowired
    private ErroreManager erroreManager;

    @Autowired
    private ElaborazioneManager elaborazioneManager;

    @Autowired
    private FlussoOrigineManager flussoOrigineManager;

    @Autowired
    private StatoFlussoErroreManager statoFlussoErroreManager;

    @Autowired
    private FlussoManager flussoManager;

    @Autowired
    private EnteManager enteManager;

    @Autowired
    private ConfigurazioneManager configurazioneManager;

    @Autowired
    private ProvvisoriManager provvisoriManager;

    @Autowired
    private PspManager pspManager;

    @Autowired
    private CodiciVersamentoDaEscudereManager codiciVersamentoDaEscudereManager;

    @Autowired
    private InvioMailManager invioMailManager;

    @Autowired
    private TrasmissioneFlussiManager trasmissioneFlussiManager;

    @Autowired
    private FlussoRendicontazioneManager flussoRendicontazioneManager;

    @Autowired
    private FlussoDettaglioManager flussoDettaglioManager;

    //	@Autowired
    //	private ListaDiCaricoManager listaDiCaricoManager;

    @Autowired
    private CatalogoManager catalogoManager;

    @Autowired
    private CodiciVersamentoConfigManager codiciVersamentoConfigManager;

    @Autowired
    ComponentiPagamentoDaSportello chiamateSportello;

    
    /**
     * @author SB
     *
     */
    @Override
    public DTOOutputWsMotoreDiRiconciliazione elaborazioneEsegui (Map<String, Configurazione> configurazioneEnte,
        Elaborazione elaborazione, List<String> statiDaEscludere){
        LOGGER.info("RiconciliazioneManagerImpl.eseguiRieseguiElaborazione INIZIO");

        DTOOutputWsMotoreDiRiconciliazione output = new DTOOutputWsMotoreDiRiconciliazione();
        Ente ente = null;

        try {
	     ente = recuperaEnte(elaborazione.getIdEnte());
	     validaConfigurazioneEnte(configurazioneEnte, ente);
        
	      Configurazione numeroMassimoFlussiPerPagina = configurazioneEnte.get ( Costanti.PAGE_SIZE_FLUSSI_ORIG );	
	      Integer pageSize = new Integer ( numeroMassimoFlussiPerPagina.getValore () );	
	      LOGGER.info ( String.format ( "Numero massimo di flussi origine per pagina: %d", pageSize ) );	
	      long numFlussiDaElaborare=flussoOrigineManager.contaFlussiOrigineDaElaborare ( ente.getCodiceFiscale (), elaborazione.getListaFlussi (), statiDaEscludere );	
	      LOGGER.info ( String.format ( "Totale flussi: %d", numFlussiDaElaborare ) );	
	      int numeroPagine = (int) ( numFlussiDaElaborare / pageSize ) + 1;	
	      LOGGER.info ( String.format ( "Totale pagine: %d", numeroPagine ) );
	      List<FlussoOrigine> flussiOrigineOriginali= new ArrayList<FlussoOrigine>();
	      List<FlussoOrigine> flussiElaborati= new ArrayList<FlussoOrigine>();
	
	      for ( int i = 0; i < numeroPagine; i++ ) {
				boolean firstPage=false;
				boolean lastPage=false;	
				
				if (i==0)firstPage=true;
				if (i==numeroPagine-1)lastPage=true;
				output=eseguiRieseguiElaborazione ( configurazioneEnte, elaborazione, statiDaEscludere, i, pageSize, firstPage, lastPage,
						flussiOrigineOriginali,  flussiElaborati );
	      }
        
        } catch (ElaborazioneException elaborazioneException) {
            if (CostantiErrori.ELABORAZIONE_NON_PRESENTE.equals(elaborazioneException.getCodiceErrore())) {
                output.setEsito(new DTOOutputWsEsito("OK", CostantiErrori.WS_ESITO_OK_DEFAULT,
                                "Nessuna elaborazione disponibile per i parametri di input"));
            } else {
                output.setEsito( new DTOOutputWsEsito("KO", CostantiErrori.WS_ESITO_KO_DEFAULT, "Elaborazione in errore"));
            }
        } 
        
        return output;
    }

    /**
     * @author lfantini
     *
     */
    @Override
    public DTOOutputWsMotoreDiRiconciliazione eseguiRieseguiElaborazione(Map<String, Configurazione> configurazioneEnte,
        Elaborazione elaborazione, List<String> statiDaEscludere, int pageIdx, Integer pageSize, boolean firstPage, boolean lastPage, 
        List<FlussoOrigine> flussiOrigineOriginali, List<FlussoOrigine>   flussiElaborati) {

        LOGGER.info("RiconciliazioneManagerImpl.eseguiRieseguiElaborazione INIZIO");

        DTOOutputWsMotoreDiRiconciliazione output = new DTOOutputWsMotoreDiRiconciliazione();

        /*
         * 0 - Controlli sull'input - ente obbligatorio - rielaborazione true o false
         * (null = errore) - altri input facoltativi --- ERRORE --- Scrittura solo su
         * output WS
         */
        List<DTOErroreFlusso> errori = new ArrayList<>();
        Ente ente = null;
        List<FlussoOrigine> flussiOrigineDaElaborare = null;
//        List<FlussoOrigine> flussiOrigineOriginali = null;
        try {

            ente = recuperaEnte(elaborazione.getIdEnte());
            validaConfigurazioneEnte(configurazioneEnte, ente);

            /*
             * 1 - Elaborazione Deve esistere un record sulla cbl_t_elaborazione con stato
             * SCHEDULATA per l'ente in esame Il record deve avere data inizio <= data
             * corrente --- ERRORE --- Scrittura solo su output WS (non esiste record su
             * tabella che soddisfi requisiti)
             */
            if (firstPage) aggiornaStatoElaborazione(elaborazione, StatoElaborazioneEnum.AVVIATA, flussiOrigineOriginali);

            /* INIZIO DELLA TRANSAZIONE */

            /*
             * 2 - Update sulla cbl_t_flusso_origine - Recuperare tutti i flussi inerenti
             * l'elaborazione (IN BOZZA o IN ATTESA). Se presente il parametro
             * flussiDaElaborare estrarre solo quelli - Se il parametro rielaborazione e'
             * true - Aggiornare tutti i record sulla cbl_t_flusso origine impostando ente,
             * elaborazione e stato a IN ELABORAZIONE --- ERRORE --- Scrittura sulla tabella
             * cbl_t_elaborazione del messaggio di errore relativo (nessun flusso
             * elaborabile) e su output WS. Impostare stato a TERMINATA
             */

            // SPOSTATO CONTROLLO SEGUENTE FUORI DAL LOOP SUI FLUSSI ORIGINE (PER QUESTIONI
            // DI PERFORMANCE)
            /*
             * 3 - Verifica riconciliabilita' ente - l'ente deve essere riconciliabile, con
             * flag accertamento a true e non deve essere plurintermediato. ovviamente se
             * ente non esiste -> errore --- ERRORE --- scrittura in output sul ws,
             * elaborazione al massimo in terminata con errori, stato flusso origine in NON
             * ELABORABILE
             */


//            Configurazione numeroMassimoFlussiPerPagina = configurazioneEnte.get ( Costanti.PAGE_SIZE_FLUSSI_ORIG );
//
//            Integer pageSize = new Integer ( numeroMassimoFlussiPerPagina.getValore () );
//
//            LOGGER.info ( String.format ( "Numero massimo di flussi origine per pagina: %d", pageSize ) );
//
//            long numFlussiDaElaborare
//                = flussoOrigineManager.contaFlussiOrigineDaElaborare ( ente.getCodiceFiscale (), elaborazione.getListaFlussi (), statiDaEscludere );
//
//            LOGGER.info ( String.format ( "Totale flussi: %d", numFlussiDaElaborare ) );
//
//            int numeroPagine = (int) ( numFlussiDaElaborare / pageSize ) + 1;
//
//            LOGGER.info ( String.format ( "Totale pagine: %d", numeroPagine ) );
//
//            for ( int i = 0; i < numeroPagine; i++ ) {

//                LOGGER.info ( String.format ( "Inizio elaborazione pagina %d di %d", i + 1, numeroPagine ) );

            
                //:TODO lollino sistemare query e tirare su nuovi campi sugli importi.
                flussiOrigineDaElaborare = recuperaFlussiOrigineDaElaborareEVerificaRiconciliabilita ( ente, elaborazione,
                    elaborazione.getListaFlussi (), errori, pageIdx, pageSize, statiDaEscludere );
                /* COMMIT DELLA TRANSAZIONE */
                flussiOrigineOriginali.addAll(copiaFlussiOrigine ( flussiOrigineDaElaborare ));
                /* INIZIO DELLA TRANSAZIONE */

                /*
                 * 4 - Recupero dei provvisori - Recuperare i provvisori dei flussi da elaborare chiamando apposito servizio --- ERRORE --- L'elaborazione potr�
                 * al massimo essere terminata con errori, tutti i flussi senza provvisori passano IN ATTESA. Vengono rimossi i flussi in attesa dalla lista dei
                 * flussi elaborabili
                 */
                if (!CollectionUtils.isEmpty(flussiOrigineDaElaborare)) {
                    // se l'ente riceve i provvisori a servizi li richiedo a contabilia

                    if ( null != ente.getModalitaAcquisizioneProvvisori ()
                                    && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_SERVIZI == ente.getModalitaAcquisizioneProvvisori ()
                                    .getCodice () ) {
                        // inserire query di Massimo e Lorena. la query di scrematura mette i flussi in stato 11 individuati.
                        flussiOrigineDaElaborare = recuperaProvvisori (
                            configurazioneEnte.get ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI ), ente,
                            flussiOrigineDaElaborare, elaborazione );
                    }
                    

                    /* COMMIT DELLA TRANSAZIONE */

                    /* ***** PER OGNI FLUSSO SULLA CBL_T_FLUSSO_ORIGINE - INIZIO *****/
                    if ( !CollectionUtils.isEmpty ( flussiOrigineDaElaborare ) ) {
                    	flussiElaborati.addAll(flussiOrigineDaElaborare);
                    	                    	
                        for ( FlussoOrigine flussoOrigine: flussiOrigineDaElaborare ) {
                            try {

                                flussoOrigineManager.impostaStatoFlussoEdElaborazione ( flussoOrigine, elaborazione );

                                /*
                                 * 5 - Controllo sul numero dei tentativi Il numero massimo dei tentativi di elaborazione del flusso dev'essere <= a quello
                                 * definito sulla cbl_t_configurazione per l'ente oggetto di elaborazione --- ERRORE --- - l'elaborazione sar�, al massimo,
                                 * terminata con errori. Scrittura dell'errore sull'output del WS - lo stato del flusso sulla cbl_t_flusso_origine deve essere
                                 * NON ELABORABILE
                                 */
                                verificaNumeroMassimoTentativi ( flussoOrigine,
                                    configurazioneEnte.get ( Costanti.CONFIG_ATTR_MAX_NUM_TENTATIVI ) );

                                /*
                                 * 6 - Verifica riconciliabilit� PSP - il psp deve esistere --- ERRORE --- scrittura in output sul ws, elaborazione al massimo
                                 * in terminata con errori, stato flusso origine in NON ELABORABILE - il psp deve essere riconcibiliabile --- ERRORE ---
                                 * scrittura in output sul ws, elaborazione al massimo in terminata con errori, stato flusso origine in NON RICONCILIABILE
                                 */
                                verificaPsp ( flussoOrigine );

                                /*
                                 * 11
                                 * Verifico ente plurintermediato Se il nuovo flag invio pagamenti sconosciuti e' abilitato allora devo mandare il flusso anche se i
                                 * totali non tornano. Se il totale del flusso di pagopa � maggiore della somma dei pagamenti intermediati e non intermediati allora
                                 * blocco il flusso. Perch� vuol dire che il flusso comprende una plurintermediazione tecnologica. Vuol dire che abbiamo dei
                                 * pagamenti sconosciuti e quindi il flusso deve essere trattato a mano.
                                 */
                                // Rivisto da Artan e Osorio sulla base delle nuove logiche di invio dei pagamenti sconosciuti. Non piu' necessario.
                                //                                verificaInvioFlussoSconosciuto ( flussoOrigine, ente, configurazioneEnte.get ( Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI ) );

                                /*
                                 * 7 - Lettura di tutti i flussi sintesi Vengono recuperati tutti i record sulla cbl_t_flusso_sintesi relativi al
                                 * cbl_t_flusso_origine in elaborazione --- ERRORE --- Se non esistono record, l'elaborazione potr� al massimo essere terminata
                                 * con errori, flusso origine va IN ERRORE. Passaggio al flusso successivo
                                 */
                                List<FlussoSintesi> flussiSintesiDaElaborare = recuperaFlussiSintesi ( ente, flussoOrigine );

                                Provvisori provvisorio = new Provvisori ();

                                // verificare cosa succede con codice "cuneo 5"
                                if (null != ente.getModalitaAcquisizioneProvvisori()
                                                && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_NESSUNA != ente
                                                .getModalitaAcquisizioneProvvisori().getCodice()) {
                                    provvisorio = recuperaProvvisorio ( ente, flussoOrigine );
                                }

                                if ( !CollectionUtils.isEmpty ( flussiSintesiDaElaborare ) ) {
                                    for ( FlussoSintesi flussoSintesi: flussiSintesiDaElaborare ) {
                                        try {
                                            /* ***** PER OGNI RECORD SULLA CBL_T_FLUSSO_SINTESI - INIZIO *****/

                                            /* INIZIO DELLA TRANSAZIONE */
                                            // Punti 8 - 14 tutti in transazione
                                            elaboraFlussoSintesi ( provvisorio, flussoOrigine, flussoSintesi, ente );

                                            /* COMMIT DELLA TRANSAZIONE */

                                            /* ***** PER OGNI RECORD SULLA CBL_T_FLUSSO_SINTESI - FINE *****/
                                        } catch ( FlussoSintesiException flussoSintesiException ) {
                                            DTOErroreFlusso erroreFlusso = salvaErroreFlussoSintesi ( flussoSintesi,
                                                flussoSintesiException.getCodiceErrore (),
                                                flussoSintesiException.getInfoAggiuntive () );

                                            errori.add ( erroreFlusso );
                                        }
                                    }

                                    /*
                                     * 13 - Controlli sugli importi La somma di tutti gli importi dei dettagli della sintesi deve essere uguale all'importo del
                                     * provvisorio --- ERRORE --- errore sulla cbl_r_stato_flusso_errore con relativo messaggio, update della riga di sintesi
                                     * con l'id dell'errore. Elaborazione al massimo in terminata con errori, flusso origine IN ERRORE. passaggio al prossimo
                                     * flusso sintesi
                                     */
                                    if (null != ente.getModalitaAcquisizioneProvvisori()
                                                    && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_NESSUNA != ente
                                                    .getModalitaAcquisizioneProvvisori().getCodice()) {
                                        controllaImportiProvvisorio ( flussoOrigine, provvisorio );
                                    }
                                } else {
                                    // se non esistono record sulla sintesi scateno errore e mando mail
                                    String message = String.format ( "Nessun record di sintesi per il flusso %s",
                                        flussoOrigine.getIdentificativoFlusso () );
                                    LOGGER.error ( message );
                                    throw new FlussoOrigineException ( message, CostantiErrori.RECORD_SINTESI_ASSENTI,
                                        StatoFlussoEnum.IN_ERRORE, message );
                                }

                                /* INIZIO DELLA TRANSAZIONE */

                                /*
                                 * 15 - Update su flusso origine Aggiornare flusso sintesi. Portare stato in ELABORATO se nessun errore, IN_ERRORE altrimenti
                                 */
                                if ( checkElaborazioneFlussoOrigine ( flussoOrigine, errori ) ) {
                                    aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.ELABORATO );

                                    try {
                                        // RDI-20
                                        // Se la modalita' dei provvisori dell'ente e' CON (Servizi)
                                        // allora i dati vengono inviati comprensivi di provvisori (flusso rendicondato)
                                        // Se la modalita' dei provvisori dell'ente e' NES (Nessuna)
                                        // allora i dati vengono inviati senza provvisori (flusso completo)
                                        if ( null != ente.getModalitaAcquisizioneProvvisori ()
                                                        && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_SERVIZI == ente
                                                        .getModalitaAcquisizioneProvvisori ().getCodice () ) {
                                            ResponseType response = trasmissioneFlussiManager.trasmettiFlussiPagoPA (
                                                configurazioneEnte,
                                                flussoOrigine.getIdentificativoFlusso () );

                                            // se l'esito e' ok imposto lo stato del flusso in acquisito, altrimenti in
                                            // rifiutato
                                            if ( Costanti.RESPONSE_CONTABILIA_OK
                                                            .equalsIgnoreCase ( response.getResult ().getCodice () ) ) {
                                                if (Costanti.MESSAGE_SOLO_FLUSSI_SCONOSCIUTI.equals ( response.getResult ().getMessaggio () ))
                                                {
                                                    aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.ELABORATO );
                                                }
                                                else
                                                {
                                                    aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.ACQUISITO );
                                                }
                                            } else {
                                                // impostare nuova logica di log e inserimento su DB ed invio email.
                                                //                                          aggiornaStatoFlussoOrigine(flussoOrigine, StatoFlussoEnum.RIFIUTATO)
                                                try {
                                                    String message = String.format (
                                                        "Errore durante la trasmissione del flusso: %s al sistema contabile.%nDettaglio errore:%n%s",
                                                        flussoOrigine.getIdentificativoFlusso (),
                                                        response.getResult ().getCodice () + " - " + response.getResult ().getMessaggio () );
                                                    LOGGER.warn ( message );
                                                    invioMailManager.invioMail ( EmailEnum.ERRORE_ACQUISIZIONE,
                                                        CostantiErrori.ERRORE_DI_SISTEMA, ente.getIdEnte (), flussoOrigine.getIdentificativoFlusso (),
                                                        message );
                                                    salvaErroreFlussoOrigine ( flussoOrigine,
                                                        response.getResult ().getCodice (),
                                                        Arrays.asList ( response.getResult ().getMessaggio () ),
                                                        StatoFlussoEnum.RIFIUTATO );
                                                } catch ( Exception e ) {
                                                    LOGGER.error ( "ERRORE DURANTE IL SALVATAGGIO DELLO STATO DEL FLUSSO", e );
                                                    aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.RIFIUTATO );
                                                }
                                            }
                                        } else if ( null != ente.getModalitaAcquisizioneProvvisori ()
                                                        && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_NESSUNA == ente
                                                        .getModalitaAcquisizioneProvvisori ().getCodice ()
                                                        // CSI_PAG-1389: Adeguamento modric per enti senza integrazione contabile.
                                                        // Se all'interno della cbl_t_configurazione per l'ente che sto elaborando � presente il della costante
                                                        // CONFIG_ATTR_SERVICE_ENDPOINT_NESSUNA_INTEGRAZIONE_ESTERNA allora non invio il flusso completo.
                                                        // Significa che ho un ente senza sistema contabile (Vedi cuneo... almeno inizialmente sprovvisto).
                                                        && !Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_NESSUNA_INTEGRAZIONE_ESTERNA.equals ( configurazioneEnte
                                                            .get ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI ).getValore () ) ) {

                                            it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType response
                                            = trasmissioneFlussiManager.trasmettiFlussiCompletiPagoPA (
                                                configurazioneEnte,
                                                flussoOrigine.getIdentificativoFlusso () );

                                            if ( null != response ) {
                                                if ( Costanti.RESPONSE_WS_OK.equalsIgnoreCase ( response.getResult ().getCodice () ) ) {
                                                    
                                                    if (Costanti.MESSAGE_SOLO_FLUSSI_SCONOSCIUTI.equals ( response.getResult ().getMessaggio () ))
                                                    {
                                                        aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.ELABORATO );
                                                    }
                                                    else
                                                    {
                                                        aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.ACQUISITO );
                                                    }
                                                    
                                                } else {
                                                    //                                                aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.RIFIUTATO )
                                                    try {
                                                        String message = String.format (
                                                            "Errore durante la trasmissione del flusso: %s al sistema contabile.%nDettaglio errore:%n%s",
                                                            flussoOrigine.getIdentificativoFlusso (),
                                                            response.getResult ().getCodice () + " - " + response.getResult ().getMessaggio () );
                                                        LOGGER.warn ( message );
                                                        invioMailManager.invioMail ( EmailEnum.ERRORE_ACQUISIZIONE,
                                                            CostantiErrori.ERRORE_DI_SISTEMA, ente.getIdEnte (), flussoOrigine.getIdentificativoFlusso (),
                                                            message );
                                                        salvaErroreFlussoOrigine ( flussoOrigine,
                                                            response.getResult ().getCodice (),
                                                            Arrays.asList ( response.getResult ().getMessaggio () ),
                                                            StatoFlussoEnum.RIFIUTATO );
                                                    } catch ( Exception e ) {
                                                        LOGGER.error ( "ERRORE DURANTE IL SALVATAGGIO DELLO STATO DEL FLUSSO", e );
                                                        aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.RIFIUTATO );
                                                    }
                                                }
                                            } else {
                                                String message = "Errore durante la trasmissione dei flussi al sistema contabile "
                                                                + "causato dal flusso con identificativo: " + flussoOrigine.getIdentificativoFlusso ();
                                                LOGGER.error ( message );
                                                aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.IN_ERRORE );
                                                invioMailManager.invioMail ( EmailEnum.ERRORE_FLUSSO,
                                                    CostantiErrori.ERRORE_DI_SISTEMA, ente.getIdEnte (), null, message );
                                            }
                                        } else {
                                            if ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_NESSUNA_INTEGRAZIONE_ESTERNA
                                                            .equals ( configurazioneEnte.get ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI ).getValore () ) ) {
                                                LOGGER.info ( "Flusso: " + flussoOrigine.getIdentificativoFlusso ()
                                                + " non inviato al sistema contabile in quanto non previsto dalla configurazione" );
                                            }
                                            //                                      }
                                        }
                                    } catch ( Exception e ) {
                                        String message = String.format (
                                            "Errore durante la trasmissione dei flussi al sistema contabile: %s",
                                            e.getMessage () );
                                        LOGGER.error ( "Errore durante la trasmissione dei flussi al sistema contabile", e );
                                        aggiornaStatoFlussoOrigine ( flussoOrigine, StatoFlussoEnum.IN_ERRORE );
                                        // se la trasmissione fallisce -> invio mail
                                        invioMailManager.invioMail ( EmailEnum.ERRORE_FLUSSO,
                                            CostantiErrori.ERRORE_DI_SISTEMA, ente.getIdEnte (), null, message );
                                    }
                                } else {
                                    aggiornaStatoFlussoOrigine(flussoOrigine, StatoFlussoEnum.IN_ERRORE);
                                }

                                /* COMMIT DELLA TRANSAZIONE */

                                /* ***** PER OGNI FLUSSO SULLA CBL_T_FLUSSO_ORIGINE - FINE *****/
                            } catch ( FlussoOrigineException flussoOrigineException ) {
                                DTOErroreFlusso erroreFlusso = salvaErroreFlussoOrigine ( flussoOrigine,
                                    flussoOrigineException.getCodiceErrore (),
                                    flussoOrigineException.getInfoAggiuntive (),
                                    flussoOrigineException.getStatoFlusso () );
                                errori.add ( erroreFlusso );

                            }
                        }
                    }
                }

//            }

            /* INIZIO DELLA TRANSAZIONE */
              
                    /*
                     * 16 - Update su cbl_t_elaborazione Aggiornamento della CBL_T_ELABORAZIONE con
                     * stato TERMINATA ed eventuali messaggi di errore
                     */
                if (lastPage) {
                	if (!CollectionUtils.isEmpty(flussiElaborati)) {


                		aggiornaStatoElaborazione(elaborazione, StatoElaborazioneEnum.TERMINATA, flussiOrigineOriginali);
                		output.setEsito(new DTOOutputWsEsito("OK", CostantiErrori.WS_ESITO_OK_DEFAULT, "Elaborazione completata"));
                	}
                	else {
                		String message = "Non e' stato elaborato alcun flusso";
                		salvaErroreElaborazione(elaborazione, CostantiErrori.NESSUN_FLUSSO_ELABORATO,
                				StatoElaborazioneEnum.TERMINATA, message, null, flussiOrigineOriginali);
                		output.setEsito(new DTOOutputWsEsito("OK", CostantiErrori.WS_ESITO_OK_DEFAULT, message));
                	}

                } 

            // output.setDatiUtente(input.getDatiUtente());
//            if (!CollectionUtils.isEmpty(flussiOrigineDaElaborare)) {
//                /*
//                 * 16 - Update su cbl_t_elaborazione Aggiornamento della CBL_T_ELABORAZIONE con
//                 * stato TERMINATA ed eventuali messaggi di errore
//                 */
//            	if (lastPage) {
//            		
//            		
//	                aggiornaStatoElaborazione(elaborazione, StatoElaborazioneEnum.TERMINATA, flussiOrigineOriginali);
//	                output.setEsito(new DTOOutputWsEsito("OK", CostantiErrori.WS_ESITO_OK_DEFAULT, "Elaborazione completata"));
//            	}
//            } else {
//                String message = "Non e' stato elaborato alcun flusso";
//                salvaErroreElaborazione(elaborazione, CostantiErrori.NESSUN_FLUSSO_ELABORATO,
//                    StatoElaborazioneEnum.TERMINATA, message, null, flussiOrigineOriginali);
//                output.setEsito(new DTOOutputWsEsito("OK", CostantiErrori.WS_ESITO_OK_DEFAULT, message));
//            }
            /* COMMIT DELLA TRANSAZIONE */

        } catch (ElaborazioneException elaborazioneException) {
            StatoElaborazioneEnum statoElaborazione = elaborazioneException.getStatoElaborazione() != null
                            ? elaborazioneException.getStatoElaborazione()
                                            : StatoElaborazioneEnum.TERMINATA;
            salvaErroreElaborazione(elaborazione, elaborazioneException.getCodiceErrore(), statoElaborazione,
                elaborazioneException.getMessage(), elaborazioneException.getInfoAggiuntive(),
                flussiOrigineOriginali);
            // output.setDatiUtente(input.getDatiUtente());
            if (CostantiErrori.ELABORAZIONE_NON_PRESENTE.equals(elaborazioneException.getCodiceErrore())) {
                output.setEsito(new DTOOutputWsEsito("OK", CostantiErrori.WS_ESITO_OK_DEFAULT,
                                "Nessuna elaborazione disponibile per i parametri di input"));
            } else {
                output.setEsito(
                    new DTOOutputWsEsito("KO", CostantiErrori.WS_ESITO_KO_DEFAULT, "Elaborazione in errore"));
            }
        } catch (Exception e) {
            /*
             * 999 - Per ogni errore non precedentemente contemplato: cbl_t_elaborazione IN
             * ERRORE
             */

            List<String> infoAggiuntive = new ArrayList<>();
            infoAggiuntive.add(e.getMessage());
            salvaErroreElaborazione(elaborazione, CostantiErrori.ERRORE_DI_SISTEMA, StatoElaborazioneEnum.IN_ERRORE,
                null, infoAggiuntive, flussiOrigineOriginali);
            // output.setDatiUtente(input.getDatiUtente());
            output.setEsito(new DTOOutputWsEsito("KO", CostantiErrori.WS_ESITO_KO_DEFAULT, "Elaborazione in errore"));
        }
        output.setListaErrori(errori);

        if (!CollectionUtils.isEmpty(output.getListaErrori())) {
            gestisciErroriElaborazione(configurazioneEnte.get(Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI),
                output, elaborazione, ente);
        }

        LOGGER.info("RiconciliazioneManagerImpl.eseguiRieseguiElaborazione FINE");
        return output;
    }

    private void validaConfigurazioneEnte(Map<String, Configurazione> configurazioneEnte, Ente ente)
                    throws ElaborazioneException {

        if (null == configurazioneEnte) {
            throw new ElaborazioneException(
                "Impossibile recuperare i parametri di configurazione. L'elaborazione non puo' continuare",
                CostantiErrori.ERRORE_DI_SISTEMA, StatoElaborazioneEnum.IN_ERRORE);
        }

        if (null == configurazioneEnte.get(Costanti.CONFIG_ATTR_MAX_NUM_TENTATIVI)) {
            throw new ElaborazioneException(
                "Impossibile recuperare i parametri di configurazione (numero massimo tentativi). L'elaborazione non puo' continuare",
                CostantiErrori.ERRORE_DI_SISTEMA, StatoElaborazioneEnum.IN_ERRORE);
        }

        if (null == configurazioneEnte.get(Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI)
                        && null != ente.getModalitaAcquisizioneProvvisori()
                        && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_SERVIZI == ente.getModalitaAcquisizioneProvvisori()
                        .getCodice()) {
            throw new ElaborazioneException(
                "Impossibile recuperare i parametri di configurazione (endpoint invio flussi). L'elaborazione non puo' continuare",
                CostantiErrori.ERRORE_DI_SISTEMA, StatoElaborazioneEnum.IN_ERRORE);
        }

        if (null == configurazioneEnte.get(Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_RECUPERO_PROVVISORI)
                        && null != ente.getModalitaAcquisizioneProvvisori()
                        && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_SERVIZI == ente.getModalitaAcquisizioneProvvisori()
                        .getCodice()) {
            throw new ElaborazioneException(
                "Impossibile recuperare i parametri di configurazione (endpoint recupero provvisori). L'elaborazione non puo' continuare",
                CostantiErrori.ERRORE_DI_SISTEMA, StatoElaborazioneEnum.IN_ERRORE);
        }

    }

    private List<FlussoOrigine> copiaFlussiOrigine(List<FlussoOrigine> flussiOrigineDaElaborare) {
        List<FlussoOrigine> flussiOrigineOriginali = new ArrayList<>();

        if (null != flussiOrigineDaElaborare) {
            for (FlussoOrigine flussoOrigine : flussiOrigineDaElaborare) {
                flussiOrigineOriginali.add(flussoOrigine);
            }
        }

        return flussiOrigineOriginali;
    }

    private Provvisori recuperaProvvisorio(Ente ente, FlussoOrigine flussoOrigine) throws FlussoOrigineException {
        List<Provvisori> provvisori = provvisoriManager.leggi(ente.getIdEnte(),
            flussoOrigine.getIdentificativoFlusso());

        String message = "";
        if (CollectionUtils.isEmpty(provvisori)) {
            message = String.format("Nessun provvisorio presente per Ente [%s] e identificativo flusso [%s]",
                ente.getIdEnte(), flussoOrigine.getIdentificativoFlusso());
            throw new FlussoOrigineException("Provvisorio non presente", CostantiErrori.ERRORE_NO_PROVVISORI, message);
        }
        if (provvisori.size() > 1) {
            message = String.format("Presenti [%d] provvisori relativi a Ente = [%s] e identificativo flusso = [%s]",
                provvisori.size(), ente.getIdEnte(), flussoOrigine.getIdentificativoFlusso());
            throw new FlussoOrigineException("Provvisorio duplicato",
                CostantiErrori.DATI_PROVVISORI_DUPLICATO_SU_SINTESI, message);
        }
        return provvisori.get(0);
    }

    private void gestisciErroriElaborazione(Configurazione conf, DTOOutputWsMotoreDiRiconciliazione output,
        Elaborazione elaborazione, Ente ente) {
        String idEnte = null;
        String messaggio = "";
        if (null != elaborazione) {
            idEnte = elaborazione.getIdEnte();
        }
        if (CostantiErrori.WS_ESITO_KO_DEFAULT.equalsIgnoreCase(output.getEsito().getCodiceErrore())) {
            messaggio = String.format("Elaborazione relativa all'ente %s in errore. Si prega di verificare", idEnte);
        } else {

            StringBuffer flussiErrati = new StringBuffer();

            for (DTOErroreFlusso erroreFlusso : output.getListaErrori()) {
                flussiErrati.append(erroreFlusso.getIdentificativoFlusso() + "<br/>");
            }

            messaggio = String.format(
                "Elaborazione relativa all'ente %s completata con errori. Lista flussi in errore:<br/><b>%s</b>",
                idEnte, flussiErrati.toString());
        }

        // trasmissione flussi in errore
        Set<String> identificativiFlussoInErrore = new HashSet<>();
        for (DTOErroreFlusso erroreFlusso : output.getListaErrori()) {
            identificativiFlussoInErrore.add(erroreFlusso.getIdentificativoFlusso());
        }
        if (CollectionUtils.isEmpty(identificativiFlussoInErrore)
                        && null != ente.isFlagRicezioneErrori() && ente.isFlagRicezioneErrori()
                        && null != ente.getModalitaAcquisizioneProvvisori()
                        && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_SERVIZI == ente.getModalitaAcquisizioneProvvisori()
                        .getCodice()) {
            try {
                ResponseType response = trasmissioneFlussiManager.trasmettiFlussiInErrorePagoPA(conf,
                    new ArrayList<>(identificativiFlussoInErrore));
                if (null == response || null == response.getResult()
                                || !Costanti.RESPONSE_CONTABILIA_OK.equalsIgnoreCase(response.getResult().getCodice())) {
                    throw new EpaymodricException("Trasmissione flussi errati in errore");
                }
            } catch (Exception e) {
                String message = String.format(
                    "Errore durante la trasmissione dei flussi errati al sistema contabile: %s", e.getMessage());
                LOGGER.error("Errore durante la trasmissione dei flussi errati al sistema contabile", e);
                // se la trasmissione fallisce -> invio mail
                invioMailManager.invioMail(EmailEnum.ERRORE_FLUSSO, CostantiErrori.ERRORE_DI_SISTEMA,
                    elaborazione.getIdEnte(), null, message);
            }

        }

        invioMailManager.invioMail(EmailEnum.ERRORE_BUSINESS, CostantiErrori.ELABORAZIONE_TERMINATA_IN_ERRORE, idEnte,
            messaggio, null);
    }

    @Override
    public void controllaValiditaParametriInput(DTOInputWsMotoreDiRiconciliazione input) throws InputNotValidException {

        boolean valid = true;
        if (null == input) {
            valid = false;
        }
        if (valid && StringUtils.isEmpty(input.getCodiceEnte())) {
            valid = false;
        }
        if (valid && StringUtils.isEmpty(input.getIsRiesecuzione())) {
            valid = false;
        }
        if (!valid) {
            throw new InputNotValidException("Parametri di input non validi");
        }

    }

    @Override
    public Ente recuperaEnte(String identificativoEnte) throws ElaborazioneException {
        return enteManager.leggiPerIdEnte(identificativoEnte);
    }

    @Override
    public List<FlussoOrigine> inizializzaFlussiOrigineDaElaborare(Ente ente, Elaborazione elaborazione,
        List<String> flussiDaElaborare, int pageNumber, int pageSize, List<String> statiDaEscludere ) throws ElaborazioneException {
        return flussoOrigineManager.recuperaFlussiDaElaborare ( ente.getCodiceFiscale (), flussiDaElaborare, pageNumber, pageSize, statiDaEscludere, elaborazione.getId() );
    }

    @Override
    public void verificaRiconciliabilitaEnte(Ente ente, List<FlussoOrigine> flussiOrigineDaElaborare,
        List<DTOErroreFlusso> errori) throws ElaborazioneException {

        if (!ente.isFlagRiconciliazione()) {

            // se un ente non e' riconciliabile, prima di uscire imposto a NON
            // RICONCILIABILE tutte le occorrenze della cbl_t_flusso_origine coinvolte
            if (flussiOrigineDaElaborare != null) {
                for (FlussoOrigine flussoOrigine : flussiOrigineDaElaborare) {
                    try {
                        aggiornaStatoFlussoOrigine(flussoOrigine, StatoFlussoEnum.NON_RICONCILIABILE);
                    } catch (FlussoOrigineException e) {
                        LOGGER.error(String.format("Errore durante l'impostazione a non riconciliabile del flusso %s",
                            flussoOrigine.getIdentificativoFlusso()), e);

                    }
                }
            }

            String message = String.format("Ente %s non riconciliabile", ente.getCodiceFiscale());

            DTOErroreFlusso errore = new DTOErroreFlusso();
            errore.setCodiceErrore(CostantiErrori.ENTE_NON_RICONCILIABILE);
            errore.setDescrizioneAggiuntivaErrore(message);
            errore.setDescrizioneErrore(message);
            errori.add(errore);
            List<String> descrizioneAggiuntiva = new ArrayList<>();
            descrizioneAggiuntiva.add(message);
            salvaStatoFlussoErrore(null, errore.getCodiceErrore(), descrizioneAggiuntiva);
            throw new ElaborazioneException(message, CostantiErrori.ENTE_NON_RICONCILIABILE, message);
        }

    }

    @Override
    @Transactional
    public List<FlussoOrigine> recuperaFlussiOrigineDaElaborareEVerificaRiconciliabilita(Ente ente,
        Elaborazione elaborazione, List<String> flussiDaElaborare, List<DTOErroreFlusso> errori, int pageNumber, int pageSize, List<String> statiDaEscludere )
                        throws ElaborazioneException {

        List<FlussoOrigine> flussiOrigine = inizializzaFlussiOrigineDaElaborare ( ente, elaborazione, flussiDaElaborare, pageNumber, pageSize, statiDaEscludere );

        verificaRiconciliabilitaEnte(ente, flussiOrigine, errori);

        return flussiOrigine;
    }

    @Override
    public List<FlussoOrigine> recuperaProvvisori(Configurazione conf, Ente ente,
        List<FlussoOrigine> flussiOrigineDaElaborare, Elaborazione elaborazione) throws ElaborazioneException {

        LOGGER.info(String.format("Ente %s con modalit� acquisizione provvisori a servizi.", ente.getCodiceFiscale()));

        List<String> causaliVersamento = ProvvisoriUtility.creaListaCausali(flussiOrigineDaElaborare);

        List<Provvisori> provvisoriAcquisiti = provvisoriManager.richiediProvvisori(conf, ente.getCodiceFiscale(),
            causaliVersamento, null, null, null, null);

        if (!CollectionUtils.isEmpty(provvisoriAcquisiti)) {

            List<FlussoOrigine> flussiConProvvisori = new ArrayList<>();

            for ( FlussoOrigine flussoOrigine: flussiOrigineDaElaborare ) {
                for ( Provvisori provvisorio: provvisoriAcquisiti ) {
                    //CSI_PAG-306
                    //if ( null != provvisorio && provvisorio.getIdentificativoFlusso ().equalsIgnoreCase ( flussoOrigine.getIdentificativoFlusso () ) ) {
                    if ( null != provvisorio && provvisorio.getIdentificativoFlusso ().contains ( flussoOrigine.getIdentificativoFlusso () ) ) {
                        flussiConProvvisori.add ( flussoOrigine );
                        break;
                    }
                }
            }

            flussiOrigineDaElaborare.removeAll(flussiConProvvisori);

            // tutti i flussi senza provvisori sono da marcare "in attesa"
            LOGGER.warn("tutti i flussi senza provvisori sono da marcare \"in attesa\"");
            for (FlussoOrigine flussoOrigine : flussiOrigineDaElaborare) {
            	StatoFlusso statoFlussoOdl= flussoOrigine.getStatoFlusso();
            	flussoOrigineManager.impostaStatoFlussoEdElaborazione(flussoOrigine, elaborazione);

            	if (statoFlussoOdl!= null && 
            			StatoFlussoEnum.IN_ATTESA.getCodice().equals(flussoOrigine.getStatoFlusso().getCodiceStatoFlusso()))
            	{
            		flussoOrigineManager.cambiaStatoFlusso(flussoOrigine, StatoFlussoEnum.IN_ATTESA);
            	}
            	else
            	{

            		try {
            			aggiornaStatoFlussoOrigine(flussoOrigine, StatoFlussoEnum.IN_ATTESA);
            		} catch (FlussoOrigineException e) {
            			LOGGER.error(String.format("Errore durante l'impostazione a non riconciliabile del flusso %s",
            					flussoOrigine.getIdentificativoFlusso()), e);

            		}

            	}


            	LOGGER.warn(String.format("Flusso %s in attesa", flussoOrigine.getIdentificativoFlusso()));
            }

            flussiOrigineDaElaborare = flussiConProvvisori;

        } else {
            LOGGER.warn("Nessun provvisorio acquisito, tutti i flussi origine vanno in attesa");
            for (FlussoOrigine flussoOrigine : flussiOrigineDaElaborare) {
            	StatoFlusso statoFlussoOdl= flussoOrigine.getStatoFlusso();
            	
                flussoOrigineManager.impostaStatoFlussoEdElaborazione(flussoOrigine, elaborazione);
            	if (statoFlussoOdl!= null && 
            			StatoFlussoEnum.IN_ATTESA.getCodice().equals(flussoOrigine.getStatoFlusso().getCodiceStatoFlusso()))
            	{
            		flussoOrigineManager.cambiaStatoFlusso(flussoOrigine, StatoFlussoEnum.IN_ATTESA);
            	}
            	else
            	{

            		try {
            			aggiornaStatoFlussoOrigine(flussoOrigine, StatoFlussoEnum.IN_ATTESA);
            		} catch (FlussoOrigineException e) {
            			LOGGER.error(String.format("Errore durante l'impostazione a non riconciliabile del flusso %s",
            					flussoOrigine.getIdentificativoFlusso()), e);

            		}

            	}
                LOGGER.warn(String.format("Flusso %s in attesa", flussoOrigine.getIdentificativoFlusso()));
            }
            flussiOrigineDaElaborare = new ArrayList<>();
        }

        return flussiOrigineDaElaborare;
    }

    @Override
    public Configurazione recuperaConfigurazione(String identificativoEnte) {
        return configurazioneManager.leggi(identificativoEnte, Costanti.CONFIG_ATTR_MAX_NUM_TENTATIVI);
    }

    @Override
    public void verificaNumeroMassimoTentativi(FlussoOrigine flussoOrigine, Configurazione configurazione)
                    throws FlussoOrigineException {

        LOGGER.debug(String.format("Verifica se per il flusso con identificativo [%s] e' stato superato il numero massimo di tentativi", flussoOrigine.getIdentificativoFlusso()));

        try {
            if (flussoOrigine.getContatoreTentativi() >= Integer.valueOf(configurazione.getValore())) {
                String message = String.format("Numero massimo tentativi superato. Massimo = [%d], Attuale = [%d]",
                    Integer.valueOf(configurazione.getValore()), flussoOrigine.getContatoreTentativi());
                throw new FlussoOrigineException("Numero massimo tentativi superato", CostantiErrori.NUM_MAX_TENTATIVI,
                    StatoFlussoEnum.NON_ELABORABILE, message);
            }
        } catch (NumberFormatException nfe) {
            String message = String.format("Errore durante la lettura del valore del parametro %s",
                Costanti.CONFIG_ATTR_MAX_NUM_TENTATIVI);
            LOGGER.error(message, nfe);
            throw new FlussoOrigineException(message, CostantiErrori.ERRORE_DI_SISTEMA, StatoFlussoEnum.IN_ERRORE,
                message);
        }

    }

    /**
     * Metodo che verifica 
     * @param flussoOrigine
     * @param ente
     * @param configurazioneEnte
     * @throws FlussoOrigineException 
     */
    private void verificaInvioFlussoSconosciuto ( FlussoOrigine flussoOrigine, Ente ente, Configurazione confInvioPagSco ) throws FlussoOrigineException {
        if ( !Boolean.TRUE.equals ( Boolean.valueOf ( confInvioPagSco.getValore () ) )
            && !ente.isEntePlurintermediato () && flussoOrigine.getNumeroTotalePagamentiSconosciuti () > 0 ) {
            String message = String.format ( "Errore durante controllo plurintermedialita' ente [%s] e flusso [%s]",
                ente.getCodiceFiscale (), flussoOrigine.getIdentificativoFlusso () );
            throw new FlussoOrigineException ( message, CostantiErrori.ENTE_NO_PLURIMANDATARIO, message );
        }
    }
    
    @Override
    public void verificaPsp(FlussoOrigine flussoOrigine) throws FlussoOrigineException {

        LOGGER.debug(String.format("Verifica del psp per il flusso con identificativo [%s]", flussoOrigine.getIdentificativoFlusso()));

        Psp psp = pspManager.leggiPerIdentificativoPsp(flussoOrigine.getIdentificativoPsp());
        String message = "";
        if (null == psp) {
            message = String.format("PSP [%s] non esistente", flussoOrigine.getIdentificativoPsp());
            throw new FlussoOrigineException(message, CostantiErrori.PSP_NON_VALORIZZATO,
                StatoFlussoEnum.NON_ELABORABILE, message);
        }

        if (null == psp.isFlagRiconciliabile() || !psp.isFlagRiconciliabile()) {
            message = String.format("PSP [%s] non riconciliabile", flussoOrigine.getIdentificativoPsp());
            throw new FlussoOrigineException(message, CostantiErrori.PSP_NON_RICONCILIABILE,
                StatoFlussoEnum.NON_RICONCILIABILE, message);
        }

    }

    @Override
    public List<FlussoSintesi> recuperaFlussiSintesi(Ente ente, FlussoOrigine flussoOrigine)
                    throws FlussoOrigineException {
        LOGGER.debug(String.format("Recupera flussi sintesi da flusso con identificativo flusso [%s]", flussoOrigine.getIdentificativoFlusso()));

        List<FlussoSintesi> listaFlussiSintesi = flussoManager.recuperaFlussiSintesiDaOrigine(flussoOrigine);

        if (CollectionUtils.isEmpty(listaFlussiSintesi)
                        && StatoFlussoEnum.IN_ATTESA.toString().equals(flussoOrigine.getStatoFlusso().getCodiceStatoFlusso())) {
            DTOInputWsSpacchettamentoFlussoRendicontazione input = new DTOInputWsSpacchettamentoFlussoRendicontazione();
            input.setCodiceFiscaleEnte(ente.getCodiceFiscale());
            input.setIdFlussoOrigine(flussoOrigine.getIdentificativoFlusso());
            try {
                flussoRendicontazioneManager
                .spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso(input);
            } catch (Exception e) {
                String message = "Errore durante lo spacchettamento";
                LOGGER.error(message, e);
                throw new FlussoOrigineException(e.getMessage(), CostantiErrori.ERRORE_SPACCHETTAMENTO, message);
            }
            listaFlussiSintesi = flussoManager.recuperaFlussiSintesiDaOrigine(flussoOrigine);
        }

        return listaFlussiSintesi;

    }

    @Override
    public void verificaCatalogoEAggiornaFlussoSintesi(Provvisori provvisorio, Ente ente, FlussoOrigine flussoOrigine, FlussoSintesi flussoSintesi)
                    throws  RiconciliazioneException {

        flussoSintesi.setProvvisorioAnno(provvisorio.getAnnoProvvisorio());
        flussoSintesi.setProvvisorioNumero(provvisorio.getNumeroProvvisorio());

        // RDI-20
        // Da ciascun flusso di sintesi, seleziono i dettagli per poter valutare se il pagamento � in stato 9 (assenza RPT) o meno.

        List<FlussoDettaglio> flussiDettagli = flussoDettaglioManager.cercaDettagliDaSintesi(flussoSintesi.getId());

        LOGGER.info("Prima della modifica");
        boolean isStatoNove = controlloPagamentoStatoNove(flussiDettagli, ente, flussoSintesi);
        LOGGER.info("isStatoNove: " + isStatoNove );
        if(isStatoNove) {
            // RDI-20:
            // Il pagamento � nello stato 9, quindi controllo se appartiene al mondo PiemontePay o meno,
            // valutando il flag nella cbl_t_codici_versamento_config.
            // Se appartiene al mondo PiemontePay chiedo i dati a sportello
            // Se non appartiene al mondo PiemontePay cerco i dati in cbl_t_catalogo di modric
            Boolean cercaSuPagamenti = codiciVersamentoConfigManager.leggiCodiciVersamentoConfig(ente.getIdEnte(), flussoSintesi.getCodiceVersamento());
            LOGGER.info("cercaSuPagamenti: " + cercaSuPagamenti);
            for(FlussoDettaglio flussoDettaglio: flussiDettagli){
                if (cercaSuPagamenti) {
                    aggiornaDatiConDatiDaSportello ( ente,  flussoOrigine,  flussoSintesi, flussoDettaglio, isStatoNove);
                } else {
                    recuperaRiferimentoContabileAggiornaFlussoSintesiAggiornaDettaglio(ente, flussoDettaglio, flussoSintesi, isStatoNove);
                }
            }

        } else{
            /*
             * 14 - Verifica lista di carico Accedere alla cbl_v_dati_accertamento per ente,
             * codice versamento, dati specifici di riscossione e anno esercizio. deve
             * esistere record --- ERRORE --- errore sulla cbl_r_stato_flusso_errore con
             * relativo messaggio, update della riga di sintesi con l'id dell'errore.
             * Elaborazione al massimo in terminata con errori, flusso origine IN ERRORE.
             * passaggio al prossimo flusso sintesi
             */
            if (null != ente.getModalitaAcquisizioneProvvisori()
                            && Costanti.MODALITA_ACQUISIZIONE_PROVVISORI_NESSUNA != ente.getModalitaAcquisizioneProvvisori().getCodice()) {

                verificaCatalogoDiCaricoEAggiornaFlussoSintesi(ente, provvisorio.getAnnoEsercizio(), flussoSintesi, isStatoNove);
            } else {
                Calendar annoDataPagamentoCalcolata = Calendar.getInstance ();
                if ( null != flussoSintesi.getDataPagamentoCalcolata () ) {
                    annoDataPagamentoCalcolata.setTime ( flussoSintesi.getDataPagamentoCalcolata () );
                }
                verificaCatalogoDiCaricoEAggiornaFlussoSintesi ( ente, annoDataPagamentoCalcolata.get ( Calendar.YEAR ), flussoSintesi, isStatoNove );
            }
        }
    }

    /**
     * Metodo per recuperare i dati da sportello. Se sportello restituisce delle informazioni relative al pagamento, si aggiorna
     * il flusso di sintesi e il flusso di dettaglio. Se non restituisce dati si cercano informazioni su catalogo di modric
     * @param ente
     * @param flussoOrigine e' il flusso di origine del pagamento che ha le informazioni da utilizzare su sportello per ottenere il pagamento
     * @param flussoSintesi e' il flusso di sintesi del pagamento che ha le informazioni da utilizzare su sportello per ottenere il pagamento e che deve poi essere aggiornato
     * @param flussoDettaglio e' il flusso di dettaglio del pagamento he ha le informazioni da utilizzare su sportello per ottenere il pagamento e che deve poi essere aggiornato
     * @param isStatoNove indica se il pagamento � in stato 9 o meno
     * @throws RiconciliazioneException restituisce errore nel caso in cui c'� piu' di un riferimento contabile o non c'� riferimento contabile
     */
    private void aggiornaDatiConDatiDaSportello (Ente ente, FlussoOrigine flussoOrigine, FlussoSintesi flussoSintesi,FlussoDettaglio flussoDettaglio, boolean isStatoNove) throws RiconciliazioneException {

        LOGGER.info("Chiamata a sportello per ottere i dati del pagamento");

        String authorization = "Basic " + ResourceBundle.getBundle("config").getString (Costanti.CONFIG_HEADER_SERVICE_ENDPOINT_EPAYBEAPI );

        ComponentiImportoOutput datiDaSportello = chiamateSportello.getComponentiPagamento(
            authorization,
            null != flussoOrigine ? flussoOrigine.getIdentificativoIstitutoRicevente() : null,
                            null !=flussoSintesi ? flussoSintesi.getCodiceVersamento(): null,
                                            null != flussoDettaglio ? flussoDettaglio.getIdentificativoUnicoVersamento() : null,
                                                            null != flussoDettaglio ? flussoDettaglio.getImportoSingoloVersamento(): null,
                                                                            null != flussoDettaglio ? flussoDettaglio.getIdTransaction(): null,
                                                                                            null != flussoDettaglio ? flussoDettaglio.getDatiSpecificiDiRiscossione(): null,
                                                                                                            null != flussoDettaglio ? flussoDettaglio.getEsitoPagamento(): null,
                                                                                                                            null != flussoDettaglio ? flussoDettaglio.getAnagraficaPagatore(): null,
                                                                                                                                            null != flussoDettaglio ? flussoDettaglio.getCodiceFiscalePagatore(): null);

        if(null != datiDaSportello.getCodiceEsito() && CostantiErrori.RETURN_CODE_OK.equals(datiDaSportello.getCodiceEsito())) {
            // se sportello restuisce dei dati, si utilizzaono questi dati, altrimenti si prendono da catalogo
            if(CollectionUtils.isEmpty(datiDaSportello.getComponentiImportoList())) {
                recuperaRiferimentoContabileAggiornaFlussoSintesiAggiornaDettaglio(ente, flussoDettaglio, flussoSintesi, isStatoNove);
            } else {
                aggiornaSintesiAggiornaDettaglioConDatiDaSportello(flussoSintesi, flussoDettaglio, datiDaSportello.getComponentiImportoList() );
            }
        } else {
            throw new RiconciliazioneException(datiDaSportello.getDescrizioneEsito());

        }
    }
    @Override
    public void controllaCodiciEsclusione(Ente ente, FlussoSintesi flussoSintesi) throws FlussoSintesiException {
        List<String> codiciDaEscludere = codiciVersamentoDaEscudereManager
                        .leggiCodiciVersamentoDaEscudere(ente.getIdEnte());
        if (!StringUtils.isEmpty(flussoSintesi.getCodiceVersamento())
                        && !StringUtils.isEmpty(flussoSintesi.getDatiSpecificiDiRiscossione())
                        && codiciDaEscludere.contains(flussoSintesi.getCodiceVersamento())) {
            String message = String.format("Codice versamento %s presente tra quelli da escludere",
                flussoSintesi.getCodiceVersamento());
            throw new FlussoSintesiException(message, CostantiErrori.NO_CODICE_VERSAMENTO, message);
        }

    }

    @Override
    public void controllaDatiSpecificiRiscossione(FlussoSintesi flussoSintesi, Ente ente)
                    throws FlussoSintesiException {
        if ((StringUtils.isEmpty(flussoSintesi.getCodiceVersamento())
                        || StringUtils.isEmpty(flussoSintesi.getDatiSpecificiDiRiscossione()))
                        && (!ente.isEntePlurintermediato())) {
            String message = String.format(
                "Errore durante controllo dati specifici riscossione per l'ente [%s] e flusso [%s]",
                ente.getCodiceFiscale(), flussoSintesi.getIdentificativoFlusso());
            throw new FlussoSintesiException(message, CostantiErrori.NO_DATI_RISCOSSIONE, message);
        }

    }

    @Override
    public void controllaImportiProvvisorio(FlussoOrigine flussoOrigine, Provvisori provvisorio)
                    throws FlussoOrigineException {

        LOGGER.debug("Controllo importi provvisorio");
        // 1 - se importo totale flusso (flusso origine) <>
        // importo_totale_pagamenti_intermediati
        // 2 - mi ricavo l'importo delle sintesi
        // 3 - se importo sintesi e' diverso da importo provvisorio allora errore
        if (flussoOrigine.getImportoTotalePagamenti() != null
                        && flussoOrigine.getImportoTotalePagamentiIntermediati() != null
                        && flussoOrigine.getImportoTotalePagamenti()
                        .compareTo(flussoOrigine.getImportoTotalePagamentiIntermediati()) != 0) {
            BigDecimal importoTotaleSintesi = flussoManager.getImportoTotaleSintesi(flussoOrigine);

            if (!provvisorio.getImportoProvvisorio().equals(importoTotaleSintesi)) {
                String message = String.format(
                    "Per l'identificativo flusso %s l'importo provvisorio: %.3f e l'importo sintesi: %.3f non sono congruenti",
                    flussoOrigine.getIdentificativoFlusso(), provvisorio.getImportoProvvisorio(),
                    importoTotaleSintesi);
                throw new FlussoOrigineException(message, CostantiErrori.NON_RICONCILIABILE_IMPORTI, message);
            }
        } else {
            BigDecimal importoDettagli = flussoManager.getImportoTotaleDettagli(flussoOrigine);
            // 4 - else
            if (null == importoDettagli || !importoDettagli.equals(provvisorio.getImportoProvvisorio())) {
                String message = String.format("Importo provvisorio: %.3f e importo dettagli: %.3f non congruenti",
                    provvisorio.getImportoProvvisorio(), importoDettagli);
                throw new FlussoOrigineException(message, CostantiErrori.NON_RICONCILIABILE_IMPORTI, message);
            }
        }

    }

    private void verificaCatalogoDiCaricoEAggiornaFlussoSintesi(Ente ente, Integer anno, FlussoSintesi flussoSintesi, boolean isStatoNove)
                    throws FlussoSintesiException {

        Catalogo catalogo = catalogoManager.recuperaDatiCatalogo(ente.getIdEnte(), flussoSintesi.getCodiceVersamento(),
            flussoSintesi.getDatiSpecificiDiRiscossione(), anno,
            flussoSintesi.getDataPagamentoCalcolata());

        String message = "";
        String datiVistaInput = estraiDatiVista(flussoSintesi.getCodiceVersamento(),
            flussoSintesi.getDatiSpecificiDiRiscossione(), anno);

        if (null == catalogo) {
            message = String.format("Dati accertamento [%s] non presenti per il flusso sintesi %s", datiVistaInput,
                flussoSintesi.getIdentificativoFlusso());
            if (ente.isFlagAccertamento()) {
                throw new FlussoSintesiException(message, CostantiErrori.NO_DATI_ACCERTAMENTO, message);
            } else {
                LOGGER.warn(message);
            }
        }else {
            aggiornaFlussoSintesi (catalogo, flussoSintesi, isStatoNove);
        }
    }

    /**
     * RDI-20:
     * Nel caso di pagamenti in stato 9, per i quali non e' possibile recuperare le informazioni dallo sportello
     * (o perch� la tabella di configurazione non lo consente o perche' sportello non ha dati relativi al pagamento), e' necessario
     * reperire le informazioni del pagamento da modric.
     * Se per tale pagamento, modric ha solo un riferimento contabile viene aggiornata la sintesi e il dettaglio del pagamento
     * con le informazioni contenute nel riferimento contabile, altrimenti viene restituito errore
     * @param ente
     * @param anno e' l'anno della data_pagamento_calcolata, utilizzato per la ricerca per anno_esercizio in catalogo
     * @param flussiDettagli e' la lista con i flussi di dettagli che devono essere aggiornati con i nuovi dati
     * @param flussoSintesi e' il flusso di sintesi che deve essere aggiornato
     * @param isStatoNove indica lo stato del pagamento
     * @throws RiconciliazioneException eccezione nel caso in cui non ci siano riferimenti contabili o ce ne siano piu' di uno
     */
    private void recuperaRiferimentoContabileAggiornaFlussoSintesiAggiornaDettaglio(Ente ente, FlussoDettaglio flussoDettaglio, FlussoSintesi flussoSintesi, boolean isStatoNove) throws RiconciliazioneException {

        LOGGER.info("Recupero il riferimento per l'anno corrente attivo");

        Calendar annoDataPagamentoCalcolata = Calendar.getInstance();
        annoDataPagamentoCalcolata.setTime(flussoSintesi.getDataPagamentoCalcolata()  );
        Catalogo catalogo = catalogoManager.recuperaRiferimentoContabilePerAnnoCorrenteAttivo ( ente.getIdEnte (), flussoSintesi.getCodiceVersamento (),
            annoDataPagamentoCalcolata.get ( Calendar.YEAR ), flussoSintesi.getDataPagamentoCalcolata (), flussoSintesi.getDatiSpecificiDiRiscossione (),
            flussoSintesi.getDataPagamentoCalcolata () );

        String message = "";
        String datiVistaInput = estraiDatiVista(flussoSintesi.getCodiceVersamento(),
            flussoSintesi.getDatiSpecificiDiRiscossione(), annoDataPagamentoCalcolata.get(Calendar.YEAR));

        if (null == catalogo) {
            message = String.format("Dati accertamento [%s] non presenti per il flusso sintesi %s", datiVistaInput,
                flussoSintesi.getIdentificativoFlusso());
            if (ente.isFlagAccertamento()) {
                throw new FlussoSintesiException(message, CostantiErrori.NO_DATI_ACCERTAMENTO, message);
            } else {
                LOGGER.warn(message);
            }
        }
        else {
            aggiornaFlussoSintesi ( catalogo,  flussoSintesi, isStatoNove);

            aggiornaFlussoDettaglio(catalogo.getDatiSpecificiRiscossione(), flussoDettaglio);
        }
    }

    /**
     * RDI-20:
     * Metodo che controlla se un pagamento � in stato 9 o meno.
     * Un pagamento in stato 9 dovrebbe avere un solo flusso di dettaglio, con valori di
     * dati_specifici_riscossione, transaction_id e esito_pagamento valorizzati opportunamente, cos� da poterlo identificare.
     * Nel caso in cui siano presenti piu' flussi di dettaglio per lo stesso pagamento, la tripletta
     * (dati_specifici_riscossione, transaction_id e esito_pagamento) deve essere identica, altrimenti deve restituire errore.
     *
     * @param flussoDettagli, una lista con tutti i flussi di dettaglio del pagamento
     * @param ente
     * @param flussoSintesi, e' il flusso di sintesi del pagamento
     * @return true se il pagamento e' in stato 9, false se il pagamento non e' in stato 9
     * @throws RiconciliazioneException restituisce un eccezione nel caso in cui un pagamento che dovrebbe essere in stato 9, ha dettagli con la tripletta diversa
     */
    private boolean controlloPagamentoStatoNove(List<FlussoDettaglio> flussoDettagli, Ente ente, FlussoSintesi flussoSintesi) throws RiconciliazioneException {

        LOGGER.info("Controllo se il pagamento e' nello stato 9 (assenza RPT)");
        boolean isStatoNove = false;

        for (int i = 0; i<flussoDettagli.size(); i++) {
            if (null != flussoDettagli.get(i) && flussoDettagli.get(i).getDatiSpecificiDiRiscossione().equalsIgnoreCase(Costanti.DATI_SPECIFICI_RISCOSSIONE_STATO_9)
                            && flussoDettagli.get(i).getIdTransaction().equalsIgnoreCase(Costanti.TRANSACTION_ID_STATO_9)
                            && flussoDettagli.get(i).getEsitoPagamento().equalsIgnoreCase(Costanti.ESITO_PAGAMENTO_9)) {

                if(i>0 && !isStatoNove) {
                    String message = String.format(
                        "Errore durante controllo dati specifici riscossione e transaction id per l'ente [%s] e flusso [%s]",
                        ente.getCodiceFiscale(), flussoSintesi.getIdentificativoFlusso());
                    LOGGER.info(message);
                    throw new RiconciliazioneException(message);
                }
                isStatoNove = true;
            }
        }
        LOGGER.info("Fine controllo se il pagamento e' nello stato 9 (assenza RPT)");
        return isStatoNove;
    }

    /**
     * Metodo che aggiorna le informazioni del flusso di dettaglio nel caso di pagamento in stato 9, aggiornando il dati_specifici_riscossione
     * @param datiSpecificiRiscossione e' il nuovo valore, ottenuto da sportello o da modric, che il flusso di dettaglio deve avere
     * @param flussiDettagli e' la lista dei flussi di dettaglio del pagamento in stato 9, a cui bisogna aggiornare i dati_specifici_riscossione
     */
    private void aggiornaFlussoDettaglio (String datiSpecificiRiscossione, FlussoDettaglio flussoDettaglio) {

        LOGGER.info("Aggiorno il flusso di dettaglio");

        flussoDettaglio.setDatiSpecificiDiRiscossione(datiSpecificiRiscossione);
        flussoDettaglioManager.aggiornaDettaglio(flussoDettaglio);
    }

    /**
     * RDI-20:
     * Sia nel caso di pagamenti nello stato 9 che per quelli che non lo sono, e' necessario aggiornare il
     * flusso di sintesi del pagamento. I dati che devono essere aggiornati pero' cambiano a seconda dello stato del pagamento.
     * Nel caso di pagamento in stato 9, devono essere aggiornati dati_specifici_riscossione, accertamento_anno e accertamento_numero.
     * Nel caso di pagamento non in stato 9, devono essere aggiornati capitolo, articolo, piano_dei_conti, descrizione_versamento, accertamento_anno e accertamento_numero
     * @param catalogo contiene le informazioni che devono aggiornare il flusso sintesi, nel caso di pagamento in stato non 9
     * @param flussoSintesi e' il flusso di sintesi che deve essere aggiornato
     * @param isStatoNove indica se il flusso � in stato 9 o meno
     */
    private void aggiornaFlussoSintesi (Catalogo catalogo, FlussoSintesi flussoSintesi, boolean isStatoNove) {

        LOGGER.info("Aggiorno il flusso di sintesi");

        if(isStatoNove){
            flussoSintesi.setDatiSpecificiDiRiscossione(catalogo.getDatiSpecificiRiscossione());
            flussoSintesi.setAccertamentoAnno(catalogo.getAccertamentoAnno());
            flussoSintesi.setAccertamentoNumero(catalogo.getAccertamentoNro());
        } else {
            flussoSintesi.setDescrizioneCodiceVersamento(catalogo.getDescrizioneVersamento());
            if (flussoSintesi.getAccertamentoAnno() == null) {
                flussoSintesi.setAccertamentoAnno(catalogo.getAccertamentoAnno());
            }
            if (flussoSintesi.getAccertamentoNumero() == null) {
                flussoSintesi.setAccertamentoNumero(catalogo.getAccertamentoNro());
            }
        }

        flussoSintesi.setCapitolo ( catalogo.getCapitolo () != null ? catalogo.getCapitolo () : null );
        flussoSintesi.setArticolo(catalogo.getArticolo() != null ? catalogo.getArticolo().intValue() : null);
        flussoSintesi.setPianoDeiConti(catalogo.getPianoDeiConti());

        flussoManager.aggiornaDatiProvvisorio(flussoSintesi);
    }

    /**
     * RDI-20: Metodo per aggiornare ed inserire il flusso di sintesi e il flusso di dettaglio, con i dati ottenuti da sportello.
     * Siccome da sportello possono giungere piu' dati relativi al pagamento, significa che un dato va ad aggiornare il flusso sintesi
     * e il flusso dettaglio attualmente presente, mentre gli altri vanno ad inserire nuovi flussi
     * a partire da quelli gia' esistenti ma contenenti le nuove informazioni ottenute da sportello
     * @param flussoSintesi e' il flusso di sintesi che deve essere aggiornato
     * @param flussoDettaglio e' il flusso di dettaglio che deve essere aggiornato
     * @param datiDaSportello sono i dati ottenuti da sportello
     */
    private void aggiornaSintesiAggiornaDettaglioConDatiDaSportello(FlussoSintesi flussoSintesi, FlussoDettaglio flussoDettaglio, List<SingoloComponenteImportoOutput> datiDaSportello ) {

        for(int i=0; i<datiDaSportello.size(); i++) {
            if(i==0) {
                LOGGER.info("Aggiorno il flusso di sintesi e flusso di dettaglio");
                flussoSintesi.setDatiSpecificiDiRiscossione(datiDaSportello.get(i).getDatiSpecificiRiscossione());
                flussoSintesi.setAccertamentoAnno(datiDaSportello.get(i).getAnnoAccertamento());
                flussoSintesi.setAccertamentoNumero(datiDaSportello.get(i).getNumeroAccertamento());
                flussoSintesi.setImportoQuotaAggregazione(new BigDecimal(datiDaSportello.get(i).getImportoSingoloVersamento()));
                flussoManager.aggiornaDatiProvvisorio(flussoSintesi);

                flussoDettaglio.setDatiSpecificiDiRiscossione(datiDaSportello.get(i).getDatiSpecificiRiscossione());
                flussoDettaglioManager.aggiornaDettaglio(flussoDettaglio);
            } else {

                LOGGER.info("Inserisco nuovo flusso di sintesi e nuovo flusso di dettaglio");
                flussoSintesi.setDatiSpecificiDiRiscossione(datiDaSportello.get(i).getDatiSpecificiRiscossione());
                flussoSintesi.setAccertamentoAnno(datiDaSportello.get(i).getAnnoAccertamento());
                flussoSintesi.setAccertamentoNumero(datiDaSportello.get(i).getNumeroAccertamento());
                flussoSintesi.setImportoQuotaAggregazione(new BigDecimal(datiDaSportello.get(i).getImportoSingoloVersamento()));
                FlussoSintesi newFlussoSintesi = flussoManager.inserisciSintesiDaSintesi(flussoSintesi);

                if(newFlussoSintesi!=null) {
                    flussoDettaglio.setFlussoSintesi(newFlussoSintesi);
                    flussoDettaglio.setDatiSpecificiDiRiscossione(datiDaSportello.get(i).getDatiSpecificiRiscossione());
                    flussoDettaglio.setImportoSingoloVersamento(new BigDecimal(datiDaSportello.get(i).getImportoSingoloVersamento()));
                    flussoDettaglioManager.inserisciDettaglioDaDettaglio(flussoDettaglio);
                } else {

                }
            }
        }
    }

    //	private void annullaDatiListaDiCarico(VistaDatiAccertamento vistaDatiAccertamento) {
    //
    //		if (vistaDatiAccertamento.getIdListaDiCarico() != null) {
    //			listaDiCaricoManager.invalidaListaDiCarico(vistaDatiAccertamento.getIdListaDiCarico());
    //		} else {
    //			LOGGER.warn(String.format(
    //					"Impossibile annullare la lista di carico per il seguente record: Accertamento anno %s, Accertamento numero %s, Codice versamento %s, Ente %s",
    //					vistaDatiAccertamento.getAccertamentoAnno(), vistaDatiAccertamento.getAccertamentoNro(),
    //					vistaDatiAccertamento.getCodiceVersamento(), vistaDatiAccertamento.getIdEnte()));
    //		}
    //
    //	}

    private String estraiDatiVista(String codiceVersamento, String datiSpecificiDiRiscossione, Integer annoEsercizio) {
        String annoEsercizioStr = annoEsercizio != null ? Integer.toString(annoEsercizio) : "null";
        codiceVersamento = null != codiceVersamento ? codiceVersamento : "null";
        datiSpecificiDiRiscossione = null != datiSpecificiDiRiscossione ? datiSpecificiDiRiscossione : "null";
        return String.join("-", annoEsercizioStr, codiceVersamento, datiSpecificiDiRiscossione);
    }

    @Override
    @Transactional
    public void elaboraFlussoSintesi(Provvisori provvisorio, FlussoOrigine flussoOrigine, FlussoSintesi flussoSintesi,
        Ente ente) throws RiconciliazioneException {

        // se non esistono record di dettaglio per la sintesi e contemporaneamente dati
        // specifici riscossione e codice versamento NON sono null - errore
        controllaDatiDiDettaglio(flussoSintesi);

        /*
         * 8 - Aggiornamento dei dati del provvisorio sul record di sintesi Viene
         * reperito il provvisorio legato al flusso, update delle colonne anno e numero
         * provvisorio della sintesi a partire dai valori del provvisorio, update di
         * capitolo, articolo e piano dei conti --- ERRORE --- Provvisorio duplicato :
         * errore sulla cbl_r_stato_flusso_errore con relativo messaggio, update della
         * riga di sintesi con l'id dell'errore. Elaborazione al massimo in terminata
         * con errori, flusso origine IN ERRORE. uscita dal ciclo interno (SINTESI) e
         * passaggio al flusso origine successivo. Stessa cosa in caso di errore in fase
         * di update (con messaggio diverso)
         */
        verificaCatalogoEAggiornaFlussoSintesi(provvisorio, ente, flussoOrigine, flussoSintesi);

        /*
         * 9 - Controllo codici esclusione Se il codice versamento della sintesi � uno
         * di quelli presenti sotto la cbl_t_codiciversamento_esclusione --- ERRORE ----
         * errore sulla cbl_r_stato_flusso_errore con relativo messaggio, update della
         * riga di sintesi con l'id dell'errore. ERRORE DI TIPO WARNING, PROCEDERE CON
         * ELABORAZIONE
         */
        try {
            controllaCodiciEsclusione(ente, flussoSintesi);
        } catch (FlussoSintesiException innerException) {
            StatoFlussoErrore statoFlussoErrore = salvaStatoFlussoErrore(flussoSintesi,
                innerException.getCodiceErrore(), innerException.getInfoAggiuntive());
            flussoManager.aggiornaIdErrore(statoFlussoErrore, flussoSintesi.getId());
        }

        /*
         * 12 - Controlli dati specifici riscossione Se non esistono dati specifici di
         * riscossione o codice versamento E CONTEMPORANEAMENTE l'ente NON e'
         * plurintermediato OR CONTEMPORANEAMENTE l'ente ha flag_accertamento a TRUE ---
         * ERRORE --- errore sulla cbl_r_stato_flusso_errore con relativo messaggio,
         * update della riga di sintesi con l'id dell'errore. Elaborazione al massimo in
         * terminata con errori, flusso origine IN ERRORE. passaggio al prossimo flusso
         * sintesi
         */
        // controllaDatiSpecificiRiscossione ( flussoSintesi, ente );

        // pulire id_errore sul flusso sintesi
        StatoFlussoErrore nessunErrore = new StatoFlussoErrore();
        flussoManager.aggiornaIdErrore(nessunErrore, flussoSintesi.getId());

    }

    @Override
    public void aggiornaStatoFlussoOrigine(FlussoOrigine flussoOrigine, StatoFlussoEnum stato)
                    throws FlussoOrigineException {
        flussoOrigineManager.cambiaStatoFlusso(flussoOrigine, stato);
        flussoOrigineManager.aggiornaContatoreTentativi(flussoOrigine.getContatoreTentativi() + 1,
            flussoOrigine.getIdentificativoFlusso());
    }

    @Override
    public void aggiornaStatoElaborazione(Elaborazione elaborazione, StatoElaborazioneEnum stato,
        List<FlussoOrigine> flussiOrigine) throws ElaborazioneException {

        LOGGER.info("Aggiorna lo stato dell'elaborazione a: " + stato.getCodice() );

        if (!elaborazione.isStatoAggiornato()) {
            elaborazione.setStatoElaborazione(stato);
            elaborazione.setDataFine(new Timestamp(System.currentTimeMillis()));
            if (!StatoElaborazioneEnum.AVVIATA.equals(stato)) {
                elaborazione.setDataFine(new Timestamp(System.currentTimeMillis()));
                elaborazione.setStatoAggiornato(true);
            } else {
                elaborazione.setDataInizio(new Timestamp(System.currentTimeMillis()));
            }
            if (StatoElaborazioneEnum.TERMINATA.equals(stato)) {
                ArrayList<String> flussi = new ArrayList<>();

                if (null != flussiOrigine) {
                    for (FlussoOrigine flussoOrigine : flussiOrigine) {
                        flussi.add(flussoOrigine.getIdentificativoFlusso());
                    }

                    elaborazione.setListaFlussi(flussi);
                }
            }
            elaborazioneManager.salva(elaborazione);
        }

    }

    @Override
    public DTOErroreFlusso salvaErroreFlussoSintesi(FlussoSintesi flussoSintesi, String codiceErrore,
        List<String> infoAggiuntive) {
        if (null == flussoSintesi) {
            throw new IllegalArgumentException("Flusso sintesi null!");
        }
        StatoFlussoErrore statoFlussoErrore = salvaStatoFlussoErrore(flussoSintesi, codiceErrore, infoAggiuntive);
        flussoManager.aggiornaIdErrore(statoFlussoErrore, flussoSintesi.getId());

        DTOErroreFlusso erroreFlusso = new DTOErroreFlusso();
        erroreFlusso.setCodiceErrore(codiceErrore);

        String descrizioneErrore = statoFlussoErrore.getDescrizioneAggiuntivaErrore();

        if (null != infoAggiuntive && infoAggiuntive.size() > 0) {
            descrizioneErrore = String.join(",", infoAggiuntive);
        }

        erroreFlusso.setDescrizioneErrore(descrizioneErrore);
        erroreFlusso.setIdentificativoFlusso(flussoSintesi.getIdentificativoFlusso());
        erroreFlusso.setTipologia(CostantiErrori.DTO_ERRORE_TIPOLOGIA_FS);
        return erroreFlusso;
    }

    @Override
    public DTOErroreFlusso salvaErroreFlussoOrigine(FlussoOrigine flussoOrigine, String codiceErrore,
        List<String> infoAggiuntive, StatoFlussoEnum statoFlusso) {
        if (null == flussoOrigine) {
            throw new IllegalArgumentException("Flusso origine null!");
        }

        StatoFlussoErrore statoFlussoErrore = salvaStatoFlussoErrore(flussoOrigine, codiceErrore, infoAggiuntive);

        try {
            aggiornaStatoFlussoOrigine(flussoOrigine, statoFlusso);
        } catch (FlussoOrigineException e) {
            LOGGER.error("ERRORE DURANTE IL SALVATAGGIO DELLO STATO DEL FLUSSO", e);
        }

        DTOErroreFlusso erroreFlusso = new DTOErroreFlusso();
        erroreFlusso.setCodiceErrore(codiceErrore);
        erroreFlusso.setDescrizioneErrore(statoFlussoErrore.getDescrizioneAggiuntivaErrore());
        erroreFlusso.setIdentificativoFlusso(flussoOrigine.getIdentificativoFlusso());
        erroreFlusso.setTipologia(CostantiErrori.DTO_ERRORE_TIPOLOGIA_FO);
        return erroreFlusso;
    }

    @Override
    @Transactional
    public boolean salvaErroreElaborazione(Elaborazione elaborazione, String codiceErrore,
        StatoElaborazioneEnum statoElaborazione, String messaggioErrore, List<String> infoAggiuntive,
        List<FlussoOrigine> flussiOrigine) {
        if (null == elaborazione) {
            LOGGER.error("Elaborazione null!");
            return false;
        }
        if (null == statoElaborazione) {
            LOGGER.error("Stato Elaborazione null!");
            return false;
        }
        if (!(elaborazione.isStatoAggiornato() && elaborazione.getStatoElaborazione() != null
                        && elaborazione.getStatoElaborazione().equals(statoElaborazione))) {
            if (StatoElaborazioneEnum.TERMINATA.equals(statoElaborazione)) {

                if (!CostantiErrori.NESSUN_FLUSSO_ELABORATO.equalsIgnoreCase(codiceErrore)) {
                    elaborazione.setMsgErrore(messaggioErrore);
                }

            } else if (StatoElaborazioneEnum.IN_ERRORE.equals(statoElaborazione)) {
                StringBuffer sb = new StringBuffer(DEFAULT_PROCESSING_ERROR_MESSAGE);

                if (!StringUtils.isEmpty(messaggioErrore)) {
                    if (null != infoAggiuntive && infoAggiuntive.size() > 0) {
                        String[] infoAggiuntiveArray = new String[infoAggiuntive.size()];
                        sb.append(" " + String.format(messaggioErrore,
                            (Object[]) infoAggiuntive.toArray(infoAggiuntiveArray)));
                    } else {
                        sb.append(" " + messaggioErrore);
                    }
                }
                elaborazione.setMsgErrore(sb.toString().trim());

            }
            elaborazione.setErrore(recuperaErrore(codiceErrore));
            elaborazione.setDataFine(new Timestamp(System.currentTimeMillis()));
            elaborazione.setStatoElaborazione(statoElaborazione);

            ArrayList<String> flussi = new ArrayList<>();
            if (null != flussiOrigine) {
                for (FlussoOrigine flussoOrigine : flussiOrigine) {
                    flussi.add(flussoOrigine.getIdentificativoFlusso());
                }

                elaborazione.setListaFlussi(flussi);
            }

            elaborazioneManager.salva(elaborazione);
            elaborazione.setStatoAggiornato(true);

        }
        return true;
    }

    private void controllaDatiDiDettaglio(FlussoSintesi flussoSintesi) throws FlussoSintesiException {

        boolean dettagliPresenti = flussoDettaglioManager.controllaDettagliPerRiconciliazione(flussoSintesi);

        if (!dettagliPresenti) {
            String message = String.format(
                "Nessuna riga di dettaglio per il flusso sintesi con id = [%s] relativo al flusso origine [%s]",
                flussoSintesi.getId(), flussoSintesi.getIdentificativoFlusso());
            LOGGER.error(message);
            throw new FlussoSintesiException("Nessun dettaglio trovato", CostantiErrori.RECORD_DETTGLIO_ASSENTI,
                message);
        }

    }

    private Errore recuperaErrore(String codiceErrore) {
        Errore errore = null;
        if (null != codiceErrore) {
            errore = erroreManager.leggi(codiceErrore);
            if (errore == null) {
                LOGGER.warn(String.format("Codice errore %s inesistente!", codiceErrore));
            }
        } else {
            LOGGER.warn("Codice errore null!");
        }
        return errore;
    }

    private StatoFlussoErrore salvaStatoFlussoErrore(Flusso flusso, String codiceErrore, List<String> infoAggiuntive) {

        Errore errore = recuperaErrore(codiceErrore);

        StatoFlussoErrore statoFlussoErrore = StatoFlussoErroreUtility.caricaStatoFlussoErrore(flusso, errore);

        String descrizioneErrore = errore.getDescrizioneErrore();

        if (null != infoAggiuntive && infoAggiuntive.size() > 0) {
            descrizioneErrore = String.join(",", infoAggiuntive);
        }

        statoFlussoErrore.setDescrizioneAggiuntivaErrore(descrizioneErrore);

        statoFlussoErrore = statoFlussoErroreManager.inserisciFlussoErrore(statoFlussoErrore);

        return statoFlussoErrore;

    }

    private boolean checkElaborazioneFlussoOrigine(FlussoOrigine flussoOrigine, List<DTOErroreFlusso> errori) {
        boolean outcome = true;

        for (DTOErroreFlusso errore : errori) {
            if (errore.getIdentificativoFlusso().equalsIgnoreCase(flussoOrigine.getIdentificativoFlusso())) {
                outcome = false;
                break;
            }
        }

        return outcome;
    }
}
