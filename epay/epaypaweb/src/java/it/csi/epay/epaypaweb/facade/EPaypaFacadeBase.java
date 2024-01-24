/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.facade;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;
import static it.csi.epay.epaypaweb.util.Util.COD_ESITO_OK;
import static it.csi.epay.epaypaweb.util.Util.DET_ESITO_OK;
import static it.csi.epay.epaypaweb.util.Util.UTENTE_INSERIMENTO;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.DatiSingolaRevocaDto;
import it.csi.epay.epaypaweb.dto.EsitoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.EsitoPosizioniDebitorieDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.dto.RichiestaDiRevocaDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.dto.TestataEsitoPosizioniDebitorieDto;
import it.csi.epay.epaypaweb.enumeration.EsitoRiversamentoEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoMittenteEnum;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.FacadeXmlChoiceException;
import it.csi.epay.epaypaweb.facade.dto.common.EsitoAggiornamentoType;
import it.csi.epay.epaypaweb.facade.dto.common.EsitoInserimentoType;
import it.csi.epay.epaypaweb.facade.dto.common.PosizioneDebitoriaType;
import it.csi.epay.epaypaweb.facade.dto.common.ResultType;
import it.csi.epay.epaypaweb.facade.dto.common.SoggettoType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.AvvisoScadutoType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.CorpoAvvisiScadutiType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.CorpoNotifichePagamentoType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.CorpoRichiesteDiRevocaType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.DatiSingolaRevocaType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.DatiTransazionePSPType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.EsitoAggiornaPosizioniDebitorieRequest;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.EsitoInserimentoListaDiCaricoRequest;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.NotificaPagamentoType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.RichiestaDiRevocaType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TestataAvvisiScadutiType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TestataEsitoType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TestataNotifichePagamentoType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TestataRichiesteDiRevocaType;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TrasmettiAvvisiScadutiRequest;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TrasmettiNotifichePagamentoRequest;
import it.csi.epay.epaypaweb.facade.epaywso2enti.dto.TrasmettiRichiesteDiRevocaRequest;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.TestataFlussoRendicontazioneType;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.TrasmettiFlussoRendicontazioneRequestType;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.gov.CtDatiSingoliPagamenti;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.gov.CtFlussoRiversamento;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.gov.CtIdentificativoUnivoco;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.gov.CtIstitutoMittente;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.gov.StTipoIdentificativoUnivoco;
import it.csi.epay.epaypaweb.util.Util;

