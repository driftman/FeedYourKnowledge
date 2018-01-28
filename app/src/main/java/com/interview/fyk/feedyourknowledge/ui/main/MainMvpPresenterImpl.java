package com.interview.fyk.feedyourknowledge.ui.main;

import com.interview.fyk.feedyourknowledge.R;

import android.util.Log;

import com.interview.fyk.feedyourknowledge.data.db.ChannelHelper;
import com.interview.fyk.feedyourknowledge.data.model.Channel;
import com.interview.fyk.feedyourknowledge.data.model.Feed;
import com.interview.fyk.feedyourknowledge.data.network.RssClient;
import com.interview.fyk.feedyourknowledge.data.network.RssInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abk on 26/01/2018.
 */

public final class MainMvpPresenterImpl<V extends MainMvpView>
        implements MainMvpPresenter<V>,
        Callback<Feed> {

    private static final String TAG = MainMvpPresenterImpl.class.getSimpleName();
    private static MainMvpPresenterImpl mainMvpPresenterInstance;

    private Channel channel = null;
    private Call<Feed> rssCall;
    private V mainMvpView;
    private Integer currentFeed = RssOrigin.FRANCE;
    private RssInterface rssInterface;
    private ChannelHelper channelHelper;

    private MainMvpPresenterImpl() {

    }

    public static MainMvpPresenterImpl getInstance() {
        if (mainMvpPresenterInstance == null)
            mainMvpPresenterInstance = new MainMvpPresenterImpl();
        return mainMvpPresenterInstance;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mainMvpView = mvpView;
        rssInterface = RssClient.getClient().create(RssInterface.class);
        channelHelper = ChannelHelper.getInstance(mainMvpView.getContext());
    }

    @Override
    public void onDetach() {
        mainMvpView = null;
        channel = null;
    }

    @Override
    public void handleError(Object error) {

    }

    public boolean isViewAttached() {
        return mainMvpView != null;
    }

    public V getMvpView() {
        return mainMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }


    @Override
    public void onViewInitialized(Boolean forceUpdate) {
        checkViewAttached();
        // showing loading message
        mainMvpView.showLoading();
        mainMvpView.setToolbarTitle(null);
        if (forceUpdate || channel == null) {
            // To make the refresh look great we will
            // try to gather com.interview.fyk.feedyourknowledge.data from three different feeds
            rssCall = getCurrentFeed();
            rssCall.enqueue(this);
        } else {
            feedList();
        }
    }

    private void moveToNextFeed() {
        if (currentFeed < RssOrigin.MIDDLE_EAST)
            currentFeed++;
        else
            currentFeed = RssOrigin.FRANCE;
    }

    private Call<Feed> getCurrentFeed() {
        switch (currentFeed) {
            case RssOrigin.FRANCE:
                return rssInterface.getFranceChannel();
            case RssOrigin.AFRICA:
                return rssInterface.getAfricaChannel();
            case RssOrigin.AMERICAS:
                return rssInterface.getAmericasChannel();
            case RssOrigin.ASIA:
                return rssInterface.getAsiaChannel();
            case RssOrigin.EUROPE:
                return rssInterface.getEuropeChannel();
            case RssOrigin.MIDDLE_EAST:
                return rssInterface.getMiddleEastChannel();
            default:
                return rssInterface.getFranceChannel();
        }
    }

    @Override
    public void onResponse(Call<Feed> call, Response<Feed> response) {
        Log.d(TAG, response.body().toString());
        channel = response.body().getmChannel();
        if (channel != null) {
            // caching the request result
            channelHelper.set(currentFeed.toString(), channel);
        }
        feedList();
        // setting the next rss source
        moveToNextFeed();

    }

    private void feedList() {
        checkViewAttached();
        mainMvpView.setFeedList(channel.getFeedItems());
        mainMvpView.setRefreshing(false);
        // hiding loading message
        mainMvpView.hideLoading();
        mainMvpView.setToolbarTitle(channel.getTitle());
    }

    @Override
    public void onFailure(Call<Feed> call, Throwable t) {
        Log.d(TAG, t.getMessage());
        Channel channel = channelHelper.get(currentFeed.toString());
        if (channel != null) {
            this.channel = channel;
            feedList();
        } else {
            mainMvpView.onError(R.string.network_not_active);
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Presenter.onAttach(MvpView) not called");
        }
    }

    private static class RssOrigin {
        static final int FRANCE = 1;
        static final int AFRICA = 2;
        static final int AMERICAS = 3;
        static final int ASIA = 4;
        static final int EUROPE = 5;
        static final int MIDDLE_EAST = 6;
    }
}
