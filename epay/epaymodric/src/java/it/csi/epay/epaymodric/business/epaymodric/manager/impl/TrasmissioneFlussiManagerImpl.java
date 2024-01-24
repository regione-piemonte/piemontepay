/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import it.csi.epay.epaymodric.business.epaymodric.bo.Configurazione;
import it.csi.epay.epaymodric.business.epaymodric.facade.FlussiCompletiFacade;
import it.csi.epay.epaymodric.business.epaymodric.facade.RiconciliazioneVersamentiFacade;
import it.csi.epay.epaymodric.business.epaymodric.manager.ConfigurazioneManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EsitoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.TrasmissioneFlussiManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblRStatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTProvvisorio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTPsp;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoDettaglioRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoOrigineRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoSintesiRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ProvvisoriRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.PspRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoFlussoErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.ConversionUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.DatiAggiuntiviType;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TestataFlussiInErrore;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TestataTrasmissioneFlussiType;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TrasmettiFlussiInErrorePagoPARequest;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.TrasmettiFlussiPagoPARequest;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.CategoriaIUV;
import it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoCompletoType;
import it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.TestataFlussoCompletoType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ElencoFlussiInErroreType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ElencoFlussiInErroreType.RigheFlussi;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.FlussiInErroreType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.FlussoDettaglioType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.FlussoRiconciliazioneType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.FlussoRiconciliazioneType.RigheSintesi;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.FlussoSintesiType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.FlussoSintesiType.RigheDettaglio;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.PersonaFisicaType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.PersonaGiuridicaType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResultType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.SoggettoType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.TestataFlussiErroreType;
import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.TestataFlussoRiconciliazioneType;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringResult;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;


/**
 * @author fabio.fenoglio
 */

@Component
public class TrasmissioneFlussiManagerImpl implements TrasmissioneFlussiManager {

	private final Logger logger = LogManager.getLogger(this.getClass());

	private static final String SPLIT_CHARACTER = " ";

	@Autowired
	private FlussoOrigineRepository flussoOrigineRepository;

	@Autowired
	private PspRepository pspRepository;

	@Autowired
	private ProvvisoriRepository provvisoriRepository;

	@Autowired
	private FlussoSintesiRepository flussoSintesiRepository;

	@Autowired
	private FlussoDettaglioRepository flussoDettaglioRepository;

	@Autowired
	private StatoFlussoErroreRepository statoFlussoErroreRepository;

	@Autowired
	private EsitoManager esitoService;

	@Autowired
	private RiconciliazioneVersamentiFacade riconciliazioneVersamentiFacade;

	@Autowired
	private FlussiCompletiFacade flussiCompletiFacade;

    @Autowired
    private ConfigurazioneManager configurazioneManager;
    
	private static java.util.ResourceBundle res;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	static {
		 try {
		res = java.util.ResourceBundle.getBundle("config");
		 } catch(MissingResourceException e) {
		 System.out.println(e);
		 }
	}

	public static java.util.ResourceBundle getRes() {
		return res;
	}

	@Override
	public DTOOutputWsTrasmettiFlussiPagoPA creaOutputTrasmettiFlussiPagoPA(DTOInputWsTrasmettiFlussiPagoPA input) {
		logger.info("TrasmissioneFlussiService.trasmettiFlussiPagoPA INIZIO");

		DTOOutputWsTrasmettiFlussiPagoPA output = new DTOOutputWsTrasmettiFlussiPagoPA();
		DTOOutputWsEsito esito;

		CblTFlussoOrigine flussoOrigine = flussoOrigineRepository.findOne(input.getIdFlusso());

		if (flussoOrigine != null) {

			// TestataFlussoRiconciliazioneType testata = getTestataTrasmissioneFlussi (
			// input.getIdFlusso () );

			// TrasmettiFlussoRiconciliazioneRequest flussi = getFlussiRiconciliazione (
			// input.getIdFlusso () );
			// flussi.setTestataFlusso ( testata );

			// output.setTestataXml ( marshal ( testata,
			// TestataFlussoRiconciliazioneType.class ) );
			// output.setTrasmettiFlussoRiconciliazioneRequest ( marshal ( flussi,
			// TrasmettiFlussoRiconciliazioneRequest.class ) );

			esito = esitoService.getEsito(CostantiErrori.WS_ESITO_OK_DEFAULT);
		} else {
			esito = esitoService.getEsito(CostantiErrori.WS_ESITO_KO_DEFAULT);
		}

		logger.info("TrasmissioneFlussiService.trasmettiFlussiPagoPA FINE");
		output.setEsito(esito);
		return output;
	}

	@Override
	public DTOOutputWsTrasmettiFlussiInErrorePagoPA creaOutputTrasmettiFlussiInErrorePagoPA(
			DTOInputWsTrasmettiFlussiInErrorePagoPA input) {
		logger.info("TrasmissioneFlussiService.trasmettiFlussiInErrorePagoPA INIZIO");

		DTOOutputWsTrasmettiFlussiInErrorePagoPA output = new DTOOutputWsTrasmettiFlussiInErrorePagoPA();
		DTOOutputWsEsito esito;

		CblTFlussoOrigine flussoOrigine = flussoOrigineRepository.findOne(input.getIdFlusso());

		if (flussoOrigine != null) {

			esito = esitoService.getEsito(CostantiErrori.WS_ESITO_OK_DEFAULT);
		} else {
			esito = esitoService.getEsito(CostantiErrori.WS_ESITO_KO_DEFAULT);
		}

		logger.info("TrasmissioneFlussiService.trasmettiFlussiInErrorePagoPA FINE");
		output.setEsito(esito);
		return output;
	}

	

