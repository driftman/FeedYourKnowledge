package com.interview.fyk.feedyourknowledge.ui.main;

import com.interview.fyk.feedyourknowledge.ui.base.MvpPresenter;

/**
 * Created by abk on 26/01/2018.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onViewInitialized(Boolean forceUpdate);

}
