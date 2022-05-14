package com.example.crypto.methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Workers {

    private static JSONArray CurrentWorkers;

    public static int getCountWorkers(){
        return CurrentWorkers.size();
    }

    public static JSONObject getCurrentWorker(int index){
        return (JSONObject) CurrentWorkers.get(index);
    }

    public static void setCurrentWorkers(JSONArray currentWorkers) {
        CurrentWorkers = currentWorkers;
    }

    private static String haveDF;
    private static double freeMem;
    private static String cpuTemp;
    private static Long power;

    private static String hiveVersion;
    private static String nvidiaVersion;
    private static String amdVersion;
    private static Boolean need_upgrade = false;

    public static void setHaveDF(String haveDF){
        Workers.haveDF = haveDF;
    }

    public static String getHaveDF(){
        return haveDF;
    }

    public static double getFreeMem() {
        return freeMem;
    }

    public static void setFreeMem(double freeMem) {
        Workers.freeMem = freeMem;
    }

    public static String getCpuTemp() {
        return cpuTemp;
    }

    public static void setCpuTemp(String cpuTemp) {
        Workers.cpuTemp = cpuTemp;
    }

    public static Long getPower() {
        return power;
    }

    public static void setPower(Long power) {
        Workers.power = power;
    }

    public static String getHiveVersion() {
        return hiveVersion;
    }

    public static void setHiveVersion(String hiveVersion) {
        Workers.hiveVersion = hiveVersion;
    }

    public static String getNvidiaVersion() {
        return nvidiaVersion;
    }

    public static void setNvidiaVersion(String nvidiaVersion) {
        Workers.nvidiaVersion = nvidiaVersion;
    }

    public static String getAmdVersion() {
        return amdVersion;
    }

    public static void setAmdVersion(String amdVersion) {
        Workers.amdVersion = amdVersion;
    }

    public static Boolean getNeed_upgrade() {
        return need_upgrade;
    }

    public static void setNeed_upgrade(Boolean need_upgrade) {
        Workers.need_upgrade = need_upgrade;
    }
}