	private byte[] getFlussoRiconciliato(CblTFlussoOrigine flussoOrigine) {
		FlussoRiconciliazioneType FlussoRiconciliazione = new FlussoRiconciliazioneType();

		FlussoRiconciliazione.setTestataFlusso(getTestataFlussoRiconciliazione(flussoOrigine));
		FlussoRiconciliazione.setRigheSintesi(getFlussiRiconciliazione(flussoOrigine));
		 if (FlussoRiconciliazione.getRigheSintesi () == null || CollectionUtils.isEmpty ( FlussoRiconciliazione.getRigheSintesi () .getSingolaRigaSintesi ()) )
	        {
	            return null;
	        }
		
		StringResult result = new StringResult();

		jaxb2Marshaller.marshal(FlussoRiconciliazione, result);

		String marshalled = result.toString();

		marshalled = marshalled.replaceAll("ns2:flussoRiconciliazioneType", "FlussoRiconciliazione");

		return marshalled.getBytes();
	}
	

    private byte[] getFlussoCompleto(CblTFlussoOrigine flussoOrigine) {
        FlussoCompletoType flussoCompleto = new FlussoCompletoType();

        flussoCompleto.setTestataFlusso(getTestataFlussoCompleto(flussoOrigine));
        flussoCompleto.setRigheSintesi(getRigheSintesiFlussoCompleto(flussoOrigine));
        
        if (flussoCompleto.getRigheSintesi () == null ||CollectionUtils.isEmpty ( flussoCompleto.getRigheSintesi () .getSingolaRigaSintesi ()) )
        {
            return null;
        }

        StringResult result = new StringResult();
        jaxb2Marshaller.marshal(flussoCompleto, result);

        return result.toString().getBytes();
    }
    
    private FlussoCompletoType getFlussoCompletoType(CblTFlussoOrigine flussoOrigine) {
        FlussoCompletoType flussoCompleto = new FlussoCompletoType();

        flussoCompleto.setTestataFlusso(getTestataFlussoCompleto(flussoOrigine));
        flussoCompleto.setRigheSintesi(getRigheSintesiFlussoCompleto(flussoOrigine));
        
        return flussoCompleto;
       
    }
    

    private byte[] getFlussoCompletoByte(FlussoCompletoType flussoCompletoType) {
        
        if (flussoCompletoType== null || flussoCompletoType.getRigheSintesi () == null 
                        ||CollectionUtils.isEmpty ( flussoCompletoType.getRigheSintesi () .getSingolaRigaSintesi ()) )
        {
            return null;
        }

        StringResult result = new StringResult();
        jaxb2Marshaller.marshal(flussoCompletoType, result);

        return result.toString().getBytes();
    }

	private TestataTrasmissioneFlussiType getTestataTrasmissioneFlussi(CblTFlussoOrigine flussoOrigine) {

		TestataTrasmissioneFlussiType testata = new TestataTrasmissioneFlussiType();

		testata.setCFEnteCreditore(flussoOrigine.getIdentificativoIstitutoRicevente());
		testata.setIdentificativoFlusso(flussoOrigine.getIdentificativoFlusso());
		testata.setIdMessaggio(getIdMessaggio(flussoOrigine.getId()));
		testata.setIdPSP(flussoOrigine.getCblTPsp().getIdentificativoPsp());
		testata.setDataOraFlusso(ConversionUtils.convertDateToXmlGregorianCalendar(new Date()));

		return testata;
	}

	@Override
	public ResponseType trasmettiFlussiInErrorePagoPA(Configurazione conf, List<String> identificativoFlussoList) {

		logger.info("TrasmissioneFlussiService.trasmettiFlussiInErrorePagoPA INIZIO");

		ResponseType response;

		TrasmettiFlussiInErrorePagoPARequest request = new TrasmettiFlussiInErrorePagoPARequest();

		List<CblTFlussoOrigine> flussoOrigineList = flussoOrigineRepository
				.findByIdentificativoFlussoIn(identificativoFlussoList);

		TestataFlussiInErrore testata = getTestataFlussiInErrore(flussoOrigineList.get(0).getId());
		request.setTestataFlussiInErrore(testata);
		request.setFlussiInErrore(getFlussiInErrore(flussoOrigineList));

		response = riconciliazioneVersamentiFacade.trasmettiFlussiInErrorePagoPA(
				/* getRes ().getString ( "service.riconciliazioneversamenti.endpoint" ), */conf.getValore(), request);

		logger.info("TrasmissioneFlussiService.trasmettiFlussiInErrorePagoPA FINE");

		return response;
	}

	private byte[] getFlussiInErrore(List<CblTFlussoOrigine> flussoOrigineList) {
		ElencoFlussiInErroreType elencoFlussiInErroreType = new ElencoFlussiInErroreType();

		RigheFlussi righeFlussi = new RigheFlussi();
		for (CblTFlussoOrigine flussoOrigine : flussoOrigineList) {

			List<CblTFlussoSintesi> flussoSintesiList = flussoSintesiRepository
					.findByIdFlussoOrigineAndIdErroreIsNotNull(flussoOrigine.getId());

			if (null != flussoSintesiList && flussoSintesiList.size() > 0) {
				for (CblTFlussoSintesi flussoSintesi : flussoSintesiList) {
					righeFlussi.getFlussoErrato().addAll(mapFlussoInErrore(flussoSintesi, flussoOrigine));
				}
			} else {
				righeFlussi.getFlussoErrato().addAll(mapFlussoInErrore(null, flussoOrigine));
			}
		}

		TestataFlussiErroreType testata = new TestataFlussiErroreType();

		if ( flussoOrigineList.size() > 0 ) {
			testata.setCFEnteCreditore(flussoOrigineList.get(0).getIdentificativoIstitutoRicevente());
			testata.setDataOraMessaggio(
					ConversionUtils.convertDateToXmlGregorianCalendar(flussoOrigineList.get(0).getDataoraFlusso()));
			testata.setDenominazioneEnte(flussoOrigineList.get(0).getCblTEnte().getDenominazione());
			testata.setIdMessaggio(getIdMessaggio(flussoOrigineList.get(0).getId()));
		}
		elencoFlussiInErroreType.setTestataFlussi(testata);
		elencoFlussiInErroreType.setRigheFlussi(righeFlussi);

		StringResult result = new StringResult();

		jaxb2Marshaller.marshal(elencoFlussiInErroreType, result);

		return result.toString().getBytes();
	}

