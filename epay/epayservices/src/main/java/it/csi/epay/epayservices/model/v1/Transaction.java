/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Transaction
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-06-23T14:48:24.058528920Z[GMT]")

public class Transaction implements Serializable {
    private static final long serialVersionUID = -8098802380457184596L;

@JsonProperty("idTransaction")
  private String idTransaction = null;

  @JsonProperty("paymentUrl")
  private String paymentUrl = null;


   /**
   * Cart id, will be setted with TRANSACTION_ID
   * @return idTransaction
  **/
  public String getIdTransaction() {
    return idTransaction;
  }

  public void setIdTransaction(String idTransaction) {
    this.idTransaction = idTransaction;
  }

   /**
   * pagoPA url for payment
   * @return paymentUrl
  **/
  public String getPaymentUrl() {
    return paymentUrl;
  }

  public void setPaymentUrl(String paymentUrl) {
    this.paymentUrl = paymentUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.idTransaction, transaction.idTransaction) &&
        Objects.equals(this.paymentUrl, transaction.paymentUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTransaction, paymentUrl);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    idTransaction: ").append(toIndentedString(idTransaction)).append("\n");
    sb.append("    paymentUrl: ").append(toIndentedString(paymentUrl)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
