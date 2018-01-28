package com.interview.fyk.feedyourknowledge.data.network;

import com.interview.fyk.feedyourknowledge.data.model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by abk on 26/01/2018.
 */

public interface RssInterface {
    // the default com.interview.fyk.feedyourknowledge.data call
    @GET("fr/france/rss/")
    Call<Feed> getFranceChannel();
    @GET("en/africa/rss/")
    Call<Feed> getAfricaChannel();
    @GET("en/americas/rss/")
    Call<Feed> getAmericasChannel();
    @GET("en/asia-pacific/rss/")
    Call<Feed> getAsiaChannel();
    @GET("en/europe/rss/")
    Call<Feed> getEuropeChannel();
    @GET("en/middle-east/rss/")
    Call<Feed> getMiddleEastChannel();

}
