/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import it.csi.epay.epaypacatalogweb.integration.facade.impl.GestioneUtentiFacadeMockImpl;


public class UserVO {


    private int id;

    @NotNull
    @NotEmpty
    private String ssnNumber;

    @NotNull
    @NotEmpty
    @Pattern ( regexp = "^[a-zA-Z\\s]+" )
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date startDate;

    @DateTimeFormat ( pattern = "dd/MM/yyyy" )
    private Date expirationDate;

    private List<FunzioneVO> activeFunctions, globalFunctions;

    private List<AssociazioneTematicaVO> activeDepositCodes, depositCodes;

    private boolean active;

    public UserVO () {
        super ();
    }

    public UserVO ( int id, String ssnNumber, String name, String email, Date startDate, Date expirationDate ) {
        super ();
        this.id = id;
        this.ssnNumber = ssnNumber;
        this.name = name;
        this.email = email;
        this.active = true;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.activeFunctions = new ArrayList<> ();
        this.activeDepositCodes = new ArrayList<> ();
        this.globalFunctions = GestioneUtentiFacadeMockImpl.getAllFunctions ();
        this.depositCodes = GestioneUtentiFacadeMockImpl.getAllPaymentDepositCodes ();
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getSsnNumber () {
        return ssnNumber;
    }

    public void setSsnNumber ( String ssnNumber ) {
        this.ssnNumber = ssnNumber;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public boolean isActive () {
        return active;
    }

    public void setActive ( boolean active ) {
        this.active = active;
    }

    public Date getStartDate () {
        return startDate;
    }

    public void setStartDate ( Date startDate ) {
        this.startDate = startDate;
    }

    public Date getExpirationDate () {
        return expirationDate;
    }

    public void setExpirationDate ( Date expirationDate ) {
        this.expirationDate = expirationDate;
    }

    public List<FunzioneVO> getActiveFunctions () {
        return activeFunctions;
    }

    public void setActiveFunctions ( List<FunzioneVO> activeFunctions ) {
        this.activeFunctions = activeFunctions;
    }

    public List<FunzioneVO> getGlobalFunctions () {
        return globalFunctions;
    }

    // DEPOSIT CODES

    public List<AssociazioneTematicaVO> getDepositCodes () {
        return depositCodes;
    }

    public void setDepositCodes ( List<AssociazioneTematicaVO> depositCodes ) {
        this.depositCodes = depositCodes;
    }

    public List<AssociazioneTematicaVO> getActiveDepositCodes () {
        return activeDepositCodes;
    }

    public void setActiveDepositCodes ( List<AssociazioneTematicaVO> activeDepositCodes ) {
        this.activeDepositCodes = activeDepositCodes;
    }

    @Override
    public String toString () {
        return "UserVO [id=" + id + ", ssnNumber=" + ssnNumber + ", name=" + name + ", email=" + email + ", startDate=" + startDate + ", expirationDate="
                        + expirationDate + ", activeFunctions=" + activeFunctions + ", globalFunctions=" + globalFunctions + ", activeDepositCodes=" + activeDepositCodes
                        + ", depositCodes=" + depositCodes + ", active=" + active + "]";
    }


}
