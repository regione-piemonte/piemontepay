/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.voceentrata;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileOutput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileOutputDto;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Operation ( consumes = RicercaVoceEntrataDisponibileInput.class, produces = RicercaVoceEntrataDisponibileOutput.class )
@Component
public class RicercaVoceEntrataDisponibileOperation implements OperationHandler<RicercaVoceEntrataDisponibileInput, RicercaVoceEntrataDisponibileOutput> {

    @Autowired
    private VoceEntrataRepository enteRepository;

    @Autowired
    private ProfilazioneService profilazioneService;

    @Autowired
    private Mapper dozerBeanMapper;

	@Override
    public void authorize ( RicercaVoceEntrataDisponibileInput input,
        OperationDispatchingContext<RicercaVoceEntrataDisponibileInput, RicercaVoceEntrataDisponibileOutput> context ) {
        SecurityUtils.assertAnyUseCase (
            Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO,
            Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	@Transactional
    public RicercaVoceEntrataDisponibileOutput execute ( RicercaVoceEntrataDisponibileInput input,
        OperationDispatchingContext<RicercaVoceEntrataDisponibileInput, RicercaVoceEntrataDisponibileOutput> context ) {

        List<VoceEntrata> records = enteRepository.ricercaDisponibili ( input, SecurityUtils.getCurrentIdEnte () );

        ArrayList<RicercaVoceEntrataDisponibileOutputDto> risultati = new ArrayList<> ();

        // l'utente deve avere visibilita' totale sulla tematica
        PrincipalVO principal = SecurityUtils.getPrincipal ();
        List<String> codiciTematicheVisibili = profilazioneService.getCodiciTematicheConVisibilitaTotale ( principal );

        for ( VoceEntrata record: records ) {
            if ( codiciTematicheVisibili.contains ( record.getTematica ().getCodice () ) ) {
                risultati.add ( map ( record ) );
            }
        }

        return RicercaVoceEntrataDisponibileOutput.ok ( risultati );
	}

    private RicercaVoceEntrataDisponibileOutputDto map ( VoceEntrata input ) {
        RicercaVoceEntrataDisponibileOutputDto output = dozerBeanMapper.map ( input, RicercaVoceEntrataDisponibileOutputDto.class );
		return output;
	}

}
