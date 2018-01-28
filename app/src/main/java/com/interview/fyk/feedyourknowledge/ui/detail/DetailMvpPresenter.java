package com.interview.fyk.feedyourknowledge.ui.detail;

import com.interview.fyk.feedyourknowledge.ui.base.MvpPresenter;

import com.interview.fyk.feedyourknowledge.data.model.FeedItem;

/**
 * Created by abk on 26/01/2018.
 */

public interface DetailMvpPresenter<V extends DetailMvpView> extends MvpPresenter<V> {

    void saveState(FeedItem feedItem);

    void onViewInitialized(FeedItem feedItem);

}
