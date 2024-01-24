/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.business.manager.services;

/**
 *
 * @author 71758
 */
public class UserNameToken {

    private String tsCreated = "";
    private String tsExpires = "";
    private String nonce = "";
    private String digest = "";
    private String password = "";
    private String username = "";

    public UserNameToken() {
        this.setTsCreated("2009-07-03T07:55:56.733Z");
        this.setTsExpires("2009-07-03T08:00:56.749Z");
        this.setNonce("xVaWtU91/9I2uH6FpixUXg==");
        this.setDigest("MBAKpyF08jya6k7mPkqDL/EbDYw=");
        this.setPassword("joepassword");
        this.setUsername("joe");
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(String tsCreated) {
        this.tsCreated = tsCreated;
    }

    public String getTsExpires() {
        return tsExpires;
    }

    public void setTsExpires(String tsExpires) {
        this.tsExpires = tsExpires;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
