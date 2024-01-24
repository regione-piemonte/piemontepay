/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.config.TestConstants;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class EliminaCodiceVersamentoCollegatoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    private EliminaCodiceVersamentoOutput call ( EliminaCodiceVersamentoInput input ) {
        try {
            return getPort ().eliminaCodiceVersamento ( input );
        } catch ( RuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Before
    public void eliminaDipendenze () {
        riferimentoContabileRepository.deleteAll ();
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        EliminaCodiceVersamentoInput input = new EliminaCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setId ( TestConstants.ID_CV_COLLEGATO );

        EliminaCodiceVersamentoOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

    }

    @Test
    public void shouldSetFlagAnnullato () {
        // cancello le assoc utente - cv
        associazioneUtenteCodiceVersamentoRepository.deleteAll ();

        // aggiungo le assoc utente - tematica con flag visibilita' totale a True
        for ( AssociazioneUtenteTematica assoc: associazioneUtenteTematicaRepository.findAll () ) {
            if ( !EntityUtils.isTrue ( assoc.getFlagVisibilitaTotale () ) ) {
                assoc.setFlagVisibilitaTotale ( true );
                associazioneUtenteTematicaRepository.save ( assoc );
            }
        }

        EliminaCodiceVersamentoInput i = new EliminaCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( CodiceVersamento dto: repo.findAll () ) {
            if ( dto.getCodiceVersamentoPadre () == null ) {
                continue;
            }

            assertNotNull ( dto.getId () );
            i.setId ( dto.getId () );

            EliminaCodiceVersamentoOutput output = call ( i );

            assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

            assertEquals ( true, repo.findOne ( dto.getId () ).getFlagAnnullato () );
        }

    }

    @Test
    public void shouldNotDeleteCodiciPadre () {
        // cancello le assoc utente - cv
        associazioneUtenteCodiceVersamentoRepository.deleteAll ();

        // aggiungo le assoc utente - tematica con flag visibilita' totale a True
        for ( AssociazioneUtenteTematica assoc: associazioneUtenteTematicaRepository.findAll () ) {
            if ( !EntityUtils.isTrue ( assoc.getFlagVisibilitaTotale () ) ) {
                assoc.setFlagVisibilitaTotale ( true );
                associazioneUtenteTematicaRepository.save ( assoc );
            }
        }
        EliminaCodiceVersamentoInput i = new EliminaCodiceVersamentoInput ();
        i.setCaller ( TestHelper.getCallerAdmin () );

        assertTrue ( repo.count () > 0 );

        for ( CodiceVersamento dto: repo.findAll () ) {
            if ( dto.getCodiceVersamentoPadre () == null ) {
                continue;
            }

            assertNotNull ( dto.getId () );
            i.setId ( dto.getId () );

            EliminaCodiceVersamentoOutput output = call ( i );

            assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

            assertTrue ( repo.findOne ( dto.getCodiceVersamentoPadre ().getId () ).getFlagAnnullato () == null );
            assertEquals ( true, repo.findOne ( dto.getId () ).getFlagAnnullato () );
        }

        assertTrue ( repo.count () > 0 );
    }
}
