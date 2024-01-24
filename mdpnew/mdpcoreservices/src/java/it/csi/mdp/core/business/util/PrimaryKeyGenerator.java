/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.util;

import java.util.Hashtable;

/**
 * <p>Title: Key generator</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Smy ltd</p>
 * @author Simone Salvai
 * @version 1.0
 */

public class PrimaryKeyGenerator {

   public final static int PK_LENGHT = 30;
   public final static int PK_BASE = 36;
   public final static int SPACE = -1;

   private static char intToCharMap[] = {
         '0', '1', '2', '3', '4', '5', '6', '7', '8',
         '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
         'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
         'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

   private static Hashtable charToIntMap;

   private PrimaryKeyGenerator() { }

   private static void inizializeCharToInt() {
      charToIntMap = new Hashtable(PK_BASE);
      for (int i = 0; i < PK_BASE; i++) {
         charToIntMap.put( new Character(intToCharMap[i]), new Integer(i) );
      }
   }

   // Converte la stringa in un array di valori interi
   private static int[] toIntForm(String pk) {
      int[] intFormPK = new int[PK_LENGHT];
      StringBuffer sb;
      sb = new StringBuffer(pk);
      while (sb.length() < PK_LENGHT) {
         sb.insert(0,' ');
      }
      pk = sb.toString();

      for (int i = 0; i < PK_LENGHT; i++) {
         if ( pk.charAt(i) == ' ' )
            intFormPK[i] = SPACE;
         else
            intFormPK[i] = ((Integer)charToIntMap.get(new Character(pk.charAt(i)))).intValue();
            
      }
      return intFormPK;
   }

   // Trava il successivo valore della chiave in forma intera
   private static int[] nextPKValue(int[] pk) {
      boolean updated = false;
      int current = PK_LENGHT-1;
      while (!updated) {
         if (pk[current]<PK_BASE-1) {
            pk[current]++;
            updated = true;
         }
         else {
            pk[current] = 0;
            current--;
            if (pk[current] == SPACE)
               pk[current] = 0;
         }
      }
      return pk;
   }

   // Converte l'array di valori interi in una stringa
   private static String toStringFrom(int[] pk) {
      StringBuffer sb;
      int current = PK_LENGHT-1;

      sb = new StringBuffer();
      while( pk[current] != -1 ) {
         sb.insert(0, intToCharMap[pk[current]] );
         current--;
      }
      return sb.toString();
   }

   public static String getNextPK(String pk) {
      String nextPK = "";
      int intFormPK[] = null;

      if (charToIntMap == null)
         inizializeCharToInt();

      intFormPK = toIntForm(pk);
      intFormPK = nextPKValue(intFormPK);
      nextPK = toStringFrom(intFormPK);
      return nextPK;
   }

   public static void main(String[] args) {
      String startPK = "0";
      for (int i=0; i<100000 ; i++) {
         startPK = getNextPK(startPK);
         System.out.println(i + " "+ startPK);
      }
    }

}
