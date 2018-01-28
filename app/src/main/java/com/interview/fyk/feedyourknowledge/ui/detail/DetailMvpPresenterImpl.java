package com.interview.fyk.feedyourknowledge.ui.detail;

import com.interview.fyk.feedyourknowledge.data.model.FeedItem;

/**
 * Created by abk on 28/01/2018.
 */

public final class DetailMvpPresenterImpl<V extends DetailMvpView> implements DetailMvpPresenter<V> {

    // statics
    public static final String TAG = DetailActivity.class.getSimpleName();
    private static DetailMvpPresenterImpl detailMvpPresenterInstance;

    private V detailMvpView;
    private FeedItem feedItem;
    private Boolean forceUpdate = false;

    private DetailMvpPresenterImpl() {}

    public static DetailMvpPresenterImpl getInstance() {
        if (detailMvpPresenterInstance == null)
            detailMvpPresenterInstance = new DetailMvpPresenterImpl();
        return detailMvpPresenterInstance;
    }

    @Override
    public void saveState(FeedItem feedItem) {
        this.feedItem = feedItem;
    }

    @Override
    public void onViewInitialized(FeedItem feedItem) {
        detailMvpView.setToolbarTitle(feedItem.getTitle());
    }

    @Override
    public void onAttach(V detailMvpView) {
        this.detailMvpView = detailMvpView;
    }

    @Override
    public void onDetach() {
        detailMvpView = null;
        feedItem = null;
    }

    @Override
    public void handleError(Object error) {}
}
