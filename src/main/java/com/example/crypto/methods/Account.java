package com.example.crypto.methods;

public class Account {
    private static Long balance;

    private static String tracking_id;
    private static String ip_address;
    private static String login;
    private static String name;
    private static String email;
    private static String access_token;
    private static String phone;
    private static String skype;
    private static String telegram;
    private static String company;
    private static String secret;
    private static String qr_code;

    private static boolean email_confirm;
    private static boolean code_enabled;

    public static String getTracking_id() {
        return tracking_id;
    }

    public static void setTracking_id(String value) {
        tracking_id = value;
    }

    public static String getIp_address() {
        return ip_address;
    }

    public static void setIp_address(String value) {
        ip_address = value;
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

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Account.phone = phone;
    }

    public static String getSkype() {
        return skype;
    }

    public static void setSkype(String skype) {
        Account.skype = skype;
    }

    public static String getTelegram() {
        return telegram;
    }

    public static void setTelegram(String telegram) {
        Account.telegram = telegram;
    }

    public static String getCompany() {
        return company;
    }

    public static void setCompany(String company) {
        Account.company = company;
    }

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        Account.secret = secret;
    }

    public static String getQr_code() {
        return qr_code;
    }

    public static void setQr_code(String qr_code) {
        Account.qr_code = qr_code;
    }
}
