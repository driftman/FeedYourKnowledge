package com.interview.fyk.feedyourknowledge.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.interview.fyk.feedyourknowledge.R;
import com.interview.fyk.feedyourknowledge.data.model.CategoryItem;
import com.interview.fyk.feedyourknowledge.ui.base.BaseActivity;
import com.interview.fyk.feedyourknowledge.ui.detail.DetailActivity;

import java.util.List;

import com.interview.fyk.feedyourknowledge.data.model.FeedItem;

public class MainActivity extends BaseActivity implements
        MainMvpView,
        SwipeRefreshLayout.OnRefreshListener {

    private MainMvpPresenterImpl mPresenter;
    private FeedAdapter feedAdapter;
    private RecyclerView itemsListRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout info;
    private TextView infoMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoryItem category = getServiceName(savedInstanceState);
        mPresenter = MainMvpPresenterImpl.getInstance(category);
        mPresenter.onAttach(this);
        initViews();
    }

    @Override
    public void setFeedList(List<FeedItem> feedItems) {
        feedAdapter.setFeedItems(feedItems);
        hideLoading();
    }

    @Override
    public void showNoConnectivityInfoBar() {}

    @Override
    public void hideNoConnectivityInfoBar() {}

    @Override
    public void setRefreshing(Boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void showDetail(FeedItem feedItem) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.FEED_ITEM_EXTRA, feedItem);
        startActivity(intent);
    }

    protected void initViews() {
        super.initViews();
        // setting up the loading view
        info = (RelativeLayout) findViewById(R.id.loading);
        infoMessage = (TextView) findViewById(R.id.info_message);
        itemsListRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_items);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh_feed);
        swipeRefreshLayout.setColorSchemeResources(new int[]{R.color.colorPrimary});
        setUpListeners();
        // setting up the recycler view
        itemsListRecyclerView.setNestedScrollingEnabled(false);
        itemsListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedAdapter = new FeedAdapter(this, this);
        itemsListRecyclerView.setAdapter(feedAdapter);
        // letting the presenter know that the view is ready
        mPresenter.onViewInitialized(false);
    }

    private void setUpListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        // If the loading is triggered from the SwipeRefreshLayout
        // that means that we are forcing the list update
        mPresenter.onViewInitialized(true);
    }

    @Override
    public void onError(@StringRes int resId) {
        super.onError(resId);
        info.setVisibility(View.VISIBLE);
        infoMessage.setText(getString(resId));
        setRefreshing(false);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        info.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        info.setVisibility(View.VISIBLE);
        infoMessage.setText(getString(R.string.loading));
    }

    @Override
    public void networkNotConnected() {
        super.networkNotConnected();
        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void networkConnected() {
        super.networkConnected();
        swipeRefreshLayout.setEnabled(true);
    }

    /**
     * Permet de retourner le nom du service depuis lequel on doit consommer les données
     * @param bundle Bundle contenant les informations
     * @return Nom du service
     */
    private CategoryItem getServiceName(Bundle bundle) {
        if (getIntent().getExtras() != null && getIntent().getExtras().getParcelable(CategoryActivity.CATEGORY_ITEM) != null) {
            return getIntent().getExtras().getParcelable(CategoryActivity.CATEGORY_ITEM);
        } else if (bundle != null && bundle.getString(CategoryActivity.CATEGORY_ITEM) != null) {
            return bundle.getParcelable(CategoryActivity.CATEGORY_ITEM);
        } else {
            return null;
        }
    }
}
