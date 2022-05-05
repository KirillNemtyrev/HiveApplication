package com.example.crypto.methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Workers {

    private static JSONArray CurrentWorkers;

    public static int getCountWorkers(){
        return CurrentWorkers.size();
    }

    public static JSONArray getCurrentWorkers() {
        return CurrentWorkers;
    }

    public static JSONObject getCurrentWorker(int index){
        return (JSONObject) CurrentWorkers.get(index);
    }

    public static void setCurrentWorkers(JSONArray currentWorkers) {
        CurrentWorkers = currentWorkers;
    }
}
