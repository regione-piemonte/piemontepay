/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.vo;

public class FunzioneVO {

    private int id;
    private String functionName;

    public FunzioneVO() {
        super();
    }

    public FunzioneVO ( int id, String functionName ) {
        super();
        this.id = id;
        this.functionName = functionName;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getFunctionName () {
        return functionName;
    }

    public void setFunctionName ( String functionName ) {
        this.functionName = functionName;
    }

    @Override
    public String toString () {
        return "FunzioneVO [id=" + id + ", functionName=" + functionName + "]";
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        FunzioneVO other = (FunzioneVO) obj;
        if ( id != other.getId () )
            return false;
        return true;
    }



}
