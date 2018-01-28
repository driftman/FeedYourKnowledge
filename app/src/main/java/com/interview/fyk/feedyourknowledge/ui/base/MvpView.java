package com.interview.fyk.feedyourknowledge.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Base interface that should be used by every view in the MVP pattern.
 **/

public interface MvpView {

    Context getContext();

    void setToolbarTitle(String title);

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void networkConnected();

    void networkNotConnected();

    boolean checkNetworkConnectionState();
}
