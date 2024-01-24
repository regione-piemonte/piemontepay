/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.util.CriteriaBuilderUtil;
import it.csi.epay.epaypacatalogsrv.test.config.EpaycatalogWsUnitTestConfigH2;


@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { EpaycatalogWsUnitTestConfigH2.class } )
public class EnteRepositoryTest {

    @Autowired
    private EnteRepository repo;

    @Test
    public void countWorks () {
        assertTrue ( repo.count () >= 0 );
    }

    @Test
    public void findAllWorks () {
        assertTrue ( repo.findAll ().size () >= 0 );
    }

    @Test
    public void findByCriteriaOnCFReturnsCorrectResults () {

        List<Ente> enti = repo.findAll ();
        assertTrue ( enti.size () > 0 );

        for ( Ente ente: enti ) {
            assertNotNull ( ente.getCodiceFiscale () );

            Specification<Ente> specification = CriteriaBuilderUtil
                .likeCaseInsensitive ( Ente_.codiceFiscale, ente.getCodiceFiscale () );

            List<Ente> result = repo.findAll ( specification );
            assertNotNull ( result );
            assertEquals ( 1, result.size () );
            assertEquals ( ente.getCodiceFiscale (), result.get ( 0 ).getCodiceFiscale () );
        }
    }

    @Test
    public void findByCriteriaOnDenominazioneReturnsResults () {

        List<Ente> enti = repo.findAll ();
        assertTrue ( enti.size () > 0 );

        for ( Ente ente: enti ) {
            assertNotNull ( ente.getCodiceFiscale () );

            Specification<Ente> specification = CriteriaBuilderUtil
                .likeCaseInsensitive ( Ente_.denominazione, ente.getDenominazione () );

            List<Ente> result = repo.findAll ( specification );
            assertNotNull ( result );
            assertTrue ( result.size () > 0 );
        }
    }

    @Test
    public void findByCriteriaOnCFWorksByContains () {

        List<Ente> enti = repo.findAll ();
        Ente enteTest = null;

        Pattern pattern = Pattern.compile ( "[a-zA-Z]{2,}" );

        // look for code alphanumeric
        for ( Ente ente: enti ) {
            if ( ente.getCodiceFiscale () != null
                            && ente.getCodiceFiscale ().length () >= 2
                            && pattern.matcher ( ente.getCodiceFiscale () ).lookingAt () ) {
                enteTest = ente;
                break;
            }
        }

        assertNotNull ( enteTest );

        Matcher m = pattern.matcher ( enteTest.getCodiceFiscale () );
        assertTrue ( m.find () );

        String matchingSequenceFirst = m.group ( 0 ).substring ( 0, 1 );

        Specification<Ente> specification = CriteriaBuilderUtil
            .likeCaseInsensitive ( Ente_.codiceFiscale, matchingSequenceFirst );

        List<Ente> result = repo.findAll ( specification );
        assertNotNull ( result );
        assertTrue ( result.size () > 0 );
        Ente found = null;
        for ( Ente enteTrovato: result ) {
            if ( enteTrovato.getCodiceFiscale ().equals ( enteTest.getCodiceFiscale () ) ) {
                found = enteTrovato;
                break;
            }
        }
        assertNotNull ( found );
    }

    @Test
    public void findByCriteriaOnCFIsCaseInsensitive () {

        List<Ente> enti = repo.findAll ();
        Ente enteTest = null;

        Pattern pattern = Pattern.compile ( "[a-zA-Z]{2,}" );

        // look for code alphanumeric
        for ( Ente ente: enti ) {
            if ( ente.getCodiceFiscale () != null
                            && ente.getCodiceFiscale ().length () >= 2
                            && pattern.matcher ( ente.getCodiceFiscale () ).lookingAt () ) {
                enteTest = ente;
                break;
            }
        }

        assertNotNull ( enteTest );

        Specification<Ente> specification = CriteriaBuilderUtil
            .likeCaseInsensitive ( Ente_.codiceFiscale, enteTest.getCodiceFiscale ().toUpperCase () );

        List<Ente> result = repo.findAll ( specification );
        assertNotNull ( result );
        assertEquals ( 1, result.size () );
        assertEquals ( enteTest.getCodiceFiscale (), result.get ( 0 ).getCodiceFiscale () );

        specification = CriteriaBuilderUtil
            .likeCaseInsensitive ( Ente_.codiceFiscale, enteTest.getCodiceFiscale ().toLowerCase () );

        result = repo.findAll ( specification );
        assertNotNull ( result );
        assertEquals ( 1, result.size () );
        assertEquals ( enteTest.getCodiceFiscale (), result.get ( 0 ).getCodiceFiscale () );
    }

    @Test
    public void findByCriteriaOnDenominazioneWorksByContains () {

        List<Ente> enti = repo.findAll ();
        Ente enteTest = null;

        Pattern pattern = Pattern.compile ( "[a-zA-Z]{2,}" );

        // look for code alphanumeric
        for ( Ente ente: enti ) {
            if ( ente.getDenominazione () != null
                            && ente.getDenominazione ().length () >= 2
                            && pattern.matcher ( ente.getDenominazione () ).lookingAt () ) {
                enteTest = ente;
                break;
            }
        }

        assertNotNull ( enteTest );

        Matcher m = pattern.matcher ( enteTest.getDenominazione () );
        assertTrue ( m.find () );

        String matchingSequenceFirst = m.group ( 0 ).substring ( 0, 1 );

        Specification<Ente> specification = CriteriaBuilderUtil
            .likeCaseInsensitive ( Ente_.denominazione, matchingSequenceFirst );

        List<Ente> result = repo.findAll ( specification );
        assertNotNull ( result );
        assertTrue ( result.size () > 0 );
        Ente found = null;
        for ( Ente enteTrovato: result ) {
            if ( enteTrovato.getDenominazione ().equals ( enteTest.getDenominazione () ) ) {
                found = enteTrovato;
                break;
            }
        }
        assertNotNull ( found );
    }

    @Test
    public void findByCriteriaOnDenominazioneIsCaseInsensitive () {

        Pattern pattern = Pattern.compile ( "[a-zA-Z]{2,}" );

        List<Ente> enti = repo.findAll ();
        Ente enteTest = null;

        // look for code alphanumeric
        for ( Ente ente: enti ) {
            if ( ente.getDenominazione () != null
                            && ente.getDenominazione ().length () >= 2
                            && pattern.matcher ( ente.getDenominazione () ).lookingAt () ) {
                enteTest = ente;
                break;
            }
        }

        assertNotNull ( enteTest );

        Specification<Ente> specification = CriteriaBuilderUtil
            .likeCaseInsensitive ( Ente_.denominazione, enteTest.getDenominazione ().toUpperCase () );

        List<Ente> result = repo.findAll ( specification );
        assertNotNull ( result );
        assertEquals ( 1, result.size () );
        assertEquals ( enteTest.getDenominazione (), result.get ( 0 ).getDenominazione () );

        specification = CriteriaBuilderUtil
            .likeCaseInsensitive ( Ente_.denominazione, enteTest.getDenominazione ().toLowerCase () );

        result = repo.findAll ( specification );
        assertNotNull ( result );
        assertEquals ( 1, result.size () );
        assertEquals ( enteTest.getDenominazione (), result.get ( 0 ).getDenominazione () );
    }

}
