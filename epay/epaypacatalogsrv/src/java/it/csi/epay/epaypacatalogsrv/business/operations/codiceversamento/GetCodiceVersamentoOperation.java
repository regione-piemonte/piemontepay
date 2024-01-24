/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.EncryptionDecryptionUtil;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Operation ( consumes = GetCodiceVersamentoInput.class, produces = GetCodiceVersamentoOutput.class )
@Component
public class GetCodiceVersamentoOperation implements OperationHandler<GetCodiceVersamentoInput, GetCodiceVersamentoOutput> {


    
    private static final String CONFIG_PROPERTIES = "config.properties";
	@Autowired
	private CodiceVersamentoRepository repository;

	@Autowired
	private Mapper dozerBeanMapper;

	@Autowired
	private ProfilazioneService profilazioneService;

	@Override
	public void authorize ( GetCodiceVersamentoInput input, OperationDispatchingContext<GetCodiceVersamentoInput, GetCodiceVersamentoOutput> context ) {

		SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO );
		SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	public void validateInput ( GetCodiceVersamentoInput input, OperationDispatchingContext<GetCodiceVersamentoInput, GetCodiceVersamentoOutput> context ) {

		if ( input.getId () == null ) {
			throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
		}
		if ( input.getId () < 1L ) {
			throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
		}
	}

	@Override
	@Transactional
	public GetCodiceVersamentoOutput execute ( GetCodiceVersamentoInput input,
		OperationDispatchingContext<GetCodiceVersamentoInput, GetCodiceVersamentoOutput> context ) {

		CodiceVersamento raw = repository.findOne ( input.getId () );
		if ( raw == null || EntityUtils.isTrue ( raw.getFlagAnnullato () ) ) {
			throw new NotFoundException ( Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND );
		}

		filtraVisibilita ( raw );

		GetCodiceVersamentoOutputDto mapped = this.map ( raw );
		return GetCodiceVersamentoOutput.ok ( mapped );
	}

	private void filtraVisibilita ( CodiceVersamento raw ) {

		// l'utente deve avere visibilita' del codice
		SecurityUtils.assertVisibilitaSuCodiceVersamento ( raw );

		if ( raw.getCodiciVersamentoCollegati () != null && raw.getCodiciVersamentoCollegati ().size () > 0 ) {
			PrincipalVO principal = SecurityUtils.getPrincipal ();

			List<String> listaTematicheVisibili = profilazioneService.getCodiciTematicheConVisibilitaTotale ( principal );

			if ( !listaTematicheVisibili.contains ( raw.getVoceEntrata ().getTematica ().getCodice () ) ) {
				// non ho visibilita' totale, filtro i codici collegati per i singoli cv

				List<Long> listaIdCvVisibili = profilazioneService.getIdCodiciVersamentoConVisibilita ( principal );

				List<CodiceVersamento> filtrati = new ArrayList<> ();
				for ( CodiceVersamento originale: raw.getCodiciVersamentoCollegati () ) {
					if ( listaIdCvVisibili.contains ( originale.getId () ) ) {
						filtrati.add ( originale );
					}
				}
				raw.setCodiciVersamentoCollegati ( filtrati );
			}
		}
	}

