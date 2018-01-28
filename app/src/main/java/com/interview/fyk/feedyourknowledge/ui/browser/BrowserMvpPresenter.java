package com.interview.fyk.feedyourknowledge.ui.browser;

import com.interview.fyk.feedyourknowledge.ui.base.MvpPresenter;


/**
 * Created by abk on 28/01/2018.
 */

public interface BrowserMvpPresenter<V extends BrowserMvpView> extends MvpPresenter<V> {

    void onViewInitialized(String url);

    void onWebViewLoadingFinished();

}
