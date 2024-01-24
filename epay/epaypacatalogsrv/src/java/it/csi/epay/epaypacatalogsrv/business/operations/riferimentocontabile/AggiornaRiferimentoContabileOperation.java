/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentoContabileService;
import it.csi.epay.epaypacatalogsrv.business.service.impl.InvioMailServiceImpl;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;


@Operation ( consumes = AggiornaRiferimentoContabileInput.class, produces = AggiornaRiferimentoContabileOutput.class )
@Component
public class AggiornaRiferimentoContabileOperation implements OperationHandler<AggiornaRiferimentoContabileInput, AggiornaRiferimentoContabileOutput> {

    @Autowired
    private RiferimentoContabileRepository repository;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private RiferimentoContabileService riferimentoContabileService;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Override
    public void preValidateInput ( AggiornaRiferimentoContabileInput input,
        OperationDispatchingContext<AggiornaRiferimentoContabileInput, AggiornaRiferimentoContabileOutput> context ) {

        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( AggiornaRiferimentoContabileInput input,
        OperationDispatchingContext<AggiornaRiferimentoContabileInput, AggiornaRiferimentoContabileOutput> context ) {
        SecurityUtils.assertUseCase ( Constants.USE_CASES.MODIFICA_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public void validateInput ( AggiornaRiferimentoContabileInput input,
        OperationDispatchingContext<AggiornaRiferimentoContabileInput, AggiornaRiferimentoContabileOutput> context ) {

        RiferimentoContabile entity = repository.findOne ( input.getId () );
        if ( entity == null ) {
            throw new NotFoundException ( Constants.MESSAGES.NOT_FOUND );
        }

        if ( EntityUtils.isTrue ( entity.getFlagAnnullato () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );
        }

        if ( !EntityUtils.isRiferimentoInVita ( entity ) ) {
            throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );
        }

		if ( input.getDataFineValidita() == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "data fine validita'" );
        }

        if ( input.getDataFineValidita () != null ) {

            if ( input.getDataFineValidita ().before ( entity.getDataInizioValidita () ) ) {
                throw new BadRequestException (
                    Constants.MESSAGES.RIFERIMENTO_CONTABILE_DATA_FINE_DATA_INIZIO );
            }

            LocalDate today = LocalDate.now ();
            LocalDate ldFineValidita = input.getDataFineValidita ().toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
            if ( ldFineValidita.isBefore ( today ) ) {
                throw new BadRequestException (
                    Constants.MESSAGES.RIFERIMENTO_CONTABILE_DATA_FINE_FUTURA );
            }
        }

    }

    @Override
    @Transactional
    public AggiornaRiferimentoContabileOutput execute ( AggiornaRiferimentoContabileInput input,
        OperationDispatchingContext<AggiornaRiferimentoContabileInput, AggiornaRiferimentoContabileOutput> context ) {

        RiferimentoContabile corrente = repository.findOne ( input.getId () );

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( corrente.getCodiceVersamento () );

        riferimentoContabileService.salvaVoceStoricoPerModifica ( corrente );

        EsitoPropagazioneDTO risultatoPropagazione = null;
        RiferimentoContabile aggiornato = mapPerAggiornamentoInPlace ( corrente, input );
        
      //RDI-41 Si ripete il controllo che dovrebbe essere anche effettuato lato web per maggiore 
        
        VerificaPresenzaRiferimentiContabiliInput verificaInput = new VerificaPresenzaRiferimentiContabiliInput();
        verificaInput.setAnnoEsercizio(aggiornato.getAnnoEsercizio());
        verificaInput.setIdCodiceVersamento(aggiornato.getCodiceVersamento ().getId ());
        verificaInput.setGenerico(null== input.getAnnoAccertamento());
        verificaInput.setIdRiferimentoContabile ( aggiornato.getId () );
        verificaInput.setDataFineValidita ( aggiornato.getDataFineValidita () );
        verificaInput.setDataInizioValidita ( aggiornato.getDataInizioValidita () );
        verificaInput.setCaller ( input.getCaller () );
        
        VerificaPresenzaRiferimentiContabiliOutput verificaOutput = riferimentoContabileService.verificaPresenzaRiferimentiContabili(verificaInput);

		if ( !StringUtils.isEmpty ( verificaOutput.getMessaggioErrore () ) ) {
			throw new BadRequestException ( !StringUtils.isEmpty ( verificaOutput.getMessaggioErrore () ) ?
							verificaOutput.getMessaggioErrore () : verificaOutput.getDescrizioneEsito () );
		}

        EntityUtils.populateEditFields ( aggiornato );
        corrente.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        corrente.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );
        repository.save ( corrente );

        risultatoPropagazione = propagazioneService.propagaRiferimentoContabile (
            corrente,
            AzioneDaPropagare.MODIFICA );

        if ( risultatoPropagazione.getEsito () != EsitoPropagazione.OK && risultatoPropagazione.getEsito () != EsitoPropagazione.NONE ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        corrente.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        corrente.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );
        repository.save ( corrente );

        // Inserito controllo sullo stato per evitare spam alla casella di posta del servizio.
        if ( risultatoPropagazione.getEsito () != EsitoPropagazione.OK ) {
            inviaMail ( corrente );
        }
        
        return AggiornaRiferimentoContabileOutput.ok ();
    }

    private void inviaMail ( RiferimentoContabile entity ) {
        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "stato_aggiornamento", entity.getDescrizioneEsitoAggiornamento () );

        parametri.put ( "azione_oggetto", "Modifica" );
        parametri.put ( "azione", "modificato" );
        parametri.put ( "descrizione_ente", entity.getCodiceVersamento ().getEnte ().getDenominazione () );
        parametri.put ( "descrizione_codice_versamento", entity.getCodiceVersamento ().getCodice () + " - " + entity.getCodiceVersamento ().getDescrizione () );
        parametri.put ( "data_inizio_validita", new SimpleDateFormat ( "dd/MM/yyyy" ).format ( entity.getDataInizioValidita () ) );

        invioMailService.inviaMail ( EmailEnum.RIFERIMENTO_CONTABILE,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            entity.getCodiceVersamento ().getEnte ().getId (),
            parametri );
    }

	public RiferimentoContabile mapPerAggiornamentoInPlace ( RiferimentoContabile corrente, AggiornaRiferimentoContabileInput input ) {
		corrente.setDataFineValidita ( input.getDataFineValidita () );
		corrente.setNumeroCapitolo ( input.getNumeroCapitolo () );
		corrente.setNumeroArticolo ( input.getNumeroArticolo () );
		corrente.setLivelloPdc ( input.getLivelloPdc () );
		corrente.setTitolo ( input.getTitolo () );
		corrente.setTipologia ( input.getTipologia () );
		corrente.setCategoria ( input.getCategoria () );
		corrente.setCodiceTributo ( input.getCodiceTributo () );
		return corrente;
	}

}
