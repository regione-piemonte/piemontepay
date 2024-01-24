/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.migrazione;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class EseguiMigrazioneInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Boolean eliminaLogPrecedenti;

    private Boolean eliminaGiaMigrati;

    private Boolean eseguiMigrazione;

    private Boolean anteprima;

    private Boolean importaEnti;

    private Boolean importaCodiciVersamento;

    private Boolean importaRiferimentiContabili;

    private Boolean importaCdu;

    private Boolean importaUtenti;

    public Boolean getEliminaLogPrecedenti () {
        return eliminaLogPrecedenti;
    }

    public void setEliminaLogPrecedenti ( Boolean eliminaLogPrecedenti ) {
        this.eliminaLogPrecedenti = eliminaLogPrecedenti;
    }

    public Boolean getEliminaGiaMigrati () {
        return eliminaGiaMigrati;
    }

    public void setEliminaGiaMigrati ( Boolean eliminaGiaMigrati ) {
        this.eliminaGiaMigrati = eliminaGiaMigrati;
    }

    public Boolean getEseguiMigrazione () {
        return eseguiMigrazione;
    }

    public void setEseguiMigrazione ( Boolean eseguiMigrazione ) {
        this.eseguiMigrazione = eseguiMigrazione;
    }

    public Boolean getAnteprima () {
        return anteprima;
    }

    public void setAnteprima ( Boolean anteprima ) {
        this.anteprima = anteprima;
    }

    public Boolean getImportaEnti () {
        return importaEnti;
    }

    public void setImportaEnti ( Boolean importaEnti ) {
        this.importaEnti = importaEnti;
    }

    public Boolean getImportaCodiciVersamento () {
        return importaCodiciVersamento;
    }

    public void setImportaCodiciVersamento ( Boolean importaCodiciVersamento ) {
        this.importaCodiciVersamento = importaCodiciVersamento;
    }

    public Boolean getImportaRiferimentiContabili () {
        return importaRiferimentiContabili;
    }

    public void setImportaRiferimentiContabili ( Boolean importaRiferimentiContabili ) {
        this.importaRiferimentiContabili = importaRiferimentiContabili;
    }

    public Boolean getImportaCdu () {
        return importaCdu;
    }

    public void setImportaCdu ( Boolean importaCdu ) {
        this.importaCdu = importaCdu;
    }

    public Boolean getImportaUtenti () {
        return importaUtenti;
    }

    public void setImportaUtenti ( Boolean importaUtenti ) {
        this.importaUtenti = importaUtenti;
    }

}
