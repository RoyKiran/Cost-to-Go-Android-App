package com.bakmaps.cost_to_go.Transport;

import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACGenarator {

    private static final String RAIL_PRIVATE_KEY = "623b0a3b42aaadce1cd71e17bf9b5165";


    public static String generateMessage(TreeMap<String,String> msgParams){
        StringBuilder parameter=new StringBuilder();

        for (Map.Entry<String, String> entry : msgParams.entrySet()) {
            if (entry.getKey()== "format" ) {
                parameter.append("\"" + entry.getValue() + "\"");
            }
            parameter.append(".");
        }
        parameter.deleteCharAt(parameter.lastIndexOf("."));

        return calculateHMAC(parameter.toString().toLowerCase());
    }


    public static String calculateHMAC(String message) {
        StringBuffer resultHMAC = new StringBuffer();
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secret = new SecretKeySpec(RAIL_PRIVATE_KEY.getBytes(),
                    "HmacSHA1");
            mac.init(secret);
            byte[] digest = mac.doFinal(message.getBytes());
            for (byte b : digest) {
                resultHMAC.append(String.format("%02x", b));
            }
            System.out.println(resultHMAC);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultHMAC.toString();
    }
}