	public static String getIdMessaggio(Long idFlusso) {
		/*
		 * - Data nel formato anno-mese-giorno (10 caratteri)
		 *
		 * - Un carattere "-"
		 *
		 * - Timestamp nel formato ora:minuti:secondi.millisecondi (12 caratteri)
		 *
		 * - Un carattere "-"
		 *
		 * - Id fisico della tabella cbl_t_flusso_origine con tanti "0" in testa fino ad
		 * arrivare a 35 caratteri di lunghezza
		 */
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS").format(new Date());
		String idFlussoString = idFlusso.toString();
		String zeri = "";
		int numeroZeriDaAggiungere = 35 - (timestamp.length() + idFlussoString.length() + 1);
		while (numeroZeriDaAggiungere-- > 0) {
			zeri += "0";
		}

		return timestamp + "-" + zeri + idFlussoString;
	}

	private TestataFlussoRiconciliazioneType getTestataFlussoRiconciliazione(CblTFlussoOrigine flussoOrigine) {
		if (logger.isDebugEnabled()) {
			logger.debug("TrasmissioneFlussiService.getTestataTrasmissioneFlussi INIZIO");
		}
		TestataFlussoRiconciliazioneType output;

		if (flussoOrigine != null) {

			output = new TestataFlussoRiconciliazioneType();

			output.setIdMessaggio(getIdMessaggio(flussoOrigine.getId()));
			output.setIdentificativoFlusso(flussoOrigine.getIdentificativoFlusso());
			output.setCFEnteCreditore(flussoOrigine.getIdentificativoIstitutoRicevente());
			output.setIdPSP(flussoOrigine.getCblTPsp().getIdentificativoPsp());

			if (StringUtils.isNotEmpty(flussoOrigine.getCblTPsp().getIdentificativoPsp())) {
				CblTPsp psp = pspRepository.findByIdentificativoPsp(flussoOrigine.getCblTPsp().getIdentificativoPsp());
				if (psp != null) {
					output.setIdPSP(psp.getIdentificativoPsp());
					output.setDenominazionePSP(psp.getDenominazionePsp());
				} else {
					throw new DataIntegrityViolationException("Identificativo PSP non valido");
				}
			} else {
				output.setDenominazionePSP(null);
			}

			output.setDataOraMessaggio(ConversionUtils.convertDateToXmlGregorianCalendar(new Date()));

			if (flussoOrigine.getCblTEnte() != null) {
				output.setDenominazioneEnte(flussoOrigine.getCblTEnte().getDenominazione());
			}

			List<CblTProvvisorio> provvisorioList = provvisoriRepository.findAllByIdentificativoFlussoAndStato(
					flussoOrigine.getIdentificativoFlusso(), Costanti.VALORE_CAMPO_PROVVISORI_VALIDO);
			if (provvisorioList != null && provvisorioList.size() > 0) {
				if (null != provvisorioList.get(0).getAnnoProvvisorio()) {
					output.setProvvisorioAnno(provvisorioList.get(0).getAnnoProvvisorio());
				}
				if (null != provvisorioList.get(0).getNumeroProvvisorio()) {
					output.setProvvisorioNumero(provvisorioList.get(0).getNumeroProvvisorio());
				}
			}

			output.setImportoTotalePagamentiFlusso(flussoOrigine.getImportoTotalePagamenti());

			output.setNumeroTotalePagamentiFlusso(flussoOrigine.getNumeroTotalePagamenti() != null
					? new BigInteger(flussoOrigine.getNumeroTotalePagamenti().toString())
					: null);

			output.setImportoTotalePagamentiIntermediati(flussoOrigine.getImportoTotalePagamentiIntermediati());

			output.setNumeroTotalePagamentiIntermediati(flussoOrigine.getNumeroTotalePagamentiIntermediati() != null
					? new BigInteger(flussoOrigine.getNumeroTotalePagamentiIntermediati().toString())
					: null);

			output.setIdentificativoUnivocoRegolamento(flussoOrigine.getIdentificativoUnivocoRegolamento());

			if (null != flussoOrigine.getDataRegolamento()) {
				output.setDataRegolamento(
						ConversionUtils.convertDateToXmlGregorianCalendar(flussoOrigine.getDataRegolamento()));
			}

		} else {
			output = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("TrasmissioneFlussiService.getTestataTrasmissioneFlussi FINE");
		}
		return output;
	}