	private GetCodiceVersamentoOutputDto map ( CodiceVersamento input ) {

		if ( input.getCodiciVersamentoCollegati () != null ) {
			List<CodiceVersamento> filtrati = new ArrayList<> ();
			for ( CodiceVersamento cv: input.getCodiciVersamentoCollegati () ) {
				if ( !EntityUtils.isTrue ( cv.getFlagAnnullato () ) ) {
					filtrati.add ( cv );
				}
			}
			input.setCodiciVersamentoCollegati ( filtrati );
		}

		GetCodiceVersamentoOutputDto output = dozerBeanMapper.map ( input, GetCodiceVersamentoOutputDto.class );

		// interrompo ricorsione infinita
		if ( output.getCodiciVersamentoCollegati () != null ) {
			for ( GetCodiceVersamentoOutputDto o: output.getCodiciVersamentoCollegati () ) {

				GetCodiceVersamentoOutputDto objectStub = new GetCodiceVersamentoOutputDto ();
				objectStub.setId ( output.getId () );
				o.setCodiceVersamentoPadre ( objectStub );
			}
		}

		if ( output.getCodiceVersamentoPadre () != null && output.getCodiceVersamentoPadre ().getCodiciVersamentoCollegati () != null ) {
			List<GetCodiceVersamentoOutputDto> newList = new ArrayList<> ();

			for ( GetCodiceVersamentoOutputDto o: output.getCodiceVersamentoPadre ().getCodiciVersamentoCollegati () ) {
				GetCodiceVersamentoOutputDto objectStub = new GetCodiceVersamentoOutputDto ();
				objectStub.setId ( o.getId () );
				newList.add ( objectStub );
			}

			output.getCodiceVersamentoPadre ().setCodiciVersamentoCollegati ( newList );
		}

		// ordino output
		if ( output.getCodiciVersamentoCollegati () != null ) {
			output.getCodiciVersamentoCollegati ().sort ( new Comparator<GetCodiceVersamentoOutputDto> () {

				@Override
				public int compare ( GetCodiceVersamentoOutputDto o1, GetCodiceVersamentoOutputDto o2 ) {
					return o1.getCodice ().compareTo ( o2.getCodice () );
				}

			} );
		}

		if(input.getModalitaIntegrazione () !=  null) {
			output.setCodiceModalitaIntegrazione ( input.getModalitaIntegrazione ().getCodice () );
			output.setMbModalita ( input.getModalitaIntegrazione ().getDescrizione () );
		}
		if ( !CollectionUtils.isEmpty ( input.getCodiciVersamentoSecondariCollegati () ) ) {
			output.setIdCodiceVersamentoSecondarioCollegato ( input.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getId () );
			output.setIdEnteSecondarioCollegato ( input.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getEnte().getId());
			output.setMbCodiceVersamentoAssociato ( input.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getCodice () + " - "
							+ input.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getDescrizione () );
			output.setMbEnteSecondario ( input.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getEnte ().getDenominazione() );
		}
		
		output.setFlagPersonalizzazioneCov ( input.getFlagPersonalizzazioneCov () );
		output.setDescrizioneTextCov ( input.getDescrizioneTextCov () );
		 if (   SecurityUtils.hasUseCase ( Constants.USE_CASES.ASSISTENZA )   )
		 {
		     Properties properties = null;
		     if (null != input.getPassphrase () ||  null != input.getCredenzialiPnd ())
		     {
		          properties =  getProperties ();
		     }
		     if (null != input.getPassphrase ()) {
		        
		         output.setStrPassphrase ( EncryptionDecryptionUtil.decrypt ( new String ( input.getPassphrase () ), properties.getProperty ( Constants.PASSWORD_PASSPHRASE ) ) );
		     }
		     
		     if (null != input.getCredenzialiPnd ()) {
		         String credenzialiBase64= EncryptionDecryptionUtil.decrypt ( new String ( input.getCredenzialiPnd () ), properties.getProperty ( Constants.PASSWORD_NOTIFICATION_PRICE) ) ;
                 output.setStrCredenzialiPnd (new String(Base64.getDecoder ().decode ( credenzialiBase64.getBytes () )) );
             }
		 }
		
		 output.setDtInizioValidita  (input.getDataInizioValidita ()!=null? new Timestamp ( input.getDataInizioValidita ().getTime () ):null );
	      output.setDtFineValidita (input.getDataFineValidita ()!=null? new Timestamp ( input.getDataFineValidita ().getTime () ):null );

		return output;
	}
	
	  private Properties getProperties () {
	        Properties properties;
	        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
	            properties = new Properties();
	            properties.load(inputStream);
	            
	        } catch (IOException e) {
	            throw new RuntimeException("Errore lettura parametri: " + e.getMessage());
	        }
	        return properties;
	    }

}
