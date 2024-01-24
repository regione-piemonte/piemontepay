/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.test.model;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class ParentUnitTest {

    @Before
    public void beforeParentTest () {
        mockServletEnvironment ();
        MockitoAnnotations.initMocks ( this );
    }

    protected void mockServletEnvironment () {
        MockHttpServletRequest request = new MockHttpServletRequest ();
        RequestContextHolder.setRequestAttributes ( new ServletRequestAttributes ( request ) );
    }

}
