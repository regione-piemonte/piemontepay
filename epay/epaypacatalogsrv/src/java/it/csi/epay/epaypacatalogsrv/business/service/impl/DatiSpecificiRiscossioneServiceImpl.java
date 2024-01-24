/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.api.util.CodiciEsito;
import it.csi.epay.epaypacatalogsrv.business.service.DatiSpecificiRiscossioneService;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.ComponentiAccertamento;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossione;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossione;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossioneOutput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamentoLight;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.EnteLight;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoLightRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteLightRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;

@Service
@Transactional ( readOnly = true )
public class DatiSpecificiRiscossioneServiceImpl  implements DatiSpecificiRiscossioneService{

	private static final Logger LOG = LogManager.getLogger(DatiSpecificiRiscossioneService.class);

	@Autowired
	private RiferimentoContabileRepository riferimentoContabileRepository;
	@Autowired
	private EnteRepository enteRepository;
	@Autowired
	private EnteLightRepository enteLightRepository;
	@Autowired
	private CodiceVersamentoRepository codiceVersamentoRepository;
	@Autowired
	private CodiceVersamentoLightRepository codiceVersamentoLightRepository;

	@Override
	@Deprecated
	public DatoSpecificoRiscossioneOutput getDatoSpecificoRiscossione ( DatoSpecificoRiscossioneInput input ) {
		//struttrure di output da popolare
		DatoSpecificoRiscossioneOutput dsrOutput = new DatoSpecificoRiscossioneOutput ();
		List<DatoSpecificoRiscossione> elencoDatiSpecifici = new LinkedList<> ();
		try {
			Assert.notNull(input, "Valorizzare input del servizio"  );
			Assert.notNull (  input.getCodiceFiscaleEnte (), "Valorizzare codice fiscale ente" );
			Assert.notNull ( input.getTipoPagamento (), "Valorizzare codice versamento"  );

		} catch ( Exception e ) {
			dsrOutput.setCodiceEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getCodice () );
			dsrOutput.setDescrizioneEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getMessaggio () );
			return dsrOutput;
		}
		List<ComponentiAccertamento> listaComponenti = input.getComponentiAccertamento ();
		dsrOutput.setCodiceFiscaleEnte ( input.getCodiceFiscaleEnte () );
		dsrOutput.setTipoPagamento ( input.getTipoPagamento () );

		CodiciEsito ce = CodiciEsito.ESITO_OK;
		dsrOutput.setCodiceEsito ( ce.getCodice () );

		try {
			Ente ente = enteRepository.findOneByCodiceFiscale ( input.getCodiceFiscaleEnte () );
			if ( null == ente ) {
				dsrOutput.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getCodice () );
				dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getMessaggio (), input.getCodiceFiscaleEnte () ) );
				return dsrOutput;
			}

			CodiceVersamento codiceVersamento = codiceVersamentoRepository.findByCodiceAndIdEnte ( ente.getId (), input.getTipoPagamento () );
			if ( null == codiceVersamento ) {
				dsrOutput.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_COV_NON_TROVATO.getCodice () );
				dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getMessaggio (), input.getTipoPagamento () ) );
				return dsrOutput;
			}

			boolean isComponentePresente = !CollectionUtils.isEmpty ( listaComponenti );
			Integer annoCorrente = Calendar.getInstance ().get ( Calendar.YEAR );
			if ( isComponentePresente ) {
				for ( ComponentiAccertamento comp: listaComponenti ) {
					Integer numeroAccertamento = null;
					if ( null != comp.getNumeroAccertamento () ) {
						numeroAccertamento = Integer.parseInt ( comp.getNumeroAccertamento () );
					}
					Integer annoAccertamento = comp.getAnnoAccertamento ();
					boolean annoENumeroAccValorizzati = null != numeroAccertamento && null != annoAccertamento;
					if ( annoENumeroAccValorizzati ) {
						List<RiferimentoContabile> records
						= riferimentoContabileRepository.ricercaDatoSpecificoRiscossione ( codiceVersamento.getId (), ente.getId (),
							numeroAccertamento, annoAccertamento, annoCorrente );
						if ( records.size () == 1 ) {
							RiferimentoContabile rc = records.get ( 0 );
							dsrOutput.setDescrizioneEsito ( ce.getMessaggio () );
							DatoSpecificoRiscossione dsr = new DatoSpecificoRiscossione ();
							dsr.setCodice ( rc.getCodiceTipologiaDatoSpecificoRiscossione () + "/" + rc.getDatoSpecificoRiscossione () );
							dsr.setDescrizione ( rc.getTassonomia () != null ? rc.getTassonomia ().getDescrTipoServizio () : "" );
							dsr.setComponentiAccertamento ( comp );
							elencoDatiSpecifici.add ( dsr );
						} else {
			                /* DEV/CSI_PAG-2416 - BEGIN1 */
							if ( records.size () > 1 ) {
								ce = CodiciEsito.ERRORE_DATI_NON_UNIVOCI;
								dsrOutput.setDescrizioneEsito ( String.format ( ce.getMessaggio (), ( input.getTipoPagamento () +
												" - CF Ente: " + input.getCodiceFiscaleEnte () +
												" - Anno Accertamento: " + annoAccertamento +
												" - Numero Accertamento: " + numeroAccertamento ) ) );
							} else {
								ce = CodiciEsito.ERRORE_DATI_NON_PRESENTI;
								dsrOutput.setDescrizioneEsito ( String.format ( ce.getMessaggio (), ente.getDenominazione () + " / " + codiceVersamento.getDescrizione () ) );
							}
							dsrOutput.setCodiceEsito ( ce.getCodice () );
							/* DEV/CSI_PAG-2416 - END1 */
							elencoDatiSpecifici.clear ();
							break;
						}
					} else {
						List<RiferimentoContabile> records
						= riferimentoContabileRepository.ricercaDatiSpecificiRiscossioneEmptyAcc ( codiceVersamento.getId (), ente.getId (), annoCorrente );
						if ( records.size () == 1 ) {
							RiferimentoContabile rc = records.get ( 0 );
							dsrOutput.setDescrizioneEsito ( ce.getMessaggio () );
							DatoSpecificoRiscossione dsr = new DatoSpecificoRiscossione ();
							dsr.setCodice ( rc.getCodiceTipologiaDatoSpecificoRiscossione () + "/" + rc.getDatoSpecificoRiscossione () );
							dsr.setDescrizione ( rc.getTassonomia () != null ? rc.getTassonomia ().getDescrTipoServizio () : "" );
							dsr.setComponentiAccertamento ( comp );
							elencoDatiSpecifici.add ( dsr );
						} else {
							ce = records.size () > 1 ? CodiciEsito.ERRORE_DATI_NON_UNIVOCI : CodiciEsito.ERRORE_DATI_NON_PRESENTI;
							dsrOutput.setCodiceEsito ( ce.getCodice () );
							dsrOutput.setDescrizioneEsito ( String.format ( ce.getMessaggio (), ( input.getTipoPagamento () +
											" - CF Ente: " + input.getCodiceFiscaleEnte () +
											" - Anno Accertamento: " + annoAccertamento +
											" - Numero Accertamento: " + numeroAccertamento ) ) );
							elencoDatiSpecifici.clear ();
							break;
						}
					}
				}
			} else {
				List<RiferimentoContabile> records
				= riferimentoContabileRepository.ricercaDatiSpecificiRiscossioneAnyAcc ( codiceVersamento.getId (), ente.getId (), annoCorrente );
				if ( records.size () == 1 ) {
					ComponentiAccertamento comp = new ComponentiAccertamento ();
					RiferimentoContabile rc = records.get ( 0 );
					comp.setAnnoAccertamento ( rc.getAnnoAccertamento () );
					comp.setAnnoEsercizio ( rc.getAnnoEsercizio () );
					comp.setNumeroAccertamento ( rc.getNumeroAccertamento ().toString () );
					dsrOutput.setDescrizioneEsito ( ce.getMessaggio () );
					DatoSpecificoRiscossione dsr = new DatoSpecificoRiscossione ();
					dsr.setCodice ( rc.getCodiceTipologiaDatoSpecificoRiscossione () + "/" + rc.getDatoSpecificoRiscossione () );
					dsr.setDescrizione ( rc.getTassonomia () != null ? rc.getTassonomia ().getDescrTipoServizio () : "" );
					dsr.setComponentiAccertamento ( comp );
					elencoDatiSpecifici.add ( dsr );
				} else {
					ce = records.size () > 1 ? CodiciEsito.ERRORE_DATI_NON_UNIVOCI : CodiciEsito.ERRORE_DATI_NON_PRESENTI;
					dsrOutput.setCodiceEsito ( ce.getCodice () );
					dsrOutput.setDescrizioneEsito ( String.format ( ce.getMessaggio (), ( input.getTipoPagamento () +
									" - CF Ente: " + input.getCodiceFiscaleEnte () ) ) );
					elencoDatiSpecifici.clear ();
				}
			}

			dsrOutput.setElencoDatiSpecifici ( elencoDatiSpecifici );

		} catch ( Exception e ) {
			LOG.error (
				String.format ( CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getMessaggio (), input.getCodiceFiscaleEnte () + " - " + input.getTipoPagamento () ),
				e );
			dsrOutput.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getCodice () );
			dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getMessaggio (),
				input.getCodiceFiscaleEnte () + " - " + input.getTipoPagamento () ) );
		}
		return dsrOutput;
	}
	
	@Override
	public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione ( DatiSpecificiRiscossioneInput input ) {

	    DatiSpecificiRiscossioneOutput dsrOutput = new DatiSpecificiRiscossioneOutput ();

	    List<DatiSpecificiRiscossione> elencoDatiSpecifici = new LinkedList<> ();

	    try {
	        Assert.notNull(input, "Valorizzare input del servizio"  );
	        Assert.notNull (  input.getCodiceFiscaleEnte (), "Valorizzare codice fiscale ente" );
	        Assert.notNull ( input.getTipoPagamento (), "Valorizzare codice versamento"  );
	        if (null!= input.getAnnoEsercizio ())
	        {
	            Assert.isInstanceOf ( Integer.class, input.getAnnoEsercizio () );

	        }
	       
	    } catch ( Exception e ) {
	        dsrOutput.setCodiceEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getCodice () );
	        dsrOutput.setDescrizioneEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getMessaggio () );
	        return dsrOutput;
	    }
	    dsrOutput.setCodiceFiscaleEnte ( input.getCodiceFiscaleEnte () );
	    dsrOutput.setTipoPagamento ( input.getTipoPagamento () );

	    CodiciEsito ce = CodiciEsito.ESITO_OK;
	    dsrOutput.setCodiceEsito ( ce.getCodice () );

	    try
	    {
//	        Integer annoCorrente = Calendar.getInstance ().get ( Calendar.YEAR );
	        EnteLight ente = enteLightRepository.findOneByCodiceFiscale ( input.getCodiceFiscaleEnte () );
	        if ( null == ente ) {
	            dsrOutput.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getCodice () );
	            dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getMessaggio (), input.getCodiceFiscaleEnte () ) );
	            return dsrOutput;
	        }

	        CodiceVersamentoLight codiceVersamento = codiceVersamentoLightRepository.findByCodiceAndIdEnte ( ente.getId (), input.getTipoPagamento () );
	        if ( null == codiceVersamento ) {
	            dsrOutput.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_COV_NON_TROVATO.getCodice () );
	            dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.CODICE_ERRORE_ENTE_NON_TROVATO.getMessaggio (), input.getTipoPagamento () ) );
	            return dsrOutput;
	        }

	        List<RiferimentoContabile> records
	        = riferimentoContabileRepository.ricercaDatiSpecificiRiscossione ( codiceVersamento.getId (), ente.getId () ,input.getAnnoEsercizio () );
	        
	        if (CollectionUtils.isEmpty ( records ))
	        {
	            LOG.error (
	                String.format ( CodiciEsito.ERRORE_DATI_NON_PRESENTI.getMessaggio (), input.getCodiceFiscaleEnte () + " - " + input.getTipoPagamento () ));
	            
	            ce = CodiciEsito.ERRORE_DATI_NON_PRESENTI;
                dsrOutput.setCodiceEsito ( ce.getCodice () );
                /* DEV/CSI_PAG-2416 - BEGIN2 */
                dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.ERRORE_DATI_NON_PRESENTI.getMessaggio (), ente.getDenominazione () + " / " + codiceVersamento.getDescrizione () ) );
                /* DEV/CSI_PAG-2416 - END2 */
                return dsrOutput;
	        }
	        if (findDuplicates ( records ))
	        {
	            LOG.error (
                    String.format ( CodiciEsito.ERRORE_DATI_NON_UNIVOCI.getMessaggio (), input.getCodiceFiscaleEnte () + " - " + input.getTipoPagamento () ));
                
                ce = CodiciEsito.ERRORE_DATI_NON_UNIVOCI;
                dsrOutput.setCodiceEsito ( ce.getCodice () );
                dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.ERRORE_DATI_NON_UNIVOCI.getMessaggio (), input.getTipoPagamento () ) );
                return dsrOutput;
	        }

	        for (RiferimentoContabile rc: records)
	        {
                dsrOutput.setDescrizioneEsito ( ce.getMessaggio () );
                DatiSpecificiRiscossione dsr = new DatiSpecificiRiscossione ();
                dsr.setCodice ( rc.getCodiceTipologiaDatoSpecificoRiscossione () + "/" + rc.getDatoSpecificoRiscossione () );
                dsr.setDescrizione ( rc.getTassonomia () != null ? rc.getTassonomia ().getDescrTipoServizio () : "" );
                dsr.setAnnoAccertamento ( rc.getAnnoAccertamento () );
                dsr.setNumeroAccertamento ( null != rc.getNumeroAccertamento () ? rc.getNumeroAccertamento ().toString (): null);
                dsr.setAnnoEsercizio ( rc.getAnnoEsercizio () );
                dsr.setCodiceTributo ( rc.getCodiceTributo () );
                elencoDatiSpecifici.add ( dsr );
	            
	        }
	        dsrOutput.setElencoDatiSpecifici ( elencoDatiSpecifici );
	    }
	    catch ( Exception e ) {
	        LOG.error (
	            String.format ( CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getMessaggio (), input.getCodiceFiscaleEnte () + " - " + input.getTipoPagamento () ),
	            e );
	        dsrOutput.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getCodice () );
	        dsrOutput.setDescrizioneEsito ( String.format ( CodiciEsito.CODICE_ERRORE_REPERIMENTO_DSR.getMessaggio (),
	            input.getCodiceFiscaleEnte () + " - " + input.getTipoPagamento () ) );
	    }
	    return dsrOutput;

	}
	
	boolean findDuplicates(List<RiferimentoContabile> list) { 
	    int i, j; 
	    for( i = 0; i < list.size(); i++) { 
	        for(j = 0; j < list.size(); j++) { 
	            if(i != j) { 
	                RiferimentoContabile rif1 = list.get(i); 
	                RiferimentoContabile rif2 = list.get(j); 
	                if ((null == rif1.getAnnoAccertamento ()
	                                && null == rif1.getNumeroAccertamento  () )
	                                && 
	                                (null == rif2.getAnnoAccertamento ()
	                                && null == rif2.getNumeroAccertamento  () )  ){ 
	                    return true; 
	                } 
	                if ((null != rif1.getAnnoAccertamento ()
	                                && null != rif1.getNumeroAccertamento  () )
	                                && 
	                                (rif1.getAnnoAccertamento ().equals ( rif2.getAnnoAccertamento () )
	                                 && rif1.getNumeroAccertamento  ().equals ( rif2.getNumeroAccertamento  ()  )  )
	                    ){
	                    return true; 
	                }
	            } 
	        } 
	    } 
	    return false;
	} 
	
	


}
