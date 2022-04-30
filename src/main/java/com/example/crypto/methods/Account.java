package com.example.crypto.methods;

public class Account {

    private static Long user_id;
    private static Long balance;

    private static String tracking_id;
    private static String login;
    private static String name;
    private static String email;
    private static String access_token;

    private static boolean email_confirm;
    private static boolean code_enabled;

    public static Long getUser_id() {
        return user_id;
    }

    public static void setUser_id(Long value) {
        user_id = value;
    }

    public static String getTracking_id() {
        return tracking_id;
    }

    public static void setTracking_id(String value) {
        tracking_id = value;
    }

    public static Long getBalance() {
        return balance;
    }

    public static void setBalance(Long value) {
        balance = value;
    }

    public static void setName(String input){
        name = input;
    }

    public static String getName(){
        return name;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String input) {
        login = input;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String input) {
        email = input;
    }

    public static String getAccessToken() {
        return access_token;
    }

    public static void setAccessToken(String input) {
        access_token = input;
    }

    public static boolean isEmail_confirm() {
        return email_confirm;
    }

    public static void setEmail_confirm(boolean actually) {
        email_confirm = actually;
    }

    public static boolean isCode_enabled() {
        return code_enabled;
    }

    public static void setCode_enabled(boolean actually) {
        code_enabled = actually;
    }
}