	private TestataFlussiInErrore getTestataFlussiInErrore(Long idFlusso) {
		if (logger.isDebugEnabled()) {
			logger.debug("TrasmissioneFlussiService.getTestataFlussiInErrore INIZIO");
		}
		TestataFlussiInErrore output;

		CblTFlussoOrigine flussoOrigine = flussoOrigineRepository.findOne(idFlusso);

		if (flussoOrigine != null) {

			output = new TestataFlussiInErrore();

			output.setIdMessaggio(getIdMessaggio(idFlusso));
			output.setCFEnteCreditore(flussoOrigine.getIdentificativoIstitutoRicevente());

			output.setDataOra(ConversionUtils.convertDateToXmlGregorianCalendar(new Date()));

		} else {
			output = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("TrasmissioneFlussiService.getTestataFlussiInErrore FINE");
		}
		return output;
	}

	private RigheSintesi getFlussiRiconciliazione(CblTFlussoOrigine flussoOrigine) {
	    if (logger.isDebugEnabled()) {
	        logger.debug("TrasmissioneFlussiService.getFlussoRiconciliazioneSintetico INIZIO");
	    }
	    RigheSintesi output = new RigheSintesi();

	    List<CblTFlussoSintesi> flussiSintesi = getFlussiSintesiOrdinati(flussoOrigine.getId());

	    Integer progressivo = 1;

	    for (CblTFlussoSintesi flussoSintesi : flussiSintesi) {

	        FlussoSintesiType rigaSintesi = mapFlussoSintesi(flussoSintesi, progressivo);
	        List<CblTFlussoDettaglio> flussiDettaglio = getFlussiDettaglioOrdinati(flussoSintesi);
			
	        Configurazione conf = configurazioneManager.leggi ( flussoOrigine.getCblTEnte ().getIdEnte (), Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI );

	        for ( CblTFlussoDettaglio flussoDettaglio: flussiDettaglio ) {
	            //                if ( !CategoriaIUV.SCONOSCIUT_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
	            //                    || ( CategoriaIUV.SCONOSCIUT_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
	            //                        && Boolean.TRUE.equals ( Boolean.valueOf ( conf.getValore () ) ) ) ) {

	            if ( CategoriaIUV.INTERM_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
	                            || CategoriaIUV.NON_INTERM_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
	                            ||  (conf != null && Boolean.TRUE.equals ( Boolean.valueOf ( conf.getValore () ) ) ) ) {
	                if ( null == rigaSintesi.getRigheDettaglio () ) {
	                    RigheDettaglio righeDettaglio = new RigheDettaglio ();
	                    rigaSintesi.setRigheDettaglio ( righeDettaglio );
	                }
	                rigaSintesi.getRigheDettaglio ().getSingolaRigaDettaglio ().add ( mapFlussoDettaglio ( flussoDettaglio, flussoSintesi ) );
	            }
	        }

	        if (rigaSintesi.getRigheDettaglio()!= null && !CollectionUtils.isEmpty ( rigaSintesi.getRigheDettaglio().getSingolaRigaDettaglio () ))
	        {
	            output.getSingolaRigaSintesi().add(rigaSintesi);
	            progressivo++;
	        }
	    }
	    if (logger.isDebugEnabled()) {
	        logger.debug("TrasmissioneFlussiService.getFlussoRiconciliazioneSintetico FINE");
	    }
	    return output;
	}
	
	
	private FlussoCompletoType.RigheSintesi getRigheSintesiFlussoCompleto(CblTFlussoOrigine flussoOrigine) {

	    FlussoCompletoType.RigheSintesi output = new FlussoCompletoType.RigheSintesi();

	    List<CblTFlussoSintesi> flussiSintesi = getFlussiSintesiOrdinati(flussoOrigine.getId());

	    Integer progressivo = 1;

	    for (CblTFlussoSintesi flussoSintesi : flussiSintesi) {
	        it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoSintesiType fst= mapSingolaRigaSintesiFlussoCompleto(flussoSintesi, flussoOrigine, progressivo);
	        if (!CollectionUtils.isEmpty ( fst.getRigheDettaglio().getSingolaRigaDettaglio () ))
	        {
	            output.getSingolaRigaSintesi().add(fst);
	            progressivo++;
	        }
	    }

	    return output;
	}
	

	private List<CblTFlussoSintesi> getFlussiSintesiOrdinati(Long idFlusso) {
		List<CblTFlussoSintesi> flussiSintesi = flussoSintesiRepository.findByIdFlussoOrigine(idFlusso);

		if (flussiSintesi != null) {
			// ordino per ID in modo da avere una numerazione progressiva consistente
			flussiSintesi.sort(new Comparator<CblTFlussoSintesi>() {

				@Override
				public int compare(CblTFlussoSintesi o1, CblTFlussoSintesi o2) {
					return o1.getId().compareTo(o2.getId());
				}
			});
		}

		return flussiSintesi;
	}

	private List<CblTFlussoDettaglio> getFlussiDettaglioOrdinati(CblTFlussoSintesi flussoSintesi) {

		List<CblTFlussoDettaglio> flussiDettaglio = flussoDettaglioRepository.findByCblTFlussoSintesi(flussoSintesi);

		if (flussiDettaglio != null) {
			// ordino per ID in modo da avere una numerazione progressiva consistente
			flussiDettaglio.sort(new Comparator<CblTFlussoDettaglio>() {

				@Override
				public int compare(CblTFlussoDettaglio o1, CblTFlussoDettaglio o2) {
					return o1.getId().compareTo(o2.getId());
				}
			});
		}

		return flussiDettaglio;
	}

