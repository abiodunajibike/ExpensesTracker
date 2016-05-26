package com.abiodunajibike.app.expensestracker.helpers;

import java.text.DecimalFormat;

/**
 * Created by AJ on 24/05/2016.
 */
public class ReUsableClasses {

    public static String formatValue(Double val){
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedString = formatter.format(val);
        return formattedString;
    }


}
