package org.ac.cst8277.islam.moin.util;

import java.util.HashMap;
import java.util.UUID;

public class Util {

    public static HashMap<String,Boolean> userLogMap = new HashMap<>();

    public static String generateTokenUUID(){
        return UUID.randomUUID().toString();
    }
}
