package com.example.crypto;

import java.io.*;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HTTPRequests {
    private static String basicURL = "https://api2.hiveos.farm/api/v2/";

    // Codes
    // Successful
    public static final int CODE_AUTHENTICATION_TOKEN = 200;
    public static final int CODE_CREATE_FARM = 201;
    public static final int CODE_AUTHENTICATED_TOKEN = 204;
    // Client error
    public static final int CODE_NOT_AUTHENTICATED = 401;
    public static final int CODE_NOT_ALLOWED = 403;
    public static final int CODE_VALIDATION_ERROR = 422;

    // For auth
    public static String ACCESS_TOKEN = "";
    static HttpClient httpClient = HttpClientBuilder.create().build();

    public static int Authentication(String login, String password, String twofa_code) {
        try {
            // Details for post
            JSONObject params = new JSONObject();
            params.put("login", login);
            params.put("password", password);
            params.put("twofa_code", twofa_code);
            params.put("remember", true);
            StringEntity payload = new StringEntity(params.toString());

            HttpPost request = new HttpPost(basicURL + "auth/login");
            request.addHeader("content-type", "application/json");
            request.setEntity(payload);
            HttpResponse response = httpClient.execute(request);

            // Get body entity
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            // Get access token
            Object obj = new JSONParser().parse(result);
            JSONObject jo = (JSONObject) obj;

            ACCESS_TOKEN = (String) jo.get("access_token");

            return response.getStatusLine().getStatusCode();

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int CheckAuthentication(){
        try {
            HttpGet request = new HttpGet(basicURL + "auth/check");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            HttpResponse response = httpClient.execute(request);

            return response.getStatusLine().getStatusCode();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int Logout(){
        try {

            HttpPost request = new HttpPost(basicURL + "auth/logout");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);

            int CODE_STATUS = response.getStatusLine().getStatusCode();
            ACCESS_TOKEN = "";
            response.close();

            return CODE_STATUS;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject GetAccount(){
        try {
            HttpGet request = new HttpGet(basicURL + "account");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            HttpResponse response = httpClient.execute(request);

            // Get body entity
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            // Get access token
            Object obj = new JSONParser().parse(result);
            JSONObject jo = (JSONObject) obj;

            return jo;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int CreateFerm(String name, boolean autotags, boolean hiveon){
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
            request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            request.setEntity(payload);
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(request);

            int CODE_STATUS = response.getStatusLine().getStatusCode();
            response.close();

            return CODE_STATUS;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject GetFarms(){
        try {
            HttpGet request = new HttpGet(basicURL + "farms");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            HttpResponse response = httpClient.execute(request);

            // Get body entity
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            // Get access token
            Object obj = new JSONParser().parse(result);
            JSONObject jo = (JSONObject) obj;

            return jo;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject GetEvents(){
        try {
            HttpGet request = new HttpGet(basicURL + "account/events");
            request.setHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            HttpResponse response = httpClient.execute(request);

            // Get body entity
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            // Get access token
            Object obj = new JSONParser().parse(result);
            JSONObject jo = (JSONObject) obj;

            return jo;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
