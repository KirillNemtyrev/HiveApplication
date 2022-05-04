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
    private static final File file = new File("config.ini");

    public static void getParams(){
        try {
            if(!file.isFile()) saveParams();

            Properties props = new Properties();
            props.load(new FileInputStream(file));
            // Load props
            SettingLogin = props.getProperty("LOGIN");
            SettingPassword = props.getProperty("PASSWORD");
            SettingToken = props.getProperty("TOKEN");
            SettingRemember = Boolean.parseBoolean(props.getProperty("REMEMBER"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveParams(){
        try {
            if(!file.createNewFile()) System.out.println("File is created..");

            FileWriter writer = new FileWriter(file.getPath());

            String temp = "LOGIN = " + (SettingRemember ? SettingLogin : "")  + "\n";
            temp += "PASSWORD = " + (SettingRemember ? SettingPassword : "")  + "\n";
            temp += "TOKEN = " + (SettingRemember ? SettingToken : "") + "\n";
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
