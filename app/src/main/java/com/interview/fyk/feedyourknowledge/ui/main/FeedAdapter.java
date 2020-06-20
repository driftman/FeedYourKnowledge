package com.interview.fyk.feedyourknowledge.ui.main;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.interview.fyk.feedyourknowledge.R;
import com.interview.fyk.feedyourknowledge.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.interview.fyk.feedyourknowledge.data.model.FeedItem;

/**
 * Created by abk on 26/01/2018.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ItemHolder> {

    private Context context;
    private List<FeedItem> feedItems = new ArrayList<>();
    private MainMvpView listener;

    public FeedAdapter(Context context, MainMvpView listener) {
        this.context = context;
        this.listener = listener;
    }

    public FeedAdapter(Context context, MainMvpView listener, List<FeedItem> feedItems) {
        this.context = context;
        this.feedItems = feedItems;
        this.listener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feed_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        FeedItem feedItem = feedItems.get(position);
        holder.bind(context, feedItem, listener);
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
        notifyDataSetChanged();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView author;
        private final TextView publicationTimeAgo;
        private final ImageView thumbnail;

        public ItemHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            author = (TextView) itemView.findViewById(R.id.item_author);
            publicationTimeAgo = (TextView) itemView.findViewById(R.id.item_publication_time_ago);
            thumbnail = (ImageView) itemView.findViewById(R.id.item_thumbnail);
        }

        public void bind(final Context context, final FeedItem feedItem, final MainMvpView listener) {
            title.setText(feedItem.getTitle());
            author.setText(feedItem.getAuthor());
            publicationTimeAgo.setText(timeAgo(feedItem.getPubDate()));
            Picasso.with(context)
                    .load(feedItem.getThumbnail().getUrl())
                    .into(thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.showDetail(feedItem);
                }
            });
        }

        private String timeAgo(String pubDate) {
            String timeAgo = DateUtils.getFormattedTimeAgo(pubDate);
            String formattedTimeAgo = itemView.getContext().getString(R.string.time_ago, timeAgo);
            return formattedTimeAgo;
        }
    }

}
