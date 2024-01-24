/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.tassonomia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.UpdateTassonomieInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.UpdateTassonomieItemInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.UpdateTassonomieOutput;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.TassonomiaRepository;


@Operation ( consumes = UpdateTassonomieInput.class, produces = UpdateTassonomieOutput.class )
@Component
@Transactional
public class UpdateTassonomieOperation implements OperationHandler<UpdateTassonomieInput, UpdateTassonomieOutput> {

    @Autowired
    private TassonomiaRepository tassonomiaRepository;

    private static final int MAX_FIELD_LENGTH = 499;

    @Override
    public UpdateTassonomieOutput execute ( UpdateTassonomieInput input, OperationDispatchingContext<UpdateTassonomieInput, UpdateTassonomieOutput> context ) {
        List<UpdateTassonomieItemInput> fromBatchList = input.getTassonomie ();

        // NON USIAMO LAMBDA O ALTRE ROBE DI JAVA O SI SPACCA TUTTO
        for ( Tassonomia fromDB: tassonomiaRepository.findAll () ) {
            if ( fromBatchList.contains ( fromDB ) ) {
                UpdateTassonomieItemInput fromBatch = fromBatchList.get ( fromBatchList.indexOf ( fromDB ) ); // delete o update
                if ( fromBatch.getToBeDelete () || fromBatch.getToBeUpdate () ) {
                    Tassonomia t = map ( fromBatch, fromDB );
                    tassonomiaRepository.save ( t );
                }
            }
        }

        for ( UpdateTassonomieItemInput fromBatch: fromBatchList ) {
            if ( Boolean.TRUE.equals ( fromBatch.getToBeInsert () ) ) {
                Tassonomia t = map ( fromBatch, null );
                tassonomiaRepository.save ( t );
            }
        }

        tassonomiaRepository.flush ();

        return UpdateTassonomieOutput.ok ();
    }

    private Tassonomia map ( UpdateTassonomieItemInput ti, Tassonomia to ) {
        if ( null == to ) {
            to = new Tassonomia ();
            to.setDataInserimento ( new Timestamp ( new Date ().getTime () ) );
        } else {
            to.setDataModifica ( new Timestamp ( new Date ().getTime () ) );
        }
        if ( null != ti.getCodTipoEnteCreditore () ) {
            to.setCodTipoEnteCreditore ( ti.getCodTipoEnteCreditore () );
        }
        if ( null != ti.getTipoEnteCreditore () ) {
            to.setTipoEnteCreditore ( ti.getTipoEnteCreditore () );
        }
        if ( null != ti.getMacroArea () ) {
            to.setMacroArea ( ti.getMacroArea () );
        }
        if ( null != ti.getNomeMacroArea () ) {
            to.setNomeMacroArea ( ti.getNomeMacroArea () );
        }
        if ( null != ti.getDescrMacroArea () ) {
            to.setDescrMacroArea ( ti.getDescrMacroArea () );
        }
        if ( null != ti.getCodTipologiaServizio () ) {
            to.setCodTipologiaServizio ( ti.getCodTipologiaServizio () );
        }
        if ( null != ti.getTipoServizio () ) {
            to.setTipoServizio ( ti.getTipoServizio () );
        }
        if ( null != ti.getMotivoGiuridicoRiscossione () ) {
            to.setMotivoGiuridicoRiscossione ( ti.getMotivoGiuridicoRiscossione () );
        }
        if ( null != ti.getDescrTipoServizio () ) {
            String desc = ti.getDescrTipoServizio ();
            desc = desc.substring ( 0, Math.min ( desc.length (), MAX_FIELD_LENGTH ) );
            to.setDescrTipoServizio ( desc );
        }
        if ( null != ti.getnVersioneTassonomia () ) {
            to.setnVersioneTassonomia ( ti.getnVersioneTassonomia () );
        }
        if ( null != ti.getDatiSpecificiIncasso () ) {
            to.setDatiSpecificiIncasso ( ti.getDatiSpecificiIncasso () );
        }
        if ( null != ti.getDataInizioValidita () ) {
            to.setDataInizioValidita ( ti.getDataInizioValidita () );
        }
        if ( null != ti.getDataFineValidita () ) {
            to.setDataFineValidita ( ti.getDataFineValidita () );
        }
        if ( null != ti.getFlagAggiornato () ) {
            to.setFlagAggiornato ( ti.getFlagAggiornato () );
        }
        if ( null != ti.getFlagCancellato () ) {
            to.setFlagCancellato ( ti.getFlagCancellato () );
        }
        if ( null != ti.getDataCancellazione () ) {
            to.setDataCancellazione ( ti.getDataCancellazione () );
        }
        if ( null != ti.getUtenteInserimento () ) {
            to.setUtenteInserimento ( ti.getUtenteInserimento () );
        }
        if ( null != ti.getUtenteModifica () ) {
            to.setUtenteModifica ( ti.getUtenteModifica () );
        }
        return to;
    }

}
