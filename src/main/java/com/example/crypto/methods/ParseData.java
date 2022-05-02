package com.example.crypto.methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class ParseData {

    private static int countCard = 0;

    private static double power_cost = 0.1;
    private static JSONArray videoCard = new JSONArray();
    private static final String basicURL = "https://whattomine.com/";

    public static void parseVideo()
    {
        try {
            String URL = basicURL + "gpus?cost=" + power_cost;
            Document doc = Jsoup.connect(URL).userAgent("Chrome/4.0.249.0 Safari/532.5").get();
            Elements element = doc.body().getElementsByAttributeValue("data-test", "gpu-row");

            countCard = element.size();
            int count = 0;
            for(Element parse : element) {

                // Split for brand and name
                JSONObject data = new JSONObject();
                String name = parse.getElementsByTag("a").get(0).text();
                String temp[] = name.split(" ", 2);
                // 0 - Brand card
                // 1 - Name card
                data.put("brand", temp[0]);
                data.put("name", temp[1]);
                data.put("link", basicURL + parse.select("a").attr("href"));
                data.put("release", parse.getElementsByClass("text-center").get(0).text());
                data.put("revenue", parse.getElementsByClass("text-end table-").get(0).text());
                data.put("profit", parse.getElementsByClass("text-end table-success fw-bold").get(0).text());

                JSONArray getCoins = new JSONArray();
                Elements coins = parse.getElementsByClass("clearfix");
                int index = 0;
                for(Element coin : coins) {
                    JSONObject profit = new JSONObject();

                    profit.put("name", coin.getElementsByTag("strong").get(0).text());
                    profit.put("cost", coin.getElementsByClass("float-end").get(0).text());
                    getCoins.add(index, profit);
                    index++;
                }

                data.put("coins", getCoins);
                videoCard.add(count, data);
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCountCard(){
        return countCard;
    }

    public static void setPowerCost(double cost){
        power_cost = cost;
    }

    public static double getPowerCost(){
        return power_cost;
    }

    public static JSONObject getData(int index){
        return (JSONObject) videoCard.get(index);
    }
}
