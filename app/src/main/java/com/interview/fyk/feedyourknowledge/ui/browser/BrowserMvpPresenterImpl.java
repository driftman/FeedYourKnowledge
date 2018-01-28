package com.interview.fyk.feedyourknowledge.ui.browser;

import com.interview.fyk.feedyourknowledge.ui.base.MvpView;

/**
 * Created by abk on 28/01/2018.
 */

public final class BrowserMvpPresenterImpl<V extends BrowserMvpView> implements BrowserMvpPresenter<V> {

    private static BrowserMvpPresenterImpl browserMvpPresenter;

    private V browserMvpView;

    private BrowserMvpPresenterImpl() {}

    public static BrowserMvpPresenterImpl getInstance() {
        if (browserMvpPresenter == null)
            browserMvpPresenter = new BrowserMvpPresenterImpl();
        return browserMvpPresenter;
    }

    @Override
    public void onAttach(V browserMvpView) {
        this.browserMvpView = browserMvpView;
    }

    @Override
    public void onDetach() {
        browserMvpView = null;
    }

    @Override
    public void handleError(Object error) {
        //
    }

    @Override
    public void onViewInitialized(String url) {
        browserMvpView.openUrl(url);
        browserMvpView.setToolbarTitle("FRANCE 24");
    }

    @Override
    public void onWebViewLoadingFinished() {
        if (browserMvpView != null)
            browserMvpView.hideLoading();
    }
}
