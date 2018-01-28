package com.interview.fyk.feedyourknowledge.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by abk on 26/01/2018.
 */

@NamespaceList({
        @Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom")
})
@Root(name = "channel", strict = false)
public class Channel {

    @Element(name = "title")
    private String title;

    @ElementList(inline = true, name="item")
    private List<FeedItem> mFeedItems;

    public Channel() {
    }

    public Channel(String title, List<FeedItem> mFeedItems) {
        this.title = title;
        this.mFeedItems = mFeedItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FeedItem> getmFeedItems() {
        return mFeedItems;
    }

    public void setmFeedItems(List<FeedItem> mFeedItems) {
        this.mFeedItems = mFeedItems;
    }

    public Channel(List<FeedItem> mFeedItems) {
        this.mFeedItems = mFeedItems;
    }

    public List<FeedItem> getFeedItems() {
        return mFeedItems;
    }
}
