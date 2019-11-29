package network;

import org.json.JSONArray;
import org.json.JSONObject;
import ui.Jdraws;
import ui.Jgui;
import ui.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

public class ReadWebPageEx {

    public String city = "";
    String theURL = "";
    BufferedReader br = null;
    Main main = new Main();
    URL url;
    StringBuilder sb = new StringBuilder();
    String line;
    public boolean noCity = true;

    public HttpURLConnection connection;

    public void setURL(Jgui jgui) throws MalformedURLException {
        theURL = "http://api.openweathermap.org/data/2.5/weather?q=";
        System.out.println("Which city are you in right now?");
        jgui.printWords("Which city are you in right now?");
        while (noCity) {
            System.out.print("");
        }
        System.out.println(city);
        theURL = theURL + city + "&APPID=2f1e23b55d3aca190082997fc5e92297";
        Main main = new Main();
        url = new URL(theURL);
    }

    public String getApi(Jgui jgui) throws IOException {

        // I have used code given from the cpsc 210 personal project sample code

        setURL(jgui);
        noCity = true;

        try {
            //Request Setup
            setConnection();
            int status = connection.getResponseCode();
            if (status > 299) {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(url.openStream()));
                read();
            }
            return parse(sb.toString());
        } finally {

            if (br != null) {
                br.close();
            }
            connection.disconnect();
        }
    }

    public void setConnection() throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(20000);
        connection.setReadTimeout(20000);
    }

    public void read() throws IOException {
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
    }

    public String parse(String responseBody) {
        JSONObject body = new JSONObject(responseBody);
        JSONArray weatherArr = body.getJSONArray("weather");
        JSONObject weatherOb = weatherArr.getJSONObject(0);
        return weatherOb.getString("main");
    }
}