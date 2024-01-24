/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * PaymentNotice
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-06-23T14:48:24.058528920Z[GMT]")

public class PaymentNotice {
  @JsonProperty("description")
  private String description = null;

  @JsonProperty("applicationId")
  private String applicationId = null;

  @JsonProperty("noticeNumber")
  private String noticeNumber = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;


   /**
   * Breve descrizione del pagamento
   * @return description
  **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


   /**
   * identificativo applicazione
   * @return applicationId
  **/
  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }


   /**
   * Numero avviso, with aux-digit
   * @return noticeNumber
  **/
  public String getNoticeNumber() {
    return noticeNumber;
  }

  public void setNoticeNumber(String noticeNumber) {
    this.noticeNumber = noticeNumber;
  }


   /**
   * Payment amount ( in euro cents ) of the payment notice
   * minimum: 1
   * maximum: 99999999999
   * @return amount
  **/
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentNotice paymentNotice = (PaymentNotice) o;
    return Objects.equals(this.description, paymentNotice.description) &&
        Objects.equals(this.applicationId, paymentNotice.applicationId) &&
        Objects.equals(this.noticeNumber, paymentNotice.noticeNumber) &&
        Objects.equals(this.amount, paymentNotice.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, applicationId, noticeNumber, amount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentNotice {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    applicationId: ").append(toIndentedString(applicationId)).append("\n");
    sb.append("    noticeNumber: ").append(toIndentedString(noticeNumber)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
