package com.bakmaps.cost_to_go.Transport;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by WORK with FUN on 01-02-2016.
 */
public class RailHttp {

    private String url = "http://railpnrapi.com/api/";
    private static final String RAIL_PUBLIC_KEY = "acaae158bfd23f8f58f7558b7c1613e4";
    String code;
    TreeMap<String, String> parameter = new TreeMap<String, String>();

    void formURL(TreeMap<String, String> parameter) {
        url = url.concat(code + "/");
        int i = 0;
        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            if (i != 0) {
                url.concat(entry.getKey() + "/" + entry.getValue() + "/");
            }
        }
        url.concat("pbapikey/" + RAIL_PUBLIC_KEY + "/pbapisign/" + HMACGenarator.generateMessage(parameter));
    }

    void checkPNRStatus(String pnr) {
        // Purpose - Checking Indian railways PNR status using 10 digit PNR number.
        // url :- http://railpnrapi.com/api/check_pnr/pnr/<pnr number>/format/xml|json/pbapikey/<your public api key>/pbapisign/<HMAC signature>
        parameter.clear();
        parameter.put("code", "check_pnr");
        parameter.put("pnr", pnr);
        parameter.put("format", "json");
        formURL(parameter);
    }

    void trainScheduleRoute(String trainNo) {
        // Purpose - Getting full train detail along with route and schedule detail using train number.
        // url :- http://railpnrapi.com/api/route/train/<train number>/format/xml|json/pbapikey/<your public api key>/pbapisign/<HMAC signature>
        parameter.clear();
        parameter.put("code", "route");
        parameter.put("train", trainNo);
        parameter.put("format", "json");
        formURL(parameter);
    }

    void checkSeatAvailability(String trainNo, String fromStation, String toStation,String date, String trainClass, String quota){
        //Purpose :-  Checking availability in Indian trains for reservation.
        /** url:- http://railpnrapi.com/api/check_avail/tnum/<TRAIN_NUMBER>/fscode/<FROM_STATION_CODE>/tscode/<TO_STATION_CODE>/date/<TRAVEL_DATE in DD-MM-YYYY Format>
         /class / <TWO_DIGIT_CLASS_CODE> / quota / <TWO_DIGIT_QUOTA_CODE> / format<xml|json> / pbapikey / <your public api key> / pbapisign / <HMAC signature>
         **/
        parameter.clear();
        parameter.put("code", "check_avail");
        parameter.put("tnum", trainNo);
        parameter.put("fscode",fromStation);
        parameter.put("tscode",toStation);
        parameter.put("date",date);
        parameter.put("class",trainClass);
        parameter.put("quota",quota);
        parameter.put("format", "json");
        formURL(parameter);
    }


}
