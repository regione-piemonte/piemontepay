/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Cart
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-06-23T14:48:24.058528920Z[GMT]")

public class Cart {
    @JsonProperty("paymentNotices")
  private List<PaymentNotice> paymentNotices = new ArrayList<PaymentNotice>();

    @JsonProperty("emailNotice")
  private String emailNotice = null;


   /**
   * Get paymentNotices
   * @return paymentNotices
  **/
  public List<PaymentNotice> getPaymentNotices() {
    return paymentNotices;
  }

  public void setPaymentNotices(List<PaymentNotice> paymentNotices) {
    this.paymentNotices = paymentNotices;
  }

   /**
   * citizen email; provide it for notification system
   * @return emailNotice
  **/
  public String getEmailNotice() {
    return emailNotice;
  }

  public void setEmailNotice(String emailNotice) {
    this.emailNotice = emailNotice;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cart cart = (Cart) o;
    return Objects.equals(this.paymentNotices, cart.paymentNotices) &&
        Objects.equals(this.emailNotice, cart.emailNotice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentNotices, emailNotice);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cart {\n");
    
    sb.append("    paymentNotices: ").append(toIndentedString(paymentNotices)).append("\n");
    sb.append("    emailNotice: ").append(toIndentedString(emailNotice)).append("\n");
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
