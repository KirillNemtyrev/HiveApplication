package com.example.crypto.methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Farm {

    private static int count_farm;

    private static JSONArray get_farms;

    private static String CurrentFarmID;
    private static String CurrentFarmName;
    private static String CurrentFarmRole;
    private static String CountGPU;
    private static String CountRIGS;
    private static String PowerFarm;
    private static double BalanceFarm;

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

    public static void setCurrentFarmRole(String currentFarmRole) {
        CurrentFarmRole = currentFarmRole;
    }

    public static String getCurrentFarmRole() {
        return CurrentFarmRole;
    }

    public static void setCurrentFarm(String currentFarm) {
        CurrentFarmID = currentFarm;
    }

    public static String getCurrentFarmName() {
        return CurrentFarmName;
    }

    public static void setCurrentFarmName(String currentFarm) {
        CurrentFarmName = currentFarm;
    }

    public static String getCountGPU() {
        return CountGPU;
    }

    public static void setCountGPU(String countGPU) {
        CountGPU = countGPU;
    }

    public static String getCountRIGS() {
        return CountRIGS;
    }

    public static void setCountRIGS(String countRIGS) {
        CountRIGS = countRIGS;
    }

    public static String getPowerFarm() {
        return PowerFarm;
    }

    public static void setPowerFarm(String powerFarm) {
        PowerFarm = powerFarm;
    }

    public static double getBalanceFarm() {
        return BalanceFarm;
    }

    public static void setBalanceFarm(double balanceFarm) {
        BalanceFarm = balanceFarm;
    }
}