	private FlussoSintesiType mapFlussoSintesi(CblTFlussoSintesi flussoSintesi,
					Integer progressivo) {
		FlussoSintesiType output = new FlussoSintesiType();

        if ( null != flussoSintesi.getArticolo () ) {
            output.setArticolo ( flussoSintesi.getArticolo ().toString () );
        }
		
        if ( null != flussoSintesi.getCapitolo () ) {
            output.setCapitolo ( flussoSintesi.getCapitolo () );
        }
		
		output.setCodiceVersamento(flussoSintesi.getCodiceVersamento());
		output.setDatiSpecificiDiRiscossione(flussoSintesi.getDatiSpecificiDiRiscossione());
		// output.setIdFlusso ( flussoOrigine.getIdentificativoFlusso () );
		output.setImportoQuotaAggregazione(flussoSintesi.getImportoQuotaAggregazione());
		output.setNroPagamentiAggregazione(
				null != flussoSintesi && flussoSintesi.getNumeroVersQuotaAggregazione() != null
						? flussoSintesi.getNumeroVersQuotaAggregazione().intValue()
						: 0);
        if ( StringUtils.isNotBlank ( flussoSintesi.getPianoDeiConti () ) ) {
            output.setPdC ( flussoSintesi.getPianoDeiConti () );
        }
		output.setDescrizioneCodiceVersamento(flussoSintesi.getDescrizioneCodiceVersamento());
		if (null != progressivo) {
			output.setProgressivoFlussoSintetico(progressivo);
		}

		output.setAccertamentoAnno(
				flussoSintesi.getAccertamentoAnno() != null ? flussoSintesi.getAccertamentoAnno () : null);
		output.setAccertamentoNro(
				flussoSintesi.getAccertamentoNumero() != null ? flussoSintesi.getAccertamentoNumero ()
						: null);

		return output;
	}

	private FlussoDettaglioType mapFlussoDettaglio(CblTFlussoDettaglio source, CblTFlussoSintesi flussoSintesi) {
		FlussoDettaglioType output = new FlussoDettaglioType();

		output.setCausale(source.getDescrizioneCausaleVersamento());
		output.setDescrizioneCausaleVersamento(flussoSintesi.getDescrizioneCodiceVersamento());
		output.setEsitoPagamento(source.getEsitoPagamento());
		output.setIdentificativoUnivocoRiscossione(source.getIdentificativoUnicoRiscossione());
		output.setIdentificativoUnivocoVersamento(source.getIdentificativoUnicoVersamento());
		output.setImportoSingoloVersamento(source.getImportoSingoloVersamento());
		if (null != source.getIndiceSingoloVersamento()) {
			output.setIndiceSingoloVersamento(source.getIndiceSingoloVersamento());
		}
		output.setTransactionid(source.getTransactionid());
		output.setDataPagamento(ConversionUtils.convertTimestampToXmlGregorianCalendar(source.getDataPagamento()));

		if (StringUtils.isNotEmpty(source.getCodicefiscalePagatore())) {
			output.setAnagraficaPagatore(getAnagraficaPagatore(source));
		}

		return output;
	}

	private List<FlussiInErroreType> mapFlussoInErrore(CblTFlussoSintesi source, CblTFlussoOrigine flussoOrigine) {

		List<FlussiInErroreType> outputList = new ArrayList<>();

		List<CblRStatoFlussoErrore> erroreList = statoFlussoErroreRepository.findByCblTFlussoOrigine(flussoOrigine);

		if (null != erroreList && erroreList.size() > 0) {

			for (CblRStatoFlussoErrore errore : erroreList) {
				outputList.add(mapSingleError(errore, source, flussoOrigine));
			}

		} else {
			outputList.add(mapSingleError(null, source, flussoOrigine));
		}
		return outputList;
	}

	private FlussiInErroreType mapSingleError(CblRStatoFlussoErrore errore, CblTFlussoSintesi source,
			CblTFlussoOrigine flussoOrigine) {
		FlussiInErroreType output = new FlussiInErroreType();
		if (null != errore) {
			output.setCodiceErrore(errore.getCblDErrore().getCodiceErrore());
			output.setDescrizioneErrore(errore.getDescrizioneAggiuntivaErrore());
		} else {
			output.setCodiceErrore(CostantiErrori.ERRORE_DI_SISTEMA);
			output.setDescrizioneErrore("ERRORE DI SISTEMA");
		}
		if (null != source) {
			output.setCodiceVersamento(source.getCodiceVersamento());
		}
		output.setIdPsp(flussoOrigine.getCblTPsp().getIdentificativoPsp());
		output.setDenominazionePSP(flussoOrigine.getCblTPsp().getDenominazionePsp());
		output.setIdentificativoFlusso(flussoOrigine.getIdentificativoFlusso());
		output.setIdentificativoUnivocoRegolamento(flussoOrigine.getIdentificativoUnivocoRegolamento());
		output.setImportoTotalePagamentiIntermediati(flussoOrigine.getImportoTotalePagamentiIntermediati());
		output.setImportoTotalePagamentiFlusso(flussoOrigine.getImportoTotalePagamenti());
		if (null != flussoOrigine.getNumeroTotalePagamenti()) {
			output.setNumeroTotalePagamentiFlusso(new BigInteger(flussoOrigine.getNumeroTotalePagamenti().toString()));
		}
		if (null != flussoOrigine.getNumeroTotalePagamentiIntermediati()) {
			output.setNumeroTotalePagamentiIntermediati(
					new BigInteger(flussoOrigine.getNumeroTotalePagamentiIntermediati().toString()));
		}
		return output;
	}
	
