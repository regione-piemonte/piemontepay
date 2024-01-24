/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.cache;

public class CacheElement {

  private long time;

  private Object value;
  
  private boolean timeouted;

  public CacheElement(Object value) {
    this.time = System.currentTimeMillis();
    this.value = value;
  }

  public Object getValue() {
    return value;
  }

  public long getTime() {
    return time;
  }

  public boolean isTimeouted() {
    return timeouted;
  }

  public void setTimeouted(boolean timeouted) {
    this.timeouted = timeouted;
  }

}
