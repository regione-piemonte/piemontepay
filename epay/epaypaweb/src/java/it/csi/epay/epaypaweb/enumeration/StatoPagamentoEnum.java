/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum StatoPagamentoEnum {
	//@formatter:off
    CODICE_0(0, "Da Pagare"), 
    CODICE_1 (1, "Compilato"),
    CODICE_2 (2, "In Attesa"),
    CODICE_3(3, "Fallito"),
    CODICE_4 (4, "Successo"),
    CODICE_5 (5, "Annullato"),
    CODICE_6 (6, "Transazione inizializzata"),
    CODICE_7 (7, "Transazione avviata"),
    CODICE_8 (8, "Transazione errore"),
    CODICE_9(9, "Invalidato dall'ente"),
    CODICE_10(10, "Pagamento revocato"),
    CODICE_11(-1, "Non definito");

    private Integer id;
    private String des;

    private StatoPagamentoEnum(Integer id, String des) {
        this.id = id;
        this.des = des;
    }
    
    
    public Integer getId () {
        return id;
    }
    
    public void setId ( Integer id ) {
        this.id = id;
    }


    public String getDes() {
        return des;
    }

    static public StatoPagamentoEnum fromId(Integer id) {
        for (StatoPagamentoEnum en : StatoPagamentoEnum.values()) {
            if (en.id.equals(id)) {
                return en;
            }
        }
        return null;
    }

}
