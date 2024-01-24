/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.model;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;


/**
 *
 */

public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public boolean isNew () {
        return getId () == null;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( getId () == null ) ? 0 : getId ().hashCode () );
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        if ( getId () == null ) {
            if ( other.getId () != null )
                return false;
        } else if ( !getId ().equals ( other.getId () ) )
            return false;
        return true;
    }

}
