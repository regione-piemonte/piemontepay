/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.gestioneente;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.multipart.MultipartFile;

import it.csi.epay.epaypacatalogweb.model.AssociazioneVO;
import it.csi.epay.epaypacatalogweb.model.GenericVO;


public class ModificaEnteVO extends EnteVO {

    private static final long serialVersionUID = 1L;

    private List<AssociazioneVO<GenericVO>> codiciVersamentoSelezionati = new LinkedList<> ();

    private List<AssociazioneVO<GenericVO>> codiciVersamentoNonSelezionati = new LinkedList<> ();

    private Map<Long, Integer> idCodiciVersamentoSelezionati = new HashMap<> ();

    private MultipartFile newLogo;

    private Boolean cancellaLogo;

    private String logoContentEncoded;

    private String logoAttuale;

    public ModificaEnteVO () {
        super ();
    }

    public ModificaEnteVO ( EnteVO ente ) {
        super ();
        try {
            PropertyUtils.copyProperties ( this, ente );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
            throw new RuntimeException ( e );
        }
    }

    public String getLogoAttuale () {
        return logoAttuale;
    }

    public void setLogoAttuale ( String logoAttuale ) {
        this.logoAttuale = logoAttuale;
    }

    public String getLogoContentEncoded () {
        return logoContentEncoded;
    }

    public void setLogoContentEncoded ( String logoContentEncoded ) {
        this.logoContentEncoded = logoContentEncoded;
    }

    public Boolean getCancellaLogo () {
        return cancellaLogo;
    }

    public void setCancellaLogo ( Boolean cancellaLogo ) {
        this.cancellaLogo = cancellaLogo;
    }

    public MultipartFile getNewLogo () {
        return newLogo;
    }

    public void setNewLogo ( MultipartFile newLogo ) {
        this.newLogo = newLogo;
    }

    public Map<Long, Integer> getIdCodiciVersamentoSelezionati () {
        return idCodiciVersamentoSelezionati;
    }

    public void setIdCodiciVersamentoSelezionati ( Map<Long, Integer> idCodiciVersamentoSelezionati ) {
        this.idCodiciVersamentoSelezionati = idCodiciVersamentoSelezionati;
    }

    public List<AssociazioneVO<GenericVO>> getCodiciVersamentoSelezionati () {
        return codiciVersamentoSelezionati;
    }

    public void setCodiciVersamentoSelezionati ( List<AssociazioneVO<GenericVO>> codiciVersamentoSelezionati ) {
        this.codiciVersamentoSelezionati = codiciVersamentoSelezionati;
    }

    public List<AssociazioneVO<GenericVO>> getCodiciVersamentoNonSelezionati () {
        return codiciVersamentoNonSelezionati;
    }

    public void setCodiciVersamentoNonSelezionati ( List<AssociazioneVO<GenericVO>> codiciVersamentoNonSelezionati ) {
        this.codiciVersamentoNonSelezionati = codiciVersamentoNonSelezionati;
    }

}
