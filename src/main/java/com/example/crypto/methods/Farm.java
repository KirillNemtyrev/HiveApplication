package com.example.crypto.methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Farm {

    private static int count_farm;

    private static JSONArray get_farms;

    public static void setFarms(JSONArray farms){
        get_farms = farms;
        count_farm = get_farms.size();
    }

    public static int getCount_farm(){
        return count_farm;
    }

    public static JSONObject getFarm(int index){

        if(index > count_farm) return null;

        return (JSONObject) get_farms.get(index);
    }
}
