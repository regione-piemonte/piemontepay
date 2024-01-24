/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.operations.codiceversamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.test.TestHelper;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;
import it.csi.epay.epaypacatalogsrv.test.model.ParentOperationTest;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
@Transactional
public class GetOpzioniCodiceVersamentoOperationTest extends ParentOperationTest {

    @Autowired
    private CodiceVersamentoRepository repo;

    private GetOpzioniCodiceVersamentoInput getInput () {
        GetOpzioniCodiceVersamentoInput input = new GetOpzioniCodiceVersamentoInput ();
        input.setCaller ( TestHelper.getCallerAdmin () );
        input.setSoloBase ( false );
        return input;
    }

    private GetOpzioniCodiceVersamentoOutput call ( GetOpzioniCodiceVersamentoInput input ) {

        try {
            return getPort ().getOpzioniCodiceVersamento ( input );
        } catch ( RuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

    @Test
    public void shouldReturnNotAllowedWithoutCaller () {

        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.setCaller ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutPrincipal () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.getCaller ().setPrincipal ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceFiscale () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceFiscale ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithoutCodiceEnte () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceEnte ( null );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceFiscale () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceFiscale ( "INVALID" );
        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnNotAllowedWithInvalidCodiceEnte () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.getCaller ().getPrincipal ().setCodiceEnte ( "INVALID" );

        assertEquals ( Constants.RESULT_CODES.NOT_ALLOWED, call ( input ).getCodiceEsito () );
    }

    @Test
    public void shouldReturnOKWithInputCorrect () {

        GetOpzioniCodiceVersamentoInput input = getInput ();
                assertEquals ( Constants.RESULT_CODES.OK, call ( input ).getCodiceEsito () );

    }

    @Test
    public void shouldReturnAllMandatoryFields () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        assertTrue ( repo.count () > 0 );

        for ( DecodificaOutputDto o: call ( input ).getOpzioni () ) {

            CodiceVersamento dto = repo.findOne ( o.getId () );

            assertEquals ( dto.getId (), o.getId () );
            assertEquals ( dto.getCodice (), o.getCodice () );
            assertEquals ( dto.getDescrizione (), o.getDescrizione () );
        }
    }

    @Test
    public void shouldReturnOnlyParents () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.setSoloBase ( true );
        assertTrue ( repo.count () > 0 );

        for ( DecodificaOutputDto o: call ( input ).getOpzioni () ) {

            CodiceVersamento dto = repo.findOne ( o.getId () );

            assertNull ( dto.getCodiceVersamentoPadre () );
        }
    }

    @Test
    public void shouldNotReturnOnlyParents () {
        GetOpzioniCodiceVersamentoInput input = getInput ();
        input.setSoloBase ( false );
        assertTrue ( repo.count () > 0 );
        boolean some = false;

        for ( DecodificaOutputDto o: call ( input ).getOpzioni () ) {

            CodiceVersamento dto = repo.findOne ( o.getId () );

            if ( dto.getCodiceVersamentoPadre () != null ) {
                some = true;
            }
        }

        assertTrue ( "at least one should have a father", some );
    }

    @Test
    public void shouldNotReturnWithFlagAnnullato () {

        GetOpzioniCodiceVersamentoInput input = getInput ();
        GetOpzioniCodiceVersamentoOutput output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        int size0 = output.getOpzioni ().size ();

        assertTrue ( size0 > 0 );

        List<CodiceVersamento> all = repo.findAll ();
        assertTrue ( all.size () >= 3 );
        for ( int i = 0; i <= all.size () / 2; i++ ) {
            CodiceVersamento entity = all.get ( i );
            entity.setFlagAnnullato ( true );
            repo.save ( entity );
        }

        output = call ( input );

        assertEquals ( Constants.RESULT_CODES.OK, output.getCodiceEsito () );

        int size1 = output.getOpzioni ().size ();

        assertTrue ( size1 < size0 );
    }
}
