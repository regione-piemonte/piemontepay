/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Error
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2023-06-12T15:32:30.142683518Z[GMT]")

public class Error {
    @JsonProperty("status")
  private String status = null;

  /**
   * Gets or Sets code
   */
//  @JsonAdapter(CodeEnum.Adapter.class)
//  public enum CodeEnum {
//      @JsonProperty("VALIDATION_ERROR")
//    VALIDATION_ERROR("VALIDATION_ERROR"),
//    @JsonProperty("INTERNAL_ERROR")
//    INTERNAL_ERROR("INTERNAL_ERROR"),
//    @JsonProperty("BUSINESS_ERROR")
//    BUSINESS_ERROR("BUSINESS_ERROR"),
//    @JsonProperty("AUTHORIZATION_ERROR")
//    AUTHORIZATION_ERROR("AUTHORIZATION_ERROR");
//
//    private String value;
//
//    CodeEnum(String value) {
//      this.value = value;
//    }
//    public String getValue() {
//      return value;
//    }
//
//    @Override
//    public String toString() {
//      return String.valueOf(value);
//    }
//    public static CodeEnum fromValue(String input) {
//      for (CodeEnum b : CodeEnum.values()) {
//        if (b.value.equals(input)) {
//          return b;
//        }
//      }
//      return null;
//    }
//    
//    public static class Adapter extends TypeAdapter<CodeEnum> {
//      @Override
//      public void write(final JsonWriter jsonWriter, final CodeEnum enumeration) throws IOException {
//        jsonWriter.value(String.valueOf(enumeration.getValue()));
//      }
//
//      @Override
//      public CodeEnum read(final JsonReader jsonReader) throws IOException {
//        Object value = jsonReader.nextString();
//        return CodeEnum.fromValue((String)(value));
//      }
//    }
//  } 
  
  @JsonProperty("code")
  private CodeEnum code = null;

  @JsonProperty("detail")
  private String detail = null;

//  public Error status(String status) {
//    this.status = status;
//    return this;
//  }

   /**
   * Get status
   * @return status
  **/
//  @Schema(required = true, description = "")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

//  public Error code(CodeEnum code) {
//    this.code = code;
//    return this;
//  }

   /**
   * Get code
   * @return code
  **/
//  @Schema(required = true, description = "")
  public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }

//  public Error detail(String detail) {
//    this.detail = detail;
//    return this;
//  }

   /**
   * Get detail
   * @return detail
  **/
//  @Schema(description = "")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.status, error.status) &&
        Objects.equals(this.code, error.code) &&
        Objects.equals(this.detail, error.detail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, code, detail);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
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