/** Classe padre lato facade */
public class EPaypaFacadeBase {
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".facade");

	protected ResultType buildResultOK() {
		return buildResult(COD_ESITO_OK, DET_ESITO_OK);
	}

	protected ResultType buildResult(String codice, Throwable e) {
		String messaggio = null;
		if (e != null) {
			if (e.getMessage() != null) {
				messaggio = e.getMessage();
			} else {
				messaggio = "";
			}
			if (e.getCause() != null) {
				messaggio += ": " + e.getCause().toString();
			}
		}
		return buildResult(codice, messaggio);
	}

	protected ResultType buildResult(String codice, String messaggio) {
		ResultType resultType = new ResultType();
		resultType.setCodice(codice);
		resultType.setMessaggio(messaggio);
		return resultType;
	}

	private FlussoDto toFlussoDto(TestataNotifichePagamentoType type) {
		FlussoDto dto = null;

		if (type != null) {
			dto = new FlussoDto();
			dto.setTipoFlusso(TipoFlussoEnum.NOTIFICHE_PAGAMENTO); // fisso
			dto.setStatoFlusso(StatoFlussoEnum.ACQUISITO); // fisso
			dto.setIdMessaggio(type.getIdMessaggio());
			dto.setCodFiscaleEnte(type.getCFEnteCreditore());
			dto.setCodVersamento(type.getCodiceVersamento());
			dto.setNumeroElementi(type.getNumeroPagamenti());
			dto.setImportoTotale(type.getImportoTotalePagamenti());
			dto.setPagamentiSpontanei(type.isPagamentiSpontanei());
			dto.setDataInserimento(null); // la data viene calcolata al momento del salvataggio
			dto.setUtenteInserimento(UTENTE_INSERIMENTO); // fisso
			dto.setDataUltimaVariazione(null); // la data viene calcolata al momento del salvataggio
			dto.setUtenteUltimaVariazione(UTENTE_INSERIMENTO); // fisso
			dto.setCodEsito(null); // impostato al momento del salvataggio
			dto.setDetEsito(null); // impostato al momento del salvataggio
			dto.setDataInoltro(null);
		}

		return dto;
	}

    private FlussoDto toFlussoDto(TestataRichiesteDiRevocaType type) {
        FlussoDto dto = null;

        if (type != null) {
            dto = new FlussoDto();
            dto.setTipoFlusso(TipoFlussoEnum.RICHIESTE_DI_REVOCA); // fisso
            dto.setStatoFlusso(StatoFlussoEnum.ACQUISITO); // fisso
            dto.setIdMessaggio(type.getIdMessaggio());
            dto.setCodFiscaleEnte(type.getCFEnteCreditore());
            dto.setCodVersamento(type.getCodiceVersamento());
            dto.setNumeroElementi(type.getNumeroPagamenti());
            dto.setImportoTotale(type.getImportoTotalePagamenti());
            dto.setDataInserimento(null); // la data viene calcolata al momento del salvataggio
            dto.setUtenteInserimento(UTENTE_INSERIMENTO); // fisso
            dto.setDataUltimaVariazione(null); // la data viene calcolata al momento del salvataggio
            dto.setUtenteUltimaVariazione(UTENTE_INSERIMENTO); // fisso
            dto.setCodEsito(null); // impostato al momento del salvataggio
            dto.setDetEsito(null); // impostato al momento del salvataggio
            dto.setDataInoltro(null);
            dto.setRevoca ( "-TBD-" ); //<--------- TODO CSI_PAG-184 RDI-01
        }

        return dto;
    }	
	private FlussoDto toFlussoDto(TestataAvvisiScadutiType type) {
		FlussoDto dto = null;

		if (type != null) {
			dto = new FlussoDto();
			dto.setTipoFlusso(TipoFlussoEnum.AVVISI_SCADUTI); // fisso
			dto.setStatoFlusso(StatoFlussoEnum.ACQUISITO); // fisso
			dto.setIdMessaggio(type.getIdMessaggio());
			dto.setCodFiscaleEnte(type.getCFEnteCreditore());
			dto.setCodVersamento(type.getCodiceVersamento());
			dto.setNumeroElementi(type.getNumeroAvvisiScaduti());
			dto.setImportoTotale(type.getImportoTotaleAvvisiScaduti());
			dto.setDataInserimento(null); // la data viene calcolata al momento del salvataggio
			dto.setUtenteInserimento(UTENTE_INSERIMENTO); // fisso
			dto.setDataUltimaVariazione(null); // la data viene calcolata al momento del salvataggio
			dto.setUtenteUltimaVariazione(UTENTE_INSERIMENTO); // fisso
			dto.setCodEsito(null); // impostato al momento del salvataggio
			dto.setDetEsito(null); // impostato al momento del salvataggio
			dto.setDataInoltro(null);
		}

		return dto;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T unmarshalFromXML(byte[] input, Class<?> objectFlactoryClass, Class<T> unmarshalledObjClass) throws XMLStreamException, JAXBException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(new ByteArrayInputStream(input)));

        JAXBContext jc = JAXBContext.newInstance(objectFlactoryClass);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        return ((JAXBElement<T>)unmarshaller.unmarshal(xsr)).getValue();
	}
	
	private FlussoDto toFlussoDto(TestataFlussoRendicontazioneType type1, CtFlussoRiversamento type2) {
		FlussoDto dto = null;

		if (type1 != null) {
			if (dto == null)
				dto = new FlussoDto();

			dto.setTipoFlusso(TipoFlussoEnum.TRASMETTI_FLUSSO_RENDICONTAZIONE); // fisso
			dto.setStatoFlusso(StatoFlussoEnum.INOLTRATO); // fisso
			dto.setIdMessaggio(type1.getIdMessaggio());
			dto.setCodFiscaleEnte(type1.getCFEnteCreditore());
			dto.setIdFlussoRendicontazione(type1.getIdentificativoFlusso());
		}
		if (type2 != null) {
			if (dto == null)
				dto = new FlussoDto();

			dto.setNumeroElementi(Util.bd2int(type2.getNumeroTotalePagamenti()));
			dto.setImportoTotale(type2.getImportoTotalePagamenti());
			//
			dto.setDataOraCreazioneFlusso(EPaypaFacadeAdapter.toDate(type2.getDataOraFlusso()));
			dto.setIdUnivocoRegolamento(type2.getIdentificativoUnivocoRegolamento());
			dto.setDataRegolamento(EPaypaFacadeAdapter.toDate(type2.getDataRegolamento()));
			dto.setCodBicPsp(type2.getCodiceBicBancaDiRiversamento());
			//
			CtIstitutoMittente istitutoMittenteType = type2.getIstitutoMittente();
			if (istitutoMittenteType != null) {
				CtIdentificativoUnivoco identificativoUnivocoMittenteType = istitutoMittenteType.getIdentificativoUnivocoMittente();
				if (identificativoUnivocoMittenteType != null) {
					StTipoIdentificativoUnivoco tipoIdentificativoMittenteType = identificativoUnivocoMittenteType.getTipoIdentificativoUnivoco();
					if (tipoIdentificativoMittenteType != null) {
						dto.setTipoMittente(TipoMittenteEnum.fromId(tipoIdentificativoMittenteType.name()));
					}
					dto.setCodIdUnivocoMittente(identificativoUnivocoMittenteType.getCodiceIdentificativoUnivoco());
				}
				dto.setDenominazioneMittente(istitutoMittenteType.getDenominazioneMittente());
			}
		}

		// campi non significativi in questo tipo di flusso, ecc.
		if (dto != null) {
			dto.setCodVersamento(null);
			dto.setDataInserimento(null); // la data viene calcolata al momento del salvataggio
			dto.setUtenteInserimento(UTENTE_INSERIMENTO); // fisso
			dto.setDataUltimaVariazione(null); // la data viene calcolata al momento del salvataggio
			dto.setUtenteUltimaVariazione(UTENTE_INSERIMENTO); // fisso
			dto.setCodEsito(null); // impostato al momento del salvataggio
			dto.setDetEsito(null); // impostato al momento del salvataggio
			dto.setDataInoltro(null);
		}

		return dto;
	}

	private SoggettoAnagraficoDto toSoggettoAnagrafico(SoggettoType type) throws FacadeXmlChoiceException {
		SoggettoAnagraficoDto dto = null;

		if (type != null) {
			dto = new SoggettoAnagraficoDto();
			dto.setIdUnivocoFiscale(type.getIdentificativoUnivocoFiscale());
			if (type.getPersonaFisica() != null) {
				if (type.getPersonaGiuridica() != null) {
					StringBuffer cognomeNomeRagioneSociale = new StringBuffer();
					if (type.getPersonaFisica().getCognome() != null)
						cognomeNomeRagioneSociale.append(type.getPersonaFisica().getCognome() + " ");
					if (type.getPersonaFisica().getNome() != null)
						cognomeNomeRagioneSociale.append(type.getPersonaFisica().getNome() + " ");
					if (type.getPersonaGiuridica().getRagioneSociale() != null)
						cognomeNomeRagioneSociale.append(type.getPersonaGiuridica().getRagioneSociale());

					throw new FacadeXmlChoiceException(cognomeNomeRagioneSociale.toString(), 2);
				}
				dto.setTipologiaSoggettoEnum(TipologiaSoggettoEnum.PERSONA_FISICA);
				dto.setCognome(type.getPersonaFisica().getCognome());
				dto.setNome(type.getPersonaFisica().getNome());

			} else {
				if (type.getPersonaGiuridica() == null)
					throw new FacadeXmlChoiceException(null, 0);

				dto.setTipologiaSoggettoEnum(TipologiaSoggettoEnum.PERSONA_GIURIDICA);
				dto.setRagioneSociale(type.getPersonaGiuridica().getRagioneSociale());
			}
			dto.setIndirizzo(type.getIndirizzo());
			dto.setCivico(type.getCivico());
			dto.setCap(type.getCAP());
			dto.setLocalita(type.getLocalita());
			dto.setProvincia(type.getProvincia());
			dto.setNazione(type.getNazione());
			dto.setEmail(type.getEMail());
		}

		return dto;
	}

	protected NotificaPagamentoDto toNotificaPagamentoDto(NotificaPagamentoType type) throws FacadeXmlChoiceException {
	    NotificaPagamentoDto dto = null;

		if (type != null) {
			dto = new NotificaPagamentoDto();
			dto.setIdPosizioneDebitoria(type.getIdPosizioneDebitoria());
			dto.setAnnoRiferimento(type.getAnnoDiRiferimento());
			dto.setIUV(type.getIUV());
			dto.setImportoPagato(type.getImportoPagato());
			dto.setDataScadenza(EPaypaFacadeAdapter.toDate(type.getDataScadenza()));
			dto.setDesCausaleVersamento(type.getDescrizioneCausaleVersamento());
			dto.setDataEsitoPagamento(EPaypaFacadeAdapter.toDate(type.getDataEsitoPagamento()));
			dto.setSoggettoDebitore(toSoggettoAnagrafico(type.getSoggettoDebitore()));
			dto.setSoggettoVersante(toSoggettoAnagrafico(type.getSoggettoVersante()));
			dto.setDatiSpecificiRiscossione(type.getDatiSpecificiRiscossione());
			dto.setNote(type.getNote());
			dto.setCodAvviso(type.getCodiceAvviso());
			//
			DatiTransazionePSPType internalType = type.getDatiTransazionePSP();
			if (internalType != null) {
				dto.setIdPsp(internalType.getIdPSP());
				dto.setRagioneSocialePsp(internalType.getRagioneSocialePSP());
				dto.setCodTipoVersamento(internalType.getTipoVersamento());
				dto.setDesTipoVersamento("");
				dto.setIdFlussoRendicontazionePsp(internalType.getIdFlussoRendicontazionePSP());
				dto.setDataAvvioTransazione(EPaypaFacadeAdapter.toDate(internalType.getDataOraAvvioTransazione()));
				dto.setIUR(internalType.getIUR());
				dto.setDataOraAutorizzazione(EPaypaFacadeAdapter.toDate(internalType.getDataOraAutorizzazione()));
				dto.setTipoSicurezza(internalType.getTipoSicurezza());
				dto.setImportoTransato(internalType.getImportoTransato());
				dto.setImportoCommissioni(internalType.getImportoCommissioni());
			}
		}

		return dto;
	}

    protected RichiestaDiRevocaDto toRichiestaDiRevocaDto(RichiestaDiRevocaType type) throws FacadeXmlChoiceException {
        RichiestaDiRevocaDto dto = null;

        if (type != null) {
            dto = new RichiestaDiRevocaDto();
            
            dto.setApplicationId(type.getApplicationId ());
            dto.setCodiceContestoPagamento(type.getCodiceContestoPagamento ());
            dto.setDataOraMessaggioRevoca(type.getDataOraMessaggioRevoca ());
            
            List<DatiSingolaRevocaDto>lista = new ArrayList();
            
            for(DatiSingolaRevocaType item:type.getListaDatiSingolaRevoca ().getDatiSingolaRevoca ()) {
                lista.add ( toDatiSingolaRevocaDto(item) );
            }
            
            dto.setListaDatiSingolaRevoca (lista);
            
            dto.setIdentificativoDominio(type.getIdentificativoDominio ());
            dto.setIdentificativoMessaggioRevoca(type.getIdentificativoMessaggioRevoca ());
            dto.setImportoPagato(type.getImportoPagato ());
            dto.setIstitutoAttestante(toSoggettoDto(type.getIstitutoAttestante ()));
            dto.setIUV(type.getIUV ());
            dto.setTipoRevoca(type.getTipoRevoca ());
            dto.setXml(type.getXML ());
        }

        return dto;
    }	
	
    private DatiSingolaRevocaDto toDatiSingolaRevocaDto(DatiSingolaRevocaType dati) {
        DatiSingolaRevocaDto rev = new DatiSingolaRevocaDto ();
        
        rev.setCausale ( dati.getCausale () );
        rev.setDatiAggiuntivi (  dati.getDatiAggiuntivi ());
        rev.setImporto ( dati.getSingoloImportoRevocato () );
        rev.setIur ( dati.getIUR () );
        
        return rev;
    }
    
    private SoggettoAnagraficoDto toSoggettoDto(SoggettoType sogg) {
        SoggettoAnagraficoDto ext = new SoggettoAnagraficoDto();
        
        ext.setCap ( sogg.getCAP () );
        ext.setCivico ( sogg.getCivico () );
        ext.setEmail ( sogg.getEMail () );
        ext.setIdUnivocoFiscale ( sogg.getIdentificativoUnivocoFiscale () );
        ext.setIndirizzo (  sogg.getIndirizzo ());
        ext.setLocalita ( sogg.getLocalita () );
        ext.setNazione (  sogg.getNazione ());
        ext.setProvincia ( sogg.getProvincia () );
        ext.setRagioneSociale ( sogg.getPersonaGiuridica () != null ? sogg.getPersonaGiuridica ().getRagioneSociale () : ""  );
        
        TipologiaSoggettoEnum typo = TipologiaSoggettoEnum.PERSONA_GIURIDICA;
        String cognome = "";
        String nome = "";
        
        if(sogg.getPersonaFisica () != null) {
            typo = TipologiaSoggettoEnum.PERSONA_FISICA;
            ext.setCognome ( sogg.getPersonaFisica ().getCognome ());
            ext.setNome ( sogg.getPersonaFisica ().getNome ());
        }else {
            ext.setCognome ( sogg.getPersonaGiuridica ().getRagioneSociale ());
        }
        
        ext.setTipologiaSoggettoEnum ( typo );
        
        return ext;
    }
    
	//@formatter:off
	private List<NotificaPagamentoDto> toNotificaDtoList(CorpoNotifichePagamentoType type) throws FacadeXmlChoiceException {
		List<NotificaPagamentoDto> dtoList = null;

		if (   type != null
			&& type.getElencoNotifichePagamento() != null
			&& type.getElencoNotifichePagamento().getNotificaPagamento() != null)
		{
			dtoList = new ArrayList<NotificaPagamentoDto>();
			for (NotificaPagamentoType itemType : type.getElencoNotifichePagamento().getNotificaPagamento()) {
				dtoList.add(toNotificaPagamentoDto(itemType));
			}
		}

		return dtoList;
	}
	//@formatter:on
	
	//@formatter:off
    private List<RichiestaDiRevocaDto> toRichiestaRiRevocaDtoList(CorpoRichiesteDiRevocaType type) throws FacadeXmlChoiceException {
        List<RichiestaDiRevocaDto> dtoList = null;

        if (   type != null
            && type.getElencoRichiesteDiRevoca () != null
            && type.getElencoRichiesteDiRevoca ().getRichiestaDiRevoca () != null)
        {
            dtoList = new ArrayList<RichiestaDiRevocaDto>();
            for (RichiestaDiRevocaType itemType : type.getElencoRichiesteDiRevoca().getRichiestaDiRevoca()) {
                dtoList.add(toRichiestaDiRevocaDto(itemType));
            }
        }

        return dtoList;
    }
    //@formatter:on	

	protected AvvisoScadutoDto toAvvisoScadutoDto(AvvisoScadutoType type) {
		AvvisoScadutoDto dto = null;

		if (type != null) {
			dto = new AvvisoScadutoDto();
			dto.setIUV(type.getIUV());
			dto.setImporto(type.getImporto());
			dto.setDataScadenza(EPaypaFacadeAdapter.toDate(type.getDataScadenza()));
		}

		return dto;
	}

	//@formatter:off
	private List<AvvisoScadutoDto> toAvvisoScadutoDtoList(CorpoAvvisiScadutiType type) {
		List<AvvisoScadutoDto> dtoList = null;

		if (   type != null
			&& type.getElencoAvvisiScaduti() != null
			&& type.getElencoAvvisiScaduti().getAvvisoScaduto() != null)
		{
			dtoList = new ArrayList<AvvisoScadutoDto>();
			for (AvvisoScadutoType itemType : type.getElencoAvvisiScaduti().getAvvisoScaduto()) {
				dtoList.add(toAvvisoScadutoDto(itemType));
			}
		}

		return dtoList;
	}
	//@formatter:on

	private RiversamentoDto toRivesamentoDto(CtDatiSingoliPagamenti type) {
		RiversamentoDto dto = null;

		if (type != null) {
			dto = new RiversamentoDto();
			dto.setIUV(type.getIdentificativoUnivocoVersamento());
			dto.setIUR(type.getIdentificativoUnivocoRiscossione());
			dto.setIndicePagamento(type.getIndiceDatiSingoloPagamento());
			dto.setImportoPagato(type.getSingoloImportoPagato());
			dto.setEsito(EsitoRiversamentoEnum.fromId(type.getCodiceEsitoSingoloPagamento()));
			dto.setDataEsito(EPaypaFacadeAdapter.toDate(type.getDataEsitoSingoloPagamento()));
		}

		return dto;
	}

	private List<RiversamentoDto> toRendicontazioneDtoList(CtFlussoRiversamento type) {
		List<RiversamentoDto> dtoList = null;

		if (type != null) {
			dtoList = new ArrayList<RiversamentoDto>();
			for (CtDatiSingoliPagamenti itemType : type.getDatiSingoliPagamenti()) {
				dtoList.add(toRivesamentoDto(itemType));
			}
		}

		return dtoList;
	}

	protected FlussoCompletoDto<NotificaPagamentoDto> toFlussoNotifichePagamentoDto(TrasmettiNotifichePagamentoRequest type) throws FacadeXmlChoiceException {
		FlussoCompletoDto<NotificaPagamentoDto> dto = null;

		if (type != null) {
			dto = new FlussoCompletoDto<NotificaPagamentoDto>();
			dto.setFlusso(toFlussoDto(type.getTestata()));
			dto.setItemList(toNotificaDtoList(type.getCorpoNotifichePagamento()));
		}

		return dto;
	}

	protected FlussoCompletoDto<RichiestaDiRevocaDto> toFlussoRichiesteDiRevocaDto(TrasmettiRichiesteDiRevocaRequest type) throws FacadeXmlChoiceException {
        FlussoCompletoDto<RichiestaDiRevocaDto> dto = null;

        if (type != null) {
            dto = new FlussoCompletoDto<RichiestaDiRevocaDto>();
            dto.setFlusso(toFlussoDto(type.getTestata()));
            dto.setItemList(toRichiestaRiRevocaDtoList(type.getCorpoRichiesteDiRevoca()));
        }

        return dto;
    }
	
	protected FlussoCompletoDto<AvvisoScadutoDto> toAvvisoScadutoDto(TrasmettiAvvisiScadutiRequest type) {
		FlussoCompletoDto<AvvisoScadutoDto> dto = null;

		if (type != null) {
			dto = new FlussoCompletoDto<AvvisoScadutoDto>();
			dto.setFlusso(toFlussoDto(type.getTestata()));
			dto.setItemList(toAvvisoScadutoDtoList(type.getCorpoAvvisiScaduti()));
		}

		return dto;
	}

	//@formatter:off
	protected FlussoCompletoDto<RiversamentoDto> toFlussoRendicontazioneDto(TrasmettiFlussoRendicontazioneRequestType type) throws XMLStreamException, JAXBException {
		FlussoCompletoDto<RiversamentoDto> dto = null;

		if (type != null) {
			dto = new FlussoCompletoDto<RiversamentoDto>();
			CtFlussoRiversamento flussoRiversamento = unmarshalFromXML(
					type.getFlussoRiversamento(),
					it.csi.epay.epaypaweb.facade.rendicontazione.dto.gov.ObjectFactory.class,
					CtFlussoRiversamento.class
			);

			dto.setFlusso(toFlussoDto(type.getTestata(), flussoRiversamento));
			dto.setItemList(toRendicontazioneDtoList(flussoRiversamento));
		}

		return dto;
	}
	//@formatter:on

	private TestataEsitoPosizioniDebitorieDto toTestataEsitoPosizioniDebitorieDto(ResultType resultType, TestataEsitoType testataType) {
		TestataEsitoPosizioniDebitorieDto dto = null;

		if (resultType != null && testataType != null) {
			dto = new TestataEsitoPosizioniDebitorieDto();
			dto.setCodEsitoGenerale(resultType.getCodice());
			dto.setDetEsitoGenerale(resultType.getMessaggio());
			dto.setIdMessaggioFlusso(testataType.getIdMessaggio());
			dto.setCodFiscaleEnte(testataType.getCFEnteCreditore());
			dto.setCodVersamento(testataType.getCodiceVersamento());
		}

		return dto;
	}

	private EsitoPosizioneDebitoriaDto toEsitoPosizioneDebitoriaDto(PosizioneDebitoriaType type) {
		EsitoPosizioneDebitoriaDto dto = null;

		if (type != null) {
			dto = new EsitoPosizioneDebitoriaDto();
			dto.setIdPosizioneDebitoriaEsterna(type.getIdPosizioneDebitoria());
			dto.setIUV(type.getIUV()); // N.B. opzionale per gli esiti posizioni debitorie inserite, assente per gli esiti posizioni debitorie aggiornate
			dto.setCodAvviso(type.getCodiceAvviso());
			dto.setCodEsito(type.getCodiceEsito());
			dto.setDetEsito(type.getDescrizioneEsito());
		}

		return dto;
	}

	//@formatter:off
	private List<EsitoPosizioneDebitoriaDto> toEsitoPosizioneDebitoriaList(EsitoInserimentoType type) {
		List<EsitoPosizioneDebitoriaDto> dtoList = null;

		if (   type != null
			&& type.getElencoPosizioniDebitorieInserite() != null
			&& type.getElencoPosizioniDebitorieInserite().getPosizioneDebitoriaInserita() != null)
		{
			dtoList = new ArrayList<EsitoPosizioneDebitoriaDto>();
			for (PosizioneDebitoriaType itemType : type.getElencoPosizioniDebitorieInserite().getPosizioneDebitoriaInserita()) {
				dtoList.add(toEsitoPosizioneDebitoriaDto(itemType));
			}
		}

		return dtoList;
	}
	//@formatter:on

	//@formatter:off
	private List<EsitoPosizioneDebitoriaDto> toEsitoPosizioneDebitoriaList(EsitoAggiornamentoType type) {
		List<EsitoPosizioneDebitoriaDto> dtoList = null;

		if (   type != null
			&& type.getElencoPosizioniDebitorieAggiornate() != null
			&& type.getElencoPosizioniDebitorieAggiornate().getPosizioneDebitoriaAggiornata() != null)
		{
			dtoList = new ArrayList<EsitoPosizioneDebitoriaDto>();
			for (PosizioneDebitoriaType itemType : type.getElencoPosizioniDebitorieAggiornate().getPosizioneDebitoriaAggiornata()) {
				dtoList.add(toEsitoPosizioneDebitoriaDto(itemType));
			}
		}

		return dtoList;
	}
	//@formatter:on

	protected EsitoPosizioniDebitorieDto toEsitoPosizioniDebitorieDto(EsitoInserimentoListaDiCaricoRequest type) {
		EsitoPosizioniDebitorieDto dto = null;

		if (type != null) {
			dto = new EsitoPosizioniDebitorieDto();
			dto.setTestataDto(toTestataEsitoPosizioniDebitorieDto(type.getResult(), type.getTestataEsito()));
			dto.setEsitoList(toEsitoPosizioneDebitoriaList(type.getEsitoInserimento()));
		}

		return dto;
	}

	protected EsitoPosizioniDebitorieDto toEsitoPosizioniDebitorieDto(EsitoAggiornaPosizioniDebitorieRequest type) {
		EsitoPosizioniDebitorieDto dto = null;

		if (type != null) {
			dto = new EsitoPosizioniDebitorieDto();
			dto.setTestataDto(toTestataEsitoPosizioniDebitorieDto(type.getResult(), type.getTestataEsito()));
			dto.setEsitoList(toEsitoPosizioneDebitoriaList(type.getEsitoAggiornamento()));
		}

		return dto;
	}

}
