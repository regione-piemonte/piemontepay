/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

public class AvvisoPagamentoRequestDto extends PrincipalDto {
	
	private static final long serialVersionUID = 1L;
	
	private Long idPosizioneDebitoria;
	private String serviceEndpoint;
	private String auth;
	
	/**
     * @param ipAddress
     * @param idUtente
     * @param codiceFiscaleUtente
     * @param codiceApplicazione
     * @param idPosizioneDebitoria
     * @param serviceEndpoint
     * @param auth
     */
    public AvvisoPagamentoRequestDto ( String ipAddress, Long idUtente, String codiceFiscaleUtente, String codiceApplicazione, Long idPosizioneDebitoria,
        String serviceEndpoint, String auth ) {
        super ( ipAddress, idUtente, codiceFiscaleUtente, codiceApplicazione );
        this.idPosizioneDebitoria = idPosizioneDebitoria;
        this.serviceEndpoint = serviceEndpoint;
        this.auth = auth;
    }

    public String getServiceEndpoint() {
		return serviceEndpoint;
	}

	public void setServiceEndpoint(String serviceEndpoint) {
		this.serviceEndpoint = serviceEndpoint;
	}

	public Long getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(Long idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

    
    /**
     * @return the auth
     */
    public String getAuth () {
        return auth;
    }

    
    /**
     * @param auth the auth to set
     */
    public void setAuth ( String auth ) {
        this.auth = auth;
    }
	
}
