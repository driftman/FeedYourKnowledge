package com.interview.fyk.feedyourknowledge.ui.base;

import com.interview.fyk.feedyourknowledge.R;
import com.interview.fyk.feedyourknowledge.utils.NetworkUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by abk on 26/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private static final String PLACEHOLDER = "...";
    // connectivity broadcast receiver
    private BroadcastReceiver connectivityBroadcastReceiver;
    // views
    private Toolbar toolbar;
    private LinearLayout noConnectivity;
    // variables
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(!checkNetworkConnectionState()) {
                    networkNotConnected();
                } else {
                    networkConnected();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onPause();
        registerReceiver(connectivityBroadcastReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectivityBroadcastReceiver);
    }

    protected void initViews() {
        // setting up the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(PLACEHOLDER);
        // setting up the no connectivity available layout
        noConnectivity = (LinearLayout) findViewById(R.id.no_connectivity);
        // custom placeholder
        setToolbarTitle(title);
    }

    @Override
    public void showLoading() {
        //
    }

    @Override
    public void hideLoading() {
        //
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.general_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.general_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean checkNetworkConnectionState() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void networkConnected() {
        noConnectivity.setVisibility(View.GONE);
    }

    @Override
    public void networkNotConnected() {
        noConnectivity.setVisibility(View.VISIBLE);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setToolbarTitle(String title) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title != null ? title : PLACEHOLDER);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public Context getContext() {
        return this;
    }

}
