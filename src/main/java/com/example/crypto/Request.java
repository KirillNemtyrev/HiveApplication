package com.example.crypto;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Request {
    private static String basicURL = "https://api2.hiveos.farm/api/v2/";

    // Codes
    // Successful
    public static final int CODE_AUTHENTICATION_TOKEN = 200;
    public static final int CODE_AUTHENTICATED_TOKEN = 204;
    // Client error
    public static final int CODE_NOT_AUTHENTICATED = 401;
    public static final int CODE_NOT_ALLOWED = 403;
    public static final int CODE_VALIDATION_ERROR = 422;

    // For auth
    public static String ACCESS_TOKEN = "";
    private String TOKEN_TYPE = "bearer";
    private static int EXPIRES_IN = 0;
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
            //EXPIRES_IN = (int) jo.get("expires_in");

            return response.getStatusLine().getStatusCode();

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static int CheckAuthentication(){
        try {
            HttpGet request = new HttpGet(basicURL + "auth/check");
            request.addHeader("content-type", "application/json");
            request.setHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            HttpResponse response = httpClient.execute(request);

            return response.getStatusLine().getStatusCode();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void GetAccount(){
        try {
            HttpGet request = new HttpGet(basicURL + "account");
            request.addHeader("content-type", "application/json");
            request.setHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            HttpResponse response = httpClient.execute(request);

            // Get body entity
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            System.out.print(result);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
