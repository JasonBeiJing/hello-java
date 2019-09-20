package com.hello.java.util;

import java.security.SecureRandom;
import java.util.Locale;

public class RandomString {
    public static void main(String[] args) {
        System.err.println(get());
    }

    public static String get() {
        SecureRandom random = new SecureRandom();
        byte[] rbs = new byte[24];
        random.nextBytes(rbs);

        StringBuilder token = new StringBuilder(24);
        int length = rbs.length;
        for (int i = 0; i < length; ++i) {
            String sTemp = Integer.toHexString(255 & rbs[i]);
            if (sTemp.length() < 2) {
                token.append(0);
            }
            token.append(sTemp.toUpperCase(Locale.US));
        }
        return token.toString();
    }
}
