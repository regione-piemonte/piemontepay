/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class PaginazineThreads implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4038777049280043108L;

    private AtomicInteger pageIndex;

    private int pageSize;

    private AtomicBoolean isFirstPage;

    private AtomicBoolean isLastPage;

    private AtomicLong lastIdFlusso;

    /**
     * @param numeroPaginaInElaborazione
     * @param pageSize
     * @param isFirstPage
     * @param isLastPage
     */
    public PaginazineThreads ( AtomicInteger pageIndex, int pageSize, AtomicBoolean isFirstPage, AtomicBoolean isLastPage ) {
        super ();
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
    }

    /**
     * 
     */
    @SuppressWarnings ( "unused" )
    private PaginazineThreads () {
        super ();
    }

    /**
     * @return the pageIndex
     */
    public AtomicInteger getPageIndex () {
        return pageIndex;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize () {
        return pageSize;
    }

    /**
     * @return the isFirstPage
     */
    public AtomicBoolean getIsFirstPage () {
        return isFirstPage;
    }

    /**
     * @return the isLastPage
     */
    public AtomicBoolean getIsLastPage () {
        return isLastPage;
    }

    /**
     * @return the lastIdFlusso
     */
    public AtomicLong getLastIdFlusso () {
        return lastIdFlusso;
    }

}
