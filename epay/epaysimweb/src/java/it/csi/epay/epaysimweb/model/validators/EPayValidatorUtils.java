/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.validators;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

public class EPayValidatorUtils {
    public static boolean isValidCodiceFiscale(String codFiscale) {
        boolean ret = false;
        if (codFiscale != null) {
            if (codFiscale.toUpperCase().matches("[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]")) {
                ret = true;
            }
        }

        return ret;
    }

    public static boolean almenoUnCampoValidato(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        boolean oneNotNull = false;
        for (Field field : fields) {
            try {
                if (field.getName().equalsIgnoreCase("SerialVersionUID")) {
                    continue;
                }
                Method method = target.getClass().getDeclaredMethod("get"+StringUtils.capitalize(field.getName()));
                Object value = method.invoke(target, (Object[])null);
                if (value != null) {
                    if (value instanceof String) {
                        oneNotNull = !"".equals(value);
                        if (oneNotNull) {
                            break;
                        }
                    } else if (value instanceof Object && value.getClass().getName().startsWith("it.csi.epay")) {
                        if (almenoUnCampoValidato(value)) {
                            oneNotNull = true;
                            break;
                        }
                    } else {
                        oneNotNull = true;
                        break;
                    }
                }
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return oneNotNull;
    }

}
