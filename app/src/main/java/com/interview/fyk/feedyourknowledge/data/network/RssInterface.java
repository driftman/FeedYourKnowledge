package com.interview.fyk.feedyourknowledge.data.network;

import com.interview.fyk.feedyourknowledge.data.model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by abk on 26/01/2018.
 */

public interface RssInterface {

    @GET("fr/{serviceName}/rss/")
    Call<Feed> getChannelFromServiceName(@Path("serviceName") String serviceName);

}