	@Override
	public ResponseType trasmettiFlussiPagoPA(Map<String, Configurazione> configurazioneEnte, String identificativoFlusso) {
	    logger.info("TrasmissioneFlussiService.trasmettiFlussiPagoPA INIZIO");

	    ResponseType response = null;

	    List<String> identificativoFlussoList = new ArrayList<>();

	    identificativoFlussoList.add(identificativoFlusso);

	    List<CblTFlussoOrigine> flussoOrigineList = flussoOrigineRepository
	                    .findByIdentificativoFlussoIn(identificativoFlussoList);

	    if (flussoOrigineList != null && flussoOrigineList.size() == 1) {
	        CblTFlussoOrigine flussoOrigine = flussoOrigineList.get(0);

	        TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest = new TrasmettiFlussiPagoPARequest();

	        trasmettiFlussiPagoPARequest.setTestataTrasmissioneFlussi(getTestataTrasmissioneFlussi(flussoOrigine));
	        trasmettiFlussiPagoPARequest.setFlussoRiconciliato(getFlussoRiconciliato(flussoOrigine));

	        if (null == trasmettiFlussiPagoPARequest.getFlussoRiconciliato ())
	        {
	            response = new ResponseType ();
	            it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ResultType resultType= new it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ResultType ();
	            response.setResult ( resultType );
	            if ( Boolean.FALSE.equals ( Boolean.valueOf (configurazioneEnte
	                .get ( Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI ).getValore ()) ) ) 
	            {
	                response.getResult ().setCodice ( Costanti.RESPONSE_WS_OK );
	                response.getResult ().setMessaggio ( Costanti.MESSAGE_SOLO_FLUSSI_SCONOSCIUTI );
	            }
	            else
	            {
	                response.getResult ().setCodice ("KO");
	                response.getResult ().setMessaggio ( "Non sono stati trovati flussi sintesi" );
	            }
	        }

	        else
	        {
	            response = riconciliazioneVersamentiFacade.trasmettiFlussiPagoPA( /*
	             * getRes ().getString (
	             * "service.riconciliazioneversamenti.endpoint"
	             * ),
	             */
	                configurazioneEnte
	                .get ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI ).getValore(), trasmettiFlussiPagoPARequest);
	        }
	    }


