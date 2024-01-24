/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

public enum CodeEnum {
        VALIDATION_ERROR("VALIDATION_ERROR"),
        INTERNAL_ERROR("INTERNAL_ERROR"),
        BUSINESS_ERROR("BUSINESS_ERROR"),
        AUTHORIZATION_ERROR("AUTHORIZATION_ERROR");

        private String value;

        CodeEnum(String value) {
          this.value = value;
        }
        public String getValue() {
          return value;
        }

        @Override
        public String toString() {
          return String.valueOf(value);
        }
        public static CodeEnum fromValue(String input) {
          for (CodeEnum b : CodeEnum.values()) {
            if (b.value.equals(input)) {
              return b;
            }
          }
          return null;
        }
        

}
