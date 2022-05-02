package com.example.crypto.methods;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Request {

    // Successful codes
    public static final int CODE_AUTHENTICATION_TOKEN = 200;
    public static final int CODE_CREATE_FARM = 201;
    public static final int CODE_AUTHENTICATED_TOKEN = 204;
    // Client error
    public static final int CODE_NOT_AUTHENTICATED = 401;
    public static final int CODE_NOT_ALLOWED = 403;
    public static final int CODE_VALIDATION_ERROR = 422;
    // API URL
    private static final String basicURL = "https://api2.hiveos.farm/api/v2/";

    private static final HttpClient httpClient = HttpClientBuilder.create().build();

    public static int sendAuthentication(String login, String password, String code){
        try {
            JSONObject params = new JSONObject();
            params.put("login", login);
            params.put("password", password);
            params.put("twofa_code", code);
            params.put("remember", true);
            StringEntity payload = new StringEntity(params.toString());

            HttpPost request = new HttpPost(basicURL + "auth/login");
            request.setHeader("content-type", "application/json");
            request.setEntity(payload);

            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);
            int HTTP_CODE_RESPONSE = response.getStatusLine().getStatusCode();

            // Get body entity
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            // Get access token
            Object parse = new JSONParser().parse(result);
            JSONObject body = (JSONObject) parse;

            Account.setAccessToken((String) body.get("access_token"));
            response.close();

            return HTTP_CODE_RESPONSE;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Logout(){
        try {

            HttpPost request = new HttpPost(basicURL + "auth/logout");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + Account.getAccessToken());
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);
            response.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int checkAccessToken(String token){
        try {
            HttpGet request = new HttpGet(basicURL + "auth/check");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + token);
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);
            int HTTP_CODE_RESPONSE = response.getStatusLine().getStatusCode();
            response.close();

            return HTTP_CODE_RESPONSE;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getAccount(){
        try {
            HttpGet request = new HttpGet(basicURL + "account");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + Account.getAccessToken());
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            int HTTP_CODE_RESPONSE = response.getStatusLine().getStatusCode();
            response.close();

            if (HTTP_CODE_RESPONSE != Request.CODE_AUTHENTICATION_TOKEN) return null;

            Object parse = new JSONParser().parse(result);
            JSONObject main_info = (JSONObject) parse;
            JSONObject profile = (JSONObject) main_info.get("profile");

            Account.setLogin((String) profile.get("login"));
            Account.setName((String) profile.get("name"));
            Account.setEmail((String) profile.get("email"));
            Account.setPhone((String) profile.get("phone"));
            Account.setSkype((String) profile.get("skype"));
            Account.setTelegram((String) profile.get("telegram"));
            Account.setCompany((String) profile.get("company_info"));

            Account.setTracking_id((String) main_info.get("tracking_id"));
            Account.setIp_address((String) main_info.get("ip"));
            Account.setBalance((Long) main_info.get("balance"));
            Account.setEmail_confirm((boolean) main_info.get("email_confirmed"));
            Account.setCode_enabled((boolean) main_info.get("2fa_enabled"));
            return true;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getFarms(){
        try {
            HttpGet request = new HttpGet(basicURL + "farms");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + Account.getAccessToken());
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);

            // Get body entity
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            int HTTP_CODE_RESPONSE = response.getStatusLine().getStatusCode();
            response.close();

            if (HTTP_CODE_RESPONSE != Request.CODE_AUTHENTICATION_TOKEN) return null;

            // Get all farms
            Object parse = new JSONParser().parse(result);
            JSONObject farm = (JSONObject) parse;
            JSONArray farms = (JSONArray) farm.get("data");
            Farm.setFarms(farms);
            return true;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int createFerm(String name, boolean autotags, boolean hiveon){
        try {
            // Details for post
            JSONObject params = new JSONObject();
            params.put("name", name);
            params.put("timezone", "Europe/Moscow");
            params.put("auto_tags", autotags);
            params.put("charge_on_pool", hiveon);
            StringEntity payload = new StringEntity(params.toString());

            HttpPost request = new HttpPost(basicURL + "farms");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + Account.getAccessToken());
            request.setEntity(payload);
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);

            int CODE_STATUS = response.getStatusLine().getStatusCode();
            response.close();

            return CODE_STATUS;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int updateProfile(JSONObject params){
        try {
            StringEntity payload = new StringEntity(params.toString());

            HttpPatch request = new HttpPatch(basicURL + "/account/profile");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + Account.getAccessToken());
            request.setEntity(payload);
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);

            int CODE_STATUS = response.getStatusLine().getStatusCode();
            response.close();

            return CODE_STATUS;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int updatePassword(String old_password, String new_password){
        try {
            JSONObject params = new JSONObject();
            params.put("password", old_password);
            params.put("new_password", new_password);
            StringEntity payload = new StringEntity(params.toString());

            HttpPatch request = new HttpPatch(basicURL + "/account/profile");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + Account.getAccessToken());
            request.setEntity(payload);
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);

            int CODE_STATUS = response.getStatusLine().getStatusCode();
            response.close();

            return CODE_STATUS;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}