package com.interview.fyk.feedyourknowledge.ui.base;

/**
 * Every presenter in the must implement this method by the MvpView type
 * that should should be attached with.
 **/

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleError(Object error);
}
