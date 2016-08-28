package com.ksubaka.album.strategy;

/**
 * Created by zhuanghua on 16/8/28.
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class OmdbStrategy extends ApiStrategy {


    public OmdbStrategy() {
        super("http://www.omdbapi.com/?s=", "get");
    }

    @Override
    public String retrieveData(String searchString) {
        String response = "";

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet(uri + URLEncoder.encode(searchString, "UTF-8"));
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");

            HttpResponse httpResponse = httpClient.execute(request);
            response = EntityUtils.toString(httpResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return response;
    }
}