	    logger.info("TrasmissioneFlussiService.trasmettiFlussiPagoPA FINE");
	    return response;
	}

	@Override
	public it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType trasmettiFlussiCompletiPagoPA(
	    Map<String, Configurazione> configurazioneEnte, String identificativoFlusso) {
		logger.info("TrasmissioneFlussiCompletiService.trasmettiFlussiCompletiPagoPA INIZIO");

		it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType response = null;

		List<String> identificativoFlussoList = new ArrayList<>();
		identificativoFlussoList.add(identificativoFlusso);

		List<CblTFlussoOrigine> flussoOrigineList = flussoOrigineRepository
				.findByIdentificativoFlussoIn(identificativoFlussoList);

		if (flussoOrigineList != null && flussoOrigineList.size() == 1) {
			CblTFlussoOrigine flussoOrigine = flussoOrigineList.get(0);

			if (flussoOrigine != null) {
			    it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.TrasmettiFlussiPagoPARequest trasmettiFlussiCompletiPagoPARequest = new it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.TrasmettiFlussiPagoPARequest();

			    trasmettiFlussiCompletiPagoPARequest.setTestata(getTestataTrasmissioneFlussiCompleti(flussoOrigine));
			     FlussoCompletoType flussoCompletoTtype= getFlussoCompletoType ( flussoOrigine );
			     trasmettiFlussiCompletiPagoPARequest.setFlussoCompleto(getFlussoCompletoByte ( flussoCompletoTtype ));
			     trasmettiFlussiCompletiPagoPARequest.setDatiAggiuntivi(getDatiAggiuntiviType(flussoCompletoTtype));
			    
//			    trasmettiFlussiCompletiPagoPARequest.setFlussoCompleto(getFlussoCompleto(flussoOrigine));
//			    trasmettiFlussiCompletiPagoPARequest.setDatiAggiuntivi(getDatiAggiuntivi(flussoOrigine));

			    if (null == trasmettiFlussiCompletiPagoPARequest.getFlussoCompleto ())
			    {
			        response = new it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType ();
			        ResultType resultType= new ResultType ();
			        response.setResult ( resultType );
			        if ( Boolean.FALSE.equals ( Boolean.valueOf (configurazioneEnte
			            .get ( Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI ).getValore ()) ) ) 
			        {
			            response.getResult ().setCodice ( Costanti.RESPONSE_WS_OK );
			            response.getResult ().setMessaggio ( Costanti.MESSAGE_SOLO_FLUSSI_SCONOSCIUTI );
			        }
			        else
			        {
			            response.getResult ().setCodice ("KO");
                        response.getResult ().setMessaggio ( "Non sono stati trovati flussi sintesi" );
			        }
			    }
			    else
			    {

			        //				controllare se il flusso ha sintesi inviare flusso, se non ha sintesi controllo se il flag e' false non invio niente, simulo una risposta ok, se no lancio un'eccezione 
			        //				  Configurazione conf = configurazioneManager.leggi ( flussoOrigine.getCblTEnte ().getIdEnte (), Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI );
			        response = flussiCompletiFacade.trasmettiFlussiCompletiPagoPA(configurazioneEnte
			            .get ( Costanti.CONFIG_ATTR_SERVICE_ENDPOINT_INVIO_FLUSSI ).getValore(),
			            trasmettiFlussiCompletiPagoPARequest);
			    }
			}
		}

		logger.info("TrasmissioneFlussiCompletiService.trasmettiFlussiPagoPA FINE");
		return response;
	}

	private it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.TestataTrasmissioneFlussiType getTestataTrasmissioneFlussiCompleti(
			CblTFlussoOrigine flussoOrigine) {

		it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.TestataTrasmissioneFlussiType testata = new it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp.TestataTrasmissioneFlussiType();

		testata.setIdMessaggio(getIdMessaggio(flussoOrigine.getId()));
		testata.setCFEnteCreditore(flussoOrigine.getIdentificativoIstitutoRicevente());
		testata.setIdentificativoFlusso(flussoOrigine.getIdentificativoFlusso());
		testata.setDataOraFlusso(ConversionUtils.convertDateToXmlGregorianCalendar(new Date()));
		testata.setIdPSP(flussoOrigine.getCblTPsp().getIdentificativoPsp());

		return testata;
	}


	private DatiAggiuntiviType getDatiAggiuntivi(CblTFlussoOrigine flussoOrigine) {
		DatiAggiuntiviType datiAggiuntivi = new DatiAggiuntiviType();

		DatiAggiuntiviType.ElencoCodiciVersamento elencoCodiciVersamento = new DatiAggiuntiviType.ElencoCodiciVersamento();

		List<CblTFlussoSintesi> flussiSintesi = getFlussiSintesiOrdinati(flussoOrigine.getId());
		
		for (CblTFlussoSintesi flussoSintesi : flussiSintesi) {
			elencoCodiciVersamento.getCodiceVersamento().add(flussoSintesi.getCodiceVersamento());
		}

		datiAggiuntivi.setElencoCodiciVersamento(elencoCodiciVersamento);

		return datiAggiuntivi;

	}
	
	private DatiAggiuntiviType getDatiAggiuntiviType(FlussoCompletoType flussoCompleto) {
        DatiAggiuntiviType datiAggiuntivi = new DatiAggiuntiviType();

        DatiAggiuntiviType.ElencoCodiciVersamento elencoCodiciVersamento = new DatiAggiuntiviType.ElencoCodiciVersamento();

        
        if (flussoCompleto != null && flussoCompleto.getRigheSintesi () != null 
                        && !CollectionUtils.isEmpty (  flussoCompleto.getRigheSintesi ().getSingolaRigaSintesi () ))
        {
            for (it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoSintesiType flussoSintesi : flussoCompleto.getRigheSintesi ().getSingolaRigaSintesi ()) {
                elencoCodiciVersamento.getCodiceVersamento().add(flussoSintesi.getCodiceVersamento());
            }
            datiAggiuntivi.setElencoCodiciVersamento(elencoCodiciVersamento);
        }

        return datiAggiuntivi;

    }

	private TestataFlussoCompletoType getTestataFlussoCompleto(CblTFlussoOrigine flussoOrigine) {
		TestataFlussoCompletoType output = new TestataFlussoCompletoType();

		output.setIdMessaggio(getIdMessaggio(flussoOrigine.getId()));
		output.setDataOraMessaggio(ConversionUtils.convertDateToXmlGregorianCalendar(new Date()));
		output.setCFEnteCreditore(flussoOrigine.getIdentificativoIstitutoRicevente());
		output.setDenominazioneEnte(
				flussoOrigine.getCblTEnte() != null ? flussoOrigine.getCblTEnte().getDenominazione() : null);

		if (StringUtils.isNotEmpty(flussoOrigine.getCblTPsp().getIdentificativoPsp())) {
			CblTPsp psp = pspRepository.findByIdentificativoPsp(flussoOrigine.getCblTPsp().getIdentificativoPsp());
			if (psp != null) {
				output.setIdPSP(psp.getIdentificativoPsp());
				output.setDenominazionePSP(psp.getDenominazionePsp());
			} else {
				throw new DataIntegrityViolationException("Identificativo PSP non valido");
			}
		} else {
			output.setIdPSP(null);
			output.setDenominazionePSP(null);
		}

		output.setIdentificativoFlusso(flussoOrigine.getIdentificativoFlusso());

		output.setIdentificativoUnivocoRegolamento(flussoOrigine.getIdentificativoUnivocoRegolamento());

		output.setDataRegolamento(null != flussoOrigine.getDataRegolamento()
				? ConversionUtils.convertDateToXmlGregorianCalendar(flussoOrigine.getDataRegolamento())
				: null);

		output.setNumeroTotalePagamentiFlusso(flussoOrigine.getNumeroTotalePagamenti() != null
				? new BigInteger(flussoOrigine.getNumeroTotalePagamenti().toString())
				: null);

		output.setNumeroTotalePagamentiIntermediati(flussoOrigine.getNumeroTotalePagamentiIntermediati() != null
				? new BigInteger(flussoOrigine.getNumeroTotalePagamentiIntermediati().toString())
				: null);

		output.setImportoTotalePagamentiFlusso(flussoOrigine.getImportoTotalePagamenti());

		output.setImportoTotalePagamentiIntermediati(flussoOrigine.getImportoTotalePagamentiIntermediati());

		return output;
	}

	

	private it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoSintesiType mapSingolaRigaSintesiFlussoCompleto(
			CblTFlussoSintesi flussoSintesi, CblTFlussoOrigine flussoOrigine, Integer progressivo) {
		it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoSintesiType output = new it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoSintesiType();

		output.setIdFlusso(flussoOrigine.getIdentificativoFlusso());
		output.setCodiceVersamento(flussoSintesi.getCodiceVersamento());
		output.setDescrizioneCodiceVersamento(flussoSintesi.getDescrizioneCodiceVersamento());
		output.setDatiSpecificiDiRiscossione(flussoSintesi.getDatiSpecificiDiRiscossione());
		output.setImportoQuotaAggregazione(flussoSintesi.getImportoQuotaAggregazione());
		output.setNroPagamentiAggregazione(
						flussoSintesi.getNumeroVersQuotaAggregazione () != null
								? flussoSintesi.getNumeroVersQuotaAggregazione().intValue()
								: 0 );
        if ( null != flussoSintesi.getCapitolo () ) {
            output.setCapitolo ( flussoSintesi.getCapitolo () );
        }

        if ( null != flussoSintesi.getArticolo () ) {
            output.setArticolo ( flussoSintesi.getArticolo ().toString () );
        }

		if (null != progressivo) {
			output.setProgressivoFlussoSintetico(progressivo);
		}
		output.setAccertamentoAnno(
				flussoSintesi.getAccertamentoAnno() != null ? flussoSintesi.getAccertamentoAnno () : null);
		output.setAccertamentoNumero(
				flussoSintesi.getAccertamentoNumero() != null ? flussoSintesi.getAccertamentoNumero ()
						: null);

		it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoSintesiType.RigheDettaglio righeDettaglio = new it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoSintesiType.RigheDettaglio();

		List<CblTFlussoDettaglio> flussiDettaglio = getFlussiDettaglioOrdinati(flussoSintesi);
		 Configurazione conf = configurazioneManager.leggi ( flussoOrigine.getCblTEnte ().getIdEnte (), Costanti.ABILITA_INVIO_PAGAMENTI_SCONOSCIUTI );

		for (CblTFlussoDettaglio flussoDettaglio : flussiDettaglio) {
//                if ( !CategoriaIUV.SCONOSCIUT_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
//                    || ( CategoriaIUV.SCONOSCIUT_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
//                        && (conf != null || Boolean.TRUE.equals ( Boolean.valueOf ( conf.getValore () ) ) ) )) {
//                    righeDettaglio.getSingolaRigaDettaglio()
//                    .add(mapSingolaRigaDettaglioFlussoCompleto(flussoDettaglio, flussoSintesi));
//                }
		    
		    if ( CategoriaIUV.INTERM_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
		                    || CategoriaIUV.NON_INTERM_PPAY.name ().equals ( flussoDettaglio.getCblDCategoriaIuv ().getCodice () )
		                    ||  (conf != null && Boolean.TRUE.equals ( Boolean.valueOf ( conf.getValore () ) ) ) ) {
		                    righeDettaglio.getSingolaRigaDettaglio()
		                    .add(mapSingolaRigaDettaglioFlussoCompleto(flussoDettaglio, flussoSintesi));
		                }
		}
		
		output.setRigheDettaglio(righeDettaglio);

		return output;
	}

	private it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoDettaglioType mapSingolaRigaDettaglioFlussoCompleto(
			CblTFlussoDettaglio flussoDettaglio, CblTFlussoSintesi flussoSintesi) {

		it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoDettaglioType output = new it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp.FlussoDettaglioType();

		if (StringUtils.isNotEmpty(flussoDettaglio.getCodicefiscalePagatore())) {
			output.setAnagraficaPagatore(getAnagraficaPagatore(flussoDettaglio));
		}

		output.setDescrizioneCausaleVersamento(flussoSintesi.getDescrizioneCodiceVersamento());
		output.setCausale(flussoDettaglio.getDescrizioneCausaleVersamento());
		output.setDataPagamento(
				ConversionUtils.convertTimestampToXmlGregorianCalendar(flussoDettaglio.getDataPagamento()));
		output.setEsitoPagamento(flussoDettaglio.getEsitoPagamento());
		output.setImportoSingoloVersamento(flussoDettaglio.getImportoSingoloVersamento());
		if (null != flussoDettaglio.getIndiceSingoloVersamento()) {
			output.setIndiceSingoloVersamento(flussoDettaglio.getIndiceSingoloVersamento());
		}
		output.setTransactionid(flussoDettaglio.getTransactionid());
		output.setIdentificativoUnivocoVersamento(flussoDettaglio.getIdentificativoUnicoVersamento());
		output.setIdentificativoUnivocoRiscossione(flussoDettaglio.getIdentificativoUnicoRiscossione());

		return output;
	}

	private SoggettoType getAnagraficaPagatore(CblTFlussoDettaglio source) {
		SoggettoType anagraficaPagatore = new SoggettoType();

		anagraficaPagatore.setIdentificativoUnivocoFiscale(source.getCodicefiscalePagatore());

		if (StringUtils.isNotEmpty(source.getAnagraficaPagatore())) {
			if (source.getCodicefiscalePagatore().toUpperCase().trim()
					.matches("[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]")) {
				PersonaFisicaType personaFisica = new PersonaFisicaType();
				if (null != source.getAnagraficaPagatore()
						&& source.getAnagraficaPagatore().indexOf(SPLIT_CHARACTER) > 0) {
					int splitIndex = source.getAnagraficaPagatore().indexOf(SPLIT_CHARACTER);
					personaFisica.setCognome(source.getAnagraficaPagatore().substring(splitIndex + 1 ));
					personaFisica.setNome(source.getAnagraficaPagatore().substring(0, splitIndex));
				} else {
					personaFisica.setCognome(source.getAnagraficaPagatore());
					personaFisica.setNome(SPLIT_CHARACTER);
				}
				anagraficaPagatore.setPersonaFisica(personaFisica);
			} else {
				PersonaGiuridicaType personaGiuridica = new PersonaGiuridicaType();
				personaGiuridica.setRagioneSociale(source.getAnagraficaPagatore());
				anagraficaPagatore.setPersonaGiuridica(personaGiuridica);
			}
		}
		return anagraficaPagatore;
	}
}
