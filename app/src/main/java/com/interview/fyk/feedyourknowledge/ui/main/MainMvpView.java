package com.interview.fyk.feedyourknowledge.ui.main;

import com.interview.fyk.feedyourknowledge.ui.base.MvpView;

import java.util.List;

import com.interview.fyk.feedyourknowledge.data.model.FeedItem;

/**
 * Created by abk on 26/01/2018.
 */

public interface MainMvpView extends MvpView {


    void setFeedList(List<FeedItem> feedItems);

    void showDetail(FeedItem feedItem);

    void showNoConnectivityInfoBar();

    void hideNoConnectivityInfoBar();

    void setRefreshing(Boolean refreshing);

}
