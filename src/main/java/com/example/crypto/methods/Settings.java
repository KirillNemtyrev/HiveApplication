package com.example.crypto.methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private static String SettingLogin = "";
    private static String SettingPassword = "";
    private static String SettingToken = "";
    private static boolean SettingRemember = false;
    private static File file = new File("config.ini");

    public static void getParams(){
        try {
            if(!file.isFile()) file.createNewFile();
            Properties props = new Properties();
            props.load(new FileInputStream(file));
            // Load props
            SettingLogin = props.getProperty("LOGIN");
            SettingPassword = props.getProperty("PASSWORD");
            SettingToken = props.getProperty("TOKEN");
            SettingRemember = Boolean.getBoolean(props.getProperty("REMEMBER"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveParams(){
        try {
            if(!file.isFile()) file.createNewFile();
            FileWriter writer = new FileWriter(file.getPath());

            String temp = "LOGIN = " + (SettingRemember ? SettingLogin : "")  + "\n";
            temp += "PASSWORD = " + (SettingRemember ? SettingPassword : "")  + "\n";
            temp += "TOKEN = " + SettingToken  + "\n";
            temp += "REMEMBER = " + SettingRemember;

            writer.write(temp);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setSettingLogin(String login){
        SettingLogin = login;
    }

    public static String getSettingLogin(){
        return SettingLogin;
    }

    public static void setSettingPassword(String password){
        SettingPassword = password;
    }

    public static String getSettingPassword(){
        return SettingPassword;
    }

    public static void setSettingToken(String token){
        SettingToken = token;
    }

    public static String getSettingToken(){
        return SettingToken;
    }

    public static void setSettingRemember(boolean remember){
        SettingRemember = remember;
    }

    public static boolean getSettingRemember(){
        return SettingRemember;
    }
}
