package com.yuri.think;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WikiFetcher {

    private long lastRequestTime = -1;
    private long minInterval = 1000;

    public Elements fetchWikipedia(String url) throws IOException {
        sleepIfNeeded();

        Connection conn  = Jsoup.connect(url);
        Document doc = conn.get();

        Element content = doc.getElementById("mw-content-text");

        Elements params = content.select("p");

        return params;
    }

    private void sleepIfNeeded() {
        if(lastRequestTime != -1) {
            long currentTime = System.currentTimeMillis();
            long nextRequestTime = lastRequestTime + minInterval;

            if(currentTime < nextRequestTime) {
                try {
                    Thread.sleep(nextRequestTime - currentTime);
                } catch (InterruptedException e) {
                    System.err.println("Warning : sleep interrupted in fetchWikipedia.");
                }
            }
        }
        lastRequestTime = System.currentTimeMillis();
    }

    public Elements readWikipedia(String url) throws IOException {
        URL realURL = new URL(url);

        String filename = realURL.getHost() + realURL.getPath();

        InputStream stream = WikiFetcher.class.getClassLoader().getResourceAsStream(filename);
        Document doc = Jsoup.parse(stream, "UTF-8", filename);

        Element content = doc.getElementById("mw-content-text");
        Elements params = content.select("p");

        return params;
    }

}
