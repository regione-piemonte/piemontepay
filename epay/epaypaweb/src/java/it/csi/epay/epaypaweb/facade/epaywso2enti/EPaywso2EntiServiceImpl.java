/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.facade.epaywso2enti;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.common.*;
import it.csi.epay.epaypaweb.exception.*;
import it.csi.epay.epaypaweb.facade.EPaypaFacadeBase;
import it.csi.epay.epaypaweb.facade.EPaypaFacadeValidator;
import it.csi.epay.epaypaweb.facade.dto.common.ResponseType;
import it.csi.epay.epaypaweb.facade.dto.common.ResultType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.*;
import it.csi.epay.epaypaweb.facade.epaywso2enti.service.EPaywso2EntiService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebService;

@Stateless
@WebService(name = "EPaywso2EntiService", portName = "EPaywso2EntiServiceSOAP", targetNamespace = "http://www.csi.it/epay/epaywso/epaywso2entisrv")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EPaywso2EntiServiceImpl extends EPaypaFacadeBase implements EPaywso2EntiService {
	static private final String CLASSNAME = EPaywso2EntiServiceImpl.class.getSimpleName();

	static private final String ERR_APP_101 = "101";
	static private final String ERR_APP_112 = "112";
	static private final String ERR_APP_113 = "113";
	static private final String ERR_APP_114 = "114";
	static private final String ERR_APP_115 = "115";
	static private final String ERR_APP_116 = "116";
	static private final String ERR_SYS_200 = "200";

	@EJB
	private GestioneFlussiBusiness business;

	@Override
	public ResponseType trasmettiNotifichePagamento(TrasmettiNotifichePagamentoRequest requestType) {
		String methodName = "trasmettiNotifichePagamento";
		

		ResponseType responseType = new ResponseType();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// verifica
			EPaypaFacadeValidator.notNull("TrasmettiNotifichePagamentoRequest", requestType);

			// business con normalizzazione dei codici ente non censiti
			//FlussoCompletoDto<NotificaPagamentoDto> dto = toFlussoNotifichePagamentoDto(requestType);
			//business.trasmettiFlussoNotifichePagamento(dto);
			
			business.trasmettiFlussoNotifichePagamento(new TrasmettiFlussoNotifichePagamentoRequestDto(null, 0L, "SYSTEM", "", toFlussoNotifichePagamentoDto(requestType)));

			// response ok
			responseType.setResult(buildResultOK());

		} catch (FacadeXmlChoiceException e) {
			switch (e.getN()) {
			case 0:
				responseType.setResult(buildResult(ERR_APP_101, "Un soggetto anagrafico deve indicare una persona fisica o una persona giuridica, non nessuna delle due"));
				break;
			case 1:
				// OK
				break;
			default:
				responseType.setResult(buildResult(ERR_APP_101, "Il soggetto anagrafico " + e.getName() + " non puo' essere contemporanamente persona fisica e persona giuridica"));
				break;
			}
		} catch (BusinessTrasmettiFlussoTestataNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Testata non valorizzata"));

		} catch (BusinessTrasmettiFlussoCorpoNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Corpo non valorizzato"));

		} catch (BusinessTrasmettiFlussoExistsException e) {
			responseType.setResult(buildResult(ERR_APP_112, buildMessage(e)));

		} catch (BusinessNotFoundException e) {
			responseType.setResult(buildResult(ERR_APP_113, e.getObjName() + " non trovato per " + e.getKeyName() + ": " + e.getCod()));

		} catch (Throwable e) {
			responseType.setResult(buildResult(ERR_SYS_200, e));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return responseType;
	}

	@Override
	public ResponseType trasmettiAvvisiScaduti(TrasmettiAvvisiScadutiRequest requestType) {
		String methodName = "trasmettiAvvisiScaduti";
		

		ResponseType responseType = new ResponseType();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// verifica
			EPaypaFacadeValidator.notNull("TrasmettiAvvisiScadutiRequest", requestType);

			// business con normalizzazione dei codici ente non censiti
			//FlussoCompletoDto<AvvisoScadutoDto> dto = toAvvisoScadutoDto(requestType);
			//business.trasmettiFlussoAvvisoScaduto(dto);
			
			business.trasmettiFlussoAvvisoScaduto(new TrasmettiFlussoAvvisoScadutoRequestDto(null, 0L, "SYSTEM", "", toAvvisoScadutoDto(requestType)));

			// response ok
			responseType.setResult(buildResultOK());

		} catch (BusinessTrasmettiFlussoTestataNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Testata non valorizzata"));

		} catch (BusinessTrasmettiFlussoCorpoNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Corpo non valorizzato"));

		} catch (BusinessTrasmettiFlussoExistsException e) {
			responseType.setResult(buildResult(ERR_APP_112, buildMessage(e)));

		} catch (BusinessNotFoundException e) {
			responseType.setResult(buildResult(ERR_APP_113, e.getObjName() + " non trovato per " + e.getKeyName() + ": " + e.getCod()));

		} catch (Throwable e) {
			responseType.setResult(buildResult(ERR_SYS_200, e));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return responseType;
	}

	@Override
	//@formatter:off
	public ResponseType esitoInserimentoListaDiCarico(EsitoInserimentoListaDiCaricoRequest requestType) {
		String methodName = "esitoInserimentoListaDiCarico";
		

		ResponseType responseType = new ResponseType();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// verifica
			EPaypaFacadeValidator.notNull("EsitoInserimentoListaDiCaricoRequest", requestType);
			EPaypaFacadeValidator.notNull("Result", requestType.getResult());
			EPaypaFacadeValidator.notNull("TestataEsito", requestType.getTestataEsito());

			// business con normalizzazione dei codici ente non censiti
			business.registraEsitoInserimentoListaDiCarico(new RegistraEsitoInserimentoListaDiCaricoRequestDto(null, 0L, "SYSTEM", "", toEsitoPosizioniDebitorieDto(requestType)));
			//EsitoPosizioniDebitorieDto dto = toEsitoPosizioniDebitorieDto(requestType);
			//business.registraEsitoInserimentoListaDiCarico(dto);

			// response ok
			responseType.setResult(buildResultOK());

		} catch (BusinessTestataNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Testata non valorizzata"));

		} catch (BusinessNotFoundException e) {
			responseType.setResult(buildResult(ERR_APP_113, e.getObjName() + " non trovato per " + e.getKeyName() + ": " + e.getCod()));

		} catch (BusinessFoundMoreThanOneResultException e) {
			responseType.setResult(buildResult(ERR_APP_114, e.getObjName() + " non univoco per " + e.getKeyName() + ": " + e.getCod()));

		} catch (BusinessPosizioneDebitoriaRipetutaException e) {
			responseType.setResult(buildResult(ERR_APP_115, "idFlusso:" + e.getIdFlusso() + ", ripetitizione di idPosizioneDebitoriaEsterna:" + e.getIdPosizioneDebitoriaEst()));

		} catch (BusinessMismatchInNumberOfElementsException e) {
			responseType.setResult(buildResult(ERR_APP_115, "idFlusso:" + e.getId() + ", numero posizioni debitorie effettivo:" + e.getInt1() + ", numero esiti ricevuti:" + e.getInt2()));

		} catch (BusinessMismatchInNumberOfUpdatesException e) {
			responseType.setResult(buildResult(ERR_APP_115, "idFlusso:" + e.getId() + ", numero posizioni debitorie aggiornabili:" + e.getLong1() + ", numero esiti ricevuti:" + e.getLong2()));

		} catch (BusinessEsitoGiaRicevutoException e) {
			responseType.setResult(buildResult(ERR_APP_116 ,"idFlusso:" + e.getId())); 

		} catch (Throwable e) {
			responseType.setResult(buildResult(ERR_SYS_200, e));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return responseType;
	}
	//@formatter:on

	@Override
	//@formatter:off
	public ResponseType esitoAggiornaPosizioniDebitorie(EsitoAggiornaPosizioniDebitorieRequest requestType) {
		String methodName = "esitoAggiornaPosizioniDebitorie";
		

		ResponseType responseType = new ResponseType();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// verifica
			EPaypaFacadeValidator.notNull("EsitoAggiornaPosizioniDebitorieRequest", requestType);
			EPaypaFacadeValidator.notNull("Result", requestType.getResult());
			EPaypaFacadeValidator.notNull("TestataEsito", requestType.getTestataEsito());

			// business con normalizzazione dei codici ente non censiti
			//EsitoPosizioniDebitorieDto dto = toEsitoPosizioniDebitorieDto(requestType);
			//business.registraEsitoPosizioniDebitorieAggiornate(dto);
			
			business.registraEsitoPosizioniDebitorieAggiornate(new RegistraEsitoPosizioniDebitorieAggiornateRequestDto(null, 0L, "SYSTEM", "",toEsitoPosizioniDebitorieDto(requestType))); 

			// response ok
			responseType.setResult(buildResultOK());

		} catch (BusinessTestataNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Testata non valorizzata"));

		} catch (BusinessNotFoundException e) {
			responseType.setResult(buildResult(ERR_APP_113, e.getObjName() + " non trovato per " + e.getKeyName() + ": " + e.getCod()));

		} catch (BusinessFoundMoreThanOneResultException e) {
			responseType.setResult(buildResult(ERR_APP_114, e.getObjName() + " non univoco per " + e.getKeyName() + ": " + e.getCod()));

		} catch (BusinessPosizioneDebitoriaRipetutaException e) {
			responseType.setResult(buildResult(ERR_APP_115, "idFlusso:" + e.getIdFlusso() + ", ripetitizione di idPosizioneDebitoriaEsterna:" + e.getIdPosizioneDebitoriaEst()));

		} catch (BusinessMismatchInNumberOfElementsException e) {
			responseType.setResult(buildResult(ERR_APP_115, "idFlusso:" + e.getId() + ", numero posizioni debitorie effettivo:" + e.getInt1() + ", numero esiti ricevuti:" + e.getInt2()));

		} catch (BusinessMismatchInNumberOfUpdatesException e) {
			responseType.setResult(buildResult(ERR_APP_115, "idFlusso:" + e.getId() + ", numero posizioni debitorie aggiornabili:" + e.getLong1() + ", numero esiti ricevuti:" + e.getLong2()));

		} catch (BusinessEsitoGiaRicevutoException e) {
			responseType.setResult(buildResult(ERR_APP_116, "idFlusso:" + e.getId()));

		} catch (Throwable e) {
			responseType.setResult(buildResult(ERR_SYS_200, e));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return responseType;
	}
	//@formatter:on

	private String buildMessage(BusinessTrasmettiFlussoExistsException e) {
		return "Id Messaggio: " + e.getIdMessaggio()
			+ ", Cod. Fiscale Ente: " + e.getCodFiscaleEnte()
			+ ", Cod. Versamento: " + e.getCodVersamento()
			+ ", Tipo flusso: " + e.getTipoFlusso();
	}

    @Override
    public ResponseType trasmettiRT ( TrasmettiRTRequest parameters ) {
        ResponseType responseType = new ResponseType();

        log.debug ( "-------------------------------------------------------------------------------" );
        log.debug ( "RICEZIONE RT CF ENTE:" + parameters.getTestata ().getCFEnteCreditore () );
        log.debug ( "RICEZIONE RT CV:"      + parameters.getTestata ().getCodiceVersamento ());
        log.debug ( "RICEZIONE RT IDMESS:"  + parameters.getTestata ().getIdMessaggio () );
        log.debug ( "RICEZIONE RT NUMRT"    + parameters.getTestata ().getNumeroRT () );
        log.debug ( "-------------------------------------------------------------------------------" );
        log.debug ( "RICEZIONE RT ID:"  + parameters.getCorpoRT ().getElencoRT ().getRT ().get ( 0 ).getId ());
        log.debug ( "RICEZIONE RT XML:" + new String(parameters.getCorpoRT ().getElencoRT ().getRT ().get ( 0 ).getXML ()));
        log.debug ( "-------------------------------------------------------------------------------" );

        ResultType res = new ResultType ();
        
        res.setCodice ( "000" );
        res.setMessaggio ( "OK" );
        
        responseType.setResult ( res );
        
        return responseType;
    }


    @Override
    public ResponseType trasmettiRichiesteDiRevoca ( TrasmettiRichiesteDiRevocaRequest requestType ) {
        String methodName = "trasmettiRichiesteDiRevoca";
        

        ResponseType responseType = new ResponseType();

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            // verifica
            EPaypaFacadeValidator.notNull("TrasmettiRichiesteDiRevocaRequest", requestType);

            // business con normalizzazione dei codici ente non censiti
            //FlussoCompletoDto<RichiestaDiRevocaDto> dto = toFlussoRichiesteDiRevocaDto(requestType);
            //business.trasmettiFlussoRichiesteDiRevoca(dto);
            
            business.trasmettiFlussoRichiesteDiRevoca(new TrasmettiFlussoRichiesteDiRevocaRequestDto(null, 0L, "SYSTEM", "", toFlussoRichiesteDiRevocaDto(requestType)));

            // response ok
            responseType.setResult(buildResultOK());

        } catch (FacadeXmlChoiceException e) {
            switch (e.getN()) {
            case 0:
                responseType.setResult(buildResult(ERR_APP_101, "Un soggetto anagrafico deve indicare una persona fisica o una persona giuridica, non nessuna delle due"));
                break;
            case 1:
                // OK
                break;
            default:
                responseType.setResult(buildResult(ERR_APP_101, "Il soggetto anagrafico " + e.getName() + " non puo' essere contemporanamente persona fisica e persona giuridica"));
                break;
            }
        } catch (BusinessTrasmettiFlussoTestataNullException e) {
            responseType.setResult(buildResult(ERR_APP_101, "Testata non valorizzata"));

        } catch (BusinessTrasmettiFlussoCorpoNullException e) {
            responseType.setResult(buildResult(ERR_APP_101, "Corpo non valorizzato"));

        } catch (BusinessTrasmettiFlussoExistsException e) {
            responseType.setResult(buildResult(ERR_APP_112, buildMessage(e)));

        } catch (BusinessNotFoundException e) {
            responseType.setResult(buildResult(ERR_APP_113, e.getObjName() + " non trovato per " + e.getKeyName() + ": " + e.getCod()));

        } catch (Throwable e) {
            responseType.setResult(buildResult(ERR_SYS_200, e));

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return responseType;        
    }

}
