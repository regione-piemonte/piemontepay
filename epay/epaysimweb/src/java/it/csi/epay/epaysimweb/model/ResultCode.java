/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model;

public enum ResultCode {

    OK(0L),
    KO(1L),
    DELETED(2L),
    ERROR(100L);

    private final Long id;
    ResultCode(Long id) { this.id = id; }
    public Long getValue() { return id; }
}
