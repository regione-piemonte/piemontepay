/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model;

import java.util.Date;


public class UserVO {

    private int id;
    private String ssnNumber, name, email;
    private Date startDate, expirationDate;

    public UserVO() {
        super();
    }

    public UserVO(int id, String ssnNumber, String name, String email, Date startDate, Date expirationDate) {
        super();
        this.id = id;
        this.ssnNumber = ssnNumber;
        this.name = name;
        this.email = email;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSsnNumber() {
        return ssnNumber;
    }

    public void setSsnNumber(String ssnNumber) {
        this.ssnNumber = ssnNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {

        System.out.println(startDate);
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UserDTO [ssnNumber=" + ssnNumber + ", name=" + name + ", email=" + email + ", startDate=" + startDate
                        + ", expirationDate=" + expirationDate + "]";
    }


    public void add ( UserVO user ) {
        // TODO Auto-generated method stub

    }


}
