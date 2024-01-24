/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;


public class Email implements Serializable {

    private static final long serialVersionUID = 1100814031693004797L;

    public String getRecipient () {
        return recipient;
    }

    public void setRecipient ( String recipient ) {
        this.recipient = recipient;
    }

    public String getSender () {
        return sender;
    }

    public void setSender ( String sender ) {
        this.sender = sender;
    }

    public String getSubject () {
        return subject;
    }

    public void setSubject ( String subject ) {
        this.subject = subject;
    }

    public String getBody () {
        return body;
    }

    public void setBody ( String body ) {
        this.body = body;
    }

    public String getServer () {
        return server;
    }

    public void setServer ( String server ) {
        this.server = server;
    }

    public Long getPort () {
        return port;
    }

    public void setPort ( Long port ) {
        this.port = port;
    }

    public String getUser () {
        return user;
    }

    public void setUser ( String user ) {
        this.user = user;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public Long getRetryTimes () {
        return retryTimes;
    }

    public void setRetryTimes ( Long retryTimes ) {
        this.retryTimes = retryTimes;
    }

    public String getEmergencyRecipient () {
        return emergencyRecipient;
    }

    public void setEmergencyRecipient ( String emergencyRecipient ) {
        this.emergencyRecipient = emergencyRecipient;
    }

    public Long getRetryDelaySeconds () {
        return retryDelaySeconds;
    }

    public void setRetryDelaySeconds ( Long retryDelaySeconds ) {
        this.retryDelaySeconds = retryDelaySeconds;
    }

    public Long getRetryTimesMax () {
        return retryTimesMax;
    }

    public void setRetryTimesMax ( Long retryTimesMax ) {
        this.retryTimesMax = retryTimesMax;
    }

    public String getSmtpAuth () {
        return smtpAuth;
    }

    public void setSmtpAuth ( String smtpAuth ) {
        this.smtpAuth = smtpAuth;
    }

    public String getStarTTLsEnable () {
        return starTTLsEnable;
    }

    public void setStarTTLsEnable ( String starTTLsEnable ) {
        this.starTTLsEnable = starTTLsEnable;
    }

    public String getTransportProtocol () {
        return transportProtocol;
    }

    public void setTransportProtocol ( String transportProtocol ) {
        this.transportProtocol = transportProtocol;
    }

    public String getCarbonCopy () {
        return carbonCopy;
    }

    public void setCarbonCopy ( String carbonCopy ) {
        this.carbonCopy = carbonCopy;
    }

    private String recipient;

    private String carbonCopy;

    private String sender;

    private String subject;

    private String body;

    private String server;

    private Long port;

    private String user;

    private String password;

    private Long retryTimes;

    private Long retryTimesMax;

    private Long retryDelaySeconds;

    private String emergencyRecipient;

    private String smtpAuth;

    private String starTTLsEnable;

    private String transportProtocol;

    private Long id;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

}
