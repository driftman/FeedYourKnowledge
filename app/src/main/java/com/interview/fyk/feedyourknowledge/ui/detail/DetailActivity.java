package com.interview.fyk.feedyourknowledge.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.interview.fyk.feedyourknowledge.R;
import com.interview.fyk.feedyourknowledge.ui.base.BaseActivity;
import com.interview.fyk.feedyourknowledge.ui.browser.BrowserActivity;
import com.squareup.picasso.Picasso;

import com.interview.fyk.feedyourknowledge.data.model.FeedItem;

public class DetailActivity extends BaseActivity implements DetailMvpView, View.OnClickListener {

    // statics
    public static final String TAG = DetailActivity.class.getSimpleName();
    public static final String FEED_ITEM_EXTRA = "FEED_ITEM_EXTRA";

    // views
    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewDescription;
    private ImageView imageViewThumbnail;
    private Button buttonViewWebsite;

    // presenter
    private DetailMvpPresenterImpl mPresenter;

    // the object that will hold the feed item
    private FeedItem feedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // gathering the feedItem whether from the intent
        // or the savedInstanceState
        setFeedItem(savedInstanceState);
        mPresenter = DetailMvpPresenterImpl.getInstance();
        mPresenter.onAttach(this);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fillViews();
    }

    protected void initViews() {
        super.initViews();
        // adding the navigation button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textViewTitle = (TextView) findViewById(R.id.title);
        textViewAuthor = (TextView) findViewById(R.id.author);
        textViewDescription = (TextView) findViewById(R.id.description);
        imageViewThumbnail = (ImageView) findViewById(R.id.thumbnail);
        buttonViewWebsite = (Button) findViewById(R.id.go_website);
        // setting up listeners
        buttonViewWebsite.setOnClickListener(this);
        mPresenter.onViewInitialized(feedItem);
    }

    private void fillViews() {
        textViewTitle.setText(feedItem.getTitle());
        textViewAuthor.setText(feedItem.getAuthor());
        textViewDescription.setText(feedItem.getDescription());
        Picasso.with(this)
                .load(feedItem.getThumbnail().getUrl())
                .into(imageViewThumbnail);
    }

    public void goToWebViewActivity(String link) {
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.putExtra(BrowserActivity.URL, link);
        startActivity(intent);
    }

    private void setFeedItem(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            feedItem = savedInstanceState.getParcelable(FEED_ITEM_EXTRA);
        } else {
            feedItem = getIntent().getParcelableExtra(FEED_ITEM_EXTRA);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(feedItem != null)
            savedInstanceState.putParcelable(FEED_ITEM_EXTRA, feedItem);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if(checkNetworkConnectionState())
            goToWebViewActivity(feedItem.getLink());
        else
            onError(R.string.network_not_active);
    }

}
