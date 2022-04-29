package com.example.crypto;

import java.io.*;
import java.util.Properties;

public class ConfigFile {

    private static String LOGIN = "";
    private static String PASSWORD = "";
    private static boolean SAVE_AUTO = false;

    public static void LoadCFG() {

        try {
            File file = new File("config.ini");
            Properties props = new Properties();
            if (file.isFile()) {
                props.load(new FileInputStream(file));

                LOGIN = props.getProperty("LOGIN");
                PASSWORD = props.getProperty("PASSWORD");
                Request.ACCESS_TOKEN = props.getProperty("TOKEN");
                SAVE_AUTO = Boolean.getBoolean(props.getProperty("SAVE_AUTO"));
                return;
            }

            file.createNewFile();
            SaveCFG();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void SaveCFG() {
        try {
            File file = new File("config.ini");
            if (file.isFile()) {
                FileWriter writer = new FileWriter(file.getPath());

                writer.write("LOGIN = " + LOGIN + "\n"
                        + "PASSWORD = " + PASSWORD + "\n"
                        + "TOKEN = " + Request.ACCESS_TOKEN + "\n"
                        + "SAVE_AUTO = " + SAVE_AUTO);
                writer.close();
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLOGIN(){
        return LOGIN;
    }

    public static String getPASSWORD(){
        return PASSWORD;
    }

    public static boolean getSave(){
        return SAVE_AUTO;
    }

    public static void setLOGIN(String InputLogin){
        LOGIN = InputLogin;
    }

    public static void setPASSWORD(String InputPassword){
        PASSWORD = InputPassword;
    }

    public static void setSave(boolean action){
        SAVE_AUTO = action;
    }
}
