package com.interview.fyk.feedyourknowledge.data.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by abk on 26/01/2018.
 */

public class RssClient {

    private static Retrofit retrofit = null;

    // Configuring HTTP client and TIMEOUT settings
    private static OkHttpClient getRequestHeader() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        return httpClient;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(RssEndPoint.FRANCE_RSS_ENDPOINT)
                    // Parsing Data with SimpleXml
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .client(getRequestHeader())
                    .build();
        }
        return retrofit;
    }
}
