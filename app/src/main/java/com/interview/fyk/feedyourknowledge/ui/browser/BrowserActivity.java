package com.interview.fyk.feedyourknowledge.ui.browser;

import com.interview.fyk.feedyourknowledge.R;
import com.interview.fyk.feedyourknowledge.ui.base.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class BrowserActivity extends BaseActivity implements BrowserMvpView {

    public static final String TAG = BrowserActivity.class.getSimpleName();
    public static final String URL = "URL";
    // MVP Presenter
    private BrowserMvpPresenterImpl mPresenter;
    // Views
    private WebView webView;
    private ProgressBar progressBar;
    // Variables
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        url = savedInstanceState != null ? savedInstanceState.getString(URL) :
                getIntent().getStringExtra(URL);
        mPresenter = BrowserMvpPresenterImpl.getInstance();
        mPresenter.onAttach(this);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    protected void initViews() {
        super.initViews();
        // adding the navigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new FYKWebClient());
        mPresenter.onViewInitialized(url);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void openUrl(String url) {
        webView.loadUrl(url);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (url != null)
            outState.putString(URL, url);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private class FYKWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mPresenter.onWebViewLoadingFinished();
        }
    }
}
