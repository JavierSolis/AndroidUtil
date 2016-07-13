package com.javiersolis.lib.android.util.identificationDocument;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 3/12/15.
 */
public class UtilValidDocument {
    public static boolean validateIdentificationDocumentPeru(String identificationDocument)
    {
        if (!identificationDocument.isEmpty())
        {
            int addition = 0;
            int[] hash = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
            int identificationDocumentLength = identificationDocument.length();

            String identificationComponent = identificationDocument.substring(0, identificationDocumentLength - 1);

            int identificationComponentLength = identificationComponent.length();

            int diff = hash.length - identificationComponentLength;

            for (int i = identificationComponentLength - 1; i >= 0; i--)
            {
                addition += (identificationComponent.charAt(i) - '0') * hash[i + diff];
            }

            addition = 11 - (addition % 11);

            if (addition == 11)
            {
                addition = 0;
            }

            char last = Character.toUpperCase(identificationDocument.charAt(identificationDocumentLength - 1));

            if (identificationDocumentLength == 11)
            {
                // The identification document corresponds to a RUC.
                return addition== (last - '0');
            }
            else if (Character.isDigit(last))
            {
                // The identification document corresponds to a DNI with a number as verification digit.
                char[] hashNumbers = { '6', '7', '8', '9', '0', '1', '1', '2', '3', '4', '5' };
                return last==hashNumbers[addition];
            }
            else if (Character.isLetter(last))
            {
                // The identification document corresponds to a DNI with a letter as verification digit.
                char[] hashLetters = { 'K', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
                return last==hashLetters[addition];
            }
        }

        return false;
    }
}
