/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.dto.common;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Generated;


public class ConfigurazioneDTO implements Serializable, Comparable<ConfigurazioneDTO> {

    private static final long serialVersionUID = 1L;

    private String key;

    private String value;

    @Generated ( "SparkTools" )
    private ConfigurazioneDTO ( Builder builder ) {
        key = builder.key;
        value = builder.value;
    }

    public boolean isEmpty () {
        return ( value == null || value.trim ().isEmpty () );
    }

    public Long asLong ( Long defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return Long.valueOf ( value );
        }
    }

    public Integer asInteger ( Integer defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return Integer.valueOf ( value );
        }
    }

    public Boolean asBoolean ( Boolean defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return Boolean.valueOf ( value );
        }
    }

    public String asString ( String defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public List<String> asStringList ( String separator, List<String> defaultValue ) {
        if ( isEmpty () ) {
            return defaultValue;
        } else {
            List<String> output = new LinkedList<> ();
            for ( String token: value.split ( separator ) ) {
                output.add ( token.trim () );
            }

            return output;
        }
    }

    public String asString () {
        return asString ( null );
    }

    public List<String> asStringList ( String separator ) {
        return asStringList ( separator, null );
    }

    public Long asLong () {
        return asLong ( null );
    }

    public Integer asInteger () {
        return asInteger ( null );
    }

    public Boolean asBoolean () {
        return asBoolean ( null );
    }

    public String getKey () {
        return key;
    }

    public void setKey ( String key ) {
        this.key = key;
    }

    public String getValue () {
        return value;
    }

    public void setValue ( String value ) {
        this.value = value;
    }

    @Override
    public int compareTo ( ConfigurazioneDTO toCompare ) {
        int compare = key.compareTo ( toCompare.getKey () )
            + value.compareTo ( toCompare.getValue () );
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "key: [" + key + "]" );
        stringBuffer.append ( "value: [" + value + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( key == null ) ? 0 : key.hashCode () );
        result = prime * result + ( ( value == null ) ? 0 : value.hashCode () );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass () != obj.getClass () ) {
            return false;
        }
        ConfigurazioneDTO other = (ConfigurazioneDTO) obj;

        if ( key == null ) {
            if ( other.key != null ) {
                return false;
            }
        } else if ( !key.equals ( other.key ) ) {
            return false;
        }
        if ( value == null ) {
            if ( other.value != null ) {
                return false;
            }
        } else if ( !value.equals ( other.value ) ) {
            return false;
        }
        return true;
    }

    /**
     * Creates builder to build {@link ConfigurazioneDTO}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    public static Builder builder ( String key, String value ) {
        return new Builder ().withKey ( key ).withValue ( value );
    }

    /**
     * Builder to build {@link ConfigurazioneDTO}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private String key;

        private String value;

        private Builder () {
        }

        public Builder withKey ( String key ) {
            this.key = key;
            return this;
        }

        public Builder withValue ( String value ) {
            this.value = value;
            return this;
        }

        public ConfigurazioneDTO build () {
            return new ConfigurazioneDTO ( this );
        }
    }

}
