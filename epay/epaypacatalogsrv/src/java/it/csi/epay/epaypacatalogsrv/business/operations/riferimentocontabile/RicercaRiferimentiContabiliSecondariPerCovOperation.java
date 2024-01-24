/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentiContabiliSecondariPerCovInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentiContabiliSecondariPerCovOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Operation ( consumes = RicercaRiferimentiContabiliSecondariPerCovInput.class, produces = RicercaRiferimentiContabiliSecondariPerCovOutput.class )
@Component
public class RicercaRiferimentiContabiliSecondariPerCovOperation implements  OperationHandler< RicercaRiferimentiContabiliSecondariPerCovInput, RicercaRiferimentiContabiliSecondariPerCovOutput> {

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;
    
    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;
    
    @Autowired
    private Mapper dozerBeanMapper;


    @Override
    public void authorize ( RicercaRiferimentiContabiliSecondariPerCovInput input,
        OperationDispatchingContext<RicercaRiferimentiContabiliSecondariPerCovInput, RicercaRiferimentiContabiliSecondariPerCovOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    public void validateInput ( RicercaRiferimentiContabiliSecondariPerCovInput input,
        OperationDispatchingContext<RicercaRiferimentiContabiliSecondariPerCovInput, RicercaRiferimentiContabiliSecondariPerCovOutput> context ) {
        if ( null ==  input || null == input.getIdCodiceVersamento()) {
            throw new BadRequestException ();
        }
    }

  

    @Override
    @Transactional
    public RicercaRiferimentiContabiliSecondariPerCovOutput execute ( RicercaRiferimentiContabiliSecondariPerCovInput input,
        OperationDispatchingContext<RicercaRiferimentiContabiliSecondariPerCovInput, RicercaRiferimentiContabiliSecondariPerCovOutput> context ) {

		List<RiferimentoContabile> records = riferimentoContabileRepository.ricercaRiferimentiContabiliSecondariPerCov(
        		input.getIdCodiceVersamento());


        ArrayList<RicercaRiferimentoContabileOutputDto> risultati = new ArrayList<> ();

        for ( RiferimentoContabile record: records ) {
            risultati.add ( map ( record ) );
        }


        if ( risultati != null ) {
            risultati.sort ( new Comparator<RicercaRiferimentoContabileOutputDto> () {

                @Override
                public int compare ( RicercaRiferimentoContabileOutputDto o1, RicercaRiferimentoContabileOutputDto o2 ) {
                    String v1 = o1.getCodiceTipologiaDatoSpecificoRiscossione () + "-" + o1.getDatoSpecificoRiscossione () + "-"
                        + o1.getDescrizioneDatoSpecificoRiscossione () + "-" + o1.getId ();
                    String v2 = o2.getCodiceCodiceVersamento () + "-" + o2.getDescrizioneCodiceVersamento () + "-"
                        + o2.getCodiceTipologiaDatoSpecificoRiscossione () + "-" + o2.getDatoSpecificoRiscossione () + "-"
                        + o2.getDescrizioneDatoSpecificoRiscossione () + "-" + o2.getId ();
                    return v1.compareTo ( v2 );
                }

            } );
        }

          return RicercaRiferimentiContabiliSecondariPerCovOutput.ok ( risultati );
    }

       private RicercaRiferimentoContabileOutputDto map ( RiferimentoContabile input ) {

		   return dozerBeanMapper.map ( input, RicercaRiferimentoContabileOutputDto.class );

    }
}
